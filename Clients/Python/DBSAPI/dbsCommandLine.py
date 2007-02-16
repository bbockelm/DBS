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



#############################################################################
##Default URL for the Service
#URL="http://cmssrv18.fnal.gov:8989/DBS/servlet/DBSServlet"

URL="http://cmslcgco01.cern.ch:8900/DBS/servlet/DBSServlet"
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

      self.add_option("--p","--path", action="store", type="string", default="/*/*/*", dest="path",
           help="specify dataset path, e.g. -p=/primary/tier/processed, or --path=/primary/tier/processed")

      self.add_option("--pattern", action="store", type="string", default="*", dest="pattern",
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
      command_help += "\n           listBlocks or lsb, must provide --path"
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
       	#if self.optdict.has_key('pattern'):
       	apiret = self.api.listPrimaryDatasets(self.optdict.get('pattern'))
       	for anObj in apiret:
        	print anObj['Name']

  def handleListProcessedDatasets(self):
	datasetPaths = []
        wholepath= self.optdict.get('path')
	if wholepath == "*":
		apiret = self.api.listProcessedDatasets("*")
	else:
		pathl = breakPath(self.optdict.get('path'))
		#pathl[] is always empty !
		apiret = self.api.listProcessedDatasets(pathl[1], pathl[2], pathl[3])
        if len(apiret) < 1 :
		print "No Datasets found"
	for anObj in apiret:
	    #import pdb
            #pdb.set_trace()

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


  def handleListFiles(self):
       path=self.optdict.get('path')
       print "Path: ", path
       if path == '/*/*/*':
         print "Can not list ALL files of ALL datasets, please specify a dataset path"
       else:
         apiret = self.api.listFiles(path)
         for anObj in apiret:
           print anObj['LogicalFileName']


       print "Total files listed: %s" %len(apiret) 
  def handleBlockCall(self):
       path=self.optdict.get('path')
       print "Path: ", path
       if path == '/*/*/*':
         print "Can not list ALL Blocks of ALL datasets, please specify a dataset path"
       else:
         apiret = self.api.listBlocks(path)
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

