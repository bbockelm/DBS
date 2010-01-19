"""
web unittests
"""

__revision__ = "$Id: DBSWriterModel_t.py,v 1.6 2010/01/19 18:01:49 yuyi Exp $"
__version__ = "$Revision: 1.6 $"

import os
import sys
import unittest
from dbsserver_t.utils.DBSRestApi import DBSRestApi
from ctypes import *

class NullDevice:
    def write(self, s):
	pass

"""
This is has to be change everytime running the test. So we need to make it using uuid. YG 1/11/10
COUNTER = os.environ['DBS_TEST_COUNTER']
"""

def uuid():
    lib = CDLL("libuuid.so.1")
    uuid = create_string_buffer(16)
    return lib.uuid_generate(byref(uuid))
    
	
config = os.environ["DBS_TEST_CONFIG_WRITER"] 
api = DBSRestApi(config) 
uid = uuid()
print "uid: ", uid
primary_ds_name = 'unittest_web_primary_ds_name_%s' % uid
print "primaryDS: ", primary_ds_name
dataset = 'unittest_web_dataset_%s' % uid 
print "Dataset: " , dataset
tier = 'GEN-SIM-RAW'
app_name='cmsRun'
output_module_label='Merged'
pset_hash='76e303993a1c2f842159dbfeeed9a0dd' 
release_version='CMSSW_1_2_3'
    
