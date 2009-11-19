#!/usr/bin/env python
""" DAO Object for FileParents table """ 

__revision__ = "$Revision: 1.5 $"
__version__  = "$Id: Insert.py,v 1.5 2009/11/19 17:37:42 akhukhun Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % self.dbi.engine.url.username

        self.sql = \
"""
INSERT INTO %sFILE_LUMIS 
(FILE_LUMI_ID, RUN_NUM, LUMI_SECTION_NUM, FILE_ID) 
VALUES (:FILE_LUMI_ID, :RUN_NUM, :LUMI_SECTION_NUM, :FILE_ID)
""" % (self.owner)

    def execute( self, daoinput, conn = None, transaction = False ):
        self.dbi.processData(self.sql, daoinput, conn, transaction)
