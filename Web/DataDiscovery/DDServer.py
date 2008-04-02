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
import thread, smtplib, tempfile, zlib, traceback
import xml.sax, xml.sax.handler
from   xml.sax.saxutils import escape

# Cheetah template modules
from   Cheetah.Template import Template

# CherryPy server modules
import cherrypy 
from   cherrypy    import expose
from   cherrypy    import tools
from   cherrypy.lib.static import serve_file
#from   cherrypy.lib import profiler

# DBS  modules
from   DDUtil      import *
from   DDConfig    import *
from   DDHelper    import *
from   Templates   import *
from   DDSearch    import *
from DDParamServer import *
from DDWS          import *


# experiment
from DDQueryMaker import *
from DDRules      import *

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

# DBS framework in order to make migration
try:
    from DBSAPI.dbsApi import DbsApi
    from DBSAPI.dbsMigrateApi import DbsMigrateApi
except:
#    traceback.print_exc()
    pass

# DBS migration service
try:
    from MS.Wrapper import API as DBS_MS
except:
    traceback.print_exc()
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
        self.ddConfig  = DDConfig()
        DDLogger.__init__(self,self.ddConfig.loggerDir(),"DDServer",verbose)
        setSQLAlchemyLogger(super(DDServer,self).getHandler(),super(DDServer,self).getLogLevel())
        setCherryPyLogger(super(DDServer,self).getHandler(),super(DDServer,self).getLogLevel())

#        self.lucene = DDLucene(verbose)
        self.pServer= DDParamServer(server="edge.fnal.gov:8888",verbose=verbose)
        # ProdRequest URL https://cmsdoc.cern.ch/cms/test/aprom/DBS/prodrequest/ProdRequest/getHome
        self.prodRequestServer= DDParamServer(server="cmslcgco01.cern.ch:8030",verbose=verbose)
        self.phedexServer= DDParamServer(server="cmsdoc.cern.ch",verbose=verbose)
        self.PhedexURL="https://cmsdoc.cern.ch:8443/cms/aprom/phedex/prod/Request::Create"
        self.dbs  = DBSGLOBAL
        self.baseUrl = ""
        self.topUrl= ""
        self.mastheadUrl = self.ddConfig.masthead()
        self.footerUrl   = self.ddConfig.mastfooter()
        self.adminUrl = self.ddConfig.adminUrl()
        self.msApi=""
        try:
            self.msApi = DBS_MS(url=self.adminUrl)
        except:
            if verbose:
               traceback.print_exc()
            pass
        self.adminVer = self.ddConfig.adminVer()
        self.ns = self.ddConfig.ns()
        self.globalDD = self.ddConfig.global_dd()
        self.ddUrls = []
        self.ddUrls.append(self.globalDD)
        self.site = ""
        self.app  = ""
        self.primD= ""
        self.tier = ""
        self.helper     = DDHelper(self.dbs,self.ddConfig.iface(),verbose,html=1)
        self.asearch    = DDSearch(dbsHelper=self.helper)
        self.ddrules    = DDRules(verbose)
        self.qmaker     = DDQueryMaker(self.dbs)
        self.qmaker.setVerbose(verbose)
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
        self.verbose    = verbose
        self.profile    = profile
        self.dbsDict    = {}
        self.userMode   = 'user'
        self.timer      = self.timerForSummary = time.time()
        self.sumPage    = ""
        self.firstSearch=1
        self.quiet      = 0
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
        self.globalDD=normUrl(self.globalDD)
        self.dbsdd=normUrl(self.dbsdd)
        if self.globalDD!=self.dbsdd:
           self.sendSOAP(self.globalDD,"wsAddUrl",{'url':self.dbsdd,'reply':self.dbsdd})
        self.writeLog("DDServer init")

    def readyToRun(self):
        opts=self.context.CmdLineArgs().opts
        self.qmaker.setVerbose(opts.verbose)
        self.helper.setVerbose(opts.verbose)
        self.ddrules.setVerbose(opts.verbose)
        self.baseUrl = opts.baseUrl
#        self.baseUrl = self.context.CmdLineArgs ().opts.baseUrl
        if self.baseUrl[-1]!="/": self.baseUrl+="/"
        self.mastheadUrl=self.baseUrl+"sitedb/Common/masthead"
        self.footerUrl=self.baseUrl+"sitedb/Common/footer"
#        self.mastheadUrl=self.baseUrl+"Common/masthead"
#        self.footerUrl=self.baseUrl+"Common/footer"
        self.topUrl=self.baseUrl+"DDServer/"
        # I only need this if webtools force to use a new URL structure.
        self.dbsdd=self.dbsdd+"/DDServer/"
        try:
           self.verbose=opts.verbose
           self.helper.setVerbose(self.verbose)
        except:
           pass

    def sendSOAP(self,host,service,aDict={}):
        envelope=constructSOAPEnvelope(self.ns,service,aDict)
        if self.verbose:
           print "### Host %s sends the following msg to %s"%(self.dbsdd,host)
           print envelope
        try:
           debug=0
           if self.verbose:
              debug=1
           sendSOAPMessage(host,self.ns,service,envelope,debug=debug)
        except:
           pass

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

    def ws(self,**kwargs):
        self.namespace_expr = re.compile(r'^\{.*\}')
        # get request data and produce an ElementTree that we can work with.
        request = cherrypy.request
        if self.verbose==2:
           self.writeLog(printDict(request.__dict__))
        
        response = cherrypy.response
        if self.verbose==2:
           self.writeLog(printDict(response.__dict__))
        if request.headers.has_key('Content-Length'):
           clen = int(request.headers.get('Content-Length'))
        else:
           clen = 0
        data = request.body.read(clen)
        
        request.soap_start = data[:2048]
        soapreq = elementtree.ElementTree.fromstring(data)

        # find the body of the request and the specific method name that has
        # been requested.
        body = soapreq.find("{http://schemas.xmlsoap.org/soap/envelope/}Body")
        body = body.getchildren()[0]

        methodname = body.tag
        methodname = self.namespace_expr.sub("", methodname)
        request.soap_method = methodname
        method=getattr(self,methodname)

        params = {"_ws" : True}
        params["xml_body"] = body
        return method(**params)

    ws.exposed=True

    def soapMsg(self,msg):
        page="""
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <DDResponse xmlns="%s">"""%self.ns
        page+=msg
        page+="""
    </DDResponse>
  </soap:Body>
</soap:Envelope>"""
        return page

    def parseWSInput(self,**kwargs):
        self.writeLog("Input params="+repr(kwargs))
        xml_body=kwargs['xml_body']
        self.writeLog("%s %s"%(type(xml_body),repr(xml_body)))
        xmlItems=xml_body.getchildren()
        xmlDict={}
        for item in xmlItems:
            xmlDict[self.namespace_expr.sub("", item.tag)]=item.text
#            print item.tag,item.text,self.namespace_expr.sub("", item.tag)
        return xmlDict

    def wsError(self,msg):
        self.setContentType('xml')
        page=self.soapMsg("""<ddError>%s</ddError>"""%msg)
        return page

    def wsGetNDatasets(self,**kwargs):
        self.setContentType('xml')
        xmlDict=self.parseWSInput(**kwargs)
        nsets = self.helper.nDatasets()
        msg="""<NumberOfDatasets><int>%s</int></NumberOfDatasets>"""%nsets
        page=self.soapMsg(msg)
        self.writeLog(page)
        return page
    wsGetNDatasets.exposed=True

    def wsGetDatasetSummary(self,**kwargs):
        self.setContentType('xml')
        xmlDict=self.parseWSInput(**kwargs)
        if not xmlDict.has_key('dataset'):
           return self.wsError("Wrong parameter")
        oDict,mDict = self.helper.datasetSummary(xmlDict['dataset'])
        key=mDict.keys()[0]
        prdDate,cBy,nblks,blkSize,nFiles,nEvts=mDict[key]
        msg="""
      <Dataset>
        <string>%s</string>
        <string>%s</string>
        <string>%s</string>
        <int>%s</int>
        <int>%s</int>
        <int>%s</int>
        <int>%s</int>
      </Dataset>
        """%(key,prdDate,cBy,nblks,blkSize,nFiles,nEvts)
        page=self.soapMsg(msg)
        self.writeLog(page)
        return page
    wsGetDatasetSummary.exposed=True

    def wsAddUrl(self,**kwargs):
        self.setContentType('xml')
        xmlDict=self.parseWSInput(**kwargs)
        if not xmlDict.has_key('url'):
           return self.wsError("Wrong parameter")
        url = xmlDict['url']
        if not self.ddUrls.count(url):
           self.ddUrls.append(url)
        if xmlDict.has_key('reply'):
           vlock = thread.allocate_lock()
           host=xmlDict['reply']
           debug=0
           if self.verbose:
              debug=1
              print "### I need to reply to",host
           aDict={}
           aDict['url']=self.ddUrls
