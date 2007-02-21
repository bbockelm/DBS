#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Author:  Valentin Kuznetsov, Cornell University, 2006
#

"""
DBS Data discovery setting module. It defines DBS/DLS instances, binding between them.
It also contain DBSDD class which provides access to underlying DB through
SQLAlchemy module.
"""

# system modules
import sys, string, types

#import sqlalchemy.mods.threadlocal
try:
   import sqlalchemy
except:
   print "Fail to load sqlalchemy module, some functionality will be disabled"
   pass

# DBS modules
from   DBSUtil import *
from   DBSAuth import *
from   DDExceptions import *

################################################################################################
DEFAULT_URL = "http://cmsdoc.cern.ch/cms/test/aprom/DBS/CGIServer/prodquery"
DEFAULT_URL = "http://cmsdoc.cern.ch/cms/test/aprom/DBS/CGIServer/prodquerytest2"
DEFAULT_URL = "http://cmsdbs.cern.ch/cms/prod/comp/DBS/CGIServer/prodquerytest3"

# OTHER URLs
#DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/prodquery"
#DEFAULT_URL = "http://cmssrv18.fnal.gov:8000/cms/aprom/DBS/CGIServer/prodquery"
#DEFAULT_URL = "exec:/home/vk/Cornell/CMS/DataDiscovery/work/prodquery"
#DEFAULT_URL = "http://cmssrv18.fnal.gov:8000/cgi-bin/prodquery"

# DLS instances are:
#  https://twiki.cern.ch/twiki/bin/view/CMS/DLS#DLS_instances
#
# The DBS/DLS pairs for the production teams are:
#
# 1) 
# DBS: MCLocal_1/Writer  
# DLS: lxgate10.cern.ch:18081  type: DLS_TYPE_MYSQL
# 
# 2)
# DBS: MCLocal_2/Writer
# DLS: prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_2 type: DLS_TYPE_DLI or DLS_TYPE_LFC
# 
# 3)
# DBS: MCLocal_3/Writer
# DLS: prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_3 type: DLS_TYPE_DLI or DLS_TYPE_LFC
#
# 4)
# DBS: MCLocal_4/Writer
# DLS: prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_4 type: DLS_TYPE_DLI or DLS_TYPE_LFC
DBSGLOBAL="localhost"
DBS_DLS_INST= {
   "localhost" :("http://localhost:8080/DBS/servlet/DBSServlet","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/LFC"),
   "FNAL8282":("http://cmssrv17.fnal.gov:8282/DBS/servlet/DBSServlet","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_1"), 
   "cmslcgco01":("http://cmslcgco01.cern.ch:8900/DBS/servlet/DBSServlet","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_1"), 
   "FNAL8989":("http://cmssrv18.fnal.gov:8989/DBS/servlet/DBSServlet","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_1"), 
#   "MCLocal_1/Writer":("","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_1"), 
#   "MCLocal_2/Writer":("","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_2"), 
#   "MCLocal_3/Writer":("","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_3"),
#   "MCLocal_4/Writer":("","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_4"),
#   "Dev/Writer":("","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/LFC"),
#   "DevMC/Writer":("","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_Test"),
#   "RelVal/Writer":("","DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/RelVal"),
#   "Dev/fanfani":("DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_Test"),
#   "MCLocal_5/Writer":("DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_5"),
#   "MCLocal_6/Writer":("DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_6"),
#   "MCLocal_7/Writer":("DLS_TYPE_DLI","prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_7")
}
################################################################################################
def getDictOfSites():
    """
       Read DLS_INFO file (default name is dls.all) and construct dictionary of
       {DBS instance: list of DLS sites}
    """
    fName = DLS_INFO
    f=open(fName,'r')
    sDict={}
    for item in f.readlines():
        try:
           dbs,site=string.split(item)
        except:
           pass
           continue
        addToDict(sDict,dbs,site)
    f.close()
    dbsList = sDict.keys()
    dbsList.sort()
    try:
       dbsList.remove(DBSGLOBAL)
    except:
       pass
    dbsList=[DBSGLOBAL]+dbsList
    s="{l:["
    for dbsInst in dbsList:
       s+='"%s",'%dbsInst
    s=s[:-1]+"],\n"
    s+="n:{"
    for dbsInst in dbsList:
        s+='"%s":{l: ['%dbsInst
        for item in sDict[dbsInst]:
            s+='"%s",'%item
        s=s[:-1]+"],n:null},\n"
    s=s[:-2]+"}}"
    return s

