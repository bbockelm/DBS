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

#if useEngine=='sqlite':
#   print "Use SQLite engine"
#   engine = create_engine('sqlite:///sqlite.db', strategy='threadlocal', echo=verbose)
#elif useEngine=='oracle':
#   print "Use ORACLE engine"
#   engine = create_engine('oracle://%s:%s@%s'%(ddConfig.user(),ddConfig.password(),ddConfig.dbname()),strategy='threadlocal', echo=verbose)
#elif useEngine=='mysql':
#   print "Use MySQL engine"
#   engine = create_engine('mysql://%s:%s@localhost/%s'%(ddConfig.user(),ddConfig.password(),ddConfig.dbname()),echo=verbose)
#else:
#   raise "Unsupported DB engine backend"
if useEngine=='sqlite':
   print "Use SQLite engine"
   engine = create_engine('sqlite:///sqlite.db')
elif useEngine=='oracle':
   print "Use ORACLE engine"
   engine = create_engine('oracle://%s:%s@%s'%(user,password,dbname),strategy='threadlocal',threaded=True)
elif useEngine=='mysql':
   print "Use MySQL engine"
   engine = create_engine('mysql://%s:%s@localhost/%s'%(user,password,dbname),strategy='threadlocal')
else:
   raise "Unsupported DB engine backend"

#DBS_CONNECTION = engine.connect()
#metadata = MetaData()

tableList = []
#
seq_user = Sequence(name='seq_user',start=1,increment=True)
t_user = Table('t_user', engine,
  Column('id', Integer, Sequence('seq_user'), nullable = False, primary_key = True),
  Column('userid', String(60), nullable = False, unique=True)
)
tableList.append(t_user)
#
seq_command = Sequence(name='seq_command',start=1,increment=True)
t_command = Table('t_command', engine,
  Column('id', Integer, Sequence('seq_command'), nullable = False, primary_key = True),
  Column('command', String(1000))
)
tableList.append(t_command)
#
seq_history = Sequence(name='seq_history',start=1,increment=True)
t_history = Table('t_history', engine,
  Column('id', Integer, Sequence('seq_history'), nullable = False, primary_key = True),
  Column('userid', Integer, ForeignKey("t_user.id"), nullable = False),
  Column('cmdid', Integer, ForeignKey("t_command.id"), nullable = False),
  Column('date', Date, onupdate=func.current_timestamp()),
  Column('time', Time, onupdate=func.current_timestamp())
)
tableList.append(t_history)
#
# Table mappers
class T_USER(object):
   def __init__(self,iName=None,iPassword=None):
       self.name = iName
       self.password = iPassword

class T_HISTORY(object):
   def __init__(self,iUserId=None,iDate=None,iCmdId=None):
       self.userid = iUserId
       self.date   = iDate
       self.cmdid  = iCmdId

class T_COMMAND(object):
   def __init__(self,iCmd=None):
       self.command  = iCmd
#
############ MAPPERS ###############
#
#map_user = mapper(T_USER,t_user)
#map_hist = mapper(T_HISTORY,t_history,properties={'t_user':relation(T_USER)})
#
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
    
