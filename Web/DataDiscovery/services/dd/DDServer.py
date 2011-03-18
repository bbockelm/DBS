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
from xml.dom import *
try:
    # Python 2.5
    import xml.etree.ElementTree as ET
except:
    # prior requires elementtree
    import elementtree.ElementTree as ET

# Cheetah template modules
from   Cheetah.Template import Template

# CherryPy server modules
import cherrypy 
from   cherrypy    import expose
from   cherrypy    import tools
from   cherrypy.lib.static import serve_file

# DBS  modules
from   utils.DDUtil        import *
from   utils.DDConfig      import *
from   model.dd.DDHelper   import *
from   Templates           import *
from   utils.DDParamServer import *
from   utils.DDWS          import *
from utils.DDAuth import DDAuthentication

from utils.sitedb_tools import SiteDBManager
from utils.siteconfig_tools import SiteConfigManager
from utils.phedex_tools import PhedexManager
from utils.dbs_tools import DBSManager

# support for SSL
try:
    import OpenSSL
except:
    pass

# DBS framework in order to make migration
try:
    from DBSAPI.dbsMigrateApi import DbsMigrateApi
except:
    traceback.print_exc()
    pass

# DBS migration service
try:
    from MS.Wrapper import API as DBS_MS
except:
    traceback.print_exc()
    pass

# webtools framework
from utils.webtools_modules import *

class DDServer(Controller): 
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
        t0 = time.time()
        self.securityApi=""
        try:
            if  context:
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

        # data service managers
        self.auth     = DDAuthentication()
        self.dbsmgr   = DBSManager(self.ddConfig.dbsVer(), self.ddConfig.rs(), verbose)
        self.sdb      = SiteDBManager(verbose)
        self.phedex   = PhedexManager(self.sdb, verbose)
        self.sitecfg  = SiteConfigManager(self.sdb, verbose)

        self.phedexServer= DDParamServer(server="https://cmsweb.cern.ch",verbose=verbose)
        self.PhedexURL="https://cmsweb.cern.ch/phedex/prod/Request::Create"
        self.dbsglobal = self.ddConfig.dbsprimary()
