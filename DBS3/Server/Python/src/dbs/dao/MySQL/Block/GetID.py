#!/usr/bin/env python
"""
This module provides Block.GetID data access object.
Light dao object to get the id for a give /primds/procds/tier#block
"""
__revision__ = "$Id: GetID.py,v 1.1 2010/02/05 21:00:39 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.Block.GetID import GetID as OraBlockGetID

class GetID(OraBlockGetID):
            pass

