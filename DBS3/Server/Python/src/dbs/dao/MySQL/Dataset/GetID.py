#!/usr/bin/env python
"""
This module provides Dataset.GetID data access object.
Light dao object to get the id for a give /primds/procds/tier
"""
__revision__ = "$Id: GetID.py,v 1.1 2010/02/05 21:00:41 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.Dataset.GetID import GetID as OraDatasetGetID

class GetID(OraDatasetGetID):
            pass

