#!/usr/bin/env python
#
# $Id: dbsApplication.py,v 1.5 2005/11/23 18:30:31 sveseli Exp $
#
# Application class. 
#

import dbsObject
import types

import dbsException

VERSION_TAG_ = "version"
FAMILY_TAG_ = "family"
EXECUTABLE_TAG_ = "executable"
CONFIG_CONDITIONS_VERSION_TAG_ = "configConditionsVersion"
PARAMETER_SET_TAG_ = "parameterSet"
INPUT_TYPE_NAME_TAG_ = "inputTypeName"
OUTPUT_TYPE_NAME_TAG_ = "outputTypeName"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"


##############################################################################
# DBS application class.

class DbsApplication(dbsObject.DbsObject):

  def __init__(self, version=None, family=None, executable=None,
	       configConditionsVersion=None, parameterSet=None,
	       inputTypeName=None, outputTypeName=None, 
	       applicationDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, applicationDict)
    if version is not None:
      self[VERSION_TAG_] = str(version)

    if family is not None:
      self[FAMILY_TAG_] = str(family)

    if executable is not None:
      self[EXECUTABLE_TAG_] = str(executable)

    if configConditionsVersion is not None:
      self[CONFIG_CONDITIONS_VERSION_TAG_] = str(configConditionsVersion)

    if parameterSet is not None:
      self[PARAMETER_SET_TAG_] = str(parameterSet)

    if inputTypeName is not None:
      self[INPUT_TYPE_NAME_TAG_] = str(inputTypeName)

    if outputTypeName is not None:
      self[OUTPUT_TYPE_NAME_TAG_] = str(outputTypeName)


    self.setNamespace(WSDL_NAMESPACE_)

  def getVersion(self):
    """ Retrieve version. """
    return self.get(VERSION_TAG_)

  def getFamily(self):
    """ Retrieve family. """
    return self.get(FAMILY_TAG_)

  def getExecutable(self):
    """ Retrieve executable. """
    return self.get(EXECUTABLE_TAG_)

  def getConfigConditionsVersion(self):
    """ Retrieve config conditions version. """
    return self.get(CONFIG_CONDITIONS_VERSION_TAG_)

  def getParameterSet(self):
    """ Retrieve parameter set. """
    return self.get(PARAMETER_SET_TAG_)

  def getInputTypeName(self):
    """ Retrieve input type name. """
    return self.get(INPUT_TYPE_NAME_TAG_)

  def getOutputTypeName(self):
    """ Retrieve output type name. """
    return self.get(OUTPUT_TYPE_NAME_TAG_)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  app = DbsApplication(family="reco", executable="dummy", version="p1")
  print app

  print "Done"
