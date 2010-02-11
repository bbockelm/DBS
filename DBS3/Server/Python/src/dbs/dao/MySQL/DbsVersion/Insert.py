#!/usr/bin/env python
""" DAO Object for DbsVersions table """ 

__revision__ = "$Revision: 1.1 $"
__version__  = "$Id: Insert.py,v 1.1 2010/02/05 21:00:43 afaq Exp $ "

from dbs.dao.Oracle.DbsVersion.Insert import Insert as OraDbsVersionInsert

class Insert(OraDbsVersionInsert):
            pass

