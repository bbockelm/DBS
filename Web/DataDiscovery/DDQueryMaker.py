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
from   DDRules   import *
#from   DDRules   import constrainList

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
  def __init__(self,dbManager,dbsInst=""):
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
      self.ddrules     = DDRules(self.verbose)
      DDLogger.__init__(self,self.ddConfig.loggerDir(),"DDQueryMaker",self.verbose)
      self.dbManager   = dbManager
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
             sel=sel.offset(idx).limit(self.ddConfig.queryLimit())
          res = con.execute(sel)
      except:
          msg="While connecting to %s exception was thrown:\n"%self.dbsInstance
          msg+=getExcept()
          res=[msg]
          self.writeLog(msg)
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
      return formatQuery(self.dbManager.printQuery(self.dbsInstance,sel).replace("\n",""))

  def compileQuery(self,sel):
      return self.dbManager.compileQuery(self.dbsInstance,sel)

  def extractBindParams(self,query):
      cq=self.compileQuery(query)
      return cq.params

  def sortOrder(self,sortName,sortOrder):
      if not sortName: return []
      if sortOrder=="desc":
         oBy = [sqlalchemy.desc(sortName)]
      else:
         oBy = [sqlalchemy.asc(sortName)]
      return oBy

  def makeJoinQuery(self,toSelect,toJoin,wClause,sortName,sortOrder,case,funcDict={}):
#      print "\n\n+++makeJoinQuery",toSelect,toJoin,wClause,sortName,sortOrder,case,funcDict

      # Analyze what needs to be joined
      toJoinList=[]
      for item in toJoin.split(","):
          t,c=item.split(".")
          if not toJoinList.count(t): toJoinList.append(t)

      try:
          person = self.dbManager.getTable(self.dbsInstance,'Person')
          oSel   = []
          gBy    = []
          for tabCol in toSelect.split(","):
              tab,col = tabCol.split(".")
              tabOut  = self.dbManager.getTable(self.dbsInstance,tab)
              # special case for createby/modifyby
              if col.lower().find('createdby')!=-1 or \
                 col.lower().find('lastmodifiedby')!=-1:
                 tabOut = person
                 col = 'DistinguishedName'
              # end of special case
              tcObj   = self.col(tabOut,col)
              if funcDict and funcDict.has_key(tabCol):
                 func = getattr(sqlalchemy.func,funcDict[tabCol])
                 oSel.append(func(tcObj.distinct()))
              else:
                 oSel.append(tcObj)
                 gBy.append(tcObj)
          _oSel  = sqlalchemy.select(oSel,distinct=True)
          iSel   = []
          personJoin=[]
          for tabCol in toJoin.split(","):
              tab,col = tabCol.split(".")
              tabOut  = self.dbManager.getTable(self.dbsInstance,tab)
              # special case for createby/modifyby
              if col.lower().find('createdby')!=-1 or \
                 col.lower().find('creationdate')!=-1 or \
                 col.lower().find('lastmodifiedby')!=-1 or \
                 col.lower().find('lastmodificationdate')!=-1:
                 if not personJoin:
                    personJoin.append( ( person,self.col(tabOut,'CreatedBy'),self.col(person,'ID') ) )
              # end of special case
              iSel.append(self.col(tabOut,col))
          _Sel   = sqlalchemy.select(iSel,distinct=True)
          if self.dbManager.dbType[self.dbsInstance]=='oracle':
             qb  = Schema(self.dbManager.dbTables[self.dbsInstance],owner=self.dbsInstance)
          else:
             qb  = Schema(self.dbManager.dbTables[self.dbsInstance])
          if len(toJoinList)==1:
             query =_oSel
          elif toSelect==toJoin:
             query =_oSel
          else:
             query  = qb.BuildQueryWithSel(_oSel,_Sel,personJoin)
          if not funcDict: gBy=[]
          if gBy:
             query=query.group_by(*gBy)
          bparams=[]
          wList  = wClause.split()
          for idx in xrange(0,len(wList)):
              item = wList[idx]
              if item.lower().find("creationdate")!=-1:
                 wList[idx]="Person.CreationDate"
              elif item.lower().find("lastmodificationdate")!=-1:
                 wList[idx]="Person.LastModificationDate"
              elif item.lower().find("createdby")!=-1:
                 wList[idx]="Person.DistinguishedName"
              elif item.lower().find("modifyby")!=-1:
                 wList[idx]="Person.DistinguishedName"
              if constrainList().count(item):
                 rval=wList[idx+1]
                 rval=rval.replace("*","%")
                 bParam="p%s"%idx
                 key=":%s"%bParam
                 wList[idx+1]=key
                 bparams.append(sqlalchemy.bindparam(key=bParam,value=rval))
          sel=sqlalchemy.text(' '.join(wList),bind=self.dbManager.engine[self.dbsInstance],bindparams=bparams)
          query.append_whereclause(sel)
          return query
      except:
          msg=getExcept()
          self.writeLog(msg)
          traceback.print_exc()
          raise "Fail in DDQueryMaker::makeJoinQuery"+traceback.format_exc()

  def makeQuery(self,_name,**kwargs):
      try:
#          print "DDQueryMaker::makerQuery",_name,kwargs
          person = self.dbManager.getTable(self.dbsInstance,'Person')
          personJoin=[]
          funcDict  = getArg(kwargs,"funcDict",{})
          nameIn,nameOut =_name.split("2")
          table,col = nameOut.split(".")
          tabOut    = self.dbManager.getTable(self.dbsInstance,table)
          colOut    = col
          tcObj     = self.col(tabOut,colOut)
          gBy       = []
          if funcDict and funcDict.has_key(colOut):
             func = getattr(sqlalchemy.func,funcDict[colOut])
             oSel=[func(tcObj.distinct())]
          else:
             oSel=[tcObj]
             gBy.append(tcObj)
          table,col = nameIn.split(".")
          tabIn     = self.dbManager.getTable(self.dbsInstance,table)
          colIn     = col
          # special case for createby/modifyby
          if col.lower().find('createdby')!=-1 or \
             col.lower().find('creationdate')!=-1 or \
             col.lower().find('lastmodifiedby')!=-1 or \
             col.lower().find('lastmodificationdate')!=-1:
             personJoin.append( ( person,self.col(tabIn,'CreatedBy'),self.col(person,'ID') ) )
             tabIn  = person
             if col.lower().find('createdby')!=-1 or col.lower().find('lastmodifiedby')!=-1:
                colIn = 'DistinguishedName'
          # end of special case
          iSel      = [self.col(tabIn,colIn)]
          _oSel     = sqlalchemy.select(oSel,distinct=True)
          if self.dbManager.dbType[self.dbsInstance]=='oracle':
             qb     = Schema(self.dbManager.dbTables[self.dbsInstance],owner=self.dbsInstance)
          else:
             qb     = Schema(self.dbManager.dbTables[self.dbsInstance])
          if tabIn==tabOut:
             query  =_oSel 
             if colIn!=colOut and personJoin:
                tabJoin,leftCol,rightCol=personJoin[0]
                pJoin=tabIn.join(tabJoin,onclause=leftCol==rightCol)
                query.append_from(pJoin)
          else:
             _Sel   = sqlalchemy.select(iSel+oSel,distinct=True)
             query  = qb.BuildQueryWithSel(_oSel,_Sel,personJoin)
          query=query.distinct()
          query=query.apply_labels()
          if not funcDict: gBy=[]
          if gBy:
             query=query.group_by(*gBy)
          if kwargs.has_key('rval'):
             rval   = kwargs['rval']
             # special case for createby/modifyby, use Person table to look-up the value
             if colIn.lower().find('createdby')!=-1 or \
                colIn.lower().find('creationdate')!=-1 or \
                colIn.lower().find('lastmodifiedby')!=-1 or \
                colIn.lower().find('lastmodificationdate')!=-1:
                tabIn=person
             # end of special case
             if  type(rval) is types.StringType:
                 case   = getArg(kwargs,'case','on')
                 self.buildExp(query,self.col(tabIn,colIn),rval,case)
             else:
                 query.append_whereclause(self.col(tabIn,colIn).in_(rval))
          return query
      except:
          msg=getExcept()
          self.writeLog(msg)
          traceback.print_exc()
          raise "Fail in DDQueryMaker::makeQuery"+traceback.format_exc()
      
  ### Implementation for DDSearch
  def buildNotLikeExp(self,sel,tc,val,case='on'):
      if self.dbManager.dbType[self.dbsInstance]=='oracle':
         esc='\\'
      else:
         esc=None
      val=val.replace("_","\_")
      if case=='on':
         return sel.append_whereclause( ~tc.like(val.replace("*","%"),escape=esc) )
      else:
         return sel.append_whereclause( ~sqlalchemy.func.upper(tc).like(val.upper().replace("*","%"),escape=esc) )
  def buildLikeExp(self,sel,tc,val,case='on'):
      if self.dbManager.dbType[self.dbsInstance]=='oracle':
         esc='\\'
      else:
         esc=None
      val=val.replace("_","\_")
      if case=='on':
         return sel.append_whereclause( tc.like(val.replace("*","%"),escape=esc) )
      else:
         return sel.append_whereclause( sqlalchemy.func.upper(tc).like(val.upper().replace("*","%"),escape=esc) )
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
         msg=getExcept()
         self.writeLog(msg)
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
#      print "\n\n+++processSelSeq input\n",iList
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
      qList=[i1,i3]
      # NOTE: INTERSECT works ONLY in ORACLE
      if i2.lower()=="and":
         if self.dbManager.dbType[self.dbsInstance]=='oracle':
            sel = sqlalchemy.intersect(*qList)
         else:
            sel=i1
            sel.append_whereclause(list(i1.inner_columns)[0].in_(i3))
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
#      t,c  = tabCol.split(".")
#      tc   = self.col(self.dbManager.getTable(self.dbsInstance,t),c)
      oSel = [sqlalchemy.func.count('*')]
      sel  = sqlalchemy.select(oSel,from_obj=[query])
      sel  = sel.alias('sel')
