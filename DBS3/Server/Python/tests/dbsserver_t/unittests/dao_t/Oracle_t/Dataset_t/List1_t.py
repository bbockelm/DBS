"""
dao  unittests
"""

__revision__ = "$Id: DBSReaderModel.py,v 1.8 2009/12/27 13:39:17 akhukhun Exp $"
__version__ = "$Revision: 1.8 $"

import os
import unittest
import logging
from WMCore.Database.DBFactory import DBFactory
from dbs.dao.Oracle.Dataset.List1 import List1 as DatasetList1

class List_t(unittest.TestCase):
    
    def setUp(self):
        """setup all necessary parameters"""
        dburl = os.environ["DBS_TEST_DBURL_READER"] 
        self.logger = logging.getLogger("dbs test logger")
        self.dbowner = os.environ["DBS_TEST_DBOWNER_READER"]
        self.dbi = DBFactory(self.logger, dburl).connect()
        self.assertTrue(self.dbi.engine.dialect.name == "oracle", \
                        "Database must be oracle" )
                        
    def test01(self):
        """dao.Oracle.Dataset.List1: Basic"""
        dao = DatasetList1(self.logger, self.dbi, self.dbowner)
        dao.execute()
        dao.execute(dataset="*")
        dao.execute(parent_dataset='*')
        dao.execute(version='%')
        dao.execute(hash='%')
        dao.execute(app_name='%')
        dao.execute(output_module_label='%')
        result = dao.execute("ThisDoesNotExist")
        self.assertTrue(type(result) == list)
        self.assertEqual(len(result), 0)
        
if __name__ == "__main__":
    SUITE = unittest.TestLoader().loadTestsFromTestCase(List_t)
    unittest.TextTestRunner(verbosity=2).run(SUITE)
        
