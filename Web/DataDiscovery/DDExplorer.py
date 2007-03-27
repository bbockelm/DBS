#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2007

"""
DBS data discovery command line interface
"""

import  xml.sax, xml.sax.handler
from xml.sax.saxutils import escape

import httplib, urllib, types, string, os, sys
from optparse import OptionParser

def help():
    h ="""
This tool support a three input formats: xml string, xml file, txt file.

The XML structure:
<?xml version="1.0" encoding="utf-8"?>
<ddRequest>
<select column='PrimaryDataset.Name' />
<select column='ProcessedDataset.Name' />
<output limit="5" offset="2" />
<where column="PrimaryDataset.Name" operator="like" value="MTCC" />
</ddRequest>

The following conditions are supported: =,>=,<=,>,<,like,likeLeft,likeRight

The XML file should contain the proper XML structure, see above.

The txt file format:
[select]
PrimaryDataset.Name
ProcessedDataset.Name

[where]
PrimaryDataset.Name like 'MTCC'

[output]
limit=5
offset=10

To list all known TableName.colName's please use the following XML
<?xml version="1.0" encoding="utf-8"?>
<ddRequest>
<list />
</ddRequest>


"""
    return h

class DDOptionParser: 
  """
     DDOptionParser is main class to parse options for L{DDHelper} and L{DDServer}.
  """
  def __init__(self):
    self.parser = OptionParser()
    self.parser.add_option("--dbsInst",action="store", type="string", dest="dbsInst",
         help="specify DBS instance to use, e.g. --dbsInst=MCLocal_1/Writer")
    self.parser.add_option("-v","--verbose",action="store", type="int", default=0, dest="verbose",
         help="specify verbosity level, 0-none, 1-info, 2-debug")
    self.parser.add_option("--file",action="store", type="string", default=False, dest="input",
         help="specify input file name which contains your request.")
    #TODO: add default=host, detault=port
    self.parser.add_option("--host",action="store",type="string",dest="host",
         help="specify a host name of Data Discovery service, e.g. cmsdbs.cern.ch/discovery/")
    self.parser.add_option("--port",action="store",type="string",dest="port",
         help="specify a port number of Data Discovery service")
    self.parser.add_option("--output",action="store",type="string",default="txt",dest="output",
         help="specify output format, supported formats are: xml, list, txt (default)")
    self.parser.add_option("--limit",action="store",type="string",default="100",dest="limit",
         help="specify a limit on output, e.g. 50 results, usually it should come together with --offset")
    self.parser.add_option("--offset",action="store",type="string",default="0",dest="offset",
         help="specify an offset for query output, in other words it allow skip up to specified value the output, usually it should come together with --limit")
    self.parser.add_option("--list",action="store_true",dest="list",
         help="retrieve a list of know DBS tables.columns")
    self.parser.add_option("--help-usage",action="store_true",dest="format")
  def getOpt(self):
    """
        Returns parse list of options
    """
    return self.parser.parse_args()

def sendMessage(host,port,dbsInst,xmlEnvelope,output="list",debug=0):
    """
       Send message to server, message should be an well formed XML document.
    """
    if debug:
       httplib.HTTPConnection.debuglevel = 1
       print "Contact",host,port
    http_conn = httplib.HTTP(host,port)
    http_conn.putrequest('POST','/cliHandler?dbsInst=%s&input=%s'%(dbsInst,xmlEnvelope))
    http_conn.putheader('Host',host)
    http_conn.putheader('Content-Type','text/xml; charset=utf-8')
    http_conn.putheader('Content-Length',str(len(xmlEnvelope)))
    http_conn.endheaders()
    http_conn.send(xmlEnvelope)

    (status_code,msg,reply)=http_conn.getreply()
    data=http_conn.getfile().read()
    if debug or msg!="OK":
       print
       print http_conn.headers
       print "*** Send message ***"
       print xmlEnvelope
       print "************************************************************************"
       print "status code:",status_code
       print "message:",msg
       print "************************************************************************"
       print reply
       print "*** Server data ***"
       print data,type(data)
    if output=='xml':
       return data
    try:
      result = res = []
      out=""
      class Handler (xml.sax.handler.ContentHandler):
        def __init__(self):
            self.res=[]
            self.out=""
        def startElement(self, name, attrs):
            if name=='column':
               if  output=="list":
                   self.res.append(attrs['value'])
               else:
                   self.out+="%s\t"%attrs['value']
        def endElement(self, name):
            if name=="row":
               if output=="list":
                   if not result.count(self.res):
                      result.append(self.res)
               else:
                   print self.out
               self.out=""
               self.res=[]
            pass
      xml.sax.parseString (data, Handler ())
      if output=="txt":
         return
      return result
    except:
      sys.excepthook(sys.exc_info()[0],sys.exc_info()[1],sys.exc_info()[2])
    return []