#      sel.use_labels=True
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
          msg+=getExcept()
          self.writeLog(msg)
          raise "Fail in countSel"
      self.closeConnection(con)
      return res

  def queryAnalyzer(self,query,userMode="user"):
      bindDict= self.extractBindParams(query)
      sel_txt = str(query)
#      print query.__dict__
      if self.verbose:
         print "\n+++ QUERY ANALYZER\n",self.printQuery(query)
         print bindDict
      selList = sel_txt.lower().split()
      nJoins  = selList.count('join')
      # find all tables involved in a query and calculate their weight
      tWeight = 0
      tList   = []
      findInString(sel_txt.lower(),'from','where',tList)
      tableList = []
      # walk through string between from/where and identify involved tables
      for item in tList:
          for o in item.split():
              o=o.replace("\n","").strip().replace("%s."%self.dbsInstance,"") # strip off schema owner
              if o=='join' or o.find('on')!=-1 or o.find(".")!=-1 or o.find('=')!=-1 \
              or o.find('left')!=-1 or o.find('outer')!=-1 or tableList.count(o): continue
              tableList.append(o) 
      if self.verbose:
         print "+++ QUERY ANALYZER, TABLES\n",tableList
      for table in tableList:
          try:
             tWeight+=self.ddrules.tableWeights[table]
          except: pass
      # find all occurences of where ... select and count how many conditions we have
      condWeight=0
      cDict=constrainDict()
      cList=[]
      findInString(sel_txt.lower(),'where','select',cList)
      if  self.verbose:
          print "+++ QUERY ANALYZER, CONDITIONS\n",cList
      for cond in cList:
          if not cond: continue
          cond=cond.replace('union','').replace('intersect','').replace(")"," ) ")
          factor=1
          val=None
          for elem in cond.split()[2:]:
              if elem[0]==":": # found bind parameter
                 val=bindDict[elem[1:]] # remove ":" find the name
                 if val[0]=="%": # rvalue starts with %
                    factor=5
                    break
