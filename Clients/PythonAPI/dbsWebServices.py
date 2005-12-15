#!/usr/bin/env python
#
# $Id: dbsWebServices.py,v 1.1 2005/11/23 18:30:31 sveseli Exp $
#
# DBS Web Services server class. 
#



###############################################################################
# Python imports.
import threading
import os
import string

###############################################################################
# Web Service imports.

import SOAPpy
import dbsLogManager
import dbsConfigManager

import dbsDatasetService

###############################################################################
# Constants.
DEFAULT_SERVICE_PORT_ = 27982
DEFAULT_SERVICE_HOST_ = "localhost"

DEFAULT_SOAP_DEBUG_FLAG_ = False

###############################################################################
# Web Service class.

class DbsWebServices:

  __instanceLock = threading.RLock()

  # Initialization.
  def __init__(self, host = None, port = None):
    """ Initialization. """
    DbsWebServices.__instanceLock.acquire()
    try:
      funcName = "%s.%s" % (self.__class__, "__init__()")
      self._logManager = dbsLogManager.getInstance()

      # Initialize host and port. 
      if host is None or port is None:
        configManager = dbsConfigManager.getInstance().getServerConfigManager()
        host = configManager.getHost(DEFAULT_SERVICE_HOST_)
        port = configManager.getPort(DEFAULT_SERVICE_PORT_)
      self._host = str(host)
      self._port = int(port)

      
      self._logManager.log(
	what="Initializing server on host %s (port: %s)" % (host, port),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      
      # Verbose or not?
      verboseFlag = configManager.getSoapDebugFlag(DEFAULT_SOAP_DEBUG_FLAG_)
      SOAPpy.Config.debug = verboseFlag
      self._logManager.log(what="SOAP debug flag is set to %s" % verboseFlag,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      #self._server = SOAPpy.SOAPServer((host, port))
      self._server = SOAPpy.ThreadingSOAPServer((host, port))

      self._logManager.log(what="Registering interface classes",
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      # Dataset service.
      dss = dbsDatasetService.getInstance()
      ns = dss.getNamespace()
      self._logManager.log(
	what="Registering DatasetService in the namespace %s" % ns,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      self._server.registerObject(dss, ns)

      self._logManager.log(what="Initialized", where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      
    finally:
      DbsWebServices.__instanceLock.release()

  # Start listening for requests.
  def listen(self):
    DbsWebServices.__instanceLock.acquire()
    try:
      funcName = "%s.%s" % (self.__class__, "__listen__()")
      self._logManager.log(
	what="Listening on host %s (port: %s)" % (self._host, self._port),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      self._server.serve_forever()
    finally:
      DbsWebServices.__instanceLock.release()


###############################################################################
# Unit testing.

if __name__ == "__main__":
  ws = DbsWebServices()
  ws.listen()

