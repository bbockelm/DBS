#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853.
#
# Author:  Valentin Kuznetsov, 2006
#

# system modules
import os, sys, string, stat, re, types

# DBS specific modules
from dbsException    import DbsException
from dbsApiException import *

class DbsConfig:
  def __init__(self,iConfig={}):
    """
       Read and parse content of $HOME/.esdb.conf configuration file
       The following syntax is supported in configuration file:
       # ESDB configuration file
       login:password
       # ESDB Master
       Comments are started with '#' letter. User can specify login and password
       to provide access to underlying DB, if SQLite is used they're ignored. 
    """
    uFileName=""
    if os.environ.has_key('DBSCONFIG'):
       if not os.path.isfile(os.environ['DBSCONFIG']):
          raise DbsException(args="The '%s' config file does not exists"%os.environ['DBSCONFIG'])
       uFileName=os.environ['DBSCONFIG']
    else:
       uFileName = os.path.normpath(os.environ["HOME"]+"/.dbs.conf")
    self.configFile=uFileName
    if not os.path.isfile(uFileName):
       raise DbsException(args="The '%s' config file does not exists"%uFileName)
    mode = os.stat(uFileName)[stat.ST_MODE]
    if mode!=33152:
       # mode is not -rw-------
       print "WARNING: permission of %s is set to 0600 mode (-rw-------)"%uFileName
       os.chmod(uFileName,0600)
    login = masterHost =  masterName = masterPort = masterSocket = admin = ""
    iList=['user','password','driver','url','host','port','servlet','version']
    self.configDict={}
    for read in open(uFileName).readlines():
        line = string.split(read,"\n")[0]
        if not len(line): continue
        if line[0]=="#": continue
        for item in iList:
            keyword=string.upper(item)
            if re.search(keyword,line):
               self.configDict[item] = string.split(line,"%s="%keyword)[1]
            if iConfig.has_key(item) and iConfig[item]:
               self.configDict[item] = iConfig[item]
  def host(self):
    if not self.configDict.has_key('host'):
       raise DbsException(args="DBS configuration missing host number")
    return self.configDict['host']
  def port(self):
    if not self.configDict.has_key('port'):
       raise DbsException(args="DBS configuration missing port number")
    return self.configDict['port']
  def servlet(self):
    if not self.configDict.has_key('servlet'):
       raise DbsException(args="DBS configuration missing servlet number")
    return self.configDict['servlet']
  def version(self):
    if not self.configDict.has_key('version'):
       raise DbsException(args="DBS configuration missing version number")
    return self.configDict['version']

#
# main
#
if __name__ == "__main__":
   dbsConfig = DbsConfig()
   print "Config file",dbsConfig.configFile
   print dbsConfig.configDict

