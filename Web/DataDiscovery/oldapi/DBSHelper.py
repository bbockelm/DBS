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
import DBSOptions
from   DDConfig   import *
from   DBSInst    import * # defines DBS instances and schema
from   DBSUtil    import * # general utils

_ddConfig = DBSDDConfig()
if _ddConfig.iface()=='cgi':
   import DBSAPIOLD.dbsCgiApi as dbsCgiApi
   import DBSAPIOLD.dbsException as dbsException
   from   DBSAPIOLD.dbsApi import DbsApi, DbsApiException, InvalidDataTier, DbsDatabaseError
else:
   import DBSAPI.dbsWebApi as dbsWebApi
   import DBSAPI.dbsException as dbsException
   from   DBSAPI.dbsApi import DbsApi, DbsApiException, InvalidDataTier, DbsDatabaseError

# import DLS modules
import dlsClient
import dlsApi


class DBSHelper(DDLogger): 
  """
      DBSHelper class
  """
  def __init__(self,dbsInst=DBSGLOBAL,iface="cgi",verbose=0,html=0):
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
      DDLogger.__init__(self,"DBSHelper",verbose)
      self.iface       = string.lower(iface)
      self.dbsInstance = dbsInst
      self.dbsdls      = DBS_DLS_INST
      self.verbose     = verbose
      self.html        = html
      self.datasetPath = "*"# default path to entire content of DBS
      # cache
      self.blockDict   = {} #  {'dataset': {'fileBlock': [LFNs]}}
      try:
         self.dbManager      = DBSDB(self.iface,self.verbose)
#         self.dbsDB       = self.dbManager.engine #  {'dbsInst': DBSDB }
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


  def setQuiet(self):
      self.quiet=1

  def rssMaker(self,dbsInst):
      ddConfig  = DBSDDConfig()
      url = ddConfig.url()
      self.setDBSDLS(dbsInst)
      aList = self.listApplications()
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
          for p in pList:
              page+="<item>"
              datasetName = p['datasetPathName']
              empty,prim,tier,proc = string.split(datasetName,"/")
              if not os.path.isdir('rss/%s/%s/%s'%(dbsInst,appPath,prim)):
                 os.makedirs(os.path.join(os.getcwd(),'rss/%s/%s/%s'%(dbsInst,appPath,prim)))
              fList = self.api.listFiles(datasetName)
              evt=0
              for item in fList:
                  evt+=item['NumberOfEvents']
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
                  empty,primD,tier,proc = string.split( path, "/" )
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
                  empty,primD,tier,proc = string.split( path, "/" )
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
         is done via L{DBSInst.DBSDB} call. For DBS/DLS we use dbsCgiApi and dlsClient.getDlsApi calls,
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
            self.api = dbsWebApi.DbsWebApi({'url':url})
#            con = self.connectToDB()
#            con.close()
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

  ### WRAPPER ###
  def listApplicationConfigs(self,appPath):
      """
         Wrapper around dbsApi
      """
      res = []
      if self.iface=="cgi":
         for item in self.api.listApplicationConfigs(appPath):
             content = item['parameterSet']['content']
             res.append(('content',content))
             hash    = item['parameterSet']['hash']
             res.append(('hash',hash))
         return res
      else:
         ver=family=exe="*"
         if appPath and appPath!="*":
            empty,ver,family,exe=string.split(appPath,"/")
         res = self.api.listAlgorithms(patternVer=ver,patternFam=family,patternExe=exe)
         cList=[]
         for item in res:
             content = item['ParameterSetID']['Content']
             cList.append(content)
         return cList 

  def listProcessedDatasets(self,datasetPath="*"):
      """
         Wrapper around dbsApi
      """
      res = ""
      if self.iface=="cgi":
         res = self.api.listProcessedDatasets(datasetPath)
      else:
         if datasetPath=="*":
            prim=tier=proc="*"
         else:
            empty,prim,tier,proc=string.split(datasetPath,"/")
         res = self.api.listProcessedDatasets(patternPrim=prim,patternDT=tier,patternProc=proc)
