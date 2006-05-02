#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys, time
from dbsWsApi import DbsWsApi
from dbsException import DbsException
from dbsClientDatastructures import DbsFile, DbsBlock, DbsApplication, DbsEventCollection, DbsPrimaryDataset, DbsProcessedDataset, DbsProcessingPath
from dbsApi import DbsApi, DbsApiException, InvalidDataTier, DBS_LOG_LEVEL_ALL_

DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/prodquery"
# DEFAULT_URL = "exec:../../Servers/CGIServer/prodquery"

try:
  nop = 1
  if (len(sys.argv) > 1): nop = int(sys.argv[1])

  nevc = 1000
  if (len(sys.argv) > 2): nevc = int(sys.argv[2])

  api = DbsWsApi()
  api.setLogLevel(DBS_LOG_LEVEL_ALL_)
  # api.setDebug(1)

  # Make a new test dataset, insert group of event collections
  basename = "test_big_%f" % time.time()
  primary = DbsPrimaryDataset (name = basename)
  app = DbsApplication(parameterSet = basename, family = 'testfamily', version='testversion', executable='testexe')
  ppath = DbsProcessingPath(dataTier = "GEN", application = app)
  block = DbsBlock (numberOfFiles = nevc, numberOfBytes = nevc, blockStatusName = 'test')
  gen = DbsProcessedDataset (isDatasetOpen=True,
			     processingPath=ppath,
                             primaryDatasetName=basename,
                             processedDatasetName="test_process")
  all = []
  for i in range(nop):
    files = []
    evcs = []
    all.append ((files, evcs))
    for j in range(nevc/nop):
      f = DbsFile (fileBlockId = -1, logicalFileName="%s.%d.%d" % (basename, i, j),
		   fileSize=1, checksum="cksum:1", fileType="EVD", fileStatus = 'test')
      e = DbsEventCollection (datasetPathName = '/%s/GEN/test_process' % basename,
			      collectionName="%s.%d.%d.GEN" % (basename, i, j),
			      numberOfEvents=1, collectionIndex= i*nop + j, fileList=[f])
      files.append (f)
      evcs.append (e)

  print "Creating primary dataset %s" % primary
  api.createPrimaryDataset (primary)

  print "Creating processed dataset %s" % gen
  api.createProcessedDataset (gen)

  print "Creating file block %s" % block
  bid = api.createFileBlock ("/%s/GEN/test_process" % basename, block)

  for (files, evcs) in all:
    for f in files: f._fileBlockId = bid
    print "Inserting %d evcs into %s" % (len(evcs), gen)
    api.insertEventCollections (evcs)

except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
