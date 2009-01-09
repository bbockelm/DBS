#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
# Author:  Valentin Kuznetsov, 2008
"""
Set of DBS utilities used by DD
"""
import re
import traceback
import elementtree.ElementTree as ET

from   Cheetah.Template import Template
from   Templates        import *
from   utils.DDUtil     import findInString

# DBS imports
from   RS.Wrapper import RegService
from   DBSAPI.dbsApi import DbsApi
from   utils.dbsapi import DbsApi2
#from  DBSAPI.dbsMigrateApi import DbsMigrateApi
#from  MS.Wrapper import API as DBS_MS

### Please note, thise section is temporary until DBS will implement
### queyring the views
import sqlalchemy
import types
def wrapToView(view, field, query):
    query=query.replace('\n',' ').replace('\t',' ').strip()
    query="""SELECT * FROM %s WHERE %s IN (%s)"""%(view,field,query)
    return query

def executeQueryInDB(dbManager, dbsInst, sql, bindDict, verbose=''):
    bparams=[]
    for key in bindDict.keys():
        bparams.append(sqlalchemy.bindparam(key=key,value=bindDict[key]))
    sql=sql.replace('\n',' ').replace('\t',' ').strip()
    sel=sqlalchemy.text(sql, bind=dbManager.engine[dbsInst], bindparams=bparams)
    if  verbose:
        print str(sel)
        print bindDict
    con = dbManager.connect(dbsInst, 'dbsapi')
    try:
        result = con.execute(sel)
    except:
        msg="\nDBS instance %s"%dbsInst
        msg+="\n### Query:\n"+str(sel)
        msg+= traceback.format_exc()
        raise "Fail in executeQueryInDB"+msg
    oList = []
    tList = []
    for item in result:
        if  type(item) is types.StringType:
            raise item
        oList.append(item.values())
        if  not tList:
            tList=list(item.keys())
    con.close()
    return oList, tList
########################

def gettitles(userInput):
    tList   = []
    findInString(userinput.lower(), 'find', 'where', tList)
    titles  = tList[0].strip().split(",")
    return titles

def parsedbsquery(i):
    """
    crwal DBS XML output
    """
    sql   = ""
    bdict = {}
    val   = re.compile("[0-9]")
    for k in i.getchildren():
        name = ""
        rval = ""
        if k.tag == "sql":
           sql = k.text
        if k.tag == "bindparams":
           for j in k:
               if  val.match(j.text) and j.text.find(".") == -1:
                   bdict[j.tag] = int(j.text)
               else:
                   bdict[j.tag] = j.text
    return sql, bdict

def dbsquery(data, tag = "python_query"):
    """
    parse DBS XML output and return quadruple of query, bind paramers,
    count query and its bind parameters.
    """
    elem  = ET.fromstring(data)
    sql   = ""
    bdict = {}
    csql  = ""
    cdict = {}
    for i in elem:
        if  i.tag == "python_query":
            sql, bdict = parsedbsquery(i)
        elif i.tag == "count_query":
           csql, cdict = parsedbsquery(i)
    sql  = sql.replace("\n"," ").replace("\t"," ").strip()
    csql = csql.replace("\n"," ").replace("\t"," ").strip()
    res  = (sql, bdict, csql, cdict)
    return res

#
# NOTE: I can't use ANY XML parser since DBS-QL doesn't provide mapping between
#       user query and output results. Until that done I must use plain text parsing
#       of DBS-QL results
def dbsparser(data):
    # output in a format 
    # <result SUM_FILE_SIZE='25155849149393' FILE_CREATEBY_DN='mlmiller@vocms19.cern.ch' />
    # I wanted this script be completely stand-alone, so I don't want to use XML parsers
    # which may not be installed on remote site, so plain filter on <result will work here.
    oList  = []
    titles = []
    for item in data.split('\n'):
        if item.find("<result")==-1: continue
        if  not titles:
            for elem in item.split(" "):
                if  elem.find('='):
                    titles.append(elem.split('=')[0])
        r   = item.split("'")
        item = []
        counter = 0
        for idx in range(1,len(r),2):
            elem = r[idx]
            item.append(elem)
        oList.append(item)
    return oList, titles

