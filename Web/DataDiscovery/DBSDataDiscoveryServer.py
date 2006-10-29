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
import os, string, logging, types, time, socket, socket

# Cheetah template modules
from   Cheetah.Template import Template

# CherryPy server modules
import cherrypy 

# DBS  modules
import CheetahDBSTemplate
from   DBSHelper import *
from   DBSUtil   import *
 
SENDMAIL = "/usr/sbin/sendmail" # sendmail location

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
    def __init__(self,verbose=0):
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
        self.dbs  = DBSGLOBAL
        self.site = ""
        self.app  = ""
        self.primD= ""
        self.tier = ""
        self.helper     = DBSHelper(self.dbs,verbose,html=1)
        self.dbsdls     = self.helper.getDbsDls()
        self.dbsList    = self.dbsdls.keys()
        self.dbsList.sort()
        self.dbsList.remove(DBSGLOBAL)
        self.dbsList = [DBSGLOBAL]+self.dbsList
        self.dbsShortNames=[]
        for dbs in self.dbsList:
            name=string.split(dbs,"/")[0]
            if string.find(dbs,'fanfani')!=-1:
               name+="_fanfani"
            self.dbsShortNames.append(name)
        self.topHTML    = ""
        self.bottomHTML = ""
        self.verbose    = verbose
        self.dbsDict    = {}
        self.userMode   = True
        self.timer      = self.timerForSummary = time.time()
        self.sumPage    = ""
        self.firstSearch=1
        self.siteDict   = {}
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
        self.dbsdd      = 'http://'+self.hostname+":"+str(self.port)
        if os.environ.has_key('DBSDD'):
           self.dbsdd = os.environ['DBSDD']
        print "DBSDataDiscoveryServer '%s'"%self.dbsdd
        self.formDict   = {
                           'menuForm': ("","","","","",""), # (msg,dbsInst,site,app,primD,tier)
                           'siteForm': ("","") # (dbsInst,site)
                          }

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

    def sendErrorReport(self,msg=""):
        """
           Send a complete report with provided msg. Capture internals of
           L{DBSDataDiscoveryServer} and L{DBSHelper}.
           Use /usr/sbin/sendmail as mail agent.
           @type  msg: string 
           @param msg: message 
           @rtype : none
           @return: none
        """
        p = os.popen("%s -t" % SENDMAIL, "w")
        p.write("To: vk@mail.lns.cornell.edu\n")
        p.write("Subject: DBS DD error\n")
        p.write("\n") # blank line separating headers from body
        if msg: p.write("\n"+msg+"\n\n\n")
        p.write("Request internals:\n")
        p.write("Request from: %s:%s\n"%(cherrypy.request.remote_host,cherrypy.request.remote_port))
        p.write("Request url : %s\n"%cherrypy.request.browser_url)
        p.write("\n\n")
        p.write("DBSDataDiscoveryServer:\n")
        p.write("=======================\n")
        p.write("%s: %s\n"%("DBS Instance   ",self.dbs))
        p.write("%s: %s\n"%("site           ",self.site))
        p.write("%s: %s\n"%("application    ",self.site))
        p.write("%s: %s\n"%("primary dataset",self.primD))
        p.write("%s: %s\n"%("Data tier      ",self.tier))
        p.write("\n") # blank line separating headers from body
        p.write("DBSHelper:\n")
        p.write("==========\n")
        p.write("%s: %s\n"%("DBS Instance   ",self.helper.dbsInstance))
        p.write("%s: %s\n"%("engine         ",self.helper.dbsDB))
        p.write("%s: %s\n"%("dbsApi         ",self.helper.dbsApi))
        p.write("%s: %s\n"%("dbsDLS         ",self.helper.dbsDLS))
        p.write("%s: %s\n"%("dlsType        ",self.helper.dlsType))
        p.write("%s: %s\n"%("dlsEndpoint    ",self.helper.endpoint))
        sts = p.close()
        if sts != 0:
            print "mail exit status", sts
        
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
                     'userMode'    : self.userMode
                    }
        t = Template(CheetahDBSTemplate.templateTop, searchList=[nameSpace])
        page = str(t)
        nameSpace = {'dbsNames'    : self.dbsShortNames }
        t = Template(CheetahDBSTemplate.templateAjaxInit, searchList=[nameSpace])
        page+= str(t)
        if intro:
           page+=CheetahDBSTemplate.templateIntro
        return page

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
           
        nameSpace = {
                     'host'         : self.dbsdd,
                     'userMode'     : self.userMode,
                     'navigatorForm': menuForm,
                     'searchForm'   : self.searchForm(),
                     'siteForm'     : siteForm,
                     'dbsContent'   : self.dbsContList(),
                     'glossary'     : self.glossary(),
                     'frontPage'    : 0
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
                         'dbsContent'   : self.dbsContList(),
                         'frontPage'    : 1,
                         'glossary'     : self.glossary()
                        }
            t = Template(CheetahDBSTemplate.templateFrontPage, searchList=[nameSpace])
            return str(t)
        except:
            t=self.errorReport("Fail in genPanel function")
            pass
            return str(t)

    def dbsContList(self):
        """
           Generates template code for DBS instances within accordeon (Rico term) menu
        """
        nameSpace={'dbsContList':self.dbsShortNames}
        t = Template(CheetahDBSTemplate.templateDbsCont, searchList=[nameSpace])
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
    
    def searchHelper(self,keywords="",restrictDict={}):
        """
           Helper function which invoke L{DBSHelper.search} to find out data based on input keywords.
           @type keywords: string
           @param keywords: keyword string, the keywords are separated by empty spaces
           @rtype: list
           @return: a list of [(primD,tier,App version, App family, App exe)]
        """
        pList=[]
