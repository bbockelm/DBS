#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#

# system modules
import os, sys, string, stat, re, time
import traceback
#from optparse import OptionParser
import optparse
#from optparse import Option

from dbsApi import DbsApi

# DBS specific modules
from dbsException    import DbsException
from dbsApiException import *

import threading
import sys

import pdb


class printDot ( threading.Thread ):
       def __init__(self):  
          threading.Thread.__init__(self)
          self.doIt = 1
          self.write = sys.stdout.write

       def run ( self ):
          last = int(str(time.time()).split('.')[0])
          while ( self.doIt != 0 ) :
            curr = int(str(time.time()).split('.')[0])
            if curr > last+5 :
               self.write("-")
            else: 
              continue
            last =  int(str(time.time()).split('.')[0])


#############################################################################
##Default URL for the Service
URL="http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet"
#URL="http://cmslcgco01.cern.ch:8900/DBS/servlet/DBSServlet"
##Version of the Cleint API
VERSION="v00_00_06"
#############################################################################

saved_help="/tmp/out.log"

def print_help(self):
       print open(saved_help, 'r').read()
       print "Help is in my control now............!!!!!!!!!"

class DbsOptionParser(optparse.OptionParser):
  """
     OptionParser is main class to parse options.
  """

  def __init__(self):
      optparse.OptionParser.__init__(self, usage="%prog --help or %prog --command [options]", version="%prog 1.0", conflict_handler="resolve")

      self.add_option("-q", "--quiet",action="store_true", default=False, dest="quiet",
           help="be quiet during deployment procedure")

      self.add_option("--url",action="store", type="string", dest="url", default=URL,
           help="specify URL, e.g. http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet")

      self.add_option("-v","--verbose", action="store", type="string", default="WARNING", dest="level",
           help="specify verbose level, e.g. --verbose=1, or higher --verbose=2")

      self.add_option("--apiver","--apiversion", action="store", type="string", default=VERSION, dest="version",
           help="specify dbs client api version, e.g. --ver=v00_00_06, or --version=v00_00_06")

      self.add_option("--p","--path", action="store", type="string", dest="path",
           help="specify dataset path, e.g. -p=/primary/tier/processed, or --path=/primary/tier/processed, supports shell glob")

      self.add_option("--pattern", action="store", type="string", dest="pattern",
           help="some commands scope could be restricted with pattern, e.g. listPrimaryDataset, supports shell glob for Primary Dataset Names")

      self.add_option("--algopattern", action="store", type="string", dest="algopattern",
           help="Algorithms can be specified as algopattern /appname/appversion/appfamily/pset_hash, supports shell glob")

      self.add_option("--blockpattern", action="store", type="string", dest="blockpattern",
           help="BlockName pattern for listBlocks and other calls in format /primary/tier/processed#guid, supports shell glob")

      self.add_option("--sepattern", action="store", type="string", dest="sepattern",
           help="Storage Element Name pattern for glob serach")

      self.add_option("--lfnpattern", action="store", type="string", dest="lfnpattern",
           help="Logical File Name pattern for glob serach")

      self.add_option("--report", action="store_true", default=False, dest="report",
           help="If you add this option with some listCommands the output is generated in a detailed report format")

      self.add_option("--doc", action="store_true", default=False, dest="doc",
           help="Generates a detailed documentation for reference")

      ## Always keep this as the last option in the list
      self.add_option("-c","--command", action="store", type="string", default="notspecified", dest="command",
                           help="specify what command would you like to run, e.g. -c=listPrimaryDataset, or --command=listPrimaryDataset")

      ## capture help
      self.capture_help()
      ## redirect print_help
      optparse.OptionParser.print_help=print_help 

  def capture_help(self):
      saveout = sys.stdout
      savehere = open(saved_help, 'w')
      sys.stdout = savehere
      self.print_help()
      sys.stdout = saveout
      savehere.close()

  def use(self):

      command_help = "\nPossible commands are:\n"
      command_help += "\n           listPrimaryDatasets or lsp, can be qualified with --pattern"
      command_help += "\n           listProcessedDatasets lsd, can provide --path"
      command_help += "\n           listAlgorithms or lsa, can provide --path"
      command_help += "\n           listRuns or lsr, can provide --path"
      command_help += "\n           listTiers or lst, can provide --path"
      command_help += "\n           listBlocks or lsb, can provide --path and/or --blockpattern"
      command_help += "\n           listFiles or lsf, must provide --path"
      command_help += "\n           listFileParents or lsfp, must be qualified with --pattern"
      command_help += "\n           listFileAlgorithms or lsfa,"
      command_help += "\n           listFileTiers or lsft,"
      command_help += "\n           listFileBranches or lsfb,"
      command_help += "\n           listFileLumis or lsfl,"
      command_help += "\n           listStorageElements or lsse,"
      command_help += "\n           listAnalysisDatasetDefinition or lsad,"
      command_help += "\n           listAnalysisDataset or lsa,"
      command_help += "\n           listDatasetContents or lsdc,"
      command_help += "\n\nSome examples:\n"
      command_help += "\npython dbsCommandLine.py --help"
      command_help += "\npython dbsCommandLine.py -c lsp --pattern=TestPrimary*"
      command_help += "\npython dbsCommandLine.py -c listPrimaryDatasets --pattern=TestPrimary*"
      command_help += "\npython dbsCommandLine.py -c listPrimaryDatasets --pattern=*"
      command_help += "\npython dbsCommandLine.py -c listPrimaryDatasets"
      command_help += "\npython dbsCommandLine.py -c listProcessedDatasets"
      command_help += "\npython dbsCommandLine.py -c lsd --p=/QCD_pt_0_15_PU_OnSel/SIM/CMSSW_1_2_0-FEVT-1168294751-unmerged"
      command_help += "\npython dbsCommandLine.py -c listFiles --path=/PrimaryDS_ANZAR_01/SIM/anzar-procds-01"
      command_help += "\npython dbsCommandLine.py -c lsf --path=/PrimaryDS_ANZAR_01/SIM/anzar-procds-01"
      command_help += "\n\nUse --doc for detailed documentation"
      command_help += "\n"
      return command_help

  def print_help(self):
       help=1
       optparse.OptionParser.print_help(self)
       #print self.use()
   
  def getOpt(self):
      """
          Returns parse list of options
          @type  self: class object
          @param self: none
          @rtypei : none
          @return : list of options.
      """
      return self.parse_args()
  

