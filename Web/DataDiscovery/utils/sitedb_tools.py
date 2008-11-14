#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Author:  Valentin Kuznetsov

"""
SiteDB tools
"""

import os
import sys
import types
import string
import urllib
import urllib2
import traceback

class SiteDBManager(object):
    """
       Class responsibile for sitedb services
    """
    def __init__(self, verbose = None):
        self.verbose = verbose
        self.url     = "/sitedb/json/index/"
        self.server  = "https://cmsweb.cern.ch"
        self.ctype   = 'application/json'
        
    def getCMSName(self, se):
        """
           get CMS name for given SE
        """
        params = {'name' : se} 
        url = self.server+self.url + "SEtoCMSName"
        datastr = urllib2.urlopen(url,
                         urllib.urlencode(params, doseq=True)).read()
        data = eval(compile(datastr, '<string>', 'eval'))
        if  data and type(data) is types.DictType:
            return data['0']['name']
        return None
        
    def getSEName(self, site):
        """
           get SE name for given CMS name
        """
        params = {'name' : site} 
        url = self.server+self.url + "CMSNametoSE"
        datastr = urllib2.urlopen(url,
                         urllib.urlencode(params, doseq=True)).read()
        data = eval(compile(datastr, '<string>', 'eval'))
        if  data and type(data) is types.DictType:
            return data['0']['name']
        return None

    def getNodes(self, site):
        """
           get PhEDEx node name for given CMS name
        """
        params = {'cms_name' : site} 
        url = self.server+self.url + "CMSNametoPhEDExNode"
        datastr = urllib2.urlopen(url,
                         urllib.urlencode(params, doseq=True)).read()
        data    = eval(compile(datastr, '<string>', 'eval'))
        nodes   = []
        if  data and type(data) is types.DictType:
            for val in data.values():
                nodes.append(val['phedex_node'])
            return nodes
        return nodes
#
# main
#
if __name__ == "__main__":
    mgr = SiteDBManager()
    print mgr.getSEName('T1_US_FNAL')
    print mgr.getSEName('T2_DE_DESY')
    print mgr.getCMSName('srm-cms.cern.ch')
    print mgr.getNode('T1_US_FNAL')

