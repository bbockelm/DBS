#!/usr/bin/env python
"""
This module provides business object class to interact with Dataset Run table. 
"""

__revision__ = "$Id: DBSRun.py,v 1.9 2010/03/18 19:48:12 afaq Exp $"
__version__ = "$Revision: 1.9 $"

from WMCore.DAOFactory import DAOFactory

class DBSRun:
    """
    Site business object class
    """

    def __init__(self, logger, dbi, owner):
        daofactory = DAOFactory(package='dbs.dao', logger=logger, dbinterface=dbi, owner=owner)
        self.logger = logger
        self.dbi = dbi
        self.owner = owner

        self.runlist = daofactory(classname="DatasetRun.List")

    def listRuns(self, minrun=-1, maxrun=-1):
        """
        List run known to DBS.
        """
	try:
		conn = self.dbi.connection()
		tran=False
		ret=self.runlist.execute(conn, minrun, maxrun, tran)
		return ret

	except Exception, ex:
		raise ex
		
	finally:
		conn.close()

   
