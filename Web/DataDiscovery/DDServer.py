#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
DBS data discovery server module.
"""

# system modules
import os, string, logging, types, time, socket, socket, urlparse, random, urllib, difflib
import smtplib, tempfile
import xml.sax, xml.sax.handler
from   xml.sax.saxutils import escape

# Cheetah template modules
from   Cheetah.Template import Template

# CherryPy server modules
import cherrypy 
from   cherrypy    import expose
from   cherrypy    import tools
#from   cherrypy.lib import profiler

# DBS  modules
from   DDUtil      import *
from   DDConfig    import *
from   DDHelper    import *
from   Templates   import *
from DDParamServer import *

#from   DDLucene  import *
# load DBS history tables module
try:
    from   DDTables  import *
except:
    print "WARNING! Cannot load DDTables, your persistent history will be turned off"
    pass

# support for SSL
try:
    import OpenSSL
except:
    pass

# webtools framework
from Framework import Controller
from Framework.PluginManager import DeclarePlugin

from Tools.SecurityModuleCore import encryptCookie, decryptCookie
from Tools.SecurityModuleCore import SecurityDBApi
from Tools.SecurityModuleCore.SecurityDBApi import SecurityDBApi
from Framework import Context
from Framework.Logger import Logger

from Tools.SecurityModuleCore import SecurityToken, RedirectToLocalPage, RedirectAway, RedirectorToLogin
from Tools.SecurityModuleCore import Group, Role, NotAuthenticated, FetchFromArgs
from Tools.SecurityModuleCore import is_authorized, is_authenticated, has_site
from Tools.Functors import AlwaysFalse

class DDServer(DDLogger,Controller): 
    """
       DBS Data discovery server class.
       It uses CherryPy web application framework 
       http://www.cherrypy.org
       to run the server. It's easy as:

       ::
           import cherrypy
           class HelloWorld(object):
              def index(self):
                  return "Hello World!"
              index.exposed = True

           cherrypy.quickstart(HelloWorld())

       The services can be easily pluggen by writing class methods which returns
       either HTML constructed page or simple string. The methods becomes visible at
       server if it's exposed (see above example).

       All pages are constructed using Cheetah template package.
       http://www.cheetahtemplate.org

       All methods are wrapped in try/catch blocks. In the case of any error
       exception captured passed to L{sendErrorReport} method.
    """
    def __init__(self,context="",verbose=0,profile=0):
        """
           DDServer constructor. It takes only one optional argument.
           Initialize L{DBSHelper} with default DBS instance "MCGlobal/Writer".
           Keep cache of DBS instances and current selection of (dbs,site,app,primD,tier).
           @type  verbose: boolean or integer 
           @param verbose: verbosity level 
           @rtype : none
           @return: none 
        """
#        self.p = profiler.Profiler(os.getcwd())
        self.ddConfig  = DDConfig()
        DDLogger.__init__(self,self.ddConfig.loggerDir(),"DDServer",verbose)
        setSQLAlchemyLogger(super(DDServer,self).getHandler(),super(DDServer,self).getLogLevel())
        setCherryPyLogger(super(DDServer,self).getHandler(),super(DDServer,self).getLogLevel())
        self.securityApi=""
        try:
            if context:
               context.OptionParser ().add_option("-v","--verbose",action="store",type="int", 
               default=0, dest="verbose",help="specify verbosity level, 0-none, 1-info, 2-debug")
               self.securityApi=SecurityDBApi(context)
            Controller.__init__ (self, context, __file__)
            self.config=self.setConfig(base="DDServer")
        except:
            if verbose:
                printExcept()
            pass
#        self.lucene = DDLucene(verbose)
        self.pServer= DDParamServer(server="edge.fnal.gov:8888",verbose=verbose)
# ProdRequest URL https://cmsdoc.cern.ch/cms/test/aprom/DBS/prodrequest/ProdRequest/getHome
        self.prodRequestServer= DDParamServer(server="cmslcgco01.cern.ch:8030",verbose=verbose)
        self.phedexServer= DDParamServer(server="cmsdoc.cern.ch",verbose=verbose)
        self.dbs  = DBSGLOBAL
        self.baseUrl = ""
        self.topUrl= ""
        self.mastheadUrl = self.ddConfig.masthead()
        self.footerUrl   = self.ddConfig.mastfooter()
        self.site = ""
        self.app  = ""
        self.primD= ""
        self.tier = ""
        self.helper     = DDHelper(self.dbs,self.ddConfig.iface(),verbose,html=1)
        self.dbsdls     = self.helper.getDbsDls()
        self.dbsList    = self.dbsdls.keys()
        self.dbsList.sort()
        try:
           self.dbsList.remove(DBSGLOBAL)
        except:
           pass
        self.dbsList = [DBSGLOBAL]+self.dbsList
        self.dbsTime    = 0
        self.dlsTime    = 0
        self.htmlTime   = 0
#        self.topHTML    = ""
#        self.bottomHTML = ""
        self.verbose    = verbose
        self.profile    = profile
        self.dbsDict    = {}
        self.userMode   = 'user'
        self.timer      = self.timerForSummary = time.time()
        self.sumPage    = ""
        self.firstSearch=1
        self.quiet      = 0
#        self.siteDict   = {}
#        self.host       = urlparse.urljoin(os.environ['DBSDD'],"discovery")
#        self.host       = os.environ['DBSDD']
        try:
            self.hostname = socket.gethostbyaddr(socket.gethostname())[0]
        except:
            self.hostname = 'localhost'
            pass
        self.port       = 8003
        for line in open('CherryServer3.conf').readlines():
            if string.find(line,'server.socketPort')!=-1:
               self.port=string.strip(string.split(string.replace(line,'\n',''),'=')[1])
               break
        try:
            self.dbsdd = self.ddConfig.url()
            if self.verbose:
               print "Read from config"
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            self.dbsdd = 'http://'+self.hostname+":"+str(self.port)
            print "Read from hostname and port"
        if os.environ.has_key('DBSDD'):
           self.dbsdd = os.environ['DBSDD']
        print "DDServer URL '%s'"%self.dbsdd
        self.formDict   = {
                           'menuForm': ("","","","","",""), # (msg,dbsInst,site,app,primD,tier)
                           'siteForm': ("",""), # (dbsInst,site)
                           'searchForm': DBSGLOBAL # (dbsInst,)
                          }
        self.sectionDict={ 
               'Algorithm': ['AppExecutable','AppVersion','AppFamily'],
               'Run/Lumi' : ['Runs','LumiSection'],
               'Files'    : ['Files','Branch'],
               'Datasets' : ['PrimaryDataset','ProcessedDataset','AnalysisDataset'],
               'Storage'  : ['StorageElement','Block'],
               'Tier'     : ['DataTier'],
               'Description' : ['MCDescription','TriggerPathDescription','PrimaryDatasetDescription'],
              }
        self.sectionDict2={ 
               'algo'    : ['AppExecutable','AppVersion','AppFamily'],
               'runLumi' : ['Runs','LumiSection'],
               'files'   : ['Files','Branch'],
               'dataset' : ['PrimaryDataset','ProcessedDataset','AnalysisDataset'],
               'storage' : ['StorageElement','Block'],
               'tier'    : ['DataTier'],
               'desc'    : ['MCDescription','TriggerPathDescription','PrimaryDatasetDescription'],
              }
        self.writeLog("DDServer init")

    def readyToRun(self):
        opts=self.context.CmdLineArgs().opts
        self.baseUrl = opts.baseUrl
#        self.baseUrl = self.context.CmdLineArgs ().opts.baseUrl
        if self.baseUrl[-1]!="/": self.baseUrl+="/"
#        self.mastheadUrl=self.baseUrl+"sitedb/Common/masthead"
#        self.footerUrl=self.baseUrl+"sitedb/Common/footer"
        self.mastheadUrl=self.baseUrl+"Common/masthead"
        self.footerUrl=self.baseUrl+"Common/footer"
        self.topUrl=self.baseUrl+"DDServer/"
        try:
           self.verbose=opts.verbose
           self.helper.setVerbose(self.verbose)
        except:
           pass
        self.context.Logger ().message ("Creating DBS Data Discovery roles and groups")
        securityApi = self.context.SecurityDBApi ()
        securityApi.api.addRole ("DBS admin")
        securityApi.api.addGroup ("dbs_admin")    

    def redirectPage(self):
        page = self.genTopHTML()
        nameSpace = { 'localtime':time.asctime() }
        t = templateRedirect(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    redirectPage.exposed=True     
        
    def setQuiet(self):
        self.helper.setQuiet()
        self.quiet=1
        
    def setContentType(self,type):
        """
           Set CherryPy Content-Type to provided type
           @type type: string
           @param type: type of application, "text/xml" or "text/html"
           @rtype: none
           @return: none
        """
        cherrypy.response.headers['Expires']="Thu, 15 Apr 2010 20:00:00 GMT"
#        cherrypy.response.headers['Last-Modified']=time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime())
        cherrypy.response.headers['Cache-Control']="no-store, no-cache, must-revalidate, max-age=315360000"
        cherrypy.response.headers['Cache-Control']="post-check=0, pre-check=0"
        
        if  int(string.split(cherrypy.__version__,".")[0])==3:
            if type=="xml":
               cherrypy.response.headers['Content-Type']='text/xml'
            elif type=="rss":
               cherrypy.response.headers['Content-Type']='application/rss+xml'
            else:
               cherrypy.response.headers['Content-Type']='text/html'
        elif int(string.split(cherrypy.__version__,".")[0])==2:
            if type=="xml":
               cherrypy.response.headerMap['Content-Type'] = "text/xml"
            elif type=="rss":
               cherrypy.response.headerMap['Content-Type'] = "application/rss+xml"
            else:
               cherrypy.response.headerMap['Content-Type'] = "text/html"

    def sendErrorReport(self,iMsg=""):
        """
           Send a complete report with provided msg. Capture internals of
           L{DDServer} and L{DBSHelper}.
           Use /usr/sbin/sendmail as mail agent.
           @type  msg: string 
           @param msg: message 
           @rtype : none
           @return: none
        """
        msg = ""
        if iMsg: msg+=iMsg
        msg+="Request internals:\n"
        if  int(string.split(cherrypy.__version__,".")[0])==3:
            msg+="Request from: %s:%s\n"%(cherrypy.request.remote.ip,cherrypy.request.remote.port)
            url =cherrypy.url()
        else:
            msg+="Request from: %s:%s\n"%(cherrypy.request.remote_host,cherrypy.request.remote_port)
            url=cherrypy.request.browser_url
        msg+="Request url : %s\n"%url
        msg+="\n\n"
        msg+="DDServer:\n"
        msg+="=======================\n"
        msg+="%s: %s\n"%("DBS Instance   ",self.dbs)
        msg+="%s: %s\n"%("site           ",self.site)
        msg+="%s: %s\n"%("application    ",self.site)
        msg+="%s: %s\n"%("primary dataset",self.primD)
        msg+="%s: %s\n"%("Data tier      ",self.tier)
        msg+="\n" # blank line separating headers from body
        msg+="DBSHelper:\n"
        msg+="==========\n"
        msg+="%s: %s\n"%("DBS Instance   ",self.helper.dbsInstance)
        try:
           msg+="%s: %s\n"%("engine         ",self.helper.dbsDB)
        except:
           pass
        msg+="%s: %s\n"%("dbsApi         ",self.helper.dbsApi)
        msg+="%s: %s\n"%("dbsDLS         ",self.helper.dbsDLS)
        msg+="%s: %s\n"%("dlsType        ",self.helper.dlsType)
        msg+="%s: %s\n"%("dlsEndpoint    ",self.helper.endpoint)
        sendEmail(msg)
        
    def helperInit(self,dbsInst):
        """
           Initialize L{DBSHelper} with given DBS instances
           @type  dbsInst: string 
           @param dbsInst: DBS instances 
           @rtype : none
           @return: none
        """
        self.dbs = dbsInst
        self.helper.setDBSDLS(dbsInst)

    def genTopHTML(self,intro=False,userMode='user',onload=''):
        """
           Generates top structure of HTML page using Cheetah template.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        nameSpace = {
                     'host'        : self.dbsdd,
#                     'baseUrl'     : self.baseUrl,
                     'baseUrl'     : self.topUrl,
                     'mastheadUrl' : self.mastheadUrl,
                     'footerUrl'   : self.footerUrl,
                     'title'       : 'DBS Data Discovery Page',
                     'dbsGlobal'   : DBSGLOBAL,
                     'userMode'    : userMode,
                     'onload'      : onload
                    }
        page = templateTop(searchList=[nameSpace]).respond()
        if intro:
           page+=templateIntro().respond()
        return page

    def genResultsHTML(self):
        """
           Generates results structure of HTML page using Cheetah template.
           @type  self: class object
           @param self: none
           @rtype : none 
           @return: returns HTML code 
        """

        nameSpace = { 'localtime':time.asctime() }
        t = templateResults(searchList=[nameSpace]).respond()
        return str(t)

    def genBottomHTML(self):
        """
           Generates top structure of HTML page using Cheetah template.
           @type  self: class object
           @param self: none
           @rtype : none 
           @return: returns HTML code 
        """

        nameSpace = { 'localtime':time.asctime() }
        t = templateBottom(searchList=[nameSpace]).respond()
        return str(t)

    def index(self,dbsInst=DBSGLOBAL,userMode='user',**kwargs): 
        """
           Construct start up page by invoking L{init} call.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="resetUserNav();")
            page+= self.whereMsg('Navigator',userMode)
            auto=0
            if kwargs.has_key('auto') and kwargs['auto']=='on':
               auto=1
            userNav = self.genEmptyUserNavigator(dbsInst,userMode,auto)
            t = templateMenuNavigator(searchList=[{'userNavigator':userNav}]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in index function")
            pass
            return str(t)
    index.exposed = True 

    def errorReport(self,msg):
        """
           Error handle routine.
           @type msg: string
           @param msg: input text
           @rtype: string
           @return: returns HTML code
        """
        if  int(string.split(cherrypy.__version__,".")[0])==3:
            port=cherrypy.request.remote.port
            host=cherrypy.request.remote.ip
            url =cherrypy.url()
        else:
            port=cherrypy.request.remote_port
            host=cherrypy.request.remote_host
            url =cherrypy.request.browser_url
        nameSpace = {
                     'msg':getExceptionInHTML(),
                     'port': port,
                     'host': host,
                     'url' : url 
                    }
        t = templateERROR(searchList=[nameSpace]).respond()
        self.sendErrorReport(msg+"\n"+getExcept())
        return str(t)

    def glossary(self):
        """
           Generate DBS glossary page
        """
        nameSpace = {}
        t = templateGlossary(searchList=[nameSpace]).respond()
        return str(t)
        
    def getUserName(self):
        """
           Get userName from stored cookie, should be run within WEBTOOLS framework
        """
        userName=""
        try:
            cookie=cherrypy.request.cookie
            userName = decryptCookie (cookie["dn"].value, self.securityApi)
        except:
            if self.verbose:
               self.writeLog(getExcept())
            pass
        return userName

    def getDN(self,userName):
        """
           Get user DN, for that we use WEBTOOLS SecurityAPI.
        """
        return self.securityApi.getDNFromUsername(userName)

    ################## Menu init methods
    def _navigator(self,dbsInst=DBSGLOBAL,userMode="user",**kwargs):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="resetUserNav();")
            page+= self.whereMsg('Navigator',userMode)
            auto=0
            if kwargs.has_key('auto') and kwargs['auto']=='on':
               auto=1
            userNav = self.genEmptyUserNavigator(dbsInst,userMode,auto)
            t = templateMenuNavigator(searchList=[{'userNavigator':userNav}]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in navigator init function")
            pass
            return str(t)
    _navigator.exposed=True

    def makeSectionDict(self,dbsInst):
        self.helperInit(dbsInst)
        out=""
        for section in self.sectionDict2.keys():
            tabOut=str(self.sectionDict2[section])
            colOut=""
            for tableName in self.sectionDict2[section]:
#                cols = self.helper.getTableColumns(tableName)
                cTemp = self.helper.getTableColumns(tableName)
                cols=[]
                for item in cTemp:
                    cols.append(item.encode('ascii'))
                if cols.count('ID'): cols.remove('ID')
                if cols.count('id'): cols.remove('id')
                colOut+=str(cols)+","
            colOut=colOut[:-1]
            out+="""var %sList={'tableNames':%s,'columnNames':[%s]};\n"""%(section,tabOut,colOut)
        return out

    def _finder(self,userMode="user"):
        try:
            page        = self.genTopHTML(intro=False,userMode=userMode)
            dbsList     = list(self.dbsList)
            dbsInst     = DBSGLOBAL
            sectionDicts= self.makeSectionDict(dbsInst)

            # make auto-completion forms for alias lookup
            tag="kw_alias_lookup"
            nameSearch={'tag':tag,'inputId':tag,'inputName':tag,'size':50,'userMode':userMode,'dbsInst':'','table':'','column':'','label':'My query alias:','zIndex':9000,'method':'getAliasesFromHistoryDB'}
            t = templateAutoComplete(searchList=[nameSearch]).respond()
            myAlias=str(t)

            nameSearch={'host':self.dbsdd,'dbsList':dbsList,'dbsInst':DBSGLOBAL,'userMode':userMode,'sectionDicts':sectionDicts,'myAlias':myAlias}
            t = templateMenuFinder2(searchList=[nameSearch]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in finder init function")
            pass
            return str(t)
    _finder.exposed=True

    def _finder_v1(self,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="ResetFinder('%s')"%DBSGLOBAL)
            searchForm=self.searchForm(DBSGLOBAL,userMode)
            nameSearch={'host':self.dbsdd,'searchForm':searchForm,'userMode':userMode}
            t = templateMenuFinder(searchList=[nameSearch]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in finder init function")
            pass
            return str(t)
    _finder.exposed=True

    def _config(self,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="registerAjaxLucene();ajaxGetLuceneParams()")
            page+=self.whereMsg('Config parameter search',userMode)
            t = templateSearchEngine(searchList=[{}]).respond()
            luceneForm=str(t)
            nameSearch={'host':self.dbsdd,'luceneForm':luceneForm}
            t = templateMenuConfig(searchList=[nameSearch]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in config init function")
            pass
            return str(t)
    _config.exposed=True

    def _analysis(self,dbsInst=DBSGLOBAL,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= self.whereMsg('Analysis dataset search',userMode)

            # make auto-completion forms for ads name and def name
            nameSearch={'tag':'ads_name','inputId':'ads_name','inputName':'ads_name','size':100,'userMode':userMode,'dbsInst':dbsInst,'table':'AnalysisDataset','column':'Name','label':'Name:','zIndex':9000,'method':'getTableColumn'}
            t = templateAutoComplete(searchList=[nameSearch]).respond()
            adsName=str(t)
            nameSearch={'tag':'adsd_name','inputId':'adsd_name','inputName':'adsd_name','size':100,'userMode':userMode,'dbsInst':dbsInst,'table':'AnalysisDSDef','column':'Name','label':'Definition name:','zIndex':8000,'method':'getTableColumn'}
            t = templateAutoComplete(searchList=[nameSearch]).respond()
            adsDefName=str(t)

            tierList = self.helper.getDataTiers()
            dbsList=list(self.dbsList)
            dbsList.remove(dbsInst)
            dbsList=[dbsInst]+dbsList
            nameSearch={'tierList':tierList,'userMode':userMode,'dbsList':dbsList,'adsName':adsName,'adsDefName':adsDefName,'host':self.dbsdd}
            t = templateMenuAnalysis(searchList=[nameSearch]).respond()
            page+= str(t)

            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in analysis init function")
            pass
            return str(t)
    _analysis.exposed=True

    def _rss(self,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="resetUserNav();")
            page+= self.whereMsg('RSS feeds',userMode)
            emptyList=[]    
            nameSearch= {
                          'dbsInst'     : DBSGLOBAL,
                          'userMode'    : userMode,
                          'dbsList'     : self.dbsList,
                          'softReleases': emptyList,
                          'primTypes'   : emptyList,
                          'primDatasets': emptyList,
                          'style'       : "width:200px",
                        }
            t = templateMenuRss(searchList=[nameSearch]).respond()
            page+= str(t)

#            t = templateMenuRss(searchList=[]).respond()
#            page+= str(t)
#            page+= self.getRss(ajax=0,userMode=userMode)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in rss init function")
            pass
            return str(t)
    _rss.exposed=True

    def rssGenerator(self,primD,app="Any",dbsInst=DBSGLOBAL,userMode="user",**kwargs):
        self.setContentType('rss')
        if string.lower(app)   =="all" or string.lower(app)   =="any": app="*"
        if string.lower(primD) =="all" or string.lower(primD) =="any": primD="*"
        try:
            if primD=="*" and app=="*":
               p="In order to subscribe to RSS feeds you must choose either Primary dataset or Release"
               return self.genTopHTML()+p+self.genBottomHTML()
        
            # get primDesc and creation date
            primPubDate,primDesc=self.helper.getPrimDetailsForRSS(prim=primD)
            # for given primary, generate procList
            self.helperInit(dbsInst)
            procList = self.helper.getProcDSForRss(prim=primD,rel=app)
            nameSearch={
                        'host'       : self.dbsdd,
                        'prim'       : primD,
                        'release'    : app,
                        'primDesc'   : primDesc,
                        'dbsInst'    : dbsInst,
                        'primPubDate': primPubDate,
                        'buildDate'  : time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime()),
                        'procList'   : procList,
                        'userMode'   : userMode
                       }
            t = templateRSS(searchList=[nameSearch]).respond()
            page=str(t)
        except:
            t=self.errorReport("Fail in rss init function")
            page=str(t)
            pass
        if self.verbose==2:
           self.writeLog(page)
        return page
    rssGenerator.exposed=True

    def _siteSearch(self,userMode="expert",**kwargs):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= self.whereMsg('Site search',userMode)
            dbsInst,site=self.formDict['siteForm']
            auto=0
            if kwargs.has_key('auto') and kwargs['auto']=="on":
               auto=1
            siteForm=self.siteForm(dbsInst,site,userMode,auto)
            nameSpace = {
                         'host'         : self.dbsdd,
                         'userMode'     : userMode,
                         'siteForm'     : siteForm,
                         'dbsList'      : self.dbsList,
                        }
            t = templateMenuSiteSearch(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in siteSearch init function")
            pass
            return str(t)
    _siteSearch.exposed=True

    def _history(self,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= self.whereMsg('History',userMode)
            t = templateMenuHistory(searchList=[]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in history init function")
            pass
            return str(t)
    _history.exposed=True

    def _help(self,userMode="user",**kwargs):
        try:
            section=""
            if kwargs.has_key('section'):
               section="""<script type="text/javascript">\nshowResMenu('%s',['help_intro', 'help_glossary', 'help_wild-card', 'help_like', 'help_regexp', 'help_resources', 'help_feedback', 'help_refs', 'help_browser'])</script>"""%kwargs['section']
             
            page = self.genTopHTML(intro=False,userMode=userMode)
            nameSpace = {
                         'host'         : self.dbsdd,
                         'userMode'     : userMode,
                         'glossary'     : self.glossary(),
                         'section'      : section,
                        }
            t = templateMenuHelp(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in help init function")
            pass
            return str(t)
    _help.exposed=True

    def _contact(self,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= self.whereMsg('Contact information',userMode)
            nameSpace = {
                         'host'         : self.dbsdd,
                         'userMode'     : self.userMode,
                        }
            t = templateMenuContact(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in contact init function")
            pass
            return str(t)
    _contact.exposed=True

    @is_authorized (Role("DBS admin"), Group("dbs_admin"), 
                    onFail=RedirectToLocalPage ("/redirectPage"))
    def _dbsExpert(self,dbsInst=DBSGLOBAL,userMode="dbsExpert"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= self.whereMsg('DBS expert page',userMode)
            dbsTables = self.helper.dbManager.getTableNames(dbsInst)
            dbsTables.sort()
            nameSpace = {
                         'dbsList'      : self.dbsList,
                         'dbsTables'    : dbsTables
                        }
            t = templateMenuDbsExpert(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in dbsExpert init function")
            pass
            return str(t)
    _dbsExpert.exposed=True

    def _runs(self,dbsInst=DBSGLOBAL,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="resetRunNav();")
            page+= self.whereMsg('Run search',userMode)
            nameSpace = {
                         'dbsList'      : self.dbsList,
                         'dbsInst'      : DBSGLOBAL,
                         'userMode'     : userMode,
                         'style'        : 'width:200px',
                        }
            t = templateMenuRuns(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in run search init function")
            pass
            return str(t)
    _runs.exposed=True

    ################## END OF init methods
    def whereMsg(self,msg,userMode):
        t = templateWhere(searchList=[{'where':'DBS discovery :: '+msg,'userMode':userMode}]).respond()
        return str(t)

    def searchHelper(self,keywords=""):
        """
           Helper function which invoke L{DBSHelper.search} to find out data based on input keywords.
           @type keywords: string
           @param keywords: keyword string, the keywords are separated by empty spaces
           @rtype: list
           @return: a list of [(primD,tier,App version, App family, App exe)]
        """
        oList = self.helper.search(keywords)
        return oList
        
    def genEmptyUserNavigator(self,dbsInst,userMode="user",auto=0,**kwargs):
        if auto:
           # auto-competion form for processed datasets
           nameSearch={'tag':'proc','inputId':'proc','inputName':'proc','size':'80','userMode':userMode,'dbsInst':dbsInst,'table':'Block','column':'Path','label':'','zIndex':9000,'method':'getTableColumn'}
           t = templateAutoComplete(searchList=[nameSearch]).respond()
           prdForm=str(t)
        else:
           prdForm="""<input type="text" name="proc" id="proc" size="80">"""

        page=""
        emptyList=[]
        dbsList=list(self.dbsList)
        dbsList.remove(dbsInst)
        dbsList=[dbsInst]+dbsList
        nameSearch={
                    'dbsInst'     : dbsInst,
                    'host'        : self.dbsdd,
                    'userMode'    : userMode,
                    'dbsList'     : dbsList,
                    'groupList'   : emptyList,
                    'dataTypes'   : emptyList,
                    'softReleases': emptyList,
                    'primTypes'   : emptyList,
#                    'siteList'    : emptyList,
                    'siteDict'    : {},
                    'style'       : "width:200px",
                    'prdForm'     : prdForm,
                    'autocomplete': auto,
                   }
        t = templateUserNav(searchList=[nameSearch]).respond()
        page+= str(t)
        if self.verbose==2:
           self.writeLog(page)
        return page
    genEmptyUserNavigator.exposed=True 
    
    def genUserNavigator(self,dbsInst,userMode="user",ajax=0,**kwargs):
        page=""
        if  int(ajax)==1:
            self.setContentType('xml')
            page+="""<ajax-response><response type="element" id="kw_userNavigator">"""
        nameSearch={
                    'dbsInst'     : dbsInst,
                    'userMode'    : userMode,
                    'dbsList'     : self.dbsList,
                    'groupList'   : self.helper.getPhysicsGroups,
                    'dataTypes'   : self.helper.getDataTiers(),
                    'softReleases': self.helper.getSoftwareReleases(),
#                    'primDatasets': self.helper.getPrimaryDatasets(),
#                    'siteList'    : self.helper.getSites(), 
                    'siteDict'    : sortSitesByDomain(self.helper.getSites()), 
                    'style'       : "width:200px",
                   }
        t = templateUserNav(searchList=[nameSearch]).respond()
        page+= str(t)
        if  int(ajax)==1:
            page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    genUserNavigator.exposed=True 

    def searchForm(self,firstDBS=DBSGLOBAL,userMode='user'):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """

        if self.ddConfig.iface()=='cgi': searchFunc="javascript:ajaxSearch()"
        else: searchFunc="javascript:ResetAllResults();ajaxFinderSearch('%s')"%userMode

        # FIXME, TODO: firstDBS should be passed here
        if not firstDBS: firstDBS=DBSGLOBAL

        sectionList=self.sectionDict.keys()
        sectionList.sort()
        tableList = self.sectionDict['Algorithm']
        tableList.sort()
        
        nameSpace={'id':1,'sectionList':sectionList,'tableList':tableList}
        t = templateSelectLine(searchList=[nameSpace]).respond()
        selectLine = str(t)

        # make auto-completion forms for alias lookup
        tag="kw_alias_lookup"
        nameSearch={'tag':tag,'inputId':tag,'inputName':tag,'size':50,'userMode':userMode,'dbsInst':'','table':'','column':'','label':'My query alias:','zIndex':9000,'method':'getAliasesFromHistoryDB'}
        t = templateAutoComplete(searchList=[nameSearch]).respond()
        myAlias=str(t)

        nameSpace = {
                     'firstDBS'       : firstDBS,
                     'userMode'       : userMode,
                     'dbsList'        : self.dbsList,
                     'searchFunction' : searchFunc,
                     'selectLine'     : selectLine,
                     'dbsGlobal'      : DBSGLOBAL,
                     'myAlias'        : myAlias,
                    }
        t = templateSearchTable(searchList=[nameSpace]).respond()
        page = str(t)
        self.firstSearch=0
        return page
        
    def getDBSDict(self,dbsInst):
        """
            Read dbsDict file in local directory which contains
            a JS dictionary for pull down menu.
        """
        name = string.split(dbsInst,"/")[0]
        dbs  = "dbsDict.%s"%name
        f = open(dbs,'r')
        self.dbsDict = string.strip(f.read())
        f.close()
        return self.dbsDict
           
    def genNavigatorMenuDict(self,dbsInst,**kwargs):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="navigatorDict">"""
        endAjaxMsg="</response></ajax-response>"
        try:
            page+="navDict="+self.getDBSDict(dbsInst)
        except:
            t=self.errorReport("Fail in genNavigatorMenuDict function")
            page+=str(t)
            pass
        page+=endAjaxMsg
        if self.verbose==2:
           self.writeLog(page)
        return page
    genNavigatorMenuDict.exposed = True

    def adminDataset(self,dbsInst,dataset,userMode,siteList,**kwargs):
        page = self.genTopHTML(userMode=userMode)
        page+= self.whereMsg('Navigator :: Results :: list of datasets :: admin dataset',userMode)

        # auto-competion form for processed datasets
#        nameSearch={'tag':'blockName','inputId':'blockName','inputName':'blockName','size':'100','userMode':userMode,'dbsInst':dbsInst,'table':'Block','column':'Name','label':'','zIndex':9000,'method':'yuiGetBlocksForDataset'}
#        t = templateAutoComplete(searchList=[nameSearch]).respond()
#        blkForm=str(t)
        
        dbsList=list(self.dbsList)
        dbsList.remove(dbsInst)
        dbsList=[dbsInst]+dbsList
        site="*"
        if kwargs.has_key("site"):
           site=kwargs["site"]
        blkList=self.helper.getBlocksFromSite(site=site,datasetPath=dataset)
        siteDict=sortSitesByDomain(siteList[1:-1].replace(" ","").replace("'","").split(","))
        nameSpace={
                  'dbsInst' : dbsInst,
                  'dataset' : dataset,
                  'dbsList' : dbsList,
                  'dbsListOrig': self.dbsList,
                  'blkList' : blkList,
                  'siteDict': siteDict,
                  'style'   : "",
#                  'blkForm' : blkForm,
                  'userMode': userMode,
                  }
        t = templateAdminDatasets(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    adminDataset.exposed=True

    def adminTask(self,dbsInst,userMode,dataset,**kwargs):
        page = self.genTopHTML(userMode=userMode)
        # analyse input kwargs
        # lookup for lfn and find out a list
        if kwargs.has_key('lfn'):
           lfnList=[]
           if kwargs['lfn'].lower()=="all":
              lfns=self.helper.getLFNs(dbsInst,blockName="",dataset=dataset,run="")
           elif kwargs['lfn'].lower().find("like")!=-1:
              lfn=kwargs['lfn'].split("like:")[1]
              lfns=self.helper.getLFNs(dbsInst,blockName="",dataset=dataset,lfn="%%%s%%"%lfn)
           else:
              lfns=self.helper.getLFNs(dbsInst,blockName="",dataset=dataset,lfn=lfn)
           for item in lfns:
              lfnList.append(item[0])
           kwargs['lfnList']={'lfn':lfnList}
           del kwargs['lfn'] # delete lfn key-pair from our dictionary

        skipList=['submit','title','submit request']
        input = str(templateXML(searchList=[{'kwargs':kwargs,'skipList':skipList}]).respond())
        xmlOutput=urllib.unquote(input).replace("<","&lt;").replace(">","&gt;<br />").replace("&lt;/","<br/>&lt;/").replace("&lt;","<b>&lt;").replace("&gt;","&gt;</b>")
        nameSpace = {'kwargs':kwargs,'skipList':skipList,'xmlOutput':xmlOutput,'userMode':userMode}
        t = templateAdminMigration(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    adminTask.exposed=True
        
    def sendAdminRequest(self,*args,**kwargs):
        skipList=['submit','title','submit request']
        xml=str(templateXML(searchList=[{'kwargs':kwargs,'skipList':skipList}]).respond())
        print "\n\nsendAdminRequest\n",xml
        page = self.genTopHTML(userMode='expert')
        page+="<h3>Your request has been send to DBS</h3>"
        page+="<p>Confirmation number: XXXYYY</p>"
        page+="""<p>You can visit this <a href="">page</a> to see and monitor progress of your request"""
        page+= self.genBottomHTML()
        return page
    sendAdminRequest.exposed=True

    def showProcDatasets(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",primType="*",date="*",userMode='user'):
        """
           Get all processed datasets for given set of input parameters
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @type  site: string
           @param site: user selection of the site, default "All"
           @type  app: string
           @param app: user selection of the application, default "*"
           @type  primD: string
           @param primD: user selection of the primary dataset, default "*"
           @type  tier: string
           @param tier: user selection of the data tier, default "*"
           @rtype : string
           @return: returns HTML code
        """
        if type(proc) is not types.ListType and len(proc)>1:
           if  proc.find("*")!=-1 or proc.find("%")!=-1 or \
               proc.lower().find("regexp:")!=-1 or proc.lower().find("like:")!=-1:
               # we got a pattern
               if proc.lower().find("regexp:")!=-1:
                  # we got regular expression pattern
                  op,pat=proc.split("regexp:")
                  if self.dbManager.dbType[self.dbsInstance]=='oracle':
                     wClause=" REGEXP_LIKE(Path,:p0) "
                  else:
                     wClause=" Path REGEXP :p0 "
                  bDict={'p0':"%s"%pat}
                  nDatasets=self.helper.countBlocks(whereClause="",site=site,explicitWClause=wClause,explicitDict=bDict)
               elif proc.lower().find("like:")!=-1:
                  nDatasets=self.helper.countBlocks(proc,site)
                  wClause,bDict=parseKeywordInput(proc,"tblk.Path")
               else:
                  # we got a pattern
                  nDatasets=self.helper.countBlocks("like:%s"%proc,site)
                  wClause=" tblk.Path LIKE :p0 "
                  bDict={'p0':"%s"%proc.replace('*','%')}
               proc=self.helper.getDatasetPathFromMatch(wClause,bDict=bDict,site=site)
           else:
               if proc[0]!="/":
                  page=self.genTopHTML()
                  msg ="Dataset name should be in a form /primary/processed/tier<br />"
                  msg+="You provided '%s'<br />"%proc
                  msg+="If you need wild-card search please use *%s*<br />"%proc
                  page+=msg
                  page+=self.genBottomHTML()
                  return page
        try:
            self.helperInit(dbsInst)
            page = self.genTopHTML(userMode=userMode)
            page+= self.whereMsg('Navigator :: Results :: list of datasets',userMode)
            page+= self.genResultsHTML()
            page+= "<pre>"
            if  type(proc) is not types.ListType and len(proc) and proc!="*":
                page+=proc+"\n"
            else:
                procList = self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc,site=site,primType=primType,date=date,userMode=userMode,fromRow=0,limit=0,count=0)
                procList.sort()
                for procD in procList:
                    page+=procD+"\n"
            page+= "</pre>"
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in showProcDatasets function")
            pass
            return str(t)
    showProcDatasets.exposed=True

    def getURL(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",hist=""): 
        # add URL link to the page
        siteHTML=urllib.quote(site)
        if site=="*":
           siteHTML='All'
        tierHTML=urllib.quote(tier)
        if tier=="*":
           tierHTML='All'
        histHTML=urllib.quote(hist+"""<hr class="dbs" />""")
        groupHTML=urllib.quote(group)
        appHTML=urllib.quote(app)
        primHTML=urllib.quote(primD)
        procHTML=urllib.quote(proc)
        url="""%s/getData?dbsInst=%s&amp;site=%s&amp;group=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;hist=%s&amp;_idx=0&amp;ajax=0"""%(self.dbsdd,dbsInst,siteHTML,groupHTML,appHTML,primHTML,tierHTML,procHTML,histHTML)
        page="""<hr class="dbs" /><p>For a bookmark to this data, use</p><a href="%s">%s</a>"""%(url,splitString(url,80,'\n'))
        return page

    def getDatasetList(self,group="*",app="*",prim="*",tier="*",proc="*",site="*",primType="*",date="*",userMode='user',fromRow=0,limit=0,count=0):
        """
           Call different APIs for given list of app/prim/tier/proc. Return a list of processed
           datasets.
        """
        if string.lower(tier) =="all" or string.lower(tier)=="any": tier="*"
        if string.lower(site) =="all" or string.lower(site)=="any": site="*"
        if string.lower(app)  =="all" or string.lower(app)=="any": app="*"
        if string.lower(group)=="all" or string.lower(group)=="any": group="*"
        if string.lower(prim) =="all" or string.lower(prim)=="any": prim="*"
        if string.lower(date) =="all" or string.lower(date)=="any": date="*"
        return self.helper.listProcessedDatasets(group,app,prim,tier,proc,site,primType,date,userMode,fromRow,limit,count)

    def getMatch(self,table,column,val,row=0,limit=0):
        pList=[]
        whereDict={}
#        whereDict['%s.%s'%(table,column)]="%"+val.replace('*','').replace('%','')
        whereDict['%s.%s'%(table,column)]=val.replace('*','%')
        for item in self.helper.getTableColumn(table,column,row,limit,whereDict):
            pList.append(item)
        return pList

    def getDataHelper(self,dbsInst,site="Any",group="*",app="*",primD="*",tier="*",proc="*",primType="*",date="*",hist="",_idx=0,ajax=1,userMode="user",pagerStep=RES_PER_PAGE,phedex=0,**kwargs): 
        """
           Main worker. It pass user selected information to the L{DBSHelper} and 
           form HTML representation of the data output.
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @type  site: string
           @param site: user selection of the site, default "All"
           @type  app: string
           @param app: user selection of the application, default "*"
           @type  primD: string
           @param primD: user selection of the primary dataset, default "*"
           @type  tier: string
           @param tier: user selection of the data tier, default "*"
           @rtype : string
           @return: returns HTML code
        """
#        t1=time.time()
        nDatasets=0
        pagerStep=int(pagerStep)
        if not proc: proc="*"
        if  type(proc) is types.ListType:
            proc_orig=proc
        else:
            proc_orig=urllib.quote(proc)
        if string.lower(tier) =="all" or string.lower(tier)=="any": tier="*"
        if string.lower(site) =="all" or string.lower(site)=="any": site="*"
        if string.lower(app)  =="all" or string.lower(app)=="any": app="*"
        if string.lower(group)=="all" or string.lower(group)=="any": group="*"
        if string.lower(primD)=="all" or string.lower(primD)=="any": primD="*"
        if string.lower(date) =="all" or string.lower(date)=="any": date="*"
        if string.lower(primType)=="all" or string.lower(primType)=="any": primType="*"
        if type(proc) is not types.ListType and (string.lower(proc)=="any" or string.lower(proc)=="any"): proc="*"
#        if type(proc) is not types.ListType and len(proc)>1 and (proc[0]=="*" or proc[0]=="%"):

        if type(proc) is not types.ListType and len(proc)>1:
           if  proc.find("*")!=-1 or proc.find("%")!=-1 or \
               proc.lower().find("regexp:")!=-1 or proc.lower().find("like:")!=-1:
               if proc.lower().find("regexp:")!=-1:
                  # we got regular expression pattern
                  op,pat=proc.split("regexp:")
                  if self.dbManager.dbType[self.dbsInstance]=='oracle':
                     wClause=" REGEXP_LIKE(Path,:p0) "
                  else:
                     wClause=" Path REGEXP :p0 "
                  bDict={'p0':"%s"%pat}
                  nDatasets=self.helper.countBlocks(whereClause="",site=site,explicitWClause=wClause,explicitDict=bDict)
               elif proc.lower().find("like:")!=-1:
                  nDatasets=self.helper.countBlocks(proc,site)
                  wClause,bDict=parseKeywordInput(proc,"tblk.Path")
               else:
                  # we got a pattern
                  nDatasets=self.helper.countBlocks("like:%s"%proc,site)
                  wClause=" tblk.Path LIKE :p0 "
                  bDict={'p0':"%s"%proc.replace('*','%')}
               proc=self.helper.getDatasetPathFromMatch(wClause,row=_idx*pagerStep,limit=pagerStep,bDict=bDict,site=site)
           else:
               if proc[0]!="/":
                  page=self.genTopHTML()
                  msg ="Dataset name should be in a form /primary/processed/tier<br />"
                  msg+="You provided '%s'<br />"%proc
                  msg+="If you need wild-card search please use *%s*<br />"%proc
                  page+=msg
                  page+=self.genBottomHTML()
                  return page
           
        self.dbsTime=self.dlsTime=0
        page=""
        className="show_inline"
#        className="hide"
#        if int(_idx)==0:
#           className="show_inline"
            
        
#        print "Init step",time.time()-t1

        self.helperInit(dbsInst)
        self.dbs  = dbsInst
        self.site = site
        self.app  = app
        self.primD= primD
        self.tier = tier
        

        primaryDataset=primD
        dataTier = tier
        appPath = app
        softRel = app
        if app[0]=="/":
           softRel=app.split("/")[1]
        id=0
        prevPage=""
        oldDataset=oldTotEvt=oldTotFiles=oldTotSize=0
        if  type(proc) is types.ListType:
            if not nDatasets:
               nDatasets = len(proc)
            datasetsList = proc
#            i=int(_idx)*pagerStep
#            j=i+pagerStep
#            datasetsList=proc[i:j]
            proc=proc_orig
        else:
#            ttt=time.time()
            nDatasets = self.getDatasetList(group=group,app=appPath,prim=primD,tier=tier,proc=proc,site=site,primType=primType,date=date,count=1)
#            print "Time to get all dataset",time.time()-ttt
#            ttt=time.time()
            datasetsList = self.getDatasetList(group=group,app=appPath,prim=primD,tier=tier,proc=proc,site=site,primType=primType,date=date,userMode=userMode,fromRow=_idx*pagerStep,limit=pagerStep,count=0)
#            print "Time to get N datasets",time.time()-ttt

        # Construct result page
        rPage=""
        if nDatasets:
           rPage+="Result page:"

#        print "Paging step before loop",time.time()-t1
        # the progress bar for all results
        if _idx:
            rPage+="""<a href="getData?dbsInst=%s&amp;site=%s&amp;group=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;primType=%s&amp;date=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s">&#171; Prev</a> """%(dbsInst,site,group,app,primD,tier,proc,primType,date,_idx-1,userMode,pagerStep)
        tot=_idx
        for x in xrange(_idx,_idx+GLOBAL_STEP):
            if nDatasets>x*pagerStep:
               tot+=1
        for index in xrange(_idx,tot):
           ref=index+1
           if index==_idx:
              ref="""<span class="gray_box">%s</span>"""%(index+1)
           rPage+="""<a href="getData?dbsInst=%s&amp;site=%s&amp;group=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;primType=%s&amp;date=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s"> %s </a> """%(dbsInst,site,group,app,primD,tier,proc,primType,date,index,userMode,pagerStep,ref)
