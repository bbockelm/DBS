#!/usr/bin/env python
"""
This module provides DatasetTYpe.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.1 2010/02/05 21:00:42 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from WMCore.Database.DBFormatter import DBFormatter

from dbs.dao.Oracle.DatasetType.GetID import GetID as OraDatasetTypeGetID

class GetID(OraDatasetTypeGetID):
            pass

