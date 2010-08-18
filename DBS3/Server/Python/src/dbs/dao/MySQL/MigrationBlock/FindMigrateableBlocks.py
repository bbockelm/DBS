#!/usr/bin/env python
"""
This module provides MigrationBlock.FindMigrateableBlock data access object.
"""
__revision__ = "$Id: FindMigrateableBlock.py,v 1.1 2010/08/18 19:01:43 yuyi Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.MigrationBlock.FindMigrateableBlocks import FindMigrateableBlocks as OraMigFindMigrateableBlock

class FindMigrateableBlocks(OraMigFindMigrateableBlock):
    pass
		   