def parseInput(input,verbose=0):
    if type(input) is types.DictType:
       return formXMLInput(input,verbose)
    lines=""
    iFileName=input
    try:
        lines=open(iFileName).readlines()
    except:
        if type(input) is types.StringType:
           if string.find(input,"""<?xml version="1.0" encoding="utf-8"?>""")!=-1:
              return input
           else:
              msg="Provided input '%s' is neither valid XML or existing file, see --help for more options"%input
              raise msg
        raise "Fail to open a file '%s'"%iFileName
    if string.split(lines[0],"\n")[0]=="""<?xml version="1.0" encoding="utf-8"?>""":
       return string.join(lines)
    select=where=0
    oDict={}
    for read in lines:
        line = string.split(read,"\n")[0]
        if not len(line): continue
        if line[0]=="#": continue
        if line.lower()=='[select]':
           item='select'
           continue
        if line.lower()=='[where]':
           item='where'
           continue
        if line.lower()=='[output]':
           item='output'
           continue
        oDict.setdefault(item,[]).append(line)
#    print oDict
    return formXMLInput(oDict,verbose)

def formXMLInput(iDict,verbose=0):
    xmlOutput="""<?xml version="1.0" encoding="utf-8"?><ddRequest>\n"""
    for item in iDict['select']:
        xmlOutput+="""<select column='%s' />\n"""%str(item)
    if  iDict.has_key('output'):
        for item in iDict['output']:
            xmlOutput+="""<output %s />\n"""%str(item)
    else:
        xmlOutput+="""<option limit="100" offset="0" />"""
    if  iDict.has_key('where'):
        for item in iDict['where']:
            col,op,val=item.split()
            if val[0]=="'" and val[-1]=="'":
               val=val[1:-2]
            if val[0]=="\"" and val[-1]=="\"":
               val=val[1:-2]
            xmlOutput+="""<where column="%s" operator="%s" value="%s" />\n"""%(str(col),str(op),str(val))
    xmlOutput+="</ddRequest>"
    if verbose:
       print "\n\nformed outputXML\n",xmlOutput
    return xmlOutput
    
def queryDBS(host,port,dbsInst,input,output="list",verbose=0):
    inputXML=parseInput(input,verbose)
    if  verbose:
        print inputXML
    envelope=urllib.quote(inputXML.strip())
    return sendMessage(host,port,dbsInst,envelope,output,verbose)

#
# main
#
if __name__ == "__main__":
#    host= "localhost"
#    port= 8001
#    dbsInst="localhost"

    host= "cmslcgco01.cern.ch"
    port= 8003
    dbsInst="cmslcgco01"

    optManager  = DDOptionParser()
    (opts,args) = optManager.getOpt()
#    print opts
    if opts.format:
       h=help()
       print h
       sys.exit(0)
    if opts.host: host=opts.host
    if opts.port: port=opts.port
    if opts.dbsInst: dbsInst=opts.dbsInst

    if not ['xml','list','txt'].count(opts.output):
       print "Output '%s' is not supported. For more options use --help"%opts.output
       sys.exit(1)
    if  opts.input:
        if  opts.verbose:
            print "Read input from file '%s'"%opts.input
        inputXML=opts.input
    else:
        # input examples
        inputXML="""<?xml version="1.0" encoding="utf-8"?><ddRequest><list /></ddRequest>"""
    if  not opts.input and not opts.list:
        print "\nUsage: DDExplorer.py --help"
    else:
        result = queryDBS(host,port,dbsInst,inputXML,opts.output,opts.verbose)
        if opts.output!="txt":
           print "### RESULT:",result
    # Test input as dictionary
#    iDict={'select':['PrimaryDataset.Name'],'output':['limit="5" offset="1"'],'where':['PrimaryDataset.Name like MTCC']}
#    result=queryDBS(host,port,dbsInst,iDict,"list")
#    print "\n###",iDict,result
