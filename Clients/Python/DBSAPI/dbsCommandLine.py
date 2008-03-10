#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
###################################################
#
#  Developed by M. Anzar Afaq @ FNAL (March 2007)
#
#  Disclaimer:
#        Any modifications to original 
#        code will be considered Users responsibility 
#        and will not be supported)
###################################################
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
from TermUtilities import TerminalController
from TermUtilities import ProgressBar

import StringIO
import signal, os

import pprint 

# Importing a dynamically generated module
def importCode(code,name,add_to_sys_modules=0):
    """
    Import dynamically generated code as a module. code is the
    object containing the code (a string, a file handle or an
    actual compiled code object, same types as accepted by an
    exec statement). The name is the name to give to the module,
    and the final argument says wheter to add it to sys.modules
    or not. If it is added, a subsequent import statement using
    name will return this module. If it is not added to sys.modules
    import will try to load it in the normal fashion.

    import foo

    is equivalent to

    foofile = open("/path/to/foo.py")
    foo = importCode(foofile,"foo",1)

    Returns a newly generated module.
    """
    import sys,imp

    module = imp.new_module(name)

    exec code in module.__dict__
    if add_to_sys_modules:
        sys.modules[name] = module

    return module

class printDot ( threading.Thread ):
       def __init__(self):  
          threading.Thread.__init__(self)
          self.doIt = 1
          #self.write = sys.stdout.write
	  term=TerminalController()
	  self.mypb = ProgressBar(term, "API Progress")

       def run ( self ):
          last = int(str(time.time()).split('.')[0])
	  update=0.0
          while ( self.doIt != 0 ) :
            curr = int(str(time.time()).split('.')[0])
            if curr > last+1 :
               update+=0.01
               self.mypb.update(update, "working")
	       last=int(str(time.time()).split('.')[0])	
            else: 
              continue
       def mark_done(self):
	  self.doIt = 0
          self.mypb.update(1.0, "Done")
	  self.mypb.clear()

class showProgress( threading.Thread ):
	def __init__(self):
		threading.Thread.__init__(self)
		self.doIt = 1
		self.notwril=False

	def run ( self ):
		if self.notwril:
			return
		sys.stdout.write('Processing ... ')
		chars = ('|', '/', '-', '\\')
		while (self.doIt):
		    	for char in chars:	
				sys.stdout.write(char+'\b')
				sys.stdout.flush()
				time.sleep(0.1)
	def stop(self):
		if self.notwril:
			return
		if self.doIt == 1: print ""
		self.doIt = 0
	
#############################################################################
##Default URL for the Service
#
# If NO URL is provided, URL from dbs.config will be used
#
#############################################################################

#saved_help="out.log" 
saved_help= StringIO.StringIO()