#         oList = []
#         for item in res:
#             proc = item['Name']
#             prim = item['PrimaryDataset']['Name']
#             tiers= item['TierList']
#             for tier in tiers:
#                 oList.append("/%s/%s/%s"%(prim,tier,proc))
#         res = oList
      return res

  def listDatasetsFromApp(self,appPath="*"):
      """
         Wrapper around dbsApi
      """
      if self.iface=="cgi":
         return self.api.listDatasetsFromApp(appPath)
      else:
         if appPath=="*":
            ver=family=exe="*"
         else:
            empty,ver,family,exe=string.split(appPath,"/")
#            print "\n\n#####listDatasetsFromApp",appPath,ver,family,exe,self.dbsInstance
         return self.api.listProcessedDatasets(patternVer=ver,patternFam=family,patternExe=exe)

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

  def listBlocks(self,datasetPath="*",app="*",events="yes"):
      """
         Wrapper around dbsApi
      """
      if self.iface=="cgi":
         return self.api.listBlocks(datasetPath,app,events)
      else:
         return self.api.listBlocks(datasetPath)
  ### END OF WRAPPER ###
  def getPhysicsGroups(self):
      return ['Group1','Group2']
  def getDataTypes(self):
      return ['Type1','Type2']

  def getDataDescription(self,primaryDataset="",processedDataset=""):
      if self.iface=="cgi":
         return {}
      else:
         if processedDataset:
            empty,prim,tier,proc=string.split(processedDataset,"/")
            return self.api.getDatasetDetails(patternPrim=prim,patternDT=tier,patternProc=proc)

  def getDatasetsFromApplications(self,datasetPath="*"):
      """
         DBS data discovery wrapper around dbsCgiApi.getDatasetsFromApp.
         It makes a dictionary of all applications.
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : dictionary
         @return: dict[appPath]=[/primD/tier/proc,]
      """
      t1=time.time()
      aDict = {}
      iList = self.listApplications(datasetPath)
      for item in iList:
          if self.iface=="cgi":
             family = item.get('family')
             ver    = item.get('version')
             exe    = item.get('executable')
             path=formDatasetPath(ver,family,exe)
          else:
#             family = item.get('ApplicationFamily')
#             ver    = item.get('ApplicationVersion')
#             exe    = item.get('ExecutableName')
             path=item
          pList = self.listDatasetsFromApp(path)
#          print "\n\n#### getDatasetsFromApplications",item,pList
          for p in pList:
#              empty,primD,tier,proc = string.split( p['datasetPathName'], "/" )
#              addToDict(aDict,path,(primD,tier,proc))
              procDatasetName = p['datasetPathName']
              addToDict(aDict,path,procDatasetName)
      print "time:",(time.time()-t1)
      return aDict

  def getApplications(self,datasetPath="*"):
      """
         DBS data discovery wrapper around dbsCgiApi.listApplications
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : list 
         @return: a list of application in the following form, [(family,version,exe)]
      """
      aList = []
      dList = self.listApplications(datasetPath)
      for item in dList:
          if self.iface=="cgi":
             family = item.get('family')
             ver    = item.get('version')
             exe    = item.get('executable')
             path=formDatasetPath(ver,family,exe)
          else:
