#!/usr/bin/env python
"""
This module provides business object class to interact with FileLumi. 
"""

__revision__ = "$Id: DBSDataset.py,v 1.13 2009/12/22 21:50:14 afaq Exp $"
__version__ = "$Revision: 1.13 $"

from WMCore.DAOFactory import DAOFactory

class DBSFileLumi:
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
        
        self.filelumilist = daofactory(classname="FileLumi.List")

    def listFileLumis(self, logical_file_name="", block_name=""): 
        """
        optional parameter: logical_file_name, block_name
        returns: logical_file_name, file_lumi_id, run_num, lumi_section_num
        """
        return self.filelumilist.execute(logical_file_name, block_name)
