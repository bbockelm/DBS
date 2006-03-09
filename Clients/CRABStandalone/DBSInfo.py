#!/usr/bin/env python
import sys, os, string, re
import exceptions
try:
 sys.path.append('../PythonAPI')
 import dbsCgiApi
 import dbsApi
except:
  msg="ERROR no DBS API available"
  #raise CrabException(msg)
  print msg
  sys.exit(1)

# #######################################
class DBSError(exceptions.Exception):
  def __init__(self, errorName, errorMessage):
   args='\nERROR DBS %s : %s \n'%(errorName,errorMessage)
   exceptions.Exception.__init__(self, args)
   pass

  def getErrorMessage(self):
   """ Return error message """
   return "%s" % (self.args)

# #######################################
class DBSInvalidDataTierError(exceptions.Exception):
  def __init__(self, errorName, errorMessage):
   args='\nERROR DBS %s : %s \n'%(errorName,errorMessage)
   exceptions.Exception.__init__(self, args)
   pass

  def getErrorMessage(self):
   """ Return error message """
   return "%s" % (self.args)

# #######################################
class DBSInfoError:
  def __init__(self, url):
    print '\nERROR accessing DBS url : '+url+'\n'
    pass

##################################################################################
# Class to extract info from DBS 
###############################################################################

class DBSInfo:
     def __init__(self):
          # Construct api object
          self.api = dbsCgiApi.DbsCgiApi() 
          # Configure api logging
          self.api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)
          self.api.setLogLevel(dbsApi.DBS_LOG_LEVEL_INFO_)

     def getMatchingDatasets (self, owner, dataset):
         """ Query DBS to get provenance """
         try:
           list = self.api.listDatasets("/%s/*/%s" % (dataset, owner))
         except dbsApi.InvalidDataTier, ex:
           raise DBSInvalidDataTierError(ex.getClassName(),ex.getErrorMessage())
         except dbsApi.DbsApiException, ex:
           raise DBSError(ex.getClassName(),ex.getErrorMessage())
         return list 

     def getDatasetProvenance(self, path, dataTiers):
         """ Query DBS to get provenance """
         try:
           datasetParentList = self.api.getDatasetProvenance(path,dataTiers)
         except dbsApi.InvalidDataTier, ex:
           raise DBSInvalidDataTierError(ex.getClassName(),ex.getErrorMessage())
         except dbsApi.DbsApiException, ex:
           raise DBSError(ex.getClassName(),ex.getErrorMessage())
         return datasetParentList                                                                                                            

     def getDatasetContents(self, path):
         """ Query DBS to get event collections """
         # count events per block
         nevtsbyblock = {}
         try:
           for fileBlock in self.api.getDatasetContents (path):
              ## get the event collections for each block
	      nevts = 0
	      for evc in fileBlock.getEventCollectionList():
		nevts = nevts + evc.getNumberOfEvents()
              print "DBSInfo: total nevts %i in block %s "%(nevts,fileBlock.getBlockName())
              nevtsbyblock[fileBlock.getBlockName()]=nevts
         except dbsApi.DbsApiException, ex:
           raise DBSError(ex.getClassName(),ex.getErrorMessage())

         # returning a map of fileblock-nevts  will be enough for now
         # TODO: in future the EvC collections grouped by fileblock should be returned
         return nevtsbyblock
