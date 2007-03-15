#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
CLI DBS Data discovery toolkit.
"""

# import system modules
import string, sys, time, types, popen2

# import DBS modules
import DDOptions
from   DDConfig  import *
from   DBSInst   import * # defines DBS instances and schema
from   DDUtil    import * # general utils

# import DLS modules
import dlsClient
import dlsApi

# DREW code, need to remove try block once it's ready
try:
    from QueryBuilder.Schema import Schema
    from QueryBuilder.QueryWriter import SqlQueryWriter, OracleQueryWriter
except:
    pass

class DDHelper(DDLogger): 
  """
      DDHelper class
  """
  def __init__(self,dbsInst=DBSGLOBAL,iface="sqlalchemy",verbose=0,html=0):
      """
         Constructor which takes two arguments DBS instance and verbosity level.
         It initialize internal logger with own name and pass verbosity level to it.
         @type  dbsInst: string 
         @param dbsInst: name of the DBS instance, e.g. MCGlobal/Writer (default)
         @type verbose: boolean or integer
         @param verbose: verbosity level
         @rtype : none
         @return: none
      """
      DDLogger.__init__(self,"DDHelper",verbose)
      self.iface       = string.lower(iface)
      self.dbsInstance = dbsInst
      self.dbsdls      = DBS_DLS_INST
      self.verbose     = verbose
      self.html        = html
      self.datasetPath = "*"# default path to entire content of DBS
      # cache
      self.blockDict   = {} #  {'dataset': {'fileBlock': [LFNs]}}
      try:
         self.dbManager      = DBManager(self.iface,self.verbose)
      except:
         if self.verbose:
            print "WARNING! some of the functionality will be disable due to missing authentication"
            printExcept()
         pass
      self.dbsTime     = 0
      self.dlsTime     = 0
      self.api         = "" # dbsCgiApi.DbsCgiApi(url,{'instance':dbsInst})
      self.dbsApi      = {} #  {'dbsInst': dbsCgiApi.DbsCgiApi(url,dbsInst) }
      self.dbsDLS      = {} #  {'dbsInst': dlsClient.getDlsApi(dlsType,endpoint) }
      self.dlsType     = "" # DLS type
      self.endpoint    = "" # DLS endpoint
      self.dls_iface   = "" # used only when we query DLS for sites/blockNames, LFC iface's
      self.voms_timer  = 0  # timer to check grid credentials
      self.dlsInst     = {} # {(type,epoint): dlsClient.getDlsApi(type,epoint)}
      # set DBS/DLS 
      self.setDBSDLS(dbsInst)
      self.quiet       = 0
      self.ddConfig  = DDConfig()

  def col(self,table,col):
      return self.dbManager.col(self.dbsInstance,table,col)
  def setQuiet(self):
      self.quiet=1

  def rssMaker(self,dbsInst):
#      ddConfig  = DDConfig()
      url = self.ddConfig.url()
      self.setDBSDLS(dbsInst)
      #aList = self.listApplications()
      aList = self.getApplications()
      for app in aList:
          app_link="""%s/getData?dbsInst=%s&amp;site=All&amp;app=%s&amp;primD=*&amp;tier=All&amp;proc=*&amp;ajax=0"""%(url,dbsInst,app)
          appPath=string.replace(app,"/","___")
          gmt=time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime())
          page="""<?xml version="1.0" ?>
<rss version="2.0">
<channel>
<title>%s</title>
<description>%s</description>
<link>%s</link>
<language>en-us</language>
<pubDate>%s</pubDate>
<lastBuildDate>%s</lastBuildDate>
<docs>http://en.wikipedia.org/wiki/RSS_(file_format)</docs>
<generator>DBS discovery page</generator>
<managingEditor>vk@mail.lns.cornell.edu</managingEditor>
<webMaster>vk@mail.lns.cornell.edu</webMaster>
"""%(app,'app description',app_link,gmt,gmt)
          pList = self.listDatasetsFromApp(app)
          for datasetName in pList:
              page+="<item>"
              empty,prim,proc,tier = string.split(datasetName,"/")
              if not os.path.isdir('rss/%s/%s/%s'%(dbsInst,appPath,prim)):
                 os.makedirs(os.path.join(os.getcwd(),'rss/%s/%s/%s'%(dbsInst,appPath,prim)))
              evt=self.numberOfEvents(datasetName)
              link="""%s/getData?dbsInst=%s&amp;site=All&amp;app=*&amp;primD=*&amp;tier=All&amp;proc=%s&amp;ajax=0"""%(url,dbsInst,datasetName)
              page+="""
