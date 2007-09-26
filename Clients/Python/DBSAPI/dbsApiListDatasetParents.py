
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

def dbsApiImplListDatasetParents(self, dataset):
    """
    Retrieves the list of processed dataset which are parents iof the given dataset.
    Returns a list of DbsAnalysisDataset objects. 

    
    params:
	  dataset: is the processed dataset represented either in string format as path (/prim/datatier/proc) or in DbsProcessedDataset format.
	  This is a mandatory field and is not defaulted
	  
    returns: 
          list of DbsProcessedDataset objects  
    examples: 
          api.listDatasetParents("/test_primary_anzar_001/TestProcessedDS001/SIM")

    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsException	
             
    """

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    path = get_path(dataset)

    # Invoke Server.    
    data = self._server._call ({ 'api' : 'listDatasetParents', 
                                 'path' : path  
				}, 'GET')

    logging.log(DBSDEBUG, data)
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):

        def startElement(self, name, attrs):
          if name == 'processed_dataset_parent':
		  parentPath = str(attrs['path'])
		  myPath = parentPath.split('/')
		  result.append(DbsProcessedDataset ( 
			  			Name=myPath[2],
                                                #openForWriting=str(attrs['open_for_writing']), 
                                                PrimaryDataset=DbsPrimaryDataset(Name=myPath[1]),
                                                PhysicsGroup=str(attrs['physics_group_name']),
                                                PhysicsGroupConverner=str(attrs['physics_group_convener']),
                                                CreationDate=str(attrs['creation_date']),
                                                CreatedBy=str(attrs['created_by']),
                                                LastModificationDate=str(attrs['last_modification_date']),
                                                LastModifiedBy=str(attrs['last_modified_by']),
                                                PathList=[parentPath],     
                                                #Path=[str(attrs['path'])],     
                                                ))

      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)


  #-------------------------------------------------------------------

