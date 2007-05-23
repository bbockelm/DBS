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
import elementtree
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
      self.ddConfig  = DDConfig()
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
              id,name,ver,psType,ann,cDate,cBy,mDate,mBy=item
              cDate=timeGMT(cDate)
              mDate=timeGMT(mDate)
              cBy=parseCreatedBy(cBy)
              mBy=parseCreatedBy(mBy)
              if id and name:
                  oList.append((id,name,ver,psType,ann,cDate,cBy,mDate,mBy))
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
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
                     tqps.outerjoin(talc,onclause=self.col(talc,'ParameterSetID')==self.col(tqps,'ID'))
                     .outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                     .outerjoin(tpal,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .outerjoin(tprd,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in listApplicationsConfigsContent"
      if self.verbose:
         self.writeLog("time listApplicationsConfigsContent: %s"%(time.time()-t1))
      self.closeConnection(con)
      return oList

  def listProcessedDatasets(self,group="*",app="*",prim="*",tier="*",proc="*",site="*",userMode="user",fromRow=0,limit=0,count=0):
      if group.lower()=='any': group="*"
      if app.lower()  =='any': app  ="*"
      if prim.lower() =='any': prim ="*"
      if tier.lower() =='any': tier ="*"
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
          tseb = self.alias('SEBlock','tseb')
          tse  = self.alias('StorageElement','tse')
          tpg  = self.alias('PhysicsGroup','tpg')

          if  count:
              oSel = [sqlalchemy.func.count(self.col(tblk,'Path').distinct())]
          else:
              oSel = [self.col(tblk,'Path')]
          sel = sqlalchemy.select(oSel,
                 from_obj=[
                     tprd.outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .outerjoin(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                     .outerjoin(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
                     .outerjoin(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                     .outerjoin(tpg,onclause=self.col(tprd,'PhysicsGroup')==self.col(tpg,'ID'))
                     ],distinct=True,order_by=[sqlalchemy.desc( self.col(tblk,'Path') )] )
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
          if group and group!="*":
             sel.append_whereclause(self.col(tpg,'PhysicsGroupName')==group)
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
                 oSel=['rownum',s.c.tblk_path]
                 sel = sqlalchemy.select(oSel,group_by=oSel,order_by=[sqlalchemy.desc(s.c.tblk_path)])
                 sel.append_having( 'rownum>%s and rownum<=%s'%(fromRow,fromRow+limit) )
             else:
                 sel.limit=limit
                 sel.offset=fromRow
#          print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in listProcessedDataset"
      if count:
         res = result.fetchone()[0]
         self.closeConnection(con)
         return res
      for item in result:
#          print item
          # since we queried oracle in different way we will retrieve results differently
          if  self.dbManager.dbType[self.dbsInstance]=='oracle' and limit:
              rownum,path = item
          else:
              path = item[0]
          if not path: continue
          if not oList.count(path): oList.append(path)
      self.closeConnection(con)
      return oList

  def getPrimDetailsForRSS(self,prim="*"):
      if prim.lower() =='any': prim ="*"
      con = self.connectToDB()
      try:
          tpm  = self.alias('PrimaryDataset','tpm')
          oSel = [self.col(tpm,'CreationDate'),self.col(tpm,'Annotation')]
          sel = sqlalchemy.select(oSel,from_obj=[tpm],distinct=True )
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getPrimDetailsForRSS"
      cDate=""
      annotation=""
      for item in result:
          if item and item[0]:
             cDate,annotation=item
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
                     tprd.outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .outerjoin(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getProcDSForRss"
      for item in result:
          if not (item and item[0]): continue
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

  def joinTiers(self,sel,tjoin,tier,tprd):
      aList=[]
      bList=[]
      if tier and tier!="*":
         tierList=tier.split("-")
         for idx in xrange(0,len(tierList)):
             aList.append(self.alias('DataTier','tdt%s'%idx))
             bList.append(self.alias('ProcDSTier','tpds%s'%idx))
             tierValue=tierList[idx]
             sel.append_from( sqlalchemy.outerjoin( aList[-1],bList[-1],self.col(bList[-1],'DataTier')==self.col(aList[-1],'ID') ) )
             sel.append_whereclause(self.col(aList[-1],'Name')==tierValue)
             sel.append_whereclause(self.col(tprd,'ID')==self.col(bList[-1],'Dataset'))

  def listBlocks(self,kwargs):
      # {'blockName': (nEvt,blockStatus,nFiles,blockSize)}
      # second output:
      # [{'Name':,'BlockSize':,'NumberOfFiles':,'NumberOfEvents':,'OpenForWriting':,'CreationDate','CreationDate':,'LastModificationDate':,'LastModifiedBy'}]
#      print "\n\nlistBlocks",kwargs
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
                          .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .outerjoin(tp1,onclause=self.col(tblk,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(tblk,'LastModifiedBy')==self.col(tp2,'ID'))
                          .outerjoin(tseb,onclause=self.col(tseb,'BlockID')==self.col(tblk,'ID'))
                          .outerjoin(tse,onclause=self.col(tseb,'SEID')==self.col(tse,'ID'))
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
             sel.append_whereclause(self.col(tse,'SEName')==kwargs['site'])
          idx=-1
          if kwargs.has_key('idx'): idx=kwargs['idx']
          if kwargs.has_key('userMode') and kwargs['userMode']=="user":
             sel.append_whereclause(self.col(tblk,'NumberOfEvents')!=0)
#          print "\n\nblockList query\n",sel
          result = self.getSQLAlchemyResult(con,sel,idx)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in listBlocks"
      aDict={}
      aList=[]
      siteList=[]
      totEvt=0
      totFiles=0
      totSize=0
      oldse=""
      oldBlk=""
      for item in result:
#          print "blockList item result=",item
          if not item[0]: continue
          blockName,blockSize,nFiles,nEvts,blockStatus,cBy,cDate,mBy,mDate,sename=item
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
      return aDict,totEvt,totFiles,totSize,siteList

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
                     tprd.outerjoin(tf,onclause=self.col(tf,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     ] )
          if proc and proc!="*":
             sel.append_whereclause(self.col(tprd,'Name')==proc)
          if prim and prim!="*":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if tier and tier!="*":
             self.joinTiers(sel,tpds,tier,tprd)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
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
                      tprd.outerjoin(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                      .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                      .outerjoin(tdt,onclause=self.col(tpds,'DataTier')==self.col(tdt,'ID'))
                      .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                      .outerjoin(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                      .outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                      .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                      .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                      .outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                            ],distinct=True,
                   order_by=[self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName'),self.col(tpm,'Name'),self.col(tdt,'Name'),self.col(tprd,'Name')]
                                 )
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
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
                     talc.outerjoin(tape,onclause=self.col(talc,'ExecutableName')==self.col(tape,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
                     .outerjoin(tapf,onclause=self.col(talc,'ApplicationFamily')==self.col(tapf,'ID'))
                            ],distinct=True,
                       order_by=[self.col(tapv,'Version'),self.col(tapf,'FamilyName'),self.col(tape,'ExecutableName')]
                                 )
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
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
                     tpm.outerjoin(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
                         ],distinct=True,order_by=oSel )
              if dsType and dsType!="*":
                 sel.append_whereclause(self.col(tpmt,'Type')==dsType)
              result = self.getSQLAlchemyResult(con,sel)
              for item in result:
                  if item[0]:
                     oList.append(item[0])
          except:
              if self.verbose:
                 self.writeLog(getExcept())
              printExcept()
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
                     tprd.outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .outerjoin(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
                     .outerjoin(tpg,onclause=self.col(tprd,'PhysicsGroup')==self.col(tpg,'ID'))
                     .outerjoin(tpal,onclause=self.col(tpal,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(talc,onclause=self.col(tpal,'Algorithm')==self.col(talc,'ID'))
                     .outerjoin(tapv,onclause=self.col(talc,'ApplicationVersion')==self.col(tapv,'ID'))
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
              if self.verbose:
                 self.writeLog(getExcept())
              printExcept()
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
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
            if self.verbose:
               self.writeLog(getExcept())
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
          sel.distinct=True
          # Due to bug in SQLAlchemy, I need to make a print statement, otherwise I'm not getting results.
          # should be fixed with usage of SQLAlchemy 0.3.7
#          print "### getTableContent",self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
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
      # I need to lookup SQLAlchemy tables
      oSel = []
      for item in iList:
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

      if long(limit):
         query.limit=long(limit)
         query.offset=long(offset)
      query.Distinct=True
#      print "\n\n###queryMaker",self.printQuery(query)
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
          if self.verbose:
             self.writeLog(getExcept())
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getConfigContentByName"
      if self.verbose:
         self.writeLog("time getConfigContentByName: %s"%(time.time()-t1))
      self.closeConnection(con)
      return content

  def getLFNs(self,dbsInst,blockName,dataset,run="*"):
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
                     tprd.outerjoin(tf,self.col(tf,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                         .outerjoin(tb,onclause=self.col(tb,'Dataset')==self.col(tprd,'ID'))
                         .outerjoin(tfs,onclause=self.col(tf,'FileStatus')==self.col(tfs,'ID'))
                         .outerjoin(tft,onclause=self.col(tf,'FileType')==self.col(tft,'ID'))
                     ],distinct=True,order_by=oSel
                                  )
          if  run and run!="*":                        
              sel  = sqlalchemy.select(oSel,
                     from_obj=[
                         tprd.outerjoin(tf,self.col(tf,'Dataset')==self.col(tprd,'ID'))
                             .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                             .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                             .outerjoin(tb,onclause=self.col(tb,'Dataset')==self.col(tprd,'ID'))
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
          sel.append_whereclause(self.col(tfs,'Status')!="INVALID")   
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getLFNs"
      oList=[]
      for item in result:
          if not item[0]: continue
          oList.append(item)
      self.closeConnection(con)
      return oList

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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getLFN_Branches"
      if userMode!='user':
         tList=['Name','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      else:
         tList=['Name']
      oList=[]
      for item in result:
          if not item[0]: continue
          name,dn1,cDate,dn2,mDate=item
          dn1=parseCreatedBy(dn1)
          dn2=parseCreatedBy(dn2)
          cDate=timeGMT(cDate)
          mDate=timeGMT(mDate)
#          oList.append(item)
          oList.append((name,dn1,cDate,dn2,mDate))
      self.closeConnection(con)
      return tList,oList

  def getLFN_Lumis(self,dbsInst,lfn,userMode='user'):
      con = self.connectToDB()
      try:
          tls   = self.alias('LumiSection','tls')
          tfr  = self.alias('FileRunLumi','tfr')
          tf   = self.alias('Files','tf')
          tp1   = self.alias('Person','tp1')
          tp2   = self.alias('Person','tp2')

          if userMode!='user':
             oSel = [self.col(tls,'LumiSectionNumber'),self.col(tls,'RunNumber'),self.col(tls,'StartEventNumber'),self.col(tls,'EndEventNumber'),self.col(tls,'LumiStartTime'),self.col(tls,'LumiEndTime'),self.col(tp1,'DistinguishedName'),self.col(tls,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(tls,'LastModificationDate')]
          else:
             oSel = [self.col(tls,'LumiSectionNumber'),self.col(tls,'RunNumber'),self.col(tls,'StartEventNumber'),self.col(tls,'EndEventNumber'),self.col(tls,'LumiStartTime'),self.col(tls,'LumiEndTime')]
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getLFN_Lumis"
      if userMode!='user':
         tList=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime','CreatedBy','CreationDate','LastModifiedBy','LastModificationDate']
      else:
         tList=['LumiSectionNumber','RunNumber','StartEventNumber','EndEventNumber','LumiStartTime','LumiEndTime']
      oList=[]
      for item in result:
          if not item[0]: continue
          lumi,run,begNum,endNum,lumiBeg,lumiEnd,dn1,cDate,dn2,mDate=item
          cDate=timeGMT(cDate)
          mDate=timeGMT(mDate)
          dn1=parseCreatedBy(dn1)
          dn2=parseCreatedBy(dn2)
#          oList.append(item)
          oList.append((lumi,run,begNum,endNum,lumiBeg,lumiEnd,dn1,cDate,dn2,mDate))
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
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

  def getRunDBInfo(self,run):
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

  def getRuns(self,dataset,minRun="*",maxRun="*",fromRow=0,limit=0,count=0,userMode="user"):
      if minRun.lower()=="any": minRun="*"
      if maxRun.lower()=="any": maxRun="*"
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
          tpt  = self.alias('PrimaryDSType','tpt')
          tp1  = self.alias('Person','tp1')
          tp2  = self.alias('Person','tp2')

          if  count:
              oSel = [sqlalchemy.func.count(self.col(trun,'RunNumber').distinct())]
              gBy  = []
          else:
#              oSel = [self.col(trun,'RunNumber'),self.col(trun,'NumberOfEvents'),self.col(trun,'NumberOfLumiSections'),self.col(trun,'TotalLuminosity'),self.col(trun,'StoreNumber'),self.col(trun,'StartOfRun'),self.col(trun,'EndOfRun'),self.col(tp1,'DistinguishedName'),self.col(trun,'CreationDate'),self.col(tp2,'DistinguishedName'),self.col(trun,'LastModificationDate'),self.col(tpt,'Type'),self.col(tblk,'Path')]
              oSel = [self.col(trun,'RunNumber'),self.col(trun,'NumberOfEvents'),self.col(trun,'NumberOfLumiSections'),self.col(trun,'TotalLuminosity'),self.col(trun,'StoreNumber'),self.col(trun,'StartOfRun'),self.col(trun,'EndOfRun'),self.col(tp1,'DistinguishedName'),self.col(trun,'CreationDate'),self.col(trun,'LastModificationDate'),self.col(tpt,'Type'),self.col(tblk,'Path')]
              gBy=list(oSel)
              oSel+=[sqlalchemy.func.sum(self.col(tf,'FileSize')),sqlalchemy.func.count(self.col(tf,'LogicalFileName').distinct())]
          sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          tprd.outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(trun,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
                          .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .outerjoin(tpt,onclause=self.col(tpm,'Type')==self.col(tpt,'ID'))
                          .outerjoin(tfrl,onclause=self.col(tfrl,'Run')==self.col(trun,'ID'))
                          .outerjoin(tf,onclause=self.col(tfrl,'Fileid')==self.col(tf,'ID'))
                          .outerjoin(tp1,onclause=self.col(trun,'CreatedBy')==self.col(tp1,'ID'))
                          .outerjoin(tp2,onclause=self.col(trun,'LastModifiedBy')==self.col(tp2,'ID'))
                                ],distinct=True,
                                  group_by=gBy,
                                  order_by=[sqlalchemy.desc(self.col(trun,'RunNumber'))]
                                 )
          if dataset:
             empty,prim,proc,tier=string.split(dataset,"/")
             if proc and proc!="*":
                sel.append_whereclause(self.col(tprd,'Name')==proc)
             if prim and prim!="*":
                sel.append_whereclause(self.col(tpm,'Name')==prim)
             if tier and tier!="*":
                self.joinTiers(sel,tpds,tier,tprd)
          if minRun and minRun!="*":
             sel.append_whereclause(self.col(trun,'RunNumber')>=minRun)
          if maxRun and maxRun!="*":
             sel.append_whereclause(self.col(trun,'RunNumber')<=maxRun)

          sel.append_whereclause(self.col(tblk,'Name')!=sqlalchemy.null())
          sel.append_whereclause(self.col(tf,'LogicalFileName')!=sqlalchemy.null())
          result=""
          if not count and limit:
#             sel.use_labels=True
             if  self.dbManager.dbType[self.dbsInstance]=='oracle':
                 minRow,maxRow=fromRow,fromRow+limit
                 s = """ select * from ( select a.*, rownum as rnum from ( %s ) a ) where rnum between %s and %s"""%(self.printQuery(sel),minRow,maxRow)
                 print s
                 result=con.execute(s,{"trun_runnumber":minRun,"trun_runnumb_1":maxRun})
             else:
                 sel.limit=limit
                 sel.offset=fromRow
                 result = self.getSQLAlchemyResult(con,sel)
          else:       
              result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getRuns"
      if count:
         res = result.fetchone()[0]
         self.closeConnection(con)
         return long(res)
      oList=[]
      runs=""
      for item in result:
          if  item and item[0]:
              if self.dbManager.dbType[self.dbsInstance]=='oracle':
                 run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,fSize,nFiles,row=item
              else:
                 run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mDate,dsType,path,fSize,nFiles=item
#              run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mBy,mDate,dsType,path=item
              mBy=''
              cDate=timeGMT(cDate)
              mDate=timeGMT(mDate)
              cBy=parseCreatedBy(cBy)
              mBy=parseCreatedBy(mBy)
              if not fSize: fSize=0
              if not run: continue
              oList.append( (run,nEvts,nLumis,totLumi,store,sRun,eRun,cBy,cDate,mBy,mDate,dsType,path,fSize,nFiles) )
              runs+="%s,"%run
      oList.sort()
      oList.reverse()
      if self.verbose:
         self.writeLog("time in getRuns: %s"%(time.time()-t1))
      self.closeConnection(con)
      runs=runs[:-1] # get rid of last comma
      runDBInfoDict={}
      if userMode!="user": runDBInfoDict=self.getRunDBInfo(runs)
      return oList,runDBInfoDict

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

          oSel = [sqlalchemy.func.min(self.col(trun,'RunNumber')),sqlalchemy.func.max(self.col(trun,'RunNumber'))]
          sel  = sqlalchemy.select(oSel,
                       from_obj=[
                          tprd.outerjoin(tpdr,onclause=self.col(tpdr,'Dataset')==self.col(tprd,'ID'))
                          .outerjoin(trun,onclause=self.col(tpdr,'Run')==self.col(trun,'ID'))
                          .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                          .outerjoin(tpmt,onclause=self.col(tpm,'Type')==self.col(tpmt,'ID'))
                                ],distinct=True
                                 )
          if prim and prim.lower()!="any":
             sel.append_whereclause(self.col(tpm,'Name')==prim)
          if primType and primType.lower()!="any":
             sel.append_whereclause(self.col(tpmt,'Type')==primType)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
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
      blockInfoDict,totEvts,totFiles,totSize,siteList = self.listBlocks(kwargs)
      return siteList,blockInfoDict,totEvts,totFiles,sizeFormat(totSize)

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
      con = self.connectToDB()
      oList  = []
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
              oSel = [self.col(tad,'Name'),self.col(tad,'Annotation'),self.col(tadt,'Type'),
                      self.col(tads,'Status'),self.col(tadd,'Name'),
                      self.col(tadd,'LumiSections'),
                      self.col(tadd,'LumiSectionRanges'),
                      self.col(tadd,'Runs'),
                      self.col(tadd,'RunsRanges'),
                      self.col(tadd,'Algorithms'),
                      self.col(tadd,'LFNs'),
                      self.col(tadd,'AnalysisDatasets'),
                      self.col(tadd,'UserCut'),
                      self.col(tadd,'Description'),
                      self.col(tp1,'DistinguishedName'),self.col(tad,'CreationDate'),
                      self.col(tp2,'DistinguishedName'),self.col(tad,'LastModificationDate'),
                      self.col(tpg,'PhysicsGroupName'),
                      self.col(tblk,'Path') ]
          sel  = sqlalchemy.select(oSel,
                   from_obj=[
                     tad.outerjoin(tprd,onclause=self.col(tad,'ProcessedDS')==self.col(tprd,'ID'))
                     .outerjoin(tpds,onclause=self.col(tpds,'Dataset')==self.col(tprd,'ID'))
                     .outerjoin(tpm,onclause=self.col(tprd,'PrimaryDataset')==self.col(tpm,'ID'))
                     .outerjoin(tblk,onclause=self.col(tblk,'Dataset')==self.col(tprd,'ID'))
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
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in getAnalysisDS"
      if cDict.has_key('max') and cDict['max']==1:
         res = result.fetchone()[0]
         self.closeConnection(con)
         return res # we return how many datasets are found
      aList=[]
      for item in result:
          if not item[0]: continue
          name,ann,adtType,status,dName,dLumi,dLumiRange,dRuns,dRunRange,dAlg,dLFN,dADS,dCut,dDesc,cBy,cDate,mBy,mDate,group,path=item
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
          aList.append((name,ann,adtType,status,dName,dLumi,dLumiRange,dRuns,dRunRange,dAlg,dLFN,dADS,dCut,dDesc,cBy,cDate,mBy,mDate,group,path))
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
          if int(iLimit):
             sel.limit=int(iLimit)
             sel.offset=int(iOffset)
          sel.use_labels=True
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
          printExcept()
          raise "Fail in listBlocksFromSite"
      aList=[]
      aDict={}
      for item in result:
          blockName,blockSize,nFiles,nEvts,blockStatus,cBy,cDate,mBy,mDate,sename=item
          cDate=timeGMT(cDate)
          mDate=timeGMT(mDate)
          cBy=parseCreatedBy(cBy)
          mBy=parseCreatedBy(mBy)
          if not blockName: continue
          aDict={'Name':blockName,'BlockSize':blockSize,'NumberOfFiles':nFiles,'NumberOfEvents':nEvts,'OpenForWriting':blockStatus,'CreatedBy':cBy,'CreationDate':cDate,'LastModifiedBy':mBy,'LastModificationDate':mDate}
          aList.append(aDict)
      if self.verbose:
         self.writeLog("time listBlocksFromSite: %s"%(time.time()-t1))
      self.closeConnection(con)
      return aList

  def getBlocksFromSite(self,site,blockList=[]):
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
          for block in blockList:
             sel.append_whereclause(self.col(tblk,'Name')==block)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          if self.verbose:
             self.writeLog(getExcept())
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
          if self.verbose:
             self.writeLog(getExcept())
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

