#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853. All rights reserved.
#
# Author:  Valentin Kuznetsov, 2006

"""
DBS Web API
"""

# system modules
import os, re, string, xml.sax, xml.sax.handler
from xml.sax.saxutils import escape
from cStringIO import StringIO

# DBS specific modules
from dbsApi import *

class DbsWebApi(DbsApi):
  """
  DbsWebApi class, provides a wrapper around DbsApi for Web application, e.g.
  DBS/DLS discovery page
  """ 

  def __init__(self, Args={}):
    """ 
    Constructor. 
    """

    DbsApi.__init__(self,Args)

  #-------------------------------------------------------------------

  def listBlocks(self, dataset="*"):
    """
    Retrieve list of Blocks matching a shell glob pattern.
    Returns a list of DbsFileBlock objects.  If the pattern is
    given, it will be matched against the content as a shell glob
    pattern.

    May raise an DbsApiException.

    Please note, that it has the same logic and code as in DbsApi, but return
    type is different, it is dictionary.
    """

    # Invoke Server.
    path = self._path(dataset)
    data = self._server._call ({ 'api' : 'listBlocks', 'path' : path }, 'GET')

    # Parse the resulting xml output.
    try:
      result={}
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'block':
               dbsFileBlock = DbsFileBlock(
                                       Name=str(attrs['name']), 
                                       BlockSize=int(attrs['size']),
                                       NumberOfFiles=int(attrs['number_of_files']),
                                       #OpenForWriting=str(attrs['open_for_writing']),
                                       CreationDate=str(attrs['creation_date']),
                                       CreatedBy=str(attrs['created_by']),
                                       LastModificationDate=str(attrs['last_modification_date']),
                                       LastModifiedBy=str(attrs['last_modified_by']),
                                       )
               result[dbsFileBlock['Name']]=dbsFileBlock

      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listProcessedDatasets(self, patternPrim="*", patternDT="*", patternProc="*",   
                                  patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
    """
    Retrieve list of processed datasets matching a shell glob patterns.
    Returns a list of DbsProcessedDataset objects.  If the pattern(s) are
    given, they will be matched against the dataset primary dataset, data tier, application version,  etc. as a shell glob
    pattern.

    May raise an DbsApiException.

    """

    # Invoke Server.    
    data = self._server._call ({ 'api' : 'listProcessedDatasets', 
		    'primary_datatset_name_pattern' : patternPrim, 
		    'data_tier_name_pattern' : patternDT, 
		    'processed_datatset_name_pattern' : patternProc, 
		    'app_version' : patternVer, 
		    'app_family_name' : patternFam, 
		    'app_executable_name' : patternExe, 
		    'parameterset_name' : patternPS }, 
		    'GET')
 
#    print "Client:API:getProcessedDatasets",data
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        
	def startElement(self, name, attrs):
	  if name == 'processed-dataset':
             self.proc = str(attrs['processed_datatset_name'])
             self.prim = str(attrs['primary_datatset_name'])
          if name == 'data_tier':
             n = "/"+self.prim+"/"+str(attrs['name'])+"/"+self.proc
             result.append(DbsProcessedDataset(datasetPathName=n))

      xml.sax.parseString (data, Handler ())
      return result

    except DbsException, ex:
	raise DbsBadResponse(exception=ex)
    except Exception, ex:
	raise DbsBadResponse(exception=ex)

  def getDatasetDetails(self, patternPrim="*", patternDT="*", patternProc="*",   
                              patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
      return super(DbsWebApi,self).listProcessedDatasets(
                         patternPrim=patternPrim,patternDT=patternDT,patternProc=patternProc,
                         patternVer=patternVer,patternExe=patternExe,patternPS=patternPS)
