#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2008 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2008

"""
Data Discovery Manager
"""

# import system modules
import string, sys, time, types, popen2, httplib
import elementtree, traceback
from elementtree.ElementTree import fromstring

# import DBS modules
import DDOptions
from   DDConfig  import *
from   DBSInst   import * # defines DBS instances and schema
from   DDUtil    import * # general utils
from   DDRules   import constrainList

# QueryBuilder
from QueryBuilder.Schema import Schema

# import DLS modules
#try:
#    import dlsClient
#    import dlsApi
#except:
#    pass
import __builtin__

class DDQueryMaker(DDLogger): 
  """
      DDQueryMaker class
  """
  def __init__(self,dbsInst=""):
      """
         Constructor which takes two arguments DBS instance and verbosity level.
         It initialize internal logger with own name and pass verbosity level to it.
         @type  dbsInst: string 
         @param dbsInst: name of the DBS instance, e.g. MCGlobal/Writer (default)
         @type verbose: boolean or integer
         @param verbose: verbosity level
         @rtype : none
         @return: none
      """
      self.ddConfig  = DDConfig()
      if not dbsInst:
         dbsInst=self.ddConfig.dbsprimary()
      self.dbsInstance = dbsInst
      self.verbose     = 0
      self.html        = 0
      DDLogger.__init__(self,self.ddConfig.loggerDir(),"DDQueryMaker",self.verbose)
      try:
         self.dbManager      = DBManager('OBSOLETE need to be removed',self.verbose)
      except:
         if self.verbose:
            print "WARNING! some of the functionality will be disable due to missing authentication"
            self.writeLog(getExcept())
            printExcept()
         pass
      self.initDBS(self.dbsInstance)

  def initDBS(self,dbsInst):
      """
         Set DBS instance to use at given time.
      """
      if not DBS_INST_URL.keys().count(dbsInst):
         msg = "Wrong DBS instance '%s'\n"%dbsInst
         msg+= "Available instances:\n"
         for dbs in self.dbsdls.keys():
             msg+= dbs+"\n"
         raise msg
      self.dbsInstance = dbsInst
      self.writeLog("DBS Instnace: %s"%dbsInst)
      con = self.connectToDB()
      self.closeConnection(con)

  def closeConnection(self,con):
      # if SQLAlchemy uses pool for engine, then it should correctly handle all connections
      # and there is no needs to close it, since a new one will be taken from pool
      # but I can keep it commented out here and use this function everywhere to rollback
      # quickly.
#      con.close()
      return

  def connectToDB(self):
      con=""
      try:
          con = self.dbManager.connect(self.dbsInstance)
      except:
	 try:
             con = self.dbManager.connect(self.dbsInstance)
         except:
             try:
                 # try second time, but sleep for 2 seconds before retry
                 time.sleep(2)
                 self.dbManager.clear()
                 con = self.dbManager.connect(self.dbsInstance)
             except Exception, ex:
                 raise DbsDatabaseError(args=ex)
             pass
         pass
      return con

  def getSQLAlchemyResult(self,con,sel,idx=-1):
      """
         Set DBS instance and
         execute given query, if fails throws exception, including query
         @type  sel: SQLAlchemy select object
         @param sel: SQL query written in SQLAlchemy
         @rtype : SQLAlchemy query object 
         @return: results of the query
      """
      res = []
      try:
          if idx>-1:
             sel.limit=self.ddConfig.queryLimit()
             sel.offset=idx
          res = con.execute(sel)
      except:
          msg="While connecting to %s exception was thrown:\n"%self.dbsInstance
          msg+=getExcept()
          res=[msg]
          pass
      return res

  def setVerbose(self,level):
      self.verbose=level
      self.dbManager.setVerbose(level)

  def alias(self,tableName,aliasName=""):
      return self.dbManager.getTable(self.dbsInstance,tableName,aliasName)

  def col(self,table,col):
      return self.dbManager.col(self.dbsInstance,table,col)

  def printQuery(self,sel):
      return self.dbManager.printQuery(self.dbsInstance,sel).replace("\n","")

  def compileQuery(self,sel):
      return self.dbManager.compileQuery(self.dbsInstance,sel)

  def extractBindParams(self,query):
      cq=self.compileQuery(query)
      bindparams=cq.__dict__['binds']
      bparams={}
      for key in bindparams.keys():
          bparams[key]=bindparams[key].value
      return bparams

  def sortOrder(self,sortName,sortOrder):
      if sortOrder=="desc":
         oBy = [sqlalchemy.desc(sortName)]
      else:
         oBy = [sqlalchemy.asc(sortName)]
      return oBy

  def sortQuery(self,tabCol,sortName,sortOrder,query):
      try:
          print "DDQueryMaker::makerQuery",_name,kwargs
          t,c       = tabCol.split(".")
          tab    = self.dbManager.getTable(self.dbsInstance,t)
          oSel      = [self.col(tabOut,c)]
          oBy       = None
          obj       = None
          if t.lower()=='block' and c=='Path' and sortCol.find('Date')!=-1:
              tprd      = self.alias('ProcessedDataset','tprd')
              oBy       = self.sortOrder(self.col(tprd,sortName),sortOrder)
              sortSel   = [self.col(tprd,sortName)]
              obj       = tprd.join(tab,onclause=self.col(tprd,'ID')==self.col(tab,'Dataset'))
          else:
              oBy       = self.sortOrder(self.col(tab,sortName),sortOrder)
              sortSel   = [self.col(tab,sortName)]
          iSel      = [self.col(tabIn,colIn)]
          _oSel     = sqlalchemy.select(oSel+sortSel)
          query  = sqlalchemy.select(oSel+sortSel,from_obj=[obj],distinct=True,order_by=[oBy])
          query.append_whereclause(self.col(tab,c).in_(sel))
