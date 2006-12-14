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
import dbsException
import dbsApi
# we try to load dbsCgiApi, since it exists only for CGI server
try:
   import dbsCgiApi
except:
   pass
import DBSOptions
from   dbsApi  import DbsApi, DbsApiException, InvalidDataTier
from   DBSInst import * # defines DBS instances and schema
from   DBSUtil import * # general utils

# import DLS modules
import dlsClient
import dlsApi


class DBSHelper(DBSLogger): 
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
      DBSLogger.__init__(self,"DBSHelper",verbose)
      self.iface       = string.lower(iface)
      self.dbsInstance = dbsInst
      self.dbsdls      = DBS_DLS_INST
      self.verbose     = verbose
      self.html        = html
      self.datasetPath = "*"# default path to entire content of DBS
      # cache
      self.blockDict   = {} #  {'dataset': {'fileBlock': [LFNs]}}
      try:
         self.dbsDBs      = DBSDB(self.verbose)
         self.dbsDB       = self.dbsDBs.engine #  {'dbsInst': DBSDB }
      except:
         print "WARNING! some of the functionality will be disable due to missing authentication"
         if self.verbose:
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

#  def initJSDict(self,userMode=True):
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
#      if userMode:
#         fileName='dbsDict.global.tmp'
#      else:
#         fileName='dbsDict.all.tmp'
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
#      if userMode:
#         dbsList=[DBSGLOBAL]
#      else:
#         dbsList=self.dbsdls.keys()
#         dbsList.sort()
#         dbsList.remove(DBSGLOBAL)
#         dbsList=[DBSGLOBAL]+dbsList
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

  def initJSDict_v1(self,userMode=True):
      """
         Form dictionary for JavaScript used in presentation layer.
         @type  userMode: boolean 
         @param userMode: define which DBS instance(s) to use, in userMode we only use DBS global,
         for experts others instances has been initialized.
         @rtype : dictionary
         @return: { DBSInst: { dbs:appDict={ app:primDict={ primD:tierDict={ tier:null } } }, } }
      """
      # if we're in user mode, we only know about DBSGLOBAL
      # if we're in expert mode, load all DBS instances
      if userMode:
         dbsList=[DBSGLOBAL]
      else:
         dbsList=self.dbsdls.keys()
      dbsDict = {}
      for dbs in dbsList:
          self.setDBSDLS(dbs)
          appList = self.getApplications()
          appDict = {}
          for app in appList:
              family = app[0]
              ver    = app[1]
              exe    = app[2]
              path=formDatasetPath(ver,family,exe)
              primDict = {}
              tierDict = {}
              pList = self.listDatasetsFromApp(path)
              pList.sort()
	      tierDict['All']="*"
              oldPrimD="All"
              for item in pList:
                  empty,primD,tier,proc = string.split( item['datasetPathName'], "/" )
	 	  print primD,tier,proc 
                  if primD!=oldPrimD:
                     oldPrimD=primD
                  tierDict[tier]=""
              primDict[oldPrimD]=tierDict
              appDict[path]=primDict
          dbsDict[dbs]=appDict
      return dbsDict

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
            self.api = dbsApi.DbsApi()
      else:
         self.api = self.dbsApi[dbsInst]
      if not self.dbsDLS.has_key(dbsInst):
         dlsType,endpoint = DBS_DLS_INST[dbsInst]
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
      dlsType,endpoint = DBS_DLS_INST[self.dbsInstance]
      # replace DLI type with LFC
      if dlsType=="DLS_TYPE_DLI":
         dlsType ="DLS_TYPE_LFC"
      if (time.time()-self.voms_timer)>12*60*60: # more then 12 hours
         self.voms_timer=time.time()
#         res = popen2.Popen4("voms-proxy-init -voms cms -q")
         res = popen2.Popen4("cat $HOME/.globus/pp.txt | grid-proxy-init -pwstdin -q")
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

  def listDatasetsFromApp(self,appPath="*"):
      """
         Wrapper around dbsApi
      """
      if self.iface=="cgi":
         return self.api.listDatasetsFromApp(appPath)
      else:
         empty,ver,family,exe=string.split(appPath,"/")
         return self.api.listProcessedDatasets(patternVer=ver,patternFam=family,patternExe=exe)

  def listApplications(self,datasetPath="*"):
      """
         Wrapper around dbsApi
      """
      if self.iface=="cgi":
         aList = self.api.listApplications(datasetPath)
         aList.sort()
         aList.reverse()
         return aList
      else:
         return self.api.listAlgorithms(datasetPath)

  def listBlocks(self,datasetPath="*",app="*",events="yes"):
      """
         Wrapper around dbsApi
      """
      if self.iface=="cgi":
         aList = self.api.listBlocks(datasetPath,app,events)
