#!/usr/bin/env python
""" DAO Object for ApplicationExecutables table """ 

__revision__ = "$Revision: 1.6 $"
__version__  = "$Id: Insert.py,v 1.6 2010/02/11 22:51:59 afaq Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi, owner):
            DBFormatter.__init__(self, logger, dbi)
	    self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""

            self.sql = """INSERT INTO %sAPPLICATION_EXECUTABLES ( APP_EXEC_ID, APP_NAME) VALUES (:app_exec_id, :app_name)""" % (self.owner)

    def execute( self, appExeObj, conn=None, transaction=False ):
	try:
	    print self.sql
	    print appExeObj
            result = self.dbi.processData(self.sql, appExeObj, conn, transaction)
 	except Exception, e:
	    if str(ex).lower().find("unique constraint") != -1 or str(ex).lower().find("duplicate") != -1:
		pass
	    else:
		raise