#        if len(keywords):
#           pList=string.split(keywords)
#        oList = self.helper.search(pList,restrictDict)
        oList = self.helper.search(keywords,pList,restrictDict)
        return oList
        
    def searchForm(self):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """
        t = Template(CheetahDBSTemplate.templateSearchTable, searchList=[{}])
        page = str(t)
        self.firstSearch=0
        return page
        
    def search(self,keywords="",**kwargs):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results">"""
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
        if self.userMode:
           self.helperInit(DBSGLOBAL)
           oList=self.searchHelper(keywords)
        else:
           if  not len(dbsList):
               dbsList=self.dbsList
           for dbsInst in dbsList:
               print "dbs",dbsInst
               self.helperInit(dbsInst)
               oList+=self.searchHelper(keywords)
        nameSpace = {
                     'firstSearch': self.firstSearch, 
                     'oList'      : oList, 
                     'userMode'   : self.userMode,
                     'keywords'   : keywords
                    }
        t = Template(CheetahDBSTemplate.templateDataFromSelection, searchList=[nameSpace])
        page+= str(t)
        page+=endAjaxMsg
        if self.verbose:
           print page
        return page
    search.exposed = True

    def advancedSearch(self,keywords="",**kwargs):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results">"""
        endAjaxMsg="</response></ajax-response>"
#        page = self.genTopHTML()
        self.firstSearch=0
#        page+= self.genVisiblePanel('Search')
        if keywords and not validator(keywords):
           page+="""
<hr class="dbs" />
<div class="show_red_bold">Your search expression <p><em>%s</em></p> is not valid</div>
"""%keywords
#           page+= self.genBottomHTML()
           page+=endAjaxMsg
           return page
        oList= []
        # parse keywords and search for DBSINST
        dbsList=appList=tierList=siteList=primList=[]
#        pList=[]
#        if len(keywords):
#           pList=string.split(keywords)
#        for word in pList:
#            if string.find(string.lower(word),":")>-1:
#               p,w = string.split(string.lower(word),":")
#               print p,w
#               if p=="dbs":
#                  for dbs in self.dbsList:
#                      if string.lower(dbs)==w:
#                         dbsList.append(dbs)
#               elif p=="app":
#                  appList.append(w)
#               elif p=="tier":
#                  tierList.append(w)
#               elif p=="site":
#                  siteList.append(w)
#               elif p=="prim":
#                  primList.append(w)
#        restrictDict={}
#        restrictDict["dbs"] =dbsList
#        restrictDict["site"]=siteList
#        restrictDict["app"] =appList
#        restrictDict["prim"]=primList
#        restrictDict["tier"]=tierList
        if self.userMode:
#           oList=self.searchHelper(keywords,restrictDict)
           self.helperInit(DBSGLOBAL)
           oList=self.searchHelper(keywords)
        else:
           if  not len(dbsList):
               dbsList=self.dbsList
           for dbsInst in dbsList:
               print "dbs",dbsInst
               self.helperInit(dbsInst)
               oList+=self.searchHelper(keywords)
        nameSpace = {
                     'firstSearch': self.firstSearch, 
                     'oList'      : oList, 
                     'userMode'   : self.userMode,
                     'keywords'   : keywords
                    }
        t = Template(CheetahDBSTemplate.templateDataFromSelection, searchList=[nameSpace])
        page+= str(t)
#        page+= self.genBottomHTML()
        page+=endAjaxMsg
