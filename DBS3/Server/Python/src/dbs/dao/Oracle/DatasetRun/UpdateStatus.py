#!/usr/bin/env python
"""
This module provides DatasetRun.UpdateStatus data access object.
"""
__revision__ = "$Id: $"
__version__ = "$Revision: $"

from WMCore.Database.DBFormatter import DBFormatter

class UpdateStatus(DBFormatter):

    """
    Dataset Update Statuss DAO class.
    """

    def __init__(self, logger, dbi, owner):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
	self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = """UPDATE %sDATASET_RUNS SET COMPLETE = :complete where DATASET = :dataset and RUN_NUMBER = :run_number""" %  self.owner 
        
    def execute ( self, conn, transaction, dataset="", run_number=-1, complete=1 ):
        """
        for a given (dataset, run) mark it complete
        """	
	binds = { "dataset" : dataset , "run_number" : run_number, "complete" : complete }
        result = self.dbi.processData(self.sql, binds, conn, transaction)
    