def dbsparser_touse(data):
    """
    parse DBS XML output and return (resultList, titleList)
    """
    elem  = ET.fromstring(data)
    oList = [] # results
    tList = [] # titles
    for i in elem:
        if  i.tag == "result":
            oList += [i.attrib.values()]
            if  not tList:
                tList = i.attrib.keys()
    return oList, tList

class DBSManager(object):
    """
    DBS manager which construct DBS API based on DBS RS service
    """
    def __init__(self, use_rs=0, verbose = None):
        self.dbsconfig={'mode':'POST', 'retry':2}
        self.verbose = verbose
        self.dbsapi  = {}
        self.dbsattr = {} # dict[alias]=(url,account,ver)
        self.dbsall  = {}
        self.qldict  = {} # dict of supported keys:attrs
        if  use_rs == "0" or not use_rs:
            self.init_DB()
        else:
            self.init_RS()

    def init_DB(self):
        from utils.DDAuth import DDAuthentication
        auth = DDAuthentication()
        for dbs in auth.dbsInstances():
            dbAuth  = DDAuthentication(dbs, self.verbose) 
            dbType, dbName, dbUser, dbPass, host, url = dbAuth.dbInfo()
            dbOwner = dbs.upper()
            url = url.replace('https','http').replace('_writer','').replace(':8443','')
            version = 'DBS_2_0_4'
            self.dbsattr[dbs]={'url':url, 'account':dbUser, 'version':version}
            dbsdict = {'url':url, 'login':dbUser, 'owner':dbOwner,
                       'version':version, 'critical':'Y',
                       'dbtype':dbType, 'location':'CERN'}
            self.dbsall[dbs] = dbsdict

    def init_RS(self):
        reader = re.compile('_r$')
        try :
            api = RegService()
            result = api.queryRegistrationFindAll()
            for i in result:
                # TMP: RS needs to fix account names and remove '.' from names
                # I'm adding _reader suffix to use readable accounts
                account = str(i._accountName).replace('.','').lower()+'_reader'
                owner   = str(i._accountName).replace('.','')
                if  str(i._critical).lower() == 'y' and \
                    i._url and i._accountName and i._serverVersion and \
                    reader.search(i._alias):
                    dbsdict = {'url':i._url, 'login':account,
                               'version':i._serverVersion}
#                    if  i._alias.find('caf') !=-1 or \
#                        i._alias.find('_ph_') != -1 or \
#                        i._alias.find('int_') != -1:
#                        alias = 'cms_dbs_%s' % str(i._alias).replace('_r','')
#                    else:
#                        alias = 'cms_dbs_prod_%s' % str(i._alias).replace('_r','')
#                    self.dbsattr[alias] = dbsdict
                    self.dbsattr[i._alias] = dbsdict
                if  i._dbName.find('oracle') != -1:
                    dbtype = 'oracle'
                else:
                    dbtype = 'mysql'
                dbsdict = {'url':i._url, 'login':account,'owner':owner,
                           'version':i._serverVersion,'critical':i._critical,
                           'dbtype':dbtype,'location':i._physicalLocation}
                self.dbsall[i._alias] = dbsdict
        except:
            traceback.print_exc()
            raise Exception("Fail to access DBS RS service")

    def keys_attrs(self, dbsalias):
        """
        Return a list of supported DBS-QL keys
        """
        if  self.qldict.has_key(dbsalias):
            return self.qldict[dbsalias]
        dbsapi = self.getapi(dbsalias)
        keys = {}
        for item in dbsapi.getHelp():
            keys[item['name']] = item['attrs']
        self.qldict[dbsalias] = keys
        return keys

    def aliases(self, all = None):
        """
        Return list of known to RS DBS aliases
        """
        if  all:
            return self.dbsall.keys()
        return self.dbsattr.keys()

    def geturl(self, dbsalias):
        """
        Return URL for given DBS alias
        """
        return self.dbsall[dbsalias]['url']

    def getdbtype(self, dbsalias):
        return self.dbsall[dbsalias]['dbtype']

