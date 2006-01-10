#!/usr/bin/env python
#
# $Id: dbsEventCollection.py,v 1.8 2005/12/15 22:52:40 sekhri Exp $
#
# Event collection class. 
#

import types

import dbsObject
import dbsException
import dbsFile

EVENT_COLLECTION_DICT_TAG_ = "collectionDict"
EVENT_COLLECTION_ID_TAG_ = "collectionId"
EVENT_COLLECTION_NAME_TAG_ = "collectionName"
PARENTAGE_TYPE_NAME_TAG_ = "parentageType"
EVENT_COLLECTION_INDEX_TAG_ = "collectionIndex"
EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_ = "numberOfEvents"
PARENT_EVENT_COLLECTION_TAG_ = "parentEventCollection"
FILE_LIST_TAG_ = "fileList"
RUN_NUMBER_TAG_ = "runNumber"
IS_PRIMARY_TAG_ = "isPrimary"
PROCESSED_DATASET_NAME_TAG_ = "processedDatasetName"


WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"

##############################################################################
# DBS event collection class.

class DbsEventCollection(dbsObject.DbsObject):

  def __init__(self, collectionName=None,
               parentageType=None,
	       numberOfEvents=None, collectionIndex=None,
	       runNumber=None, isPrimary=None, parentEventCollection=None,
	       collectionId=None, processedDatasetName=None,
	       fileList=[],
	       collectionDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, collectionDict)
    if collectionId is not None:
      self[EVENT_COLLECTION_ID_TAG_] = int(collectionId)

    if collectionName is not None:
      self[EVENT_COLLECTION_NAME_TAG_] = str(collectionName)

    if parentageType is not None:
      self[PARENTAGE_TYPE_NAME_TAG_] = str(parentageType)

    if collectionIndex is not None:
      self[EVENT_COLLECTION_INDEX_TAG_] = int(collectionIndex)

    if numberOfEvents is not None:
      self[EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_] = int(numberOfEvents)

    if runNumber is not None:
      self[RUN_NUMBER_TAG_] = int(runNumber)

    if isPrimary is not None:
      self[IS_PRIMARY_TAG_] = str(isPrimary)

    if processedDatasetName is not None:
      self[PROCESSED_DATASET_NAME_TAG_] = str(processedDatasetName)

    if parentEventCollection is not None:
      self[PARENT_EVENT_COLLECTION_TAG_] = parentEventCollection

    # Correct parent event collection if needed.
    parentEventCollection = self.get(PARENT_EVENT_COLLECTION_TAG_)
    if parentEventCollection != None and \
	   not isinstance(parentEventCollection, DbsEventCollection):
      try:
	self[PARENT_EVENT_COLLECTION_TAG_] = DbsEventCollection(collectionDict=parentEventCollection)
      except Exception, ex:
	raise dbsException.InvalidArgument(args="Argument %s cannot be converted into a dbsEventCollection.DbsEventCollection object." % parentEventCollection)

    # Make sure we have file list initialized.
    if self.has_key(FILE_LIST_TAG_):
      fileList = fileList + self[FILE_LIST_TAG_]
    self[FILE_LIST_TAG_] = dbsFile.DbsFileList()
    for f in fileList:
      
      newFile = f
      if not isinstance(f, dbsFile.DbsFile):
	newFile = dbsFile.DbsFile(fileDict=newFile)
      self[FILE_LIST_TAG_].append(newFile)

    self.setNamespace(WSDL_NAMESPACE_)
    
  def getCollectionId(self):
    """ Retrieve collection id. """
    result = self.get(EVENT_COLLECTION_ID_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % EVENT_COLLECTION_ID_TAG_)
    return result

  def getCollectionName(self):
    """ Retrieve collection name. """
    result = self.get(EVENT_COLLECTION_NAME_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % EVENT_COLLECTION_NAME_TAG_)
    return result

  def getParentageType(self):
    """ Retrieve collection name. """
    result = self.get(PARENTAGE_TYPE_NAME_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PARENTAGE_TYPE_NAME_TAG_)
    return result


  def getCollectionIndex(self):
    """ Retrieve collection index. """
    result = self.get(EVENT_COLLECTION_INDEX_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % EVENT_COLLECTION_INDEX_TAG_)
    return result  

  def getNumberOfEvents(self):
    """ Retrieve number of events. """
    result = self.get(EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_)
    return result

  def getRunNumber(self):
    """ Retrieve run number. """
    result = self.get(RUN_NUMBER_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % RUN_NUMBER_TAG_)
    return result

  def getIsPrimary(self):
    """ Retrieve isPrimary flag. """
    result = self.get(IS_PRIMARY_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % IS_PRIMARY_TAG_)
    return result  

  def getProcessedDatasetName(self):
    """ Retrieve processed dataset name. """
    result = self.get(PROCESSED_DATASET_NAME_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PROCESSED_DATASET_NAME_TAG_)
    return result

  def getParentEventCollection(self):
    """ Retrieve parent event collection. """
    result = self.get(PARENT_EVENT_COLLECTION_TAG_) 
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PARENT_EVENT_COLLECTION_TAG_)
    return result  

  def getFileList(self):
    """ Retrieve file list. """
    return self.get(FILE_LIST_TAG_) 

