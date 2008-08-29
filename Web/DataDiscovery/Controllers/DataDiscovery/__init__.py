from Framework import Controller
from Framework.PluginManager import DeclarePlugin
from cherrypy import expose

from services.dd.DDServer import *
from services.rest.DDRestServer import *

DeclarePlugin ("/Controllers/DataDiscovery/DDServer", DDServer, {"baseUrl": "/DDServer"})
DeclarePlugin ("/Controllers/DataDiscovery/DDRestServer", DDRestServer, {"baseUrl": "/services"})