#        if nDatasets>tot*pagerStep:
        if nDatasets>(_idx+1)*pagerStep:
           rPage+="""<a href="getData?dbsInst=%s&amp;site=%s&amp;group=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;primType=%s&amp;date=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s">Next &#187;</a>"""%(dbsInst,site,group,app,primD,tier,proc,primType,date,_idx+1,userMode,pagerStep)

        if _idx and _idx*pagerStep>nDatasets:
           return "No data found for this request"

#        print "Paging step before snapshot",time.time()-t1
        page+="""<div id="results_response_%s" class="%s">"""%(_idx,className)

        regList=[]
        bList=[]
        id=0

        page+=self.whereMsg('Navigator :: Results',userMode)
        # check which date has been passed and format it accordingly
        _date="Any"
        if date.lower()!="*":
           if date.find("_")!=-1:
              d1,d2=date.split("_")
              _date="%s<br/>%s"%(time.strftime("%Y/%m/%d",time.gmtime(int(d1))),time.strftime("%Y/%m/%d",time.gmtime(int(d2))))
           else:
              _date=time.strftime("%Y/%m/%d",time.gmtime(int(date)))
        # I re-use this dict for different templates
        _nameSpace = {
                     'nDatasets': nDatasets,
                     'datasetList': datasetsList,
                     'userMode' : userMode,
                     'dbsInst'  : dbsInst,
                     'dbsInstUrl': DBS_DLS_INST[dbsInst],
                     'site'     : site,
                     'rel'      : softRel,
                     'primD'    : primD,
                     'tier'     : tier,
                     'proc'     : proc,
                     'primType' : primType,
                     'group'    : group,
                     'app'      : app,
                     'date'     : _date,
                     'unm_date' : date,
                     'idx'      : _idx,
                     'ajax'     : ajax,
                     'phedex'   : phedex,
                     'host'     : self.dbsdd,
                     'style'    : "margin-top:-20px",
                     'rPage'    : rPage,
                     'pagerStep': pagerStep,
                     'nameForPager': "datasets",
                     'onchange' : "javascript:LoadGetData('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc,primType,date,_idx,ajax,userMode)
                    }
        t = templateSnapshot(searchList=[_nameSpace]).respond()
        snapshot=str(t)
        page+=snapshot
        t = templatePagerStep(searchList=[_nameSpace]).respond()
        page+=str(t)
