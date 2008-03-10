#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
DBS Data discovery option module. It defines optinos used by both
DDServer and DDHelper classes.
To get option list and explanation please run DDHelper.py --help.
"""

import sys
from optparse import OptionParser

class DDOptionParser: 
  """
     DDOptionParser is main class to parse options for L{DDHelper} and L{DDServer}.
  """
  def __init__(self,service=""):
    self.parser = OptionParser()

    # options which are applied to any services
    self.parser.add_option("--quiet",action="store_true", default=False, dest="quiet",
         help="be quiet and don't print exceptions")
    self.parser.add_option("-v","--verbose",action="store", type="int", default=0, dest="verbose",
         help="specify verbosity level, 0-none, 1-info, 2-debug")
    self.parser.add_option("--profile",action="store_true", default=False, dest="profile",
         help="perform profiling of the code")
    self.parser.add_option("--iface",action="store", type="string", default=False, dest="iface",
         help="specify server name, e.g. cgi or JavaServer")
    self.parser.add_option("--ssl",action="store_true", default=False, dest="ssl",
         help="run data discovery in secure mode, you must define DD_CRT/DD_PEM environments for host certificate and private keys respectively")
    self.parser.add_option("--port",action="store", type="int", default=8003, dest="port",
         help="specify port number to be used by web server, default is 8003.")
    self.parser.add_option("--dbsInst",action="store", type="string", dest="dbsInst",
         help="specify DBS instance to use, e.g. cms_dbs_prod_global")

    # options specific to DDHelper
    if  service=="DDHelper":
        self.parser.add_option("--dict",action="store", type="string", dest="dict",
             help="use to generate JavaScript dictionary, pass Global/All")
        self.parser.add_option("--primaryDataset",action="store", type="string", dest="primD",
             help="specify primary dataset, e.g. --primaryDataset=CSA06-081-os-minbias")
        self.parser.add_option("--dataTier",action="store", type="string", dest="DT",
             help="specify Data Tier within dataset, e.g. --dataTier=RECO")
        self.parser.add_option("--app",action="store", type="string", dest="app",
             help="specify application keys (version,family,exe), e.g. --app=CMSSW_0_8_1,Merged,cmsRun")
        self.parser.add_option("--showProcDatasets",action="store_true", default=False, dest="showProcD",
             help="be quiet and show only processed datasets")
        self.parser.add_option("--site",action="store", type="string", dest="site",
             help="specify DLS site you're interesting, e.g. --site=fnal.gov")
        self.parser.add_option("--search",action="store", type="string", dest="search",
             help="specify any keywords to search your data, e.g. --search='CMSSW_0_8_1 and Merged'")
    if  service=="DDQueryMaker":
        self.parser.add_option("--query",action="store", type="string", dest="query",
             help="specify input query")


    # options specific to DDExplorer
    if service=="DDExplorer":
        self.parser.add_option("--file",action="store", type="string", default=False, dest="input",
             help="specify input file name which contains your request")
         
  def getOpt(self):
    """
        Returns parse list of options
        @type  self: class object
        @param self: none
        @rtypei : none
        @return : list of options.
    """
    return self.parser.parse_args()

#
# main
#
if __name__ == "__main__":
    optManager  = DDOptionParser()
    (opts,args) = optManager.getOpt()
    print "options:  ",opts
    print "arguments:",args
    
