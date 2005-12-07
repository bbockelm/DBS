#!/usr/bin/env python
#
# $Id: dbsPrimaryDataset.py,v 1.1 2005/12/07 17:21:11 sveseli Exp $
#
# Dataset class. 
#

import types
import string

import dbsObject
import dbsException
import dbsDataset
import dbsMonteCarloDescription

PHYSICS_GROUP_NAME_TAG_ = "physicsGroupName"
DATASET_DESCRIPTION_TAG_ = "datasetDescription"
TRIGGER_DESCRIPTION_TAG_ = "triggerDescription"
MONTE_CARLO_DESCRIPTION_TAG_ = "monteCarloDescription"
PRIMARY_DATASET_ID_TAG_ = "primaryDatasetId"


WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"


##############################################################################
# DBS primary dataset class.

class DbsPrimaryDataset(dbsDataset.DbsDataset):

  def __init__(self, datasetName=None, physicsGroupName=None,
	       datasetDescription=None, triggerDescription=None,
	       monteCarloDescription=None,
	       datasetDict={}):
    """ Constructor. """
    dbsDataset.DbsDataset.__init__(self, datasetName=datasetName,
				   datasetDict=datasetDict)
    if datasetDescription is not None:
      self[DATASET_DESCRIPTION_TAG_] = str(datasetDescription)

    if triggerDescription is not None:
      self[TRIGGER_DESCRIPTION_TAG_] = str(triggerDescription)

    if monteCarloDescription is not None:
      self[MONTE_CARLO_DESCRIPTION_TAG_] = monteCarloDescription

    if physicsGroupName is not None:
      self[PHYSICS_GROUP_NAME_TAG_] = str(physicsGroupName)

    # Correct mc description if needed.
    mcDescription = self.get(MONTE_CARLO_DESCRIPTION_TAG_)
    if mcDescription != None and not isinstance(
      mcDescription, dbsMonteCarloDescription.DbsMonteCarloDescription):
      try:
	self[MONTE_CARLO_DESCRIPTION_TAG_] = dbsMonteCarloDescription.DbsMonteCarloDescription(mcDescriptionDict=mcDescription)
      except Exception, ex:
	raise dbsException.InvalidArgument(args="Argument %s cannot be converted into a dbsMonteCarloDescription.DbsMonteCarloDescription object." % mcDescription)

    self.setNamespace(WSDL_NAMESPACE_)


  def getDatasetDescription(self):
    """ Retrieve dataset description. """
    return self.get(DATASET_DESCRIPTION_TAG_)

  def getPhysicsGroupName(self):
    """ Retrieve physics group name. """
    return self.get(PHYSICS_GROUP_NAME_TAG_)

  def getTriggerDescription(self):
    """ Retrieve trigger description. """
    return self.get(TRIGGER_DESCRIPTION_TAG_)

  def getMonteCarloDescription(self):
    """ Retrieve mc description. """
    return self.get(MONTE_CARLO_DESCRIPTION_TAG_)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  mc = dbsMonteCarloDescription.DbsMonteCarloDescription(
      description="MyMonteCarloDescription",
    production="production",
    decayChain="decayChain",
    isMcData="true")

  dataset = DbsPrimaryDataset(datasetName="ds1",
			      datasetDescription="my dataset desc",
			      physicsGroupName="top",
			      monteCarloDescription=mc)
  
  print dataset
  print "Adding myAttr to the dataset"
  dataset["myAttr"] = "myValue"
  print dataset
  

  print "Done"


