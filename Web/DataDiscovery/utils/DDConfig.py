#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853.
#
# Author:  Valentin Kuznetsov, 2006
#

# system modules
import os, sys, string, re, types
from utils.DDExceptions import *

class DDConfig:
  def __init__(self,iConfig={}):
    """
       Read and parse content of DBSDD.conf configuration file
       Comments are started with '#' letter. User can specify login and password
       to provide access to underlying DB, if SQLite is used they're ignored. 
    """
    uFileName=""
    if os.environ.has_key('DDHOME'):
       if not os.path.isfile(os.path.join(os.environ['DDHOME'],'DBSDD.conf')):
          raise DDException(args="The '%s' config file does not exists"%os.path.join(os.environ['DDHOME'],'DBSDD.conf'))
       uFileName=os.path.join(os.environ['DDHOME'],'DBSDD.conf')
    else:
       raise DDException(args="No DBSDD environment found")
    self.configFile=uFileName
    if not os.path.isfile(uFileName):
       raise DDException(args="The '%s' config file does not exists"%uFileName)
    iList=['engine','user','password','verbose','dbname','url','iface','rs','querylimit','querythreshold','loggerdir','masthead','mastfooter','admin_url','admin_ver','dbs_url','dbs_ver','ns','global_dd','dbsprimary','memcache','cachelimit']
    self.configDict={}
    for read in open(uFileName).readlines():
        line = string.split(read,"\n")[0]
        if not len(line): continue
        if line[0]=="#": continue
        for item in iList:
            keyword=string.upper(item)
            if re.match(keyword,line):
               self.configDict[item] = string.split(line,"%s="%keyword)[1]
            if iConfig.has_key(item) and iConfig[item]:
               self.configDict[item] = iConfig[item]
  def dbsprimary(self):
    if not self.configDict.has_key('dbsprimary'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing DBSPRIMARY parameter")
    return self.configDict['dbsprimary']
  def global_dd(self):
    if not self.configDict.has_key('global_dd'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing GLOBAL_DD parameter")
    return self.configDict['global_dd']
  def ns(self):
    if not self.configDict.has_key('ns'):
       return ""
    return self.configDict['ns']
  def memcache(self):
    if not self.configDict.has_key('memcache'):
       return []
    cachelist = self.configDict['memcache'].split(",")
    return cachelist
  def cachelimit(self):
    if not self.configDict.has_key('cachelimit'):
       return 0
    return self.configDict['cachelimit']
  def adminUrl(self):
    if not self.configDict.has_key('admin_url'):
       return ""
    return self.configDict['admin_url']
  def adminVer(self):
    if not self.configDict.has_key('admin_ver'):
       return ""
    return self.configDict['admin_ver']
  def dbsUrl(self):
    if not self.configDict.has_key('dbs_url'):
       return ""
    return self.configDict['dbs_url']
  def dbsVer(self):
    if not self.configDict.has_key('dbs_ver'):
       return ""
    return self.configDict['dbs_ver']
  def masthead(self):
    if not self.configDict.has_key('masthead'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing MASTHEAD parameter")
    return self.configDict['masthead']
  def mastfooter(self):
    if not self.configDict.has_key('mastfooter'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing MASTFOOTER parameter")
    return self.configDict['mastfooter']
  def loggerDir(self):
    if not self.configDict.has_key('loggerdir'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing LOGGERDIR parameter")
    return self.configDict['loggerdir']
  def queryLimit(self):
    if not self.configDict.has_key('querylimit'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing QUERYLIMIT parameter")
    return self.configDict['querylimit']
  def queryThreshold(self):
    if not self.configDict.has_key('querythreshold'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing QUERYTHRESHOLD parameter")
    return self.configDict['querythreshold']
  def iface(self):
    if not self.configDict.has_key('iface'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing IFACE parameter")
    return self.configDict['iface']
  def rs(self):
    if not self.configDict.has_key('rs'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing RS parameter")
    return self.configDict['rs']
  def url(self):
    if not self.configDict.has_key('url'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing URL parameter")
    return self.configDict['url']
  def user(self):
    if not self.configDict.has_key('user'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing USER parameter")
    return self.configDict['user']
  def password(self):
    if not self.configDict.has_key('password'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing PASSWORD parameter")
    return self.configDict['password']
  def dbname(self):
    if not self.configDict.has_key('dbname'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing DBNAME parameter")
    return self.configDict['dbname']
  def engine(self):
    if not self.configDict.has_key('engine'):
       raise DDException(args="Data Discovery configuration, DBSDD.conf, missing ENGINE parameter")
    return self.configDict['engine']
  def verbose(self):
    if not self.configDict.has_key('verbose'):
       return True
    return self.configDict['verbose']

#
# main
#
if __name__ == "__main__":
   dbsConfig = DDConfig()
   print "Config file",dbsConfig.configFile
   print dbsConfig.configDict

