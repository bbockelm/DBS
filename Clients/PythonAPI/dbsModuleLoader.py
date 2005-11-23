#!/usr/bin/env python
#
# $Id: dbsModuleLoader.py,v 1.3 2005/11/07 21:40:02 sveseli Exp $
#
# Base DBS Web Service Exception class. 
#


import string
import imp
import sys

import dbsException
import dbsStaticMethod


PY_FILE_SUFFIX_ = ".py"
PYC_FILE_SUFFIX_ = ".pyc"

##############################################################################
# Module loader exceptions.

class DbsModuleLoaderError(dbsException.DbsException):

  def __init__(self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

##############################################################################
# Module loader class.

class DbsModuleLoader:

  def filterModuleName(moduleName):
    """ Filter module name. """
    try:
      baseName = moduleName
      pos = string.find(baseName, PY_FILE_SUFFIX_)
      if pos > 0:
	baseName = baseName[0:pos]
      return baseName
    except Exception, ex:
      raise DbsModuleLoaderError(exception=ex)

  filterModuleName = dbsStaticMethod.DbsStaticMethod(filterModuleName)
  
  def isModuleLoaded(moduleName):
    """ Is module loaded? """
    baseName = DbsModuleLoader.filterModuleName(moduleName)
    if sys.modules.has_key(baseName):
      return True
    else:
      return False

  isModuleLoaded = dbsStaticMethod.DbsStaticMethod(isModuleLoaded)

  def findModule(moduleName, moduleDir = None):
    """ Find python module, return (file, fileName, description). """
    try:
      searchPath = None
      if moduleDir is not None:
	searchPath = [moduleDir]
      baseName = DbsModuleLoader.filterModuleName(moduleName)
      (file, fileName, fileDesc) = imp.find_module(baseName, searchPath)
      return (file, fileName, fileDesc)
    except DbsModuleLoaderError, ex:
      # Just re-raise this one.
      raise 
    except Exception, ex:
      raise DbsModuleLoaderError(exception=ex)

  findModule = dbsStaticMethod.DbsStaticMethod(findModule)

  def loadModuleFast(moduleName, moduleDir = None):
    """ Load python module (do not reload if it has been loaded before). """
    baseName = DbsModuleLoader.filterModuleName(moduleName)
    if sys.modules.has_key(baseName):
      return sys.modules[baseName]
    else:
      return DbsModuleLoader.loadModule(moduleName, moduleDir)

  loadModuleFast = dbsStaticMethod.DbsStaticMethod(loadModuleFast)
    
  def loadModule(moduleName, moduleDir = None):
    """ Load python module, regardless if it has been loaded before or not. """
    module = None
    try:
      try:
	file = None
	baseName = DbsModuleLoader.filterModuleName(moduleName)
	(file, fileName, fileDesc) = DbsModuleLoader.findModule(
	  moduleName, moduleDir)
	module = imp.load_module(baseName, file, fileName, fileDesc)
	return module
      finally:
	if file is not None:
	  file.close()
    except DbsModuleLoaderError, ex:
      # Just re-raise this one.
      raise 
    except Exception, ex:
      raise DbsModuleLoaderError(exception=ex)

  loadModule = dbsStaticMethod.DbsStaticMethod(loadModule)
    
##############################################################################
# Unit tests.

if __name__ == "__main__":

  module = DbsModuleLoader.loadModule("dbsApi.py")
  print "Loaded: ", module
  print "IsLoaded: ", DbsModuleLoader.isModuleLoaded("dbsApi.py")
  print "Load fast: ", DbsModuleLoader.loadModuleFast("dbsApi.py")
  print "Done"
