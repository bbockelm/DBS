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

def constrainList():
    return ['>=','>','<=','<','=','not_like','not like','like','in','between']
#    return ['>=','>','<=','<','=','not like','like','in','between','is not null','is null']

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
           'site':['SE2Block'],
           'prim':['Prim2Proc','Proc2Block'],
           'proc':['Proc2Block'],
           'tier':['Tier2Proc','Proc2Block'],
           #'pset':['Pset2Algo','Algo2Proc','Proc2Block']
       }
       # list of supported constrain operator, order is matter, since we walk through
       # the list to find a match. Example, if pattern is '<=' and order is <,<= we will
       # find first match to <, which is wrong, while if order is <=,< first match will be correct.
       self.sList=constrainList()
       self.oList=['and','or','minus','(',')']
       self.kLIst=self.dbs_map.keys()

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

   def parser(self,input,case='on'):
       words=self.parseInput(self.preParseInput(input),case)
       return words

   def preParseInput(self,input):
       input=input.replace(")"," ) ").replace("("," ( ")
       return input
    
   def parseInput(self,input,case):
       iList = input.split()
#       print "My input",input,iList
       msg   = "Fail to parse your input"
       words = []
       pDict = {}
       pattern = ""
       try:
           for idx in xrange(0,len(iList)):
               item = iList[idx]
               if self.oList.count(item):
                  if pattern:
                     key="q_%s"%idx
                     pDict[key]=pattern
                     words.append(key)
                     pattern=""
                  words.append(item)
                  continue
               pattern+=" "+item
           if  pattern:
               key="q_%s"%idx
               pDict[key]=pattern
               words.append(key)
       except:
           raise msg
       for key in pDict.keys():
           words[words.index(key)]=self.parsePattern(pDict[key],case)
       return ' '.join(words)

   def parsePattern(self,pattern,case):
       co_idx=-1
       words=[]
       for co in self.sList:
           j=pattern.find(co)
           if j!=-1:
              key = pattern[:j]
              k   = pattern.rfind(co)
              val = pattern[j+len(co):]
              words.append("%s:"%key.strip())
              words.append(co)
              words.append(val.strip())
              break
       if not words:
          raise "Fail to parse '%s', no constrain operator found"%pattern
       return self.parseObject([''.join(words)],case)

   def parseObject(self,input,case):
       words = input
       _words= []
       f=""
       v=""
       for w in words:
           if w.find(":")!=-1:
              f,v=w.split(":")
              if v.find('not like')==0:
                 v='not_like'+v[len('not like'):]
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
                 _call+="'%s'},case='%s'"%(v,case)
                 for i in xrange(0,count):
                     _call+=")"
                 _words.append(_call)
              except:
                 traceback.print_exc()
                 raise "Unable to parse your input, parse it as '%s', list of known keywords %s, list of supported operators %s"%(words,str(self.dbs_map.keys()),self.sList)
#                 raise "Unknown keyword '%s', known list: %s"%(f,str(self.dbs_map.keys()))
           else:
                 traceback.print_exc()
                 raise "Keyword '%s' does not contain separator \":\"."%w
       eString = ' '.join(_words)
#       print "\n+++ Translate user input:\n%s\n+++ into the following expression:\n%s\n"%(input,eString)
       return eString

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
    aSearch.parser("(dataset like *bla* and block>=123) or run=12345")
    aSearch.parser("run=12345")
    aSearch.parser("run not like 12345")
    aSearch.parser("run no like 12345")
    aSearch.parser("run 12345")
