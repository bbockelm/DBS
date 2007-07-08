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
import httplib, urllib, inspect

# DBS  modules
from   DDUtil   import *
from   DDConfig import *

class DDParamServer(DDLogger): 
    def __init__(self,server,verbose=0):
        self.ddConfig   = DDConfig()
        DDLogger.__init__(self,self.ddConfig.loggerDir(),"DDParamServer",verbose)
        self.verbose = verbose
        self.server     = server

    def sendGetMessage(self,file="index.html",debug=0):
        if debug:
           httplib.HTTPConnection.debuglevel = 1
        http_conn = httplib.HTTPConnection(self.server)
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
        conn = httplib.HTTPConnection(self.server)
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

    # test Phedex status response call
    url="/cms/test/aprom/phedex/dev/egeland/prod/XML::TransferStatus"
    server = DDParamServer(server="cmsdoc.cern.ch")
    params={'dataset':'/GlobalJun07-A/Online/RAW','se_name':['cmssrm.fnal.gov','cmssrc.cern.ch']}
    page = server.sendPostMessage(url,params,debug=1)
    print page

    # test configuration file server call
    server = DDParamServer(server="edge.fnal.gov:8888")
    data = server.sendGetMessage(debug=1)
    print data

    param = {}
    data = server.sendPostMessage("/DBSSearch/paramlist.jsp",param,debug=1)
    print data

    pList = []
    pname0="caloTowers.minimumE"
    ptype0=1 # 1 is numeric, 2 is a string
    val0=0
    op0=0 # <,>,<=,>=,=,!=,like,likeRight,likeLeft
    param = {'num':1}
    param['pname0']="caloTowers.minimumE"
    param['ptype0']=1
    param['val0']=0
    param['op0']="<"
    print "Sending",param
    
    data = server.sendPostMessage("/DBSSearch/query.jsp",param,debug=1)
    print data

