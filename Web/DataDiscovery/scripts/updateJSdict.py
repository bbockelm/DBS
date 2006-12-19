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

#
# main
#
if __name__ == "__main__":
    iface="cgi"
    helper = DBSHelper.DBSHelper()
    for dbsInst in DBSInst.DBS_DLS_INST.keys():
        try:
            fileName = helper.initJSDict(dbsInst)
            print fileName
            if os.path.isfile(fileName) and os.stat(fileName)[stat.ST_SIZE]>0:
               os.rename(fileName,string.replace(fileName,".tmp",""))
            else:
               DBSUtil.printExcept()
               raise "File %s is corrupted"%fileName
        except:
            DBSUtil.printExcept()
            raise "Fail to generate JS dictionary for dbs instance",dbsInst
