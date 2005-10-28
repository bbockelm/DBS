#!/usr/bin/env python
import sys, os, string, re
import xml.sax
import urllib
 
import sys
sys.path.append('./DBSAPI')
import dbsCgiApi

class DBSError:
  def __init__(self, owner, dataset):
    print '\nERROR accessing DBS for Owner/Dataset: '+owner+'/'+dataset+'\n'
    pass

class DBSInfoError:
  def __init__(self, url):
    print '\nERROR accessing DBS url : '+url+'\n'
    pass

##################################################################################
# Class to extract info from DBS 
###############################################################################

class DBSInfo:
     def __init__(self, owner, dataset, dataTiers):
          self.owner = owner
          self.dataset = dataset
          self.dataTiers = dataTiers
          self.dbspath=dataset+'/datatier/'+owner
          
          self.api = dbsCgiApi.DbsCgiApi(cgiUrl="http://cern.ch/cms-dbs/cgi-bin") 

# ####################################
     def getDatasetProvenance(self):
         """
          query DBS to get provenance
         """
         datasetParentList = self.api.getDatasetProvenance(self.dbspath,self.dataTiers)
                                                                                                                     
         parent = {}
         for aparent in datasetParentList:
           print "DBSInfo: parent path is "+aparent.getDatasetPath()+" datatier is: "+aparent.getDataTier()
           parent[aparent.getDatasetPath()]=aparent.getDataTier()

         return parent

# ####################################
     def getDatasetContents(self):
         """
          query DBS to get event collections
         """

         fileBlockList = self.api.getDatasetContents(self.dbspath)
                                                                                                                     
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
              nevts=nevts+eventCollection.getNumberOfEvents()
            print "DBSInfo: total nevts %i in block %s "%(nevts,fileBlock.getBlockName())
            nevtsbyblock[fileBlock.getBlockName()]=nevts

         # returning a map of fileblock-nevts  will be enough for now
         # TODO: in future the EvC collections grouped by fileblock should be returned
         
         return nevtsbyblock

