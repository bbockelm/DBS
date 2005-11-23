#!/usr/bin/env python
#
# $Id: dbsUtility.py,v 1.2 2005/11/09 21:37:59 sveseli Exp $
#
# DBS utilities.
#

import os
import time
import sys
import dbsException
import dbsLogManager

##############################################################################
# DBS utility exceptions.

class DbsUtilityException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)


##############################################################################
# DBS Subprocess info class. 

class DbsSubprocessInfo:

  def __init__ (self, cmdString="", stdOut="", stdErr="", exitStatus=0):
    """ Initialization. """
    self._cmdString = cmdString
    self._stdOut = stdOut
    self._stdErr = stdErr
    self._exitStatus = exitStatus

  def getCmdString(self):
    """ Return command string. """
    return self._cmdString

  def getStdErr(self):
    """ Return standard error. """
    return self._stdErr

  def getStdOut(self):
    """ Return standard output. """
    return self._stdOut

  def getExitStatus(self):
    """ Return subprocess exit status. """
    return self._exitStatus

  def __str__(self):
    """ String representation. """
    result = ""
    result = result + "Command String: %s\n" % (self._cmdString)
    result = result + "Exit Status: %s\n" % (self._exitStatus)
    result = result + "Standard Output:\n%s\n" % (self._stdOut)
    result = result + "Standard Error:\n%s\n" % (self._stdErr)
    return result
    
    
##############################################################################
# DBS utilities.

# Execute subprocess.
def DbsExecSubprocess(cmd):
  funcName = "%s" % ("DbsExecSubprocess()")
  logManager = dbsLogManager.getInstance()
  logManager.log(what="Executing: %s" % cmd,
		 where=funcName,
		 logLevel=dbsLogManager.LOG_LEVEL_INFO_)

  pipeOut = os.pipe()
  pipeErr = os.pipe()
  pid = os.fork()
  if pid == 0:
    # Child process.

    # Standard output.
    os.close(pipeOut[0])
    os.dup2(pipeOut[1], sys.stdout.fileno())

    # Standard error.
    os.close(pipeErr[0])
    os.dup2(pipeErr[1], sys.stderr.fileno())
    os.execvp("sh", ["sh", "-c", cmd])

  else:
    # Parent process.
    os.close(pipeOut[1])
    os.close(pipeErr[1])
    (pid, exitStatus) = os.waitpid(pid, 0)

    # Correct exit status for scripts that contain explicit exit.
    if os.WIFEXITED(exitStatus):
      exitStatus = os.WEXITSTATUS(exitStatus)
    
    # Standard output.
    out = os.fdopen(pipeOut[0]).read()

    # Standard error.
    err = os.fdopen(pipeErr[0]).read()

    logManager.log(what="Executed %s. Command exit status: %s" % (
      cmd, exitStatus),
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    return DbsSubprocessInfo(cmdString=cmd,
			     stdOut=out, stdErr=err, exitStatus=exitStatus)


# Make up unique temporary file name with a given prefix and extension.
def DbsCreateTmpFileName(prefixString, extensionString):
  funcName = "%s" % ("DbsCreateTmpFileName()")
  logManager = dbsLogManager.getInstance()

  logManager.log(
    what="Constructing temporary file name with prefix %s and extension %s." % (prefixString, extensionString),
    where=funcName,
    logLevel=dbsLogManager.LOG_LEVEL_INFO_)
  
  # Get pid.
  pid = os.getpid()

  # Get timestamp.
  timeStamp = time.time()

  # Construct filename.
  result = "%s.%s" % (pid, timeStamp)
  if prefixString != "":
    result = "%s.%s" % (prefixString, result) 
  if extensionString != "":
    result = "%s.%s" % (result, extensionString)

  logManager.log(what="Temporary file: %s." % result,
		 where=funcName,
		 logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)

  return result

##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    cmd = "ls -ltr"
    subprocessInfo = DbsExecSubprocess(cmd)
    print subprocessInfo
    print ""
    
    cmd = "ls -ltr xyz"
    subprocessInfo = DbsExecSubprocess(cmd)
    print "Executed command: %s" % subprocessInfo.getCmdString()
    print "Exit status: %s" % subprocessInfo.getExitStatus()
    print "Standard output: %s" % subprocessInfo.getStdOut()
    print "Standard error: %s" % subprocessInfo.getStdErr()

    print "Temporary file: %s" % DbsCreateTmpFileName("tmpFile", "txt")
  except dbsException.DbsException, ex:
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
