#!/usr/bin/env python
""" DAO Object for ACQUISITION_ERAS table """

__revision__ = "$Revision: $"
__version__  = "$Id: $ "

from WMCore.Database.DBFormatter import DBFormatter
from sqlalchemy import exceptions
from dbs.dao.Oracle.InsertTable.Insert import InsertSingle

class Insert(InsertSingle):
    """ ACQUISITION_ERAS Insert DAO Class"""
    def execute(self, daoinput, conn = None, transaction = False):
        try:
            self.executeSingle(daoinput, "ACQUISITION_ERAS", conn = None, transaction = False)
        except Exception:
            raise

