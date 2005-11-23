#!/usr/bin/env python
#
# $Id: dbsLogManager.py,v 1.1 2005/10/21 22:50:51 lat Exp $
#
# Logging class. Since this class is a singleton, one should
# access it using something like
#
#  import dbsLogManager
#  logManager = dbsLogManager.getInstance()
#


import sys
import os
import time
import threading

import dbsException
import dbsConfigManager

# These are the log levels used as masks. 
LOG_LEVEL_QUIET_ = 0     # no output
LOG_LEVEL_INFO_ = 1      # messages intended for users
LOG_LEVEL_TRACE_ = 2     # execution trace
LOG_LEVEL_DEBUG_ = 4     # debugging
LOG_LEVEL_WARNING_ = 8   # warnings
LOG_LEVEL_ERROR_ = 16    # errors
LOG_LEVEL_ALL_ = 255     # all messages

DEFAULT_LOG_LEVEL_ = LOG_LEVEL_INFO_


APPEND_ = "a"
WRITE_ONLY_ = "w"
DEFAULT_TIMESTAMP_FORMAT_ = "<%m/%d/%y %H:%M:%S>"
DEFAULT_FILE_SUFFIX_FORMAT_ = "__%m_%d_%y"
DEFAULT_LOG_DIR_ = "."

##############################################################################
# Exception classes.

