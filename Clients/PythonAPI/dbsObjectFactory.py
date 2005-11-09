#!/usr/bin/env python
#
# $Id: dbsObjectFactory.py,v 1.1 2005/11/08 21:46:54 sveseli Exp $
#
# This class keeps track of the information needed to
# instantiate a DBS object.
#

import string
import dbsObjectInfo
import dbsUtility
import dbsException
import dbsLogManager


##############################################################################
# Exception classes.

class DbsObjectFactoryException(dbsException.DbsException):

  def __init__(self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

##############################################################################
# Creates objects from object info class.

class DbsObjectFactory:

  def createObject(objectInfo):
    """ Create object from object info. """
    funcName = "%s.%s" % ("DbsObjectFactory", "createObject")
    
    if not isinstance(objectInfo, dbsObjectInfo.DbsObjectInfo):
      raise dbsException.InvalidArgument(args="Argument needs to be an instance of class dbsObjectInfo.DbsObjectInfo.")
    try:
      className = objectInfo.getClassName()
      argumentsString = objectInfo.getArgumentsString()
      nameList = string.split(className, ".")
      if len(nameList) > 1:
	moduleName = string.join(nameList[0:-1], ".")
	className = nameList[-1]
	cmd = "import %s; object = %s.%s(%s)" % (moduleName, moduleName,
						 className, argumentsString)
      else: 
	cmd = "object = %s(%s)" % (className, argumentsString)
      exec cmd
      return object
    except Exception, ex:
      errMsg = "Could not create object from: %s (Error: %s)" % (
	objectInfo, ex)
      dbsLogManager.getInstance().log(
	what=errMsg,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsObjectFactoryException(exception=ex)

  createObject = dbsUtility.StaticMethod(createObject)
  
##############################################################################
# Unit testing.

if __name__ == "__main__":
  objectInfo = dbsObjectInfo.DbsObjectInfo("long", "77")
  print objectInfo
  object = DbsObjectFactory.createObject(objectInfo)
  print object
  print "Done"
