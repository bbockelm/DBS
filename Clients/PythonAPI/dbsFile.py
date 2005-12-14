#!/usr/bin/env python
#
# $Id: dbsFile.py,v 1.2 2005/12/13 17:27:30 sveseli Exp $
#
# Event collection class. 
#

import types

import dbsObject
import dbsException

LOGICAL_FILE_NAME_TAG_ = "logicalFileName"
GUID_TAG_ = "guid"
CHECK_SUM_TAG_ = "checkSum"
FILE_TYPE_TAG_ = "fileType"
FILE_SIZE_TAG_ = "fileSize"
FILE_STATUS_TAG_ = "fileStatus"

FILE_BLOCK_ID_TAG_ = "fileBlockId"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"

##############################################################################
# DBS event collection class.

class DbsFile(dbsObject.DbsObject):

  def __init__(self, logicalFileName=None,
	       guid=None, checkSum=None, fileType=None,
	       fileStatus=None, fileBlockId=None, fileSize=None,
	       fileDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, fileDict)

    if logicalFileName is not None:
      self[LOGICAL_FILE_NAME_TAG_] = str(logicalFileName)

    if guid is not None:
      self[GUID_TAG_] = str(guid)

    if checkSum is not None:
      self[CHECK_SUM_TAG_] = str(checkSum)

    if fileType is not None:
      self[FILE_TYPE_TAG_] = str(fileType)

    if fileSize is not None:
      self[FILE_SIZE_TAG_] = long(fileSize)

    if fileStatus is not None:
      self[FILE_STATUS_TAG_] = str(fileStatus)

    if fileBlockId is not None:
      self[FILE_BLOCK_ID_TAG_] = str(fileBlockId)

    self.setNamespace(WSDL_NAMESPACE_)
    
  def getLogicalFileName(self):
    """ Retrieve logical file name. """
    result = self.get(LOGICAL_FILE_NAME_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % LOGICAL_FILE_NAME_TAG_)
    return result

  def getGuid(self):
    """ Retrieve file guid. """
    result = self.get(GUID_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % GUID_TAG_)
    return result

  def getCheckSum(self):
    """ Retrieve check sum. """
    result = self.get(CHECK_SUM_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % CHECK_SUM_TAG_)
    return result

  def getFileType(self):
    """ Retrieve file type. """
    result = self.get(FILE_TYPE_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FILE_TYPE_TAG_)
    return result

  def getFileSize(self):
    """ Retrieve file type. """
    result = self.get(FILE_SIZE_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FILE_SIZE_TAG_)
    return result

  def getFileStatus(self):
    """ Retrieve file status. """
    result = self.get(FILE_STATUS_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FILE_STATUS_TAG_)
    return result

  def getFileBlockId(self):
    """ Retrieve file block id. """
    result = self.get(FILE_BLOCK_ID_TAG_) 
    if result == None:
      raise dbsException.DataNotInitialized(args="Value for %s has not been set." % FILE_BLOCK_ID_TAG_)
    return result

class DbsFileList(dbsObject.DbsObjectList):

  def __init__(self, fileList=[]):
    """ Constructor. """
    # If needed convert elements to DbsFile. If this cannot
    # be done, InvalidArgument exception will be thrown.
    newFileList = []
    for f in fileList:
      newFile = self.__createFile(f)
      newFileList.append(newFile)
    dbsObject.DbsObjectList.__init__(self, newFileList)

    self.setNamespace(WSDL_NAMESPACE_)
    
  def __createFile(self, f):
    """ Create new file object. """
    try:
      newFile = f
      if isinstance(f, DbsFile):
	# this is ok
	pass
      elif type(f) == types.DictType:
	# can convert to DbsFile
	newFile = DbsFile(fileDict=f)
      else:
	# assume soap struct; wild guess, may throw exception
	newFile = DbsFile(fileDict=f)
      return newFile
    except Exception, ex:
      raise dbsException.InvalidArgument(exception=ex)

  def append(self, f):
    """ Append new file object. """
    newFile = self.__createFile(f)
    dbsObject.DbsObjectList.append(self, newFile)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  file = DbsFile(logicalFileName="lfn", fileSize=12345678)
  print file
  print file.getFileSize()
  print file.getWsRep()
  
  print "Done"