#        print "Paging step",time.time()-t1
#        t2=time.time()
        if  not nDatasets:
            page+="""<p><span class="box_red">No data found</span></p>"""
#        print "####",nDatasets,len(datasetsList)
        try:
            for id in xrange(0,len(datasetsList)):
                dataset=datasetsList[id]
                #### TEST
                dDict,mDict = self.helper.datasetSummary(dataset,watchSite=site,htmlMode=userMode)
                if mDict:
                    t = templateProcessedDatasetsLite(searchList=[{'dbsInst':dbsInst,'path':dataset,'appPath':appPath,'dDict':dDict,'masterDict':mDict,'host':self.dbsdd,'userMode':userMode,'phedex':phedex}]).respond()
                    page+=str(t)
                else:
                    page+="""<hr class="dbs" /><br/><b>%s</b><br /><span class="box_red">No data found</span>"""%dataset
#                prdDate, siteList, blockDict, totEvt, totFiles, totSize = self.helper.getData(dataset,site,userMode)
#                page+= self.dataToHTML(dbsInst,dataset,prdDate,siteList,blockDict,totEvt,totFiles,totSize,id,snapshot,appPath,userMode,phedex)
        except:    
            page+="<verbatim>"+getExcept()+"</verbatim>"

        # end of response tag
        page+="""</div><hr class="dbs" />"""
        _nameSpace['style']="" # change style for the pager
        t = templatePagerStep(searchList=[_nameSpace]).respond()
        page+=str(t)
        
#        print "Data step",time.time()-t1, time.time()-t2

        return page
    getDataHelper.exposed=True

    def blockListToHTML(self,dbsInst,bList,appPath="*"):
        if not len(bList): return ""
        nameSpace = {'host': self.dbsdd, 'dbsInst': dbsInst, 'blockList' : bList,'appPath':appPath}
        t = templateBlockList(searchList=[nameSpace]).respond()
        page=str(t)
        return page
        
    def crabCfg(self,dataset,totEvt,userMode='userMode',**kwargs):
        page=self.genTopHTML(userMode=userMode)
        page+=self.whereMsg('Navigator :: Results :: CRAB configuration file :: dataset=%s'%dataset,userMode)
        nameSpace = {
                     'dataset'  : dataset,
                     'totEvt'   : totEvt
                    }
        t = templateCRAB(searchList=[nameSpace]).respond()
        page+=str(t)
        page+=self.genBottomHTML()
        return page
    crabCfg.exposed=True
    
    def responseTime(self,htmlTime):
        nameSpace = {
                     'dbsTime'  : "%5.3f"%self.dbsTime,
                     'dlsTime'  : "%5.3f"%self.dlsTime,
                     'htmlTime' : "%5.3f"%(htmlTime-self.dbsTime-self.dlsTime),
                     'totalTime': "%5.3f"%htmlTime
                    }
        t = templateTime(searchList=[nameSpace]).respond()
        page=str(t)
        return page

    def getData(self,dbsInst,site="Any",group="*",app="*",primD="*",tier="*",proc="*",primType="*",date="*",hist="",_idx=0,ajax=1,userMode="user",pagerStep=RES_PER_PAGE,phedex=0,**kwargs): 
        """
           HTML wrapper for Main worker L{getDataHelper}.
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @type  site: string
           @param site: user selection of the site, default "All"
           @type  app: string
           @param app: user selection of the application, default "*"
           @type  primD: string
           @param primD: user selection of the primary dataset, default "*"
           @type  tier: string
           @param tier: user selection of the data tier, default "*"
           @type  i: integer
           @param i: index to start with
           @type  j: integer
           @param j: index to end with
           @rtype : string
           @return: returns HTML code
        """
        _idx=int(_idx)
        tier=tier.upper()
        if app!="*" and app[0]!="/":
           app="/%s/*/*"%app
        t1=time.time()
        if string.lower(tier)=="all" or string.lower(tier)=="any": tier="*"
        if string.lower(site)=="all" or string.lower(site)=="any": site="*"
        if string.lower(date)=="all" or string.lower(date)=="any": date="*"
        if kwargs.has_key('cDate1') and kwargs.has_key('cDate2'):
           # we got a date range
           try:
               d1=time.mktime(map(lambda x: int(x), kwargs['cDate1'].split("/"))+[0,0,0,0,0,0])
               d2=time.mktime(map(lambda x: int(x), kwargs['cDate2'].split("/"))+[0,0,0,0,0,0])
               date="%d_%d"%(d1,d2)
           except:
               return "Please check that you entered dates in correct format: yyyy/mm/dd"
        if type(phedex) is types.StringType and string.lower(phedex)=="off":
           phedex=0
        if phedex:
           # we will pass site as phedex parameter in order to create a single 
           # phedex call in templateProcessedDatasets.tmpl
           if site=="*":
              phedex="multiple"
           else:
              phedex=site
        page=""
        if  int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page="""<ajax-response><response type="object" id="results">"""
            try:
                self.helperInit(dbsInst)
#                self.htmlInit()
                msg="dbsInst='%s', site='%s', app='%s', primD='%s', tier='%s'"%(dbsInst,site,app,primD,tier)
                self.writeLog(msg)
                msg=""
                self.formDict['menuForm']=(msg,dbsInst,site,app,primD,tier)
                page+= self.getDataHelper(dbsInst,site,group,app,primD,tier,proc,primType,date,hist,_idx,ajax,userMode,pagerStep,phedex)
            except:
                t=self.errorReport("Fail in getData function")
                page+=str(t)
            t2=time.time()
            if userMode!="user" and self.profile:
               page+=self.responseTime(t2-t1)
            page+="</response></ajax-response>"
        else:
           page=self.genTopHTML(userMode=userMode)
           if hist:
              page+=hist
           result = self.getDataHelper(dbsInst,site,group,app,primD,tier,proc,primType,date,hist,_idx,ajax,userMode,pagerStep,phedex)
           page+= result
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getData.exposed = True 