class DBSDB(DBSLogger):
  """
     DBSDD class takes care of authentication with underlying DB using
     DBS_DBPARAM. SQLAlchemy to provides DB access layer, currently I added
     SQLite, MySQL, ORACLE support. The tables automatically retrieves from DB
     and internal schema can be reconstructed on the fly.

     For description of SQLAlchemy pleasee see
     http://www.sqlalchemy.org
                                
  """
  def __init__(self,iface='cgi',verbose=0):
      """
         DBSDD constructor. 
         @type verbose: boolean or integer
         @param verbose: verbosity level
         @rtype : none
         @return: none
      """
      DBSLogger.__init__(self,"DBSDD",verbose)
      self.engine = {}
      self.tQuery = {}
      self.iface=iface
      if verbose:
         self.verbose=True
      else:
         self.verbose=False
      self.dbTables = {}
      self.dbType = {}
#      self.connect()
      
  def connect(self,dbsInst):
      t_ini=time.time()
      if  not self.engine.has_key(dbsInst):
          dbAuth = DBSAuthentication(dbsInst,self.verbose) 
          dbType, dbName, dbUser, dbPass, host = dbAuth.dbInfo()
          eType  = string.lower(dbType)
          if self.verbose:
             print "DBSDB:conntect",dbType,dbName,host

          # Initialize SQLAlchemy engines
          eName=""
          if eType=='sqlite':
             self.writeLog("Use SQLite instance '%s'"%dbsInst)
             eName = "%s:///%s"%(eType,dbName)
             tQuery= "SELECT name FROM SQLITE_MASTER WHERE type='table';"
          elif eType=='oracle':
             self.writeLog("Use ORACLE instance '%s'"%dbsInst)
             eName = "%s://%s:%s@%s"%(eType,dbUser,dbPass,dbName)
             tQuery= "select table_name from user_tables"
          elif eType=='mysql':
             self.writeLog("Use MySQL instance '%s'"%dbsInst)
             eName = "%s://%s:%s@%s/%s"%(eType,dbUser,dbPass,host,dbName)
             tQuery= "show tables"
          else:
             printExcept("Unsupported DB engine backend for '%s'"%dbsInst)
          self.dbType[dbsInst]=eType

