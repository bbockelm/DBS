#!/usr/bin/env python
#
# $Id: dbsFileBlock.py,v 1.2 2005/10/28 16:19:02 sveseli Exp $
#
# File block class. 
#

import UserDict
import types

import dbsEventCollection
import dbsException

FILE_BLOCK_NAME_TAG_ = "FileBlockName"
FILE_BLOCK_ID_TAG_ = "FileBlockId"
EVENT_COLLECTION_LIST_TAG_ = "EventCollectionList"


##############################################################################
# DBS file block class.

class DbsFileBlock(UserDict.UserDict):

  def __init__(self, blockName=None,
	       blockId=None, eventCollectionList=[]):
    """ Constructor. """
    UserDict.UserDict.__init__(self)
    self[FILE_BLOCK_NAME_TAG_] = str(blockName)
    self[FILE_BLOCK_ID_TAG_] = int(blockId)
    self[EVENT_COLLECTION_LIST_TAG_] = []
    if not type(eventCollectionList) == types.ListType:
      raise dbsException.InvalidArgument(args="Argument eventCollectionList has to be a list of dbsEventCollection.DbsEventCollection objects.")      
    for eventCollection in eventCollectionList:
      self.addEventCollection(eventCollection)
      
  def getBlockName(self):
    """ Retrieve block name. """
    return self.get(FILE_BLOCK_NAME_TAG_)

  def getBlockId(self):
    """ Retrieve block id. """
    return self.get(FILE_BLOCK_NAME_TAG_)

  def getEventCollectionList(self):
    """ Retrieve event collection list. """
    return self.get(EVENT_COLLECTION_LIST_TAG_)

  def addEventCollection(self, eventCollection):
    """ Add event collection. """
    if not isinstance(eventCollection, dbsEventCollection.DbsEventCollection):
      raise dbsException.InvalidArgument(args="Argument eventCollection needs to be an instance of class dbsEventCollection.DbsEventCollection.")
    self[EVENT_COLLECTION_LIST_TAG_].append(eventCollection)
    

##############################################################################
# Unit testing.

if __name__ == "__main__":
  eventCollection = dbsEventCollection.DbsEventCollection(
    collectionName="ec1", numberOfEvents=111)
  fileBlock = DbsFileBlock(blockId=123, blockName="fb1")
  print fileBlock
  fileBlock.addEventCollection(eventCollection)
  print fileBlock
  print "Adding myAttr to the dataset"
  fileBlock["myAttr"] = "myValue"
  print fileBlock
  print "Adding 11 as eventCollection"
  try:
    fileBlock.addEventCollection(11)
  except dbsException.DbsException, ex:
    print "OK, exception caught: %s" % ex
  print "Done"
