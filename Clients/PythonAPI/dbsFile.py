#!/usr/bin/env python
#
# File class. 

from dbsObject import DbsObject

class DbsFile(DbsObject):
  def __init__(self, objectId=None, logicalFileName=None,
	       guid=None, checkSum=None, fileType=None,
	       fileStatus=None, fileBlockId=None, fileSize=None,
	       dict={}):
    """ Constructor. """
    DbsObject.__init__ (self, objectId, dict)
    if logicalFileName is not None:
      self._logicalFileName = str(logicalFileName)

    if guid is not None:
      self._guid = str(guid)

    if checkSum is not None and checkSum != '': # empty chceck for RefDB only!
      self._checkSum = str(checkSum)

    if fileType is not None:
      self._fileType = str(fileType)

    if fileSize is not None and fileSize != '': # empty check for RefDB only!
      self._fileSize = long(fileSize)

    if fileStatus is not None:
      self._fileStatus = str(fileStatus)

    if fileBlockId is not None:
      self._fileBlockId = str(fileBlockId)

    assert (self._logicalFileName)
    # assert (self._fileType) -- for RefDB
    # assert (self._checkSum) -- for RefDB
    # assert (self._fileSize) -- for RefDB

  def getLogicalFileName(self):
    """ Retrieve logical file name. """
    return self._logicalFileName

  def getGuid(self):
    """ Retrieve file guid. """
    return self._guid

  def getCheckSum(self):
    """ Retrieve check sum. """
    return self._checkSum

  def getFileType(self):
    """ Retrieve file type. """
    return self._fileType

  def getFileSize(self):
    """ Retrieve file type. """
    return self._fileSize

  def getFileStatus(self):
    """ Retrieve file status. """
    return self._fileStatus

  def getFileBlockId(self):
    """ Retrieve file block id. """
    return self._fileBlockId

##############################################################################
# Unit testing.

if __name__ == "__main__":
  file = DbsFile(logicalFileName="lfn", fileSize=12345678)
  print file
  print file.getFileSize()
  print "Done"
