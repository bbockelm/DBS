#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
   DBS deployment script
"""

import os, sys, time, string, popen2, tempfile, urllib
from optparse import OptionParser

class DBSDeploymentOptionParser:
  """
     OptionParser is main class to parse options.
  """
  def __init__(self):
      self.parser = OptionParser()
      self.parser.add_option("--quiet",action="store_true", default=False, dest="quiet",
           help="be quiet during deployment procedure")
      self.parser.add_option("--dbType",action="store", type="string", dest="dbType",
           help="specify DB type, e.g. ORACLE, MySQL")
      self.parser.add_option("--db",action="store", type="string", dest="db",
           help="specify DB to use")
      self.parser.add_option("--username",action="store", type="string", dest="userName",
           help="specify user name for DB access (if any)")
      self.parser.add_option("--password",action="store", type="string", dest="userPass",
           help="specify user password for DB access (if any)")
      self.parser.add_option("--version",action="store", type="string", dest="version",
           help="specify DBS version to use, e.g. v00_00_02, default is the head")
      self.parser.add_option("--prefix",action="store", type="string", dest="prefix",
           help="specify location for installation")
      self.parser.add_option("--listVersions",action="store_true", default=False, dest="listVersions",
           help="list available version of DBS code")
      self.parser.add_option("-v","--verbose",action="store_true", default=False, dest="verbose",
           help="be verbose")
      self.parser.add_option("--drop",action="store_true", default=False, dest="drop",
           help="drop tables if they are exists")
  def getOpt(self):
      """
          Returns parse list of options
          @type  self: class object
          @param self: none
          @rtypei : none
          @return : list of options.
      """
      return self.parser.parse_args()

class DBSDeployment:
  """
      Main class to do deployment.
  """
  def __init__(self,dbType,db,user,password,prefix,version,drop=0,verbose=0):
      self._verbose=verbose
      self._drop=drop
      self._dbType = dbType
      self._db=db
      self._version=version
      if dbType=="ORACLE":
         self._schema='DBS-NeXtGen-Oracle_DEPLOYABLE.sql'
         self._dropSQL="OracleReset.sql"
         self._cmd="sqlplus %s/%s@%s"%(user,password,db)
         self._probeSQL="SELECT table_name FROM USER_TABLES"
      elif dbType=="MYSQL":
         self._schema='DBS-NeXtGen-MySQL_DEPLOYABLE.sql'
         self._dropSQL="MySQLReset.sql"
         self._cmd="mysql --user=%s --password='%s' %s"%(user,password,db)
         self._probeSQL="SHOW TABLES"
      else:
         raise "Unknown DB type '%s', please use --dbType option to specify it"%dbType
      self._prefix=prefix
      if not os.environ.has_key('DBS_HOME'):
         raise "Not DBS_HOME found in your environment"
      self._compDict={'SCHEMA':'Schema/NeXtGen',
                      'CLIENT':'Clients/Python',
                      'SERVER':'Servers/JavaServer'}
         
  def checkDBS(self):
      for key in self._compDict.keys():
          os.environ['DBS_'+key+'_PATH']=os.path.join(os.environ['DBS_HOME'],self._compDict[key])
      if  os.environ.has_key('PYTHONPATH'):
          os.environ['PYTHONPATH']=os.environ['DBS_CLIENT_PATH']+":"+os.environ['PYTHONPATH']
      else:
          os.environ['PYTHONPATH']=os.environ['DBS_CLIENT_PATH']
      self._schema=os.path.join(os.environ['DBS_SCHEMA_PATH'],self._schema)
      self._dropSQL=os.path.join(os.environ['DBS_SCHEMA_PATH'],self._dropSQL)
      
      if self._verbose:
         print "Location of schema files"
         print self._schema
         print self._dropSQL
      if not os.path.isfile(self._schema):
         raise "Fail to find schema file '%s'"%self._schema
      if not os.path.isfile(self._dropSQL):
         raise "Fail to find schema file '%s'"%self._dropSQL

  def checkUnitTests(self):
      unitTestsDir=os.path.join(os.environ['DBS_CLIENT_PATH'],'UnitTests')
      os.chdir(unitTestsDir)
      fList = os.listdir(os.getcwd())
      for file in fList:
          if file[-3:]!=".py":
             continue
          if self._verbose:
             print "+++ Unit test %s"%file
          self.execute("python %s"%file,"Fail to execute unit test '%s'"%file)
      
  def probeDB(self):
      # check if appropriate tool for underlying DB exists in a path

      # got to DBS_HOME
      os.chdir(os.environ['DBS_SCHEMA_PATH'])
      if self._verbose:
         print "chdir %s"%os.getcwd()
      tmpFile = tempfile.NamedTemporaryFile('w')
      tmpFile.write(self._probeSQL)
      tmpFile.flush()
      cmd=self._cmd+" < "+tmpFile.name
      if self._verbose:
         print "Probing your %s DB"%self._dbType
      self.execute(cmd,"Fail to probe your system, please investigate")
      tmpFile.close()
      
  def execute(self,cmd,errMsg):
      try:
          res = popen2.Popen3(cmd,True)
          status = res.wait()
          result= res.fromchild.read()
          if status>0:
#             print "status",status
             raise result
          if self._verbose:
             print result
      except:
          raise cmd+"\n"+errMsg

  def dropTables(self):
      cmd=self._cmd+" < "+self._dropSQL
      self.execute(cmd,"Fail to drop tables from DB")

  def createTables(self):
      if self._drop:
         self.dropTables()
      cmd=self._cmd+" < "+self._schema
      self.execute(cmd,"Fail to create tables from DB")

  def CheckEnvironment(self,verbose=0):
      cvsEnv=os.environ['CVSROOT']
      cmsCvs=':pserver:anonymous@cmscvs.cern.ch:/cvs_server/repositories/CMSSW'
      if cvsEnv!=cmsCvs:
         if verbose:
            print "Found CVSROOT='%s'"%cvsEnv
            print "To deploy DBS I'll set it up to '%s'"%CMSCVS
         os.environ['CVSROOT']=cmsCvs

  def listDBSVersions(self,verbose=0):
      cmd="cvs status -v DBS/Schema/NeXtGen/DBS-NeXtGen-MySQL.sql"
      
  def checkoutDBS(self):
      if os.path.isdir(self._schema) and os.path.isdir('CVS'):
         if self._verbose:
            print "Found schema '%s' in local directory"%self._schema
            return
      cmd="cvs co"
      if os.path.isdir('COMP/DBS'):
         cmd="cvs up"
      self.CheckEnvironment(self._verbose)
      version=""
      if self._version:
         version="-r %s"%self.version
      cmd='%s %s '%(cmd,version)
      for d in self._compDict.values():
          exe = cmd+"COMP/DBS/"+d
          if self._verbose:
             print "Execute:",exe
          self.execute(exe,"Fail to checkout/update DBS code from CVS")
      # since we downloaded schema from cvs let's redefine what we will use
      if dbType=="ORACLE":
         self._schema=os.path.join(os.environ['DBS_SCHEMA_PATH'],'DBS-NeXtGen-Oracle.sql')
         self._dropSQL=os.path.join(os.environ['DBS_SCHEMA_PATH'],'OracleReset.sql')
      elif dbType=="MYSQL":
         self._schema=os.path.join(os.environ['DBS_SCHEMA_PATH'],'DBS-NeXtGen-MySQL.sql')
         self._dropSQL=os.path.join(os.environ['DBS_SCHEMA_PATH'],'MySQLReset.sql')
      
      if self._verbose:
         print "Location of schema files"
         print self._schema
         print self._dropSQL

  def CheckSchema(self,verbose=0):
      if os.path.isfile(self._schema):
         if verbose:
            print "Found existing schema: '%s'"%self._schema
      else:
         raise "No schema file is found, please re-run the script with --checkout option"
         
  def checkAnt(self):
      if os.environ.has_key('ANT_HOME'):
         if self._verbose:
            print "Found ant in '%s'"%os.environ['ANT_HOME']
         os.chdir(os.environ['DBS_SERVER_PATH'])
         cmd="ant dist"
         self.execute(cmd,"Ant fail to pass a test on your system, will download it")
      else:
         source="http://apache2.openmirrors.org/ant/binaries/apache-ant-1.6.5-bin.tar.bz2"
         if self._verbose:
            print "Download '%s'"%source
         urllib.urlretrieve(source)

  def checkTomcat(self):
      if os.environ.has_key('CATALINA_HOME'):
         if self._verbose:
            print "Found tomcat in '%s'"%os.environ['CATALINA_HOME']
      else:
         source="http://download.nextag.com/apache/tomcat/tomcat-5/v5.5.20/bin/apache-tomcat-5.5.20.tar.gz"
         if self._verbose:
            print "Download '%s'"%source
         urllib.urlretrieve(source)
  
  def checkComponents(self):
      self.checkAnt()
      self.checkTomcat()

  def writeConfigFile(self):
      return
         
#
# main
#
if __name__ == "__main__":
    optManager  = DBSDeploymentOptionParser()
    (opts,args) = optManager.getOpt()
#    print "options:  ",opts

    db=dbType=userName=userPass=""
    if not opts.dbType:
       raise "Please speficy DB type, e.g. MySQL or ORACLE"
    else:
       dbType=string.upper(opts.dbType)

    if not opts.db:
       raise "Please provide DB name"
    else:
       db=opts.db

    if not opts.userName:
       raise "Please provide user name to access your DB"
    else:
       userName=opts.userName

    if not opts.userPass:
       raise "Please provide password to access your DB"
    else:
       userPass=opts.userPass
       
    version=""
    if opts.version:
       version = opts.version

    prefix=os.getcwd()
    if opts.prefix:
       prefix=opts.prefix
       
    dbsManager = DBSDeployment(dbType,db,userName,userPass,prefix,version,opts.drop,opts.verbose)
#    dbsManager.checkoutDBS()
#    dbsManager.checkComponents()
    dbsManager.checkDBS()
    dbsManager.probeDB()
    dbsManager.createTables()
    dbsManager.checkUnitTests()


