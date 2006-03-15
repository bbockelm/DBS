from Service_services import *
import dbsException


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
   def __init__(self, fileBlockId, fileType, fileStatus, logicalFileName, id = None, guid = None, checksum = None, fileSize = None):
      ns1.File_Def.__init__(self)
      self._id = id
      self._guid = guid
      self._logicalFileName = logicalFileName
      self._checksum = checksum
      self._fileSize = fileSize
      self._fileStatus = fileStatus
      self._fileType = fileType
      self._fileBlockId = fileBlockId

class  DbsEventCollection(ns1.EventCollection_Def):

   """ Class for EventCollection """
   def __init__(self, datasetPathName, collectionName, collectionIndex, collectionId = None, numberOfEvents = None, parent = None, parentageType = None, fileList = None):
      ns1.EventCollection_Def.__init__(self)
      self._collectionId = collectionId
      self._collectionIndex = collectionIndex
      self._numberOfEvents = numberOfEvents
      self._collectionName = collectionName
      self._datasetPathName = datasetPathName
      self._parent = parent
      self._parentageType = parentageType
      self._fileList = fileList

class  DbsBlock(ns1.Block_Def):

   """ Class for Block """
   def __init__(self, numberOfBytes, numberOfFiles, blockStatusName, blockId = None, blockName = None, eventCollectionList = None):
      ns1.Block_Def.__init__(self)
      self._blockId = blockId
      self._blockStatusName = blockStatusName
      self._blockName = blockName
      self._numberOfFiles = numberOfFiles
      self._numberOfBytes = numberOfBytes
      self._eventCollectionList = eventCollectionList


