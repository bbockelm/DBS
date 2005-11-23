#!/usr/bin/env python
#
# $Id: dbsWebService.py,v 1.3 2005/11/07 21:40:02 sveseli Exp $
#
# Base DBS Web Service class. 
#


###############################################################################
# Web service imports.

import string

import dbsWebServiceException
import dbsServantManager
import dbsLogManager
import dbsConfigManager
import dbsObjectInfo
import dbsObjectFactory

###############################################################################
# Constants.
DEFAULT_SOAP_DEBUG_FLAG_ = 1
DEFAULT_NUMBER_OF_SERVANTS_ = 1

###############################################################################
# Exception classes are defined in dbsWebServiceException module.

###############################################################################
# Base class for all web services.

class DbsWebService:

  # Initialization.
  def __init__(self, serviceName):
    """ Initialization. """
        
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    self._serviceName = serviceName

    # Log manager.
    self._logManager = dbsLogManager.getInstance()
          
    # Config manager.
    configManager = dbsConfigManager.getInstance().getServerConfigManager()
      
    # Servant manager.
    nServants = configManager.getNumberOfServants(DEFAULT_NUMBER_OF_SERVANTS_)
    self._logManager.log(
      what="%s will have %s servants." % (self._serviceName, nServants),
      where=funcName, logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    self._servantManager = dbsServantManager.DbsServantManager(
      nServants, self.__class__)

    # API.
    apiClassName = configManager.getApiClassName()
    apiArgumentsString = configManager.getApiArgumentsString()
    try:
      apiObjectInfo = dbsObjectInfo.DbsObjectInfo(
	apiClassName, apiArgumentsString)
      self._logManager.log(
	what="Creating api object: %s" % (apiObjectInfo),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      self._api = dbsObjectFactory.DbsObjectFactory.createObject(apiObjectInfo)
      self._logManager.log(
	what="Created api object: %s" % (self._api),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    except dbsObjectFactory.DbsObjectFactoryException, ex:
      self._logManager.log(
	what="Could not create api object %s: %s" % (apiObjectInfo, ex),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise dbsWebServiceException.DbsWebServiceServerFault(exception=ex)
    
    self._logManager.log(what="Initialized",
			 where=funcName,
			 logLevel=dbsLogManager.LOG_LEVEL_INFO_)


  def getApi(self):
    """ Retunr api object. """
    return self._api

  def getParameter(self, parameterName, parameterValue, **kwargs):
    """ Extract required parameter from kwargs if parameterValue is None. """
    funcName = "%s.%s" % (self.__class__.__name__, "getParameter()")
    parameter = parameterValue
    if parameter is None:
      # Try kwargs with parameterName.
      parameter = kwargs.get(parameterName, None)
      if parameter is None:
        # Try kwargs with parameterName in lowercase.
        parameter = kwargs.get(string.lower(parameterName), None)
        if parameter is None:
	  faultString = "Missing required input parameter: %s" % parameterName
	  self._logManager.log(faultString,
			       where=funcName,
			       logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
          raise dbsWebServiceException.MissingParameterFault(
            faultString=faultString)
    return parameter
  
  def getNamespace(self):
    return self._serviceName

  def acquireServant(self, funcName):
    """ Acquire servant. """
    servantId = None
    try:
      servantId = self._servantManager.acquireServant()
      return servantId
    except NoAvailableServants, ex:
      raise DbsWebServiceServerFault(
	exception=ex, faultString="%s" % (ex))
    
  def releaseServant(self, servantId):
    """ Release previously acquired servant. """ 
    self._servantManager.releaseServant(servantId)

###############################################################################
# Unit testing.

if __name__ == "__main__":
  ws = DbsWebService("myWebServiceClass")
  
  print "Done"



