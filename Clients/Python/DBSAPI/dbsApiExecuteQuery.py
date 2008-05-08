
import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsPrimaryDataset import DbsPrimaryDataset
from xml.sax import SAXParseException

from dbsException import DbsException
from dbsApiException import *

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

def dbsApiImplExecuteQuery(self, query="*", begin="", end="", type="exe"):
    """
    """
    try: 
      funcInfo = inspect.getframeinfo(inspect.currentframe())
      ##logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

      # Invoke Server.    
      data = self._server._call ({ 'api' : 'executeQuery', 'query' : query , 
		      'begin':str(begin),
		      'end':str(end),
		      'type':type,
		      }, 'GET')

      ###logging.log(DBSDEBUG, data)

      # No parsing nothing at this point, lets just return the data.
      return data

    except SAXParseException, ex:
      msg = "Unable to parse XML response from DBS Server"
      msg += "\n  Server has not responded as desired, try setting level=DBSDEBUG"
      raise DbsBadXMLData(args=msg, code="5999")

