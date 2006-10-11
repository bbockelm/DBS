#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
DBS Data discovery option module. It defines optinos used by both
DBSDataDiscoveryServer and DBSHelper classes.
To get option list and explanation please run DBSHelper.py --help.
"""

import sys
from optparse import OptionParser

class DBSOptionParser: 
  """
     DBSOptinoParser is main class to parse options for L{DBSHelper} and L{DBSDataDiscoveryServer}.
  """
  def __init__(self):
    self.parser = OptionParser()
    self.parser.add_option("--quiet",action="store_true", default=False, dest="quiet",
         help="be quiet and don't print exceptions")
    self.parser.add_option("--dict",action="store", type="string", dest="dict",
         help="generate dict, pass Global/All")
    self.parser.add_option("--primaryDataset",action="store", type="string", dest="primD",
         help="specify primary dataset, e.g. --primaryDataset=CSA06-081-os-minbias")
    self.parser.add_option("--dataTier",action="store", type="string", dest="DT",
         help="specify Data Tier within dataset, e.g. --dataTier=RECO")
    self.parser.add_option("--app",action="store", type="string", dest="app",
         help="specify application keys (version,family,exe), e.g. --app=CMSSW_0_8_1,Merged,cmsRun")
    self.parser.add_option("--dbsInst",action="store", type="string", dest="dbsInst",
         help="specify DBS instance to use, e.g. --dbsInst=MCLocal_1/Writer")
    self.parser.add_option("--showProcDatasets",action="store_true", default=False, dest="showProcD",
         help="be quiet and show only processed datasets")
    self.parser.add_option("--site",action="store", type="string", dest="site",
         help="specify DLS site you're interesting, e.g. --site=fnal.gov")
    self.parser.add_option("--search",action="store", type="string", dest="search",
         help="specify any keywords to search your data, e.g. --search='CMSSW_0_8_1 and Merged'")
    self.parser.add_option("-v","--verbose",action="store_true", default=False, dest="verbose",
         help="be verbose")
         
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
    optManager  = DBSOptionParser()
    (opts,args) = optManager.getOpt()
    print "options:  ",opts
    print "arguments:",args
    
