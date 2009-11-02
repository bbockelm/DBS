#!/usr/bin/env python
#
# Revision: $"
# Id: $"
#
import sys, optparse
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsConfig import DbsConfig
from DBSAPI.dbsAnalysisDatasetDefinition import DbsAnalysisDatasetDefinition
from DBSAPI.dbsAnalysisDataset import DbsAnalysisDataset
from xml.sax.saxutils import escape, unescape

class DbsDQOptionParser(optparse.OptionParser):
  """
     OptionParser is main class to parse options.
  """
  def __init__(self):

      optparse.OptionParser.__init__(self, usage="%prog --help or %prog --command [options]", 
		version="%prog 0.0.1", conflict_handler="resolve")

      self.add_option("--url=",action="store", type="string", dest="url", default="https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_global_writer/servlet/DBSServlet", 
           help="specify URL, DEFAULT is --url=https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_global_writer/servlet/DBSServlet")

      self.add_option("--dataset", action="store", type="string", dest="dataset", help="REQUIRED: specify a valid dataset path")

      self.add_option("--query", action="store", type="string", dest="query", help="REQUIRED: specify a valid dbs QL query")

      self.add_option("--templatequeryname", action="store", default="", type="string", dest="templatequeryname", help="REQUIRED: specify a valid bname for the query to be saved (ADS definition, if name is already in use the query will be reuded from DBS)")

if __name__ == "__main__":

		optManager  = DbsDQOptionParser()
		(opts,args) = optManager.parse_args()
		opts = opts.__dict__
		
		url=opts['url']
		dataset=opts['dataset']
		query=opts['query']
		templatequeryname=opts['templatequeryname']


		if url in ('', None, 'BADURL'):
                        configDict = DbsConfig(opts)
                        opts['url'] = str(configDict.url())

                if dataset in ('', None):
                        print "You MUST specify a valid dataset path, use --dataset= or --help"
                        sys.exit(0)
		if query in ('', None):
                        print "You MUST specify a valid query, use --query= or --help"
		if templatequeryname in ('', None):
                        print "You MUST specify a valid templatequeryname, use --templatequeryname= or --help"

		api = DbsApi(opts)

		adsdef = DbsAnalysisDatasetDefinition(Name=templatequeryname,
                                         #ProcessedDatasetPath=martQ['PATH'],  #No path for template query
                                         UserInput=escape(query),
                                         SQLQuery=escape(query),
                                         Description=templatequeryname
                         )

		try:
			
			api.createAnalysisDatasetDefinition (adsdef)

		except DbsApiException, ex:
                	if ex.getErrorMessage().find("Already Exists") < 0:
				print ex
			print "WARNING ....ADS DEF ALREADY EXISTS, Will be RE USED from DBS..and query provided here will NOT be used..."	
		ads=DbsAnalysisDataset(
                        Type='TEST',
                        Status='NEW',
                        PhysicsGroup='RelVal',
                        Path=dataset,
                        Description="scripted"
                )
                print "Processing, please wait..."
		try:
			api.createAnalysisDataset(ads, templatequeryname)	

		except DbsApiException, ex:
  			print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  			if ex.getErrorCode() not in (None, ""):
    				print "DBS Exception Error Code: ", ex.getErrorCode()


