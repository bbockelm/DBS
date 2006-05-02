# This file is generated on date XXXX

import dbsException
import exceptions
from dbsBaseObject import *

class  DbsPrimaryDataset(DbsBase):
   """ 
   Class for PrimaryDataset

   Following input parameters:
              primaryDatasetId, User may not need to set this variable always
              name, Probably a required variable
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()

class  DbsApplication(DbsBase):
   """ 
   Class for Application

   Following input parameters:
              applicationId, User may not need to set this variable always
              executable, Probably a required variable
              version, Probably a required variable
              family, Probably a required variable
              parameterSet, Probably a required variable
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()

class  DbsProcessingPath(DbsBase):
   """ 
   Class for ProcessingPath

   Following input parameters:
              pathId, User may not need to set this variable always
              parent, User may not need to set this variable always
              application, Probably a required variable
              dataTier, Probably a required variable
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()

class  DbsProcessedDataset(DbsBase):
   """ 
   Class for ProcessedDataset

   Following input parameters:
              id, User may not need to set this variable always
              processedDatasetName, Probably a required variable
              primaryDatasetName, Probably a required variable
              processingPath, Probably a required variable
              isDatasetOpen, Probably a required variable
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()

class  DbsFile(DbsBase):
   """ 
   Class for File

   Following input parameters:
              id, User may not need to set this variable always
              guid, User may not need to set this variable always
              logicalFileName, Probably a required variable
              checksum, User may not need to set this variable always
              fileSize, User may not need to set this variable always
              fileStatus, Probably a required variable
              fileType, Probably a required variable
              fileBlockId, Probably a required variable
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()

class  DbsEventCollection(DbsBase):
   """ 
   Class for EventCollection

   Following input parameters:
              collectionId, User may not need to set this variable always
              collectionIndex, Probably a required variable
              numberOfEvents, User may not need to set this variable always
              status, User may not need to set this variable always
              collectionName, Probably a required variable
              datasetPathName, Probably a required variable
              parent, User may not need to set this variable always
              parentageType, User may not need to set this variable always
              fileList, User may not need to set this variable always
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()

class  DbsBlock(DbsBase):
   """ 
   Class for Block

   Following input parameters:
              blockId, User may not need to set this variable always
              blockStatusName, Probably a required variable
              blockName, User may not need to set this variable always
              numberOfFiles, Probably a required variable
              numberOfBytes, Probably a required variable
              eventCollectionList, User may not need to set this variable always
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()


