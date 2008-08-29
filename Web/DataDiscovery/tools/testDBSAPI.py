#!/usr/bin/env python

import os, sys
from DBSAPI.dbsApi import DbsApi

dbsUrl="http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet"
config={'url':dbsUrl,'mode':'POST','version':'DBS_1_2_2','retry':2}
dbsApi=DbsApi(config)
print dbsApi,sys.version
userInput="find datasesssssss where dataset like *"
try:
   res=dbsApi.executeQuery(userInput,begin=0,end=10,type="query")
   print res
except:
   import traceback
   traceback.print_exc()
   pass


