#!/usr/bin/env python
"""
This module provides business object class to interact with Primary Dataset. 
"""

__revision__ = "$Id: DBSSite.py,v 1.2 2010/02/11 22:54:21 afaq Exp $"
__version__ = "$Revision: 1.2 $"

from WMCore.DAOFactory import DAOFactory

class DBSSite:
    """
    Site business object class
    """
    def __init__(self, logger, dbi, owner):
        daofactory = DAOFactory(package='dbs.dao', logger=logger, dbinterface=dbi, owner=owner)
        self.logger = logger
        self.dbi = dbi
        self.owner = owner

        self.sitelist = daofactory(classname="Site.List")
        self.sm = daofactory(classname="SequenceManager")
        self.sitein = daofactory(classname="Site.Insert")


    def listSites(self):
        """
        Returns all sites.
        """
	conn = self.dbi.connection()
        result=self.sitelist.execute(conn)
	conn.close()
	return result

    def insertSite(self, businput):
        """
        Input dictionary has to have the following keys:
        site_name
        it builds the correct dictionary for dao input and executes the dao
        """
        conn = self.dbi.connection()
        tran = conn.begin()
        try:
	    siteobj={
		"site_name" : businput["site_name"]
	    }
            businput["site_id"] = self.sm.increment("SEQ_SI", conn, True)
            self.sitein.execute(conn, businput, True)
            tran.commit()
        except Exception, ex:
            if str(ex).lower().find("unique constraint") != -1 or str(ex).lower().find("duplicate") != -1:
                # already exists, lets fetch the ID
                self.logger.warning("Unique constraint violation being ignored...")
                self.logger.warning("%s" % ex)
                pass
            else:
                tran.rollback()
                self.logger.exception(ex)
                raise
        finally:
            conn.close()
