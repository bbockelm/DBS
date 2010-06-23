#!/usr/bin/env python
""" DAO Object for ACQUISITION_ERAS table """

__revision__ = "$Revision: 1.9 $"
__version__  = "$Id: Insert.py,v 1.9 2010/03/09 16:38:03 afaq Exp $ "

from WMCore.Database.DBFormatter import DBFormatter
from sqlalchemy import exceptions
from dbs.dao.Oracle.InsertTable.Insert import InsertSingle

class Insert(InsertSingle):
    """ ACQUISITION_ERAS Insert DAO Class"""
    def execute(self, conn, daoinput, transaction = False):
	if not conn:
	    raise Exception("dbs/dao/Oarcle/AcquisitionEra/Insert expects db connection from upper layer.")
        try:
            self.executeSingle(conn, daoinput, "ACQUISITION_ERAS", transaction)
        except Exception:
            raise

