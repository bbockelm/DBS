#!/usr/bin/env python
#
# $Id: dbsDataset.py,v 1.6 2005/12/07 21:18:41 sveseli Exp $
#
# Dataset class. 
#

import types
import re
import string

import dbsObject
import dbsException

import dbsStaticMethod

DATASET_NAME_TAG_ = "datasetName"
DATASET_PATH_TAG_ = "datasetPath"
DATA_TIER_TAG_ = "dataTier"
DATA_TYPE_TAG_ = "dataType"
DATASET_DICT_TAG_ = "datasetDict"

ALLOWED_NAME_CHARS_ = "[a-zA-Z0-9_\a\.-]"
ALLOWED_DATASET_REGEX_ = [
  re.compile("/%s+/%s+" % (ALLOWED_NAME_CHARS_, ALLOWED_NAME_CHARS_)), 
  re.compile("/%s+/%s+/%s+" % (ALLOWED_NAME_CHARS_, ALLOWED_NAME_CHARS_,
		    ALLOWED_NAME_CHARS_))
  ]

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"

##############################################################################
# DBS Dataset class exceptions.

class DbsDatasetException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)
    
class InvalidDatasetPathName(DbsDatasetException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsDatasetException.__init__(self, **kwargs)


##############################################################################
# DBS dataset class.

class DbsDataset(dbsObject.DbsObject):

  def __init__(self, datasetName=None, datasetPath=None,
	       dataTier=None, dataType=None, datasetDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, datasetDict)
    if datasetName is not None:
      self[DATASET_NAME_TAG_] = str(datasetName)
    if datasetPath is not None:
      self[DATASET_PATH_TAG_] = str(datasetPath)
    if dataTier is not None:
      self[DATA_TIER_TAG_] = str(dataTier)
    if dataType is not None:
      self[DATA_TYPE_TAG_] = str(dataType)

    self.setNamespace(WSDL_NAMESPACE_)

  def getDatasetName(self):
    """ Retrieve dataset name. """
    result = self.get(DATASET_NAME_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % DATASET_NAME_TAG_)
    return result

  def getDatasetPath(self):
    """ Retrieve dataset path. """
    result = self.get(DATASET_PATH_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % DATASET_PATH_TAG_)
    return result

  def getDataTier(self):
    """ Retrieve datatier. """
    result = self.get(DATA_TIER_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % DATA_TIER_TAG_)
    return result

  def getDataType(self):
    """ Retrieve dataype. """
    result = self.get(DATA_TYPE_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % DATA_TYPE_TAG_)
    return result

  def verifyDatasetPathName(datasetPathName):
    """ Verify the validity of the given name. """
    for regex in ALLOWED_DATASET_REGEX_:
      if regex.match(datasetPathName):
	return
    raise InvalidDatasetPathName(args="Invalid dataset path name: %s" % datasetPathName) 

  verifyDatasetPathName = dbsStaticMethod.DbsStaticMethod(verifyDatasetPathName)


class DbsDatasetList(dbsObject.DbsObjectList):

  def __init__(self, datasetList=[]):
    """ Constructor. """
    # If needed convert elements to DbsDataset. If this cannot
    # be done, InvalidArgument exception will be thrown.
    dsList = []
    for ds in datasetList:
      newDs = self.__createDataset(ds)
      dsList.append(newDs)
    dbsObject.DbsObjectList.__init__(self, dsList)
    self.setNamespace(WSDL_NAMESPACE_)

  def __createDataset(self, dataset):
    """ Create new dataset object. """
    try:
      newDs = dataset
      if isinstance(dataset, DbsDataset):
	# this is ok
	pass
      elif type(dataset) == types.DictType:
	# can convert to DbsDataset
	newDs = DbsDataset(datasetDict=dataset)
      else:
	# assume this is soap struct; wild guess, may throw exception
	datasetName = dataset.datasetName
	datasetPath = dataset.datasetPath
	dataTier = dataset.dataTier
	dataType = dataset.dataType
	newDs = DbsDataset(datasetName=datasetName,
			   datasetPath=datasetPath,
			   dataType=dataType,
			   dataTier=dataTier)
      return newDs
    except Exception, ex:
      raise dbsException.InvalidArgument(exception=ex)

  def append(self, dataset):
    """ Append new dataset object. """
    newDs = self.__createDataset(dataset)
    dbsObject.DbsObjectList.append(self, newDs)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  dataset = DbsDataset(datasetName="ds1",
		       datasetPath="/ds1/ds1",
		       dataTier="Digi",
		       dataType="PU")
  print dataset
  print "Adding myAttr to the dataset"
  dataset["myAttr"] = "myValue"
  print dataset

  # Use all loaded datasets to verify that pathname verification method works.
  #dsPathList = string.split(open("dataset_names.txt", "r").read())
  #nDatasets = len(dsPathList)
  #for dsPath in dsPathList:
  #  try:
  #    DbsDataset.verifyDatasetPathName(dsPath)
  #    print "%s   [Passed]" % dsPath
  #  except InvalidDatasetPathName, ex:
  #    print "%s   [Failed]" % dsPath



  print "Done"


