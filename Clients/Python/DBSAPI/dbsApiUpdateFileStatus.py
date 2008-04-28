
import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsException import DbsException
from dbsApiException import *

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

def dbsApiImplUpdateFileStatus(self, lfn, status, description =""):
    """
    Updates the Status filed of a File (lfn)

    lfn: Logical File Name of file that needs to be updated
    status: One of the possible status 
  
    """

    funcInfo = inspect.getframeinfo(inspect.currentframe())
    #logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))

    data = self._server._call ({ 'api' : 'updateFileStatus',
                         'lfn' : file_name(lfn),
                         'status' : status,
			 'description': description,
                         }, 'POST')

    
  # ------------------------------------------------------------
