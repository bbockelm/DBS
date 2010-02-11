#!/usr/bin/env python
"""
This module provides File.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.1 2010/02/05 21:00:43 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.File.GetID import GetID as OraFileGetID

class GetID(OraFileGetID):
            pass

