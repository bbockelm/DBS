#!/usr/bin/env python
"""
This module provides Block.UpdateStats data access object.
"""
__revision__ = "$Id: UpdateStats.py,v 1.1 2010/02/05 21:00:39 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.Block.List import List as OraBlockList


from dbs.dao.Oracle.Block.UpdateStats import UpdateStats as OraUpdateStats
class UpdateStats(OraUpdateStats):
    pass

