#!/usr/bin/env python
""" DAO Object for Sites table """ 

__revision__ = "$Revision: 1.2 $"
__version__  = "$Id: Insert.py,v 1.2 2009/10/20 02:19:23 afaq Exp $ "

from WMCore.Database.DBFormatter import DBFormatter
from sqlalchemy import exceptions
from dbs.dao.Oracle.InsertTable.Insert import InsertSingle

class Insert(InsertSingle):
    """ DAO for Insert Site """
    def execute( self, daoinput, conn=None, transaction=False ):
	try:
            self.executeSingle(daoinput, "SITES", conn = None, transaction = False)
        except Exception:
            raise


