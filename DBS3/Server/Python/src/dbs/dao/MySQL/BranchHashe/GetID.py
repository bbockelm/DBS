#!/usr/bin/env python
"""
This module provides BranchHashe.GetID data access object.
Light dao object to get the id for a given BranchHash
"""
__revision__ = "$Id: GetID.py,v 1.1 2010/02/05 21:00:40 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from dbs.dao.Oracle.BranchHashe.GetID import GetID as OraBranchHasheGetID

class GetID(OraBranchHasheGetID):
            pass