<title>%s</title>
<description>Number of events: %s</description>
<link>%s</link>
<pubDate>%s</pubDate>
<guid>%s</guid>
</item>
"""%(datasetName,evt,link,gmt,link)
          if  len(pList):
              page+="</channel></rss>"
              fName=os.path.join(os.getcwd(),'rss/%s/%s/%s/rss.xml'%(dbsInst,appPath,prim))
              f=open(fName,'w')
              f.write(page)
              f.close()

  def initJSDict(self,dbsInst="all"):
      """
         Form dictionary for JavaScript used in presentation layer.
         @type  userMode: boolean 
         @param userMode: define which DBS instance(s) to use, in userMode we only use DBS global,
         for experts others instances has been initialized.
         @rtype : dictionary
         @return: { DBSInst: { dbs:appDict={ app:primDict={ primD:tierDict={ tier:null } } }, } }
      """
      if dbsInst!="all":
         if not self.dbsdls.has_key(dbsInst):
            print "List of available dbs instances:\n"
            printListElements(self.dbsdls.keys())
            msg="No dbs instance '%s' found"%dbsInst
            raise msg
         else:
            name=string.split(dbsInst,"/")[0]
            fileName='dbsDict.%s.tmp'%name
      else:
         fileName='dbsDict.global.tmp'
      file=open(fileName,'w')
      # if we're in user mode, we only know about DBSGLOBAL
      # if we're in expert mode, load all DBS instances
      init=time.time()
      if dbsInst=="all":
         dbsList=self.dbsdls.keys()
         dbsList.sort()
         try:
            dbsList.remove(DBSGLOBAL)
         except:
            pass
         dbsList=[DBSGLOBAL]+dbsList
      else:
         dbsList=[dbsInst]
      s = "\n"
      s+= "{l:[ "
      for dbs in dbsList:
          s+='\"%s\",'%dbs
      s=s[:-1]+"],\n"
      file.write(s)
      file.flush()
      s=""
      countIns=0
      for dbs in dbsList:
          if not countIns:
             s+="n:{\"%s\":"%dbs
          else:
             s+=",\"%s\":"%dbs
          countIns+=1
          self.setDBSDLS(dbs)
          appDict = self.getDatasetsFromApplications()
          appList = appDict.keys()
          appList.sort()
          appList.reverse()
          s+= "{l:[ "
          for app in appList:
              s+='\"%s\",'%app
          s=s[:-1]+"],\n"
          file.write(s)
          file.flush()
          s=""
          countApp=0
          for app in appList:
              if not countApp:
                 s+="n:{\"%s\":"%app
              else:
                 s+=",\"%s\":"%app
              countApp+=1
              pList = appDict[app]
              pList.sort()
              pList.reverse()
              s+= "{l:[ "
              oldPrimD=""
#              for primD,tier,proc in pList:
              for path in pList:
                  empty,primD,proc,tier = string.split( path, "/" )
                  if oldPrimD!=primD:
                     s+='\"%s\",'%primD
                     oldPrimD=primD
              s=s[:-1]+"],\n"
              file.write(s)
              file.flush()
              s=""
              count = 0
              oldPrimD = ""
              oldTier  = ""
#              for primD,tier,proc in pList:
              for path in pList:
                  empty,primD,proc,tier = string.split( path, "/" )
#                  s+="\n# %s %s, %s, %s\n"%(app,primD,tier,proc)
                  if primD!=oldPrimD:
                     oldPrimD=primD
                     if count: 
                        s=s[:-1]+"],n:null}\n"
                        oldTier=""
                     if not count:
                        s+="n:{\"%s\":{l:[\"All\","%primD
                     else:
                        s+=",\"%s\":{l:[\"All\","%primD
                  count+=1
                  if tier!=oldTier:
                     s+="\"%s\","%tier
                     oldTier=tier
              s=s[:-1]+"],n:null}}}\n"
              oldTier=""
              file.write(s)
              file.flush()
              s=""
          s+="}}"
          file.write(s)
          file.flush()
          s=""
      s+="}}"
      file.write(s)
      file.close()
      self.writeLog("initJSDict time: %s"%(time.time()-init))
      return fileName
#      return s

  def getDbsDls(self):
      """
         Returns a list of known DBS/DLS instances
         @type  self: class object
         @param self: none 
         @rtype : list
         @return: list of known DBS/DLS instances 
      """
      return self.dbsdls

  def setDBSDLS(self,dbsInst):
      """
         Set DBS/DLS instance to use at given time. All instances are cached. Its initialization
         is done via L{DBSInst.DBManager} call. For DBS/DLS we use dbsCgiApi and dlsClient.getDlsApi calls,
         respectively.
         @type  dbsInst: string 
         @param dbsInst: DBS instance name, e.g. MCGlobal/Writer 
         @type url: string
         @param url: DBS URL, e.g. http://cmsdoc.cern.ch/cms/test/aprom/DBS/CGIServer/prodquery
         @rtype : none
         @return: none
      """
      if not self.dbsdls.keys().count(dbsInst):
         msg = "Wrong DBS instance '%s'\n"%dbsInst
         msg+= "Available instances:\n"
         for dbs in self.dbsdls.keys():
             msg+= dbs+"\n"
         raise msg
      self.dbsInstance = dbsInst
      self.writeLog("DBS Instnace: %s"%dbsInst)
      # use cache
      if not self.dbsApi.has_key(dbsInst):
         if self.iface=="cgi":
            self.api = dbsCgiApi.DbsCgiApi(DEFAULT_URL,{'instance':dbsInst})
         else: 
            # new api can be initialized with DbsConfig, but we will use default one
            url,dlsType,endpoint = DBS_DLS_INST[dbsInst]
#            self.api = dbsWebApi.DbsWebApi({'url':url})
            self.api=""
            con = self.connectToDB()
            self.closeConnection(con)
         self.dbsApi[dbsInst]=self.api
      else:
         self.api = self.dbsApi[dbsInst]
      if not self.dbsDLS.has_key(dbsInst):
         url,dlsType,endpoint = DBS_DLS_INST[dbsInst]
         self.writeLog("DLS Instance: %s %s"%(dlsType,endpoint))
         self.dlsApi = dlsClient.getDlsApi(dlsType, endpoint)
      else:
         self.dlsApi = self.dbsDLS[dbsInst]
 
  def setDLS_LFC(self):
      """
         Set grid instance of DLS for further usage. By default for all queries we use
         DLI DLS type, but if we would like to invoke DLS call getBlockNames we need to use
         LFC DLS type. In order to do that we check cached voms timer and if it's expired in
         12 hour cycle, we invoke voms-proxy-init call to get new credentials. Then we iniliaze
         appropriate DLS LFC instance and cache it.
      """
      url,dlsType,endpoint = DBS_DLS_INST[self.dbsInstance]
      # replace DLI type with LFC
      if dlsType=="DLS_TYPE_DLI":
         dlsType ="DLS_TYPE_LFC"
      if (time.time()-self.voms_timer)>12*60*60: # more then 12 hours
         self.voms_timer=time.time()
#         res = popen2.Popen4("voms-proxy-init -voms cms -q")
#         res = popen2.Popen4("cat $HOME/.globus/pp.txt | grid-proxy-init -pwstdin -q")
         cmd="cat /data/DBSDataDiscovery/COMP/DBS/Web/DataDiscovery/pp.txt | grid-proxy-init -cert /data/vk/cert/usercert.pem -key /data/vk/cert/userkey.pem -pwstdin -q"
         res = popen2.Popen4(cmd)
         res.wait()
         result=res.fromchild.read()
         if result:
            msg="Fail to initialize voms, '%s'"%result
            raise msg
      # use cache if possible
      if not self.dlsInst.has_key((dlsType,endpoint)):
         self.dls_iface = dlsClient.getDlsApi(dlsType, endpoint)
         self.dlsInst[(dlsType,endpoint)] = self.dls_iface
         if self.verbose:
            self.dls_iface.setVerbosity(dlsApi.DLS_VERB_HIGH)
      else:
         self.dls_iface = self.dlsInst[(dlsType,endpoint)]

  ### HELPER functions ###
  def getTableColumn(self,table,column,iRow=1,iLimit=0):
      t1=time.time()
      con = self.connectToDB()
      oList  = []
      try:
          content = self.getTableContent(con,table,iList=[column],fromRow=iRow,limit=iLimit)
      except:
          printExcept()
          raise "Fail in getTableColumn"
      for item in content:
          oList.append(item[0])
      self.closeConnection(con)
      return oList

  def listApplicationConfigs(self,appPath):
      t1=time.time()
      con = self.connectToDB()
      oList  = []
      try:
          tape = self.alias('AppExecutable','tape')
          tapv = self.alias('AppVersion','tapv')
          tapf = self.alias('AppFamily','tapf')
          talc = self.alias('AlgorithmConfig','talc')
          tqps = self.alias('QueryableParameterSet','tqps')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          oSel = [self.col(tqps,'ID'),self.col(tqps,'Name'),self.col(tqps,'Version'),self.col(tqps,'Type'),self.col(tqps,'Annotation'),self.col(tqps,'CreationDate'),self.col(tp1,'DistinguishedName'),self.col(tqps,'LastModificationDate'),self.col(tp2,'DistinguishedName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                     tqps.outerjoin(talc,onclause=self.col(talc,'ParameterSetID')==self.col(tqps,'ID'))
                     .outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                     .outerjoin(tp1,onclause=self.col(tqps,'CreatedBy')==self.col(tp1,'ID'))
                     .outerjoin(tp2,onclause=self.col(tqps,'LastModifiedBy')==self.col(tp2,'ID'))
                            ],distinct=True,order_by=oSel
                                 )
          if appPath and appPath!="*":
             empty,ver,fam,exe=string.split(appPath,"/")
             if ver and ver!="*":
                sel.append_whereclause(self.col(tapv,'Version')==ver)
             if fam and fam!="*":
                sel.append_whereclause(self.col(tapf,'FamilyName')==fam)
             if exe and exe!="*":
                sel.append_whereclause(self.col(tape,'ExecutableName')==exe)
          result = self.getSQLAlchemyResult(con,sel)
          for item in result:
              id,name,ver,type,ann,cDate,cBy,mDate,mBy=item
              if id and name:
                  oList.append((id,name,ver,type,ann,cDate,cBy,mDate,mBy))
      except:
          printExcept()
          raise "Fail in listApplicationsConfigs"
      if self.verbose:
         self.writeLog("time listApplicationsConfigs: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

  def listProcessedDatasets(self,group="*",app="*",prim="*",tier="*",proc="*"):
      if group.lower()=='any': group="*"
      if app.lower()  =='any': app  ="*"
      if prim.lower() =='any': prim ="*"
      if tier.lower() =='any': tier ="*"
      if proc.lower() =='any': proc ="*"
      # TODO: add group to join when table is available
      if proc and proc!="*":
         if type(proc) is types.ListType:
            return proc
         return [proc]
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tape = self.alias('AppExecutable','tape')
          tapv = self.alias('AppVersion','tapv')
          tapf = self.alias('AppFamily','tapf')
          talc = self.alias('AlgorithmConfig','talc')
          tpal = self.alias('ProcAlgo','tpal')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          sel  = sqlalchemy.select([self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name')],
                 from_obj=[
                     tprd.outerjoin(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                     .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .outerjoin(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                     ],distinct=True,
                 order_by=[sqlalchemy.desc(self.col(tpm,'Name')),sqlalchemy.desc(self.col(tdt,'Name')),sqlalchemy.desc(self.col(tprd,'Name'))] )
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             sel.append_whereclause(self.col(tdt,'Name')==tier)
          if app and app!="*":
             empty,ver,fam,exe=string.split(app,"/")
             if ver.lower()=="any" or ver.lower()=="all": ver="*"
             if fam.lower()=="any" or fam.lower()=="all": fam="*"
             if exe.lower()=="any" or exe.lower()=="all": exe="*"
             if ver and ver!="*":
                sel.append_whereclause(self.col(tapv,'Version')==ver)
             if fam and fam!="*":
                sel.append_whereclause(self.col(tapf,'FamilyName')==fam)
             if exe and exe!="*":
                sel.append_whereclause(self.col(tape,'ExecutableName')==exe)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in listProcessedDataset"
      for item in result:
          prim = item[0]
          tier = item[1]
          proc = item[2]
          oList.append("/%s/%s/%s"%(prim,proc,tier))
      self.closeConnection(con)
      return oList

  def listDatasetsFromApp(self,appPath="*"):
      return self.listProcessedDatasets(app=appPath)

  def listApplications(self,appPath="*"):
      """
         Wrapper around dbsApi
      """
      if self.iface=="cgi":
         aList = self.api.listApplications(appPath)
         aList.sort()
         aList.reverse()
         return aList
      else:
#         print "#### listApplications",appPath
         if appPath=="*":
            ver=family=exe="*"
         else:
            empty,ver,family,exe=string.split(appPath,"/")
         res = self.api.listApplications(patternVer=ver,patternFam=family,patternExe=exe)
#         print "\n\n\#### listApplications",res
#         res = self.api.listAlgorithms(patternVer=ver,patternFam=family,patternExe=exe)
         return res

  def listBlocks(self,kwargs):
      # {'blockName': (nEvt,blockStatus,nFiles,blockSize)}
      # second output:
      # [{'Name':,'BlockSize':,'NumberOfFiles':,'NumberOfEvents':,'OpenForWriting':,'CreationDate','CreationDate':,'LastModificationDate':,'LastModifiedBy'}]
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tblk = self.alias('Block','tblk')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')

          oSel = [self.col(tblk,'Name'),self.col(tblk,'BlockSize'),self.col(tblk,'NumberOfFiles'),self.col(tblk,'NumberOfEvents'),self.col(tblk,'OpenForWriting'),self.col(tp1,'DistinguishedName'),self.col(tblk,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tblk,'LastModificationDate'),self.col(tse,'SEName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                          tprd.outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                          .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .outerjoin(tp1,onclause=self.col(tblk,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(tblk,'LastModifiedBy')==self.col(tp2,'ID'))
                          .outerjoin(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                          .outerjoin(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                            ],distinct=True,order_by=oSel
                                 )
          if kwargs.has_key('datasetPath') and kwargs['datasetPath']:
             empty,prim,proc,tier=string.split(kwargs['datasetPath'],"/")
             if proc and proc!="*":
                sel.append_whereclause(self.col(tprd,'Name')==proc)
             if prim and prim!="*":
                sel.append_whereclause(self.col(tpm,'Name')==prim)
             if tier and tier!="*":
                sel.append_whereclause(self.col(tdt,'Name')==tier)
          if kwargs.has_key('blockName') and kwargs['blockName']:
             sel.append_whereclause(self.col(tblk,'Name')==kwargs['blockName'])
          if kwargs.has_key('site') and kwargs['site'] and kwargs['site']!="*" and string.lower(kwargs['site'])!='all':
             sel.append_whereclause(self.col(tse,'SEName')==kwargs['site'])
          idx=-1
          if kwargs.has_key('idx'): idx=kwargs['idx']
          result = self.getSQLAlchemyResult(con,sel,idx)
      except:
          printExcept()
          raise "Fail in listBlocks"
      aDict={}
      aList=[]
      siteList=[]
      totEvt=totFiles=totSize=0
      for item in result:
          if not item[0]: continue
          blockName,blockSize,nFiles,nEvts,blockStatus,cBy,cDate,mBy,mDate,sename=item
          totEvt+=nEvts
          totFiles+=nFiles
          totSize+=blockSize
          if not sename: sename='N/A'

          if not siteList.count(sename): siteList.append(sename)

          if kwargs.has_key('fullOutput'):
             aDict={'Name':blockName,'BlockSize':blockSize,'NumberOfFiles':nFiles,'NumberOfEvents':nEvts,'OpenForWriting':blockStatus,'CreatedBy':cBy,'CreationDate':cDate,'LastModifiedBy':mBy,'LastModificationDate':mDate}
             aList.append(aDict)
          else:
             if aDict.has_key(blockName):
                if not aDict[blockName].count(sename):
                   sList = aDict[blockName]
                   aDict[blockName]=sList+[sename]
             else:
                aDict[blockName]=[nEvts,blockStatus,nFiles,blockSize,sename]
      if self.verbose:
         self.writeLog("time listBlocks: %s"%(time.time()-t1))
      self.closeConnection(con)
      if kwargs.has_key('fullOutput'):
         return aList
      return aDict,totEvt,totFiles,totSize,siteList

  def numberOfEvents(self,datasetPath):
      prim=tier=proc=""
      if datasetPath and datasetPath!="*":
         empty,prim,proc,tier=string.split(datasetPath,"/")
      con = self.connectToDB()
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          tf   = self.alias('Files','tf')
          sel  = sqlalchemy.select([self.col(tf,'NumberOfEvents')],
                 from_obj=[
                     tprd.outerjoin(tf,onclause=self.col(tf,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                         .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     ] )
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             sel.append_whereclause(self.col(tdt,'Name')==tier)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in numberOfEvents"
      evts=0
      for item in result:
          if not item[0]: continue
          evts+= item[0]
      self.closeConnection(con)
      return evts

  ### END OF WRAPPER ###

  def getDataDescription(self,processedDataset):
      prim=tier=proc=""
      if processedDataset and processedDataset!="*":
         empty,prim,proc,tier=string.split(processedDataset,"/")
      con = self.connectToDB()
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpdd = self.alias('PrimaryDatasetDescription','tpdd')
          tmcd = self.alias('MCDescription','tmcd')
          tod  = self.alias('OtherDescription','tod')
          ttpd = self.alias('TriggerPathDescription','tttpd')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          mcDesc=[self.col(tmcd,'MCChannelDescription'),self.col(tmcd,'MCProduction'),self.col(tmcd,'MCDecayChain'),self.col(tp1,'DistinguishedName'),self.col(tmcd,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tmcd,'LastModificationDate')]
          trDesc=[self.col(ttpd,'TriggerPathDescription'),self.col(tp1,'DistinguishedName'),self.col(ttpd,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(ttpd,'LastModificationDate')]
          # find out if it's MC or real data, depending on that information retrieve
          # appropriate description
          tpt  = self.alias('PrimaryDSType','tpt')
          sel  = sqlalchemy.select([self.col(tpt,'Type')],
                 from_obj=[
                 tpm.outerjoin(tpt,onclause=self.col(tpm,'Type')==self.col(tpt,'ID'))
                     ],distinct=True,use_labels=True)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          result = self.getSQLAlchemyResult(con,sel)
          row = result.fetchone()
          dataType = row[0]
          # TODO: fix dataType checking, I need to know what would be used, 'MC' or 'mc', 'raw' or 'real data' etc.
          desc=mcDesc
          if string.lower(dataType)=='mc':
             desc = mcDesc
          elif string.lower(dataType)=='raw':
             desc = trDesc
          else:
             desc=mcDesc+trDesc
          sel  = sqlalchemy.select(desc,
                 from_obj=[
                 tprd.outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .outerjoin(tpdd,onclause=self.col(tpm,'Description')==self.col(tpdd,'ID'))
                     .outerjoin(tmcd,onclause=self.col(tpdd,'MCChannelDescriptionID')==self.col(tmcd,'ID'))
                     .outerjoin(tod,onclause=self.col(tpdd,'OtherDescriptionID')==self.col(tod,'ID'))
                     .outerjoin(ttpd,onclause=self.col(tpdd,'TriggerDescriptionID')==self.col(ttpd,'ID'))
                     .outerjoin(tp1,onclause=self.col(tpdd,'CreatedBy')==self.col(tp1,'ID'))
                     .outerjoin(tp2,onclause=self.col(tpdd,'LastModifiedBy')==self.col(tp2,'ID'))
                     ],distinct=True,use_labels=True,
                  order_by=desc )
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getDataDescription"
      oList = result.fetchall()
#      oList=[]
#      for item in result:
#          oList.append(item)
      self.closeConnection(con)
      return oList

  def getDatasetsFromApplications(self):
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tape = self.alias('AppExecutable','tape')
          tapv = self.alias('AppVersion','tapv')
          tapf = self.alias('AppFamily','tapf')
          talc = self.alias('AlgorithmConfig','talc')
          tpal = self.alias('ProcAlgo','tpal')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          sel  = sqlalchemy.select([self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName'),self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name')],
                   from_obj=[
                      tprd.outerjoin(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                      .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                      .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                      .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                      .outerjoin(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                      .outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                      .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                      .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                            ],distinct=True,
                   order_by=[self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName'),self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name')]
                                 )
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in listProcessedDataset"
      for item in result:
          ver  = item[0]
          fam  = item[1]
          exe  = item[2]
          prim = item[3]
          tier = item[4]
          proc = item[5]
          if ver and fam and exe and prim and tier and proc:
             addToDict(aDict,"/%s/%s/%s"%(ver,fam,exe),"/%s/%s/%s"%(prim,proc,tier))
      if self.verbose:
         self.writeLog("time getDatasetsFromApplications: %s"%(time.time()-t1))
      self.closeConnection(con)
      return aDict

  def getApplications(self):
      t1=time.time()
      con = self.connectToDB()
      oList  = []
      try:
          tape = self.alias('AppExecutable','tape')
          tapv = self.alias('AppVersion','tapv')
          tapf = self.alias('AppFamily','tapf')
          talc = self.alias('AlgorithmConfig','talc')
          sel  = sqlalchemy.select([self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName')],
                   from_obj=[
                     talc.outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                            ],distinct=True,
                       order_by=[self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName')]
                                 )
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getApplications"
      oList   = []
      for entry in result:
          path="/%s/%s/%s"%(entry[0],entry[1],entry[2])
          if self.html:
             navBar   ="MakeNavBarApp('%s','%s')"%(self.dbsInstance,path)
             dataInfo ="ajaxGetData('%s','all','*','%s','*','*','*')"%(self.dbsInstance,path)
#             blockInfo="ajaxGetDbsData('%s','all','*','%s','*','*','*')"%(self.dbsInstance,path)
#             runInfo  ="ajaxGetRuns('%s','all','*','%s','*','*','*')"%(self.dbsInstance,path)
#             path="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;%s;%s">%s</a>"""%(navBar,dataInfo,blockInfo,runInfo,path)
             path="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;">%s</a>"""%(navBar,dataInfo,path)
          oList.append(path)
      if self.verbose:
         self.writeLog("time getApplications: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

  def getPhysicsGroups(self):
      return self.getTableColumn('PhysicsGroup','PhysicsGroupName')
#      t1=time.time()
#      con = self.connectToDB()
#      oList  = []
#      try:
#          content = self.getTableContent(con,'PhysicsGroup',iList=['PhysicsGroupName'],fromRow=1,limit=0)
#      except:
#          printExcept()
#          raise "Fail in getPhysicsGroups"
#      oList   = []
#      for item in content:
#          oList.append(item[0])
#      if self.verbose:
#         self.writeLog("time getPhysicsGroups: %s"%(time.time()-t1))
#      self.closeConnection(con)
#      return oList

  def getSites(self):
      return self.getTableColumn('StorageElement','SEName')

  def getDataTiers(self):
      return self.getTableColumn('DataTier','Name')

  def getSoftwareReleases(self):
      return self.getTableColumn('AppVersion','Version')

  def getDatasetsFromApp(self,appPath="*",_prim="*",_tier="*"):
      """
         DBS data discovery wrapper around dbsCgiApi.listDatasetsFromApp
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : list 
         @return: a list of datasets from application in the following form, [datasetPathName]
      """
      if _prim.lower()=="any" or _prim.lower()=="all": _prim="*"
      if _tier.lower()=="any" or _tier.lower()=="all": _tier="*"
      oList = []
      dList = self.listDatasetsFromApp(appPath)
      for dataset in dList:
#          empty,prim,tier,app = string.split(dataset,"/")
          empty,prim,proc,tier = string.split(dataset,"/")
          if _prim!="*" and prim!=_prim: continue
          if _tier!="*" and tier!=_tier: continue
          oList.append(dataset)
      oList.sort()
      oList.reverse()
      return oList
      
  def getPrimaryDatasets(self,datasetPath="*",html=0):
      t1=time.time()
      con = self.connectToDB()
      content = self.getTableContent(con,'PrimaryDataset',iList=['Name'],fromRow=1,limit=0)
      oList   = []
      for entry in content:
          name=entry[0]
          if html:
             navBar   ="MakeNavBarPrimDS('%s','%s')"%(self.dbsInstance,name)
             dataInfo ="ajaxGetData('%s','all','*','*','%s','*','*')"%(self.dbsInstance,name)
#             blockInfo="ajaxGetDbsData('%s','all','*','*','%s','*','*')"%(self.dbsInstance,name)
#             runInfo  ="ajaxGetRuns('%s','all','*','*','%s','*','*')"%(self.dbsInstance,name)
#             name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;%s;%s">%s</a>"""%(navBar,dataInfo,blockInfo,runInfo,name)
             name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;">%s</a>"""%(navBar,dataInfo,name)
          oList.append(name)
      if self.verbose:
         self.writeLog("time getPrimaryDatasets: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList
      
  def getProcessedDatasets(self,datasetPath="*",app=1,html=0):
      t1=time.time()
      prim=tier=proc=""
      if datasetPath and datasetPath!="*":
         empty,prim,proc,tier=string.split(datasetPath,"/")
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          sel  = sqlalchemy.select([self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name')],
                 from_obj=[
                     tprd.outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                     .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     ],distinct=True,
                 order_by=[self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name')] )
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             sel.append_whereclause(self.col(tdt,'Name')==tier)
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in listProcessedDataset_al"
      for item in result:
          if not item[0] or not item[1] or not item[2]: continue
          name="/%s/%s/%s"%(item[0],item[1],item[2])
          if html:
             navBar   ="MakeNavBarProcDS('%s','%s')"%(self.dbsInstance,name)
             dataInfo ="ajaxGetData('%s','all','*','*','*','*','%s')"%(self.dbsInstance,name)
#             blockInfo="ajaxGetDbsData('%s','all','*','*','*','*','%s')"%(self.dbsInstance,name)
#             runInfo  ="ajaxGetRuns('%s','all','*','*','*','*','%s')"%(self.dbsInstance,name)
#             name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;%s;%s">%s</a>"""%(navBar,dataInfo,blockInfo,runInfo,name)
             name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;">%s</a>"""%(navBar,dataInfo,name)
          oList.append(name)
      if self.verbose:
         self.writeLog("time getProcessedDatasets: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList
  
  def getDatasetContent(self,dataset):
      content = self.api.getDatasetContents(dataset)
      return content

  def getDatasetProvenance(self,dataset):
#      pList=[]
#      for parent in self.api.getDatasetProvenance(dataset):
#          p=parent['parent']['datasetPathName']
#          if not p: break
#          pList.append(p)
#          pList+=self.getDatasetProvenance(p)
#      return pList


      t1=time.time()
      prim=tier=proc=""
      if datasetPath and datasetPath!="*":
         empty,prim,proc,tier=string.split(datasetPath,"/")
      con = self.connectToDB()
      oList  = []
      try:

          tprd = self.alias('ProcessedDataset','tprd')
          tprd2= self.alias('ProcessedDataset','tprd2')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          tpdp = self.alias('ProcDSParent','tpdp')
          tpdp2= self.alias('ProcDSParent','tpdp2')

          oSel = [self,col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd2,'Name')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tprd.outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                     .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .outerjoin(tpdp,onclause=self.col(tpdp,'ThisDataset')==self.col(tprd,'ID'))
                     .outerjoin(tpdp2,onclause=self.col(tpdp2,'ItsParent')==self.col(tprd2,'ID'))
                     ],distinct=True,order_by=oSel )
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             sel.append_whereclause(self.col(tdt,'Name')==tier)
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in listProcessedDataset_al"
      for item in result:
          if not item[0] or not item[1] or not item[2]: continue
          name="/%s/%s/%s"%(item[0],item[1],item[2])
#          if html:
#             navBar   ="MakeNavBarProcDS('%s','%s')"%(self.dbsInstance,name)
#             dataInfo ="ajaxGetData('%s','all','*','*','*','*','%s')"%(self.dbsInstance,name)
#             blockInfo="ajaxGetDbsData('%s','all','*','*','*','*','%s')"%(self.dbsInstance,name)
#             runInfo  ="ajaxGetRuns('%s','all','*','*','*','*','%s')"%(self.dbsInstance,name)
#             name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;%s;%s">%s</a>"""%(navBar,dataInfo,blockInfo,runInfo,name)
          oList.append(name)
      if self.verbose:
         self.writeLog("time getProcessedDatasets: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList


  def exeQuery(self,q):
      """
         Set DBS instance and
         execute given query, if fails throws exception, including query
         @type  q: string 
         @param q: SQL query 
         @rtype : SQLAlchemy query object 
         @return: results of the query
      """
      result=""
      try:
         result= self.dbManager.engine[self.dbsInstance].execute(q)
      except:
         # if we fail because of connection drop let's reconnect again
	 try:
            self.setDBSDLS(self.dbsInstance)
	    result= self.dbManager.engine[self.dbsInstance].execute(q)
	 except:
            printExcept()
            raise "Fail to execute \n\n%s\n\n"%q
	 pass
      return result

  def closeConnection(self,con):
      # if SQLAlchemy uses pool for engine, then it should correctly handle all connections
      # and there is no needs to close it, since a new one will be taken from pool
      # but I can keep it commented out here and use this function everywhere to rollback
      # quickly.
#      con.close()
      return

  def connectToDB(self):
      con=""
      try:
          con = self.dbManager.connect(self.dbsInstance)
      except Exception, ex:
	 try:
             # try second time, but sleep for 2 seconds before retry
             time.sleep(2)
             con = self.dbManager.connect(self.dbsInstance)
         except Exception, ex:
             raise DbsDatabaseError(args=ex)
         pass
      return con

  def getSQLAlchemyResult(self,con,sel,idx=-1):
      """
         Set DBS instance and
         execute given query, if fails throws exception, including query
         @type  sel: SQLAlchemy select object
         @param sel: SQL query written in SQLAlchemy
         @rtype : SQLAlchemy query object 
         @return: results of the query
      """
      res = []
      try:
          if idx>-1:
             sel.limit=self.ddConfig.queryLimit()
             sel.offset=idx
#          if not con:
#             con = self.connectToDB()
          res = con.execute(sel)
#          self.closeConnection(con)
      except:
          msg="While connecting to %s exception was thrown:\n"%self.dbsInstance
          msg+=getExcept()
          res=[msg]
          pass
      return res

  def getSQLAlchemyResult_orig(self,sel):
      """
         Set DBS instance and
         execute given query, if fails throws exception, including query
         @type  sel: SQLAlchemy select object
         @param sel: SQL query written in SQLAlchemy
         @rtype : SQLAlchemy query object 
         @return: results of the query
      """
      self.setDBSDLS(self.dbsInstance)
      try:
#          con = self.dbManager.engine[self.dbsInstance].connect()
          con = self.dbManager.connect(self.dbsInstance)
          res = con.execute(sel)
          self.closeConnection(con)
      except Exception, ex:
         # if we fail because of connection drop let's reconnect again
	 try:
             # try second time, but sleep for 2 seconds before retry
             time.sleep(2)
             self.dbManager.connect()
#             con = self.dbManager.engine[self.dbsInstance].connect()
             con = self.dbManager.connect(self.dbsInstance)
             res = con.execute(sel)
             self.closeConnection(con)
         except Exception, ex:
             raise DbsDatabaseError(args=ex)
         pass
      return res

  def getTableContent(self,con,tableName,iList=['*'],fromRow=1,limit=0):
      try:
          if limit:
             sel = sqlalchemy.select(iList, from_obj=[tableName], limit=limit, offset=fromRow)
          else:
             sel = sqlalchemy.select(iList, from_obj=[tableName])
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail to get content for table='%s'"%tableName
      return result

  def getTableColumns(self,tableName):
      res=[]
      try:
#          res = ['All']+self.dbManager.getColumns(self.dbsInstance,tableName)
          res = self.dbManager.getColumns(self.dbsInstance,tableName)
      except:
#          printExcept()
#          raise "Fail to get columns for table='%s'"%tableName
          msg="Fail to get columns for table='%s'\n"%tableName
          msg+=getExceptionInHTML()
          res=msg
      return res

  def queryMaker(self,iList,execute=1):
      """ 
         Build a query out of input iList=['TableName.ColumnName',]
      """
      # I need to lookup SQLAlchemy tables
#      oSel = []
#      for item in iList:
#          table,col=item.split(".")
#          if col=="*":
#             for c in self.dbManager.getColumns(self.dbsInstance,table):
#                 oSel.append(self.col(self.dbManager.getTable(self.dbsInstance,table),c))
#          else:
#             oSel.append(self.col(self.dbManager.getTable(self.dbsInstance,table),col))
#      md     = self.dbManager.metaDict[self.dbsInstance]
#      sel    = sqlalchemy.select(oSel)
#      qb     = Schema(md)
#      query  = qb.BuildQuery(sel)
#      print str(query)
#      if  execute:
#          res = self.executeSQLQuery(query)
#      return str(query),res
      qb     = Schema(self.dbManager.constructSchema(self.dbsInstance))
      query  = qb.BuildQuery(iList)
      writer = SqlQueryWriter()
      query.Write(writer)
      if  self.verbose:
          self.writeLog(qb)
          self.writeLog(repr(iList))
          self.writeLog(writer.getvalue())
      res = []
      print writer.getvalue()
      if  execute:
          res = self.executeSQLQuery(writer.getvalue())
      return writer.getvalue(),res

  def getAllTableColumns(self):
      tList = self.dbManager.dbTables[self.dbsInstance].keys()
      tList.sort()
      oList = []
      for table in tList:
          tObj= self.dbManager.dbTables[self.dbsInstance][table]
          for col  in tObj.columns:
              oList.append('%s.%s'%(tObj.fullname,col.name))
      return oList

  def getDbsSchema(self,iTable='all',html=1):
      res = ""
      tList = self.dbManager.dbTables[self.dbsInstance].keys()
      tList.sort()
      for table in tList:
          tObj= self.dbManager.dbTables[self.dbsInstance][table]
          if string.lower(iTable)!="all" and string.lower(iTable)!=string.lower(tObj.fullname):
             continue
#          print tObj.__dict__
          if html:
             res+="<p><b>%s</b></p>"%tObj.fullname
             res+="""<table class="dbs_table">"""
          else:
             res+= "%s\n"%tObj.fullname
          for col  in tObj.columns:
#              print col.name,col.foreign_key
              colType=string.split(string.split(repr(col.type))[0],".")[-1]
              fk=""
              if col.foreign_key: fk=repr(col.foreign_key)
              if col.primary_key:
                 fk="PrimaryKey"
#                 if col.autoincrement: fk+=", Autoincrment"
              if html:
                 res+="<tr><td>%s</td><td>%s</td><td>%s</td></tr>"%(col.name,colType,fk)
              else:
                 res+="  %s %s %s\n"%(col.name,pk,fk)
          if html: res+="</table><p />"
          res+="\n\n"
      return res
          

  def checkQuery(self,query):
      if type(query) is sqlalchemy.sql.Select: return True
      if string.find(query.lower(),"insert")!=-1:
         return False
      if string.find(query.lower(),"update")!=-1:
         return False
        
      known_tables = self.dbManager.getTableNames(self.dbsInstance)
      found=0
      for tName in known_tables:
          if string.find(query,tName)!=-1:
             found=1
      if not found:
         return False
      return True

  def executeSQLQuery(self,query):
      if not self.checkQuery(query):
         return "Your query is not valid, you either specified unkonwn table or tried to perform insert/update operation, which are forbidden."

      con = self.connectToDB()
      res = ""
      try:
         res = con.execute(query)
      except:
         res = getExceptionInHTML()
         self.closeConnection(con)
         return res
      oList = []
      counter=0
      for item in res:
          if not counter:
             oList.append(item.keys())
          oList.append(item)
          counter+=1
      self.closeConnection(con)
      return oList

  def formSQLQuery(self,tDict):
      con = self.connectToDB()
      print "###formSQLQuery",tDict.keys()
#      tableName=tList[0]

      cols = []
      for tableName in tDict.keys():
          iList = tDict[tableName]
          # first get table content and check if input list of columns names match
          cList=self.dbManager.getColumns(self.dbsInstance,tableName)
          if len(iList) and not iList.count('All'):
             for c in cList:
                 if iList.count(c):
                    cols.append('%s.%s'%(tableName,c))
          else:
             cols+=map(lambda x: '%s.%s'%(tableName,x), cList)


      # form query
#      tObj = self.dbManager.getTableObject(self.dbsInstance,'PrimaryDataset')
#      tObj2 = self.dbManager.getTableObject(self.dbsInstance,'ProcessedDataset')
#      tObj3 = self.dbManager.getTableObject(self.dbsInstance,'AlgorithmConfig')
#      tObj4 = self.dbManager.getTableObject(self.dbsInstance,'ProcAlgo')
#      print tObj4.foreign_keys
#      joinObject = tObj.join(tObj2)
#          sel = sqlalchemy.select([tObj.c.Name,tObj2.c.Name,tObj3.c.ApplicationVersion],from_obj=[tObj3.join(tObj2).join(tObj)])
      try:
          # get table objects
          tObjs=map(lambda x: self.dbManager.getTableObject(self.dbsInstance,x),tDict.keys())
          joinObject = tObjs[0]
          for _tObj in tObjs[1:]:
              joinObject=joinObject.join(_tObj)
#          joinObject = tObjs[0]
#          tObjs.remove(tObjs[0])
#          try:
#              for _tObj in tObjs:
#                  joinObject=joinObject.join(_tObj)
#                  joinObject.remove(_tObj)
#          except:
#             raise "Fail" 
              

          # form query
          sel = sqlalchemy.select(cols,from_obj=[joinObject])
          res = self.getSQLAlchemyResult(con,sel)
          page="""<table class="dbs_table">"""
          page+="<tr>"
          for col in cols:
              page+="<th>%s</th>"%col
          page+="</tr>"
          for item in res:
              page+="<tr>"
              for elem in item:
                  page+="<td>%s</td>"%elem
              page+="</tr>"
          page+="</table>"
#          print page
          return page
      except sqlalchemy.exceptions.ArgumentError, ex:
          print ex
          _cant,_table1,_and,_table2,_empty = string.split(ex.args[0],"\'")
          refList= [_table1.split()[-1],_table2.split()[-1]]
          print "refList",refList
          for tName in self.dbManager.getTableNames(self.dbsInstance):
#          for tName in ['ProcAlgo']:
#              print "tableName=",tName
              fKeys=self.dbManager.getTableObject(self.dbsInstance,tName).foreign_keys
              fKeysName=[]
              for fk in fKeys: 
                  fkName = string.split(fk._colspec,".")[0]
                  if refList.count(fkName) and not fKeysName.count(fkName):
                     fKeysName.append(fkName)
                  if len(refList)==len(fKeysName):
                     print "Found",fk,refList,tName
                     tDict[tName]=[]
                     print tDict
                     return self.formSQLQuery(tDict)
                     self.closeConnection(con)
                     return
                      
#                  print fk,refList,fk.references(self.dbManager.getTableObject(self.dbsInstance,refList[0])),fk.references(self.dbManager.getTableObject(self.dbsInstance,refList[1]))
#                  if fk.references(self.dbManager.getTableObject(self.dbsInstance,refList[0])):
#                     counter+=1
#                  if fk.references(self.dbManager.getTableObject(self.dbsInstance,refList[1])):
#                     counter+=1
#                  if counter==2:
#                     print "Found",fk,refList,tName
#                     tDict[tName]=[]
#                     print tDict
#                     self.closeConnection(con)
#                     return
#          print "#### ac,procAlgo"
#          fKeys=self.dbManager.getTableObject(self.dbsInstance,'AlgorithmConfig').foreign_keys
#          for fk in fKeys: print fk,fk.references(self.dbManager.getTableObject(self.dbsInstance,'ProcAlgo'))
#          print "#### procAlgo,ac"
#          fKeys=self.dbManager.getTableObject(self.dbsInstance,'ProcAlgo').foreign_keys
#          for fk in fKeys: print fk,fk.references(self.dbManager.getTableObject(self.dbsInstance,'AlgorithmConfig'))
          
#          [c.key for c in s.columns]
#      nList=[]
#      for x in cList:
#          nList.append(getattr(tObj.c,x))
#      return map(cList,lambda x: getattr(table,x))
          
      self.closeConnection(con) 

  def formQuery(self):
      return

  def getConfigContent(self,dbsInst,id):
      self.setDBSDLS(dbsInst)
      con = self.connectToDB()
      try:
          tqps = self.alias('QueryableParameterSet','tqps')
          sel  = sqlalchemy.select([self.col(tqps,'Content')],self.col(tqps,'ID')==id)
          res  = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getConfigContent"
      content=""
      for item in res:
          content=str(item[0]) # need to use str to make ORACLE happy
      self.closeConnection(con)
      return content

  def getConfigContentByName(self,dbsInst,name,rel=""):
      self.setDBSDLS(dbsInst)
      t1=time.time()
      con = self.connectToDB()
      oList  = []
      try:
          tapv = self.alias('AppVersion','tapv')
          talc = self.alias('AlgorithmConfig','talc')
          tqps = self.alias('QueryableParameterSet','tqps')
          sel  = sqlalchemy.select([self.col(tqps,'Content')],
                   from_obj=[
                     tqps.outerjoin(talc,onclause=self.col(talc,'ParameterSetID')==self.col(tqps,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                            ],distinct=True
                                 )
          if rel and rel!="*":
             sel.append_whereclause(self.col(tapv,'Version')==rel)
          result = self.getSQLAlchemyResult(con,sel)
          for item in result:
              content=item[0]
      except:
          printExcept()
          raise "Fail in getConfigContentByName"
      if self.verbose:
         self.writeLog("time getConfigContentByName: %s"%(time.time()-t1))
      self.closeConnection(con)
      return content

  def getLFNs(self,dbsInst,blockName,dataset):
      prim=tier=proc="*"
      if dataset and dataset!="*":
         empty,prim,proc,tier=string.split(dataset,"/")
      con = self.connectToDB()
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          tb   = self.alias('Block','tb')
          tf   = self.alias('Files','tf')
          oSel = [self.col(tf,'LogicalFileName'),self.col(tf,'FileSize'),self.col(tf,'FileStatus'),self.col(tf,'FileType'),self.col(tf,'NumberOfEvents')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tprd.outerjoin(tf,self.col(tf,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                         .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                         .outerjoin(tb,onclause=self.col(tb,'Dataset')==self.col(tprd,'ID'))
                     ],distinct=True,order_by=oSel
                                  )
          sel.append_whereclause(self.col(tf,'Block')==self.col(tb,'ID'))
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             sel.append_whereclause(self.col(tdt,'Name')==tier)
          if blockName and blockName!="*":
             sel.append_whereclause(self.col(tb,'Name')==blockName)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getLFNs"
      oList=[]
      for item in result:
          if not item[0]: continue
          oList.append(item)
      self.closeConnection(con)
      return oList

  def getLFNs_old(self,dbsInst,blockName,dataset):
      self.setDBSDLS(dbsInst)
      res = self.api.getLFNs(blockName,dataset)
      return res

#  def getLFN_Branches(self,dbsInst,lfn):
#      self.setDBSDLS(dbsInst)
#      res = self.api.listFileBranches(lfn)
#      return res
  def getLFN_Branches(self,dbsInst,lfn):
      con = self.connectToDB()
      try:
          tb   = self.alias('Branch','tb')
          tfb  = self.alias('FileBranch','tfb')
          tf   = self.alias('Files','tf')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          oSel = [self.col(tb,'Name'),self.col(tp1,'DistinguishedName'),self.col(tb,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tb,'LastModificationDate')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tf.outerjoin(tfb,self.col(tfb,'Fileid')==self.col(tf,'ID'))
                       .outerjoin(tb,onclause=self.col(tfb,'Branch')==self.col(tb,'ID'))
                       .outerjoin(tp1,onclause=self.col(tb,'CreatedBy')==self.col(tp1,'ID'))
                       .outerjoin(tp2,onclause=self.col(tb,'LastModifiedBy')==self.col(tp2,'ID'))
                     ],distinct=True,order_by=oSel
                                  )
          if lfn and lfn!="*":
             sel.append_whereclause(self.col(tf,'LogicalFileName')==lfn)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getLFN_Branches"
      tList=['Name','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      oList=[]
      for item in result:
          if not item[0]: continue
          oList.append(item)
      self.closeConnection(con)
      return tList,oList

#  def getLFN_Lumis(self,dbsInst,lfn):
#      self.setDBSDLS(dbsInst)
#      res = self.api.listFileLumis(lfn)
#      return res
  def getLFN_Lumis(self,dbsInst,lfn):
      con = self.connectToDB()
      try:
          tls   = self.alias('LumiSection','tls')
          tfr  = self.alias('FileRunLumi','tfr')
          tf   = self.alias('Files','tf')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          oSel = [self.col(tls,'LumiSectionNumber'),self.col(tls,'RunNumber'),self.col(tls,'StartEventNumber'),self.col(tls,'EndEventNumber'),self.col(tls,'LumiStartTime'),self.col(tls,'LumiEndTime'),self.col(tp1,'DistinguishedName'),self.col(tls,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tls,'LastModificationDate')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tf.outerjoin(tfr,self.col(tfr,'Fileid')==self.col(tf,'ID'))
                       .outerjoin(tls,onclause=self.col(tfr,'Lumi')==self.col(tls,'ID'))
                       .outerjoin(tp1,onclause=self.col(tls,'CreatedBy')==self.col(tp1,'ID'))
                       .outerjoin(tp2,onclause=self.col(tls,'LastModifiedBy')==self.col(tp2,'ID'))
                     ],distinct=True,order_by=oSel
                                  )
          if lfn and lfn!="*":
             sel.append_whereclause(self.col(tf,'LogicalFileName')==lfn)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getLFN_Lumis"
      tList=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      oList=[]
      for item in result:
          if not item[0]: continue
          oList.append(item)
      self.closeConnection(con)
      return tList,oList

#  def getLFN_Algos(self,dbsInst,lfn):
#      self.setDBSDLS(dbsInst)
#      res = self.api.listFileAlgorithms(lfn)
#      return res

#  def getLFN_Tiers(self,dbsInst,lfn):
#      self.setDBSDLS(dbsInst)
#      res = self.api.listFileTiers(lfn)
#      return res
  def getLFN_Tiers(self,dbsInst,lfn):
      con = self.connectToDB()
      try:
          tdt  = self.alias('DataTier','tdt')
          tft  = self.alias('FileTier','tft')
          tf   = self.alias('Files','tf')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          oSel = [self.col(tdt,'Name'),self.col(tp1,'DistinguishedName'),self.col(tdt,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tdt,'LastModificationDate')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tf.outerjoin(tft,self.col(tft,'Fileid')==self.col(tf,'ID'))
                       .outerjoin(tdt,onclause=self.col(tft,'DataTier')==self.col(tdt,'ID'))
                       .outerjoin(tp1,onclause=self.col(tdt,'CreatedBy')==self.col(tp1,'ID'))
                       .outerjoin(tp2,onclause=self.col(tdt,'LastModifiedBy')==self.col(tp2,'ID'))
                     ],distinct=True,order_by=oSel
                                  )
          if lfn and lfn!="*":
             sel.append_whereclause(self.col(tf,'LogicalFileName')==lfn)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getLFN_Tiers"
      tList=['Name','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      oList=[]
      for item in result:
          if not item[0]: continue
          oList.append(item)
      self.closeConnection(con)
      return tList,oList

#  def getLFN_Parents(self,dbsInst,lfn):
#      self.setDBSDLS(dbsInst)
#      res = self.api.listFileParents(lfn)
#      return res

  def alias(self,tableName,aliasName):
      """
         Helper function to get table alias from SQLAlchemy for current DBS instance
         @type  tableName: string 
         @param tableName: name of the table
         @type  aliasName: string
         @param aliasName: desired alias name
         @rtype : SQLAlchemy table object
         @return: table alias
      """
      return self.dbManager.getTable(self.dbsInstance,tableName,aliasName)
      
  def search(self,searchString):
      """
         Retrieves a summary information from DB. It makes join over
         primary Dataset, data tier, application. The idea is simlar to iTunes.

         ::
             select distinct tprd.id,tp.id,tpm.name,tdt.name,tapp.app_version,tapf.name,tapp.executable
             FROM t_processed_dataset tprd 
             LEFT OUTER JOIN t_data_tier tdt ON tdt.id = tprd.data_tier 
             LEFT OUTER JOIN t_processing_name tpn ON tpn.id = tprd.name 
             LEFT OUTER JOIN t_primary_dataset tpm ON tpm.id = tprd.primary_dataset 
             LEFT OUTER JOIN t_processing tp ON tp.primary_dataset = tpm.id 
             LEFT OUTER JOIN t_app_config tapc ON tp.app_config = tapc.id 
             LEFT OUTER JOIN t_parameter_set tpset ON tapc.parameter_set = tpset.id 
             LEFT OUTER JOIN t_application tapp ON tapc.application = tapp.id 
             LEFT OUTER JOIN t_app_family tapf ON tapp.app_family = tapf.id 
             ORDER BY tpm.name, tdt.name, tapp.app_version, tapf.name, tapp.executable
              
         @type pList: list
         @param pList: arbitrary list keyword
         @rtype: list
         @return: a list of [(primD,tier,App version, App family, App exe)]
      """
      if  not validator(searchString):
          msg = "Wrong expression '%s'"%searchString
          raise msg

      con = self.connectToDB()

      oList  = []
      if  self.iface=='cgi':
          tprd = self.alias('t_processed_dataset','tprd')
          tpm  = self.alias('t_primary_dataset','tpm')
          tpn  = self.alias('t_processing_name','tpn')
          tp   = self.alias('t_processing','tp')
          tdt  = self.alias('t_data_tier','tdt')
          tapp = self.alias('t_application','tapp')
          tapc = self.alias('t_app_config','tapc')
          tapf = self.alias('t_app_family','tapf')
          tpset= self.alias('t_parameter_set','tpset')
          sel  = sqlalchemy.select([tpm.c.name,tdt.c.name,tapp.c.app_version,
                                   tapf.c.name,tapp.c.executable],
                       from_obj=[
                                  tprd.outerjoin(tdt,onclause=tdt.c.id==tprd.c.data_tier)
                                  .outerjoin(tpn,onclause=tpn.c.id==tprd.c.name)
                                  .outerjoin(tpm,onclause=tpm.c.id==tprd.c.primary_dataset)
                                  .outerjoin(tp,onclause=tp.c.primary_dataset==tpm.c.id)
                                  .outerjoin(tapc,onclause=tp.c.app_config==tapc.c.id)
                                  .outerjoin(tpset,onclause=tapc.c.parameter_set==tpset.c.id)
                                  .outerjoin(tapp,onclause=tapc.c.application==tapp.c.id)
                                  .outerjoin(tapf,onclause=tapp.c.app_family==tapf.c.id)
                                ],
                       order_by=[tpm.c.name,tdt.c.name,
                                 tapp.c.app_version,tapf.c.name,tapp.c.executable],distinct=True
                                 )
          result = self.getSQLAlchemyResult(con,sel)
          # loop over results and find a match to list of keywords from pList.
          for iTup in result:
              tup = iTup.__dict__['_RowProxy__row']# tuple, rather then instance from SQL object
              searchList=toLower(tupleToList(tup))
              if eval(constructExpression(searchString,'searchList')):
                 oList.append((self.dbsInstance,)+tup)
      else: # JavaServer, DBS-2
          tproc = self.alias('ProcessedDataset','tproc')
          tprim = self.alias('PrimaryDataset','tprim')
          tpdst = self.alias('ProcDSTier','tpdst')
          tpdsr = self.alias('ProcDSRuns','tpdsr')
          talgo = self.alias('ProcAlgo','talgo')
          ttier = self.alias('DataTier','ttier')
          sel=sqlalchemy.select([tprim.c.Name,ttier.c.Name,tproc.c.Name],
                       from_obj=[
                                  tproc.outerjoin(tprim,onclause=tprim.c.ID==tproc.c.PrimaryDataset)
                                  .outerjoin(tpdst,onclause=tpdst.c.Dataset==tproc.c.ID)
                                  .outerjoin(tpdsr,onclause=tpdsr.c.Dataset==tproc.c.ID)
                                  .outerjoin(talgo,onclause=talgo.c.Dataset==tproc.c.ID)
                                  .outerjoin(ttier,onclause=tpdst.c.DataTier==ttier.c.ID)
                                ],
                       order_by=[tprim.c.Name,ttier.c.Name,tproc.c.Name],
                       distinct=True
                                 )
          for item in string.split(searchString,'___'):
              if not item: continue
              key,value=string.split(item,":")
              if not value: continue
              if key=='tier':
                 res = self.searchTier(value)
              if key=='runs':
                 res = self.searchRuns(value)
              if key=='proc':
                 res = self.searchProc(value)
              if key=='prim':
                 res = self.searchPrim(value)
              if key=='algo':
                 res = self.searchAlgo(value)
              if res:
                 self.append_whereclause(res)
        
          result = self.getSQLAlchemyResult(con,sel)
          d="" # we will return "v1,v2,v3" string instead of list for easy access in JS
          for item in result:
              print "\n\n####",item
              prim=item[0]
              tier=item[1]
              proc=item[2]
              if not proc or not tier or not proc: continue
              d+="/%s/%s/%s,"%(prim,tier,proc)
          oList=d[:-1] # remove last ","
      self.closeConnection(con)
      return oList

  def searchTier(self,input):
      print "\n\n#### tier %s\n\n"%input
  def searchRuns(self,input):
      print "\n\n#### runs %s\n\n"%input
      res=""
      return res
  def searchProc(self,input):
      print "\n\n#### proc %s\n\n"%input
  def searchPrim(self,input):
      print "\n\n#### prim %s\n\n"%input
  def searchAlgo(self,input):
      print "\n\n#### algo %s\n\n"%input

  def getDBSSummary(self,dbsInst):
      """
         Collect a global summary from DBS/DLS. Currently only two queries invoked
         select COUNT(logical_name) from t_file
         select SUM(filesize) from t_file
         to fetch total number of files and their size in DBS instance. In a future
         more information should be retrieved from DBS/DLS. For instance, each site
         should reports number of files it has and total disk space used.
         @type  dbsInst: string 
         @param dbsInst: name of DBS instance 
         @rtype : dictionary
         @return: {'Number of files':N, 'Total file size':M}
      """
      self.setDBSDLS(dbsInst)
      con = self.connectToDB()
      sumDict = {}
      tf  = self.alias('t_file','tf')
      sel = sqlalchemy.select([sqlalchemy.func.count(tf.c.logical_name)])
      sumDict['Number of files'] = self.getSQLAlchemyResult(con,sel).fetchone()[0]
      sel = sqlalchemy.select([sqlalchemy.func.sum(tf.c.filesize)])
      sumDict['Total file size'] = sizeFormat(self.getSQLAlchemyResult(con,sel).fetchone()[0])
      self.closeConnection(con)
      return sumDict

  def WhatExists(self,datasetPath):
      """
         CLI interface function, which provides snapshot of available primary datasets
         and application in DBS.
         @type  datasetPath: string 
         @param datasetPath: name of the dataset 
         @rtype : none
         @return: print out available primary datasets and applications
      """
      printMsg("Available primary datasets:")

      for primaryDataset in self.getPrimaryDatasets(datasetPath):
          print primaryDataset

      print
      printMsg("Available applications:")
      appList = self.getApplications()
      for app in appList:
          print app
#          print app.get('executable'),app.get('version'),app.get('family')

  def getRuns(self,dataset):
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          tpdr = self.alias('ProcDSRuns','tpdr')
          trun = self.alias('Runs','trun')
          tpt  = self.alias('PrimaryDSType','tpt')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          oSel = [self.col(trun,'RunNumber'),self.col(trun,'NumberOfEvents'),self.col(trun,'NumberOfLumiSections'),self.col(trun,'TotalLuminosity'),self.col(trun,'StoreNumber'),self.col(trun,'StartOfRun'),self.col(trun,'EndOfRun'),self.col(tp1,'DistinguishedName'),self.col(trun,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(trun,'LastModificationDate'),self.col(tpt,'Type')]
          sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          tprd.outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(trun,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
                          .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                          .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .outerjoin(tpt,onclause=self.col(tpm,'Type')==self.col(tpt,'ID'))
                          .outerjoin(tp1,onclause=self.col(trun,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(trun,'LastModifiedBy')==self.col(tp2,'ID'))
                                ],distinct=True,order_by=oSel
                                 )
          if dataset:
             empty,prim,proc,tier=string.split(dataset,"/")
             if proc and proc!="*":
                sel.append_whereclause(self.col(tprd,'Name')==proc)
             if prim and prim!="*":
                sel.append_whereclause(self.col(tpm,'Name')==prim)
             if tier and tier!="*":
                sel.append_whereclause(self.col(tdt,'Name')==tier)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getRuns"
      oList=[]
      for item in result:
          run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mBy,mDate,type=item
          if not run: continue
          aDict={'RunNumber':run,'NumberOfEvents':nEvts,'NumberOfLumiSections':nLumis,'TotalLuminosity':totLumi,'StoreNumber':store,'StartOfRun':sRun,'EndOfRun':eRun,'CreatedBy':cBy,'CreationDate':cDate,'LastModificationDate':mDate,'LastModifiedBy':mBy,'Type':type}
          oList.append(aDict)
      if self.verbose:
         self.writeLog("time in getRuns: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

  def getDbsData(self,dataset):
      kwargs={'datasetPath':dataset,'fullOutput':1}
      return self.listBlocks(kwargs)

  def getDbsBlockData(self,blockName):
      kwargs={'blockName':blockName,'fullOutput':1}
      return self.listBlocks(kwargs)

  def getUserData(self,group,tier,rel,prim,site):
      # TODO: I need to add physics group table when it's available
      # if site is provided add DLS calls to filter results based on site search.

      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
#          tphg = self.alias('PhysicsGroup','tphg')
          talc = self.alias('AlgorithmConfig','talc') 
          tpal = self.alias('ProcAlgo','tpal')
          tver = self.alias('AppVersion','tver')
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          sel  = sqlalchemy.select([self.col(tprd,'Name')],
                   from_obj=[
                      tprd.outerjoin(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                      .outerjoin(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                      .outerjoin(tver,onclause=self.col(talc,'ApplicationVersion')==self.col(tver,'ID'))
                      .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                      .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                      .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                            ],distinct=True,
                   order_by=[self.col(tprd,'Name')]
                             )
#          if group and group!="*":
#             sel.append_whereclause(self.col(tphg,'Name')==rel)
          if rel and rel!="*":
             sel.append_whereclause(self.col(tver,'Version')==rel)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             sel.append_whereclause(self.col(tdt,'Name')==tier)
          print str(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in getUserData"
      oList=[]
      for item in result:
          pset = item[0]
          if not pset: continue
          oList.append("/%s/%s/%s"%(prim,pset,tier))
      if self.verbose:
         self.writeLog("time in getUserData: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

  def getData(self,dataset,site="Any",idx=-1):
      """
         Returns 
         blockDict={'blockName': (nEvt,blockStatus,nFiles,blockSize,hostList)}
         locDict={'location': [blockName]}
         and total number of events in given dataset
         The 'location'='N/A' when DLS is not responding, in this case exception message is thrown.
         @type  dataset: string 
         @param dataset: name of the dataset
         @type site: string
         @param site: site name, default 'All'
         @rtype : tuple
         @return: {'location': [blockName]},
         {'blockName': (nEvt,blockStatus,nFiles,blockSize,hostList)}, 
         totalNumberOfEvents, totalNumberOfFiles, totalSize of dataset
      """
      kwargs={'datasetPath':dataset,'site':site,'idx':idx}
      blockInfoDict,totEvts,totFiles,totSize,siteList = self.listBlocks(kwargs)
      return siteList,blockInfoDict,totEvts,totFiles,sizeFormat(totSize)

  def getBlockInfoForSite(self,site):
      if site.lower()=='all' or site.lower()=='any': site="*"
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tblk = self.alias('Block','tblk')
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          oSel = [self.col(tblk,'Name'),self.col(tblk,'BlockSize'),self.col(tblk,'NumberOfFiles'),self.col(tblk,'NumberOfEvents'),self.col(tblk,'OpenForWriting'),self.col(tp1,'DistinguishedName'),self.col(tblk,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tblk,'LastModificationDate'),self.col(tse,'SEName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                     tblk.outerjoin(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                     .outerjoin(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                     .outerjoin(tp1,onclause=self.col(tblk,'CreatedBy')==self.col(tp1,'ID'))
                     .outerjoin(tp2,onclause=self.col(tblk,'LastModifiedBy')==self.col(tp2,'ID'))
                            ],distinct=True,order_by=oSel
                                 )
          if site!="*":
             sel.append_whereclause(self.col(tse,'SEName')==site)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in listBlocksFromSite"
      aList=[]
      aDict={}
      for item in result:
          blockName,blockSize,nFiles,nEvts,blockStatus,cBy,cDate,mBy,mDate,sename=item
          if not blockName: continue
          aDict={'Name':blockName,'BlockSize':blockSize,'NumberOfFiles':nFiles,'NumberOfEvents':nEvts,'OpenForWriting':blockStatus,'CreatedBy':cBy,'CreationDate':cDate,'LastModifiedBy':mBy,'LastModificationDate':mDate}
          aList.append(aDict)
      if self.verbose:
         self.writeLog("time listBlocksFromSite: %s"%(time.time()-t1))
      self.closeConnection(con)
      return aList

  def getBlocksFromSite(self,site):
      if site.lower()=='all' or site.lower()=='any': site="*"
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tblk = self.alias('Block','tblk')
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')

          oSel = [self.col(tblk,'Name')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                     tblk.outerjoin(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                     .outerjoin(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                            ],distinct=True,order_by=oSel
                                 )
          if site!="*":
             sel.append_whereclause(self.col(tse,'SEName')==site)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          printExcept()
          raise "Fail in listBlocksFromSite"
      aList=[]
      for item in result:
          aList.append(item[0])
      if self.verbose:
         self.writeLog("time listBlocksFromSite: %s"%(time.time()-t1))
      self.closeConnection(con)
      return aList

  def getBlocksFromSite_dls(self,site):
      """
         Use DLS api to get block names for given site.
         @type  site: string
         @param site: site name 
         @rtype : list
         @return: list of block names
      """
      bList = []
      # query DLS
      try:
          self.setDLS_LFC()
          blockList = self.dls_iface.getFileBlocks([site], session=True)
          for entry in blockList:
              bList.append(entry.fileBlock.name)
      except:
          printExcept()
          pass
      return bList
      
def formAppPath(iAppString):
    """
       Helper function to construct application path out of given pattern
       @type  iAppString: string 
       @param iAppString: application pattern, e.g. CMSSW_0_8_1,*,* 
       @rtype : string
       @return: path name, e.g. /CMSSW_0_8_1/*/* 
    """
    if not iAppString:
       return "/*/*/*"
    s = string.split(iAppString,",")
    if len(s)==1:
       return "/"+s[0]+"*/*/*"
    if len(s)==2:
       return "/"+s[0]+"/"+s[1]+"/*"
    if len(s)==3:
       return "/"+s[0]+"/"+s[1]+"/"+s[2]
    print "Please specify in the following format (including comas): version,family,exe"
    sys.exit(1)
    
    
#
# main
#
if __name__ == "__main__":
    optManager  = DDOptions.DDOptionParser('DDHelper')
    (opts,args) = optManager.getOpt()
#    print "options:  ",opts
#    print "arguments:",args
    
    dbsInst = DBS_DLS_INST.keys()[0]
    if opts.dbsInst:
       dbsInst = opts.dbsInst

    verbose = 0
    if opts.verbose:
       verbose=1

    iface="cgi"
    if opts.iface!="cgi":
       iface = "sqlalchemy"
    helper = DDHelper(dbsInst,iface,verbose)


    #TMP



#    res = helper.api.listProcessedDatasets(patternVer='TestVersion01_20070210_12h28m18s',patternFam='AppFamily01',patternExe='TestExe01')
#    res = helper.getDatasetsFromApp("/TestVersion01_20070210_12h28m18s/AppFamily01/TestExe01","TestPrimary_001_20070210_12h28m18s","*")
#    res = helper.listApplicationConfigs('/AppFamily01/TestExe01/TestVersion01_20070210_12h28m18s')
#    tDict={'PrimaryDataset':['Name'],'ProcessedDataset':['Name'],'AlgorithmConfig':['ApplicationVersion','ApplicationFamily','ApplicationExecutable']}
#    l = helper.formSQLQuery(tDict)
    
    t1=time.time()
    res = helper.listProcessedDatasets()
    print "time DDHelper.listProcessedDatasets:",(time.time()-t1)
    t1=time.time()
    res = helper.listApplicationConfigs("*")
    print "time DDHelper.listApplicationConfigs:",(time.time()-t1)

    dataset="/TestPrimary_001_20070210_12h28m18s/SIM_20070210_12h28m18s/TestProcessed_20070210_12h28m18s"
    t1=time.time()
    res = helper.listBlocks(dataset)
    print "time DDHelper.listBlocks:",(time.time()-t1)

    t1=time.time()
    res = helper.getRuns(dataset)
    print "time DDHelper.getRuns:",(time.time()-t1)
    sys.exit(0)
    
    if opts.dict:
       helper.initJSDict(opts.dict)
       sys.exit(0)

    if opts.search:
       pattern=string.split(opts.search,",")
       print "Search for",pattern
       oList = helper.search(opts.search)
#       pattern=string.split(opts.search,",")
#       oList = helper.search(pattern)
       for item in oList:
           print item
       sys.exit(0)

    datasetPath="*"
    primaryDataset="*"
    dataTier="*"
    appPath="*"
    site=""
    if not opts.primD and not opts.site:
       helper.WhatExists(datasetPath)
       sys.exit(0)

    if opts.primD:
       primaryDataset=opts.primD
    if opts.DT:
       dataTier=opts.DT
       if string.lower(dataTier)=="all":
          dataTier="*"
    if opts.app:
       appPath=formAppPath(opts.app)
    if opts.quiet:
       helper.setQuiet()
    if opts.site:
       site=opts.site
       bList = helper.getBlocksFromSite(site)
       print "Site: '%s'"%site
       for blockName in bList:
           print "      '%s'"%blockName
       sys.exit(0)
       
    if verbose:
       print "appPath",appPath
    appDatasets = helper.getDatasetsFromApp(appPath)
    if verbose:
       print
       printListElements(appDatasets,"appDatasets ")

#    print "Pass search critireas:"
    print

    hostField=0
    t0=time.time()
    for dataset in appDatasets:
        t1 = time.time()
        empty,prim,proc,tier = string.split(dataset,"/")
        if primaryDataset!="*" and prim!=primaryDataset: continue
        if dataTier!="*" and tier!=dataTier: continue
        locDict, blockDict, totEvt, totFiles, totSize = helper.getData(dataset,appPath)
        evtLength = len(str(totEvt))
        if not hostField:
           for key in locDict.keys():
               if len(key)>hostField: hostField=len(key)
        print dataset
        if  not opts.showProcD:
            for bName in blockDict.keys():
                count=0
                if not blockDict[bName][0]:
                   print "contains 0 events, 0 files."
                   continue
                evt      = blockDict[bName][0]
                bStatus  = blockDict[bName][1]
                nFiles   = blockDict[bName][2]
                bSize    = blockDict[bName][3]
                siteList = blockDict[bName][4:]
                for idx in xrange(0,len(siteList)):
                    site=siteList[idx]
                    if not idx:
                       print string.ljust(site,hostField),string.ljust(str(evt),evtLength),bName
                    else:
                       empty = " "*(hostField)
                       print empty,string.ljust(str(evt),evtLength),bName
            print "Summary: %s events, %s files, %s"%(totEvt,totFiles,totSize)
            print
        print "time: %s sec"%(time.time()-t1)
    print "total time: %s sec for %s datasets"%((time.time()-t0),len(appDatasets))

