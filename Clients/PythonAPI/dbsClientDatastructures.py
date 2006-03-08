from Service_services import *


class  DbsPrimaryDataset(ns1.PrimaryDataset_Def):

   """ Class for PrimaryDataset """
   def __init__(self, name, primaryDatasetId = None):
      ns1.PrimaryDataset_Def.__init__(self)
      self._primaryDatasetId = primaryDatasetId
      self._name = name

class  DbsApplication(ns1.Application_Def):

   """ Class for Application """
   def __init__(self, parameterSet, family, version, executable, applicationId = None):
      ns1.Application_Def.__init__(self)
      self._applicationId = applicationId
      self._executable = executable
      self._version = version
      self._family = family
      self._parameterSet = parameterSet

class  DbsProcessingPath(ns1.ProcessingPath_Def):

   """ Class for ProcessingPath """
   def __init__(self, dataTier, application, pathId = None, parent = None):
      ns1.ProcessingPath_Def.__init__(self)
      self._pathId = pathId
      self._parent = parent
      self._application = application
      self._dataTier = dataTier

class  DbsProcessedDataset(ns1.ProcessedDataset_Def):

   """ Class for ProcessedDataset """
   def __init__(self, isDatasetOpen, processingPath, primaryDatasetName, processedDatasetName, id = None):
      ns1.ProcessedDataset_Def.__init__(self)
      self._id = id
      self._processedDatasetName = processedDatasetName
      self._primaryDatasetName = primaryDatasetName
      self._processingPath = processingPath
      self._isDatasetOpen = isDatasetOpen

class  DbsFile(ns1.File_Def):

   """ Class for File """
   def __init__(self, inblock, type, status, logical_name, id = None, guid = None, checksum = None, filesize = None):
      ns1.File_Def.__init__(self)
      self._id = id
      self._guid = guid
      self._logical_name = logical_name
      self._checksum = checksum
      self._filesize = filesize
      self._status = status
      self._type = type
      self._inblock = inblock

class DbsFileList:
   """a LIST Class for the Python Class Type DbsFile"""
   def __init__(self, dbsfilelist=[]):
     self.dbsfilelist = []
     for anObj in dbsfilelist:
       newListObj = self.__createListObj(anObj)
       self.dbsfilelist.append(newListObj)


   def __createListObj(self, inObj):
     """ Create new file object. """
     try:
       newObj = inObj
       if isinstance(newObj, DbsFile):
         # this is ok
         return newFile
     except Exception, ex:
       raise dbsException.InvalidArgument(exception=ex)

   def append(self, thisObj):
     """ Append new  object to the list """
     newObj = self.__createFile(thisObj)
     self.dbsfilelist.append(newObj)


class  DbsEventCollection(ns1.EventCollection_Def):

   """ Class for EventCollection """
   def __init__(self, parentageType, collectionName, collection_index, collectionId = None, numberOfEvents = None, parent = None, fileList = None):
      ns1.EventCollection_Def.__init__(self)
      self._collectionId = collectionId
      self._collection_index = collection_index
      self._numberOfEvents = numberOfEvents
      self._collectionName = collectionName
      self._parent = parent
      self._parentageType = parentageType
      self._fileList = fileList

class DbsEventCollectionList:
   """a LIST Class for the Python Class Type DbsEventCollection"""
   def __init__(self, dbseventcollectionlist=[]):
     self.dbseventcollectionlist = []
     for anObj in dbseventcollectionlist:
       newListObj = self.__createListObj(anObj)
       self.dbseventcollectionlist.append(newListObj)


   def __createListObj(self, inObj):
     """ Create new file object. """
     try:
       newObj = inObj
       if isinstance(newObj, DbsEventCollection):
         # this is ok
         return newFile
     except Exception, ex:
       raise dbsException.InvalidArgument(exception=ex)

   def append(self, thisObj):
     """ Append new  object to the list """
     newObj = self.__createFile(thisObj)
     self.dbseventcollectionlist.append(newObj)


class  DbsBlock(ns1.Block_Def):

   """ Class for Block """
   def __init__(self, numberOfBytes, numberOfFiles, blockStatusName, blockId = None, eventCollectionList = None):
      ns1.Block_Def.__init__(self)
      self._blockId = blockId
      self._blockStatusName = blockStatusName
      self._numberOfFiles = numberOfFiles
      self._numberOfBytes = numberOfBytes
      self._eventCollectionList = eventCollectionList


