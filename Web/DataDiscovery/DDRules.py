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
import os, string, logging, types, time, traceback, new, re
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
       self.cmsNames ={}
       self.boolwords=['and','or','(',')','not','like']
       self.functions=['total','sum','count']
       # mapping between keyword-names and DB views
       self.dbView ={
           'dataset'        :'datasetsummary',
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
           'dataset'        :'processed dataset',
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
           'dataset'        :'Block',
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
           'dataset'        :'Path',
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
           ('dataset','dataset'):['Block_Path2Block_Path'],
           ('block','dataset'):['Block_Name2Block_Path'],
           ('file','dataset'):['Files_LogicalFileName2Block_Path'],
           ('release','dataset'):['AppVersion_Version2ProcessedDataset_ID','ProcessedDataset_ID2Block_Path'],
           ('run','dataset'):['Runs_RunNumber2ProcessedDataset_ID','ProcessedDataset_ID2Block_Path'],
           ('lumi','dataset'):['LumiSection_LumiSectionNumber2Runs_ID','Runs_ID2ProcessedDataset_ID2Block_Path'],
           ('site','dataset'):['StorageElement_SEName2Block_Path'],
           ('primds','dataset'):['PrimaryDataset_Name2Block_Path'],
           ('procds','dataset'):['ProcessedDataset_Name2Block_Path'],
           ('tier','dataset'):['DataTier_Name2ProcessedDataset_ID','ProcessedDataset_ID2Block_Path'],
           # to block
           ('dataset','block'):['Block_Path2Block_Name'],
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
           ('dataset','file'):['Block_Path2Files_LogicalFileName'],
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
           ('dataset','release'):['Block_Path2AppVersion_Version'],
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
           ('dataset','run'):['Block_Path2ProcessedDataset_ID','ProcessedDataset_ID2Runs_RunNumber'],
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
           ('dataset','lumi'):['Block_Path2ProcessedDataset_ID','ProcessedDataset_ID2LumiSection_LumiSectionNumber'],
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
           ('dataset','site'):['Block_Path2StorageElement_SEName'],
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
           ('dataset','primds'):['Block_Path2PrimaryDataset_Name'],
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
           ('dataset','procds'):['Block_Path2ProcessedDataset_Name'],
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
           ('dataset','tier'):['Block_Path2ProcessedDataset_ID','ProcessedDataset_ID2DataTier_Name'],
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

   def getCMSNames(self):
       try:
           if not self.cmsNames:
              self.cmsNames=DDUtil.getCMSNames()
           else:
              lastTime=self.cmsNames['time']
              if (time.time()-lastTime)>(24*60*60): # more then a day
                 self.cmsNames=DDUtil.getCMSNames()
       except:
            self.cmsName=[]
            pass
       return self.cmsNames

   def parser(self,input,backEnd="oracle",sortName='CreationDate',sortOrder='desc',case='on'):
       if not DDUtil.validator(input):
          raise "Input '%s' does not contain equal number of open/closed brackets"%input
       words=self.parseInput(self.preParseInput(input),backEnd,sortName,sortOrder,case)
       return words

   def preParseCMSNames(self,input):
#       print "preParseCMSNames, input=",input
       if input.find("site ")==-1: return input
       cmsNames=self.getCMSNames()
       swapedDict=DDUtil.swapDict(cmsNames)
       iList=input.split()
       for idx in xrange(2,len(iList)):
           item=iList[idx].replace("*","")
           for i in [0,1,2,3]:
               t=re.search("T%s_"%i,item)
               kw=iList[idx-2]
               op=iList[idx-1]
               if t and kw.lower()=="site":
                  i1=' '.join(iList[:idx-2])
                  # look-up sites out of cmsNames
                  i2=""
                  for name in cmsNames.values():
                      if type(name) is types.StringType and name.find(item)!=-1:
                         if not i2:
                            i2+=" site %s %s "%(op,swapedDict[name])
                         else:
                            i2+=" or site %s %s "%(op,swapedDict[name])
                  input=i1+i2
                  if len(iList)>idx:
                     input+=' '.join(iList[idx+1:])
                     return self.preParseCMSNames(input)
#       print "preParseCMSNames, output=",input
       return input

   def preParseInput(self,input):
       if len(input.split())==1 and not re.match("dataset=",input):
          input="find dataset where dataset like %s"%input
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
       input = ' '.join(isplit)
       input = input.replace(" path "," dataset ")
       input = self.preParseCMSNames(input)
       return input
    
   def parseInput(self,input,backEnd,sortName,sortOrder,case):
       _input="%s"%input
       idx=input.lower().find('where')
       if idx!=-1:
          what=input[:idx]
          input   = input[idx+len('where')+1:]
          selKey = what.lower().replace('find','').strip()
       else:
          selKey = "dataset"
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
       # check if user made multiple select, if so, check where clause for provided operators
       if (not self.colName.keys().count(selKey) and (selKey.find(",")!=-1 or selKey.find("total")!=-1)) or backEnd.lower()=="mysql":
          if self.verbose:
             if backEnd.lower()=="mysql":
                print "\n+++ Found MySQL backend, no INTERSECT, will do JOIN queires\n",_input
             else:
                print "\n+++ Found multiple selection, stop parsing\n",_input
          if input.find(" = ")==-1 and selKey.find("total")==-1 and selKey.find(",")!=-1:
             msg ="In order to query multiple fields you MUST provide at least one equal constrain\n"
             msg+="Example: find file,run where dataset=/a/b/c\n"
             raise msg
          sList=[]
          fDict={}
          for key in selKey.split(","):
              key=key.strip()
              # look for functions
              funcFound=DDUtil.findKeyInAList(self.functions,key)
              if funcFound:
                 sKey=key.replace("(","").replace(")","").replace(funcFound,"").strip()
                 if not self.colName.keys().count(sKey):
                    raise "Fail to parse select expression, invalid key='%s'"%sKey
                 tabName=self.tableName[sKey]
                 colName=self.colName[sKey]
                 if funcFound=="total":
                    if colName.lower().find("numberof")!=-1 or colName.lower().find("size")!=-1:
                       fDict["%s.%s"%(tabName,colName)]="sum"
                    else:
                       fDict["%s.%s"%(tabName,colName)]="count"
                 else:
                    fDict["%s.%s"%(tabName,colName)]=funcFound
                 key=sKey
              if not self.colName.keys().count(key):
                 raise "Fail to parse select expression, invalid key='%s'"%key
              sKey="%s.%s"%(self.tableName[key],self.colName[key])
              sList.append(sKey)

          toSelect=','.join(sList)
          for val in pDict.values():
              key,op,rval=val.split()
              if not self.colName.keys().count(key):
                 raise "Fail to parse where clause expression, invalid key='%s'"%key
              input=input.replace(key,"%s.%s"%(self.tableName[key],self.colName[key]))
              _select="%s.%s"%(self.tableName[key],self.colName[key])
              if not sList.count(_select):
                 sList.append(_select)
          toJoin=','.join(sList)
          if self.colName.keys().count(sortName):
             sortName="%s.%s"%(self.tableName[sortName],self.colName[sortName])
          else:
             sortName=sList[0]
          fCall="self.makeJoinQuery(toSelect='%s',toJoin='%s',wClause='%s',sortName='%s',sortOrder='%s',case='%s',funcDict=%s)"%(toSelect,toJoin,input,sortName,sortOrder,case,fDict)
          if self.verbose:
             print fCall
          return fCall
       # proceed with parsing
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
       _fDict= {}
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
                    colName=self.colName[selKey]
                    if funcFound=="total":
                       if colName.lower().find("numberof")!=-1 or colName.lower().find("size")!=-1:
                          _fDict[colName]="sum"
                       else:
                          _fDict[colName]="count"
                    else:
                       _fDict[colName]=funcFound
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
                    _call+=",funcDict=%s"%_fDict
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
    aSearch.parser("find dataset where dataset like *Online*")
    aSearch.parser("(dataset=/a/b/c and block=123) or run >= 12345 dataset =bla or (dataset= /c/d/e or run=123)")
    aSearch.parser("find primds where (dataset like *Online* or dataset not like *RelVal* ) and release> CMSSW_1_7 ")
    try:
        aSearch.parser("find file,run where dataset like *Online*")
    except:
        traceback.print_exc()
        pass
    aSearch.parser("find file,run where dataset=/CSA07AllEvents/CMSSW_1_6_7-CSA07-Tier0-AOD-A2-Gumbo/AODSIM and run=123")
    aSearch.parser("find file,total(run) where dataset=/CSA07AllEvents/CMSSW_1_6_7-CSA07-Tier0-AOD-A2-Gumbo/AODSIM and run=123")
    aSearch.parser("find total(run) where (dataset like *bla* and block>=123) or run=12345")
    print "\n\n### site = T2_UK ===>",aSearch.preParseCMSNames("site = T2_UK")
    aSearch.parser("run=12345 and site like T2_UK*")
#    aSearch.parser("find run where (dataset like *bla* and block>=123) or run=12345")
#    aSearch.parser("run=12345")
#    aSearch.parser("run not like 12345")
#    aSearch.parser("run no like 12345")
#    aSearch.parser("run 12345")
