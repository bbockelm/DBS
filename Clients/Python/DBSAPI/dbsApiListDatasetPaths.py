import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsProcessedDataset import DbsProcessedDataset
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsAlgorithm import DbsAlgorithm
from dbsQueryableParameterSet import DbsQueryableParameterSet
from xml.sax import SAXParseException

from dbsException import DbsException
from dbsApiException import *

import logging
import inspect

from dbsLogger import *

from dbsUtil import *


def dbsApiImplListDatasetPaths(self):

    """
	List all the datasets Paths known to THIS DBS 

    """

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    ##logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))


    # Lets get all tiers no matter what, otherwise Server puts unnecessary checks on the DataTier
    patternDT='*'

    # Invoke Server.    
    data = self._server._call ({ 'api' : 'listDatasetPaths'}, 'GET')


    ##logging.log(DBSDEBUG, data)  

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):


        def startElement(self, name, attrs):
          if name == 'processed_dataset':
		results.append(str(attr('path')))
		
      xml.sax.parseString (data, Handler ())
      return result

    except SAXParseException, ex:
      msg = "Unable to parse XML response from DBS Server"
      msg += "\n  Server has not responded as desired, try setting level=DBSDEBUG"
      raise DbsBadXMLData(args=msg, code="5999")


