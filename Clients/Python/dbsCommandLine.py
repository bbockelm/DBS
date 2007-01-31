#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853.
#
# Author:  Valentin Kuznetsov, 2006
#

# system modules
import os, sys, string, stat, re
from optparse import OptionParser

from dbsApi import DbsApi

# DBS specific modules
from dbsException    import DbsException
from dbsApiException import *

class DbsOptionParser:
  """
     OptionParser is main class to parse options.
  """
  def __init__(self):

      self.parser = OptionParser()
      #self.parser.add_option("-h", "--help",action="store_true", default=False, dest="help",
      #     help="be quiet during deployment procedure")
      self.parser.add_option("-q", "--quiet",action="store_true", default=False, dest="quiet",
           help="be quiet during deployment procedure")

      self.parser.add_option("--url",action="store", type="string", dest="url",
           help="specify URL, e.g. http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet")

      self.parser.add_option("-v","--verbose", action="store", type="int", default=0, dest="verbose",
           help="specify verbose level, e.g. --verbose=1, or higher --verbose=2")

      self.parser.add_option("--ver","--version", action="store", type="string", default="v00_00_05", dest="version",
           help="specify dbs client api version, e.g. --ver=v00_00_05, or --version=v00_00_05")

      self.parser.add_option("-c","--command", action="store", type="string", default="", dest="command",
           help="specify what command would you like to run, e.g. -c=listPrimaryDataset, or --command=listPrimaryDataset")

      self.parser.add_option("-p","--path", action="store", type="string", default="/*/*/*", dest="path",
           help="specify dataset path, e.g. -p=/primary/tier/processed, or --path=/primary/tier/processed")

  def getOpt(self):
      """
          Returns parse list of options
          @type  self: class object
          @param self: none
          @rtypei : none
          @return : list of options.
      """
      return self.parser.parse_args()


#def apiWrapper(DbsApi):


#
# main
#


if __name__ == "__main__":

  try:

    optManager  = DbsOptionParser()
    (opts,args) = optManager.getOpt()

    # Todo
    # Help on individual commands
    # 

    api = DbsApi(opts.__dict__)
    for primary in api.listPrimaryDatasets(''):
     #for primary in api.listPrimaryDatasets('ab;bc'):
     print "  %s" % primary
    
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

  print "Done"