#          print "eName,dbsInst",eName,dbsInst
          self.engine[dbsInst] = sqlalchemy.create_engine(eName, 
                                                          strategy='threadlocal',
                                                          threaded=True,
                                                          echo=self.verbose)
          self.tQuery[dbsInst] = tQuery

      dbsMeta = sqlalchemy.DynamicMetaData(case_sensitive=False)
      dbsMeta.connect(self.engine[dbsInst])

      con = self.engine[dbsInst].connect()
      if  not self.dbTables.has_key(dbsInst):
          tables={}
          tList = con.execute(self.tQuery[dbsInst])
          for t in tList: 
              tables[t[0]]=sqlalchemy.Table(t[0], dbsMeta, autoload=True, case_sensitive=False)
          self.dbTables[dbsInst]=tables
      t_end=time.time()
      self.writeLog("Initialization time: '%s' seconds"%(t_end-t_ini))
      return con

  def getTable(self,dbsInst,tableName,tableAlias=""):
      """
         Returns table and/or alias to the table. 
         @type  tableName: string
         @param tableName: table name
         @type tableAlias: string
         @param tableAlias: alias to the table (optional)
         @rtype : string
         @return: SQLAlchemy table object
      """
      tables = self.dbTables[dbsInst]
      if self.dbType[dbsInst]=='oracle':
         tableName=string.upper(tableName)
      if tableAlias:
         # return alias to the table
         return tables[tableName].alias(tableAlias)
      else:
         # return table itself
         return tables[tableName]

  def getTableNames(self,dbsInst):
      """
         Should retrieve list of existing table names from underlying DB.
         @type  self: class object
         @param self: none
         @rtype : string
         @return: not implemented yet, should return list
      """
      return self.dbTables[dbsInst].keys()

  def getForeignKeys(self,dbsInst,table):
      tDict = self.dbTables[dbsInst]
      if self.dbType[dbsInst]=='oracle':
         table=string.upper(table)
      if tDict.has_key(table):
         return tDict[table].foreign_keys
      raise "No table '%s' found",table

  def getColumns(self,dbsInst,table):
      tDict = self.dbTables[dbsInst]
      if self.dbType[dbsInst]=='oracle':
         table=string.upper(table)
      if tDict.has_key(table):
         return tDict[table]._columns.keys()
      raise "No table '%s' found",table

  def col(self,dbsInst,tableAlias,colName):
      if self.dbType[dbsInst]=='oracle':
         return getattr(tableAlias.c,string.lower(colName))
      return getattr(tableAlias.c,colName)

  def getTableObject(self,dbsInst,table):
      for t in self.dbTables[dbsInst].keys():
          if string.lower(table)==string.lower(t):
             return self.dbTables[dbsInst][t] # all table names stored as lower case
      raise "No table '%s' found",table

# Table classes
class T_PROCESSED_DATASET(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iPrimName=None,iTier=None,iName=None,iInput=None):
       """
          @type  self: class object
          @param self: none
          @rtype : none
          @return: none
       """
       self.primary_dataset = iPrimName
       self.data_tier = iTier
       self.name = iName
       self.intput = iInput

class T_PRIMARY_DATASET(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iName=None):
       """
          @type  iName: string
          @param iName: name
          @rtype : none
          @return: none
       """
       self.name = iName

class T_PROCESSING_NAME(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iName=None):
       """
          @type  iName: string
          @param iName: name
          @rtype : none
          @return: none
       """
       self.name = iName

class T_PROCESSING(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iPrimaryDataset=None,iAppConfig=None,iName=None,isOpen=None,iInput=None):
       """
          @type  iPrimaryDataset: string 
          @param iPrimaryDataset:
          @type  iAppConfig: string 
          @param iAppConfig:
          @type  iName: string 
          @param iName:
          @type  isOpen: char 
          @param isOpen:
          @type  iInput: 
          @param iInput:
          @rtype : none
          @return: none
       """
       self.primary_dataset=iPrimaryDataset
       self.app_config = iAppConfig
       self.name = iName
       self.is_open = isOpen
       self.input = iInput

class T_PROCESSING_PATH(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iName=None):
       """
          @type  self: class object
          @param self: none
          @rtype : none
          @return: none
       """
#       pass

class T_DATA_TIER(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iName=None):
       """
          @type  iName: string
          @param iName: name
          @rtype : none
          @return: none
       """
       self.name = iName

class T_APP_FAMILY(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iName=None):
       """
          @type  iName: string
          @param iName: name
          @rtype : none
          @return: none
       """
       self.name = iName

class T_APPLICATION(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iExe=None,iVer=None):
       """
          @type  iExe: string 
          @param iExe: executable name
          @type  iVer: string
          @param iVer: version name
          @rtype : none
          @return: none
       """
       self.executable = iExe
       self.app_version= iVer

class T_APP_CONFIG(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self):
       """
          @type  self: class object
          @param self: none
          @rtype : none
          @return: none
      """
#      pass

class T_PARAMETER_SET(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iHash=None,iContent=None):
       """
          @type  iHash: string
          @param iHash: hash
          @type  iContent: string
          @param iContent: 
          @rtype : none
          @return: none
       """
       self.hash=iHash
       self.content=iContent

