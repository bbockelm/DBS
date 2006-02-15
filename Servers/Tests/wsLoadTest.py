#!/usr/bin/env python
#
# $Id: wsLoadTest.py,v 1.1 2006/02/14 18:51:43 lueking Exp $
#
# Web service test.
#
# Base API class provides some common functionality (e.g., logging
# configuration). Exception modules are defined in the dbsApi module.
#
import sys
import os.path
import string
#import SOAPpy
import time
#
# import dbs stuff
#
try:
  sys.path.append('../python')
  sys.path.append('../python/lib')
  import dbsException
  import dbsApi
  import dbsWsClient
  import dbsWsApi
#
  import dbsMonteCarloDescription
  import dbsPrimaryDataset
  import dbsProcessedDataset
  import dbsProcessingPath
  import dbsEventCollection
  import dbsApplication
  import dbsFile
  import dbsFileBlock
except:
  msg="ERROR no DBS API available"
  print msg  

##############################################################################



# Client usage.
def usage():
  clientName = os.path.basename(sys.argv[0])
  print "Usage:"
  print "  %s --dspath=<datasetPath> --n-calls=<number of calls> [--verbose]" % clientName
  print "ex:  wsLoadTest.py --dspath=/sw04_Anzar/DST/sw_DST813_2_g133_OSC --n-calls=1"
##############################################################################
# Web service implementation of the DBS API class. Exceptions are defined
# in dbsApi module.


# Main code. 
if __name__ == "__main__":
  # Simple argument parser, ignore unrecognized arguments.
  dimensionsString = None
  datasetPath = None
  nCalls = 1
  debug=0
  for a in sys.argv[1:]:
    arg = string.split(a, "=")
    if arg[0] == "--dspath":
      datasetPath = arg[1]
    elif arg[0] == "--wsdl":
      wsdl = arg[1]
    elif arg[0] == "--verbose":
      debug = 1
    elif arg[0] == "--n-calls":
      nCalls = int(arg[1])
    else:
      print "Ignoring unrecognized argument: %s" % a
  if not datasetPath:
    usage()
    sys.exit(1)
      
  # Arguments are ok, invoke the service nCalls times, keep statistics.
  nSuccess = 0
  nFailure = 0
  sumDeltaT = 0.0
  minDeltaT = 1.0e32
  maxDeltaT = 0
  api = dbsWsApi.DbsWsApi(wsdlUrl="./DbsDatasetService.wsdl.xml")
  # Configure logging.
  if debug: api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)
  for i in range(0, nCalls):
    try:
      print "\nAttempt #%s" % (i+1)
      t1 = time.time()
      # Get dataset contents. It returns list of file blocks, each
      # file block containing a set of event collections.
      if debug: print "Getting dataset contents for: %s" % datasetPath
      fileBlockList = api.getDatasetContents(datasetPath,False)
      t2 = time.time()
      deltaT = t2-t1
      if deltaT < minDeltaT:
        minDeltaT = deltaT
      if deltaT > maxDeltaT:
        maxDeltaT = deltaT
      sumDeltaT = sumDeltaT + deltaT
      print "Dataset contents for: %s" % datasetPath
      for fileBlock in fileBlockList:
          print ""
          print "File block name/id: %s/%s" % (fileBlock.getBlockName(),
                                           fileBlock.getBlockId())
          for eventCollection in fileBlock.getEventCollectionList():
            print "  %s" % eventCollection
      #fileList = datasetInfo.fileList
      #datasetSizeInBytes = datasetInfo.datasetSizeInBytes
      #print "Dimension string: %s" % (dimensionsString)
      #print "Dataset file list:\n%s" % (fileList)
      #print "Number of files in dataset: %s" % (len(fileList))
      #print "Dataset size: %s bytes" % (datasetSizeInBytes)
      print "Call Duration: %.2f seconds" % (deltaT)
      nSuccess = nSuccess + 1
    #except SOAPpy.Error, ex:
    #    nFailure = nFailure + 1
    #    print "Caught SOAP exception: %s" % ex.faultstring
    except dbsException.DbsException, ex:
        nFailure = nFailure + 1
        print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
    except:
        nFailure = nFailure + 1
        print "Caught %s exception: %s" % (sys.exc_info()[0], sys.exc_info()[1])
  sys.stdout.flush()
  sys.stderr.flush()

  print "\nTest Report"
  print "================"
  print "Total Calls: %s" % nCalls
  print "Successful Calls: %s" % nSuccess
  print "Success Percentage: %.2f" % (nSuccess/float(nCalls)*100.0)
  print "Failed Calls: %s" % nFailure
  print "Failure Percentage: %.2f" % (nFailure/float(nCalls)*100.0)
  if nSuccess > 0:
    print "Average Successful Call Duration: %.4f seconds" % (sumDeltaT/nSuccess)
  print "Maximum Call Duration: %.4f seconds" % (maxDeltaT)
  print "Minimum Call Duration: %.4f seconds" % (minDeltaT)

 
