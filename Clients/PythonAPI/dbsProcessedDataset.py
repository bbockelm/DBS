#!/usr/bin/env python
#
# $Id: dbsProcessedDataset.py,v 1.4 2005/12/15 22:52:40 sekhri Exp $
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
PROCESSED_DATASET_NAME_TAG_ = "datasetName"
IS_DATASET_OPEN_TAG_ = "isDatasetOpen"
PROCESSING_PATH_TAG_ = "processingPath"

PROCESSED_DATASET_ID_TAG_ = "processedDatasetId"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"


##############################################################################
# DBS primary dataset class.

class DbsProcessedDataset(dbsDataset.DbsDataset):

  def __init__(self, datasetName=None, primaryDatasetName=None,
	       isDatasetOpen=None, processingPath=None, processedDatasetId = None,
	       datasetDict={}):
    """ Constructor. """
    dbsDataset.DbsDataset.__init__(self, datasetName=datasetName,
				   datasetDict=datasetDict)
    if primaryDatasetName is not None:
      self[PRIMARY_DATASET_NAME_TAG_] = str(primaryDatasetName)

    if isDatasetOpen is not None:
      self[IS_DATASET_OPEN_TAG_] = str(isDatasetOpen)

    if processedDatasetId is not None:
      self[PROCESSED_DATASET_ID_TAG_] = processedDatasetId


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
    result = self.get(PRIMARY_DATASET_NAME_TAG_)
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PRIMARY_DATASET_NAME_TAG_)
    return result

  def getDatasetName(self):
    """ Retrieve pricessed dataset name. """
    result = self.get(PROCESSED_DATASET_NAME_TAG_)
    #if result == None:
    #   raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PROCESSED_DATASET_NAME_TAG_)
    return result


  def getIsDatasetOpen(self):
    """ Retrieve is open flag. """
    result = self.get(IS_DATASET_OPEN_TAG_)
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % IS_DATASET_OPEN_TAG_)
    return result

  def getProcessedDatasetID(self):
    """ aaa. """
    result = self.get(PROCESSED_DATASET_ID_TAG_)
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % IS_DATASET_OPEN_TAG_)
    return result


  def getProcessingPath(self):
    """ Retrieve processing path. """
    result = self.get(PROCESSING_PATH_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PROCESSING_PATH_TAG_)
    return result


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


