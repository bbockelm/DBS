#!/usr/bin/env python
"""
DBS Migration Service Client Interface Rest Model module
"""

__revision__ = "$Id: DBSMigrateModel.py,v 1.1 2010/04/22 07:47:39 akhukhun Exp $"
__version__ = "$Revision: 1.1 $"

import re
import cjson
import time
import traceback

from cherrypy import request, response, HTTPError
from WMCore.WebTools.RESTModel import RESTModel
from dbs.utils.dbsUtils import dbsUtils

from dbs.business.DBSMigrate import DBSMigrate

class DBSMigrateModel(RESTModel):
    """
    DBS Migration Service Web Model
    """
    
    def __init__(self, config):
        """
        All parameters are provided through DBSConfig module
        """
        RESTModel.__init__(self, config)
	
        self.addMethod('POST', 'submit', self.submit)
        self.addMethod('POST', 'remove', self.remove)
        self.addMethod('GET',  'status', self.status)

	self.dbsMigrate = DBSMigrate(self.logger, self.dbi, config.dbowner)

    def submit(self):
	"""
	Interface for submitting a migration request
	"""
	body = request.body.read()
	indata = cjson.decode(body)
	indata.update({"creation_date": dbsUtils().getTime(), \
		"last_modification_date" : dbsUtils().getTime(), \
		"create_by" : dbsUtils().getCreateBy() , "last_modified_by" : dbsUtils().getCreateBy() })
	return self.dbsMigrate.insertMigrationRequest(indata)

    def status(self, migration_id="", block="", dataset="", user=""):
	"""
	Interface to query status of a migration request
	In this preference order of input parameters :-
	    migration_id, block, dataset, user
	    (if multi parameters are provided, only the precedence order is followed
	"""
	return self.dbsMigrate.listMigrationRequests(migration_id, block, dataset, user)
    
    def remove(self, migration_id=""):
	"""
	Interface to remove a migration request from the queue
	Only FAILED, COMPLETED and PENDING requests can be removed
	(running requests cannot be removed)
	"""
	return self.dbsMigrate.removeMigrationRequest(migration_id)

