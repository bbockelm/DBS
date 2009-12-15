#!/usr/bin/env python
"""
DBS Rest Model module
"""

__revision__ = "$Id: DBSWriterModel.py,v 1.2 2009/12/15 17:29:58 afaq Exp $"
__version__ = "$Revision: 1.2 $"

import re
import cjson

from cherrypy import request, response, HTTPError
from WMCore.WebTools.RESTModel import RESTModel

from dbs.business.DBSPrimaryDataset import DBSPrimaryDataset
from dbs.business.DBSDataset import DBSDataset
from dbs.business.DBSBlock import DBSBlock
from dbs.business.DBSFile import DBSFile

from dbs.web.DBSReaderModel import DBSReaderModel

import traceback

class DBSWriterModel(DBSReaderModel):
    """
    DBS3 Server API Documentation 
    """
    def __init__(self, config):
        """
        All parameters are provided through DBSConfig module
        """

        DBSReaderModel.__init__(self, config)

        self.addService('POST', 'primarydatasets', self.insertPrimaryDataset)
        self.addService('POST', 'datasets', self.insertDataset)
        self.addService('POST', 'blocks', self.insertBlock)
        self.addService('POST', 'files', self.insertFile)



    #----- These methods will move to an appropriate class in util
    def getDate(self):
	return time.time() 	

    def getUser(self):
	return "ANZAR"

    def insertPrimaryDataset(self):
        """
	Inserts a Primary Dataset in DBS
        Gets the input from cherrypy request body.
        input must be a dictionary with the following two keys:
        primary_ds_name, primary_ds_type
        """
	
	try :
        	body = request.body.read()
        	indata = cjson.decode(body)
        	indata.update({"creationdate": self.getDate(), "createby": self.getUser() })
        	self.dbsPrimaryDataset.insertPrimaryDataset(data)
		
	except Exception, ex:
       		raise Exception ("DBS Server Exception: %s \n. Exception trace: \n %s " % (ex, traceback.format_exc()) ) 


    def insertDataset(self):
        """
        gets the input from cherrypy request body.
        input must have the following keys:
        KEYS : required/optional:default = ...
        ...
        """

        body = request.body.read()
        indata = cjson.decode(body)
            
        # need proper validation
        dataset={}
        dataset['primaryds'] = indata['PRIMARY_DS_NAME']
        dataset['processedds'] = indata['PROCESSED_DATASET_NAME']
        dataset['datatier'] = indata['DATA_TIER_NAME']
        dataset['globaltag'] = indata.get('GLOBAL_TAG', '')
        dataset['physicsgroup'] = indata.get('PHYSICS_GROUP_NAME', '')
        dataset['creationdate'] = 1234
        dataset['createby'] = "me"
        dataset['datasettype'] = indata.get('DATASET_TYPE', 'test')
        dataset['lastmodificationdate'] = 1234
        dataset['lastmodifiedby'] = "me"
        dataset['isdatasetvalid'] = indata.get('IS_DATASET_VALID', '')
        dataset['xtcrosssection'] = indata.get('XTCROSSSECTION', '')
        dataset['dataset'] = indata["DATASET"]

        self.dbsDataset.insertDataset(dataset)

        
    def insertBlock(self):
        """
        gets the input from cherrypy request body.
        input must be a dictionary with the following keys:
        KEYS: required/optional : default = ...
        ...
        """

        body = request.body.read()
        indata = cjson.decode(body)

        # Proper validation needed
        vblock = re.match(r"(/[\w\d_-]+/[\w\d_-]+/[\w\d_-]+)#([\w\d_-]+)$", 
                      indata["BLOCK_NAME"])
        assert vblock, "Invalid block name %s" % indata["BLOCK_NAME"]
        block={} 
        block.update({
                      "dataset":vblock.groups()[0],
                      "creationdate": indata.get("CREATION_DATE", 123456),
                      "createby":indata.get("CREATE_BY","me"),
                      "lastmodificationdate":indata.get("LAST_MODIFICATION_DATE", 12345),
                      "lastmodifiedby":indata.get("LAST_MODIFIED_BY","me"),
                      "blockname":indata["BLOCK_NAME"],
                      "filecount":indata.get("FILE_COUNT", 0),
                      "blocksize":indata.get("BLOCK_SIZE", 0),
                      "originsite":"TEST",
                      "openforwriting":1
                      })
        self.dbsBlock.insertBlock(block)


    def insertFile(self):
        """
        gets the input from cherrypy request body
        input must be a (list of) dictionary with the following keys: <br />
        LOGICAL_FILE_NAME (required) : string  <br />
        IS_FILE_VALID: (optional, default = 1): 1/0 <br />
        BLOCK, required: /a/b/c#d <br />
        DATASET, required: /a/b/c <br />
        FILE_TYPE (optional, default = EDM): one of the predefined types, <br />
        CHECK_SUM (optional, default = '-1'): string <br />
        EVENT_COUNT (optional, default = -1): int <br />
        FILE_SIZE (optional, default = -1.): float <br />
        ADLER32 (optional, default = ''): string <br />
        MD5 (optional, default = ''): string <br />
        AUTO_CROSS_SECTION (optional, default = -1.): float <br />
	    FILE_LUMI_LIST (optional, default = []): [{"RUN_NUM": 123, "LUMI_SECTION_NUM": 12},{}....] <br />
	    FILE_PARENT_LIST(optional, default = []) :[{"FILE_PARENT_LFN": "mylfn"},{}....] <br />
        """
        body = request.body.read()
        indata = cjson.decode(body)["files"]
        
        # proper validation needed
        businput = []
        assert type(indata) in (list, dict)
        if type(indata) == dict:
            indata = [indata]
            
        for f in indata:
            f.update({"DATASET":f["DATASET"],
                     "CREATION_DATE":12345,
                     "CREATE_BY":"aleko",
                     "LAST_MODIFICATION_DATE":12345,
                     "LAST_MODIFIED_BY":"alsoaleko",
                     "FILE_LUMI_LIST":f.get("FILE_LUMI_LIST",[]),
                     "FILE_PARENT_LIST":f.get("FILE_PARENT_LIST",[])})
            businput.append(f)
            
        self.dbsFile.insertFile(businput)
