#!/usr/bin/env python
"""
This module provides Dataset.List data access object.
Lists dataset_parent and output configuration parameters too.
"""
__revision__ = "$Id:$"
__version__ = "$Revision: $"

from dbs.dao.Oracle.Dataset.List import List as OraDatasetList

class List(OraDatasetList):
        pass

