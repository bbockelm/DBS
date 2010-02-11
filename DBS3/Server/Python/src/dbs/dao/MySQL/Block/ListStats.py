#!/usr/bin/env python
"""
This module provides Block.ListStats data access object.
Block parameters based on current conditions at DBS, are listed by this DAO
"""
__revision__ = "$Id: ListStats.py,v 1.1 2010/02/05 21:00:39 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.Block.ListStats import List as OraBlockListStats

class ListStats(OraBlockListStats):
        pass
	

