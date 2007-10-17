
import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsRun import DbsRun
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsProcessedDataset import DbsProcessedDataset
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsLumiSection import DbsLumiSection
from dbsFile import DbsFile
from dbsFileBlock import DbsFileBlock
from dbsDataTier import DbsDataTier
from dbsStorageElement import DbsStorageElement
from dbsFileBranch import DbsFileBranch
from dbsAlgorithm import DbsAlgorithm
from dbsAnalysisDataset import DbsAnalysisDataset
from dbsAnalysisDatasetDefinition import DbsAnalysisDatasetDefinition
from dbsFileTriggerTag import DbsFileTriggerTag
from dbsMigrateApi import DbsMigrateApi
from dbsDQFlag import DbsDQFlag
from dbsRunLumiDQ import DbsRunLumiDQ
from xml.sax import SAXParseException

from dbsException import DbsException
from dbsApiException import *

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

def dbsApiImplListRunLumiDQ(self, runLumiDQList=[], timeStamp="", dqVersion=""):

    """
    Lists the Run/LumiSection DQ information based on the input criteria 
	(for now if NO criteria is provided dumps all DQ information, DO NOT DO That !) 

    params: 
         runLumiDQList : Takes LIST of DbsRunLumiDQ objects, each representing a Run OR a LumiSection within a Run
                         with a list of Data Quality Flags (Sub-System and Sub-Sub-System Flags and their values)
                         defined as "DQFlagList". Each object of this list if of type DbsDQFlag. 
                         These values are made part of look up, so if you say
                        
			flag1 = DbsDQFlag (
				        Name = "HCAL+",
        				Value = "GOOD",
        			)
			run_dq_search_criteria = DbsRunLumiDQ (
        			RunNumber=1,
        			#LumiSectionNumber can be part of this serach criteria
        			#LumiSectionNumber=123,
        			DQFlagList = [flag1]
				#Multiple flags
        			#DQFlagList = [flag1, flag2, flag3]
        			)
			dqHierarchyList = api.listRunLumiDQ(  [run_dq_search_criteria]  )

			This will mean, Looking for DQ information for RunNumber = 1, with a Sub-System HCAL+ and Value=GOOD.
        		Anyother type of combinitions can be provided. This is just an example.

			The returned values can be marched through to see whats returned.

			    for aDQ in dqHierarchyList:
			        print "\nRunNumber: ", aDQ['RunNumber']
  				print "LumiSectionNumber: ", aDQ['LumiSectionNumber']
			        for aSubDQ in aDQ['DQFlagList']:
                			print "      ", aSubDQ['Name'], aSubDQ['Value']
                			for aSubSubDQ in aSubDQ['SubSysFlagList']:
                        			print "                ", aSubSubDQ['Name'], aSubSubDQ['Value']

	dqVersion:  User can specify a Data Quality Version, By DEFAULT latest values are listed by this API
	timeStamp: The SnapShot of Data Quality at a certain time, Time Specified in UNIX Time Format (Seconds since epoch)
    
    """

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))
    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"

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

    logging.log(DBSDEBUG, xmlinput)

    data = self._server._call ({ 'api' : 'listRunLumiDQ',
                         'xmlinput' : xmlinput, 
			'time_stamp':timeStamp, 
			'dq_version':dqVersion }, 'POST')
    logging.log(DBSDEBUG, data)


    # Parse the resulting xml output.
    try:
      result = []

      class Handler (xml.sax.handler.ContentHandler):

        def startElement(self, name, attrs):
          if name == 'run':
               self.currRun = DbsRunLumiDQ (
						RunNumber=getLong(attrs['run_number']),
						LumiSectionNumber=getLong(attrs['lumi_section_number']),
						DQFlagList=[]
						)
          if name == 'dq_sub_system':
                        currSubFlag = DbsDQFlag(
                                        Name=str(attrs['name']),
                                        Value=str(attrs['value']),
					Parent=str(attrs['parent']),
                                        #CreationDate=str(attrs['creation_date']),
                                        #CreatedBy=str(attrs['created_by']),
                                        #LastModificationDate=str(attrs['last_modification_date']),
                                        #LastModifiedBy=str(attrs['last_modified_by']),
                                        )
			#if currSubFlag in self.currRun['DQFlagList']:
			#	self.currRun['DQFlagList']
			self.currRun['DQFlagList'].append(currSubFlag)

          if name == 'dq_sub_subsys':
			parent=str(attrs['parent'])
                        subSubFlag = DbsDQFlag(
                                                Name=str(attrs['name']),
                                                Value=str(attrs['value']),
						Parent=parent,
                                                #CreationDate=str(attrs['creation_date']),
                                                #CreatedBy=str(attrs['created_by']),
                                                #LastModificationDate=str(attrs['last_modification_date']),
                                                #LastModifiedBy=str(attrs['last_modified_by']),
                                                )
			found = 0	
			for aSubSys in self.currRun['DQFlagList']:
				if aSubSys['Name'] == parent:
					aSubSys['SubSysFlagList'].append(subSubFlag)
					found = 1
					break
				else :
					for aSubSubSys in aSubSys['SubSysFlagList']:
						if aSubSubSys['Name'] == parent:
							aSubSubSys['SubSysFlagList'].append(subSubFlag)
							found = 1
							break


        def endElement(self, name):
            if name == 'run':
               result.append(self.currRun)


      xml.sax.parseString (data, Handler ())
      return result

    except SAXParseException, ex:
      msg = "Unable to parse XML response from DBS Server"
      msg += "\n  Server has not responded as desired, try setting level=DBSDEBUG"
      raise DbsBadXMLData(args=msg, code="5999")

