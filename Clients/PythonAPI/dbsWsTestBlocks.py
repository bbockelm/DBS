#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsWsApi import DbsWsApi
from dbsException import DbsException
from dbsApi import DbsApi, DbsApiException, InvalidDataTier, DBS_LOG_LEVEL_ALL_

try:
  api = DbsWsApi()
  api.setLogLevel(DBS_LOG_LEVEL_ALL_)
  # api.setDebug(1)

  # List all datasets and count of files in all their blocks
  print "Listing datasets blocks/files"
  blocks = files = 0
  for dataset in api.listDataset("*/*/*"): # "/*/Hit/*"
    print "Files for: %s" % dataset
    for block in api.getDatasetContents(dataset,True):
      blocks += 1
      nfiles = 0
      for evc in block._eventCollectionList:
        nfiles += len(evc._fileList)
      files += nfiles
      print "  %s: %d files" % (block._blockName, nfiles)

  print >> sys.stderr, "%d blocks, %d files" % (blocks, files)

except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
