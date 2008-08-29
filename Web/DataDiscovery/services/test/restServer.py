#!/usr/bin/env python

import cherrypy
from services.rest import *
from services.rest.RestService import *

class Service(object):
    @cherrypy.expose
    def default(self, *args, **kwargs):
        print "Called Service default"
        print args
        print kwargs
        return 
#
# Main
#
if __name__=="__main__":
   rest_service = Service()
   url='http://localhost:8080/service/rest'
   rest_service.rest = RestService(url)
#   rest_service.rest = RestService()
#   rest_service.rest._url = "http://localhost:8080/service"
   conf = {'/':{'request.dispatch':cherrypy.dispatch.MethodDispatcher()}}
   cherrypy.quickstart(rest_service,'/service',config=conf)

