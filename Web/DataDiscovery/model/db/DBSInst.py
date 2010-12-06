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
from utils.DDUtil       import *
from utils.DDAuth       import *
from utils.DDConfig     import *
from utils.DDExceptions import *

def checkSQLAlchemyVersion():
    ver=None
    rel=None
    max=None
    try:
       ver=sqlalchemy.__version__
       rel,max,min=ver.split(".")
       min = min.split("p")[0]
    except:
       print "##### DD ERROR: Cannot determine SQLAlchemy version"
       sys.__stdout__.flush()
       traceback.print_exc()
#    if not (int(rel)>=0 and int(max)>=4 and int(min)>=5):
#       msg="##### DD ERROR: Wrong SQLAlchemy version='%s', DD depends on 0.4.5 and higher"%ver
#       sys.__stdout__.flush()
#       raise msg
checkSQLAlchemyVersion()
################################################################################################
# DBS1, CGI server
DEFAULT_URL = "http://cmsdbs.cern.ch/cms/prod/comp/DBS/CGIServer/prodquerytest3"
#
# DLS instances are: https://twiki.cern.ch/twiki/bin/view/CMS/DLS#DLS_instances
# DBS2 instances are: https://twiki.cern.ch/twiki/bin/view/CMS/CMS-DMS-DBS-2-instances
#
config=DDConfig()
DBSGLOBAL=config.dbsprimary()
#DBSGLOBAL="cms_dbs_prod_global"
dbAuth = DDAuthentication() 
print "\n+++ Initialize DBS instances:"
for dbs in dbAuth.dbsInstances():
    print dbs
print "\n+++ Primary DBS instance:",DBSGLOBAL
print

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

class DBManager():
  """
     DBSDD class takes care of authentication with underlying DB using
     DBS_DBPARAM. SQLAlchemy to provides DB access layer, currently I added
     SQLite, MySQL, ORACLE support. The tables automatically retrieves from DB
     and internal schema can be reconstructed on the fly.

     For description of SQLAlchemy pleasee see
     http://www.sqlalchemy.org
                                
  """
  def __init__(self,dbsmgr,verbose=0):
      """
         DBSDD constructor. 
         @type verbose: boolean or integer
         @param verbose: verbosity level
         @rtype : none
         @return: none
      """
      config = DDConfig()
      self.dbsmgr    = dbsmgr
      self.verbose   = int(verbose)
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
      self.vQuery    = {}
      self.dbTables  = {}
      self.dbType    = {}
      self.lowTables = {}
      self.metaDict  = {}
        
  def connect(self,dbsInst,iface="dd"):
      t_ini=time.time()
      eType='oracle' # default DB back-end
      dbOwner = ''
      if  not self.engine.has_key(dbsInst):
          if  self.dbsmgr:
              dbType  = self.dbsmgr.getdbtype(dbsInst)
              dbName  = self.dbsmgr.getaccount(dbsInst)
              dbUser  = self.dbsmgr.getlogin(dbsInst)
              dbPass  = self.dbsmgr.getpass(dbsInst)
              host    = ''
              url     = self.dbsmgr.geturl(dbsInst)
              dbOwner = self.dbsmgr.getowner(dbsInst)
          else:
              dbAuth  = DDAuthentication(dbsInst,self.verbose) 
              dbType, dbName, dbUser, dbPass, host, url = dbAuth.dbInfo()
              dbOwner = dbsInst.upper()

          eType  = string.lower(dbType)
          print "DBManager:connect to %s@%s:%s/%s"%(dbType,dbsInst,host,dbName)

          # Initialize SQLAlchemy engines
          eName=""
          vQuery=""
          if eType=='sqlite':
             eName = "%s:///%s"%(eType,dbName)
             tQuery= "SELECT name FROM SQLITE_MASTER WHERE type='table';"
             engine= sqlalchemy.create_engine(eName)
          elif eType=='oracle':
             eName = "%s://%s:%s@%s"%(eType,dbUser,dbPass,dbName)
#             tQuery= "select table_name from user_tables"
             tQuery= "select tname from tab"
             tQuery="""SELECT table_name FROM all_tables WHERE owner='%s'"""%dbOwner
             vQuery="""SELECT view_name FROM all_views WHERE owner='%s'"""%dbOwner
             engine= sqlalchemy.create_engine(eName,strategy='threadlocal',threaded=True)
          elif eType=='mysql':
             eName = "%s://%s:%s@%s/%s"%(eType,dbUser,dbPass,host,dbName)
             tQuery= "show tables"
             engine= sqlalchemy.create_engine(eName,strategy='threadlocal')
          else:
             printExcept("Unsupported DB engine backend for '%s'"%dbsInst)
          self.dbType[dbsInst] = eType
          self.engine[dbsInst] = engine
          self.tQuery[dbsInst] = tQuery
          self.vQuery[dbsInst] = vQuery

      try:
          con = self.engine[dbsInst].connect()
      except:
          raise "Fail to connect to '%s'" % dbsInst

      if  not self.dbTables.has_key(dbsInst):
          dbsMeta = sqlalchemy.MetaData()
          dbsMeta.bind=self.engine[dbsInst]
          self.metaDict[dbsInst]=dbsMeta
          tables={}
          tList = con.execute(self.tQuery[dbsInst])
# since SQLAlchemy 0.4
#          tList = self.engine[dbsInst].table_names()
          for t in tList: 
              tName = t[0].lower()
              if self.verbose>1:
                 print "DBS Tables|Views",tName
              if eType=='oracle':
                 tables[tName]=sqlalchemy.Table(tName,dbsMeta,autoload=True,schema=dbOwner.lower(),oracle_resolve_synonyms=True,useexisting=True)
              else:
                 tables[tName]=sqlalchemy.Table(t[0],dbsMeta,autoload=True)
              if self.verbose>1:
                 print tables[tName].__dict__
          if  eType=='oracle': # read views separately
              vList = con.execute(self.vQuery[dbsInst])
              for v in vList: 
                  tName=v[0].lower()
                  if self.verbose>1:
                     print "DBS Views",tName
                  if eType=='oracle':
                     tables[tName]=sqlalchemy.Table(tName,dbsMeta,autoload=True,schema=dbOwner.lower(),oracle_resolve_synonyms=True,useexisting=True)
                  else:
                     tables[tName]=sqlalchemy.Table(v[0],dbsMeta,autoload=True)
                  if self.verbose>1:
                     print tables[tName].__dict__
          self.dbTables[dbsInst]=tables
          t_end=time.time()
          print "%s initialization time: '%s' seconds"%(dbsInst, t_end-t_ini)
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
      tableName=string.lower(tableName)
      if not tables.has_key(tableName):
         if tables.has_key(tableName.lower()):
            tableName=tableName.lower()
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

  def compileQuery(self,dbsInst,sel):
      if  type(sel) is types.StringType:
          return sel
      if  self.dbType[dbsInst]=='oracle':
          return sel.compile(dialect=oracle.dialect()) 
      if  self.dbType[dbsInst]=='mysql':
          return sel.compile(dialect=mysql.dialect()) 
      if  self.dbType[dbsInst]=='sqlite':
          return sel.compile(dialect=sqlite.dialect()) 

  def getForeignKeys(self,dbsInst,table):
      tDict = self.dbTables[dbsInst]
      table=string.lower(table)
      if tDict.has_key(table):
         return tDict[table].foreign_keys
      raise "No table '%s' found"%table

  def getColumns(self,dbsInst,table):
      tDict = self.dbTables[dbsInst]
      table=string.lower(table)
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
