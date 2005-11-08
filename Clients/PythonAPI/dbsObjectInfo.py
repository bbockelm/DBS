#!/usr/bin/env python
#
# $Id: dbsObjectInfo.py,v 1.4 2005/10/28 18:29:57 sveseli Exp $
#
# This class keeps track of the information needed to
# instantiate a DBS object.
#

import UserDict
import types

CLASS_NAME_TAG_ = "ClassName"
ARGUMENTS_STRING_TAG_ = "ArgumentsString"


##############################################################################
# DBS file block class.

class DbsObjectInfo(UserDict.UserDict):

  def __init__(self, className=None,
	       argumentsString=None):
    """ Constructor. """
    UserDict.UserDict.__init__(self)
    self[CLASS_NAME_TAG_] = str(className)
    self[ARGUMENTS_STRING_TAG_] = str(argumentsString)
      
  def getClassName(self):
    """ Retrieve class name. """
    return self.get(CLASS_NAME_TAG_)

  def getArgumentsString(self):
    """ Retrieve block id. """
    return self.get(ARGUMENTS_STRING_TAG_)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  objectInfo = DbsObjectInfo("mymodule.myclass", "myarg=77")
  print objectInfo
  print "Done"
