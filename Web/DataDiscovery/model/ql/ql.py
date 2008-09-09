#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008

"""
Data Discovery Queyr Language
"""

# system modules
import os, string, types, traceback
import xml.sax, xml.sax.handler
from   xml.sax.saxutils import escape
from xml.dom import *

# experiment
from model.dd.DDQueryMaker import *
from utils.DDUtil import *

# DBS framework in order to make migration
from DBSAPI.dbsApi import DbsApi

class QL(object):
    def __init__(self,verbose=0):
        self.dbs       = DBSGLOBAL
        self.verbose   = verbose
        self.dbManager = DBManager('OBSOLETE need to be removed',self.verbose)
        self.qmaker    = DDQueryMaker(self.dbManager,self.dbs)
        self.dbsConfig = {}
        self.dbsApi    = {}
        self.dbsConfig = {'mode':'POST','version':'DBS_1_2_1','retry':2}
        print "+++ Init QL"

    def getDbsApi(self,dbsInst):
        dbsUrl=DBS_INST_URL[dbsInst]
        dbsUrl=dbsUrl.replace('https','http').replace('_writer','').replace(':8443','')
        if self.verbose:
           print "dbsUrl",dbsUrl
        self.dbsConfig['url']=dbsUrl
        if self.dbsApi.has_key(dbsInst):
           return self.dbsApi[dbsInst]
        else:
           dbsApi = DbsApi(self.dbsConfig)
           self.dbsApi[dbsInst]=dbsApi
           return dbsApi

    def queryDBS(self,**kwargs):
        try:
            userInput=getArg(kwargs,'userInput',"").strip()
#            idx=int(getArg(kwargs,'page',0))
#            pagerStep=int(getArg(kwargs,'index',RES_PER_PAGE))
            idx=int(getArg(kwargs,'page',-1))
            pagerStep=int(getArg(kwargs,'index',-1))
            qtype=getArg(kwargs,'type',"query")
            dbsInst=getArg(kwargs,'dbsInst',DBSGLOBAL)

            dbsApi = self.getDbsApi(dbsInst)
            if  self.verbose:
                print dbsApi.getServerUrl()
            if  idx==-1 and pagerStep==-1:
                fromRow=""
                toRow=""
            else:
                fromRow=idx*pagerStep
                toRow=idx*pagerStep+pagerStep
            if  pagerStep==-1:
                res=dbsApi.executeQuery(userInput,type=qtype)
            else:
                res=dbsApi.executeQuery(userInput,begin=fromRow,end=toRow,type=qtype)
        except:
            return traceback.format_exc()
        return res

    def fetch(self,userInput,**kwargs):
        what = getArg(kwargs,'return',"exe")
        kwargs['userInput']=userInput
        if what=="total":
           return self.nResults(**kwargs)
        elif what=="query":
           return self.query(**kwargs)
        else:
           return self.execute(**kwargs)

    def query(self,**kwargs):
        res = self.queryDBS(**kwargs)
        return res
#        sql,bindDict,count_sql,count_bindDict=getDBSQuery(res)
#        return (sql.replace('\n',''),bindDict,count_sql.replace('\n',''),count_bindDict)

    def execute(self,**kwargs):
        kwargs['type']="exe"
        res = self.queryDBS(**kwargs)
        return res

    def nResults(self,**kwargs):
        res = self.queryDBS(**kwargs)
        sql,bindDict,count_sql,count_bindDict=getDBSQuery(res)
        return self.qmaker.executeDBSCountQuery(count_sql,count_bindDict,iface="dbsapi")
#
#
#
if __name__=="__main__":
    ql = QL()
    input="find dataset where dataset like *"
    print ql.nResults(userInput=input)
    print ql.query(userInput=input)
    print ql.execute(userInput=input)

