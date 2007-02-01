#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853.
#
# Author:  Valentin Kuznetsov, 2006
#

# system modules
import os, sys, string, stat, re
#from optparse import OptionParser
import optparse
#from optparse import Option

from dbsApi import DbsApi

# DBS specific modules
from dbsException    import DbsException
from dbsApiException import *

class DbsOptionParser(optparse.OptionParser):
  """
     OptionParser is main class to parse options.
  """

  def __init__(self):
      optparse.OptionParser.__init__(self, usage="%prog --help or %prog --command [options]", version="%prog 1.0", conflict_handler="resolve")

      self.add_option("-q", "--quiet",action="store_true", default=False, dest="quiet",
           help="be quiet during deployment procedure")

      self.add_option("--url",action="store", type="string", dest="url",
           help="specify URL, e.g. http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet")

      self.add_option("-v","--verbose", action="store", type="string", default="WARNING", dest="level",
           help="specify verbose level, e.g. --verbose=1, or higher --verbose=2")

      self.add_option("--apiver","--apiversion", action="store", type="string", default="v00_00_05", dest="version",
           help="specify dbs client api version, e.g. --ver=v00_00_05, or --version=v00_00_05")

      self.add_option("--p","--path", action="store", type="string", default="/*/*/*", dest="path",
           help="specify dataset path, e.g. -p=/primary/tier/processed, or --path=/primary/tier/processed")

      self.add_option("--pattern", action="store", type="string", default="*", dest="pattern",
           help="some command scope could be restricted with pattern, e.g. listPrimaryDataset")

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
      #command_help += "\npython dbsCommandLine.py --help"
      command_help += "\npython dbsCommandLine.py -c lsp --pattern TestPrimary*"
      command_help += "\npython dbsCommandLine.py -c listPrimaryDatasets --pattern TestPrimary*"
      command_help += "\npython dbsCommandLine.py -c listPrimaryDatasets"
      #command_help += "\npython dbsCommandLine.py -c listProcessedDatasets"
      #command_help += "\npython dbsCommandLine.py -c lsd --p /QCD_pt_0_15_PU_OnSel/SIM/CMSSW_1_2_0-FEVT-1168294751-unmerged"
      #command_help += "\npython dbsCommandLine.py -c listFiles --path /PrimaryDS_ANZAR_01/SIM/anzar-procds-01"
      command_help += "\npython dbsCommandLine.py -c lsf --path /PrimaryDS_ANZAR_01/SIM/anzar-procds-01"
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

# API Call Wrapper
def callApi(args):
    #print args
    optdict=args.__dict__
    apiCall = optdict.get('command', '')
    if apiCall in ("", None):
       print "No command specified, use --help for details" 
    if optdict.has_key('help'):
       print "NEED Help!!"

    api = DbsApi(opts.__dict__)

    #Execute the proper API call
    ##listPrimaryDatasets 
    if apiCall in ('listPrimaryDatasets', 'lsp'):
       #if optdict.has_key('pattern'):
       apiret = api.listPrimaryDatasets(optdict.get('pattern'))
       for anObj in apiret:
        print anObj['Name']

    ##listProcessedDatasets
    elif apiCall in ('listProcessedDatasets', 'lsd'):
       pathl = breakPath(optdict.get('path'))   
       #pathl[] is always empty !

       datasetPaths = [] 
       apiret = api.listProcessedDatasets(pathl[1], pathl[2], pathl[3]) 
       for anObj in apiret:
          for aPath in anObj['PathList']:
              if aPath not in datasetPaths:
                 datasetPaths.append(aPath)
                 #Print on screen as well
                 print aPath

       #Print all paths
       #for aPath in datasetPaths:
          #print aPath

    ##listFiles
    elif apiCall in ('listFiles', 'lsf'):
       path=optdict.get('path')
       print "Path: ", path
       if path == '/*/*/*':
         print "Can not list ALL files of ALL datasets, please specify a dataset path"
       else:  
         apiret = api.listFiles(path)  
         for anObj in apiret:
           print anObj['LogicalFileName']
       

    ##listBlocks
    elif apiCall in ('listBlocks', 'lsb'):
       path=optdict.get('path')
       print "Path: ", path
       if path == '/*/*/*':
         print "Can not list ALL Blocks of ALL datasets, please specify a dataset path"
       else:
         apiret = api.listBlocks(path)
         for anObj in apiret:
             print anObj['Name']

    else:
       print "API Call: %s not implemented" %apiCall

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
       callApi(opts)
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

