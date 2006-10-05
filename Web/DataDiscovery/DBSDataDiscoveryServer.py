#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
DBS Data discovery server module.
DBSDD environment is used in all templates, it defines serer name and port, e.g.
http://localhost:8000.

"""

# system modules
import os, string, logging, types, time

# Cheetah template modules
from   Cheetah.Template import Template

# CherryPy server modules
import cherrypy 

# DBS  modules
import CheetahDBSTemplate
from   DBSHelper import *
from   DBSUtil   import *
 
DBSDD="http://localhost:8080"
if os.environ.has_key('DBSDD'):
   DBSDD=os.environ['DBSDD']

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
        print "DBSDataDiscoveryServer uses '%s'"%DBSDD
        self.helper     = DBSHelper(self.dbs,verbose)
        self.dbsdls     = self.helper.getDbsDls()
        self.dbsList    = self.dbsdls.keys()
        self.dbsList.sort()
        self.dbsList.remove(DBSGLOBAL)
        self.dbsList = [DBSGLOBAL]+self.dbsList
        self.topHTML    = ""
        self.bottomHTML = ""
        self.verbose    = verbose
        self.dbsDict    = {}
        self.userMode   = True
        self.timer      = self.timerForSummary = time.time()
        self.sumPage    = ""
        self.firstSearch=1
        self.siteDict   = {}

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
        SENDMAIL = "/usr/sbin/sendmail" # sendmail location
        import os
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
#        for key in self.__dict__:
#            p.write("%s: %s\n"%(key,self.__dict__[key]))
        p.write("\n") # blank line separating headers from body
        p.write("DBSHelper:\n")
        p.write("==========\n")
        p.write("%s: %s\n"%("DBS Instance   ",self.helper.dbsInstance))
        p.write("%s: %s\n"%("engine         ",self.helper.dbsDB))
        p.write("%s: %s\n"%("dbsApi         ",self.helper.dbsApi))
        p.write("%s: %s\n"%("dbsDLS         ",self.helper.dbsDLS))
        p.write("%s: %s\n"%("dlsType        ",self.helper.dlsType))
        p.write("%s: %s\n"%("dlsEndpoint    ",self.helper.endpoint))
#        for key in self.__dict__:
#            p.write("%s: %s\n"%(key,self.__dict__[key]))
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
                     'host'        : DBSDD,
                     'title'       : 'DBS Data Discovery Page',
                     'userMode'    : self.userMode
                    }
        t = Template(CheetahDBSTemplate.templateTop, searchList=[nameSpace])
        page = str(t)
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
           @rtype: returns HTML code
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

    def init(self):
        """
           Construct Init service page, which includes L{genMenuForm}.
           @type  self: class object
           @param self: none
           @rtype : string
           @return: returns HTML code
        """
        try:
#            page = self.genTopHTML(intro=True)
            page = self.genTopHTML(intro=False)
            nameSpace = {
                         'host'         : DBSDD,
                         'userMode'     : self.userMode,
                         'navigatorForm': self.genMenuForm(),
                         'searchForm'   : self.searchForm(),
                         'siteForm'     : self.siteForm(),
                         'summary'      : self.summary(),
                         'datasets'     : self.getAllPrimaryDatasets()
                        }
            t = Template(CheetahDBSTemplate.templateFrontPage, searchList=[nameSpace])
            page+= str(t)
#            page+= self.genMenuForm(frontPage=1)
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
#            page = self.genTopHTML(intro=True)
#            page+= self.genMenuForm(frontPage=1)
#            page+= CheetahDBSTemplate.templateLine_OR
#            page+= self.siteForm()
#            page+= self.genBottomHTML()
#            return page
        except:
            t=self.errorReport("Fail in expert function")
            pass
            return str(t)
    expert.exposed = True

    def summary(self):
        page=""
        try:
            if not self.sumPage or (time.time()-self.timerForSummary)>(24*60*60):
               self.sumPage=self.getSummary()
               page+=self.sumPage
               self.timerForSummary=time.time()
            else:
               page+=self.sumPage
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
        if len(keywords):
           pList=string.split(keywords)
        oList = self.helper.search(pList,restrictDict)
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
        
    def search(self,keywords=""):
        """
           Search any match in DB for given keywords. The search done over
           meta-data tables.
        """
        page = self.genTopHTML()
        self.firstSearch=0
        page+= self.searchForm()
        oList= []
        # parse keywords and search for DBSINST
        dbsList=appList=tierList=siteList=primList=[]
        pList=[]
        if len(keywords):
           pList=string.split(keywords)
        for word in pList:
            if string.find(string.lower(word),":")>-1:
               p,w = string.split(string.lower(word),":")
               print p,w
               if p=="dbs":
                  for dbs in self.dbsList:
                      if string.lower(dbs)==w:
                         dbsList.append(dbs)
               elif p=="app":
                  appList.append(w)
               elif p=="tier":
                  tierList.append(w)
               elif p=="site":
                  siteList.append(w)
               elif p=="prim":
                  primList.append(w)
        restrictDict={}
        restrictDict["dbs"] =dbsList
        restrictDict["site"]=siteList
        restrictDict["app"] =appList
        restrictDict["prim"]=primList
        restrictDict["tier"]=tierList
        if self.userMode:
           oList=self.searchHelper(keywords,restrictDict)
        else:
           if not len(dbsList):
              dbsList=self.dbsList
           for dbsInst in dbsList:
               self.helperInit(dbsInst)
               oList+=self.searchHelper(keywords)
        nameSpace = {
                     'firstSearch': self.firstSearch, 
                     'oList'      : oList, 
                     'userMode'   : self.userMode
                    }
        t = Template(CheetahDBSTemplate.templateDataFromSelection, searchList=[nameSpace])
        page+= str(t)
        page+= self.genBottomHTML()
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
#        page = self.genTopHTML()
        page = """
