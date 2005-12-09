#!/usr/bin/env python
#
# $Id: dbsObjectInfo.py,v 1.2 2005/11/09 21:37:59 sveseli Exp $
#
# This class keeps track of the information needed to
# instantiate a DBS object.
#

import UserDict
import types
import dbsException

CLASS_NAME_TAG_ = "ClassName"
ARGUMENTS_STRING_TAG_ = "ArgumentsString"


##############################################################################
# DBS object info class.

class DbsObjectInfo(UserDict.UserDict):

  def __init__(self, className=None,
	       argumentsString=None):
    """ Constructor. """
    UserDict.UserDict.__init__(self)
    self[CLASS_NAME_TAG_] = str(className)
    self[ARGUMENTS_STRING_TAG_] = str(argumentsString)
      
  def getClassName(self):
    """ Retrieve class name. """
    result = self.get(CLASS_NAME_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % CLASS_NAME_TAG_)
    return result

  def getArgumentsString(self):
    """ Retrieve argument string. """
    result = self.get(ARGUMENTS_STRING_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % ARGUMENTS_STRING_TAG_)
    return result


##############################################################################
# Unit testing.

if __name__ == "__main__":
  objectInfo = DbsObjectInfo("mymodule.myclass", "myarg=77")
  print objectInfo
  print "Done"
