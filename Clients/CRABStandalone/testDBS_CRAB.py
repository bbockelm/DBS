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

        dataTiers = dataTiersList.split(',')
        

        try:
           pubdata=DataDiscovery.DataDiscovery(owner,
                                               dataset,
                                               dataTiers, dataTiers)
           pubdata.fetchDBSInfo()

        except DataDiscovery.DataDiscoveryError:
                print 'ERROR ***: accessing DataDiscovery'
                sys.exit(1)


        ## get list of all dataset-owner pairs        
        print "\n List of all  dataset-owner pairs needed: "
        dsowmap=pubdata.getDatasetOwnerPairs()
        for ow in dsowmap.keys():
          print "  dataset "+dsowmap[ow]+" owner "+ow

        ## get max number of events
        print " number of events for primary fileblocks %i"%pubdata.getMaxEvents()

        ## get fileblocks : this is the input for DLS
        fb=pubdata.getFileBlocks()

        sys.exit(0)

