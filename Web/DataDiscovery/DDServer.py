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
import xml.sax, xml.sax.handler
from   xml.sax.saxutils import escape

# Cheetah template modules
from   Cheetah.Template import Template

# CherryPy server modules
import cherrypy 

# DBS  modules
from   DDUtil    import *
from   DDConfig  import *
from   DDLucene  import *
from   DDHelper  import *
from   Templates import *
 
class DDServer(DDLogger): 
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
    def __init__(self,verbose=0,profile=0):
        """
           DDServer constructor. It takes only one optional argument.
           Initialize L{DBSHelper} with default DBS instance "MCGlobal/Writer".
           Keep cache of DBS instances and current selection of (dbs,site,app,primD,tier).
           @type  verbose: boolean or integer 
           @param verbose: verbosity level 
           @rtype : none
           @return: none 
        """
        DDLogger.__init__(self,"DDServer",verbose)
        self.ddConfig  = DDConfig()
        self.lucene = DDLucene(verbose)
        self.dbs  = DBSGLOBAL
        self.site = ""
        self.app  = ""
        self.primD= ""
        self.tier = ""
        if self.ddConfig.iface()=='sqlalchemy':
           self.helper     =  DDHelper(self.dbs,self.ddConfig.iface(),verbose,html=1)
        else:
           self.helper     = DBSHelper(self.dbs,self.ddConfig.iface(),verbose,html=1)
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
        self.topHTML    = ""
        self.bottomHTML = ""
        self.verbose    = verbose
        self.profile    = profile
        self.dbsDict    = {}
        self.userMode   = True
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
        self.port       = 8001
        for line in open('CherryServer.conf').readlines():
            if string.find(line,'server.socketPort')!=-1:
               self.port=string.strip(string.split(string.replace(line,'\n',''),'=')[1])
               break
        try:
            self.dbsdd = self.ddConfig.url()
            if self.verbose:
               print "Read from config"
        except:
            printExcept()
            self.dbsdd = 'http://'+self.hostname+":"+str(self.port)
            print "Read from hostname and port"
        if os.environ.has_key('DBSDD'):
           self.dbsdd = os.environ['DBSDD']
        print "DDServer '%s'"%self.dbsdd
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
              }
        self.writeLog("DDServer init")

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
        if  int(string.split(cherrypy.__version__,".")[0])==3:
            if type=="xml":
               cherrypy.response.headers['Content-Type']='text/xml'
            else:
               cherrypy.response.headers['Content-Type']='text/html'
        elif int(string.split(cherrypy.__version__,".")[0])==2:
            if type=="xml":
               cherrypy.response.headerMap['Content-Type'] = "text/xml"
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
        msg+="Request from: %s:%s\n"%(cherrypy.request.remote_host,cherrypy.request.remote_port)
        msg+="Request url : %s\n"%cherrypy.request.browser_url
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

    def htmlInit(self):
        """
           Initialize top/bottom common structures of HTML page and cache it.
           @type  self: class object
           @param self: none 
           @rtype : none 
           @return: none
        """
        if not self.topHTML:
           self.topHTML=self.genTopHTML()
        if not self.bottomHTML:
           self.bottomHTML=self.genBottomHTML()

    def genTopHTML(self,intro=False):
        """
           Generates top structure of HTML page using Cheetah template.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        nameSpace = {
                     'host'        : self.dbsdd,
                     'title'       : 'DBS Data Discovery Page',
                     'dbsGlobal'   : DBSGLOBAL,
                     'userMode'    : self.userMode
                    }
        page = templateTop(searchList=[nameSpace]).respond()
        if intro:
           page+=templateIntro().reapond()
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

    def index(self): 
        """
           Construct start up page by invoking L{init} call.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
            self.userMode=True
            return self.init()
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
        nameSpace = {
                     'msg':getExceptionInHTML(),
                     'port': cherrypy.request.remote_port,
                     'host': cherrypy.request.remote_host,
                     'url' : cherrypy.request.browser_url
                    }
        t = templateERROR(searchList=[nameSpace]).respond()
        self.sendErrorReport(msg+"\n"+getExcept())
        return str(t)

    def genVisiblePanel(self,view):
        """
           Generate code for Navigation Panel which is visible by default
           @type view: string
           @param view: specify view to be visible at front, e.g. keyword search
           @rtype: string
           @return: returns HTML code
        """
        try:
            nameSpace = {
                         'host'    : self.dbsdd,
                         'panel'   : self.genPanelHelper(), 
                         'view'    : view
                        }
            t = templateVisiblePanel(searchList=[nameSpace]).respond()
            page = str(t)
            return page
        except:
            t=self.errorReport("Fail in genVisiblePanel function")
            pass
            return str(t)
    genVisiblePanel.exposed=True

    def genHiddenPanel(self,view):
        """
           Generate code for Navigation Panel which is hidden by default
           @type view: string
           @param view: specify view to be visible at front, e.g. keyword search
           @rtype: string
           @return: returns HTML code
        """
        try:
            nameSpace = {
                         'host'    : self.dbsdd,
                         'panel'   : self.genPanelHelper(), 
                         'view'    : view
                        }
            t = templateHiddenPanel(searchList=[nameSpace]).respond()
            page = str(t)
            return page
        except:
            t=self.errorReport("Fail in genHiddenPanel function")
            pass
            return str(t)
    genHiddenPanel.exposed=True

    def glossary(self):
        """
           Generate DBS glossary page
        """
        nameSpace = {}
        t = templateGlossary(searchList=[nameSpace]).respond()
        return str(t)
        
    def genPanelHelper(self):
        """
           Navigator Panel helper function which perform all the work
        """
        msg,dbsInst,site,app,primD,tier=self.formDict['menuForm']
        menuForm=self.genMenuForm(0,msg,dbsInst,site,app,primD,tier)
        dbsInst,site=self.formDict['siteForm']
        siteForm=self.siteForm(dbsInst,site)
        dbsInst=self.formDict['searchForm']
        searchForm=self.searchForm(dbsInst)

        userNav = self.genUserNavigator()

        t = templateOperators(searchList=[{'tag':'parameterListOperators'}]).respond()
        nameSpace={'operators':str(t)}
        t = templateSearchEngine(searchList=[nameSpace]).respond()
        luceneForm=str(t)
        dbsTables = self.helper.dbManager.getTableNames(dbsInst)
        dbsTables.sort()

        nameSpace = {
                     'host'         : self.dbsdd,
                     'userMode'     : self.userMode,
                     'navigatorForm': menuForm,
                     'userNavigator': userNav,
                     'searchForm'   : searchForm,
                     'siteForm'     : siteForm,
                     'dbsNames'     : self.dbsList,
                     'glossary'     : self.glossary(),
                     'frontPage'    : 0,
                     'dbsGlobal'    : DBSGLOBAL,
                     'dbsList'      : self.dbsList,
                     'DBSDD'        : self.dbsdd,
                     'step'         : GLOBAL_STEP,
                     'iface'        : self.ddConfig.iface(),
                     'tip'          : tip(),
                     'luceneForm'   : luceneForm,
                     'dbsInst'      : dbsInst,
                     'dbsTables'    : dbsTables
                    }
        t = templateFrontPage(searchList=[nameSpace]).respond()
        return str(t)
        
    def genPanel(self):
        """
           Construct Init service page, which includes L{genMenuForm}.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
            nameSpace = {
                         'host'         : self.dbsdd,
                         'userMode'     : self.userMode,
                         'navigatorForm': self.genMenuForm(),
                         'searchForm'   : self.searchForm(),
                         'siteForm'     : self.siteForm(),
                         'dbsNames'     : self.dbsList,
                         'frontPage'    : 1,
                         'dbsGlobal'    : DBSGLOBAL,
                         'glossary'     : self.glossary(),
                         'tip'          : tip()
                        }
            t = templateFrontPage(searchList=[nameSpace]).respond()
            return str(t)
        except:
            t=self.errorReport("Fail in genPanel function")
            pass
            return str(t)

    def init(self,view='Navigator'):
        """
           Construct Init service page, which includes L{genMenuForm}.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
            page = self.genTopHTML(intro=False)
            page+= self.genVisiblePanel(view)
            page+= self.genResultsHTML()
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in init function")
            pass
            return str(t)
    init.exposed = True
    
    def expert(self):
        """
           Constructs 'expert' service page. The summary, L{getSummary}, has been updated
           once a day by using internal timer.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
            self.userMode = False
            return self.init()
        except:
            t=self.errorReport("Fail in expert function")
            pass
            return str(t)
    expert.exposed = True

    def dbsExpert(self):
        """
           Constructs 'dbsExpert' service page. The summary, L{getSummary}, has been updated
           once a day by using internal timer.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
            self.userMode = "dbsExpert"
            return self.init('SQL')
        except:
            t=self.errorReport("Fail in dbsExpert function")
            pass
            return str(t)
    dbsExpert.exposed = True

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
        
    def genUserNavigator(self):
        nameSearch={
                    'dbsInst'     : DBSGLOBAL,
                    'groupList'   : self.helper.getPhysicsGroups,
                    'dataTypes'   : self.helper.getDataTypes(),
                    'softReleases': self.helper.getSoftwareReleases(),
                    'primDatasets': self.helper.getPrimaryDatasets(),
                    'siteList'    : getListOfSites(), 
                   }
        t = templateUserNav(searchList=[nameSearch]).respond()
        return str(t)
        

    def searchForm(self,firstDBS=DBSGLOBAL):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """

        if self.ddConfig.iface()=='cgi': searchFunc="javascript:ajaxSearch()"
        else: searchFunc="javascript:ResetAllResults();ajaxFinderSearch()"

        # FIXME, TODO: firstDBS should be passed here
        if not firstDBS: firstDBS=DBSGLOBAL

        sectionList=self.sectionDict.keys()
        sectionList.sort()
        tableList = self.sectionDict['Algorithm']
        tableList.sort()
        
        nameSpace={'id':1,'sectionList':sectionList,'tableList':tableList}
        t = templateSelectLine(searchList=[nameSpace]).respond()
        selectLine = str(t)

        nameSpace = {
                     'firstDBS'       : firstDBS,
                     'userMode'       : self.userMode,
                     'dbsList'        : self.dbsList,
                     'searchFunction' : searchFunc,
                     'selectLine'     : selectLine,
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
        
    def genMenuForm(self,frontPage=0,msg="",firstDBS="",firstSite="",firstApp="",firstPrim="",firstTier=""):
        """
           Constructs menus. It caches user selections and pass then to HTML form.
           All work is done by using Cheetah templates.
           @type  msg: string 
           @param msg: optional message
           @type firstDBS: string
           @param firstDBS: user selection of DBS menu
           @type firstSite: string
           @param firstSite: user selection of the site
           @type firstApp: string
           @param firstApp: user selection of the application
           @type firstPrim: string
           @param firstPrim: user selection of the primary dataset 
           @type firstTier: string
           @param firstTier: user selection of the data tier
           @rtype : none
           @return: returns HTML code
        """
        if not firstDBS: firstDBS=DBSGLOBAL
        if firstSite=="*": firstSite="All"
        if firstTier=="*": firstTier="All"
        # IMPORTANT: here I commented out load of dictionary, since now I obtained it from AJAX
        # I also commented out obj=$dict in CheetahDBSTemplate.templateJS 
#        dbsDict = self.getDBSDict()
        nameSpace = {
                     'host'     : self.dbsdd, 
#                     'dict'     : dbsDict,
                     'userMode' : self.userMode,
                     'firstDBS' : firstDBS,
                     'firstSite': firstSite,
                     'firstApp' : firstApp,
                     'firstPrim': firstPrim,
                     'firstTier': firstTier
                    }
        t = templateJS(searchList=[nameSpace]).respond()
        page = str(t)
        if self.userMode:
           dbsList=[DBSGLOBAL]
        else:
           dbsList = self.dbsList
        nameSpace = {
                     'dbsList'  : dbsList,
                     'frontPage': frontPage,
                     'sList'    : getListOfSites(), 
                     'userMode' : self.userMode,
                     'msg'      : msg,
                     'firstDBS' : firstDBS,
                     'firstSite': firstSite,
                     'firstApp' : firstApp,
                     'firstPrim': firstPrim,
                     'firstTier': firstTier,
                     'dbsDesc'  : templateDBSInstanceDesc().respond(),
                     'siteDesc' : templateTierSiteDesc().respond(),
                     'primDesc' : templateApplicationDesc().respond(),
                     'appDesc'  : templatePrimaryDatasetDesc().respond(),
                     'tierDesc' : templateDataTierDesc().respond(),
                    }
        t = templateJSForm(searchList=[nameSpace]).respond()
        page+= str(t)
        return page
        
    def genSnapshot(self,dbs,site,app,primD,tier):
        """
           Captures a current user selection of provided fields.
           @type  dbs: string
           @param dbs: user selection of DBS menu
           @type  site: string
           @param site: user selection of the site
           @type  app: string
           @param app: user selection of the application
           @type  primD: string
           @param primD: user selection of the primary dataset 
           @type  tier: string
           @param tier: user selection of the data tier
           @rtype : string 
           @return: returns HTML code
        """
        nameSpace = {
                     'userMode': self.userMode,
                     'dbsInst' : dbs, 
                     'site'    : site,
                     'app'     : app,
                     'primD'   : primD,
                     'tier'    : tier
                    }
        t = templateSnapshot(searchList=[nameSpace]).respond()
        return str(t)
        
    def getDataFromSelection(self,userSelection,ajax=1,**kwargs):
        """
           Retrieves data upon user selection criterias.
        """
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="results">"""
           endAjaxMsg="</response></ajax-response>"
        else:
           page=self.genTopHTML()
           page+= self.genResultsHTML()
        try:
            if  not userSelection:
                page+="No data found"
            else:
                # see CheetahDBSTemplate templateSearchTable form
                sList = string.split(userSelection,",")
                for item in sList:
                    if not item: continue
                    dbsInst,primD,tier,ver,fam,exe=string.split(item,"___")
                    app="/"+ver+"/"+fam+"/"+exe
                    group="*"
                    site="All"
                    if not self.userMode:
                       page+="""<div class="sectionhead_tight">%s instance</div>"""%dbsInst
                    page+= self.getDataHelper(dbsInst,site,group,app,primD,tier)
                    self.firstSearch=0
        except:
            t=self.errorReport("Fail in getDataFromSelection function")
            page+=str(t)
            pass
        url="%s/getDataFromSelection?userSelection=%s&amp;ajax=0"%(self.dbsdd,userSelection)
        page+="""<hr class="dbs" /><p>For a bookmark to this data, use</p><a href="%s">%s</a>"""%(url,splitString(url,122))
        if int(ajax):
           page+=endAjaxMsg
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDataFromSelection.exposed = True 
    
    def getDataFromSelection_useForm(self,userSelection,**kwargs):
        """
           Retrieves data upon user selection criterias.
        """
        print "Call getDataFromSelection",userSelection
        # AJAX wants response as "text/xml" type
        try:
            page=self.genTopHTML()
            page+= self.searchForm()
            page+= self.genResultsHTML()
            self.firstSearch=1
            # see CheetahDBSTemplate templateSearchTable form
            sList = userSelection
            if type(userSelection) is not types.ListType:
               sList = [userSelection]
            for item in sList:
                dbsInst,primD,tier,ver,fam,exe=string.split(item,"___")
                app="/"+ver+"/"+fam+"/"+exe
                site="All"
                group="*"
                if not self.userMode:
                   page+="""<div class="dbs-h1">%s instance</div>"""%dbsInst
                page+= self.getDataHelper(dbsInst,site,group,app,primD,tier)
                self.firstSearch=0
            page+=self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getDataFromSelection function")
            pass
            return str(t)
    getDataFromSelection_useForm.exposed = True 

    def showProcDatasets(self,dbsInst,site="All",app="*",primD="*",tier="*",proc="*"):
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
        try:
            self.helperInit(dbsInst)
            page = self.genTopHTML()
            page+= self.genResultsHTML()
            page+= "<pre>"
            if  len(proc) and proc!="*":
                page+=proc+"\n"
            else:
                procList = self.helper.getDatasetsFromApp(app,primD,tier)
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

    def showProcDatasetsHTML(self,dbsInst,site,app,primD,tier):
        """
           Get all processed datasets for given set of input parameters
           @type  dbs: string
           @param dbs: user selection of DBS menu
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
        nameSpace = {
                     'host'    : self.dbsdd,
                     'dbsInst' : dbsInst, 
                     'site'    : site,
                     'app'     : app,
                     'primD'   : primD,
                     'tier'    : tier
                    }
        t = templateProcDatasets(searchList=[nameSpace]).respond()
        return str(t)
    
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

    def getDatasetList(self,group="*",app="*",prim="*",tier="*",proc="*"):
        """
           Call different APIs for given list of app/prim/tier/proc. Return a list of processed
           datasets.
        """
        return self.helper.listProcessedDatasets(group,app,prim,tier,proc)

    def getDataHelper(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",hist="",_idx=0,ajax=1,**kwargs): 
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
        if string.lower(tier)=="all" or string.lower(tier)=="all": tier="*"
        if string.lower(site)=="all" or string.lower(site)=="all": site="*"
        self.dbsTime=self.dlsTime=0
        page=""
        className="hide"
        if int(_idx)==0:
           className="show_inline"
            
        page+="""<div id="results_response_%s" class="%s">"""%(_idx,className)
        
        self.helperInit(dbsInst)
        self.dbs  = dbsInst
        self.site = site
        self.app  = app
        self.primD= primD
        self.tier = tier
        
        leftBar = self.showProcDatasetsHTML(dbsInst,site,app,primD,tier)

        primaryDataset=primD
        dataTier = tier
        appPath = app
        id=0
        prevPage=""
        oldDataset=oldTotEvt=oldTotFiles=oldTotSize=0
        t1=time.time()
        datasetsList = self.getDatasetList(group=group,app=appPath,prim=primD,tier=tier,proc=proc)
        nDatasets=len(datasetsList)

        if _idx and nDatasets<(_idx+1)*RES_PER_PAGE:
           return ""

        t2=time.time()
        self.dbsTime=(t2-t1)
        regList=[]
        bList=[]
        id=0

        nameSpace = {
                     'userMode': self.userMode,
                     'dbsInst' : dbsInst,
                     'site'    : site,
                     'app'     : app,
                     'primD'   : primD,
                     'tier'    : tier,
                     'proc'    : proc
                    }
        t = templateSnapshot(searchList=[nameSpace]).respond()
        snapshot=str(t)
        # the view and process page should be generated once
        if  int(_idx)==0:
            nameSpace = {'leftBar' : leftBar}
            t = templateLeftBar(searchList=[nameSpace]).respond()
            page+=str(t)

        page+="""<script type="text/javascript">ShowPageResults()</script>"""
        self.dbsTime=(t2-t1)
        for idx in xrange(0,nDatasets):
            ttt1 = time.time()
            dataset = datasetsList[idx]
            id+=1
            self.writeLog(dataset)

            # process only RES_PER_PAGE datasets within given (_idx) index range
            if not (_idx*RES_PER_PAGE<=idx and idx<(_idx*RES_PER_PAGE+RES_PER_PAGE)): continue

            siteList, blockDict, totEvt, totFiles, totSize = self.helper.getData(dataset,site)
            self.dbsTime+=self.helper.dbsTime
            self.dlsTime+=self.helper.dlsTime
            # new stuff which do not show repeating datasets
            p = self.dataToHTML(dbsInst,dataset,siteList,blockDict,totEvt,totFiles,totSize,id,snapshot)
            if oldTotEvt==totEvt and oldTotFiles==totFiles and oldDataset:
               bList.append((dataset,totEvt))
            else:
               if len(bList): 
                  page+=self.blockListToHTML(dbsInst,bList,appPath)
                  bList=[]
               bList.append((dataset,totEvt))
               page+=prevPage
            oldTotEvt=totEvt
            oldTotFiles=totFiles
            oldTotSize=totSize
            oldDataset=dataset
            prevPage = p

#            print "##### %s %s sec"%(dataset,(time.time()-ttt1))
        page+=self.blockListToHTML(dbsInst,bList,appPath)
        page+=prevPage # end of new stuff

        # generate URL link
        page+=self.getURL(dbsInst,site,group,app,primD,tier,proc,hist)
        # end of response tag
        page+="</div>"

        # the progress bar for all results
        if  int(_idx)==0:
            step=GLOBAL_STEP
            pages = nPages(nDatasets,RES_PER_PAGE)
            if step>pages: step=pages
            nameSpace = {
                         'idx'     : _idx,
                         'step'    : step,
                         'tot'     : pages,
                         'dbsInst' : dbsInst,
                         'site'    : site,
                         'group'   : group,
                         'app'     : app,
                         'prim'    : primD,
                         'tier'    : tier,
                         'proc'    : proc,
                         'res_page': RES_PER_PAGE
                        }
            t = templateNextBar(searchList=[nameSpace]).respond()
            page+=str(t)
        return page
    getDataHelper.exposed=True

    def blockListToHTML(self,dbsInst,bList,appPath="*"):
        if not len(bList): return ""
        nameSpace = {'host': self.dbsdd, 'dbsInst': dbsInst, 'blockList' : bList,'appPath':appPath}
        t = templateBlockList(searchList=[nameSpace]).respond()
        page=str(t)
        return page
        
    def crabCfg(self,dataset,totEvt,**kwargs):
        nameSpace = {
                     'dataset'  : dataset,
                     'totEvt'   : totEvt
                    }
        t = templateCRAB(searchList=[nameSpace]).respond()
        page=str(t)
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

    def getData(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",hist="",_idx=0,ajax=1,**kwargs): 
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
        page=""
        if  int(ajax):
            # AJAX wants response as "text/xml" type
            self.setContentType('xml')
            page="""<ajax-response><response type="object" id="results">"""
            try:
                if string.lower(tier)=="all" or string.lower(tier)=="any": tier="*"
                if string.lower(site)=="all" or string.lower(site)=="any": site="*"
                self.helperInit(dbsInst)
                self.htmlInit()
                msg="dbsInst='%s', site='%s', app='%s', primD='%s', tier='%s'"%(dbsInst,site,app,primD,tier)
                self.writeLog(msg)
                msg=""
                self.formDict['menuForm']=(msg,dbsInst,site,app,primD,tier)
                page+= self.getDataHelper(dbsInst,site,group,app,primD,tier,proc,hist,_idx,ajax)
            except:
                t=self.errorReport("Fail in getData function")
                page+=str(t)
            t2=time.time()
            if not self.userMode and self.profile:
               page+=self.responseTime(t2-t1)
            page+="</response></ajax-response>"
        else:
           page=self.genTopHTML()
           if hist:
              page+=hist
           page+= self.genResultsHTML()
           sumData="ajaxGetData('%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc)
           dbsData="ajaxGetDbsData('%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc)
           runData="ajaxGetRuns('%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc)
           parents="ajaxGenParentsGraph('%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc)
           configs="ajaxGenAppConfigs('%s','%s','%s','%s','%s','%s','%s')"%(dbsInst,site,group,app,primD,tier,proc)
           
           page+= """<script type="text/javascript">ajaxInit('%s');showResultsMenu();%s;%s;%s;%s;%s;showResMenu('results')</script>"""%(dbsInst,sumData,dbsData,runData,parents,configs)
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getData.exposed = True 

    def getUserData(self,dbsInst,group="All",tier="*",rel="*",prim="*",site="*",_idx=0,ajax=1,**kwargs): 
        self.helperInit(dbsInst)
        app="/%s/*/*"%rel # it is in form /ver/fam/exe
        _idx=int(_idx)
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results">"""
        page+= self.getDataHelper(dbsInst,site,group,app,primD,tier,proc,hist,_idx,ajax)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getUserData.exposed=True

    def getDbsData(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,**kwargs): 
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
           page=self.genTopHTML()
           page+= self.genResultsHTML()
        try:
            if string.lower(tier)=="all": tier="*"
            self.helperInit(dbsInst)
            self.htmlInit()
            self.formDict['menuForm']=("",dbsInst,"All",app,primD,tier)

            dList=self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc)
            nDatasets=len(dList)
            className="hide"
            if int(_idx)==0:
               className="show_inline"
            page+="""<div id="results_dbs_response_%s" class="%s">"""%(_idx,className)
            for idx in xrange(0,nDatasets):
                tid = 't_dbs_'+str(idx)
                dataset = dList[idx]

                # process only RES_PER_PAGE datasets within given (_idx) index range
                if not (_idx*RES_PER_PAGE<=idx and idx<(_idx*RES_PER_PAGE+RES_PER_PAGE)): continue
                nameSpace = {
                             'dbsList'  : self.helper.getDbsData(dataset),
                             'tableId'  : tid,
                             'proc'     : dataset,
                             'host'     : self.dbsdd,
                             'dbsInst'  : dbsInst
                            }
                t = templateDbsInfo(searchList=[nameSpace]).respond()
                page+=str(t)
            page+="</div>"
        except:
            t=self.errorReport("Fail in getDbsData function")
            page+=str(t)
        t2=time.time()
        if not self.userMode and self.profile:
           page+=self.responseTime(t2-t1)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDbsData.exposed = True 

    def getRuns(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,**kwargs): 
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
           page=self.genTopHTML()
           page+= self.genResultsHTML()
        try:
            if string.lower(tier)=="all": tier="*"
            self.helperInit(dbsInst)
            self.htmlInit()
            self.formDict['menuForm']=("",dbsInst,"All",app,primD,tier)

            dList=self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc)
            nDatasets=len(dList)
            className="hide"
