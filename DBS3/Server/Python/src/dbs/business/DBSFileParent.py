#!/usr/bin/env python
"""
This module provides business object class to interact with FileParent. 
"""

__revision__ = "$Id: DBSDataset.py,v 1.13 2009/12/22 21:50:14 afaq Exp $"
__version__ = "$Revision: 1.13 $"

from WMCore.DAOFactory import DAOFactory

class DBSFileParent:
    """
    DatasetParent business object class
    """
    def __init__(self, logger, dbi, owner):
        """
        initialize business object class.
        """
        daofactory = DAOFactory(package='dbs.dao', logger=logger, 
                                dbinterface=dbi, owner=owner)
        self.logger = logger
        self.dbi = dbi
        
        self.fileparentlist = daofactory(classname="FileParent.List")

    def listFileParents(self, logical_file_name): 
        """
        required parameter: logical_file_name
        returns: logical_file_name, parent_logical_file_name, parent_file_id
        """
        return self.fileparentlist.execute(logical_file_name)
