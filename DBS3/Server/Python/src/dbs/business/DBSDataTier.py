#!/usr/bin/env python
"""
This module provides business object class to interact with datatiers table. 
"""

__revision__ = "$Id: DBSDataTier.py,v 1.1 2010/04/21 19:50:01 afaq Exp $"
__version__ = "$Revision: 1.1 $"

from WMCore.DAOFactory import DAOFactory

class DBSDataTier:
    """
    DataTier business object class
    """
    def __init__(self, logger, dbi, owner):
	daofactory = DAOFactory(package='dbs.dao', logger=logger, dbinterface=dbi, owner=owner)
	self.logger = logger
	self.dbi = dbi
	self.owner = owner

	self.sm = daofactory(classname="SequenceManager")
	self.dataTier = daofactory(classname="DataTier.List")
	self.dtin = daofactory(classname="DataTier.Insert")

    def listDataTiers(self, data_tier_name=""):
	"""
	List data tier(s)
	"""
	try:
	    conn = self.dbi.connection()
	    result = self.dataTier.execute(conn, data_tier_name)
	    return result
	except Exception, ex:
	    raise ex
	finally:
	    conn.close()

    def insertDataTier(self, businput):
        """
        Input dictionary has to have the following keys:
        data_tier_name, creation_date, create_by
        it builds the correct dictionary for dao input and executes the dao
        """
	conn = self.dbi.connection()
        tran = conn.begin()
        try:
	    businput["data_tier_id"] = self.sm.increment(conn, "SEQ_DT", tran)
	    assert businput["data_tier_name"]
	    assert businput["creation_date"]
	    assert businput["create_by"]
            self.dtin.execute(conn, businput, tran)
            tran.commit()
        except Exception, ex:
                if str(ex).lower().find("unique constraint") != -1 or str(ex).lower().find("duplicate") != -1:
                        # already exist
                        self.logger.warning("Unique constraint violation being ignored...")
                        self.logger.warning("%s" % ex)
			pass
		else:
            		tran.rollback()
            		self.logger.exception(ex)
            		raise
        finally:
            conn.close()