#        self.dbsglobal = DBSGLOBAL
#        self.dbsglobal = 'global_r'
        self.yyyymmdd=re.compile('^\d{8}$')
        self.pathMatch=re.compile("^\/[^/]+\/[^/]+\/[^/]+$")
        self.baseUrl = ""
        self.topUrl= ""
        self.mastheadUrl = self.ddConfig.masthead()
        self.footerUrl   = self.ddConfig.mastfooter()
        self.adminUrl = self.ddConfig.adminUrl()
        self.msApi=""
        try:
            self.msApi = DBS_MS(url=self.adminUrl)
            print "\n+++ Loaded DBS MS API",self.adminUrl
        except:
            if verbose:
               traceback.print_exc()
            pass
        self.adminVer = self.ddConfig.adminVer()
        self.ns = self.ddConfig.ns()
        self.globalDD = self.ddConfig.global_dd()
        self.ddUrls = []
        self.ddUrls.append(self.globalDD)
        self.ddServices={} # e.g. {'url1':{'time':time,'nsets':nsets,...},}
        self.ddServices[self.globalDD]={}
        self.site = ""
        self.app  = ""
        self.primD= ""
        self.tier = ""
        self.cmsNames   = {}
        self.iface      = self.ddConfig.iface()
        self.helper     = DDHelper(self.dbsmgr, self.ddConfig, verbose)
        self.dbsList    = self.dbsmgr.aliases()
        self.dbsList.sort()
        try:
           self.dbsList.remove(self.dbsglobal)
        except:
           pass
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
        self.dbsList = [self.dbsglobal]+self.dbsList
        try:
            self.dbsdd = self.ddConfig.url()
            if self.verbose:
               print "Read from config"
        except:
            if self.verbose:
               traceback.print_exc()
            raise traceback.format_exc()
        if os.environ.has_key('DBSDD'):
           self.dbsdd = os.environ['DBSDD']
        self.dbsConfig={'url':self.dbsdd,'mode':'POST','version':self.ddConfig.dbsVer(),'retry':2}
        try:
            self.hostname = socket.gethostbyaddr(socket.gethostname())[0]
        except:
            self.hostname = 'localhost'
            pass
        print "+++ DDServer URL '%s'"%self.dbsdd
        print "+++ Using %s interface"%self.iface
        print "+++ Using %s dbs-client version"%self.ddConfig.dbsVer()
        try:
            self.outputConfigMap()
        except:
            traceback.print_exc()
            pass
        self.formDict   = {
                           'menuForm': ("","","","","",""), # (msg,dbsInst,site,app,primD,tier)
                           'siteForm': ("",""), # (dbsInst,site)
                           'searchForm': self.dbsglobal # (dbsInst,)
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
           self.ddUrls.append(self.dbsdd)
           self.ddServices[self.dbsdd]={}
        totTime = time.time()-t0
        print "+++ DDServer started in %s sec" % totTime

    def readyToRun(self):
        opts=self.context.CmdLineArgs().opts
        try:
            self.helper.setVerbose(opts.verbose)
            self.setLevel(opts.verbose) # set logger level
            self.verbose=opts.verbose
        except:
            pass
        self.baseUrl = opts.baseUrl
        print "===> baseURL", self.baseUrl
        if  not self.baseUrl:
            self.baseUrl="/"
#        if self.baseUrl[-1]!="/": self.baseUrl+="/"
        self.mastheadUrl=self.baseUrl+"sitedb/Common/masthead"
        self.footerUrl=self.baseUrl+"sitedb/Common/footer"
        self.topUrl=self.baseUrl

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

    def redirectPage(self,**kwargs):
        page = self.genTopHTML()
        nameSpace = { 'localtime':time.asctime() }
        t = templateRedirect(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    redirectPage.exposed=True     
        
    def setContentType(self,type):
        """
           Set CherryPy Content-Type to provided type
           @type type: string
           @param type: type of application, "text/xml" or "text/html"
           @rtype: none
           @return: none
        """
        cherrypy.response.headers['Expires']="Thu, 15 Apr 2012 20:00:00 GMT"
#        cherrypy.response.headers['Last-Modified']=time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime())
        cherrypy.response.headers['Cache-Control']="no-store, no-cache, must-revalidate, max-age=315360000"
        cherrypy.response.headers['Cache-Control']="post-check=0, pre-check=0"
        
        if type=="xml":
           cherrypy.response.headers['Content-Type']='text/xml'
        elif type=="rss":
           cherrypy.response.headers['Content-Type']='application/rss+xml'
        elif type=="plain":
           cherrypy.response.headers['Content-Type']='text/plain'
        else:
           cherrypy.response.headers['Content-Type']='text/html'

    def ws(self,**kwargs):
        self.namespace_expr = re.compile(r'^\{.*\}')
        # get request data and produce an ElementTree that we can work with.
        request = cherrypy.request
        if self.verbose==2:
           print request.__dict__
        
        response = cherrypy.response
        if self.verbose==2:
           print response.__dict__
        if request.headers.has_key('Content-Length'):
           clen = int(request.headers.get('Content-Length'))
        else:
           clen = 0
        data = request.body.read(clen)
        
        request.soap_start = data[:2048]
        soapreq = ET.fromstring(data)

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
        print "Input params="+repr(kwargs)
        xml_body=kwargs['xml_body']
        print "%s %s"%(type(xml_body),repr(xml_body))
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
        nsets = self.helper.nDatasets(dbsInst)
        msg="""<NumberOfDatasets><int>%s</int></NumberOfDatasets>"""%nsets
        page=self.soapMsg(msg)
        print page
        return page
    wsGetNDatasets.exposed=True

    def wsGetDatasetSummary(self,**kwargs):
        self.setContentType('xml')
        xmlDict=self.parseWSInput(**kwargs)
        if not xmlDict.has_key('dataset'):
           return self.wsError("Wrong parameter")
        oDict,mDict = self.helper.datasetSummary(dbsInst, xmlDict['dataset'])
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
        print page
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
           self.ddServices[url]={}
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
           print "\n\n### I got update from '%s' it contains '%s'"%(url,nsets)
           serviceDict=self.ddServices[url]
           serviceDict['nsets']=nsets
           if not serviceDict.has_key('time') or \
             (time.time()-serviceDict['time'])>5*60: # less then 5 minutes
               aDict={}
               aDict['url']=self.dbsdd
               aDict['nsets']=self.helper.nDatasets(dbsInst)
               print "*** send update to '%s' with aDict=%s"%(url,aDict)
               thread.start_new_thread(self.sendSOAP,(url,"wsUpdateSiteInfo",aDict))
               serviceDict['time']=time.time()
               self.ddServices[url]=serviceDict
           print "+++ wsUpdateSiteInfo",self.ddServices
        # send my info to known sites
#        vlock = thread.allocate_lock()
#        for url in self.ddUrls:
#            if url==self.dbsdd: continue
#            aDict={}
#            aDict['url']=self.dbsdd
#            aDict['nsets']=self.helper.nDatasets(dbsInst)
#            print aDict
###            thread.start_new_thread(self.sendSOAP,(url,"wsUpdateSiteInfo",aDict))

    def sendErrorReport(self, dbsInst, iMsg=""):
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
        msg+="Request from: %s:%s\n"%(cherrypy.request.remote.ip,cherrypy.request.remote.port)
        url =cherrypy.url()
        msg+="Request url : %s\n"%url
        msg+="\n\n"
        msg+="DDServer:\n"
        msg+="%s: %s\n"%("DBS Instance   ", dbsInst)
        sendEmail(msg)
        
    def getCMSNames(self):
        try:
            if not self.cmsNames:
               self.cmsNames=getCMSNames()
            else:
               lastTime=self.cmsNames['time']
               if (time.time()-lastTime)>(24*60*60): # more then a day
                  self.cmsNames=getCMSNames()
        except:
             self.cmsName=[]
             pass
        return self.cmsNames

    def genTopHTML(self,intro=False,userMode='user',onload=''):
        """
           Generates top structure of HTML page using Cheetah template.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        standalone = 1
        if  self.securityApi:
            standalone = 0
        nameSpace = {
                     'host'        : self.dbsdd,
#                     'baseUrl'     : self.baseUrl,
                     'baseUrl'     : self.topUrl,
                     'mastheadUrl' : self.mastheadUrl,
                     'footerUrl'   : self.footerUrl,
                     'title'       : 'DBS Data Discovery Page',
                     'dbsGlobal'   : self.dbsglobal,
                     'userMode'    : userMode,
                     'onload'      : onload,
                     'standalone'  : standalone 
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

    def index(self,dbsInst='',userMode='user',**kwargs): 
        """
           Construct start up page by invoking L{init} call.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        if  not dbsInst:
            dbsInst = self.dbsglobal
        return self._advanced(dbsInst,userMode)
    index.exposed = True 

    def errorReport(self, dbsInst, msg):
        """
           Error handle routine.
           @type msg: string
           @param msg: input text
           @rtype: string
           @return: returns HTML code
        """
        port=cherrypy.request.remote.port
        host=cherrypy.request.remote.ip
        url =cherrypy.url()
        nameSpace = {
                     'msg':getExceptionInHTML(),
                     'port': port,
                     'host': host,
                     'url' : url 
                    }
        t = templateERROR(searchList=[nameSpace]).respond()
        self.sendErrorReport(dbsInst, msg+"\n"+getExcept())
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
               traceback.print_exc()
            pass
        return userName

    def decodeUserName(self,**kwargs):
        if kwargs.has_key('dn'):
           dn=kwargs['dn'][1:-1] # the DN I get from browser has extra quotes at beg/end of string
           if not dn: return ""
           return decryptCookie(dn, self.securityApi)
        return self.getUserFromCookie()
        cookie=cherrypy.request.headers['cookie']
        for elem in cookie.split(";"):
            item=elem.strip()
            if len(item)>4 and item[0:3]=="dn=":
               dn = item[3:].replace("\"","")
               return decryptCookie(dn,self.securityApi)
        return ""

    def getEmail(self,userName):
        return "%s@cern.ch"%userName

    def getUserEmail(self):
#        userName=self.decodeUserName(**kwargs)
#        dn=urllib.quote(self.getDN(userName))
#        email=self.getEmail(self.getDN(userName))
        user=self.getUserFromCookie()
        return "%s@cern.ch"%user

    def getUserFromCookie(self):
        user = ""
        cookie=cherrypy.request.headers['cookie']
        for elem in cookie.split(";"):
            item=elem.strip()
            if len(item)>4 and item[0:3]=="dn=":
               dn = item[3:].replace("\"","")
               user=decryptCookie(dn,self.securityApi)
        return user

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

    def checkParam(self,p):
        if p.find("<")!=-1 or p.find(">")!=-1:
           msg="Invalid input parameter, '%s'"%p
           raise msg

    ################## Menu init methods
    def _status(self, userMode="user", **kwargs):
        page  = self.genTopHTML()
        dbses = dict(self.dbsmgr.dbsattr)
        t     = templateDBSstatus(searchList=[{'dbsdict':dbses}]).respond()
        page += str(t)
        page += 'For more information please visit <a href="http://cmsdbssrv.cern.ch/rswebapp/html/home.jsf">RS page</a>'
        page += self.genBottomHTML()
        return page
    _status.exposed = True

    def _navigator(self,dbsInst='',userMode="user",**kwargs):
        if  not dbsInst:
            dbsInst = self.dbsglobal
        try:
            for p in [dbsInst,userMode]+kwargs.keys():
                self.checkParam(p)
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
            t=self.errorReport(dbsInst, "Fail in navigator init function")
            pass
            return str(t)
    _navigator.exposed=True

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
            t=self.errorReport('', "Fail in config init function")
            pass
            return str(t)
    _config.exposed=True

    def _analysis(self,dbsInst='',userMode="user"):
        if  not dbsInst:
            dbsInst = self.dbsglobal
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= self.whereMsg('Analysis dataset search',userMode)

            # make auto-completion forms for ads name and def name
#            nameSearch={'tag':'ads_name','inputId':'ads_name','inputName':'ads_name','size':100,'userMode':userMode,'dbsInst':dbsInst,'table':'AnalysisDataset','column':'Name','label':'Name:','zIndex':9000,'method':'getTableColumn'}
            nameSearch={'tag':'ads_name','inputId':'ads_name','inputName':'ads_name','size':100,'userMode':userMode,'dbsInst':dbsInst,'key':'ads','wherekey':'ads','label':'Name:','zIndex':9000,'method':'findInDBS'}
            t = templateAutoComplete(searchList=[nameSearch]).respond()
            adsName=str(t)
#            nameSearch={'tag':'adsd_name','inputId':'adsd_name','inputName':'adsd_name','size':100,'userMode':userMode,'dbsInst':dbsInst,'table':'AnalysisDSDef','column':'Name','label':'Definition name:','zIndex':8000,'method':'getTableColumn'}
            nameSearch={'tag':'adsd_name','inputId':'adsd_name','inputName':'adsd_name','size':100,'userMode':userMode,'dbsInst':dbsInst,'key':'ads','wherekey':'ads.desc','label':'Definition name:','zIndex':8000,'method':'findInDBS'}
            t = templateAutoComplete(searchList=[nameSearch]).respond()
            adsDefName=str(t)

            tierList = self.helper.getDataTiers(dbsInst)
            dbsList=list(self.dbsList)
            dbsList.remove(dbsInst)
            dbsList=[dbsInst]+dbsList
            nameSearch={'tierList':tierList,'userMode':userMode,'dbsList':dbsList,'adsName':adsName,'adsDefName':adsDefName,'host':self.dbsdd}
            t = templateMenuAnalysis(searchList=[nameSearch]).respond()
            page+= str(t)

            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in analysis init function")
            pass
            return str(t)
    _analysis.exposed=True

    def _advanced(self,dbsInst='',userMode="user",msg="",**kwargs):
        if  not dbsInst:
            dbsInst = self.dbsglobal
        try:
            dbsList = []
            for dbs in self.dbsList:
                if  userMode=="user":
                    if  dbs==self.dbsglobal or dbs.find("ph_analys")!=-1 or dbs.find('_caf_')!=-1:
                        dbsList.append(dbs)
                else:
                    dbsList.append(dbs)
            try:
                dbsList.remove(dbsInst)
            except:
                pass
#            dbsList=list(self.dbsList)
#            dbsList.remove(dbsInst)
            dbsList=[dbsInst]+dbsList
            if msg: userHelp=0
            else:   userHelp=1
            nameSearch={'dbsInst':dbsInst,'userHelp':userHelp,'dbsList':dbsList,'host':self.dbsdd,'style':'','userMode':userMode,'userInput':'','aSearchKeys':self.aSearchKeys(),'showHelp':1}
            t = templateAdvancedSearchForm(searchList=[nameSearch]).respond()
            if userMode=="user":
               page = self.genTopHTML(intro=False,userMode=userMode,onload="resetUserNav();")
               page+="""<p class="sectionhead">ADVANCED KEYWORD SEARCH</p>"""
            else:
               page = self.genTopHTML(intro=False,userMode=userMode)
            page+= str(t)
            if msg:
               page+=msg
            if userMode=="user":
#               page+="""<hr class="dbs" />"""
               page+="""<p class="sectionhead">MENU-DRIVEN INTERFACE</p>"""
               auto=0
               if kwargs.has_key('auto') and kwargs['auto']=='on':
                  auto=1
               userNav = self.genEmptyUserNavigator(dbsInst,userMode,auto,navigator=1)
               t = templateMenuNavigator(searchList=[{'userNavigator':userNav}]).respond()
               page+= str(t)
            elif userMode=="dbsExpert":
               page+="""<hr class="dbs" />"""
#               nameSpace = {'userMode':userMode,'ddList':self.ddUrls,'dbsdd':self.dbsdd}
#               page+= templateRemoteDD(searchList=[nameSpace]).respond()
               # Walk through all registered DD services and exchange update SOAP msg's
#               nsets = self.helper.nDatasets(dbsInst)
#               for url in self.ddUrls:
#                   if url!=self.dbsdd:
#                      self.sendSOAP(url,"wsUpdateSiteInfo",{'url':self.dbsdd,'reply':self.dbsdd,'nsets':nsets})
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in advanced init function")
            pass
            return str(t)
    _advanced.exposed=True

    def _multiSearch(self, userMode = "user", userInput = "", genTop = 1, **kwargs):
        try:
            dbsList = list(self.dbsList)
            nameSearch={'userMode':userMode, 'userInput': userInput, 
                        'dbsList':dbsList}
            t = templateMultiSearchForm(searchList=[nameSearch]).respond()
            page = ""
            if  genTop:
                page += self.genTopHTML(intro=False,userMode=userMode)
            page+= str(t)
            if  genTop:
                page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in advanced init function")
            pass
            return str(t)
    _multiSearch.exposed=True

    def _rss(self,userMode="user"):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="resetUserNav();")
            page+= self.whereMsg('RSS feeds',userMode)
            emptyList=[]    
            nameSearch= {
                          'dbsInst'     : self.dbsglobal,
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
            t=self.errorReport('', "Fail in rss init function")
            pass
            return str(t)
    _rss.exposed=True

    def rssGenerator(self,primD,app="Any",dbsInst='',userMode="user",**kwargs):
        if  not dbsInst:
            dbsInst = self.dbsglobal
        self.setContentType('rss')
        if string.lower(app)   =="all" or string.lower(app)   =="any": app="*"
        if string.lower(primD) =="all" or string.lower(primD) =="any": primD="*"
        try:
            if primD=="*" and app=="*":
               p="In order to subscribe to RSS feeds you must choose either Primary dataset or Release"
               return self.genTopHTML()+p+self.genBottomHTML()
        
            # get primDesc and creation date
            primPubDate,primDesc=self.helper.getPrimDetailsForRSS(dbsInst, prim=primD)
            # for given primary, generate procList
            procList = self.helper.getProcDSForRss(dbsInst, prim=primD,rel=app)
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
            t=self.errorReport(dbsInst, "Fail in rss init function")
            page=str(t)
            pass
        if self.verbose==2:
           print page
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
            t=self.errorReport('', "Fail in siteSearch init function")
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
            t=self.errorReport('', "Fail in history init function")
            pass
            return str(t)
    _history.exposed=True

    def _pages(self,userMode):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            nameSpace = {
                         'userMode' : userMode,
                         'ddList'   : self.ddUrls,
                         'dbsdd'    : self.dbsdd,
                        }
            page+= templateRemoteDD(searchList=[nameSpace]).respond()
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport('', "Fail in help init function")
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
                         'dbs_ql'       : self.aSearchKeys(),
                         'section'      : section,
                        }
            t = templateMenuHelp(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport('', "Fail in help init function")
            pass
            return str(t)
    _help.exposed=True

    @is_authenticated (onFail=RedirectToLocalPage ("/redirectPage"))
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
            t=self.errorReport('', "Fail in contact init function")
            pass
            return str(t)
    _contact.exposed=True

#    @is_authorized (Role("Global Admin"), Group("DBS"), 
#		    onFail=RedirectToLocalPage ("/redirectPage"))
#    def _dbsExpert(self,dbsInst='',userMode="dbsExpert"):
#        if  not dbsInst:
#            dbsInst = self.dbsglobal
#        try:
#            page = self.genTopHTML(intro=False,userMode=userMode)
#            page+= self.whereMsg('DBS expert page',userMode)
#            dbsTables = self.dbManager.getTableNames(dbsInst)
#            dbsTables.sort()
#            nameSpace = {
#                         'dbsList'      : self.dbsList,
#                         'dbsTables'    : dbsTables
#                        }
#            t = templateMenuDbsExpert(searchList=[nameSpace]).respond()
#            page+= str(t)
#            page+= self.genBottomHTML()
#            return page
#        except:
#            t=self.errorReport(dbsInst, "Fail in dbsExpert init function")
#            pass
#            return str(t)
#    _dbsExpert.exposed=True

    def _runs(self,dbsInst='',userMode="user"):
        if  not dbsInst:
            dbsInst = self.dbsglobal
        try:
            page = self.genTopHTML(intro=False,userMode=userMode,onload="resetRunNav();")
            page+= self.whereMsg('Run search',userMode)
            nameSpace = {
                         'dbsList'      : self.dbsList,
                         'dbsInst'      : self.dbsglobal,
                         'userMode'     : userMode,
                         'style'        : 'width:200px',
                        }
            t = templateMenuRuns(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in run search init function")
            pass
            return str(t)
    _runs.exposed=True

    def _tools(self,userMode='user'):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= templateTools(searchList=[{}]).respond()
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport('', "Fail in tools init function")
            pass
            return str(t)
    _tools.exposed=True
    def tool_cli(self,userMode='user'):
        try:
            page = self.genTopHTML(intro=False,userMode=userMode)
            page+= templateDBSCLI(searchList=[{'ver':self.ddConfig.dbsVer()}]).respond()
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport('', "Fail in tools init function")
            pass
            return str(t)
    tool_cli.exposed=True
    def dbssql(self):
        return serve_file(os.path.join(os.environ['DDHOME'],'tools/dbssql'),\
                content_type='text/plain')
    dbssql.exposed=True
    def tool_du(self):
        return serve_file(os.path.join(os.environ['DDHOME'],'tools/cms-dbs-du'),\
                content_type='text/plain')
    tool_du.exposed=True
    def tool_ls(self):
        return serve_file(os.path.join(os.environ['DDHOME'],'tools/cms-dbs-ls'),\
                content_type='text/plain')
    tool_ls.exposed=True
    def tool_stat(self):
        return serve_file(os.path.join(os.environ['DDHOME'],'tools/cms-dbs-stat'),\
                content_type='text/plain')
    tool_stat.exposed=True
    def tool_pfn(self):
        return serve_file(os.path.join(os.environ['DDHOME'],'tools/find-pfn'),\
                content_type='text/plain')
    tool_pfn.exposed=True

    ################## END OF init methods
    def whereMsg(self,msg,userMode):
        t = templateWhere(searchList=[{'where':'DBS discovery :: '+msg,'userMode':userMode}]).respond()
        return str(t)

    def genEmptyUserNavigator(self,dbsInst,userMode="user",auto=0,**kwargs):
        if auto:
           # auto-competion form for processed datasets
#           nameSearch={'tag':'proc','inputId':'proc','inputName':'proc','size':'80','userMode':userMode,'dbsInst':dbsInst,'table':'Block','column':'Path','label':'','zIndex':9000,'method':'getTableColumn'}
           nameSearch={'tag':'proc','inputId':'proc','inputName':'proc','size':'80','userMode':userMode,'dbsInst':dbsInst,'key':'dataset','wherekey':'dataset','label':'','zIndex':9000,'method':'findInDBS'}
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
                    'siteDict'    : {},
                    'style'       : "width:200px",
                    'prdForm'     : prdForm,
                    'autocomplete': auto,
                   }
        nav = getArg(kwargs,'navigator','')
        if nav:
           t = templateNavigator(searchList=[nameSearch]).respond()
        else:
           t = templateUserNav(searchList=[nameSearch]).respond()
        page+= str(t)
        if self.verbose==2:
           print page
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
                    'groupList'   : self.helper.getPhysicsGroups(dbsInst),
                    'dataTypes'   : self.helper.getDataTiers(dbsInst),
                    'softReleases': self.helper.getSoftwareReleases(dbsInst),
                    'siteDict'    : sortSitesByDomain(self.helper.getSites(dbsInst)), 
                    'style'       : "width:200px",
                   }
        t = templateUserNav(searchList=[nameSearch]).respond()
        page+= str(t)
        if  int(ajax)==1:
            page+="</response></ajax-response>"
        if self.verbose==2:
           print page
        return page
    genUserNavigator.exposed=True 

    def searchForm(self,firstDBS='',userMode='user'):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """
        if  not firstDBS:
            firstDBS = self.dbsglobal

        searchFunc="javascript:ResetAllResults();ajaxFinderSearch('%s')"%userMode
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
                     'dbsGlobal'      : self.dbsglobal,
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
            t=self.errorReport(dbsInst, "Fail in genNavigatorMenuDict function")
            page+=str(t)
            pass
        page+=endAjaxMsg
        if self.verbose==2:
           print page
        return page
    genNavigatorMenuDict.exposed = True

    #################### ADMIN FORMS #######################
    def _tinyurl(self, url, **kwargs):
        page  = self.genTopHTML()
        js    = "load('http://tinyurl.com/create.php?url='+'%s')" % url
        page += """<script type="text/javascript">%s</script>""" % js
        page += self.genBottomHTML()
        return page
    _tinyurl.exposed=True

    def _admin(self,**kwargs):
        page = self.genTopHTML()
        try:
            dbsInst = kwargs['dbsInst']
        except:
            dbsInst = 'cms_dbs_prod_global'
	dbslist = list(self.dbsList)
	dbslist.remove(dbsInst)
	dbslist = [dbsInst] + dbslist
        nameSpace = {'host':self.dbsdd, 'dbsInst':dbsInst, 'dbslist':dbslist, 'userMode':'expert', 'user': self.getUserFromCookie() }
        t = templateAdministrateForm(searchList=[nameSpace]).respond()
        page+= str(t)
#        page+= """<hr class="dbs" />"""
        page+= """<br/><div class="sectionhead">CHECK YOUR REQUESTS:</div>"""
        kwargs['fullpage'] = 0
        page+= self.ms_getRequestByUser(**kwargs)
        page+= self.genBottomHTML()
        return page
    _admin.exposed=True

    def redirectAdminPage(self,**kwargs):
        page = self.genTopHTML()
        try:
            dbsInst = kwargs['dbsInst']
        except:
            dbsInst = 'cms_dbs_prod_global'
	dbslist = list(self.dbsList)
	dbslist.remove(dbsInst)
	dbslist = [dbsInst] + dbslist
        page+= "<div><b>You have unsufficient privileges to administrate datasets. Please contact cms-dbs-support [at] cern [dot] ch with this request.</b></div><br/><br/>"
        nameSpace = {'host':self.dbsdd, 'dbsInst':dbsInst, 'dbslist':dbslist, 'userMode':'expert', 'user': self.getUserFromCookie() }
        t = templateAdministrateForm(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    redirectAdminPage.exposed=True     
        
    @is_authorized (Role("DBSExpert"), Group("DBS"), 
                    onFail=RedirectToLocalPage ("/redirectAdminPage"))
    def adminDataset(self,dbsInst,dataset,userMode,siteList="[]",**kwargs):
        page = self.genTopHTML(userMode=userMode)
        # check that supplied instance and dataset are real
        nRes = self.aSearch(dbsInst,userInput="find dataset where dataset = '%s'"%dataset,results=1)
        if nRes!=1:
           page+="<p>No '%s' dataset found in DBS"%dataset
           page+= self.genBottomHTML()
           return page
        if siteList=="[]":
           siteList=str(self.helper.getSiteList(dbsInst, dataset.strip()))
        page+= self.whereMsg('Navigator :: Results :: list of datasets :: admin tasks',userMode)

        # auto-competion form for processed datasets
#        nameSearch={'tag':'blockName','inputId':'blockName','inputName':'blockName','size':'100','userMode':userMode,'dbsInst':dbsInst,'key':'block.name','wherekey':'block.name','label':'','zIndex':9000,'method':'findInDBS'}
#        t = templateAutoComplete(searchList=[nameSearch]).respond()
#        blkForm=str(t)
        
        dbsList=list(self.dbsList)
        dbsList.remove(dbsInst)
        dbsList=[dbsInst]+dbsList
        site="*"
        if kwargs.has_key("site"):
           site=kwargs["site"]
        blkList=self.helper.getBlocksFromSite(dbsInst, site=site,datasetPath=dataset)
#        siteDict=sortSitesByDomain(siteList[1:-1].replace(" ","").replace("'","").split(","))
        nameSpace={
                  'dbsInst' : dbsInst,
                  'dataset' : dataset,
                  'dbsList' : dbsList,
                  'dbsListOrig': self.dbsList,
                  'blkList' : blkList,
                  'siteList': siteList[1:-1].replace("'",""),
                  'style'   : "",
                  'userMode': userMode,
                  'adminUrl': self.adminUrl,
                  'apiversion': self.adminVer,
                  'email'   : self.getUserEmail(),
                  }
        t = templateAdminDatasets(searchList=[nameSpace]).respond()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    adminDataset.exposed=True

    def ms_deleteRequest(self,**kwargs):
        userMode=getArg(kwargs,'userMode','expert')
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
        rList  = [result]
        if type(result) is types.ListType:
            rList = result
        t      = templateAdminPage(searchList=[{'userMode':userMode,'rList':rList}]).respond()
        page  += str(t)
        page  += self.genBottomHTML()
        return page
    ms_deleteRequest.exposed=True

    def ms_getRequestByUser(self,userMode="user",**kwargs):
        fullpage = getArg(kwargs,'fullpage',1)
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
        if  fullpage:
            page   = self.genTopHTML(userMode=userMode)
        else:
            page   = ""
        rList  = [result]
        if type(result) is types.ListType:
            rList = result
        t      = templateAdminPage(searchList=[{'userMode':userMode,'rList':rList}]).respond()
        page  += str(t)
        if  fullpage:
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
        rList  = [result]
        if type(result) is types.ListType:
            rList = result
        t      = templateAdminPage(searchList=[{'userMode':userMode,'rList':rList}]).respond()
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
        rList  = [result]
        if type(result) is types.ListType:
            rList = result
        t      = templateAdminPage(searchList=[{'userMode':userMode,'rList':rList}]).respond()
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
            if  self.verbose:
                print "Fail in ms_addRequest"
                print kwargs
                traceback.print_exc()
            result = getExcMessage(getArg(kwargs,'userMode','user'))
            pass
        return result

    def adminRequest(self,**kwargs):
#        print "\n\n+++adminRequest",kwargs
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
#        if debug:
#           httplib.HTTPConnection.debuglevel = 1
#           httplib.HTTPSConnection.debuglevel = 1
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
           elem=ET.fromstring(data)
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
        dataset=kwargs['dataset']
        if kwargs.has_key('lfn'):
           if kwargs['lfn'].lower()=="all":
              lfnList=self.helper.getLFNsFromSite(dbsInst, site="all",datasetPath=dataset,run="*")
           elif kwargs['lfn'].lower().find("like")!=-1:
              lfn=kwargs['lfn'].split("like:")[1]
              lfnList=self.helper.getLFNsFromSite(dbsInst, site="all",datasetPath=dataset,run="*",lfn="%%%s%%"%lfn)
           else:
              lfnList=self.helper.getLFNsFromSite(dbsInst, site="all",datasetPath=dataset,run="*",lfn=lfn)
           del kwargs['lfn'] # delete lfn key-pair from our dictionary
           kwargs['lfn']=lfnList
        # lookup for block_name and find out a list
        if kwargs.has_key('block_name'):
           block_names=""
           if kwargs['block_name'].lower()=="all":
              block_names=self.helper.getBlocksFromSite(dbsInst, site="*",datasetPath=dataset)
              del kwargs['block_name'] # delete block_name key-pair from our dictionary
              kwargs['block_name']=block_names
        if kwargs.has_key('dataset'): del kwargs['dataset'] # I don't need it anymore
        page = self.genTopHTML(userMode=userMode)
        if  kwargs['api']=="addRequest":
#            result=self.adminRequest(**kwargs)
            result=self.ms_addRequest(**kwargs)
            rList  = [result]
            if type(result) is types.ListType:
                rList = result
            t      = templateAdminPage(searchList=[{'userMode':userMode,'rList':rList}]).respond()
            page  += str(t)
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
        bList= self.helper.getBlocksInfo(dbsInst, dataset) 
        minRun,maxRun=self.helper.getRunRangeForDataset(dbsInst, dataset)
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
        cherrypy.response.headers['Content-Type']='text/plain'
        page = ""
#        page = self.genTopHTML(userMode=userMode)
        # TODO: I need to validate user input, runRanges
        # it should be in a form minR-maxRun,minRun-maxRun,...
        lfnList=self.helper.getLFNsFromRunRanges(dbsInst, dataset,runRanges)
        page+=self.formatLFNList(lfnList,"cff",idx=0)
#        page+= self.genBottomHTML()
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
           print input
        xmlOutput=input.replace("<","&lt;").replace(">","&gt;").replace("\n","<br/>")
        page+="<p>The following DBS-Mart XML file has been created:</p>"
        page+="""<div class="yellow_box">%s</div>"""%xmlOutput
        page+="""<p>Right click on this <a type="xml" href="createADS?dataset=%s&amp;userMode=%s&amp;xml=True%s">XML</a> file in order to save it locally.</p>"""%(dataset,userMode,getParams(kwargs))
        page+= self.genBottomHTML()
        return page
    createADS_xml.exposed=True

    def findDatasetsFromLFN(self,dbsInst,lfn,userMode,**kwargs):
        pList = self.helper.findDatasetsFromLFN(dbsInst, lfn)
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
        query = "find dataset "
        cond  = ""
        if  site:
            if  site.lower() == 'any':
                site = "*"
            if  site == "*":
                cond += ""
            elif  site.find('*') != -1:
                cond += "and site like %s " % site
            else:
                cond += "and site = %s " % site
        if  group:
            if  group.lower() == 'any':
                group = "*"
            if  group == "*":
                cond += ""
            elif  group.find('*') != -1:
                cond += "and phygrp like %s " % group
            else:
                cond += "and phygrp = %s " % group
        if  app:
            if  app and app[0]=="/": # keep backward compatible options
                app=app.split("/")[1]
            if  app.lower() == 'any':
                app = "*"
            if  app == "*":
                cond += ""
            elif  app.find('*') != -1:
                cond += "and release like %s " % app
            else:
                cond += "and release = %s " % app
        if  primD:
            if  primD.lower() == 'any':
                primD = "*"
            if  primD == "*":
                cond += ""
            elif  primD.find('*') != -1:
                cond += "and primds like %s " % primD
            else:
                cond += "and primds = %s " % primD
        if  tier:
            if  tier.lower() == 'any':
                tier = "*"
            if  tier == "*":
                cond += ""
            elif  tier.find('*') != -1:
                cond += "and dataset.tier like %s " % tier
            else:
                cond += "and dataset.tier = %s " % tier
        if  proc:
            if  proc.find("/") != -1: # it is actually dataset
                key  = 'dataset'
            else:
                key  = 'procds'
            if  proc.lower() == 'any':
                proc = "*"
            if  proc == "*":
                cond += ""
            elif  proc.find('*') != -1:
                cond += "and %s like %s " % (key, proc)
            else:
                cond += "and %s = %s " % (key, proc)
        if  primType:
            if  primType.lower() == 'any':
                primType = "*"
            if  primType == "*":
                cond += ""
            elif  primType.find('*') != -1:
                cond += "and datatype like %s " % primType
            else:
                cond += "and datatype = %s " % primType
        if  date:
            if  date.lower() == 'any':
                date = "*"
            if  date.find('*') == -1:
                cond += "and dataset.createdate = %s " % date
        if  cond:
            query = "find dataset where " + cond[4:] # eliminate first "and "
        else:
            query = "find dataset where dataset like *"
        page=self.aSearch(dbsInst=dbsInst,userMode=userMode,_idx=_idx,pagerStep=pagerStep,userInput=query,sortName='dataset.createdate',sortOrder='desc')
#        page=self.aSearch(dbsInst=dbsInst,userMode=userMode,_idx=_idx,pagerStep=pagerStep,userInput=query)
        return page
    getDataHelper.exposed=True

    def blockListToHTML(self,dbsInst,bList,appPath="*"):
        if not len(bList): return ""
        nameSpace = {'host': self.dbsdd, 'dbsInst': dbsInst, 'blockList' : bList,'appPath':appPath}
        t = templateBlockList(searchList=[nameSpace]).respond()
        page=str(t)
        return page
        
    def crabCfg(self,dbsInst,dataset,totEvt,userMode='userMode',**kwargs):
        cherrypy.response.headers['Content-Type']='text/plain'
        page = ""
        nameSpace = {
                     'dbsInst'  : dbsInst,
                     'dbsUrl'   : self.dbsmgr.geturl(dbsInst),
                     'dataset'  : dataset,
                     'totEvt'   : totEvt
                    }
        t = templateCRAB(searchList=[nameSpace]).respond()
        page+=str(t)
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
        return self.getDataHelper(dbsInst,site,group,app,primD,tier,proc,primType,date,hist,_idx,ajax,userMode,pagerStep,phedex)
    getData.exposed = True 

    def getBlocksInfo(self,dbsInst,dataset,userMode):
        """
           Retreive block information for give dataset
        """
        t1=time.time()
        page=self.genTopHTML(userMode=userMode)
        try:
            page+= self.whereMsg('Navigator :: Results :: File block information',userMode)
            blkList = self.helper.getBlocksInfo(dbsInst, dataset)
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
            t=self.errorReport(dbsInst, "Fail in getBlocksInfo function")
            page+=str(t)
        page+=self.genBottomHTML()
        if self.verbose==2:
           print page
        return page
    getBlocksInfo.exposed = True 

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
            page+=self.whereMsg('Navigator :: Results :: Run information',userMode)

            # pager
            nResults=0
            _minRun=0 # full range for given dataset.
            _maxRun=0 
            try:    
               nResults,_minRun,_maxRun=self.helper.getRuns(dbsInst, dataset=dataset,minRun=minRun,maxRun=maxRun,count=1,userMode=userMode)
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
            runList,runDBInfoDict=self.helper.getRuns(dbsInst, dataset,minRun=minRun,maxRun=maxRun,fromRow=_idx*pagerStep,limit=pagerStep,count=0,userMode=userMode)
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
                         'admin'    : getArg(kwargs,'admin',0),
                         'showRange': 1,
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
            t=self.errorReport(dbsInst, "Fail in getRunsData function")
            page+=str(t)
        t2=time.time()
        if userMode!="user" and self.profile:
           page+=self.responseTime(t2-t1)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           print page
        return page
    getRuns.exposed = True 

    def getRunsFromRange(self,dbsInst,dataset,minRun,maxRun,userMode="user",_idx=0,ajax=0,pagerStep=RES_PER_PAGE,**kwargs): 
        """
           @type  dbsInst: string
           @param dbsInst: user selection of DBS menu
           @rtype : string
           @return: returns HTML code
        """
        if  not dataset:
            page  = self.genTopHTML(userMode=userMode)
            page += "<p>You must specify dataset</p>"
            page += self.genBottomHTML()
            return page
        pat = re.compile("/.*/.*/[A-Z].*")
        if  not pat.match(dataset):
            page  = self.genTopHTML(userMode=userMode)
            page += "<p>Wrong dataset pattern</p>"
            page += self.genBottomHTML()
            return page
        pat = re.compile("[1-9][0-9]+")
        if  not pat.match(minRun) or not pat.match(maxRun):
            page  = self.genTopHTML(userMode=userMode)
            page += "<p>Wrong run number pattern</p>"
            page += self.genBottomHTML()
            return page
        if  int(maxRun) - int(minRun) > 100:
            page  = self.genTopHTML(userMode=userMode)
            page += "<p>You requested more then 100 runs. Such query takes too much"
            page += " time in DBS to proceed. Please re-evaluate your request.</p>"
            page += "<p>minRun=%s, maxRun=%s</p>" % (minRun, maxRun)
            page += self.genBottomHTML()
            return page
        if  int(maxRun) < int(minRun):
            page  = self.genTopHTML(userMode=userMode)
            page += "<p>Wrong run range, max run number greater then min run number</p>"
            page += self.genBottomHTML()
            return page
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
            page+=self.whereMsg('Run search :: Results :: Run information',userMode)

            nResults=0
            try:    
               res=self.helper.getRuns(dbsInst,dataset,minRun=minRun,maxRun=maxRun,count=1,userMode=userMode)
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
                rPage+="""<a href="getRunsFromRange?dbsInst=%s&amp;dataset=%s&amp;minRun=%s&amp;maxRun=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s">&#171; Prev</a> """%(dbsInst,dataset,minRun,maxRun,_idx-1,userMode,pagerStep)
            tot=_idx
            for x in xrange(_idx,_idx+GLOBAL_STEP):
                if nResults>x*pagerStep:
                   tot+=1
            for index in xrange(_idx,tot):
                ref=index+1
                if index==_idx:
                   ref="""<span class="gray_box">%s</span>"""%(index+1)
                rPage+="""<a href="getRunsFromRange?dbsInst=%s&amp;dataset=%s&amp;minRun=%s&amp;maxRun=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s"> %s </a> """%(dbsInst,dataset,minRun,maxRun,index,userMode,pagerStep,ref)
            if  nResults>(_idx+1)*pagerStep:
                rPage+="""<a href="getRunsFromRange?dbsInst=%s&amp;dataset=%s&amp;minRun=%s&amp;maxRun=%s&amp;_idx=%s&amp;ajax=0&amp;userMode=%s&amp;pagerStep=%s">Next &#187;</a> """%(dbsInst,dataset,minRun,maxRun,_idx+1,userMode,pagerStep)

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
                         'onchange' : "javascript:LoadGetRunsFromRange('%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,dataset,minRun,maxRun,_idx,ajax,userMode,pagerId)
                        }
            t = templatePagerStep(searchList=[_nameSpace]).respond()
            pagerPage=str(t)

            nRun = self.helper.getNRuns(dbsInst, minRun,maxRun)
            runList,runDBInfoDict=self.helper.getRuns(dbsInst,dataset,minRun=minRun,maxRun=maxRun,fromRow=_idx*pagerStep,limit=pagerStep,count=0,userMode=userMode)
            if len(runList):
                page+=pagerPage
                nameSpace = {
                             'dbsInst'  : dbsInst,
                             'host'     : self.dbsdd,
                             'runList'  : runList,
                             'runDBInfo': runDBInfoDict,
                             'tableId'  : "runTable",
                             'proc'     : "",
                             'userMode' : userMode,
                             'admin'    : getArg(kwargs,'admin',0),
                             'showRange': 1,
                             'nRun'     : nRun,
                             'minRun'   : minRun,
                             'maxRun'   : maxRun,
                            }
                t = templateRunsInfo(searchList=[nameSpace]).respond()
                page+=str(t)
                pagerId+=1
                _nameSpace['pagerId']=pagerId
                _nameSpace['onchange']="javascript:LoadGetRunsFromRange('%s','%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,dataset,minRun,maxRun,_idx,ajax,userMode,pagerId)
                t = templatePagerStep(searchList=[_nameSpace]).respond()
                page+=str(t)
        except:
            t=self.errorReport(dbsInst, "Fail in getRunsFromRange function")
            page+=str(t)
        t2=time.time()
        if userMode!="user" and self.profile:
           page+=self.responseTime(t2-t1)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           print page
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
            lfnDict = self.helper.getLFNs_Runs(dbsInst, blockName)
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
               traceback.print_exc()
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
            lfnList = self.helper.getLFNs(dbsInst, blockName,dataset,run)
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
            t=self.errorReport(dbsInst, "Fail in getLFNlist function")
            pass
            return str(t)
    getLFNlist.exposed = True
 
    def formatLFNList(self,iList,what="txt",idx=-1):
        if not iList:
           return ""
        lfnList=[]
        if idx!=-1:
           for i in xrange(0,len(iList)):
               lfnList.append(iList[i][idx])
        else:
           lfnList=iList
        if what=="cff":
           t = templateFormatLfn_cff(searchList=[{'lfnList':lfnList,'pfnList':None}]).respond()
        elif what=="py" or what=="python":
           t = templateFormatLfn_py(searchList=[{'lfnList':lfnList,'pfnList':None}]).respond()
        else:
           t = templateFormatLfn_txt(searchList=[{'lfnList':lfnList,'pfnList':None}]).respond()
        return str(t)

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
        cherrypy.response.headers['Content-Type']='text/plain'
        try:
            page = ""
