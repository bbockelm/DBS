
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

from dbsException import DbsException
from dbsApiException import *

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

def dbsApiImplRemapFiles_DEPRECATED(self, inFiles, outFile):
    """ 
    Remaps the file parentage . This api is called after a merge job is completed.
    A list of input file lfns and one output file is given to this api. The parents of all the input files
    becomes the parent of the output file. The childern of all the input files becomes the children of the 
    output file.
    
    param: 
        inFiles : A list of LFNs that are to be remapped. 
	
        outFiles : The LFN of the final output merged file
	
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
    
         fileList = ['aaa1122-0909-9767-8764aaa', 'aaa1122-0909-9767-8764bb']
         api.remapFiles (fileList, 'MyoutFile')
    """
    # Prepare XML description of the input

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    
    for afile in inFiles:
       xmlinput += " <in_file lfn='" + afile +"'/>"
       
    xmlinput += " <out_file lfn='" + outFile +"'/>"
    xmlinput += "</dbs>"

    logging.log(DBSDEBUG, xmlinput)
    if self.verbose():
       print "remapFiles, xmlinput",xmlinput

    # Call the method
    data = self._server._call ({ 'api' : 'remapFiles',
                         'xmlinput' : xmlinput }, 'POST')
    logging.log(DBSDEBUG, data)

  # ------------------------------------------------------------