#            if not _idx and nDatasets<(_idx+1)*RES_PER_PAGE:
            if int(_idx)==0:
               className="show_inline"
            page+="""<div id="runs_response_%s" class="%s">"""%(_idx,className)
            for idx in xrange(0,nDatasets):
                tid = 't_runs_'+str(idx)
                dataset = dList[idx]

                # process only RES_PER_PAGE datasets within given (_idx) index range
                if not (_idx*RES_PER_PAGE<=idx and idx<(_idx*RES_PER_PAGE+RES_PER_PAGE)): continue
                nameSpace = {
                             'runList'  : self.helper.getRuns(dataset),
                             'tableId'  : tid,
                             'proc'     : dataset
                            }
                t = templateRunsInfo(searchList=[nameSpace]).respond()
                page+=str(t)
            page+="</div>"
        except:
            t=self.errorReport("Fail in getRunsData function")
            page+=str(t)
        t2=time.time()
        if not self.userMode and self.profile:
           page+=self.responseTime(t2-t1)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getRuns.exposed = True 

    def getAnalysisDS(self,dbsInst,dataset=""):
        page="Here information about analysis dataset information will appear"
        return page
    getAnalysisDS.exposed=True

    def getParameterSet(self,dbsInst,dataset=""):
        page="Here information about paramterSet information will appear"
        return page
    getParameterSet.exposed=True

    def getLFNlist(self,dbsInst,blockName,dataset=""):
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
            self.htmlInit()
            page = self.genTopHTML()
            page+= self.genResultsHTML()
            page+="""<div id="_snapshot"></div><hr class="dbs" /><br />"""
            page+="""<script type="text/javascript">GetParentNavBar()</script>"""
            page+= self.lfnToHTML(dbsInst,blockName,dataset)
            url="""%s/getLFNlist?dbsInst=%s&amp;blockName=%s&amp;dataset=%s"""%(self.dbsdd,dbsInst,string.replace(blockName,'#','%23'),dataset)
            page+="""<hr class="dbs" /><p>For a bookmark to this data, use</p><a href="%s">%s</a>"""%(url,splitString(url,122))
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getLFNlist function")
            pass
            return str(t)
    getLFNlist.exposed = True
 
    def getLFN_txt(self,dbsInst,blockName,dataset=""):
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
            self.htmlInit()
            page ="""<html><body><pre>\n"""
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset)
            for item in lfnList:
                lfn=item[0]
                page+="%s\n"%lfn
            page+="\n</pre></body></html>"
            return page
        except:
            t=self.errorReport("Fail in getLFN_txt function")
            pass
            return str(t)
    getLFN_txt.exposed = True
    
    def getLFNsForSite(self,dbsInst,site,blockList="",what="cff"):
        """
           Generates a list of LFNs for given site
        """
        try:
            self.htmlInit()
            page ="""<html><body><pre>\n"""
            bList=[]
            if  blockList:
                bList=blockList.split(",")
            else:
                try:
                    bList=self.helper.getBlocksFromSite(site)
                except:
                    printExcept()
                    page+="No LFNs found for site '%s'\n"%site
                    pass
            if what=="cff":
               page+="replace PoolSource.fileNames = {\n"
            for blockName in bList:
                try:
                    lfnList = self.helper.getLFNs(dbsInst,blockName,"")
                    if not lfnList: page+="No LFNs found in '%s' on site='%s' for blockName='%s'"%(dbsInst,site,blockName)
                    for item in lfnList:
                        if  what=="cff":
                            lfn=item[0]
                            if lfn==lfnList[-1][0]:
                               page+="'%s'\n"%lfn
                            else:
                               page+="'%s',\n"%lfn
                        else:
                            lfn=item[0]
                            page+="%s\n"%lfn
                except:
                    printExcept()
                    page+="No LFNs found int DBS for block='%s'\n"%blockName
                    pass
            if what=="cff": page+="}"
            page+="\n</pre></body></html>"
            return page
        except:
            t=self.errorReport("Fail in getLFNsForSite function")
            pass
            return str(t)
    getLFNsForSite.exposed=True
    
    def getConfigContent(self,dbsInst,id,name,**kwargs):
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
            self.htmlInit()
            page = self.genTopHTML()
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

    def getBlocksForSite(self,site):
        """
           Generates list of file blocks for given site
        """
        try:
            self.htmlInit()
            page ="""<html><body><pre>\n"""
            for blockName in self.helper.getBlocksFromSite(site):
                page+="%s\n"%blockName
            page+="\n</pre></body></html>"
            return page
        except:
            t=self.errorReport("Fail in getBlocksForSite function")
            pass
            return str(t)
    getBlocksForSite.exposed=True
    
    def getLFN_cfg(self,dbsInst,blockName,dataset=""):
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
            self.htmlInit()
            page ="""<html><body><pre>\n"""
            page+="replace PoolSource.fileNames = {\n"
            lfnList = self.helper.getLFNs(dbsInst,blockName,dataset)
            for item in lfnList:
                lfn=item[0]
                if lfn==lfnList[-1][0]:
                   page+="'%s'\n"%lfn
                else:
                   page+="'%s',\n"%lfn
            page+="}\n</pre></body></html>"
            return page
        except:
            t=self.errorReport("Fail in getLFN_cfg function")
            pass
            return str(t)
    getLFN_cfg.exposed = True

    def lfnToHTML(self,dbsInst,blockName,dataset=""):
        """
           Constructs LFN list into table.
           @type  dataset: string
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        lfnList = self.helper.getLFNs(dbsInst,blockName,dataset)
        nameSpace = {
                     'host'      : self.dbsdd,
                     'dbsInst'   : dbsInst,
                     'blockName' : blockName,
                     'lfnList'   : lfnList
                    }
        t = templateLFN(searchList=[nameSpace]).respond()
        return str(t)
        
    def dataToHTML(self,dbsInst,path,siteList,blockDict,totEvt,totFiles,totSize,id,snapshot=""):
        """
           Forms output tables.
           @type  path: string 
           @param path: processing path
           @rtype : string
           @return: returns HTML code
        """

        # keep in cache siteList
#        self.siteDict=siteList
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
                     'userMode'   : self.userMode,
                     'tid'        : id,
                     'snapshot'   : snapshot
                    }
        t = templateLFB(searchList=[nameSpace]).respond()
	page+=str(t)
        return page
        
    def getFileBlocks(self,dbsInst,site,**kwargs):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results">"""
        page+="""
<table id="tableSiteBlocks" class="sortable" cellspacing="0" cellpadding="0" border="1">
<tr valign="top" align="center" id="tr_tableSiteBlock" name="tr_tableSiteBlock" class="sortable_gray">
<td>Block name</td>
<td>Events</td>
<td>Files</td>
<td>Size</td>
<td>LFNs</td>
<td>Status</td>
<td>Created by</td>
<td>Creation time</td>
<td>Modified by</td>
<td>Modifiction time</td>
</tr>
"""
        self.helper.setDBSDLS(dbsInst)
        self.helper.setDLS_LFC()
        # code stolen from DLS, TODO: add try/except and handle error conditions
        partialList = self.helper.dls_iface._getEntriesFromDir("/", site, True)
        for i in partialList:
            if(not i.fileBlock.name.startswith('/')): i.fileBlock.name = '/' + i.fileBlock.name
            blockName = i.fileBlock.name
            nameSpace = {
                         'dbsList'  : self.helper.getDbsBlockData(blockName),
                         'tableId'  : 'blockInfo_'+blockName,
                         'host'     : self.dbsdd,
                         'dbsInst'  : dbsInst
                        }
            t = templateDbsInfoTableEntry(searchList=[nameSpace]).respond()
            # query DBS and get more info about blocks
            page+=str(t)
