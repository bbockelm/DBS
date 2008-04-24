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
import sys, os, string, logging, types, time, traceback, new, re
import DDUtil

def constrainDict():
    """Dictionary of constrain operators with their weight as values"""
    cDict = {
       '>=':1,
       '>':1,
       '<=':1,
       '<':1,
       '!=':2,
       '=':2,
       'not_like':0.5,
       'not like':0.5,
       'like':0.5,
       'in':1,
       'between':1,
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
       self.yyyymmdd=re.compile('^\d{8}$')
       # mapping between keyword-names and DB views
       self.dbView ={
           'dataset'     :'datasetsummary',
           'block'       :'',
           'file'        :'filesummary',
           'release'     :'releasesummary',
           'run'         :'runsummary',
           'lumi'        :'',
           'site'        :'sitesummary',
           'primds'      :'primsummary',
           'procds'      :'procsummary',
           'tier'        :'tiersummary',
           'ads'         :'',
           'group'       :'',
       }
       # association between keyword-names and human readble meaning
       self.longName={
           'dataset'     :'processed dataset',
           'block'       :'block',
           'file'        :'logical file name',
           'release'     :'software release',
           'run'         :'run',
           'lumi'        :'luminosity section',
           'site'        :'storage element',
           'primds'      :'primary dataset',
           'procds'      :'processed dataset',
           'tier'        :'data tier',
           'ads'         :'analisis dataset',
           'group'       :'physics group name',
           # additions to above keywords
           'createdate'  :'creation date',
           'modifydate'  :'last modification date',
           'createby'    :'creator name',
           'modifyby'    :'person name of last modified entity',
           'name'        :'name of entity',
           'path'        :'path of entity',
           'number'      :'number of entity',
           'version'     :'version of entity',
           'numevents'   :'number of events',
           'numlumi'     :'number of lumi sections',
           'starttime'   :'start time of entity',
           'endtime'     :'end time of entity',
           'size'        :'size of entity',
           'type'        :'type of entity',
           'convener'    :'convener name',
       }
       # user help
       self.tooltip={
           'dataset'     :'fully qualified dataset path or pattern, e.g. /DaqTest-A/Online/RAW.',
           'block'       :'block name, including block UID',
           'file'        :'logical file name name or pattern',
           'release'     :'software release, e.g. CMSSW_1_7_1',
           'run'         :'run number or run range, e.g. 34850-34860',
           'lumi'        :'luminosity section number or range of lumi section numbers',
           'site'        :'storage element name or pattern, e.g. srm.cern.ch',
           'primds'      :'primary dataset name or pattern, e.g. DaqTest-A',
           'procds'      :'processed dataset name or pattern, e.g. Online.',
           'tier'        :'data tier name or pattern, e.g. RAW',
           'ads'         :'analysis dataset name, e.g. MyTopAnalysis',
           'group'       :'physics group name, e.g. Higgs',
       }
       # associate between keyword-names and DBS tables
       self.tableName={
           'dataset'     :'Block',
           'block'       :'Block',
           'file'        :'Files',
           'release'     :'AppVersion',
           'run'         :'Runs',
           'lumi'        :'LumiSection',
           'site'        :'StorageElement',
           'primds'      :'PrimaryDataset',
           'procds'      :'ProcessedDataset',
           'tier'        :'DataTier',
           'ads'         :'AnalisisDataset',
           'group'       :'PhysicsGroup',
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
           'group':1,
           'ads':1,
       }
       self.colName={
           'dataset'     :'Path',
           'block'       :'Name',
           'file'        :'LogicalFileName',
           'release'     :'Version',
           'run'         :'RunNumber',
           'lumi'        :'LumiSectionNumber',
           'site'        :'SEName',
           'primds'      :'Name',
           'procds'      :'Name',
           'tier'        :'Name',
           'ads'         :'Name',
           'group'       :'PhysicsGroupName',
       }
       # mapping of member functions to real DBS col names, if dict is provided in a value
       # its keys specify the key for DBS table to whcih member function is applicable
       self.colMember = {
           'name'        :{'dataset':'Name','primds':'Name','procds':'Name','ads':'Name','block':'Name','file':'LogicalFileName','tier':'Name','group':'PhysicsGroupName'},
           'number'      :{'run':'RunNumber','lumi':'LumiSectionNumber'},
           'createdate'  :'CreationDate',
           'modifydate'  :'LastModificationDate',
           'createby'    :'CreatedBy',
           'modifyby'    :'LastModifiedBy',
           'size'        :{'file':'FileSize','block':'BlockSize'},
           'numevents'   :{'file':'NumberOfEvents','run':'NumberOfEvents'},
           'starttime'   :{'run':'StartOfRun','lumi':'LumiStartTime'},
           'endtime'     :{'run':'EndOfRun','lumi':'LumiEndTime'},
           'numlumi'     :{'lumi':'NumberOfLumiSections'},
           'type'        :{'file':'FileType.Type','primds':'PrimDSType.Type','ads':'AnalysisDSType.Type'},
           'convener'    :'PhysicsGroupConvener',
       }
       self.colFunc = {
           'latestrelease' :['file'],
           'parentrelease' :['file'],
           'childrelease'  :['file'],
           'intluminosity' :['lumi','run','procds','dataset'],
           'dataquality'   :['run','lumi'],
           'numruns'       :['ads','procds','dataset'],
           'numfiles'      :['ads','procds','dataset','block','run'],
           'numblocks'     :['ads','procds','dataset','run'],
           'findevent'     :['lumi'],
           'today'         :['primds','procds','ads','block','file','release'],
       }
       # mapping from keyword to keyword pairs and DBS DB paths
       self.dbs_map={
           # to dataset
           ('dataset','dataset'):['Block.Path2Block.Path'],
           ('block','dataset'):['Block.Name2Block.Path'],
           ('file','dataset'):['Files.LogicalFileName2Block.Path'],
           ('release','dataset'):['AppVersion.Version2ProcessedDataset.ID','ProcessedDataset.ID2Block.Path'],
           ('run','dataset'):['Runs.RunNumber2ProcessedDataset.ID','ProcessedDataset.ID2Block.Path'],
           ('lumi','dataset'):['LumiSection.LumiSectionNumber2Runs.ID','Runs.ID2ProcessedDataset.ID2Block.Path'],
           ('site','dataset'):['StorageElement.SEName2Block.Path'],
           ('primds','dataset'):['PrimaryDataset.Name2Block.Path'],
           ('procds','dataset'):['ProcessedDataset.Name2Block.Path'],
           ('tier','dataset'):['DataTier.Name2ProcessedDataset.ID','ProcessedDataset.ID2Block.Path'],
           ('ads','dataset'):['AnalysisDataset.Name2Block.Path'],
           ('group','dataset'):['PhysicsGroup.PhysicsGroupName2Block.Path'],
           # to block
           ('dataset','block'):['Block.Path2Block.Name'],
           ('block','block'):['Block.Name2Block.Name'],
           ('file','block'):['Files.LogicalFileName2Block.Name'],
           ('release','block'):['AppVersion.Version2ProcessedDataset.ID','ProcessedDataset.ID2Block.Name'],
           ('run','block'):['Runs.RunNumber2ProcessedDataset.ID','ProcessedDataset.ID2Block.Name'],
           ('lumi','block'):['LumiSection.LumiSectionNumber2Runs.ID','Runs.ID2ProcessedDataset.ID2Block.Name'],
           ('site','block'):['StorageElement.SEName2Block.Name'],
           ('primds','block'):['PrimaryDataset.Name2Block.Name'],
           ('procds','block'):['ProcessedDataset.Name2Block.Name'],
           ('tier','block'):['DataTier.Name2ProcessedDataset.ID','ProcessedDataset.ID2Block.Name'],
           ('ads','block'):['AnalysisDataset.Name2Block.Name'],
           ('group','block'):['PhysicsGroup.PhysicsGroupName2Block.Name'],
           # to file
           ('dataset','file'):['Block.Path2Files.LogicalFileName'],
           ('block','file'):['Block.Name2Files.LogicalFileName'],
           ('file','file'):['Files.LogicalFileName2Files.LogicalFileName'],
           ('release','file'):['AppVersion.Version2ProcessedDataset.ID','ProcessedDataset.ID2Files.LogicalFileName'],
           ('run','file'):['Runs.RunNumber2Files.LogicalFileName'],
           ('lumi','file'):['LumiSection.LumiSectionNumber2Files.LogicalFileName'],
           ('site','file'):['StorageElement.SEName2Files.LogicalFileName'],
           ('primds','file'):['PrimaryDatset.Name2Files.LogicalFileName'],
           ('procds','file'):['ProcessedDataset.Name2Files.LogicalFileName'],
           ('tier','file'):['DataTier.Name2Files.LogicalFileName'],
           ('ads','file'):['AnalysisDataset.Name2Files.LogicalFileName'],
           ('group','file'):['PhysicsGroup.PhysicsGroupName2Files.LogicalFileName'],
           # to release
           ('dataset','release'):['Block.Path2AppVersion.Version'],
           ('block','release'):['Block.Name2AppVersion.Version'],
           ('file','release'):['Files.LogicalFileName2AppVersion.Version'],
           ('release','release'):['AppVersion.Version2AppVersion.Version'],
           ('run','release'):['Runs.RunNumber2ProcessedDataset.ID','ProcessedDataset.ID2AppVersion.Version'],
           ('lumi','release'):['LumiSection.LumiSectionNumber2ProcessedDataset.ID','ProcessedDataset.ID2AppVersion.Version'],
           ('site','release'):['StorageElement.SEName2AppVersion.Version'],
           ('primds','release'):['PrimaryDataset.Name2AppVersion.Version'],
           ('procds','release'):['ProcessedDataset.Name2AppVersion.Version'],
           ('tier','release'):['DataTier.Name2AppVersion.Version'],
           ('ads','release'):['AnalysisDataset.Name2AppVersion.Version'],
           ('group','release'):['PhysicsGroup.PhysicsGroupName2AppVersion.Version'],
           # to run
           ('dataset','run'):['Block.Path2ProcessedDataset.ID','ProcessedDataset.ID2Runs.RunNumber'],
           ('block','run'):['Block.Name2ProcessedDataset.ID','ProcessedDataset.ID2Runs.RunNumber'],
           ('file','run'):['Files.LogicalFileName2Runs.RunNumber'],
           ('release','run'):['AppVersion.Version2ProcessedDataset.ID','ProcessedDataset.ID2Runs.RunNumber'],
           ('run','run'):['Runs.RunNumber2Runs.RunNumber'],
           ('lumi','run'):['LumiSection.LumiSectionNumber2Runs.RunNumber'],
           ('site','run'):['StorageElement.SEName2ProcessedDataset.ID','ProcessedDataset.ID2Runs.RunNumber'],
           ('primds','run'):['PrimaryDataset.Name2ProcessedDataset.ID','ProcessedDataset.ID2Runs.RunNumber'],
           ('procds','run'):['ProcessedDataset.Name2Runs.RunNumber'],
           ('tier','run'):['DataTier.Name2ProcessedDataset.ID','ProcessedDataset.ID2Runs.RunNumber'],
           ('ads','run'):['AnalysisDataset.Name2Runs.RunNumber'],
           ('group','run'):['PhysicsGroup.PhysicsGroupName2Runs.RunNumber'],
           # to lumi
           ('dataset','lumi'):['Block.Path2ProcessedDataset.ID','ProcessedDataset.ID2Runs.ID','Runs.ID2LumiSection.LumiSectionNumber'],
           ('block','lumi'):['Block.Name2ProcessedDataset.ID','ProcessedDataset.ID2LumiSection.LumiSectionNumber'],
           ('file','lumi'):['Files.LogicalFileName2Runs.ID','Runs.ID2LumiSection.LumiSectionNumber'],
           ('release','lumi'):['AppVersion.Version2ProcessedDataset.ID','ProcessedDataset.ID2Runs.ID','Runs.ID2LumiSection.LumiSectionNumber'],
           ('run','lumi'):['Runs.RunNumber2LumiSection.LumiSectionNumber'],
           ('lumi','lumi'):['LumiSection.LumiSectionNumber2LumiSection.LumiSectionNumber'],
           ('site','lumi'):['StorageElement.SEName2ProcessedDataset.ID','ProcessedDataset.ID2LumiSection.LumiSectionNumber'],
           ('primds','lumi'):['PrimaryDatset.Name2LumiSection.LumiSectionNumber'],
           ('procds','lumi'):['ProcessedDataset.Name2LumiSection.LumiSectionNumber'],
           ('tier','lumi'):['DataTier.Name2ProcessedDataset.ID','ProcessedDataset.ID2LumiSection.LumiSectionNumber'],
           ('ads','lumi'):['AnalysisDataset.Name2LumiSection.LumiSectionNumber'],
           ('group','lumi'):['PhysicsGroup.PhysicsGroupName2LumiSection.LumiSectionNumber'],
           # to site
           ('dataset','site'):['Block.Path2StorageElement.SEName'],
           ('block','site'):['Block.Name2StorageElement.SEName'],
           ('file','site'):['Files.LogicalFileName2StorageElement.SEName'],
           ('release','site'):['AppVersion.Version2StorageElement.SEName'],
           ('run','site'):['Runs.RunNumber2ProcessedDataset.ID','ProcessedDataset.ID2StorageElement.SEName'],
           ('lumi','site'):['LumiSection.LumiSectionNumber2ProcessedDataset.ID','ProcessedDataset.ID2StorageElement.SEName'],
           ('site','site'):['StorageElement.SEName2StorageElement.SEName'],
           ('primds','site'):['PrimaryDataset.Name2StorageElement.SEName'],
           ('procds','site'):['ProcessedDataset.Name2StorageElement.SEName'],
           ('tier','site'):['DataTier.Name2ProcessedDataset.ID','ProcessedDataset.ID2StorageElement.SEName'],
           ('ads','site'):['AnalysisDataset.Name2StorageElement.SEName'],
           ('group','site'):['PhysicsGroup.PhysicsGroupName2StorageElement.SEName'],
           # to primds
           ('dataset','primds'):['Block.Path2PrimaryDataset.Name'],
           ('block','primds'):['Block.Name2PrimaryDataset.Name'],
           ('file','primds'):['Files.LogicalFileName2PrimaryDataset.Name'],
           ('release','primds'):['AppVersion.Version2PrimaryDataset.Name'],
           ('run','primds'):['Runs.RunNumber2PrimaryDataset.Name'],
           ('lumi','primds'):['LumiSection.LumiSectionNumber2PrimaryDataset.Name'],
           ('site','primds'):['StorageElement.SEName2PrimaryDataset.Name'],
           ('primds','primds'):['PrimaryDataset.Name2PrimaryDataset.Name'],
           ('procds','primds'):['ProcessedDataset.Name2PrimaryDataset.Name'],
           ('tier','primds'):['DataTier.Name2PrimaryDataset.Name'],
           ('ads','primds'):['AnalysisDataset.Name2PrimaryDataset.Name'],
           ('group','primds'):['PhysicsGroup.PhysicsGroupName2PrimaryDataset.Name'],
           # to procds
           ('dataset','procds'):['Block.Path2ProcessedDataset.Name'],
           ('block','procds'):['Block.Name2ProcessedDataset.Name'],
           ('file','procds'):['Files.LogicalFileName2ProcessedDataset.Name'],
           ('release','procds'):['AppVersion.Version2ProcessedDataset.Name'],
           ('run','procds'):['Runs.RunNumber2ProcessedDataset.Name'],
           ('lumi','procds'):['LumiSection.LumiSectionNumber2ProcessedDataset.Name'],
           ('site','procds'):['StorageElement.SEName2ProcessedDataset.Name'],
           ('primds','procds'):['PrimaryDataset.Name2ProcessedDataset.Name'],
           ('procds','procds'):['ProcessedDataset.Name2ProcessedDataset.Name'],
           ('tier','procds'):['DataTier.Name2ProcessedDataset.Name'],
           ('ads','procds'):['AnalysisDataset.Name2ProcessedDataset.Name'],
           ('group','procds'):['PhysicsGroup.PhysicsGroupName2ProcessedDataset.Name'],
           # to tier
           ('dataset','tier'):['Block.Path2ProcessedDataset.ID','ProcessedDataset.ID2DataTier.Name'],
           ('block','tier'):['Block.Name2ProcessedDataset.ID','ProcessedDataset.ID2DataTier.Name'],
           ('file','tier'):['Files.LogicalFileName2DataTier.Name'],
           ('release','tier'):['AppVersion.Version2DataTier.Name'],
           ('run','tier'):['Runs.RunNumber2ProcessedDataset.ID','ProcessedDataset.ID2DataTier.Name'],
           ('lumi','tier'):['LumiSection.LumiSectionNumber2ProcessedDataset.ID','ProcessedDataset.ID2DataTier.Name'],
           ('site','tier'):['StorageElement.SEName2ProcessedDataset.ID','ProcessedDataset.ID2DataTier.Name'],
           ('primds','tier'):['PrimaryDataset.Name2DataTier.Name'],
           ('procds','tier'):['ProcessedDataset.Name2DataTier.Name'],
           ('tier','tier'):['DataTier.Name2DataTier.Name'],
           ('ads','tier'):['AnalysisDataset.Name2DataTier.Name'],
           ('group','tier'):['PhysicsGroup.PhysicsGroupName2DataTier.Name'],
           # to ads
           ('dataset','ads'):['Block.Path2AnalysisDataset.Name'],
           ('block','ads'):['Block.Name2AnalysisDataset.Name'],
           ('file','ads'):['Files.LogicalFileName2AnalysisDataset.Name'],
           ('release','ads'):['AppVersion.Version2AnalysisDataset.Name'],
           ('run','ads'):['Runs.RunNumber2ProcessedDataset.ID','ProcessedDataset.ID2AnalysisDataset.Name'],
           ('lumi','ads'):['LumiSection.LumiSectionNumber2AnalysisDataset.Name'],
           ('site','ads'):['StorageElement.SEName2ProcessedDataset.ID','ProcessedDataset.ID2AnalysisDataset.Name'],
           ('primds','ads'):['PrimaryDataset.Name2AnalysisDataset.Name'],
           ('procds','ads'):['ProcessedDataset.Name2AnalysisDataset.Name'],
           ('ads','ads'):['AnalysisDataset.Name2AnalysisDataset.Name'],
           ('group','ads'):['PhysicsGroup.PhysicsGroupName2AnalysisDataset.Name'],
           # to group
           ('dataset','group'):['Block.Path2PhysicsGroup.PhysicsGroupName'],
           ('block','group'):['Block.Name2PhysicsGroup.PhysicsGroupName'],
           ('file','group'):['Files.LogicalFileName2PhysicsGroup.PhysicsGroupName'],
           ('release','group'):['AppVersion.Version2PhysicsGroup.PhysicsGroupName'],
           ('run','group'):['Runs.RunNumber2ProcessedDataset.ID','ProcessedDataset.ID2PhysicsGroup.PhysicsGroupName'],
           ('lumi','group'):['LumiSection.LumiSectionNumber2PhysicsGroup.PhysicsGroupName'],
           ('site','group'):['StorageElement.SEName2ProcessedDataset.ID','ProcessedDataset.ID2PhysicsGroup.PhysicsGroupName'],
           ('primds','group'):['PrimaryDataset.Name2PhysicsGroup.PhysicsGroupName'],
           ('procds','group'):['ProcessedDataset.Name2PhysicsGroup.PhysicsGroupName'],
           ('group','group'):['PhysicsGroup.PhysicsGroupName2PhysicsGroup.PhysicsGroupName'],
           ('group','group'):['PhysicsGroup.PhysicsGroupName2PhysicsGroup.PhysicsGroupName'],
       }
       self.keywords=list(self.colName.keys())
       keywords=[]
       for m in self.colMember.keys():
           if type(self.colMember[m]) is types.DictType:
              for k in self.colMember[m].keys():
                  newKey = "%s.%s"%(k,m)
                  keywords.append(newKey)
           else:
              for key in self.keywords:
                  newKey = "%s.%s"%(key,m)
                  keywords.append(newKey)
       self.keywords+=keywords
       self.operators=constrainList()
       # list of supported constrain operator, order is matter, since we walk through
       # the list to find a match. Example, if pattern is '<=' and order is <,<= we will
       # find first match to <, which is wrong, while if order is <=,< first match will be correct.
       self.oList=['and','or','minus','(',')']
       self.kList=self.dbs_map.keys()

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
       if self.verbose:
          print "-"*len(input)
          print input
          print "-"*len(input)
       if not DDUtil.validator(input):
          raise "Input '%s' does not contain equal number of open/closed brackets"%input
       if input.find("'")!=-1 or input.find("\"")!=-1:
          raise "Quotes are not allowed"
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
                            i2+=" site = %s "%swapedDict[name]
                         else:
                            i2+=" or site = %s "%swapedDict[name]
                  input=i1+i2
                  if len(iList)>idx:
                     input+=' '.join(iList[idx+1:])
                     return self.preParseCMSNames(input)
#       print "preParseCMSNames, output=",input
       return input

   def formatErrorInInput(self,input,where,mmm):
       marker="-"*input.find(where)
       marker+="^"
       msg ="\nFail to parse your conditions:\n\n"
       msg+=input+"\n"
       msg+=marker+"\n"
       msg+="\nERROR: "+mmm+"\n"
       msg+="\nPlease consult Query Language syntax in HELP section on Data Discovery page"
       return msg

   def checkConditions(self,input,conditions):
#       print conditions
       for i in xrange(0,len(conditions)):
           if ['and','or','(',')'].count(conditions[i]):
              return self.checkConditions(input,conditions[i+1:])
           if len(conditions)==1 or len(conditions)==2:
              msg="No valid condition found, expect <key> <op> <value>"
              raise self.formatErrorInInput(input,conditions[i],msg)
           key = conditions[i]
           if not self.keywords.count(key): 
              msg="Unknown keyword '%s'"%key
              raise self.formatErrorInInput(input,conditions[i],msg)
           op  = conditions[i+1]
           if op=="not":
              op+=" %s"%conditions[i+2]
              if not self.operators.count(op): 
                 msg="Unknown operator '%s'"%op
                 raise self.formatErrorInInput(input,conditions[i+1],msg)
              val= conditions[i+3]
              if key.find("createdate")!=-1 and not self.yyyymmdd.match(val):
                 msg="Creation date should be supplied in a form of YYYYMMDD"
                 raise self.formatErrorInInput(input,conditions[i+3],msg)
              return self.checkConditions(input,conditions[i+4:])
           else:
              if not self.operators.count(op): 
                 msg="Unknown operator '%s'"%op
                 raise self.formatErrorInInput(input,conditions[i+1],msg)
              val = conditions[i+2]
              if key.find("createdate")!=-1 and not self.yyyymmdd.match(val):
                 msg="Creation date should be supplied in a form of YYYYMMDD"
                 raise self.formatErrorInInput(input,conditions[i+2],msg)
              return self.checkConditions(input,conditions[i+3:])

   def preParseInput(self,input):
       if len(input.split())==1 and input.find("=")==-1 and input.find(">")==-1 and input.find("<")==-1:
          if input.find("*")!=-1:
             input="find dataset where dataset like %s"%input
          else:
             input="find dataset where dataset = %s"%input
       input=input.replace(")"," ) ").replace("("," ( ")
        
       isplit=input.split()
       # wrap operator ['<=','>=','!=','=','<','>'] with spaces for better parsing
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
                            elif op=="=" and item[idx-1]=='!':
                               continue
                            else:
                               j1=item.find(op)
                               j2=item.rfind(op)
                               if j1!=j2:
                                  item=item[:j1]+' '+item[j1:j2+len(op)]+' '+item[j2+len(op):]
                               else:
                                  item=item.replace(op," %s "%op)
                  isplit[i]=item
       except:
           traceback.print_exc()
           raise "prePraseInput: fail to parse your input='%s'"%input
       input = ' '.join(isplit)
       input = input.replace(" path "," dataset ")
       try:
           # check if used provided input with key=bla* or key=1-2 and 
           # replace '=' with appropriate synatx
           runrange= re.compile("^\d*-\d*")
           runlist = re.compile("^\d*,\d*")
           isplit  = input.split()
           for idx in xrange(0,len(isplit)):
               if isplit[idx].find("*")!=-1 and isplit[idx-1]=="=":
                  isplit[idx-1]="like"
               elif isplit[idx].find("*")!=-1 and isplit[idx-1]=="!=":
                  isplit[idx-1]="not like"
               elif runrange.search(isplit[idx]):
                  if isplit[idx-1]=="=":
                     isplit[idx-1]="between"
                  elif isplit[idx-1]=="!=":
                     isplit[idx-1]="not between"
                     raise "Operator != is not supported for ranges"
               elif runlist.search(isplit[idx]):
                  if isplit[idx-1]=="=":
                     isplit[idx-1]="in"
                  elif isplit[idx-1]=="!=":
                     isplit[idx-1]="not in"
                     raise "Operator != is not supported for list"
           input = ' '.join(isplit)
       except Exception, ex:
           raise ex

       # check user conditions
       input=input.strip()
       widx=input.find(" where ")
       if widx==-1:
          conditions=input.split()
       else:
          conditions=input[widx+len(" where "):].split()
       self.checkConditions(input,conditions)

       input = self.preParseCMSNames(input)
       return input
    
   def getTabCol(self,key):
       if key.find(".")!=-1:
          k,m = key.split(".")
          tab = self.tableName[k]
          if self.colMember.keys().count(m):
             col = self.colMember[m]
             if type(col) is types.DictType:
                col = col[k]
          elif self.colFunc.keys().count(m) and self.colFunc.values().count(k):
             col = "self.makeFunc(%s)"%m
          else:
             raise "ERROR: unknown attribute member '%s'"%key
       else:
          tab = self.tableName[key]
          col = self.colName[key]
       # exception, in case of type, I used full tab.col notation in colMember
       if col.find(".")!=-1:
          tab,col=col.split(".")
       return "%s.%s"%(tab,col)

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
       if (not self.keywords.count(selKey) and (selKey.find(",")!=-1 or selKey.find("total")!=-1)) or backEnd.lower()=="mysql":
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
          _toJoin="%s"%selKey
          for key in selKey.split(","):
              key=key.strip()
              # look for functions
              funcFound=DDUtil.findKeyInAList(self.functions,key)
              if funcFound:
                 sKey=key.replace("(","").replace(")","").replace(funcFound,"").strip()
                 if not self.keywords.count(sKey):
                    raise "Fail to parse select expression, invalid key='%s'"%sKey
                 _tabCol=self.getTabCol(sKey)
                 tabName,colName=_tabCol.split(".")
                 if funcFound=="total":
                    if colName.lower().find("numberof")!=-1 or colName.lower().find("size")!=-1:
                       fDict[_tabCol]="sum"
                    else:
                       fDict[_tabCol]="count"
                 else:
                    fDict[_tabCol]=funcFound
                 key=sKey
              if not self.keywords.count(key):
                 raise "Fail to parse select expression, invalid key='%s'"%key
              sKey=self.getTabCol(key)
              sList.append(sKey)

          toSelect=','.join(sList)
          for val in pDict.values():
              key,op,rval=val.split()
              if not self.keywords.count(key):
                 raise "Fail to parse where clause expression, invalid key='%s'"%key
              input=input.replace(key,self.getTabCol(key))
              _select=self.getTabCol(key)
              _toJoin+=",%s"%key
              if not sList.count(_select):
                 sList.append(_select)
          toJoin=','.join(sList)
          # resolve ambiguity, File,Lumi should go through runs
          _toJoinKeys=_toJoin.strip().split(",")
          if _toJoinKeys.count('file') and _toJoinKeys.count('lumi') and not _toJoinKeys.count('run'):
             toJoin+=",FileRunLumi.ID"

          if self.keywords.count(sortName):
             sortName=self.getTabCol(sortName)
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
           for co in self.operators:
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
       """
          Create a function call for DDQueryMaker. selKey is an input set of keys which we will
          look-up. The input is a list of conditions which will be applied to the query in a
          form of key:rval. Here rval will contain operator to be used, e.g. >200.
          Either look-up keys or conditions keys can be in form of a.b (table.col) or just
          keywords known to DD, e.g. dataset, file, run, etc.
       """
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
                    cName=self.getTabCol[selKey]
                    if funcFound=="total":
                       if cName.lower().find("numberof")!=-1 or cName.lower().find("size")!=-1:
                          _fDict[cName]="sum"
                       else:
                          _fDict[cName]="count"
                    else:
                       _fDict[cName]=funcFound
                 if selKey.find(".")!=-1:
                    sKey,keyOut = selKey.split(".")
                 else:
                    sKey=selKey
                 ################ 
                 # TODO: if f is in form a.b I need to check for functions
                 if f.find(".")!=-1:
                    fTabCol = self.getTabCol(f)
                    fTab,fCol = fTabCol.split(".")
                    fList = self.dbs_map[(fTab.lower(),sKey)]
                    firstEl= fList[0]
                    firstEl= '2'.join([fTabCol]+firstEl.split("2")[1:])
                    fList[0]=firstEl
                 else:
                    fList = self.dbs_map[(f,sKey)]
                 if selKey.find(".")!=-1:
                    sKey,keyOut = selKey.split(".")
                    lastEl= fList[-1]
                    lastEl= '2'.join(lastEl.split("2")[:-1]+[self.getTabCol(selKey)])
                    fList[-1]=lastEl
                 ################ 
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
                 raise "ERROR: unable to parse your input: '%s'\nparse it as '%s'\n### List of known keywords %s\n### List of supported operators %s"%(selKey,words,str(self.keywords),self.operators)
           else:
                 traceback.print_exc()
                 raise "Keyword '%s' does not contain separator \":\"."%w
       eString = ' '.join(_words)
       if self.verbose:
          print "\n+++ Translate iList:\n%s\n\n%s\n"%(input,eString)
       return eString

#
# main
#
if __name__ == "__main__":
    aSearch = DDRules(verbose=1)
    print aSearch.keywords
    aSearch.parser("find dataset where dataset like *Online*")
    aSearch.parser("find dataset.createby where dataset=QCD*")
    aSearch.parser("find dataset where dataset.createby>20080423")
    try:
        aSearch.parser("find dataset where dataset.createdate>200804238")
    except: pass
    aSearch.parser("find dataset.createby where dataset.createdate>20080423")
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
    aSearch.parser("run=12345 and dataset=Online*")
    aSearch.parser("run=12345-56789 and run=1,2,3 or dataset!=Online*")
    try:
        aSearch.parser("run!=12345-56789")
    except:
        traceback.print_exc()
        pass
    try:
        aSearch.parser("run=12345-56789 Online* Test")
    except:
        traceback.print_exc()
        pass
    try:
        aSearch.parser("run=12345-56789 and dataset Online* ")
    except:
        traceback.print_exc()
        pass
    try:
        aSearch.parser("run=12345-56789 and dataset ")
    except:
        traceback.print_exc()
        pass
    try:
        aSearch.parser("run=12345-56789 and dataset =")
    except:
        traceback.print_exc()
        pass
    try:
        aSearch.parser("run=12345-56789 and dataset == Online*")
    except:
        traceback.print_exc()
        pass
    aSearch.parser("site=T2*")
    aSearch.parser("*QCD*")
    try:
        aSearch.parser("site==T2*")
    except:
        traceback.print_exc()
        pass