#          print query
#          print
          return query
      except:
          traceback.print_exc()
          raise "Fail in DDQueryMaker::sortQuery"+traceback.format_exc()
      
  def makeQuery(self,_name,**kwargs):
      try:
#          print "DDQueryMaker::makerQuery",_name,kwargs
          nameIn,nameOut =_name.split("2")
          table,col = nameOut.split("_")
          tabOut    = self.dbManager.getTable(self.dbsInstance,table)
          table,col = nameOut.split("_")
          tabOut    = self.dbManager.getTable(self.dbsInstance,table)
          colOut    = col
          oSel      = [self.col(tabOut,col)]
          table,col = nameIn.split("_")
          tabIn     = self.dbManager.getTable(self.dbsInstance,table)
          colIn     = col
          iSel      = [self.col(tabIn,colIn)]
          _oSel     = sqlalchemy.select(oSel,distinct=True)
          if nameIn==nameOut:
             query  =_oSel 
          else:
             md     = self.dbManager.metaDict[self.dbsInstance]
             _Sel   = sqlalchemy.select(iSel+oSel,distinct=True)
             qb     = Schema(self.dbManager.dbTables[self.dbsInstance])
             query  = qb.BuildQueryWithSel(_oSel,_Sel)
          query.distinct=True
          if kwargs.has_key('rval'):
             rval   = kwargs['rval']
             if  type(rval) is types.StringType:
                 case   = getArg(kwargs,'case','on')
                 self.buildExp(query,self.col(tabIn,colIn),rval,case)
             else:
                 query.append_whereclause(self.col(tabIn,colIn).in_(rval))
#          print query
#          print
          return query
      except:
          traceback.print_exc()
          raise "Fail in DDQueryMaker::makerQuery"+traceback.format_exc()
      
  def executeSQLQuery(self,query):
      if not self.checkQuery(query):
         return "Your query is not valid, you either specified unkonwn table or tried to perform insert/update operation, which are forbidden."
      if self.verbose==2:
         print "Execute SQL query:\n",query

      con = self.connectToDB()
      res = ""
      if type(query) is sqlalchemy.sql.Select:
         query.use_labels=True
      try:
         res = con.execute(query)
      except:
         res = getExceptionInHTML()
         self.closeConnection(con)
         return res
      oList = []
      counter=0
      for item in res:
          if not counter:
             oList.append(item.keys())
          oList.append(item)
          counter+=1
      self.closeConnection(con)
      return oList

  ### Implementation for DDSearch
  def buildNotLikeExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause( ~tc.like(val.replace("*","%")) )
      else:
         return sel.append_whereclause( ~sqlalchemy.func.upper(tc).like(val.upper().replace("*","%")) )
  def buildLikeExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause( tc.like(val.replace("*","%")) )
      else:
         return sel.append_whereclause( sqlalchemy.func.upper(tc).like(val.upper().replace("*","%")) )
  def buildEqExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc==val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)==val.upper())
  def buildLtExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc<val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)<val.upper())
  def buildLteqExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc<=val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)<=val.upper())
  def buildGtExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc>val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)>val.upper())
  def buildGteqExp(self,sel,tc,val,case='on'):
      if case=='on':
         return sel.append_whereclause(tc>=val)
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc)>=val.upper())
  def buildInExp(self,sel,tc,val,case='on'):
      iList=val.split(",")
      if case=='on':
         return sel.append_whereclause(tc.in_(*iList))
      else:
         return sel.append_whereclause(sqlalchemy.func.upper(tc).in_(*iList))
  def buildExp(self,sel,tc,val,case):
      try:
         for co in constrainList():
             idx=val.lower().find(co)
             if idx==0:
                op=co
                val=val[len(co):]
