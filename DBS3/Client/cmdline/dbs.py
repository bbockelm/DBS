#!/usr/bin/env python
"""
Very simple dbs3 client:
"""

__revision__ = "$Id: dbs.py,v 1.5 2010/08/09 18:20:38 akhukhun Exp $"
__version__ = "$Revision: 1.5 $"

import json
import os, sys, socket
import urllib, urllib2
from optparse import OptionParser

class DBSCLI:
    def __init__(self, baseurl):
        self.baseurl = baseurl
        self.opener =  urllib2.build_opener()
        
    def callServer(self, call, params={}):
        "method for GET verb"

	try:
	    UserID=os.environ['USER']+'@'+socket.gethostname()
	    headers =  {"Content-type": "application/json", "Accept": "application/json", "UserID": UserID }

	    calling = "/".join((self.baseurl, call))
	    if params:
		calling = "?".join((calling, urllib.urlencode(params, doseq=True)))

	    req = urllib2.Request(url=calling, headers = headers)
	    data = self.opener.open(req)
	    res = data.read()
	    try:
		res = json.loads(res)
	    except:
		raise Exception("Could not decode the json: %s" % res)

	    if type(res)==dict and res.has_key('exception'):
		raise Exception("DBS Server raised an exception: %s" % res['message'])

	    return res
	
	except urllib2.HTTPError, httperror:
	    exc = json.loads(httperror.read())
	    raise Exception('DBS server raised an exception: %s' % exc['message'])

	except urllib2.URLError, urlerror:
	    raise urlerror
		
	except Exception, ex:
	    raise ex
    
def pretty_print_json(injson):
    """
    This method tries to print the input json
    """
    print json.dumps(injson, sort_keys = False, indent = 4)


if __name__ == "__main__":
    URL = os.getenv('DBS_READER_URL', "http://localhost:8585/DBS")
    for opt in sys.argv:
	if opt.startswith('--url=http'):
	    URL = opt.split('=')[1]
    try:
	CLI = DBSCLI(URL)
	calls = CLI.callServer('help')

	if len(sys.argv) <=1 or sys.argv[1] not in calls:
	    print "\nUsage: dbs <call> --param1=<param1> --param2=<param2> ..."
	    print "server supports the following api calls:" 
	    for c in calls: print ' ', c
	    print "For more information on a specific api, do : dbs <call> --help \n"
	else: 
	    call = sys.argv[1]
	    info = CLI.callServer('help/%s' % call)
	    parser = OptionParser(usage=info['doc'])
	    parser.add_option("--url", dest='url')
	    for p in info['params']:
		parser.add_option("--%s"%p, dest=p)
	    opts, args = parser.parse_args()
	    assert len(args)==1, "Only one positional argument(api call) can be provided"
	    optdict = opts.__dict__
	    #clean the dictionary from empty parameters and keys
	    options = dict((k, optdict[k]) for k in optdict if optdict[k] and not k=='url') 
	    result = CLI.callServer(call, options)
	    pretty_print_json(result)

    except urllib2.URLError, urlerror:
	print "Unable to connect to specified URL ($DBS_READER_URL or --url= ) : %s " %URL