class DbsEventCollectionList(dbsObject.DbsObjectList):

  def __init__(self, eventCollectionList=[]):
    """ Constructor. """
    # If needed convert elements to DbsEventCollection. If this cannot
    # be done, InvalidArgument exception will be thrown.
    ecList = []
    for ec in eventCollectionList:
      newEc = self.__createEventCollection(ec)
      ecList.append(newEc)
    dbsObject.DbsObjectList.__init__(self, ecList)
    self.setNamespace(WSDL_NAMESPACE_)

  def __createEventCollection(self, eventCollection):
    """ Create new event collection object. """
    try:
      newEc = eventCollection
      if isinstance(eventCollection, DbsEventCollection):
	# this is ok
	pass
      elif type(eventCollection) == types.DictType:
	# can convert to DbsEventCollection
	newEc = DbsEventCollection(collectionDict=eventCollection)
      else:
	# assume soap struct; wild guess, may throw exception
	newEc = DbsEventCollection(collectionDict=eventCollection)
      return newEc
    except Exception, ex:
      raise dbsException.InvalidArgument(exception=ex)

  def append(self, eventCollection):
    """ Append new event collection object. """
    newEc = self.__createEventCollection(eventCollection)
    dbsObject.DbsObjectList.append(self, newEc)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  ec = DbsEventCollection(collectionName="ec1", collectionId=56,
			  numberOfEvents=123)
  print "Event collection: \n", ec
  print "Event collection id: ", ec.getCollectionId()
  ec["myKey"] = "myValue"
  print "Event collection after adding 'myKey': \n", ec

  ecList = DbsEventCollectionList([ec])
  print "Event collection list: \n", ecList
  ecList.append(DbsEventCollection(collectionName="ec2", numberOfEvents=228, collectionId=72345))
  print "Event collection list after append: \n", ecList

  ecList2 = DbsEventCollectionList()  

  try:
    import SOAPpy
    print "Testing SOAP structs"
    sec = SOAPpy.structType({"numberOfEvents": 250,
			     "collectionName": "EvC_Run106600804"})
    print "SOAP struct: ", sec
    wsec = DbsEventCollection(collectionDict=sec._data)
    print "DbsEventCollection: ", wsec
    secList = SOAPpy.Types.typedArrayType(data=[sec])
    print "SOAP list: ", secList
    wsecList = DbsEventCollectionList(secList._data)
    print "DbsEventCollectionList: ", wsecList
  except ImportError, ex:
    pass
  
  print "Done"