class T_FILE(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,guid=None,lName=None,chkSum=None,fSize=None,iStatus=None,iType=None,iBlock=None):
       """
          @type  guid: string
          @param guid:
          @type  lName: string
          @param lName:
          @type  chkSum: string
          @param chkSum:
          @type  fSize: string
          @param fSize:
          @type  iStatus: string
          @param iStatus:
          @type  iType: string
          @param iType:
          @type  iBlock: string
          @param iBlock:
          @rtype : none
          @return: none
       """
       self.guid         = guid
       self.logical_name = lName
       self.checksum     = chkSum
       self.filesize     = fSize
       self.status       = iStatus
       self.type         = iType
       self.inblock      = iBlock

class T_FILE_TYPE(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self):
       """
          @type  self: class object
          @param self: none
          @rtype : none
          @return: none
      """
#      pass

class T_BLOCK(object): 
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iProcessing=None,iStatus=None,iFiles=0,iBytes=0):
       """
          @type  iProcessing: string 
          @param iProcessing: 
          @type  iStatus: string 
          @param iStatus: 
          @type  iFiles: int 
          @param iFiles: 
          @type  iBytes: long 
          @param iBytes: 
          @rtype : none
          @return: none
       """
       self.processing=iProcessing
       self.status=iStatus
       self.files=iFiles
       self.bytes=iBytes

class T_EVCOLL_STATUS(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iName=None):
       """
          @type  iName: string
          @param iName: 
          @rtype : none
          @return: none
       """
       self.name=iName

class T_EVCOLL_FILE(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iEvtColl=None,iFileId=None):
       """
          @type  iEvtColl: string
          @param iEvtColl: none
          @type  iFileId: int
          @param iFileId: 
          @rtype : none
          @return: none
       """
       self.evcoll=iEvtColl
       self.fileid=iFileId

class T_EVCOLL_PARENTAGE(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self):
       """
          @type  self: class object
          @param self: none
          @rtype : none
          @return: none
      """
#      pass

class T_PARENTAGE_TYPE(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iName=None):
       """
          @type  iName: string
          @param iName:
          @rtype : none
          @return: none
       """
       self.name=iName

class T_EVENT_COLLECTION(object):
   """
      SQLAlchemy mapper. Provides a mapper class to the table. Mapper name is matched to the
      table one.
   """
   def __init__(self,iProc=None,iName=None,iEvt=None,iStatus=None):
       """
          @type  iProc: string 
          @param iProc: 
          @type  iName: string 
          @param iName: 
          @type  iEvt: int 
          @param iEvt: 
          @type  iStatus: int 
          @param iStatus: 
          @rtype : none
          @return: none
       """
       self.processed_dataset=iProc
       self.name=iName
       self.events=iEvt
       self.status=iStatus

