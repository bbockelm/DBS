#!/usr/bin/env python
"""
This module provides FileParent.List data access object.
"""
__revision__ = "$Id: List.py,v 1.1 2010/02/05 21:00:45 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.FileParent.List import List as OraFileParentList

class List(OraFileParentList):
        pass