#             family = item.get('ApplicationFamily')
#             ver    = item.get('ApplicationVersion')
#             exe    = item.get('ExecutableName')
             path=item
          if self.html:
             navBar   ="MakeNavBarApp('%s','%s')"%(self.dbsInstance,path)
             dataInfo ="ajaxGetData('%s','all','%s','*','*','*')"%(self.dbsInstance,path)
             blockInfo="ajaxGetDbsData('%s','all','%s','*','*','*')"%(self.dbsInstance,path)
             runInfo  ="ajaxGetRuns('%s','all','%s','*','*','*')"%(self.dbsInstance,path)
             path="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;%s;%s">%s</a>"""%(navBar,dataInfo,blockInfo,runInfo,path)
          aList.append(path)
      aList.sort()
      aList.reverse()
      return aList

  def getSoftwareReleases(self):
      """
         DBS data discovery wrapper around listApplications
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : list 
         @return: a list of application in the following form, [(family,version,exe)]
      """
      aList = []
      dList = self.listApplications()
      for item in dList:
          path=item
          empty,soft,fam,exe=string.split(path,'/')
          aList.append(soft)
      aList.sort()
      aList.reverse()
      return aList

  def getDatasetsFromApp(self,appPath="*",_prim="*",_tier="*"):
      """
         DBS data discovery wrapper around dbsCgiApi.listDatasetsFromApp
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : list 
         @return: a list of datasets from application in the following form, [datasetPathName]
      """
      oList = []
      dList = self.listDatasetsFromApp(appPath)
      for entry in dList:
          dataset = entry.get('datasetPathName')
          empty,prim,tier,app = string.split(dataset,"/")
          if _prim!="*" and prim!=_prim: continue
          if _tier!="*" and tier!=_tier: continue
          oList.append(dataset)
      oList.sort()
      oList.reverse()
      return oList
      
  def getPrimaryDatasets(self,datasetPath="*",html=0):
      """
         DBS data discovery wrapper around dbsCgiApi.listPrimaryDatasets
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : list 
         @return: a list of primary datasets in the following form, [datasetName]
      """
      oList = []
      dList = self.api.listPrimaryDatasets(datasetPath)
      dList.sort()
      dList.reverse()
      if self.verbose:
         print "For given",datasetPath
         print printListElements(dList,"Found a list of primary datasets")
      for entry in dList:
          if self.iface=="cgi":
             name = entry.get('datasetName')
          else:
             name = entry.get('Name')
          if html:
#          if self.html:
             navBar   ="MakeNavBarPrimDS('%s','%s')"%(self.dbsInstance,name)
             dataInfo ="ajaxGetData('%s','all','*','%s','*','*')"%(self.dbsInstance,name)
             blockInfo="ajaxGetDbsData('%s','all','*','%s','*','*')"%(self.dbsInstance,name)
             runInfo  ="ajaxGetRuns('%s','all','*','%s','*','*')"%(self.dbsInstance,name)
             name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;%s;%s">%s</a>"""%(navBar,dataInfo,blockInfo,runInfo,name)
          oList.append(name)
      return oList
      
  def getProcessedDatasets(self,datasetPath="*",app=1,html=0):
      """
         DBS data discovery wrapper around dbsCgiApi.listDatasetFromApp/listProcessedDatsets
         First try listDatasetsFromApp, if fail try listProcessedDatasets
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : list 
         @return: a list of processed datasets in the following form, [datasetName]
      """
      oList = []
      if app:
         dList = self.listDatasetsFromApp(datasetPath)
      else:
         dList = self.listProcessedDatasets(datasetPath)
      dList.sort()
      dList.reverse()
      for entry in dList:
          if self.iface=="cgi":
             name = entry.get('datasetPathName') # name=/prim/tier/proc
          else:
             name = entry.get('datasetPathName') # name=/prim/tier/proc
          if html:
             navBar   ="MakeNavBarProcDS('%s','%s')"%(self.dbsInstance,name)
             dataInfo ="ajaxGetData('%s','all','*','*','*','%s')"%(self.dbsInstance,name)
             blockInfo="ajaxGetDbsData('%s','all','*','*','*','%s')"%(self.dbsInstance,name)
             runInfo  ="ajaxGetRuns('%s','all','*','*','*','%s')"%(self.dbsInstance,name)
             name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;%s;%s">%s</a>"""%(navBar,dataInfo,blockInfo,runInfo,name)
          oList.append(name)
      return oList
  
  def getDatasetContent(self,dataset):
      content = self.api.getDatasetContents(dataset)
      return content

  def getDatasetProvenance(self,dataset):
#      print "search",dataset
      pList=[]
      for parent in self.api.getDatasetProvenance(dataset):
          p=parent['parent']['datasetPathName']