#          print cond
#          print val,factor
          for op in cDict.keys():
              if op=='in' or op=='between' or op=='like' or op=='not like': _op=" %s "%op
              else: _op=op
              if cond.find(_op)!=-1:
                 condWeight+=cDict[op]/factor
                 break
      nInter=sel_txt.lower().count('intersect')
      nUnion=sel_txt.lower().count('union')
      threshold=nJoins+nInter-nUnion+tWeight-condWeight
      th_str="nJoins+nInter-nUnion+tWeight-condWeight"
      report="Number of joins=%s, conditions=%s, tables weight=%s, # intersect=%s, # union=%s\n"%(nJoins,condWeight,tWeight,nInter,nUnion)
      if self.verbose:
         print "\n+++ QUERY ANALYZER\n"
         print report
         print "%s=%s\n"%(th_str,threshold)
         print sel_txt
         print
      qth = self.ddConfig.queryThreshold()
      if int(threshold)>=int(qth):
         msg ="Your request cannot be efficiently fulfilled due to large amount of processing data.\n"
         msg+="Please revise your search criterias and try again.\n"
         msg+="Hints:\n"
         msg+=" - Try to avoid if possible 'like *pattern' since a full table scan need to be done.\n"
         msg+="   Instead be more specific and use 'like pattern*' to narrow down your search.\n"
         msg+="   Example:\n"
         msg+="       (bad)  find dataset where release like *CMSSW_1_7*\n"
         msg+="       (good) find dataset where release like CMSSW_1_7*\n"
         msg+=" - Use more constrains\n."
         msg+="   Example:\n"
         msg+="       (much better) find dataset where release like CMSSW_1_7* and prim GlobalNov07-A\n"
         if userMode!='user':
            msg+=report
         if userMode=='dbsExpert':
            msg+=sel_txt
         print "\n+++ QUERY ANALYZER ALERT +++"
         if not self.verbose:
            print sel_txt
         print "%s=%s above threshold %s\n"%(th_str,threshold,qth)
         raise msg

  def processQuery(self,input,userMode="user"):
      """Take input list of path-functions and construct out of them SQL and process it"""
      if self.verbose:
         print "\n\n+++ProcessQuery:\n",str(input)
      if input.find("makeJoinQuery")!=-1:
         sel = eval(input)
      else:
         sel = self.processSelExp(input)
      self.queryAnalyzer(sel,userMode)
      return sel

  def executeQuery(self,output,tabCol,sortName,sortOrder,query,fromRow,limit):
      if output.find("total")!=-1 and output.find(",")==-1: # ex: find total(run) where ...
         return self.executeSingleQuery(query)
      if not sortName and output.find(",")==-1:
         t,c   = tabCol.split(".") 
         sortName=c
      dbTables = self.dbManager.getTableNames(self.dbsInstance)
      tName    = output+"summary"
      # check if we have CLOB data, if so discard sorting 
      if self.ddrules.clob.count(output):
         sortName=""
      if dbTables.count(tName.lower()) and limit:
         return self.executeQueryFromView(output,tabCol,sortName,sortOrder,query,fromRow,limit)
      else:
         return self.executeQueryFromTable(output,tabCol,sortName,sortOrder,query,fromRow,limit)
      
  def wrapToView(self,view,field,query):
      print "\n\n#### call wrapToView",view,field,query
      query=query.replace('\n',' ').replace('\t',' ').strip()
      query="""SELECT * FROM %s WHERE %s IN (%s)"""%(view,field,query)
      if self.verbose:
         print query
      return query
      
  def executeDBSQuery(self,sql,bindDict):
#  def executeDBSQuery(self,dbsApi,userInput,fromRow,toRow):
# I can actually call server instead of client
# http://cmssrv17.fnal.gov:8989/DBSADSTEST03_ADSDEF/servlet/DBSServlet?apiversion=DBS_1_1_2&query=find%20dataset%20where%20dataset%20like%20%25QCD_800-1000%25&begin=&api=executeQuery&end=&type=query
# so the call would be
# http://url/DBSServlet?apiversion=DBS_1_1_2&query=userInput&begin=&api=executeQuery=&type=query
#
      bparams=[]
      for key in bindDict.keys():
          bparams.append(sqlalchemy.bindparam(key=key,value=bindDict[key]))
      sql=sql.replace('\n',' ').replace('\t',' ').strip()
      sel=sqlalchemy.text(sql,bind=self.dbManager.engine[self.dbsInstance],bindparams=bparams)
      if self.verbose:
