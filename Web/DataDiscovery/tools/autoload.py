#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006
"""
Common utilities
"""

import os, sys, string, time
#from sqlalchemy import *
import sqlalchemy
from sqlalchemy.databases import information_schema
from sqlalchemy.databases import oracle
from sqlalchemy.databases import mysql
from sqlalchemy.databases import sqlite

# QueryBuilder
#from QueryBuilder.WriteSqlAlchemyGraph import WriteSqlAlchemyGraph
#from QueryBuilder.DotGraph import DotGraph

def setSQLAlchemyLogger(hdlr,logLevel):
    # set up logging for SQLAlchemy
    logging.getLogger('sqlalchemy.engine').setLevel(logLevel)
    logging.getLogger('sqlalchemy.orm.unitofwork').setLevel(logLevel)
    logging.getLogger('sqlalchemy.pool').setLevel(logLevel)

    logging.getLogger('sqlalchemy.engine').addHandler(hdlr)
    logging.getLogger('sqlalchemy.orm.unitofwork').addHandler(hdlr)
    logging.getLogger('sqlalchemy.pool').addHandler(hdlr)

class DBManager(object):
  """
     DBSDD class takes care of authentication with underlying DB using
     DBS_DBPARAM. SQLAlchemy to provides DB access layer, currently I added
     SQLite, MySQL, ORACLE support. The tables automatically retrieves from DB
     and internal schema can be reconstructed on the fly.

     For description of SQLAlchemy pleasee see
     http://www.sqlalchemy.org
                                
  """
  def __init__(self,verbose=0):
      """
         DBSDD constructor. 
         @type verbose: boolean or integer
         @param verbose: verbosity level
         @rtype : none
         @return: none
      """
#      DDLogger.__init__(self,config.loggerDir(),"DBManager",verbose)
      self.verbose   = verbose
      self.clear()
      
  def setVerbose(self,level):
      self.verbose=level

  def writeGraph(self,dbAlias):
      print "writeGraph"
      fileName="%s.dot"%dbAlias
      dot=DotGraph(file(fileName,"w"))
      tDict = self.dbTables[dbAlias]
      for key in tDict.keys():
          table=tDict[key]
          tableName=key
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
        
  def connect(self,dbType,dbName,dbUser,dbPass,host):
      dbAlias="%s__%s__%s"%(host,dbType,dbName)
      t_ini=time.time()
      eType='oracle' # default DB back-end
      eType  = string.lower(dbType)
      print "Connect to %s/%s (%s back-end)"%(host,dbName,dbType)

      # Initialize SQLAlchemy engines
      eName=""
      if eType=='sqlite':
#         self.writeLog("Use SQLite instance '%s'"%dbAlias)
         eName = "%s:///%s"%(eType,dbName)
         tQuery= "SELECT name FROM SQLITE_MASTER WHERE type='table';"
         engine= sqlalchemy.create_engine(eName)
      elif eType=='oracle':
#         self.writeLog("Use ORACLE instance '%s'"%dbAlias)
         eName = "%s://%s:%s@%s"%(eType,dbUser,dbPass,dbName)
#             tQuery= "select table_name from user_tables"
         tQuery= "select tname from tab"
         db_owner="CMS_DBS_INT_GLOBAL"
         tQuery="""SELECT table_name FROM all_tables WHERE owner='%s'"""%db_owner
         vQuery="""SELECT view_name FROM all_views WHERE owner='%s'"""%db_owner
#         tQuery="""SELECT table_name FROM all_tables WHERE owner='%s'"""%dbAlias.upper()
         engine= sqlalchemy.create_engine(eName,strategy='threadlocal',threaded=True)
      elif eType=='mysql':
#         self.writeLog("Use MySQL instance '%s'"%dbAlias)
         eName = "%s://%s:%s@%s/%s"%(eType,dbUser,dbPass,host,dbName)
         tQuery= "show tables"
         engine= sqlalchemy.create_engine(eName,strategy='threadlocal')
      else:
         printExcept("Unsupported DB engine back-end")
      self.dbType[dbAlias] = eType
      self.engine[dbAlias] = engine
      self.tQuery[dbAlias] = tQuery
      self.vQuery[dbAlias] = vQuery

      # test new connection routine
