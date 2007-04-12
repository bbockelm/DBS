#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Author:  Valentin Kuznetsov, Cornell University, 2006
#

# system modules
import sys, string, time, types
#import sqlalchemy.mods.threadlocal
from   sqlalchemy import *

# DBS modules
from   DDConfig import *

ddConfig  = DDConfig()
useEngine = ddConfig.engine()
verbose   = ddConfig.verbose()
user      = ddConfig.user()
password  = ddConfig.password()
dbname    = ddConfig.dbname()

if useEngine=='sqlite':
   print "Use SQLite engine for Data Discovery history"
   engine = create_engine('sqlite:///sqlite.db')
elif useEngine=='oracle':
   print "Use ORACLE engine for Data Discovery history"
   engine = create_engine('oracle://%s:%s@cms_dbs'%(user,password),strategy='threadlocal',threaded=True)
elif useEngine=='mysql':
   print "Use MySQL engine for Data Discovery history"
   engine = create_engine('mysql://%s:%s@localhost/%s'%(user,password,dbname),strategy='threadlocal',echo=True)
else:
   raise "Unsupported DB engine backend"

#DBS_CONNECTION = engine.connect()
#metadata = MetaData()

tableList = []
#
SEQ_USER = Sequence(name="%s.%s"%(dbname,'SEQ_USER'),start=1,increment=True)
DD_USER = Table('DD_USER', engine,
  Column('id', Integer, Sequence("%s.%s"%(dbname,'SEQ_USER')), nullable = False, primary_key = True,autoincrement=True),
  Column('userid', String(60), nullable = False, unique=True),
  schema=dbname
)
tableList.append(DD_USER)
#
SEQ_INSTANCE = Sequence(name="%s.%s"%(dbname,'SEQ_INSTANCE'),start=1,increment=True)
DD_INSTANCE = Table('DD_INSTANCE', engine,
  Column('id', Integer, Sequence("%s.%s"%(dbname,'SEQ_INSTANCE')), nullable = False, primary_key = True,autoincrement=True),
  Column('dbsinstance', String(60), nullable = False, unique=True),
  schema=dbname
)
tableList.append(DD_INSTANCE)
#
SEQ_COMMAND = Sequence(name="%s.%s"%(dbname,'SEQ_COMMAND'),start=1,increment=True)
DD_COMMAND = Table('DD_COMMAND', engine,
  Column('id', Integer, Sequence("%s.%s"%(dbname,'SEQ_COMMAND')), nullable = False, primary_key = True,autoincrement=True),
  Column('command', String(1000)),
  Column('alias', String(1000)),
  schema=dbname
)
tableList.append(DD_COMMAND)
#
SEQ_HISTORY = Sequence(name="%s.%s"%(dbname,'SEQ_HISTORY'),start=1,increment=True)
DD_HISTORY = Table('DD_HISTORY', engine,
  Column('id', Integer, Sequence("%s.%s"%(dbname,'SEQ_HISTORY')), nullable = False, primary_key = True,autoincrement=True),
  Column('userid', Integer, ForeignKey("DD_USER.id"), nullable = False),
  Column('cmdid', Integer, ForeignKey("DD_COMMAND.id"), nullable = False),
  Column('dbsid', Integer, ForeignKey("DD_INSTANCE.id"), nullable = False),
  Column('history_date', Date, onupdate=func.current_timestamp(), nullable = False),
  Column('history_time', String(100), onupdate=func.current_timestamp()),
  schema=dbname
)
tableList.append(DD_HISTORY)
#
# main
#
if __name__ == "__main__":
    tableList.reverse()
    for table in tableList:
        print "Drop table",table
        try:
           table.drop()
        except:
           pass
    tableList.reverse()
    for table in tableList:
        print table,type(table)
        try:
           table.create()
        except:
           sys.excepthook(sys.exc_info()[0],sys.exc_info()[1],sys.exc_info()[2])
           raise "Fail to create %s"%table.name
    
    con=engine.connect()
    res=select([DD_HISTORY]).execute()
    print "select",res
    for item in res:
       print res
