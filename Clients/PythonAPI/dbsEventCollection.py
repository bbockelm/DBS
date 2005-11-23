#!/usr/bin/env python
#
# $Id: dbsEventCollection.py,v 1.1 2005/10/21 22:50:51 lat Exp $
#
# Event collection class. 
#

import types

import dbsObject
import dbsException

EVENT_COLLECTION_DICT_TAG_ = "collectionDict"
EVENT_COLLECTION_NAME_TAG_ = "collectionName"
EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_ = "numberOfEvents"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"

##############################################################################
# DBS event collection class.

class DbsEventCollection(dbsObject.DbsObject):

  def __init__(self, collectionName=None,
	       numberOfEvents=None, collectionDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, collectionDict)
    if not self.has_key(EVENT_COLLECTION_NAME_TAG_) \
       or collectionName is not None:
      self[EVENT_COLLECTION_NAME_TAG_] = str(collectionName)
    if not self.has_key(EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_) \
       or numberOfEvents is not None:
      self[EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_] = int(numberOfEvents)
    self.setNamespace(WSDL_NAMESPACE_)
    
  def getCollectionName(self):
    """ Retrieve collection name. """
    return self.get(EVENT_COLLECTION_NAME_TAG_)

  def getNumberOfEvents(self):
    """ Retrieve number of events. """
    return self.get(EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_)

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
	collectionName = eventCollection.collectionName
	numberOfEvents = eventCollection.numberOfEvents
	newEc = DbsEventCollection(collectionName=collectionName,
				   numberOfEvents=numberOfEvents)
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
  ec = DbsEventCollection(collectionName="ec1", numberOfEvents=123)
  print "Event collection: \n", ec
  ec["myKey"] = "myValue"
  print "Event collection after adding 'myKey': \n", ec

  ecList = DbsEventCollectionList([ec])
  print "Event collection list: \n", ecList
  ecList.append(DbsEventCollection(collectionName="ec2", numberOfEvents=228))
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