#      self.createObjs(eName)
      print eType,tQuery
      con = self.engine[dbAlias].connect()

      if  not self.dbTables.has_key(dbAlias):
          dbsMeta = sqlalchemy.MetaData()
          dbsMeta.bind=self.engine[dbAlias]
          self.metaDict[dbAlias]=dbsMeta
          tables={}
          tList = con.execute(self.tQuery[dbAlias])
          for t in tList: 
              tname  = t[0].lower()
              print "Found table",tname
              tbl  = sqlalchemy.Table(tname,dbsMeta,autoload=True,schema=db_owner.lower(),oracle_resolve_synonyms=True,useexisting=True)
#              tbl  = sqlalchemy.Table(tname, dbsMeta, autoload=True)
              if tname.lower()=='files':
                 print tbl.__dict__
                 print tbl.__dict__.keys()
                 print tbl.foreign_keys
                 for fk in tbl.foreign_keys:
                     print "\n### parent",fk.parent.table.__dict__
                     print "\n### column",fk.column.table.__dict__
              tables[tname]=tbl
              code = repr(tbl)
              code = code.replace(',Column(', ',\n    Column(')
              code = code.replace('BoundMetaData()', 'metadata')
              caps = string.capitalize(tname)
#              self.makeMapper(tname,code,caps)
          vList = con.execute(self.vQuery[dbAlias])
          for v in vList: 
#              tname  = '%s.%s'%(db_owner,v[0].lower())
              tname  = v[0].lower()
              print "Found view",tname
              tbl  = sqlalchemy.Table(tname, dbsMeta, autoload=True, schema=db_owner.lower())
#              tbl  = sqlalchemy.Table(tname, dbsMeta, autoload=True)
              if tname.lower()=='sitesummary':
                 print tbl.__dict__
                 print tbl.__dict__.keys()
                 print tbl.foreign_keys
                 print tbl.columns
                 for fk in tbl.foreign_keys:
                     print "\n### parent",fk.parent.table.__dict__
                     print "\n### column",fk.column.table.__dict__
              tables[tname]=tbl
              code = repr(tbl)
              code = code.replace(',Column(', ',\n    Column(')
              code = code.replace('BoundMetaData()', 'metadata')
              caps = string.capitalize(tname)
