#!/usr/bin/env python

import sys,os,re,string,getopt
#
import DataDiscovery
#

###########################################################################
def usage():
  """
  print the command line and exit
  """
  print "\n This script perfoms DBS Data Discovery (a la CRAB) for a list dataset/owner provided in a file. \n The problematic dataset/owner pairs are written in an output file. \n\n Usage: python checklistinDBS_forCRAB.py <options> \n Options: \n --checklist=<file with the list of dataset/owner to check> \n --badlist=<output file name where the problematic message for dataset/owner are written> \n --help \t\t\t\t print this help \n"

###########################################################################
if __name__ == '__main__':
        """
        Test program to perform Data Discovery via DBS query
        """

        valid = ['checklist=','badlist=','help']
        try:
          opts, args = getopt.getopt(sys.argv[1:], "", valid)
        except getopt.GetoptError, ex:
          usage()
          print str(ex)
          sys.exit(1)

        checklist = None
        badlist = None

        for opt, arg in opts:
          if opt == "--help":
            usage()
            sys.exit(0)
        for opt, arg in opts:
          if opt == "--checklist":
            checklist = arg
        for opt, arg in opts:
          if opt == "--badlist":
            badlist = arg 

	if checklist == None:
	    print "ERROR: --checklist option not provided"
	    usage()
	    sys.exit(1)
	if not os.path.exists(checklist):
	    print "File %s not found" % checklist
	    sys.exit(1)
        if badlist == None:
            print "ERROR: --badlist option not provided"
            usage()
            sys.exit(1)



## Read the file with the dataset/owner pairs to check
        dataTiers =''                                    
     	checklist_file = open(checklist,'r')
        badlist_file = open(badlist,'w')
     	for line in checklist_file.readlines():
            try:
    	         data=string.split(string.strip(line),'/')
 	         dataset=data[0]
	         owner=data[1]
                 print "==== Processing dataset: %s owner: %s"%(dataset,owner)
            except:
                 print "ERROR: \n The File %s content must have lines in the form: \n datasetname/ownername "%checklist
                 sys.exit(1)

            # check a dataset/owner being in DBS  
            try:
               pubdata=DataDiscovery.DataDiscovery(owner,
                                                  dataset,
                                                  dataTiers, dataTiers)
               pubdata.fetchDBSInfo()

            except DataDiscovery.DataDiscoveryError, ex:
                msg='ERROR ***: failed Data Discovery in DBS : %s \n'%ex.getErrorMessage()
                badlist_file.write(msg)
            except DataDiscovery.NoDataTierinProvenanceError, ex :
                msg='ERROR ***: failed Data Discovery in DBS : %s \n'%ex.getErrorMessage()
                badlist_file.write(msg)
            except DataDiscovery.NotExistingDatasetError, ex :
                msg='ERROR ***: failed Data Discovery in DBS : %s \n'%ex.getErrorMessage()
                badlist_file.write(msg)

            # extract the CRAB relevant information from DBS 
            try:
               ## get list of all required data in the form of dbs paths  (dbs path = /dataset/datatier/owner)
               DBSPaths=pubdata.getDBSPaths()
            except: 
                msg='ERROR ***: Owner=%s Dataset=%s : No required data in the form of dbs paths extracted \n'%(owner,dataset)
                badlist_file.write(msg)
            try:
                ## get max number of events
                nevents=pubdata.getMaxEvents()
            except:
                msg='ERROR ***: Owner=%s Dataset=%s : No number of event extracted \n'%(owner,dataset)
                badlist_file.write(msg)
            try:
                ## get fileblocks : this is the input for DLS                
                fb=pubdata.getFileBlocks()
            except:
                msg='ERROR ***: Owner=%s Dataset=%s : No fileblock extracted \n'%(owner,dataset)
                badlist_file.write(msg)


        badlist_file.close()
        checklist_file.close()

        sys.exit(0)