#        page+= str(t)
        page+="</table>"
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getFileBlocks.exposed=True

    def getBlocksFromSiteHelper(self,dbsInst,site):
        """
           Generates AJAX response to get all primary datasets available in all DBS instances
        """
        # AJAX wants response as "text/xml" type
#        self.setContentType('xml')
#        page="""<ajax-response><response type="element" id="siteBlocksHandler">"""
        page=""
        self.helper.setDBSDLS(dbsInst)
        bList = self.helper.getBlocksFromSite(site)
        nameSpace = {
                     'host'   : self.dbsdd,
                     'dbsInst': dbsInst,
                     'site'   : site,
                     'bList'  : bList
                    }
        t = templateFileBlocksFromSite(searchList=[nameSpace]).respond()
        page+= str(t)
#        page+="</response></ajax-response>"
#        if self.verbose==2:
#           self.writeLog(page)
        return page
    getBlocksFromSiteHelper.exposed=True
    
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
        dList = self.helper.getPrimaryDatasets(datasetPath="*",html=1)
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

    def getProcessedDatasetsHelper(self,dbsInst):
        """
           Get list of processed dataset for given DBS instances.
           @type  dbsInst: string
           @param dbsInst: DBS instances
           @rtype : string
           @return: returns HTML code
        """
        self.helperInit(dbsInst)
        dList = self.helper.getProcessedDatasets(datasetPath="*",app=0,html=1)
        nameSpace = {
                     'msg'     : "%s: processed datasets"%dbsInst,
                     'dbsInst' : dbsInst,
                     'dList'   : dList
                    }
        t = templatePrintList(searchList=[nameSpace]).respond()
        return str(t)

    def getProcessedDatasets(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get processed datasets for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="<ajax-response>"
        page+="""<response type="object" id="dbs_proc">"""
        page+="""<div class="div_scroll">"""+self.getProcessedDatasetsHelper(dbsInst)+"</div>"
        page+="</response>\n"
        page+="</ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getProcessedDatasets.exposed=True

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

    def getTiers(self,dbsInst,**kwargs):
        """
           Generates AJAX response to get tiers for given DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="kw_tier">"""
        self.helperInit(dbsInst)
        dList=['Any']+self.helper.getDataTypes()
        nameSpace = {'name':'dataTypes','iList': dList,'selTag':'dataTypes','changeFunction':''}
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
        page="""<ajax-response><response type="element" id="kw_prim">"""
        self.helperInit(dbsInst)
        dList = ['Any']+self.helper.getPrimaryDatasets()
        nameSpace = {'name':'trigLines','iList': dList,'selTag':'trigLines','changeFunction':''}
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
        page="""<ajax-response><response type="element" id="kw_release">"""
        self.helperInit(dbsInst)
        dList = ['Any']+self.helper.getSoftwareReleases()
        nameSpace = {'name':'softReleases','iList': dList,'selTag':'softReleases','changeFunction':''}
        t = templateSelect(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getSoftwareReleases.exposed=True

    def getDatasets(self):
        """
           Forms datasets content into HTML.
           @type  self: class object
           @param self: none 
           @rtype : string
           @return: returns HTML code
        """
        dList = self.helper.getPrimaryDatasets()
        nameSpace = {
                     'host'  : self.dbsdd,
                     'msg'   : "Available primary datasets",
                     'dList' : dList
                    }
        t = templatePrintList(searchList=[nameSpace]).respond()
        page = self.genTopHTML()
        page+= self.genMenuForm()
        page+= str(t)
        page+= self.genBottomHTML()
        return page
    getDatasets.exposed=True

    def getDatasetContentHelper(self,dbsInst,dataset,**kwargs):
        """
           Get dataset content
           @type  dbsInst: string
           @param dbsInst: dbs instance
           @type  dataset: string
           @param dataset: dataset name
           @rtype: string
           @return: returns parents fro given datasets in HTML form 
        """
        page=""
        try:
            self.helperInit(dbsInst)
            siteList, blockDict, totEvt, totFiles, totSize = self.helper.getData(dataset,"*")
            page+=self.blockListToHTML(dbsInst,[(dataset,totEvt)])
            page+=self.dataToHTML(dbsInst,dataset,siteList,blockDict,totEvt,totFiles,totSize,id=0)
        except:
            printExcept()
            page="""<hr class="dbs" />No information found for dataset '%s' in DBS='%s'"""%(dataset,dbsInst)
        return page

    def getDatasetProvenanceHelper(self,dataset,**kwargs):
        """
           Get dataset provenance
           @type  dataset: string
           @param dataset: dataset name
           @rtype: string
           @return: returns parents fro given datasets in HTML form 
        """
        try:
            parents  = self.helper.getDatasetProvenance(dataset)
            nameSpace={
                       'host'      : self.dbsdd, 
                       'dataset'   : dataset, 
                       'parentList': parents
                      }
            t = templateProvenance(searchList=[nameSpace]).respond()
            page = str(t)
        except:
            page="No provenance information found at this time"
        return page

    def getDatasetProvenance(self,dataset,**kwargs):
        """
           Generates AJAX response to get dataset provenance
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page ="""<ajax-response><response type="element" id="parentGraph">"""
        page+=self.getDatasetProvenanceHelper(dataset)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDatasetProvenance.exposed=True

    def getProvenanceForAllDatasets(self,dbsInst,site="All",group="*",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,**kwargs): 
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
           page=self.genTopHTML()
           page+= self.genResultsHTML()

        if string.lower(tier)=="all": tier="*"
        if string.lower(site)=="all": site="*"
        self.helperInit(dbsInst)
        dList = self.getDatasetList(group=group,app=app,prim=primD,tier=tier,proc=proc)
        nDatasets=len(dList)
        className="hide"
        if int(_idx)==0:
           className="show_inline"
        page+="""<div id="parents_response_%s" class="%s">"""%(_idx,className)
        for idx in xrange(0,nDatasets):
            dataset = dList[idx]

            # process only RES_PER_PAGE datasets within given (_idx) index range
            if not (_idx*RES_PER_PAGE<=idx and idx<(_idx*RES_PER_PAGE+RES_PER_PAGE)): continue

            page+=self.getDatasetProvenanceHelper(dataset)
        page+="</div>"
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getProvenanceForAllDatasets.exposed = True 
    
    def genSiteMenuDict(self):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="navigatorDict">"""
        endAjaxMsg="</response></ajax-response>"
        try:
            page+="siteDict="+getDictOfSites()
        except:
            t=self.errorReport("Fail in genSiteMenuDict function")
            page+=str(t)
            pass
        page+=endAjaxMsg
        if self.verbose==2:
           self.writeLog(page)
        return page
    genSiteMenuDict.exposed = True
        
    def siteForm(self,firstDBS="",firstSite=""):
        """
           Generates site form request
        """
        if not firstDBS: firstDBS=DBSGLOBAL
        if firstSite=="*": firstSite="All"
        # IMPORTANT: we removed here siteDict, since it's replaced by AJAX call genSiteMenuDict
        nameSpace = {
                     'firstDBS' : firstDBS,
                     'firstSite': firstSite,
                     'dbsList'  : self.dbsList,
                     'siteDict' : getDictOfSites()
                    }
        t = templateSiteForm(searchList=[nameSpace]).respond()
        page = str(t)
        return page

    def sendFeedback(self,userEmail,feedbackText):
        """
           Generates feedback form.
        """
        p = os.popen("%s -t" % SENDMAIL, "w")
        p.write("To: vkuznet@gmail.com\n")
        p.write("Subject: DBS data discovery user feedback\n")
        p.write("\n") # blank line separating headers from body
        p.write("From: %s\n"%userEmail)
        p.write("\n") # blank line separating headers from body
        p.write(feedbackText)
        sts = p.close()
        page = self.genTopHTML()
        page+= """<p class="sectionhead_tight">Your feedback is greatly appreciated and has been send to maintainer.</p>"""
        page+= self.genVisiblePanel('Resources')
        page+= self.genBottomHTML()
        return page
    sendFeedback.exposed=True

    def storeHistory(self,actionString,userId):
        # update DB history
        # select cmdid from given history string
        c = sqlalchemy.select([t_command.c.id],t_command.c.command==actionString).execute()
        r = c.fetchone()
        if r and r[0]:
           cid = r[0]
        else:
           res=t_command.insert().execute(command=actionString)
           cid=res.last_inserted_ids()[0]
        # try to insert name/password, if fail get them from DB
        uid=0
        try:
           res=t_user.insert().execute(userid=userId)
           uid=res.last_inserted_ids()[0]
        except:
           c = t_user.select(and_(t_user.c.userid==userId)).execute()
           r = c.fetchone()
           if r and r[0]:
              uid = r[0]
        if not uid:
           raise "Fail to find uid in DBS DD history for %s"%(userId,)
        # insert into t_history date/userid/cmdid
        iDate=time.strftime("%Y-%m-%d",time.localtime())
        iTime=time.strftime("%H:%M:%S",time.localtime())
        t_history.insert().execute(userid=uid,cmdid=cid,date=iDate,time=iTime)

    def historySearch(self,iYear,iMonth,oYear,oMonth,userId,**kwargs):
        cList=[]
        try:
            iDate='%s-%s-%s'%(iYear,monthId(iMonth),'01')
            oDate='%s-%s-%s'%(oYear,monthId(oMonth),'31')
            c = select([t_history.c.date,t_history.c.time,t_command.c.command],
                        and_(
                             t_history.c.userid==t_user.c.id,
                             t_history.c.cmdid==t_command.c.id,
                             t_history.c.date>=iDate,t_history.c.date<=oDate,
                             t_user.c.userid==userId
                            ),
                        use_labels=True,distinct=True,
                        order_by=[desc(t_history.c.date),desc(t_history.c.time)]).execute()
            cList=c.fetchall()
        except:
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

    def getHistory(self,userId,iLimit=100,**kwargs):
        cList=[]
        try:
            c = select([t_history.c.date,t_history.c.time,t_command.c.command],
                        and_(
                             t_history.c.userid==t_user.c.id,
                             t_history.c.cmdid==t_command.c.id,
                             t_user.c.userid==userId
                            ),
                        use_labels=True,distinct=True,limit=iLimit,
                        order_by=[desc(t_history.c.date),desc(t_history.c.time)]).execute()
        except:
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

    def history(self,actionString,userId,**kwargs):
        """
            History updater
        """
        try:
            self.storeHistory(actionString,userId)
        except:
#            if not self.quiet:
            printExcept()
            pass
        # AJAX wants response as "text/xml" type
        iDate=time.strftime("%Y-%m-%d",time.localtime())
        iTime=time.strftime("%H:%M:%S",time.localtime())
        self.setContentType('xml')
        nameSpace={
                   'date'      : iDate, 
                   'time'      : iTime, 
                   'action'    : actionString
                  }
        t = templateHistory(searchList=[nameSpace]).respond()
        page="""<ajax-response><response type="object" id="sessionHistory">"""
        page+= str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    history.exposed=True

    def register(self,nameinput,passinput,**kwargs):
        """
           Register users with internal history DB.
        """
        return self.index()
    register.exposed=True

    def getAppConfigs(self,dbsInst,appPath,ajax=1,**kwargs):
        """
            Application configs retriever
        """
        self.helperInit(dbsInst)
        rList=self.helper.getSoftwareReleases()
        try:
            givenRelease=appPath.split("/")[1]
            rList.remove(givenRelease)
        except:
            pass
        nameSpace={'selTag':"",'changeFunction':"",'name':"selRels",'iList':rList}
        t = templateSelect(searchList=[nameSpace]).respond()
        rels=str(t)
        nameSpace={
                   'appPath'   : appPath,
                   'dbsInst'   : dbsInst,
                   'host'      : self.dbsdd,
                   'configList': self.helper.listApplicationConfigs(appPath),
                   'releases'  : rList
                  }
        t = templateAppConfigs(searchList=[nameSpace]).respond()
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="object" id="appConfigs">"""
        else:
           page=self.genTopHTML()
           page+= self.genResultsHTML()
        page+= str(t)
        if int(ajax):
           page+="</response></ajax-response>"
        else:
           page+=self.genBottomHTML()
        if self.verbose==2:
           self.writeLog(page)
        return page
    getAppConfigs.exposed=True

    def compareAppConfigs(self,dbsInst,appConfig,iRel,oRel):
#        page = "diff %s between %s %s"%(appConfig,iRel,oRel) 
        c1 = self.helper.getConfigContentByName(dbsInst,appConfig,iRel)
        c2 = self.helper.getConfigContentByName(dbsInst,appConfig,oRel)
        page=self.genTopHTML()
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

    def showMessage(self,msg):
        page=self.genTopHTML()
        page+=msg
        page+=self.genBottomHTML()
        return page
    showMessage.exposed=True

    def checkUser(self,userId,**kwargs):
        """
           Check existence of user name in history DB
        """
        msg=""
        # check user name
        c = select([t_user.c.name],t_user.c.userid==userId).execute()
        r = c.fetchone()
        if r and r[0]==userId:
           msg=userId
           c = t_user.select(and_(t_user.c.userid==userId)).execute()
           r = c.fetchone()
#           if (not r) or (r and r[2]!=password):
#              msg='wrong password'
        else:
           try:
               res=t_user.insert().execute(userid=userId)
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

    def getDataDescription(self,dbsInst,processedDataset="",**kwargs):
        """ 
            Get data description.
        """
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
#        self.setContentType('xml')
#        page="""<ajax-response><response type="element" id="floatDataDescription">"""
        page=self.genTopHTML()
        description=""
        dList=self.helper.getDataDescription(processedDataset)
        # get formatted output of dataset details
        nameSpace={'dList' : dList, 'dataset':processedDataset }
        t = templateDatasetDetails(searchList=[nameSpace]).respond()
        description+=str(t)
        page+=description

        page+=self.genBottomHTML()
#        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getDataDescription.exposed=True

    def getTableTemplate(self,func,dbsInst,lfn,msg,ajax,**kwargs):
        self.htmlInit()
        tList,iList=func(dbsInst,lfn)
        p=content=""
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="element" id="floatDataDescription">"""
        else:
           page=self.genTopHTML()
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
           self.writeLog(page)
        return page

    def getLFN_Branches(self,dbsInst,lfn,ajax=0,**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Branches,dbsInst,lfn,'ROOT branches',ajax)
        return page
    getLFN_Branches.exposed=True

    def getLFN_Lumis(self,dbsInst,lfn,ajax=0,**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Lumis,dbsInst,lfn,'LFN lumis',ajax)
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

    def getMoreInfo(self,dbsInst,path,appPath,id,**kwargs):
        """ 
            Get data description.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="%s">"""%id
        nameSpace={'host':self.dbsdd,'dbsInst':dbsInst,'path':path,'appPath':appPath,'id':id}
        t = templateMoreInfo(searchList=[nameSpace]).respond()
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getMoreInfo.exposed=True

    def getRss(self):
        dbsList=[]
        if self.userMode:
           dbsList.append(DBSGLOBAL)
        else:
           dbsList=self.dbsList
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="element" id="rss_list">"""
        for dbs in dbsList:
            rssList=findRssFiles('rss/%s'%dbs)
            nameSpace={
                       'userMode'    : self.userMode,
                       'host'        : self.dbsdd,
                       'dbs'         : dbs,
                       'rssList'     : rssList
                      }
            t = templateRssList(searchList=[nameSpace]).respond()
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    getRss.exposed=True

    def getLucene(self,**kwargs):
        print "\n\ngetLucene",kwargs
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
#        param = {'method':'lookup','term':'block.subsys.NumberParameter129=111'}
#        data = self.lucene.sendPostMessage("/DBSLookupWeb/DBSLookup",param,debug=1)
        data = self.lucene.sendPostMessage("/DBSLookupWeb/DBSLookup",kwargs,debug=1)
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

    def makeSelect(self,iList,tag,name="",changeFunction="",selTag="",**kwargs):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="%s">"""%tag
        nameSpace={'iList':iList,'changeFunction':changeFunction,'selTag':selTag,'name':name}
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
        return self.makeSelect(tList,"divSectionTables_%s"%id,"sectionTables",changeFunction,"sectionTables_%s"%id)
    getSectionTables.exposed=True

    def getTableColumns(self,dbsInst,tableName,id,**kwargs):
        self.helperInit(dbsInst)
        tList = self.helper.getTableColumns(tableName)
        changeFunction=""
        return self.makeSelect(tList,"tableCols_%s"%id,"tableCols",changeFunction,"tableColumns_%s"%id)
    getTableColumns.exposed=True

    def getTableColumnsFromSection(self,dbsInst,section,id,**kwargs):
        """
           This is a fake, since AJAX calls are async, and I need to get
           columns for the first table in a section, I made explicit call.
        """
        self.helperInit(dbsInst)
        tList = self.sectionDict[section]
        tableName = tList[0]
        tList = self.helper.getTableColumns(tableName)
        changeFunction=""
        return self.makeSelect(tList,"tableCols_%s"%id,"tableCols",changeFunction,"tableColumns_%s"%id)
    getTableColumnsFromSection.exposed=True

    def finderExample(self):
        page = """
<object width='800' height='600'>
<param name='movie' value='images/DataDiscoveryFinder.swf'>
<embed src='images/DataDiscoveryFinder.swf' width='800' height='600'>
</embed>
</object>
"""
#        page = self.genTopHTML(intro=False)
#        page+= """Here you can find a few examples demonstrating usage of Finder on discovery page.

#        <p />
#        <img src="images/FinderExample1.jpg" alt="selectRun" />
#        Here is an example how to make a selection of different items in a finder.
#        In order to make your select please click on plus button and choose from appropriate menu.
#        <p />

#        <img src="images/FinderExample2.jpg" alt="selectRun" />
#        This example demostrate how to make a selection while applying some cuts.
#        """
#        page+= self.genBottomHTML()
        return page
    finderExample.exposed=True

    def finderSearch(self,**kwargs):
        print "\n\n####",kwargs
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results_finder">"""
        iDict={}
        iList=[]
        for key in kwargs.keys():
            if key=="_": continue
#            page+="Key='%s' parameters='%s'"%(key,kwargs[key])
            pList = kwargs[key].split("_newparam_")
            print kwargs[key],pList
            for item in pList:
                table,col,op,where=string.split(item,"__")
                addToDict(iDict,table,col)
                print "####",table,self.helper.dbManager.getDBTableName('localhost',table),col
                iList.append("%s.%s"%(self.helper.dbManager.getDBTableName('localhost',table),col))
        print "looking for",iDict
        
        # TODO: for now I test how query will work
#        res = self.helper.formSQLQuery(iDict)
#        page+=res
        query,oList = self.helper.queryMaker(iList)
        t = templateQueryOutput(searchList=[{'query':query,'iList':oList}]).respond()
        page+=str(t)
#        page+="<pre>%s\n%s</pre>"%(writer.getvalue(),repr(oList))
        
        page+="</response></ajax-response>"
        if self.verbose==2:
           self.writeLog(page)
        return page
    finderSearch.exposed=True

    def executeSQLQuery(self,query,**kwargs):
        q = string.lower(query.strip())
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results_finder">"""
        if q.find("select")==-1:
           page+="You are attempt execute non select query, it's forbidden"
        else:
           # here we got back a list whose first entry is column names
           iList = self.helper.executeSQLQuery(query)
           if  type(iList) is not types.ListType:
               page+=iList
           else:
               nameSpace={'branch':iList[1:]}
               t = templateTableBody(searchList=[nameSpace]).respond()
               content=str(t)
               nameSpace={'header':iList[0],'content':content}
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

    def cliHandler(self,input):
        data=urllib.unquote(input)
        if self.verbose==1:
           self.writeLog(data)
        try:
            selList = []
            conDict = {}
            class Handler (xml.sax.handler.ContentHandler):
                def startElement(self, name, attrs):
                    if name=='select':
                       selList.append(attrs['column'])
                    if name=='list':
                       selList.append(1)
            xml.sax.parseString (data, Handler ())
        except:
            printExcept()
#        print selList,conDict,listTables
        if not len(selList):
           return """<?xml version="1.0" encoding="utf-8"?><ddResponse></ddResponse>"""
        if len(selList)==1 and selList[0]==1:
           oList=self.helper.getAllTableColumns()
           res="""<?xml version="1.0" encoding="utf-8"?><ddResponse>"""
           for item in oList:
                res+="""<row><column name='Table.colName' value='%s' /></row>"""%item
           res+="""</ddResponse>"""
           return res
        # HACK, TODO: when Drew will provide a single query replace this hack
        if len(selList)==1:
           table,col=string.split(selList[0],".")
           query="SELECT DISTINCT %s FROM %s"%(col,table)
           oList=self.helper.executeSQLQuery(query)
        else:
           query,oList = self.helper.queryMaker(selList)
#        print query,oList
        res="""<?xml version="1.0" encoding="utf-8"?><ddResponse>"""
        for iList in oList[1:]: # here first element in a list is column names
            res+="<row>"
            for idx in xrange(0,len(iList)):
                res+="""<column name='%s' value='%s' />"""%(oList[0][idx],iList[idx])
            res+="</row>"
        res+="""</ddResponse>"""
        return res
    cliHandler.exposed=True
#
# main
#
if __name__ == "__main__":
    optManager  = DDOptions.DDOptionParser('DDServer')
    (opts,args) = optManager.getOpt()

    # load DBS history tables module
    try:
        from   DDTables  import *
    except:
        if opts.verbose:
           printExcept()
        print "WARNING! Cannot load DDTables, your persistent history will be turned off"
        pass
    dbsManager = DDServer(opts.verbose,opts.profile)
    if opts.quiet:
       dbsManager.setQuiet()
    if  int(string.split(cherrypy.__version__,".")[0])==3:
        cherrypy.config.update("CherryServer3.conf")
        conf = {'/'         : {'tools.staticdir.root': os.getcwd()},
                '/images'   : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'images'},
                '/rss'      : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'rss'},
                '/css'      : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'css'},
                '/js'       : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'js'},
                '/WEBTOOLS' : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'WEBTOOLS'},
                '/yui'      : {'tools.staticdir.on': True, 'tools.staticdir.dir': 'yui'},
               }


    else:
        cherrypy.root = dbsManager
        cherrypy.config.update(file="CherryServer.conf")
        cherrypy.config.update({'global': {'static_filter.root' : os.getcwd() }})

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
           cherrypy.quickstart(dbsManager, '/', config=conf)
       else:
           cherrypy.server.start()
