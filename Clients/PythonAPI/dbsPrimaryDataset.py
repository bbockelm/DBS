#!/usr/bin/env python
#
# Dataset class. 

from dbsObject import DbsObject

class DbsPrimaryDataset(DbsObject):
  def __init__(self, objectId=None, datasetName=None, dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if datasetName is not None:
      self._datasetName = str(datasetName)

    assert (self._datasetName)

  def getDatasetName(self):
    return self._datasetName

##############################################################################
# Unit testing.

if __name__ == "__main__":
  dataset = DbsPrimaryDataset(datasetName="ds1")
  print dataset
  print "Adding myAttr to the dataset"
  dataset["myAttr"] = "myValue"
  print dataset
  
  dataset2 = DbsPrimaryDataset(datasetName="ds2")
  print dataset2
  print "Done"
