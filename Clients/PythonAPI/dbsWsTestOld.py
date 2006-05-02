#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import os, sys
from dbsWsApi import DbsWsApi
from dbsException import DbsException
from dbsApi import DbsApi, DbsApiException, InvalidDataTier, DBS_LOG_LEVEL_ALL_

try:
  args = {}
  api = DbsWsApi()
  api.setLogLevel(DBS_LOG_LEVEL_ALL_)
  # api.setDebug(1)

  # Datasets we play with
  datasetPattern = "/*/*/eg_2x1033PU761_TkMu_2_g133_OSC"
  datasetPath = "/eg03_jets_1e_pt170up/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
  otherDatasetPath = "bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"

  # List some datasets
  print ""
  print "Listing datasets %s" % datasetPattern
  datasets = api.listDataset (datasetPattern)
  for dataset in datasets:
    print "  %s" % dataset

  # Get dataset provenance. It returns list of dataset parents.
  print ""
  tiers = [ "Hit" ]
  """
  print "Provenance for: %s (dataTiers: %s)" % (datasetPath, tiers)
  for parent in api.getDatasetProvenance(datasetPath, tiers):
    print "  %s" % parent
  """

  print ""
  tiers = [ "Digi", "Hit" ]
  """
  print "Provenance for: %s (dataTiers: %s)" % (otherDatasetPath, tiers)
  for parent in api.getDatasetProvenance(otherDatasetPath, tiers):
    print "  %s" % parent
  """

  # Get dataset contents, returning a list of blocks with event collections
  print ""
  print "Dataset contents for: %s" % otherDatasetPath
  for block in api.getDatasetContents(otherDatasetPath, False):
    print "  File block name/id: %s/%d, %d event collections}" % \
     (block._blockName, block._blockId, len(block._eventCollectionList))

  # Get dataset contents as a list of blocks with files
  print ""
  print "Dataset files for: %s" % datasetPath
  for block in api.getDatasetContents(datasetPath, True):
    nfiles = 0
    for evc in block._eventCollectionList:
      nfiles += len(evc._fileList)
    print "  File block name/id: %s/%d, %d files, %d evcs}" % \
      (block._blockName, block._blockId, nfiles, len(block._eventCollectionList))

except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
