#!/usr/bin/env python

import sys, os, re, string
#
import DataDiscovery
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
  print '\t',sys.argv[0],' <owner> <dataset> <comma separeted list of datatiers>'

###########################################################################
if __name__ == '__main__':
        """
        Test program to perform Data Discovery via DBS query
        """

        if len(sys.argv)<=3:
          usage()
          sys.exit(1)

        owner = sys.argv[1]
        dataset = sys.argv[2]
        dataTiersList = sys.argv[3]

#     owner ='eg_Hit752_g133'
#     dataset ='eg03_hzz_2e2mu_350'

        dataTiers = dataTiersList.split(',')
        

        try:
           pubdata=DataDiscovery.DataDiscovery(owner,
                                               dataset,
                                               dataTiers, dataTiers)
           pubdata.fetchDBSInfo()

        except DataDiscovery.DataDiscoveryError:
                print 'ERROR ***: accessing DataDiscovery'
                sys.exit(1)


        print "\nThe information extracted from DBS needed by CRAB are:"
        ## get list of all dataset-owner pairs        
        print "\n List of all required data (needed for site-local catalogue discovery) : "
        DBSPaths=pubdata.getDBSPaths()
        for path in DBSPaths:
          print " --> "+path 

        ## get max number of events

        print "\n Number of events for primary fileblocks (needed for job splitting) : %i"%pubdata.getMaxEvents()

        ## get fileblocks : this is the input for DLS
        fb=pubdata.getFileBlocks()
        print "\n FileBlock names (needed as input for DLS) : %s \n"%fb

        sys.exit(0)

