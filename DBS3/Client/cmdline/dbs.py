# dbs cmdline tool 
# $Revision: $"
# $Id: $"
# @author anzar
#
import os, sys, socket
import urllib, urllib2
from httplib import HTTPConnection
from StringIO import StringIO
from exceptions import Exception
import cjson
import getopt 

try:
	# Python 2.6
	import json
except:
	# Prior to 2.6 requires simplejson
	import simplejson as json

class DbsApi:
        def __init__(self, url="", proxy=""):
		"""
		* DbsApi CTOR
		url: serevr URL
		proxy: http proxy; this feature is TURNED OFF at the moemnt
		"""
                self.url=url
		self.proxy=proxy
		self.opener =  urllib2.build_opener()

	def callServer(self, urlplus="", params={}, callmethod='GET'):
		"""
        	* callServer
		* API to make HTTP call to the DBS Server
		* urlplus: addition to URL, this is generall where the VERB is provided for the REST call, as '/files'
        	* params: parameters to server
		* callmethod; the HTTP method used, by default it is HTTP-GET, possible values are GET, POST and PUT
		"""
		UserID=os.environ['USER']+'@'+socket.gethostname()
		headers =  {"Content-type": "application/json", "Accept": "application/json", "UserID": UserID }

		res=""
		try:
			calling=self.url+urlplus
			
			proxies = {}
			if self.proxy not in (None, ""):
				proxies = { 'http': self.proxy }
			#print calling
			if params == {} and not callmethod in ('POST', 'PUT') :
				#data = urllib.urlopen(calling, proxies=proxies)
				#data = urllib2.urlopen(calling)
				req = urllib2.Request(url=calling, headers = headers)
				data = urllib2.urlopen(req)
			else:
				params = cjson.encode(params)
				req = urllib2.Request(url=calling, data=params, headers = headers)
				req.get_method = lambda: callmethod
				data = self.opener.open(req)
			res = data.read()
		except urllib2.HTTPError, httperror:
			self.parseForException(json.loads(httperror.read()))
			
			#HTTPError(req.get_full_url(), code, msg, hdrs, fp)
		except urllib2.URLError, urlerror:
			raise urlerror
		
		#FIXME: We will always return JSON from DBS, even from POST, PUT, DELETE APIs, make life easy here
		json_ret=json.loads(res)
		self.parseForException(json_ret)
		return json_ret
		
	def parseForException(self, data):
	    """
	    An internal method, should not be used by clients
	    """
	    if type(data)==type("abc"):
		data=json.loads(data)
            if type(data) == type({}) and data.has_key('exception'):
		#print "Service Raised an exception: "+data['exception']
		raise Exception("DBS Server raised an exception: %s" %data['message'])
	    return data

def make_url_params(params):
    """
    Makes a '&' separated string from the input dictionary
    param1=value1&param2=value2...
    """
    c=0   
    ret="" 
    for k,v in params.items():
	if c==0: 
	    ret += "?"
	    c=1
	else: ret +="&"
	#this is just a special case with block_name
	if k=='block_name':
	    parts=v.split('#')
	    v=parts[0]+urllib.quote_plus('#')+parts[1]
	ret+="%s=%s" % (k,v)
    return ret

def parse_params(input):
    """parse the comma separated k=v list"""
    try:
	params={}
	input=input.strip()
	if len(input) < 0:
	    return params
	for akv in input.split(","):
	    akv=akv.strip()
	    akv_split=akv.split("=")
	    k=akv_split[0].strip()
	    v=akv_split[1].strip()
	    params[k]=v
	return params
    except:
	print "unable to parse the parameters, refer to --help"
	sys.exit(0)

def validate_verb(verb):
    """validates against supported dbs verbs"""
    if verb not in ['primarydatasets','outputconfigs','datasets','datasetparents','datasetchildren','blocks'
		    ,'files','fileparents','filechildren','filelumis','runs','sites','datatypes','datatiers'
		    ,'blockparents','blockparents','blockchildren']:
	print "'%s' is not a valid DBS verb, refer to DBS documentation" %verb
	sys.exit(0)
    return verb
	   
def help(prog):
    """
    Dislays basic help
    """
    help_txt="""
    This simple tool lets you inetract with a dbs instacnce
    USAGE : python %s --url=<dbs-reader-serrvice-url> --verb=<one-of-valid-verbs> --params=<comma-separated-list-of-param=value>
    DBS supports following verbs (and respective parameters) (--help displays this help) :-
	primarydatasets : dataset
        outputconfigs : dataset, logical_file_name, release_version, pset_hash, app_name, output_module_label
        datasets : dataset, parent_dataset, release_version, pset_hash, app_name, output_module_label, processing_version, acquisition_era, run_num, 
		   physics_group_name, logical_file_name, primary_ds_name, primary_ds_type, data_tier_name, dataset_access_type
	datasetparents : dataset
	datasetchildren : dataset
        blocks : block_name, dataset, logical_file_name, origin_site_name, run_num
        files : logical_file_name, dataset, block, release_version, pset_hash, app_name, output_module_label, minrun, maxrun, origin_site_name, lumi_list
	fileparents : logical_file_name
        filechildren : logical_file_name
	filelumis : logical_file_name
        runs : minrun, maxrun
	sites : block_name, site_name
	datatypes : dataset
        datatiers : datatier
	blockparents : block_name
	blockchildren : block_name
    Refer to DBS documentation for more details [https://twiki.cern.ch/twiki/bin/view/CMS/DBS-3APIREFERENCE]
    Example :-
	python %s --url=http://cmssrv18.fnal.gov:8585/dbs3 --verb=files --params="block_name=/BbartoJpsi/CMSSW_1_4_4-CSA07-2151/GEN-SIM#f4c0f218-8c7e-45e2-af1f-59b571e074ba , release_version=CMSSW_1_4_4" 
    """ % (prog, prog)
    print help_txt

def prety_print_json(injson):
    """
    This method tries to print the input json
    """
    print json.dumps(injson, sort_keys = False, indent = 4)
    
if __name__ == "__main__":
	#get the cmdline options
	options, remainder = getopt.gnu_getopt(sys.argv[1:], 'huvp', ['help','url=', 'verb=','params='])
	params={}
	verb=""
	url=""

	if len(options) < 1:
	    help(sys.argv[0])
	    sys.exit(0)	
	for opt, arg in options:
	    if opt in ('-h', '--help'):
		help(sys.argv[0])	
	    elif opt in ('--verb'):
		verb=validate_verb(arg)
	    elif opt in ('--params'):
	       params=parse_params(arg)
	    elif opt in ('--url'):
		url=arg
	    else :
		help(sys.argv[0])	    
	if url=="":
	    print "No url provide, use --help for more information"
	    sys.exit(0)
	if len(params) > 1 and verb == "":
	    print "No verb provided, use --help for more information"
	    sys.exit(0)
	dbsapi = DbsApi(url=url)	
	prety_print_json(dbsapi.callServer("/%s%s" %(verb,make_url_params(params)), callmethod='GET' ))
	