<table id="table_summary" class="nonsortable" cellspacing="0" cellpadding="0" border="1">
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
#        page+="""<hr class="dbs" />"""
        for dbsInst in dbsList:
            sumDict   = self.helper.getDBSSummary(dbsInst) 
            nameSpace = {'dbsInst':dbsInst,'sumDict':sumDict}
            t = Template(CheetahDBSTemplate.templateSummary, searchList=[nameSpace])
            page+=str(t)
#        page+= self.genBottomHTML()
        page+="</table>"
        return page

    def getDBSDict(self):
        """
            Read dbsDict.all, dbsDict.all files in local directory which contains
            a JS dictionary for pull down menu.
        """
        f = open('dbsDict.global','r')
        self.dbsUser = f.read()
        f.close()
        f = open('dbsDict.all','r')
        self.dbsDict = f.read()
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
        dbsDict = self.getDBSDict()
        nameSpace = {
                     'host'     : DBSDD, 
                     'dict'     : dbsDict,
                     'userMode' : self.userMode,
                     'firstDBS' : firstDBS,
                     'firstSite': firstSite,
                     'firstApp' : firstApp,
                     'firstPrim': firstPrim,
                     'firstTier': firstTier
                    }
        t = Template(CheetahDBSTemplate.templateJS, searchList=[nameSpace])
        page = str(t)
#        if not msg:
#           msg=self.searchForm()
        if self.userMode:
           dbsList=[DBSGLOBAL]
#           if not msg:
#              msg=CheetahDBSTemplate.templateUserHelp
        else:
           dbsList = self.dbsList
