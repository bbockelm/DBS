"""
web unittests
"""

__revision__ = "$Id: DBSReaderModel.py,v 1.8 2009/12/27 13:39:17 akhukhun Exp $"
__version__ = "$Revision: 1.8 $"

import os
import unittest
from dbsserver_t.utils.DBSRestApi import DBSRestApi

COUNTER = os.environ['DBS_TEST_COUNTER']

class DBSWriterModel_t(unittest.TestCase):
    
    def setUp(self):
        """setup all necessary parameters"""
        config = os.environ["DBS_TEST_CONFIG_WRITER"] 
        self.api = DBSRestApi(config) 

    def test01(self):
        """web.DBSReaderModel.insertPrimaryDataset: basic test"""
        data = {'primary_ds_name':'unittest_web_primary_ds_name_%s' % COUNTER,
                'primary_ds_type':'TEST'}
        self.api.insert('primarydatasets', data)
        
        
if __name__ == "__main__":
    SUITE = unittest.TestLoader().loadTestsFromTestCase(DBSWriterModel_t)
    unittest.TextTestRunner(verbosity=2).run(SUITE)
