#!/usr/bin/env python
#
# $Id: dbsPrimaryDataset.py,v 1.3 2005/12/09 16:22:03 sveseli Exp $
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
    result = self.get(DATASET_DESCRIPTION_TAG_)
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % DATASET_DESCRIPTION_TAG_)
    return result

  def getPhysicsGroupName(self):
    """ Retrieve physics group name. """
    result = self.get(PHYSICS_GROUP_NAME_TAG_)
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % PHYSICS_GROUP_NAME_TAG_)
    return result

  def getTriggerDescription(self):
    """ Retrieve trigger description. """
    result = self.get(TRIGGER_DESCRIPTION_TAG_)
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % TRIGGER_DESCRIPTION_TAG_)
    return result

  def getMonteCarloDescription(self):
    """ Retrieve mc description. """
    result = self.get(MONTE_CARLO_DESCRIPTION_TAG_)
    #if result == None:
    #  raise dbsException.DataNotInitialized(args="Value for %s has not been set." % MONTE_CARLO_DESCRIPTION_TAG_)
    return result


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
  
  dataset2 = DbsPrimaryDataset(datasetName="ds2",
			      physicsGroupName="top",
			      monteCarloDescription=mc)
  print dataset2
  try:
    dataset2.getDatasetDescription()
  except dbsException.DbsException, ex:
    print "caught: %s" % ex
  print "Done"


