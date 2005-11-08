#!/usr/bin/env python
import sys, os, string, re
sys.path.append('./DBSAPI')
import dbsCgiApi
import dbsApi

class DBSError:
  def __init__(self, dbspath):
    print '\nERROR accessing DBS for dataset '+dbspath+'\n' 
    pass

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
          
          self.api = dbsCgiApi.DbsCgiApi(cgiUrl="http://cern.ch/cms-dbs/cgi-bin") 
#          self.api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)

# ####################################
     def getDatasetProvenance(self):
         """
          query DBS to get provenance
         """
         try:
           datasetParentList = self.api.getDatasetProvenance(self.dbspath,self.dataTiers)
         except dbsCgiApi.DbsCgiApiException:
           raise DBSError(self.dbspath) 
         return datasetParentList                                                                                                            
# ####################################
     def getDatasetContents(self):
         """
          query DBS to get event collections
         """
         try:
           fileBlockList = self.api.getDatasetContents(self.dbspath)
         except dbsCgiApi.DbsCgiApiException:
           raise DBSError(self.dbspath)                                                                                                           
         ## get the fileblock and event collections
         nevtsbyblock= {}
         for fileBlock in fileBlockList:
            ## get the event collections for each block
            print "DBSInfo: --- block: "+fileBlock.getBlockName()
            eventCollectionList = fileBlock.getEventCollectionList()
            nevts=0
            for eventCollection in eventCollectionList:
              #print "DBSInfo:  evc: "+eventCollection.getCollectionName()+" nevts: %i"%eventCollection.getNumberOfEvents()
              nevts=nevts+eventCollection.getNumberOfEvents()
            print "DBSInfo: total nevts %i in block %s "%(nevts,fileBlock.getBlockName())
            #common.logger.debug(6,"DBSInfo: total nevts %i in block %s "%(nevts,fileBlock.getBlockName()))
            nevtsbyblock[fileBlock.getBlockName()]=nevts

         # returning a map of fileblock-nevts  will be enough for now
         # TODO: in future the EvC collections grouped by fileblock should be returned
         
         return nevtsbyblock