#           self.sendSOAP(host,"wsAddUrl",aDict)
           thread.start_new_thread(self.sendSOAP,(host,"wsAddUrl",aDict))

    def wsUpdateSiteInfo(self,**kwargs):
        self.setContentType('xml')
        xmlDict=self.parseWSInput(**kwargs)
        if xmlDict.has_key('url') and xmlDict.has_key('nsets'):
           url  = xmlDict['url']
           nsets= xmlDict['nsets']
           print "\n\n### I got reply from '%s' it contains '%s'"%(url,nsets)
        # send my info to known sites
        vlock = thread.allocate_lock()
        for url in self.ddUrls:
            if url==self.dbsdd: continue
            aDict={}
            aDict['url']=self.dbsdd
            aDict['nsets']=self.helper.nDatasets()
            print aDict
#            thread.start_new_thread(self.sendSOAP,(url,"wsUpdateSiteInfo",aDict))

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
        return self._advanced(dbsInst,userMode)
#        try:
#            page = self.genTopHTML(intro=False,userMode=userMode,onload="resetUserNav();")
#            page+= self.whereMsg('Navigator',userMode)
#            auto=0
#            if kwargs.has_key('auto') and kwargs['auto']=='on':
#               auto=1
#            userNav = self.genEmptyUserNavigator(dbsInst,userMode,auto)
#            t = templateMenuNavigator(searchList=[{'userNavigator':userNav}]).respond()
#            page+= str(t)
#            page+= self.genBottomHTML()
#            return page
#        except:
#            t=self.errorReport("Fail in index function")
#            pass
#            return str(t)
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

    def decodeUserName(self,**kwargs):
        if kwargs.has_key('dn'):
           dn=kwargs['dn'][1:-1] # the DN I get from browser has extra quotes at beg/end of string
           if not dn: return ""
           return decryptCookie(dn, self.securityApi)
        return ""
    def getEmail(self,userName):
        return "%s@cern.ch"%userName

    def getDN(self,userName):
        """
           Get user DN, for that we use WEBTOOLS SecurityAPI.
        """
        if not userName: return ""
        dn=""
        try:
            dnDict=self.securityApi.getDNFromUsername(userName)
            dn=dnDict[0]['dn']
        except:
            pass
        return dn

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

    def _advanced(self,dbsInst=DBSGLOBAL,userMode="user",msg=""):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            dbsList=list(self.dbsList)
            dbsList.remove(dbsInst)
            dbsList=[dbsInst]+dbsList
            if msg: userHelp=0
            else:   userHelp=1
            nameSearch={'dbsInst':dbsInst,'userHelp':userHelp,'dbsList':dbsList,'host':self.dbsdd,'style':'','userMode':userMode,'userInput':''}
            t = templateAdvancedSearchForm(searchList=[nameSearch]).respond()
            page+= str(t)
            if msg:
               page+=msg
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in advanced init function")
            pass
            return str(t)
    _advanced.exposed=True

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

    def _pages(self,userMode):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            nameSpace = {
                         'userMode' : userMode,
                         'ddList'   : self.ddUrls,
                        }
            page+= templateRemoteDD(searchList=[nameSpace]).respond()
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in help init function")
            pass
            return str(t)
    _pages.exposed=True

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

    @is_authenticated (onFail=RedirectToLocalPage ("/DDServer/redirectPage"))
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

    @is_authorized (Role("Global Admin"), Group("DBS"), 
		    onFail=RedirectToLocalPage ("/DDServer/redirectPage"))
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

    #################### ADMIN FORMS #######################
#    @is_authorized (Role("Global Admin"), Group("DBS"), 
    @is_authorized (Role("DBSExpert"), Group("DBS"), 
                    onFail=RedirectToLocalPage ("/DDServer/redirectPage"))
    def adminDataset(self,dbsInst,dataset,userMode,siteList,**kwargs):
        page = self.genTopHTML(userMode=userMode)
