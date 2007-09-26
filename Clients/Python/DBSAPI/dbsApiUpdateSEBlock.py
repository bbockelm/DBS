
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

def dbsApiImplUpdateSEBlock(self, blockName, storage_element_from, storage_element_to):
    """
    Updates the Storage Element in the spoecified block from torage_element_from to storage_element_to
    
    param: 
	blockName : the name of the block in which storage element will be changed
	storage_element_from : The name of storage element in the string format or object format that needs to be changed
	storage_element_to : The name of storage element in the string format or object format that it will be changed to.
			  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         api.updateSEBlock ("/pri/proc/tier#1234", "se1", "se2")

    """

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    seNameFrom = get_name(storage_element_from)
    seNameTo = get_name(storage_element_to)
    
    name = get_name(blockName)

    data = self._server._call ({ 'api' : 'updateSEBlock',
		    'block_name' : name,
		    'storage_element_name_from' : seNameFrom,
		    'storage_element_name_to' : seNameTo }, 'POST')
    logging.log(DBSDEBUG, data)