#          print "child",p
          if not p: break
          pList.append(p)
          pList+=self.getDatasetProvenance(p)
      return pList

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

  def connectToDB(self):
#      self.setDBSDLS(self.dbsInstance)
      con=""
      try:
          con = self.dbManager.connect(self.dbsInstance)
      except Exception, ex:
         # if we fail because of connection drop let's reconnect again
	 try:
             # try second time, but sleep for 2 seconds before retry
             time.sleep(2)
             con = self.dbManager.connect(self.dbsInstance)
         except Exception, ex:
             raise DbsDatabaseError(args=ex)
         pass
      return con

  def getSQLAlchemyResult(self,con,sel):
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
#          if not con:
#             con = self.connectToDB()
          res = con.execute(sel)
#          con.close()
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
          con.close()
      except Exception, ex:
         # if we fail because of connection drop let's reconnect again
	 try:
             # try second time, but sleep for 2 seconds before retry
             time.sleep(2)
             self.dbManager.connect()
#             con = self.dbManager.engine[self.dbsInstance].connect()
             con = self.dbManager.connect(self.dbsInstance)
             res = con.execute(sel)
             con.close()
         except Exception, ex:
             raise DbsDatabaseError(args=ex)
         pass
      return res

  def getTableContent(self,tableName,iList=['*'],fromRow=1,limit=0):
      try:
          con = self.connectToDB()
          if limit:
             sel = sqlalchemy.select(iList, from_obj=[tableName], limit=limit, offset=fromRow)
          else:
             sel = sqlalchemy.select(iList, from_obj=[tableName])
          result = self.getSQLAlchemyResult(con,sel)
          if  self.verbose:
              print "getTableContent",tableName,iList
              for item in result:
                  print item
          con.close()
      except:
          printExcept()
          raise "Fail to get table='%s' content"%tableName
      return result

  def getTableColumns(self,tableName):
      res=[]
      try:
#          con = self.connectToDB()
          res = self.dbManager.getColumns(self.dbsInstance,tableName)
#          con.close()
      except:
          printExcept()
          raise "Fail to get table='%s' columns"%tableName
      return res

  def formSQLQuery(self,tDict):
      con = self.connectToDB()
      print "###formSQLQuery",tDict.keys()
#      tableName=tList[0]

      cols = []
      for tableName in tDict.keys():
          iList = tDict[tableName]
          # first get table content and check if input list of columns names match
          cList=self.dbManager.getColumns(self.dbsInstance,tableName)
          if len(iList):
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
          print page
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
                     con.close()
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
#                     con.close()
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
          
      con.close() 

  def formQuery(self):
      return

  def getLFNs(self,dbsInst,blockName,dataset):
      self.setDBSDLS(dbsInst)
      res = self.api.getLFNs(blockName,dataset)
      return res

  def getLFN_Branches(self,dbsInst,lfn):
      self.setDBSDLS(dbsInst)
      res = self.api.listFileBranches(lfn)
      return res

  def getLFN_Lumis(self,dbsInst,lfn):
      self.setDBSDLS(dbsInst)
      res = self.api.listFileLumis(lfn)
      return res

  def getLFN_Algos(self,dbsInst,lfn):
      self.setDBSDLS(dbsInst)
      res = self.api.listFileAlgorithms(lfn)
      return res

  def getLFN_Tiers(self,dbsInst,lfn):
      self.setDBSDLS(dbsInst)
      res = self.api.listFileTiers(lfn)
      return res

  def getLFN_Parents(self,dbsInst,lfn):
      self.setDBSDLS(dbsInst)
      res = self.api.listFileParents(lfn)
      return res

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
      con.close()
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
      con.close()
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
      if self.iface=='cgi':
         return []
      else:
         return self.api.listRuns(dataset)

  def getDbsData(self,dataset):
      if self.iface=='cgi':
         return []
      else:
         return self.api.listBlocksFull(dataset=dataset)

  def getDbsBlockData(self,blockName):
      if self.iface=='cgi':
         return []