#           if not msg:
#              nameSpace={'host':DBSDD}
#              msg = str(Template(CheetahDBSTemplate.templateExpertHelp,searchList=[nameSpace]))

        nameSpace = {
                     'dbsList'  : dbsList,
                     'frontPage': frontPage,
                     'sList'    : getListOfSites(), 
                     'userMode' : self.userMode,
                     'msg'      : msg,
                     'firstDBS' : firstDBS
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
        
    def getDataFromSelection(self,userSelection):
        """
           Retrieve data upon user selection criterias.
        """
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
    getDataFromSelection.exposed = True 

    def showProcDatasets(self,dbsInst,site="All",app="*",primD="*",tier="*"):
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

    def showProcDatasetsHTML(self,dbs,site,app,primD,tier):
        nameSpace = {
                     'host'    : DBSDD,
                     'dbsInst' : dbs, 
                     'site'    : site,
                     'app'     : app,
                     'primD'   : primD,
                     'tier'    : tier
                    }
        t = Template(CheetahDBSTemplate.templateProcDatasets, searchList=[nameSpace])
        return str(t)
    
    def getDataHelper(self,dbsInst,site="All",app="*",primD="*",tier="*"): 
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
        
        nameSpace={'firstSearch': self.firstSearch}
        t = Template(CheetahDBSTemplate.templateSeparator, searchList=[nameSpace])
        page= str(t)
        
        page+= self.showProcDatasetsHTML(dbsInst,site,app,primD,tier)
#        page+="Processed datasets: "

        primaryDataset=primD
        dataTier = tier
        appPath = app
        id=0
        prevPage=""
        oldDataset=oldTotEvt=oldTotFiles=oldTotSize=0
        dList = self.helper.getDatasetsFromApp(appPath)
        for dataset in dList:
            id+=1
            self.writeLog(dataset)
            empty,prim,tier,app = string.split(dataset,"/")
            if primaryDataset!="*" and prim!=primaryDataset: continue
            if dataTier!="*" and tier!=dataTier: continue
            locDict, blockDict, totEvt, totFiles, totSize = self.helper.getData(dataset,site)
            # new stuff which do not show repeating datasets
            p = self.dataToHTML(dataset,locDict,blockDict,totEvt,totFiles,totSize,id)
            if oldTotEvt==totEvt and oldTotFiles==totFiles:
               page+="""<div id="procDataset" name="procDataset" class="off"><b>%s</b></div>"""%oldDataset
            else:
               page+=prevPage
            oldTotEvt=totEvt
            oldTotFiles=totFiles
            oldTotSize=totSize
            oldDataset=dataset
            prevPage = p
        page+=prevPage # end of new stuff

#            page+= self.dataToHTML(dataset,locDict,totEvt,totFiles,totSize,id)
        return page

    def getData(self,dbsInst,site="All",app="*",primD="*",tier="*"): 
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
        try:
            if string.lower(tier)=="all": tier="*"
            if string.lower(site)=="all": site="*"
            self.helperInit(dbsInst)
            self.htmlInit()
            msg="dbsInst='%s', site='%s', app='%s', primD='%s', tier='%s'"%(dbsInst,site,app,primD,tier)
            self.writeLog(msg)
            if self.userMode:
               msg=CheetahDBSTemplate.templateUserHelp
            else:
               nameSpace={'host':DBSDD}
               msg = str(Template(CheetahDBSTemplate.templateExpertHelp,searchList=[nameSpace]))
            page = self.genTopHTML()
            page+= self.genMenuForm(0,msg,dbsInst,site,app,primD,tier)
            page+= self.getDataHelper(dbsInst,site,app,primD,tier)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getData function")
            pass
            return str(t)
    getData.exposed = True 

    def getLFNlist(self,blockName,dataset=""):
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
            page = self.topHTML
            if dataset:
               page+= self.genSnapshot(self.dbs,self.site,self.app,self.primD,self.tier)
            page+="""<hr class="dbs" />"""
            page+= self.lfnToHTML(blockName,dataset)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getLFNlist function")
            pass
            return str(t)
    getLFNlist.exposed = True
 
    def getLFN_txt(self,blockName,dataset=""):
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
            lfnList = self.helper.getLFNs(blockName,dataset)
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
    
    def getLFNsForSite(self,site):
        try:
            self.htmlInit()
            page ="""<html><body><pre>\n"""
            for blockName in self.siteDict[site]:
                lfnList = self.helper.getLFNs(blockName,"")
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
    
    def getLFN_cfg(self,blockName,dataset=""):
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
            lfnList = self.helper.getLFNs(blockName,dataset)
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

    def lfnToHTML(self,blockName,dataset=""):
        """
           Constructs LFN list into table.
           @type  dataset: string
           @param dataset: dataset name
           @type blockName: string
           @param blockName: block name
           @rtype : string
           @return: returns HTML code
        """
        lfnList = self.helper.getLFNs(blockName,dataset)
        nameSpace = {
                     'blockName' : blockName,
                     'lfnList'   : lfnList
                    }
        t = Template(CheetahDBSTemplate.templateLFN, searchList=[nameSpace])
        return str(t)
        
    def dataToHTML(self,path,locDict,blockDict,totEvt,totFiles,totSize,id):
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
                     'host'       : DBSDD,
                     'path'       : path,
                     'firstSearch': self.firstSearch,
                     'locDict'    : locDict,
                     'blockDict'  : blockDict,
                     'nEvents'    : totEvt,
                     'totFiles'   : totFiles,
                     'totSize'    : totSize,
                     'userMode'   : self.userMode,
                     'tid'        : id
                    }
        t = Template(CheetahDBSTemplate.templateLFB, searchList=[nameSpace])
	page+=str(t)
        return page
        
    def getBlocksFromSite(self,dbsInst,site):
        """
           Gets block names for given site.
           @type  site: string 
           @param site: site name 
           @rtype : string
           @return: returns HTML code
        """
        try:
            page = self.genTopHTML()
            page+= self.siteForm(dbsInst,site)
            self.helper.setDBSDLS(dbsInst)
            bList = self.helper.getBlocksFromSite(site)
            nameSpace = {
                         'host'   : DBSDD,
                         'site'   : site,
                         'bList'  : bList
                        }
            t = Template(CheetahDBSTemplate.templateFileBlocksFromSite, searchList=[nameSpace])
            page+= str(t)
            page+= self.genBottomHTML()
            return page
        except:
            t=self.errorReport("Fail in getBlocksFromSite function")
            pass
            return str(t)
    getBlocksFromSite.exposed=True

    def getAllPrimaryDatasets(self):
        page=""
        for dbs in self.dbsList:
            self.helperInit(dbs)
            content=self.getDatasetsForDbsInst(dbs)
            nameSpace = {'dbs':string.replace(dbs,"/","_"),'content':content}
            t = Template(CheetahDBSTemplate.templateDivEntries, searchList=[nameSpace])
            page+=str(t)
        return page

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
                     'msg'   : "Primary datasets",
                     'dList' : dList
                    }
        t = Template(CheetahDBSTemplate.templatePrintList, searchList=[nameSpace])
        return str(t)

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
                     'host'  : DBSDD,
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

    def siteForm(self,firstDBS="",firstSite=""):
        if not firstDBS: firstDBS=DBSGLOBAL
        if firstSite=="*": firstSite="All"
        nameSpace = {
                     'firstDBS' : firstDBS,
                     'firstSite': firstSite,
                     'dbsList'  : self.dbsList,
                     'siteDict' : getDictOfSites()
                    }
        t = Template(CheetahDBSTemplate.templateSiteForm, searchList=[nameSpace])
        page = str(t)
        return page
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
#    if os.environ.has_key("DDHOME"):
#       cherrypy.config.update({
#            'global': {'static_filter.root' : os.environ["DDHOME"] }})
#    if os.environ.has_key("DBSDD"):
#       s = string.split(os.environ["DBSDD"],":")
#       print "Call on port",s[2]
#       cherrypy.config.update({
#            'global': {'server.socket_port' : s[2] }})
    cherrypy.server.start()
