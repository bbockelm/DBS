#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Author:  Valentin Kuznetsov

"""
Phedex tools
"""

import os
import sys
import types
import string
import urllib
import urllib2
import traceback

class PhedexManager(object):
    """
       Class responsibile for phedex services
    """
    def __init__(self, sitedbmgr, verbose = None):
        self.verbose = verbose
        self.sitedb  = sitedbmgr
        self.server  = 'https://cmsweb.cern.ch'
        self.url     = "/phedex/datasvc/json/prod/"
        self.ctype   = 'application/json'
        self.se_cache = {}

    def run(self, method, params):
        url = self.server+self.url+method
        datastr = urllib2.urlopen(url,
                         urllib.urlencode(params, doseq=True)).read()
        if  str(datastr).lower().find("error") != -1:
            raise Exception("Phedex error '%s' " % datastr)
        data = eval(compile(datastr.replace('null','None'), '<string>', 'eval'))
        return data

    def siteCache(self, site):
        """
        Keep local cache of se:site mapping
        """
        if  site.find(".") != -1:
            se   = site
            site = self.sitedb.getCMSName(site)
        else:
            se   = self.sitedb.getSEName(site)
        nodes   = []
        if  not self.se_cache.has_key(se):
            nodes = self.sitedb.getNodes(site)
            self.se_cache[se] = nodes
        else:
            nodes = self.se_cache[se]
        return nodes

    def getFiles(self, lfn, se, protocol = 'rfio'):
        """
           use phedex data service to look-up pfn from given lfn
        """
        nodes   = self.siteCache(se)
        params  = {'node' : nodes, 'lfn' : lfn, 'protocol' : protocol}
        method  = 'lfn2pfn'
        data    = self.run(method, params)
        pfnList = []
        for item in data['phedex']['mapping']:
            pfn = item['pfn']
            if  pfn:
                pfnList.append(pfn)
        return pfnList

    def nodes(self, site = ''):
        """
           nodes phedex parser
        """
        method   = 'nodes'
        params   = {'noempty':''}
        if  site:
            params = {'node':self.siteCache(site)}
        data     = self.run(method, params)
        dataDict = data["phedex"]["node"] 
        oList    = []
        titles   = []
        lenList  = []
        for item in dataDict:
            # {u'kind': u'Disk', u'technology': u'DPM', 
            #  u'name': u'T3_TW_NCU', 
            #  u'se': u'grid02.phy.ncu.edu.tw', u'id': u'801'}
            if  type(item) is not types.DictType:
                continue
            if  not titles:
                titles  = item.keys()
                lenList = [len(t) for t in titles]
            iList = []
            vList = list(item.values())
            for idx in range(0, len(vList)):
                elem = vList[idx]
                if  not elem: 
                    elem = "N/A"
                if  lenList[idx] < len(elem):
                    lenList[idx] = len(elem)
                iList.append(elem)
            oList.append(iList)
        return oList, titles
      
#
# main
#
if __name__ == "__main__":
    from utils.sitedb_tools import SiteDBManager
    from utils.siteconfig_tools import SiteConfigManager
    sdbmgr   = SiteDBManager()
    sitemgr  = SiteConfigManager(sdbmgr)
    mgr      = PhedexManager(sdbmgr)

    lfn      = '/store/mc/CSA08/JetET150/GEN-SIM-RECO/CSA08_S156_v1/0005/F28BF340-122F-DD11-AE66-001A4BD22406.root'
    site     = 'T2_DE_DESY'
    protocol = sitemgr.protocol(site)
    se       = sdbmgr.getSEName(site)
    print mgr.getFiles(lfn, se, protocol)
    print mgr.nodes('T1_US_FNAL')
    print mgr.nodes(se)
