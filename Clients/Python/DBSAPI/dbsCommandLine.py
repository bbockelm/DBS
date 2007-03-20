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
           help="specify dbs client api version, e.g. --ver=v00_00_05, or --version=v00_00_05")

      self.add_option("--p","--path", action="store", type="string", dest="path",
           help="specify dataset path, e.g. -p=/primary/tier/processed, or --path=/primary/tier/processed")

      self.add_option("--pattern", action="store", type="string", dest="pattern",
           help="some command scope could be restricted with pattern, e.g. listPrimaryDataset")

      self.add_option("--report", action="store_true", default="False", dest="report",
           help="If you add this option with some listCommands the output is generated in a detailed report format")


      #self.add_option("--apppattern", action="store", type="string", default="*", dest="apppattern",
      #     help="Pattern of Application in /appname/family/version format")

      ## Always keep this as the last option in the list
      self.add_option("-c","--command", action="store", type="string", default="notspecified", dest="command",
                           help="specify what command would you like to run, e.g. -c=listPrimaryDataset, or --command=listPrimaryDataset")

  def use(self):

      command_help = "\nPossible commands are:\n"
      command_help += "\n           listPrimaryDatasets or lsp, can be qualified with --pattern"
      command_help += "\n           listProcessedDatasets lsd, can provide --path"
      command_help += "\n           listAlgorithms or lsa, can provide --path"
      command_help += "\n           listRuns or lsr, can provide --path"
      command_help += "\n           listTiers or lst, can provide --path"
      command_help += "\n           listBlocks or lsb, can provide --path and/or --pattern"
      command_help += "\n           listFiles or lsf, must provide --path"
      command_help += "\n           listFileParents or lsfp, must be qualified with --pattern"
      command_help += "\n           listFileAlgorithms or lsfa,"
      command_help += "\n           listFileTiers or lsft,"
      command_help += "\n           listFileBranches or lsfb,"
      command_help += "\n           listFileLumis or lsfl,"
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
      command_help += "\nNote: Definition of '--pattern=' may vary for each commandline option."
      command_help += "\n"
      return command_help

  def print_help(self):
       help=1
       optparse.OptionParser.print_help(self)
       print self.use()
   
  def getOpt(self):
      """
          Returns parse list of options
          @type  self: class object
          @param self: none
          @rtypei : none
          @return : list of options.
      """
      return self.parse_args()
  

def breakPath(inpath):
   
   if not inpath.startswith('/'):
      raise DbsException (args="Path starts with a '/'",  code="1201")
   if inpath.endswith('/'):
      inpath=inpath[:-1]

   pathl = inpath.split('/')
   if len(pathl) < 3:
      print "Error must provide a full qualifying path"
   else:
      return pathl  

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
        
        if self.optdict.get('path'):
          wholepath= self.optdict.get('path')
        else:
          wholepath= "*"

	if wholepath == "*":
		apiret = self.api.listProcessedDatasets("*")
	else:
		pathl = breakPath(self.optdict.get('path'))
		apiret = self.api.listProcessedDatasets(pathl[1], pathl[3], pathl[2])
        if len(apiret) < 1 :
		print "No Datasets found"
	for anObj in apiret:

	    if self.optdict.get('report') != 'False' :	
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


  def getAlgoPattern(self):

        if self.optdict.get('pattern'):
          algopat = self.optdict.get('pattern')
          if not algopat.startswith('/'):
             raise DbsException (args="Algorithm patters starts with a '/'",  code="1200")
          algopat=algopat[1:]
          if algopat.endswith('/'):
                algopat=algopat[:-1]
          algotoks = algopat.split('/')
          return algotoks
        return []
          
#{'ApplicationVersion': 'CMSSW_1_2_0', 'ExecutableName': 'cmsRun', 'ParameterSetID': {'Content': 'NOT KNOWN', 'Version': 'NOT KNOWN', 'Hash': 'fa8cd7e4d090367ed1459e9397d1464f', 'Name': 'fa8cd7e4d090367ed1459e9397d1464f', 'Type': 'CSA06', 'Annotation': 'NOT KNOWN'}, 'LastModifiedBy': 'NO_DN', 'CreatedBy': 'NO_DN', 'CreationDate': '1174329371237', 'LastModificationDate': '2007-3-19.19.36. 11. 257693000', 'ApplicationFamily': 'DIGI-RECO'}

  def handleListAlgorithms(self):

        print "Retrieving list of Algorithm, Please wait..." 
        algotoks = self.getAlgoPattern()
        if len(algotoks):
          #pdb.set_trace()
          if len(algotoks)==4:
             apiret = self.api.listAlgorithms(patternExe=algotoks[0], patternVer=algotoks[1], patternFam=algotoks[2], patternPS=algotoks[3])
          if len(algotoks)==3:
             apiret = self.api.listAlgorithms(patternExe=algotoks[0], patternVer=algotoks[1], patternFam=algotoks[2], patternPS="*")
	  if len(algotoks)==2:
             apiret = self.api.listAlgorithms(patternExe=algotoks[0], patternVer=algotoks[1], patternFam="*", patternPS="*")
	  if len(algotoks)==1:
             apiret = self.api.listAlgorithms(patternExe=algotoks[0], patternVer="*", patternFam="*", patternPS="*")
          #apiret = self.api.listPrimaryDatasets(self.optdict.get('pattern'))
        else:
          apiret = self.api.listAlgorithms()

        print "\n/ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash\n"  
        for anObj in apiret:
                print "       /"+ anObj['ExecutableName'] \
				+ "/" + anObj['ApplicationVersion']  \
					+"/"+ anObj['ApplicationFamily'] \
						+ "/" + anObj['ParameterSetID']['Hash']
        if (len(apiret) > 10): print "\n/ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash"  


  def handleListFiles(self):
       path=self.optdict.get('path') or '/*/*/*'
       if path == '/*/*/*':
         print "Can not list ALL files of ALL datasets, please specify a dataset path using --path="
       else:
         #dot = printDot()
         #dot.start()
         print "making api call, this may take sometime depending upon size of dataset, please wait...."
         apiret = self.api.listFiles(path)
         #dot.doIt = 0
         #dot.stop()
         
	 if self.optdict.get('report') != 'False' :
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


  def handleBlockCall(self):
       path=self.optdict.get('path') or ''
       pattern=self.optdict.get('pattern') or ''
       if path in ['/*/*/*', 'K'] and pattern in ['*', 'K'] :
         print "Can not list ALL Blocks of ALL datasets, please specify a dataset path (--path=) and/or a block name pattern (--pattern=)"
	 return
       else:
         apiret = self.api.listBlocks(path, pattern)
         if self.optdict.get('report') != 'False' :
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
    if opts.__dict__.get('command') != 'notspecified' : 
       ApiDispatcher(opts)
    else:
       optManager.print_help() 

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