#         aList.sort()
#         aList.reverse()
         return aList
      else:
         return self.api.listBlocks(datasetPath)

  def getDatasetsFromApplications(self,datasetPath="*"):
      """
         DBS data discovery wrapper around dbsCgiApi.getDatasetsFromApp.
         It makes a dictionary of all applications.
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : dictionary
         @return: dict[appPath]=[(primD,tier,proc)]
      """
      aDict = {}
      iList = self.listApplications(datasetPath)
      for item in iList:
          if self.iface=="cgi":
             family = item.get('family')
             ver    = item.get('version')
             exe    = item.get('executable')
          else:
             family = item.get('ApplicationFamily')
             ver    = item.get('ApplicationVersion')
             exe    = item.get('ExecutableName')
          path=formDatasetPath(ver,family,exe)
          pList = self.listDatasetsFromApp(path)
          for p in pList:
#              empty,primD,tier,proc = string.split( p['datasetPathName'], "/" )
#              addToDict(aDict,path,(primD,tier,proc))
              procDatasetName = p['datasetPathName']
              addToDict(aDict,path,procDatasetName)
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
          else:
             family = item.get('ApplicationFamily')
             ver    = item.get('ApplicationVersion')
             exe    = item.get('ExecutableName')
          path=formDatasetPath(ver,family,exe)
          if self.html:
#             path = """<a href="javascript:showWaitingMessage();registerAjaxGetDatasetsFromApplicationCalls();ajaxGetDatasetsFromApplication('%s','%s')">%s</a>"""%(self.dbsInstance,path,path)
             path = """<a href="javascript:showWaitingMessage();ajaxGetDatasetsFromApplication('%s','%s')">%s</a>"""%(self.dbsInstance,path,path)
          aList.append(path)
      aList.sort()
      aList.reverse()
      return aList

  def getDatasetsFromApp(self,datasetPath="*"):
      """
         DBS data discovery wrapper around dbsCgiApi.listDatasetsFromApp
         @type  datasetPath: string 
         @param datasetPath: dataset path 
         @rtype : list 
         @return: a list of datasets from application in the following form, [datasetPathName]
      """
      oList = []
      dList = self.listDatasetsFromApp(datasetPath)
      dList.sort()
      dList.reverse()
      for entry in dList:
          oList.append(entry.get('datasetPathName'))
      return oList
      
  def getPrimaryDatasets(self,datasetPath="*"):
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
          if self.html:
#             name = """<a href="javascript:showWaitingMessage();registerAjaxGetDetailsForPrimDatasetCalls();ajaxGetDetailsForPrimDataset('%s','%s')">%s</a>"""%(self.dbsInstance,name,name)
             name = """<a href="javascript:showWaitingMessage();ajaxGetDetailsForPrimDataset('%s','%s')">%s</a>"""%(self.dbsInstance,name,name)
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
         dList = self.api.listProcessedDatasets(datasetPath)
      dList.sort()
      dList.reverse()
      for entry in dList:
          if self.iface=="cgi":
             name = entry.get('datasetPathName')
          else:
             name = entry.get('Name')
          if html:
#             name = """<a href="javascript:showWaitingMessage();registerAjaxDatasetContentCalls();ajaxGetDatasetContent('%s','%s')">%s</a>"""%(self.dbsInstance,name,name)
             name = """<a href="javascript:showWaitingMessage();ajaxGetDatasetContent('%s','%s')">%s</a>"""%(self.dbsInstance,name,name)
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
#          while 1:
#             print "in while search",p
#             pp=self.getDatasetProvenance(p)
#             if not pp: break
#             print "in while child",pp
#             pList+=pp
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
         result= self.dbsDB[self.dbsInstance].dbsEngine.execute(q)
      except:
         # if we fail because of connection drop let's reconnect again
	 try:
            self.setDBSDLS(self.dbsInstance)
	    result= self.dbsDB[self.dbsInstance].dbsEngine.execute(q)
	 except:
            printExcept()
            raise "Fail to execute \n\n%s\n\n"%q
	 pass
      return result

  def getSQLAlchemyResult(self,sel):
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
          con = self.dbsDBs.engine[self.dbsInstance].connect()
          res = con.execute(sel)
          con.close()
      except Exception, ex:
         # if we fail because of connection drop let's reconnect again
	 try:
             # try second time, but sleep for 2 seconds before retry
             time.sleep(2)
             self.dbsDBs.connect()
             con = self.dbsDBs.engine[self.dbsInstance].connect()
             res = con.execute(sel)
             con.close()
         except Exception, ex:
             raise DbsDatabaseError(args=ex)
         pass
      return res

  def getLFNs(self,dbsInst,blockName,dataset):
      self.setDBSDLS(dbsInst)
      res = self.api.getLFNs(blockName,dataset)
      return res
