#!/usr/bin/env python
#
# Dataset class. 

import types, re, string
from dbsObject import DbsObject
from dbsException import DbsException
from dbsPrimaryDataset import DbsPrimaryDataset

ALLOWED_NAME_CHARS_ = "[a-zA-Z0-9_\a\.-]"
ALLOWED_DATASET_REGEX_ = [
  re.compile("/%s+/%s+" % (ALLOWED_NAME_CHARS_, ALLOWED_NAME_CHARS_)), 
  re.compile("/%s+/%s+/%s+" % (ALLOWED_NAME_CHARS_, ALLOWED_NAME_CHARS_,
		    ALLOWED_NAME_CHARS_))
  ]

class DbsDatasetException(DbsException):
  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsException.__init__(self, **kwargs)
    
class InvalidDatasetPathName(DbsDatasetException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsDatasetException.__init__(self, **kwargs)

class DbsProcessedDataset(DbsObject):
  def __init__(self, parent=None, primaryDataset=None, datasetName=None,
	       dataTier=None, datasetPath=None, isDatasetOpen=None,
	       objectId=None, dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if primaryDataset is not None:
      self._primaryDataset = primaryDataset

    if datasetName is not None:
      self._datasetName = str(datasetName)

    if dataTier is not None:
      self._dataTier = str(dataTier)

    if datasetPath is not None:
      self._datasetPath = str(datasetPath)

    if parent is not None:
      self._parent = parent

    if isDatasetOpen is not None:
      self._isDatasetOpen = str(isDatasetOpen)

    if getattr(self, '_primaryDataset', None) \
       and not isinstance(self._primaryDataset, DbsPrimaryDataset):
      self._primaryDataset = DbsPrimaryDataset (dict = self._primaryDataset)

    if getattr(self, '_parent', None) \
       and not isinstance(self._parent, DbsProcessedDataset):
      self._parent = DbsProcessedDataset (datasetDict = self._parent)

    assert (getattr(self, '_datasetPath', None)
	    or (getattr(self, '_primaryDataset', None)
		and getattr(self, '_datasetName', None)
		and getattr(self, '_dataTier', None)))

     # FIXME: Auto-convert path to primary+name+tier?

  def getPrimaryDataset(self):
    """ Retrieve primary dataset. """
    return self._primaryDataset

  def getDatasetName(self):
    """ Retrieve the dataset name. """
    return self._datasetName

  def getDataTier(self):
    """ Retrieve the data tier name. """
    return self._dataTier

  def getDatasetPath(self):
    """ Retrieve the dataset full path. """
    return self._datasetPath

  def getParent(self):
    """ Retrieve parent dataset. """
    return self._parent

  def getIsDatasetOpen(self):
    """ Retrieve is open flag. """
    return self._isDatasetOpen

def verifyDatasetPathName(datasetPathName):
  """ Verify the validity of the given name. """
  for regex in ALLOWED_DATASET_REGEX_:
    if regex.match(datasetPathName): return
  raise InvalidDatasetPathName(args="Invalid dataset path name '%s'" % datasetPathName) 

##############################################################################
# Unit testing.

if __name__ == "__main__":
  from dbsApplication import DbsApplication
  prim = DbsPrimaryDataset (datasetName = "x")
  print prim
  proc1 = DbsProcessedDataset (primaryDataset=prim,
			       datasetName="y1",
			       dataTier="Hit")
  print proc1
  proc2 = DbsProcessedDataset (primaryDataset=prim,
			       datasetName="y2",
			       dataTier="Digi",
			       parent=proc1)
  print proc2
  print "Done"
