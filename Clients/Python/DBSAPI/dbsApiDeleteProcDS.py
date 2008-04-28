
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

def dbsApiImplDeleteProcDS(self, dataset):

    """
    Moves the dataset path into the recycle bin implemented in DBS server.
    
    param: 
        path : The dataset path represented in the form /primname/procname/datatier
                          
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException  
           
    examples:
         api.deleteProcDS ("/this/hahah/SIM")
 
    """   

    path = get_path(dataset)
    funcInfo = inspect.getframeinfo(inspect.currentframe())
    ####logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))
    data = self._server._call ({ 'api' : 'deleteProcDS',
                         'path' : path }, 'POST')
    ####logging.log(DBSDEBUG, data)

   # ------------------------------------------------------------

def dbsApiImplUndeleteProcDS(self, dataset):

    """
    Moves the dataset path from the recycle bin back in the DBS server.
    
    param: 
        path : The dataset path represented in the form /primname/procname/datatier
                          
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException  
           
    examples:
         api.deleteProcDS ("/this/hahah/SIM")
 
    """   

    path = get_path(dataset)
    funcInfo = inspect.getframeinfo(inspect.currentframe())
    ####logging.log(DBSDEBUG, "Api call invoked %s" % str(funcInfo[2]))
    data = self._server._call ({ 'api' : 'undeleteProcDS',
                         'path' : path }, 'POST')
    ####logging.log(DBSDEBUG, data)

   # ------------------------------------------------------------

