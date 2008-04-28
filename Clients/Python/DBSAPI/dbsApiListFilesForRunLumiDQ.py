
import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsDQFlag import DbsDQFlag
from dbsRunLumiDQ import DbsRunLumiDQ
from xml.sax import SAXParseException

from dbsException import DbsException
from dbsApiException import *
from dbsFile import DbsFile

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

def dbsApiImplListFilesForRunLumiDQ(self, runLumiDQList, timeStamp="", dqVersion=""):

    """
    
    """

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    ###logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))
    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"

    if type(runLumiDQList) == str:
	first=1
 	print "str_input", runLumiDQList
	tag_val_list=runLumiDQList.strip().split('&')
	for atag_val in tag_val_list:
		tag, val = atag_val.split('=')
		if tag=='RunNumber':
			xmlinput += "<run run_number='"+val+ "' lumi_section_number='' />"
			continue
		#if tag=='LumiSection':
		else: 
			if (first):
				xmlinput += "<run run_number='' lumi_section_number='' />"
				first=0
		xmlinput += "<dq_sub_system name='" + tag + "' value='" + val + "' />"

    else :
    	for aRunLumiDQ in runLumiDQList:
        	xmlinput += "<run run_number='"+str(get_run(aRunLumiDQ.get('RunNumber'))) + "'"
        	xmlinput += " lumi_section_number='"+str(aRunLumiDQ.get('LumiSectionNumber', ''))+"'"
        	xmlinput += " />"

        	for aFlag in aRunLumiDQ.get('DQFlagList'):
                	xmlinput += "<dq_sub_system name='" + aFlag.get('Name') + "'  value='" + aFlag.get('Value') + "'  />"
                	# Sub sub system list
                	for aSubFlag in aFlag.get('SubSysFlagList'):
                        	xmlinput += "<dq_sub_subsys name='" + aSubFlag.get('Name') + "'  value='" + aSubFlag.get('Value') + "'  />"



    xmlinput += "</dbs>"

    ###logging.log(DBSDEBUG, xmlinput)

    data = self._server._call ({ 'api' : 'listFilesForRunLumiDQ',
                         'xmlinput' : xmlinput, 
			'time_stamp':timeStamp, 
			'dq_version':dqVersion }, 'POST')
    ###logging.log(DBSDEBUG, data)

    # Parse the resulting xml output.
    try:
      result = []

      class Handler (xml.sax.handler.ContentHandler):

	def __init__(self):
		self.SubSysFlags = []
		self.SubSubFlags = []

        def startElement(self, name, attrs):
          if name == 'file':
		aFile = DbsFile ( LogicalFileName=str(attrs['lfn']) )
		result.append(aFile)

      xml.sax.parseString (data, Handler ())
      return result

    except SAXParseException, ex:
      msg = "Unable to parse XML response from DBS Server"
      msg += "\n  Server has not responded as desired, try setting level=DBSDEBUG"
      raise DbsBadXMLData(args=msg, code="5999")

