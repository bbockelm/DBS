#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008

"""
Data Discovery search module
"""

# system modules
import os, string, logging, types, time, traceback, new
import DDUtil

class DDSearch:
   def __init__(self,dbsHelper="",phedexHelper="",runsumHelper="",lumiHelper="",condHelper=""):
       self.dbsHelper=dbsHelper
       self.phedexHelper=phedexHelper
       self.runsumHelper=runsumHelper
       self.lumiHelper=lumiHelper
       self.condHelper=condHelper
       self.boolwords=['and','or','(',')','not','like']
       self.dbs_map={
           'dataset':['Block2Block'],
           'block':['Block2Block'],
           'lfn':['File2Block'],
           #'ads':['Ads2Proc','Proc2Block'],
           'release':['Rel2Algo','Algo2Proc','Proc2Block'],
           'run':['Run2Proc','Proc2Block'],
           'lumi':['Lumi2Run','Run2Proc','Proc2Block'],
           'se':['SE2Block'],
           #'pset':['Pset2Algo','Algo2Proc','Proc2Block']
       }
       self.runsum_map={}
       self.lumi_map={}
       self.phedex_map={
           'site':['PhedexCall'],
       }
       self.cond_map={}
       self.dbms={
           'dbs':self.dbs_map,
           'runsumdb':self.runsum_map,
           'lumidb':self.lumi_map,
           'phedex':self.phedex_map,
           'conddb':self.cond_map,
       }
       # construct DBS methods
       for v in self.dbs_map.values():
           for f in v:
               setattr(self,f,self.templateFunc(self.dbsHelper,f))
       # construct runsumdb methods
       for v in self.runsum_map.values():
           for f in v:
               setattr(self,f,self.templateFunc(self.runsumHelper,f))
       # construct lumidb methods
       for v in self.lumi_map.values():
           for f in v:
               setattr(self,f,self.templateFunc(self.lumiHelper,f))
       # construct Phedex methods
       for v in self.phedex_map.values():
           for f in v:
               setattr(self,f,self.templateFunc(self.phedexHelper,f))
       # construct conddb methods
       for v in self.cond_map.values():
           for f in v:
               setattr(self,f,self.templateFunc(self.condHelper,f))

   def templateFunc(self,base,f):
       if base and hasattr(base,f):
          return getattr(base,f)
       else:
          print "%s.%s is not yet implemented"%(str(base),f)

   def parseSearchInput(self,input):
       words = DDUtil.inputParser(input,self.dbs_map.keys())
       _words= []
       f=""
       v=""
       for w in words:
           if w.find(":")!=-1:
              f,v=w.split(":")
              try:
                 fList = self.dbs_map[f]
                 _call = ""
                 count = 0
                 _fList=list(fList)
                 _fList.reverse()
                 for func in _fList:
                     if len(_fList)==1 or func==_fList[-1]:
                        _call+= "self.%s(input={'%s':"%(func,f)
                     else:
                        _call+= "self.%s(input="%func
                     count+=1
                 _call+="'%s'}"%v
                 for i in xrange(0,count):
                     _call+=")"
                 _words.append(_call)
              except:
                 traceback.print_exc()
                 raise "Unknown keyword '%s', known list: %s"%(f,str(self.dbs_map.keys()))
           else:
                 traceback.print_exc()
                 raise "Keyword does not contain separator \":\"."
       eString = ' '.join(_words)
#       print "\n+++ Translate user input:\n%s\n+++ into the following expression:\n%s\n"%(input,eString)
       return eString

   def parseSearchInput_v1(self,input):
#       input = input.replace("("," ( ").replace(")"," ) ")
       words = DDUtil.inputParser( ":".join(input.split(":")) )
#       words = input.split()
       _words= []
       f=""
       v=""
       for w in words:
           if w.find(":")!=-1:
              _split=w.split(":")
              sub="dbs"
              if len(_split)==3:
                 sub,f,v=w.split(":")
              elif len(_split)==2:
                 f,v=w.split(":")
              else:
                  raise "Not supported expression, '%s'"%w
              try:
                 fList = self.dbms[sub][f]
#                 _call = "set("
                 _call = ""
                 count = 0
                 _fList=list(fList)
                 _fList.reverse()
                 for func in _fList:
                     if len(_fList)==1 or func==_fList[-1]:
                        _call+= "self.%s(input={'%s':"%(func,f)
                     else:
                        _call+= "self.%s(input="%func
                     count+=1
                 _call+="'%s'}"%v
                 for i in xrange(0,count):
                     _call+=")"
#                 _call+=")" # end of set
                 _words.append(_call)
              except:
                 traceback.print_exc()
                 raise "Unknown keyword '%s', known list: %s"%(f,str(self.dbms[sub].keys()))
           else:
                 traceback.print_exc()
                 raise "Keyword does not contain separator \":\"."
#              if not self.boolwords.count(w):
#                 traceback.print_exc()
#                 raise "Unknown boolean keyword '%s', known list: %s"%(w,str(self.boolwords))
#              if w=="and":
#                 _words.append(" INTERSECTS ") # intersection
#              elif w=="or":
#                 _words.append(" UNION ") # union
#              else:
#                 _words.append(w)
#              if w=="and":
#                 _words.append(" & ") # intersection
#              elif w=="or":
#                 _words.append(" | ") # union
#              else:
#                 _words.append(w)
       eString = ' '.join(_words)
       print "\n+++ Translate user input:\n%s\n+++ into the following expression:\n%s\n"%(input,eString)
       return eString
#       return eval(eString)

class PhedexTest:
   def __init__(self):
       print "Init PhedexTest"
   def PhedexCall(self,**kwargs):
       print "Call PhedexCall with %s"%(str(kwargs))
       return [1,2,3]
class DbsTest:
   def __init__(self):
       print "Init DbsTest"
   def Pset2Algo(self,**kwargs):
       print "Call Pset2Algo",str(kwargs)
       return [1,2,3]
   def Rel2Algo(self,**kwargs):
       print "Call Rel2Algo",str(kwargs)
       return [1,2,3]
   def Algo2Proc(self,**kwargs):
       print "Call Algo2Proc",str(kwargs)
       return [1,2,3]
   def SE2Block(self,**kwargs):
       print "Call SE2Block",str(kwargs)
       return [1,2,3]
   def Lumi2Run(self,**kwargs):
       print "Call Lumi2Run",str(kwargs)
       return [1,2,3]
   def File2Block(self,**kwargs):
       print "Call File2Block",str(kwargs)
       return [1,2,3]
   def Proc2Block(self,**kwargs):
       print "Call Proc2Block",str(kwargs)
       return [1,2,3]
   def Ads2Proc(self,**kwargs):
       print "Call Ads2Proc",str(kwargs)
       return [1,2,3]
   def Block2Block(self,**kwargs):
       print "Call Block2Block",str(kwargs)
       return [1,2,3]
   def FindDatasets(self,**kwargs):
       print "Call FindDatasets",str(kwargs)
#
# main
#
if __name__ == "__main__":
    aSearch = DDSearch(dbsHelper=DbsTest(),phedexHelper=PhedexTest())
    aSearch.parseSearchInput("(dataset:*bla* and block:123) or pset:*2g")
    aSearch.parseSearchInput("phedex:site:cern.ch")