#
############ TABLES ###############
#
#t_processing         = sqlalchemy.Table('t_processing', dbsMeta, autoload=True)
#t_processing_name    = sqlalchemy.Table('t_processing_name', dbsMeta, autoload=True)
#t_app_config         = sqlalchemy.Table('t_app_config', dbsMeta, autoload=True)
#t_application        = sqlalchemy.Table('t_application', dbsMeta, autoload=True)
#t_app_family         = sqlalchemy.Table('t_app_family', dbsMeta, autoload=True)
#t_parameter_set      = sqlalchemy.Table('t_parameter_set', dbsMeta, autoload=True)
#t_primary_dataset    = sqlalchemy.Table('t_primary_dataset',dbsMeta, autoload=True)
#t_block              = sqlalchemy.Table('t_block', dbsMeta, autoload=True)
#t_block_status       = sqlalchemy.Table('t_block_status', dbsMeta, autoload=True)
#t_evcoll_file        = sqlalchemy.Table('t_evcoll_file', dbsMeta, autoload=True)
#t_evcoll_parentage   = sqlalchemy.Table('t_evcoll_parentage', dbsMeta, autoload=True)
#t_file               = sqlalchemy.Table('t_file', dbsMeta, autoload=True)
#t_event_collection   = sqlalchemy.Table('t_event_collection', dbsMeta, autoload=True)
#t_evcoll_status      = sqlalchemy.Table('t_evcoll_status', dbsMeta, autoload=True)
#t_parentage_type     = sqlalchemy.Table('t_parentage_type', dbsMeta, autoload=True)
#t_processed_dataset  = sqlalchemy.Table('t_processed_dataset', dbsMeta, autoload=True)
#t_data_tier          = sqlalchemy.Table('t_data_tier', dbsMeta, autoload=True)
#
############ MAPPERS ###############
#
#map_tpr   = sqlalchemy.mapper(T_PROCESSING,t_processing)
#map_tpn   = sqlalchemy.mapper(T_PROCESSING_NAME,t_processing_name,properties={'t_processing':sqlalchemy.relation(T_PROCESSING)})
#map_tac   = sqlalchemy.mapper(T_APP_CONFIG,t_app_config,properties={'t_processing':sqlalchemy.relation(T_PROCESSING)})
#map_tap   = sqlalchemy.mapper(T_APPLICATION,t_application,properties={'t_app_config':sqlalchemy.relation(T_APP_CONFIG)})
#map_taf   = sqlalchemy.mapper(T_APP_FAMILY,t_app_family,properties={'t_application':sqlalchemy.relation(T_APPLICATION)})
#map_tps   = sqlalchemy.mapper(T_PARAMETER_SET,t_parameter_set,properties={'t_app_config':sqlalchemy.relation(T_APP_CONFIG)})
#map_tpd   = sqlalchemy.mapper(T_PRIMARY_DATASET,t_primary_dataset)
#map_tb    = sqlalchemy.mapper(T_BLOCK,t_block)
#map_tevcf = sqlalchemy.mapper(T_EVCOLL_FILE,t_evcoll_file)
#map_tevcp = sqlalchemy.mapper(T_EVCOLL_PARENTAGE,t_evcoll_parentage)
#map_tf    = sqlalchemy.mapper(T_FILE,t_file,properties={'t_evcoll_file':sqlalchemy.relation(T_EVCOLL_FILE)})
#map_tevc  = sqlalchemy.mapper(T_EVENT_COLLECTION,t_event_collection,properties={'t_evcoll_parentage':sqlalchemy.relation(T_EVCOLL_PARENTAGE),'t_evcoll_file':sqlalchemy.relation(T_EVCOLL_FILE)})

#map_tevcs = sqlalchemy.mapper(T_EVCOLL_STATUS,t_evcoll_status,properties={'t_event_collection':sqlalchemy.relation(T_EVENT_COLLECTION)})
#map_tpt   = sqlalchemy.mapper(T_PARENTAGE_TYPE,t_parentage_type,properties={'t_evcoll_parentage':sqlalchemy.relation(T_EVCOLL_PARENTAGE)})
#map_tprd  = sqlalchemy.mapper(T_PROCESSED_DATASET,t_processed_dataset,properties={'t_event_collection':sqlalchemy.relation(T_EVENT_COLLECTION)})
#map_tdt   = sqlalchemy.mapper(T_DATA_TIER,t_data_tier,properties={'t_processed_dataset':sqlalchemy.relation(T_PROCESSED_DATASET)})
#
# main
#
if __name__ == "__main__":
   dbsDB = DBSDB()
   for t in dbsDB.tables.keys():
       table = dbsDB.getTable(t)
       print t, type(t), table
       if t=='t_data_tier':
          for dbs in DBS_DLS_INST.keys():
              con = dbsDB.engine[dbs].connect()
              res = con.execute("select * from %s"%t)
              for i in res:
                  print dbs,i
       
#    tableList = []
#    for table in dbsTables:
#        _table = sqlalchemy.Table(table,dbsMeta,autoload=True)
#        colList = [c.name for c in _table.columns]
#        tableList.append((_table,colList))
#        print "### Table:",table
#        print colList
