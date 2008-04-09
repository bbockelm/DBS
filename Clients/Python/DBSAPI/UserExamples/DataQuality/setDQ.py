#
import sys, optparse
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsDQFlag import DbsDQFlag
from DBSAPI.dbsRunLumiDQ import DbsRunLumiDQ


class DbsDQOptionParser(optparse.OptionParser):
  """
     OptionParser is main class to parse options.
  """

  def __init__(self):

      optparse.OptionParser.__init__(self, usage="%prog --help or %prog --command [options]", 
		version="%prog 0.0.1", conflict_handler="resolve")

      self.add_option("--url=",action="store", type="string", dest="url", default="BADURL",
           help="specify URL, e.g. --url=http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet, If no url is provided default url from dbs.config is attempted")

      self.add_option("--run", action="store", type="int", dest="run", help="specify a valid run number")

      self.add_option("--tag", action="store", type="string", dest="tag", help="Quality Information Mask")

      self.add_option("--value", action="store", type="string", dest="value", default="UNKNOWN", 
		help="Value can be GOOD, BAD and UNKNOWN (Defualt)")



if __name__ == "__main__":

	try:
		optManager  = DbsDQOptionParser()
		(opts,args) = optManager.parse_args()
		opts = opts.__dict__

		if opts['url'] in ('', None, 'BADURL'):
			print "You must specify a valid DBS URL, use --url= or --help"
			sys.exit(0)

                if opts['run'] in ('', None):
                        print "You must specify a valid run number, use --run= or --help"
                        sys.exit(0)

                if opts['tag'] in ('', None):
                        print "You must specify a valid QIM, use --tag= or --help"
                        sys.exit(0)

                if opts['value'] in ('', None):
                        print "You must specify a valid value: GOOD, BAD, UNKNOWN (Defualt), use --value= or --help"
                        sys.exit(0)

		print opts

		flag = DbsDQFlag (
			Name = opts['tag'],
			Value = opts['value'],
			)
		run_dq = DbsRunLumiDQ (
			RunNumber=opts['run'],
			#LumiSectionNumber=123,
			DQFlagList = [flag]
			)

		print run_dq

		api = DbsApi(opts)

		api.insertRunLumiDQ( [run_dq] )

		
	except DbsApiException, ex:
  		print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  		if ex.getErrorCode() not in (None, ""):
    			print "DBS Exception Error Code: ", ex.getErrorCode()

