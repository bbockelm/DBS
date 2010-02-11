#!/usr/bin/env python
"""
This module provides FileLumi.List data access object.
"""
__revision__ = "$Id: List.py,v 1.1 2010/02/05 21:00:44 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.FileLumi.List import List as OraFileLumiList

class List(OraFileLumiList):
        pass

