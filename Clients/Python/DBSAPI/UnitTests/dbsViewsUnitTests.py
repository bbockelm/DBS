#!/usr/bin/env python
#pylint: disable-msg=C0301,C0103

"""
Unit test for DBS views
"""

import unittest
import httplib
import urllib
import urllib2
import types
import time
import os
import sys
from xml.dom.minidom import parse, parseString

from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsUtil import *

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)


def parseDBSoutput(data, exclude=None):
    """
    DBS XML parser for DBS server DBS_2_0_6 and later
    """
    dom  = parseString(data)
    datalist = []
    for node in dom.getElementsByTagName('row'):
        olist = []
        for child in node.childNodes:
            subnode = child.firstChild
            if  not subnode:
                continue
            data = subnode.data
            tag = subnode.parentNode.tagName
            if  exclude:
                if  exclude != tag:
                    if  tag.lower().find('creationdate') != -1:
                        data = timeformat(data)
                    olist.append((tag, data))
            else:
                olist.append((tag, data))
#            print "tag ", subnode.parentNode.tagName
#            print "data", data
#            olist.append((subnode.parentNode.tagName,data))
        if  olist:
            if  len(olist) == 1:
                datalist.append(olist[-1])
            else:
                datalist.append(olist)
    return datalist

def timeformat(itime):
    """Return time in CEST timezone as DBS server does"""
    num = eval(itime)
    return time.strftime("%Y-%m-%d %H:%M:%S CEST", time.gmtime(num+60*60*2))

def call(url, params, check=None):
    data   = urllib2.urlopen(url, urllib.urlencode(params, doseq=True))
    result = data.read()
    if  check and result.find('exception') != -1:
        raise Exception(result)
    return result

class testDBS(unittest.TestCase):
    """
    A test class for the DAS DBS module
    """
    def setUp(self):
        """
        set up DAS core module
        """
        self.url = "http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet"
        self.ver = 'DBS_2_0_6'

        
        self.url = api.url()
        self.ver = api.version()
        
        self.params = {'apiversion':self.ver,
                       'api':'executeSummary','begin':'0','end':'1'}

    def test_no_executeSummary(self): 
        params = dict(self.params)
        params['query'] = 'find dataset where dataset like *CRUZET4*'
        result = call(self.url, params)
        expect = """<?xml version='1.0' standalone='yes'?>\n<!-- DBS Version 1 -->\n<dbs>\n<exception message=' ____________ API Invoked executeSummary____________\nInvalid API'  code ='1018' detail ='The api executeSummary provided by the client is not valid'/>\n\n<stack_trace>\n</stack_trace>\n</dbs>\n"""
        self.assertRaises(Exception, expect, result)

    def test_executeSummary_noparams(self): 
        params = dict(self.params)
        params['query'] = 'find dataset where dataset like *CRUZET4*'
        result = call(self.url, params)
        expect = """<?xml version='1.0' standalone='yes'?>\n<!-- DBS Version 1 -->\n<dbs>\n<summary_view>\n<exception>\njava.sql.SQLException: ORA-00936: missing expression\n\n</exception>\n</summary_view>\n<SUCCESS/>\n</dbs>\n"""
        self.assertEqual(expect, result)

    def test_executeSummary_releasesummary(self): 
        selkeys= ['release', 'release.createdate', 'release.createby',
                  'algo.family', 'algo.exe']
#        cond   = "release lile *"
#        query  = "find "+','.join(selkeys)+" where " + cond
        query  = "find "+','.join(selkeys)

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params, check=True)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find release where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='release'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)

    def test_executeSummary_filesummary(self): 
        selkeys= ['file', 'file.createdate', 'file.createby', 'file.checksum', 
                  'file.numevents', 'file.size', 'file.type', 'file.status']
        cond   = "dataset=/test_Primary*"
        query  = "find "+','.join(selkeys)+" where " + cond

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find file where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='file'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)

    def test_executeSummary_runsummary(self): 
        selkeys= ['run', 'run.createdate', 'run.createby', 'run.moddate', 
                  'run.modby', 'run.totlumi', 'run.store', 'run.starttime',
                  'run.endtime', 'run.numevents', 'lumi.startevnum', 
                  'lumi.endevnum', 'count(lumi.evnum)']
        cond   = "run like *"
        query  = "find "+','.join(selkeys)+" where " + cond

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params, check=True)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find run where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='run'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)

    def test_executeSummary_sitesummary(self): 
        selkeys= ['site', 'site.createdate', 'site.createby', 
                  'count(block.dataset)'] 
        cond   = "site like *"
        query  = "find "+','.join(selkeys)+" where " + cond

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params, check=True)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find site where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='site'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)

    def test_executeSummary_primdssummary(self): 
        selkeys= ['primds', 'primds.createdate', 'primds.createby', 
                  'datatype', 'count(procds)'] 
        cond   = "primds like test_Primary_2a01e98c-0c45-48c6-aefc-f445f49408d0"
        query  = "find "+','.join(selkeys)+" where " + cond

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params, check=True)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find primds where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='primds'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)

    def test_executeSummary_procdssummary(self): 
        selkeys= ['procds', 'procds.createdate', 'procds.createby', 
                  'count(block.dataset)', 'sum(block.size)',
                  'sum(block.numfiles)', 'sum(block.numevents)'] 
        cond   = "procds like *"
        query  = "find "+','.join(selkeys)+" where " + cond

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params, check=True)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find procds where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='procds'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)

    def test_executeSummary_tiersummary(self): 
        selkeys= ['tier', 'tier.createdate', 'tier.createby', 
                  'count(block.dataset)']
        cond   = "tier like *"
        query  = "find "+','.join(selkeys)+" where " + cond

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params, check=True)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find tier where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='tier'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)

    def test_executeSummary_datasetsummary(self): 
        selkeys= ['dataset', 'dataset.createdate', 'dataset.createby', 
                  'sum(block.size)', 'count(block.dataset)', 'sum(block.numfiles)',
                  'sum(block.numevents)', 'sum(site)' ]
        cond   = "dataset like *"
        query  = "find "+','.join(selkeys)+" where " + cond

        # call executeQuery
        params = dict(self.params)
        params['query'] = query
        params['api'] = 'executeQuery'
        result = call(self.url, params, check=True)
        dbs_query = []
        for item in parseDBSoutput(result):
            for key, val in item:
                dbs_query.append(val)

        # call executeSummary
        query ="find dataset where " + cond
        params['query'] = query
        params['api'] = 'executeSummary'
        result = call(self.url, params, check=True)
        dbs_summary = []
        for item in parseDBSoutput(result, exclude='dataset'):
            for key, val in item:
                dbs_summary.append(val)
        self.assertEqual(dbs_query, dbs_summary)
def suite():
    suite = unittest.TestSuite()
    suite.addTest(unittest.makeSuite(testDBS))
    return suite
#
# main
#
if __name__ == '__main__':
    unittest.main()

