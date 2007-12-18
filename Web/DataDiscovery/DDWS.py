#!/usr/bin/env python
#
# Copyright 2007 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2007
# Version: $Id: DDWS.py,v 1.1 2007/12/18 15:50:33 valya Exp $
"""
Web services toolkit
"""

import os, sys, string, sre, httplib, urllib, inspect
import smtplib, traceback

def parseWSDL(wsdl):
    """Parse a wsdl file. So far we use urllib to do a job to read content of the file"""
    data = urllib.urlopen(wsdl).read()
    print data
    return data
    
def soapEnvelope():
    """Form a soap envelop in a form of CS web service wants. For service description,
    please consult http://cougar.cs.cornell.edu/CLEO/CLEO_WS.asmx"""
    
    envelope="""<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">"""
    return envelope
    
def headerEnvelope(ns,userName,password):
    """Form a header of soap envelop which include user authentication"""
    envelope="""
<soap:Header>
    <AuthHeader xmlns="ns">
      <Username>%s</Username>
      <Password>%s</Password>
    </AuthHeader>
</soap:Header>"""%(ns,userName,password)
    return envelope
    
def soapBody(ns,method,aDict):
    """Form a body of soap envelop. Construct appropriate array of items to retrieve."""
    envelope="""
  <soap:Body>
    <%s xmlns="%s">"""%(method,ns)
    for key in aDict.keys():
        argName=key
        argValue=aDict[key]
        envelope+="\n      <%s>%s</%s>"%(argName,argValue,argName)
    envelope+="""
    </%s>
  </soap:Body>"""%method
    return envelope
    
def endEnvelope():
    """Add end statement to soap envelop"""
    envelope="""</soap:Envelope>"""
    return envelope

def constructSOAPEnvelope(ns,method,aDict):
    """Construct a soap envelop for given method and argument list"""
#    envelope=soapEnvelope()+headerEnvelope()+soapBody(method,aList)+endEnvelope()
    envelope=soapEnvelope()+soapBody(ns,method,aDict)+endEnvelope()
    return envelope
    
def sendSOAPMessage(host,ns,method,envelope,debug=0):
    """Send soap message to cougar.cs.cornell.edu. Right now we use httplib to do a job"""
    try:
	if debug:
	    httplib.HTTPConnection.debuglevel = 1
        if host[:7]=="http://":
           host=host[7:]
	http_conn = httplib.HTTP(host)
	http_conn.putrequest('POST','/ws')
        http_conn.putheader('Host',host)
        http_conn.putheader('Content-Type','text/xml; charset=utf-8')
        http_conn.putheader('Content-Length',str(len(envelope)))
        http_conn.putheader('SOAPAction',ns+method)
        http_conn.endheaders()
        http_conn.send(envelope)

	(status_code,msg,reply)=http_conn.getreply()
	response=http_conn.getfile().read()
	if debug or msg!="OK":
	    print
	    print http_conn.headers
	    print "*** Outgoing SOAP ", "*"*54
	    print envelope
	    print "*"*72
	    print "status code:",status_code
	    print "message:",msg
	    print "*"*72
	    print reply
	    print "*** Incoming SOAP ", "*"*54
	    print response
    except:
	# Write out the exception to stderr
        sys.excepthook( sys.exc_info()[0], sys.exc_info()[1], sys.exc_info()[2] )
#
# MAIN
#
if __name__ == "__main__":
   x = 1
   verbose = 0
   test    = 1
   usage ="""DDWS.py [ -help ] [ -listServices ] [ -verbose ]
           [ -<serviceName> [<param>=<value> <param>=<value>] ]
	   
           Examples: DDWS.py -wsGetDatasetSummary dataset=/a/b/c
   """
   if len(sys.argv)==1:
      print usage
      sys.exit() 
   index=0
   while x < len(sys.argv):
     index+=1
     if index==20: break
     print x,sys.argv[x]
     if sys.argv[x]=="-verbose":
        verbose=1
	x+=1
     if sys.argv[x]=="-listServices":
        print "CLEO_admin services:"
	print "- wsGetNDatasets()"
	print "  Request how many datasets\n"
	print "- wsGetDatasetSummary(dataset)"
	print "  Get dataset summary\n"
	sys.exit()
     if sys.argv[x]=="-help":
        print usage
	sys.exit()
     if x < len(sys.argv) and sys.argv[x][0]=="-" and sys.argv[x]!="-inject":
	service=sys.argv[x][1:]
	x+=1
	listArgs=sys.argv[x:]
        aDict= {}
	for item in listArgs:
	    if item[0]=="-": break
	    name=""
	    aList=[]
	    if string.find(item,"=")==-1:
	       print "You need to supply argument name for",item
	       sys.exit()
	    else:
	       ss=string.split(item,"=")
	       name = ss[0]
	       value= ss[1]
	    if sre.match("\A\d+\.\d+\Z", value):
                value=float(value)
	    else:
		if sre.match("\A\d+\Z", value):
                    value=int(value)
            aDict[name]=value
	    x+=1
   ns="CMS_DBS"
   host="localhost:8030"
   envelope=constructSOAPEnvelope(ns,service,aDict)
   sendSOAPMessage(host,ns,service,envelope,verbose)

