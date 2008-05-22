#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2007 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2007

"""
DBS data discovery ParamSearch module
"""

# system modules
import os, string, logging, types, time, socket
import httplib, urllib, inspect, urllib2

# DBS  modules
from   DDUtil   import *
from   DDConfig import *

class DDParamServer(DDLogger): 
    def __init__(self,server,verbose=0):
        self.ddConfig   = DDConfig()
        DDLogger.__init__(self,self.ddConfig.loggerDir(),"DDParamServer",verbose)
        self.verbose = verbose
        self.secure  = 0
        if server.find("https://")!=-1:
           server=server.replace("https://","")
           self.secure=1
        elif server.find("http://")!=-1:
           server=server.replace("https://","")
        self.path=""
        self.server  = server
        if server.find("/")!=-1:
           url,path=server.split("/",1)
           self.server=url
           self.path=path

    def sendGetMessage(self,file="index.html",debug=0):
        if debug:
           httplib.HTTPConnection.debuglevel = 1
        if self.secure:
           http_conn = httplib.HTTPSConnection(self.server)
        else:
           http_conn = httplib.HTTPConnection(self.server)
        if self.path:
           file="/%s/%s"%(self.path,file)
        http_conn.request("GET", "/%s"%file)
        response = http_conn.getresponse()
        if response.reason!="OK":
           print response.status,response.reason
           return 0
        else:
           data = response.read()
           return data
        http_conn.close()

    def sendPostMessage(self,method,iParams,debug=0):
        url=self.server+"/"+self.path+"/"+method
        if self.secure:
           url="https://"+os.path.normpath(url)
        else:
           url="http://"+os.path.normpath(url)
        print url,iParams
        data=urllib2.urlopen(url,urllib.urlencode(iParams,doseq=True)).read()
        return data

    def sendPostMessage_v1(self,method,iParams,debug=0):
        if debug:
           httplib.HTTPConnection.debuglevel = 1
        oParams=""
        for key in iParams:
            if len(oParams):
               amp="&"
            else:
               amp=""
            param=iParams[key]
            if type(param) is types.ListType:
               for item in param:
                   if len(oParams):
                      amp="&"
                   else:
                      amp=""
                   oParams=oParams+amp+key+"="+urllib.quote(item)
            else:
               oParams=oParams+amp+key+"="+urllib.quote(param)
        if debug:
           print "Encode parameters",iParams,oParams
        headers = {"Content-type": "application/x-www-form-urlencoded",
                   "Accept": "text/plain"}
        if self.secure:
           conn = httplib.HTTPSConnection(self.server)
           print "Contact HTTPS",self.server
        else:
           conn = httplib.HTTPConnection(self.server)
        if self.path:
           method="/%s/%s"%(self.path,method)
        method=method.replace("//","/")
        print self.server,method,oParams,headers
        conn.request("POST",method, oParams, headers)
        response = conn.getresponse()

        if response.reason!="OK":
           print response.status,response.reason
           return 0
        else:
           data = response.read()
           return data
        http_conn.close()

#
# main
#
if __name__ == "__main__":
    optManager  = DDOptions.DDOptionParser()
    (opts,args) = optManager.getOpt()

    # test ProdRequest status response call
    url="/ProdRequest/getRequestsByDataset"
    server = DDParamServer(server="https://cmsweb.cern.ch/prodrequest",verbose=1)
    params={'primary_dataset':'PhotonJet_500-7000','id':'1'}
    page = server.sendPostMessage(url,params,debug=1)
    print page

    # test Phedex status response call
    url="/cms/test/aprom/phedex/dev/egeland/prod/XML::TransferStatus"
    server = DDParamServer(server="cmsdoc.cern.ch")
    params={'dataset':'/GlobalJun07-A/Online/RAW','se_name':['cmssrm.fnal.gov','cmssrm.cern.ch']}
    page = server.sendPostMessage(url,params,debug=1)
    print page

    # test configuration file server call
#    server = DDParamServer(server="edge.fnal.gov:8888")
#    data = server.sendGetMessage(debug=1)
#    print data

#    param = {}
#    data = server.sendPostMessage("/DBSSearch/paramlist.jsp",param,debug=1)
#    print data

#    pList = []
#    pname0="caloTowers.minimumE"
#    ptype0=1 # 1 is numeric, 2 is a string
#    val0=0
#    op0=0 # <,>,<=,>=,=,!=,like,likeRight,likeLeft
#    param = {'num':1}
#    param['pname0']="caloTowers.minimumE"
#    param['ptype0']=1
#    param['val0']=0
#    param['op0']="<"
#    print "Sending",param
#    
#    data = server.sendPostMessage("/DBSSearch/query.jsp",param,debug=1)
#    print data

