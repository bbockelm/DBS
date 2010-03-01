#!/usr/bin/env python
"""
This module provides DatasetRun.List data access object.
"""
__revision__ = "$Id: List.py,v 1.2 2010/02/11 19:39:31 afaq Exp $"
__version__ = "$Revision: 1.2 $"

from dbs.dao.Oracle.DatasetRun.List import List as OraDatasetRunList

class List(OraDatasetRunList):
        pass