#      print "### LFN",res[0]
#      lfnList = []
#      for item in res:
#          lfn   = item[0]
#          fSize = item[1]
#          status= item[2]
#          type  = item[3]
#          evts  = item[4]
          # item=(id,logical_name,fileSize,status,type)
#          lfnList.append((lfn,fSize,status,type,evts))
#      print "### new list",lfnList[0]
#      return lfnList

  def getLFNs_bName(self,blockName):
      """
         Get list of LFNs for block name.
         The following query is invoked:
         
         select
         f.logical_name,
         f.filesize,
         fs.name,
         ft.name
         from t_block b
         join t_block_status bs
         on bs.id = b.status
         left join t_file f
         on f.inblock = b.id
         left join t_file_status fs
         on fs.id = f.status
         left join t_file_type ft
         on ft.id = f.type
         where b.guid= :bid
            
         @type blockName: string
         @param blockName: name of the file block
         @rtype : list 
         @return: list of LFNs
      """
      blockbase,blockId = string.split(blockName,"#")
      guid=""
      if len(blockId)>4: # this is guid
         if blockName[0]!='/':
            guid='/'+blockName
         else:
            guid=blockName

      tb  = self.alias('t_block','tb')
      tbs = self.alias('t_block_status','tbs')
      tf  = self.alias('t_file','tf')
      tfs = self.alias('t_file_status','tfs')
      tft = self.alias('t_file_type','tft')
      if guid:
          sel = sqlalchemy.select([tf.c.logical_name,tf.c.filesize,tfs.c.name,tft.c.name],
                       sqlalchemy.and_( tb.c.guid==guid ),
                       from_obj=[
                                  tb.outerjoin(tbs,onclause=tbs.c.id==tb.c.status)
                                  .outerjoin(tf,onclause=tf.c.inblock==tb.c.id)
                                  .outerjoin(tfs,onclause=tfs.c.id==tf.c.status)
                                  .outerjoin(tft,onclause=tft.c.id==tf.c.type)
                                           ],
                       order_by=[tf.c.logical_name]
                                 )
      else:
          sel = sqlalchemy.select([tf.c.logical_name,tf.c.filesize,tfs.c.name,tft.c.name],
                       sqlalchemy.and_( tb.c.id==blockId ),
                       from_obj=[
                                  tb.join(tbs,onclause=tbs.c.id==tb.c.status)
                                  .outerjoin(tf,onclause=tf.c.inblock==tb.c.id)
                                  .outerjoin(tfs,onclause=tfs.c.id==tf.c.status)
                                  .outerjoin(tft,onclause=tft.c.id==tf.c.type)
                                           ],
                       order_by=[tf.c.logical_name]
                                 )
      res = self.getSQLAlchemyResult(sel)
      lfnList = []
      for item in res:
	  lfn   = item[0]
	  fSize = item[1]
	  status= item[2]
	  type  = item[3]
          # item=(id,logical_name,fileSize,status,type)
          lfnList.append((lfn,fSize,status,type))
      return lfnList

  def getLFNs_orig(self,dataset,blockName):
      """
         Get list of LFNs for given dataset and block name.
         The following query is invoked:
         
         select
         f.logical_name,
         f.filesize,
         fs.name,
         ft.name
         from t_processed_dataset pd
         join t_processing p
         on p.primary_dataset = pd.primary_dataset
         and p.name = pd.name
         join t_block b
         on b.processing = p.id
         join t_block_status bs
         on bs.id = b.status
         left join t_file f
         on f.inblock = b.id
         left join t_file_status fs
         on fs.id = f.status
         left join t_file_type ft
         on ft.id = f.type
         where pd.id= :pid and b.id= :bid
            
         @type  dataset: string 
         @param dataset: dataset name 
         @type blockName: string
         @param blockName: name of the file block
         @rtype : list 
         @return: list of LFNs
      """
      pid = self.datasetFromPath(dataset)
      if not pid:
         return []

      blockbase,blockId = string.split(blockName,"#")

      tpd = self.alias('t_processed_dataset','tpd')
      tp  = self.alias('t_processing','tp')
      tb  = self.alias('t_block','tb')
      tbs = self.alias('t_block_status','tbs')
      tf  = self.alias('t_file','tf')
      tfs = self.alias('t_file_status','tfs')
      tft = self.alias('t_file_type','tft')
      sel = sqlalchemy.select([tf.c.logical_name,tf.c.filesize,tfs.c.name,tft.c.name],
                   sqlalchemy.and_( tpd.c.id==pid,tb.c.id==blockId ),
                   from_obj=[
                              tpd.join(tp,onclause=tp.c.primary_dataset==tpd.c.primary_dataset)
                              .join(tb,onclause=tb.c.processing==tp.c.id)
                              .join(tbs,onclause=tbs.c.id==tb.c.status)
                              .outerjoin(tf,onclause=tf.c.inblock==tb.c.id)
                              .outerjoin(tfs,onclause=tfs.c.id==tf.c.status)
                              .outerjoin(tft,onclause=tft.c.id==tf.c.type)
                                       ],
                   order_by=[tf.c.logical_name]
                             )
