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
import os, string, logging, types, time, socket, socket, urlparse, random, urllib

# Cheetah template modules
from   Cheetah.Template import Template

# CherryPy server modules
import cherrypy 

# DBS  modules
import CheetahDBSTemplate
from   DBSHelper import *
from   DBSUtil   import *
from   DDConfig  import *
 
class DBSDataDiscoveryServer(DBSLogger): 
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
           DBSDataDiscoveryServer constructor. It takes only one optional argument.
           Initialize L{DBSHelper} with default DBS instance "MCGlobal/Writer".
           Keep cache of DBS instances and current selection of (dbs,site,app,primD,tier).
           @type  verbose: boolean or integer 
           @param verbose: verbosity level 
           @rtype : none
           @return: none 
        """
        DBSLogger.__init__(self,"DBSDataDiscoveryServer",verbose)
        self.ddConfig  = DBSDDConfig()
        self.dbs  = DBSGLOBAL
        self.site = ""
        self.app  = ""
        self.primD= ""
        self.tier = ""
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
        print "DBSDataDiscoveryServer '%s'"%self.dbsdd
        self.formDict   = {
                           'menuForm': ("","","","","",""), # (msg,dbsInst,site,app,primD,tier)
                           'siteForm': ("",""), # (dbsInst,site)
                           'searchForm': ("",) # (dbsInst,)
                          }

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
        if type=="xml":
           cherrypy.response.headerMap['Content-Type'] = "text/xml"
        else:
           cherrypy.response.headerMap['Content-Type'] = "text/html"

    def sendErrorReport(self,iMsg=""):
        """
           Send a complete report with provided msg. Capture internals of
           L{DBSDataDiscoveryServer} and L{DBSHelper}.
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
        msg+="DBSDataDiscoveryServer:\n"
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
        t = Template(CheetahDBSTemplate.templateTop, searchList=[nameSpace])
        page = str(t)
        if intro:
           page+=CheetahDBSTemplate.templateIntro
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
        t = Template(CheetahDBSTemplate.templateResults, searchList=[nameSpace])
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
        t = Template(CheetahDBSTemplate.templateBottom, searchList=[nameSpace])
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
        t = Template(CheetahDBSTemplate.templateERROR, searchList=[nameSpace])
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
            t = Template(CheetahDBSTemplate.templateVisiblePanel, searchList=[nameSpace])
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
            t = Template(CheetahDBSTemplate.templateHiddenPanel, searchList=[nameSpace])
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
        t = Template(CheetahDBSTemplate.templateGlossary, searchList=[nameSpace])
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
           
        nameSpace = {
                     'host'         : self.dbsdd,
                     'userMode'     : self.userMode,
                     'navigatorForm': menuForm,
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
                     'tip'          : tip()
                    }
        t = Template(CheetahDBSTemplate.templateFrontPage, searchList=[nameSpace])
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
            t = Template(CheetahDBSTemplate.templateFrontPage, searchList=[nameSpace])
            return str(t)
        except:
            t=self.errorReport("Fail in genPanel function")
            pass
            return str(t)

    def init(self):
        """
           Construct Init service page, which includes L{genMenuForm}.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
            page = self.genTopHTML(intro=False)
            page+= self.genVisiblePanel('Navigator')
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

    def summary(self,**kwargs):
        """
           Generates AJAX response to get summary information about DBS. It scans all DBS instances
           and retrieve all information from there. The information has been cached for 1 day.
           All the work is done in L{getSummary} method.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page=""
        try:
            page="""<ajax-response><response type="element" id="summary">"""
            if not self.sumPage or (time.time()-self.timerForSummary)>(24*60*60):
               self.sumPage=self.getSummary()
               page+=self.sumPage
               self.timerForSummary=time.time()
            else:
               page+=self.sumPage
            page+="</response></ajax-response>"
            return page
        except:
            t=self.errorReport("Fail in summary function")
            pass
            return str(t)
    summary.exposed = True
    
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
        
    def searchForm(self,firstDBS=""):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """
#        t = Template(CheetahDBSTemplate.templateKeywords, searchList=[])
#        help=str(t)

        if self.ddConfig.iface()=='cgi': searchFunc="javascript:ajaxSearch()"
        else: searchFunc="javascript:ResetAllResults();ajaxKeywordSearch()"

        # FIXME, TODO: firstDBS should be passed here
        if not firstDBS: firstDBS=DBSGLOBAL
        keyList=['run','block','lfn','tier','release','primDS','procDS','lumiSection']
        keyList.sort()
        nameSpace = {
                     'firstDBS' : firstDBS,
                     'dbsList'  : self.dbsList,
                     'keyList'  : keyList,
                     'searchFunction': searchFunc
                    }
        t = Template(CheetahDBSTemplate.templateSearchTable, searchList=[nameSpace])
        page = str(t)
        self.firstSearch=0
        return page
        
    def search(self,dbsInst,keywords="",**kwargs):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        if  self.helper.iface=='cgi':
            page="""<ajax-response><response type="object" id="results">"""
        else:
            page="""<ajax-response><response type="object" id="results_kw">"""
        endAjaxMsg="</response></ajax-response>"
        self.firstSearch=0
        if keywords and not validator(keywords):
           page+="""
