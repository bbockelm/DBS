#!/usr/bin/env python
"""
This module provides PrimaryDataset.List data access object.
"""
__revision__ = "$Id: List.py,v 1.1 2010/02/05 21:00:47 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.PrimaryDataset.List import List as OraPrimaryDatasetList

class List(OraPrimaryDatasetList):
        pass