#      sel.append_whereclause(tfs.c.name!='invalid')
      res = self.getSQLAlchemyResult(sel)
      lfnList = []
      for item in res:
	  lfn   = item[0]
	  fSize = item[1]
	  status= item[2]
	  type  = item[3]
          # item=(id,logical_name,fileSize,status,type)
          lfnList.append((lfn,fSize,status,type))
      return lfnList

  def datasetFromPath(self,path):
      """
         Get dataset path id. The following query has been invoked:

         select procds.id
         from t_processed_dataset procds
         join t_primary_dataset primds
         on primds.id = procds.primary_dataset
         join t_processing_name proname
         on proname.id = procds.name
         join t_data_tier dt
         on dt.id = procds.data_tier
         where proname.name = :proname
         and primds.name = :primD
         and dt.name = :tier
              
         @type  path: string 
         @param path: path 
         @rtype : integer
         @return: path id
      """
      empty,prim,tier,proc = string.split(path,"/")
      # I need to get a few tables
      tprd = self.alias('t_processed_dataset','tprd')
      tpm  = self.alias('t_primary_dataset','tpm')
      tpn  = self.alias('t_processing_name','tpn')
      tdt  = self.alias('t_data_tier','tdt')
      
      sel  = sqlalchemy.select([tprd.c.id],tpm.c.id==tprd.c.primary_dataset,distinct=True)
      sel.append_whereclause(tpn.c.id==tprd.c.name)
      sel.append_whereclause(tdt.c.id==tprd.c.data_tier)
      if proc and proc!="*":
         sel.append_whereclause(tpn.c.name==proc)
      if prim and prim!="*":
         sel.append_whereclause(tpm.c.name==prim)
      if tier and tier!="*":
         sel.append_whereclause(tdt.c.name==tier)
      res = self.getSQLAlchemyResult(sel)
      return res.fetchone()[0]

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
      return self.dbsDBs.getTable(tableName,aliasName)
      
  def search(self,searchString,pList=[],rDict={}):
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
      if not validator(searchString):
         msg = "Wrong expression '%s'"%searchString
         raise msg
      tprd = self.alias('t_processed_dataset','tprd')
      tpm  = self.alias('t_primary_dataset','tpm')
      tpn  = self.alias('t_processing_name','tpn')
      tp   = self.alias('t_processing','tp')
      tdt  = self.alias('t_data_tier','tdt')
      tapp = self.alias('t_application','tapp')
      tapc = self.alias('t_app_config','tapc')
      tapf = self.alias('t_app_family','tapf')
      tpset= self.alias('t_parameter_set','tpset')
      sel = sqlalchemy.select([tpm.c.name,tdt.c.name,tapp.c.app_version,
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
      result = self.getSQLAlchemyResult(sel)
      oList  = []
      # loop over results and find a match to list of keywords from pList.
      for iTup in result:
          tup = iTup.__dict__['_RowProxy__row'] # get real tuple, rather then instance from SQL object
          searchList=toLower(tupleToList(tup))
#          print searchList,searchString,constructExpression(searchString,'searchList')
          if eval(constructExpression(searchString,'searchList')):
             oList.append((self.dbsInstance,)+tup)
      return oList

  def getBlockInfo_orig(self,dataset):
      """
         Retrieves information about blocks. What we need to know for data discovery is only
         block name and total number of events for given dataset.
         The main query is:
         
         select distinct b.id,bs.name,b.files
         from t_event_collection evc
         join t_evcoll_file evf
         on evf.evcoll = evc.id
         left join t_evcoll_status evs
         on evs.id = evc.status
         join t_file f
         on f.id = evf.fileid
         join t_block b
         on b.id = f.inblock
         join t_block_status bs
         on bs.id = b.status
         left join t_file_status fs
         on fs.id = f.status
         where evc.processed_dataset = :id ORDER by b.id
            
         Also invoke the same query to get total number of events in a block, by replacing select
         with sum(tevc.events) and specifying blockid.
         @type dataset: string
         @param dataset: name of the dataset, e.g. /test_primary/test_tier/test_process 
         @rtype: dictionary
         @return: blockDict[blockName]=(nEvt,bStatus,nFiles)
      """
      id = self.datasetFromPath(dataset)
      if not id:
         return []
         
      # get block name (t_primary_dataset.name, t_processing->t_processing_name.name)
      empty,prim,tier,proc = string.split(dataset,"/")
      blockbase = "/"+prim+"/"+proc
      
      # use SQLAlchemy to do the job, rather then writing pure SQL
      tevc= self.alias('t_event_collection','tevc')
      tevf= self.alias('t_evcoll_file','tevf')
      tevs= self.alias('t_evcoll_status','tevs')
      tb  = self.alias('t_block','tb')
      tbs = self.alias('t_block_status','tbs')
      tf  = self.alias('t_file','tf')
      tfs = self.alias('t_file_status','tfs')
      sel = sqlalchemy.select([tb.c.id,tbs.c.name,tb.c.files,tfs.c.name],
                   sqlalchemy.and_( tevc.c.processed_dataset==id ),
                   from_obj=[
                              tevc.join(tevf,onclause=tevf.c.evcoll==tevc.c.id)
                              .outerjoin(tevs,onclause=tevs.c.id==tevc.c.status)
                              .join(tf,onclause=tf.c.id==tevf.c.fileid)
                              .join(tb,onclause=tb.c.id==tf.c.inblock)
                              .join(tbs,onclause=tbs.c.id==tb.c.status)
                              .outerjoin(tfs,onclause=tfs.c.id==tf.c.status)
                                       ],
                   order_by=[tb.c.id],distinct=True
                             )
      result = self.getSQLAlchemyResult(sel)
      bDict = {}
      for item in result:
          blockId = str(item[0])
          bStatus = item[1]
          nFiles  = item[2]
          fStatus = str(item[3])
          # I need to check file status to be valid
          if string.lower(fStatus)=='invalid':
             continue
          bName   = blockbase+"#"+blockId
          
          # use SQLAlchemy to do the work
          sel = sqlalchemy.select([sqlalchemy.func.sum(tevc.c.events)],
                   sqlalchemy.and_( tevc.c.processed_dataset==id,tb.c.id==blockId ),
                   from_obj=[
                              tevc.join(tevf,onclause=tevf.c.evcoll==tevc.c.id)
                              .outerjoin(tevs,onclause=tevs.c.id==tevc.c.status)
                              .join(tf,onclause=tf.c.id==tevf.c.fileid)
                              .join(tb,onclause=tb.c.id==tf.c.inblock)
                              .join(tbs,onclause=tbs.c.id==tb.c.status)
                              .outerjoin(tfs,onclause=tfs.c.id==tf.c.status)
                                       ],
                   order_by=[tb.c.id],distinct=True
                             )
          result  = self.getSQLAlchemyResult(sel) 
          nEvt = result.fetchone()[0]
          bDict[bName]=(nEvt,bStatus,nFiles)
      return bDict

  def getBlockInfo(self,dataset,app):
#      blocks = self.api.listBlocks(dataset,app,"yes")
      blocks = self.listBlocks(dataset,app)
      return blocks
  
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
      sumDict = {}
      tf  = self.alias('t_file','tf')
      sel = sqlalchemy.select([sqlalchemy.func.count(tf.c.logical_name)])
      sumDict['Number of files'] = self.getSQLAlchemyResult(sel).fetchone()[0]
      sel = sqlalchemy.select([sqlalchemy.func.sum(tf.c.filesize)])
      sumDict['Total file size'] = sizeFormat(self.getSQLAlchemyResult(sel).fetchone()[0])
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
      # IMPORTANT: I think we need to replace listBlocks(dataset) to listBlocksFromApp(app)
#      t1 = time.time()
#      bList=[]
#      if self.iface=='cgi':
#         blockInfoDict = self.listBlocks(dataset,app,"yes")
#         bList = blockInfoDict.values()
#      else:
#         bList = self.listBlocks(dataset,app,"yes")
#      t2 = time.time()
#      self.dbsTime=(t2-t1)

#      for item in bList:
#          print "item=",item
#          if self.iface=='cgi':
#             blockName = item['blockName']
#             evts,bStatus,nFiles,bBytes = item
#          else:
#             blockName = item['Name']
#             evts = 0
#             bStatus = 'N/A'
#             nFiles = item['NumberOfFiles']
#             bBytes = item['BlockSize']
#          
      t1 = time.time()
      blockInfoDict = self.listBlocks(dataset,app,"yes")
      t2 = time.time()
      self.dbsTime=(t2-t1)
      if string.lower(site)=="all": site="*"
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
                         hostList.append(str(loc.host))
                         addToDict(locDict,str(loc.host),blockName)
          except:
              if not self.quiet:
                 printExcept()
              if site=="*":
                 hostList.append('N/A')
                 addToDict(locDict,'N/A',blockName)
              pass
          # end of DLS query
          blockInfoDict[blockName]+=hostList
#      print "+++",blockInfoDict
#      print "###",locDict
      if self.verbose:
         print "### time =",self.dbsTime,self.dlsTime,(time.time()-t1)
      return locDict,blockInfoDict,nEvts,totFiles,sizeFormat(totSize)

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
    if opts.server!="cgi":
       iface = "JavaServer"
    helper = DBSHelper(dbsInst,iface,verbose)
    
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
    for dataset in appDatasets:
        empty,prim,tier,app = string.split(dataset,"/")
        if primaryDataset!="*" and prim!=primaryDataset: continue
        if dataTier!="*" and tier!=dataTier: continue
        locDict, blockDict, totEvt, totFiles, totSize = helper.getData(dataset,appPath)
        evtLength = len(str(totEvt))
        # TMP: redo the following part, since now I got blockDict
        # parse blockDict={'blockName': (hostList,nEvt,blockStatus,nFiles,blockSize)}
        #         locDict={'location': [blockName]}
#        hostField=0
#        for bName in blockDict.keys():
#            for item in blockDict[bName]:
#                siteList = item[0]
#                for site in siteList:
#                    if len(site)>hostField: hostField=len(site)
#        print dataset
#        for bName in blockDict.keys():
#            count=0
#            for item in blockDict[bName]:
#                siteList = item[0]
#                evt      = item[1]
#                bStatus  = item[2]

#                for idx in xrange(0,len(siteList)):
#                    site=siteList[idx]
#                    if not idx:
#                       print string.ljust(site,hostField),string.ljust(str(evt),evtLength),bName
#                    else:
#                       print string.ljust(site,hostField),"replica"
#        print "Summary: %s events, %s files, %s"%(totEvt,totFiles,totSize)
#        print

        evtLength = len(str(totEvt))
        if not hostField:
           for key in locDict.keys():
               if len(key)>hostField: hostField=len(key)
        print dataset
        if  not opts.showProcD:
            for bName in blockDict.keys():
                count=0
#                print "blockDict",bName,blockDict[bName]
                if not blockDict[bName][0]:
                   print "contains 0 events, 0 files."
                   continue
                evt,bStatus,nFiles,bSize,site = blockDict[bName]
                if not count:
                   print string.ljust(site,hostField),string.ljust(str(evt),evtLength),bName
                   count+=1
                else:
                   empty = " "*(hostField)
                   print empty,string.ljust(str(evt),evtLength),bName
            print "Summary: %s events, %s files, %s"%(totEvt,totFiles,totSize)
            print