#    def getUserData(self,dbsInst,group="All",tier="*",rel="*",prim="*",site="*",_idx=0,ajax=1,**kwargs): 
#        self.helperInit(dbsInst)
#        app="/%s/*/*"%rel # it is in form /ver/fam/exe
#        _idx=int(_idx)
#        self.setContentType('xml')
#        page="""<ajax-response><response type="object" id="results">"""
#        page+= self.getDataHelper(dbsInst,site,group,app,primD,tier,proc,hist,_idx,ajax)
#        page+="</response></ajax-response>"
#        if self.verbose==2:
#           self.writeLog(page)
#        return page
#    getUserData.exposed=True

    def getBlocksInfo(self,dbsInst,dataset,userMode):
        """
           Retreive block information for give dataset
        """
        t1=time.time()
        page=self.genTopHTML(userMode=userMode)
        try:
            page+= self.whereMsg('Navigator :: Results :: File block information',userMode)
            blkList = self.helper.getBlocksInfo(dataset)
            nameSpace = {
                         'blkList'  : blkList,
                         'proc'     : dataset,
                         'host'     : self.dbsdd,
                         'dbsInst'  : dbsInst,
                         'userMode' : userMode
                        }
            t = templateBlocksInfo(searchList=[nameSpace]).respond()
            page+=str(t)
        except:
            t=self.errorReport("Fail in getBlocksInfo function")
            page+=str(t)
        page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getBlocksInfo.exposed = True 

    def getDbsData(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,userMode="user",pagerStep=RES_PER_PAGE,**kwargs): 
        """
           HTML wrapper for Main worker L{getDataHelper}.
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @type  site: string
           @param site: user selection of the site, default "All"
           @type  app: string
           @param app: user selection of the application, default "*"
           @type  primD: string
           @param primD: user selection of the primary dataset, default "*"
           @type  tier: string
           @param tier: user selection of the data tier, default "*"
           @type  i: integer
           @param i: index to start with
           @type  j: integer
           @param j: index to end with
           @rtype : string
           @return: returns HTML code
        """
        _idx=int(_idx)
        t1=time.time()
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="results_dbs">"""
        else:
           page=self.genTopHTML(userMode=userMode)
        try:
            if string.lower(tier)=="all": tier="*"
            self.helperInit(dbsInst)
            self.formDict['menuForm']=("",dbsInst,"All",app,primD,tier)

            if type(proc) is not types.ListType:
               if proc.lower()=="all" or proc.lower()=="any" or proc.lower()=="*":
                  dList=self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc,site=site,userMode=userMode,fromRow=_idx*pagerStep,limit=pagerStep)
               else:
                  dList=[proc]
            else:
               dList=proc
            page+= self.whereMsg('Navigator :: Results :: File block information',userMode)
            for idx in xrange(0,len(dList)):
                tid = 't_dbs_'+str(idx)
                dataset = dList[idx]
                nameSpace = {
                             'dbsList'  : self.helper.getDbsData(dataset),
                             'tableId'  : _idx,
                             'proc'     : dataset,
                             'host'     : self.dbsdd,
                             'dbsInst'  : dbsInst,
                             'userMode' : userMode
                            }
                t = templateDbsInfo(searchList=[nameSpace]).respond()
                page+=str(t)
        except:
            t=self.errorReport("Fail in getDbsData function")
            page+=str(t)
        t2=time.time()
        if userMode!="user" and self.profile:
           page+=self.responseTime(t2-t1)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDbsData.exposed = True 

    def getRuns(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,userMode="user",pagerStep=RES_PER_PAGE,**kwargs): 
        """
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @type  site: string
           @param site: user selection of the site, default "All"
           @type  app: string
           @param app: user selection of the application, default "*"
           @type  primD: string
           @param primD: user selection of the primary dataset, default "*"
           @type  tier: string
           @param tier: user selection of the data tier, default "*"
           @type  i: integer
           @param i: index to start with
           @type  j: integer
           @param j: index to end with
           @rtype : string
           @return: returns HTML code
        """
        _idx=int(_idx)
        t1=time.time()
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="runs">"""
        else:
           page=self.genTopHTML(userMode=userMode)
        try:
            if string.lower(tier)=="all": tier="*"
            self.helperInit(dbsInst)
            self.formDict['menuForm']=("",dbsInst,"All",app,primD,tier)

            if type(proc) is not types.ListType:
               if proc.lower()=="all" or proc.lower()=="any" or proc.lower()=="*":
                  dList=self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc,site=site,userMode=userMode,fromRow=_idx*pagerStep,limit=pagerStep)
               else:
                  dList=[proc]
            else:
               dList=proc
            page+=self.whereMsg('Navigator :: Results :: Run information',userMode)
            for idx in xrange(0,len(dList)):
                tid = 't_runs_'+str(idx)
                dataset = dList[idx]
                runList,runDBInfoDict=self.helper.getRuns(dataset,userMode=userMode)
                nameSpace = {
                             'dbsInst'  : dbsInst,
                             'host'     : self.dbsdd,
                             'runList'  : runList,
                             'runDBInfo': runDBInfoDict,
                             'tableId'  : tid,
                             'proc'     : dataset,
                             'userMode' : userMode
                            }
                t = templateRunsInfo(searchList=[nameSpace]).respond()
                page+=str(t)
        except:
            t=self.errorReport("Fail in getRunsData function")
            page+=str(t)
        t2=time.time()
        if userMode!="user" and self.profile:
           page+=self.responseTime(t2-t1)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getRuns.exposed = True 

    def getRunsFromRange(self,dbsInst,primD,primType,minRun,maxRun,userMode="user",_idx=0,ajax=0,pagerStep=RES_PER_PAGE,**kwargs): 
        """
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @rtype : string
           @return: returns HTML code
        """
        _idx=int(_idx)
        pagerStep=int(pagerStep)
        t1=time.time()
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="runs">"""
        else:
           page=self.genTopHTML(userMode=userMode)
        try:
            self.helperInit(dbsInst)
            page+=self.whereMsg('Run search :: Results :: Run information',userMode)

            nResults=0
            try:    
               nResults=self.helper.getRuns(dataset="",primD=primD,primType=primType,minRun=minRun,maxRun=maxRun,count=1,userMode=userMode)
            except:
               msg="No runs found for your request:<br />"
               msg+="<ul>"
               msg+="<li>DBS instnace: <em>%s</em>"%dbsInst
               msg+="<li>Run range:  <em>%s-%s</em>"%(minRun,maxRun)
               msg+="</ul>"
               page+=msg
               page+=self.genBottomHTML()
               return page
            page+="""<p>For run range %s-%s found %s run,dataset entries</p>"""%(minRun,maxRun,nResults)
            ########## Construct result page
            rPage=""
            if nResults:
               rPage+="Result page:"

            # the progress bar for all results
            if _idx:
                rPage+="""<a href="getRunsFromRange?dbsInst=%s&amp;primD=%s&amp;primType=%s&amp;minRun=%s&amp;maxRun=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s">&#171; Prev</a> """%(dbsInst,primD,primType,minRun,maxRun,_idx-1,userMode,pagerStep)
            tot=_idx
            for x in xrange(_idx,_idx+GLOBAL_STEP):
                if nResults>x*pagerStep:
                   tot+=1
            for index in xrange(_idx,tot):
                ref=index+1
                if index==_idx:
                   ref="""<span class="gray_box">%s</span>"""%(index+1)
                rPage+="""<a href="getRunsFromRange?dbsInst=%s&amp;;primD=%s&amp;primType=%s&amp;minRun=%s&amp;maxRun=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s"> %s </a> """%(dbsInst,primD,primType,minRun,maxRun,index,userMode,pagerStep,ref)
            if  nResults>(_idx+1)*pagerStep:
                rPage+="""<a href="getRunsFromRange?dbsInst=%s&amp;;primD=%s&amp;primType=%s&amp;minRun=%s&amp;maxRun=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s">Next &#187;</a> """%(dbsInst,primD,primType,minRun,maxRun,_idx+1,userMode,pagerStep)

            if _idx and (_idx*pagerStep)>nResults:
               return "No data found for this request"
            ##### end of the pager
            _nameSpace = {
                         'style'    : "",
                         'rPage'    : rPage,
                         'pagerStep': pagerStep,
                         'nameForPager': "rows",
                         'onchange' : "javascript:LoadGetRunsFromRange('%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,primD,primType,minRun,maxRun,_idx,ajax,userMode)
                        }
            t = templatePagerStep(searchList=[_nameSpace]).respond()
            pagerPage=str(t)

            runList,runDBInfoDict=self.helper.getRuns(dataset="",primD=primD,primType=primType,minRun=minRun,maxRun=maxRun,fromRow=_idx*pagerStep,limit=pagerStep,count=0,userMode=userMode)
            if len(runList):
                page+=pagerPage
                nameSpace = {
                             'dbsInst'  : dbsInst,
                             'host'     : self.dbsdd,
                             'runList'  : runList,
                             'runDBInfo': runDBInfoDict,
                             'tableId'  : "runTable",
                             'proc'     : "",
                             'userMode' : userMode
                            }
                t = templateRunsInfo(searchList=[nameSpace]).respond()
                page+=str(t)
                page+=pagerPage
        except:
            t=self.errorReport("Fail in getRunsFromRange function")
            page+=str(t)
        t2=time.time()
        if userMode!="user" and self.profile:
           page+=self.responseTime(t2-t1)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getRunsFromRange.exposed = True 

    def getParameterSet(self,dbsInst,dataset=""):
        page="Here information about paramterSet information will appear"
        return page
    getParameterSet.exposed=True

    def getLFNs(self,dbsInst,blockName,**kwargs):
        """
           Retrieves LFN list.
           @type  dataset: string 
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="blockLFNs">"""
        dataset="*" # I don't retrieve all LFNs from dataset
        run="*"
        try:
            self.helperInit(dbsInst)
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset,run)
            page+="""
<select multiple="multiple" name="blockLFNlist" id="blockLFNlist" style="width:1000px" size="10">
<option value="All">
All LFNs in a block
</option>\n"""
            for lfn in lfnList:
                lName = lfn[0]
                page+="""<option value="%s">%s</option>\n"""%(lName,lName)
            page+="</select>\n"
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            pass
        page+="</response></ajax-response>"
        if self.verbose==2:
           print page
        return page
    getLFNs.exposed = True
 
    def getLFNlist(self,dbsInst,blockName,dataset="",userMode='user',run='*'):
        """
           Retrieves and represents LFN list. The list is formed by L{lfnToHTML}.
           @type  dataset: string 
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        try:
            self.helperInit(dbsInst)
            page = self.genTopHTML(userMode=userMode)
            t="dataset"
            v=dataset
            if blockName and blockName!="*":
               t="block"
               v=blockName
            if run and run!='*':
               page+= self.whereMsg('Navigator :: Results :: LFN list :: %s %s :: run %s'%(t,v,run),userMode)
            else:
               page+= self.whereMsg('Navigator :: Results :: LFN list :: %s %s'%(t,v),userMode)
            page+= self.lfnToHTML(dbsInst,blockName,dataset,userMode,run)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getLFNlist function")
            pass
            return str(t)
    getLFNlist.exposed = True
 
    def getLFN_txt(self,dbsInst,blockName,dataset="",userMode='user',run='*'):
        """
           Retrieves and represents LFN list in ASCII form
           @type  dataset: string 
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        try:
            self.helperInit(dbsInst)
            page = self.genTopHTML(userMode=userMode)
            t="dataset"
            v=dataset
            if blockName and blockName!="*":
               t="block"
               v=blockName
            if run and run!='*':
               page+= self.whereMsg('Navigator :: Results :: LFN list :: %s %s :: run %s'%(t,v,run),userMode)
            else:
               page+= self.whereMsg('Navigator :: Results :: LFN list :: %s %s'%(t,v),userMode)
            page+="""<pre>\n"""
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset,run)
            for item in lfnList:
                lfn=item[0]
                page+="%s\n"%lfn
            page+="\n</pre>"
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getLFN_txt function")
            pass
            return str(t)
    getLFN_txt.exposed = True
    
#    def getLFNsForSite(self,dbsInst,site,blockList="",what="cff",userMode='user'):
    def getLFNsForSite(self,dbsInst,site,datasetPath,what="cff",userMode='user'):
        """
           Generates a list of LFNs for given site
        """
        try:
#            self.htmlInit()
            self.helperInit(dbsInst)
            page = self.genTopHTML(userMode=userMode)
            page+= self.whereMsg('Navigator :: Results :: LFN list :: site %s'%site,userMode)
            page+="""<pre>\n"""
            bList=[]
#            if  blockList:
#                bList=blockList.split(",")
#            else:
            try:
#                blocks=[]
#                if blockList:
#                   blocks=blockList.split(",")
#                bList=self.helper.getBlocksFromSite(site,blocks)

#                bList=self.helper.getBlocksFromSite(site,datasetPath)
                lfnList=self.helper.getLFNsFromSite(site,datasetPath)
            except:
                if self.verbose:
                   self.writeLog(getExcept())
                printExcept()
                page+="No LFNs found for site '%s'\n"%site
                pass
            if what=="cff":
               page+="replace PoolSource.fileNames = {\n"

            # loop over files
            for item in lfnList:
                if  what=="cff":
                    lfn=item
                    if lfn==lfnList[-1]:
                       page+="'%s'\n"%lfn
                    else:
                       page+="'%s',\n"%lfn
                else:
                    lfn=item
                    page+="%s\n"%lfn
#            for blockName in bList:
#                try:
#                    lfnList = self.helper.getLFNs(dbsInst,blockName,"")
#                    if not lfnList: page+="No LFNs found in '%s' on site='%s' for blockName='%s'\n"%(dbsInst,site,blockName)
#                    for item in lfnList:
#                        if  what=="cff":
#                            lfn=item[0]
#                            if lfn==lfnList[-1][0] and blockName==bList[-1]:
#                               page+="'%s'\n"%lfn
#                            else:
#                               page+="'%s',\n"%lfn
#                        else:
#                            lfn=item[0]
#                            page+="%s\n"%lfn
#                except:
#                    if self.verbose:
#                       self.writeLog(getExcept())
#                    printExcept()
#                    page+="No LFNs found int DBS for block='%s'\n"%blockName
#                    pass
            if what=="cff": page+="}"
            page+="\n</pre>"
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getLFNsForSite function")
            pass
            return str(t)
    getLFNsForSite.exposed=True
    
    def getConfigContent(self,dbsInst,id,name,userMode='user',**kwargs):
        """
           Retrieves content of configuration file
           @type  dataset: string 
           @param dataset: dataset name
           @type fileName: string
           @param fileName: file name
           @rtype : string
           @return: returns HTML code
        """
        try:
