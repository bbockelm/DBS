#!/usr/bin/env python
"""
This module provides business object class to interact with DatasetParent. 
"""

__revision__ = "$Id: DBSDataset.py,v 1.13 2009/12/22 21:50:14 afaq Exp $"
__version__ = "$Revision: 1.13 $"

from WMCore.DAOFactory import DAOFactory

class DBSDatasetParent:
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
        
        self.datasetparentlist = daofactory(classname="DatasetParent.List")

    def listDatasetParents(self, dataset):
        """
        takes required dataset parameter
        returns only parent dataset name
        """
        return self.datasetparentlist.execute(dataset)
