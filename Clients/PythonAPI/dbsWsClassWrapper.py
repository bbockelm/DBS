#!/usr/bin/env python
#
# $Id: dbsEventCollection.py,v 1.1 2005/10/21 22:50:51 lat Exp $
#
# Event collection class. 
#

import UserDict
try:
  import SOAPpy
  __imported_SOAPpy = True
except:
  __imported_SOAPpy = False


EVENT_COLLECTION_NAME_TAG_ = "EventCollectionName"
EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_ = "NumberOfEvents"

##############################################################################
# DBS event collection class.

class DbsEventCollection(UserDict.UserDict):

  def __init__(self, collectionName=None,
	       numberOfEvents=None):
    """ Constructor. """
    UserDict.UserDict.__init__(self)
    self[EVENT_COLLECTION_NAME_TAG_] = str(collectionName)
    self[EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_] = int(numberOfEvents)

  def getCollectionName(self):
    """ Retrieve collection name. """
    return self.get(EVENT_COLLECTION_NAME_TAG_)

  def getNumberOfEvents(self):
    """ Retrieve number of events. """
    return self.get(EVENT_COLLECTION_NUMBER_OF_EVENTS_TAG_)


##############################################################################
# Web service wrapper for the DBS event collection class.

# Define the class only if soappy can be imported.

if __imported_SOAPpy:
  
  class WsDbsEventCollection(SOAPpy.structType):
    """ Constructor. """
    

  
##############################################################################
# Unit testing.

if __name__ == "__main__":
  eventCollection = DbsEventCollection(collectionName="ec1",
				       numberOfEvents=123)
  print eventCollection
  print "Done"
