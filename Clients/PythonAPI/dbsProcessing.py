#!/usr/bin/env python
#
# Processing class. 

import types
from dbsObject import DbsObject
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsApplicationConfig import DbsApplicationConfig

class DbsProcessing(DbsObject):
  def __init__(self, parent=None, primaryDataset=None, processingName=None,
	       applicationConfig=None, isOpen=None, objectId=None, dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if parent is not None:
      self._parent = parent

    if primaryDataset is not None:
      self._primaryDataset = primaryDataset

    if applicationConfig is not None:
      self._applicationConfig = applicationConfig

    if processingName is not None:
      self._processingName = str(processingName)

    if isOpen is not None:
      self._isOpen = str(isOpen)

    if getattr(self, '_parent', None) and not isinstance(self._parent, DbsProcessing):
      self._parent = DbsProcessing (dict = self._parent)

    if not isinstance(self._primaryDataset, DbsPrimaryDataset):
      self._primaryDataset = DbsPrimaryDataset (dict=self._primaryDataset)

    if not isinstance(self._applicationConfig, DbsApplicationConfig):
      self._applicationConfig = DbsApplicationConfig (dict=self._applicationConfig)

    # assert (self._processingName)

  def getParent(self):
    """ Retrieve parent processing. """
    return self._parent

  def getPrimaryDataset(self):
    """ Retrieve primary dataset. """
    return self._primaryDataset

  def getProcessingName(self):
    """ Retrieve the processing name. """
    return self._processingName

  def getApplicationConfig(self):
    """ Retrieve the application configuration. """
    return self._applicationConfig

  def getIsOpen(self):
    """ Retrieve is open flag. """
    return self._isOpen

##############################################################################
# Unit testing.

if __name__ == "__main__":
  print DbsProcessing (primaryDataset = { "datasetName" : "x" },
		       processingName = "ThisIsAProcessing",
		       applicationConfig = DbsApplicationConfig (
		         application = { "executable" : "cmsRun",
					 "version" : "0_6_0",
					 "family" : "Framework" },
			 parameterSet = { "hash" : "1234",
					  "content" : "dudaa daabi duu" }))
  print "Done"
