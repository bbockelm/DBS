#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008

"""
DBS data discovery command line interface
"""

import  xml.sax, xml.sax.handler
from xml.sax.saxutils import escape

import httplib, urllib, types, string, os, sys
from optparse import OptionParser

def help():
    h ="""
"""
    return h

class DDOptionParser: 
  """
     DDOptionParser is main class to parse options for L{DDHelper} and L{DDServer}.
  """
  def __init__(self):
    self.parser = OptionParser()
    self.parser.add_option("--dbsInst",action="store", type="string", dest="dbsInst",
         help="specify DBS instance to use, e.g. --dbsInst=cms_dbs_prod_global")
    self.parser.add_option("-v","--verbose",action="store", type="int", default=0, dest="verbose",
         help="specify verbosity level, 0-none, 1-info, 2-debug")
#    self.parser.add_option("-q","--quit",action="store", type="int", default=0, dest="quiet",
#         help="quiet output, not summary details about datasets, non-zero value for being quiet")
    self.parser.add_option("--input",action="store", type="string", default=False, dest="input",
         help="specify input for your request.")
    self.parser.add_option("--host",action="store",type="string",dest="host",
         help="specify a host name of Data Discovery service, e.g. https://cmsweb.cern.ch/dbs_discovery/")
#    self.parser.add_option("--port",action="store",type="string",dest="port",
#         help="specify a port number of Data Discovery service")
    self.parser.add_option("--page",action="store",type="string",default="0",dest="page",
         help="specify output page, should come together with --limit")
    self.parser.add_option("--limit",action="store",type="string",default="10",dest="limit",
         help="specify a limit on output, e.g. 50 results, should come together with --pager")
#    self.parser.add_option("--show",action="store",type="string",default="10",dest="show",
#         help="specify which information to show about datasets, like --filter=lfn will show LFNs,etc. Tools accepts the following filters: lfn, se, run")
  def getOpt(self):
    """
        Returns parse list of options
    """
    return self.parser.parse_args()

def sendMessage(host,port,dbsInst,userInput,page,limit,debug=0):
    """
       Send message to server, message should be an well formed XML document.
    """
    input=urllib.quote(userInput)
    if debug:
       httplib.HTTPConnection.debuglevel = 1
       print "Contact",host,port
    _port=443
    if host.find("http://")!=-1:
       _port=80
    if host.find("https://")!=-1:
       _port=443
    host=host.replace("http://","").replace("https://","")
    if host.find(":")==-1:
       port=_port
    prefix_path=""
    if host.find("/")!=-1:
       hs=host.split("/")
       host=hs[0]
       prefix_path='/'.join(hs[1:])
    port=int(port)
    if port==443:
       http_conn = httplib.HTTPS(host,port)
    else:
       http_conn = httplib.HTTP(host,port)
    path='/aSearch?dbsInst=%s&html=0&_idx=%s&pagerStep=%s&userInput=%s'%(dbsInst,page,limit,input)
    if prefix_path:
       path="/"+prefix_path+path[1:]
    http_conn.putrequest('POST',path)
    http_conn.putheader('Host',host)
    http_conn.putheader('Content-Type','text/html; charset=utf-8')
    http_conn.putheader('Content-Length',str(len(input)))
    http_conn.endheaders()
    http_conn.send(input)

    (status_code,msg,reply)=http_conn.getreply()
    data=http_conn.getfile().read()
    if debug or msg!="OK":
       print
       print http_conn.headers
       print "*** Send message ***"
       print input
       print "************************************************************************"
       print "status code:",status_code
       print "message:",msg
       print "************************************************************************"
       print reply
#       print "*** Server data ***"
#       print data,type(data)
    return data

#
# main
#
if __name__ == "__main__":
    host= "cmsweb.cern.ch/dbs_discovery/"
    port= 443
    dbsInst="cms_dbs_prod_global"
    optManager  = DDOptionParser()
    (opts,args) = optManager.getOpt()
    if opts.host: host=opts.host
    if host.find("http://")!=-1:
       host=host.replace("http://","")
    if host[-1]!="/":
       host+="/"
    if opts.dbsInst: dbsInst=opts.dbsInst
    if opts.input:
       if os.path.isfile(opts.input):
          input=open(opts.input,'r').readline()
       else:
          input=opts.input
    else:
       print "\nUsage: DDSearchCLI.py --help"
       sys.exit(0)
    result = sendMessage(host,port,dbsInst,input,opts.page,opts.limit,opts.verbose)
    print result
