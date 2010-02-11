#!/usr/bin/env python
"""
This module provides Block.List data access object.
"""
__revision__ = "$Id: List.py,v 1.1 2010/02/05 21:00:39 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.Block.List import List as OraBlockList

class List(OraBlockList):
    pass

