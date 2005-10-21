#!/usr/bin/env python
#
# $Id$
#
# Event collection class. 
#

import UserDict

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
# Unit testing.

if __name__ == "__main__":
  eventCollection = DbsEventCollection(collectionName="ec1",
				       numberOfEvents=123)
  print eventCollection
  print "Done"
