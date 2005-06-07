import sys, os, commands, pdb
from publishAgent import *

def removeTagsWSP(line) : 
    """
    This function removes HTML tags and extra whitespace from 
    assignment files downloaded from the RefDB.
    """
    outp = ""
    outtag = 1
    for char in line : 
        if char == '<' : 
            outtag = 0
        if char == '>' :
            outtag = 1
        if outtag == 1 and char != '>' : 
            outp = outp + char
    return outp.strip() 

class assignmentParameters : 
    """
    This class loads assignment id information from files downloaded 
    from the RefDB.
    """
    def __init__(self, assignmentNumber, file) : 
        # Save assignment id
        self.assignmentNumber = assignmentNumber
        # Open the file and read it in.
        self.contents = open(file, 'r').readlines()
        self.contents = map(lambda x : removeTagsWSP(x), self.contents)
        # The contents are in X=Y format, read it into a dictionary
        self.pDict = {}
        for line in self.contents : 

            key = line.split('=')[0].strip()
            if len(line.split('=')) > 1 :
                val = line.split('=')[1]
            else : 
                val = 'null'

            # truncate to 80 char for now to stop database errors
            if len(val) > 80 : 
                val = val[:80]
            if len(key) > 80 : 
                key = key[:80]
            
            if key == "ProdStepType" :
                # Set the input/output collection types based 
                # on ProdStepType
                self.foundOutputCT = val                
                if self.foundOutputCT == "CMKIN" : 
                    self.foundInputCT = "NoInput"
                elif self.foundOutputCT == "CMSIM" : 
                    self.foundInputCT = "CMKIN"
                elif self.foundOutputCT == "Digi" : 
                    self.foundInputCT = "Hit"
                elif self.foundOutputCT == "OSCAR" : 
                    self.foundInputCT = "CMKIN"
                    self.foundOutputCT = "Hit"
                elif self.foundOutputCT == "Hit" : 
                    self.foundInputCT = "CMSIM"         
            elif key == "ApplicationName" :
                # Set the application name
                self.foundApp = val
            elif key == "ExecutableName" :
                # Set the executable name
                self.foundExe = val
            elif key == "ApplicationVersion" :
                # Set the version
                self.foundVer = val        
            else : 
                # Set an ordinary key/value pair
                self.pDict[key] = val
        self.pDict['AssignmentID'] = self.assignmentNumber
        # Make up some basic values for these other things needed by 
        # application configuration.
        self.paramSetName = "Parameters for assignment " + \
                                    self.assignmentNumber 

        self.paramSetVersion = "1.0"
        self.paramSetComments = "Loaded from RefDB by Elmo"

###Init###

# This is the directory where we expect to find the "clean" and "assignmentinfo"
# directories for the examples.
refDBbaby = "../../refDBbaby"
# Load the contents of the "clean" file which has the run data
filenum = sys.argv[1]
file = refDBbaby + '/clean/clean'+filenum+'.txt'
contents = open(file).readlines()
# Get an instance of DBSPublisher.
DBS = DBSpublisher() 
# Get the dataset name and ID
dsetLine = contents[4]
datasetID = dsetLine.split()[0]
datasetName = dsetLine.split()[1]
# Get the keys of the RefDB runs 
datasetKeys = contents[8].split()
# The following are bookkeeping deviced used during this example insert
doneACs = {}
donePRIs = {}
donePROs = {}

###Loop over Runs###

