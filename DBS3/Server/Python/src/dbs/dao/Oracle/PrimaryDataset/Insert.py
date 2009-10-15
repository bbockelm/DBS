#!/usr/bin/env python
"""
This module provides PrimaryDataset.Insert data access object.
"""
__revision__ = "$Id: $"
__version__ = "$Revision: =$"


from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):
    """
    PrimaryDataset Insert DAO Class.
    """

    sql = \
"""
INSERT INTO PRIMARY_DATASETS
(PRIMARY_DS_ID, PRIMARY_DS_NAME, PRIMARY_DS_TYPE_ID, CREATION_DATE, CREATE_BY) 
VALUES(:primarydsid, :primarydsname, :primarydstypeid, :creationdate, :createby)
"""

    def execute(self, primdsobject, conn=None, transaction=False):
        """
        primdsobject dictionary has to have the following keys:
        primarydsid, primarydsname, primarydstypeid, creationdate, createby
        """
        binds = primdsobject
        self.dbi.processData(self.sql, binds, conn, transaction)
            
