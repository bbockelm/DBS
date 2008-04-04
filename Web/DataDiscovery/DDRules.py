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

def constrainDict():
    """Dictionary of constrain operators with their weight as values"""
    cDict = {
       '>=':1,
       '>':1,
       '<=':1,
       '<':1,
       '=':1,
       'not_like':0.5,
       'not like':0.5,
       'like':0.5,
       'in':1,
       'between':1
    }
    return cDict
def constrainList():
    return constrainDict().keys()

class DDRules:
   def __init__(self,verbose=0):
       self.verbose=verbose
       self.boolwords=['and','or','(',')','not','like']
       self.functions=['total','sum','count']
       # mapping between keyword-names and DB views
       self.dbView ={
           'path'        :'datasetsummary',
           'block'       :'',
           'file'        :'filesummary',
           'release'     :'releasesummary',
           'run'         :'runsummary',
           'lumi'        :'',
           'site'        :'sitesummary',
           'primds'      :'primsummary',
           'procds'      :'procsummary',
           'tier'        :'tiersummary',
           'adsname'     :'',
           'adspath'     :'',
           'adsversion'  :'',
           'physicsgroup':'',
       }
       # association between keyword-names and human readble meaning
       self.longName={
           'path'        :'processed dataset',
           'block'       :'block',
           'file'        :'logical file name',
           'release'     :'software release',
           'run'         :'run',
           'lumi'        :'luminosity section',
           'site'        :'storage element',
           'primds'      :'primary dataset',
           'procds'      :'processed dataset',
           'tier'        :'data tier',
           'createdate'  :'creation date',
           'modifydate'  :'last modification date',
           'createdby'   :'created date',
           'modifyby'    :'last modified by',
           'adsname'     :'analisis dataset name',
           'adspath'     :'analisis dataset path',
           'adsversion'  :'analisis dataset version',
           'physicsgroup':'physics group name',
       }
       # associate between keyword-names and DBS tables
       self.tableName={
           'path'        :'Block',
           'block'       :'Block',
           'file'        :'Files',
           'release'     :'AppVersion',
           'run'         :'Runs',
           'lumi'        :'LumiSection',
           'site'        :'StorageElement',
           'primds'      :'PrimaryDataset',
           'procds'      :'ProcessedDataset',
           'tier'        :'DataTier',
           'adsname'     :'AnalisisDataset',
           'adspath'     :'AnalisisDataset',
           'adsversion'  :'AnalisisDataset',
           'physicsgroup':'PhysicsGroup',
       }
       # use lowercase table names since SQLAlchemy returns them in lower-case
       self.tableWeights={
           'block':3,
           'files':3,
           'appversion':1,
           'runs':3,
           'lumisection':3,
           'storageelement':1,
           'primarydataset':1,
           'processeddataset':1,
           'datatier':1,
           'physicsgroup':1,
       }
       self.colName={
           'path'        :'Path',
           'block'       :'Name',
           'file'        :'LogicalFileName',
           'release'     :'Version',
           'run'         :'RunNumber',
           'lumi'        :'LumiSectionNumber',
           'site'        :'SEName',
           'primds'      :'Name',
           'procds'      :'Name',
           'tier'        :'Name',
           'adsname'     :'Name',
           'adspath'     :'Path',
           'adsversion'  :'Version',
           'physicsgroup':'Name',
           'createdate'  :'CreationDate',
           'modifydate'  :'LastModificationDate',
           'createby'    :'CreatedBy',
           'modifyby'    :'LastModifiedBy',
       }
       # mapping from keyword to keyword pairs and DBS DB paths
       self.dbs_map={
           # to dataset
           ('path','path'):['Block_Path2Block_Path'],
           ('block','path'):['Block_Name2Block_Path'],
           ('file','path'):['Files_LogicalFileName2Block_Path'],
           ('release','path'):['AppVersion_Version2ProcessedDataset_ID','ProcessedDataset_ID2Block_Path'],
           ('run','path'):['Runs_RunNumber2ProcessedDataset_ID','ProcessedDataset_ID2Block_Path'],
           ('lumi','path'):['LumiSection_LumiSectionNumber2Runs_ID','Runs_ID2ProcessedDataset_ID2Block_Path'],
           ('site','path'):['StorageElement_SEName2Block_Path'],
           ('primds','path'):['PrimaryDataset_Name2Block_Path'],
           ('procds','path'):['ProcessedDataset_Name2Block_Path'],
           ('tier','path'):['DataTier_Name2ProcessedDataset_ID','ProcessedDataset_ID2Block_Path'],
           # to block
           ('path','block'):['Block_Path2Block_Name'],
           ('block','block'):['Block_Name2Block_Name'],
           ('file','block'):['Files_LogicalFileName2Block_Name'],
           ('release','block'):['AppVersion_Version2ProcessedDataset_ID','ProcessedDataset_ID2Block_Name'],
           ('run','block'):['Runs_RunNumber2ProcessedDataset_ID','ProcessedDataset_ID2Block_Name'],
           ('lumi','block'):['LumiSection_LumiSectionNumber2Runs_ID','Runs_ID2ProcessedDataset_ID2Block_Name'],
           ('site','block'):['StorageElement_SEName2Block_Name'],
           ('primds','block'):['PrimaryDataset_Name2Block_Name'],
           ('procds','block'):['ProcessedDataset_Name2Block_Name'],
           ('tier','block'):['DataTier_Name2ProcessedDataset_ID','ProcessedDataset_ID2Block_Name'],
           # to file
           ('path','file'):['Block_Path2Files_LogicalFileName'],
           ('block','file'):['Block_Name2Files_LogicalFileName'],
           ('file','file'):['Files_LogicalFileName2Files_LogicalFileName'],
           ('release','file'):['AppVersion_Version2Files_LogicalFileName'],
           ('run','file'):['Runs_RunNumber2Files_LogicalFileName'],
           ('lumi','file'):['LumiSection_LumiSectionNumber2Files_LogicalFileName'],
           ('site','file'):['StorageElement_SEName2Files_LogicalFileName'],
           ('primds','file'):['PrimaryDatset_Name2Files_LogicalFileName'],
           ('procds','file'):['ProcessedDataset_Name2Files_LogicalFileName'],
           ('tier','file'):['DataTier_Name2Files_LogicalFileName'],
           # to release
           ('path','release'):['Block_Path2AppVersion_Version'],
           ('block','release'):['Block_Name2AppVersion_Version'],
           ('file','release'):['Files_LogicalFileName2AppVersion_Version'],
           ('release','release'):['AppVersion_Version2AppVersion_Version'],
           ('run','release'):['Runs_RunNumber2ProcessedDataset_ID','ProcessedDataset_ID2AppVersion_Version'],
           ('lumi','release'):['LumiSection_LumiSectionNumber2ProcessedDataset_ID','ProcessedDataset_ID2AppVersion_Version'],
           ('site','release'):['StorageElement_SEName2AppVersion_Version'],
           ('primds','release'):['PrimaryDataset_Name2AppVersion_Version'],
           ('procds','release'):['ProcessedDataset_Name2AppVersion_Version'],
           ('tier','release'):['DataTier_Name2AppVersion_Version'],
           # to run
           ('path','run'):['Block_Path2ProcessedDataset_ID','ProcessedDataset_ID2Runs_RunNumber'],
           ('block','run'):['Block_Name2ProcessedDataset_ID','ProcessedDataset_ID2Runs_RunNumber'],
           ('file','run'):['Files_LogicalFileName2Runs_RunNumber'],
           ('release','run'):['AppVersion_Version2ProcessedDataset_ID','ProcessedDataset_ID2Runs_RunNumber'],
           ('run','run'):['Runs_RunNumber2Runs_RunNumber'],
           ('lumi','run'):['LumiSection_LumiSectionNumber2Runs_RunNumber'],
           ('site','run'):['StorageElement_SEName2ProcessedDataset_ID','ProcessedDataset_ID2Runs_RunNumber'],
           ('primds','run'):['PrimaryDataset_Name2ProcessedDataset_ID','ProcessedDataset_ID2Runs_RunNumber'],
           ('procds','run'):['ProcessedDataset_Name2Runs_RunNumber'],
           ('tier','run'):['DataTier_Name2ProcessedDataset_ID','ProcessedDataset_ID2Runs_RunNumber'],
           # to lumi
           ('path','lumi'):['Block_Path2ProcessedDataset_ID','ProcessedDataset_ID2LumiSection_LumiSectionNumber'],
           ('block','lumi'):['Block_Name2ProcessedDataset_ID','ProcessedDataset_ID2LumiSection_LumiSectionNumber'],
           ('file','lumi'):['Files_LogicalFileName2LumiSection_LumiSectionNumber'],
           ('release','lumi'):['AppVersion_Version2ProcessedDataset_ID','ProcessedDataset_ID2Runs_ID','Runs_ID2LumiSection_LumiSectionNumber'],
           ('run','lumi'):['Runs_RunNumber2LumiSection_LumiSectionNumber'],
           ('lumi','lumi'):['LumiSection_LumiSectionNumber2LumiSection_LumiSectionNumber'],
           ('site','lumi'):['StorageElement_SEName2ProcessedDataset_ID','ProcessedDataset_ID2LumiSection_LumiSectionNumber'],
           ('primds','lumi'):['PrimaryDatset_Name2LumiSection_LumiSectionNumber'],
           ('procds','lumi'):['ProcessedDataset_Name2LumiSection_LumiSectionNumber'],
           ('tier','lumi'):['DataTier_Name2ProcessedDataset_ID','ProcessedDataset_ID2LumiSection_LumiSectionNumber'],
           # to site
           ('path','site'):['Block_Path2StorageElement_SEName'],
           ('block','site'):['Block_Name2StorageElement_SEName'],
           ('file','site'):['Files_LogicalFileName2StorageElement_SEName'],
           ('release','site'):['AppVersion_Version2StorageElement_SEName'],
           ('run','site'):['Runs_RunNumber2ProcessedDataset_ID','ProcessedDataset_ID2StorageElement_SEName'],
           ('lumi','site'):['LumiSection_LumiSectionNumber2ProcessedDataset_ID','ProcessedDataset_ID2StorageElement_SEName'],
           ('site','site'):['StorageElement_SEName2StorageElement_SEName'],
           ('primds','site'):['PrimaryDataset_Name2StorageElement_SEName'],
           ('procds','site'):['ProcessedDataset_Name2StorageElement_SEName'],
           ('tier','site'):['DataTier_Name2ProcessedDataset_ID','ProcessedDataset_ID2StorageElement_SEName'],
           # to primds
           ('path','primds'):['Block_Path2PrimaryDataset_Name'],
           ('block','primds'):['Block_Name2PrimaryDataset_Name'],
           ('file','primds'):['Files_LogicalFileName2PrimaryDataset_Name'],
           ('release','primds'):['AppVersion_Version2PrimaryDataset_Name'],
           ('run','primds'):['Runs_RunNumber2PrimaryDataset_Name'],
           ('lumi','primds'):['LumiSection_LumiSectionNumber2PrimaryDataset_Name'],
           ('site','primds'):['StorageElement_SEName2PrimaryDataset_Name'],
           ('primds','primds'):['PrimaryDataset_Name2PrimaryDataset_Name'],
           ('procds','primds'):['ProcessedDataset_Name2PrimaryDataset_Name'],
           ('tier','primds'):['DataTier_Name2PrimaryDataset_Name'],
           # to procds
           ('path','procds'):['Block_Path2ProcessedDataset_Name'],
           ('block','procds'):['Block_Name2ProcessedDataset_Name'],
           ('file','procds'):['Files_LogicalFileName2ProcessedDataset_Name'],
           ('release','procds'):['AppVersion_Version2ProcessedDataset_Name'],
           ('run','procds'):['Runs_RunNumber2ProcessedDataset_Name'],
           ('lumi','procds'):['LumiSection_LumiSectionNumber2ProcessedDataset_Name'],
           ('site','procds'):['StorageElement_SEName2ProcessedDataset_Name'],
           ('primds','procds'):['PrimaryDataset_Name2ProcessedDataset_Name'],
           ('procds','procds'):['ProcessedDataset_Name2ProcessedDataset_Name'],
           ('tier','procds'):['DataTier_Name2ProcessedDataset_Name'],
           # to tier
           ('path','tier'):['Block_Path2ProcessedDataset_ID','ProcessedDataset_ID2DataTier_Name'],
           ('block','tier'):['Block_Name2ProcessedDataset_ID','ProcessedDataset_ID2DataTier_Name'],
           ('file','tier'):['Files_LogicalFileName2DataTier_Name'],
           ('release','tier'):['AppVersion_Version2DataTier_Name'],
           ('run','tier'):['Runs_RunNumber2ProcessedDataset_ID','ProcessedDataset_ID2DataTier_Name'],
           ('lumi','tier'):['LumiSection_LumiSectionNumber2ProcessedDataset_ID','ProcessedDataset_ID2DataTier_Name'],
           ('site','tier'):['StorageElement_SEName2ProcessedDataset_ID','ProcessedDataset_ID2DataTier_Name'],
           ('primds','tier'):['PrimaryDataset_Name2DataTier_Name'],
           ('procds','tier'):['ProcessedDataset_Name2DataTier_Name'],
           ('tier','tier'):['DataTier_Name2DataTier_Name'],
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

   def setVerbose(self,v):
       self.verbose=v

   def parser(self,input,sortName='CreationDate',sortOrder='desc',case='on'):
       if not DDUtil.validator(input):
          raise "Input '%s' does not contain equal number of open/closed brackets"%input
       words=self.parseInput(self.preParseInput(input),sortName,sortOrder,case)
       return words

   def preParseInput(self,input):
       input=input.replace(")"," ) ").replace("("," ( ")
       # wrap operator ['<=','>=','!=','=','<','>'] with spaces for better parsing
       isplit=input.split()
       try:
           for i in xrange(0,len(isplit)):
               item  = isplit[i]
               if item=='<=' or item=='>=' or item=='!=':
                  item=' %s '%item
                  isplit[i]=item
               elif len(item)==1 and (item=='=' or item=='<' or item=='>'):
                  item=' %s '%item
                  isplit[i]=item
               else:
                  for op in ['<=','>=','!=','=','<','>']:
                      idx = item.find(op)
                      if idx!=-1:
                         if not ['=','<','>'].count(op):
                            item=item.replace(op," %s "%op)
                         else:
                            if op=="=" and (item[idx-1]=='<' or item[idx-1]=='>'):
                               continue
                            elif (op=="<" or op==">") and len(item)>idx+1 and item[idx+1]=='=':
                               continue
                            else:
                               item=item.replace(op," %s "%op)
                  isplit[i]=item
       except:
           traceback.print_exc()
           raise "prePraseInput: fail to parse your input='%s'"%input
       return ' '.join(isplit)
    
   def parseInput(self,input,sortName,sortOrder,case):
       idx=input.lower().find('where')
       if idx!=-1:
          what=input[:idx]
          input   = input[idx+len('where')+1:]
          selKey = what.lower().replace('find','').strip()
       else:
          selKey = "path"
       iList = input.split()
       if self.verbose:
          print "\nParser input='%s', iList='%s', selKey=%s"%(input,iList,selKey)
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
           words[words.index(key)]=self.parsePattern(selKey,sortName,sortOrder,pDict[key],case)
       return ' '.join(words)

   def parsePattern(self,selKey,sortName,sortOrder,pattern,case):
       co_idx=-1
       words=[]
       msg="Fail to parse '%s', no constrain operator found"%pattern
       try:
           for co in self.sList:
               j=pattern.find(co)
               # look-up operator, find itx index in a pattern and see that neighboors should be ' '.
               if j!=-1 and pattern[j-1]==' ' and pattern[j+len(co)]==' ':
                  key = pattern[:j]
                  k   = pattern.rfind(co)
                  val = pattern[j+len(co):]
                  words.append("%s:"%key.strip())
                  words.append(co)
                  words.append(val.strip())
                  break
       except:
          raise msg
       if not words:
          raise msg
       return self.parseObject(selKey,sortName,sortOrder,[''.join(words)],case)

   def parseObject(self,selKey,sortName,sortOrder,input,case):
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
                 funcFound=DDUtil.findKeyInAList(self.functions,selKey)
                 if funcFound:
                    selKey=selKey.replace("(","").replace(")","").replace(funcFound,"").strip()
                 fList = self.dbs_map[(f,selKey)]
                 _call = ""
                 count = 0
                 _fList=list(fList)
                 _fList.reverse()
                 for func in _fList:
                     _call+= "self.makeQuery('%s',rval="%func
                     count+=1
                 _call+="'%s',case='%s'"%(v,case)
                 if funcFound:
                    _call+=",func=%s"%funcFound
                 for i in xrange(0,count):
                     if i==count-1:
                        _call+=",sortName='%s',sortOrder='%s')"%(sortName,sortOrder)
                     else:
                        _call+=")"
                 _words.append(_call)
              except:
                 traceback.print_exc()
                 raise "Unable to parse your input, selKey='%s', parse it as '%s', list of known keywords %s, list of supported operators %s"%(selKey,words,str(self.dbs_map.keys()),self.sList)
           else:
                 traceback.print_exc()
                 raise "Keyword '%s' does not contain separator \":\"."%w
       eString = ' '.join(_words)
       if self.verbose:
          print "\n+++ Translate user input:\n%s\n+++ into the following expression:\n%s\n"%(input,eString)
       return eString

#
# main
#
if __name__ == "__main__":
    aSearch = DDRules(verbose=1)
    aSearch.parser("(path=/a/b/c and block=123) or run >= 12345 path =bla or (path= /c/d/e or run=123)")
    aSearch.parser("find primds where (path like *Online* or path not like *RelVal* ) and release> CMSSW_1_7 ")
#    aSearch.parser("find total(run) where (path like *bla* and block>=123) or run=12345")
#    aSearch.parser("find run where (path like *bla* and block>=123) or run=12345")
#    aSearch.parser("run=12345")
#    aSearch.parser("run not like 12345")
#    aSearch.parser("run no like 12345")
#    aSearch.parser("run 12345")
