from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsApi import *
import time

try:
    optManager  = DbsOptionParser()
    (opts,args) = optManager.getOpt()

    api = DbsApi(opts.__dict__)
    serverInfo = api.getServerInfo()
    print "Server Version : ", serverInfo['ServerVersion']
    print "Schema Version : ", serverInfo['SchemaVersion']

    versions = api.listDQVersions()
    for aVer in versions:
	print "Version: %s, CreationDate: %s" \
	   % (aVer[0], time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime(long(aVer[1]))))

except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
      print "DBS Exception Error Code: ", ex.getErrorCode()