#         print "\n\n+++ executeDBSQuery\n",userInput
         print self.printQuery(sel)
         print self.extractBindParams(sel)
      con  = self.connectToDB()
      try:
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          print msg
          traceback.print_exc()
          msg+=getExcept()
          self.writeLog(msg)
          raise "Fail in executeDBSQuery"
      oList=[]
      tList=[]
      for item in result:
          if type(item) is types.StringType:
              raise item
          oList.append(item.values())
          if not tList:
             tList=list(item.keys())
          # item is a sqlalchemy.engine.base.RowProxy object and we can take its values
#          if self.dbManager.dbType[self.dbsInstance]=='oracle':
#             oList.append(item.values()[:-1]) # last element is rownum
#             if not tList:
#                tList=list(item.keys()[:-1])
#          else:
#             oList.append(item.values())
#             if not tList:
#                tList=list(item.keys())
      self.closeConnection(con)
      return oList,tList

  def executeDBSCountQuery(self,count_sql,count_bindDict):
      con  = self.connectToDB()
      res=""
      bparams=[]
      for key in count_bindDict.keys():
          bparams.append(sqlalchemy.bindparam(key=key,value=count_bindDict[key]))
      sql=count_sql.replace('\n',' ').replace('\t',' ').strip()
      sel=sqlalchemy.text(sql,bind=self.dbManager.engine[self.dbsInstance],bindparams=bparams)
      if self.verbose:
         print self.printQuery(sel)
         print self.extractBindParams(sel)
      try:
          result = self.getSQLAlchemyResult(con,sel)
          print result
          res = result.fetchone()[0]
      except:
          msg="\n### Query:\n"+str(sel)
          print msg
          traceback.print_exc()
          msg+=getExcept()
          self.writeLog(msg)
          raise "Fail in executeDBSQuery"
      # end of number of results
      self.closeConnection(con)
      return res

  def executeSingleQuery(self,sel):
#      print "\n\n+++ executeSingleQuery\n",sel,self.extractBindParams(sel)
      con  = self.connectToDB()
      try:
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          print msg
          traceback.print_exc()
          msg+=getExcept()
          self.writeLog(msg)
          raise "Fail in executeSingleQuery"
      oList=[]
      tList=[]
      for item in result:
          # item is a sqlalchemy.engine.base.RowProxy object and we can take its values
          if self.dbManager.dbType[self.dbsInstance]=='oracle':
             oList.append(item.values()[:-1]) # last element is rownum
             if not tList:
                tList=list(item.keys()[:-1])
          else:
             oList.append(item.values())
             if not tList:
                tList=list(item.keys())
      self.closeConnection(con)
      return oList,tList

  def executeQueryFromView(self,output,tabCol,sortName,sortOrder,query,fromRow,limit):
#      print "\n\n+++executeQueryFromView",output,tabCol,sortName,sortOrder,query,fromRow,limit
      con  = self.connectToDB()
      sel  = ""
      try:
          # see http://progcookbook.blogspot.com/2006/02/using-rownum-properly-for-pagination.html
          # ORACLE need special way to fetch fromRow/limit, so we wrap query into
          # select * from (SELECT x.*, rownum as rnum FROM (query) x) where rnum between min and max;
          t,c    = tabCol.split(".")
#          tName  = output+"Summary"
          tName  = self.ddrules.dbView[output]
          tab    = self.dbManager.getTable(self.dbsInstance,tName)
          oBy    = self.sortOrder(self.col(tab,sortName),sortOrder)
          obj    = tab
          sortCol= self.col(tab,sortName)
          oSel   = ['*']
          sel = sqlalchemy.select(oSel,from_obj=[obj],order_by=oBy,distinct=True)
          sel.append_whereclause(self.col(tab,c).in_(query))
          if  limit:
              if self.dbManager.dbType[self.dbsInstance]=='oracle':
                 tmp = sel.alias('tmp')
                 q   = sqlalchemy.select(['tmp.*','rownum as rnum'],from_obj=[tmp])
                 sel = sqlalchemy.select(['*'],from_obj=[q])
                 sel.append_whereclause("rnum between %s and %s"%(fromRow,fromRow+limit) )
              else:
                 sel=sel.offset(fromRow).limit(limit).apply_labels()
          if self.verbose:
             print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          print msg
          traceback.print_exc()
          msg+=getExcept()
          self.writeLog(msg)
          raise "Fail in executeQueryFromView"
      oList=[]
      tList=[]
      for item in result:
          if type(item) is types.StringType:
             raise item
          # item is a sqlalchemy.engine.base.RowProxy object and we can take its values
          if self.dbManager.dbType[self.dbsInstance]=='oracle':
             valList=item.values()[:-1] # last element is rownum
             if not tList:
                tList=list(item.keys()[:-1])
          else:
             valList=item.values()
             if not tList:
                tList=list(item.keys())
          oList.append(valList)
      self.closeConnection(con)
      return oList,tList

  def executeQueryFromTable(self,output,tabCol,sortName,sortOrder,query,fromRow,limit):
