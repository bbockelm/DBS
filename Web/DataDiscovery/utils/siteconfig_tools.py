#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Author:  Valentin Kuznetsov

"""
SiteConfig tools
"""

import os
import sys
import types
import string
import urllib
import urllib2
import traceback

class SiteConfigManager(object):
    """
       Handle site configuration
    """
    def __init__(self, sdbmgr, verbose = None):
        self.sdb     = sdbmgr
        self.verbose = verbose
        self.url     = '/cgi-bin/cmssw.cgi/COMP/SITECONF'
        self.server  = 'http://cmssw.cvs.cern.ch'
        self.ctype   = 'text/plain'

    def protocol(self, site):
        if  site.find(".") != -1:
            site = self.sdb.getCMSName(site)
        url    = '/%s/JobConfig/site-local-config.xml' % site
        params = {'revision' : '1.3', 'content-type':self.ctype} 
        url    = self.server+self.url + url
        if  self.verbose:
            print 'SiteConfig URL', url
        data   = urllib2.urlopen(url,
                         urllib.urlencode(params, doseq=True)).read()
        protocol = ''
        event = 0
        for line in data.split('\n'):
            if  line.find('<event-data>') != -1:
                event = 1
            if  event and line.find('<catalog') != -1:
                protocol = line.split('"')[1].split('protocol=')[-1]
                break
            if  line.find('</event-data>') != -1:
                event = 0
        return protocol
#
# main
#
if __name__ == "__main__":
    from utils.sitedb_tools import SiteDBManager
    sdbmgr  = SiteDBManager()
    sitemgr = SiteConfigManager(sdbmgr)
    print sitemgr.protocol('T1_US_FNAL')
