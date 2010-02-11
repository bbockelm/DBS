#!/usr/bin/env python
"""
This module provides business object class to interact with DBSAcqusitionEra. 
"""

__revision__ = "$Id: DBSAcquisitionEra.py,v 1.2 2009/12/28 16:55:29 afaq Exp $"
__version__ = "$Revision $"

from WMCore.DAOFactory import DAOFactory

class DBSAcquisitionEra:
    """
    DBSAcqusition Era business object class
    """
    def __init__(self, logger, dbi, owner):
        daofactory = DAOFactory(package='dbs.dao', logger=logger, dbinterface=dbi, owner=owner)
        self.logger = logger
        self.dbi = dbi
        self.owner = owner

        #self.acqlist = daofactory(classname="AcquisitionEra.List")
        self.acqin = daofactory(classname="AcquisitionEra.Insert")
        self.sm = daofactory(classname="SequenceManager")


    def listAcquisitionEras(self):
        """
        Returns all AcquisitionEras.
        """
        return self.acqlist.execute()


    def insertAcquisitionEra(self, businput):
        """
        Input dictionary has to have the following keys:
        acquisition_era_name, creation_date, create_by
        it builds the correct dictionary for dao input and executes the dao
        """
        conn = self.dbi.connection()
        tran = conn.begin()
        try:
	    businput["acquisition_era_id"] = self.sm.increment("SEQ_AQE", conn, True)
	    assert businput["acquisition_era_name"]
	    assert businput["creation_date"]
	    assert businput["create_by"]
            self.acqin.execute(businput, conn, True)
            tran.commit()
        except Exception, ex:
                if str(ex).lower().find("unique constraint") != -1 or str(ex).lower().find("duplicate") != -1:
                        # already exists
                        self.logger.warning("Unique constraint violation being ignored...")
                        self.logger.warning("%s" % ex)
			pass
		else:
            		tran.rollback()
            		self.logger.exception(ex)
            		raise
        finally:
            conn.close()
