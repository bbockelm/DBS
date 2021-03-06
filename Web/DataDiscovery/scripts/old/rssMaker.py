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
import string, os, sys, stat, shutil

# DBS modules
import DDHelper, DBSInst, DDUtil
from   DDOptions    import *
from   DDConfig     import *
from   DDExceptions import *

#
# main
#
if __name__ == "__main__":
    optManager  = DDOptionParser('DDHelper')
    (opts,args) = optManager.getOpt()
    ddConfig    = DDConfig()
    iface       = ddConfig.iface()
    if opts.iface:
       iface=opts.iface
    helper   = DDHelper.DDHelper(iface=iface)
    if opts.quiet:
       helper.setQuiet()
    if opts.dbsInst:
       if not DBSInst.DBS_DLS_INST.has_key(opts.dbsInst):
          print "Wrong DBS instances '%s'"%opts.dbsInst
          DDUtil.printListElements(DBSInst.DBS_DLS_INST.keys(),"Known DBS instances")
          sys.exit(1)
    for dbsInst in DBSInst.DBS_DLS_INST.keys():
        if opts.dbsInst:
           if dbsInst!=opts.dbsInst:
              continue
        try:
            if os.path.isdir('rss/%s'%dbsInst):
               shutil.rmtree(os.path.join(os.getcwd(),'rss/%s'%dbsInst))
            helper.rssMaker(dbsInst)
        except:
            printExcept()
            raise "Fail to generate rss for dbs instance",dbsInst
