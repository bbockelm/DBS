#!/usr/bin/env python
#
# Event collection class. 

import types
from dbsObject import DbsObject
from dbsException import DbsException
from dbsParent import DbsParent
from dbsFile import DbsFile

class DbsEventCollection(DbsObject):
  def __init__(self, collectionName=None, numberOfEvents=None,
	       collectionStatus=None, objectId=None,
	       parentageList=[], fileList=[], dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if collectionName is not None:
      self._collectionName = str(collectionName)

    if collectionStatus is not None:
      self._collectionStatus = str(collectionStatus)

    if numberOfEvents is not None:
      self._numberOfEvents = long(numberOfEvents)

    # Correct parent event collections if needed.
    if not self.__dict__.has_key('_parentageList'):
      self._parentageList = parentageList
    for i in range(len(self._parentageList)):
      p = self._parentageList[i]
      if not isinstance(p, DbsParent):
        (parent, type) = (p['parent'], p['type'])
      else:
	(parent, type) = (p._parent, p._type)
      if not isinstance(parent, DbsEventCollection):
	parent = DbsEventCollection(dict=parent)
      self._parentageList[i] = DbsParent(parent=parent, type=str(type))

    # correct file list if needed.
    if not self.__dict__.has_key('_fileList'):
      self._fileList = fileList

    for i in range(len(self._fileList)):
      if not isinstance(self._fileList[i], DbsFile):
	self._fileList[i] = DbsFile(fileDict=self._fileList[i])

  def getCollectionName(self):
    """ Retrieve collection name. """
    return self._collectionName

  def getCollectionStatus(self):
    """ Retrieve collection status. """
    return self._collectionStatus

  def getNumberOfEvents(self):
    """ Retrieve number of events. """
    return self._numberOfEvents

  def getParentageList(self):
    """ Retrieve parentage list. """
    return self._parentageList

  def getFileList(self):
    """ Retrieve file list. """
    return self._fileList

##############################################################################
# Unit testing.

if __name__ == "__main__":
  ec = DbsEventCollection(collectionName="ec1", objectId=56, numberOfEvents=123)
  print "Event collection: \n", ec
  print "Event collection id: ", ec.getObjectId()
  ec["myKey"] = "myValue"
  print "Event collection after adding 'myKey': \n", ec

  ecList = [ec]
  print "Event collection list: \n", ecList
  ecList.append(DbsEventCollection(collectionName="ec2", numberOfEvents=228, objectId=72345))
  print "Event collection list after append: \n", ecList

  print "More complex event collection with parentage:"
  print DbsEventCollection(collectionName="ex", numberOfEvents=42, objectId=1,
			   parentageList = [ { 'parent' : ec, 'type' : 'input' }])
  print "Done"
