#!/usr/bin/env python
"""
This module provides Site.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.1 2010/02/05 21:00:51 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.Site.GetID import GetID as OraSiteGetID

class GetID(OraSiteGetID):
            pass