#        if self.verbose:
        if 1:
           print page
        return page
    advancedSearch.exposed = True

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

    def getDBSDict(self):
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
        
    def genNavigatorMenuDict(self):
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="navigatorDict">"""
        endAjaxMsg="</response></ajax-response>"
        try:
            page+="navDict="+self.getDBSDict()
        except:
            t=self.errorReport("Fail in genNavigatorMenuDict function")
            page+=str(t)
            pass
        page+=endAjaxMsg
        if self.verbose:
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
                     'firstTier': firstTier
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
        
    def getDataFromSelection(self,userSelection,**kwargs):
        """
           Retrieves data upon user selection criterias.
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="results">"""
        endAjaxMsg="</response></ajax-response>"
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
        page+=endAjaxMsg
        if self.verbose:
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

    def showProcDatasets(self,dbsInst,site="All",app="*",primD="*",tier="*"):
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
            self.dbs  = dbsInst
            self.site = site
            self.app  = app
            self.primD= primD
            self.tier = tier
            primaryDataset=primD
            dataTier = tier
            page = self.genTopHTML()
            page+= self.genSnapshot(self.dbs,self.site,self.app,self.primD,self.tier)
            page+="""<hr class="dbs" />"""
            procList = self.helper.getDatasetsFromApp(app)
            page+= "<pre>"
            for procD in procList:
                empty,prim,tier,app = string.split(procD,"/")
                if primaryDataset!="*" and prim!=primaryDataset: continue
                if dataTier!="*" and tier!=dataTier: continue
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
    
    def getDataHelper(self,dbsInst,site="All",app="*",primD="*",tier="*",**kwargs): 
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
        page=""
        
        self.helperInit(dbsInst)
        self.dbs  = dbsInst
        self.site = site
        self.app  = app
        self.primD= primD
        self.tier = tier
        
        page+= self.showProcDatasetsHTML(dbsInst,site,app,primD,tier)

        primaryDataset=primD
        dataTier = tier
        appPath = app
        id=0
        prevPage=""
        oldDataset=oldTotEvt=oldTotFiles=oldTotSize=0
        dList = self.helper.getDatasetsFromApp(appPath)
        regList=[]
        last=0
        page+="""<script type="text/javascript">registerAjaxProvenanceCalls();</script>"""
        for dataset in dList:
            id+=1
            self.writeLog(dataset)
            empty,prim,tier,app = string.split(dataset,"/")
            if primaryDataset!="*" and prim!=primaryDataset: continue
            if dataTier!="*" and tier!=dataTier: continue
            locDict, blockDict, totEvt, totFiles, totSize = self.helper.getData(dataset,appPath,site)
            # new stuff which do not show repeating datasets
            if id>=len(dList):
               last=1
            p = self.dataToHTML(dbsInst,dataset,locDict,blockDict,totEvt,totFiles,totSize,id,last)
            if oldTotEvt==totEvt and oldTotFiles==totFiles and oldDataset:
               idPath=string.replace(oldDataset,"/","___")
               page+="""
