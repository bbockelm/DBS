from Framework import Controller
from Framework.PluginManager import DeclarePlugin
from cherrypy import expose

from DDServer import *

#DeclarePlugin ("/Controllers/DataDiscovery/DDServer", DDServer, {"baseUrl": "/"})
DeclarePlugin ("/Controllers/DataDiscovery/DDServer", DDServer, {"baseUrl": "/DDServer"})