# Loop over the runs in the "clean" file while begin at line 9
# This is in table format with keys at the top (line 8) and 
# values in each row underneath.
for line in contents[9:] : 
    # The following dict will contain run data per line.
    datasetInfo = {}
    datasetValues = line.split()
    # For each line, put values into dictionary using keys 
    # from line 8.
    for i in range(len(datasetKeys)) : 
        datasetInfo[datasetKeys[i]] = datasetValues[i]
        # Have to mmdecode the POOLWXYZ values
        if datasetKeys[i][0:4] == "POOL" : 
            decodecmd = 'echo "' + datasetValues[i] + '" | mmencode -u | gzip -d'
            output = commands.getoutput(decodecmd).split('\n')
            # Really we are interested only in the lfn names...
            lfnList  = []
            for subline in output : 
                if subline.find("<lfn") >= 0 : 
                    lfn = subline.split('"')[1]
                    lfnList.append(lfn)
            # Reset the POOLWXYZ element to the list of lfns
            datasetInfo[datasetKeys[i]] = lfnList

    # Initialize subdomains within this line
    ecPublish = {}    # for event collections
    priPublish = {}   # for primary dataset
    proPublish = {}   # for processed dataset
    acPublish = {}    # for application configuration

    # Every line contains several event collections actually
    # These are identified by assignment id.  We loop over columns
    # and when we hit a column named "AssWXYZ" we start a new 
    # event collection. 
    for item in datasetKeys : 
        if item[0:3] == "Ass" : 
            # Add a new event collection by assignment ID
            ecPublish[datasetInfo[item]] = {}            
            # Add a new application configuration by assignemnt ID too   
            acPublish[datasetInfo[item]] = None

    # Now we loop over it again to get the order of AssignmentIDs in the line.
    # We assume these come in processing order.   
    orderASID = []
    currentASID = None
    for item in datasetKeys : 
        # If we are moving into a new "Assignment ID domain", update the 
        # current AssignmentID pointer.
        if item[0:3] == "Ass" : 
            currentASID = datasetInfo[item]
            orderASID.append(currentASID)
            # load the actual application config parameters from the 
            # assignment info 
            acPublish[currentASID] = assignmentParameters(currentASID, \
                   refDBbaby+"/assignmentinfo/info"+currentASID+".txt")
            # But special rules apply for legacy CMSIM parameters...
            # These are mixed in with the CMKIN parameters, which are flagged
            # by starting out with "Assignment" rather than just "Ass" and a number.
            if item == 'AssignmentID' : 
                # Append the CMSIM parameters
                orderASID.append(datasetInfo['SimAssignmentID'])
                ecPublish[datasetInfo['SimAssignmentID']] = {}        
                acPublish[datasetInfo['SimAssignmentID']] = None
                # But in the OSCAR world, the SimAssignmentID is present but always 0.
                if datasetInfo['SimAssignmentID'] != '0' : 
                    # If this was zero, then the assignment really didn't exist
                    acPublish[datasetInfo['SimAssignmentID']] = \
                       assignmentParameters(datasetInfo['SimAssignmentID'], \
                       "/Users/gregorygraham/druid/RefDBMine/assignmentinfo/info"+\
                         datasetInfo['SimAssignmentID']+".txt")

        # Add parameters to the correct ecPublish dictionary.
        # Unless "Sim" is in the name, then assume it goes with CMSIM event collection            
        if currentASID != None : 
            if item.find('Sim') >= 0 : 
                ecPublish[datasetInfo['SimAssignmentID']][item] = datasetInfo[item]         
            else : 
                ecPublish[currentASID][item] = datasetInfo[item]         

    # Loop over event collections for import
    ppath = '/'
    ppid = None
    for asid in orderASID : 
        # Get the application configuration 
        ac = acPublish[asid]
        # Make sure it is not None (could happen if SimAssignmentID = 0)
        if ac != None : 
          # Check for CMKIN assignment
          if ac.foundOutputCT == 'CMKIN' : 
            # Use CMKIN assignment to define the primary dataset
            MCChannel = ac.pDict['DatasetName']
            MCProduction = ac.pDict['DatasetName']
            MCDecayChain = 'null'
            Group = ac.pDict['Group']
            mcDescription = {'MCChannel':MCChannel, \
                  'MCProduction':MCProduction, \
                  'MCDecayChain':MCDecayChain}
            # Load the primary dataset or, if already loaded, just get the ID
            if not MCChannel in donePRIs.keys() : 
                priID = DBS.publishPrimaryDataset(mcDescription, MCChannel, MCChannel + ' for group ' + Group, \
                        Group + "-stream", Group, "Stream for group " + Group )
                priID = int(priID)
                print "Defining Primary Dataset for dataset "+ MCChannel
                donePRIs[MCChannel] = priID
            else : 
                priID = donePRIs[MCChannel]
            # Set a fake owner name for the prcessed dataset
            OwnerName = MCChannel + '-CMKIN' 
            # yes this is considered a primary event collection
            primeEC = 'y'   
          else :  
            # Get the real owner name for the processed dataset
            OwnerName = ac.pDict['OutputOwnerName']
            # If not CMKIN, then it is not a primary EC in this example.
            primeEC = 'n'   
          DatasetName = ac.pDict['DatasetName']

          # load the application configuration or, if done already, just get the id
          if not ac.paramSetName in doneACs.keys() : 
              acID = DBS.publishApplicationConfiguration(ac.foundApp, ac.foundExe, ac.foundVer, \
                 ac.paramSetName, ac.paramSetVersion, ac.paramSetComments, \
                 ac.pDict, ac.foundInputCT, ac.foundOutputCT)
              acID = int(acID)
              print "Defining Application Configuration for AssignmentID "+asid
              doneACs[ac.paramSetName] = acID
          else : 
              acID = doneACs[ac.paramSetName]

          # append the app config id to the processing path
          ppath = ppath + '%d'%acID

          # load the processed dataset or, if done already, just get the id
          if not DatasetName+OwnerName in donePROs.keys() : 
              proID = DBS.publishProcessedDataset(DatasetName, OwnerName, ppid, acID, ppath)
              print "Defining Processed Dataset for Dataset " + DatasetName + \
                                 " and owner " + OwnerName
              proID = int(proID)
              donePROs[DatasetName+OwnerName] = proID
          else : 
              proID = donePROs[DatasetName+OwnerName]

          # Initialize the event collection
          eDict = {}
          ecLumi = "0.0"
          ecName = "NoName"
          ecIndex = 0
          ecNevts = 0
          ecStatus = "NoStatus"
          ecLFNs = []
 
          # loop over items in the event collection
          for item in ecPublish[asid].keys() : 

              if item=="GenRunStatusID" or \
                 item[0:5] == "ValSt" or \
                 item == "SimrunStatusID" : 
                  # Set the status variable.
                  ecStatus = ecPublish[asid][item]
              elif item == "NbGenSelectedEvts" or \
                   item[0:5] == "EvNum" : 
                  # Get the number of events 
                  ecNevts = int(ecPublish[asid][item])
              elif item == "CrossSection" : 
                  # What is relation between lumi and cross section???
                  ecLumi = ecPublish[asid][item]
              elif item.find("RunNum") >=0 : 
                  # Get the run number, use as EC index
                  ecName = "ECforRunNumber"+ecPublish[asid][item]
                  ecIndex = int(ecPublish[asid][item])
              elif item == "Ntupl" : 
                  # For CMKIN, use ntuple name for sole LFN
                  ecLFNs.append((ecPublish[asid][item], ecPublish[asid]['NtupleCheckSum'], \
                    ecPublish[asid]['NtupleSizeMB'], 'OK', 'Ntuple'))
              elif item[0:4] == "POOL" : 
                  # For not(CMKIN) use the lfns found from mmdecodign the POOL string
                  for lf in ecPublish[asid][item] : 
                      # don't know checksums or file sizes in this case
                      ecLFNs.append((lf, '0', '0', 'OK', 'Ntuple'))
              else : 
                  # Set the other parameters in a custom parameter set
                  eDict[item] = ecPublish[asid][item]
                  if len(eDict[item]) > 255 : 
                      eDict[item] = eDict[item][:255]
              # dummy values for what I couldn't find in RefDB
              ecValStatus = "NotValidated"
              ecParamName = "Parameters for "+ecName

          # load the event collection
          ecID = DBS.publishEventCollection(ecStatus, ecValStatus, ecNevts, \
              ecLumi, ecName, proID, ecIndex, primeEC, ecParamName, eDict, \
              ecLFNs)                          
          print "Loading event collection " + DatasetName + ', ' + \
                OwnerName + ', ' + ecName
         
          # remember the app config ID as parent in the next round
          ppid = acID
 
