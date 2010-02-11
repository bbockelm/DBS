#!/usr/bin/env python
"""
This module provides File.List data access object.
"""
__revision__ = "$Id: List.py,v 1.1 2010/02/05 21:00:43 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.File.List import List as OraFileList

class List(OraFileList):
        pass

