"""
DBS  insert buffer configuration file
"""

__revision__ = "$Id: $"
__version__ = "$Revision: $"

import os, logging
from WMCore.Configuration import Configuration

config = Configuration()

config.section_("Agent")
config.Agent.hostName = "cmssrv49.fnal.gov"
config.Agent.contact = "anzar@fnal.gov"
config.Agent.teamName = "DBS"
config.Agent.agentName = "DBSWriteBehindCache"
config.Agent.useMsgService = False
config.Agent.useTrigger = False
    
config.section_("General")
config.General.workDir = "/uscms/home/anzar/devDBS3/DBS3_ROOT"

config.section_("CoreDatabase")
config.CoreDatabase.connectUrl = "mysql://dbs3:XXXXXX@cmssrv49.fnal.gov:3306/CMS_DBS3_ANZ_3"
config.CoreDatabase.dialect = "mysql"
config.CoreDatabase.dbowner = '__MYSQL__'
#config.CoreDatabase.socket = os.getenv("DBSOCK")

config.component_('DBSInsertBuffer')
config.DBSInsertBuffer.default_expires=300
config.DBSInsertBuffer.pollInterval = 1 
config.DBSInsertBuffer.namespace= "dbs.components.insertbuffer.DBSInsertBuffer"
config.DBSInsertBuffer.componentDir = config.General.workDir + "/DBSInsertBuffer"
#config.DBSInsertBuffer.dbowner = '__MYSQL__'
config.DBSInsertBuffer.workerThreads = 1

