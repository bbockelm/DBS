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
     def __init__(self, dbspath, dataTiers):
          self.dbspath=dbspath 
          self.dataTiers = dataTiers
          # Construct api object
          self.api = dbsCgiApi.DbsCgiApi(cgiUrl="http://cern.ch/cms-dbs/cgi-bin") 
          #self.api = dbsCgiApi.DbsCgiApi(cgiUrl="http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer")
          # Configure api logging
          self.api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)
          self.api.setLogLevel(dbsApi.DBS_LOG_LEVEL_INFO_)
          # lower log level

# ####################################
     def getDatasetProvenance(self):
         """
          query DBS to get provenance
         """
         try:
           datasetParentList = self.api.getDatasetProvenance(self.dbspath,self.dataTiers)
         except dbsApi.InvalidDataTier, ex:
           raise DBSInvalidDataTierError(ex.getClassName(),ex.getErrorMessage())
         except dbsApi.DbsApiException, ex:
           raise DBSError(ex.getClassName(),ex.getErrorMessage())
         return datasetParentList                                                                                                            
         #parent = {}
         #for aparent in datasetParentList:
         #  common.logger.debug(6, "DBSInfo: parent path is "+aparent.getDatasetPath()+" datatier is "+aparent.getDataTier())
         #  parent[aparent.getDatasetPath()]=aparent.getDataTier()
         #
         #return parent

# ####################################
     def getDatasetContents(self):
         """
          query DBS to get event collections
         """
         try:
           fileBlockList = self.api.getDatasetContents(self.dbspath)
         except dbsApi.DbsApiException, ex:
           raise DBSError(ex.getClassName(),ex.getErrorMessage())
         ## get the fileblock and event collections
         nevtsbyblock= {}
         for fileBlock in fileBlockList:
            ## get the event collections for each block
            #print fileBlock.getBlockName()
            #print fileBlock.getBlockId()
            eventCollectionList = fileBlock.getEventCollectionList()
            nevts=0
            for eventCollection in eventCollectionList:
              #print "DBSInfo:  evc: "+eventCollection.getCollectionName()+" nevts: %i"%eventCollection.getNumberOfEvents()
              #common.logger.debug(20,"DBSInfo:  evc: "+eventCollection.getCollectionName()+" nevts:%i"%eventCollection.getNumberOfEvents()) 
              #print "DBSInfo:  evc: "+eventCollection.getCollectionName()+" nevts:%i"%eventCollection.getNumberOfEvents()
              nevts=nevts+eventCollection.getNumberOfEvents()
            #common.logger.debug(6,"DBSInfo: total nevts %i in block %s "%(nevts,fileBlock.getBlockName()))
            print "DBSInfo: total nevts %i in block %s "%(nevts,fileBlock.getBlockName())
            nevtsbyblock[fileBlock.getBlockName()]=nevts

         # returning a map of fileblock-nevts  will be enough for now
         # TODO: in future the EvC collections grouped by fileblock should be returned
         
         return nevtsbyblock

