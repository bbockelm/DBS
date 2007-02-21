#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2007 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2007

"""
DBS data discovery Lucene module
"""

# system modules
import os, string, logging, types, time, socket
import httplib, urllib, inspect

# DBS  modules
import CheetahDBSTemplate
from   DBSUtil   import *
from   DDConfig  import *

class DDLucene(DBSLogger): 
    def __init__(self,verbose=0,profile=0):
        DBSLogger.__init__(self,"DDLucene",verbose)
#        self.ddConfig   = DBSDDConfig()
#        self.luceneHost = self.ddConfig.luceneHost()
#        self.lucenePort = self.ddConfig.lucenePort()
        self.luceneHost = "http://kusanagi.rcac.purdue.edu"
        self.lucenePort = 8080
        self.luceneHost = "http://www.google.com"
        self.lucenePort = 80
        self.lucene     = "kusanagi.rcac.purdue.edu:8080"

    def sendGetMessage(self,file="index.html",debug=0):
        if debug:
           httplib.HTTPConnection.debuglevel = 1
        http_conn = httplib.HTTPConnection(self.lucene)
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
        params = urllib.urlencode(iParams)
        print params
        headers = {"Content-type": "application/x-www-form-urlencoded",
                   "Accept": "text/plain"}
        conn = httplib.HTTPConnection(self.lucene)
        conn.request("POST",method, params, headers)
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
    optManager  = DBSOptions.DBSOptionParser()
    (opts,args) = optManager.getOpt()
    lucene = DDLucene()
    data = lucene.sendGetMessage(debug=1)
    print data

    param = {'method':'parameters'}
    data = lucene.sendPostMessage("/DBSLookupWeb/DBSLookup",param,debug=1)
    print data

    param = {'method':'lookup','term':'block.subsys.NumberParameter129=111'}
    data = lucene.sendPostMessage("/DBSLookupWeb/DBSLookup",param,debug=1)
    print data
