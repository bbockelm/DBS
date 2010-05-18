#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsRun import DbsRun
from DBSAPI.dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

try:

    url_list=[
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_global_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_01_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_02_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_04_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_05_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_06_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_07_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_08_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_09_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_ph_analysis_01_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_ph_analysis_02_writer/servlet/DBSServlet',
                'https://cmsdbsprod.cern.ch:8443/cms_dbs_caf_analysis_01_writer/servlet/DBSServlet',
		'https://cmst0dbs.cern.ch:8443/cms_dbs_prod_tier0_writer/servlet/DBSServlet',
		'https://cmst0dbs.cern.ch:8443/cms_dbs_int_tier0_writer/servlet/DBSServlet',
	]

    args={}	
    args['mode']='POST'
    args['version']='DBS_2_0_8'
    args['level']='DBSINFO'
    
    for aurl in url_list:
	args['url']=aurl
	print aurl
	api = DbsApi(args)
	api.insertTier ('DQM')
	api.insertTier ('GEN-SIM-RAWDEBUG')
	
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