#            page = self.genTopHTML(userMode=userMode)
            t="dataset"
            v=dataset
            if blockName and blockName!="*":
               t="block"
               v=blockName
            lfnList = self.helper.getLFNs(dbsInst, blockName,dataset,run)
            page+=self.formatLFNList(lfnList,what,idx=0)
#            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in getLFN_txt function")
            pass
            return str(t)
    getLFN_txt.exposed = True

    def getLFNsWithParents(self,dbsInst,blockName,dataset="",userMode='user',run='*',**kwargs):
        cherrypy.response.headers['Content-Type'] = 'text/plain'
        method = getArg(kwargs,'method',self.iface)
        if  method == "dbsapi":
            return self.getLFNsWithParents_ql(dbsInst,blockName,dataset,userMode,run,**kwargs)
        else:
            return self.getLFNsWithParents_dd(dbsInst,blockName,dataset,userMode,run,**kwargs)
    getLFNsWithParents.exposed = True

    def getLFNsWithParents_ql(self,dbsInst,blockName,dataset="",userMode='user',run='*',**kwargs):
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
            format = getArg(kwargs,'format','cff')
            query1 = "find file"
            query2 = "find file.parent"
            cond   = ""
            if  blockName and blockName != "*":
                cond += "and block=%s " % blockName
            if  dataset and dataset != "*":
                cond += "and dataset=%s " % dataset
            if  run and run != "*":
                cond += "and run=%s " % run
            if  cond: # we remove from cond first and
                query1 += " where " + cond[4:]
                query2 += " where " + cond[4:]
            lfnList = [i[0] for i in self.aSearchResults(dbsInst,query1)]
            parentLFNList = [i[0] for i in self.aSearchResults(dbsInst,query2)]
            page = self.formatLFNPoolSource(lfnList,parentLFNList,format)
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in getLFNsWithParents function")
            pass
            return str(t)

    def formatLFNPoolSource(self,lfnList,parentLfnList,format="cff"):
        if format=="cff":
           t = templateFormatLfn_cff(searchList=[{'lfnList':lfnList,'pfnList':parentLfnList}]).respond()
        elif format=="py" or format=="python":
           t = templateFormatLfn_py(searchList=[{'lfnList':lfnList,'pfnList':parentLfnList}]).respond()
        else:
           t = templateFormatLfn_txt(searchList=[{'lfnList':lfnList,'pfnList':parentLfnList}]).respond()
        return str(t)

    def getLFNsForSite(self,dbsInst,site,datasetPath,what="cff",userMode='user',run="*"):
        """
           Generates a list of LFNs for given site
        """
        cherrypy.response.headers['Content-Type']='text/plain'
        try:
            page =""
            bList=[]
            lfnList=[]
            if site=="None": site="*"
            try:
                lfnList=self.helper.getLFNsFromSite(dbsInst,site,datasetPath,run)
            except:
                printExcept()
                page+="No LFNs found for site '%s'\n"%site
                pass
            if what=="py":
               page+=self.formatLFNPoolSource(lfnList,[],what)
            else:
               page+=self.formatLFNList(lfnList,what)
