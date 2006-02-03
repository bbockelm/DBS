#!/usr/bin/env python
#
# $Id: dbsFileBlock.py,v 1.10 2006/01/26 23:10:01 afaq Exp $
#
# File block class. 
#

import dbsObject
import types

import dbsEventCollection
import dbsException

FILE_BLOCK_DICT_TAG_ = "blockDict"
FILE_BLOCK_NAME_TAG_ = "blockName"
FILE_BLOCK_ID_TAG_ = "blockId"
FILE_BLOCK_STATUS_NAME_TAG_ = "blockStatusName"
NUMBER_OF_FILES_TAG_ = "numberOfFiles"
NUMBER_OF_BYTES_TAG_ = "numberOfBytes"
PROCESSED_DATASET_NAME_TAG_ = "processedDatasetName"
EVENT_COLLECTION_LIST_TAG_ = "eventCollectionList"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"


##############################################################################
# DBS file block class.

class DbsFileBlock(dbsObject.DbsObject):

  def __init__(self, blockName=None,
	       blockId=None, processedDatasetName=None,
	       blockStatusName=None, numberOfBytes=None, numberOfFiles=None,
	       eventCollectionList=[], blockDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, blockDict)
    if blockName is not None:
      self[FILE_BLOCK_NAME_TAG_] = str(blockName)

    if processedDatasetName is not None:
      self[PROCESSED_DATASET_NAME_TAG_] = str(processedDatasetName)

    if blockId is not None:
      self[FILE_BLOCK_ID_TAG_] = int(blockId)

    if blockStatusName is not None:
      self[FILE_BLOCK_STATUS_NAME_TAG_] = str(blockStatusName)

    if numberOfBytes is not None:
      self[NUMBER_OF_BYTES_TAG_] = long(numberOfBytes)

    if numberOfFiles is not None:
      self[NUMBER_OF_FILES_TAG_] = long(numberOfFiles)

    # Make sure that all event collections are of the type DbsEventCollection
    # and that the list os of type DbsEventCollectionList.
    dictEcList = []
    if self.has_key(EVENT_COLLECTION_LIST_TAG_):
      dictEcList = self[EVENT_COLLECTION_LIST_TAG_]
    self[EVENT_COLLECTION_LIST_TAG_] = dbsEventCollection.DbsEventCollectionList()
    for eventCollection in eventCollectionList+dictEcList:
      self.addEventCollection(eventCollection)

    self.setNamespace(WSDL_NAMESPACE_)

  def getBlockName(self):
    """ Retrieve block name. """
    result = self.get(FILE_BLOCK_NAME_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FILE_BLOCK_NAME_TAG_)
    return result

  def getBlockId(self):
    """ Retrieve block id. """
    result = self.get(FILE_BLOCK_ID_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FILE_BLOCK_ID_TAG_)
    return result

  def getBlockStatusName(self):
    """ Retrieve block status. """
    result = self.get(FILE_BLOCK_STATUS_NAME_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FILE_BLOCK_STATUS_NAME_TAG_)
    return result

  def getNumberOfBytes(self):
    """ Retrieve number of bytes. """
    result = self.get(NUMBER_OF_BYTES_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % NUMBER_OF_BYTES_TAG_)
    return result

  def getNumberOfFiles(self):
    """ Retrieve number of files. """
    result = self.get(NUMBER_OF_FILES_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % NUMBER_OF_FILES_TAG_)
    return result  

  def getDatasetName(self):
    """ Retrieve processed dataset name. """
    result = self.get(PROCESSED_DATASET_NAME_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PROCESSED_DATASET_NAME_TAG_)
    return result

  def getEventCollectionList(self):
    """ Retrieve event collection list. """
    return self.get(EVENT_COLLECTION_LIST_TAG_)

  def addEventCollection(self, eventCollection):
    """ Add event collection. """
    self[EVENT_COLLECTION_LIST_TAG_].append(eventCollection)
        
class DbsFileBlockList(dbsObject.DbsObjectList):

  def __init__(self, fileBlockList=[]):
    """ Constructor. """
    # If needed convert elements to DbsFileBlock. If this cannot
    # be done, InvalidArgument exception will be thrown.
    fbList = []
    for fb in fileBlockList:
      newFb = self.__createFileBlock(fb)
      fbList.append(newFb)
    dbsObject.DbsObjectList.__init__(self, fbList)
    self.setNamespace(WSDL_NAMESPACE_)

  def __createFileBlock(self, fileBlock):
    """ Create new fileBlock object. """
    try:
      newFb = fileBlock
      if isinstance(fileBlock, DbsFileBlock):
	# this is ok
	pass
      elif type(fileBlock) == types.DictType:
	# can convert to DbsFileBlock
	newFb = DbsFileBlock(blockDict=fileBlock)
      else:
	# assume this is soap struct; wild guess, may throw exception
	blockId = fileBlock.blockId
	blockName = fileBlock.blockName
	eventCollectionList = fileBlock.eventCollectionList
	newFb = DbsFileBlock(blockId=blockId,
			     blockName=blockName,
			     eventCollectionList=eventCollectionList)
      return newFb
    except Exception, ex:
      raise dbsException.InvalidArgument(exception=ex)

  def append(self, fileBlock):
    """ Append new file block object. """
    newFb = self.__createFileBlock(fileBlock)
    dbsObject.DbsObjectList.append(self, newFb)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  eventCollection = dbsEventCollection.DbsEventCollection(
    collectionName="ec1", numberOfEvents=111)
  fileBlock = DbsFileBlock(blockId=123, blockName="fb1", blockStatusName="good", numberOfBytes=12123688, numberOfFiles=13)
  print fileBlock
  fileBlock.addEventCollection(eventCollection)
  print fileBlock
  print fileBlock.getBlockStatusName()
  print fileBlock.getNumberOfFiles()
  print fileBlock.getNumberOfBytes()
  print "Adding myAttr to the dataset"
  fileBlock["myAttr"] = "myValue"
  print fileBlock
  print "Adding 11 as eventCollection"
  try:
    fileBlock.addEventCollection(11)
  except dbsException.DbsException, ex:
    print "OK, exception caught: %s" % ex
  print "Done"
