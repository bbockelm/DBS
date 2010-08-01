#!/usr/bin/env python
"""
This module provides Block.List data access object.
"""
__revision__ = "$Id: List.py,v 1.2 2010/02/11 19:39:29 afaq Exp $"
__version__ = "$Revision: 1.2 $"

from dbs.dao.Oracle.Block.BriefList import BriefList as OraBlockBriefList

class BriefList(OraBlockBriefList):
    pass

