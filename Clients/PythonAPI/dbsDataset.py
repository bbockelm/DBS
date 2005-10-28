#!/usr/bin/env python
#
# $Id: dbsDataset.py,v 1.2 2005/10/28 16:00:46 sveseli Exp $
#
# Dataset class. 
#

import UserDict

DATASET_NAME_TAG_ = "DatasetName"
DATASET_PATH_TAG_ = "DatasetPath"
DATATIER_TAG_ = "DataTier"
DATATYPE_TAG_ = "DataType"

##############################################################################
# DBS dataset class.

class DbsDataset(UserDict.UserDict):

  def __init__(self, datasetName=None, datasetPath=None,
	       dataTier=None, dataType=None):
    """ Constructor. """
    UserDict.UserDict.__init__(self)
    self[DATASET_NAME_TAG_] = str(datasetName)
    self[DATASET_PATH_TAG_] = str(datasetPath)
    self[DATATIER_TAG_] = str(dataTier)
    self[DATATYPE_TAG_] = str(dataType)

  def getDatasetName(self):
    """ Retrieve dataset name. """
    return self.get(DATASET_NAME_TAG_)

  def getDatasetPath(self):
    """ Retrieve dataset path. """
    return self.get(DATASET_PATH_TAG_)

  def getDataTier(self):
    """ Retrieve datatier. """
    return self.get(DATATIER_TAG_)

  def getDataType(self):
    """ Retrieve dataype. """
    return self.get(DATATYPE_TAG_)



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