class DBSWriterModel_t(unittest.TestCase):

    def setUp(self):
        """setup all necessary parameters"""

    def test01(self):
        """test01: web.DBSWriterModel.insertPrimaryDataset: basic test"""
	#COUNTER = self.uuid()
        data = {'primary_ds_name':primary_ds_name,
                'primary_ds_type':'TEST'}
        api.insert('primarydatasets', data)
	print "PrimaryDS: ", primary_ds_name

    def test02(self):
        """test02: web.DBSWriterModel.insertPrimaryDataset: duplicate should not riase an exception"""
        data = {'primary_ds_name':primary_ds_name,
                'primary_ds_type':'TEST'}
	print "primary_ds_name: ", primary_ds_name
        api.insert('primarydatasets', data)
	
    """
    def test03(self):
	""test03: web.DBSReaderModel.insertPrimaryDataset: missing 'primary_ds_name, must throw exception""
	data = {'primary_ds_type':'TEST'}
	try:
	    junk = api.insert('primarydatasets', data)
	except IntegrityError:
	     pass
	else:
	     fail("Exception was expected and was not raised.")
    """

    def test04(self):
	"""test04: web.DBSWriterModel.insertOutputModule: basic test"""
	data = {'release_version': release_version, 'pset_hash': pset_hash, 'app_name': app_name, 'output_module_label': output_module_label}
	api.insert('outputconfigs', data)

    def test05(self):
        """test05: web.DBSWriterModel.insertOutputModule: re-insertion should not raise any errors"""
        data = {'release_version': release_version, 'pset_hash': pset_hash, 'app_name': app_name, 'output_module_label': output_module_label}
        api.insert('outputconfigs', data)

	
    """
    def test06(self):
	""test06: web.DBSWriterModel.insertOutputModule: missing parameter must cause an exception""
	data = {'pset_hash': self.pset_hash, 'app_name': self.app_name, 'output_module_label': self.output_module_label}
	self.assertRaises(KeyError, self.api.insert, 'outputconfigs', data)
	"
	try:
	    self.api.insert('outputconfigs', data)
	except:
	    pass
	else:
	    self.fail("Exception was expected and was not raised.")
    	"
    """
    
    def test07(self):
	"""test07: web.DBSWriterModel.insertDataset: basic test"""
	data = {
		'is_dataset_valid': 1, 'physics_group_name': 'Tracker', 'dataset': dataset,
	        'dataset_type': 'PRODUCTION', 'processed_ds_name': 'DBS3_Test_2010', 'primary_ds_name': primary_ds_name,
		'output_configs': [
		    {'release_version': release_version, 'pset_hash': pset_hash, 'app_name': app_name, 'output_module_label': output_module_label},
		    ],
		'global_tag': u'', 'xtcrosssection': 123, 'primary_ds_type': 'test', 'data_tier_name': tier,
		'creation_date' : 1234, 'create_by' : 'anzar', "last_modification_date" : 1234, "last_modified_by" : "anzar",
		#'processing_version': '1',  'acquisition_era_name': u'',
		}
	api.insert('datasets', data)
	
    def test08(self):
	"""test08: web.DBSWriterModel.insertDataset: duplicate insert should be ignored"""
	data = {
		'is_dataset_valid': 1, 'physics_group_name': 'Tracker', 'dataset': dataset,
	        'dataset_type': 'PRODUCTION', 'processed_ds_name': 'DBS3_Test_2010', 'primary_ds_name': primary_ds_name,
		'output_configs': [
		    {'release_version': release_version, 'pset_hash': pset_hash, 'app_name': app_name, 'output_module_label': output_module_label},
		], 
		'global_tag': u'', 'xtcrosssection': 123, 'primary_ds_type': 'test', 'data_tier_name': tier,
		'creation_date' : 1234, 'create_by' : 'anzar', "last_modification_date" : 1234, "last_modified_by" : "anzar",
		#'processing_version': '1',  'acquisition_era_name': u'',
		}
	api.insert('datasets', data)

    """	
    def test09(self):
	""test09: web.DBSWriterModel.insertDataset: missing primary dataset must raise an error""
	data = {
		'is_dataset_valid': 1, 'physics_group_name': 'Tracker', 'dataset': dataset,
	        'dataset_type': 'PRODUCTION', 'processed_ds_name': 'DBS3_Test_2010',
		'output_configs': [
		    {'release_version': release_version, 'pset_hash': pset_hash, 'app_name': app_name, 'output_module_label': output_module_label},
		],
		'global_tag': u'', 'xtcrosssection': 123, 'primary_ds_type': 'test', 'data_tier_name': tier,
		'creation_date' : 1234, 'create_by' : 'anzar', "last_modification_date" : 1234, "last_modified_by" : "anzar",
		#'processing_version': '1',  'acquisition_era_name': u'',
		}
	try:
	    api.insert('datasets', data)
	except:
	    pass
	else:
	    fail("Exception was expected and was not raised.")
	    
    def test10(self):
	""test10: web.DBSWriterModel.insertDataset: missing parameter must raise an error""
	data = {
		'is_dataset_valid': 1, 'physics_group_name': 'Tracker', 'primary_ds_name': primary_ds_name,
	        'dataset_type': 'PRODUCTION', 'processed_ds_name': 'DBS3_Test_2010',
		'output_configs': [
		    {'release_version': release_version, 'pset_hash': pset_hash, 'app_name': app_name, 'output_module_label': output_module_label},
		],
		'global_tag': u'', 'xtcrosssection': 123, 'primary_ds_type': 'test', 'data_tier_name': tier,
		'creation_date' : 1234, 'create_by' : 'anzar', "last_modification_date" : 1234, "last_modified_by" : "anzar",
		#'processing_version': '1',  'acquisition_era_name': u'',
		}
	try:
	    api.insert('datasets', data)
	except:
	    pass
	else:
	    fail("Exception was expected and was not raised.")
    """
	    
    def test11(self):
	"""test11: web.DBSWriterModel.insertDataset: no output_configs, should be fine insert!"""
	data = {
		'dataset': dataset+"_nocfg",
		'is_dataset_valid': 1, 'physics_group_name': 'Tracker', 'primary_ds_name': primary_ds_name,
	        'dataset_type': 'PRODUCTION', 'processed_ds_name': 'DBS3_Test_2010',
		'global_tag': u'', 'xtcrosssection': 123, 'primary_ds_type': 'test', 'data_tier_name': tier,
		'creation_date' : 1234, 'create_by' : 'anzar', "last_modification_date" : 1234, "last_modified_by" : "anzar",
		#'processing_version': '1',  'acquisition_era_name': u'',
		}
	api.insert('datasets', data)
 
if __name__ == "__main__":
    SUITE = unittest.TestLoader().loadTestsFromTestCase(DBSWriterModel_t)
    unittest.TextTestRunner(verbosity=2).run(SUITE)