#         print "\n\n+++buildExp, operator='%s', value='%s'"%(op,val)
         if op=='like':
            return self.buildLikeExp(sel,tc,val,case)
         elif op=='not like' or op=='not_like':
            return self.buildNotLikeExp(sel,tc,val,case)
         elif op=="<":
            return self.buildLtExp(sel,tc,val,case)
         elif op=="<=":
            return self.buildLteqExp(sel,tc,val,case)
         elif op==">":
            return self.buildGtExp(sel,tc,val,case)
         elif op==">=":
            return self.buildGteqExp(sel,tc,val,case)
         elif op=="=":
            return self.buildEqExp(sel,tc,val,case)
         elif op=="in":
            return self.buildInExp(sel,tc,val,case)
         elif op=="between":
            min,max=val.split("-")
            self.buildLteqExp(sel,tc,max,case)
            return self.buildGteqExp(sel,tc,min,case)
         else:
            raise "Unknown operator",op
      except:
         traceback.print_exc()
         raise "Fail to build query for '%s', '%s', '%s', case='%s'"%(self.printQuery(sel),tc,val,case)

  def buildListExp(self,sel,tc,idList,case='on'):
      condList=[]
      for id in idList:
          cList=[]
          if case=="on":
             cList.append(tc==id)
          else:
             cList.append(sqlalchemy.func.upper(tc)>id.upper())
          condList.append(sqlalchemy.and_(*cList))
      if len(condList): 
         sel.append_whereclause(sqlalchemy.or_(*condList))

  def processSelSeq(self,iList):
      """
         I got input list ( a and b or c ... ), with optional brackets. Will transform it into SQL
         The a,b,c in this example are path-functions.
      """
#      print "\n\n+++processSelSeq input",iList
      if len(iList)==1:
         sel = iList[0]
         return sel
      if iList[0]=="(":
         iList = iList[1:-1]
      # get 3 first elements from the list and eval 1st and 3d, since those are functions, and
      # construct either union or intersect between SQL statements
      i1 = iList[0]
      i2 = iList[1]
      i3 = iList[2]
#      print "\n\n+++processSelSeq",i1,i2,i3
      qList=[i1,i3]
      # NOTE: INTERSECT works ONLY in ORACLE
      if i2.lower()=="and":
         sel = sqlalchemy.intersect(*qList)
      elif i2.lower()=="or":
         sel = sqlalchemy.union(*qList)
      else:
         raise "Unknown operator '%s'"%i2
