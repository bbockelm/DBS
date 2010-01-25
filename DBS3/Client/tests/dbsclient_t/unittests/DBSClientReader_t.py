"""
web unittests
"""

__revision__ = "$Id: DBSClientReader_t.py,v 1.2 2010/01/25 19:49:00 afaq Exp $"
__version__ = "$Revision: 1.2 $"

import os
import json
import unittest
import sys,imp

from dbs.apis.dbsClient import *

url="http://cmssrv18.fnal.gov:8585/dbs3"
api = DbsApi(url=url)

def importCode(code,name,add_to_sys_modules=0):
    module = imp.new_module(name)
    exec code in module.__dict__
    if add_to_sys_modules:
        sys.modules[name] = module
    return module

infofile=open("info.dict","r")    
testparams=importCode(infofile, "testparams", 0).info
#['release_version', 'primary_ds_name', 'app_name', 'output_module_label', 'tier', 'pset_hash', 'procdataset', 'site', 'block', 'dataset']    

class DBSClientReader_t(unittest.TestCase):
    
    def test01(self):
        """unittestDBSClientReader_t.listPrimaryDatasets: basic test"""
        api.listPrimaryDatasets()
        api.listPrimaryDatasets('*')
	api.listPrimaryDatasets(testparams['primary_ds_name'])
	api.listPrimaryDatasets(testparams['primary_ds_name']+"*")

    def test02(self):
	"""unittestDBSClientReader_t.listDatasets: basic test"""
	api.listDatasets()
	api.listDatasets(testparams['dataset'])
	api.listDatasets(testparams['dataset']+"*")
	api.listDatasets(release_version=testparams['release_version'])
	api.listDatasets(pset_hash=testparams['pset_hash'])
	api.listDatasets(app_name=testparams['app_name'])
	api.listDatasets(output_module_label=testparams['output_module_label'])
	api.listDatasets(release_version=testparams['release_version'], pset_hash=testparams['pset_hash'], \
		app_name=testparams['app_name'], output_module_label=testparams['output_module_label'])
	api.listDatasets(dataset=testparams['dataset'], release_version=testparams['release_version'], \
		pset_hash=testparams['pset_hash'], app_name=testparams['app_name'], output_module_label=testparams['output_module_label'])
	api.listDatasets(dataset=testparams['dataset'], release_version=testparams['release_version'])

#def test03(self):

if __name__ == "__main__":
    SUITE = unittest.TestLoader().loadTestsFromTestCase(DBSClientReader_t)
    unittest.TextTestRunner(verbosity=2).run(SUITE)
        
