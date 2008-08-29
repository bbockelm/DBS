#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008
#
# This work based on example from CherryPy Essentials book, by Sylvain Hellegouarch
"""
REST resource class which handle all requests.

-----------------------------------------------------------------------------------------
HTTP method     Idempotent      Operation
-----------------------------------------------------------------------------------------
HEAD            YES             Retrieves the resource metadata. The response is the same 
                                as  the one to a GET minus the body.
GET             YES             Retrieves resource metadata and content
POST            NO              Requests the server to create a new resource
                                using the data enclosed in the request body
PUT             YES             Requests the server to replace an existing
                                resource with the one enclosed in the request
                                body. The server cannot apply the enclosed
                                resource to a resource not identified by that URL
DELETE          YES             Requests the server to remove the resource
                                identified by that URL
OPTIONS         YES             Requests the server to return details about capabilities
                                either globally or specifically towards a resource.
-----------------------------------------------------------------------------------------
"""

import cherrypy, traceback, simplejson, time
from cherrypy.lib.cptools import accept
import elementtree.ElementTree as ET

# Cheetah template modules
from   Cheetah.Template import Template
from   Templates   import *

__all__ = ['Resource','DataCache']

def genTopHTML(host,url,mastheadUrl,footerUrl,dbsInst,userMode="user",onload=""):
    nameSpace={
               'host'        : host,
               'baseUrl'     : url,
               'mastheadUrl' : mastheadUrl,
               'footerUrl'   : footerUrl,
               'title'       : 'DBS Data Discovery Page',
               'dbsGlobal'   : dbsInst,
               'userMode'    : userMode,
               'onload'      : onload
              }
    t = templateTop(searchList=[nameSpace]).respond()
    return str(t)

def genBottomHTML():
    nameSpace = { 'localtime':time.asctime() }
    t = templateBottom(searchList=[nameSpace]).respond()
    return str(t)

def dictToHTML(dict):
    o="""<table class="dbs_table">\n"""
    o+="<tr>\n"
    for key in dict.keys():
        o+="<th>%s</th>\n"%key
    o+="</tr>\n"
    arrays = dict.values()
    master = arrays[0]
    counter= 0
    for idx in range(0,len(master)):
        if counter%2:
           style='class="zebra"'
        else:
           style=""
        o+="<tr %s>\n"%style
        for arr in arrays:
            o+="<td>%s</td>\n"%arr[idx]
        o+="</tr>\n"
        counter+=1
    o+="</table>"
    return o

def decodeDBSXML(data,tag="result"):
    try:
        elem  = ET.fromstring(data)
    except:
        msg="ERROR: fail to decode data"+str(data)
        raise msg
    sql   = ""
    oDict = {}
    for i in elem:
        if i.tag=="result":
           for k in i.attrib:
               res = i.attrib[k]
               if oDict.has_key(k): oDict[k]+=[res]
               else: oDict[k]=[res]
    return oDict

class DataCache(object):
    def __init__(self,data=None):
        self.data = data
    def to_xml(self):
        return self.data
    def to_json(self):
        return simplejson.dumps(decodeDBSXML(self.data), sort_keys=True, indent=4)
    def to_html(self,host,url,mastheadUrl,footerUrl,dbsInst):
        try:
            page = genTopHTML(host,url,mastheadUrl,footerUrl,dbsInst)
            page+= "<h3>Data Discovery REST services: </h3>"
            page+= dictToHTML(decodeDBSXML(self.data))
            page+= genBottomHTML()
        except:
            page ="Unsupported accept type: <pre>%s</pre>"%self.data.replace("<","&lt;").replace(">","&rt;")
        return page

