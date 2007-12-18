#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853.
#
# Author:  Valentin Kuznetsov, 2006
#

# system modules
import os, sys, string, stat, re, types
from DDExceptions import *

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
    mode = os.stat(uFileName)[stat.ST_MODE]
    if mode!=33152:
       # mode is not -rw-------
       print "WARNING: permission of %s is set to 0600 mode (-rw-------)"%uFileName
       os.chmod(uFileName,0600)
    iList=['engine','user','password','verbose','dbname','url','iface','querylimit','loggerdir','masthead','mastfooter','admin_url','admin_ver','ns','global_dd']
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
  def global_dd(self):
    if not self.configDict.has_key('global_dd'):
       raise DDException(args="DBS configuration missing global_dd parameter")
    return self.configDict['global_dd']
  def ns(self):
    if not self.configDict.has_key('ns'):
       return ""
    return self.configDict['ns']
  def adminUrl(self):
    if not self.configDict.has_key('admin_url'):
       return ""
    return self.configDict['admin_url']
  def adminVer(self):
    if not self.configDict.has_key('admin_ver'):
       return ""
    return self.configDict['admin_ver']
  def masthead(self):
    if not self.configDict.has_key('masthead'):
       raise DDException(args="DBS configuration missing masthead parameter")
    return self.configDict['masthead']
  def mastfooter(self):
    if not self.configDict.has_key('mastfooter'):
       raise DDException(args="DBS configuration missing mastfooter parameter")
    return self.configDict['mastfooter']
  def loggerDir(self):
    if not self.configDict.has_key('loggerdir'):
       raise DDException(args="DBS configuration missing loggerdir parameter")
    return self.configDict['loggerdir']
  def queryLimit(self):
    if not self.configDict.has_key('querylimit'):
       raise DDException(args="DBS configuration missing querylimit parameter")
    return self.configDict['querylimit']
  def iface(self):
    if not self.configDict.has_key('iface'):
       raise DDException(args="DBS configuration missing iface parameter")
    return self.configDict['iface']
  def url(self):
    if not self.configDict.has_key('url'):
       raise DDException(args="DBS configuration missing url parameter")
    return self.configDict['url']
  def user(self):
    if not self.configDict.has_key('user'):
       raise DDException(args="DBS configuration missing user parameter")
    return self.configDict['user']
  def password(self):
    if not self.configDict.has_key('password'):
       raise DDException(args="DBS configuration missing password parameter")
    return self.configDict['password']
  def dbname(self):
    if not self.configDict.has_key('dbname'):
       raise DDException(args="DBS configuration missing dbname parameter")
    return self.configDict['dbname']
  def engine(self):
    if not self.configDict.has_key('engine'):
       raise DDException(args="DBS configuration missing engine parameter")
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

