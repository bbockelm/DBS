#!/usr/bin/env python

import cherrypy, types
from services.rest import *
from services.rest.RestService import *

# DD specific modules
from utils.DDUtil import *
from utils.DDConfig    import *
from utils.webtools_modules import *
from DBSAPI.dbsApi import DbsApi

class Service(object):
    @cherrypy.expose
    def default(self, *args, **kwargs):
        return 

class DDRestServer(DDLogger,Controller):
    def __init__(self,context=None,verbose=0):
        self.ddConfig  = DDConfig()
        self.name="DDRestServer"
        if context:
            Controller.__init__ (self, context, __file__)
        rest_service = Service()
        rest_service.rest = RestService()
        self.rest = rest_service.rest
        self.config=self.setConfig(base=self.name)
        print "+++ %s is loaded, supported mime_types:"%self.name
        print self.rest.supportTypes

    def readyToRun(self):
        opts=self.context.CmdLineArgs().opts
        self.baseUrl   = opts.baseUrl
        self.rest._url = opts.baseUrl+"/services"
        self.rest._mUrl= self.baseUrl+"sitedb/Common/masthead"
        self.rest._fUrl= self.baseUrl+"sitedb/Common/footer"
        self.rest._dbs = self.ddConfig.dbsprimary()
        self.rest._host= self.ddConfig.url()
        dbsUrl = self.ddConfig.dbsUrl()
        dbsVer = self.ddConfig.dbsVer()
        dbsConfig={'url':dbsUrl,'mode':'POST','version':dbsVer,'retry':2}
        self.rest._dbsApi= DbsApi(dbsConfig)
        print "+++ %s url %s"%(self.name,self.rest._url)
        print "+++ %s use DBS-url %s"%(self.name,dbsUrl)
        print "+++ %s use DBS-version %s"%(self.name,dbsVer)
        cherrypy.config.update ({'request.dispatch':cherrypy.dispatch.MethodDispatcher()})

    def setConfig(self,base=""):
        # used thread_pool, queue_size parameters to tune up server performance
        # see discussion on http://amix.dk/blog/viewEntry/119
        cherrypy.server.thread_pool = 30
        cherrypy.server.socket_queue_size = 15
        mime_types=self.rest.supportTypes
        httpHeader=[('Expires',time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime(time.time()+315360000))),
                               ('Accept-Encoding','gzip'),
                               ('TE','deflate, gzip, x-gzip, identity, trailer'),
                               ('Cache-Control','max-age=315360000'),
                               ('Authorization','Basic')
                   ]
        conf = {'/'         : {'request.dispatch':cherrypy.dispatch.MethodDispatcher(),
                               'tools.staticdir.root': os.getcwd(),
                               'tools.response_headers.on':True,
                               'tools.etags.on':True,
                               'tools.etags.autotags':True,
                               'tools.response_headers.headers':
                              [('Expires','Mon, 26 Jul 1997 05:00:00 GMT'),
                               ('Accept-Encoding','gzip'),
                               ('TE','deflate, gzip, x-gzip, identity, trailer'),
                               ('Cache-Control','no-store, no-cache, must-revalidate,post-check=0, pre-check=0')]
                              },
                '/images'   : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.root': os.getcwd(),
                               'tools.staticdir.dir':'images',
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/rss'      : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'rss'},
                '/css'      : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.root': os.getcwd(),
                               'tools.staticdir.dir':'css',
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/js'       : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.dir':'js',
                               'tools.staticdir.content_types':{'js':'text/javascript'},
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/WEBTOOLS' : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.dir':'WEBTOOLS',
                               'tools.staticdir.content_types':{'js':'text/javascript','':'text/javascript','css':'text/css'},
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/Common'   : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.dir':'WEBTOOLS/Common',
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/yui'      : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.root': os.getcwd(),
                               'tools.staticdir.dir':'yui',
                               'tools.staticdir.content_types':{'js':'text/javascript'},
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/YUI'      : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.root': os.getcwd(),
                               'tools.staticdir.dir':'YUI',
                               'tools.staticdir.content_types':{'js':'text/javascript'},
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
               }
        if  base:
            newConf={}
            for key in conf.keys():
                newConf[key]=conf[key]
                if key=="/":
                   newKey="/%s"%base.replace("/","")
                else:
                   newKey="/%s/%s"%(base.replace("/",""),key.replace("/",""))
                newConf[newKey]=conf[key]
            return newConf
        return conf

#
# Main
#
if __name__=="__main__":
    DDRest = DDRestServer(context,verbose=0)
    conf = {'/':{'request.dispatch':cherrypy.dispatch.MethodDispatcher()}}
    cherrypy.quickstart(rest_service,'/service',config=conf)
