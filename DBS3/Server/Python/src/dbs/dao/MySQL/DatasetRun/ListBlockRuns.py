#!/usr/bin/env python
"""
This module provides DatasetRun.ListBlockRuns data access object.
"""
__revision__ = "$Id: ListBlockRuns.py,v 1.1 2010/03/01 20:55:52 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.DatasetRun.ListBlockRuns import ListBlockRuns as OraDatasetRunListBlockRuns

class ListBlockRuns(OraDatasetRunListBlockRuns):
        pass