<div>
<a href="javascript:showLoadingMessage('parentGraph');registerAjaxProvenanceCalls();getProvenance('%s')">%s</a>
<div id="%s"></div>
</div>
<!--
<br />
-->
                     """%(idPath,oldDataset,idPath)
            else:
               page+=prevPage
            oldTotEvt=totEvt
            oldTotFiles=totFiles
            oldTotSize=totSize
            oldDataset=dataset
            prevPage = p
        page+=prevPage # end of new stuff
        return page
    getDataHelper.exposed=True

    def getData(self,dbsInst,site="All",app="*",primD="*",tier="*",**kwargs): 
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
           @rtype : string
           @return: returns HTML code
        """
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
            page+= self.getDataHelper(dbsInst,site,app,primD,tier)
        except:
            t=self.errorReport("Fail in getData function")
            page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
           print page
        return page
    getData.exposed = True 

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
            if dataset:
               page+= self.genSnapshot(dbsInst,self.site,self.app,self.primD,self.tier)
            page+="""<hr class="dbs" />"""
            page+= self.lfnToHTML(dbsInst,blockName,dataset)
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
            print "getLFNsForSite",dbsInst,site
            self.htmlInit()
            page ="""<html><body><pre>\n"""
            for blockName in self.siteDict[site]:
                lfnList = self.helper.getLFNs(dbsInst,blockName,"")
                for item in lfnList:
                    lfn=item[0]
                    page+="%s\n"%lfn
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
            for blockName in self.siteDict[site]:
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
            page+="replace source.fileNames = {\n"
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
                     'blockName' : blockName,
                     'lfnList'   : lfnList
                    }
        t = Template(CheetahDBSTemplate.templateLFN, searchList=[nameSpace])
        return str(t)
        
    def dataToHTML(self,dbsInst,path,locDict,blockDict,totEvt,totFiles,totSize,id,last=0):
        """
           Forms output tables.
           @type  path: string 
           @param path: processing path
           @rtype : string
           @return: returns HTML code
        """

        # keep in cache locDict
        self.siteDict=locDict
        page=""
        nameSpace = {
                     'host'       : self.dbsdd,
                     'path'       : path,
                     'firstSearch': self.firstSearch,
                     'dbsInst'    : dbsInst,
                     'locDict'    : locDict,
                     'blockDict'  : blockDict,
                     'nEvents'    : totEvt,
                     'totFiles'   : totFiles,
                     'totSize'    : totSize,
                     'userMode'   : self.userMode,
                     'tid'        : id,
                     'last'       : last
                    }
        t = Template(CheetahDBSTemplate.templateLFB, searchList=[nameSpace])
	page+=str(t)
        return page
        
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
            self.helperInit(dbs)
            page+=self.getDatasetsForDbsInst(dbs)
            page+="</response>\n"
        page+="</ajax-response>"
        if self.verbose:
           print page
        return page
    getAllPrimaryDatasets.exposed=True

    def getDatasetsForDbsInst(self,dbsInst):
        """
           Get list of primary dataset for given DBS instances.
           @type  dbsInst: string
           @param dbsInst: DBS instances
           @rtype : string
           @return: returns HTML code
        """
        dList = self.helper.getPrimaryDatasets()
        nameSpace = {
                     'msg'     : "Primary datasets",
                     'dbsInst' : dbsInst,
                     'dList'   : dList
                    }
        t = Template(CheetahDBSTemplate.templatePrintList, searchList=[nameSpace])
        return str(t)

    def getDetailsForPrimDataset(self,dbsInst,primDataset,**kwargs):
        """
           Generates AJAX response to get all primary datasets available in all DBS instances
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        self.helperInit(dbsInst)
        dList=self.helper.getProcessedDatasets("/"+primDataset+"/*/*",app=0)
        page="""<ajax-response><response type="element" id="results">"""
        nameSpace = {
                     'msg'     : 'Processed datasets (<a href="javascript:hideWaitingMessage()">hide</a>)',
                     'dbsInst' : dbsInst,
                     'dList'   : dList
                    }
        t = Template(CheetahDBSTemplate.templatePrintList, searchList=[nameSpace])
        page+=str(t)
        page+="</response></ajax-response>"
        if self.verbose:
           print page
        return page
    getDetailsForPrimDataset.exposed=True

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
#        parents  = self.helper.getDatasetProvenance(dataset)
#        nameSpace={
#                   'host'      : self.dbsdd, 
#                   'dataset'   : dataset, 
#                   'parentList': parents
#                  }
#        t = Template(CheetahDBSTemplate.templateProvenance, searchList=[nameSpace])
#        page = str(t)
        page+="</response></ajax-response>"
        if self.verbose:
           print page
        return page
    getDatasetProvenance.exposed=True

    def getProvenanceForAllDatasets(self,dbsInst,site="All",app="*",primD="*",tier="*",**kwargs): 
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
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        page="""<ajax-response><response type="object" id="parents">"""
        if string.lower(tier)=="all": tier="*"
        if string.lower(site)=="all": site="*"
        self.helperInit(dbsInst)
        self.dbs  = dbsInst
        self.site = site
        self.app  = app
        self.primD= primD
        self.tier = tier
        primaryDataset=primD
        dataTier = tier
        dList = self.helper.getDatasetsFromApp(app)
        for dataset in dList:
            empty,prim,tier,app = string.split(dataset,"/")
            if primaryDataset!="*" and prim!=primaryDataset: continue
            if dataTier!="*" and tier!=dataTier: continue
            page+=self.getDatasetProvenanceHelper(dataset)
        page+="</response></ajax-response>"
#        if self.verbose:
        if 1:
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

    def history(self,actionString,**kwargs):
        """
            History updater
        """
        # AJAX wants response as "text/xml" type
        self.setContentType('xml')
        nameSpace={
                   'time'      : time.strftime("%Y-%m-%d %H:%M:%S",time.localtime()), #time.asctime(),
                   'action'    : actionString
                  }
        t = Template(CheetahDBSTemplate.templateHistory, searchList=[nameSpace])
        page="""<ajax-response><response type="object" id="userHistory">"""
        page+= str(t)
        page+="</response></ajax-response>"
        if self.verbose:
           print page
        return page
    history.exposed=True
#
# main
#
if __name__ == "__main__":
    optManager  = DBSOptions.DBSOptionParser()
    (opts,args) = optManager.getOpt()

    verbose = 0
    if opts.verbose:
       verbose=1
       
    cherrypy.root = DBSDataDiscoveryServer(verbose) 
    cherrypy.config.update(file="CherryServer.conf")
    cherrypy.server.start()
