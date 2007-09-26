
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

def dbsApiImplListAnalysisDatasetDefinition(self, pattern="*"):
    """
    Retrieves the list of definitions of the analysis dataset by matching against the given shell pattern for analysis 
    dataset definition name.
    Returns a list of DbsAnalysisDatasetDefinition objects. 

    
    params:
          pattern:  the shell pattren for nanlysis dataset definition name. 
                    If not given then the default value of * is assigned to it and all the definations are listed
    returns: 
          list of DbsAnalysisDatasetDefinition objects  
    examples: 
          api.listAnalysisDatasetDefinition()
	  api.listAnalysisDatasetDefinition("mydef*")

    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsException	
             
    """

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    # Invoke Server.
    data = self._server._call ({ 'api' : 'listAnalysisDatasetDefinition',
				 'pattern_analysis_dataset_definition_name' : pattern 
				}, 'GET')

    logging.log(DBSDEBUG, data)
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):

        def startElement(self, name, attrs):
          if name == 'analysis_dataset_definition':
                curr_def = DbsAnalysisDatasetDefinition (
                        Name=str(attrs['analysis_dataset_definition_name']),
                        RunsList=str(attrs['runs']).split(','),
                        #TierList=str(attrs['tiers']).split(','),
                        FileList=str(attrs['lfns']).split(','),
                        LumiList=str(attrs['lumi_sections']).split(','),
                        AlgoList=str(attrs['algorithms']).split(','),
                        ProcessedDatasetPath=str(attrs['path']),
                        RunRangeList=str(attrs['runs_ranges']).split(','),
                        #AnalysisDSList=str(attrs['analysis_dataset_names']).split(','),
                        LumiRangeList=str(attrs['lumi_section_ranges']).split(','),
                        UserCut=str(attrs['user_cut']),
                        #Description=str(attrs['name']),
                        CreationDate=str(attrs['creation_date']),
                        CreatedBy=str(attrs['created_by']),
                        LastModificationDate=str(attrs['last_modification_date']),
                        LastModifiedBy=str(attrs['last_modified_by']),
                        )
                result.append(curr_def)

      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  #-------------------------------------------------------------------