# TODO: I don't need this api once migration to DBS API will be completed
    def getaccount(self, dbsalias):
        return 'cms_dbs'

    def getlogin(self, dbsalias):
        return self.dbsall[dbsalias]['login']

    def getowner(self, dbsalias):
        return self.dbsall[dbsalias]['owner']

    def getpass(self, dbsalias):
        if dbsalias.find('global') != -1:
            return 'everyknowsDBS2'
        return 'everyoneknowsDBS2'
################################

    def getapi(self, dbsalias):
        """
        Consutrct (or take from local cache) DBS api for given DBS alias
        """
        try:
            config  = dict(self.dbsconfig)
            dbsattr = self.dbsattr[dbsalias] 
            config['url'] = dbsattr['url']
            config['version'] = dbsattr['version']
            if self.dbsapi.has_key(dbsalias):
               return self.dbsapi[dbsalias]
            else:
               dbsapi = DbsApi2(config)
               self.dbsapi[dbsalias]=dbsapi
               return dbsapi
        except:
            raise Exception("Fail to construct DBS API")

    def queryxml(self, dbsalias, userinput, q_start = "", q_end = ""):
        """
        Return SQL query for given user input in XML format
        """
        dbsapi = self.getapi(dbsalias)
        dbsxml = dbsapi.executeQuery(userinput, begin = q_start, end = q_end, type="query")
        return dbsxml

    def query(self, dbsalias, userinput, q_start = "", q_end = ""):
        """
        Return SQL query for given user input
        """
        return dbsquery(self.queryxml(dbsalias, userinput, q_start, q_end))

    def count(self, dbsalias, userinput):
        """
        Execute DBS query for given user input and return total number of found results
        """
        dbsapi = self.getapi(dbsalias)
#        return dbsapi.countQuery(userinput)
        dbsxml = dbsapi.countQuery(userinput)
        result, titles = dbsparser(dbsxml)
        return result[0][0]
#        return len(result)

    def exexml(self, dbsalias, userinput, q_start = "", q_end = ""):
        """
        Execute DBS query for given user input and return results in XML format
        """
        dbsapi = self.getapi(dbsalias)
        dbsxml = dbsapi.executeQuery(userinput, begin = q_start, end = q_end, type = "exe")
        return dbsxml

    def exe(self, dbsalias, userinput, q_start = "", q_end = ""):
        """
        Execute DBS query for given user input and return results in a list format
        """
        if  not dbsalias or dbsalias.lower() == 'all':
            for alias in self.aliases():
                print alias
                result, titles = dbsparser(
                            self.exexml(alias, userinput, q_start, q_end))
                if  result:
                    return result, titles
        return dbsparser(self.exexml(dbsalias, userinput, q_start, q_end))

    def exehtml(self, dbsalias, userinput, q_start = "", q_end = ""):
        """
        Execute DBS query and return results in a form of HTML table
        """
        results, titles = self.exe(dbsalias, userinput, q_start, q_end)
        titles = gettitles(userInput)
        nameSearch = {'headers':titles, 'rows':results}
        t = templateGenericTable(searchList=[nameSearch]).respond()
        return str(t)

#
# main
#
if __name__ == "__main__":
    dbsmgr = DBSManager()
    query = "find dataset, site where site like *cern.ch"
    print "DBS aliases:"
    dbslist = dbsmgr.aliases()
    dbslist.sort()
    print dbslist
    print dbsmgr.keys_attrs(dbslist[0])

#    dbsall = dbsmgr.aliases(True)
#    print dbsall
#    dbsall.sort()
#    for dbsalias in dbsall:
#        print dbsalias
#        print dbsmgr.dbsall[dbsalias]

#    for dbsalias in dbsmgr.aliases():
#        print dbsalias
#        dbsapi = dbsmgr.getapi(dbsalias)
#        print dbsapi
#        print dbsmgr.query(dbsalias, query)
#        print dbsmgr.queryxml(dbsalias, query)
#        print dbsmgr.exe(dbsalias, query, 0, 10)
#    print dbsmgr.exehtml('all', query, 0, 10)
#    print dbsmgr.count('all', query)
