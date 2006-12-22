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

