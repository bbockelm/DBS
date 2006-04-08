#!/usr/bin/env python
#
# File block class. 

import types
from dbsObject import DbsObject
from dbsEventCollection import DbsEventCollection
from dbsProcessing import DbsProcessing
from dbsFile import DbsFile

class DbsFileBlock(DbsObject):
  def __init__(self, blockName=None, objectId=None, processing=None,
	       blockStatusName=None, numberOfBytes=None, numberOfFiles=None,
	       fileList=[], eventCollectionList=[], dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if processing is not None:
      self._processing = processing

    if blockName is not None:
      self._blockName = str(blockName)

    if blockStatusName is not None:
      self._blockStatusName = str(blockStatusName)

    if numberOfBytes is not None:
      self._numberOfBytes = long(numberOfBytes)

    if numberOfFiles is not None:
      self._numberOfFiles = long(numberOfFiles)

    # Make sure processing is an object
    if getattr(self, '_processing', None) \
       and not isinstance(self._processing, DbsProcessing):
      self._processing = DbsProcessing(dict=self._processing)

    # Make sure that all files are of the type DbsFile
    if not self.__dict__.has_key('_fileList'):
      self._fileList = []

    for file in fileList:
      self.addFile (file)

    # Make sure that all event collections are of the type DbsEventCollection
    if not self.__dict__.has_key('_eventCollectionList'):
      self._eventCollectionList = []

    for eventCollection in eventCollectionList:
      self.addEventCollection (eventCollection)

  def getBlockName(self):
    """ Retrieve block name. """
    return self._blockName

  def getBlockStatusName(self):
    """ Retrieve block status. """
    return self._blockStatusName

  def getNumberOfBytes(self):
    """ Retrieve number of bytes. """
    return self._numberOfBytes

  def getNumberOfFiles(self):
    """ Retrieve number of files. """
    return self._numberOfFiles

  def getProcessing(self):
    """ Retrieve processing. """
    return self._processing

  def getFileList (self):
    """ Retrieve file list. """
    return self._fileList

  def getEventCollectionList (self):
    """ Retrieve event collection list. """
    return self._eventCollectionList

  def addEventCollection (self, eventCollection):
    """ Add event collection. """
    if not isinstance(eventCollection, DbsEventCollection):
      eventCollection = DbsEventCollection (dict = eventCollection)
    self._eventCollectionList.append (eventCollection)

  def addFile (self, file):
    """ Add event collection. """
    if not isinstance(file, DbsFile):
      file = DbsFile (dict = file)
    self._fileList.append (file)

##############################################################################
# Unit testing.

if __name__ == "__main__":
  from dbsException import DbsException
  file = DbsFile(logicalFileName="f1", fileSize=1234)
  fileBlock = DbsFileBlock(objectId=123, blockName="fb1", blockStatusName="good", numberOfBytes=12123688, numberOfFiles=13)
  print fileBlock
  fileBlock.addFile(file)
  print fileBlock
  print fileBlock.getBlockStatusName()
  print fileBlock.getNumberOfFiles()
  print fileBlock.getNumberOfBytes()
  print "Adding myAttr to the dataset"
  fileBlock["myAttr"] = "myValue"
  print fileBlock
  print "Adding 11 as file"
  try:
    fileBlock.addFile(11)
  except DbsException, ex:
    print "OK, exception caught: %s" % ex
  print "Done"
