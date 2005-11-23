#!/usr/bin/env python
#
# $Id: dbsConfigManager.py,v 1.3 2005/11/07 21:40:02 sveseli Exp $
#
# Config manager class. 
#


import os
import sys
import string
import types

import dbsException
import dbsModuleLoader

CONFIG_FILE_ARG_ = "--config-file"
CONFIG_DIR_ARG_ = "--config-dir"
ARG_DELIMITER_ = "="

SERVER_CONFIG_DICT_ = "ServerConfigDict_"
LOG_CONFIG_DICT_ = "LogConfigDict_"

DATASET_SERVICE_CONFIG_DICT_ = "DatasetServiceConfigDict_"


# Configuration parameters.
DBS_WEB_SERVICES_ADDRESS_ENV_VAR_ = "DBS_WEB_SERVICES_ADDRESS"
DBS_WEB_SERVICES_ADDRESS_DELIMITER_ = ":"

PORT_CONFIG_PAR_ = "Port"
HOST_CONFIG_PAR_ = "Host"
NUMBER_OF_SERVANTS_CONFIG_PAR_ = "NumberOfServants"
SOAP_DEBUG_FLAG_CONFIG_PAR_ = "SoapDebugFlag"
API_CLASS_NAME_CONFIG_PAR_ = "ApiClassName"
API_ARGUMENTS_STRING_CONFIG_PAR_ = "ApiArgumentsString"

LOG_DIR_CONFIG_PAR_ = "LogDir"
LOG_FILE_NAME_CONFIG_PAR_ = "LogFileName"
LOG_FILE_NAME_SUFFIX_FORMAT_CONFIG_PAR_ = "LogFileNameSuffixFormat"
LOG_LEVEL_CONFIG_PAR_ = "LogLevel"
WRITE_TO_STDOUT_CONFIG_PAR_ = "WriteToStdOut"



##############################################################################
# Exception classes.