#        page+= """<div class="box_red">THIS IS PROTOTYPE VERSION OF FRONT-END INTERFACE, ACTUAL FUNCTIONALITY IS NOT YET WORKING!<br />Please send comments to cms-dbs-support@cern.ch</div><p></p>\n"""
        page+= self.whereMsg('Navigator :: Results :: list of datasets :: admin tasks',userMode)

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
                  'userMode': userMode,
                  'adminUrl': self.adminUrl,
                  'apiversion': self.adminVer,
                  'email'   : "vk@mail.lns.cornell.edu"
                  }
        t = templateAdminDatasets(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    adminDataset.exposed=True

    def ms_deleteRequest(self,**kwargs):
        try:
            srcUrl = kwargs['srcUrl']
            dstUrl = kwargs['dstUrl']
            path   = kwargs['path']
            result = self.msApi.deleteRequest(srcUrl, dstUrl, path)
        except:
            if self.verbose:
               traceback.print_exc()
            result = getExcMessage(kwargs['userMode'])
            pass
        page   = self.genTopHTML(userMode=userMode)
        t      = templateAdminPage(searchList=[{'userMode':userMode,'result':result}]).respond()
        page  += str(t)
        page  += self.genBottomHTML()
        return page
    ms_deleteRequest.exposed=True

    def ms_getRequestByUser(self,userMode="user",**kwargs):
        dn=""
        try:
            userName=self.decodeUserName(**kwargs)
            dn=urllib.quote(self.getDN(userName))
            result = self.msApi.getRequestByUser(dn)
        except:
            if self.verbose:
               traceback.print_exc()
            result = getExcMessage(userMode)
            pass
        page   = self.genTopHTML(userMode=userMode)
        t      = templateAdminPage(searchList=[{'userMode':userMode,'result':result}]).respond()
        page  += str(t)
        page  += self.genBottomHTML()
        return page
    ms_getRequestByUser.exposed=True

    def ms_getRequestById(self,id,userMode="user"):
        try:
            result = self.msApi.getRequestById(int(id))
        except:
            if self.verbose:
               traceback.print_exc()
            result = getExcMessage(userMode)
            pass
        page   = self.genTopHTML(userMode=userMode)
        t      = templateAdminPage(searchList=[{'userMode':userMode,'result':result}]).respond()
        page  += str(t)
        page  += self.genBottomHTML()
        return page
    ms_getRequestById.exposed=True

    def ms_getRequestByStatus(self,status,userMode="user"):
        try:
            result = self.msApi.getRequestByStatus(status)
        except:
            if self.verbose:
               traceback.print_exc()
            result = getExcMessage(userMode)
            pass
        page   = self.genTopHTML(userMode=userMode)
        t      = templateAdminPage(searchList=[{'userMode':userMode,'result':result}]).respond()
        page  += str(t)
        page  += self.genBottomHTML()
        return page
    ms_getRequestByStatus.exposed=True

    def ms_addRequest(self,**kwargs):
#        print "\n\n+++ms_addRequest",kwargs,self.msApi,type(self.msApi)
        if kwargs.has_key('submit'): del kwargs['submit']
        if kwargs.has_key('choice'): del kwargs['choice']
        dn=""
        email=""
        try:
            userName=self.decodeUserName(**kwargs)
            dn=urllib.quote(self.getDN(userName))
        except:
            pass
        try:
            srcUrl = kwargs['src_url']
            dstUrl = kwargs['dst_url']
            path   = kwargs['path']
            force  = getArg(kwargs,'force','y')
            parents= getArg(kwargs,'with_parents','y')
            if parents=='on': parents='y'
            else: parents='n'
            notify = kwargs['notify']
            result = self.msApi.addRequest(srcUrl,dstUrl,path,dn,force,parents,notify)
        except:
            if self.verbose:
               traceback.print_exc()
            result = getExcMessage(getArg(kwargs,'userMode','user'))
            pass
        return result

    def adminRequest(self,**kwargs):
        print "\n\n+++adminRequest",kwargs
        if kwargs.has_key('submit'): del kwargs['submit']
        if kwargs.has_key('choice'): del kwargs['choice']
        debug=1
        dn=""
        try:
            userName=self.decodeUserName(**kwargs)
            dn=urllib.quote(self.getDN(userName))
            email=self.getEmail(self.getDN(userName))
        except:
            pass
        # parse admin url
        if  kwargs['api']=="addRequest": # migration service
            tup=urlparse.urlparse(self.adminUrl)
            kwargs['dn']=dn
            kwargs['xml']="yes"
            adminUrl=self.adminUrl
        else:
            tup=urlparse.urlparse(DBS_INST_URL[kwargs['dbsInst']])
            if kwargs.has_key('dn'): del kwargs['dn']
            adminUrl=DBS_INST_URL[kwargs['dbsInst']]
            del kwargs['dbsInst'] # I don't need it anymore
        host,port=tup[1].split(":")
        if tup[0]=='https':
           secure=1
           if not port: port=443
        else:
           secure=0
           if not port: port=80
        port=int(port)
        path=tup[2]
        if secure:
           http_conn = httplib.HTTPSConnection(host,port)
        else:
           http_conn = httplib.HTTPConnection(host,port)
        if debug:
           httplib.HTTPConnection.debuglevel = 1
           httplib.HTTPSConnection.debuglevel = 1
           print "Contact",host,port,path
        headers={"UserID":dn,"Content-type":"application/x-www-form-urlencoded","Accept":"text/plain"}
        if debug:
           print "\n\n"
           print http_conn,http_conn.__dict__
           print path
           print kwargs
           print urllib.urlencode(kwargs)
           print "\n\n"
        result   = http_conn.request('POST',path,urllib.urlencode(kwargs),headers)
        response = http_conn.getresponse()
        code     = int(response.status)
        status   = ""
        if code==200:
           data=response.read()
           elem=elementtree.ElementTree.fromstring(data)
           for i in elem:
               if i.tag=="request":
                  id=i.attrib['id']
                  status="""Your request processed, request ID='%s', check its status <a href="%s?api=getRequestByID&request_id=%s">here</a>"""%(id,adminUrl,id)
               elif i.tag=="exception":
                  status="DBS server returns code=%s, %s"%(i.attrib['code'],i.attrib['detail'])
               elif i.tag=="error":
                  status="DBS server returns error, %s"%(i.attrib['message'])
        else:
            status="Fail to send HTTP request %s, %s"%(code,response.reason)
        return "<em>"+status+"</em>"

    def adminTask(self,**kwargs):
        userMode='user' # default
        if kwargs.has_key('userMode'):
           userMode=kwargs['userMode']
           del kwargs['userMode']
        if kwargs.has_key('dst_url'):
           dbs=kwargs['dst_url']
           del kwargs['dst_url']
           kwargs['dst_url']=DBS_INST_URL[dbs]
        if kwargs.has_key('src_url'):
           dbs=kwargs['src_url']
           del kwargs['src_url']
           kwargs['src_url']=DBS_INST_URL[dbs]
        dbsInst=kwargs['dbsInst']
        self.helperInit(dbsInst)
        dataset=kwargs['dataset']
        if kwargs.has_key('lfn'):
           if kwargs['lfn'].lower()=="all":
              lfnList=self.helper.getLFNsFromSite(site="all",datasetPath=dataset,run="*")
           elif kwargs['lfn'].lower().find("like")!=-1:
              lfn=kwargs['lfn'].split("like:")[1]
              lfnList=self.helper.getLFNsFromSite(site="all",datasetPath=dataset,run="*",lfn="%%%s%%"%lfn)
           else:
              lfnList=self.helper.getLFNsFromSite(site="all",datasetPath=dataset,run="*",lfn=lfn)
           del kwargs['lfn'] # delete lfn key-pair from our dictionary
           kwargs['lfn']=lfnList
        # lookup for block_name and find out a list
        if kwargs.has_key('block_name'):
           block_names=""
           if kwargs['block_name'].lower()=="all":
              block_names=self.helper.getBlocksFromSite(site="*",datasetPath=dataset)
              del kwargs['block_name'] # delete block_name key-pair from our dictionary
              kwargs['block_name']=block_names
        if kwargs.has_key('dataset'): del kwargs['dataset'] # I don't need it anymore
        page = self.genTopHTML(userMode=userMode)
        if  kwargs['api']=="addRequest":
#            result=self.adminRequest(**kwargs)
            result=self.ms_addRequest(**kwargs)
            t     = templateAdminPage(searchList=[{'userMode':userMode,'result':result}]).respond()
            page += str(t)
        else:
            skipList=['submit','title','submit request','choice','dbsInst_from','dbsInst_to','dn']
            input = str(templateXML(searchList=[{'kwargs':kwargs,'skipList':skipList}]).respond())
            xmlOutput=urllib.unquote(input).replace("<","&lt;").replace(">","&gt;<br />").replace("&lt;/","<br/>&lt;/").replace("&lt;","<b>&lt;").replace("&gt;","&gt;</b>")
            kwargs['apiversion']=self.adminVer
            nameSpace = {'kwargs':kwargs,'skipList':skipList,'xmlOutput':xmlOutput,'userMode':userMode,'adminUrl':DBS_INST_URL[dbsInst]}
            t = templateAdminTask(searchList=[nameSpace]).respond()
            page+= str(t)
        page+= self.genBottomHTML()
        return page
    adminTask.exposed=True

    def makeDbsApi(self,url):
        args = {}
        args['url'] = url
        args['mode'] = 'POST'
        return DbsApi(args)

    def sendAdminRequest(self,*args,**kwargs):
        skipList=['submit','title','submit request']
        xml=str(templateXML(searchList=[{'kwargs':kwargs,'skipList':skipList}]).respond())
        print "\n\nsendAdminRequest\n",xml
        api  = kwargs['api']
        if api=='migrate':
           dbsFrom  = self.makeDbsApi(DBS_INST_URL[kwargs['dbsInst_from']])
           dbsTo    = self.makeDbsApi(DBS_INST_URL[kwargs['dbsInst_to']])
           transfer = DbsMigrateApi(dbsFrom, dbsTo, True)
           print "+++ DbsMigrateApi init +++"
           transfer.migratePath(kwargs['path'])
        page = self.genTopHTML(userMode='expert')
        page+="<h3>Your request has been send to DBS</h3>"
        page+="<p>Confirmation number: XXXYYY</p>"
        page+="""<p>You can visit this <a href="">page</a> to see and monitor progress of your request"""
        page+= self.genBottomHTML()
        return page
    sendAdminRequest.exposed=True
    #################### END OF ADMIN FORMS #######################

    def createAnalysisDS(self,dbsInst,dataset,userMode="user"):
        page = self.genTopHTML(userMode=userMode)
        bList= self.helper.getBlocksInfo(dataset) 
        minRun,maxRun=self.helper.getRunRangeForDataset(dataset)
        nameSpace={
                   'dbsInst' : dbsInst,
                   'dataset' : dataset,
                   'bList'   : bList,
                   'userMode': userMode,
                   'minRun'  : minRun,
                   'maxRun'  : maxRun,
                  }
        t = templateCreateAnalysisDataset(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    createAnalysisDS.exposed=True

    def createADS(self,dbsInst,dataset,userMode="user",**kwargs):
#        print "\n\n### createADS",kwargs
        description=getKeyValue(kwargs,"description")
        definition=getKeyValue(kwargs,"definition")
        user=getKeyValue(kwargs,"user")
        status=getKeyValue(kwargs,"status")
        blkList=[] # (idx,block)
        lfnList=[] # (idx,lfn,blk_idx)
        runList=[] # (idx,run,run_idx)
        if  kwargs.has_key('block'):
            idx=0
            blkList+=genMartList(kwargs,'block',-1) # -1 here means no extra index
        for item in blkList:
            blk_idx=item[0]
            blk=item[1]
            lfnList+=genMartList(kwargs,blk,blk_idx)
        for item in lfnList:
            lfn_idx=item[0]
            lfn=item[1]
            runList+=genMartList(kwargs,lfn,lfn_idx)
        page = self.genTopHTML(userMode=userMode)
        nameSpace={
                   'dbsInst'    : dbsInst,
                   'dataset'    : dataset,
                   'description': description,
                   'definition' : definition,
                   'blkList'    : blkList,
                   'lfnList'    : lfnList,
                   'runList'    : runList,
                   'userMode'   : userMode,
                   'user'       : user,
                   'timeStamp'  : time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime()),
                   'status'     : status,
                  }
        t = templateCreateMart(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    createADS.exposed=True

    def createADSFromRunRange(self,dbsInst,dataset,runRanges,userMode="user",**kwargs):
        self.helperInit(dbsInst)
        page = self.genTopHTML(userMode=userMode)
        # TODO: I need to validate user input, runRanges
        # it should be in a form minR-maxRun,minRun-maxRun,...
        lfnList=self.helper.getLFNsFromRunRanges(dbsInst,dataset,runRanges)
        page+=self.formatLFNList(lfnList,"cff",idx=0)
        page+= self.genBottomHTML()
        return page
    createADSFromRunRange.exposed=True

    def createADS_xml(self,dbsInst,dataset,userMode="user",**kwargs):
        xml=False
        if kwargs.has_key('xml'):
           xml=True
        page = self.genTopHTML(userMode=userMode)
        input="""<?xml version="1.0" encoding="utf-8"?>\n"""
        input+="""<dataset xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="DBS-Mart">\n"""
        input+="<path>\n%s\n</path>\n"%dataset
        if  type(kwargs['block']) is types.StringType:
            input+="<block>\n<name>\n%s\n</name>\n</block>\n"%kwargs['block']
        else:
            for block in kwargs['block']:
                input+="<block>\n"
                input+="<name>\n%s\n</name>\n"%block
                if  kwargs.has_key('%s'%block):
                    lfnList = kwargs['%s'%block]
                    if type(lfnList) is types.ListType:
                       if not lfnList.count('All'):
                          for lfn in kwargs[block]:
                              input+="<lfn>\n%s\n</lfn>\n"%lfn
                    else:
                       if lfnList!="All":
                          input+="<lfn>\n%s\n</lfn>\n"%lfnList
                    input+="</block>\n"
        input+="<description>"+kwargs['description']+"</description>\n"
        input+="<tags>"+kwargs['tags']+"</tags>\n"
        input+="</dataset>\n"
        if xml:
           self.setContentType('xml')
           return input
        if self.verbose:
           self.writeLog(input)
        xmlOutput=input.replace("<","&lt;").replace(">","&gt;").replace("\n","<br/>")
        page+="<p>The following DBS-Mart XML file has been created:</p>"
        page+="""<div class="yellow_box">%s</div>"""%xmlOutput
        page+="""<p>Right click on this <a type="xml" href="createADS?dataset=%s&amp;userMode=%s&amp;xml=True%s">XML</a> file in order to save it locally.</p>"""%(dataset,userMode,getParams(kwargs))
        page+= self.genBottomHTML()
        return page
    createADS_xml.exposed=True

    def showProcDatasets(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",primType="*",date="*",userMode='user',**kwargs):
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
               caseSensitive=True
               if kwargs.has_key('caseSensitive'):
                 if kwargs['caseSensitive']=="on":
                    caseSensitive=True
                 else:
                    caseSensitive=False
                    proc=proc.upper()
               # we got a pattern
               if proc.lower().find("regexp:")!=-1:
                  arg="Path"
                  if not caseSensitive:
                     arg="UPPER(Path)"
                  # we got regular expression pattern
                  op,pat=proc.split("regexp:")
                  if self.helper.dbManager.dbType[dbsInst]=='oracle':
                     wClause=" REGEXP_LIKE(%s,:p0) ESCAPE '\\'"%arg
                     bDict={'p0':"%s"%pat.replace("_","\_")}
                  else:
                     wClause=" %s REGEXP :p0 "%arg
                     bDict={'p0':"%s"%pat}
                  nDatasets=self.helper.countBlocks(whereClause="",site=site,explicitWClause=wClause,explicitDict=bDict,caseSensitive=caseSensitive)
               elif proc.lower().find("like:")!=-1:
                  arg="tblk.Path"
                  if not caseSensitive:
                     arg="UPPER(tblk.Path)"
                  if self.helper.dbManager.dbType[dbsInst]=='oracle':
                     wClause,bDict=parseKeywordInput(proc.replace("_","\_"),arg)
                     nDatasets=self.helper.countBlocks(proc.replace("_","\_"),site,caseSensitive=caseSensitive)
                  else:
                     wClause,bDict=parseKeywordInput(proc,arg,type=self.helper.dbManager.dbType[dbsInst])
                     nDatasets=self.helper.countBlocks(proc,site,caseSensitive=caseSensitive)
               else:
                  # we got a pattern
                  wClause=" tblk.Path LIKE :p0 "
                  if not caseSensitive:
                     wClause=" UPPER(tblk.Path) LIKE :p0 "
                  bDict={'p0':"%s"%proc.replace('*','%')}
                  if self.helper.dbManager.dbType[dbsInst]=='oracle':
                     wClause+="ESCAPE '\\'"
                     bDict={'p0':"%s"%proc.replace('*','%').replace("_","\_")}
                     nDatasets=self.helper.countBlocks("like:%s"%proc.replace("_","\_"),site,caseSensitive=caseSensitive)
                  else:
                     nDatasets=self.helper.countBlocks("like:%s"%proc,site,caseSensitive=caseSensitive)
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
#            page+= self.genResultsHTML()
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

    def findDatasetsFromLFN(self,dbsInst,lfn,userMode,**kwargs):
        pList = self.helper.findDatasetsFromLFN(lfn)
        page=self.genTopHTML(userMode=userMode)
        if len(pList):
           page+="<p>Requested LFN %s found in</p>\n<ul>\n"%lfn
           for pname in pList:
               path,bid=pname.split("#")
               s="%s/getData?dbsInst=%s&amp;userMode=%s&amp;proc=%s"%(self.dbsdd,dbsInst,userMode,path)
               page+="""<li>dataset: <a href="%s">%s</a>, block <b>%s</b>\n"""%(s,path,bid) 
           page+="</ul>\n"
        else:
           page+="""<p><span class="box_red">No data found</span></p>"""
        page+=self.genBottomHTML()
        return page
    findDatasetsFromLFN.exposed=True

    def getDataHelper(self,dbsInst,site="Any",group="*",app="*",primD="*",tier="*",proc="*",primType="*",date="*",hist="",_idx=0,ajax=0,userMode="user",pagerStep=RES_PER_PAGE,phedex=0,**kwargs): 
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
        self.helperInit(dbsInst)
        self.dbs  = dbsInst
        self.site = site
        self.app  = app
        self.primD= primD
        self.tier = tier
        
#        t1=time.time()
        run=""
        if kwargs.has_key('run'):
           run=kwargs['run']
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
        if type(proc) is not types.ListType and len(proc)>1:
           if  proc.find("*")!=-1 or proc.find("%")!=-1 or \
               proc.lower().find("regexp:")!=-1 or proc.lower().find("like:")!=-1:
               caseSensitive=True
               if kwargs.has_key('caseSensitive'):
                 if kwargs['caseSensitive']=="on":
                    caseSensitive=True
                 else:
                    caseSensitive=False
                    proc=proc.upper()
               if proc.lower().find("regexp:")!=-1:
                  arg="Path"
                  if not caseSensitive:
                     arg="UPPER(Path)"
                  # we got regular expression pattern
                  op,pat=proc.split("regexp:")
                  if self.helper.dbManager.dbType[dbsInst]=='oracle':
                     wClause=" REGEXP_LIKE(%s,:p0) ESCAPE '\\'"%arg
                     bDict={'p0':"%s"%pat.replace("_","\_")}
                  else:
                     wClause=" %s REGEXP :p0 "%arg
                     bDict={'p0':"%s"%pat}
                  nDatasets=self.helper.countBlocks(whereClause="",site=site,explicitWClause=wClause,explicitDict=bDict,caseSensitive=caseSensitive)
               elif proc.lower().find("like:")!=-1:
                  arg="tblk.Path"
                  if not caseSensitive:
                     arg="UPPER(tblk.Path)"
                  if self.helper.dbManager.dbType[dbsInst]=='oracle':
                     wClause,bDict=parseKeywordInput(proc.replace("_","\_"),arg)
                     nDatasets=self.helper.countBlocks(proc.replace("_","\_"),site,caseSensitive=caseSensitive)
                  else:
                     wClause,bDict=parseKeywordInput(proc,arg,type=self.helper.dbManager.dbType[dbsInst])
                     nDatasets=self.helper.countBlocks(proc,site,caseSensitive=caseSensitive)
               else:
                  # we got a pattern
                  wClause=" tblk.Path LIKE :p0 "
                  if not caseSensitive:
                     wClause=" UPPER(tblk.Path) LIKE :p0 "
                  bDict={'p0':"%s"%proc.replace('*','%')}
                  if self.helper.dbManager.dbType[dbsInst]=='oracle':
                     wClause+="ESCAPE '\\'"
                     bDict={'p0':"%s"%proc.replace('*','%').replace("_","\_")}
                     nDatasets=self.helper.countBlocks("like:%s"%proc.replace("_","\_"),site,caseSensitive=caseSensitive)
                  else:
                     nDatasets=self.helper.countBlocks("like:%s"%proc,site,caseSensitive=caseSensitive)
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
            if pagerStep:
               datasetsList = proc[_idx:_idx+pagerStep]
            else:
               datasetsList = proc
            proc=proc_orig
        elif kwargs.has_key('zstring'):
           zproc=eval( zlib.decompress(urllib.unquote(kwargs['zstring'])) )
           nDatasets=len(zproc)
           datasetsList = zproc[_idx:_idx+pagerStep]
        else:
            nDatasets = self.getDatasetList(group=group,app=appPath,prim=primD,tier=tier,proc=proc,site=site,primType=primType,date=date,count=1)
            datasetsList = self.getDatasetList(group=group,app=appPath,prim=primD,tier=tier,proc=proc,site=site,primType=primType,date=date,userMode=userMode,fromRow=_idx*pagerStep,limit=pagerStep,count=0)

        # Construct result page
        rPage=""
        if nDatasets:
           rPage+="Result page:"

#        print "Paging step before loop",time.time()-t1
        moreParams=""
        if kwargs:
           for k in kwargs.keys():
               moreParams+="&amp;%s=%s"%(k,kwargs[k])
        # the progress bar for all results
        if _idx:
            rPage+="""<a href="getData?dbsInst=%s&amp;site=%s&amp;group=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;primType=%s&amp;date=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s%s">&#171; Prev</a> """%(dbsInst,site,group,app,primD,tier,proc,primType,date,_idx-1,userMode,pagerStep,moreParams)
        tot=_idx
        for x in xrange(_idx,_idx+GLOBAL_STEP):
            if nDatasets>x*pagerStep:
               tot+=1
        for index in xrange(_idx,tot):
           ref=index+1
           if index==_idx:
              ref="""<span class="gray_box">%s</span>"""%(index+1)
           rPage+="""<a href="getData?dbsInst=%s&amp;site=%s&amp;group=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;primType=%s&amp;date=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s%s"> %s </a> """%(dbsInst,site,group,app,primD,tier,proc,primType,date,index,userMode,pagerStep,moreParams,ref)
        if nDatasets>(_idx+1)*pagerStep:
           rPage+="""<a href="getData?dbsInst=%s&amp;site=%s&amp;group=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;primType=%s&amp;date=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s%s">Next &#187;</a>"""%(dbsInst,site,group,app,primD,tier,proc,primType,date,_idx+1,userMode,pagerStep,moreParams)

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
        pagerId=1
        _nameSpace = {
                     'nDatasets': nDatasets,
                     'datasetList': datasetsList,
                     'userMode' : userMode,
                     'dbsInst'  : dbsInst,
                     'dbsInstUrl': DBS_INST_URL[dbsInst],
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
                     'pagerId'  : pagerId,
                     'nameForPager': "datasets",
                     'onchange' : "javascript:LoadGetData('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc,primType,date,_idx,ajax,userMode,pagerId,moreParams)
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
            dbsInstURL=DBS_INST_URL[dbsInst]
            for id in xrange(0,len(datasetsList)):
                dataset=datasetsList[id]
                dDict,mDict = self.helper.datasetSummary(dataset,watchSite=site,htmlMode=userMode)
                if mDict:
                    t = templateProcessedDatasetsLite(searchList=[{'dbsInst':dbsInst,'path':dataset,'appPath':appPath,'dDict':dDict,'masterDict':mDict,'host':self.dbsdd,'userMode':userMode,'phedex':phedex,'run':run,'dbsInstURL':urllib.quote(dbsInstURL),'PhedexURL':self.PhedexURL}]).respond()
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
        pagerId+=1
        _nameSpace['pagerId']=pagerId
        _nameSpace['onchange']="javascript:LoadGetData('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc,primType,date,_idx,ajax,userMode,pagerId,moreParams)
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

    def getData(self,dbsInst,site="Any",group="*",app="*",primD="*",tier="*",proc="*",primType="*",date="*",hist="",_idx=0,ajax=0,userMode="user",pagerStep=RES_PER_PAGE,phedex=0,**kwargs): 
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
           result = self.getDataHelper(dbsInst,site,group,app,primD,tier,proc,primType,date,hist,_idx,ajax,userMode,pagerStep,phedex,**kwargs)
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
            self.helperInit(dbsInst)
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

    def getRuns(self,dbsInst,dataset,_idx=0,ajax=0,userMode="user",pagerStep=RES_PER_PAGE,**kwargs): 
        """
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @type  dataset: string
           @param dataset: processed dataset path
           @rtype : string
           @return: returns HTML code
        """
        _idx=int(_idx)
        pagerStep=int(pagerStep)
        minRun="*"
        maxRun="*"
        if kwargs.has_key('minRun'): minRun=int(kwargs['minRun'])
        if kwargs.has_key('maxRun'): maxRun=int(kwargs['maxRun'])
        t1=time.time()
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="runs">"""
        else:
           page=self.genTopHTML(userMode=userMode)
        try:
            self.helperInit(dbsInst)
            page+=self.whereMsg('Navigator :: Results :: Run information',userMode)

            # pager
            nResults=0
            _minRun=0 # full range for given dataset.
            _maxRun=0 
            try:    
               nResults,_minRun,_maxRun=self.helper.getRuns(dataset=dataset,minRun=minRun,maxRun=maxRun,count=1,userMode=userMode)
               if not nResults: raise "No runs found"
            except:
               traceback.print_exc()
               msg="No runs found for your request:<br />"
               msg+="<ul>"
               msg+="<li>DBS instnace: <em>%s</em>"%dbsInst
               msg+="<li>Dataset     : <em>%s</em>"%dataset
               msg+="</ul>"
               page+=msg
               page+=self.genBottomHTML()
               return page
            ########## Construct result page
            rPage=""
            if nResults:
               rPage+="Result page:"

            if pagerStep:
                # the progress bar for all results
                if _idx:
                    rPage+="""<a href="getRuns?dbsInst=%s&amp;dataset=%s&amp;_idx=%s&amp;ajax=%s&amp;userMode=%s&amp;pagerStep=%s"> &#171; Prev</a> """%(dbsInst,dataset,_idx-1,ajax,userMode,pagerStep)
                tot=_idx
                for x in xrange(_idx,_idx+GLOBAL_STEP):
                    if nResults>x*pagerStep:
                       tot+=1
                for index in xrange(_idx,tot):
                    ref=index+1
                    if index==_idx:
                       ref="""<span class="gray_box">%s</span>"""%(index+1)
                    rPage+="""<a href="getRuns?dbsInst=%s&amp;dataset=%s&amp;_idx=%s&amp;ajax=%s&amp;userMode=%s&amp;pagerStep=%s"> %s </a> """%(dbsInst,dataset,index,ajax,userMode,pagerStep,ref)
                if  nResults>(_idx+1)*pagerStep:
                    rPage+="""<a href="getRuns?dbsInst=%s&amp;dataset=%s&amp;_idx=%s&amp;ajax=%s&amp;userMode=%s&amp;pagerStep=%s">Next &#187;</a> """%(dbsInst,dataset,_idx+1,ajax,userMode,pagerStep)

                if _idx and (_idx*pagerStep)>nResults:
                   return "No data found for this request"
                pagerId=1
                _nameSpace = {
                             'style'    : "",
                             'rPage'    : rPage,
                             'pagerStep': pagerStep,
                             'pagerId'  : pagerId,
                             'nameForPager': "rows",
                             'pList'    : [],
                             'onchange' : "javascript:LoadGetRuns('%s','%s','%s','%s','%s','%s')"%(dbsInst,dataset,_idx,ajax,userMode,pagerId)
                            }
                t = templatePagerStep(searchList=[_nameSpace]).respond()
                pagerPage=str(t)
            else:
                pagerPage=""
            page+=pagerPage
            ##### end of the pager

            tid = 't_runs_'+str(_idx)
            runList,runDBInfoDict=self.helper.getRuns(dataset,minRun=minRun,maxRun=maxRun,fromRow=_idx*pagerStep,limit=pagerStep,count=0,userMode=userMode)
            nameSpace = {
                         'dbsInst'  : dbsInst,
                         'host'     : self.dbsdd,
                         'runList'  : runList,
                         'nRun'     : nResults,
                         'runDBInfo': runDBInfoDict,
                         'tableId'  : tid,
                         'proc'     : dataset,
                         'userMode' : userMode,
                         'minRun'   : _minRun,
                         'maxRun'   : _maxRun,
                        }
            t = templateRunsInfo(searchList=[nameSpace]).respond()
            page+=str(t)
            # bottom pager
            if pagerStep:
               pagerId+=1
               _nameSpace['pagerId']=pagerId
               _nameSpace['onchange']="javascript:LoadGetRuns('%s','%s','%s','%s','%s','%s')"%(dbsInst,dataset,_idx,ajax,userMode,pagerId)
               t = templatePagerStep(searchList=[_nameSpace]).respond()
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
               res=self.helper.getRuns(dataset="",primD=primD,primType=primType,minRun=minRun,maxRun=maxRun,count=1,userMode=userMode)
               nResults=res[0] # res=nRuns, minRun, maxRun
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
            pagerId=1
            _nameSpace = {
                         'style'    : "",
                         'rPage'    : rPage,
                         'pagerStep': pagerStep,
                         'pagerId'  : pagerId,
                         'nameForPager': "rows",
                         'pList'    : [],
                         'onchange' : "javascript:LoadGetRunsFromRange('%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,primD,primType,minRun,maxRun,_idx,ajax,userMode,pagerId)
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
                pagerId+=1
                _nameSpace['pagerId']=pagerId
                _nameSpace['onchange']="javascript:LoadGetRunsFromRange('%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,primD,primType,minRun,maxRun,_idx,ajax,userMode,pagerId)
                t = templatePagerStep(searchList=[_nameSpace]).respond()
                page+=str(t)
#                page+=pagerPage
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

    def getLFNs_Runs(self,dbsInst,blockName,**kwargs):
        """
           Retrieves LFN list.
           @type  dataset: string 
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        ajaxId="blockLFNs"
        if kwargs.has_key("ajaxId"):
           ajaxId=kwargs["ajaxId"]
        blockId="blockLFNlist"
        if kwargs.has_key("blockId"):
           blockId=kwargs["blockId"]
        onchange=""
        if kwargs.has_key("onchange"):
           onchange="javascript:%s"%kwargs["onchange"]
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="%s">"""%ajaxId
        run="*"
        try:
            self.helperInit(dbsInst)
            lfnDict = self.helper.getLFNs_Runs(dbsInst,blockName)
            lfnList = lfnDict.keys()
            lfnList.sort()
            page+="""
<select multiple="multiple" name="%s" id="%s" style="width:1000px;font-size:8px" size="10" onchange="%s">\n"""%(blockId,blockId.replace("/","___"),onchange)
            for lName in lfnList:
                runList = lfnDict[lName]
                if runList:
                   page+="""\n<optgroup label="Runs: %s">"""%str(runList)
                page+="""<option value="%s">%s</option>\n"""%(lName,lName)
                if runList:
                   page+="</optgroup>\n"
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
    getLFNs_Runs.exposed = True
 
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
        ajaxId="blockLFNs"
        if kwargs.has_key("ajaxId"):
           ajaxId=kwargs["ajaxId"]
        blockId="blockLFNlist"
        if kwargs.has_key("blockId"):
           blockId=kwargs["blockId"]
        onchange=""
        if kwargs.has_key("onchange"):
           onchange="javascript:%s"%kwargs["onchange"]
#           onchange="javascript:%s"%urllib.unquote(kwargs.has_key("onchange"))
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="%s">"""%ajaxId
        dataset="*" # I don't retrieve all LFNs from dataset
        run="*"
        try:
            self.helperInit(dbsInst)
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset,run)
            page+="""
<select multiple="multiple" name="%s" id="%s" style="width:1000px;font-size:8px" size="10" onchange="%s">
<option value="All">
All LFNs in a block
</option>\n"""%(blockId,blockId.replace("/","___"),onchange)
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
 
    def formatLFNList(self,lfnList,what="txt",idx=-1):
        if not lfnList:
           return ""
        page="""<pre>\n"""
        if what=="cff":
           page+="replace PoolSource.fileNames = {\n"
        lastItem=lfnList[-1]
        if idx>=0:
           lastItem=lastItem[idx]
        for item in lfnList:
            lfn=item
            if idx>=0:
               lfn=item[idx]
            if  what=="cff":
                if lfn==lastItem:
                   page+="'%s'\n"%lfn
                else:
                   page+="'%s',\n"%lfn
            else:
                page+="%s\n"%lfn
        if what=="cff": page+="}"
        page+="\n</pre>"
        return page

    def getLFN_txt(self,dbsInst,blockName,dataset="",userMode='user',run='*',what="txt"):
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
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset,run)
            page+=self.formatLFNList(lfnList,what,idx=0)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getLFN_txt function")
            pass
            return str(t)
    getLFN_txt.exposed = True
    
    def getLFNsForSite(self,dbsInst,site,datasetPath,what="cff",userMode='user',run="*"):
        """
           Generates a list of LFNs for given site
        """
        try:
            self.helperInit(dbsInst)
            page = self.genTopHTML(userMode=userMode)
            if run and run!="*":
               page+= self.whereMsg('Navigator :: Results :: LFN list :: site \'%s\', run %s'%(site,run),userMode)
            else:
               page+= self.whereMsg('Navigator :: Results :: LFN list :: site \'%s\''%site,userMode)
            bList=[]
            try:
                lfnList=self.helper.getLFNsFromSite(site,datasetPath,run)
            except:
                if self.verbose:
                   self.writeLog(getExcept())
                printExcept()
                page+="No LFNs found for site '%s'\n"%site
                pass
            page+=self.formatLFNList(lfnList,what)
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
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset)
            page+=self.formatLFNList(lfnList,what="cff",idx=0)
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
        pagerId=1
        _nameSpace = {
                     'nDatasets': nDatasets,
                     'userMode' : userMode,
                     'dbsInst'  : dbsInst,
                     'idx'      : _idx,
                     'host'     : self.dbsdd,
                     'style'    : "margin-top:-20px",
                     'rPage'    : rPage,
                     'pagerStep': pagerStep,
                     'pagerId'  : paherId,
                     'nameForPager': "analysis datasets",
                     'onchange' : "javascript:LoadAnalysisDS('%s','%s','%s','%s')"%(dbsInst,_idx,userMode,pagerId)
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
        pagerId+=1
        _nameSpace['pagerId']=pagerId
        _nameSpace['onchange']="javascript:LoadAnalysisDS('%s','%s','%s','%s')"%(dbsInst,_idx,userMode,pagerId)
        t = templatePagerStep(searchList=[_nameSpace]).respond()
        snapshot=str(t)
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
        pagerId=1
        _nameSpace = {
                     'rPage'       : rPage,
                     'style'       : "",
                     'pagerStep'   : pagerStep,
                     'pagerId'     : pagerId,
                     'nameForPager': "blocks",
                     'onchange'    : "javascript:LoadGetFileBlocks('%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,ajax,userMode,int(_idx),int(pagerStep),pagerId)
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
        pagerId+=1
        _nameSpace['pagerId']=pagerId
        _nameSpace['onchange']="javascript:LoadGetFileBlocks('%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,ajax,userMode,int(_idx),int(pagerStep),pagerId)
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
        page="""<ajax-response><response type="object" id="kw_group_holder">"""
        self.helperInit(dbsInst)
        dList=['Any']+self.helper.getPhysicsGroups()
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':'group','iList': dList,'selTag':'kw_group','changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templateSetFromCookie(searchList=[{'name':'group','func':''}])
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
        page="""<ajax-response><response type="object" id="%s">"""%sel
        self.helperInit(dbsInst)
#        dList=['Any']+self.helper.getSites()
        siteDict=sortSitesByDomain(self.helper.getSites())
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
#        nameSpace = {'name':tag,'iList': dList,'selTag':tag,'changeFunction':'','style':style}
        nameSpace = {'name':tag,'iList': siteDict,'selTag':tag,'changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templateSetFromCookie(searchList=[{'name':'site','func':''}])
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
        page="""<ajax-response><response type="object" id="kw_tier_holder">"""
        self.helperInit(dbsInst)
        dList=['Any']+self.helper.getDataTiers()
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':'tier','iList': dList,'selTag':'kw_tier','changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templateSetFromCookie(searchList=[{'name':'tier','func':''}])
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
        page="""<ajax-response><response type="object" id="kw_prim_holder">"""
        self.helperInit(dbsInst)
        group="*"
        tier="*"
        rel="*"
        dsType="*"
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
        t = templateSetFromCookie(searchList=[{'name':'prim','func':''}])
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
        page="""<ajax-response><response type="object" id="kw_release_holder">"""
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
        t = templateSetFromCookie(searchList=[{'name':'release','func':'ajaxUpdatePrimaryDatasets();'}])
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
        page="""<ajax-response><response type="object" id="kw_primType_holder">"""
        self.helperInit(dbsInst)
        dList = ['Any']+self.helper.getPrimaryDSTypes()
        cFunc ="ajaxEngine.registerRequest('ajaxGetTriggerLines','getTriggerLines');ajaxUpdatePrimaryDatasets();"
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        if kwargs.has_key('changeFunction'): cFunc=kwargs['changeFunction']
        nameSpace = {'name':'primType','iList': dList,'selTag':'kw_primType','changeFunction':cFunc,'style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templateSetFromCookie(searchList=[{'name':'primType','func':'ajaxUpdatePrimaryDatasets();'}])
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
                       'oList'     : parents,
                       'who'       :'parents',
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
    
    def getDatasetChildren(self,dbsInst,dataset,userMode='user',**kwargs):
        """
           Get dataset children
        """
        page=self.genTopHTML(userMode=userMode)
        self.helperInit(dbsInst)
        children = self.helper.getDatasetChildren(dataset)
        nameSpace={
                   'host'      : self.dbsdd, 
                   'dataset'   : dataset, 
                   'userMode'  : userMode,
                   'dbsInst'   : dbsInst,
                   'oList'     : children,
                   'who'       :'children',
                  }
        t = templateProvenance(searchList=[nameSpace]).respond()
        page+= str(t)
        page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDatasetChildren.exposed=True
        
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
        dbsInstURL=DBS_INST_URL[dbsInst]
        PhedexURL=self.PhedexURL
        nameSpace={'host':self.dbsdd,'dbsInst':dbsInst,'path':path,'appPath':appPath,'id':id,'userMode':userMode,'dbsInstURL':urllib.quote(dbsInstURL),'PhedexURL':PhedexURL}
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
#            whereDict[key]='%'+kwargs['query'] # we will do "where like '%s%'"
            whereDict[key]=kwargs['query'] # we will do "where like 's%'"
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
        lookup="""Lookup <a href="findDSFromFinder?dbsInst=%s&amp;userMode=%s&amp;%s">processed</a> or <a href="findADSFromFinder?dbsInst=%s&amp;userMode=%s&amp;%s">analysis</a> datasets from this results"""%(dbsInst,userMode,parameters,dbsInst,userMode,parameters)
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
        query,oList = self.helper.queryMaker(iList,whereClause)
        pList=[]
        for item in oList:
            if item==oList[0]: continue # we skip first row since it's column names
            path=item.values()[-1] # get only last item from results which is Block.Path
            if not pList.count(path): pList.append(path)
        z=zlib.compress(str(pList))
        zstring=urllib.quote(z)
        if len(pList)>50:
           page=self.getData(dbsInst,zstring=zstring,ajax=0,userMode=userMode)
        else:
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

    def getIntegratedLumi(self,dbsInst,dataset,**kwargs):
        self.setContentType('xml')
        ajax=getArg(kwargs,'ajax',1)
        if ajax:
            page="""<ajax-response>"""
            page+="""<response type='element' id="intLumi%s">"""%dataset.replace("/","___")
        else:
            page=""
        try:
#            api = self.makeDbsApi(DBS_INST_URL[dbsInst])
#            int_lumi= api.getIntegratedLuminosity(dataset)
#            page+=formatLumi(int_lumi)
            page+="N/A"
        except:
            page+="N/A"
            traceback.print_exc()
            pass
        if  ajax:
            page+="</response>"
            page+="</ajax-response>"
        if  self.verbose==2:
            self.writeLog(page)
        return page
    getIntegratedLumi.exposed=True

    # helper functions to decorate output
    def aSearchShowAll(self,**kwargs):
        tabCol   = kwargs['tabCol']
        tableName,colName=tabCol.split(".")
        tableName= tableName.lower()
        colName  = colName.lower()
        sortName = kwargs['sortName']
        sortOrder= kwargs['sortOrder']
        fromRow  = int(kwargs['fromRow'])
        limit    = int(kwargs['limit'])
        # if user pass limit/fromRow as -1 we should not apply the limit
        if limit==-1 or fromRow==-1:
           limit = 0
           fromRow=0
        userInput= kwargs['userInput']
        dbsInst  = kwargs['dbsInst']
        html     = kwargs['html']
        xml      = kwargs['xml']
        case     = kwargs['caseSensitive']
        userMode = kwargs['userMode']
        output   = kwargs['output']
        self.qmaker.initDBS(dbsInst)
        sel      = self.ddrules.parser(urllib.unquote(userInput),sortName,sortOrder,case)
        page     = ""
        print "\n\n+++aSearchShowAll",kwargs
        try:
            query= self.qmaker.processQuery(sel)
        except:
            if not html:
               return traceback.format_exc()
            msg ="<pre>%s</pre>"%getExcMessage(userMode)
            page = self._advanced(dbsInst=DBSGLOBAL,userMode=userMode,msg=msg)
            return page
        result,titleList = self.qmaker.executeQuery(output,tabCol,sortName,sortOrder,query,fromRow,limit)
        if html:
           page  = self.genTopHTML(userMode=userMode)
           page += "<p>%s sorted by %s in %s order</p>"%(self.ddrules.longName[output].capitalize(),sortName,sortOrder.upper())
        else:
           if xml:
              page+="\n<%s>\n"%tableName
           else:
              page ="\n"
        for item in result:
            if html:
               page+="%s<br/>"%item[0]
            else:
               if xml:
                  page+="<%s>%s</%s>\n"%(colName,item[0],colName)
               else:
                  page+="%s \n"%item[0]
        if html:
           page+=self.genBottomHTML()
        elif xml:
             page+="</%s>\n"%tableName
        return page
    aSearchShowAll.exposed=True

    def aSearchSummary(self,**kwargs):
#        print "\n\n+++aSearchSummary",kwargs
        tabCol   = kwargs['tabCol']
        sortName = kwargs['sortName']
        sortOrder= kwargs['sortOrder']
        fromRow  = kwargs['fromRow']
        limit    = kwargs['limit']
        # if user pass limit/fromRow as -1 we should not apply the limit
        if limit==-1 or fromRow==-1:
           limit = 0
           fromRow=0
        query    = kwargs['query']
        dbsInst  = kwargs['dbsInst']
        html     = kwargs['html']
        xml      = kwargs['xml']
        userMode = kwargs['userMode']
        case     = kwargs['caseSensitive']
        output   = kwargs['output']
        grid     = int(getArg(kwargs,'grid',0))
        userInput= kwargs['userInput']
        result,titleList = self.qmaker.executeQuery(output,tabCol,sortName,sortOrder,query,fromRow,limit)
        page     = ""
        num      = kwargs['num']
        oname    = kwargs['oname']
        link     = kwargs['link']
        longName = self.ddrules.longName[output].capitalize()
        counter  = 0
        func     = lambda x: [i.lower() for i in x]
        try:
            cDateIdx= func(titleList).index('created')
        except:
            cDateIdx=-1
        if cDateIdx==-1:
           try:
               cDateIdx= func(titleList).index('creationdate')
           except:
               cDateIdx=-1
        try:
            sizeIdx = func(titleList).index('size')
        except:
            sizeIdx =-1
        try:
            cByIdx= func(titleList).index('createdby')
        except:
            cByIdx=-1
        for item in result:
            if counter%2:
               style='class="zebra"'
            else:
               style=""
            if  html:
                if  grid:
                    page+="\n<tr %s>\n"%style
                else:
                    page+="""<hr class="dbs"/>\n"""
            if  xml:
                page+="<%s>\n"%output
            for jdx in xrange(0,len(item)):
                elem = item[jdx]
                if elem==item[0]:
                   firstElem=elem
                if cDateIdx!=-1 and jdx==cDateIdx:
                   if xml:
                      elem=timeGMT(elem)
                   else:
                      elem=timeGMTshort(elem)
                elif  sizeIdx!=-1 and jdx==sizeIdx:
                   elem=colorSizeHTMLFormat(elem)
                if cByIdx!=-1 and jdx==cByIdx:
                   elem=parseCreatedBy(elem)
                if  html:
                    if grid:
                       if not jdx: td_style='class="td_left"'
                       else:       td_style=''
                       page+="""<td %s>%s</td>\n"""%(td_style,elem)
                    else:
                       if elem==item[0]: page+="""<b>%s</b><br/>\n"""%elem
                       else:             page+="""%s %s\n"""%(titleList[jdx],elem)
                else:
                    if xml:
                       page+="  <%s>%s<%s>\n"%(titleList[jdx].lower(),elem,titleList[jdx].lower())
                    else:
                       page+="%s %s \n"%(titleList[jdx],elem)
            if html and grid:
               # add more links column
               more ="""<select style="width:100px" onchange="javascript:load(this.options[this.selectedIndex].value)">\n"""
               more+="""<option value="">More Info</option>\n"""
               for key in self.ddrules.tableName.keys():
                   if key==output: continue
                   ref  = urllib.quote("find %s where %s=%s"%(key,output,firstElem))
                   aref = """%s/aSearch?userInput=%s&amp;userMode=%s&amp;dbsInst=%s&amp;caseSensitive=%s&amp;sortOrder=%s&amp;grid=%s"""%(self.dbsdd,ref,userMode,dbsInst,case,sortOrder,grid)
                   more+="""<option value='%s'>%s</option>\n"""%(aref,"Find %s"%self.ddrules.longName[key])
               more+="</select>\n"
               page+="<td %s>%s</td>\n"%(td_style,more) # for LINKS, see adding to titleList
               page+="</tr>\n"
            if not html:
               if xml:
                  page+="</%s>"%output
               page+="\n"
            counter+=1
        if grid and html and result:
           tab="""<table width="100%%" class="dbs_table">\n<tr class="tr_th">"""
           titleList+=['LINKS']
           for t in titleList:
               t=t.upper().replace("NUMBEROF","").replace("TOTAL","").replace("CREATEDBY","CREATOR").replace("CREATIONDATE","CREATED")
               if t.lower()=='created' or t.lower()=='creationdate':
                  t+="""<br/><div class="tiny">(dd/mm/yy)</div>"""
               th_class=""
               if t==titleList[0]: th_class="th_left"
               tab+="<th class=\"%s\">%s</th>"%(th_class,t)
           tab+="</tr>"
           page=tab+page+"</table>\n"
        if html and result:
           t = templateSortBar(searchList=[{'num':num,'out':output,'oname':oname,'link':link,'titleList':titleList,'excludeList':''}]).respond()
           page = str(t)+page
        if not result:
           page+="No results"
        return page

    def blockSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def fileSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def releaseSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def runSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def lumiSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def siteSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def primSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def procSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def tierSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def datasetSummary(self,**kwargs):
        tabCol   = kwargs['tabCol']
        sortName = kwargs['sortName']
        sortOrder= kwargs['sortOrder']
        fromRow  = kwargs['fromRow']
        limit    = kwargs['limit']
        # if user pass limit/fromRow as -1 we should not apply the limit
        if limit==-1 or fromRow==-1:
           limit = 0
           fromRow=0
        query    = kwargs['query']
        dbsInst  = kwargs['dbsInst']
        html     = kwargs['html']
        xml      = kwargs['xml']
        userMode = kwargs['userMode']
        output   = kwargs['output']
        grid     = int(getArg(kwargs,'grid',0))
        num      = kwargs['num']
        oname    = kwargs['oname']
        link     = kwargs['link']
        result,titleList = self.qmaker.executeQuery(output,tabCol,sortName,sortOrder,query,fromRow,limit)
        if len(titleList)==1: # no view found
           titleList=['PATH','CREATED','CREATOR','SIZE','BLOCKS','FILES','EVENTS','SITES']
        eList=['CRAB','&#8747;<em>L</em>','LINKS']
        page     = ""
        counter  = 0
        cDate=cBy=size=nblks=nfiles=nevts=nsites=""
        for item in result:
            try:
                dataset,cDate,cBy,size,nblks,nfiles,nevts,nsites=item
                excludeList=list(eList)
            except:
                # no view found
                dataset=item[0]
                excludeList = list(titleList[1:])+eList
                pass
            run=appPath=site="*"
            dbsInstURL=DBS_INST_URL[dbsInst]
            phedex=0
            if counter%2:
               style='class="zebra"'
            else:
               style=""
            if grid and cDate:
               sList = self.helper.getSiteList(dataset)
               dDict = {}
               for site in sList:
                   dDict[site]=(timeGMT(cDate),parseCreatedBy(cBy),nblks,size,nfiles,nevts)
               mDict = dDict
            else:
               # I determine which site has datasets, not all sites may have all datasets
               dDict,mDict = self.helper.datasetSummary(dataset)
               if not mDict:
                  mDict["N/A"]=("","",0,0,0,0)
            if html:
               if mDict:
                   if grid:
                      t = templateProcessedDatasetsGrid(searchList=[{'dbsInst':dbsInst,'path':dataset,'appPath':appPath,'dDict':dDict,'masterDict':mDict,'host':self.dbsdd,'userMode':userMode,'phedex':phedex,'run':run,'dbsInstURL':urllib.quote(dbsInstURL),'PhedexURL':self.PhedexURL,'style':style}]).respond()
                   else:
                      t = templateProcessedDatasetsLite(searchList=[{'dbsInst':dbsInst,'path':dataset,'appPath':appPath,'dDict':dDict,'masterDict':mDict,'host':self.dbsdd,'userMode':userMode,'phedex':phedex,'run':run,'dbsInstURL':urllib.quote(dbsInstURL),'PhedexURL':self.PhedexURL}]).respond()
                   page+=str(t)
               else:
                   page+="""<hr class="dbs" /><br/><b>%s</b><br /><span class="box_red">No data found</span>"""%dataset
            else:
                prdDate,cBy,nblks,blkSize,nFiles,nEvts=mDict.values()[0]
                seNames=dDict.keys()
                if xml:
                   nameSpace={'path':dataset,'date':prdDate,'nEvts':nEvts,'nFiles':nFiles,'nBlks':nblks,'blkSize':blkSize,'sites':seNames}
                   t = templateDatasetXML(searchList=[nameSpace]).respond()
                   page+=str(t)
                else:
                   page+="\n%s, Created %s contains %s events, %s files, %s blocks, %s, located %s"%(dataset,prdDate,nEvts,nFiles,nblks,sizeFormat(blkSize),' '.join(seNames))
            counter+=1
        if grid:
           head=""
           titleList+=eList
           for item in titleList:
               th_class=""
               item=item.upper().replace("NUMBEROF","").replace("TOTAL","").replace("CREATEDBY","CREATOR").replace("CREATIONDATE","CREATED")
               if item==titleList[0]: th_class="th_left"
               if item.lower()=='created' or item.lower()=='creationdate':
                  item+="""<br/><div class="tiny">(dd/mm/yy)</div>"""
               head+="<th class=\"%s\">%s</th>"%(th_class,item)
           head = """<table width="100%%" class="dbs_table">\n<tr class="tr_th">%s</tr>"""%head
           page=head+page+"</table>"
        if html:
           t = templateSortBar(searchList=[{'num':num,'out':output,'oname':oname,'link':link,'titleList':titleList,'excludeList':excludeList}]).respond()
           page = str(t)+page
        return page

    def aSearchCLI(self):
        return serve_file(os.path.join(os.getcwd(),'DDSearchCLI.py'),content_type='text/plain')
    aSearchCLI.exposed=True

    def update_kwargs(self,kDict,**kwargs):
        oDict=dict(kDict)
        for key in kwargs.keys():
            oDict[key]=kwargs[key]
        return oDict
    def aSearch(self,dbsInst,userMode='user',_idx=0,pagerStep=RES_PER_PAGE,**kwargs):
        _idx=int(_idx)
        pagerStep = int(pagerStep)
        html      = getArg(kwargs,'html',1)
        xml       = getArg(kwargs,'xml',0)
        case      = getArg(kwargs,'caseSensitive','on')
        sortName  = getArg(kwargs,'sortName','')
        details   = getArg(kwargs,'details',1)
        try:
            userInput = kwargs['userInput']
        except:
            traceback.print_exc()
            raise "aSearch require input query"
        output    = "dataset"
        if userInput.lower().find(" where ")!=-1:
           output=userInput.lower().split(" where ")[0].split("find")[1].strip()
        _out     = self.ddrules.longName[output]
        if sortName.lower()=="name":
           sortName = self.ddrules.colName[output]
        sortOrder = getArg(kwargs,'sortOrder','desc')
        try :
            sel = self.ddrules.parser(urllib.unquote(userInput),sortName,sortOrder,case)
        except:
            if not html:
               return traceback.format_exc()
            msg ="<pre>%s</pre>"%getExcMessage(userMode)
            page = self._advanced(dbsInst=DBSGLOBAL,userMode=userMode,msg=msg)
            return page

        fromRow  =_idx*pagerStep
        limit    = pagerStep
        oTable   = self.ddrules.tableName[output]
        tabCol   = "%s.%s"%(oTable,self.ddrules.colName[output])
        self.qmaker.initDBS(dbsInst)
        try:
            query= self.qmaker.processQuery(sel,userMode)
        except:
            if not html:
               return traceback.format_exc()
            msg ="<pre>%s</pre>"%getExcMessage(userMode)
            page = self._advanced(dbsInst=DBSGLOBAL,userMode=userMode,msg=msg)
            return page
        nResults = self.qmaker.countSel(query,tabCol)

        # construct output kwargs
        kDict=self.update_kwargs(kwargs,query=query,output=output,tabCol=tabCol,sortName=sortName,sortOrder=sortOrder,dbsInst=dbsInst,fromRow=fromRow,limit=limit,html=html,xml=xml,userMode=userMode,userInput=userInput,case=case)

        if html:
           page = self.genTopHTML(userMode=userMode)
           dbsList=list(self.dbsList)
           dbsList.remove(dbsInst)
           dbsList=[dbsInst]+dbsList
           nameSearch={'dbsInst':dbsInst,'userHelp':0,'dbsList':dbsList,'host':self.dbsdd,'style':'','userMode':userMode,'userInput':userInput}
           t = templateAdvancedSearchForm(searchList=[nameSearch]).respond()
           page+=str(t)
        else:
           if xml:
              page="""<?xml version="1.0" encoding="utf-8"?>\n<ddresponse>\n"""
              page+="<userinput>\n  <input>%s</input>\n  <timeStamp>%s</timeStamp>\n</userinput>\n"%(urllib.unquote(userInput),time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime()))
              bParams=self.qmaker.extractBindParams(query)
              bindParams="\n"
              for key in bParams:
                  bindParams+="    <%s>%s</%s>\n"%(key,bParams[key],key)
              page+="""<query>\n  <sql>\n%s\n  </sql>\n  <bindparams>%s</bindparams>\n</query>\n"""%(query,bindParams)
           else:
              if details:
                 page ="\nFound %s %ss, showing results from %s-%s\n"%(nResults,_out,_idx*pagerStep,_idx*pagerStep+pagerStep)
              else:
                 page ="\nFound %s %ss\n"%(nResults,_out)

        if html:
           # Construct result page
           rPage=""
           if nResults:
              rPage+="Result page:"

           moreParams=""
           if kwargs:
              for k in kwargs.keys():
                  moreParams+="&amp;%s=%s"%(k,urllib.quote(kwargs[k]))
           # the progress bar for all results
           if _idx:
               rPage+="""<a href="aSearch?dbsInst=%s&amp;userMode=%s&amp;_idx=%s&amp;pagerStep=%s%s">&#171; Prev</a> """%(dbsInst,userMode,_idx-1,pagerStep,moreParams)
           tot=_idx
           for x in xrange(_idx,_idx+GLOBAL_STEP):
               if nResults>x*pagerStep:
                  tot+=1
           for index in xrange(_idx,tot):
              ref=index+1
              if index==_idx:
                 ref="""<span class="gray_box">%s</span>"""%(index+1)
              rPage+="""<a href="aSearch?dbsInst=%s&amp;userMode=%s&amp;_idx=%s&amp;pagerStep=%s%s"> %s </a> """%(dbsInst,userMode,index,pagerStep,moreParams,ref)
           if nResults>(_idx+1)*pagerStep:
              rPage+="""<a href="aSearch?dbsInst=%s&amp;userMode=%s&amp;_idx=%s&amp;pagerStep=%s%s">Next &#187;</a>"""%(dbsInst,userMode,_idx+1,pagerStep,moreParams)

           if _idx and _idx*pagerStep>nResults:
              return "No data found for this request"

           page+=self.whereMsg('Adv. search :: Results',userMode)

        # create a link for show all.
        link=""
        for key in kDict:
            if key=='query': continue
            if link: link+="&amp;"
            link+="%s=%s"%(key,kDict[key])
        link="aSearchShowAll?"+link
        kDict['num']=nResults
        kDict['oname']=_out
        kDict['link']=link

        try:
            if userMode=='dbsExpert':
               page+="<pre>%s</pre>"%query
            if details:
               method=getattr(self,output+'Summary')
               page+=method(**kDict)
            else:
               page+=self.aSearchShowAll(**kDict)
        except:    
            if html:
               page+="<verbatim>"+getExcept()+"</verbatim>"
            else:
               if xml:
                  page+="Server experience a problem processing your request"
                  page+=getExcept()
               else:
                  page+=getExcept()
        if html:
           pagerId=1
           _nameSpace = {
                        'idx'      : _idx,
                        'host'     : self.dbsdd,
                        'style'    : "",
                        'rPage'    : rPage,
                        'pagerStep': pagerStep,
                        'pagerId'  : pagerId,
                        'nameForPager': _out+"s",
                        'onchange' : "javascript:LoadASearch('%s','%s','%s','%s','%s')"%(dbsInst,userMode,_idx,pagerId,userInput)
                       }
           t = templatePagerStep(searchList=[_nameSpace]).respond()
           page+="""<hr class="dbs" />"""
           page+=str(t)
           page+=self.genBottomHTML()
        elif xml:
           page+="</ddresponse>"
        return page
    aSearch.exposed=True

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
    port=opts.port
    if  int(string.split(cherrypy.__version__,".")[0])==3:
        #cherrypy.config.update("CherryServer3.conf")
        cherrypy.config.update({'server.socket_port': port,
                                'server.thread_pool': 20,
                                'environment': 'production',
                                'log.screen':True,
                                'log.error_file':"dbs.log",
                               })
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
