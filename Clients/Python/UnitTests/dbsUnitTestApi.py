#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys

lapiObj = ""
f = ""
class DbsUnitTestApi:
	def __init__(self, obj, fileObj):
		global lapiObj
		global f
		lapiObj = obj
		f = fileObj
	def run(*listArgs, **dictArgs):
		try:
			global lapiObj
			global f
			info =  str(lapiObj.im_func.func_name) + str(listArgs[1:])
			excep = dictArgs['excep']
			lapiObj(*listArgs[1:])
			#for data in apiObj(*listArgs):
				#print "  %s" % data
			if excep:
				f.write("\nFAILED. " + info + " AN EXCEPTION WAS EXPECTED BUT NONE WAS RAISED")
			else:
				f.write("\nPASSED. " + info + " AN EXCEPTION WAS NOT EXPECTED AND NONE WAS RAISED ")
		except:
			exception =  str(sys.exc_info()[0]) + " : " +  str(sys.exc_info()[1])
			print exception
			if excep:
				f.write("\nPASSED. " + info + " AN EXCEPTION WAS EXPECTED AND RAISED. THE EXCEPTION IS : " + exception)
			else:
				f.write("\nFAILED. " + info + " AN EXCEPTION WAS NOT EXPECTED BUT RAISED. THE EXCEPTION IS : " + exception)