class printReport:

  def __init__(self, report):
	"""
    	report: is a Python data structure of this format
    	report = {
        	'summary': object (dict type), all key/vals will be printed as HEADER
        	'Comments': TEXT, any text appearing here will be printed after HEADER
        	'lines' : [lineObjs], each line Object's key/value pair will be printed in each line
        	}
    	"""
	print report['summary']
	for aline in report['lines']:
		print aline
	
class Report(dict):
        def __init__(self):
                dict.__init__(self)
                self['summary']=""
                self['lines']=[]
        def addSummary(self, summary):
                self['summary'] += summary
        def addLine(self, line):
                self['lines'].append(line)

# API Call Wrapper
class ApiDispatcher:
  def __init__(self, args):
   try:
    #print args
    self.optdict=args.__dict__
    apiCall = self.optdict.get('command', '')
    if apiCall in ("", None):
       print "No command specified, use --help for details" 
    if self.optdict.has_key('help'):
       print "NEED Help!!"
    self.api = DbsApi(opts.__dict__)
    #Execute the proper API call
    ##listPrimaryDatasets 
    if apiCall in ('listPrimaryDatasets', 'lsp'):
	self.handleListPrimaryDatasets()

    ##listProcessedDatasets
    elif apiCall in ('listProcessedDatasets', 'lsd'):
	self.handleListProcessedDatasets()

    ##listAlgorithms
    elif apiCall in ('listAlgorithms', 'lsa'):
	self.handleListAlgorithms()

    ##listFiles
    elif apiCall in ('listFiles', 'lsf'):
	self.handleListFiles() 

    ##listBlocks
    elif apiCall in ('listBlocks', 'lsb'):
	self.handleBlockCall()

    ##listStorageElements
    elif apiCall in ('listStorageElements', 'lsse'):
        self.handlelistSECall()

    else:
       print "API Call: %s not implemented" %apiCall

   except DbsApiException, ex:
      print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
      if ex.getErrorCode() not in (None, ""):
          print "DBS Exception Error Code: ", ex.getErrorCode()

   except DbsException, ex:
      print "Caught DBS Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
      if ex.getErrorCode() not in (None, ""):
          print "DBS Exception Error Code: ", ex.getErrorCode()

   except Exception, ex:
        print "Unknow Exception in user code:"
        traceback.print_exc(file=sys.stdout)

  def handleListPrimaryDatasets(self):
       	if self.optdict.get('pattern'):
          apiret = self.api.listPrimaryDatasets(self.optdict.get('pattern'))
        else:
          apiret = self.api.listPrimaryDatasets("*")
       	for anObj in apiret:
        	print anObj['Name']

  def handleListProcessedDatasets(self):

	datasetPaths = []
        if self.optdict.get('pattern'):
          raise DbsException (args="--pattern has no effect on listProcessedDataset, --path can be used for dataset patterns", code="1202") 
        
        paramDict = {}

        # See if Algorithm is specified for selection
        algoparam = self.getAlgoPattern()

        # See if any path is provided
        pathl = self.getPath(self.optdict.get('path'))
        if len(pathl):
		paramDict.update(pathl)
        if len(algoparam):
                paramDict.update(algoparam)

        print "listing datasets, please wiat...\n"
        apiret = self.api.listProcessedDatasets(**paramDict)

        if len(apiret) < 1 :
		print "No Datasets found"
	for anObj in apiret:

	    if self.optdict.get('report'):	
		sumry  = "\n\n\nProcessed Dataset %s " %anObj['Name']
		sumry += "\nCreationDate: %s" % str(time.ctime(long(anObj['CreationDate'])/1000))
		
        	report = Report()
		report.addSummary(sumry)

		report.addLine("Paths in this Processed Dataset:")
		for aPath in anObj['PathList']:
			report['lines'].append("        "+aPath)
		#Print it
        	printReport(report)
	    else:
	          for aPath in anObj['PathList']:
        	      if aPath not in datasetPaths:
                	 #datasetPaths.append(aPath)
	                 #Print on screen as well
        	         print aPath

  def getPath(self, inpath):

    pathDict = {} 
    if self.optdict.get('path'): 
	inpath = self.optdict.get('path')
   	if not inpath.startswith('/'):
      		raise DbsException (args="Path must start with a '/'",  code="1201")
   	# remove the starting '/' 
   	inpath=inpath[1:]
   	if inpath.endswith('/'):
      		inpath=inpath[:-1]
   	pathl = inpath.split('/')
   	if len(pathl) < 3:
		raise DbsException (args="must provide a full qualifying --path=/?/?/?, or no --path", code="1203")
   	else:
      		pathDict['patternPrim'] = pathl[0]
      		pathDict['patternDT'] = pathl[2]
      		pathDict['patternProc'] = pathl[1]
    else :
         pathDict['patternPrim'] = "*"
         pathDict['patternDT'] = "*"
         pathDict['patternProc'] = "*"

    return pathDict

  def getAlgoPattern(self):

        algodict = {} 
        if self.optdict.get('algopattern'):
          algopat = self.optdict.get('algopattern')
          if not algopat.startswith('/'):
             raise DbsException (args="Algorithm patters starts with a '/'",  code="1200")
          algopat=algopat[1:]
          if algopat.endswith('/'):
                algopat=algopat[:-1]
          algotoks = algopat.split('/')

          if len(algotoks) >=1 : algodict['patternExe']=algotoks[0]
          if len(algotoks) >=2 : algodict['patternVer']=algotoks[1]
          if len(algotoks) >=3 : algodict['patternFam']=algotoks[2]
          if len(algotoks) >=4 : algodict['patternPS']=algotoks[3]

        return algodict
          
  def handleListAlgorithms(self):
        print "Retrieving list of Algorithm, Please wait..." 
        algoparam = self.getAlgoPattern()
        if len(algoparam):
             apiret = self.api.listAlgorithms(**algoparam)
        else:
          apiret = self.api.listAlgorithms()

        print "\nListed as:     /ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash\n"  
        for anObj in apiret:
                print "       /"+ anObj['ExecutableName'] \
				+ "/" + anObj['ApplicationVersion']  \
					+"/"+ anObj['ApplicationFamily'] \
						+ "/" + anObj['ParameterSetID']['Hash']
        if (len(apiret) > 10): print "\nListed as:      /ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash\n\n"  


  def handleListFiles(self):
       path=self.optdict.get('path') or ''
       blockpattern=self.optdict.get('blockpattern') or ''
       lfnpattern=self.optdict.get('lfnpattern') or ''

       if path == '' and blockpattern == '' and lfnpattern=='' :
         print "Can not list ALL files of ALL datasets, please specify a dataset path using --path= and/or --blockpattern= and/or --lfnpattern"
       else:
         #dot = printDot()
         #dot.start()
         print "making api call, this may take sometime depending upon size of dataset, please wait...."

         #def listFiles(self, path="", primary="", proc="", tier_list=[], analysisDataset="",blockName="", patternLFN="*", details=None)

         apiret = self.api.listFiles(path=path, blockName=blockpattern, patternLFN=lfnpattern)
         #dot.doIt = 0
         #dot.stop()
         
	 if self.optdict.get('report') :
            for anObj in apiret:
              report = Report()
              report.addSummary("LogicalFileName: %s" %anObj['LogicalFileName'])
              report.addLine("File Details:")
              report.addLine("    Status : %s"  %anObj['Status'])
              report.addLine("    NumberOfEvents : %s"  %anObj['NumberOfEvents'])
              report.addLine("    Checksum : %s"  %anObj['Checksum'])
              report.addLine("    FileType : %s"  %anObj['FileType'])
              report.addLine("    Block : %s"  %anObj['Block']['Name'])
              report.addLine("\n")
              printReport(report)
            print "Total files listed: %s" %len(apiret)
	 else:
              for anObj in apiret:
           	print anObj['LogicalFileName']
              print "Total files listed: %s" %len(apiret)

  def handlelistSECall(self):
       sepattern=self.optdict.get('sepattern') or '*'
       apiret = self.api.listStorageElements(sepattern)
       print "Listing storage elements, please wait..." 
       for anObj in apiret:
           print anObj['Name']

  def handleBlockCall(self):
       path=self.optdict.get('path') or ''
       blockpattern=self.optdict.get('blockpattern') or ''
       sepattern=self.optdict.get('sepattern') or ''

       if path in ['/*/*/*', ''] and blockpattern in ['*', ''] and sepattern in ['*', '']:
         print "Can not list ALL Blocks of ALL datasets, specify a dataset path (--path=) and/or a block name (--blockpattern=) and/or storage element (--sepattern)"
	 return
       else:
         print "Listing block, please wait..." 
         apiret = self.api.listBlocks(dataset=path, block_name=blockpattern, storage_element_name=sepattern)
         if self.optdict.get('report') :
            for anObj in apiret:
                sumry  = "\nBlock Name %s " %anObj['Name']
                sumry += "\nBlock Path %s" %anObj['Path']
                sumry += "\nCreationDate: %s" % str(time.ctime(long(anObj['CreationDate'])/1000))
                report = Report()
                report.addSummary(sumry)
                report.addLine("Block Details:")
                report.addLine("      BlockSize: %s" %anObj['BlockSize'])
                report.addLine("      NumberOfFiles: %s" %anObj['NumberOfFiles'])
                report.addLine("      OpenForWriting: %s" % anObj['OpenForWriting'])
                report.addLine("      Storage Elements in this Block:")
                for aSE in anObj['StorageElementList']:
                        report['lines'].append("                      %s" %aSE['Name'])
                #Print it
                report.addLine("\n")
                printReport(report)
	 else :
                for anObj in apiret:
                    print anObj['Name']

#
# main
#
if __name__ == "__main__":

  opts = {}
  args = []
  try:
    optManager  = DbsOptionParser()
    (opts,args) = optManager.getOpt()

    # Todo
    # Help on individual commands
    #
    #optparse.print_help = optManager.print_help 
    #pdb.set_trace()
    if opts.__dict__.get('command') != 'notspecified' : 
       ApiDispatcher(opts)
    else:
          optManager.print_help()
          #Print doc as well
          if opts.__dict__.get('doc'):
              print optManager.use()

  except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
      print "DBS Exception Error Code: ", ex.getErrorCode()

  except DbsException, ex:
    print "Caught DBS Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
      print "DBS Exception Error Code: ", ex.getErrorCode()

  except Exception, ex:
    if ex.__doc__ != 'Request to exit from the interpreter.' :
       print "Caught Unknown Exception %s "  % ex