#      print "\n\n+++processSelSeq new query is\n",self.printQuery(sel)
      if len(iList)>3:
         return self.processSelSeq([sel]+iList[3:])
      else:
         return sel

  def processSelExp(self,input):
      """Transform input expression ((q1 and q2) or q3) into SQL"""
      # input is a string, where queries are transformed into path-functions
      if  type(input) is types.StringType:
          iList=[]
          for item in input.split():
              if item[:4]=="self":
                 iList.append(eval(item))
              else:
                 iList.append(item)
          input = iList
      lpos=-1
      rpos=-1
      for idx in xrange(0,len(input)):
          if input[idx]=="(":
             lpos=idx
          if input[idx]==")" and rpos==-1:
             rpos=idx
      if lpos!=-1 and rpos!=-1:
         expr = self.processSelSeq(input[lpos:rpos+1])
         return self.processSelExp(input[:lpos]+[expr]+input[rpos+1:])
      else:
         return self.processSelSeq(input)

  def countSel(self,query,tabCol):
      con  = self.connectToDB()
      query= query.alias('query')
      t,c  = tabCol.split(".")
      tc   = self.col(self.dbManager.getTable(self.dbsInstance,t),c)
      oSel = [sqlalchemy.func.count('*')]
      sel  = sqlalchemy.select(oSel,from_obj=[query])
      sel  = sel.alias('sel')
      if self.verbose:
         print self.printQuery(sel)
      try:
          result = self.getSQLAlchemyResult(con,sel)
          res = result.fetchone()[0]
      except:
          msg="\n### Query:\n"+str(sel)+str(result)
          print msg
          print sel.__dict__
          traceback.print_exc()
          raise "Fail in countSel"
      self.closeConnection(con)
      return res

  def processQuery(self,iList):
      """Take input list of path-functions and construct out of them SQL and process it"""
      sel = self.processSelExp(iList)
      if self.verbose:
         print "\n\n+++ProcessQuery",str(iList)
         print self.printQuery(sel)
         print self.extractBindParams(sel)
      return sel

  def executeQuery(self,tabCol,sortName,sortOrder,query,fromRow,limit):
      con  = self.connectToDB()
      sel  = ""
      try:
          # see http://progcookbook.blogspot.com/2006/02/using-rownum-properly-for-pagination.html
          # ORACLE need special way to fetch fromRow/limit, so we wrap query into
          # select * from (SELECT x.*, rownum as rnum FROM (query) x) where rnum between min and max;
          t,c    = tabCol.split(".")
          tab    = self.dbManager.getTable(self.dbsInstance,t)
          if t.lower()=='block' and c=='Path' and sortName.find('Date')!=-1:
             tprd    = self.alias('ProcessedDataset','tprd')
             oBy     = self.sortOrder(self.col(tprd,sortName),sortOrder)
             obj     = tprd.join(tab,onclause=self.col(tprd,'ID')==self.col(tab,'Dataset'))
             sortCol = self.col(tprd,sortName)
          else:
             oBy     = self.sortOrder(self.col(tab,sortName),sortOrder)
             obj     = tab
             sortCol = self.col(tab,sortName)
          if c==sortName:
             oSel= [self.col(tab,c)]
          else:
             oSel= [self.col(tab,c),sortCol]
          gBy = oSel
          if self.dbManager.dbType[self.dbsInstance]=='oracle':
             gBy = gBy+['rownum']
          sel = sqlalchemy.select(oSel,from_obj=[obj],group_by=gBy,order_by=oBy)
          sel.distinct=True
          sel.append_whereclause(self.col(tab,c).in_(query))
          if  limit:
              if self.dbManager.dbType[self.dbsInstance]=='oracle':
                 tmp = sel.alias('tmp')
                 q   = sqlalchemy.select(['tmp.*','rownum as rnum'],from_obj=[tmp])
                 sel = sqlalchemy.select(['*'],from_obj=[q])
                 sel.append_whereclause( 'rnum between %s and %s'%(fromRow,fromRow+limit) )
              else:
                 sel.limit=limit
                 sel.offset=fromRow
          if self.verbose:
             print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          print msg
          traceback.print_exc()
          raise "Fail in executeQuery"
      oList=[]
      for item in result:
          oList.append(item)
      self.closeConnection(con)
      return oList

  def getSummary(self,tabCol,value):
      con  = self.connectToDB()
      sel  = ""
      try:
          t,c  = tabCol.split(".")
          tab  = self.alias(t,'tab')
          tp   = self.alias('Person','tp')
          oSel = [self.col(tab,c),self.col(tp,'DistinguishedName')]
          oSel+= [self.col(tab,'CreationDate'),self.col(tab,'LastModificationDate')]
          obj  = tab.outerjoin(tp,onclause=self.col(tab,'CreatedBy')==self.col(tp,'ID'))
          sel  = sqlalchemy.select(oSel,from_obj=[obj],distinct=True,use_labels=True)
          sel.append_whereclause(self.col(tab,c)==value)
          if self.verbose:
             print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          print msg
          traceback.print_exc()
          raise "Fail in getSummary"
      oList=[]
      for item in result:
          name,cBy,cDate,mDate=item
          oList.append((name,timeGMT(cDate),cBy))
      self.closeConnection(con)
      return oList

#
# main`
#
if __name__ == "__main__":
    optManager  = DDOptions.DDOptionParser('DDQueryMaker')
    (opts,args) = optManager.getOpt()
#    print "options:  ",opts
#    print "arguments:",args
    
    dbsInst = DBS_INST_URL.keys()[0]
    if opts.dbsInst:
       dbsInst = opts.dbsInst

    helper = DDQueryMaker(dbsInst)
    helper.setVerbose(opts.verbose)
    from DDRules import *
    asearch= DDRules()

    t1=time.time()
    print "\nInput :",opts.query
    sel = asearch.parser(opts.query)
    print "\nParser:",sel
    res = helper.processQuery(sel)
    print "\nResult:",res
    print "total time: %s sec"%(time.time()-t1)

