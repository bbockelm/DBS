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
import string, sys, time, types, popen2, httplib
import elementtree, traceback
from elementtree.ElementTree import fromstring

# import DBS modules
import DDOptions
from   DDConfig  import *
from   DBSInst   import * # defines DBS instances and schema
from   DDUtil    import * # general utils

# QueryBuilder
from QueryBuilder.Schema import Schema

# import DLS modules
#try:
#    import dlsClient
#    import dlsApi
#except:
#    pass
import __builtin__

class DDHelper(DDLogger): 
  """
      DDHelper class
  """
  def __init__(self,dbsInst="",iface="sqlalchemy",verbose=0,html=0):
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
      self.ddConfig  = DDConfig()
      if not dbsInst:
         dbsInst=self.ddConfig().dbsprimary()
      DDLogger.__init__(self,self.ddConfig.loggerDir(),"DDHelper",verbose)
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
            self.writeLog(getExcept())
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

  def setVerbose(self,level):
      self.verbose=level
      self.dbManager.setVerbose(level)

  def col(self,table,col):
      return self.dbManager.col(self.dbsInstance,table,col)

  def printQuery(self,sel):
      return self.dbManager.printQuery(self.dbsInstance,sel).replace("\n","")
      
  def addQueryLimits(self,query,offset,limit):
      if  self.dbManager.dbType[self.dbsInstance]=='oracle':
          # SQLAlchemy incorrectly wrap queries using ORACLE back-end
          # see bug report, http://www.sqlalchemy.org/trac/ticket/536
          # on ORACLE there is no LIMIT/OFFSET and in order to make it with column who may
          # have the same values we need to do
          # select path from (select distinct path from Block where path)
          #        group by rownum,path having rownum between 1 and 5;
          query.use_labels=True
          sel=query.select()
          bindparams=query.__dict__['whereclause'].__dict__['bindparams'].values()
          s=str(sel).replace("\n","")
          gBy=''.join(s.split('FROM')[0].split('SELECT'))
          fromRow=long(offset)
          #query=s+" GROUP BY rownum, "+gBy+'HAVING rownum>%s and rownum<=%s'%(fromRow,fromRow+limit)
          nq=s+" GROUP BY rownum, "+gBy+'HAVING rownum>%s and rownum<=%s'%(fromRow,fromRow+limit)
          query = sqlalchemy.text(nq,bindparams=bindparams, bind=self.dbManager.engine[self.dbsInstance])
      else:
          query.limit=long(limit)
          query.offset=long(offset)
      return query
  
  def printExcept(self,msg=None):
      if self.verbose:
         self.writeLog(getExcept(msg))
      print msg
      printExcept()

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
      con = self.connectToDB()
      self.closeConnection(con)
      # use cache
#      if not self.dbsApi.has_key(dbsInst):
#         if self.iface=="cgi":
#            self.api = dbsCgiApi.DbsCgiApi(DEFAULT_URL,{'instance':dbsInst})
#         else: 
#            url,dlsType,endpoint = DBS_DLS_INST[dbsInst]
#            self.api=""
#            con = self.connectToDB()
#            self.closeConnection(con)
#         self.dbsApi[dbsInst]=self.api
#      else:
#         self.api = self.dbsApi[dbsInst]
      # UNCOMMENT FOR DLS usage
