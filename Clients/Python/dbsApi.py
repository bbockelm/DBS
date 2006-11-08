#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
# DBS API class. Interfacing to Server using http/https or local
#
import os, re, string, xml.sax, xml.sax.handler
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsHttpService import DbsHttpService

from dbsException import DbsException
from dbsApiException import *

from dbsBaseObject import *
from dbsRun import DbsRun 
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsProcessedDataset import DbsProcessedDataset
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsLumiSection import DbsLumiSection 
from dbsFile import DbsFile
from dbsFileBlock import DbsFileBlock
from dbsDataTier import DbsDataTier 
from dbsAlgorithm import DbsAlgorithm

from dbsParent import DbsParent

############################################################################

DBS_HOST_URL="cmssrv17.fnal.gov"
DBS_PORT=8989
DBS_SERVLET="/DBS/servlet/DBSServlet"

#DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/dbsxml"

# API Version needs to be updated, each time a change is made to API
# Sever will recive this API version and it might not work 
# if proper API version is not supplied
API_VERSION= "v00_00_01"

#############################################################################

class DbsApi:

  def __init__(self, Url=None, Args={}):
    """ Constructor. """
    #if Url is None:
    #   Url = DEFAULT_URL

    print "WARNING I have to make use of Url !!!!! ANZAR !!!!!"
    print "What about DB Instance and Configuration file ????"
    # API Version needs to be updated, each time a change is made to API
    # Sever will recive this API version and it might not work 
    # if proper API version is not supplied
    self._api_version = API_VERSION
    #
    # Create the Server proxy
    #
    self._server = DbsHttpService(DBS_HOST_URL, DBS_PORT, DBS_SERVLET, self._api_version, Args)
    #
    # 

  def getApiVersion(self):
    """
    Returns the API version of the API
    """
    return sel._api_version

  def _path (self, dataset):
    """
    Determine the dataset path of a dataset.  If the argument is a
    string, it's assumed to be the path and is returned.  If it is a
    a dataset object, we'll ask for it's path.  If that fails, we
    assume the object is a processed datatset and make it's path out
    of the primary dataset, tier and processed dataset name.
    """
    if isinstance(dataset, type("")):
      return dataset

    try:
      if dataset.get('datasetPathName') in ('', None):
         return "/" + dataset.get('primaryDataset').get('datasetName') \
             + "/" + dataset.get('dataTier') + "/" + dataset.get('datasetName')

      return dataset.get('datasetPathName')

    except:
      return "/" + dataset.get('primaryDataset').get('datasetName') \
	     + "/" + dataset.get('dataTier') + "/" + dataset.get('datasetName')

  # ------------------------------------------------------------
  #  dbsApi API Implementation follows
  # ------------------------------------------------------------
  def listPrimaryDatasets(self, pattern="*"):
    """
    Retrieve list of primary datasets matching a shell glob pattern.
    Returns a list of DbsPrimaryDataset objects.  If the pattern is
    given, it will be matched against the dataset name as a shell
    glob pattern.

    May raise an DbsApiException.
    """
 
    # Invoke Server.    
    #data = self._server._call ({ 'api' : 'listPrimaryDatasets', 'pattern' : pattern , 'instance' : 'MCLocal/Writer' })
    data = self._server._call ({ 'api' : 'listPrimaryDatasets', 'pattern' : pattern  }, 'GET')
    print data
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'primary-dataset':
	    result.append(DbsPrimaryDataset (datasetName=str(attrs['primary_name'])))

      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listProcessedDatasets(self, pattern="*"):
    """
    Retrieve list of processed datasets matching a shell glob pattern.
    Returns a list of DbsProcessedDataset objects.  If the pattern is
    given, it will be matched against the dataset path as a shell glob
    pattern.

    May raise an DbsApiException.

    """

    # Invoke Server.    
    data = self._server._call ({ 'api' : 'listDatasets', 'pattern' : pattern }, 'GET')
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        
	def startElement(self, name, attrs):
	  if name == 'processed-dataset':
            self.currDataset = DbsProcessedDataset ( Name=str(attrs['processed_datatset_name']),     
                                                OpenForWriting=str(attrs['open_for_writing']), 
                                                PrimaryDataset=DbsPrimaryDataset(Name=str(attrs['primary_datatset_name']))
                                              )
          if name == 'data_tier':
            self.currDataset['tierList'].append(str(attrs['name']))
          if name == 'algorithm':
            self.currDataset['AlgoList'].append(DbsAlgorithm( ExecutableName=str(attrs['app_executable_name']),
                                                         ApplicationVersion=str(attrs['app_version']),
                                                         ApplicationFamily=str(attrs['app_family_name'])
                                                        ) )
        def endElement(self, name):
          if name == 'processed-dataset':
             result.append(self.currDataset)
      xml.sax.parseString (data, Handler ())
      return result
    except DbsException, ex:
	raise DbsBadResponse(exception=ex)
    except Exception, ex:
	raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listDatasetsFromApp(self, pattern="*"):
    """
    Retrieve list of processed datasets matching a shell glob pattern against 
    application executable or version or family.
    Returns a list of DbsProcessedDataset objects.  If the pattern is
    given, it will be matched against the dataset path as a shell glob
    pattern.

    May raise an DbsApiException.
    """
    # Invoke Server.    
    # data = self._server._call ({ 'api' : 'listProcessedDatasets', 'pattern' : pattern })
    data = self._server._call ({ 'api' : 'listDatasetsFromApp', 'pattern' : pattern })
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'processed-dataset':
            app = DbsApplication(executable = str(attrs['app']),
			    version =  str(attrs['version']),
                            family = str(attrs['family']) )
	    #print "app" , app
	    result.append(DbsProcessedDataset (objectId=long(attrs['id']),
	    				       datasetPathName=str(attrs['path']),
					       application = app))
      xml.sax.parseString (data, Handler ())
      return result
    except DbsException, ex:
	raise DbsBadResponse(exception=ex)
    except Exception, ex:
	raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listParameterSets(self, pattern="*"):
    """
    Retrieve list of parameter sets matching a shell glob pattern.
    Returns a list of DbsParameterSet objects.  If the pattern is
    given, it will be matched against the content as a shell glob
    pattern.

    May raise an DbsApiException.
    """
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listParameterSets', 'pattern' : pattern })

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'parameter-set':
	    result.append(DbsParameterSet (objectId=long(attrs['id']),
					   hash=str(attrs['hash']),
					   content=str(attrs['content'])))
      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listAlgorithms(self, pattern="*"):
    """
    Retrieve list of applications matching a shell glob pattern.
    Returns a list of DbsApplication objects.  If the pattern is
    given, it will be matched against the application label as
    /family/executable/version as a shell glob pattern.

    May raise an DbsApiException.
    """
    # Invoke Server.
    print "\n\n\nlistApplications must change to listAlgorithm on SERVER side\n\n\n"
    #data = self._server._call ({ 'api' : 'listApplications', 'pattern' : pattern }, 'GET')
    data = self._server._call ({ 'api' : 'listApplications', 'pattern' : pattern }, 'GET')

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'algorithm':
            result.append(DbsAlgorithm( ExecutableName=str(attrs['app_executable_name']),
                                                         ApplicationVersion=str(attrs['app_version']),
                                                         ApplicationFamily=str(attrs['app_family_name']),
                                                         ParameterSetID=DbsQueryableParameterSet(hash=str(attrs['parameterset_hash']))
                                                        ) )
      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listApplicationConfigs(self, pattern="*"):
    """
    Retrieve list of application configurations matching a shell glob
    pattern.  Returns a list of DbsApplicationConfig objects.  If the
    pattern is given, it will be matched against the application label
    as /family/executable/version as a shell glob pattern; all the
    parameter sets used with that application will be returned.

    May raise an DbsApiException.
    """
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listApplicationConfigs', 'pattern' : pattern })

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'application':
	    self.app = DbsApplication(executable=str(attrs['executable']),
			              version=str(attrs['version']),
			              family=str(attrs['family']))
            
	  elif name == 'app-config':
	    pset = DbsParameterSet(objectId=long(attrs['psetid']),
				   hash=str(attrs['hash']),
				   content=str(attrs['content']))
	    result.append(DbsApplicationConfig(objectId=long(attrs['id']),
					       application = self.app,
					       parameterSet = pset))
      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def getDatasetProvenance(self, dataset, tiers=[]):
    """
    Retrieve the dataset parents of the dataset.  If tiers is an
    empty list, retrieves all parents.  Otherwise returns only the
    data tiers that match.  The result is a list of DbsParents
    with parentage type set and referring to DbsProcessedDatasets
    with path name and data tier filled in.

    The input dataset should be an DbsProcessedDataset with path
    set, or primary dataset, tier and processed dataset name
    filled in.  For backwards compatibility a simple dataset path
    name string is also accepted.

    Raises InvalidDataTier if tiers includes an unknown data tier,
    InvalidDatasetPathName if the dataset path is invalid,
    otherwise may raise an DbsApiException.
    """
    # Check path.
    path = self._path (dataset)
    verifyDatasetPathName(path)

    # Invoke Server.
    args = { 'api' : 'getDatasetProvenance', 'path' : path }
    if len(tiers): args['datatier'] = string.join(tiers, ",")
    data = self._server._call (args)

    # Parse the resulting xml output.
    try:
      parents = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'parent':
	    p = DbsProcessedDataset(datasetPathName=str(attrs['path']),
			            dataTier=str(attrs['tier']))
	    parents.append(DbsParent(parent=p, type=str(attrs['type'])))
      xml.sax.parseString (data, Handler ())
      return parents
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def getDatasetContents(self, dataset):
    """
    Retrieve the event collections of dataset by file block.  Returns
    a list of DbsFileBlock objects, with event collection list filled
    with DbsEventCollection objects.

    The input dataset should be an DbsProcessedDataset with path set,
    or a DbsProcessedDataset with primary dataset, tier and processed
    dataset name filled in.  For backwards compatibility a simple
    dataset path name string is also accepted.

    Raises InvalidDatasetPathName if the path is invalid, otherwise
    may raise an DbsApiException.

    See getDatasetFiles() for a version that returns files.
    """
    # Check path.
    path = self._path(dataset)
    verifyDatasetPathName(path)

    # Invoke Server.
    data = self._server._call ({ 'api' : 'getDatasetContents', 'path' : path })

    # Parse the resulting xml output.  The output consits of a list of blocks,
    # each with its list of event collections.
    try:
      fileBlocks = {}
      class Handler (xml.sax.handler.ContentHandler):
	def __init__ (self):
	  self._block = None
	def startElement(self, name, attrs):
	  if name == 'block':
	    id = attrs['id']
	    if not fileBlocks.has_key (id):
	      fileBlocks[id] = DbsFileBlock(objectId=long(id), blockName=str(attrs['name']))
	    self._block = fileBlocks[id]
          elif name == 'event-collection':
	    f =  DbsFile (     fileBlockId=long(attrs['inblock']),
                                guid=str(attrs['guid']),
                                logicalFileName=str(attrs['lfn']),
                                fileStatus=str(attrs['fstatus']),
                                checkSum=str(attrs['checksum']),
                                fileSize=long(attrs['size']))
	    self._block['eventCollectionList'].append (DbsEventCollection(fileList=[f], 
	      collectionName=str(attrs['name']), numberOfEvents=long(attrs['events'])))

      xml.sax.parseString (data, Handler ())
      return fileBlocks.values ()
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def getDatasetFileBlocks(self, dataset):
    """
    Retrieve the files related to the dataset, organised by file
    block.  Returns a list of DbsFileBlock objects, each with the
    file list filled with DbsFile objects.  Note that the files
    may contain data for other datasets.

    The input dataset should be an DbsProcessedDataset with path set,
    or a DbsProcessedDataset with primary dataset, tier and processed
    dataset name filled in.  For backwards compatibility a simple
    dataset path name string is also accepted.

    Raises InvalidDatasetPathName if the path is invalid, otherwise
    may raise an DbsApiException.

    See getDatasetContents() for a version that returns event
    collections.
    """
    # Check path.
    path = self._path(dataset)
    verifyDatasetPathName(path)

    # Invoke Server.
    data = self._server._call ({ 'api' : 'getDatasetFiles', 'path' : path })

    # Parse the resulting xml output.  The output consits of a list of blocks,
    # each with its list of event collections.
    try:
      blocks = {}
      class Handler (xml.sax.handler.ContentHandler):
	def __init__ (self):
	  self._block = None
	def startElement(self, name, attrs):
	  if name == 'block':
	    id = attrs['id']
	    if not blocks.has_key (id):
	      blocks[id] = DbsFileBlock (objectId=long(id),
			      		 blockName=str(attrs['name']),
					 numberOfFiles=long(attrs['files']),
					 numberOfBytes=long(attrs['bytes']),
					 blockStatus=str(attrs['status']))
	    self._block = blocks[id]
          elif name == 'file':
	    self._block['fileList'].append(DbsFile (objectId=long(attrs['id']),
		    			  fileBlockId=long(attrs['inblock']),
		    			  guid=str(attrs['guid']),
		    			  logicalFileName=str(attrs['lfn']),
		    			  fileStatus=str(attrs['status']),
		    			  checkSum=str(attrs['checksum']),
		    			  fileSize=long(attrs['size'])))

      xml.sax.parseString (data, Handler ())
      return blocks.values ()
    except Exception, ex:
      raise DbsBadResponse(exception=ex)


  # ------------------------------------------------------------
  def getLFNs(self, blockName , dataset = None):
    # Check path.
    if(dataset) :
      path = self._path(dataset)
      verifyDatasetPathName(path)

    # Invoke Server.
    if (dataset) :
      data = self._server._call ({ 'api' : 'getLFNs', 'path' : path , 'blockName' : blockName})
    else :
      data = self._server._call ({ 'api' : 'getLFNs',  'blockName' : blockName})
    #print data
    # Parse the resulting xml output.  The output consits of a list of blocks,
    # each with its list of event collections.
    try:
      blocks = {}
      mylist = []
      class Handler (xml.sax.handler.ContentHandler):
	def __init__ (self):
	  self._block = None
	def startElement(self, name, attrs):
          """
	  if name == 'block':
	    if not blocks.has_key (blockId):
	      blocks[blockId] = DbsFileBlock (objectId=long(blockId),
                                              blockName=str(attrs['name']))
            self._block = blocks[blockId]
          """  
          if name == 'file':
            """
	    self._block['fileList'].append(DbsFile (
                                          fileBlockId=long(blockId),
		    			  logicalFileName=str(attrs['lfn']),
		    			  fileStatus=str(attrs['status']),
		    			  fileType=str(attrs['type']),
		    			  fileSize=long(attrs['size'])))
            """
            mylist.append( (  str(attrs['lfn']), long(attrs['size']), str(attrs['status']), str(attrs['type']) ) )


      xml.sax.parseString (data, Handler ())
      #print mylist
      #print blocks
      #print "***************"
      #print blocks.values ()
      #return blocks.values ()
      return mylist
    except Exception, ex:
      raise DbsBadResponse(exception=ex)


  # ------------------------------------------------------------
  def createPrimaryDataset(self, dataset):
    """
    Create a new primary dataset.  Instantiates a database entity for
    the dataset, and updates input object for the id of the new row.
    The input object should be a DbsPrimaryDataset with the name set.

    Raises DbsObjectExists if a primary dataset already exists in
    the database, otherwise may raise an DbsApiException.
    """
    data = self._server._call ({ 'api' : 'createPrimaryDataset',
		         #'name' : dataset.get('datasetName') , 'instance' : 'MCLocal/Writer'  })
		         'name' : dataset.get('datasetName') })
    try:
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if (name == 'primary-dataset'):
	    dataset['objectId'] = long(attrs['id'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def createProcessing(self, processing):
    """
    Create a new processing.  Instantiates a database entity for the
    processing, and updates input object for the id of the new row.
    The input object should be a DbsProcessing duly filled in, with
    a reference to a primary dataset and application configuration,
    and optionally a parent.  Application-related information is
    automatically instantiated in the database if it doesn't exist.

    Raises DbsObjectExists if a primary dataset already exists in
    the database, DbsNoObject if the primary dataset, or parent
    if one was specified, doesn't exist in the database, otherwise
    may raise an DbsApiException.
    """
    # Prepare XML description of the input
    appc = processing.get('applicationConfig')
    app = appc.get('application')
    pname = ""
    if processing.get('parent', None) is not None:
      pname = processing.get('parent').get('processingName')
    input = "<dbs><processing name='%s' primary='%s' parent='%s'>" % (
      escape (processing.get('processingName')),
      escape (processing.get('primaryDataset').get('datasetName')),
      escape (pname))
    input += "<application executable='%s' version='%s' family='%s'/>" % (
      escape (app.get('executable')), escape (app.get('version')), escape (app.get('family')))
    input += "<parameter-set hash='%s' content='%s'/>" % (
      escape (appc.get('parameterSet').get('hash')), escape (appc.get('parameterSet').get('content')))
    input += "</processing></dbs>"

    # Call the method and fill in object id
    data = self._server._call ({ 'api' : 'createProcessing', 'xmlinput' : input })
    #data = self._server._call ({ 'api' : 'createProcessing', 'xmlinput' : input , 'instance' : 'MCLocal/Writer' })
    try:
      class Handler (xml.sax.handler.ContentHandler):
	def startElement (self, name, attrs):
	  if (name == 'processing'):
	    processing['objectId'] = long(attrs['id'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def createFileBlock(self, block):
    """
    Create a new file block.  Instantiates a database entity for the
    block, and updates input object for the id of the new row.  The
    input object should be a DbsFileBlock duly filled in, referring
    to a DbsProcessing.  On successful return the block's id and name
    will have been updated; the block will be open.

    Raises DbsNoObject if the processing does not exist in the
    database, otherwise may raise an DbsApiException.
    """
    pname = "/%s/%s" % \
      (block.get('processing').get('primaryDataset').get('datasetName'),
       block.get('processing').get('processingName'))
    if  "blockName" in block.keys():
       data = self._server._call ({ 'api' : 'createFileBlock', 'processing' : pname , 'blockName' : block['blockName'] })
    else :
       data = self._server._call ({ 'api' : 'createFileBlock', 'processing' : pname })
    try:
      class Handler (xml.sax.handler.ContentHandler):
	def startElement (self, name, attrs):
	  if (name == 'block'):
	    block['objectId'] = long(attrs['id'])
	    block['blockName'] = str(attrs['name'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)
 
  def closeFileBlock(self, block):
    """
    Closes a file block.  
    """
    input = "<dbs><block name='%s'/></dbs>" % block.get('blockName').__str__()
    data = self._server._call ({ 'api' : 'closeFileBlock', 'xmlinput' : input })
    
  # ------------------------------------------------------------
  def createProcessedDataset(self, dataset):
    """
    Create a new processed dataset.  Instantiates a database entity
    for the dataset, and updates input object for the id of the new
    row.  The input object should be a DbsProcessedDataset filled in,
    referring to a DbsPrimaryDataset and having data tier and dataset
    name set.  On return the dataset's id will be updated.

    Raises DbsObjectExists if the dataset already exists, or
    DbsNoObject if required path components, the primary dataset
    and the processing name created through createProcessing(), do
    not exist in the database; otherwise may raise an
    DbsApiException.
    """
    # Call the method and fill in object id
    data = self._server._call ({ 'api' : 'createProcessedDataset',
		    #'path' : self._path(dataset), 'instance' : 'MCLocal/Writer'})
		    'path' : self._path(dataset)})
    try:
      class Handler (xml.sax.handler.ContentHandler):
	def startElement (self, name, attrs):
	  if (name == 'processed-dataset'):
	    dataset['objectId'] = long(attrs['id'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def insertFiles(self, block, files):
    """
    Insert files to an existing block.  Instantiates a database row
    for each element of the file list.  The objects are *not* updated
    for database id on return. The block should be a DbsFileBlock.
    The files should be DbsFile objects fully described, including
    name, file size, checksum, type and optionally status.

    Raises DbsObjectExists if any of the files already exists, or
    DbsNoObject if the block does not exist in the database;
    otherwise may raise an DbsApiException.
    """
    # Prepare XML description of the input

    input = "<dbs><block name='%s'>" % escape (block.get('blockName'))
    for f in files:
      input += ("<file lfn='%s' guid='%s' checksum='%s' size='%d'"
		+ " status='%s' type='%s' />") % (
	escape (f.get('logicalFileName')), escape (f.get('guid', '')),
	escape (f.get('checkSum')), f.get('fileSize'),
	escape (f.get('fileStatus', '')), escape (f.get('fileType')))
    input += "</block></dbs>"

    # Call the method.
    data = self._server._call ({ 'api' : 'insertFiles', 'xmlinput' : input })

  # ------------------------------------------------------------
  def insertEventCollections(self, dataset, eventCollections):
    """
    Insert event collections to a processed dataset.  Instantiates a
    database row for each element of the event collection list.  The
    objects are *not* updated for database id on return.  The dataset
    should be a DbsProcessedDataset.  The event collections should
    be DbsEventCollection objects fully described, including name and
    number of events, event collection parentage referring to other
    DbsEventCollection objects, and the list of files as DbsFile the
    collections are mapped to.  The files must have their logical
    names names set, and are assumed to already exist in the database.

    Raises DbsObjectExists if any event collection already exists,
    or DbsNoObject if the processed dataset, parent collections or
    the files do not exist in the database; otherwise may raise an
    DbsApiException.
    """
    # Prepare XML description of the input
    input = "<dbs><processed-dataset path='%s'>" % escape (self._path(dataset))
    for evc in eventCollections:
      input += "<event-collection name='%s' events='%d' status='%s'>" % (
	escape (evc.get('collectionName')), evc.get('numberOfEvents'),
	escape (evc.get('collectionStatus', '')))
      for p in evc.get('parentageList'):
        input += "<parent name='%s' type='%s'/>" % (
	  escape (p.get('parent').get('collectionName')), escape (p.get('type')))
      for f in evc.get('fileList'):
        input += "<file lfn='%s'/>" % escape (f.get('logicalFileName'))
      input += "</event-collection>"
    input += "</processed-dataset></dbs>"

    # Call the method and parse output to fill in object id into each
    # event collection we passed in
    data = self._server._call ({ 'api' : 'insertEventCollections', 'xmlinput' : input })

  # ------------------------------------------------------------
  #def remap(self, eventCollections, outEventCollection, dataset):
  def remap(self, files, outFile):

   input = "<dbs>"
   for f in files:
        input += "<file-in lfn='%s'/>" % escape (f.get('logicalFileName'))
   input += "<file lfn='%s'/>" % escape (outFile.get('logicalFileName'))
   input += "</dbs>"
    
   #print "calling _server._call remap inside dbsApi"
   data = self._server._call ({ 'api' : 'remap',
		         'xmlinput' : input })

 
  # ------------------------------------------------------------
  def getDatasetInfo(self, dataset, blockName=None):
    """
    Retrieve the complete DBS snapshpot of a processed dataset with 
    event collections , files ,processing and blocks

    The input dataset should be an DbsProcessedDataset with path set,
    or a DbsProcessedDataset with primary dataset, tier and processed
    dataset name filled in.  For backwards compatibility a simple
    dataset path name string is also accepted.

    Raises InvalidDatasetPathName if the path is invalid, otherwise
    may raise an DbsApiException.

    """
    # Check path.
    path = self._path(dataset)
    verifyDatasetPathName(path)

    # Invoke Server.
    if(blockName != None) :
	print "blockName ",blockName
	return  self._server._call ({ 'api' : 'getDatasetInfo', 'path' : path , 'blockName' : blockName})
    else :
    	return  self._server._call ({ 'api' : 'getDatasetInfo', 'path' : path })
    

  # ------------------------------------------------------------
  def insertDatasetInfo(self, xmlinput):
    # Check path.
    #path = self._path(dataset)
    #verifyDatasetPathName(path)

    # Invoke Server.
    #data = self._server._call ({ 'api' : 'export', 'path' : path })
    data = self._server._call ({ 'api' : 'insertDatasetInfo', 'xmlinput' : xmlinput })
    return data
 # ------------------------------------------------------------
  
  def setFileStatus(self, name, status=None):
   if (status != None) :
    data = self._server._call ({ 'api' : 'setFileStatus',
		         'name' : name, 
		         'status' : status 
			 })
   else:
    data = self._server._call ({ 'api' : 'setFileStatus',
		         'name' : name, 
			 })
	   
    try:
       class Handler (xml.sax.handler.ContentHandler):
         def startElement(self, name, attrs):
		 pass
       xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)
 
 
  def listBlocks(self, dataset, app=None, events=None):
    # Check path.
    path = self._path(dataset)
    verifyDatasetPathName(path)

    # Invoke Server.
    if( events == None) :    
      if ( app == None ):
         data = self._server._call ({ 'api' : 'listBlocks', 'path' : path })
      else :
         data = self._server._call ({ 'api' : 'listBlocks', 'path' : path, 'app' : app })
    else :
      if ( app == None ):
         data = self._server._call ({ 'api' : 'listBlocks', 'path' : path , 'events' : events})
      else :
         data = self._server._call ({ 'api' : 'listBlocks', 'path' : path , 'events' : events, 'app' : app})

    #print data

    # Parse the resulting xml output.  The output consits of a list of blocks,
    # each with its list of event collections.
    try:
      blocks = {}
      class Handler (xml.sax.handler.ContentHandler):
	def __init__ (self):
	  self._block = None
	def startElement(self, name, attrs):
	  if name == 'block':
            evts = ''
	    id = attrs['id']
	    name = attrs['name']# CHANGED from id to name
	    if not blocks.has_key (id):
              if(events == None) :  
   	         blocks[name] = DbsFileBlock (objectId=long(id),
                                         blockName=str(attrs['name']),
                                         status=str(attrs['status']),
					 numberOfFiles=long(attrs['files']),
					 numberOfBytes=long(attrs['bytes']))
              else :
                 if "events" in attrs.keys():
                    evts = long(attrs['events'])
                 blocks[name] = [evts, str(attrs['status']), long(attrs['files']), long(attrs['bytes'])]

	    self._block = blocks[name]
      xml.sax.parseString (data, Handler ())
      #return blocks.values ()
      return blocks
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

##############################################################################
# Unit testing: see $PWD/UnitTests
