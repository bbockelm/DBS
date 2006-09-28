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
from   DBSUtil import *

class DBSAuthentication:
  """
      DBS authentication module constructor. It reads DBS_DBPARAM file and parse it.
  """
  def __init__(self,pattern):
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
      try:
          self.dbparam = open(os.environ['DBS_DBPARAM'],'r')
      except:
          printExcept("No DBS_DBPARAM environment variable found")
      found=0
      for s in self.dbparam.readlines():
          line = string.replace(s,"\n","")
          lines= string.split(line)
          if not len(lines): continue
          if lines[0][0]=="#": continue
          if lines[0]=="Section":
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
  def dbInfo(self):
      """
          @type  self: class object
          @param self: none
          @rtype : tuple
          @return: tuple (iface,db,user,passwd)
      """
      return (self.iface, self.db, self.user, self.passwd)
#
# main
#
if __name__ == "__main__":
   a = DBSAuthentication("leppcms")
   print a.dbInfo()