<hr class="dbs" />
<div class="show_red_bold">Your search expression <p><em>%s</em></p> is not valid</div>
"""%keywords
           page+=endAjaxMsg
           return page
        oList= []
        # parse keywords and search for DBSINST
        dbsList=appList=tierList=siteList=primList=[]
        if  string.lower(dbsInst)=='all':
            if self.userMode:
               dbsList.append(DBSGLOBAL)
            else:
               dbsList=self.dbsList
        else:
            if not self.dbsList.count(dbsInst):
               msg="Wrong DBS isntance, '%s'\nList of known DBS instances:\n"%dbsInst
               msg+=formattingListPrint(self.dbsList)
               page+=msg+endAjaxMsg
               return page
            dbsList=[dbsInst]
        if  self.helper.iface=='cgi':
            for dbsInst in dbsList:
                self.helperInst(dbsInst)
                oList+=self.searchHelper(keywords)
            nameSpace = {
                         'firstSearch': self.firstSearch, 
                         'oList'      : oList, 
                         'userMode'   : self.userMode,
                         'keywords'   : keywords
                        }
            t = Template(CheetahDBSTemplate.templateDataFromSelection, searchList=[nameSpace])
            page+= str(t)
        else:
            for dbsInst in dbsList:
                self.helperInit(dbsInst)
                oList=self.searchHelper(keywords)
                nameSpace = {
                             'firstSearch': self.firstSearch, 
                             'oList'      : oList, 
                             'userMode'   : self.userMode,
                             'keywords'   : keywords,
                             'dbs'        : dbsInst
                            }
                t = Template(CheetahDBSTemplate.templateDataFromKeywordSelection, searchList=[nameSpace])
                page+= str(t)
        page+=endAjaxMsg
#        if self.verbose:
        if 1:
           print page
        return page
    search.exposed = True

    def getSummary(self):
        """
           Constructs summary page.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        page = """
<table id="table_summary" cellspacing="2" cellpadding="5" border="1">
<tr>
<th class="box_gray">DBS instance</th>
<th class="box_gray"># files</th>
<th class="box_gray">size</th>
</tr>
        """
        if self.userMode:
           dbsList=[DBSGLOBAL]
        else:
           dbsList = self.dbsList
        for dbsInst in dbsList:
            sumDict   = self.helper.getDBSSummary(dbsInst) 
            nameSpace = {'dbsInst':dbsInst,'sumDict':sumDict}
            t = Template(CheetahDBSTemplate.templateSummary, searchList=[nameSpace])
            page+=str(t)
        page+="</table>"
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
           
    def getDBSDict_orig(self):
        """
            Read dbsDict.all, dbsDict.all files in local directory which contains
            a JS dictionary for pull down menu.
        """
        f = open('dbsDict.global','r')
        self.dbsUser = string.strip(f.read())
        f.close()
        f = open('dbsDict.all','r')
        self.dbsDict = string.strip(f.read())
        f.close()
        if self.userMode:
           return self.dbsUser
        else:
           return self.dbsDict
           
    def getDBSDict_v2(self):
        if (time.time()-self.timer)>1*60*60: # use 2h interval to update menu
           print "expert reset"
           self.timer=time.time()
           self.dbsDict="" # next call will update self.dbsDict
           self.dbsUser=""
           
        if self.dbsDict:
           dbsDict = self.dbsDict
           dbsUser = self.dbsUser
           print "from cache"
        else:
           dbsDict=self.helper.initJSDict(False)
           dbsUser=self.helper.initJSDict(True)
           self.dbsDict=dbsDict
           self.dbsUser=dbsUser
           print "new dict"
        if self.userMode:
           return self.dbsUser
        else:
           return self.dbsDict
        
    def genNavigatorMenuDict(self,dbsInst,**kwargs):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="navigatorDict">"""
        endAjaxMsg="</response></ajax-response>"
        try:
#            page+="navDict="+self.getDBSDict()
            page+="navDict="+self.getDBSDict(dbsInst)
        except:
            t=self.errorReport("Fail in genNavigatorMenuDict function")
            page+=str(t)
            pass
        page+=endAjaxMsg
        if self.verbose:
#        if 1:
           print page
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
        t = Template(CheetahDBSTemplate.templateJS, searchList=[nameSpace])
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
                     'dbsDesc'  : CheetahDBSTemplate.DBSInstanceDesc,
                     'siteDesc' : CheetahDBSTemplate.TierSiteDesc,
                     'primDesc' : CheetahDBSTemplate.ApplicationDesc,
                     'appDesc'  : CheetahDBSTemplate.PrimaryDatasetDesc,
                     'tierDesc' : CheetahDBSTemplate.DataTierDesc,
                    }
        t = Template(CheetahDBSTemplate.templateJSForm, searchList=[nameSpace])
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
        t = Template(CheetahDBSTemplate.templateSnapshot, searchList=[nameSpace])
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
                    site="All"
                    if not self.userMode:
                       page+="""<div class="sectionhead_tight">%s instance</div>"""%dbsInst
                    page+= self.getDataHelper(dbsInst,site,app,primD,tier)
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
        if self.verbose:
#        if 1:
           print page
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
                if not self.userMode:
                   page+="""<div class="dbs-h1">%s instance</div>"""%dbsInst
                page+= self.getDataHelper(dbsInst,site,app,primD,tier)
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
        t = Template(CheetahDBSTemplate.templateProcDatasets, searchList=[nameSpace])
        return str(t)
    
    def getURL(self,dbsInst,site="All",app="*",primD="*",tier="*",proc="*",hist=""): 
        # add URL link to the page
        siteHTML=site
        if site=="*":
           siteHTML='All'
        tierHTML=tier
        if tier=="*":
           tierHTML='All'
        histHTML=urllib.quote(hist+"""<hr class="dbs" />""")
        url="""%s/getData?dbsInst=%s&amp;site=%s&amp;app=%s&amp;primD=%s&amp;tier=%s&amp;proc=%s&amp;hist=%s&amp;_idx=0&amp;ajax=0"""%(self.dbsdd,dbsInst,siteHTML,app,primD,tierHTML,proc,histHTML)
        page="""<hr class="dbs" /><p>For a bookmark to this data, use</p><a href="%s">%s</a>"""%(url,splitString(url,80,'\n'))
        return page

    def getDatasetList(self,app="*",prim="*",tier="*",proc="*"):
        """
           Call different APIs for given list of app/prim/tier/proc. Return a list of processed
           datasets.
        """
        dList=[]
        if (proc and proc!="*") and app=="*" and prim=="*":
           dList=string.split(proc,",")
        elif proc=="*" and (app and app!="*") and (prim and prim!="*"):
           dList = self.helper.getDatasetsFromApp(app,prim,tier)
        elif proc=="*" and app=="*" and (prim and prim!="*"):
           dList=self.helper.getProcessedDatasets("/"+prim+"/*/*",app=0)
        elif proc=="*" and (app and app!="*") and prim=="*":
           dList=self.helper.getProcessedDatasets(app,app=1)
        return dList

    def getDataHelper(self,dbsInst,site="All",app="*",primD="*",tier="*",proc="*",hist="",_idx=0,ajax=1,**kwargs): 
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
        if string.lower(tier)=="all": tier="*"
        if string.lower(site)=="all": site="*"
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
        datasetsList = self.getDatasetList(app=appPath,prim=primD,tier=tier,proc=proc)
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
        t = Template(CheetahDBSTemplate.templateSnapshot, searchList=[nameSpace])
        snapshot=str(t)
        # the view and process page should be generated once
        if  int(_idx)==0:
            nameSpace = {'leftBar' : leftBar}
            t = Template(CheetahDBSTemplate.templateLeftBar, searchList=[nameSpace])
            page+=str(t)


#        jsPage="<!-- parents\n"
        # begin of response tag

#        page+="""<span id="results_response_%s" class="show_inline">"""%_idx
#        page+="""<script type="text/javascript">ClearTag('cell_waiting');ClearCells(%s,%s)</script>"""%(_idx,nDatasets)

        page+="""<script type="text/javascript">ShowPageResults()</script>"""
        self.dbsTime=(t2-t1)
        for idx in xrange(0,nDatasets):
            ttt1 = time.time()
            dataset = datasetsList[idx]
            id+=1
            self.writeLog(dataset)

            # process only RES_PER_PAGE datasets within given (_idx) index range
            if not (_idx*RES_PER_PAGE<=idx and idx<(_idx*RES_PER_PAGE+RES_PER_PAGE)): continue

            siteList, blockDict, totEvt, totFiles, totSize = self.helper.getData(dataset,appPath,site)
            self.dbsTime+=self.helper.dbsTime
            self.dlsTime+=self.helper.dlsTime
            # new stuff which do not show repeating datasets
#            jsPage+="""registerTreeView();ajaxAddTreeElement('root','%s');"""%dataset
            p = self.dataToHTML(dbsInst,dataset,siteList,blockDict,totEvt,totFiles,totSize,id,snapshot)
            if oldTotEvt==totEvt and oldTotFiles==totFiles and oldDataset:
               bList.append((dataset,totEvt))
            else:
               if len(bList): 
                  page+=self.blockListToHTML(dbsInst,bList)
                  bList=[]
               bList.append((dataset,totEvt))
               page+=prevPage
            oldTotEvt=totEvt
            oldTotFiles=totFiles
            oldTotSize=totSize
            oldDataset=dataset
            prevPage = p

#            print "##### %s %s sec"%(dataset,(time.time()-ttt1))
        page+=self.blockListToHTML(dbsInst,bList)
        page+=prevPage # end of new stuff

#        page+=jsPage+"\n-->\n"
        # generate URL link
        page+=self.getURL(dbsInst,site,app,primD,tier,proc,hist)
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
                         'app'     : app,
                         'prim'    : primD,
                         'tier'    : tier,
                         'proc'    : proc,
                         'res_page': RES_PER_PAGE
                        }
            t = Template(CheetahDBSTemplate.templateNextBar, searchList=[nameSpace])
            page+=str(t)

        return page
    getDataHelper.exposed=True

    def blockListToHTML(self,dbsInst,bList):
        if not len(bList): return ""
        nameSpace = {'host': self.dbsdd, 'dbsInst': dbsInst, 'blockList' : bList}
        t = Template(CheetahDBSTemplate.templateBlockList, searchList=[nameSpace])
        page=str(t)
        return page
        
    def crabCfg(self,dataset,totEvt,**kwargs):
        nameSpace = {
                     'dataset'  : dataset,
                     'totEvt'   : totEvt
                    }
        t = Template(CheetahDBSTemplate.templateCRAB, searchList=[nameSpace])
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
        t = Template(CheetahDBSTemplate.templateTime, searchList=[nameSpace])
        page=str(t)
        return page

    def getData(self,dbsInst,site="All",app="*",primD="*",tier="*",proc="*",hist="",_idx=0,ajax=1,**kwargs): 
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
                if string.lower(tier)=="all": tier="*"
                if string.lower(site)=="all": site="*"
                self.helperInit(dbsInst)
                self.htmlInit()
                msg="dbsInst='%s', site='%s', app='%s', primD='%s', tier='%s'"%(dbsInst,site,app,primD,tier)
                self.writeLog(msg)
                msg=""
                self.formDict['menuForm']=(msg,dbsInst,site,app,primD,tier)
                page+= self.getDataHelper(dbsInst,site,app,primD,tier,proc,hist,_idx,ajax)
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
           sumData="ajaxGetData('%s','%s','%s','%s','%s','%s')"%(dbsInst,site,app,primD,tier,proc)
           dbsData="ajaxGetDbsData('%s','%s','%s','%s','%s','%s')"%(dbsInst,site,app,primD,tier,proc)
           runData="ajaxGetRuns('%s','%s','%s','%s','%s','%s')"%(dbsInst,site,app,primD,tier,proc)
           parents="ajaxGenParentsGraph('%s','%s','%s','%s','%s','%s')"%(dbsInst,site,app,primD,tier,proc)
           configs="ajaxGenAppConfigs('%s','%s','%s','%s','%s','%s')"%(dbsInst,site,app,primD,tier,proc)
           
           page+= """<script type="text/javascript">ajaxInit('%s');showResultsMenu();%s;%s;%s;%s;%s;showResMenu('results')</script>"""%(dbsInst,sumData,dbsData,runData,parents,configs)
           page+=self.genBottomHTML()
        if self.verbose:
#        if 1:
           print page
        return page
    getData.exposed = True 

    def getDbsData(self,dbsInst,site="All",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,**kwargs): 
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

            dList=self.getDatasetList(app=app,prim=primD,tier=tier,proc=proc)
            nDatasets=len(dList)
            className="hide"
#            if not _idx and nDatasets<(_idx+1)*RES_PER_PAGE:
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
                t = Template(CheetahDBSTemplate.templateDbsInfo, searchList=[nameSpace])
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
        if self.verbose:
#        if 1:
           print page
        return page
    getDbsData.exposed = True 

    def getRuns(self,dbsInst,site="All",app="*",primD="*",tier="*",proc="*",_idx=0,ajax=1,**kwargs): 
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

            dList=self.getDatasetList(app=app,prim=primD,tier=tier,proc=proc)
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
                t = Template(CheetahDBSTemplate.templateRunsInfo, searchList=[nameSpace])
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
        if self.verbose:
#        if 1:
           print page
        return page
    getRuns.exposed = True 

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
    
    def getLFNsForSite(self,dbsInst,site):
        """
           Generates a list of LFNs for given site
        """
        try:
            self.htmlInit()
            page ="""<html><body><pre>\n"""
            try:
                bList=self.helper.getBlocksFromSite(site)
            except:
                printExcept()
                page+="No LFNs found for site '%s'\n"%site
                pass
            for blockName in bList:
                try:
                    lfnList = self.helper.getLFNs(dbsInst,blockName,"")
                    for item in lfnList:
                        lfn=item[0]
                        page+="%s\n"%lfn
                except:
                    printExcept()
                    page+="No LFNs found int DBS for block='%s'\n"%blockName
                    pass
            page+="\n</pre></body></html>"
            return page
        except:
            t=self.errorReport("Fail in getLFNsForSite function")
            pass
            return str(t)
    getLFNsForSite.exposed=True
    
    def getBlocksForSite(self,site):
        """
           Generates list of file blocks for given site
        """
        try:
            self.htmlInit()
            page ="""<html><body><pre>\n"""
#            for blockName in self.siteDict[site]:
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
        t = Template(CheetahDBSTemplate.templateLFN, searchList=[nameSpace])
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
        t = Template(CheetahDBSTemplate.templateLFB, searchList=[nameSpace])
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
            t = Template(CheetahDBSTemplate.templateDbsInfoTableEntry, searchList=[nameSpace])
            # query DBS and get more info about blocks
            page+=str(t)
        page+= str(t)
        page+="</table>"
        page+="</response></ajax-response>"
        if self.verbose:
           print page
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
        t = Template(CheetahDBSTemplate.templateFileBlocksFromSite, searchList=[nameSpace])
        page+= str(t)
#        page+="</response></ajax-response>"
#        if self.verbose:
#           print page
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
            t = Template(CheetahDBSTemplate.templateFileBlocksFromSite, searchList=[nameSpace])
        except:
            t=self.errorReport("Fail in getBlocksFromSite function")
            pass
        page+= str(t)
        page+="</response></ajax-response>"
        if self.verbose:
           print page
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
        if self.verbose:
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
        self.helperInit(dbsInst)
        dList = self.helper.getPrimaryDatasets(datasetPath="*",html=1)
        nameSpace = {
                     'msg'     : "%s: primary datasets"%dbsInst,
                     'dbsInst' : dbsInst,
                     'dList'   : dList
                    }
        t = Template(CheetahDBSTemplate.templatePrintList, searchList=[nameSpace])
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
        if self.verbose:
#        if 1:
           print page
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
        t = Template(CheetahDBSTemplate.templatePrintList, searchList=[nameSpace])
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
        if self.verbose:
#        if 1:
           print page
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
        t = Template(CheetahDBSTemplate.templatePrintList, searchList=[nameSpace])
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
        if self.verbose:
           print page
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
        t = Template(CheetahDBSTemplate.templateSelectList, searchList=[nameSpace])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
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
        dList=['SIM','DIGI','RECO','FIXME']
        nameSpace = {'name':'release','iList': dList}
        t = Template(CheetahDBSTemplate.templateSelectList, searchList=[nameSpace])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
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
        dList = self.helper.getPrimaryDatasets(datasetPath="*",html=0)
        nameSpace = {'name':'release','iList': dList}
        t = Template(CheetahDBSTemplate.templateSelectList, searchList=[nameSpace])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
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
        dList = self.helper.getSoftwareReleases()
        nameSpace = {'name':'release','iList': dList}
        t = Template(CheetahDBSTemplate.templateSelectList, searchList=[nameSpace])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
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
        t = Template(CheetahDBSTemplate.templatePrintList, searchList=[nameSpace])
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
            siteList, blockDict, totEvt, totFiles, totSize = self.helper.getData(dataset,None,"*")
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
            t = Template(CheetahDBSTemplate.templateProvenance, searchList=[nameSpace])
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
        if self.verbose:
           print page
        return page
    getDatasetProvenance.exposed=True

    def getProvenanceForAllDatasets(self,dbsInst,site="All",app="*",primD="*",tier="*",proc="*",_idx=0,**kwargs): 
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
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="parents">"""
        if string.lower(tier)=="all": tier="*"
        if string.lower(site)=="all": site="*"
        self.helperInit(dbsInst)
