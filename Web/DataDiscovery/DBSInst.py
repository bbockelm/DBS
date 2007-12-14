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

import sqlalchemy
from sqlalchemy.databases import oracle
from sqlalchemy.databases import mysql
from sqlalchemy.databases import sqlite

# QueryBuilder
#from QueryBuilder.Table import Column
#from QueryBuilder.Table import Table
from QueryBuilder.WriteSqlAlchemyGraph import WriteSqlAlchemyGraph
from QueryBuilder.DotGraph import DotGraph

# DBS modules
from DDUtil       import *
from DDAuth       import *
from DDConfig     import *
from DDExceptions import *

################################################################################################
# DBS1, CGI server
DEFAULT_URL = "http://cmsdbs.cern.ch/cms/prod/comp/DBS/CGIServer/prodquerytest3"
#
# DLS instances are: https://twiki.cern.ch/twiki/bin/view/CMS/DLS#DLS_instances
# DBS2 instances are: https://twiki.cern.ch/twiki/bin/view/CMS/CMS-DMS-DBS-2-instances
#
DBSGLOBAL="cms_dbs_prod_global"
DBS_DLS_INST={}
dbAuth = DDAuthentication() 
print "\n+++ Initialize DBS instances:"
for dbs in dbAuth.dbsInstances():
    print dbs
    DBS_DLS_INST[dbs]=""
if not DBS_DLS_INST.has_key(DBSGLOBAL):
   msg="""
Initialization of DBS instances failed, please check your DBS_DBPARAM syntax
Section                 cms_dbs_prod_global
Interface               Oracle
Database                cms_dbs
AuthDBUsername          XXXXXX
AuthDBPassword          ZZZZZZ
Url                     servlet URL
"""
   raise msg

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

class DBManager(DDLogger):
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
      config = DDConfig()
      DDLogger.__init__(self,config.loggerDir(),"DBManager",verbose)
      self.iface     = iface
      self.verbose   = verbose
      self.clear()
      
  def setVerbose(self,level):
      self.verbose=level

  def writeGraph(self,dbsInst):
      print "writeGraph"
      fileName="%s.dot"%dbsInst
      dot=DotGraph(file(fileName,"w"))
      tDict = self.dbTables[dbsInst]
      for key in tDict.keys():
          table=tDict[key]
          tableName=key
#          if tableName.lower()=='person': continue
          foreignKeys=table.foreign_keys
          for fk in foreignKeys:
              right=fk.column.table.name
              if right!='person': # exclude person table
                 dot.AddEdge(tableName,right)
      dot.Finish()

  def clear(self):
      self.engine    = {}
      self.tQuery    = {}
      self.dbTables  = {}
      self.dbType    = {}
      self.lowTables = {}
      self.metaDict  = {}
        
  def connect(self,dbsInst):
      t_ini=time.time()
      eType='oracle' # default DB back-end
      if  not self.engine.has_key(dbsInst):
          dbAuth = DDAuthentication(dbsInst,self.verbose) 
          dbType, dbName, dbUser, dbPass, host, url = dbAuth.dbInfo()
          DBS_DLS_INST[dbsInst]=url

          eType  = string.lower(dbType)
          print "DBManager:connect to %s@%s:%s/%s"%(dbType,dbsInst,host,dbName)

          # Initialize SQLAlchemy engines
          eName=""
          if eType=='sqlite':
             self.writeLog("Use SQLite instance '%s'"%dbsInst)
             eName = "%s:///%s"%(eType,dbName)
             tQuery= "SELECT name FROM SQLITE_MASTER WHERE type='table';"
             engine= sqlalchemy.create_engine(eName)
          elif eType=='oracle':
             self.writeLog("Use ORACLE instance '%s'"%dbsInst)
             eName = "%s://%s:%s@%s"%(eType,dbUser,dbPass,dbName)
