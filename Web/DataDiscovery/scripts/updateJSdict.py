#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
CLI DBS Data discovery toolkit. JavaScript navigator menu dictionary generator.
"""

# import system modules
import string, os, sys, stat

# DBS modules
import DBSHelper, DBSInst, DBSUtil
from   DBSOptions import *
from   DDConfig   import *

#
# main
#
if __name__ == "__main__":
    optManager  = DBSOptionParser()
    (opts,args) = optManager.getOpt()
    helper = DBSHelper.DBSHelper()
    if opts.quiet:
       helper.setQuiet()
    if opts.dbsInst:
       if not DBSInst.DBS_DLS_INST.has_key(opts.dbsInst):
          print "Wrong DBS instances '%s'"%opts.dbsInst
          DBSUtil.printListElements(DBSInst.DBS_DLS_INST.keys(),"Known DBS instances")
          sys.exit(1)
    for dbsInst in DBSInst.DBS_DLS_INST.keys():
        if opts.dbsInst:
           if dbsInst!=opts.dbsInst:
              continue
        try:
            fileName = helper.initJSDict(dbsInst)
            if os.path.isfile(fileName) and os.stat(fileName)[stat.ST_SIZE]>0:
               os.rename(fileName,string.replace(fileName,".tmp",""))
            else:
               DBSUtil.printExcept()
               raise "File %s is corrupted"%fileName
        except:
            DBSUtil.printExcept()
            raise "Fail to generate JS dictionary for dbs instance",dbsInst
