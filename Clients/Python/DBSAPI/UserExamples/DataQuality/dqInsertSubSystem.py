from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsApi import DbsApi

if __name__ == "__main__":

  try:
    optManager  = DbsOptionParser()
    (opts,args) = optManager.getOpt()

    api = DbsApi(opts.__dict__)

    #Add a new SubSystem
    api.insertSubSystem(name="HB", parent="HCAL")

    subSys = api.listSubSystems()
    for aSub in subSys:
	print "Name: %s, Parent: %s" %(aSub['Name'], aSub['Parent'])


  except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
      print "DBS Exception Error Code: ", ex.getErrorCode()

