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

class  DbsFileBlock(DbsBase):
   """ 
   Class for FileBlock

   Following input parameters:

              Name, User may not need to set this variable always
              Status, User may not need to set this variable always
              BlockSize, User may not need to set this variable always
              NumberOfFiles, User may not need to set this variable always
              OpenForWriting, User may not need to set this variable always
              Dataset, User may not need to set this variable always
              fileList, User may not need to set this variable always
   """
   def __init__(self, **args):
      DbsBase.__init__(self)
      # List type object fileList needs to be initialized
      # to avoid return "None" instead of empty list []
      self.setdefault('fileList', [])
      # Read in all User provided values
      self.update(args)
      # Verifying that data types of user provide parameters is correct
      # Validating the data using ValidationTable(.py)
      self.validate()