# help related funcs
class cmd_doc_writer:

  def __init__(self):
      self.wiki_help=False

  def print_all_doc(self):
      self._help_andslist()
      print "\n"
      self._help_andsdeflist()
      print "\n" 
      self._help_primarylist()
      print "\n"        
      self._help_procdslist() 
      print "\n"        
      self._help_algolist()
      print "\n"        
      self._help_filelist()
      print "\n"        
      self._help_selist()
      print "\n"        
      self._help_blocklist()
      print "\n"       
      self._help_search()  
      print "\n"
      self._help_myadslist()


  def command_help(self):
      pre=""
      if self.wiki_help:
                pre = "---++"
      command_help = pre+"IMAGINE the possibilities:\n"
      command_help = "\nPossible commands are:\n"
      command_help += "\n           listPrimaryDatasets or lsp, can be qualified with --pattern"
      command_help += "\n           listProcessedDatasets lsd, can provide --path"
      command_help += "\n           listAlgorithms or lsa, can provide --path"
      command_help += "\n           listRuns or lsr, can provide --path"
      command_help += "\n           listTiers or lst, can provide --path"
      command_help += "\n           listBlocks or lsb, can provide --path and/or --blockpattern"
      command_help += "\n           listFiles or lsf, must provide --path"
      command_help += "\n           listAnalysisDatasetDefinition or lsadef"
      command_help += "\n           listAnalysisDataset or lsads"
      command_help += "\n           search --searchtype=block,files,datasets"
      if self.wiki_help: command_help += "<verbatim>"
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
      if self.wiki_help: command_help += "</verbatim>"
      return command_help

  def command_short_help(self):
      command_help = "IMAGINE the possibilities:"
      command_help = "\nSome possible commands are:"
      command_help += "\n           listProcessedDatasets or lsd, can provide --path"
      command_help += "\n           listAlgorithms or lsa, can provide --path"
      command_help += "\n           listFiles or lsf, must provide --path"
      command_help += "\n           listAnalysisDatasetDefinition or lsadef"
      command_help += "\n           listAnalysisDataset or lsads"
      command_help += "\n           myAnalysisDataset or myads"
      command_help += "\n           search or --search\n"
      command_help += "\n EXAMPLE: python dbsCommandLine.py -c lsd --path=/*/*/RAW" 
      command_help += "\n Note: most commands can print greater details with --report"
      command_help += "\n Please use --doc for details"
      return command_help

  def _help_myadslist(self):
		pre=""
                if self.wiki_help:
                        print "---+++\b"
                print pre+"Lists Local Analysis Datasets"
                print "   Arguments:"
                print "         -c myads, or --command=myAnalysisDataset or --command=myads"
                print "         optional: --pattern=<Analysis_Dataset_Name_Pattern>"
                print "                   --help, displays this message"
                if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c myads"
                print "         python dbsCommandLine.py -c myads --pattern=MyAnalysis*"
                if self.wiki_help: print "</verbatim>"


  #Analysis Datasets
  def _help_andslist(self):
                pre=""
		if self.wiki_help:
			print "---+++\b"
                print pre+"Lists Analysis Datasets know to this DBS"
                print "   Arguments:"
                print "         -c lsads, or --command=listAnalysisDataset or --command=lsads"
                print "         optional: --pattern=<Analysis_Dataset_Name_Pattern>"
		print "                   --path=<dataset path>"
                print "                   --help, displays this message"
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsads"
                print "         python dbsCommandLine.py -c lsads --pattern=MyAnalysis*"
                print "         python dbsCommandLine.py -c lsads --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-0006210/FEVT"
                print "         python dbsCommandLine.py -c lsads --pattern=MyAnalysisDatasets4* --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-0006210/FEVT"
		if self.wiki_help: print "</verbatim>"

  #Analysis Dataset Definitions
  def _help_andsdeflist(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
                print pre+"Lists Analysis Dataset Definitions know to this DBS"
                print "   Arguments:"
                print "         -c lsadef, or --command=listAnalysisDatasetDefinition or --command=lsadef"
                print "         optional: --pattern=<Analysis_Dataset_Definition_Name_Pattern>"
                print "                   --help, displays this message"
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsadef"
                print "         python dbsCommandLine.py -c lsadef --pattern=WhatILike*"
		if self.wiki_help: print "</verbatim>"
                

  #primary
  def _help_primarylist(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
                print pre+"Lists PrimaryDatasets know to this DBS"
                print "   Arguments:"
                print "         -c lsp, or --command=listPrimaryDataset or --command=lsp"
                print "         optional: --pattern=<Primary_Dataset_Name_Pattern>"
		print "                   --help, displays this message"    
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsp"
                print "         python dbsCommandLine.py -c lsp --pattern=mc*"
		if self.wiki_help: print "</verbatim>"

  def _help_procdslist(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
                print pre+"Lists Processed Datasets (and Paths) known to this DBS"
                print "   Arguments:"
                print "         -c lsd, or --command=listProcessedDatasets or --command=lsd"
                print "         optional: "
                print "                     --algopattern=<Algorithm_Pattern>  "
                print "                     (in /ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash form )"
                print "                     supports glob patterns"
                print "                     --path=<dataset path>"
                print "                     --report, if provided a report for each dataset is generated"
		print "                     --help, displays this message"    
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsd"
                print "         python dbsCommandLine.py -c lsd --report"
                print "         python dbsCommandLine.py -c lsd --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-0006210/*"
                print "         python dbsCommandLine.py -c lsd --path=/TAC-TIBTOB-120-DAQ-EDM/*/*"
                print "         python dbsCommandLine.py -c lsd --path=/TAC-TIBTOB-120-DAQ-EDM/*/*  --algopattern=/*/CMSSW_1_2_0/*/*"
		if self.wiki_help: print "</verbatim>"

  def _help_algolist(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
                print pre+"List Algorithms known to this DBS"
                print "   Arguments:"
                print "         -c lsa, or --command=listAlgorithms or --command=lsa"
                print "         --algopattern=</ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash>"
                print "                       supports glob search"
		print "         --help, displays this message"    
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsa"
                print "         python dbsCommandLine.py -c lsa --algopattern=/*/CMSSW_1_2_0/*/*"
                print "         python dbsCommandLine.py -c lsa --algopattern=/*/CMSSW_1_2_0          (equally good)"
		if self.wiki_help: print "</verbatim>"

  def _help_filelist(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
                print pre+"List Files known to this DBS"
                print "   Arguments:"
                print "         -c lsf, or --command=listFiles or --command=lsf"
                print "         optional: "
                print "                     --lfnpattern=<LogicalFileName_Pattern>  "
                print "                                          supports glob patterns"
                print "                     --path=<dataset path>"
                print "                     --blockpattern=<Block_Name_Pattern> in the form /prim/proc/dt(n)#<GUID>, DOES NOT support glob patterns"
                print "                     --report, if provided a report for each file is generated"
		print "                     --help, displays this message"    
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsf --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219/RAW"
                print "         python dbsCommandLine.py -c lsf --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219/RAW --report"
                print "         python dbsCommandLine.py -c lsf --lfnpattern=*root* --report (don't do that please !)"
                print "         python dbsCommandLine.py -c lsf --blockpattern=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219#1134f4e5-addd-4a45-8d28-fd491d0e6154 --report"
                print "         python dbsCommandLine.py -c lsf --blockpattern=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219#1134f4e5-addd-4a45-8d28-fd491d0e6154"
                print "   Note: --path takes precedence over --blockpattern and --lfnpattern (if --path provided rest are ignored)"
		if self.wiki_help: print "</verbatim>"

  def _help_selist(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
                print pre+"List Storage Elements known to this DBS"
                print "   Arguments:"
                print "         -c lsse, or --command=listStorageElements or --command=lsse"
                print "   optional: --sepattern=<Storage element name pattern> for glob search"
		print "             --help, displays this message"    
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsse"
                print "         python dbsCommandLine.py -c lsse --sepattern=*it         (All italian Storage Elements)"
                print "         python dbsCommandLine.py -c lsse --sepattern=*fnal*         (All FNAL Storage Elements)"
		if self.wiki_help: print "</verbatim>"

  def _help_blocklist(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
                print pre+"List File Blocks known to this DBS"
                print "   Arguments:"
                print "         -c lsb, or --command=listBlocks or --command=lsb"
                print "         optional: "
                print "                     --path=<dataset path>"
                print "                     --blockpattern=<Block_Name_Pattern> in the form /prim/proc/dt(n)#<GUID>, supports glob patterns"
                print "                     --sepattern=<Storage element name pattern> for glob search"
                print "                     --report, if provided a report for each file block is generated"
		print "                     --help, displays this message"    
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c lsb --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219/RAW"
                print "         python dbsCommandLine.py -c lsb --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219/RAW --report"
                print "         python dbsCommandLine.py -c lsb --sepattern=*fnal*"
                print "         python dbsCommandLine.py -c lsb --blockpattern=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219#1134f4e5-addd-4a45-8d28-fd491d0e6154 --report"
                print "         python dbsCommandLine.py -c lsb --blockpattern=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219#1134f4e5-addd-4a45-8d28-fd491d0e6154"
                print "   Note: --path takes precedece over --blockpattern and --lfnpattern (if --path provided rest are ignored)"
		if self.wiki_help: print "</verbatim>"
  def _help_deletePDS(self):
                pre=""
                if self.wiki_help:
                        print "---+++"
		print pre+"Delete a Processed Dataset from DBS instance"
		print "   Arguments:"
		print "         -c --deletePDS or --command=deletePDS"
		print "         required: "
		print "                     --path=<dataset path>, DOES NOT supports glob patterns"
		print "         optional: "
		print "                    --help, displays this message"
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c --deletePDS --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219/RAW"
		print ""
		print ""
		print "            This command WILL NOT work on DBS Global"
		if self.wiki_help: print "</verbatim>"

  def _help_createads(self):
                pre=""
                if self.wiki_help:
                        pre="---+++"
                print pre+"Creates Analysis Dataset (Local on Disk), based on the search criteria"
		print "   This command cannot be used alone you have to use it with dbs search"
		print "   First you run search, after you have found what you were looking for "
		print "   Add --createads=<ADSNAME> to the commandline and it will create an ADS for you"
		print "   Arguments:"
		print "         --createads=<ADSNAME>"
		print "                    Where ADSNAME is a Valid Analysis Dataset Name"
                if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c search --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0/RAW --createads=ANZARTESTADS"
		if self.wiki_help: print "</verbatim>"
		
  def _help_search(self):
                pre=""
                if self.wiki_help:
                        pre="---+++"
                print pre+"Search the Data known to this DBS, based on the search criteria"
                print "   Arguments:"
                print "         -c --search, or --command=--search or --command=search"
                print "         optional: "
                print "                     --path=<dataset path>, supports glob patterns"
                print "                     --blockpattern=<Block_Name_Pattern> in the form /prim/proc/dt(n)#<GUID>, supports glob patterns"
                print "                     --algopattern=</ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash>, supports glob patterns"
                print "                     --sepattern=<Storage element name pattern> for glob search"
                print "                     --report, if provided a report is generated"
		print "                     --help, displays this message"    
		if self.wiki_help: print "<verbatim>"
                print "   examples:"
                print "         python dbsCommandLine.py -c search --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219/RAW"
                print "         python dbsCommandLine.py -c search --path=/TAC-TIBTOB-120-DAQ-EDM/*/RAW --report"
                print "         python dbsCommandLine.py -c search --sepattern=*fnal*      (come on you really want to list all data for these SEs ?)"
                print "         python dbsCommandLine.py -c search --blockpattern=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219#1134f4e5-addd-4a45-8d28-fd491d0e6154 --report"
                print "         python dbsCommandLine.py -c lsb --blockpattern=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219#1134f4e5-addd-4a45-8d28-fd491d0e6154"
		if self.wiki_help: print "</verbatim>"


###############################################################################
# Following are independent functions to re-direct and capture 
# help from optparse.OptionParser, and not part of cms_doc_writer class
#
#capture help from optparse in atemp file
def redirected_print_help(self):
	print self.print_usage()
	print print_help(self)
      
# This function just dumps the generic help text on screen
def print_help(self):
	print saved_help.getvalue()
	term=TerminalController()
	helper = cmd_doc_writer()
        print helper.command_short_help()
        #print term.BLUE+helper.command_short_help()+term.NORMAL

       #print open(saved_help, 'r').read()

###############################################################################

class DbsOptionParser(optparse.OptionParser):
  """
     OptionParser is main class to parse options.
  """

  def __init__(self):
      optparse.OptionParser.__init__(self, usage="%prog --help or %prog --command [options]", version="%prog 1.0.9", conflict_handler="resolve")

      self.add_option("--url",action="store", type="string", dest="url", default="BADURL",
           help="specify URL, e.g. --url=http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet, If no url is provided default url from dbs.config is attempted")

      self.add_option("-v","--verbose", action="store", type="string", default="ERROR", dest="level",
           help="specify verbose level, e.g. --verbose=DBSDEBUG, The possible values are, CRITICAL, ERROR, DBSWARNING, DBSDEBUG, DBSINFO, where DBSINFO is most verbose, and ERROR is default")

      self.add_option("--p","--path", action="store", type="string", dest="path",
           help="specify dataset path, e.g. -p=/primary/tier/processed, or --path=/primary/tier/processed, supports shell glob")

      self.add_option("--pattern", action="store", type="string", dest="pattern", default="*",
           help="some commands scope could be restricted with pattern, e.g. listPrimaryDataset, supports shell glob for Primary Dataset Names, "+ \
						"Use --doc or individual commands with --help")

      self.add_option("--run", action="store", type="string", dest="run",
           help="Run number, can be used by list files or search commands")

      self.add_option("--algopattern", action="store", type="string", dest="algopattern",
           help="Algorithms can be specified as algopattern /appname/appversion/appfamily/pset_hash, supports shell glob")

      self.add_option("--blockpattern", action="store", type="string", dest="blockpattern",
           help="BlockName pattern for listBlocks and other calls in format /primary/tier/processed#guid, supports shell glob")

      self.add_option("--sepattern", action="store", type="string", dest="sepattern",
           help="Storage Element Name pattern for glob search")

      self.add_option("--lfnpattern", action="store", type="string", dest="lfnpattern",
           help="Logical File Name pattern for glob search")

      self.add_option("--createads", action="store", type="string", dest="createads",
           help="Create Analysis Dataset for the search query (must be used with --search)")

      self.add_option("--dbsmartfile", action="store", type="string", dest="dbsmartfile",
           help="Location of the dbs mart file, absolute path or relative to $ADSHOME")

      self.add_option("--report", action="store_true", default=False, dest="report",
           help="If you add this option with some listCommands the output is generated in a detailed report format")

      self.add_option("--doc", action="store_true", default=False, dest="doc",
           help="Generates a detailed documentation for reference, overrides all other cmdline options (use --wiki_help to produces help document in wiki format [dbs --doc --wiki_help])")

      self.add_option("--notwril", action="store_true", default=False, dest="notwril",
           help="If provided, tool will not show 'Progressing...' Twril on screen (can be useful when redirecting output to files)")

      ## Always keep this as the last option in the list
      self.add_option("-c","--command", action="store", type="string", default="notspecified", dest="command",
                         help="Command line command, e.g. -c lsp, or --command=listPrimaryDataset, "+ \
				"Also you can use --help with individual commands, e.g, -c lsp --help ")

      ## capture help
      self.capture_help()
      ## redirect print_help
      optparse.OptionParser.print_help=redirected_print_help

  def capture_help(self):

      saveout = sys.stdout
      sys.stdout = saved_help
      self.print_help()
      sys.stdout = saveout
      #saved_help.close()

  def doc(self):
      print_help(self) 
      helper = cmd_doc_writer()
      if '--wiki_help' in sys.argv:
        helper.wiki_help=True
      print helper.command_help()
      print "\n" 	
      print "More Details on individual calls:" 	
      helper.print_all_doc()
      sys.exit(0)

  def parse_args(self):
      """
      Intercepting the parser and removing help from menu, if exist
      This is to avoid STUPID handling of help by nice optparse (optp-arse!)
      """

      if '--doc' in sys.argv:
        self.doc()

      help=False
      if '--help' in sys.argv: 
		sys.argv.remove('--help')
		help=True
      if '-h' in sys.argv: 
		sys.argv.remove('-h')
		help=True
      if '-help' in sys.argv: 
		sys.argv.remove('-help')
		help=True
      if '--h' in sys.argv : 
		sys.argv.remove('--h')
		help=True
      if '-?' in sys.argv: 
		sys.argv.remove('-?')
		help=True
      if '--?' in sys.argv : 
		sys.argv.remove('--?') 
		help=True

      if help==True:
                if len(sys.argv) < 3:
			print_help(self)
			sys.exit(0)
      		self.add_option("--want_help", action="store", type="string", dest="want_help", default="yes",
           		help="another way to ask for help")
                 
      return optparse.OptionParser.parse_args(self)	

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


  def printRED(self, msg):
    print self.term.RED+msg+self.term.NORMAL

  def printGREEN(self, msg):
    print self.term.GREEN+msg+self.term.NORMAL

  def printBLUE(self, msg):
    print self.term.BLUE+msg+self.term.NORMAL

  def makeTIME(self, intime):
    return time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime(long(intime)))

  def __init__(self, args):
   try:
    self.helper = cmd_doc_writer()
    self.term = TerminalController()
    self.progress=showProgress()
    self.optdict=args.__dict__
    apiCall = self.optdict.get('command', '')

    #See if Twril needs to be ignored
    if self.optdict.has_key('notwril'):
	self.progress.notwril=self.optdict['notwril']

    # If NO URL is provided, URL from dbs.config will be used
    if opts.__dict__['url'] == "BADURL":
        del(opts.__dict__['url']) 

    self.api = DbsApi(opts.__dict__)
    self.printGREEN( "Using DBS instance at: %s" %self.optdict.get('url', self.api.url()))
    if apiCall in ('', 'notspecified') and self.optdict.has_key('want_help'):
	print_help(self)
	return
    elif apiCall in ("--version", "version", "-ver", "--ver"):
	print "DBS API Version: %s" %self.api.getApiVersion()
	return 
    elif apiCall in ("", None):
       self.printRED ("No command specified, use --help for details")
       return

    #Execute the proper API call
    ##listPrimaryDatasets 
    elif apiCall in ('listPrimaryDatasets', 'lsp'):
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

    elif apiCall in ('listAnalysisDatasetDefinition', 'lsadef'):
	self.handlelistANDSDefCall()

    elif apiCall in ('listAnalysisDataset', 'lsads'):
        self.handlelistANDCall()

    elif apiCall in ('myAnalysisDataset', 'myads'):
	self.handlelistMyADSCall()

    elif apiCall in ('deletePDS', '--deletePDS'):
	self.handledeleteProcDSCall()

    ##Search data
    elif apiCall in ('search', '--search') or self.optdict.get('search'):
	self.handleSearchCall()

    elif apiCall in ('createads', '--createads', '--createads=', 'createads=') or self.optdict.get('createads'):
        self.handleCreateADSCall()

    else:
       self.printRED( "Unsupported API Call '%s', please use --doc or --help" %str(apiCall))

   except DbsApiException, ex:
      self.progress.stop()
      self.printRED("Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() ))
      if ex.getErrorCode() not in (None, ""):
          self.printRED("DBS Exception Error Code: %s "% str(ex.getErrorCode()))

   except DbsException, ex:
      self.progress.stop()
      self.printRED( "Caught DBS Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() ))
      if ex.getErrorCode() not in (None, ""):
          self.printRED( "DBS Exception Error Code: %s " % str(ex.getErrorCode()))

   except Exception, ex:
        self.progress.stop()
        self.printRED ("Unknow Exception in user code:")
        traceback.print_exc(file=sys.stdout)

   except KeyboardInterrupt:
        self.progress.stop()
        print "Interrupted."
        sys.exit(1)

  def handledeleteProcDSCall(self):
        if self.optdict.has_key('want_help'):
                self.helper._help_deletePDS()
                return
	path=self.optdict['path']
	if path in ('', None):
		self.printRED("You must provide --path= with this call")
		return	
	if path not in ('', '*', '/*/*/*') and path.find('*')==-1:
		self.printRED( "deleting proc dataset %s and moving it into recycle bin." %str(path))
		print self.api.deleteProcDS(path)
	else:
		self.printRED("Please specifiy dataset in correct PATH format, --path=/TAC-TIBTOB-120-DAQ-EDM/CMSSW_1_2_0-RAW-Run-00006219/RAW")


  def handlelistANDSDefCall(self):
        if self.optdict.has_key('want_help'):
                self.helper._help_andsdeflist()
                return
        if self.optdict.get('pattern'):
	  self.progress.start()
          apiret = self.api.listAnalysisDatasetDefinition(self.optdict.get('pattern'))
          self.progress.stop()
        else:
          self.progress.start()
          apiret = self.api.listAnalysisDatasetDefinition("*")
          self.progress.stop()
        for anObj in apiret:
		#print anObj
                self.reportAnDSDef(anObj)
        return

  def reportAnDSDef(self, anObj):
	print "\n  Analysis Dataset Definition: %s" %anObj['Name']
	print "      Dataset Path: %s" %str(anObj['ProcessedDatasetPath'])
	print "         CreationDate: %s" % self.makeTIME(anObj['CreationDate'])
	print "         CreatedBy: %s" %anObj['CreatedBy']
	print "         Runs Included: %s" % str(anObj['RunsList'])
	print "         RunRanges Included: %s" % str(anObj['RunRangeList'])
	print "         Lumi Sections Included: %s" %str(anObj['LumiList'])
	print "         LumiRanges Included: %s" %str(anObj['LumiRangeList'])
	#print "         Tiers Included: %s" %str(anObj['TierList'])
	print "         Algorithms Included:"
	for anAlgo in anObj['AlgoList']:
		if anAlgo in ('', None):
			continue
		algo = anAlgo.split(';')	
                print "                /"+ algo[2] \
                                + "/" + algo[0]  \
                                        +"/"+ algo[1] \
                                                + "/" + algo[3]
	return	

  def handlelistMyADSCall(self):
	if self.optdict.has_key('want_help'):
                self.helper._help_myadslist()
                return
	print "Listing ADS from DBS MART"

        adshome = os.path.expandvars(self.api.adshome())
        if not os.path.exists(adshome):
                self.printRED("ERROR: Path do not exist, ADSHOME (%s) parameter is not set or not a valid path" %str(self.api.adshome()))
	if not os.path.isdir(adshome):
		self.printRED("ERROR: Path do not exist, ADSHOME (%s) parameter is not set or not a valid path" %str(self.api.adshome()))

	import pdb
	#pdb.set_trace()

	dirList=os.listdir(adshome)
	for mart_file in dirList:
		if mart_file.endswith(".dm"):
    			print "Processing: %s " %mart_file
			martFile=open(os.path.join(adshome, mart_file), "r")
                	mart = importCode(martFile, "mart", 0)
                	KnownADS = mart.KnownADS
			pp = pprint.PrettyPrinter(indent=1)
			pp.pprint(KnownADS)
			#print KnownADS
                	#if adsname in KnownADS.keys():
			#--pattern
			print "Yet to be implemented to search for a particular ADS, easy and coming!"

	return


	         
  def handlelistANDCall(self):
        if self.optdict.has_key('want_help'):
                self.helper._help_andslist()
                return
        print self.optdict.get('path') 
        self.progress.start()
	apiret = self.api.listAnalysisDataset(self.optdict.get('pattern'), self.optdict.get('path'))
        self.progress.stop()
        for anObj in apiret:
                #print anObj
                self.reportAnDS(anObj)
        return

  def reportAnDS(self, anObj):
        print "\n\nAnalysis Dataset: %s" %anObj['Name']
        print "         CreationDate: %s" % self.makeTIME(anObj['CreationDate'])
        print "         CreatedBy: %s" %anObj['CreatedBy']
        #Lets print the definition too.
        self.reportAnDSDef(anObj['Definition'])
 
  def handleListPrimaryDatasets(self):
	if self.optdict.has_key('want_help'):
		self.helper._help_primarylist()
		return
       	if self.optdict.get('pattern'):
	  self.progress.start()
          apiret = self.api.listPrimaryDatasets(self.optdict.get('pattern'))
	  self.progress.stop()
        else:
          self.progress.start()
          apiret = self.api.listPrimaryDatasets("*")
          self.progress.stop()
       	for anObj in apiret:
        	print anObj['Name']
		#print "CreationDate: %s" %self.makeTIME(anObj['CreationDate'])
                #print "LastModificationDate: %s" % self.makeTIME(anObj['LastModificationDate'])

        return

  def handleListProcessedDatasets(self):
        if self.optdict.has_key('want_help'):
		self.helper._help_procdslist()
                return

	datasetPaths = []

        if self.optdict.get('pattern') != '*':
          print "--pattern has no effect on listProcessedDataset, --path can be used for dataset patterns"
        
        paramDict = {}

        # See if Algorithm is specified for selection
        algoparam = self.getAlgoPattern()

        # See if any path is provided
        pathl = self.getPath(self.optdict.get('path'))
        if len(pathl):
		paramDict.update(pathl)
        if len(algoparam):
                paramDict.update(algoparam)

        #dot = printDot()
        #dot.start()

        print "Listing datasets, please wait..."
	self.progress.start()
        apiret = self.api.listProcessedDatasets(**paramDict)
	self.progress.stop()

        #dot.mark_done()

        if len(apiret) < 1 :
		print "No Datasets found"
	for anObj in apiret:

	    if self.optdict.get('report'):	
		self.reportProcessedDatasets(anObj)
            else:  
		  path_list = anObj['PathList']
		  if len(path_list) == 0: 
			pass
		  	#print anObj['Name'] +" No Blocks Found ?? "
		  else:	
                  	for aPath in anObj['PathList']:
                      		if aPath not in datasetPaths:
                         		datasetPaths.append(aPath)
                         		#Print on screen as well
                         		print aPath
        return

  def reportProcessedDatasets(self, anObj):
		sumry  = "\n\n\nProcessed Dataset %s " %anObj['Name']
		sumry += "\nCreationDate: %s" % self.makeTIME(anObj['CreationDate'])
		#sumry += "\nLastModificationDate: %s" % self.makeTIME(anObj['LastModificationDate'])
		
        	report = Report()
		report.addSummary(sumry)

		report.addLine("Paths in this Processed Dataset:")
		for aPath in anObj['PathList']:
			report['lines'].append("        "+aPath)
		#Print it
        	printReport(report)
                return

  def getPath(self, inpath=None):

	pathDict = {} 
	if inpath not in (None, ''):
   		if not inpath.startswith('/'):
			raise DbsException (args="Path must start with a '/'",  code="1201")
	
	else:
		if self.optdict.get('path'):
			inpath = self.optdict.get('path')

	if inpath not in (None, ''):	
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
        if self.optdict.has_key('want_help'):
		self.helper._help_algolist()
                return

        self.printBLUE( "Retrieving list of Algorithm, Please wait..." )
        algoparam = self.getAlgoPattern()
        if len(algoparam):
	     self.progress.start()
             apiret = self.api.listAlgorithms(**algoparam)
             self.progress.stop()
        else:
	  self.progress.start()
          apiret = self.api.listAlgorithms()
          self.progress.stop()

        self.printGREEN ("\nListed as:     /ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash\n" )
        for anObj in apiret:
		print anObj
		print "CreationDate: %s" % self.makeTIME(anObj['CreationDate'])
		print "LastModificationDate: %s" % self.makeTIME(anObj['CreationDate'])
                print "       /"+ anObj['ExecutableName'] \
				+ "/" + anObj['ApplicationVersion']  \
					+"/"+ anObj['ApplicationFamily'] \
						+ "/" + anObj['ParameterSetID']['Hash']
        if (len(apiret) > 10): self.printGREEN( "\nListed as:      /ExecutableName/ApplicationVersion/ApplicationFamily/PSet-Hash\n\n" )
        return


  def handleListFiles(self):
       if self.optdict.has_key('want_help'):
		self.helper._help_filelist()
                return
       path=self.optdict.get('path') or ''
       blockpattern=self.optdict.get('blockpattern') or ''
       lfnpattern=self.optdict.get('lfnpattern') or ''
       run=self.optdict.get('run') or ''

       if path == '' and blockpattern == '' and lfnpattern=='' :
         self.printRED( "Can not list ALL files of ALL datasets, please specify a dataset path using --path= and/or --blockpattern= and/or --lfnpattern")
       else:
         self.printBLUE( "Making api call, this may take sometime depending upon size of dataset, please wait....\n")
         self.progress.start()
	 apiret = self.api.listFiles(path=path, blockName=blockpattern, patternLFN=lfnpattern, runNumber=run)
         self.progress.stop()
         if self.optdict.get('report') :
		for anObj in apiret:
			self.reportFile(anObj)
	 else:
                for anObj in apiret:
                	print "          %s" %anObj['LogicalFileName']
         self.printBLUE( "Total files listed: %s" %len(apiret))
         return

  def reportFile(self, anObj):
              report = Report()
              report.addSummary("                 LogicalFileName: %s" %anObj['LogicalFileName'])
              report.addLine("                    File Details:")
              report.addLine("                         Status : %s"  %anObj['Status'])
              report.addLine("                         Size : %s"  %anObj['FileSize'])
              report.addLine("                         NumberOfEvents : %s"  %anObj['NumberOfEvents'])
              report.addLine("                         Checksum : %s"  %anObj['Checksum'])
              report.addLine("                         FileType : %s"  %anObj['FileType'])
              report.addLine("                         Block : %s"  %anObj['Block']['Name'])
              report.addLine("\n")
              printReport(report)
              return

  def handlelistSECall(self):
       if self.optdict.has_key('want_help'):
		self.helper._help_selist()
                return

       sepattern=self.optdict.get('sepattern') or '*'
       self.progress.start()
       apiret = self.api.listStorageElements(sepattern)
       self.progress.stop()
       self.printBLUE( "Listing storage elements, please wait..." )
       for anObj in apiret:
           print anObj['Name']
       return

  def handleBlockCall(self):
       if self.optdict.has_key('want_help'):
		self.helper._help_blocklist()
                return

       path=self.optdict.get('path') or ''
       blockpattern=self.optdict.get('blockpattern') or ''
       sepattern=self.optdict.get('sepattern') or ''

       if path in ['/*/*/*', ''] and blockpattern in ['*', ''] and sepattern in ['*', '']:
         self.printRED( "Can not list ALL Blocks of ALL datasets, specify a dataset path (--path=) and/or a block name (--blockpattern=) and/or storage element (--sepattern)")
	 return
       else:
         self.printBLUE( "Listing block, please wait..." )
         self.progress.start()
         apiret = self.api.listBlocks(dataset=path, block_name=blockpattern, storage_element_name=sepattern)
         self.progress.stop()
         if self.optdict.get('report') :
            for anObj in apiret:
		self.reportBlock(anObj)
         else :
                for anObj in apiret:
                    print anObj['Name']
       return

  def reportBlock(self, anObj):
                sumry  = "\n     Block Name %s " %anObj['Name']
                sumry += "\n     Block Path %s" %anObj['Path']
		sumry += "\n     CreationDate: %s" % self.makeTIME(anObj['CreationDate'])
                report = Report()
                report.addSummary(sumry)
                report.addLine("     Block Details:")
                report.addLine("           BlockSize: %s" %anObj['BlockSize'])
                report.addLine("           NumberOfFiles: %s" %anObj['NumberOfFiles'])
                report.addLine("           OpenForWriting: %s" % anObj['OpenForWriting'])
                report.addLine("           This block available at:")
                for aSE in anObj['StorageElementList']:
                        report['lines'].append("                           %s" %aSE['Name'])
                #Print it
                report.addLine("\n")
                printReport(report)
                return

  def handleCreateADSCall(self):
	self.helper._help_createads()
	return


  def handleSearchCall(self):

       if self.optdict.has_key('want_help'):
		self.helper._help_search()
                return

       adsfileslist=[]
       createads=self.optdict.get('createads') or ''

       query=""

       pathpattern = self.optdict.get('path') or ''
       blockpattern = self.optdict.get('blockpattern') or ''
       sepattern = self.optdict.get('sepattern') or ''
       algopattern = self.optdict.get('algopattern') or ''

       pathl = self.getPath(pathpattern)
       if pathl['patternPrim'] in ['*', '', '?'] and  pathl['patternProc'] in ['*', '', '?'] and pathl['patternDT'] in ['*', '', '?']:
		pathpattern='/*/*/*'
		
       if pathpattern in ['/*/*/*', ''] and blockpattern in ['*', ''] \
		and sepattern in ['*', ''] and algopattern in ['/*/*/*/*', '']:
		self.printRED("You must provide some options with --search, Please look at 'dbs --search --help', or use 'dbs --doc'. \nCannot list EVERYTHING in DBS\n\n")
		self.helper._help_search()
		return

       if pathpattern in ['/*/*/*', ''] and blockpattern not in ['*', '']:
		pathl = self.getPath(blockpattern[:blockpattern.find("#")])
       #### Lets locate all matching PATH and then for each Path List (Blocks, Runs, Algos etc) and then for each Block 
       # See if any path is provided
       paramDict={}
       if len(pathl):
                paramDict.update(pathl)

       algoparam=self.getAlgoPattern() 
       if len(algoparam):
                paramDict.update(algoparam)
       self.printBLUE( "listing data, please wait...\n")

       query = str(paramDict)

       procret = self.api.listProcessedDatasets(**paramDict)

       if len(procret) < 1 :
                self.printRED( "No Datasets found..?")
                return  
       #avoid duplication, wonder thats must not be possible anyways.
       datasetPaths=[]
       for anObj in procret:
		print "---------------------------------------------------------------------------------------------------------"
		print "Dataset PrimaryDataset=%s, ProcessedDataset=%s" %(anObj['Name'], anObj['PrimaryDataset']['Name'])
		print "---------------------------------------------------------------------------------------------------------"
		#List the Algorithms for this Dataset
		print "Algorithms for this Processed Dataset: "
        	for anAlgo in anObj['AlgoList']:
                	print "/"+ anAlgo['ExecutableName'] \
                                + "/" + anAlgo['ApplicationVersion']  \
                                        +"/"+ anAlgo['ApplicationFamily'] \
                                                + "/" + anAlgo['ParameterSetID']['Hash']
		for aPath in anObj['PathList']:
                      if aPath not in datasetPaths:
                         datasetPaths.append(aPath)
                         print "Dataset Path: %s " %aPath
                         self.printGREEN("Listing the Blocks next..")
                         blockret = self.api.listBlocks(dataset=aPath, block_name=blockpattern, storage_element_name=sepattern)
			 for aBlk in blockret:
         			if self.optdict.get('report') :
                			self.reportBlock(aBlk)
         			else :
                    			print "        Block:  %s" %aBlk['Name']
				#Lets list files for this Block
				filesret = self.api.listFiles(blockName=aBlk['Name'])
				print "              Files: "
                                for aFile in filesret:
					if self.optdict.get('report') :
						self.reportFile(aFile)
					else: 
						print "                    %s" %aFile['LogicalFileName']

				if createads not in ('', None):
					adsfileslist.extend(filesret)

       if createads not in ('', None):
		if len(datasetPaths) > 1:
			self.printRED("Cannot create ADS, more than one matching Paths found for query, limit to ONE Path")
			return
#print "files to be added in ADS:"
       		#print adsfileslist	
		self.createADS(query, createads, aPath, adsfileslist)

       return


  def createADS(self, query, adsname, path, files):
	"""
	Created the ADS in DBS Local ADS File format
		More info will be passed and added to this method at later stage
	"""

	# The fun begins
	#Lets see if user has provided a MART File as destination

	adshome = os.path.expandvars(self.api.adshome())
	if not os.path.exists(adshome):
		self.printRED("ERROR: Path do not exist, ADSHOME (%s) parameter is not set or not a valid path" %str(self.api.adshome()))

	mart_file_name = self.optdict.get('dbsmartfile') or ''
	if mart_file_name not in ('', None):
		if not os.path.exists(mart_file_name):
			mart_file = os.path.join(adshome, mart_file_name)
		else : mart_file = mart_file_name
	#Else use the DEFAULT Mart file
	else :
		mart_file = os.path.join(adshome, "ImportedADS.dm")
	
	KnownADS = {}
        newADS={}
	newADS['QUERY']=query
	newADS['HOSTURL']=self.api.url()
	newADS['PATH']=path
	
	# LOAD the mart file if it already exits, otherwise create a new one
	if os.path.exists(mart_file):
		martFile=open(mart_file, "r")
		mart = importCode(martFile, "mart", 0)
		KnownADS = mart.KnownADS
		if adsname in KnownADS.keys():
			self.printRED("ERROR: Cannot create %s, An analysis Dataset with same name already exists in the mart %s" 
											%( str(adsname), mart_file) )
			return 
		else:
			
			KnownADS[adsname]=newADS
			print KnownADS
	else :
		#Create a Python Object and then it can be easily appended
		KnownADS = {}
		KnownADS[adsname]=newADS
		"""
		KnownADS[adsname]['QUERY']="This is some query"
		KnownADS[adsname]['HOSTURL']=self.api.url()
		KnownADS[adsname]['PATH']=path
		"""

	mart = open(str(mart_file), "w")
	mart.write("#File Last Update %s" % str(time.time()))
	mart.write("\nKnownADS = ")

	pp = pprint.PrettyPrinter(indent=1)
        pp.pprint(KnownADS)
	safestr=pp.pformat(KnownADS)

	#mart.write(str(KnownADS))
	mart.write(safestr)
	mart.close()
		
	adspath = os.path.join(adshome, adsname)

	if os.path.exists(adspath):
		# That must be checked in the MART file as well
		self.printRED("ERROR: Cannot create %s, An analysis Dataset with same name already exists" %str(adspath))
		return

	ads_file=open(adspath, 'w')
	ads_file.write("<?xml version='1.0' standalone='yes'?>")
	ads_file.write("\n<!-- DBS Version 1 -->")
	ads_file.write("\n<dbs>")
	ads_file.write("\n<src url='%s' />" % self.api.url())
	ads_file.write("\n<dataset path='%s' />" % path)
	
	for aFile in files:
		ads_file.write("\n<file lfn='%s' />" % aFile['LogicalFileName'])
	ads_file.write("\n</dbs>")
        ads_file.close()
	print "Created ADS: %s" % adspath

	"""
	ads_file.write("########################")
	# ADS name is same as file name
        #ads_file.write("\n[NAME]\n")
        #ads_file.write(ads)
        ads_file.write("\n[HOSTDBS]\n")
        ads_file.write(self.api.url())
        ads_file.write("\n[PATH]\n")
        ads_file.write(path)
        ads_file.write("\n[FILES]")
        for aFile in files:
                ads_file.write("\n%s" %aFile['LogicalFileName'])
	ads_file.close()
	print "Created ADS: %s" % adspath
	"""

# main
#
if __name__ == "__main__":
  
  opts = {}
  args = []
  try:
    optManager  = DbsOptionParser()
    (opts,args) = optManager.getOpt()
    #if opts.__dict__.get('doc'):
    #	print optManager.doc()
    #	sys.exit(0)
    #else:
    ApiDispatcher(opts)

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
