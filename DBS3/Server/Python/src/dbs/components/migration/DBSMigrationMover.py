#!/usr/bin/env python
"""
DBS Migration Service Component Module
"""
__revision__ = "$Id: DBSMigrationService.py,v 1.2 2010/06/23 21:30:02 afaq Exp $"
__version__ = "$Revision: 1.2 $"


import logging
import threading
# harness class that encapsulates the basic component logic.
from WMCore.Agent.Harness import Harness
from WMCore.WMFactory import WMFactory
from dbs.components.insertbuffer.DBSInsertBufferPoller import DBSInsertBufferPoller

#['__call__', '__doc__', '__init__', '__module__', '__str__', 'config', 'handleMessage', 'initInThread', 'initialization', 'logState', 'postInitialization', 'preInitialization', 'prepareToStart', 'publishItem', 'startComponent']

class DBSMigrationMover(Harness):
    def __init__(self, config):
	# call the base class
	Harness.__init__(self, config)
	self.pollTime = 1
	print "DBS Migration Service Initialization"
	

    def preInitialization(self):
					    
	# use a factory to dynamically load handlers.
	factory = WMFactory('generic')
					    
	# Add event loop to worker manager
	myThread = threading.currentThread()

	pollInterval = self.config.DBSMigrationMover.pollInterval
	logging.info("Setting poll interval to %s seconds for the migration service" % pollInterval)
	myThread.workerThreadManager.addWorker(DBSMigrationServicePoller(self.config), pollInterval)
	return


	    