#            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in getLFNsForSite function")
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
            page = self.genTopHTML(userMode=userMode)
            nameSpace={'name':name,'content':self.helper.getConfigContent(dbsInst,dbsInst,id)}
            t = templateAppConfigContent(searchList=[nameSpace]).respond()
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in getConfigContent function")
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
        cherrypy.response.headers['Content-Type']='text/plain'
        try:
            page=""
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset)
            page+=self.formatLFNList(lfnList,what="cff",idx=0)
            return page
        except:
            t=self.errorReport(dbsInst, "Fail in getLFN_cfg function")
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
	page+=str(t)
        return page
        
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
        dbsList=self.helper.getBlockInfoForSite(dbsInst,site,limit,offset)
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
           print page
        return page
    getFileBlocks.exposed=True

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
            bList = self.helper.getBlocksFromSite(dbsInst,site)
            nameSpace = {
                         'host'   : self.dbsdd,
                         'dbsInst': dbsInst,
                         'site'   : site,
                         'bList'  : bList
                        }
            t = templateFileBlocksFromSite(searchList=[nameSpace]).respond()
        except:
            t=self.errorReport(dbsInst, "Fail in getBlocksFromSite function")
            pass
        page+= str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           print page
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
            bList = self.helper.getBlocksFromSite(dbsInst,site="any",datasetPath=datasetPath)
            t="""<select %s size="5" style="width:200px">"""%multiple
            for blk in bList:
                t+="""<option value="%s">%s</option>"""%blk
            t+="</select>"
        except:
            t=self.errorReport(dbsInst, "Fail in getBlocksFromDataset function")
            pass
        page+= str(t)
        page+="</response></ajax-response>"
        print page
        if self.verbose==2:
           print page
        return page
    getBlocksFromDataset.exposed=True

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
           print page
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
        dList = self.helper.getPrimaryDatasets(dbsInst)
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
           print page
        return page
    getPrimaryDatasets.exposed=True

    def getBranches(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get ROOT branches for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_branch">"""
        dList=['ROOT1','ROOT2','FIXME']
        nameSpace = {'name':'release','iList': dList}
        t = templateSelectList(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           print page
        return page
    getBranches.exposed=True

    def getGroups(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get groups for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="kw_group_holder">"""
        dList=['Any']+self.helper.getPhysicsGroups(dbsInst)
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':'group','iList': dList,'selTag':'kw_group','changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templateSetFromCookie(searchList=[{'name':'group','func':''}])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           print page
        return page
    getGroups.exposed=True

    def getRunRange(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get runs for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_runRange_holder">"""
        primD=primType="any"
        for key in kwargs:
            if key=='primType':
               primType=kwargs['primType']
            if key=='primD':
               primD=kwargs['primD']
        rMin,rMax=self.helper.getRunsForPrimary(dbsInst,primD,primType)
        style="width:200px"
        page+="""<input id="kw_minRun_holder" name="minRun" value="%s" size="20" />"""%rMin
        page+="--"
        page+="""<input id="kw_maxRun_holder" name="maxRun" value="%s" size="20" />"""%rMax
        page+="</response></ajax-response>"
        if self.verbose==2:
           print page
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
        siteDict=sortSitesByDomain(self.helper.getSites(dbsInst))
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':tag,'iList': siteDict,'selTag':tag,'changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templateSetFromCookie(searchList=[{'name':'site','func':''}])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           print page
        return page
    getSites.exposed=True

    def getTiers(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get tiers for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="kw_tier_holder">"""
        dList=['Any']+self.helper.getDataTiers(dbsInst)
        style="width:200px"
        if kwargs.has_key('style'): style=kwargs['style']
        nameSpace = {'name':'tier','iList': dList,'selTag':'kw_tier','changeFunction':'','style':style}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        t = templateSetFromCookie(searchList=[{'name':'tier','func':''}])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           print page
        return page
    getTiers.exposed=True

    def getTriggerLines(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get trigger lines for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="kw_prim_holder">"""
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

        dList = self.helper.getPrimaryDatasets(dbsInst,group,tier,rel,dsType)
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
           print page
        return page
    getTriggerLines.exposed=True

    def getSoftwareReleases(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get releases for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="kw_release_holder">"""
        relList=natsort24(list( self.helper.getSoftwareReleases(dbsInst) ))
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
           print page
        return page
    getSoftwareReleases.exposed=True

    def getPrimaryDSTypes(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get primary DS types for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="kw_primType_holder">"""
        dList = ['Any']+self.helper.getPrimaryDSTypes(dbsInst)
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
           print page
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
            parents  = self.helper.getDatasetProvenance(dbsInst,dataset)
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
           print page
        return page
    getDatasetProvenance.exposed=True

    def getDatasetChildren(self,dbsInst,dataset,userMode='user',**kwargs):
        """
           Get dataset children
        """
        page=self.genTopHTML(userMode=userMode)
        children = self.helper.getDatasetChildren(dbsInst,dataset)
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
           print page
        return page
    getDatasetChildren.exposed=True
        
    def siteForm(self,firstDBS="",firstSite="",userMode='expert',auto=0):
        """
           Generates site form request
        """
        if not firstDBS: firstDBS=self.dbsglobal
        if firstSite=="*": firstSite="All"
        if auto:
           # auto-competion form for processed datasets
           nameSearch={'tag':'proc','inputId':'proc','inputName':'proc','size':'80','userMode':userMode,'dbsInst':self.dbsglobal,'key':'dataset','wherekey':'dataset','label':'','zIndex':9000,'method':'findInDBS'}
           t = templateAutoComplete(searchList=[nameSearch]).respond()
           prdForm=str(t)
        else:
           prdForm="""<input type="text" name="proc" id="proc" size="80">"""

        dbsInst = firstDBS
        siteList=self.helper.getSites(dbsInst)
        siteDict=sortSitesByDomain(siteList)
        nameSpace = {
                     'firstDBS' : firstDBS,
                     'firstSite': firstSite,
                     'dbsList'  : self.dbsList,
                     'dbsGlobal': self.dbsglobal,
                     'siteDict' : siteDict,
                     'userMode' : userMode,
                     'prdForm'  : prdForm,
                     'autocomplete': auto,
                    }
        t = templateSiteForm(searchList=[nameSpace]).respond()
        page = str(t)
        return page

    @is_authenticated (onFail=RedirectToLocalPage ("/redirectPage"))
    def sendFeedback(self,userEmail,feedbackText,userMode='user'):
        """
           Generates feedback form.
        """
        p = os.popen("%s -t" % SENDMAIL, "w")
        p.write("To: cms-dbs-support@cern.ch\n")
        p.write("Subject: response from DBS data discovery\n")
        p.write("\n") # blank line separating headers from body
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
           print page
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
           print page
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
#           print page
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
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="appConfigs">"""
        else:
           page=self.genTopHTML(userMode=userMode)
           page+=self.whereMsg('Navigator :: Results :: Configuration file(s)',userMode)
        for item in self.helper.listApplicationConfigsContent(dbsInst,appPath,procPath):
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
           print page
        return page
    getAppConfigs.exposed=True

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
           print page
        return page
    checkUser.exposed=True

    def getDataDescription(self,dbsInst,processedDataset="",userMode='user',**kwargs):
        """ 
            Get data description.
        """
        page=self.genTopHTML(userMode=userMode)
        page+=self.whereMsg('Navigator :: Results :: Data description',userMode)
        description=""
        dList=self.helper.getDataDescription(dbsInst,processedDataset)
        # get formatted output of dataset details
        nameSpace={'dList' : dList, 'dataset':processedDataset,'userMode':userMode }
        t = templateDatasetDetails(searchList=[nameSpace]).respond()
        description+=str(t)
        page+=description

        page+=self.genBottomHTML()
        if self.verbose==2:
           print page
        return page
    getDataDescription.exposed=True

    def getTableTemplate(self,func,dbsInst,lfn,msg,ajax,userMode='user',**kwargs):
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
           print page
        return page

    def getLFN_Branches(self,dbsInst,lfn,ajax=0,userMode='user',**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Branches,dbsInst,lfn,'ROOT branches',ajax,userMode)
        return page
    getLFN_Branches.exposed=True

    def getLFN_Lumis(self,dbsInst,lfn,ajax=0,userMode='user',**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Lumis,dbsInst,lfn,'LFN lumis',ajax,userMode)
        return page
    getLFN_Lumis.exposed=True

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
           print page
        return page
    getFloatBox.exposed=True

    def getRss(self,ajax=1,userMode="user"):
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="element" id="rss_list">"""
        else:
           page=""
        dbsList=[]
        if userMode=="user":
           dbsList.append(self.dbsglobal)
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
           print page
        return page
    getRss.exposed=True

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
           print page
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
           print page
        return page
    makeSelect.exposed=True

    # this method can be used for auto-completion forms, it returns a string of columns
    # see YUI, http://developer.yahoo.com/yui/autocomplete/
    def findInDBS(self,dbsInst,key,wherekey,**kwargs):
        page=""
        if kwargs.has_key('autocomplete'):
           if kwargs['autocomplete']=="off":
              return page
        cond = kwargs['query']
        if  cond.find('*') != -1:
            cond = cond.replace('*','%')
        if  cond.find('%') == -1:
            cond = '%' + cond + '%'
        query = "find %s where %s like %s" % (key, wherekey, cond)
        row=0
        limit=10
        res = self.helper.queryDBS(dbsInst, query, row, limit)
        natList = natsort24(list(res))
        for item in natList:
            page+="%s\n"%item
        return page
    findInDBS.exposed=True

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
            printExcept()
            pass
        page=""
        for item in cList:
            page+="%s\n"%item[0]
        return page
    getAliasesFromHistoryDB.exposed=True

    def addTreeElement(self,parent,node,**kwargs):
        cherrypy.response.headers['Content-Type']='text/xml'
        page="""<ajax-response><response type="object" id="treeViewInfo">
%s</response></ajax-response>"""%self.genTreeElement(parent,node)
        if self.verbose==2:
           print page
        return page
    addTreeElement.exposed=True

    def phedexStatus(self,site,datasetPath,id_suffix,**kwargs):
        self.setContentType('xml')
        url="/phedex/prod/XML::TransferStatus"
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
            if  self.verbose>1:
                print "\n\n### response phedex response"
                print "site=",site
                print "dataset=",datasetPath
                print "id_suffix=",id_suffix
                print "params=",params
                print "urllib params=",urllib.urlencode(params,doseq=True)
                print "page=",page
            if type(page) is types.StringType:
               page = string.replace(page,"""<?xml version='1.0' encoding='ISO-8859-1'?>""","")
        except:
            t=self.errorReport('', "Fail in phedexStatus function")
            page+="Phedex information is not available at this time"
            pass
        if self.verbose==2:
           print page
        return page
    phedexStatus.exposed=True 

    def getRunDBInfo(self,runs,**kwargs):
        self.setContentType('xml')
        runInfoList=[]
        try:
            runInfoList = self.helper.getRunDBInfo(dbsInst,runs)
        except:
#            t=self.errorReport("Fail in getRunDBInfo function")
            pass
        page="""<ajax-response>\n"""
        for run,runInfoDict in runInfoList:
            if runInfoDict:
               page+="""<response type='object' id="runSummary_hlt_%s">\n"""%run
               page+="""<div style="font-weight:bold">RunInfo:</div>\n"""
               runInfoKeys=runInfoDict.keys()
               runInfoKeys.sort()
               for key in runInfoKeys:
                   if key.lower()=="runnumber": continue
                   if key.lower()=="tsckey":
                      val = runInfoDict[key]
                      new_val = ""
                      # insert br for every 30th character to fit on a page
                      i=0
                      for i in xrange(0,len(val)/30):
                          new_val+=val[i*30:(i+1)*30]+"<br/>"
                      if len(val)>(i+1)*30:
                         new_val+=val[(i+1)*30:]
                      runInfoDict[key]=new_val
                   page+="<span>&#187; %s: %s</span><br/>\n"%(key,runInfoDict[key])
               page+="</response>\n"
        page+="</ajax-response>"
        if self.verbose==2:
           print page
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
            # TODO: turn on int lumi, see code lines below
#            dbsApi = self.dbsmgr.getapi(dbsInst)
#            int_lumi= dbsApi.getIntegratedLuminosity(dataset)
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
            print page
        return page
    getIntegratedLumi.exposed=True

    def getDQInfo(self,dbsInst,dataset,run,dqid,**kwargs):
        self.setContentType('xml')
        ajax  = getArg(kwargs,'ajax',1)
        admin = int(getArg(kwargs,'admin',0))
#        dqid  = "%s_%s" % (run,dataset.replace("/","___").replace("-","_"))
        if ajax:
            page="""<ajax-response>"""
            page+="""<response type='object' id="%s">\n""" % dqid
#            page+="""<response type='object' id="dq_run_%s">\n"""%run
#            page+="""<response type='element' id="dq_run_%s">\n"""%run
        else:
            page=""
        try:
            xmlinput="""<?xml version='1.0' standalone='yes'?><dbs><run run_number='%s' lumi_section_number='' /></dbs>"""%run

            params={'apiversion':self.ddConfig.dbsVer(),
                    'api':'listRunLumiDQ',
                    'dataset':dataset,
                    'xmlinput':xmlinput}
            dbsUrl = self.dbsmgr.geturl(dbsInst)
            dbsUrl=dbsUrl.replace('https','http').replace('_writer','').replace(':8443','')
            f = urllib.urlopen("%s?%s"%(dbsUrl,urllib.urlencode(params)))
            data=f.read()
            sysDict,subDict=getDQInfo(data)
            nameSpace={'tag':"dq_%s"%dqid,'sysDict':sysDict,'subDict':subDict,'admin':0}
            try:
                token = SecurityToken()
            except:
                token = None
                pass
            if admin and self.securityApi.hasGroupResponsibility(token.dn,"DataQuality","Global Admin"):
                nameSpace['admin']=1
                t = templateDQInfo(searchList=[nameSpace]).respond()
                page+="ADMIN DQ:<br/>"+str(t)
            else:
                t = templateDQInfo(searchList=[nameSpace]).respond()
                page+=str(t)
#            @is_authorized (Role("Global Admin"), Group("DataQuality"), onFail=showTempate)
#            t = templateDQInfo(searchList=[nameSpace]).respond()
#            page+=str(t)
        except:
            page+="N/A"
            traceback.print_exc()
            pass
        if  ajax:
            page+="</response>"
            page+="</ajax-response>"
        if  self.verbose==2:
            print page
#        print page
        return page
    getDQInfo.exposed=True

    # helper functions to decorate output
    def aSearchAll(self,dbsInst,keyword,func=None):
        what=keyword
        if what=="primds.type": keyword="primds"
        if func: what="%s(%s)"%(func,keyword)
        input="find %s where %s like *"%(what,keyword)
        res=self.aSearch(dbsInst,userMode='user',_idx=0,pagerStep=-1,userInput=input,html=0,caseSensitive='on',details=0)
        results=res.splitlines()
        return results

    def aSearchResults(self,dbsInst,userInput,fromRow=-1,limit=-1,method="dbsapi",**kwargs):
        result, titleList = self.exeQuery(dbsInst, userInput, fromRow, limit)
        return result 

    def aSearchShowAll(self,**kwargs):
        fromRow  = int(getArg(kwargs,'fromRow',-1))
        limit    = int(getArg(kwargs,'limit',-1))
        # if user pass limit/fromRow as -1 we should not apply the limit
        if limit==-1 or fromRow==-1:
           limit = 0
           fromRow=0
        userInput= kwargs['userInput']
        dbsInst  = getArg(kwargs,'dbsInst',self.dbsglobal)
        html     = getArg(kwargs,'html',1)
        xml      = getArg(kwargs,'xml',0)
        case     = getArg(kwargs,'caseSensitive','on')
        userMode = getArg(kwargs,'userMode','user')
        output   = getArg(kwargs,'output','')
        parents  = getArg(kwargs,'parents','')
        cff      = int(getArg(kwargs,'cff',0))
        method   = getArg(kwargs,'method',self.iface)
        page     = ""
        if  xml:
            return self.dbsmgr.exexml(dbsInst, userInput)
        else:
            result, titleList = self.exeQuery(dbsInst, userInput, fromRow, limit)
        self.setContentType('plain') 
        cDateIdx =-1
        mDateIdx =-1
        cByIdx   =-1
        sizeIdx  =-1
        func     = lambda x: [i.lower() for i in x]
        ttList   = func(titleList)
        for idx in xrange(0,len(ttList)):
            item = ttList[idx]
            if item.find('date')!=-1:
               if item.find('createdate')!=-1: cDateIdx=idx 
               if item.find('mod')!=-1: mDateIdx=idx 
            if item.find('size')!=-1: sizeIdx=idx
            if item.find('createdby')!=-1: cByIdx=idx
        if  cff and output=="file":
            page+="replace PoolSource.fileNames = {\n"
        for item in result:
            if output.find(",")==-1:
               res=item[0]
            else:
               res = str(item).strip().replace("'","").replace("(","").replace(")","").replace("[","").replace("]","")
            if  cff and output=="file":
                page+="'%s',\n"%res
            else:
                page+="%s\n"%res
        if cff and output=="file":
           idx=page.rfind(",")
           page=page[:idx]+"\n}\n"
        return page
    aSearchShowAll.exposed=True

    def aSearchSummary(self,**kwargs):
        fromRow  = kwargs['fromRow']
        limit    = kwargs['limit']
        # if user pass limit/fromRow as -1 we should not apply the limit
        if limit==-1 or fromRow==-1:
           limit = 0
           fromRow=0
        dbsInst  = kwargs['dbsInst']
        html     = kwargs['html']
        xml      = kwargs['xml']
        userMode = kwargs['userMode']
        case     = kwargs['caseSensitive']
        grid     = int(getArg(kwargs,'grid',0))
        userInput= kwargs['userInput']
        getRes   = int(getArg(kwargs,'getRes',0))
        userInput= kwargs['userInput']
        output   = findOutput(userInput)
        if  xml:
            return self.dbsmgr.exexml(dbsInst, userInput, fromRow, limit)
        else:
            sortKey = getArg(kwargs,'sortName','')
            sortOrder = getArg(kwargs,'sortOrder','')
            result, tList = self.summaryQuery(dbsInst, userInput, fromRow, limit, sortKey, sortOrder)
            titleList = [x.split('.')[-1] for x in tList]+['LINKS']
            excludeList = ['LINKS']
        if getRes:
           return result

        page     = ""
        num      = kwargs['num']
        link     = kwargs['link']
        counter  = 0
        cDateIdx =-1
        mDateIdx =-1
        cByIdx   =-1
        sizeIdx  =-1
        pathIdx  =-1
        func     = lambda x: [i.lower() for i in x]
        ttList   = func(titleList)
        for idx in xrange(0,len(ttList)):
            item = ttList[idx].lower()
            if item.find('date')!=-1:
               if item.find('creat') != -1: cDateIdx=idx 
               if item.find('mod')!=-1: mDateIdx=idx 
            if item.find('size')!=-1: sizeIdx=idx
            if item.find('createdby')!=-1: cByIdx=idx
            if item.find('path')!=-1: pathIdx=idx
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
                page+="  <row>\n"
            for jdx in xrange(0,len(item)):
                elem = item[jdx]
                if elem==item[0]:
                   firstElem=elem
                if (cDateIdx!=-1 and jdx==cDateIdx) or (mDateIdx!=-1 and jdx==mDateIdx):
                   try:
                       if xml:
                          elem=timeGMT(long(elem))
                       else:
                          elem=timeGMTshort(long(elem))
                   except:
                      pass
                elif  sizeIdx!=-1 and jdx==sizeIdx:
                   elem=colorSizeHTMLFormat(elem)
                if cByIdx!=-1 and jdx==cByIdx:
                   elem=parseCreatedBy(elem)
                if pathIdx!=-1 and jdx==pathIdx:
                   path=elem[0]+elem[1:].replace("/","<br/>/")
                   elem="""<a href="getData?dbsInst=%s&amp;proc=%s&amp;userMode=%s&amp;ajax=0&amp;phedex=off&amp;group=Any&amp;site=Any&amp;tier=Any&amp;app=Any&amp;primType=Any&amp;primD=Any">%s</a>"""%(dbsInst,elem,userMode,path)
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
                       tag=titleList[jdx].lower().replace(" ","").replace("(","_").replace(")","")
                       page+="    <%s>%s</%s>\n"%(tag,elem,tag)
                    else:
                       page+="%s %s \n"%(titleList[jdx],elem)
            if  html and grid and \
                output.find(",")==-1 and output.find("total")==-1: # no multiple select
                # add more links column
                more ="""<select style="width:100px" onchange="javascript:load(this.options[this.selectedIndex].value)">\n"""
                more+="""<option value="">More Info</option>\n"""
                qldict = self.dbsmgr.keys_attrs(dbsInst)
                if  qldict.has_key(output):
                    for attr in qldict[output]:
                        ref  = urllib.quote("find %s.%s where %s=%s"%(output,attr,output,firstElem))
                        aref = """%s/aSearch?userInput=%s&amp;userMode=%s&amp;dbsInst=%s&amp;caseSensitive=%s&amp;grid=%s"""%(self.dbsdd,ref,userMode,dbsInst,case,grid)
                        more+="""<option value='%s'>%s</option>\n"""%(aref,"Find %s.%s"%(output,attr))
                for key in qldict.keys():
                    if key==output: continue
                    ref  = urllib.quote("find %s where %s=%s"%(key,output,firstElem))
                    aref = """%s/aSearch?userInput=%s&amp;userMode=%s&amp;dbsInst=%s&amp;caseSensitive=%s&amp;grid=%s"""%(self.dbsdd,ref,userMode,dbsInst,case,grid)
                    more+="""<option value='%s'>%s</option>\n"""%(aref,"Find %s"%key)
                more+="</select>\n"
                page+="<td>%s</td>\n"%more # for LINKS, see adding to titleList
                page+="</tr>\n"
            if  not html:
                page+="\n"
            counter+=1
        if  grid and html and result:
            tab="""<table width="100%%" class="dbs_table">\n<tr class="tr_th">"""
            for t in titleList:
                th_class=""
                if  t==titleList[0]: 
                    th_class="th_left"
                if t.find('createdate') != -1:
                    t+="""<br/><div class="tiny">(dd/mm/yy)</div>"""
                tab+="<th class=\"%s\">%s</th>"%(th_class,t)
            tab+="</tr>"
            page=tab+page+"</table>\n"
        if  html and result:
            t = templateSortBar(searchList=[{'num':num,'oname':'results','link':link,'titleList':titleList,'excludeList':excludeList,'iface':'dbsapi'}]).respond()
            page = str(t)+page
        if  not result:
            page+="No results"
        return page

    def blockSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def fileSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def releaseSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def runSummary(self,**kwargs):
        page = ""
        html = int(kwargs['html'])
        if not html: return self.aSearchSummary(**kwargs)
        try:
            dbsInst = kwargs['dbsInst']
            userMode= kwargs['userMode']
            nRun    = kwargs['num']
            kwargs['getRes']=1 # get results rather then format the page with them
            results = self.aSearchSummary(**kwargs)
            page    = ""
            runList = []
            seList  = []
            minRun  = 10000000000000
            maxRun  = 0
            for item in results:
                run,cDate,cBy,mDate,mBy,totLumi,store,sRun,eRun,nEvts,sEvent,eEvent,nLumis=item
                if run<minRun: minRun=run
                if run>maxRun: maxRun=run
                pDict = self.helper.getPathSEs(dbsInst,run)
                for dsType,path in pDict.keys():
                    query = 'find sum(file.size), count(file) where dataset = %s and run = %s' % (path, run)
                    res, tList = self.exeQuery(dbsInst, query)
                    fSize, nFiles = res[0]

                    seList=pDict[(dsType,path)]
                    runList.append([run,nEvts,nLumis,totLumi,store,sRun,eRun,parseCreatedBy(cBy),timeGMT(cDate),parseCreatedBy(mBy),timeGMT(mDate),dsType,path,seList,fSize,nFiles])
            nameSpace = {
                         'dbsInst'  : dbsInst,
                         'host'     : self.dbsdd,
                         'runList'  : runList,
                         'runDBInfo': {},
                         'tableId'  : "runTable",
                         'nRun'     : nRun,
                         'minRun'   : minRun,
                         'maxRun'   : maxRun,
                         'proc'     : "",
                         'showRange': 0,
                         'userMode' : userMode,
                         'admin'    : getArg(kwargs,'admin',0),
                        }
            t = templateRunsInfo(searchList=[nameSpace]).respond()
            page+=str(t)
        except:
            if self.verbose:
               printExcept()
            kwargs['getRes']=0 # get page rather then results
            page=self.aSearchSummary(**kwargs)
            pass
        return page
    def lumiSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def siteSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def primdsSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def procdsSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def tierSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def adsSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def adsdefSummary(self,**kwargs):
        return self.aSearchSummary(**kwargs)
    def datasetSummary(self,**kwargs):
        fromRow  = kwargs['fromRow']
        limit    = kwargs['limit']
        # if user pass limit/fromRow as -1 we should not apply the limit
        if limit==-1 or fromRow==-1:
           limit = 0
           fromRow=0
        dbsInst  = kwargs['dbsInst']
        html     = kwargs['html']
        xml      = kwargs['xml']
        userMode = kwargs['userMode']
        grid     = int(getArg(kwargs,'grid',0))
        num      = kwargs['num']
        link     = kwargs['link']
        userInput= kwargs['userInput']
        sortName = getArg(kwargs, 'sortName', '')
        sortOrder= getArg(kwargs, 'sortOrder', '')
#        print "\n\n#### datasetSummary", sortName, sortOrder
        result, titleList = self.summaryQuery(dbsInst, userInput, fromRow, limit, sortName, sortOrder)

        if  not result: # no datasets found
            return ""

        excludeList=[]
        eList=['CRAB','&#8747;<em>L</em>','LINKS']
        page     = ""
        counter  = 0
        cDate = cBy = size = nblks = nfiles = nevts = nsites =""
        run = appPath = site = "*"
        phedex   =0
#        dbsInstURL = self.dbsmgr.geturl(dbsInst)
        dbsInstURL = self.auth.dbsInfo(dbsInst)[1] # use DDAuthentication and read secure DBS URL
        if  not dbsInstURL:
            dbsInstURL = self.dbsmgr.geturl(dbsInst)
            
        siteDict = {}
        useView = 0
        datasetlist = [item[0] for item in result]
        dataset2sitedict = self.helper.datasetSummary4Sites(dbsInst, datasetlist)

        excludeList=list(eList)
        if  len(result[0]) == 1: # no DB summary view found
            result = self.helper.datasetSummary_json2(dbsInst, datasetlist)
            titleList=['DATASET','CREATED','CREATOR','SIZE','BLOCKS','FILES','EVENTS','SITES']
            excludeList=list(titleList[1:])+eList
        else:
            name = 'DATASETSUMMARY.'
            titleList = [i.upper().replace(name, '') for i in list(titleList)]
        for item in result:
            dataset,cDate,cBy,size,nblks,nfiles,nevts,nsites=item
            sites = []
            siteDict = {}
            if  dataset2sitedict.has_key(dataset):
                for row in dataset2sitedict[dataset]:
                    sites.append(row[0])
                    siteDict[row[0]]=row[1:]
            else:
                sites = []
                siteDict = {}
            mDict = {'created':timeGMT(cDate),
                     'creator':parseCreatedBy(cBy),
                     'blocks':nblks, 'size':size, 'files': nfiles, 
                     'evts': nevts, 'sites': sites, 'dataset':dataset}
            uid = genkey(dataset)

#        for item in result:
#            try:
#                dataset,cDate,cBy,size,nblks,nfiles,nevts,nsites=item
#                excludeList=list(eList)
#                userView = 1
#            except: # no view found
#                dataset=item[0]
#                titleList=['DATASET','CREATED','CREATOR','SIZE','BLOCKS','FILES','EVENTS','SITES']
#                excludeList = list(titleList[1:])+eList
#                pass
            if  counter%2:
                style='class="zebra"'
            else:
                style=""
#            if  useView:
#                query = 'find site where dataset = %s' % dataset
#                sites = self.helper.getSiteList(dataset)
#                mDict = {'created':timeGMT(cDate),
#                         'creator':parseCreatedBy(cBy),
#                         'blocks':nblks, 'size':size, 'files': nfiles, 
#                         'evts': nevts, 'sites': sites, 'dataset':dataset}
#            else:
#                mDict = self.helper.datasetSummary_json(dbsInst, dataset)
#                siteDict = {}
            if  mDict:
                tempDict={'dbsInst':dbsInst,'path':dataset,'uid':uid,'appPath':appPath,'siteDict':siteDict,'mDict':mDict,'host':self.dbsdd,'userMode':userMode,'phedex':phedex,'run':run,'dbsInstURL':urllib.quote(dbsInstURL),'PhedexURL':self.PhedexURL,'style':style,'cmsNames':self.getCMSNames()}
                if  grid:
                    t = templateProcessedDatasetsGrid(searchList=[tempDict]).respond()
                else:
#                    siteDict = {}
#                    if  dataset2sitedict.has_key(dataset):
#                        for row in dataset2sitedict[dataset]:
#                            siteDict[row[0]]=row[1:]
#                    tempDict['siteDict'] = siteDict
                    t = templateProcessedDatasetsLite(searchList=[tempDict]).respond()
                page+=str(t)
            else:
                page+="""<hr class="dbs" /><br/><b>%s</b><br /><span class="box_red">No data found</span>"""%dataset
            counter+=1
        if  grid:
            head=""
            titleList+=eList
            for item in titleList:
                item = item.replace('NUMBEROF','').replace('TOTAL','')
                th_class=""
                if  item==titleList[0]: th_class="th_left"
                if  item.find('createdate') != -1:
                    item+="""<br/><div class="tiny">(dd/mm/yy)</div>"""
                head+="<th class=\"%s\">%s</th>"%(th_class,item)
            head = """<table width="100%%" class="dbs_table">\n<tr class="tr_th">%s</tr>"""%head
            page=head+page+"</table>"
        if  titleList:
            t = templateSortBar(searchList=[{'num':num,'oname':'results','link':link,'titleList':titleList,'excludeList':excludeList,'iface':'dbsapi'}]).respond()
            page = str(t)+page
        return page

    def aSearchKeys(self):
        try:
            dbsApi = self.dbsmgr.getapi(self.dbsglobal)
            helpList = dbsApi.getHelp("")
        except:
            helpList = []
            pass
        t = templateQLHelp(searchList=[{'helpList':helpList,'showExample':1,'msg':""}]).respond()
        return str(t)

    def update_kwargs(self,kDict,**kwargs):
        oDict=dict(kDict)
        for key in kwargs.keys():
            oDict[key]=kwargs[key]
        return oDict

    def aSearch(self,dbsInst='',userMode='user',_idx=0,pagerStep=RES_PER_PAGE,**kwargs):
        if  not dbsInst:
            dbsInst = self.dbsglobal
        if  dbsInst not in self.dbsList:
            page  = self.genTopHTML(userMode=userMode)
            page += "Unknown DBS instance '%s'" % dbsInst
            page += self.genBottomHTML()
            return page
        t0=time.time()
        _idx=int(_idx)
        pagerStep = int(pagerStep)
        html      = getArg(kwargs,'html',1)
        if  not int(html):
            self.setContentType('plain') 
        xml       = getArg(kwargs,'xml',0)
        case      = getArg(kwargs,'caseSensitive','on')
        details   = getArg(kwargs,'details',1)
        cff       = getArg(kwargs,'cff',0)
        try:
            userInput = kwargs['userInput']
        except:
            traceback.print_exc()
            raise "aSearch require input query"
        userInput = urllib.unquote(userInput)
        if  len(userInput.split())==1 and userInput.find("=")==-1 and \
            userInput.find(">")==-1 and userInput.find("<")==-1:
            if  not self.pathMatch.match(userInput) and \
                    userInput.find("*") == -1:
                userInput = "*%s*" % userInput
            if  userInput.find("*") != -1:
                userInput = "find dataset where dataset like %s" % userInput
            else:
                userInput = "find dataset where dataset = %s" % userInput

        # on request of Si, #108501, add valid status for dataset
        if  userInput.find('find dataset where') != -1:
            if  userInput.find('dataset.status') == -1:
                userInput += ' and dataset.status like VALID*'
        if  userInput.strip() == 'find dataset':
            userInput += ' where dataset.status like VALID*'

        sortName = getArg(kwargs, 'sortName', '')
        sortOrder= getArg(kwargs, 'sortOrder', '')
#        if  sortName and sortOrder:
#            if  userInput.find(' order by ') == -1:
#                userInput += " order by %s %s" % (sortName.lower(), sortOrder.lower())
#            else:
#                userInput = userInput.split(' order by ')[0] + \
#                        " order by %s %s" % (sortName.lower(), sortOrder.lower())
        kwargs['userInput'] = userInput

        fromRow  =_idx*pagerStep
        limit    = pagerStep
        if  int(xml):
            self.setContentType('xml') 
            return self.dbsmgr.exexml(dbsInst,userInput,fromRow,fromRow+limit)
        try:
            nResults = self.countQuery(dbsInst, userInput)
        except:
            if getArg(kwargs,'results',0):
               return "N/A"
            if not html:
               return traceback.format_exc()
            msg  = "<pre>%s</pre>"%getExcMessage(userMode)
            page = self._advanced(dbsInst=dbsInst,userMode=userMode,msg=msg)
            return page

        # check if asked to return results only
        if getArg(kwargs,'results',0):
           return nResults

        # construct output kwargs
        kDict=self.update_kwargs(kwargs,
                dbsInst=dbsInst, fromRow=fromRow, limit=limit,
                html=html, xml=xml, userMode=userMode, userInput=userInput,
                caseSensitive=case, case=case, cff=cff)

        if  html:
            page = self.genTopHTML(userMode=userMode)
            dbsList = []
            for dbs in self.dbsList:
                if  userMode=="user":
                    if  dbs==self.dbsglobal or dbs.find("ph_analys")!=-1 or dbs.find('_caf_')!=-1:
                        dbsList.append(dbs)
                else:
                    dbsList.append(dbs)
            try:
                dbsList.remove(dbsInst)
            except:
                pass
            dbsList=[dbsInst]+dbsList
            nameSearch={'dbsInst':dbsInst,'userHelp':0,'dbsList':dbsList,'host':self.dbsdd,'style':'','userMode':userMode,'userInput':userInput,'aSearchKeys':self.aSearchKeys(),'showHelp':0}
            t = templateAdvancedSearchForm(searchList=[nameSearch]).respond()
            page+=str(t)
            # Construct result page
            rPage=""
            if  nResults:
                rPage+="Result page:"

            moreParams=""
            if  kwargs:
                for k in kwargs.keys():
                    moreParams+="&amp;%s=%s"%(k,urllib.quote(kwargs[k]))
            # the progress bar for all results
            if  _idx:
                rPage+="""<a href="aSearch?dbsInst=%s&amp;userMode=%s&amp;_idx=%s&amp;pagerStep=%s%s">&#171; Prev</a> """%(dbsInst,userMode,_idx-1,pagerStep,moreParams)
            tot=_idx
            for x in xrange(_idx,_idx+GLOBAL_STEP):
                if  nResults>x*pagerStep:
                    tot+=1
            for index in xrange(_idx,tot):
                ref=index+1
                if  index==_idx:
                    ref="""<span class="gray_box">%s</span>"""%(index+1)
                rPage+="""<a href="aSearch?dbsInst=%s&amp;userMode=%s&amp;_idx=%s&amp;pagerStep=%s%s"> %s </a> """%(dbsInst,userMode,index,pagerStep,moreParams,ref)
            if  nResults>(_idx+1)*pagerStep:
                rPage+="""<a href="aSearch?dbsInst=%s&amp;userMode=%s&amp;_idx=%s&amp;pagerStep=%s%s">Next &#187;</a>"""%(dbsInst,userMode,_idx+1,pagerStep,moreParams)

            if  _idx and _idx*pagerStep>nResults:
                return "No data found for this request"
            page+=self.whereMsg('Adv. search :: Results',userMode)
        else:
            if pagerStep==-1:
               page ="\nFound %s results\n" % nResults
            else:
               page ="\nFound %s results, showing results from %s-%s, to see all results use --limit=-1\n"%(nResults,_idx*pagerStep,_idx*pagerStep+pagerStep)

        # create a link for show all.
        link=""
        for key in kDict:
            if key=='query': continue
            val=kDict[key]
            if key=='limit': val=-1
            if key=='_idx' : val=0
            if link: link+="&amp;"
            if key=='userInput': val=urllib.quote(val)
            link+="%s=%s"%(key,val)
        link="aSearchShowAll?"+link
        kDict['num']=nResults
        kDict['link']=link

        queryTime=0
        try:
            t1=time.time()
            if  userMode=='dbsExpert':
                sql = self.getQuery(dbsInst, userInput, fromRow, limit)
                page+="<!--- QUERY --->\n<pre>%s</pre>" % sql
            if  details:
                try: 
                    output = findOutput(userInput)
                    if  output.find(',') != -1: # composed keys
                        page+=self.aSearchSummary(**kDict)
                    else:
                        method=getattr(self,output+'Summary')
                        page+=method(**kDict)
                except:
                    traceback.print_exc()
                    pass
                    page+=self.aSearchSummary(**kDict)
            else:
                page+=self.aSearchShowAll(**kDict)
            queryTime=time.time()-t1
        except:    
            if  html:
#                page+="<verbatim>"+getExcept()+"</verbatim>"
                page += getExceptionInHTML()
            else:
                page+=getExcept()
        if  html:
            pagerId=1
            _nameSpace = {
                          'idx'      : _idx,
                          'host'     : self.dbsdd,
                          'style'    : "",
                          'rPage'    : rPage,
                          'pagerStep': pagerStep,
                          'pagerId'  : pagerId,
                          'nameForPager': "results",
                          'onchange' : "javascript:LoadASearch('%s','%s','%s','%s','%s','%s')"%(dbsInst,userMode,fromRow,_idx,pagerId,userInput)
                       }
            t = templatePagerStep(searchList=[_nameSpace]).respond()
            page+="""<hr class="dbs" />"""
            page+=str(t)
            page+=self.genBottomHTML()
	if self.verbose:
           print "aSearch time:",(time.time()-t0)
        return page
    aSearch.exposed=True

    def summaryQuery(self, dbsInst, userInput, fromRow, limit, sortKey="", sortOrder=""):
        if  userInput.find(',') != -1: # compound keys, not a summary view
            return self.dbsmgr.exe(dbsInst, userInput, fromRow, fromRow+limit)
        try:
            result = self._summaryQuery(dbsInst, userInput, fromRow, limit, sortKey, sortOrder)
        except:
            result = self.dbsmgr.exe(dbsInst, userInput, fromRow, fromRow+limit)
        return result

    def _summaryQuery(self, dbsInst, userInput, fromRow, limit, sortKey="", sortOrder=""):
        if  (not limit and not fromRow) or limit==-1:
            limit=""
            fromRow=""
        res = self.dbsmgr.summary(dbsInst, userInput, fromRow, fromRow+limit, sortKey, sortOrder)
        return res

    def exeQuery(self, dbsInst, userInput, fromRow, limit):
        try:
            return self._exeQuery(dbsInst, userInput, fromRow, limit)
        except:
            traceback.print_exc()
            time.sleep(2)
            try:
                return self._exeQuery(dbsInst, userInput, fromRow, limit)
            except:
                traceback.print_exc()
                raise

    def _exeQuery(self, dbsInst, userInput, fromRow, limit):
        if (not limit and not fromRow) or limit==-1:
           fromRow=""
           limit=""
        result, titleList = self.dbsmgr.exe(dbsInst, userInput, fromRow, fromRow+limit)
        return result, titleList

    def getQuery(self, dbsInst, userInput, fromRow = "", limit = ""):
        if (not limit and not fromRow) or limit==-1:
           fromRow=""
           limit=""
        begin = fromRow
        end   = fromRow+limit
        sql, bDict, count_sql, cDict = self.dbsmgr.query(dbsInst, userInput, begin, end)
        return sql.replace("  ","\n")

    def countQuery(self, dbsInst, userInput):
        try:
            return self._countQuery(dbsInst, userInput)
        except:
            traceback.print_exc()
            print "dbsInst", dbsInst
            print "userInput", userInput
            time.sleep(2)
            try:
                return self._countQuery(dbsInst, userInput)
            except:
                traceback.print_exc()
                raise

    def _countQuery(self, dbsInst, userInput):
        nResults = self.dbsmgr.count(dbsInst, userInput)
        return nResults

    def multiSearch(self,userInput,**kwargs):
        # check params for cross-checking security flaw
        for p in [userInput] + kwargs.keys():
            self.checkParam(p)
        ajax = getArg(kwargs, 'ajax', 0)
        dbs  = getArg(kwargs, 'dbsInst', '')
        page = ''
        if  int(ajax) == 1:
            self.setContentType('xml')
            page+="""<ajax-response><response type="element" id="%s">""" % dbs
        resList  = []
        userMode = getArg(kwargs,'userMore','user')
        method   = "dbsapi"
        if  not dbs:
            dbsList  = list(self.dbsList)
        else:
            dbsList  = [dbs]
        for ins in dbsList:
            resList.append(self.countQuery(ins, userInput))
        nameSearch = {'site':'CERN','url':self.dbsdd,'dbsInstList':dbsList,'input':userInput,'resList':resList,'userMode':userMode,'method':method}
        t     = templateMultiResults(searchList=[nameSearch]).respond()
        if  not int(ajax):
            page += self._multiSearch(userMode = userMode, userInput = userInput, genTop = 0)
        page += str(t)
        if  int(ajax)==1:
            page+="</response></ajax-response>"
        else:
            page  = self.genTopHTML()+page
            page += self.genBottomHTML()
        return page
    multiSearch.exposed=True

    def getPFNsHelper(self, dbsInst, site, 
                      run = '', dataset = '', lfn = '', protocol = ''):
        if  not site:
            raise Exception("Site is required parameter")
        if  not protocol:
            try:
                protocol = self.sitecfg.protocol(site)
            except:
                protocol = 'srm'
        if  not protocol:
            protocol = 'srm'
        if  site.find('.') == -1:
            site = self.sdb.getSEName(site)
        if  not site:
            return
        if  lfn:
            lfnList = [lfn]
        else:
            query   = 'find file where site = %s ' % site
            if  run:
                query += ' and run = %s ' % run 
            if  dataset:
                query += ' and dataset = %s ' % dataset 
            res, titles = self.exeQuery(dbsInst, query, fromRow = "", limit = "")
            lfnList = [i[0] for i in res]
        print "\n\n###",site, lfnList[:10], protocol
        pfnList = []
        step = 100
        for idx in range(0, len(lfnList), step):
            if  idx+step < len(lfnList):
                end = idx+step
            else:
                end = len(lfnList)
            try:
                pList = self.phedex.getFiles(lfnList[idx:end], site, protocol)
                pfnList += pList
            except:
                pass
        return pfnList

    def getPFNs(self, dbsInst, site, **kwargs):
        run      = getArg(kwargs, 'run', '')
        dataset  = getArg(kwargs, 'dataset', '')
        lfn      = getArg(kwargs, 'lfn', '')
        protocol = getArg(kwargs, 'protocol', '')
#        page = self.genTopHTML()
#        if  type(site) is types.ListType:
#            nameSearch = {'sites':site, 'run': run, 'dataset': dataset,
#                          'lfn':lfn, 'protocol':protocol } 
#            t = templateSitePFN(searchList=[nameSearch]).respond()
#            page  = str(t)
#            page += self.genBottomHTML()
#            return page
        cherrypy.response.headers['Content-Type']='text/plain'
        if  type(site) is types.ListType:
            siteList = site
        else:
            siteList = [site]
        page = ''
        for se in siteList:
            page += 'Site: %s' % se
            print se, run, dataset, lfn, protocol
            pfnList  = self.getPFNsHelper(dbsInst, se, run, dataset, lfn, protocol)
            print pfnList, type(pfnList)
            for pfn in pfnList:
                page += pfn + '\n'
            page += '\n'
        return page
    getPFNs.exposed = True

    def outputConfigMap(self):
        """Log server configuration parameters"""
        serverVars = [
#                      'httpservers',
                      'instance',
                      'interrupt',
                      'max_request_body_size',
                      'max_request_header_size',
                      'protocol_version',
                      'ssl_certificate',
                      'ssl_private_key',
                      'socket_host',
                      'socket_port',
                      'socket_file',
                      'reverse_dns',
                      'socket_queue_size',
                      'thread_pool',
                     ]
        msg="+++ CherryPy serer configuration:"
        print msg
        for var in serverVars:
            msg="    %s: %s" % (var, getattr(cherrypy.server,var))
            print msg

    def setConfig(self,base=""):
        dd_home = os.environ['DDHOME']
        # used thread_pool, queue_size parameters to tune up server performance
        # see discussion on http://amix.dk/blog/viewEntry/119
        cherrypy.server.thread_pool = 30
        cherrypy.server.socket_queue_size = 15
        mime_types=['text/css','text/javascript','application/javascript','application/x-javascript','image/gif','image/png','image/jpg','image/jpeg']
        httpHeader=[('Expires',time.strftime("%a, %d %b %Y %H:%M:%S GMT",time.gmtime(time.time()+315360000))),
                               ('Accept-Encoding','gzip'),
                               ('TE','deflate, gzip, x-gzip, identity, trailer'),
                               ('Cache-Control','max-age=315360000'),
                               ('Authorization','Basic')
                   ]
                               
        conf = {'/'         : {'tools.staticdir.root': dd_home,
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
                               'tools.staticdir.root': dd_home,
                               'tools.staticdir.dir':'images',
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/rss'      : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'rss'},
                '/xml'      : {'tools.staticdir.on': True, 
                               'tools.staticdir.dir': 'xml',
                               'tools.staticdir.content_types':{'xml':'application/xml'}
                              },
                '/css'      : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.root': dd_home,
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
                               'tools.staticdir.root': dd_home,
                               'tools.staticdir.dir':'yui',
                               'tools.staticdir.content_types':{'js':'text/javascript'},
                               'tools.response_headers.on':True,
                               'tools.response_headers.headers':httpHeader
                              },
                '/YUI'      : {'tools.gzip.on': True, 
                               'tools.gzip.mime_types':mime_types,
                               'tools.staticdir.on':True,
                               'tools.staticdir.root': dd_home,
                               'tools.staticdir.dir':'YUI',
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
    port=opts.port
    cherrypy.config.update({'server.socket_port': port,
                            'server.socket_host': '0.0.0.0',
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

    if opts.verbose:
        print "CherryPy config:"
        print cherrypy.config
    if opts.profile:
       import hotshot                   # Python profiler
       import hotshot.stats             # profiler statistics
       print "Start DBS/DLS discovery server in profile mode"
       profiler = hotshot.Profile("profile.dat")
       profiler.run("cherrypy.quickstart(dbsManager, '/', config=conf)")
       profiler.close()
       stats = hotshot.stats.load("profile.dat")
       stats.sort_stats('time', 'calls')
       stats.print_stats()
    else:       
       cherrypy.quickstart(dbsManager, '/', config=dbsManager.setConfig())
