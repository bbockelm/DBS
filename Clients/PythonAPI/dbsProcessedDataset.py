#!/usr/bin/env python
#
# $Id: dbsProcessedDataset.py,v 1.1 2005/12/07 21:18:41 sveseli Exp $
#
# Dataset class. 
#

import types
import string

import dbsObject
import dbsException
import dbsDataset
import dbsApplication
import dbsProcessingPath

PRIMARY_DATASET_NAME_TAG_ = "primaryDatasetName"
IS_DATASET_OPEN_TAG_ = "isDatasetOpen"
PROCESSING_PATH_TAG_ = "processingPath"

PROCESSED_DATASET_ID_TAG_ = "processedDatasetId"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"


##############################################################################
# DBS primary dataset class.

class DbsProcessedDataset(dbsDataset.DbsDataset):

  def __init__(self, datasetName=None, primaryDatasetName=None,
	       isDatasetOpen=None, processingPath=None,
	       datasetDict={}):
    """ Constructor. """
    dbsDataset.DbsDataset.__init__(self, datasetName=datasetName,
				   datasetDict=datasetDict)
    if primaryDatasetName is not None:
      self[PRIMARY_DATASET_NAME_TAG_] = str(primaryDatasetName)

    if isDatasetOpen is not None:
      self[IS_DATASET_OPEN_TAG_] = str(isDatasetOpen)

    if processingPath is not None:
      self[PROCESSING_PATH_TAG_] = processingPath

    # Correct processing path if needed.
    processingPath = self.get(PROCESSING_PATH_TAG_)
    if processingPath != None and not isinstance(processingPath, dbsProcessingPath.DbsProcessingPath):
      try:
	self[PROCESSING_PATH_TAG_] = dbsProcessingPath.DbsProcessingPath(processingPathDict=processingPath)
      except Exception, ex:
	raise dbsException.InvalidArgument(args="Argument %s cannot be converted into a dbsProcessingPath.DbsProcessingPath object." % processingPath)

    self.setNamespace(WSDL_NAMESPACE_)


  def getPrimaryDatasetName(self):
    """ Retrieve primary dataset name. """
    return self.get(PRIMARY_DATASET_NAME_TAG_)

  def getIsDatasetOpen(self):
    """ Retrieve is open flag. """
    return self.get(IS_DATASET_OPEN_TAG_)

  def getProcessingPath(self):
    """ Retrieve processing path. """
    return self.get(PROCESSING_PATH_TAG_)


##############################################################################
# Unit testing.

if __name__ == "__main__":

  app = dbsApplication.DbsApplication(family="reco", executable="dummy", version="p1")
  pp1 = dbsProcessingPath.DbsProcessingPath(fullPath="/x/y/z", dataTier="hit", application=app)
  print pp1
  pp2 = dbsProcessingPath.DbsProcessingPath(fullPath="/x22/y22/z22", dataTier="Digi",
			  parentPath=pp1)

  dataset = DbsProcessedDataset(datasetName="ds1",
			      processingPath=pp2)
  
  print dataset
  print "Done"


