#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008
import sys,os,string,time,traceback,re

class DbsRules:
   def __init__(self,verbose=0):
       self.verbose=verbose
       self.boolwords=['and','or','(',')','not','like']
       self.functions=['sum','count']
       self.yyyymmdd=re.compile('^\d{8}$')
       self.pathMatch=re.compile("^\/[^/]+\/[^/]+\/[^/]+$")

   def preParseInput(self,input):
       if len(input.split())==1 and input.find("=")==-1 and input.find(">")==-1 and input.find("<")==-1:
          if not self.pathMatch.match(input) and input.find("*")==-1:
             input="*%s*"%input
          if input.find("*")!=-1:
             input="find dataset where dataset like %s"%input
          else:
             input="find dataset where dataset = %s"%input
       return input

