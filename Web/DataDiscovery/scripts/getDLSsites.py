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
import string, os, sys, time, socket, popen2

# DLS modules
import dlsApi
import dlsClient

# DBS modules
import DBSInst, DBSUtil
from   DDConfig import *

#
# main
#
if __name__ == "__main__":
    # check how many getDLSsites.py processed around, if more then 2 send Email to cms-dbs
    procName='getDLSsites.py'
    res   = popen2.Popen4('ps auxw | grep %s | grep -v grep'%procName)
    result= res.fromchild.read()
    nProc = string.count(result,'%s'%procName)
    if nProc>1:
       try:
           hostname = socket.gethostbyaddr(socket.gethostname())[0]
       except:
           hostname = 'localhost'
           pass
       msg ="At %s found %s %s processes on %s.\n"%(time.asctime(),nProc,procName,hostname)
       msg+="Please take some action, since it indicates that DLS is not responding.\n"
       msg+="The %s is a part of DBS data discovery and runs as cronjob on %s."%(procName,hostname)
       DBSUtil.sendEmail(msg)
       sys.exit(1)
    # get grid proxy
#    cmd="cat $HOME/.globus/pp.txt | grid-proxy-init -pwstdin -q"
    cmd="cat /data/DBSDataDiscovery/COMP/DBS/Web/DataDiscovery/pp.txt | grid-proxy-init -cert /data/vk/cert/usercert.pem -key /data/vk/cert/userkey.pem -pwstdin -q"
    os.system(cmd)
    file = open('dls.all.tmp','w')
    for dbsInst in DBSInst.DBS_DLS_INST.keys():
        iface=""
        try:
            url,dlsType,endpoint = DBSInst.DBS_DLS_INST[dbsInst]
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

