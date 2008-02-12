#!/usr/bin/env python
#
#
#

# system modules
import os, re, string, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from nvsHttpService import NvsHttpService


from nvsConfig import NvsConfig
import urlparse
import urllib2

import logging
import inspect

from nvsLogger import *




def getInt(value = None):
	if (value == None ) :
		return 0
	if (len(value) < 1 ) :
		return 0
	return int(value)

def getLong(value = None):
	if (value == None ) :
		return 0
	if (len(value) < 1 ) :
		return 0
	return long(value)


class NvsApi(NvsConfig):
	"""
	NvsApi class, provides access to Nvs Server, 
	all clients must use this interface 
	""" 
	def __init__(self, Args={}):
		""" 
		Constructor. 
		Initializes the NVS Api by reading configuration 
		parameters from nvs.config file OR from the parameters 
		passed through Args as a Python dictionary.
		Parameters passed through Args take precedence 
		over parameters in nvs.config
	
		For MODE=POST (Default Mode): Creates a http proxy, to be able to talk to NVS Server
		"""
		NvsConfig.__init__(self,Args)
		if self.verbose():
			print "configuration dictionary:", self.configDict
			print "using version",self.version()
			print "using mode   ",self.mode()
		#
		# Connect to the Server proxy
		#
		self._server = ""
		self._server = NvsHttpService(self.url(), "0", Args)
		# Set up logging
		if not self.configDict.has_key('level'):
			self.configDict['level'] = "ERROR"
		if not self.configDict.has_key('log'):
			self.configDict['log'] = "STDOUT"
		NvsLogger(self.loglevel(), self.log())
		logging.log(NVSDEBUG, "NVS Api initialized")
	
	# ------------------------------------------------------------
	#  nvsApi API Implementation follows
	# ------------------------------------------------------------
	def validate(self, name, type):
		"""
		returns:list of similar names if validation succeeds
		examples: 
			api.validate(name = "relval", type = "Primary") : 
		raise: NvsException
		"""
		funcInfo = inspect.getframeinfo(inspect.currentframe())
		logging.log(NVSDEBUG, "Api call invoked %s" % str(funcInfo[2]))
		# Invoke Server.    
		data = self._server._call ({ 'name' : name, 'type' : type }, 'GET')
		logging.log(NVSDEBUG, data)
		if self.verbose():
			print data
		# Parse the resulting xml output.
		result = []
		class Handler (xml.sax.handler.ContentHandler):
			def startElement(self, name, attrs):
				if name == 'nvs_name':
					tmp = {}
					tmp['name'] = str(attrs['name'])
					tmp['similarity'] = str(attrs['similarity'])
					result.append(tmp)
			
		xml.sax.parseString (data, Handler ())
		return result