class DbsLogManagerException(dbsException.DbsException):

  def __init__(self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

##############################################################################
# Log manager singleton class.

class DbsLogManager:

  # Singleton support.
  __instance = None
  __instanceLock = threading.RLock()

  # Initialization.
  def __init__(self,
	       logLevel=DEFAULT_LOG_LEVEL_,
	       logDir=DEFAULT_LOG_DIR_,
	       logFileName=None,
	       logFileNameSuffixFormat=None,
	       writeToStdOut=True):
    """ Initialization. """
        
    DbsLogManager.__instanceLock.acquire()
    try:
      # Singleton stuff.
      if DbsLogManager.__instance:
        raise DbsLogManager.__instance
      DbsLogManager.__instance = self
        
      # Current time.
      self._currentTime = time.time()

      
      # Check config manager, will return default values if
      # config file is not defined.
      configManager = dbsConfigManager.getInstance().getLogConfigManager()

      # Logging level.
      self._logLevel = configManager.getLogLevel(logLevel)
        
      # Log directory.
      self._logDir = configManager.getLogDir(logDir)
            
      # Log file.
      self._log = None
      self._logFileName = configManager.getLogFileName(logFileName)
      self._logFileNameSuffixFormat = configManager.getLogFileNameSuffixFormat(
	logFileNameSuffixFormat)

      # Write to stdout.
      self._writeToStdOut = configManager.getWriteToStdOut(writeToStdOut)

    finally:
      DbsLogManager.__instanceLock.release()

  # Get log file.
  def getLogFileName(self):
    """ Return log file. """
    DbsLogManager.__instanceLock.acquire()
    try:
      return self._logFileName
    finally:
      DbsLogManager.__instanceLock.release()

  # Set log file.
  def setLogFileName(self, logFileName):
    """ Set log file. """
    DbsLogManager.__instanceLock.acquire()
    try:
      self._logFileName = logFileName
    finally:
      DbsLogManager.__instanceLock.release()

  # Get log level.
  def getLogLevel(self):
    """ Return log level. """
    DbsLogManager.__instanceLock.acquire()
    try:
      return self._logLevel
    finally:
      DbsLogManager.__instanceLock.release()

  # Set log level.
  def setLogLevel(self, logLevel):
    """ Set log level (used as the mask for logging). """
    DbsLogManager.__instanceLock.acquire()
    try:
      self._logLevel = logLevel
    finally:
      DbsLogManager.__instanceLock.release()

  # Get log file name.
  def getLogFileName(self):
    """ Return log file name suffix format. """
    DbsLogManager.__instanceLock.acquire()
    try:
      return self._logFileName
    finally:
      DbsLogManager.__instanceLock.release()

  # Set log file name.
  def setLogFileNameSuffixFormat(self, fileName):
    """ Set log file name suffix format. """
    DbsLogManager.__instanceLock.acquire()
    try:
      self._logFileName = fileName
    finally:
      DbsLogManager.__instanceLock.release()

  # Get log file suffix format.
  def getLogFileNameSuffixFormat(self):
    """ Return log file name suffix format. """
    DbsLogManager.__instanceLock.acquire()
    try:
      return self._logFileNameSuffixFormat
    finally:
      DbsLogManager.__instanceLock.release()

  # Set log file suffix format.
  def setLogFileNameSuffixFormat(self, suffixFormat):
    """ Set log file name suffix format. """
    DbsLogManager.__instanceLock.acquire()
    try:
      self._logFileNameSuffixFormat = suffixFormat
    finally:
      DbsLogManager.__instanceLock.release()

  # Get writeToStdOut flag.
  def getWriteToStdOut(self):
    """ Return log file name suffix format. """
    DbsLogManager.__instanceLock.acquire()
    try:
      return self._writeToStdOut
    finally:
      DbsLogManager.__instanceLock.release()

  # Set log file suffix format.
  def setWriteToStdOut(self, writeToStdOut):
    """ Set log file name suffix format. """
    DbsLogManager.__instanceLock.acquire()
    try:
      self._writeToStdOut = writeToStdOut
    finally:
      DbsLogManager.__instanceLock.release()


  # Open log file for logging.
  def openLogFile(self, fileName):
    """ open the log file. """
    DbsLogManager.__instanceLock.acquire()
    try:
      try:
        if self._log:
          self._log.close()
        self._log = open(fileName, APPEND_)
        return self._log
      except Exception, ex:
        raise DbsLogManagerException(exception=ex)
    finally:
      DbsLogManager.__instanceLock.release()

  # Logging.
  def log(self, what, where=None, logLevel=None):
    """
    Log the 'what' coming from the 'where' into the log file/stdout.
    If the 'logLevel' is None message will be logged (equivalent to
    LOG_LEVEL_ALL_). Otherwise, message is logged if the
    logLevel & self._loglevel returns true.  
    """
    DbsLogManager.__instanceLock.acquire()
    try:
      try:
	# Do not log messages that are unimportant.
	if logLevel is not None:
	  if not logLevel & self._logLevel:
	    return

	# Do not log if both output to stdout and logging to a file
	# is disabled.
	if self._writeToStdOut == False and self._fileName is None:
	  return

	# Prepare timestamp.
	t = time.time()
	timeStamp = time.strftime(DEFAULT_TIMESTAMP_FORMAT_, time.localtime(t))

	# Output string.
	outputStr = "%s %s\n" % (timeStamp, what)
	if where is not None:
	  outputStr = "%s %s: %s\n" % (timeStamp, where, what)

	# Log into a file.
	if self._logFileName is not None:
	  fileName = self._logFileName
	  if self._logFileNameSuffixFormat is not None:
	    fileName = fileName + time.strftime(self._logFileNameSuffixFormat)
	  if self._logDir is not None and self._logDir != "":
	    fileName = "%s/%s" % (self._logDir, fileName)
	  if self._log is None or fileName != self._log.name:
	    self.openLogFile(fileName)
	  self._log.write(outputStr)
	  self._log.flush()

	# Log to stdout.
	if self._writeToStdOut:
	  sys.stdout.write(outputStr)
	  sys.stdout.flush()
      except Exception, ex:
        raise DbsLogManagerException(exception=ex)
    finally:
      DbsLogManager.__instanceLock.release()

###############################################################################
# Log manager singleton instance.

__logManagerLock = threading.Lock()
def getInstance():
  """ Return DbsLogManager instance. """
  __logManagerLock.acquire()
  try:
    try:
      lm = DbsLogManager()
    except DbsLogManager, ex:
      lm = ex
    return lm
  finally:
    __logManagerLock.release()

###############################################################################
# Unit testing.

if __name__ == "__main__":
  lm = DbsLogManager()
  print "Log level: %s" % lm.getLogLevel()
  lm.setLogLevel(LOG_LEVEL_DEBUG_|LOG_LEVEL_INFO_)
  lm.log("Here I am", where="main()", logLevel=LOG_LEVEL_DEBUG_)
  time.sleep(1.13)
  lm.log("Here I am again", logLevel=LOG_LEVEL_INFO_)
  print "Done"
