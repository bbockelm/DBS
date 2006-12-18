#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
CLI DBS Data discovery toolkit. DLS site generator.
"""

# import system modules
import string, os, sys, stat

# DLS modules
import dlsApi
import dlsClient

# DBS modules
import DBSInst, DBSUtil

#
# main
#
if __name__ == "__main__":
    iface="cgi"
    # get grid proxy
    cmd="cat $HOME/.globus/pp.txt | grid-proxy-init -pwstdin -q"
    os.system(cmd)
    file = open('dls.all.tmp','w')
    for dbsInst in DBSInst.DBS_DLS_INST.keys():
        iface=""
        try:
            dlsType,endpoint = DBSInst.DBS_DLS_INST[dbsInst]
            if dlsType=='DLS_TYPE_DLI':
               dlsType='DLS_TYPE_LFC'
            iface = dlsClient.getDlsApi(dlsType,endpoint)
            #print dbsInst,dlsType,endpoint,iface    
            locList = iface.getAllLocations(session = True)
            for loc in locList:
                file.write('%s %s\n'%(dbsInst,loc.host)) 
            #DBSUtil.printListElements(lList,dbsInst)
        except:
            DBSUtil.printExcept()
            raise "Fail to generate location list for dbs instance",dbsInst
    file.close()
    os.rename('dls.all.tmp','dls.all')

