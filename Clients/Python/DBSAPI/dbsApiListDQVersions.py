
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

def dbsApiImplListDQVersions(self):
    """
    List of Data Quality Versions

    returns a List of TUPLE of (version, timestamp)

    """
    funcInfo = inspect.getframeinfo(inspect.currentframe())
    logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    data = self._server._call ({ 'api' : 'listDQVersions'}, 'GET')

    # Parse the resulting xml output.
    result = []

    class Handler (xml.sax.handler.ContentHandler):

        def startElement(self, name, attrs):
          if name == 'dq_version': 
                        dqVersion = str(attrs['version'])
			dq_timestamp= str(attrs['time_stamp'])
                        result.append((dqVersion, dq_timestamp))
    xml.sax.parseString (data, Handler ())
    return result

  #def listRowsInTable(self):
  # """
  #	API is used to list RowsInTable
  # """
  # data = self._server._call ({ 'api' : 'listRowsInTable', 'table_name' : 'SubSystem', 'rows':'*'}, 'GET')
  # print data



#############################################################################
# Unit testing: see $PWD/UnitTests
############################################################################

from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser

if __name__ == "__main__":

  try:
    optManager  = DbsOptionParser()
    (opts,args) = optManager.getOpt()

    api = DbsApi(opts.__dict__)
    serverInfo = api.getServerInfo()
    print "Server Version : ", serverInfo['ServerVersion']
    print "Schema Version : ", serverInfo['SchemaVersion']

    #print api.listSubSystems()
    print api.listDQVersions()

  except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
      print "DBS Exception Error Code: ", ex.getErrorCode()
 

