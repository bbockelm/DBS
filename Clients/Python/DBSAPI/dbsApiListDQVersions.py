
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
from xml.sax import SAXParseException

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

    try :
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

    except SAXParseException, ex:
      msg = "Unable to parse XML response from DBS Server"
      msg += "\n  Server has not responded as desired, try setting level=DBSDEBUG"
      raise DbsBadXMLData(args=msg, code="5999")


