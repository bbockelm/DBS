#!/usr/bin/env python
#
# $Id: dbsProcessingPath.py,v 1.4 2005/12/15 22:52:40 sekhri Exp $
#
# Processing path class. 
#

import dbsObject
import types

import dbsException

PROCESSED_DATASET_ID_TAG_ = "processedDatasetId"
PROCESSING_PATH_ID_TAG_ = "processingPathId"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"


##############################################################################
# DBS application class.

class DbsPPIds(dbsObject.DbsObject):

  def __init__(self,processedDatasetId=None, processingPathId=None):
    """ Constructor. """

    print "line 1"
    dbsObject.DbsObject.__init__(self, {})
    print "Inside __init__ of DbsPPIds"
    if processedDatasetId is not None:
      print "line 2"
      self[PROCESSED_DATASET_ID_TAG_] = int(processedDatasetId)
      print "line 3"

    if processingPathId is not None:
      print "line 4"
      self[PROCESSING_PATH_ID_TAG_] = int(processingPathId)
      print "line 5"
    print "line 6"

  def getProcessedDatasetId(self):
    """ Retrieve full path. """
    result = self.get(PROCESSED_DATASET_ID_TAG_) 
    return result

  def getProcessingPathId(self):
    """ Retrieve data tier. """
    result = self.get(PROCESSING_PATH_ID_TAG_)
    return result
 
