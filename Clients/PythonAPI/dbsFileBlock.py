# This file is generated on date XXXX

import dbsException
from dbsBaseObject import *

class  DbsFileBlock(DbsBase):
   """ 
   Class for FileBlock

   Following input parameters:
              objectId, User may not need to set this variable always
              blockName, User may not need to set this variable always
              processing, User may not need to set this variable always
              blockStatusName, Probably a required variable
              numberOfBytes, Probably a required variable
              numberOfFiles, Probably a required variable
              fileList, User may not need to set this variable always
              eventCollectionList, User may not need to set this variable always
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # Read in all User provided values
      self.setdefault('fileList', [])
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()

   def addFile (self, file):
    """ Add event collection. """
    #if not isinstance(file, DbsFile):
    #  file = DbsFile (dict = file)
    self['fileList'].append(file)


