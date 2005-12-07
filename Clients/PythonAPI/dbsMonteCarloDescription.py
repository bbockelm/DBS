#!/usr/bin/env python
#
# $Id: dbsMonteCarloDescription.py,v 1.1 2005/12/07 17:21:11 sveseli Exp $
#
# MC Description class. 
#

import types
import string

import dbsObject
import dbsException


MC_DESCRIPTION_TAG_ = "description"
MC_PRODUCTION_TAG_ = "production"
IS_MC_DATA_TAG_ = "isMcData"
MC_DECAY_CHAIN_TAG_ = "decayChain"
MC_DESCRIPTION_DICT_TAG_ = "mcDescriptionDict"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"

##############################################################################
# DBS MC Description class exceptions.

class DbsMonteCarloDescriptionException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)
    

##############################################################################
# DBS dataset class.

class DbsMonteCarloDescription(dbsObject.DbsObject):

  def __init__(self, description=None, production=None,
	       isMcData=None, decayChain=None, mcDescriptionDict={}):
    """ Constructor. """
    dbsObject.DbsObject.__init__(self, mcDescriptionDict)
    if description is not None:
      self[MC_DESCRIPTION_TAG_] = str(description)
    if production is not None:
      self[MC_PRODUCTION_TAG_] = str(production)
    if isMcData is not None:
      self[IS_MC_DATA_TAG_] = isMcData
    if decayChain is not None:
      self[MC_DECAY_CHAIN_TAG_] = isMcData

    self.setNamespace(WSDL_NAMESPACE_)

  def getDescription(self):
    """ Retrieve mc description. """
    return self.get(MC_DESCRIPTION_TAG_)

  def getProduction(self):
    """ Retrieve mc production. """
    return self.get(MC_PRODUCTION_TAG_)

  def getDecayChain(self):
    """ Retrieve decay chain. """
    return self.get(MC_DECAY_CHAIN_TAG_)

  def isMcData(self):
    """ Retrieve mc data flag. """
    return self.get(IS_MC_DATA_TAG_)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  mc = DbsMonteCarloDescription(
    description="MyMonteCarloDescription",
    production="production",
    decayChain="decayChain",
    isMcData="true")
  print mc
  print "Is MC Data: ", mc.isMcData()
  
  print "Done"


