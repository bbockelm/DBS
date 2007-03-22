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
   print "Use SQLite engine"
   engine = create_engine('sqlite:///sqlite.db')
elif useEngine=='oracle':
   print "Use ORACLE engine"
   engine = create_engine('oracle://%s:%s@%s'%(user,password,dbname),strategy='threadlocal',threaded=True)
elif useEngine=='mysql':
   print "Use MySQL engine"
   engine = create_engine('mysql://%s:%s@localhost/%s'%(user,password,dbname),strategy='threadlocal',echo=True)
else:
   raise "Unsupported DB engine backend"

#DBS_CONNECTION = engine.connect()
#metadata = MetaData()

tableList = []
#
SEQ_USER = Sequence(name='SEQ_USER',start=1,increment=True)
DD_USER = Table('DD_USER', engine,
  Column('id', Integer, Sequence('SEQ_USER'), nullable = False, primary_key = True),
  Column('userid', String(60), nullable = False, unique=True)
)
tableList.append(DD_USER)
#
SEQ_INSTANCE = Sequence(name='SEQ_INSTANCE',start=1,increment=True)
DD_INSTANCE = Table('DD_INSTANCE', engine,
  Column('id', Integer, Sequence('SEQ_INSTANCE'), nullable = False, primary_key = True),
  Column('dbsinstance', String(60), nullable = False, unique=True)
)
tableList.append(DD_INSTANCE)
#
SEQ_COMMAND = Sequence(name='SEQ_COMMAND',start=1,increment=True)
DD_COMMAND = Table('DD_COMMAND', engine,
  Column('id', Integer, Sequence('SEQ_COMMAND'), nullable = False, primary_key = True),
  Column('command', String(1000)),
  Column('alias', String(1000))
)
tableList.append(DD_COMMAND)
#
SEQ_HISTORY = Sequence(name='SEQ_HISTORY',start=1,increment=True)
DD_HISTORY = Table('DD_HISTORY', engine,
  Column('id', Integer, Sequence('SEQ_HISTORY'), nullable = False, primary_key = True),
  Column('userid', Integer, ForeignKey("DD_USER.id"), nullable = False),
  Column('cmdid', Integer, ForeignKey("DD_COMMAND.id"), nullable = False),
  Column('dbsid', Integer, ForeignKey("DD_INSTANCE.id"), nullable = False),
  Column('date', Date, onupdate=func.current_timestamp()),
  Column('time', Time, onupdate=func.current_timestamp())
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
    
