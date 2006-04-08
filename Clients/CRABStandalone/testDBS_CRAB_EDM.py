#!/usr/bin/env python

import sys, os, re, string
#
import DataDiscovery_EDM
#

###########################################################################
def usage():
  """
  print the command line and exit
  """
  print sys.argv[0],'\n'
  print '\tScript to check Data Discovery via DBS '
  print '\tAuthor: Alessandra Fanfani  <fanfani@bo.infn.it>  INFN Bologna\n'
  print '\tusage:'
  print '\t',sys.argv[0],' <datasetPath> '

###########################################################################
if __name__ == '__main__':
        """
        Test program to perform Data Discovery via DBS query
        """

        if len(sys.argv)<=1:
          usage()
          sys.exit(1)

        datasetPath = sys.argv[1]
## An example of datasetPath that is "Dev/fanfani" is:
#      datasetPath = "/TestPreProdMu10GeV/SIM/GenSimDigi"


        #dataTiersList = sys.argv[2]
        dataTiersList = ""

        dataTiers = dataTiersList.split(',')
        

        try:
           pubdata=DataDiscovery_EDM.DataDiscovery_EDM(datasetPath,
                                               dataTiers, dataTiers)
           pubdata.fetchDBSInfo()

        except DataDiscovery_EDM.DataDiscoveryError, ex:
                print 'ERROR ***: failed Data Discovery in DBS : %s'%ex.getErrorMessage()
                sys.exit(1)
        except DataDiscovery_EDM.NotExistingDatasetError, ex :
                print 'ERROR ***: failed Data Discovery in DBS : %s'%ex.getErrorMessage()
                sys.exit(1)


        print "\nThe information extracted from DBS needed by CRAB to configure CMSSW jobs are (???):" 
        ## get max number of events
        print "\n Number of events for primary fileblocks (needed for job splitting) : %i"%pubdata.getMaxEvents()
        ## get files grouped by fileblocks 
        filesbyblock=pubdata.getFiles()
        print "\n Filenames to be read %s"%filesbyblock.values()
        print "\n FileBlock names (needed as input for DLS) : %s"%filesbyblock.keys()

        sys.exit(0)

