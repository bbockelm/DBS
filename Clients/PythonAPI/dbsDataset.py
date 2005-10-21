#!/usr/bin/env python
#
# $Id$
#
# Dataset class. 
#

import UserDict

DATASET_NAME_TAG_ = "DatasetName"
DATASET_PATH_TAG_ = "DatasetPath"
DATATIER_TAG_ = "Datatier"

##############################################################################
# DBS dataset class.

class DbsDataset(UserDict.UserDict):

  def __init__(self, datasetName=None, datasetPath=None,
	       dataTier=None):
    """ Constructor. """
    UserDict.UserDict.__init__(self)
    self[DATASET_NAME_TAG_] = str(datasetName)
    self[DATASET_PATH_TAG_] = str(datasetPath)
    self[DATATIER_TAG_] = str(dataTier)

  def getDatasetName(self):
    """ Retrieve dataset name. """
    return self.get(DATASET_NAME_TAG_)

  def getDatasetPath(self):
    """ Retrieve dataset path. """
    return self.get(DATASET_PATH_TAG_)

  def getDataTier(self):
    """ Retrieve datatier. """
    return self.get(DATATIER_TAG_)



##############################################################################
# Unit testing.

if __name__ == "__main__":
  dataset = DbsDataset(datasetName="ds1",
		       datasetPath="/ds1/ds1", dataTier="Digi")
  print dataset
  print "Done"