# Generic configuration manager error class.
class ConfigManagerException(dbsException.DbsException):

  def __init__(self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

# Configuration file error.
class ConfigFileError(ConfigManagerException):

  def __init__(self, **kwargs):
    """ Initialization. """
    ConfigManagerException.__init__(self, **kwargs)

# Missing configuration file error.
class UnknownConfigFile(ConfigManagerException):

  def __init__(self):
    """ Initialization. """
    args = "Configuration file has not been provided."
    ConfigManagerException.__init__(self, args=args)


###############################################################################
# Configuration manager class.

class ConfigManager:

  # Initialization.
  def __init__(self, configDict = None, configModule = None):
    """ Initialization. """
        
    if ( configModule and type(configModule) != types.ModuleType ):
      args = "Invalid argument type for: %s" % configModule
      raise dbsException.InvalidArgument(args=args)
        
    self._configModule = configModule
    self._configDict = {}
    if ( self._configModule ):
      try:
        cmd = "self._configDict = self._configModule.%s" % (configDict)
        exec cmd
      except:
        # There is no known configuration.
        pass

  # Get configuration dictionary.
  def getConfigDict(self):
    return self._configDict

  # Get configuration parameter.
  def getConfigParameter(self, parameterName, defaultValue=None):
    return self._configDict.get(parameterName, defaultValue)

  # Get environment variable.
  def getEnvironmentVariable (self, variableName, defaultValue=None):
    return os.environ.get(variableName, defaultValue)


###############################################################################
# Log configuration class.

class LogConfigManager(ConfigManager):

  # Initialization.
  def __init__(self, configModule = None):
    """ Initialization. """
    ConfigManager.__init__(self, LOG_CONFIG_DICT_, configModule)

  def getLogLevel(self, defaultLogLevel=None):
    """ Return log level parameter. """
    return self.getConfigParameter(LOG_LEVEL_CONFIG_PAR_, defaultLogLevel)

  # Log directory parameter.
  def getLogDir(self, defaultLogDir=None):
    """ Return log dir parameter. """
    return self.getConfigParameter(LOG_DIR_CONFIG_PAR_, defaultLogDir)

  # Get log file name parameter.
  def getLogFileName(self, defaultLogFileName=None):
    """ Return log file name parameter. """
    return self.getConfigParameter(LOG_FILE_NAME_CONFIG_PAR_,
				   defaultLogFileName)

  # Get log file name suffix format parameter.
  def getLogFileNameSuffixFormat(self, defaultLogFileNameSuffixFormat=None):
    """ Return log file name suffix format parameter. """
    return self.getConfigParameter(LOG_FILE_NAME_SUFFIX_FORMAT_CONFIG_PAR_,
				   defaultLogFileNameSuffixFormat)

  # Write to stdout flag.
  def getWriteToStdOut(self, defaultWriteToStdOut=False):
    """ Determine whether to write to stdout or not. """
    return self.getConfigParameter(WRITE_TO_STDOUT_CONFIG_PAR_,
				   defaultWriteToStdOut)

###############################################################################
# Server configuration manager class.

class ServerConfigManager(ConfigManager):

  # Initialization.
  def __init__(self, configModule=None):
    """ Initialization. """
    ConfigManager.__init__(self, SERVER_CONFIG_DICT_, configModule)

  def getPort(self, defaultPort=None):
    """ Service port. """
    port = self.getConfigParameter(PORT_CONFIG_PAR_, defaultPort)
    if port is None:
      # Try getting port from the environment.
      try:
        serverAddress = os.environ[DBS_WEB_SERVICES_ADDRESS_ENV_VAR_]
        addrList = string.split(serverAddress, 
                                DBS_WEB_SERVICES_ADDRESS_DELIMITER_) 
        port = int(addrList[-1])
      except:
        pass
    return port

  def getHost(self, defaultHost=None):
    """ Service host. """
    host = self.getConfigParameter(HOST_CONFIG_PAR_, defaultHost)
    if host is None:
      # Try getting host from the environment.
      try:
        serverAddress = os.environ[SAM_WEB_SERVICES_ADDRESS_ENV_VAR_]
        addrList = string.split(serverAddress, 
                                SAM_WEB_SERVICES_ADDRESS_DELIMITER_) 
        host = str(addrList[0])
      except:
        pass
    return host

  def getNumberOfServants(self, defaultValue=None):
    """ Number of allowed servants (per service). """
    return self.getConfigParameter(NUMBER_OF_SERVANTS_CONFIG_PAR_,
				   defaultValue)

  def getSoapDebugFlag(self, defaultValue=None):
    """ Debugging of soap messages. """
    return self.getConfigParameter(SOAP_DEBUG_FLAG_CONFIG_PAR_,
				   defaultValue)

  def getApiClassName(self, defaultValue=None):
    """ API class name. """
    return self.getConfigParameter(API_CLASS_NAME_CONFIG_PAR_,
				   defaultValue)

  def getApiArgumentsString(self, defaultValue=None):
    """ API arguments string. """
    return self.getConfigParameter(API_ARGUMENTS_STRING_CONFIG_PAR_,
				   defaultValue)


###############################################################################
# Dataset service configuration manager class.

class DatasetServiceConfigManager(ConfigManager):

  # Initialization.
  def __init__(self, configModule = None):
    """ Initialization. """
    ConfigManager.__init__(self, DATASET_SERVICE_CONFIG_DICT_, configModule)

###############################################################################
# Main configuration manager singleton class.

class MainConfigManager:

  # Singleton support.
  __instance = None

  # Initialization.
  def __init__(self, argList=sys.argv):
    """ Initialization. """
        
    # Singleton stuff.
    if ( MainConfigManager.__instance ):
      raise MainConfigManager.__instance
    MainConfigManager.__instance = self
        
    # Determine the configuration file.
    self._configModule = None
    self._configFile = None
    self._configDir = None
    for a in argList:
      if string.find(a, CONFIG_FILE_ARG_) == 0:
        self._configFile = string.split(a, ARG_DELIMITER_)[-1]
      if string.find(a, CONFIG_DIR_ARG_) == 0:
        self._configDir = string.split(a, ARG_DELIMITER_)[-1]
    
    # Import configuration file if it exists.
    if self._configFile:
      self._configModule = self.importConfigFile(
	self._configFile, self._configDir)
        
    # Create various configuration managers.
    self._logConfigManager = LogConfigManager(self._configModule)
    self._serverConfigManager = ServerConfigManager(self._configModule)
    self._datasetServiceConfigManager = DatasetServiceConfigManager(
      self._configModule)

  # Get instance.
  def getInstance(self):
    """ Return the singleton instance. """
    return MainConfigManager.__instance

  # Get the configuration file.
  def getConfigFile(self):
    """ Return the configuration file. """
    return self._configFile

  # Get the configuration module.
  def getConfigModule(self):
    """ Return the configuration module. """
    return self._configModule

  # Get the configuration file directory.
  def getConfigDir(self):
    """ Return the configuration directory. """
    return self._configDir

  # Import the configuration file.
  def importConfigFile(self, configFile, configDir = None):
    """ Import the configuration file. """
    try:
      return dbsModuleLoader.DbsModuleLoader.loadModule(configFile, configDir)
    except:
      raise ConfigFileError(exception=ex)

  # Get the log configuration manager.
  def getLogConfigManager(self):
    """ Return the log configuration manager. """
    return self._logConfigManager

  # Get the server configuration manager.
  def getServerConfigManager(self):
    """ Return the server configuration manager. """
    return self._serverConfigManager

  # Get the Dataset service configuration manager.
  def getDatasetServiceConfigManager(self):
    """ Return the dataset service configuration manager. """
    return self._datasetServiceConfigManager


###############################################################################
# Configuration manager singleton instance.

def getInstance(argList = sys.argv):
  """ Return MainConfigManager instance. """
  try:
    configManager = MainConfigManager()
  except MainConfigManager, mcm:
    configManager = mcm
  return configManager

###############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    mcm = MainConfigManager()
    print "Log level: ", mcm.getLogConfigManager().getLogLevel()
    print "Done"
  except ConfigFileError, ex:
    print ex
