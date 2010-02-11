#!/usr/bin/env python
"""
This module provides ApplicationExecutable.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.1 2010/02/05 21:00:46 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.OutputModuleConfig.GetID import GetID as OraOutputModuleConfigGetID

class GetID(OraOutputModuleConfigGetID):
            pass

