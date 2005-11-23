#!/usr/bin/env python
#
# $Id: dbsDataset.py,v 1.3 2005/10/28 16:19:02 sveseli Exp $
#
# Dataset class. 
#

import types
import dbsObject
import dbsException

DATASET_NAME_TAG_ = "datasetName"
DATASET_PATH_TAG_ = "datasetPath"
DATA_TIER_TAG_ = "dataTier"
DATA_TYPE_TAG_ = "dataType"
DATASET_DICT_TAG_ = "datasetDict"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"

##############################################################################
# DBS dataset class.

class DbsDataset(dbsObject.DbsObject):

  def __init__(self, datasetName=None, datasetPath=None,
	       dataTier=None, dataType=None, datasetDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, datasetDict)
    if not self.has_key(DATASET_NAME_TAG_) \
       or datasetName is not None:
      self[DATASET_NAME_TAG_] = str(datasetName)
    if not self.has_key(DATASET_PATH_TAG_) \
       or datasetPath is not None:
      self[DATASET_PATH_TAG_] = str(datasetPath)
    if not self.has_key(DATA_TIER_TAG_) \
       or dataTier is not None:
      self[DATA_TIER_TAG_] = str(dataTier)
    if not self.has_key(DATA_TYPE_TAG_) \
       or dataType is not None:
      self[DATA_TYPE_TAG_] = str(dataType)

    self.setNamespace(WSDL_NAMESPACE_)

  def getDatasetName(self):
    """ Retrieve dataset name. """
    return self.get(DATASET_NAME_TAG_)

  def getDatasetPath(self):
    """ Retrieve dataset path. """
    return self.get(DATASET_PATH_TAG_)

  def getDataTier(self):
    """ Retrieve datatier. """
    return self.get(DATA_TIER_TAG_)

  def getDataType(self):
    """ Retrieve dataype. """
    return self.get(DATA_TYPE_TAG_)


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
  print "Done"
