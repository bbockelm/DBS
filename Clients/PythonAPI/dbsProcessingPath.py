#!/usr/bin/env python
#
# $Id: dbsProcessingPath.py,v 1.3 2005/12/12 19:04:50 sveseli Exp $
#
# Processing path class. 
#

import dbsObject
import types

import dbsApplication
import dbsException

PATH_ID_TAG_ = "pathId"
FULL_PATH_TAG_ = "fullPath"
DATA_TIER_TAG_ = "dataTier"
PARENT_PATH_TAG_ = "parentPath"
APPLICATION_TAG_ = "application"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"


##############################################################################
# DBS application class.

class DbsProcessingPath(dbsObject.DbsObject):

  def __init__(self, fullPath=None, dataTier=None, parentPath=None,
	       application=None, pathId=None,
	       processingPathDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, processingPathDict)
    if fullPath is not None:
      self[FULL_PATH_TAG_] = str(fullPath)

    if dataTier is not None:
      self[DATA_TIER_TAG_] = str(dataTier)

    if parentPath is not None:
      self[PARENT_PATH_TAG_] = parentPath

    if application is not None:
      self[APPLICATION_TAG_] = application

    if pathId is not None:
      self[PATH_ID_TAG_] = int(pathId)

    # Correct parent path if needed.
    parentPath = self.get(PARENT_PATH_TAG_)
    if parentPath != None and not isinstance(parentPath, DbsProcessingPath):
      try:
	self[PARENT_PATH_TAG_] = DbsProcessingPath(processingPathDict=parentPath)
      except Exception, ex:
	raise dbsException.InvalidArgument(args="Argument %s cannot be converted into a dbsProcessingPath.DbsProcessingPath object." % parentPath)

    # Correct application if needed.
    application = self.get(APPLICATION_TAG_)
    if application != None and not isinstance(application,
					      dbsApplication.DbsApplication):
      try:
	self[APPLICATION_TAG_] = dbsApplication.DbsApplication(applicationDict=application)
      except Exception, ex:
	raise dbsException.InvalidArgument(args="Argument %s cannot be converted into a dbsApplication.DbsApplication object." % application)

    self.setNamespace(WSDL_NAMESPACE_)

  def getFullPath(self):
    """ Retrieve full path. """
    result = self.get(FULL_PATH_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FULL_PATH_TAG_)
    return result

  def getDataTier(self):
    """ Retrieve data tier. """
    result = self.get(DATA_TIER_TAG_)
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % DATA_TIER_TAG_)
    return result
  
  def getParentPath(self):
    """ Retrieve parent path. """
    result = self.get(PARENT_PATH_TAG_)
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PARENT_PATH_TAG_)
    return result
  
  def getApplication(self):
    """ Retrieve application. """
    result = self.get(APPLICATION_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % APPLICATION_TAG_)
    return result

  def getPathId(self):
    """ Retrieve path od. """
    result = self.get(PATH_ID_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PATH_ID_TAG_)
    return result

##############################################################################
# Unit testing.

if __name__ == "__main__":
  
  app = dbsApplication.DbsApplication(family="reco", executable="dummy", version="p1")
  pp1 = DbsProcessingPath(fullPath="/x/y/z", dataTier="hit", application=app)
  print pp1
  pp2 = DbsProcessingPath(fullPath="/x22/y22/z22", dataTier="Digi",
			  parentPath=pp1, pathId=77)
  print pp2
  print pp2.getPathId()

  print "Done"
