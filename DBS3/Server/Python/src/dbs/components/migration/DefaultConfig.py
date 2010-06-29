"""
DBS migration service configuration file
"""

__revision__ = "$Id: DefaultConfig.py,v 1.1 2010/06/23 21:27:22 afaq Exp $"
__version__ = "$Revision: 1.1 $"

import os, logging
from WMCore.Configuration import Configuration

config = Configuration()

config.section_("Agent")
config.Agent.hostName = "cmssrv49.fnal.gov"
config.Agent.contact = "anzar@fnal.gov"
config.Agent.teamName = "DBS"
config.Agent.agentName = "DBSMigrationService"
config.Agent.useMsgService = False
config.Agent.useTrigger = False
    
config.section_("General")
config.General.workDir = "/uscms/home/anzar/devDBS3/DBS3_ROOT"

config.section_("CoreDatabase")
config.CoreDatabase.connectUrl = "mysql://dbs3:XXXXXXXXXXX@cmssrv49.fnal.gov:3306/CMS_DBS3_ANZ_3"
config.CoreDatabase.dialect = "mysql"
config.CoreDatabase.dbowner = '__MYSQL__'
#config.CoreDatabase.socket = os.getenv("DBSOCK")

config.component_('DBSMigrationService')
config.DBSMigrationService.default_expires=300
config.DBSMigrationService.pollInterval = 1 
config.DBSMigrationService.namespace= "dbs.components.insertbuffer.DBSMigrationService"
config.DBSMigrationService.componentDir = config.General.workDir + "/DBSMigrationService"
#config.DBSInsertBuffer.dbowner = '__MYSQL__'
config.DBSMigrationService.workerThreads = 1

