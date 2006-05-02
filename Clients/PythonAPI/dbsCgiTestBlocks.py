#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsCgiApi import DbsCgiApi
from dbsException import DbsException
from dbsApi import DbsApi, DbsApiException, InvalidDataTier, DBS_LOG_LEVEL_ALL_

DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/dbsxml"
# DEFAULT_URL = "exec:../../Servers/CGIServer/dbsxml"

try:
  args = {}
  if len(sys.argv) == 2: args['instance'] = sys.argv[1]
  api = DbsCgiApi(DEFAULT_URL, args)
  api.setLogLevel(DBS_LOG_LEVEL_ALL_)
  # api.setDebug(1)

  # List all datasets and count of files in all their blocks
  print "Listing datasets blocks/files"
  blocks = files = 0
  for dataset in api.listDatasets(): # "/*/Hit/*"
    print "Files for: %s" % dataset
    for block in api.getDatasetFileBlocks (dataset):
      blocks += 1
      files += len(block.getFileList())
      print "  %s: %d files" % (block.getBlockName(), len(block.getFileList()))

  print >> sys.stderr, "%d blocks, %d files" % (blocks, files)

except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
