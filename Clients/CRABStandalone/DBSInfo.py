#!/usr/bin/env python
import sys, os, string, re
import xml.sax
import urllib
import DBSXMLHandler

class DBSError:
  def __init__(self, owner, dataset):
    print '\nERROR accessing DBS for Owner/Dataset: '+owner+'/'+dataset+'\n'
    pass

class DBSInfoError:
  def __init__(self, url):
    print '\nERROR accessing DBS url : '+url+'\n'
    pass

##################################################################################
# Class to extract info from DBS 
###############################################################################

class DBSInfo:
     def __init__(self, owner, dataset, dataTiers):
          self.owner = owner
          self.dataset = dataset
          self.dataTiers = dataTiers
          ## DBSXMLDump 
          self.DBSclient_ = '/phedex/SL/PHEDEX_head/Utilities/DBSXMLDump -from DBS '
          self.DBSParam_ = '~/.globus/DBS_DBParam:Production/Admin'
          self.DBSDumpbase_ = self.DBSclient_+" -from DBS -db "+self.DBSParam_
          ## HTTP/CGI 
          self.DBSURL_='http://cern.ch/cms-dbs/cgi-bin/dbsxml?api='

# ####################################
     def getDatasetProvenance(self):
         """
          query DBS to get provenance
         """
         ## DBSXMLDump
         xmlfile= 'parent.xml'
         ListdataTiers=string.join(self.dataTiers,',')
         #print ListdataTiers
         print "DBSInfo: ---> getDatasetProvenance : "+self.owner+"/"+self.dataset+" with datatier "+ListdataTiers
         cmd = self.DBSDumpbase_+" -to "+xmlfile+" -datatier "+ListdataTiers+" -getDatasetProvenance \'"+self.owner+"/"+self.dataset+"\'"
         #print "DBSInfo: executing "+cmd
         os.system(cmd) 
         
         ### parse the XML
         handler = self.XMLparsing(xmlfile)
         os.system('rm '+xmlfile) 

         print "DBSInfo: parents are %s"%handler.parentsList

         for aparent in handler.parent.keys():
           print "DBSInfo: parent is "+aparent+" type: "+handler.parent[aparent]

         ## return a map with parent path and type : should I return just a list with parent paths?
         return handler.parent


# ####################################
     def getDatasetContents(self):
         """
          query DBS to get event collections
         """
         print "DBSInfo: ---> getDatasetContents : "+self.dataset+"/"+self.owner
         try:
            url=self.DBSURL_+'getDatasetContents&path='+self.dataset+'/datatier/'+self.owner
            f = urllib.urlopen(url)
         except:
           raise DBSInfoError(url)

         data = f.read()
         xmlfile = "evc.xml"
         file = open(xmlfile, 'w')
         file.write(data)
         file.close()
                                                                                                                     
         ### parse the XML
         handler = self.XMLparsing(xmlfile)
         os.system('rm '+xmlfile)
                                                                                                                     
         ## get the fileblock and event collections
         nevtsbyblock= {}
         print "DBSInfo: fileblocks are: %s"%handler.fileblocksList

         for block in handler.fileblocksList:
          print "DBSInfo: --- block: "+block
          ## get the event collections for each block
          evcnames=handler.evcbyfileblock[block].keys()
          evcnames.sort()
          nevts=0
          for evcname in evcnames:
           print "DBSInfo:  evc: "+evcname+" nevts: "+handler.evcbyfileblock[block][evcname]
           nevts=nevts+int(handler.evcoll[evcname])
          print "DBSInfo: total nevts %i in block %s "%(nevts,block)
          nevtsbyblock[block]=nevts
                                                                                                                     
         print "DBSInfo: total number of events %i"%handler.totnevts                                                                                                                     
         # returning a map of fileblock-nevts  will be enough for now
         # TODO: in future the EvC collections grouped by fileblock should be returned
                                                                                                                     
         return nevtsbyblock


# ####################################
     def XMLparsing(self, xmlfile):
         """
           parse XML
         """
         #print "\n DBSInfo: Parsing XML file "+xmlfile+"\n"
         parser = xml.sax.make_parser()
         handler = DBSXMLHandler.Handler()
         parser.setContentHandler(handler)
         parser.parse(xmlfile)
         return handler