#      if not self.dbsDLS.has_key(dbsInst):
#         url,dlsType,endpoint = DBS_DLS_INST[dbsInst]
#         self.writeLog("DLS Instance: %s %s"%(dlsType,endpoint))
#         self.dlsApi = dlsClient.getDlsApi(dlsType, endpoint)
#      else:
#         self.dlsApi = self.dbsDLS[dbsInst]
 
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
  def getTableColumn(self,table,column,iRow=0,iLimit=0,whereDict={}):
      t1=time.time()
      con = self.connectToDB()
      oList  = []
      try:
          content = self.getTableContent(con,table,iList=[column],fromRow=iRow,limit=iLimit,whereDict=whereDict)
      except:
          if self.verbose:
             self.writeLog(getExcept())
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
                     tqps.join(talc,onclause=self.col(talc,'ParameterSetID')==self.col(tqps,'ID'))
                     .join(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .join(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
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
              id,name,ver,psType,ann,cDate,cBy,mDate,mBy=item
              cDate=timeGMT(cDate)
              mDate=timeGMT(mDate)
              cBy=parseCreatedBy(cBy)
              mBy=parseCreatedBy(mBy)
              if id and name:
                  oList.append((id,name,ver,psType,ann,cDate,cBy,mDate,mBy))
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in listApplicationsConfigs"
      if self.verbose:
         self.writeLog("time listApplicationsConfigs: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

  def listApplicationConfigsContent(self,appPath,procPath):
      t1=time.time()
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tblk = self.alias('Block','tblk')
          tpal = self.alias('ProcAlgo','tpal')
          tape = self.alias('AppExecutable','tape')
          tapv = self.alias('AppVersion','tapv')
          tapf = self.alias('AppFamily','tapf')
          talc = self.alias('AlgorithmConfig','talc')
          tqps = self.alias('QueryableParameterSet','tqps')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          oSel = [self.col(tapv,'Version'),self.col(tqps,'Name'),self.col(tqps,'Content'),self.col(tqps,'Version'),self.col(tqps,'Type'),self.col(tqps,'Annotation'),self.col(tqps,'CreationDate'),self.col(tp1,'DistinguishedName'),self.col(tqps,'LastModificationDate'),self.col(tp2,'DistinguishedName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                     tqps.join(talc,onclause=self.col(talc,'ParameterSetID')==self.col(tqps,'ID'))
                     .join(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .join(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                     .join(tpal,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .join(tprd,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tp1,onclause=self.col(tqps,'CreatedBy')==self.col(tp1,'ID'))
                     .outerjoin(tp2,onclause=self.col(tqps,'LastModifiedBy')==self.col(tp2,'ID'))
                            ]
                                 )
          # to avoid ORA-00932: inconsistent datatypes: expected - got CLOB, I don't need to
          # supply distinct and order while dealing with ORACLE
          # http://forums.bea.com/bea/message.jspa?messageID=202461255&tstart=0
          if self.dbManager.dbType[self.dbsInstance]!='oracle':
             sel.distinct=True
             sel.order_by=oSel
          if procPath and procPath!="*":
             sel.append_whereclause(self.col(tblk,'Path')==procPath)
          if appPath and appPath!="*":
             empty,ver,fam,exe=string.split(appPath,"/")
             if ver.lower()=='any': ver="*"
             if fam.lower()=='any': fam="*"
             if exe.lower()=='any': exe="*"
             if ver and ver!="*":
                sel.append_whereclause(self.col(tapv,'Version')==ver)
             if fam and fam!="*":
                sel.append_whereclause(self.col(tapf,'FamilyName')==fam)
             if exe and exe!="*":
                sel.append_whereclause(self.col(tape,'ExecutableName')==exe)
          result = self.getSQLAlchemyResult(con,sel)
          for item in result:
              softRel,name,content,ver,psType,ann,cDate,cBy,mDate,mBy=item
              if not name: continue
#              if self.dbManager.dbType[self.dbsInstance]=='oracle':
#                 if content and type(content) is types.StringType:
#                    content=content.read() # since content is LOB object
              content=str(content) # since content is LOB object
              cDate=timeGMT(cDate)
              mDate=timeGMT(mDate)
              cBy=parseCreatedBy(cBy)
              mBy=parseCreatedBy(mBy)
              if name and not oList.count((softRel,name,content,ver,psType,ann,cDate,cBy,mDate,mBy)):
                 oList.append((softRel,name,content,ver,psType,ann,cDate,cBy,mDate,mBy))
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in listApplicationsConfigsContent"
      if self.verbose:
         self.writeLog("time listApplicationsConfigsContent: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

  def findDatasetsFromLFN(self,lfn):
      con = self.connectToDB()
      try:
          tblk = self.alias('Block','tblk')
          tf   = self.alias('Files','tf')
          oSel = [self.col(tblk,'Name')]
          obj  = tblk.join(tf,onclause=self.col(tf,'Block')==self.col(tblk,'ID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True)
          sel.append_whereclause(self.col(tf,'LogicalFileName')==lfn)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in findDatasetsFromLFN"
      oList=[]
      for item in result:
          path = item[0]
          if not path: continue
          if not oList.count(path): oList.append(path)
      self.closeConnection(con)
      return oList

  def listProcessedDatasets(self,group="*",app="*",prim="*",tier="*",proc="*",site="*",primType="*",date="*",userMode="user",fromRow=0,limit=0,count=0):
      if group.lower()=='any': group="*"
      app=app.replace("Any","*")
      app=app.replace("any","*")
      if app.lower()  =='any': app  ="/*/*/*"
      if app=="*": app ="/*/*/*"
      if prim.lower() =='any': prim ="*"
      if tier.lower() =='any': tier ="*"
      if site.lower() =='any': site ="*"
      if primType.lower() =='any': primType ="*"
      if date.lower() =='any': date="*"
      if type(proc) is not types.ListType and proc.lower() =='any': proc ="*"
#      if proc and proc!="*":
      if proc!="*":
         if count:
            if type(proc) is types.ListType:
               return len(proc)
            return 1
         if type(proc) is types.ListType:
            return proc
         return [proc]
      con = self.connectToDB()
      oList  = []
      sel = ""
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpmt = self.alias('PrimaryDSType','tpmt')
          tape = self.alias('AppExecutable','tape')
          tapv = self.alias('AppVersion','tapv')
          tapf = self.alias('AppFamily','tapf')
          talc = self.alias('AlgorithmConfig','talc')
          tpal = self.alias('ProcAlgo','tpal')
          tpds = self.alias('ProcDSTier','tpds')
          tdt  = self.alias('DataTier','tdt')
          tblk = self.alias('Block','tblk')
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')
          tpg  = self.alias('PhysicsGroup','tpg')

          if  count:
              oSel = [sqlalchemy.func.count(self.col(tblk,'Path').distinct())]
          else:
              oSel = [self.col(tblk,'Path'),self.col(tprd,'CreationDate')]
#          print "group,app,prim,tier,proc,site,primType",group,app,prim,tier,proc,site,primType
          # I need to decide which table to join based on input parameters
          obj=tblk
          obj=obj.join(tprd,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
          if  site and site!="*":
              obj=obj.join(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
              obj=obj.join(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
          if (proc and proc!="*") or (app and app!="/*/*/*") or \
             (prim and prim!="*") or (primType and primType!="*") or \
             (group and group!="*") or (tier and tier!="*") or (date and date!="*"):
              if (app and app!="/*/*/*"):
                  obj=obj.join(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                  obj=obj.join(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                  empty,ver,fam,exe=string.split(app,"/")
                  if ver.lower()=="any" or ver.lower()=="all": ver="*"
                  if fam.lower()=="any" or fam.lower()=="all": fam="*"
                  if exe.lower()=="any" or exe.lower()=="all": exe="*"
                  if ver!="*":
                     obj=obj.join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                  if fam!="*":
                     obj=obj.join(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                  if exe!="*":
                     obj=obj.join(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
              primInc=0
              if (prim and prim!="*"):
                  obj=obj.join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                  primInc=1
              if (primType and primType!="*"):
                  if not primInc:
                     obj=obj.join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                  obj=obj.join(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
              if  group and group!="*":
                  obj=obj.join(tpg,onclause=self.col(tprd,'PhysicsGroup')==self.col(tpg,'ID'))
              if  tier and tier!="*":
                  obj=obj.join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                  obj=obj.join(tdt,onclause=self.col(tdt,'ID')==self.col(tpds,'DataTier'))
          sel = sqlalchemy.select(oSel,from_obj=[obj],
                distinct=True,order_by=[sqlalchemy.desc( self.col(tprd,'CreationDate') )] )
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             self.joinTiers(sel,tpds,tier,tprd)
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
          if site and site!="*":
                sel.append_whereclause(self.col(tse,'SEName')==site)
          if primType and primType!="*":
                sel.append_whereclause(self.col(tpmt,'Type')==primType)
          if group and group!="*":
             sel.append_whereclause(self.col(tpg,'PhysicsGroupName')==group)
          if date and date!="*":
             if date.find("_")!=-1:
                d_low,d_up=date.split("_")
                sel.append_whereclause(self.col(tprd,'CreationDate')>=d_low)
                sel.append_whereclause(self.col(tprd,'CreationDate')<=d_up)
             else:
                sel.append_whereclause(self.col(tprd,'CreationDate')>=date)
          if userMode=="user":
             sel.append_whereclause(self.col(tblk,'NumberOfEvents')!=0)
          sel.append_whereclause(self.col(tblk,'Path')!=sqlalchemy.null())
          if not count and limit:
             if  self.dbManager.dbType[self.dbsInstance]=='oracle':
                 # on ORACLE there is no LIMIT/OFFSET and in order to make it with column who may
                 # have the same values we need to do
                 # select rownum, path from (select distinct path from Block where path is not null order by path desc) group by rownum,path having rownum between 1 and 5;
                 sel.use_labels=True
                 s=sel
                 oSel=[s.c.tblk_path,s.c.tprd_creationdate]
                 sel = sqlalchemy.select(oSel,group_by=['rownum']+oSel,order_by=[sqlalchemy.desc(s.c.tprd_creationdate)])
                 sel.append_having( 'rownum>%s and rownum<=%s'%(fromRow,fromRow+limit) )
             else:
                 sel.limit=limit
                 sel.offset=fromRow
          result = self.getSQLAlchemyResult(con,sel)
#          print self.printQuery(sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in listProcessedDataset"
      if count:
         res = result.fetchone()[0]
         self.closeConnection(con)
         return res
      for item in result:
          path,prdDate = item
          if not path: continue
          if not oList.count(path): oList.append(path)
      self.closeConnection(con)
      return oList

  def getPrimDetailsForRSS(self,prim="*"):
      if prim.lower() =='any': prim ="*"
      con = self.connectToDB()
      sel = ""
      try:
          tpm  = self.alias('PrimaryDataset','tpm')
          oSel = [self.col(tpm,'CreationDate'),self.col(tpm,'Annotation')]
          sel = sqlalchemy.select(oSel,from_obj=[tpm],distinct=True )
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getPrimDetailsForRSS"
      cDate=""
      annotation=""
      for item in result:
          if item and item[0]:
             try:
                cDate,annotation=item
             except:
                print "\n### Exception too many values to unpack, item=",item
                self.printExcept("")
                pass
             cDate=timeGMT(cDate)
      self.closeConnection(con)
      return cDate,annotation

  def getProcDSForRss(self,prim="*",rel="*",fromRow=0,limit=0):
      if rel.lower()  =='any': rel  ="*"
      if prim.lower() =='any': prim ="*"
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tapv = self.alias('AppVersion','tapv')
          talc = self.alias('AlgorithmConfig','talc')
          tpal = self.alias('ProcAlgo','tpal')
          tblk = self.alias('Block','tblk')
          tpmd = self.alias('PrimaryDatasetDescription','tpmd')
          tmcd = self.alias('MCDescription','tmcd')
          ttrd = self.alias('TriggerPathDescription','ttrd')

          oSel = [self.col(tblk,'Path'),self.col(tblk,'BlockSize'),self.col(tblk,'NumberOfFiles'),self.col(tblk,'NumberOfEvents'),self.col(tblk,'OpenForWriting'),self.col(tprd,'CreationDate'),self.col(ttrd,'TriggerPathDescription'),self.col(tmcd,'MCChannelDescription'),self.col(tmcd,'MCProduction'),self.col(tmcd,'MCDecayChain')]
          sel = sqlalchemy.select(oSel,
                 from_obj=[
                     tprd.join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                     .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .join(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .join(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .outerjoin(tpmd,onclause=self.col(tpm,'Description')==self.col(tpmd,'ID'))
                     .outerjoin(tmcd,onclause=self.col(tpmd,'MCChannelDescriptionID')==self.col(tmcd,'ID'))
                     .outerjoin(ttrd,onclause=self.col(tpmd,'TriggerDescriptionID')==self.col(ttrd,'ID'))
                     ],distinct=True,order_by=[sqlalchemy.desc( self.col(tblk,'Path') )] )
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if rel and rel!="*":
             sel.append_whereclause(self.col(tapv,'Version')==rel)
          if limit:
             sel.limit=limit
             sel.offset=fromRow
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getProcDSForRss"
      for item in result:
          if not (item and item[0]): continue
#          print "Items in RSS:",len(item),item
          path,bSize,nFiles,nEvents,status,cDate,trigDesc,mcChannelDesc,mcProd,mcDecay=item
          if not path: continue
          cDate=timeGMT(cDate)
          if status:
             status="OPEN"
          else:
             status="CLOSED"
          desc="""
Block size:         %s
Number of files:    %s
Number of events:   %s
Status:             %s
TriggerDescription: %s
MCDescription:      %s
"""%(sizeFormat(bSize),nFiles,nEvents,status,trigDesc,mcChannelDesc)
#          desc="""
#<table>
#<tr><td><b>Block size:         </b></td><td>%s</td></tr>
#<tr><td><b>Number of files:    </b></td><td>%s</td></tr>
#<tr><td><b>Number of events:   </b></td><td>%s</td></tr>
#<tr><td><b>Status:             </b></td><td>%s</td></tr>
#<tr><td><b>TriggerDescription: </b></td><td>%s</td></tr>
#<tr><td><b>MCDescription:      </b></td><td>%s</td></tr>
#</table>
#"""%(colorSizeHTMLFormat(bSize),nFiles,nEvents,status,trigDesc,mcChannelDesc)
          elem=(path,desc,cDate)
          if not oList.count(elem): oList.append(elem)
      self.closeConnection(con)
      return oList

  def listDatasetsFromApp(self,appPath="*"):
      return self.listProcessedDatasets(app=appPath)

#  def listApplications(self,appPath="*"):
#      """
#         Wrapper around dbsApi
#      """
#      if self.iface=="cgi":
#         aList = self.api.listApplications(appPath)
#         aList.sort()
#         aList.reverse()
#         return aList
#      else:
#         if appPath=="*":
#            ver=family=exe="*"
#         else:
#            empty,ver,family,exe=string.split(appPath,"/")
#         res = self.api.listApplications(patternVer=ver,patternFam=family,patternExe=exe)
#         return res

  def joinTiers(self,sel,tjoin,tier,tprd,condDict={}):
      aList=[]
      bList=[]
      if tier and tier!="*":
         tierList=tier.split("-")
         for idx in xrange(0,len(tierList)):
             aList.append(self.alias('DataTier','tdt%s'%idx))
             bList.append(self.alias('ProcDSTier','tpds%s'%idx))
             tierValue=tierList[idx]
             sel.append_from( sqlalchemy.join( aList[-1],bList[-1],self.col(bList[-1],'DataTier')==self.col(aList[-1],'ID') ) )
             sel.append_whereclause(self.col(aList[-1],'Name')==tierValue)
             condDict[findLastBindVar(str(sel))]=tierValue
             sel.append_whereclause(self.col(tprd,'ID')==self.col(bList[-1],'Dataset'))

  def getBlocksInfo(self,dataset):
      con   = self.connectToDB()
      oList = []
      sel   = ""
      try:
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')
          tblk = self.alias('Block','tblk')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')
          oSel =[self.col(tblk,'Name'),self.col(tblk,'BlockSize'),
                 self.col(tblk,'NumberOfFiles'),self.col(tblk,'NumberOfEvents'),
                 self.col(tblk,'OpenForWriting'),
                 self.col(tp1,'DistinguishedName'),self.col(tblk,'CreationDate'),
                 self.col(tp2,'DistinguishedName'),self.col(tblk,'LastModificationDate'),
                 self.col(tse,'SEName')
                ]
          oBy  = [sqlalchemy.desc(self.col(tblk,'LastModificationDate'))]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                          tblk.join(tseb,onclause=self.col(tblk,'ID')==self.col(tseb,'BlockID'))
                          .join(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                          .outerjoin(tp1,onclause=self.col(tblk,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(tblk,'LastModifiedBy')==self.col(tp2,'ID'))
                            ],distinct=True,order_by=oBy
                                 )
          sel.append_whereclause(self.col(tblk,'Path')==dataset)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getBlocksSummary"
      oDict={}
      for item in result:
          if not item[0]: continue
          name,blkSize,nFiles,nEvts,status,cBy,cDate,mBy,mDate,se=item
          cDate = timeGMT(cDate)
          cBy   = parseCreatedBy(cBy)
          mDate = timeGMT(mDate)
          mBy   = parseCreatedBy(mBy)
          if oDict.has_key(name):
             seList=oDict[name][-1]
             if not seList.count(se):
                seList.append(se)
          else:
             oDict[name]=(name,long(blkSize),long(nFiles),long(nEvts),status,cBy,cDate,mBy,mDate,[se])
#          oList.append((name,long(blkSize),long(nFiles),long(nEvts),status,cBy,cDate,mBy,mDate,se))
      self.closeConnection(con)
#      return oList
      return oDict.values()

  def nDatasets(self):
      con   = self.connectToDB()
      nsets =-1
      try:
          tblk = self.alias('Block','tblk')
          oSel =[sqlalchemy.func.count(self.col(tblk,'Path').distinct())]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[tblk] )
          result = self.getSQLAlchemyResult(con,sel)
          nsets = result.fetchone()[0]
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in nDatasets"
      self.closeConnection(con)
      return nsets

  def datasetSummary(self,dataset,watchSite="",htmlMode=""):
      if watchSite.lower()=="any" or watchSite.lower()=="all" or watchSite=="*": watchSite=""
      con   = self.connectToDB()
      oDict = {}
      sel   = ""
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tblk = self.alias('Block','tblk')
          tp   = self.alias('Person','tp')
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')

          oSel = [self.col(tprd,'CreationDate'),self.col(tp,'DistinguishedName'),
                  self.col(tblk,'Path'),self.col(tse,'SEName')]
          gBy  = list(oSel)
          _oSel= [sqlalchemy.func.count(self.col(tblk,'Name').distinct()),
                  sqlalchemy.func.sum(self.col(tblk,'BlockSize')),
                  sqlalchemy.func.sum(self.col(tblk,'NumberOfFiles')),
                  sqlalchemy.func.sum(self.col(tblk,'NumberOfEvents')) ]
          oSel+=_oSel
          oBy  = [sqlalchemy.desc(self.col(tprd,'CreationDate'))]
          _sel = sqlalchemy.select(_oSel,from_obj=[tblk],distinct=True )
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                          tprd.join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                          .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .outerjoin(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                          .outerjoin(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                          .outerjoin(tp,onclause=self.col(tprd,'CreatedBy')==self.col(tp,'ID'))
                            ],distinct=True,order_by=oBy,group_by=gBy
                                 )
          if dataset and dataset!="*":
             sel.append_whereclause(self.col(tblk,'Path')==dataset)
             _sel.append_whereclause(self.col(tblk,'Path')==dataset)
          result = self.getSQLAlchemyResult(con,sel)
          _result = self.getSQLAlchemyResult(con,_sel)
#          print "\n\n+++datasetSummary",self.printQuery(sel),dataset
#          print "_sel query:",self.printQuery(_sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in datasetSummary"
      totBlk,totSize,totFiles,totEvts=_result.fetchone()
      masterSE=""
      _oDict={} # back-up dict
      for item in result:
          if not item[0]: continue
          prdDate,cBy,path,sename,nblks,blkSize,nFiles,nEvts=item
          if not masterSE: masterSE=sename
          if blkSize>totSize and nFiles>totFiles and nEvts>totEvts:
             masterSE=sename
          prdDate  = timeGMT(prdDate)
          cBy      = parseCreatedBy(cBy)
          # I need back-up in a case if someone ask for specific site and masterSE is not that one
          _oDict[sename]=(prdDate,cBy,long(nblks),long(blkSize),long(nFiles),long(nEvts))
          # if watchSite was defined, I will apply filter for sename
          if watchSite and watchSite!="*" and watchSite!=sename: 
             continue
          oDict[sename]=(prdDate,cBy,long(nblks),long(blkSize),long(nFiles),long(nEvts))
      self.closeConnection(con)
      mDict={}
      if masterSE:
         # check if masterSE values are the same as total values for dataset
         _pDate,_cBy,_nblks,_blkSize,_nFiles,_nEvts=_oDict[masterSE]
         if totSize==_blkSize and totFiles==_nFiles and totEvts==_nEvts:
            mDict={'%s'%masterSE:_oDict[masterSE]}
         else:
            mDict={'all':(_pDate,_cBy,totBlk,totSize,totFiles,totEvts)}
      else: # when no SE's found
         if _oDict:
            mDict={'%s'%masterSE:_oDict[masterSE]}
      return oDict,mDict

  def listBlocks(self,kwargs):
      # {'blockName': (nEvt,blockStatus,nFiles,blockSize)}
      # second output:
      # [{'Name':,'BlockSize':,'NumberOfFiles':,'NumberOfEvents':,'OpenForWriting':,'CreationDate','CreationDate':,'LastModificationDate':,'LastModifiedBy'}]
#      print "\n\nlistBlocks",kwargs
      t1=time.time()
      watchSite=""
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

          oSel = [self.col(tprd,'CreationDate'),self.col(tblk,'Name'),self.col(tblk,'BlockSize'),self.col(tblk,'NumberOfFiles'),self.col(tblk,'NumberOfEvents'),self.col(tblk,'OpenForWriting'),self.col(tp1,'DistinguishedName'),self.col(tblk,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tblk,'LastModificationDate'),self.col(tse,'SEName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                          tprd.join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                          .join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                          .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .join(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                          .join(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                          .outerjoin(tp1,onclause=self.col(tblk,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(tblk,'LastModifiedBy')==self.col(tp2,'ID'))
                            ],distinct=True,order_by=oSel
                                 )
          if kwargs.has_key('datasetPath') and kwargs['datasetPath']:
             if  kwargs['datasetPath'][0]=="/":
                 empty,prim,proc,tier=string.split(kwargs['datasetPath'],"/")
                 if proc and proc!="*":
                    sel.append_whereclause(self.col(tprd,'Name')==proc)
                 if prim and prim!="*":
                    sel.append_whereclause(self.col(tpm,'Name')==prim)
                 if tier and tier!="*":
                    self.joinTiers(sel,tpds,tier,tprd)
             else:
                 msg ="Dataset name should be in a form /primary/processed/tier"
                 msg+="You provided '%s'"%kwargs['datasetPath']
                 msg+="If you need wild-card search please use *%s*"%kwargs['datasetPath']
                 raise msg

          if kwargs.has_key('blockName') and kwargs['blockName']:
             sel.append_whereclause(self.col(tblk,'Name')==kwargs['blockName'])
          if kwargs.has_key('site') and kwargs['site'] and kwargs['site']!="*" and string.lower(kwargs['site'])!='all':
#             sel.append_whereclause(self.col(tse,'SEName')==kwargs['site'])
             # instead of placing condition to query I'll identify which site to watch
             # this will allow correctly calculate total size of dataset and then
             # in results I'll filter sites wrt to watchSite
             watchSite=kwargs['site']
          idx=-1
          if kwargs.has_key('idx'): idx=kwargs['idx']
          if kwargs.has_key('userMode') and kwargs['userMode']=="user":
             sel.append_whereclause(self.col(tblk,'NumberOfEvents')!=0)
#          print "\n\nblockList query\n",sel
          result = self.getSQLAlchemyResult(con,sel,idx)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in listBlocks"
      aDict={}
      aList=[]
      siteList=[]
      totEvt=0
      totFiles=0
      totSize=0
      oldse=""
      oldBlk=""
      prdDate=""
      for item in result:
#          print "blockList item result=",item
          if not item[0]: continue
          prdDate,blockName,blockSize,nFiles,nEvts,blockStatus,cBy,cDate,mBy,mDate,sename=item
          if not blockName: continue
          prdDate=timeGMT(prdDate)
          cDate=timeGMT(cDate)
          mDate=timeGMT(mDate)
          cBy=parseCreatedBy(cBy)
          mBy=parseCreatedBy(mBy)
          if not sename: sename='N/A'
          if oldBlk!=blockName:
             totEvt+=nEvts
             totFiles+=nFiles
             totSize+=blockSize
             oldBlk=blockName
             
          # if watchSite was defined, I will apply filter for sename
          if watchSite and watchSite!=sename: 
             continue
             
          if not siteList.count(sename): siteList.append(sename)

          if kwargs.has_key('fullOutput'):
             aDict={'Name':blockName,'BlockSize':blockSize,'NumberOfFiles':nFiles,'NumberOfEvents':nEvts,'OpenForWriting':blockStatus,'CreatedBy':cBy,'CreationDate':cDate,'LastModifiedBy':mBy,'LastModificationDate':mDate}
             if not aList.count(aDict):
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
      return prdDate,aDict,totEvt,totFiles,totSize,siteList

  def numberOfEvents(self,datasetPath):
      prim=""
      tier=""
      proc=""
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
                     tprd.join(tf,onclause=self.col(tf,'Dataset')==self.col(tprd,'ID'))
                         .join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                         .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     ] )
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             self.joinTiers(sel,tpds,tier,tprd)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in numberOfEvents"
      evts=0
      for item in result:
          if not item[0]: continue
          evts+= item[0]
      self.closeConnection(con)
      return evts

  ### END OF WRAPPER ###

  def getDataDescription(self,processedDataset):
      prim=""
      tier=""
      proc=""
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
                 tpm.join(tpt,onclause=self.col(tpm,'Type')==self.col(tpt,'ID'))
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
                 tprd.join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
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
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getDataDescription"
#      oList = result.fetchall()
      oList=[]
      for item in result:
          if not item[0]:
             oList.append(item)
      self.closeConnection(con)
      return oList

  # TODO: it's looks like it's used only in initJSDict, so may be I don't need it anymore
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
          tblk = self.alias('Block','tblk')
          sel  = sqlalchemy.select([self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName'),self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name'),self.col(tblk,'Path')],
                   from_obj=[
                      tprd.join(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                      .join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                      .join(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                      .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                      .join(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                      .join(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                      .join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                      .join(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                      .join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                            ],distinct=True,
                   order_by=[self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName'),self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name')]
                                 )
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in listProcessedDataset"
      for item in result:
          ver  = item[0]
          fam  = item[1]
          exe  = item[2]
          prim = item[3]
          tier = item[4]
          proc = item[5]
          path = item[6]
          if ver and fam and exe and prim and tier and proc:
#             addToDict(aDict,"/%s/%s/%s"%(ver,fam,exe),"/%s/%s/%s"%(prim,proc,tier))
             addToDict(aDict,"/%s/%s/%s"%(ver,fam,exe),path)
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
                     talc.join(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .join(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                            ],distinct=True,
                       order_by=[self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName')]
                                 )
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
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
      
  def getPrimaryDSTypes(self):
      return self.getTableColumn('PrimaryDSType','Type')

  def getPrimaryDatasets(self,group="*",tier="*",rel="*",dsType="mc",html=0):
      t1=time.time()
      con = self.connectToDB()
      oList   = []
      if group.lower()=="any": group="*"
      if tier.lower()=="any": tier="*"
      if rel.lower()=="any": rel="*"
      if dsType.lower()=="any": dsType="*"
      if  group=="*" and tier=="*" and rel=="*":
#          content = self.getTableContent(con,'PrimaryDataset',iList=['Name'],fromRow=0,limit=0)
          try:
              tpm  = self.alias('PrimaryDataset','tpm')
              tpmt = self.alias('PrimaryDSType','tpmt')
              oSel = [self.col(tpm,'Name')]
              sel  = sqlalchemy.select(oSel,
                     from_obj=[
                     tpm.join(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
                         ],distinct=True,order_by=oSel )
              if dsType and dsType!="*":
                 sel.append_whereclause(self.col(tpmt,'Type')==dsType)
              result = self.getSQLAlchemyResult(con,sel)
              for item in result:
                  if item[0]:
                     oList.append(item[0])
          except:
              msg="\n### Query:\n"+str(sel)
              self.printExcept(msg)
              raise "Fail in getPrimaryDatasets"
#          for entry in content:
#              name=entry[0]
#              if html:
#                 navBar   ="MakeNavBarPrimDS('%s','%s')"%(self.dbsInstance,name)
#                 dataInfo ="ajaxGetData('%s','all','*','*','%s','*','*')"%(self.dbsInstance,name)
#                 name="""<a href="javascript:showWaitingMessage();ResetAllResults();%s;%s;">%s</a>"""%(navBar,dataInfo,name)
#              oList.append(name)
      else: # I need to make a full query

          try:
              tpg  = self.alias('PhysicsGroup','tpg')
              tapv = self.alias('AppVersion','tapv')
              talc = self.alias('AlgorithmConfig','talc')
              tprd = self.alias('ProcessedDataset','tprd')
              tpm  = self.alias('PrimaryDataset','tpm')
              tpmt = self.alias('PrimaryDSType','tpmt')
              tpds = self.alias('ProcDSTier','tpds')
              tdt  = self.alias('DataTier','tdt')
              tpal = self.alias('ProcAlgo','tpal')
              oSel = [self.col(tpm,'Name')]
              sel  = sqlalchemy.select(oSel,
                     from_obj=[
                     tprd.join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .join(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
                     .join(tpg,onclause=self.col(tprd,'PhysicsGroup')==self.col(tpg,'ID'))
                     .join(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .join(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                         ],distinct=True,order_by=oSel )
              if group and group!="*":
                 sel.append_whereclause(self.col(tpg,'PhysicsGroupName')==group)
              if tier and tier!="*":
                 self.joinTiers(sel,tpds,tier,tprd)
              if rel and rel!="*":
                 sel.append_whereclause(self.col(tapv,'Version')==rel)
              if dsType and dsType!="*":
                 sel.append_whereclause(self.col(tpmt,'Type')==dsType)
              result = self.getSQLAlchemyResult(con,sel)
              for item in result:
                  if item[0]:
                     oList.append(item[0])
          except:
              msg="\n### Query:\n"+str(sel)
              self.printExcept(msg)
              raise "Fail in getPrimaryDatasets"
      if self.verbose:
         self.writeLog("time getPrimaryDatasets: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

#  def getDatasetContent(self,dataset):
#      content = self.api.getDatasetContents(dataset)
#      return content

  def getDatasetProvenance(self,dataset):
      t1=time.time()
      prim=""
      tier=""
      proc=""
      if dataset and dataset!="*":
         empty,prim,proc,tier=string.split(dataset,"/")
      con = self.connectToDB()
      oList  = []
      try:

          tprd = self.alias('ProcessedDataset','tprd')
          tprd2= self.alias('ProcessedDataset','tprd2')
          tpdp = self.alias('ProcDSParent','tpdp')

          tblk = self.alias('Block','tblk')
          tblk2= self.alias('Block','tblk2')
          oSel = [self.col(tpdp,'ItsParent'),self.col(tblk2,'Path')]

          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tprd.outerjoin(tpdp,onclause=self.col(tpdp,'ThisDataset')==self.col(tprd,'ID'))
                     .outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tprd2,onclause=self.col(tpdp,'ItsParent')==self.col(tprd2,'ID'))
                     .outerjoin(tblk2,onclause=self.col(tblk2,'Dataset')==self.col(tprd2,'ID'))
                     ],distinct=True,order_by=oSel )
          if dataset and dataset!="*":
             sel.append_whereclause(self.col(tblk,'Path')==dataset)
          result = self.getSQLAlchemyResult(con,sel)
          for item in result:
              id,path=item
              if not id and not path: continue
              oList.append(path)

      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getDatasetProvenance"
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
            msg="\n### Query:\n"+str(q)
            self.printExcept(msg)
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
      except:
	 try:
             con = self.dbManager.connect(self.dbsInstance)
         except:
             try:
                 # try second time, but sleep for 2 seconds before retry
                 time.sleep(2)
                 self.dbManager.clear()
                 con = self.dbManager.connect(self.dbsInstance)
             except Exception, ex:
                 raise DbsDatabaseError(args=ex)
             pass
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

  def getTableContent(self,con,tableName,iList=['*'],fromRow=0,limit=0,whereDict={}):
#      print "\n\ngetTableContent",tableName,iList,whereDict
      try:
          tableObj=self.dbManager.getTable(self.dbsInstance,tableName)
          if limit:
             sel = sqlalchemy.select(iList, from_obj=[tableObj], limit=limit, offset=fromRow)
          else:
             sel = sqlalchemy.select(iList, from_obj=[tableObj])
          t=self.dbManager.getTable(self.dbsInstance,tableName)
          # all where clauses should be provided as whereDict['Table.Col']=value
          for key in whereDict.keys():
              tName,col = key.split('.')
              t=self.dbManager.getTable(self.dbsInstance,tName)
              val = whereDict[key]
              val = val.replace('*','%') # replace wild card
              val = val.replace('%%','%') # remove double '%'
              if val[-1]=='%': val=val[:-1] # don't count last '%'
              lval=self.col(t,col)
              sel.append_whereclause(sqlalchemy.func.upper(lval).like("%s%%"%str(val.upper())))
          if len(iList)==1:
             sel.order_by=[sqlalchemy.desc(iList[0])]
          # NOTE: originally I used distinct here, but according to Yuyi, it's not required.
#          sel.distinct=True
          # Due to bug in SQLAlchemy, I need to make a print statement, otherwise I'm not getting results.
          # should be fixed with usage of SQLAlchemy 0.3.7
#          print "### getTableContent",self.printQuery(sel),"%s%%"%str(val.upper())
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail to get content for table='%s'"%tableName
      return result

  def getTableColumns(self,tableName):
      res=[]
      try:
          res = self.dbManager.getColumns(self.dbsInstance,tableName)
      except:
          msg="Fail to get columns for table='%s'\n"%tableName
          msg+=getExceptionInHTML()
          res=msg
      return res

  #### This section contains methods which use plain SQL
  def getSiteClause(self,site=""):
      siteSel=""
      siteWhere=""
      if site=="*" or site.lower()=="any" or site.lower()=="all":
         site=""
      if site:
         siteSel=" INNER JOIN SEBlock seb ON seb.BlockID=tblk.ID INNER JOIN StorageElement se ON se.ID=seb.SEID "
         siteWhere=" AND se.SEName=:site "
      return siteSel,siteWhere

  def countBlocks(self,whereClause,site="",explicitWClause="",explicitDict={},caseSensitive=True):
      siteSel,siteWhere=self.getSiteClause(site)
      bindparams=[]
      blk="tblk.Path"
      if not caseSensitive:
         blk="UPPER(tblk.Path)"
      mysql=""
      if self.dbManager.dbType[self.dbsInstance]=='mysql':
         mysql="COLLATE latin1_bin"
      if explicitWClause:
         oDict=explicitDict
         sel = "select COUNT(DISTINCT Path) from Block tblk %s where %s %s %s"%(siteSel,explicitWClause,siteWhere,mysql)
      else:
         wClause,oDict=parseKeywordInput(whereClause,blk)
         sel = "select COUNT(DISTINCT Path) from Block tblk %s where %s %s %s"%(siteSel,wClause,siteWhere,mysql)
      if self.dbManager.dbType[self.dbsInstance]=='oracle':
         if sel.lower().find("like")!=-1 and sel.lower().find("escape")==-1:
            sel+=" ESCAPE '\\'" # I hate DBs for having non-standard keywords
      for bind_param in oDict.keys():
          bindparams.append(sqlalchemy.bindparam(key=bind_param,value=oDict[bind_param]))
      if siteWhere:
          bindparams.append(sqlalchemy.bindparam(key="site",value=site))

      query = sqlalchemy.text(sel, bindparams=bindparams, bind=self.dbManager.engine[self.dbsInstance])
#      print "\n\n+++countBlocks",self.printQuery(query),bindparams
      if self.verbose:
         self.writeLog(query)
         self.writeLog(bindparams)
      try:
          result = query.execute()
          for path in result:
              return path[0]
      except:
          msg="\n### Query:\n"+str(query)
          self.printExcept(msg)
          raise "Fail in countBlocks '%s' '%s'"%(query,str(whereClause))

  def getDatasetPathFromMatch(self,whereCond,row=0,limit=0,bDict={},site=""):
      siteSel,siteWhere=self.getSiteClause(site)
      oList=[]
      query=""
      try:
          if self.dbManager.dbType[self.dbsInstance]=='oracle':
             sel="SELECT DISTINCT tblk.path tblk_path, tprd.CreationDate tprd_cdate FROM Block tblk LEFT OUTER JOIN ProcessedDataset tprd ON tblk.dataset = tprd.id %s where %s %s ORDER BY tprd.CreationDate DESC"%(siteSel,whereCond,siteWhere)
             if limit:
                sel="SELECT tblk_path, tprd_cdate FROM (%s) group by rownum, tblk_path, tprd_cdate having rownum>%s and rownum<=%s ORDER BY tprd_cdate DESC"%(sel,row,row+limit)
          else:
             sel="SELECT DISTINCT Path,CreationDate FROM Block tblk %s where %s %s COLLATE latin1_bin order by CreationDate DESC "%(siteSel,whereCond,siteWhere)
             if limit:
                sel+="limit %s, %s"%(row,row+limit)
          bParams= []
          for bind_param in bDict.keys():
              bParams.append(sqlalchemy.bindparam(key=bind_param,value=bDict[bind_param]))
          if siteWhere:
             bParams.append(sqlalchemy.bindparam(key="site",value=site))
          query  = sqlalchemy.text(sel,bindparams=bParams,bind=self.dbManager.engine[self.dbsInstance])
#          print "\n\n+++getDatasetPathFromMatch",self.printQuery(query),bParams
          if self.verbose:
             self.writeLog(query)
             self.writeLog(bParams)
          result = query.execute()
          for path in result:
              p,mdate = path
              oList.append(p)
      except:
          msg="\n### Query:\n"+str(query)
          self.printExcept(msg)
          raise "Fail in getDatasetPathMatch %s"%pattern
      return oList

  def buildRegExpQuery(self,tableName,colName,pattern,op="",fromRow=0,limit=0,count=0):
      if self.dbManager.dbType[self.dbsInstance]=='oracle':
         regExp="REGEXP_LIKE(Path,:p)"
         if op.lower().find("no")!=-1:
            regExp="NOT "+regExp
      else: # MySQL
         regExp="Path REGEXP :p"
      query=""
      try:
          if count:
             query  = sqlalchemy.text("select COUNT(DISTINCT Path) from Block where %s"%regExp, bind=self.dbManager.engine[self.dbsInstance])
             result = query.execute(p=pattern)
             for r in result:
                 return r[0]
          else:
             return self.getDatasetPathFromMatch(regExp,pattern,fromRow,limit)
      except:
          msg="\n### Query:\n"+str(query)
          self.printExcept(msg)
          raise "Fail to get content for table='%s'"%tableName
      return oList
  #########
      
  def bindWhereClause(self,whereClause):
      # replace (,) with ___ to enable proper splitting
      whereList=whereClause.replace("(","___ ( ___").replace(")","___ ) ___").split("___")
#      whereList=whereClause.split()
      # loop over whereList and search for pattern Table.Column which should follow by 
      # one opearator and left value
      knownOperators=["like","=",">",">=","<","<="]
      bindparams = []
      for idx in xrange(0,len(whereList)):
          item=whereList[idx]
          if item.find(".")!=-1:
             bind_param=""
             try:
                 condList=item.strip().split()
                 tableColumn=condList[0]
                 op=condList[1]
                 value=' '.join(condList[2:])
                 if not knownOperators.count(op):
                    raise DDException(args="Unknown operator found in where clause experession")
                 rval=value.strip().replace("'","")
                 tableName,col=tableColumn.split(".")
                 bind_param="%s_%s_%s"%(tableName,col,idx)
                 whereList[idx]=whereList[idx].replace(value,":"+bind_param)
             except:
                 if self.verbose:
                    self.writeLog(getExcept())
                 printExcept()
                 raise DDException(args="Your condition %s should be in a form: Table.Column <operator> '<value>'. Please note, spaces should be presented in order to make it work"%item)

#             op=whereList[idx+1]
#             if not knownOperators.count(op):
#                raise DDException(args="Unknown operator found in where clause experession")
#             rval=whereList[idx+2].replace("'","")
#             tableName,col=item.split(".")
#             try:
#                 t=self.dbManager.getTable(self.dbsInstance,tableName)
#             except:
#                 msg="Unkown table '%s'\n"%tableName
#                 raise DDException(args=printExcept(msg))
#             lval=self.col(t,col)
#             bind_param="%s_%s_%s"%(tableName,col,idx)
#             whereList[idx+2]=":"+bind_param
             bindparams.append(sqlalchemy.bindparam(key=bind_param,value=rval))
      textClause = sqlalchemy.text(text=' '.join(whereList),engine=self.dbManager.engine[self.dbsInstance],bindparams=bindparams)
      return textClause

  def queryMaker(self,iList,whereClause="",limit=0,offset=0,execute=1):
      """ 
         Build a query out of input iList=['TableName.ColumnName',]
         whereClause is a list of (table,col,operator,value)
      """
      # scan whereClause and use lval's for query builder
      fw=[]
      wc=whereClause.split()
      bWords=['=','!=','like','not']
      for idx in xrange(0,len(wc)):
          item=wc[idx]
          if item.find(".")!=-1 and idx!=len(wc) and bWords.count(wc[idx+1]):
             n=item.replace("(","").replace(")","")
             table,col=n.split(".")
             fw.append("%s.%s"%(self.dbManager.getDBTableName(self.dbsInstance,table),col) )
      # I need to lookup SQLAlchemy tables
      _list=[]
      for item in iList+fw:
          if not _list.count(item):
             _list.append(item)
      oSel = []
      for item in _list:
          table,col=item.split(".")
          if col=="*":
             for c in self.dbManager.getColumns(self.dbsInstance,table):
                 oSel.append(self.col(self.dbManager.getTable(self.dbsInstance,table),c))
          else:
             oSel.append(self.col(self.dbManager.getTable(self.dbsInstance,table),col))
      md     = self.dbManager.metaDict[self.dbsInstance]
      sel    = sqlalchemy.select(oSel)
      qb     = Schema(self.dbManager.dbTables[self.dbsInstance])
      query  = qb.BuildQuery(sel)
      if  whereClause:
          if not self.checkWhereClause(whereClause):
             raise DDException(args="Invalid where clause found")
          textClause=self.bindWhereClause(whereClause)
          query.append_whereclause(textClause)

      query.distinct=True
      if long(limit):
         query=self.addQueryLimits(query,offset,limit)
#      print "\n\n###queryMaker",self.printQuery(query),query.__dict__,type(query)
#      print str(query)
      if  execute:
          res = self.executeSQLQuery(query)
      return str(query),res

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
          
  def checkWhereClause(self,whereClause):
      if string.find(whereClause.lower(),"insert")!=-1:
         return False
      if string.find(whereClause.lower(),"update")!=-1:
         return False
      if string.find(whereClause.lower(),"alter")!=-1:
         return False
      if whereClause.lower().find("select")!=-1:
         return False
      return True

  def checkQuery(self,query):
      if type(query) is sqlalchemy.sql.Select: return True
      if type(query) is sqlalchemy.sql._TextClause: return True
      if string.find(query.lower(),"insert")!=-1:
         return False
      if string.find(query.lower(),"update")!=-1:
         return False
      if string.find(query.lower(),"alter")!=-1:
         return False
        
      known_tables = self.dbManager.getTableNames(self.dbsInstance)
      found=0
      for tName in known_tables:
          if string.find(query.lower(),tName.lower())!=-1:
             found=1
      if not found:
         return False
      return True

  def executeSQLQuery(self,query):
      if not self.checkQuery(query):
         return "Your query is not valid, you either specified unkonwn table or tried to perform insert/update operation, which are forbidden."
      if self.verbose==2:
         print "Execute SQL query:\n",query

      con = self.connectToDB()
      res = ""
      if type(query) is sqlalchemy.sql.Select:
         query.use_labels=True
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

  def getConfigContent(self,dbsInst,id):
      self.setDBSDLS(dbsInst)
      con = self.connectToDB()
      try:
          tqps = self.alias('QueryableParameterSet','tqps')
          sel  = sqlalchemy.select([self.col(tqps,'Content')],self.col(tqps,'ID')==id)
          res  = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
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
                     tqps.join(talc,onclause=self.col(talc,'ParameterSetID')==self.col(tqps,'ID'))
                     .join(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                            ],distinct=True
                                 )
          if rel and rel!="*":
             sel.append_whereclause(self.col(tapv,'Version')==rel)
          result = self.getSQLAlchemyResult(con,sel)
          for item in result:
              content=item[0]
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getConfigContentByName"
      if self.verbose:
         self.writeLog("time getConfigContentByName: %s"%(time.time()-t1))
      self.closeConnection(con)
      return content

  def getLFNsFromRunRanges(self,dbsInst,dataset,runRanges):
      con = self.connectToDB()
      try:
          tb   = self.alias('Block','tb')
          tf   = self.alias('Files','tf')
          tfs  = self.alias('FileStatus','tfs')
          tft  = self.alias('FileType','tft')
          tfrl = self.alias('FileRunLumi','tfrl')
          tr   = self.alias('Runs','tr')
          oSel = [self.col(tf,'LogicalFileName')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tb.join(tf,self.col(tf,'Block')==self.col(tb,'ID'))
                       .outerjoin(tfrl,onclause=self.col(tf,'ID')==self.col(tfrl,'Fileid'))
                       .outerjoin(tr,onclause=self.col(tr,'ID')==self.col(tfrl,'Run'))
                     ],distinct=True,order_by=oSel
                                  )
          sel.append_whereclause(self.col(tb,'Path')==dataset)
          # parse runRanges=minRun-maxRun,minRun-maxRun
          runList=[]
          for item in runRanges.split(","):
              minR,maxR=item.split("-")
              runList.append((minR,maxR))
          condList=[]
          for minR,maxR in runList:
              cList=[]
              cList.append(self.col(tr,'RunNumber')>=minR)
              cList.append(self.col(tr,'RunNumber')<=maxR)
              condList.append(sqlalchemy.and_(*cList))
          if len(condList): 
             sel.append_whereclause(sqlalchemy.or_(*condList))
          result = self.getSQLAlchemyResult(con,sel)
          #print "\n+++ getLFNsFromRuNList",self.printQuery(sel),runList
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getLFNs"
      oList=[]
      for item in result:
          if not item[0]: continue
          oList.append(item)
      self.closeConnection(con)
      return oList

  def getLFNs(self,dbsInst,blockName,dataset,run="*",lfn="*"):
      prim="*"
      tier="*"
      proc="*"
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
          tfs  = self.alias('FileStatus','tfs')
          tft  = self.alias('FileType','tft')
          tfrl = self.alias('FileRunLumi','tfrl')
          tr   = self.alias('Runs','tr')
          oSel = [self.col(tf,'LogicalFileName'),self.col(tf,'FileSize'),self.col(tfs,'Status'),self.col(tft,'Type'),self.col(tf,'NumberOfEvents'),self.col(tf,'Checksum')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tprd.join(tf,self.col(tf,'Dataset')==self.col(tprd,'ID'))
                         .join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                         .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                         .join(tb,onclause=self.col(tb,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tfs,onclause=self.col(tf,'FileStatus')==self.col(tfs,'ID'))
                         .outerjoin(tft,onclause=self.col(tf,'FileType')==self.col(tft,'ID'))
                     ],distinct=True,order_by=oSel
                                  )
          if  run and run!="*":                        
              sel  = sqlalchemy.select(oSel,
                     from_obj=[
                         tprd.join(tf,self.col(tf,'Dataset')==self.col(tprd,'ID'))
                             .join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                             .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                             .join(tb,onclause=self.col(tb,'Dataset')==self.col(tprd,'ID'))
                             .outerjoin(tfs,onclause=self.col(tf,'FileStatus')==self.col(tfs,'ID'))
                             .outerjoin(tft,onclause=self.col(tf,'FileType')==self.col(tft,'ID'))
                             .outerjoin(tfrl,onclause=self.col(tf,'ID')==self.col(tfrl,'Fileid'))
                             .outerjoin(tr,onclause=self.col(tr,'ID')==self.col(tfrl,'Run'))
                         ],distinct=True,order_by=oSel
                                      )
          sel.append_whereclause(self.col(tf,'Block')==self.col(tb,'ID'))
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             self.joinTiers(sel,tpds,tier,tprd)
          if blockName and blockName!="*":
             sel.append_whereclause(self.col(tb,'Name')==blockName)
          if run and run!="*":
             sel.append_whereclause(self.col(tr,'RunNumber')==run)
          if lfn and lfn!="*":
             sel.append_whereclause(self.col(tf,'LogicalFileName').like(lfn))
          sel.append_whereclause(self.col(tfs,'Status')!="INVALID")   
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getLFNs"
      oList=[]
      for item in result:
          if not item[0]: continue
          oList.append(item)
      self.closeConnection(con)
      return oList

  def getLFNs_Runs(self,dbsInst,blockName):
      con = self.connectToDB()
      try:
          tb   = self.alias('Block','tb')
          tf   = self.alias('Files','tf')
          tfs  = self.alias('FileStatus','tfs')
          tft  = self.alias('FileType','tft')
          tfrl = self.alias('FileRunLumi','tfrl')
          tr   = self.alias('Runs','tr')
          oSel = [self.col(tf,'LogicalFileName'),self.col(tr,'RunNumber')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tb.join(tf,self.col(tf,'Block')==self.col(tb,'ID'))
                       .outerjoin(tfrl,onclause=self.col(tf,'ID')==self.col(tfrl,'Fileid'))
                       .outerjoin(tr,onclause=self.col(tr,'ID')==self.col(tfrl,'Run'))
                     ],distinct=True,order_by=oSel
                                  )
          if blockName and blockName!="*":
             sel.append_whereclause(self.col(tb,'Name')==blockName)
          #sel.append_whereclause(self.col(tfs,'Status')!="INVALID")   
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getLFNs_Runs"
      oDict={}
      for item in result:
          if not item[0]: continue
          lfn,run=item
          if oDict.has_key(lfn):
             _list=oDict[lfn]
             _list+=[run]
             oDict[lfn]=_list
          else:
             oDict[lfn]=[run]
      self.closeConnection(con)
      return oDict

  def getLFN_Branches(self,dbsInst,lfn,userMode='user'):
      con = self.connectToDB()
      try:
          tb   = self.alias('Branch','tb')
          tfb  = self.alias('FileBranch','tfb')
          tf   = self.alias('Files','tf')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')
          if userMode!='user':
             oSel = [self.col(tb,'Name'),self.col(tp1,'DistinguishedName'),self.col(tb,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tb,'LastModificationDate')]
          else:
             oSel = [self.col(tb,'Name')]
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
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getLFN_Branches"
      if userMode!='user':
         tList=['Name','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      else:
         tList=['Name']
      oList=[]
      dn1=cDate=dn2=mDate=""
      for item in result:
          if not item[0]: continue
          if userMode!='user':
             name,dn1,cDate,dn2,mDate=item
             dn1=parseCreatedBy(dn1)
             dn2=parseCreatedBy(dn2)
             cDate=timeGMT(cDate)
             mDate=timeGMT(mDate)
             oList.append((name,dn1,cDate,dn2,mDate))
          else:
             name=item[0]
             oList.append(item)
      self.closeConnection(con)
      return tList,oList

  def getLFN_Lumis(self,dbsInst,lfn,userMode='user'):
      con = self.connectToDB()
      try:
          tls   = self.alias('LumiSection','tls')
          tfr   = self.alias('FileRunLumi','tfr')
          trun  = self.alias('Runs','trun')
          tf    = self.alias('Files','tf')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          if userMode!='user':
             oSel = [self.col(tls,'LumiSectionNumber'),self.col(trun,'RunNumber'),self.col(tls,'StartEventNumber'),self.col(tls,'EndEventNumber'),self.col(tls,'LumiStartTime'),self.col(tls,'LumiEndTime'),self.col(tp1,'DistinguishedName'),self.col(tls,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tls,'LastModificationDate')]
          else:
             oSel = [self.col(tls,'LumiSectionNumber'),self.col(trun,'RunNumber'),self.col(tls,'StartEventNumber'),self.col(tls,'EndEventNumber'),self.col(tls,'LumiStartTime'),self.col(tls,'LumiEndTime')]
          sel  = sqlalchemy.select(oSel,
                 from_obj=[
                     tf.outerjoin(tfr,self.col(tfr,'Fileid')==self.col(tf,'ID'))
                       .outerjoin(trun,onclause=self.col(tfr,'Run')==self.col(trun,'ID'))
                       .outerjoin(tls,onclause=self.col(tfr,'Lumi')==self.col(tls,'ID'))
                       .outerjoin(tp1,onclause=self.col(tls,'CreatedBy')==self.col(tp1,'ID'))
                       .outerjoin(tp2,onclause=self.col(tls,'LastModifiedBy')==self.col(tp2,'ID'))
                     ],distinct=True,order_by=oSel
                                  )
          if lfn and lfn!="*":
             sel.append_whereclause(self.col(tf,'LogicalFileName')==lfn)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getLFN_Lumis"
      if userMode!='user':
         tList=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      else:
         tList=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime']
      oList=[]
      for item in result:
          if not item[1]: continue
          if userMode!='user':
              lumi,run,begNum,endNum,lumiBeg,lumiEnd,dn1,cDate,dn2,mDate=item
              cDate=timeGMT(cDate)
              mDate=timeGMT(mDate)
              dn1=parseCreatedBy(dn1)
              dn2=parseCreatedBy(dn2)
              oList.append((lumi,run,begNum,endNum,lumiBeg,lumiEnd,dn1,cDate,dn2,mDate))
          else:
              lumi,run,begNum,endNum,lumiBeg,lumiEnd=item
              oList.append((lumi,run,begNum,endNum,lumiBeg,lumiEnd))
      self.closeConnection(con)
      return tList,oList

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
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getLFN_Tiers"
      tList=['Name','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      oList=[]
      for item in result:
          if not item[0]: continue
          name,cBy,cDate,mBy,mDate=item
          cBy=parseCreatedBy(cBy)
          mBy=parseCreatedBy(mBy)
#          oList.append(item)
          oList.append(name,cBy,cDate,mBy,mDate)
      self.closeConnection(con)
      return tList,oList

  def alias(self,tableName,aliasName=""):
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

  def getRunSummaryTIF(self,run):
      """ I need to make the following query
            http://cmsmon.cern.ch/cmsdb/servlet/RunSummaryTIF?RUN=8757,8762&DB=cms_pvss_tk&XML=1
      """
      runDBDict={}
      conn = httplib.HTTPConnection("cmsmon.cern.ch")
      if type(run) is types.ListType:
         runUrl=""
         for r in run:
             runUrl+="%s,"%r
         conn.request("GET", "/cmsdb/servlet/RunSummaryTIF?RUN=%s&DB=cms_pvss_tki&XML=1"%runUrl[:-1])
      else:
         conn.request("GET", "/cmsdb/servlet/RunSummaryTIF?RUN=%s&DB=cms_pvss_tki&XML=1"%run)
      r1 = conn.getresponse()
      if int(r1.status)==200:
         data=r1.read()
         elem=elementtree.ElementTree.fromstring(data)
         for i in elem:
             if i.tag=="query":
                query_data=i # get query
                for j in query_data:
                    if  j.tag=="row":
                        run=0
                        runmode=""
                        system=""
                        for k in j.getchildren():
                            if k.tag.lower()=="run":     run=int(k.text)
                            if k.tag.lower()=="runmode": runmode=k.text
                            if k.tag.lower()=="system":  system=k.text
                        if run and not runDBDict.has_key(run):
                           runDBDict[run]=(runmode,system)
      return runDBDict

  def getRunDBInfo(self,run):
      """ I need to make the following query
            http://cmsmon.cern.ch/cmsdb/servlet/RunSummary?RUN=12024,12222&XML=1
      """
      runDBDict={}
      conn = httplib.HTTPConnection("cmsmon.cern.ch")
      if type(run) is types.ListType:
         runUrl=""
         for r in run:
             runUrl+="%s,"%r
         conn.request("GET", "/cmsdb/servlet/RunSummary?RUN=%s&XML=1"%runUrl[:-1])
      else:
         conn.request("GET", "/cmsdb/servlet/RunSummary?RUN=%s&XML=1"%run)
      r1 = conn.getresponse()
      if int(r1.status)==200:
         data=r1.read()
         elem=elementtree.ElementTree.fromstring(data)
         for i in elem:
             if i.tag=="query":
                query_data=i # get query
                for j in query_data:
                    if  j.tag=="row":
                        run=0
                        global_key=triggers=events=bfield=components=""
                        for k in j.getchildren():
                            if k.tag.lower()=="run":        run=int(k.text)
                            if k.tag.lower()=="global_key": global_key=k.text
                            if k.tag.lower()=="triggers":   triggers=k.text
                            if k.tag.lower()=="events":     events=k.text
                            if k.tag.lower()=="bfield":     bfield=k.text
                            if k.tag.lower()=="components": components=k.text
                        if run and not runDBDict.has_key(run):
                           runDBDict[run]=(global_key,triggers,events,bfield,components)
      return runDBDict

  def getRunDQInfo(self,run):
      con = self.connectToDB()
      oList  = []
      try:
          trun = self.alias('Runs','trun')
          trlq = self.alias('RunLumiQuality','trlq')
          tss  = self.alias('SubSystem','tss')
          tqv  = self.alias('QualityValues','tqv')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          oSel = [self.col(tss,'Name'),self.col(tqv,'Value')]
          sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          trun.outerjoin(trlq,onclause=self.col(trlq,'Run')==self.col(trun,'ID'))
                          .outerjoin(tss,onclause=self.col(trlq,'SubSystem')==self.col(tss,'ID'))
                          .outerjoin(tqv,onclause=self.col(tpdr,'DQValue')==self.col(tqv,'ID'))
                                ],distinct=True,
                                  order_by=oSel
                                 )
          if run and run!="*":
             sel.append_whereclause(self.col(trun,'RunNumber')==run)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getRunDQInfo"
      oList=[]
      for item in result:
          if  item and item[0]:
              name,value=item
              oList.append((name,value))
      return oList
      
  def getRuns(self,dataset,primD="*",primType="*",minRun="*",maxRun="*",fromRow=0,limit=0,count=0,userMode="user"):
      if primD.lower()=="any": primD="*"
      if primType.lower()=="any": primType="*"
      if type(minRun) is types.StringType and minRun.lower()=="any": minRun="*"
      if type(maxRun) is types.StringType and maxRun.lower()=="any": maxRun="*"
      
#      print dataset,primD,primType,minRun,maxRun,fromRow,limit,count,userMode

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
          tpdr = self.alias('ProcDSRuns','tpdr')
          trun = self.alias('Runs','trun')
          tfrl = self.alias('FileRunLumi','tfrl')
          tf   = self.alias('Files','tf')
          tfs  = self.alias('FileValidStatus','tfs')
          tse  = self.alias('StorageElement','tse')
          tsb  = self.alias('SEBlock','tsb')
          tpt  = self.alias('PrimaryDSType','tpt')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          if  count:
              oSel = [self.col(trun,'RunNumber'),self.col(tblk,'Path')]
              gBy  = list(oSel)
          else:
              oSel = [self.col(trun,'RunNumber'),self.col(trun,'NumberOfEvents'),self.col(trun,'NumberOfLumiSections'),self.col(trun,'TotalLuminosity'),self.col(trun,'StoreNumber'),self.col(trun,'StartOfRun'),self.col(trun,'EndOfRun'),self.col(tp1,'DistinguishedName'),self.col(trun,'CreationDate'),self.col(trun,'LastModificationDate'),self.col(tpt,'Type'),self.col(tblk,'Path')]
              gBy=list(oSel)

          sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          trun.join(tpdr,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
                          .join(tprd,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
                          .join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                          .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .join(tpt,onclause=self.col(tpm,'Type')==self.col(tpt,'ID'))
                          .outerjoin(tp1,onclause=self.col(trun,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(trun,'LastModifiedBy')==self.col(tp2,'ID'))
                                ],distinct=True,
                                  group_by=gBy,
                                  order_by=[sqlalchemy.desc(self.col(trun,'RunNumber'))]
                                 )
          condDict={}
          if dataset:
             sel.append_whereclause(self.col(tblk,'Path')==dataset)
             condDict[findLastBindVar(str(sel))]=dataset
          if primD and primD!="*":
             sel.append_whereclause(self.col(tpm,'Name')==primD)
             condDict[findLastBindVar(str(sel))]=primD
          if primType and primType!="*":
             sel.append_whereclause(self.col(tpt,'Type')==primType)
             condDict[findLastBindVar(str(sel))]=primType
          if minRun and minRun!="*":
             sel.append_whereclause(self.col(trun,'RunNumber')>=minRun)
             condDict[findLastBindVar(str(sel))]=minRun
          if maxRun and maxRun!="*":
             sel.append_whereclause(self.col(trun,'RunNumber')<=maxRun)
             condDict[findLastBindVar(str(sel))]=maxRun

          result=""
#          print sel
          if not count and limit:
#             sel.use_labels=True
             if  self.dbManager.dbType[self.dbsInstance]=='oracle':
                 minRow,maxRow=fromRow,fromRow+limit
                 s = """ select * from ( select a.*, rownum as rnum from ( %s ) a ) where rnum between %s and %s"""%(self.printQuery(sel),minRow,maxRow)
#                 print "not count and limit"
#                 print s
#                 print condDict
                 result=con.execute(s,condDict)
             else:
                 sel.limit=limit
                 sel.offset=fromRow
                 result = self.getSQLAlchemyResult(con,sel)
          else:       
              result = self.getSQLAlchemyResult(con,sel)
#              print sel
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getRuns"
      if count:
         total=0
         minRun=1000000000
         maxRun=0
         for i in result:
             run,path=i
             if minRun>run: minRun=run
             if maxRun<run: maxRun=run
             total+=1
         self.closeConnection(con)
         return total,minRun,maxRun
      oList=[]
      oDict={}
      pDict={} # diction of dataset path which we will fill with SE's later.
      runs=""
      for item in result:
          if  item and item[0]:
              if self.dbManager.dbType[self.dbsInstance]=='oracle' and limit:
#                 run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,fSize,nFiles,row=item
                 run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,row=item
              else:
                 run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path=item
              # lookup fileSize and number of Files for given run   
              oSel = [sqlalchemy.func.sum(self.col(tf,'FileSize').distinct()),sqlalchemy.func.count(self.col(tf,'LogicalFileName').distinct())]
              sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          trun.join(tfrl,onclause=self.col(tfrl,'Run')==self.col(trun,'ID'))
                          .join(tf,onclause=self.col(tfrl,'Fileid')==self.col(tf,'ID'))
                          .join(tfs,onclause=self.col(tf,'FileStatus')==self.col(tfs,'ID'))
                          .join(tblk,onclause=self.col(tblk,'ID')==self.col(tf,'Block'))
                                ],distinct=True )
              sel.append_whereclause(self.col(trun,'RunNumber')==run)
              sel.append_whereclause(self.col(tblk,'Path')==path)
              sel.append_whereclause(self.col(tf,'LogicalFileName')!=sqlalchemy.null())
              sel.append_whereclause(self.col(tfs,'Status')!="INVALID")   
#              print "loop run=",run
#              print sel
              result = self.getSQLAlchemyResult(con,sel)
              fSize,nFiles = result.fetchone()
#              print run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,path,fSize,nFiles
#              dsType="data"

              mBy=''
              cDate=timeGMT(cDate)
              mDate=timeGMT(mDate)
              cBy=parseCreatedBy(cBy)
              mBy=parseCreatedBy(mBy)
              if not fSize: fSize=0
              if not run: continue
#              print "\n\n#####"
#              print run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,se,fSize,nFiles

              oList.append( [run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mBy,mDate,dsType,path,[],fSize,nFiles] )
              if not pDict.has_key(path): pDict[path]=[]
              runs+="%s,"%run

      # check if we found any runs, if not just return
      if not runs:
         return [],{}

      # now let's fill pDict (dict of dataset paths) with SE's
      condList=[]   
      try:    
          oSel = [self.col(tblk,'Path'),self.col(tse,'SEName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                      tblk.join(tsb,onclause=self.col(tsb,'BlockID')==self.col(tblk,'ID'))
                          .join(tse,onclause=self.col(tsb,'SEID')==self.col(tse,'ID'))
                            ],distinct=True )
          sel.append_whereclause(self.col(tse,'SEName')!=sqlalchemy.null())
          for path in pDict.keys():
              condList.append(self.col(tblk,'Path')==path)
          if len(condList): 
             sel.append_whereclause(sqlalchemy.or_(*condList))
          result = self.getSQLAlchemyResult(con,sel)
          for item in result:
              path,se=item
              if pDict.has_key(path):
                 pDict[path]=pDict[path]+[se]
              else:
                 pDict[path]=[se]
          for idx in xrange(0,len(oList)):
              path=oList[idx][12]
              oList[idx][13]=pDict[path] # place for se's list
      except:
          msg="\n### Query:\n"+str(sel)+"\nConditions\n"+str(condList)
          self.printExcept(msg)
          raise "Fail in getRuns"
      ######### end of se's lookup

      if self.verbose:
         self.writeLog("time in getRuns: %s"%(time.time()-t1))
      self.closeConnection(con)
      runs=runs[:-1] # get rid of last comma
      runDBInfoDict={}
      if userMode!="user":
         try:
            runDBInfoDict=self.getRunSummaryTIF(runs)
         except:
            pass
      return oList,runDBInfoDict

  def getRuns_old(self,dataset,primD="*",primType="*",minRun="*",maxRun="*",fromRow=0,limit=0,count=0,userMode="user"):
      if primD.lower()=="any": primD="*"
      if primType.lower()=="any": primType="*"
      if minRun.lower()=="any": minRun="*"
      if maxRun.lower()=="any": maxRun="*"
      
#      print dataset,primD,primType,minRun,maxRun,fromRow,limit,count,userMode

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
          tpdr = self.alias('ProcDSRuns','tpdr')
          trun = self.alias('Runs','trun')
          tfrl = self.alias('FileRunLumi','tfrl')
          tf   = self.alias('Files','tf')
          tfs  = self.alias('FileValidStatus','tfs')
          tse  = self.alias('StorageElement','tse')
          tsb  = self.alias('SEBlock','tsb')
          tpt  = self.alias('PrimaryDSType','tpt')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          if  count:
#              oSel = [sqlalchemy.func.count(self.col(trun,'RunNumber').distinct())]
#              gBy  = []
              oSel = [self.col(trun,'RunNumber'),self.col(tblk,'Path')]
              gBy  = list(oSel)
          else:
              oSel = [self.col(trun,'RunNumber'),self.col(trun,'NumberOfEvents'),self.col(trun,'NumberOfLumiSections'),self.col(trun,'TotalLuminosity'),self.col(trun,'StoreNumber'),self.col(trun,'StartOfRun'),self.col(trun,'EndOfRun'),self.col(tp1,'DistinguishedName'),self.col(trun,'CreationDate'),self.col(trun,'LastModificationDate'),self.col(tpt,'Type'),self.col(tblk,'Path')]
              gBy=list(oSel)
              oSel+=[sqlalchemy.func.sum(self.col(tf,'FileSize').distinct()),sqlalchemy.func.count(self.col(tf,'LogicalFileName').distinct())]

### New way, once I'll fix problem with data type
#          obj=trun
#          obj=obj.outerjoin(tblk,onclause=self.col(trun,'Block')==self.col(tblk,'ID'))
#          if  not count:
#              if dataset or (primD and primD!="*"):
#                 obj=obj.outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
#                 empty,prim,proc,tier=string.split(dataset,"/")
#                 if (prim and prim!="*") or (primD and primD!="*"):
#                    obj=obj.outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
#                 if tier and tier!="*":
#                    obj=obj.outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
#                    obj=obj.outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
#              if primType and primType!="*":
#                 obj=obj.outerjoin(tpt,onclause=self.col(tpm,'Type')==self.col(tpt,'ID'))
#          sel = sqlalchemy.select(oSel,from_obj=[obj],distinct=True,group_by=gBy,
#                                  order_by=[sqlalchemy.desc(self.col(trun,'RunNumber'))]
#                                 )
          sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          tprd.outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
                          .join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(trun,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
                          .join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                          .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .join(tpt,onclause=self.col(tpm,'Type')==self.col(tpt,'ID'))
                          .join(tfrl,onclause=self.col(tfrl,'Run')==self.col(trun,'ID'))
                          .join(tf,onclause=self.col(tfrl,'Fileid')==self.col(tf,'ID'))
                          .join(tfs,onclause=self.col(tf,'FileStatus')==self.col(tfs,'ID'))
                          .outerjoin(tp1,onclause=self.col(trun,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(trun,'LastModifiedBy')==self.col(tp2,'ID'))
                                ],distinct=True,
                                  group_by=gBy,
                                  order_by=[sqlalchemy.desc(self.col(trun,'RunNumber'))]
                                 )
          condDict={}
          if dataset:
             empty,prim,proc,tier=string.split(dataset,"/")
             if proc and proc!="*":
                sel.append_whereclause(self.col(tprd,'Name')==proc)
                condDict[findLastBindVar(str(sel))]=proc
             if prim and prim!="*":
                sel.append_whereclause(self.col(tpm,'Name')==prim)
                condDict[findLastBindVar(str(sel))]=prim
             if tier and tier!="*":
                self.joinTiers(sel,tpds,tier,tprd,condDict)
          if primD and primD!="*":
             sel.append_whereclause(self.col(tpm,'Name')==primD)
             condDict[findLastBindVar(str(sel))]=primD
          if primType and primType!="*":
             sel.append_whereclause(self.col(tpt,'Type')==primType)
             condDict[findLastBindVar(str(sel))]=primType
          if minRun and minRun!="*":
             sel.append_whereclause(self.col(trun,'RunNumber')>=minRun)
             condDict[findLastBindVar(str(sel))]=minRun
          if maxRun and maxRun!="*":
             sel.append_whereclause(self.col(trun,'RunNumber')<=maxRun)
             condDict[findLastBindVar(str(sel))]=maxRun

          sel.append_whereclause(self.col(tblk,'Name')!=sqlalchemy.null())
          sel.append_whereclause(self.col(tf,'Dataset')==self.col(tprd,'ID'))
          sel.append_whereclause(self.col(tf,'LogicalFileName')!=sqlalchemy.null())
          status="INVALID"
          sel.append_whereclause(self.col(tfs,'Status')!=status)   
          condDict[findLastBindVar(str(sel))]=status
          result=""
          print sel
          print condDict
          if not count and limit:
#             sel.use_labels=True
             if  self.dbManager.dbType[self.dbsInstance]=='oracle':
                 minRow,maxRow=fromRow,fromRow+limit
                 s = """ select * from ( select a.*, rownum as rnum from ( %s ) a ) where rnum between %s and %s"""%(self.printQuery(sel),minRow,maxRow)
                 print "not count and limit"
                 print s
                 print condDict
                 result=con.execute(s,condDict)
             else:
                 sel.limit=limit
                 sel.offset=fromRow
                 result = self.getSQLAlchemyResult(con,sel)
          else:       
              result = self.getSQLAlchemyResult(con,sel)
#              print sel
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getRuns"
      if count:
#         res = result.fetchone()[0]
         total=0
         for i in result: total+=1
         self.closeConnection(con)
#         return long(res)
         return total
      oList=[]
      oDict={}
      pDict={} # diction of dataset path which we will fill with SE's later.
      runs=""
      for item in result:
          if  item and item[0]:
              if self.dbManager.dbType[self.dbsInstance]=='oracle' and limit:
                 run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,fSize,nFiles,row=item
              else:
                 run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,fSize,nFiles=item
              mBy=''
              cDate=timeGMT(cDate)
              mDate=timeGMT(mDate)
              cBy=parseCreatedBy(cBy)
              mBy=parseCreatedBy(mBy)
              if not fSize: fSize=0
              if not run: continue
#              print "\n\n#####"
#              print run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,se,fSize,nFiles

              oList.append( [run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mBy,mDate,dsType,path,[],fSize,nFiles] )
              if not pDict.has_key(path): pDict[path]=[]
              runs+="%s,"%run

#      printListElements(oList,'oList')
      # now let's fill pDict (dict of dataset paths) with SE's
      try:    
          oSel = [self.col(tblk,'Path'),self.col(tse,'SEName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                      tblk.join(tsb,onclause=self.col(tsb,'BlockID')==self.col(tblk,'ID'))
                          .join(tse,onclause=self.col(tsb,'SEID')==self.col(tse,'ID'))
                            ],distinct=True )
          sel.append_whereclause(self.col(tse,'SEName')!=sqlalchemy.null())
          condList=[]   
          for path in pDict.keys():
              condList.append(self.col(tblk,'Path')==path)
          if len(condList): 
             sel.append_whereclause(sqlalchemy.or_(*condList))
          result = self.getSQLAlchemyResult(con,sel)
          for item in result:
              path,se=item
              if pDict.has_key(path):
                 pDict[path]=pDict[path]+[se]
              else:
                 pDict[path]=[se]
          for idx in xrange(0,len(oList)):
              path=oList[idx][12]
              oList[idx][13]=pDict[path] # place for se's list
      except:
          msg="\n### Query:\n"+str(sel)+"\nConditions\n"+str(condList)
          self.printExcept(msg)
          raise "Fail in getRuns"
      ######### end of se's lookup

      if self.verbose:
         self.writeLog("time in getRuns: %s"%(time.time()-t1))
      self.closeConnection(con)
      runs=runs[:-1] # get rid of last comma
      runDBInfoDict={}
      if userMode!="user":
         try:
            runDBInfoDict=self.getRunSummaryTIF(runs)
         except:
            pass
      return oList,runDBInfoDict

  def getRunRangeForDataset(self,dataset):
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpdr = self.alias('ProcDSRuns','tpdr')
          tblk = self.alias('Block','tblk')
          trun = self.alias('Runs','trun')
          tfrl = self.alias('FileRunLumi','tfrl')
          tf   = self.alias('Files','tf')

          oSel = [sqlalchemy.func.min(self.col(trun,'RunNumber')),sqlalchemy.func.max(self.col(trun,'RunNumber'))]
          sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          tblk.join(tprd,onclause=self.col(tprd,'ID')==self.col(tblk,'Dataset'))
                          .outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(trun,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
#                          tblk.outerjoin(tf,onclause=self.col(tf,'Block')==self.col(tblk,'ID'))
#                          .outerjoin(tfrl,onclause=self.col(tfrl,'Fileid')==self.col(tf,'ID'))
#                          .outerjoin(trun,onclause=self.col(tfrl,'Run')==self.col(trun,'ID'))
                                ] )
          sel.append_whereclause(self.col(tblk,'Path')==dataset)
#          sel.append_whereclause(self.col(tf,'LogicalFileName')!=sqlalchemy.null())
          result = self.getSQLAlchemyResult(con,sel)
          oList = result.fetchall()
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getRunRangeForDataset"
      rMin=oList[0][0]
      rMax=oList[0][1]
      if not rMin: rMin="N/A"
      if not rMax: rMax="N/A"
      self.closeConnection(con)
      return rMin,rMax

  def getRunsForPrimary(self,prim="any",primType="any"):
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tprd = self.alias('ProcessedDataset','tprd')
          tpm  = self.alias('PrimaryDataset','tpm')
          tpmt = self.alias('PrimaryDSType','tpmt')
          tpdr = self.alias('ProcDSRuns','tpdr')
          trun = self.alias('Runs','trun')
#          tfrl = self.alias('FileRunLumi','tfrl')
#          tf   = self.alias('Files','tf')

          oSel = [sqlalchemy.func.min(self.col(trun,'RunNumber')),sqlalchemy.func.max(self.col(trun,'RunNumber'))]
          if prim.lower()=="any" and primType.lower()=="any":
              sel  = sqlalchemy.select(oSel,from_obj=[trun])
          else:
              sel  = sqlalchemy.select(oSel,
                           from_obj=[
                              tprd.join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                              .join(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
                              .outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
                              .outerjoin(trun,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
#                          tprd.outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
#                          .outerjoin(trun,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
#                          .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
#                          .join(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
#                          .outerjoin(tfrl,onclause=self.col(tfrl,'Run')==self.col(trun,'ID'))
#                          .outerjoin(tf,onclause=self.col(tfrl,'Fileid')==self.col(tf,'ID'))
                                ],distinct=True
                                 )
          if prim and prim.lower()!="any":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if primType and primType.lower()!="any":
             sel.append_whereclause(self.col(tpmt,'Type')==primType)
#          sel.append_whereclause(self.col(tf,'LogicalFileName')!=sqlalchemy.null())
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getRunsForPrimary"
      oList = result.fetchall()
      rMin=oList[0][0]
      rMax=oList[0][1]
      if not rMin: rMin="N/A"
      if not rMax: rMax="N/A"
      if self.verbose:
         self.writeLog("time in getRunsForPrimary: %s"%(time.time()-t1))
      self.closeConnection(con)
      return rMin,rMax

  def getDbsData(self,dataset):
      kwargs={'datasetPath':dataset,'fullOutput':1}
      return self.listBlocks(kwargs)

  def getDbsBlockData(self,blockName):
      kwargs={'blockName':blockName,'fullOutput':1}
      return self.listBlocks(kwargs)

  def getData(self,dataset,site="Any",userMode="user",idx=-1):
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
# This code is ready for use in DBS2, once SEnames will be in place
      kwargs={'datasetPath':dataset,'site':site,'idx':idx,'userMode':userMode}
      prdDate,blockInfoDict,totEvts,totFiles,totSize,siteList = self.listBlocks(kwargs)
      return prdDate,siteList,blockInfoDict,totEvts,totFiles,sizeFormat(totSize)

  def getData_viaDLS(self,dataset,site="Any",userMode="user",idx=-1):
# The backward compatible code to use DLS
      kwargs={'datasetPath':dataset,'site':site,'idx':idx,'userMode':userMode}
      blockInfoDict,totEvts,totFiles,totSize,siteList = self.listBlocks(kwargs)
      # blockInfoDict=[nEvts,blockStatus,nFiles,blockSize,list of se's]
      siteList=[]
      for blockName in blockInfoDict.keys():
          item   = blockInfoDict[blockName]
          evts   = item[0]
          bStatus= item[1]
          nFiles = item[2]
          bBytes = item[3]
          seList = item[4]
          if not evts:
             continue # this eliminates file blocks with no events
          # query DLS
          hostList=[]
          try:
              dlsList = self.dlsApi.getLocations(blockName)
              for entry in dlsList:
                  for loc in entry.locations:
                      dlsHost = str(loc.host)
                      if site=="*" or dlsHost==site:
                         hostList.append(dlsHost)
                      if not siteList.count(dlsHost): siteList.append(dlsHost)
          except:
              if not self.quiet:
                 if self.verbose:
                    self.writeLog(getExcept())
                 printExcept()
              if site=="*":
                 hostList.append('N/A')
              if not siteList.count('N/A'): siteList.append('N/A')
              pass
          # end of DLS query
          blockInfoDict[blockName]=blockInfoDict[blockName][:4]+hostList
      siteList.sort()
      return siteList,blockInfoDict,totEvts,totFiles,sizeFormat(totSize)

  def getAnalysisDS(self,dataset="*",an_dataset="*",cDict={},fromRow=0,limit=0):
      t1=time.time()
      aDict = {}
      tDict = {}
      con   = self.connectToDB()
      oList = []
      sel   = ""
      try:
          tpm  = tDict['PrimaryDatset']    = self.alias('PrimaryDataset','tpm')
          tpds = tDict['ProcDSTier']       = self.alias('ProcDSTier','tpds')
          tdt  = tDict['DataTier']         = self.alias('DataTier','tdt')
          tprd = tDict['ProcessedDataset'] = self.alias('ProcessedDataset','tprd')
          tad  = tDict['AnalysisDataset']  = self.alias('AnalysisDataset','tad')
          tadt = tDict['AnalysisDSType']   = self.alias('AnalysisDSType','tadt')
          tads = tDict['AnalysisDSStatus'] = self.alias('AnalysisDSStatus','tads')
          tadd = tDict['AnalysisDSDef']    = self.alias('AnalysisDSDef','tadd')
          tpg  = tDict['PhysicsGroup']     = self.alias('PhysicsGroup','tpg')
          tblk = tDict['Block']            = self.alias('Block','tblk')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          if  cDict.has_key('max') and cDict['max']==1:
              oSel = [sqlalchemy.func.count(self.col(tad,'Name').distinct())]
          else:
              oSel = [self.col(tad,'Name'),self.col(tadt,'Type'),
                      self.col(tads,'Status'),self.col(tadd,'Name'),
                      self.col(tadd,'LumiSections'),
                      self.col(tadd,'LumiSectionRanges'),
                      self.col(tadd,'Runs'),
                      self.col(tadd,'RunsRanges'),
                      self.col(tadd,'Algorithms'),
                      self.col(tadd,'LFNs'),
                      self.col(tadd,'UserCut'),
                      self.col(tadd,'Description'),
                      self.col(tp1,'DistinguishedName'),self.col(tad,'CreationDate'),
                      self.col(tp2,'DistinguishedName'),self.col(tad,'LastModificationDate'),
                      self.col(tpg,'PhysicsGroupName'),
                      self.col(tblk,'Path') ]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                     tad.join(tprd,onclause=self.col(tad,'ProcessedDS')==self.col(tprd,'ID'))
                     .join(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .join(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .join(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tadt,onclause=self.col(tad,'Type')==self.col(tadt,'ID'))
                     .outerjoin(tads,onclause=self.col(tad,'Status')==self.col(tads,'ID'))
                     .outerjoin(tadd,onclause=self.col(tad,'Definition')==self.col(tadd,'ID'))
                     .outerjoin(tpg,onclause=self.col(tad,'PhysicsGroup')==self.col(tpg,'ID'))
                     .outerjoin(tp1,onclause=self.col(tad,'CreatedBy')==self.col(tp1,'ID'))
                     .outerjoin(tp2,onclause=self.col(tad,'LastModifiedBy')==self.col(tp2,'ID'))
                            ]
                                 )
          prim="*"
          tier="*"
          proc="*"
          if dataset and dataset!="*":
             empty,prim,proc,tier=dataset.split('/')
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          if tier and tier!="*":
             self.joinTiers(sel,tpds,tier,tprd)
          if an_dataset and an_dataset!="*":
             sel.append_whereclause(self.col(tad,'Name')==an_dataset)
          for key in cDict.keys():
              if key=='max': continue
              op,val=cDict[key]
              if not val: continue
              tableName,col=key.split(".")
              lval=self.col(tDict[tableName],col)
              if op=="=":
                 sel.append_whereclause(lval==val)
              elif op=="like":
                 sel.append_whereclause(lval.like("%%%s%%"%str(val)))
              elif op=="likeLeft":
                 sel.append_whereclause(lval.like("%%%s"%str(val)))
              elif op=="likeRight":
                 sel.append_whereclause(lval.like("%s%%"%str(val)))
              elif op=="whereClause":
                 sel.append_whereclause(val)
          sel.append_whereclause(self.col(tad,'Name')!=sqlalchemy.null())
          # to avoid ORA-00932: inconsistent datatypes: expected - got CLOB, I don't need to
          # supply distinct and order while dealing with ORACLE
          # http://forums.bea.com/bea/message.jspa?messageID=202461255&tstart=0
          if self.dbManager.dbType[self.dbsInstance]!='oracle':
             sel.distinct=True
             sel.order_by=[sqlalchemy.desc(self.col(tp2,'LastModifiedBy'))]
          if not cDict.has_key('max'):
             sel.use_labels=True
             if limit:
                sel.limit=limit
                sel.offset=fromRow
#          print self.printQuery(sel)
#          print str(sel).replace("\n","")
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getAnalysisDS"
      if cDict.has_key('max') and cDict['max']==1:
         res = result.fetchone()[0]
         self.closeConnection(con)
         return res # we return how many datasets are found
      aList=[]
      for item in result:
          if not item[0]: continue
          name,adtType,status,dName,dLumi,dLumiRange,dRuns,dRunRange,dAlg,dLFN,dCut,dDesc,cBy,cDate,mBy,mDate,group,path=item
          cDate=timeGMT(cDate)
          mDate=timeGMT(mDate)
          cBy=parseCreatedBy(cBy)
          mBy=parseCreatedBy(mBy)
          # to avoid ProgrammingError: LOB variable no longer valid after subsequent fetch
          dLumi      = parseBLOBdata(dLumi)
          dLumiRange = parseBLOBdata(dLumiRange)
          dRunRange  = parseBLOBdata(dRunRange) 
          dRuns      = parseBLOBdata(dRuns)
          dCut       = parseBLOBdata(dCut)
          dDesc      = parseBLOBdata(dDesc)
          dAlg       = parseBLOBdata(dAlg)
          dLFN       = parseBLOBdata(dLFN)
          dCut       = parseBLOBdata(dCut)
          dDesc      = parseBLOBdata(dDesc)
          groupItem = (name,adtType,status,dName,dLumi,dLumiRange,dRuns,dRunRange,dAlg,dLFN,dCut,dDesc,cBy,cDate,mBy,mDate,group,path)
          if not aList.count( groupItem ):
             aList.append( groupItem )
      if self.verbose:
         self.writeLog("time getAnalysisDS: %s"%(time.time()-t1))
      self.closeConnection(con)
      return aList

  def getBlockInfoForSite(self,site,iLimit=25,iOffset=0):
      if site.lower()=='all' or site.lower()=='any': site="*"
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tblk = self.alias('Block','tblk')
#          tblk2= self.alias('Block','tblk2')
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          oSel = [self.col(tblk,'Path'),self.col(tblk,'Name'),self.col(tblk,'BlockSize'),self.col(tblk,'NumberOfFiles'),self.col(tblk,'NumberOfEvents'),self.col(tblk,'OpenForWriting'),self.col(tp1,'DistinguishedName'),self.col(tblk,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tblk,'LastModificationDate'),self.col(tse,'SEName')]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                     tblk.join(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                     .join(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                     .outerjoin(tp1,onclause=self.col(tblk,'CreatedBy')==self.col(tp1,'ID'))
                     .outerjoin(tp2,onclause=self.col(tblk,'LastModifiedBy')==self.col(tp2,'ID'))
                            ],distinct=True,order_by=oSel
                                 )
          if site!="*":
             sel.append_whereclause(self.col(tse,'SEName')==site)
          if int(iLimit):
             sel.limit=int(iLimit)
             sel.offset=int(iOffset)
          sel.use_labels=True
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in listBlocksFromSite"
      aList=[]
      aDict={}
      for item in result:
          prdName,blockName,blockSize,nFiles,nEvts,blockStatus,cBy,cDate,mBy,mDate,sename=item
          cDate=timeGMT(cDate)
          mDate=timeGMT(mDate)
          cBy=parseCreatedBy(cBy)
          mBy=parseCreatedBy(mBy)
          if not blockName: continue
          aDict={'ProcDSName':prdName,'Name':blockName,'BlockSize':blockSize,'NumberOfFiles':nFiles,'NumberOfEvents':nEvts,'OpenForWriting':blockStatus,'CreatedBy':cBy,'CreationDate':cDate,'LastModifiedBy':mBy,'LastModificationDate':mDate}
          aList.append(aDict)
      if self.verbose:
         self.writeLog("time listBlocksFromSite: %s"%(time.time()-t1))
      self.closeConnection(con)
      return aList

#  def getBlocksFromSite(self,site,blockList=[]):
  def getBlocksFromSite(self,site,datasetPath):
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
                     tblk.join(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                     .join(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                            ],distinct=True,order_by=oSel
                                 )
          if site!="*":
             sel.append_whereclause(self.col(tse,'SEName')==site)
          if datasetPath!="*":
             sel.append_whereclause(self.col(tblk,'Path')==datasetPath)
#          condList=[]   
#          for block in blockList:
#              condList.append(self.col(tblk,'Name')==block)
#          if len(condList): 
#             sel.append_whereclause(sqlalchemy.or_(*condList))
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in listBlocksFromSite"
      aList=[]
      for item in result:
          aList.append(item[0])
      if self.verbose:
         self.writeLog("time listBlocksFromSite: %s"%(time.time()-t1))
      self.closeConnection(con)
      return aList

  def getLFNsFromSite(self,site,datasetPath,run="*"):
      if site.lower()=='all' or site.lower()=='any': site="*"
      if run.lower()=='all' or run.lower()=='any': run="*"
      t1=time.time()
      aDict = {}
      con = self.connectToDB()
      oList  = []
      try:
          tblk = self.alias('Block','tblk')
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')
          tf   = self.alias('Files','tf')
          tfs  = self.alias('FileStatus','tfs')
          tfrl = self.alias('FileRunLumi','tfrl')
          tr   = self.alias('Runs','tr')

          oSel = [self.col(tf,'LogicalFileName')]
          obj  = tblk.join(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
          obj  = obj.join(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
          obj  = obj.join(tf,onclause=self.col(tblk,'ID')==self.col(tf,'Block'))
          obj  = obj.join(tfs,onclause=self.col(tf,'FileStatus')==self.col(tfs,'ID'))
          if run and run!="*":
             obj=obj.outerjoin(tfrl,onclause=self.col(tf,'ID')==self.col(tfrl,'Fileid'))
             obj=obj.outerjoin(tr,onclause=self.col(tr,'ID')==self.col(tfrl,'Run'))
          sel  = sqlalchemy.select(oSel,
                   from_obj=[obj],distinct=True,order_by=oSel
                                 )
          if site!="*":
             sel.append_whereclause(self.col(tse,'SEName')==site)
          if datasetPath!="*":
             sel.append_whereclause(self.col(tblk,'Path')==datasetPath)
          if run and run!="*":
             sel.append_whereclause(self.col(tr,'RunNumber')==run)
          sel.append_whereclause(self.col(tfs,'Status')!="INVALID")   
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          self.printExcept(msg)
          raise "Fail in getLFNsFromSite"
      aList=[]
      for item in result:
          aList.append(item[0])
      if self.verbose:
         self.writeLog("time getLFNsFromSite: %s"%(time.time()-t1))
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          pass
      return bList
  ### Implementation for DDSearch
  def buildLikeExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause( tc.like(val.replace("*","%")) )
      else:
         return sel.append_whereclause( sqlalchemy.func.upper(tc).like(val.upper().replace("*","%")) )
  def buildEqExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc==val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)==val.upper())
  def buildLtExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc<=val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)<=val.upper())
  def buildGtExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc>=val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)>=val.upper())
  def buildExp(self,sel,tc,val,case):
      try:
         if val.find("*")!=-1:
            return self.buildLikeExp(sel,tc,val,case)
         elif val.find("-")!=-1:
            min,max=val.split("-")
            self.buildLtExp(sel,tc,max,case)
            return self.buildGtExp(sel,tc,min,case)
         else:
            return self.buildEqExp(sel,tc,val,case)
      except:
         traceback.print_exc()
         raise "Fail to build query for '%s', '%s', '%s', case='%s'"%(self.printQuery(sel),tc,val,case)
  def buildListExp(self,sel,tc,idList):
      condList=[]
      for id in idList:
          cList=[]
          cList.append(tc==id)
          condList.append(sqlalchemy.and_(*cList))
      if len(condList): 
         sel.append_whereclause(sqlalchemy.or_(*condList))
  def Block2Block(self,**kwargs):
      """Take a list of input vars and return list of block Ids"""
      if self.verbose: print "Call Block2Block",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tblk = self.alias('Block','tblk')
          oSel =[self.col(tblk,'Path')]
          sel  = sqlalchemy.select(oSel,from_obj=[tblk],distinct=True )
          if kwargs.has_key('block'):
             blk_name=kwargs['block']
             if blk_name.find("*")!=-1:
#                 sel.append_whereclause(self.col(tblk,'Name').like(blk_name.replace("*","%")))
                 self.buildLikeExp(sel,self.col(tblk,'Name'),blk_name,case)
             else:
#                 sel.append_whereclause(self.col(tblk,'Name').like('%%'+blk_name+'%%'))
                 self.buildLikeExp(sel,self.col(tblk,'Name'),'%%'+blk_name+'%%',case)
          if kwargs.has_key('dataset'):
             self.buildExp(sel,self.col(tblk,'Path'),kwargs['dataset'],case)
#          print self.printQuery(sel)
          return sel
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in Block2Block"

  def Pset2Algo(self,**kwargs):
      """Take a list of input vars and return list of algo Ids"""
      if self.verbose: print "Call Pset2Algo",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tq   = self.alias('QueryableParameterSet','tq')
          tac  = self.alias('AlgorithmConfig','tac')
          talgo= self.alias('ProcAlgo','talgo')
          oSel =[self.col(talgo,'ID')]
          obj  = talgo.join(tac,onclause=self.col(talgo,'Algorithm')==self.col(tac,'ID'))
          obj  = obj.join(tq,onclause=self.col(tac,'ParameterSetID')==self.col(tq,'ID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('pset'):
             self.buildExp(sel,self.col(tq,'Content'),kwargs['pset'],case)
          elif kwargs.has_key('idlist'):
             self.buildListExp(sel,self.col(tq,'ID'),kwargs['idlist'])
#          print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in Pset2Algo"
      for item in result:
          if item and item[0]:
             oList.append(item[0])
      self.closeConnection(con)
      return {'idlist':oList}

  def Rel2Algo(self,**kwargs):
      """Take a list of input vars and return list of algo Ids"""
      if self.verbose: print "Call Rel2Algo",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tav  = self.alias('AppVersion','tav')
          tac  = self.alias('AlgorithmConfig','tac')
          talgo= self.alias('ProcAlgo','talgo')
          oSel =[self.col(talgo,'ID')]
          obj  = talgo.join(tac,onclause=self.col(talgo,'Algorithm')==self.col(tac,'ID'))
          obj  = obj.join(tav,onclause=self.col(tac,'ApplicationVersion')==self.col(tav,'ID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('release'):
             self.buildExp(sel,self.col(tav,'Version'),kwargs['release'],case)
          elif kwargs.has_key('idlist'):
             self.buildListExp(sel,self.col(tav,'ID'),kwargs['idlist'])
#          print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in Rel2Algo"
      for item in result:
          if item and item[0]:
             oList.append(item[0])
      self.closeConnection(con)
      return {'idlist':oList}

  def Algo2Proc(self,**kwargs):
      """Take a list of input vars and return list of proc Ids"""
      if self.verbose: print "Call Algo2Proc",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tprd = self.alias('ProcessedDataset','tprd')
          talgo= self.alias('ProcAlgo','talgo')
          oSel =[self.col(tprd,'ID')]
          obj  = tprd.join(talgo,onclause=self.col(talgo,'Dataset')==self.col(tprd,'ID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('idlist'):
             self.buildListExp(sel,self.col(talgo,'ID'),kwargs['idlist'])
#          print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in Algo2Proc"
      for item in result:
          if item and item[0]:
             oList.append(item[0])
      self.closeConnection(con)
      return {'idlist':oList}

  def SE2Block(self,**kwargs):
      """Take a list of input vars and return list of block Ids"""
      if self.verbose: print "Call SE2Block",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tse  = self.alias('StorageElement','tse')
          tseb = self.alias('SEBlock','tseb')
          tblk = self.alias('Block','tblk')
          oSel =[self.col(tblk,'Path')]
          obj  = tblk.join(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
          obj  = obj.join(tse,onclause=self.col(tse,'ID')==self.col(tseb,'SEID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('site'):
             self.buildExp(sel,self.col(tse,'SEName'),kwargs['site'],case)
          return sel
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in SE2Block"

  def Lumi2Run(self,**kwargs):
      """Take a list of input vars and return list of run Ids"""
      if self.verbose: print "Call Lumi2Run",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      try:
          kwargs=kwargs['input']
          tr   = self.alias('Runs','tr')
          tl   = self.alias('LumiSection','tl')
          oSel =[self.col(tr,'ID')]
          obj  = tr.join(tl,onclause=self.col(tr,'ID')==self.col(tl,'RunNumber'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('lumi'):
             self.buildExp(sel,self.col(tl,'LumiSectionNumber'),kwargs['lumi'],case)
          elif kwargs.has_key('idlist'):
             self.buildListExp(sel,self.col(tl,'ID'),kwargs['idlist'])
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in Lumi2Run"
      for item in result:
          if item and item[0]:
             oList.append(item[0])
      self.closeConnection(con)
      return {'idlist':oList}

  def Run2Proc(self,**kwargs):
      """Take a list of input vars and return list of proc Ids"""
      if self.verbose: print "Call Run2Proc",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tprd = self.alias('ProcessedDataset','tprd')
          tr   = self.alias('Runs','tr')
          tpr  = self.alias('ProcDSRuns','tpr')
          oSel =[self.col(tprd,'ID')]
          obj  = tprd.join(tpr,onclause=self.col(tpr,'Dataset')==self.col(tprd,'ID'))
          obj  = obj.join(tr,onclause=self.col(tpr,'Run')==self.col(tr,'ID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('run'):
             self.buildExp(sel,self.col(tr,'RunNumber'),kwargs['run'],case)
          elif kwargs.has_key('idlist'):
             self.buildListExp(sel,self.col(tr,'ID'),kwargs['idlist'])
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in Run2Proc"
      for item in result:
          if item and item[0]:
             oList.append(item[0])
      self.closeConnection(con)
      return {'idlist':oList}

  def File2Block(self,**kwargs):
      """Take a list of input vars and return list of block Ids"""
      if self.verbose: print "Call File2Block",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tf   = self.alias('Files','tf')
          tblk = self.alias('Block','tblk')
          oSel =[self.col(tblk,'Path')]
          obj  = tblk.join(tf,onclause=self.col(tf,'Block')==self.col(tblk,'ID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('file'):
             self.buildExp(sel,self.col(tf,'LogicalFileName'),kwargs['file'],case)
          elif kwargs.has_key('idlist'):
             self.buildListExp(sel,self.col(tf,'ID'),kwargs['idlist'])
#          print self.printQuery(sel)
          return sel
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in File2Block"

  def Proc2Block(self,**kwargs):
      """Take a list of input vars and return list of block Ids"""
      if self.verbose: print "Call Proc2Block",str(kwargs)
      oList=[]
      con  = self.connectToDB()
      sel  = ""
      case = 'on'
      if kwargs.has_key('case'): case=kwargs['case']
      try:
          kwargs=kwargs['input']
          tprd = self.alias('ProcessedDataset','tprd')
          tblk = self.alias('Block','tblk')
          oSel =[self.col(tblk,'Path')]
          obj  = tblk.join(tprd,onclause=self.col(tprd,'ID')==self.col(tblk,'Dataset'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True )
          if kwargs.has_key('idlist'):
             self.buildListExp(sel,self.col(tprd,'ID'),kwargs['idlist'])
#          print self.printQuery(sel)
          return sel
      except:
          msg="\n### Query:\n"+str(sel)+str(kwargs)
          self.printExcept(msg)
          raise "Fail in Proc2Block"

  def Ads2Proc(self,**kwargs):
      print "Call Ads2Proc",str(kwargs)
      return [1,2,3]

  def FindDatasets(self,iSel,fromRow=0,limit=0,count=0):
      """Take a list of input blockid's and return list of dataset"""
      qList=[]
      for item in iSel.split():
          qList.append( eval(item) )
      # NOTE: INTERSECT works ONLY in ORACLE
      sel  = sqlalchemy.intersect(*qList)
      if not count and self.verbose:
         print "\n\n+++FindDatasets",str(iSel)
         print self.printQuery(sel)
      oList=[]
      con  = self.connectToDB()
      try:
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)+str(bidList)
          self.printExcept(msg)
          raise "Fail in FindDatasets"
      if count:
          nd=0
          for item in result:
              nd+=1
          return nd
      idx=0
      for item in result:
          if item and item[0]:
             if limit:
                if idx>=fromRow and idx<=(fromRow+limit):
                   oList.append(item[0])
             else:
                oList.append(item[0])
          idx+=1
      self.closeConnection(con)
      return oList
      
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

