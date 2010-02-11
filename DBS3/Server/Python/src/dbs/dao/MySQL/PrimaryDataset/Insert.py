#!/usr/bin/env python
""" DAO Object for PrimaryDatasets table """ 

__revision__ = "$Revision: 1.1 $"
__version__  = "$Id: Insert.py,v 1.1 2010/02/05 21:00:47 afaq Exp $ "

from dbs.dao.Oracle.PrimaryDataset.Insert import Insert as OraPrimaryDatasetInsert

class Insert(OraPrimaryDatasetInsert):
    pass