class Resource(object):
    def __init__(self,verbose=0):
        self.formatter    = DataCache()
        self._verbose     = verbose
        self.xmlReturnType= 'application/xml'
        self.supportTypes =['application/xml', 'application/atom+xml',
                            'text/json', 'text/x-json', 'application/json',
                            'text/html']
        self.defaultType  = 'text/html'
        print "+++ Init Resource",self._url
        
    @classmethod
    def printResponse(self):
        headers = cherrypy.response.headers
        for key in headers:
            print "%s: %s"%(key,headers[key])
        print cherrypy.response.body
        print cherrypy.response.status

    @classmethod
    def query(self,params):
        # analyze input and construct a query out of it
        # for example:
        #     http://host/service/rest/site/
        #     http://host/service/rest/site/srm.cern.ch
        #     http://host/service/rest/site/srm.cern.ch/dataset
        #     http://host/service/rest/site/srm.cern.ch/dataset,block
        #     http://host/service/rest/block/123456/lfn
        query = None
        lookup='dataset' # default
        try:
            method=params[0]
            if  len(params)>1:
                rvalue=params[1]
                if  len(params)==3:
                    lookup=params[2]
                    if not lookup: lookup='dataset'
                query = "find %s where %s=%s"%(lookup,method,rvalue)
            else:
                query = "find %s where %s like *"%method
        except:
            traceback.print_exc()
            msg = "Cannot construct query, not enough arguments"
            raise msg
        if  self._verbose:
            print "Calling query: '%s'"%query
        return query

    @classmethod
    def getQuery(self,params,kwargs):
        # We lookup for an data for our query
        q = self.query(params)
        if not self._model:
           self._model = self._source_class()
        obj = self._model
        kwargs['return']='query' # ask to get back the query itself rather then data
        return obj.fetch(q,**kwargs)

    @classmethod
    def getData(self,params,kwargs):
        # We lookup for an data for our query
        q = self.query(params)
        if not self._model:
           self._model = self._source_class()
        obj = self._model
        return obj.fetch(q,**kwargs)

    @classmethod
    def createData(self,params,kwargs):
        print "\n+++ Need to implement createData"
        # We lookup for an data for our query
        q = self.query(params)
        if not self._model:
           self._model = self._source_class()
        obj = self._model
        return obj.fetch(q,**kwargs)

    @classmethod
    def updateData(self,params,kwargs):
        print "\n+++ Need to implement updateData"
        # We lookup for an data for our query
        q = self.query(params)
        if not self._model:
           self._model = self._source_class()
        obj = self._model
        return obj.fetch(q,**kwargs)

    @classmethod
    def deleteData(self,params,kwargs):
        print "\n+++ Need to implement deleteData"
        # We lookup for an data for our query
        q = self.query(params)
        if not self._model:
           self._model = self._source_class()
        obj = self._model
        return obj.fetch(q,**kwargs)

    @classmethod
    def setHeader(self,ctype,length=None):
       cherrypy.response.headers['Content-Type'] = ctype
       if length: cherrypy.response.headers['Content-Length'] = length

    def response(self,iData,method):
        # inspect Accept header for matching type
        dataType = accept(self.supportTypes)
        # get data-formatter instance
        form = DataCache(iData)
        # look-up data in appropriate format for our object
        data = None
        rType= None
        if dataType in ['application/xml', 'application/atom+xml']:
           data = form.to_xml()
           rType= self.xmlReturnType
        elif dataType in ['text/json', 'text/x-json', 'application/json']:
           data = form.to_json()
           rType= dataType
        elif dataType in ['text/html']:
           data = form.to_html(self._host,self._url,self._mUrl,self._fUrl,self._dbs)
           rType= dataType
        else:
           data = iData
           rType= self.defaultType

        if  method=="head":
            self.setHeader(rType,len(data))
            return
        else:
            self.setHeader(rType)

        if  self._verbose:
            self.printResponse()
            print "return data='%s'"%data
        if  data:
            return data
        raise cherrypy.HTTPError(400, 'Bad Request')

    def checkAccept(self):
        reqAccept = cherrypy.request.headers['Accept']
        error = None
        if reqAccept.find(",")!=-1:
           if self._verbose:
              print "\n+++ Reqested multiple types",reqAccept,
           aList = reqAccept.split(",")[0]
           type  = None
           if reqAccept.find(self.defaultType)!=-1:
              type=self.defaultType
           else:
              for t in aList:
                  if t in self.supportTypes:
                     type=t
                     break
           if not type: 
              return
           cherrypy.request.headers['Accept']=type
           if self._verbose:
              print "will use",type
#        elif reqAccept=='*/*' and reqAccept not in self.supportTypes:
#           print "\n+++ Requested to accept",reqAccept,"will use default",self.defaultType
#           cherrypy.request.headers['Accept']=self.defaultType
        elif reqAccept not in self.supportTypes:
           error = 1
        else:
           error = None

        if  not cherrypy.request.headers.elements('Accept'):
            error = 1
        if  error:
            msg ="Not Acceptable, missing Accept attr in a header:"+str(cherrypy.request.headers)
            msg+="Data Discovery REST services accept the following types"
            msg+=str(self.supportTypes)
            raise cherrypy.HTTPError(406, msg)

    def handle_HEAD(self, *args, **kwargs):
        if  self._verbose:
            print "Calling handle_HEAD",args,kwargs
        self.checkAccept()
        data = self.getQuery(args[0],kwargs) # we just ask about what query correspond to request
        if not data:
           raise cherrypy.NotFound()
        # generate response
        self.response(data,"head")
    
    def handle_GET(self, *args, **kwargs):
        if  self._verbose:
            print "Calling handle_GET",args,kwargs
        self.checkAccept()
        data = self.getData(args[0],kwargs)
        if not data:
           raise cherrypy.NotFound()
        # generate response
        return self.response(data,"get")

    def handle_POST(self, *args, **kwargs):
        if  self._verbose:
            print "Calling handle_POST",args,kwargs
        self.checkAccept()
        params = args[0]
        data = self.createData(params,kwargs)

        # set appropriate headers for POST method
        cherrypy.response.status = '201 Created'
        location = '%s/%s'%(self._url,'/'.join(params))
        cherrypy.response.headers['Location'] = location

        # generate response
        return self.response(data,"post")

    def handle_PUT(self, *args, **kwargs):
        if  self._verbose:
            print "Calling handle_PUT",args,kwargs
        self.checkAccept()
        data = self.updateData(args[0],**kwargs)
        if not data:
           raise cherrypy.NotFound()
        # generate response
        return self.response(data,"put")

    def handle_DELETE(self, *args, **kwargs):
        if  self._verbose:
            print "Calling handle_DELETE",args,kwargs
        self.checkAccept()
        data = self.deleteData(args[0],kwargs)
        if not data:
           raise cherrypy.NotFound()
        # generate response
        return self.response(data,"delete")