#              self.makeMapper(tname,code,caps)
          self.dbTables[dbAlias]=tables
      t_end=time.time()
      t = self.getTable(dbAlias,'SiteSummary','vtv')
      sel = sqlalchemy.select(['*'],from_obj=[t])
      print sel
      res = con.execute(sel)
      for item in res:
          print "result from SiteView",item
      t = self.getTable(dbAlias,'Block','tblk')
      sel = sqlalchemy.select([t.c.path],from_obj=[t],distinct=True,limit=10,offset=0)
      print sel
      res = con.execute(sel)
      for item in res:
          print "result from Block",item

      sel = sqlalchemy.select([t.c.path],from_obj=[t],distinct=True)
      tmp = sel.alias('tmp')
      q   = sqlalchemy.select(['tmp.*','rownum as rnum'],from_obj=[tmp])
      sel = sqlalchemy.select(['*'],from_obj=[q])
      sel.append_whereclause( 'rnum between %s and %s'%(0,10) )
      print sel
      res = con.execute(sel)
      for item in res:
          print "result from Block",item
      return con

  def makeMapper(self,tname,code,caps):
              print """%s = %s
          class %s (object):
              pass
          mapper(%s, %s)
          """ % (tname, code, caps, caps, tname)

  def getTable(self,dbAlias,tableName,tableAlias=""):
      """
         Returns table and/or alias to the table. 
         @type  tableName: string
         @param tableName: table name
         @type tableAlias: string
         @param tableAlias: alias to the table (optional)
         @rtype : string
         @return: SQLAlchemy table object
      """
      tables = self.dbTables[dbAlias]
      if self.dbType[dbAlias]=='oracle':
         tableName=string.lower(tableName)
      if tableAlias:
         # return alias to the table
         return tables[tableName].alias(tableAlias)
      else:
         # return table itself
         return tables[tableName]

  def getTableNames(self,dbAlias):
      """
         Should retrieve list of existing table names from underlying DB.
         @type  self: class object
         @param self: none
         @rtype : string
         @return: not implemented yet, should return list
      """
      return self.dbTables[dbAlias].keys()

  def printQuery(self,dbAlias,sel):
      if  type(sel) is types.StringType:
          return sel
      if  self.dbType[dbAlias]=='oracle':
          return str(sel.compile(dialect=oracle.dialect())) 
      if  self.dbType[dbAlias]=='mysql':
          return str(sel.compile(dialect=mysql.dialect())) 
      if  self.dbType[dbAlias]=='sqlite':
          return str(sel.compile(dialect=sqlite.dialect())) 

  def getForeignKeys(self,dbAlias,table):
      tDict = self.dbTables[dbAlias]
      if self.dbType[dbAlias]=='oracle':
         table=string.lower(table)
      if tDict.has_key(table):
         return tDict[table].foreign_keys
      raise "No table '%s' found"%table

  def getColumns(self,dbAlias,table):
      tDict = self.dbTables[dbAlias]
      if self.dbType[dbAlias]=='oracle':
         table=string.lower(table)
      if tDict.has_key(table):
         return tDict[table]._columns.keys()
      raise "No table '%s' found"%table

  def col(self,dbAlias,tableAlias,colName):
      if self.dbType[dbAlias]=='oracle':
         return getattr(tableAlias.c,string.lower(colName))
      return getattr(tableAlias.c,colName)

  def getTableObject(self,dbAlias,table):
      for t in self.dbTables[dbAlias].keys():
          if string.lower(table)==string.lower(t):
             return self.dbTables[dbAlias][t] # all table names stored as lower case
      raise "No table '%s' found"%table

  def getDBTableName(self,dbAlias,table):
      # TODO: need to cache tList for given dbsIntance
      tList = self.dbTables[dbAlias].keys()
      tList.sort()
      lowTableList=[]
      if self.lowTables.has_key(dbAlias):
         lowTableList=self.lowTables[dbAlias]
      if  not lowTableList:
          for t in tList:
              lowTableList.append(string.lower(t))
          self.lowTables[dbAlias]=lowTableList
      try:    
         res=tList[self.lowTables[dbAlias].index(table.lower())]
         return res
      except:
         raise "Fail to lookup table '%s' in '%s' instance"%(table.lower(),dbAlias)
      
  def createObjs(self,dburl):
      db = sqlalchemy.create_engine(dburl)
      metadata = sqlalchemy.BoundMetaData(db)

      sql = sqlalchemy.select([information_schema.tables.c.table_name,
                               information_schema.tables.c.table_schema])

      print """from sqlalchemy import *
      metadata = BoundMetaData('%s')
      """ % dburl

      for tname,schema in db.execute(sql):
          tbl  = sqlalchemy.Table(tname, metadata, schema=schema, autoload=True);
          code = repr(tbl)
          code = code.replace(',Column(', ',\n    Column(')
          code = code.replace('BoundMetaData()', 'metadata')
          caps = string.capitalize(tname)

          print """%s = %s
      class %s (object):
          pass
      mapper(%s, %s)
      """ % (tname, code, caps, caps, tname)
  # convert SQLAlchemy schema to Drew's one.
#  def constructSchema(self,dbAlias):
#      schema = {}
#      tList = self.dbTables[dbAlias].keys()
#      tList.sort()
#      for idx in xrange(0,len(tList)):
#          table = tList[idx]
#          tObj= self.dbTables[dbAlias][table]
#          d = {}
#          for col  in tObj.columns:
#              if col.primary_key:
#                 d[col.name]=Column(PrimaryKey=True)
#                 continue
#              if col.foreign_key: 
#                 fk=repr(col.foreign_key).split("'")[1].split('.')[0]
#                 d[col.name]=Column(ForeignKey=self.getDBTableName(dbAlias,fk))
#              else:
#                 d[col.name]=Column()
#          schema[tObj.fullname] = Table(d)
#      return schema

#
# main
#
if __name__ == "__main__":
   db = DBManager()
   host=''
   dbType='Oracle'
   dbName='cms_dbs'
   dbUser='cms_dbs_int_global_reader'
   dbPass='everyoneknowsDBS2'
   db.connect(dbType,dbName,dbUser,dbPass,host)