#        self.dbs  = dbsInst
#        self.site = site
#        self.app  = app
#        self.primD= primD
#        self.tier = tier
#        primaryDataset=primD
#        dataTier = tier
        dList = self.helper.getDatasetsFromApp(app,primD,tier)
        nDatasets=len(dList)
        className="hide"
#        if not _idx and nDatasets<(_idx+1)*RES_PER_PAGE:
        if int(_idx)==0:
           className="show_inline"
        page+="""<div id="parents_response_%s" class="%s">"""%(_idx,className)
        for idx in xrange(0,nDatasets):
            dataset = dList[idx]

            # process only RES_PER_PAGE datasets within given (_idx) index range
            if not (_idx*RES_PER_PAGE<=idx and idx<(_idx*RES_PER_PAGE+RES_PER_PAGE)): continue

            page+=self.getDatasetProvenanceHelper(dataset)
        page+="</div>"
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
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
        if self.verbose:
#        if 1:
           print page
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
        t = Template(CheetahDBSTemplate.templateSiteForm, searchList=[nameSpace])
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
        if sts != 0:
            print "mail exit status", sts
        page = self.genTopHTML()
        page+= """<p class="sectionhead_tight">Your feedback is greatly appreciated and has been send to maintainer.</p>"""
        page+= self.genVisiblePanel('Resources')
        page+= self.genBottomHTML()
        return page
    sendFeedback.exposed=True

    def storeHistory(self,actionString,userName='guest',password=''):
        # update DB history
        # select cmdid from given history string
        c = select([t_command.c.id],t_command.c.command==actionString).execute()
        r = c.fetchone()
        if r and r[0]:
           cid = r[0]
        else:
           res=t_command.insert().execute(command=actionString)
           cid=res.last_inserted_ids()[0]
        # try to insert name/password, if fail get them from DB
        uid=0
        try:
           res=t_user.insert().execute(name=userName,password=password)
           uid=res.last_inserted_ids()[0]
        except:
           c = t_user.select(and_(t_user.c.name==userName,t_user.c.password==password)).execute()
           r = c.fetchone()
           if r and r[0]:
              uid = r[0]
        if not uid:
           raise "Fail to find uid in DBS DD history for %s"%(userName,)
        # insert into t_history date/userid/cmdid
        iDate=time.strftime("%Y-%m-%d",time.localtime())
        iTime=time.strftime("%H:%M:%S",time.localtime())
        t_history.insert().execute(userid=uid,cmdid=cid,date=iDate,time=iTime)

    def historySearch(self,iYear,iMonth,oYear,oMonth,userName='guest',password='',**kwargs):
        cList=[]
        try:
            iDate='%s-%s-%s'%(iYear,monthId(iMonth),'01')
            oDate='%s-%s-%s'%(oYear,monthId(oMonth),'31')
            c = select([t_history.c.date,t_history.c.time,t_command.c.command],
                        and_(
                             t_history.c.userid==t_user.c.id,
                             t_history.c.cmdid==t_command.c.id,
                             t_history.c.date>=iDate,t_history.c.date<=oDate,
                             t_user.c.name==userName
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
            t = Template(CheetahDBSTemplate.templateHistory, searchList=[nameSpace])
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
        return page
    historySearch.exposed=True

    def getHistory(self,userName='guest',password='',iLimit=100,**kwargs):
        cList=[]
        try:
            c = select([t_history.c.date,t_history.c.time,t_command.c.command],
                        and_(
                             t_history.c.userid==t_user.c.id,
                             t_history.c.cmdid==t_command.c.id,
                             t_user.c.name==userName
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
            t = Template(CheetahDBSTemplate.templateHistory, searchList=[nameSpace])
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
        return page
    getHistory.exposed=True

    def history(self,actionString,userName='guest',password='',**kwargs):
        """
            History updater
        """
        try:
            self.storeHistory(actionString,userName,password)
        except:
            if not self.quiet:
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
        t = Template(CheetahDBSTemplate.templateHistory, searchList=[nameSpace])
        page="""<ajax-response><response type="object" id="sessionHistory">"""
        page+= str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
        return page
    history.exposed=True

    def register(self,nameinput,passinput,**kwargs):
        """
           Register users with internal history DB.
        """
        return self.index()
    register.exposed=True

    def getAppConfigs(self,appPath,**kwargs):
        """
            Application configs retriever
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        nameSpace={
                   'appPath'   : appPath,
                   'configList': self.helper.listApplicationConfigs(appPath)
                  }
        t = Template(CheetahDBSTemplate.templateAppConfigs, searchList=[nameSpace])
        page="""<ajax-response><response type="object" id="appConfigs">"""
        page+= str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
        return page
    getAppConfigs.exposed=True

    def showMessage(self,msg):
        page=self.genTopHTML()
        page+=msg
        page+=self.genBottomHTML()
        return page
    showMessage.exposed=True

    def checkUser(self,userName,password,**kwargs):
        """
           Check existence of user name in history DB
        """
        msg=""
        # check user name
        c = select([t_user.c.name],t_user.c.name==userName).execute()
        r = c.fetchone()
        if r and r[0]==userName:
           msg=userName
           c = t_user.select(and_(t_user.c.name==userName,t_user.c.password==password)).execute()
           r = c.fetchone()
           if (not r) or (r and r[2]!=password):
              msg='wrong password'
        else:
           try:
               res=t_user.insert().execute(name=userName,password=password)
               msg=userName
           except:
               msg="fail";
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="historyUserName">"""
        page+= msg
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
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
        if self.verbose:
#        if 1:
           print page
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
        if self.verbose:
#        if 1:
           print page
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
        if self.verbose:
#        if 1:
           print page
        return page
    selectDataTiers.exposed=True

    def getDataDescription(self,dbsInst,primaryDataset="",processedDataset="",**kwargs):
        """ 
            Get data description.
        """
        self.helperInit(dbsInst)
        # AJAX wants response as "text/xml" type
#        self.setContentType('xml')
#        page="""<ajax-response><response type="element" id="floatDataDescription">"""
        page=self.genTopHTML()
        description=""
        dList=""
        if processedDataset:
           description=processedDataset
           dList=self.helper.getDataDescription(processedDataset=processedDataset)
        if primaryDataset:
           description=primaryDataset
           dList=self.helper.getDataDescription(primaryDataset=primaryDataset)
        # get formatted output of dataset details
        nameSpace={'dList' : dList }
        t = Template(CheetahDBSTemplate.templateDatasetDetails, searchList=[nameSpace])
        description+=str(t)

        description+="<p>Once available data description will be placed here</p>"

        nameSpace={
                   'description' : description
                  }
        t = Template(CheetahDBSTemplate.templateDescription, searchList=[nameSpace])
        page+=str(t)
        page+=self.genBottomHTML()
#        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
        return page
    getDataDescription.exposed=True

    def getTableTemplate(self,func,dbsInst,lfn,msg,ajax,**kwargs):
        self.htmlInit()
        iList=func(dbsInst,lfn)
        p=content=""
        if int(ajax):
           # AJAX wants response as "text/xml" type
           self.setContentType('xml')
           page="""<ajax-response><response type="element" id="floatDataDescription">"""
        else:
           page=self.genTopHTML()
        if  len(iList):
            try:
                for dict in iList:
                    nameSpace={'branch':dict.values()}
                    t = Template(CheetahDBSTemplate.templateTableBody, searchList=[nameSpace])
                    content+=str(t)
                nameSpace={'header':iList[0].keys(),'content':content}
                t = Template(CheetahDBSTemplate.templateTable, searchList=[nameSpace])
                p=str(t)
            except:
                printExcept()
                p="No information about '%s' found for\nDBS='%s'\nLFN='%s'"%(msg,dbsInst,lfn)
        else:
           p="No information about '%s' found for\nDBS='%s'\nLFN='%s'"%(msg,dbsInst,lfn)
        if int(ajax):
           nameSpace={'title':msg,'description':p,'className':'float_help_box'}
           t = Template(CheetahDBSTemplate.templateFloatBox, searchList=[nameSpace])
           page+=str(t)+"</response></ajax-response>"
        else:
           page+=p
           page+=self.genBottomHTML()
        if self.verbose:
#        if 1:
           print page
        return page

    def getLFN_Branches(self,dbsInst,lfn,ajax=0,**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Branches,dbsInst,lfn,'ROOT branches',ajax)
        return page
    getLFN_Branches.exposed=True

    def getLFN_Lumis(self,dbsInst,lfn,ajax=0,**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Lumis,dbsInst,lfn,'LFN lumis',ajax)
        return page
    getLFN_Lumis.exposed=True

    def getLFN_Algos(self,dbsInst,lfn,ajax=0,**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Algos,dbsInst,lfn,'LFN algorithms',ajax)
        return page
    getLFN_Algos.exposed=True

    def getLFN_Tiers(self,dbsInst,lfn,ajax=0,**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Tiers,dbsInst,lfn,'LFN tiers',ajax)
        return page
    getLFN_Tiers.exposed=True

    def getLFN_Parents(self,dbsInst,lfn,ajax=0,**kwargs):
        page=self.getTableTemplate(self.helper.getLFN_Parents,dbsInst,lfn,'LFN parents',ajax)
        return page
    getLFN_Parents.exposed=True

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
        t = Template(CheetahDBSTemplate.templateFloatBox, searchList=[nameSpace])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
        return page
    getFloatBox.exposed=True

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
            t = Template(CheetahDBSTemplate.templateRssList, searchList=[nameSpace])
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
#        if 1:
           print page
        return page
    getRss.exposed=True

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
        p="""<ajax-response><response type="object" id="treeViewInfo">
%s</response></ajax-response>"""%self.genTreeElement(parent,node)
#        if self.verbose:
        if 1:
           print p
        return p
    addTreeElement.exposed=True

    def dummy(self,arr=[],**kwargs):
#        print "\n\n#### dummy",arr,type(arr)
        iList=string.split(arr,",")
        print iList
        print "\n\n"
#        page=self.genTopHTML()
        cherrypy.response.headerMap['Content-Type'] = "text/xml"
        page="""<ajax-response><response type="object" id="dummy">"""
        nameSpace = {
                     'firstSearch': 0 
                    }
        t = Template(CheetahDBSTemplate.templateDummy, searchList=[nameSpace])
        page+= str(t)
        page+="</response></ajax-response>"
#        page+=self.genBottomHTML()
#        if self.verbose:
        if 1:
           print page
        return page
    dummy.exposed=True
#
# main
#
if __name__ == "__main__":
    optManager  = DBSOptions.DBSOptionParser()
    (opts,args) = optManager.getOpt()

    # load DBS history tables module
    try:
        from   DDTables  import *
    except:
        if not opts.quiet:
           printExcept()
        print "WARNING! Cannot load DDTables, your persistent history will be turned off"
        pass
    dbsManager = DBSDataDiscoveryServer(opts.verbose,opts.profile)
    if opts.quiet:
       dbsManager.setQuiet()
    cherrypy.root = dbsManager
    cherrypy.config.update(file="CherryServer.conf")
    cherrypy.config.update({'global': {'static_filter.root' : os.getcwd() }})
    if opts.profile:
       import hotshot                   # Python profiler
       import hotshot.stats             # profiler statistics
       print "Start DBS/DLS discovery server in profile mode"
       profiler = hotshot.Profile("profile.dat")
       profiler.run("cherrypy.server.start()")
       profiler.close()
       stats = hotshot.stats.load("profile.dat")
       stats.sort_stats('time', 'calls')
       stats.print_stats()
    else:       
       cherrypy.server.start()
