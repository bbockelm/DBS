#!/usr/bin/env python
#

import sys
from nvsException import *
from nvsOptions import NvsOptionParser
from nvsApi import NvsApi
try:
	optManager  = NvsOptionParser()
	(opts,args) = optManager.getOpt()
	api = NvsApi(opts.__dict__)
	#for a in api.validate('RelVal','Primary'):
	for a in api.validate('reco','Tier'):
		print a
  
except NvsException, ex:
	print "Caught API Exception %s: %s "  % (ex.msg, ex.code )
print "Done"