#             tQuery= "select table_name from user_tables"
             tQuery= "select tname from tab"
             tQuery="""SELECT table_name FROM all_tables WHERE owner='%s'"""%dbsInst.upper()
             engine= sqlalchemy.create_engine(eName,strategy='threadlocal',threaded=True)
          elif eType=='mysql':
             self.writeLog("Use MySQL instance '%s'"%dbsInst)
             eName = "%s://%s:%s@%s/%s"%(eType,dbUser,dbPass,host,dbName)
             tQuery= "show tables"
             engine= sqlalchemy.create_engine(eName,strategy='threadlocal')
          else:
             printExcept("Unsupported DB engine backend for '%s'"%dbsInst)
          self.dbType[dbsInst] = eType
          self.engine[dbsInst] = engine
          self.tQuery[dbsInst] = tQuery

      con = self.engine[dbsInst].connect()

      if  not self.dbTables.has_key(dbsInst):
          dbsMeta = sqlalchemy.DynamicMetaData(case_sensitive=True)
          dbsMeta.connect(self.engine[dbsInst])
          self.metaDict[dbsInst]=dbsMeta
          tables={}
          tList = con.execute(self.tQuery[dbsInst])
          for t in tList: 
              if eType=='oracle':
                 tables[t[0]]=sqlalchemy.Table(t[0].lower(), dbsMeta, autoload=True,case_sensitive=False)
              else:
                 tables[t[0]]=sqlalchemy.Table(t[0], dbsMeta, autoload=True,case_sensitive=True)
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

  def printQuery(self,dbsInst,sel):
      if  type(sel) is types.StringType:
          return sel
      if  self.dbType[dbsInst]=='oracle':
          return str(sel.compile(dialect=oracle.dialect())) 
      if  self.dbType[dbsInst]=='mysql':
          return str(sel.compile(dialect=mysql.dialect())) 
      if  self.dbType[dbsInst]=='sqlite':
          return str(sel.compile(dialect=sqlite.dialect())) 

  def getForeignKeys(self,dbsInst,table):
      tDict = self.dbTables[dbsInst]
      if self.dbType[dbsInst]=='oracle':
         table=string.upper(table)
      if tDict.has_key(table):
         return tDict[table].foreign_keys
      raise "No table '%s' found"%table

  def getColumns(self,dbsInst,table):
      tDict = self.dbTables[dbsInst]
      if self.dbType[dbsInst]=='oracle':
         table=string.upper(table)
      if tDict.has_key(table):
         return tDict[table]._columns.keys()
      raise "No table '%s' found"%table

  def col(self,dbsInst,tableAlias,colName):
      if self.dbType[dbsInst]=='oracle':
         return getattr(tableAlias.c,string.lower(colName))
      return getattr(tableAlias.c,colName)

  def getTableObject(self,dbsInst,table):
      for t in self.dbTables[dbsInst].keys():
          if string.lower(table)==string.lower(t):
             return self.dbTables[dbsInst][t] # all table names stored as lower case
      raise "No table '%s' found"%table

  def getDBTableName(self,dbsInst,table):
      # TODO: need to cache tList for given dbsIntance
      tList = self.dbTables[dbsInst].keys()
      tList.sort()
      lowTableList=[]
      if self.lowTables.has_key(dbsInst):
         lowTableList=self.lowTables[dbsInst]
      if  not lowTableList:
          for t in tList:
              lowTableList.append(string.lower(t))
          self.lowTables[dbsInst]=lowTableList
      try:    
         res=tList[self.lowTables[dbsInst].index(table.lower())]
         return res
      except:
         raise "Fail to lookup table '%s' in '%s' instance"%(table.lower(),dbsInst)
      
  # convert SQLAlchemy schema to Drew's one.
#  def constructSchema(self,dbsInst):
#      schema = {}
#      tList = self.dbTables[dbsInst].keys()
#      tList.sort()
#      for idx in xrange(0,len(tList)):
#          table = tList[idx]
#          tObj= self.dbTables[dbsInst][table]
#          d = {}
#          for col  in tObj.columns:
#              if col.primary_key:
#                 d[col.name]=Column(PrimaryKey=True)
#                 continue
#              if col.foreign_key: 
#                 fk=repr(col.foreign_key).split("'")[1].split('.')[0]
#                 d[col.name]=Column(ForeignKey=self.getDBTableName(dbsInst,fk))
#              else:
#                 d[col.name]=Column()
#          schema[tObj.fullname] = Table(d)
#      return schema

#
# main
#
if __name__ == "__main__":
   dbsDB = DBManager()
   for t in dbsDB.tables.keys():
       table = dbsDB.getTable(t)
       print t, type(t), table
