#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
DBS data discovery authentication module.
"""

# import system modules
import os, string, sys

# import DBS modules
from DDUtil import *
from DDExceptions import *

DBS_INST_URL={}

class DDAuthentication:
  """
      DBS authentication module constructor. It reads DBS_DBPARAM file and parse it.
  """
  def __init__(self,pattern="",verbose=0):
      """
          DBS authentication module constructor. It reads DBS_DBPARAM file and parse it.
          @type  pattern: string
          @param pattern: DBS instance name
          @rtype : none
          @return: none
      """
      self.iface = ""
      self.db    = ""
      self.user  = ""
      self.passwd= ""
      self.host  = ""
      self.verbose = verbose
      self.dbparam = ""
      self.dbsInst = []
#      self.url     = "http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet"
      self.url     = ""
      try:
          self.dbparam = open(os.environ['DBS_DBPARAM'],'r')
      except:
          printExcept("No DBS_DBPARAM environment variable found")
          raise "Fail to open $DBS_DBPARAM"
      found=0
      content = self.dbparam.readlines()
      mustHave=['Section','Interface','Database','AuthDBUsername','AuthDBPassword','Host','Url']
      readFile=[]
      for s in content:
          line = string.replace(s,"\n","")
          lines= string.split(line)
          if not len(lines): continue
          if lines[0][0]=="#": continue
          if not readFile.count(lines[0]):
             readFile.append(lines[0])
      readFile.sort()
      mustHave.sort()
      if  readFile!=mustHave:
          msg="""
Initialization of DBS instances failed, please check your DBS_DBPARAM environment
and correct file it points to, to have the following syntax for each registered
DBS instance:

Section                 cms_dbs_prod_global
Interface               Oracle
Database                cms_dbs
AuthDBUsername          XXXXXX
AuthDBPassword          ZZZZZZ
Host                    host_name (e.g. localhost or localhost:3307)
Url                     servlet URL

"""
          msg+="Found    : %s\n"%readFile
          msg+="Must have: %s\n"%mustHave
          raise msg

      dbs=""
      url=""
      for s in content:
          line = string.replace(s,"\n","")
          lines= string.split(line)
          if not len(lines): continue
          if lines[0][0]=="#": continue
          if lines[0]=="Section":
             if not self.dbsInst.count(lines[1]) and lines[1].find("cms_dbs")!=-1:
                self.dbsInst.append(lines[1])
                dbs=lines[1]

             if lines[1]==pattern:
                found = 1
             else:
                found = 0
          if found:
             if lines[0]=="Interface":
                self.iface = lines[1]
             if lines[0]=="Database":
                self.db = lines[1]
             if lines[0]=="AuthDBUsername":
                self.user = lines[1]
             if lines[0]=="AuthDBPassword":
                self.passwd = lines[1]
             if lines[0]=="Host":
                self.host = lines[1]
             if lines[0]=="Url":
                url = lines[1]
                self.url = url
          else:
             if lines[0]=="Url":
                url = lines[1]
          DBS_INST_URL[dbs]=url

  def dbsInstances(self):
      return self.dbsInst

  def dbInfo(self):
      """
          @type  self: class object
          @param self: none
          @rtype : tuple
          @return: tuple (iface,db,user,passwd)
      """
      return (self.iface, self.db, self.user, self.passwd, self.host, self.url)
#
# main
#
if __name__ == "__main__":
   a = DDAuthentication("leppcms")
   print a.dbInfo()

