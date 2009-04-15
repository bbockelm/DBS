#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
# Author:  Valentin Kuznetsov, 2008

import os
import sys
import socket
import urllib
import urllib2
import traceback
import elementtree.ElementTree as ET
from   DBSAPI.dbsApi import DbsApi

socket.setdefaulttimeout(30000)

def parseDBSerror(data):
    """
    parse DBS error in returned XML
    """
    elem = ""
    try:
        elem  = ET.fromstring(data)
    except:
        print "\n### parseDBSerror", data
        traceback.print_exc()
	sys.__stdout__.flush()
        data = data.replace("<", "&lt;").replace(">", "&gt;")
        msg = "Fail to parse DBS XML, please see server log\n" + data
        raise Exception(msg)
    oList = [] # results
    msg = ""
    for i in elem:
        if  i.tag == "exception":
	    if  i.attrib.has_key('code') and \
		i.attrib.has_key('message') and \
		i.attrib.has_key('detail'):
		code = i.attrib['code']
		msg  = i.attrib['message']
		det  = i.attrib['detail']
		det  = det.replace('QUERY','\nQUERY')
		det  = det.replace('POSITION','\nPOSITION')
		msg += 'DBS returns:\ncode   = %s,\nmsg    = %s,\ndetail = %s' % \
		    (code, msg, det)
    return msg

class DbsApi2(object):
    """
    DBS manager which construct DBS API based on DBS RS service
    """
    def __init__(self, config):
        self.url    = config['url']
        self.ctype  = 'text/xml'
        self.params = {'apiversion': config['version'], 'api': 'executeQuery'}
        self.config = config

    def getHelp(self, arg=""):
        dbsApi = DbsApi(self.config)
        return dbsApi.getHelp(arg)

    def executeQuery(self, query, begin = "", end = "", type = "query"):
        params = dict(self.params)
        params['query'] = query
        params['type']  = type
        params['begin'] = begin
        params['end']   = end
        params['content-type'] = self.ctype
        data   = urllib2.urlopen(self.url,
                         urllib.urlencode(params, doseq=True)).read()
        error  = parseDBSerror(data)
        if  error:
            raise Exception(error)
        return data

    def countQuery(self, query):
        """
            should be replace with real countQuery once it's presented on server
        """
        params = dict(self.params)
        params['api']   = 'countQuery'
        params['query'] = query
        params['type']  = 'exe'
        params['begin'] = ''
        params['end']   = ''
        params['content-type'] = self.ctype
        data   = urllib2.urlopen(self.url,
                         urllib.urlencode(params, doseq=True)).read()
        error  = parseDBSerror(data)
        if  error:
            raise Exception(error)
        return data

    def executeSummary(self, query, begin = "", end = "", sortKey = "", sortOrder = ""):
        params = dict(self.params)
        params['api']   = 'executeSummary'
        params['query'] = query
        params['begin'] = begin
        params['end']   = end
        params['sortKey']  = sortKey
        params['sortOrder']  = sortOrder
        params['content-type'] = self.ctype
        data   = urllib2.urlopen(self.url,
                         urllib.urlencode(params, doseq=True)).read()
        error  = parseDBSerror(data)
        if  error:
            raise Exception(error)
        return data

#
# main
#
if __name__ == "__main__":
#    config = {'version':'DBS_2_0_5','url':'http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet'}
    config = {'version':'DBS_2_0_6','url':'http://localhost:8880/DBS_VK/servlet/DBSServlet'}
    dbsapi = DbsApi2(config)
    query = 'find site where site like *'
    print "\nCall executeQuery"
    data = dbsapi.executeQuery(query,0,10,"exe")
    print data
    print "\nCall countQuery"
    data = dbsapi.countQuery(query)
    print data
    print "\nCall executeSummary"
    data = dbsapi.executeSummary("find site", 0, 10)
    print data