#      print "\n\n+++ executeQueryFromTable\n",output,tabCol,sortName,sortOrder,query,self.extractBindParams(query)
      con  = self.connectToDB()
      sel  = ""
      selRowNum=None
      try:
          # see http://progcookbook.blogspot.com/2006/02/using-rownum-properly-for-pagination.html
          # ORACLE need special way to fetch fromRow/limit, so we wrap query into
          # select * from (SELECT x.*, rownum as rnum FROM (query) x) where rnum between min and max;
          t,c    = tabCol.split(".")
          try:
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
          except:
              # multi select case
              oSel = ['*']
              oBy  = []
              if  sortName:
                  cList= []
                  query=query.apply_labels()
                  findInString(str(query).lower(),'select','from',cList)
                  oSelList=[]
                  triggerSort=0
                  for item in cList:
                      for elem in item.split():
                          if elem.lower().find(sortName.lower())!=-1: triggerSort=1
                          if elem.find(".")!=-1 or elem.lower()=="as" or elem.lower()=="distinct" or elem.lower()=="count" or elem=="(" or elem==")":
                             continue
                          oSelList.append(elem.replace(",","").strip())
                          if not oBy and triggerSort:
                             oBy=self.sortOrder(elem.replace(",","").strip(),sortOrder)
                  oSel = [','.join(oSelList)]
              qobj = query.alias('qobj')
              tab  = None
              obj  = qobj
#              if sortName and sortName.find(".")!=-1:
#                 t,col= sortName.split(".")
#                 _tab = self.dbManager.getTable(self.dbsInstance,t)
#                 oBy  = self.sortOrder(self.col(_tab,col),sortOrder)
#              elif sortName:
#                 t,col= self.ddrules.tableName(sortName),self.ddrules.colName(sortName)
#                 _tab = self.dbManager.getTable(self.dbsInstance,t)
#                 oBy  = self.sortOrder(self.col(_tab,col),sortOrder)
#              else:
#                 oBy  = []
              gBy  = []
              pass
          sel = sqlalchemy.select(oSel,from_obj=[obj],group_by=gBy,order_by=oBy,distinct=True)
          if tab:
             sel.append_whereclause(self.col(tab,c).in_(query))
          if  limit:
              if self.dbManager.dbType[self.dbsInstance]=='oracle':
                 tmp = sel.alias('tmp')
                 q   = sqlalchemy.select(['tmp.*','rownum as rnum'],from_obj=[tmp])
                 sel = sqlalchemy.select(['*'],from_obj=[q])
                 sel.append_whereclause("rnum between %s and %s"%(fromRow,fromRow+limit) )
                 selRowNum=True
              else:
                 sel=sel.offset(fromRow).limit(limit).apply_labels()
          if self.verbose:
             print self.printQuery(sel)
          result = self.getSQLAlchemyResult(con,sel)
      except:
          msg="\n### Query:\n"+str(sel)
          print msg
          traceback.print_exc()
          msg+=getExcept()
          self.writeLog(msg)
          raise "Fail in executeQueryFromTable"
      oList=[]
      tList=[]
      for item in result:
          if type(item) is types.StringType:
             raise item
          # item is a sqlalchemy.engine.base.RowProxy object and we can take its values
          if self.dbManager.dbType[self.dbsInstance]=='oracle' and selRowNum:
             valList=item.values()[:-1] # last element is rownum
             if not tList:
                tList=list(item.keys()[:-1])
          else:
             valList=item.values()
             if not tList:
                tList=list(item.keys())
          oList.append(valList)
      self.closeConnection(con)
      return oList,tList

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
          msg+=getExcept()
          self.writeLog(msg)
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

