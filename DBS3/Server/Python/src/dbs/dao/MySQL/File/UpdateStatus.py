#!/usr/bin/env python
"""
This module provides File.UpdateStatus data access object.
"""
__revision__ = "$Id: $"
__version__ = "$Revision: $"

from dbs.dao.Oracle.File.UpdateStatus import UpdateStatus as OraFileUpdateStatus

class UpdateStatus(OraFileUpdateStatus):
    pass