#            self.htmlInit()
            self.helperInit(dbsInst)
            page = self.genTopHTML(userMode=userMode)
            nameSpace={'name':name,'content':self.helper.getConfigContent(dbsInst,id)}
            t = templateAppConfigContent(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getConfigContent function")
            pass
            return str(t)
    getConfigContent.exposed = True

#    def getBlocksForSite(self,site):
#        """
#           Generates list of file blocks for given site
#        """
#        try:
#            self.htmlInit()
#            page = self.genTopHTML()
#            t = templateWhere(searchList=[{'where':'Navigator :: Results :: Blocks at site=%s'%site}]).respond()
#            page+=str(t)
#            page ="""<pre>\n"""
#            for blockName in self.helper.getBlocksFromSite(site):
#                page+="%s\n"%blockName
#            page+="\n</pre>"
#            page+=self.genBottomHTML()
#            return page
#        except:
#            t=self.errorReport("Fail in getBlocksForSite function")
#            pass
#            return str(t)
#    getBlocksForSite.exposed=True
    
    def getLFN_cfg(self,dbsInst,blockName,dataset="",userMode='user'):
        """
           Retrieves and represents LFN list in 'cff' framework form
           @type  dataset: string 
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        try:
#            self.htmlInit()
            self.helperInit(dbsInst)
            page = self.genTopHTML(userMode=userMode)
            page+= self.whereMsg('Navigator :: Results :: LFN list :: block %s'%blockName,userMode)
            page+="""<pre>\n"""
            page+="replace PoolSource.fileNames = {\n"
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset)
            for item in lfnList:
                lfn=item[0]
                if lfn==lfnList[-1][0]:
                   page+="'%s'\n"%lfn
                else:
                   page+="'%s',\n"%lfn
            page+="}\n</pre>"
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getLFN_cfg function")
            pass
            return str(t)
    getLFN_cfg.exposed = True

    def lfnToHTML(self,dbsInst,blockName,dataset="",userMode='user',run='*'):
        """
           Constructs LFN list into table.
           @type  dataset: string
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        lfnList = self.helper.getLFNs(dbsInst,blockName,dataset,run)
        nameSpace = {
                     'host'      : self.dbsdd,
                     'dbsInst'   : dbsInst,
                     'blockName' : blockName,
                     'dataset'   : dataset,
                     'lfnList'   : lfnList,
                     'userMode'  : userMode,
                     'run'       : run
                    }
        t = templateLFN(searchList=[nameSpace]).respond()
        return str(t)
        
    def dataToHTML(self,dbsInst,path,date,siteList,blockDict,totEvt,totFiles,totSize,id,snapshot="",appPath="",userMode="user",phedex=0):
        """
           Forms output tables.
           @type  path: string 
           @param path: processing path
           @rtype : string
           @return: returns HTML code
        """

        page=""
        nameSpace = {
                     'host'       : self.dbsdd,
                     'path'       : path,
                     'firstSearch': self.firstSearch,
                     'dbsInst'    : dbsInst,
                     'siteList'   : siteList,
                     'blockDict'  : blockDict,
                     'nEvents'    : totEvt,
                     'totFiles'   : totFiles,
                     'totSize'    : totSize,
                     'date'       : date,
                     'tid'        : id,
                     'snapshot'   : snapshot,
                     'appPath'    : appPath,
                     'userMode'   : userMode,
                     'phedex'     : phedex,
                    }
        t = templateProcessedDatasets(searchList=[nameSpace]).respond()
#        t = templateLFB(searchList=[nameSpace]).respond()
	page+=str(t)
        return page
        
    def getAnalysisDS(self,dbsInst,dataset,ajax=0,userMode='user',_idx=0,pagerStep=RES_PER_PAGE,**kwargs):
        _idx=int(_idx)
        if  int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page="""<ajax-response><response type="object" id="results">"""
        else:
            page=self.genTopHTML(userMode=userMode)
            page+=self.whereMsg('Navigator :: Results :: Analysis datasets for processed dataset \'%s\''%dataset,userMode)
        self.helper.setDBSDLS(dbsInst)
        dList = self.helper.getAnalysisDS(dataset)
        nameSpace = {'dList':dList,'dbsInst':dbsInst,'path':dataset,'userMode':userMode,'appPath':"/*/*/*",'full':0}
        t = templateAnalysisDS(searchList=[nameSpace]).respond()
	page+=str(t)
        if  int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getAnalysisDS.exposed=True

    def getAnalysisDSFullInfo(self,dbsInst,dataset,ads,userMode):
        page=self.genTopHTML(userMode=userMode)
        page+=self.whereMsg('Navigator :: Results :: Full Info about analysis dataset \'%s\''%ads,userMode)
        self.helper.setDBSDLS(dbsInst)
        dList = self.helper.getAnalysisDS(dataset,ads)
        for item in dList:
            if item[0]==ads:
                nameSpace={'dList':[item],'dbsInst':dbsInst,'path':dataset,'userMode':userMode,'appPath':"/*/*/*",'full':1}
                t = templateAnalysisDS(searchList=[nameSpace]).respond()
                page+=str(t)
                break
        page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getAnalysisDSFullInfo.exposed=True

    def findAnalysisDS(self,dbsInst,userMode='user',_idx=0,pagerStep=RES_PER_PAGE,**kwargs):
        self.helperInit(dbsInst)
        ads_name=adsd_name=adsd_anns=adsd_desc=adsd_rels=adsd_tier=adsd_prds=adsd_lfns=adsd_runs=adsd_lumi=adsd_cuts=""
        op_adsd_anns=op_adsd_desc=op_adsd_rels=op_adsd_tier=op_adsd_prds=op_adsd_lfns=op_adsd_runs=op_adsd_lumi=op_adsd_cuts="like"
        op_ads_name="="
        op_adsd_name="="
        _idx=int(_idx)

        if kwargs.has_key('ads_name'):   ads_name=kwargs['ads_name']
        if kwargs.has_key('adsd_name'): adsd_name=kwargs['adsd_name']
        if kwargs.has_key('adsd_anns'): adsd_name=kwargs['adsd_anns']
        if kwargs.has_key('adsd_desc'): adsd_name=kwargs['adsd_desc']
        if kwargs.has_key('adsd_rels'): adsd_name=kwargs['adsd_rels']
        if kwargs.has_key('adsd_tier'): adsd_name=kwargs['adsd_tier']
        if kwargs.has_key('adsd_prds'): adsd_name=kwargs['adsd_prds']
        if kwargs.has_key('adsd_lfns'): adsd_name=kwargs['adsd_lfns']
        if kwargs.has_key('adsd_runs'): adsd_name=kwargs['adsd_runs']
        if kwargs.has_key('adsd_lumi'): adsd_name=kwargs['adsd_lumi']
        if kwargs.has_key('adsd_cuts'): adsd_name=kwargs['adsd_cuts']

        if kwargs.has_key('op_ads_name'):   op_ads_name=kwargs['op_ads_name']
        if kwargs.has_key('op_adsd_name'): op_adsd_name=kwargs['op_adsd_name']
        if kwargs.has_key('op_adsd_anns'): op_adsd_name=kwargs['op_adsd_anns']
        if kwargs.has_key('op_adsd_desc'): op_adsd_name=kwargs['op_adsd_desc']
        if kwargs.has_key('op_adsd_rels'): op_adsd_name=kwargs['op_adsd_rels']
        if kwargs.has_key('op_adsd_tier'): op_adsd_name=kwargs['op_adsd_tier']
        if kwargs.has_key('op_adsd_prds'): op_adsd_name=kwargs['op_adsd_prds']
        if kwargs.has_key('op_adsd_lfns'): op_adsd_name=kwargs['op_adsd_lfns']
        if kwargs.has_key('op_adsd_runs'): op_adsd_name=kwargs['op_adsd_runs']
        if kwargs.has_key('op_adsd_lumi'): op_adsd_name=kwargs['op_adsd_lumi']
        if kwargs.has_key('op_adsd_cuts'): op_adsd_name=kwargs['op_adsd_cuts']

        page=self.genTopHTML(userMode=userMode)

#        aList=[]
        if type(ads_name) is types.StringType and len(ads_name)>1 and (ads_name[0]=="*" or ads_name[0]=="%"):
           # we got a pattern
#           aList=self.getMatch("AnalysisDataset","Name",ads_name)
           op_ads_name="like"
           ads_name="%"+ads_name.replace("*","").replace("%","")+"%"
        page+=self.whereMsg('Navigator :: Results :: Analysis datasets',userMode)
        cDict = {
                 'AnalysisDataset.Name'           : (op_ads_name,ads_name),
                 'AnalysisDSDef.Name'             : (op_adsd_name,adsd_name),
                 'AnalysisDSDef.LumiSections'     : (op_adsd_lumi,adsd_lumi),
                 'AnalysisDSDef.Runs'             : (op_adsd_runs,adsd_runs),
                 'AnalysisDSDef.LFNs'             : (op_adsd_lfns,adsd_lfns),
                 'AnalysisDSDef.Algorithms'       : (op_adsd_rels,adsd_rels),
                 'AnalysisDSDef.Path'             : (op_adsd_prds,adsd_prds),
                 'AnalysisDSDef.Tiers'            : (op_adsd_tier,adsd_tier),
                 'AnalysisDataset.Annotation'     : (op_adsd_anns,adsd_anns),
                 'AnalysisDSDef.UserCut'          : (op_adsd_cuts,adsd_cuts),
                 'AnalysisDSDef.Description'      : (op_adsd_desc,adsd_desc),
                }
        # first count how many datasets found in order to make a pager
        _cDict=dict(cDict)
        _cDict['max']=1
        nDatasets=self.helper.getAnalysisDS(cDict=_cDict)
        # Construct result page
        rPage=""
        if nDatasets:
           rPage+="Result page:"

        # the progress bar for all results
        if _idx:
            rPage+="""<a href="findAnalysisDS?dbsInst=%s&_idx=%s&userMode=%s&pagerStep=%s">&#171; Prev</a> """%(dbsInst,_idx-1,userMode,pagerStep)
        tot=_idx
        for x in xrange(_idx,_idx+GLOBAL_STEP):
            if nDatasets>x*pagerStep:
               tot+=1
        for index in xrange(_idx,tot):
           ref=index+1
           if index==_idx:
              ref="""<span class="gray_box">%s</span>"""%(index+1)
           rPage+="""<a href="findAnalysisDS?dbsInst=%s&_idx=%s&userMode=%s&pagerStep=%s"> %s </a> """%(dbsInst,index,userMode,pagerStep,ref)
        if nDatasets>(_idx+1)*pagerStep:
           rPage+="""<a href="findAnalysisDS?dbsInst=%s&_idx=%s&userMode=%s&pagerStep=%s">Next &#187;</a>"""%(dbsInst,_idx+1,userMode,pagerStep)

        _nameSpace = {
                     'nDatasets': nDatasets,
                     'userMode' : userMode,
                     'dbsInst'  : dbsInst,
                     'idx'      : _idx,
                     'host'     : self.dbsdd,
                     'style'    : "margin-top:-20px",
                     'rPage'    : rPage,
                     'pagerStep': pagerStep,
                     'nameForPager': "analysis datasets",
                     'onchange' : "javascript:LoadAnalysisDS('%s','%s','%s')"%(dbsInst,_idx,userMode)
                    }
        t = templatePagerStep(searchList=[_nameSpace]).respond()
        snapshot=str(t)
        page+="""<div align="center"><b>Found %s analysis datasets</b></div><p />"""%nDatasets
        page+="<p>"+snapshot+"</p>"

        if  not nDatasets:
            page+="""<p><span class="box_red">No data found</span></p>"""
        else:
            dList=self.helper.getAnalysisDS(cDict=cDict,fromRow=_idx*pagerStep,limit=_idx*pagerStep+pagerStep)
            nameSpace={'dList':dList,'dbsInst':dbsInst,'path':"*",'userMode':userMode,'appPath':"/*/*/*",'full':0}
            t = templateAnalysisDS(searchList=[nameSpace]).respond()
	    page+=str(t)
        page+="<p>"+snapshot+"</p>"
        page+=self.genBottomHTML()
        return page       
    findAnalysisDS.exposed=True

    def getFileBlocks(self,dbsInst,site,ajax=1,userMode='user',_idx=0,pagerStep=RES_PER_PAGE,**kwargs):
        _idx=int(_idx)
        if  int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page="""<ajax-response><response type="object" id="results">"""
        else:
            page=self.genTopHTML(userMode=userMode)
            page+=self.whereMsg('Navigator :: Results :: Blocks at site=%s'%site,userMode)
        limit=pagerStep
        offset=int(_idx)*int(pagerStep)
        dbsList=self.helper.getBlockInfoForSite(site,limit,offset)
        rPage="Result page:"
        # the progress bar for all results
        if _idx:
            rPage+="""<a href="getFileBlocks?dbsInst=%s&site=%s&ajax=0&userMode=%s&_idx=%s&pagerStep=%s">&#171; Prev</a> """%(dbsInst,site,userMode,_idx-1,pagerStep)
            if len(dbsList): rPage+=" | "
        if len(dbsList):
           rPage+="""<a href="getFileBlocks?dbsInst=%s&site=%s&ajax=0&userMode=%s&_idx=%s&pagerStep=%s"> Next &#187;</a> """%(dbsInst,site,userMode,_idx+1,pagerStep)
        _nameSpace = {
                     'rPage'       : rPage,
                     'style'       : "",
                     'pagerStep'   : pagerStep,
                     'nameForPager': "blocks",
                     'onchange'    : "javascript:LoadGetFileBlocks('%s','%s','%s','%s','%s','%s')"%(dbsInst,site,ajax,userMode,int(_idx),int(pagerStep))
        }
        t = templatePagerStep(searchList=[_nameSpace]).respond()
        page+=str(t)
        nameSpace = {
#                     'dbsList'  : self.helper.getBlockInfoForSite(site),
                     'dbsList'  : dbsList,
                     'host'     : self.dbsdd,
                     'dbsInst'  : dbsInst,
                     'site'     : site,
                     'userMode' : userMode
                    }
        t = templateDbsInfoTableEntry(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templatePagerStep(searchList=[_nameSpace]).respond()
        page+=str(t)
        if  int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getFileBlocks.exposed=True

#    def getBlocksFromSiteHelper(self,dbsInst,site):
#        """
#           Generates AJAX response to get all primary datasets available in all DBS instances
#        """
#        page=""
#        self.helper.setDBSDLS(dbsInst)
#        bList = self.helper.getBlocksFromSite(site)
#        nameSpace = {
#                     'host'   : self.dbsdd,
#                     'dbsInst': dbsInst,
#                     'site'   : site,
#                     'bList'  : bList
#                    }
#        t = templateFileBlocksFromSite(searchList=[nameSpace]).respond()
#        page+= str(t)
#        return page
#    getBlocksFromSiteHelper.exposed=True
    
    def getBlocksFromSite(self, dbsInst, site, **kwargs):
        """
           Gets block names for given site.
           @type  site: string 
           @param site: site name 
           @rtype : string
           @return: returns HTML code
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results">"""
        try:
            self.formDict['siteForm']=(dbsInst,site)
            self.helper.setDBSDLS(dbsInst)
            bList = self.helper.getBlocksFromSite(site)
            nameSpace = {
                         'host'   : self.dbsdd,
                         'dbsInst': dbsInst,
                         'site'   : site,
                         'bList'  : bList
                        }
            t = templateFileBlocksFromSite(searchList=[nameSpace]).respond()
        except:
            t=self.errorReport("Fail in getBlocksFromSite function")
            pass
        page+= str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getBlocksFromSite.exposed=True

    def getBlocksFromDataset(self, dbsInst, datasetPath, multiple="", **kwargs):
        """
           Gets block names for given dataset path
           @type  site: string 
           @param site: site name 
           @rtype : string
           @return: returns HTML code
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results">"""
        try:
            self.helper.setDBSDLS(dbsInst)
            bList = self.helper.getBlocksFromSite(site="any",datasetPath=datasetPath)
            t="""<select %s size="5" style="width:200px">"""%multiple
            for blk in bList:
                t+="""<option value="%s">%s</option>"""%blk
            t+="</select>"
        except:
            t=self.errorReport("Fail in getBlocksFromDataset function")
            pass
        page+= str(t)
        page+="</response></ajax-response>"
        print page
        if self.verbose==2:
           self.writeLog(page)
        return page
    getBlocksFromDataset.exposed=True

    # this method can be used for auto-completion forms, it returns a string of columns
    # see YUI, http://developer.yahoo.com/yui/autocomplete/
    def yuiGetBlocksForDataset(self,dbsInst,table,column,**kwargs):
        print "\n### getBlocksForDataset",kwargs
        page=""
        whereDict={}
        if  kwargs.has_key('query'):
            key='%s.Path'%table
            whereDict[key]=kwargs['query']
        # since this method is used only in auto-completion forms, restrict output to 10 results
        row=0
        limit=10
        natList = natsort24(list(self.helper.getTableColumn(table,column,row,limit,whereDict) ))
        for item in natList:
            page+="%s\n"%item
        return page
    yuiGetBlocksForDataset.exposed=True

    def getAllPrimaryDatasets(self,**kwargs):
        """
           Generates AJAX response to get all primary datasets available in all DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="<ajax-response>"
        for dbs in self.dbsList:
            if string.find(dbs,"fanfani")!=-1:
               name = "datasetsDev_fanfani"
            else:
               name = "datasets%s"%string.split(dbs,"/")[0]
            page+="""<response type="element" id="%s">"""%name
            page+=self.getPrimaryDatasetsHelper(dbs)
            page+="</response>\n"
        page+="</ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getAllPrimaryDatasets.exposed=True

    def getPrimaryDatasetsHelper(self,dbsInst):
        """
           Get list of primary dataset for given DBS instances.
           @type  dbsInst: string
           @param dbsInst: DBS instances
           @rtype : string
           @return: returns HTML code
        """
        self.helperInit(dbsInst)
        dList = self.helper.getPrimaryDatasets(html=1)
        nameSpace = {
                     'msg'     : "%s: primary datasets"%dbsInst,
                     'dbsInst' : dbsInst,
                     'dList'   : dList
                    }
        t = templatePrintList(searchList=[nameSpace]).respond()
        return str(t)

    def getPrimaryDatasets(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get primary datasets for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="<ajax-response>"
        page+="""<response type="object" id="dbs_prim">"""
        page+="""<div class="div_scroll">"""+self.getPrimaryDatasetsHelper(dbsInst)+"</div>"
        page+="</response>\n"
        page+="</ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getPrimaryDatasets.exposed=True

#    def getProcessedDatasetsHelper(self,dbsInst):
#        """
#           Get list of processed dataset for given DBS instances.
#           @type  dbsInst: string
#           @param dbsInst: DBS instances
#           @rtype : string
#           @return: returns HTML code
#        """
#        self.helperInit(dbsInst)
#        dList = self.helper.getProcessedDatasets(datasetPath="*",app=0,html=1)
#        nameSpace = {
#                     'msg'     : "%s: processed datasets"%dbsInst,
#                     'dbsInst' : dbsInst,
#                     'dList'   : dList
#                    }
#        t = templatePrintList(searchList=[nameSpace]).respond()
#        return str(t)

#    def getProcessedDatasets(self,dbsInst,**kwargs):
#        """
#           Generates AJAX response to get processed datasets for given DBS instances
#        """
#        self.setContentType('xml')
#        page="<ajax-response>"
#        page+="""<response type="object" id="dbs_proc">"""
#        page+="""<div class="div_scroll">"""+self.getProcessedDatasetsHelper(dbsInst)+"</div>"
#        page+="</response>\n"
#        page+="</ajax-response>"
#        if self.verbose==2:
#           self.writeLog(page)
#        return page
#    getProcessedDatasets.exposed=True

    def getApplicationsHelper(self,dbsInst):
        """
           Get list of applications for given DBS instances.
           @type  dbsInst: string
           @param dbsInst: DBS instances
           @rtype : string
           @return: returns HTML code
        """
        self.helperInit(dbsInst)
        dList = self.helper.getApplications()
        nameSpace = {
                     'msg'     : "%s: applications"%dbsInst,
                     'dbsInst' : dbsInst,
                     'dList'   : dList
                    }
        t = templatePrintList(searchList=[nameSpace]).respond()
        return str(t)

    def getApplications(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get applications for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="<ajax-response>"
        page+="""<response type="object" id="dbs_apps">"""
        page+="""<div class="div_scroll">"""+self.getApplicationsHelper(dbsInst)+"</div>"
        page+="</response>\n"
        page+="</ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getApplications.exposed=True

    def getBranches(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get ROOT branches for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_branch">"""
        self.helperInit(dbsInst)
        dList=['ROOT1','ROOT2','FIXME']
        nameSpace = {'name':'release','iList': dList}
        t = templateSelectList(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getBranches.exposed=True

    def getGroups(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get groups for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_group_holder">"""
        self.helperInit(dbsInst)
        dList=['Any']+self.helper.getPhysicsGroups()
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':'group','iList': dList,'selTag':'kw_group','changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getGroups.exposed=True

    def getRunRange(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get runs for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_runRange_holder">"""
        self.helperInit(dbsInst)
        primD=primType="any"
        for key in kwargs:
            if key=='primType':
               primType=kwargs['primType']
            if key=='primD':
               primD=kwargs['primD']
        rMin,rMax=self.helper.getRunsForPrimary(primD,primType)
#        if not rMin: return ""
#        if not rMax: return ""
        style="width:200px"
        page+="""<input id="kw_minRun_holder" name="minRun" value="%s" size="6" />"""%rMin
        page+="--"
        page+="""<input id="kw_maxRun_holder" name="maxRun" value="%s" size="6" />"""%rMax
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getRunRange.exposed=True

    def getSites(self,dbsInst,sel="",tag="site",**kwargs):
        """
           Generates AJAX response to get sites for given DBS instances
        """
        if not sel: sel="kw_site_holder"
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="%s">"""%sel
        self.helperInit(dbsInst)
#        dList=['Any']+self.helper.getSites()
        siteDict=sortSitesByDomain(self.helper.getSites())
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
#        nameSpace = {'name':tag,'iList': dList,'selTag':tag,'changeFunction':'','style':style}
        nameSpace = {'name':tag,'iList': siteDict,'selTag':tag,'changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getSites.exposed=True

    def getTiers(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get tiers for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_tier_holder">"""
        self.helperInit(dbsInst)
        dList=['Any']+self.helper.getDataTiers()
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':'tier','iList': dList,'selTag':'kw_tier','changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getTiers.exposed=True

    def getTriggerLines(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get trigger lines for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_prim_holder">"""
        self.helperInit(dbsInst)
        group=tier=rel=dsType="*"
        for key in kwargs:
            if key=='group':
               group=kwargs['group']
            if key=='tier':
               tier=kwargs['tier']
            if key=='rel':
               rel=kwargs['rel']
            if key=='dsType':
               dsType=kwargs['dsType']

        dList = self.helper.getPrimaryDatasets(group,tier,rel,dsType)
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        cFunc=''
        if kwargs.has_key('changeFunction'): cFunc=kwargs['changeFunction']
        nameSpace = {'name':'primD','iList': ['Any']+natsort24(dList),'selTag':'kw_prim','changeFunction':cFunc,'style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getTriggerLines.exposed=True

    def getSoftwareReleases(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get releases for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_release_holder">"""
        self.helperInit(dbsInst)
        relList=self.helper.getSoftwareReleases()
        relList.reverse()
        dList = ['Any']+relList
        cFunc ="ajaxEngine.registerRequest('ajaxGetTriggerLines','getTriggerLines');ajaxUpdatePrimaryDatasets();"
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':'app','iList': dList,'selTag':'kw_release','changeFunction':cFunc,'style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getSoftwareReleases.exposed=True

    def getPrimaryDSTypes(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get primary DS types for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_primType_holder">"""
        self.helperInit(dbsInst)
        dList = ['Any']+self.helper.getPrimaryDSTypes()
        cFunc ="ajaxEngine.registerRequest('ajaxGetTriggerLines','getTriggerLines');ajaxUpdatePrimaryDatasets();"
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        if kwargs.has_key('changeFunction'): cFunc=kwargs['changeFunction']
        nameSpace = {'name':'primType','iList': dList,'selTag':'kw_primType','changeFunction':cFunc,'style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getPrimaryDSTypes.exposed=True

    def getDatasetProvenanceHelper(self,dbsInst,dataset,userMode='user',**kwargs):
        """
           Get dataset provenance
           @type  dataset: string
           @param dataset: dataset name
           @rtype: string
           @return: returns parents fro given datasets in HTML form 
        """
        try:
            page = self.whereMsg('Navigator :: Results :: Provenance information',userMode)
            self.helperInit(dbsInst)
            parents  = self.helper.getDatasetProvenance(dataset)
            nameSpace={
                       'host'      : self.dbsdd, 
                       'dataset'   : dataset, 
                       'userMode'  : userMode,
                       'dbsInst'   : dbsInst,
                       'parentList': parents
                      }
            t = templateProvenance(searchList=[nameSpace]).respond()
            page+= str(t)
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            page="No provenance information found at this time"
        return page

    def getDatasetProvenance(self,dbsInst,dataset,userMode='user',ajax=1,**kwargs):
        """
           Generates AJAX response to get dataset provenance
        """
        if  int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page ="""<ajax-response><response type="element" id="parentGraph">"""
        else:
            page=self.genTopHTML(userMode=userMode)
        page+=self.getDatasetProvenanceHelper(dbsInst,dataset,userMode)
        if  int(ajax):
            page+="</response></ajax-response>"
        else:
            page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDatasetProvenance.exposed=True

    def getProvenanceForAllDatasets(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,userMode="user",**kwargs): 
        """
           AJAX method to retrieve/build provenance graph once user made a choice to lookup
           data for given input parameters.
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @type  site: string
           @param site: user selection of the site, default "All"
           @type  app: string
           @param app: user selection of the application, default "*"
           @type  primD: string
           @param primD: user selection of the primary dataset, default "*"
           @type  tier: string
           @param tier: user selection of the data tier, default "*"
           @rtype : string
           @return: returns HTML snippet in AJAX wrapper
        """
        _idx=int(_idx)
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="parents">"""
        else:
           page=self.genTopHTML(userMode=userMode)
           page+= self.genResultsHTML()

        if string.lower(tier)=="all": tier="*"
        if string.lower(site)=="all": site="*"
        self.helperInit(dbsInst)
        nDatasets = self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc,site=site,count=1)
        dList = self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc,site=site,userMode=userMode,fromRow=_idx*pagerStep,limit=pagerStep)
        className="hide"
        if int(_idx)==0:
           className="show_inline"
        page+="""<div id="parents_response_%s" class="%s">"""%(_idx,className)
        for dataset in dList:
            page+=self.getDatasetProvenanceHelper(dbsInst,dataset,userMode)
        page+="</div>"
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getProvenanceForAllDatasets.exposed = True 
    
#    def getSites(self,dbsInst,ajax=0,**kwargs):
#        self.helperInit(dbsInst)
#        if int(ajax):
#           self.setContentType('xml')
#           page="""<ajax-response><response type="element" id="sitesHolder">"""
#        else:
#           page=self.genTopHTML()
#           page+= self.genResultsHTML()
#        try:
#            siteList=['AnyVK']+self.helper.getSites()
#            nameSpace={'selTag':"siteList",'changeFunction':"",'name':"siteList",'iList':siteList}
#            t = templateSelect(searchList=[nameSpace]).respond()
#            page+=str(t)
#        except:
#            t=self.errorReport("Fail in getSites function")
#            page+=str(t)
#            pass
#        if int(ajax):
#           page+="</response></ajax-response>"
#        else:
#           page+=self.genBottomHTML()
#        if self.verbose==2:
#           self.writeLog(page)
#        return page
#    getSites.exposed = True

#    def genSiteMenuDict(self):
#        self.setContentType('xml')
#        page="""<ajax-response><response type="object" id="navigatorDict">"""
#        endAjaxMsg="</response></ajax-response>"
#        try:
#            page+="siteDict="+getDictOfSites()
#        except:
#            t=self.errorReport("Fail in genSiteMenuDict function")
#            page+=str(t)
#            pass
#        page+=endAjaxMsg
#        if self.verbose==2:
#           self.writeLog(page)
#        return page
#    genSiteMenuDict.exposed = True
        
    def siteForm(self,firstDBS="",firstSite="",userMode='expert',auto=0):
        """
           Generates site form request
        """
        if not firstDBS: firstDBS=DBSGLOBAL
        if firstSite=="*": firstSite="All"
        if auto:
           # auto-competion form for processed datasets
           nameSearch={'tag':'proc','inputId':'proc','inputName':'proc','size':'80','userMode':userMode,'dbsInst':DBSGLOBAL,'table':'Block','column':'Path','label':'','zIndex':9000,'method':'getTableColumn'}
           t = templateAutoComplete(searchList=[nameSearch]).respond()
           prdForm=str(t)
        else:
           prdForm="""<input type="text" name="proc" id="proc" size="80">"""


#        siteList=['Any']+self.helper.getSites()
        siteList=self.helper.getSites()
        siteDict=sortSitesByDomain(siteList)
        nameSpace = {
                     'firstDBS' : firstDBS,
                     'firstSite': firstSite,
                     'dbsList'  : self.dbsList,
                     'dbsGlobal': DBSGLOBAL,
#                     'siteList' : siteList,
                     'siteDict' : siteDict,
                     'userMode' : userMode,
                     'prdForm'  : prdForm,
                     'autocomplete': auto,
                    }
        t = templateSiteForm(searchList=[nameSpace]).respond()
        page = str(t)
        return page

    def sendFeedback(self,userEmail,feedbackText,userMode='user'):
        """
           Generates feedback form.
        """
        p = os.popen("%s -t" % SENDMAIL, "w")
        p.write("To: cms-dbs-support@cern.ch\n")
#        p.write("To: vk@mail.lns.cornell.edu\n")
        p.write("Subject: response from DBS data discovery\n")
        p.write("\n") # blank line separating headers from body
#        p.write("From: %s\n"%userEmail)
        p.write("Message send from DBS discovery page by %s\n"%userEmail)
        p.write("\n") # blank line separating headers from body
        p.write(feedbackText)
        sts = p.close()
        print "Status from sendFeedback",sts
        page = self.genTopHTML(userMode=userMode)
        page+= """<p class="sectionhead_tight">Your feedback is greatly appreciated and has been send to DBS support team.</p>"""
        page+= self.genBottomHTML()
        return page
    sendFeedback.exposed=True

    def sendFeedback_v1(self,userEmail,feedbackText,userMode='user'):
        """
           Generates feedback form.
        """
        tFileId, tFileName = tempfile.mkstemp()
        tFile     = open(tFileName,'w')
        tFile.write("From: %s\n"%userEmail+feedbackText)
        tFile.close()
        os.system("""mail -s "response from DBS data discovery" cms-dbs-support@cern.ch < %s"""%tFileName)
#        os.system("""mail -s "response from DBS data discovery" vk@mail.lns.cornell.edu < %s"""%tFileName)
        os.remove(tFileName)
        page = self.genTopHTML(userMode=userMode)
        page+= """<p class="sectionhead_tight">Your feedback is greatly appreciated and has been send to DBS support team.</p>"""
        page+= self.genBottomHTML()
        return page
#    sendFeedback.exposed=True

    def lookupUserId(self,userId):
        if  userId=="guest": # not authenticated user trying to store history
            _user = ""
            try:
                token = SecurityToken()
                _user = token.dn
                if _user and _user!=userId:
                   userId=_user
#                cherrypy.response.cookie['DBSDD_username']
            except:
                pass
        return userId
    lookupUserId.expose=True

    def storeHistory(self,dbsInst,userId,actionString,alias=''):
        userId=self.lookupUserId(userId)

        # update DB history
        # try to insert dbsInst, otherwise get its id from DB
        dbsid=0
        try:
           res=DD_INSTANCE.insert().execute(dbsinstance=dbsInst)
           dbsid=res.last_inserted_ids()[0]
        except:
           if self.verbose:
              self.writeLog(getExcept())
           printExcept()
           c = DD_USER.select(and_(DD_INSTANCE.c.dbsinstance==dbsInst)).execute()
           r = c.fetchone()
           if r and r[0]:
              dbsid = r[0]
        if not dbsid:
           raise "Fail to find dbsid in DBS DD history for %s"%(dbsInst,)
        # select cmdid from given history string
        c = sqlalchemy.select([DD_COMMAND.c.id],DD_COMMAND.c.command==actionString).execute()
        r = c.fetchone()
        if r and r[0]:
           cid = r[0]
        else:
           res=DD_COMMAND.insert().execute(command=actionString,alias=alias)
           cid=res.last_inserted_ids()[0]
        # try to insert user id, if fail get them from DB
        uid=0
        try:
           res=DD_USER.insert().execute(userid=userId)
           print "after DD_USER insert",res
           uid=res.last_inserted_ids()[0]
           print "after DD_USER insert, uid=",uid
        except:
           c = DD_USER.select(and_(DD_USER.c.userid==userId)).execute()
           r = c.fetchone()
           if r and r[0]:
              uid = r[0]
        if not uid:
           raise "Fail to find uid in DBS DD history for %s"%(userId,)
        # insert into DD_HISTORY date/userid/cmdid
#        iDate=time.strftime("%Y/%m/%d",time.localtime())
#        iDate=time.strftime("%d-%b-%Y %H:%M:%S",time.localtime())
#        iTime=str(time.strftime("%H:%M:%S",time.localtime()))
#        DD_HISTORY.insert().execute(userid=uid,cmdid=cid,dbsid=dbsid,history_date=iDate,history_time=iTime)
        DD_HISTORY.insert().execute(userid=uid,cmdid=cid,dbsid=dbsid,history_date=sqlalchemy.func.current_timestamp())

    def historySearch(self,iYear,iMonth,oYear,oMonth,userId,**kwargs):
        cList=[]
        try:
            iDate='%s-%s-%s'%(iYear,monthId(iMonth),'01')
            oDate='%s-%s-%s'%(oYear,monthId(oMonth),'31')
            c = select([DD_HISTORY.c.history_date,DD_HISTORY.c.history_time,DD_COMMAND.c.command],
                        and_(
                             DD_HISTORY.c.userid==DD_USER.c.id,
                             DD_HISTORY.c.cmdid==DD_COMMAND.c.id,
                             DD_HISTORY.c.history_date>=iDate,DD_HISTORY.c.history_date<=oDate,
                             DD_USER.c.userid==userId
                            ),
                        use_labels=True,distinct=True,
                        order_by=[desc(DD_HISTORY.c.history_date),desc(DD_HISTORY.c.history_time)]).execute()
            cList=c.fetchall()
        except:
            if self.verbose:
               self.writeLog(getExcept())
            if not self.quiet:
               printExcept()
            pass
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        # Please note, I'm using object type instead of element, since at startup history tab is
        # hidden and id="allHistory" is invisible for registration. When I choose history
        # tab it's class name changed to be visible and my object class can find out
        # id="allHistory"
        page="""<ajax-response><response type="element" id="historySearchResults">"""
        for rows in cList:
            nameSpace={
                       'date'      : str(rows[0]),
                       'time'      : str(rows[1]),
                       'action'    : str(rows[2])
                      }
            t = templateHistory(searchList=[nameSpace]).respond()
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    historySearch.exposed=True

    def getHistory(self,userId,iLimit=20,**kwargs):
        cList=[]
        try:
            c = select([DD_HISTORY.c.history_date,DD_HISTORY.c.history_time,DD_COMMAND.c.command],
                        and_(
                             DD_HISTORY.c.userid==DD_USER.c.id,
                             DD_HISTORY.c.cmdid==DD_COMMAND.c.id,
                             DD_USER.c.userid==userId
                            ),
                        use_labels=True,distinct=True,limit=iLimit,
                        order_by=[desc(DD_HISTORY.c.history_date),desc(DD_HISTORY.c.history_time)]).execute()
            cList=c.fetchall()
        except:
            if self.verbose:
               self.writeLog(getExcept())
            if not self.quiet:
               printExcept()
            pass
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        # Please note, I'm using object type instead of element, since at startup history tab is
        # hidden and id="allHistory" is invisible for registration. When I choose history
        # tab it's class name changed to be visible and my object class can find out
        # id="allHistory"
        page="""<ajax-response><response type="object" id="allHistory">"""
        for rows in cList:
            nameSpace={
                       'date'      : str(rows[0]),
                       'time'      : str(rows[1]),
                       'action'    : str(rows[2])
                      }
            t = templateHistory(searchList=[nameSpace]).respond()
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getHistory.exposed=True

    def history(self,dbsInst,userId,actionString,**kwargs):
        """
            History updater
        """
        alias=''
        if kwargs.has_key('alias'): alias=kwargs['alias']
        try:
            self.storeHistory(dbsInst,userId,actionString,alias)
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            pass
        # AJAX wants response as "text/xml" type
#        iDate=time.strftime("%Y-%m-%d",time.localtime())
#        iTime=time.strftime("%H:%M:%S",time.localtime())
#        self.setContentType('xml')
#        nameSpace={
#                   'date'      : iDate, 
#                   'time'      : iTime, 
#                   'action'    : actionString
#                  }
#        t = templateHistory(searchList=[nameSpace]).respond()
#        page="""<ajax-response><response type="object" id="sessionHistory">"""
#        page+= str(t)
#        page+="</response></ajax-response>"
#        if self.verbose==2:
#           self.writeLog(page)
#        return page
    history.exposed=True

    def register(self,nameinput,passinput,**kwargs):
        """
           Register users with internal history DB.
        """
        return self.index()
    register.exposed=True

    def getAppConfigs(self,dbsInst,appPath,procPath,ajax=1,userMode="user",**kwargs):
        """
            Application configs retriever
        """
        self.helperInit(dbsInst)
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="appConfigs">"""
        else:
           page=self.genTopHTML(userMode=userMode)
           page+=self.whereMsg('Navigator :: Results :: Configuration file(s)',userMode)
        for item in self.helper.listApplicationConfigsContent(appPath,procPath):
            softRel,name,content,ver,type,ann,cDate,cBy,mDate,mBy = item
            nameSpace={
                       'appPath'   : appPath,
                       'rel'       : softRel,
                       'dbsInst'   : dbsInst,
                       'host'      : self.dbsdd,
                       'name'      : name, 
                       'content'   : content, 
                       'version'   : ver, 
                       'type'      : type, 
                       'annotation': ann, 
                       'cDate'     : cDate, 
                       'cBy'       : cBy, 
                       'mDate'     : mDate, 
                       'mBy'       : mBy, 
                       'userMode'  : userMode
                      }
            t = templateAppConfigsContent(searchList=[nameSpace]).respond()
            page+=str(t)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getAppConfigs.exposed=True

#    def getAppConfigs_v1(self,dbsInst,appPath,ajax=1,**kwargs):
#        """
#            Application configs retriever
#        """
#        self.helperInit(dbsInst)
#        rList=self.helper.getSoftwareReleases()
#        try:
#            givenRelease=appPath.split("/")[1]
#            rList.remove(givenRelease)
#        except:
#            pass
#        nameSpace={'selTag':"",'changeFunction':"",'name':"selRels",'iList':rList}
#        t = templateSelect(searchList=[nameSpace]).respond()
#        rels=str(t)
#        nameSpace={
#                   'appPath'   : appPath,
#                   'dbsInst'   : dbsInst,
#                   'host'      : self.dbsdd,
#                   'configList': self.helper.listApplicationConfigs(appPath),
#                   'releases'  : rList
#                  }
#        t = templateAppConfigs(searchList=[nameSpace]).respond()
#        if int(ajax):
#           self.setContentType('xml')
#           page="""<ajax-response><response type="object" id="appConfigs">"""
#        else:
#           page=self.genTopHTML()
#           page+= self.genResultsHTML()
#        page+= str(t)
#        if int(ajax):
#           page+="</response></ajax-response>"
#        else:
#           page+=self.genBottomHTML()
#        if self.verbose==2:
#           self.writeLog(page)
#        return page
#    getAppConfigs_v1.exposed=True

    def compareAppConfigs(self,dbsInst,appConfig,iRel,oRel,userMode='user'):
#        page = "diff %s between %s %s"%(appConfig,iRel,oRel) 
        c1 = self.helper.getConfigContentByName(dbsInst,appConfig,iRel)
        c2 = self.helper.getConfigContentByName(dbsInst,appConfig,oRel)
        page=self.genTopHTML(userMode=userMode)
#        if c1==c2:
#           page+="<p>The '%s' was identical in release '%s' and '%s'</p>"%(appConfig,iRel,oRel)
#        else:
#           page+=textDiff(c1,c2,iRel,oRel,appConfig)

        diff = difflib.HtmlDiff()
        nameSpace={'config':appConfig,'iRel':iRel,'oRel':oRel,'iConf':c1,'oConf':c2}
        t = templateConfigDiff(searchList=[nameSpace]).respond()
        page+=str(t)
        page+= diff.make_table(c1.splitlines(1),c2.splitlines(1),'','',True)
        t = templateDiffLegend(searchList=[{}]).respond()
        page+=str(t)

        page+=self.genBottomHTML()
        return page
    compareAppConfigs.exposed=True

#    def showMessage(self,msg):
#        page=self.genTopHTML()
#        page+=msg
#        page+=self.genBottomHTML()
#        return page
#    showMessage.exposed=True

    def checkUser(self,userId,**kwargs):
        """
           Check existence of user name in history DB
        """
        msg=""
        # check user name
        c = select([DD_USER.c.name],DD_USER.c.userid==userId).execute()
        r = c.fetchone()
        if r and r[0]==userId:
           msg=userId
           c = DD_USER.select(and_(DD_USER.c.userid==userId)).execute()
           r = c.fetchone()
#           if (not r) or (r and r[2]!=password):
#              msg='wrong password'
        else:
           try:
               res=DD_USER.insert().execute(userid=userId)
               msg=userId
           except:
               msg="fail";
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="historyUserName">"""
        page+= msg
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    checkUser.exposed=True

    def selectApplications(self,dbsInst,**kwargs):
        """
           Retrieve list of application for given dbs instance
        """
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="selectApps">"""
        page+="""<select id="appSelector" onchange="replace('navSelector');showLoadingMessage('selectPrim');ajaxSelectPrim();replace('selectTier','to be defined')">"""
        page+='<option value="">Select</option>'
        aList = self.helper.listApplications('*')
        aList.reverse()
        for item in aList:
            family = item.get('family')
            ver    = item.get('version')
            exe    = item.get('executable')
            appVal = '/%s/%s/%s'%(ver,family,exe)
            page+='<option value="%s">%s</option>'%(appVal,appVal)
        page+= '</select>'
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    selectApplications.exposed=True

    def selectPrimaryDatasets(self,dbsInst,app,**kwargs):
        """
           Retrieve list of primary data tier for given dbs instance and app
        """
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="selectPrim">"""
        page+="""<select id="primSelector" onchange="replace('navSelector');showLoadingMessage('selectTier');ajaxSelectTier()">"""
        page+='<option value="">Select</option>'
        aList = self.helper.listDatasetsFromApp(app)
        aList.reverse()
        for item in aList:
            empty,dataset,tier,proc = string.split( item['datasetPathName'], "/" )
            page+='<option value="%s">%s</option>'%(dataset,dataset)
        page+= '</select>'
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    selectPrimaryDatasets.exposed=True

    def selectDataTiers(self,dbsInst,app,prim,**kwargs):
        """
           Retrieve list of data tiers for given dbs instance, app, and primD
        """
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="selectTier">"""
        page+="""<select id="tierSelector" onchange="replace('navSelector')">"""
        page+='<option value="">Select</option>'
        # in new api we will have listTiers
#        aList = self.helper.api.listTiers('*')

        # so far all data tiers comes from app and prim
        aList = self.helper.listDatasetsFromApp(app)
        aList.reverse()
        page+='<option value="All">All</option>'
        for item in aList:
            empty,dataset,tier,proc = string.split( item['datasetPathName'], "/" )
            if prim==dataset:
               page+='<option value="%s">%s</option>'%(tier,tier)
        page+= '</select>'
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    selectDataTiers.exposed=True

    def getDataDescription(self,dbsInst,processedDataset="",userMode='user',**kwargs):
        """ 
            Get data description.
        """
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
#        self.setContentType('xml')
#        page="""<ajax-response><response type="element" id="floatDataDescription">"""
        page=self.genTopHTML(userMode=userMode)
        page+=self.whereMsg('Navigator :: Results :: Data description',userMode)
        description=""
        dList=self.helper.getDataDescription(processedDataset)
        # get formatted output of dataset details
        nameSpace={'dList' : dList, 'dataset':processedDataset,'userMode':userMode }
        t = templateDatasetDetails(searchList=[nameSpace]).respond()
        description+=str(t)
        page+=description

        page+=self.genBottomHTML()
#        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDataDescription.exposed=True

    def getTableTemplate(self,func,dbsInst,lfn,msg,ajax,userMode='user',**kwargs):
#        self.htmlInit()
        self.helperInit(dbsInst)
        tList,iList=func(dbsInst,lfn,userMode)
        p=content=""
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="element" id="floatDataDescription">"""
        else:
           page=self.genTopHTML(userMode=userMode)
           page+=self.whereMsg('Navigator :: Results :: LFN list :: %s :: LFN=%s'%(msg,lfn),userMode)
        if  len(iList):
            try:
                nameSpace={'branch':iList}
                t = templateTableBody(searchList=[nameSpace]).respond()
                content+=str(t)
                nameSpace={'header':tList,'content':content}
                t = templateTable(searchList=[nameSpace]).respond()
                p=str(t)
            except:
                if self.verbose:
                   self.writeLog(getExcept())
                printExcept()
                p="No information about '%s' found for\nDBS='%s'\nLFN='%s'"%(msg,dbsInst,lfn)
        else:
           p="No information about '%s' found for\nDBS='%s'\nLFN='%s'"%(msg,dbsInst,lfn)
        if int(ajax):
           nameSpace={'title':msg,'description':p,'className':'float_help_box'}
           t = templateFloatBox(searchList=[nameSpace]).respond()
           page+=str(t)+"</response></ajax-response>"
        else:
           page+=p
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page

    def getLFN_Branches(self,dbsInst,lfn,ajax=0,userMode='user',**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Branches,dbsInst,lfn,'ROOT branches',ajax,userMode)
        return page
    getLFN_Branches.exposed=True

    def getLFN_Lumis(self,dbsInst,lfn,ajax=0,userMode='user',**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Lumis,dbsInst,lfn,'LFN lumis',ajax,userMode)
        return page
    getLFN_Lumis.exposed=True

#    def getLFN_Algos(self,dbsInst,lfn,ajax=0,**kwargs):
#        page=self.getTableTemplate(self.helper.getLFN_Algos,dbsInst,lfn,'LFN algorithms',ajax)
#        return page
#    getLFN_Algos.exposed=True

#    def getLFN_Tiers(self,dbsInst,lfn,ajax=0,**kwargs):
#        page=self.getTableTemplate(self.helper.getLFN_Tiers,dbsInst,lfn,'LFN tiers',ajax)
#        return page
#    getLFN_Tiers.exposed=True

#    def getLFN_Parents(self,dbsInst,lfn,ajax=0,**kwargs):
#        page=self.getTableTemplate(self.helper.getLFN_Parents,dbsInst,lfn,'LFN parents',ajax)
#        return page
#    getLFN_Parents.exposed=True

    def getFloatBox(self,title="",description="",className="",**kwargs):
        """ 
            Get data description.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="floatDataDescription">"""
        nameSpace={
                   'title'       : title,
                   'description' : description,
                   'className'   : className
                  }
        t = templateFloatBox(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getFloatBox.exposed=True

    def getMoreInfo(self,dbsInst,path,appPath,id,userMode="user",**kwargs):
        """ 
            Get data description.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="%s">"""%id
        path=path.replace('#','%23')
#        dbsInstURL="https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_global_writer/servlet/DBSServlet"
        dbsInstURL=DBS_DLS_INST[dbsInst]
        PhedexURL="https://cmsdoc.cern.ch:8443/cms/aprom/phedex/prod/Request::Create"
        nameSpace={'host':self.dbsdd,'dbsInst':dbsInst,'path':path,'appPath':appPath,'id':id,'userMode':userMode,'dbsInstURL':dbsInstURL,'PhedexURL':PhedexURL}
        t = templateMoreInfo(searchList=[nameSpace]).respond()
#        t = templatePanelMore(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getMoreInfo.exposed=True

    def getRss(self,ajax=1,userMode="user"):
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="element" id="rss_list">"""
        else:
           page=""
        dbsList=[]
        if userMode=="user":
           dbsList.append(DBSGLOBAL)
        else:
           dbsList=self.dbsList
        for dbs in dbsList:
            rssList=findRssFiles('rss/%s'%dbs)
            nameSpace={
                       'userMode'    : userMode,
                       'host'        : self.dbsdd,
                       'dbs'         : dbs,
                       'rssList'     : rssList
                      }
            t = templateRssList(searchList=[nameSpace]).respond()
            page+=str(t)
        if int(ajax):
           page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getRss.exposed=True

    def getProdRequestPage(self,**kwargs):
#        data = self.prodRequestServer.sendPostMessage("/ProdRequest/getRequestsByDataset?primary_dataset=this+is+a+test",{},debug=1)
#        print data
        page=self.genTopHTML(onload="registerAjaxProdRequestCalls();")
        page+="Response from ProdRequest<br/ >"
        page+="""<a href="javascript:ajaxGetProdRequest()">get</a>"""
        page+="""<div id="id_ProdRequest"></div>"""
        page+=self.genBottomHTML()
        return page
    getProdRequestPage.exposed=True

    def getProdRequest(self,prim,id,**kwargs):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page = self.prodRequestServer.sendPostMessage("/ProdRequest/getRequestsByDataset?primary_dataset=%s&id=%s"%(prim,id),{},debug=1)
#        page = """
#<ajax-response><response type="element" id="%s">
#<div class="float_ProdRequest">
#<div align="right"><a href="javascript:HideTag('%s')">close &#8855;</a><hr class="dbs" /></div>
#Response from ProdRequest will be placed here<br />
#primaryDataset='%s'
#</div>
#</response></ajax-response>
#"""%(id,id,prim)
        if self.verbose==2:
           self.writeLog(page)
        return page
    getProdRequest.exposed=True

    def getLucene(self,method,params="",**kwargs):
#        print "\n\ngetLucene",params,kwargs
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
#        param = {'method':'lookup','term':'block.subsys.NumberParameter129=111'}
#        data = self.lucene.sendPostMessage("/DBSLookupWeb/DBSLookup",param,debug=1)
#        data = self.lucene.sendPostMessage("/DBSLookupWeb/DBSLookup",kwargs,debug=1)
        if params:
           params=params.replace('&lt;','%3C')
           params=params.replace('&gt;','%3E')
           method+='?'+params
        data = self.pServer.sendPostMessage("/DBSSearch/%s"%method,{},debug=1)
        if string.find(data,"""<?xml version="1.0" encoding="ISO-8859-1"?>""")!=-1:
           res=string.split(data,"""<?xml version="1.0" encoding="ISO-8859-1"?>""")
           return res[1]
        else:
           return data
    getLucene.exposed=True

    def makeLine(self,id,**kwargs):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="makeMenu_%s">"""%id
        sectionList=self.sectionDict.keys()
        sectionList.sort()
        tableList = self.sectionDict['Algorithm']
        tableList.sort()
        nameSpace={'id':int(id),'sectionList':sectionList,'tableList':tableList}
        t = templateSelectLine(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    makeLine.exposed=True

    def makeSelect(self,iList,tag,name="",changeFunction="",selTag="",style="",**kwargs):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="%s">"""%tag
        nameSpace={'iList':iList,'changeFunction':changeFunction,'selTag':selTag,'name':name,'style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    makeSelect.exposed=True

    def getSectionTables(self,dbsInst,section,id,**kwargs):
        tList = self.sectionDict[section]
        changeFunction="ChangeCols(%s)"%id
        return self.makeSelect(tList,"divSectionTables_%s"%id,"sectionTables",changeFunction,"sectionTables_%s"%id,"width:160px")
    getSectionTables.exposed=True

    # this method can be used for auto-completion forms, it returns a string of columns
    # see YUI, http://developer.yahoo.com/yui/autocomplete/
    def getTableColumn(self,dbsInst,table,column,**kwargs):
        self.helperInit(dbsInst)
        page=""
        if kwargs.has_key('autocomplete'):
           if kwargs['autocomplete']=="off":
              return page
        whereDict={}
        if  kwargs.has_key('query'):
            key='%s.%s'%(table,column)
#            val=kwargs['query'].replace('*','').replace('%','') # remove wildcard
#            whereDict[key]='%'+val # we will do where like '%s%', see helper.getTableContent
            whereDict[key]='%'+kwargs['query'] # we will do "where like '%s%'"
        # since this method is used only in auto-completion forms, restrict output to 10 results
        row=0
        limit=10
        natList = natsort24(list(self.helper.getTableColumn(table,column,row,limit,whereDict) ))
#        print natList,type(natList)
        for item in natList:
            page+="%s\n"%item
        return page
    getTableColumn.exposed=True

    def getTableColumns(self,dbsInst,tableName,id,**kwargs):
        self.helperInit(dbsInst)
        tList = ['All']+self.helper.getTableColumns(tableName)
        changeFunction=""
        return self.makeSelect(tList,"tableCols_%s"%id,"tableCols",changeFunction,"tableColumns_%s"%id,"width:200px")
    getTableColumns.exposed=True

    def getTableColumnsFromSection(self,dbsInst,section,id,**kwargs):
        """
           This is a fake, since AJAX calls are async, and I need to get
           columns for the first table in a section, I made explicit call.
        """
        self.helperInit(dbsInst)
        tList = self.sectionDict[section]
        tableName = tList[0]
        tList = ['All']+self.helper.getTableColumns(tableName)
        changeFunction=""
        return self.makeSelect(tList,"tableCols_%s"%id,"tableCols",changeFunction,"tableColumns_%s"%id,style="width:160px")
    getTableColumnsFromSection.exposed=True

    def finderExample(self,userMode='user'):
        page = self.genTopHTML(intro=False,userMode=userMode)
        page+= """
<object width='800' height='600'>
<param name='movie' value='images/DBS2_Finder_demo.swf'>
<embed src='images/DBS2_Finder_demo.swf' width='800' height='600'>
</embed>
</object>
"""
#        page+= """
#<object width='800' height='600'>
#<param name='movie' value='images/DataDiscoveryFinder.swf'>
#<embed src='images/DataDiscoveryFinder.swf' width='800' height='600'>
#</embed>
#</object>
#"""

#        page+= """Here you can find a few examples demonstrating usage of Finder on discovery page.

#        <p />
#        <img src="images/FinderExample1.jpg" alt="selectRun" />
#        Here is an example how to make a selection of different items in a finder.
#        In order to make your select please click on plus button and choose from appropriate menu.
#        <p />

#        <img src="images/FinderExample2.jpg" alt="selectRun" />
#        This example demostrate how to make a selection while applying some cuts.
#        """
        page+= self.genBottomHTML()
        return page
    finderExample.exposed=True

    def constructQueryParameters_v1(self,dbsInst,kwargs):
        iDict={}
        iList=[]
        whereClause=[]
        parameters =""
        for key in kwargs.keys():
            if key=="_": continue
            parameters+=kwargs[key]
            pList = kwargs[key].split("_newparam_")
            for item in pList:
                table,col,op,where,limit=string.split(item,"__")
#                print "### '%s'-'%s'-'%s'-'%s'-'%s'"%(table,col,op,where,limit)
                if where:
                   whereClause.append((table,col,op,where))
                if col.lower()=='all': col='*'
                addToDict(iDict,table,col)
                iList.append("%s.%s"%(self.helper.dbManager.getDBTableName(dbsInst,table),col))
#        print "looking for",iDict,whereClause
        return parameters,iList,whereClause

    def constructQueryParameters(self,kwargs):
        whereClause=""
        iList=[]
        if kwargs.has_key('tableColumnList'):
           _list=kwargs['tableColumnList']
           if type(_list) is types.ListType:
              iList=list(_list)
           else:
              if _list[0]=="[" and _list[-1]=="]":
                 iList+=_list[1:-1].replace("[","").replace("]","").replace("'","").replace(" ","").split(",")
              else:
                 iList.append(_list)
        if kwargs.has_key('where') and kwargs['where']:
           where="( "+kwargs['where']+" )"
           whereClause=where
        
        parameters=""
        keyList=kwargs.keys()
        for key in keyList:
            if key!=keyList[0]:
               parameters+="&amp;"
            parameters+="%s=%s"%(key,kwargs[key])
#        parameters=urllib.quote(parameters)
        return parameters,iList,whereClause

    def finderSearch(self,dbsInst,limit=0,offset=0,userMode='user',ajax=0,**kwargs):
        limit=int(limit)
        offset=int(offset)
        if  int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page="""<ajax-response><response type="object" id="results_finder">"""
        else:
            page=self.genTopHTML()

#        t = templateFinderTitle(searchList=[{'ext':":: Results",'host':self.dbsdd,'userMode':userMode}]).respond()
#        page+=self.whereMsg(str(t),userMode)
        page+=self.whereMsg('Finder :: Results',userMode)
        parameters,iList,whereClause=self.constructQueryParameters(kwargs)
        queryInXML=self.saveQueryToXML(iList,whereClause)
        page+="""<textarea id="queryXML" class="hide">%s</textarea>"""%urllib.quote(queryInXML)
        page+="""<table width="100%%"><tr><td align="left">
Save query as:
<input type="hidden" name="dbsInst" id="dbsInst" value="%s" />
<input type="text" name="finderAlias" size="30" id="kw_alias" />
<input type="button" value="Save" id="saveQuery" onclick="javascript:ajaxFinderResultStoreQuery();" />
<p id="query_confirmation"></p>
</td></tr></table>
"""%dbsInst
        if not validator(whereClause):
           page+="Wrong where clause expression '%s'"%whereClause
           page+=self.genBottomHTML()
           return page
        try:
            query,oList = self.helper.queryMaker(iList,whereClause,limit,offset)
        except:
            page+=getExceptionInHTML()
            if  int(ajax):
                page+="</response></ajax-response>"
            else:
                page+=self.genBottomHTML()
            pass
            return page
            
#        t=templateLookupFromFinder(searchList=[{'dbsInst':dbsInst,'params':parameters,'userMode':userMode}]).respond()
#        page+=str(t)
        lookup="""Lookup <a href="findDSFromFinder?dbsInst=%s&userMode=%s&%s">processed</a> or <a href="findADSFromFinder?dbsInst=%s&userMode=%s&%s">analysis</a> datasets from this results"""%(dbsInst,userMode,parameters,dbsInst,userMode,parameters)
        # retrieve actual column names from tables
        tList=[]
        dateIdxList=[]
        for item in iList:
            table,col=item.split(".")
            if col.lower()!="*":
               tList.append("%s<br />%s"%(table,col))
               if col.lower().find("date")!=-1:
                  dateIdxList.append(len(tList)-1)
            else:
               cols=self.helper.getTableColumns(table)
               for col in cols:
                   tList.append("%s<br />%s"%(table,col))
                   if col.lower().find("date")!=-1:
                      dateIdxList.append(len(tList)-1)
        if  type(oList) is types.ListType:
            if  len(oList):
                next=""
                if  limit and len(oList)>limit:
                    next="""<p><a href="finderSearch?userMode=%s&dbsInst=%s&limit=%s&offset=%s&%s">Next %s</a> results</p>"""%(userMode,dbsInst,limit,limit+offset,parameters,limit)
                pager="""<table width="100%%"><tr><td align="left">%s</td><td align="right">%s</td></tr></table>"""%(lookup,next)
                page+=pager+"""<hr class="dbs" />"""
                t = templateQueryOutput(searchList=[{'query':query,'iList':tList,'oList':oList,'dateIdxList':dateIdxList,'userMode':userMode}]).respond()
                page+=str(t)
                page+="""<hr class="dbs" />"""+pager
            else:
                page+="No more results"
        else:
            if not oList.count('<table>'):
               page+="""<div class="box_red">%s</div>"""%oList.replace('<','&lt;').replace('>','&gt;')
            else:
               page+=oList
        if  int(ajax):
            page+="</response></ajax-response>"
        else:
            page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    finderSearch.exposed=True

    def findDSFromFinder(self,dbsInst,userMode,**kwargs):
        self.helperInit(dbsInst)
        parameters,iList,whereClause=self.constructQueryParameters(kwargs)
        if not iList.count('Block.Path'): iList.append('Block.Path')
#        aList=['Block.Path']
#        for item in aList:
#            try:
#                iList.remove(item)
#            except:
#                pass
#        for item in aList:
#            iList.append(item)
        query,oList = self.helper.queryMaker(iList,whereClause)
        pList=[]
        for item in oList:
            if item==oList[0]: continue # we skip first row since it's column names
            path=item.values()[-1] # get only last item from results which is Block.Path
            if not pList.count(path): pList.append(path)
        page=self.getData(dbsInst,proc=pList,ajax=0,userMode=userMode)
        return page
    findDSFromFinder.exposed=True

    def findADSFromFinder(self,dbsInst,userMode,**kwargs):
        self.helperInit(dbsInst)
        parameters,iList,whereClause=self.constructQueryParameters(kwargs)
        if not iList.count('AnalysisDataset.Name'):
           iList.append('AnalysisDataset.Name')
        query,oList = self.helper.queryMaker(iList,whereClause)
        bindparams=[]
        wClause=""
        idx=0
        for item in oList:
            if item==oList[0]: continue # we skip first row since it's column names
            path=item.values()[-1] # get only last item from results which is Block.Path
            bind_param="AnalysisDataset_Name_%s"%idx
            if not wClause:
               wClause="( tad.Name = :%s"%bind_param
            else:
               wClause+=" OR tad.Name = :%s"%bind_param
            bindparams.append(sqlalchemy.bindparam(key=bind_param,value=path))
            idx+=1
        wClause+=" )"
        whereClause = sqlalchemy.text(text=wClause,engine=self.helper.dbManager.engine[dbsInst],bindparams=bindparams)
        return self.findAnalysisDS(dbsInst=dbsInst,userMode=userMode,_idx=0,pagerStep=RES_PER_PAGE,ads_name=whereClause,op_ads_name='whereClause')
    findADSFromFinder.exposed=True

    # this method can be used for auto-completion forms, it returns a string of columns
    # see YUI, http://developer.yahoo.com/yui/autocomplete/
    def getAliasesFromHistoryDB(self,query,**kwargs):
        cList=[]
        try:
            query=query.replace('*','').replace('%','')
            sel = select([DD_COMMAND.c.alias],DD_COMMAND.c.alias.like('%%%s%%'%query),
                        use_labels=True,distinct=True )
            c=sel.execute()
            cList=c.fetchall()
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            pass
        page=""
        for item in cList:
            page+="%s\n"%item[0]
        return page
    getAliasesFromHistoryDB.exposed=True

    def saveQueryToXML(self,iList,whereClause):
        xmlOutput="""<?xml version="1.0" encoding="utf-8"?><ddRequest>"""
        for item in iList:
            xmlOutput+="""<select column="%s" />"""%(item)
        if  whereClause:
            xmlOutput+="""<where clause="( %s )" />"""%(whereClause)
        xmlOutput+="""</ddRequest>"""
        queryInXML=xmlOutput.strip()
        return queryInXML

    def finderStoreQueryInXML(self,dbsInst,userId,alias,**kwargs):
        iList=[]
        whereClause=""
        queryInXML=""
        for key in kwargs.keys():
            if key=="_": continue
            if key=="params":
               pList = kwargs[key].split("_table_")
               for item in pList:
                   if item and not iList.count(item):
                      iList.append(item)
            if key=="where":
               whereClause=kwargs[key]
            if key=="queryXML":
               queryInXML=urllib.unquote(kwargs[key])
        if  not queryInXML:
            queryInXML=self.saveQueryToXML(iList,whereClause)
        try:
            self.storeHistory(dbsInst,userId,queryInXML,alias)
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            pass
    finderStoreQueryInXML.exposed=True

#    def finderStoreQuery(self,dbsInst,userId,alias,**kwargs):
#        iList=[]
#        for key in kwargs.keys():
#            if key=="_": continue
#            pList = kwargs[key].split("_newparam_")
#            for item in pList:
#                table,col,op,where=string.split(item,"__")
#                if col.lower()=='all': col='*'
#                iList.append("%s.%s"%(self.helper.dbManager.getDBTableName(dbsInst,table),col))
#        query,oList = self.helper.queryMaker(iList)
#        try:
#            self.storeHistory(dbsInst,userId,query,alias)
#        except:
#            if self.verbose:
#               self.writeLog(getExcept())
#            printExcept()
#            pass
#    finderStoreQuery.exposed=True

    def finderSearchQuery(self,userId,alias,**kwargs):
        userId=self.lookupUserId(userId)
        cList=[]
        try:
#            c = select([DD_COMMAND.c.command,DD_COMMAND.c.alias],
            oSel = [DD_HISTORY.c.history_date,DD_HISTORY.c.history_time,DD_COMMAND.c.command,DD_COMMAND.c.alias,DD_INSTANCE.c.dbsinstance]
            sel = select(oSel,
                        and_(
                             DD_HISTORY.c.userid==DD_USER.c.id,
                             DD_HISTORY.c.cmdid==DD_COMMAND.c.id,
                             DD_USER.c.userid==userId,
                             DD_COMMAND.c.alias.like('%%%s%%'%alias)
                            ),
                        use_labels=True,distinct=True,
                        order_by=[desc(DD_HISTORY.c.history_date),desc(DD_HISTORY.c.history_time)]
                      )
            c=sel.execute()
            cList=c.fetchall()
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            pass
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page ="""<ajax-response><response type="object" id="myQueries">"""
        page+="""<div class="sectionhead_tight">Upon your request the following query aliases were found:</div>"""
        page+="""<table cellspacing="0" cellpadding="0">"""
        p = ""
        count=0
        for item in cList:
            oDate,oTime,oCmd,oName,dbsInst=item
            id="myQuery_%s"%count
            # in order to make AJAX works I strip off <?xml...> from query stored in DB
            # and pass around for another ajax call stripped XML request
            xml=oCmd
            oCmd=oCmd.replace("""<?xml version="1.0" encoding="utf-8"?>""","")
            oCmd=urllib.quote(oCmd)
            if  oName:
                # TODO: I need to query DB which dbs instance were used and pass it here.
                cmd="""<a href="javascript:ajaxExecuteQuery('%s','%s')">%s</a>"""%(dbsInst,oCmd,oName)
#                xmlRef="""<a href="printXML?input=%s"><img src="images/xml.png" alt="xml" style="border:none" /></a>"""%oCmd
#                txtRef="""<a href="convertXMLTOTXT?input=%s&html=1&ajax=0"><img src="images/txt.png" alt="txt" style="border:none" /></a>"""%oCmd
                xmlRef="""<a href="javascript:ajaxPrintXML('%s','%s')"><img src="images/xml.png" alt="xml" style="border:none" /></a>"""%(oCmd,id)
                txtRef="""<a href="javascript:ajaxConvertXMLTOTXT('%s','%s')"><img src="images/txt.png" alt="txt" style="border:none" /></a>"""%(oCmd,id)
                nameSpace={'date':str(oDate),'action':str(cmd),'xml':xmlRef,'txt':txtRef,'id':id}
                t = templateHistory(searchList=[nameSpace]).respond()
                p+=str(t)
                count+=1
        if not count:
           page+="""<tr><td><div class="sectionhead_tight">No queries were found for provided alias name: '%s'</div></td></tr>"""%alias
        else:
           page+=p
        page+="</table>"
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    finderSearchQuery.exposed=True

    def printXML(self,input,id,ajax=0,**kwargs):
        xml="""<?xml version="1.0" encoding="utf-8"?><br/>"""+urllib.unquote(input).replace("><","><br/><")
        xmlOutput=xml.replace("<","&lt;").replace(">","&gt;").replace("&lt;br/&gt;","<br />")
        if int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page ="""<ajax-response><response type="element" id="%s">"""%id
            page+="""<div class="float_snippet">"""
        else:
            page = self.genTopHTML()
        if int(ajax):
           page+="""<div align="right"><a href="javascript:HideTag('%s')">close &#8855;</a><hr class="dbs" /></div> """%id
        page+=xmlOutput
#        page+="<code>"+xmlOutput+"</code>"
        page+="""<p/><hr class="dbs" /><div>You may use this XML snippet with <a href="https://twiki.cern.ch/twiki/bin/view/CMS/DDExplorer">DDExplorer</a> a command line interface to Finder.</div>"""
        if int(ajax):
           page+="</div>"
           page+="""</response></ajax-response>"""
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
#        print page
        return page
    printXML.exposed=True

    def executeSQLQuery(self,dbsInst,query,**kwargs):
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results_finder">"""
        if  query.find("<ddRequest>")!=-1:
            # in order to make AJAX works I strip off <?xml...> from query stored in DB
            # let's check that input doesn't have it
            xml="""<?xml version="1.0" encoding="utf-8"?>"""
            if  query.find(xml)!=-1:
                query+=xml
            query,oList=self.cliHandler(dbsInst,query,xmlOutput=0) 
        else:
            q = string.lower(query.strip())
            if  q.find("select")==-1:
                page+="You are attempted execute non select query, it's forbidden"
                page+="</response></ajax-response>"
                return page
            oList = self.helper.executeSQLQuery(query)
        # get list of table.col and get their actual names from DB
        tList=[]
        dateIdxList=[]
        for item in query.split():
            if item.find(".")!=-1:
               table,col=item.split(".")
               tList.append("%s<br />%s"%(table,col)) 
               if col.lower().find("date")!=-1:
                  dateIdxList.append(len(tList)-1)
        # proceed with query
        if  type(oList) is not types.ListType:
            page+=oList
        else:
            # if the query was used Table.Col form and we found all columns
#            print tList,oList
            if  len(tList)==len(oList[0]):
                userMode='user'
                t = templateQueryOutput(searchList=[{'query':query,'iList':tList,'oList':oList,'dateIdxList':dateIdxList,'userMode':userMode}]).respond()
            else:
                nameSpace={'branch':oList[1:]}
                t = templateTableBody(searchList=[nameSpace]).respond()
                content=str(t)
                nameSpace={'header':oList[0],'content':content}
                t = templateTable(searchList=[nameSpace]).respond()
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    executeSQLQuery.exposed=True

    def getDbsSchema(self,dbsInst,table="all",**kwargs):
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results_finder">"""
        page+= self.helper.getDbsSchema(table)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDbsSchema.exposed=True

    def genTreeElement(self,iParent,dataset):
        # pass here node,parent pair, as an example we pass 'newNode',node
        # for DBS DD I need to lookup parent from elsewhere
        pList = self.helper.getDatasetProvenance(dataset)
#        page  = "this.nodes.push('%s')"%dataset
        page  = ""
        for parent in pList:
            nodeVar=encode(dataset)
            href="""href:"javascript:ajaxAddTreeElement('%s','%s')" """%(parent,dataset)
            if iParent=='root':
               parent = 'root'
            else:
               # update nodes array with new parent (id,dataset) where id autogenerate with
               # Math.round(100*Math.random()) or somehow get it from array index
               # lookup an id for given parent and create new name as parent='node_'+id;
               parent = encode(parent)
            page+="""var obj= { label: "%s", %s };\nthis.%s= new YAHOO.widget.TextNode(obj,this.%s,false);"""%(dataset,href,nodeVar,parent)

        if not len(pList):
            nodeVar=encode(dataset)
            if iParent=='root':
               parent = 'root'
            else:
               parent = encode(iParent)
            page+="this.%s= new YAHOO.widget.TextNode('%s',this.%s,false);"%(nodeVar,dataset,parent)
#            page+="this.%s= new YAHOO.widget.HTMLNode('%s &#8212; no parents',this.%s,false);"%(nodeVar,dataset,parent)
        return page

    def addTreeElement(self,parent,node,**kwargs):
        cherrypy.response.headerMap['Content-Type'] = "text/xml"
        page="""<ajax-response><response type="object" id="treeViewInfo">
%s</response></ajax-response>"""%self.genTreeElement(parent,node)
        if self.verbose==2:
           self.writeLog(page)
        return page
    addTreeElement.exposed=True

    def cliHandler(self,dbsInst,input,xmlOutput=1):
        self.helperInit(dbsInst)
        data=urllib.unquote(input)
        if self.verbose==1:
           self.writeLog(data)
        try:
            selList  = []
            conDict  = {}
            whereList= []
            helper=self.helper
            class Handler (xml.sax.handler.ContentHandler):
                def startElement(self, name, attrs):
                    if name=='select':
                       table,col=attrs['column'].split(".")
                       entry="%s.%s"%(helper.dbManager.getDBTableName(dbsInst,str(table)),str(col))
                       selList.append(entry)
                    if name=='list':
                       selList.append(1)
                    if name=='where':
                       clause=str(attrs['clause'])
                       whereList.append(clause)
                    if name=='output':
                       if attrs.has_key('limit'):
                          conDict['limit']=long(attrs['limit'])
                       if attrs.has_key('offset'):
                          conDict['offset']=long(attrs['offset'])
            xml.sax.parseString (data, Handler ())
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
            return """<?xml version="1.0" encoding="utf-8"?><ddresponse><exception>%s</exception></ddresponse>"""%getExceptionInHTML()
        if self.verbose==2:
           self.writeLog("Selection list:")
           self.writeLog(str(selList))
           self.writeLog("Condition list:")
           self.writeLog(str(conDict))
           self.writeLog("Where clause list:")
           self.writeLog(str(whereList))
        if not len(selList):
           return """<?xml version="1.0" encoding="utf-8"?><ddresponse></ddresponse>"""
        if len(selList)==1 and selList[0]==1:
           oList=self.helper.getAllTableColumns()
           res="""<?xml version="1.0" encoding="utf-8"?><ddresponse>"""
           for item in oList:
                res+="""<row><column name='Table.colName' value='%s' /></row>"""%item
           res+="""</ddresponse>"""
           return res

        # setup limits for query
        limit=offset=0
        if conDict.has_key('limit'):  limit  = conDict['limit']
        if conDict.has_key('offset'): offset = conDict['offset']
        whereClause=' AND '.join(["("+x+")" for x in whereList])
        query,oList = self.helper.queryMaker(selList,whereClause,limit,offset)
        if self.verbose:
           self.writeLog(query)
        if not xmlOutput:
           return query,oList
        if self.verbose==2:
           self.writeLog(query)
           self.writeLog(str(oList))
        res="""<?xml version="1.0" encoding="utf-8"?><ddresponse>"""
        for iList in oList[1:]: # here first element in a list is column names
            res+="<row>"
            for idx in xrange(0,len(iList)):
                res+="""<column name='%s' value='%s' />"""%(oList[0][idx],iList[idx])
            res+="</row>"
        res+="""</ddresponse>"""
        return res
    cliHandler.exposed=True

    def convertXMLTOTXT(self,input,id,html=0,ajax=0,**kwargs):
        if int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page ="""<ajax-response><response type="element" id="%s">"""%id
            page+="""<div class="float_snippet">"""
            page+="""<div align="right"><a href="javascript:HideTag('%s')">close &#8855;</a><hr class="dbs" /></div> """%id
        else:
            page = self.genTopHTML()
        data=urllib.unquote(input)
        try:
            selList  = []
            conDict  = {}
            whereList= []
            dbsInst=DBSGLOBAL
            helper=self.helper
            class Handler (xml.sax.handler.ContentHandler):
                def startElement(self, name, attrs):
                    if name=='select':
                       table,col=attrs['column'].split(".")
                       entry="%s.%s"%(helper.dbManager.getDBTableName(dbsInst,str(table)),str(col))
                       selList.append(entry)
                    if name=='list':
                       selList.append(1)
                    if name=='where':
                       clause=str(attrs['clause'])
                       whereList.append(clause)
                    if name=='output':
                       if attrs.has_key('limit'):
                          conDict['limit']=long(attrs['limit'])
                       if attrs.has_key('offset'):
                          conDict['offset']=long(attrs['offset'])
            xml.sax.parseString (data, Handler ())
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
        out="\n[select]\n"
        for item in selList:
            out+=item+"\n"
        if  whereList:
            whereClause=' AND '.join(["("+x+")" for x in whereList])
            out+="\n[where]\n"+whereClause+"\n"
        if conDict:
            out+="\n[output]\n"
            for key in conDict.keys():
                out+="%s=%s\n"%(key,conDict[key])
        if html:
           page+="<pre>"+out+"</pre>"
        else:
           page+=out

        page+="""<p/><hr class="dbs" /><div>You may use this TXT snippet with <a href="https://twiki.cern.ch/twiki/bin/view/CMS/DDExplorer">DDExplorer</a> a command line interface to Finder.</div>"""
        if int(ajax):
           page+="</div>"
           page+="""</response></ajax-response>"""
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    convertXMLTOTXT.exposed=True
    
    def cliHandler_v1(self,dbsInst,input,xmlOutput=1):
        self.helperInit(dbsInst)
        data=urllib.unquote(input)
        if self.verbose==1:
           self.writeLog(data)
        try:
            selList  = []
            conDict  = {}
            whereList= []
            helper=self.helper
            class Handler (xml.sax.handler.ContentHandler):
                def startElement(self, name, attrs):
                    if name=='select':
                       table,col=attrs['column'].split(".")
                       entry="%s.%s"%(helper.dbManager.getDBTableName(dbsInst,str(table)),str(col))
                       selList.append(entry)
#                       selList.append(str(attrs['column']))
                    if name=='list':
                       selList.append(1)
                    if name=='where':
                       table,col=attrs['column'].split(".")
                       whereList.append((str(table),str(col),str(attrs['operator']),str(attrs['value'])))
                    if name=='output':
                       if attrs.has_key('limit'):
                          conDict['limit']=long(attrs['limit'])
                       if attrs.has_key('offset'):
                          conDict['offset']=long(attrs['offset'])
            xml.sax.parseString (data, Handler ())
        except:
            if self.verbose:
               self.writeLog(getExcept())
            printExcept()
        if self.verbose==2:
           self.writeLog("Selection list:")
           self.writeLog(str(selList))
           self.writeLog("Condition list:")
           self.writeLog(str(conDict))
           self.writeLog("Where clause list:")
           self.writeLog(str(whereList))
        if not len(selList):
           return """<?xml version="1.0" encoding="utf-8"?><ddresponse></ddresponse>"""
        if len(selList)==1 and selList[0]==1:
           oList=self.helper.getAllTableColumns()
           res="""<?xml version="1.0" encoding="utf-8"?><ddresponse>"""
           for item in oList:
                res+="""<row><column name='Table.colName' value='%s' /></row>"""%item
           res+="""</ddresponse>"""
           return res

        # setup limits for query
        limit=offset=0
        if conDict.has_key('limit'):  limit  = conDict['limit']
        if conDict.has_key('offset'): offset = conDict['offset']
        query,oList = self.helper.queryMaker(selList,whereList,limit,offset)
        if not xmlOutput:
           return query,oList
        if self.verbose==2:
           self.writeLog(query)
           self.writeLog(str(oList))
        res="""<?xml version="1.0" encoding="utf-8"?><ddresponse>"""
        for iList in oList[1:]: # here first element in a list is column names
            res+="<row>"
            for idx in xrange(0,len(iList)):
                res+="""<column name='%s' value='%s' />"""%(oList[0][idx],iList[idx])
            res+="</row>"
        res+="""</ddresponse>"""
        return res
    cliHandler_v1.exposed=True

    def phedexStatus(self,site,datasetPath,id_suffix,**kwargs):
        self.setContentType('xml')
        url="/cms/test/aprom/phedex/dev/egeland/prod/XML::TransferStatus"
        params={}
        if site:
           if site.find(",")!=-1:
              params['se_name']=site.split(",")
           else:
              params['se_name']=site
        else:
           params['se_name']='*'
        if id_suffix: params['id_suffix']=id_suffix
        if datasetPath.find(",")!=-1:
           params['dataset']=datasetPath.split(",")
        else:
           params['dataset']=datasetPath
        page=""
        try:
            page = self.phedexServer.sendPostMessage(url,params,debug=0)
#            print "\n\n### response phedex response"
#            print site
#            print datasetPath
#            print id_suffix
#            print params
#            print page
            if type(page) is types.StringType:
               page = string.replace(page,"""<?xml version='1.0' encoding='ISO-8859-1'?>""","")
        except:
            t=self.errorReport("Fail in phedexStatus function")
            page+="Phedex information is not available at this time"
            pass
        if self.verbose==2:
           self.writeLog(page)
        return page
    phedexStatus.exposed=True 

    def getRunDBInfo(self,runs,**kwargs):
        self.setContentType('xml')
        rDict={}
        try:
            rDict = self.helper.getRunDBInfo(runs)
#            print "\n\n### getRunDBInfo response"
#            print runs
#            print rDict
        except:
            t=self.errorReport("Fail in phedexStatus function")
            pass
        page="""<ajax-response>"""
        for run in rDict.keys():
            global_key,triggers,events,bfield,components=rDict[run]
            page+="""<response type='object' id="runSummary_key_%s">"""%run
            page+="""<span>Type: %s</span>"""%global_key
            page+="</response>"
            page+="""<response type='object' id="runSummary_triggers_%s">"""%run
            page+="""<span>Triggers: %s</span>"""%triggers
            page+="</response>"
            page+="""<response type='object' id="runSummary_events_%s">"""%run
            page+="""<span>Events: %s</span>"""%triggers
            page+="</response>"
            page+="""<response type='object' id="runSummary_bfield_%s">"""%run
            page+="""<span>B-field: %s</span>"""%bfield
            page+="</response>"
            page+="""<response type='object' id="runSummary_components_%s">"""%run
            page+="""<span>Components: %s</span>"""%components
            page+="</response>"
        page+="</ajax-response>"
            
        if self.verbose==2:
           self.writeLog(page)
        return page
    getRunDBInfo.exposed=True 

    def setConfig(self,base=""):
        mime_types=['text/css','text/javascript','application/javascript','application/x-javascript','image/gif','image/png','image/jpg','image/jpeg']
        httpHeader=[('Expires',time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime(time.time()+315360000))),
                               ('Accept-Encoding','gzip'),
                               ('TE','deflate, gzip, x-gzip, identity, trailer'),
                               ('Cache-Control','max-age=315360000'),
                               ('Authorization','Basic')
                   ]
                               
        conf = {'/'         : {'tools.staticdir.root': os.getcwd(),
                               'tools.response_headers.on':True,
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
# main
#
if __name__ == "__main__":
    optManager  = DDOptions.DDOptionParser('DDServer')
    (opts,args) = optManager.getOpt()
    context="" # we pass empty context here to be able to run in stand-alone mode
    dbsManager = DDServer(context,opts.verbose,opts.profile)
    print "Using CherryPy:",cherrypy.__version__
    if opts.quiet:
       dbsManager.setQuiet()
    if  int(string.split(cherrypy.__version__,".")[0])==3:
        cherrypy.config.update("CherryServer3.conf")
        if opts.ssl:
           try:
               cherrypy.config.update({'server.ssl_certificate': '%s'%os.environ['DD_CRT'],
                                       'server.ssl_private_key': '%s'%os.environ['DD_PEM']})
               print "+++ Run in secure mode"
           except:
               printExcept()
               pass
    else:
        cherrypy.root = dbsManager
        cherrypy.config.update(file="CherryServer.conf")
        cherrypy.config.update({'global': {'static_filter.root' : os.getcwd() }})

    if opts.verbose:
        print "CherryPy config:"
        if  int(string.split(cherrypy.__version__,".")[0])==3:
            print cherrypy.config
        else:
            print cherrypy.config.__dict__
    if opts.profile:
       import hotshot                   # Python profiler
       import hotshot.stats             # profiler statistics
       print "Start DBS/DLS discovery server in profile mode"
       profiler = hotshot.Profile("profile.dat")
       if  int(string.split(cherrypy.__version__,".")[0])==3:
           profiler.run("cherrypy.quickstart(dbsManager, '/', config=conf)")
       else:
           profiler.run("cherrypy.server.start()")
       profiler.close()
       stats = hotshot.stats.load("profile.dat")
       stats.sort_stats('time', 'calls')
       stats.print_stats()
    else:       
       if  int(string.split(cherrypy.__version__,".")[0])==3:
#           cherrypy.quickstart(dbsManager, '/', config=conf)
           cherrypy.quickstart(dbsManager, '/', config=dbsManager.setConfig())
       else:
           cherrypy.server.start()
