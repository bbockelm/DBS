#!/usr/bin/env python
"""
This module provides MigrationRequests.List data access object.
"""
__revision__ = "$Id: List.py,v 1.1 2010/04/22 08:05:17 akhukhun Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.MigrationRequests.List import List as OraMigList

class List(OraMigList):
    pass
		   
