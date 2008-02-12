#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853.
#
# Author:  Valentin Kuznetsov, 2006
#

# system modules
import os, sys, string, stat, re, types

# NVS specific modules
from nvsException    import NvsException

class NvsConfig(object):
  def __init__(self,iConfig={}):
    """
       Read and parse content of configuration file
       The following syntax is supported in configuration file:
       # ESDB configuration file
       login:password
       # ESDB Master
       Comments are started with '#' letter. User can specify login and password
       to provide access to underlying DB, if SQLite is used they're ignored.
       If NVS_CLIENT_CONFIG is noty set then the configuration is not attempted to be read.
    """
 
    uFileName=""
    iList=['user','password','driver','url','host','port','log','level', 'servlet','dbname','nvsDB','dbtype','verbose','mode', 'nvshome', 'javahome' ]
    self.configDict={}

    for item in iList:
	if iConfig.has_key(item) and iConfig[item]:
		self.configDict[item] = iConfig[item]


    if os.environ.has_key('NVS_CLIENT_CONFIG'):
       if not os.path.isfile(os.environ['NVS_CLIENT_CONFIG']):
          raise NvsException("The '%s' config file does not exists"%os.environ['NVS_CLIENT_CONFIG'])
       uFileName=os.environ['NVS_CLIENT_CONFIG']
       #else:
       #   raise NvsException(args="No NVS_CLIENT_CONFIG environment is defined")
       #else:
       #   uFileName = os.path.normpath(os.environ["HOME"]+"/.nvs.conf")
       self.configFile=uFileName
       if not os.path.isfile(uFileName):
          raise NvsException("The NVS_CLIENT_CONFIG='%s' config file does not exists"%uFileName)
       mode = os.stat(uFileName)[stat.ST_MODE]
       if mode!=33152:
          # mode is not -rw-------
          #print "WARNING: permission of %s is set to 0600 mode (-rw-------)"%uFileName
          #os.chmod(uFileName,0600)
          print ""
       login = masterHost =  masterName = masterPort = masterSocket = admin = ""
       for read in open(uFileName).readlines():
           line = string.split(read,"\n")[0]
           line = line.strip()
           if not len(line): continue
           if line[0]=="#": continue
           for item in iList:
               keyword=string.upper(item)
               if re.search(keyword,line):
                  self.configDict[item] = string.split(line,"%s="%keyword)[1]
               #if iConfig.has_key(item) and iConfig[item]:
               #   self.configDict[item] = iConfig[item]

    # Over-write the values from the Passed in dict. OR use them if no config file is specified
    for item in iList:
        if iConfig.has_key(item) and iConfig[item]:
                self.configDict[item] = iConfig[item]


  def verbose(self):
    if not self.configDict.has_key('verbose'):
       return 0
    return 1
  def host(self):
    if not self.configDict.has_key('host'):
       raise NvsException("NVS configuration missing host parameter")
    return self.configDict['host']
  def port(self):
    if not self.configDict.has_key('port'):
       raise NvsException("NVS configuration missing port parameter")
    return self.configDict['port']
  def mode(self):
    if not self.configDict.has_key('mode'):
       raise NvsException("NVS configuration missing mode parameter")
    return self.configDict['mode']
  def nvshome(self):
    if not self.configDict.has_key('nvshome'):
       raise NvsException("NVS configuration missing mode parameter")
    return self.configDict['nvshome']
  def servlet(self):
    if not self.configDict.has_key('servlet'):
       raise NvsException("NVS configuration missing servlet parameter")
    return self.configDict['servlet']
  def user(self):
    if not self.configDict.has_key('user'):
       raise NvsException("NVS configuration missing user parameter")
    return self.configDict['user']
  def password(self):
    if not self.configDict.has_key('password'):
       raise NvsException("NVS configuration missing password parameter")
    return self.configDict['password']
  def dbname(self):
    if not self.configDict.has_key('dbname'):
       raise NvsException("NVS configuration missing dbname parameter")
    return self.configDict['dbname']
  def nvsDB(self):
    if not self.configDict.has_key('nvsDB'):
       raise NvsException("NVS configuration missing nvsDB parameter")
    return self.configDict['nvsDB']
  def dbtype(self):
    if not self.configDict.has_key('dbtype'):
       raise NvsException("NVS configuration missing dbtype parameter")
    return self.configDict['dbtype']
  def javahome(self):
    if not self.configDict.has_key('javahome'):
       raise NvsException("NVS configuration missing javahome parameter")
    return self.configDict['javahome']
  def url(self):
    if not self.configDict.has_key('url'):
       raise NvsException("NVS configuration missing url parameter")
    return self.configDict['url']
  def log(self):
    if not self.configDict.has_key('log'):
       raise NvsException("NVS configuration missing log parameter")
    return self.configDict['log']
  def loglevel(self):
    if not self.configDict.has_key('level'):
       raise NvsException("NVS configuration missing log level parameter")
    return self.configDict['level']

#
# main
#
if __name__ == "__main__":
   nvsConfig = NvsConfig()
   print "Config file",nvsConfig.configFile
   print nvsConfig.configDict

