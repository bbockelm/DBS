#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#

""" This file is generated on Wed Nov  1 15:22:43 2006 """ 

"""SERIOUS WARNING:

         This file is a generated file,
         in case you have made manual changes to  
         any of generated files, make sure you DO NOT
         end up over-writting them by re-running the
         generator and copying them here.

         Either make changes to generator, or carefully
         preserve the manual changes. 
"""
import dbsException
from dbsBaseObject import *

class  DbsFile(DbsBase):
   """ 
   Class for File

   Following input parameters:

              Checksum, User may not need to set this variable always
              LogicalFileName, User may not need to set this variable always
              QueryableMetadata, User may not need to set this variable always
              NumberOfEvents, User may not need to set this variable always
              FileSize, User may not need to set this variable always
              Status, User may not need to set this variable always
              FileType, User may not need to set this variable always
              ValidationStatus, User may not need to set this variable always
              Dataset, User may not need to set this variable always
              Block, User may not need to set this variable always
              lumiList, User may not need to set this variable always
              tierList, User may not need to set this variable always
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # List type object lumiList needs to be initialized
      # to avoid return "None" instead of empty list []
      self.setdefault('lumiList', [])
      # List type object tierList needs to be initialized
      # to avoid return "None" instead of empty list []
      self.setdefault('tierList', [])
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()


