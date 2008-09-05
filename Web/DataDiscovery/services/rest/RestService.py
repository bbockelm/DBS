#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008
"""
REST service implementation
"""
from services.rest.Resource import Resource
from model.ql.ql import *

#
# An example of underlying model, the TestModel class implements basic methods to be used by
# Resource class to provide a REST services.
#
class TestModel:
    def __init__(self):
        self.data="DATA"
    def fetch(self,query,**kwargs):
        return {'query':query,'result':self.data}
#    def to_xml(self):
#        return self.data+" to XML"
#    def to_json(self):
#        return self.data+" to JSON"
#    def to_html(self):
#        return self.data+" to HTML"

class RestService(Resource):
    # expose all methods of RestService into public
    exposed = True
    # define internal class to be used by Resource.
#    _source_class=TestModel
    _source_class=QL
    _verbose = 1
    _model = None
    # define internal url used by Resource
    # to be defined by DDRestServer at run time
    _url   = ""
    _host  = "" 
    _fUrl  = ""
    _mUrl  = ""
    _dbs   = ""
    _dbsApi= ""
    def GET(self,*args,**kwargs):
        return self.handle_GET(args,kwargs)
    def POST(self,*args,**kwargs):
        return self.handle_POST(args,kwargs)
    def PUT(self,*args,**kwargs):
        return self.handle_PUT(args,kwargs)
    def DELETE(self,*args,**kwargs):
        return self.handle_DELETE(args,kwargs)