#         d=[{'OpenForWriting': '1', 'CreationDate': '1167939056000', 'Name': blockName, 'NumberOfEvents': 0, 'BlockSize': 0, 'NumberOfFiles': 0, 'FileList': [], 'LastModifiedBy': 'ANZARDN', 'CreatedBy': 'ANZARDN', 'StorageElementList': [], 'LastModificationDate': '2007-01-04 14:30:56.0'}]
#         return [d]
      else:
         return self.api.listBlocksFull(block_name=blockName)

  def getData(self,dataset,app,site="All"):
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
      locDict  = {}
      nEvts    = totFiles = totSize = self.dbsTime = self.dlsTime = 0

      t1 = time.time()
      blockInfoDict = self.listBlocks(dataset,app,"yes")
      t2 = time.time()
      self.dbsTime=(t2-t1)
      if string.lower(site)=="all": site="*"

      siteList=[]
      for blockName in blockInfoDict.keys():
          evts,bStatus,nFiles,bBytes  = blockInfoDict[blockName]
          if evts:
             nEvts+=evts
          else:
             continue # this eliminates file blocks with no events
          totFiles+=nFiles
          totSize+=bBytes
          # query DLS
          hostList=[]
          try:
              t3 = time.time()
              dlsList = self.dlsApi.getLocations(blockName)
              t4 = time.time()
              self.dlsTime+=(t4-t3)
#              print "dlsTime",self.dlsTime
              for entry in dlsList:
                  for loc in entry.locations:
                      dlsHost = str(loc.host)
                      if site=="*" or dlsHost==site:
                         hostList.append(dlsHost)
                      if not siteList.count(dlsHost): siteList.append(dlsHost)
#                         addToDict(locDict,str(loc.host),blockName)
          except:
              if not self.quiet:
                 printExcept()
              if site=="*":
                 hostList.append('N/A')
              if not siteList.count('N/A'): siteList.append('N/A')
#                 addToDict(locDict,'N/A',blockName)
              pass
          # end of DLS query
          blockInfoDict[blockName]+=hostList
      if self.verbose:
         print "### time =",self.dbsTime,self.dlsTime,(time.time()-t1)
      siteList.sort()
      return siteList,blockInfoDict,nEvts,totFiles,sizeFormat(totSize)

  def getBlocksFromSite(self,site):
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
      
def formDatasetPath(primD="*",tier="*",app="*"):
    """
       Helper function to form path out of primD,tier,app. It can be used to construct
       any path out of three arguments.
       @type  primD: string 
       @param primD: name of primary dataset
       @type tier: string
       @param tier: name of tier
       @type app: string
       @param app: application name
       @rtype : string
       @return: path
    """
    return "/"+primD+"/"+tier+"/"+app
#def formAppPath(ver="*",family="*",exe="*"):
#    return "/"+ver+"/"+family+"/"+exe
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
    optManager  = DBSOptions.DBSOptionParser()
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
       iface = "JavaServer"
    helper = DBSHelper(dbsInst,iface,verbose)


    #TMP



#    res = helper.api.listProcessedDatasets(patternVer='TestVersion01_20070210_12h28m18s',patternFam='AppFamily01',patternExe='TestExe01')
#    res = helper.getDatasetsFromApp("/TestVersion01_20070210_12h28m18s/AppFamily01/TestExe01","TestPrimary_001_20070210_12h28m18s","*")
#    res = helper.listApplicationConfigs('/AppFamily01/TestExe01/TestVersion01_20070210_12h28m18s')
#    tDict={'PrimaryDataset':['Name'],'ProcessedDataset':['Name'],'AlgorithmConfig':['ApplicationVersion','ApplicationFamily','ApplicationExecutable']}
#    l = helper.formSQLQuery(tDict)
    t1=time.time()
    res = helper.listProcessedDatasets()
    print "time DDHelper.listProcessedDatasets:",(time.time()-t1)
#    print res
    t1=time.time()
    res = helper.listApplicationConfigs("*")
    print "time DDHelper.listApplicationConfigs:",(time.time()-t1)
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
        empty,prim,tier,app = string.split(dataset,"/")
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

