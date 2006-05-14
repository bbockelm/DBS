#!/usr/bin/env python2
import sys, os, string, re
from DBSInfo import *


# ####################################
class DataDiscoveryError(exceptions.Exception):
  def __init__(self, errorMessage):
   args=errorMessage
   exceptions.Exception.__init__(self, args)
   pass

  def getErrorMessage(self):
   """ Return exception error """
   return "%s" % (self.args)

# ####################################
class NotExistingDatasetError(exceptions.Exception):
  def __init__(self, errorMessage):
   args=errorMessage
   exceptions.Exception.__init__(self, args)
   pass

  def getErrorMessage(self):
   """ Return exception error """
   return "%s" % (self.args)

# ####################################
class NoDataTierinProvenanceError(exceptions.Exception):
  def __init__(self, errorMessage):
   args=errorMessage
   exceptions.Exception.__init__(self, args)
   pass

  def getErrorMessage(self):
   """ Return exception error """
   return "%s" % (self.args)

# ####################################
# class to find and extact info from published data
class DataDiscovery:
    def __init__(self, owner, dataset, dataTiers, cfg_params):

#       Attributes
	self.owner = owner
	self.dataset = dataset
        self.dataTiers = dataTiers
        self.cfg_params = cfg_params

        self.dbspaths= []     # DBS output: list of dbspaths for all data
        self.allblocks = []   # DBS output: list of map fileblocks-totevts for all dataset-owners
        self.blocksinfo = {}  # DBS output: map fileblocks-totevts for the primary block, used internally to this class
#DBS output: max events computed by method getMaxEvents 

# ####################################
    def fetchDBSInfo(self):
        """
        Contact DBS
        """
        ## add the PU among the required data tiers if the Digi are requested
        if (self.dataTiers.count('Digi')>0) & (self.dataTiers.count('PU')<=0) :
          self.dataTiers.append('PU')

        ## get info about the requested dataset 
        dbs = DBSInfo()
	self.datasets = dbs.getMatchingDatasets(self.owner, self.dataset)
	if len(self.datasets) == 0:
	  raise DataDiscoveryError("Owner=%s, Dataset=%s unknown to DBS" % (self.owner, self.dataset))
	if len(self.datasets) > 1:
	  raise DataDiscoveryError("Owner=%s, Dataset=%s is ambiguous" % (self.owner, self.dataset))

        try:
	  self.dbsdataset = self.datasets[0].get('datasetPathName')
          self.blocksinfo = dbs.getDatasetContents(self.dbsdataset)
	  self.allblocks.append (self.blocksinfo.keys ())
          self.dbspaths.append(self.dbsdataset)
        except DBSError, ex:
          raise DataDiscoveryError(ex.getErrorMessage())
        
        if len(self.blocksinfo) <= 0:
         raise NotExistingDatasetError (("\nNo data for %s in DBS\nPlease check"
	 				+ " dataset/owner variables in crab.cfg")
					% self.dbsdataset)

        ## get info about the parents
        try:
          parents = dbs.getDatasetProvenance(self.dbsdataset, self.dataTiers)
        except DBSInvalidDataTierError, ex:
          raise DataDiscoveryError(ex.getErrorMessage ()
	    + "\n Check the data_tier variable in crab.cfg !\n")
        except DBSError, ex:
          raise DataDiscoveryError(ex.getErrorMessage())

        ## verify that all requested parent data tiers were returned
        self.checkParentDataTier(parents, self.dataTiers) 

        ## for each parent get the corresponding fileblocks
	try:
          for p in parents:
            ## fill a list of dbspaths
	    #parentPath = p.get('datasetPathName')
            parentPath = p.get('parent').get('datasetPathName')
	    self.dbspaths.append (parentPath)
	    parentBlocks = dbs.getDatasetContents (parentPath)
	    self.allblocks.append (parentBlocks.keys ())
        except DBSError, ex:
          raise DataDiscoveryError(ex.getErrorMessage())

# #################################################
    def checkParentDataTier(self, parents, dataTiers):
        """
         check that the data tiers requested by the user really exists in the provenance of the given dataset
        """
	startType = string.split(self.dbsdataset,'/')[2]
        # for example 'type' is PU and 'dataTier' is Hit
        parentTypes = map(lambda p: p.get('type'), parents)
        for tier in dataTiers:
	  if parentTypes.count(tier) <= 0 and tier != startType:
            raise NoDataTierinProvenanceError(("\nData %s not published in DBS with asked data tiers :"
	    				       + " the data tier not found is %s !\n  Check the data_tier"
					       + " variable in crab.cfg !") % (self.dbsdataset,tier))

# #################################################
    def getMaxEvents(self):
        """
         max events of the primary dataset-owner
        """
        ## loop over the fileblocks of the primary dataset-owner
        nevts=0       
        for blockevts in self.blocksinfo.values():
          nevts=nevts+blockevts

        return nevts

# #################################################
    def getDBSPaths(self):
        """
         list the DBSpaths for all required data
        """
        return self.dbspaths

# #################################################
    def getEVC(self):
        """
         list the event collections structure by fileblock 
        """
        print "To be used by a more complex job splitting... TODO later... "
        print "it requires changes in what's returned by DBSInfo.getDatasetContents and then fetchDBSInfo"

# #################################################
    def getFileBlocks(self):
        """
         fileblocks for all required dataset-owners 
        """
        return self.allblocks        

########################################################################


