#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
# Author:  Valentin Kuznetsov, 2008

import os
import urllib
import urllib2
import traceback
import elementtree.ElementTree as ET

def parseDBSerror(data):
    """
    parse DBS error in returned XML
    """
    elem  = ET.fromstring(data)
    oList = [] # results
    for i in elem:
        if  i.tag == "exception":
            code = i.attrib['code']
            msg  = i.attrib['message']
            det  = i.attrib['detail']
            det  = det.replace('QUERY','\nQUERY')
            det  = det.replace('POSITION','\nPOSITION')
            return 'DBS returns:\ncode   = %s,\nmsg    = %s,\ndetail = %s' % \
                (code, msg, det)

class DbsApi2(object):
    """
    DBS manager which construct DBS API based on DBS RS service
    """
    def __init__(self, config):
        self.url    = config['url']
        self.ctype  = 'text/xml'
        self.params = {'apiversion': config['version'], 'api': 'executeQuery'}

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
#
# main
#
if __name__ == "__main__":
    config = {'version':'DBS_2_0_4','url':'http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet'}
    dbsapi = DbsApi2(config)
    query = 'find site where site like *'
    data = dbsapi.executeQuery(query,0,10)
    print data
