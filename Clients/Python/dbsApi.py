#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
# DBS API class. Interfacing to Server using http/https or local
#

# system modules
import os, re, string, xml.sax, xml.sax.handler
from xml.sax.saxutils import escape
from cStringIO import StringIO

# DBS specific modules
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
from dbsConfig import DbsConfig

class DbsApi(DbsConfig):

  def __init__(self, Args={}):
    """ Constructor. """
    DbsConfig.__init__(self,Args)
    #
    # Create the Server proxy
    #
    self._server = DbsHttpService(self.host(), self.port(), self.servlet(), self.version(), Args)
    #
    # 

  def getApiVersion(self):
    """
    Returns the API version of the API
    """
    return sel.version()

  def _path (self, dataset):
    """
    Determine the dataset path of a dataset.  If the argument is a
    string, it's assumed to be the path and is returned.  If it is a
    a dataset object, we'll ask for it's path.  If that fails, we
    assume the object is a processed datatset and make it's path out
    of the primary dataset, tier and processed dataset name.
    """

    if dataset.get('Name') not in ('', None):
         primary = dataset.get('PrimaryDataset')
         if primary != None:
            tier= dataset.get('TierList', [])
            if tier != []:
               return "/" + primary.get('Name') \
                     + "/" + tier[0] + "/" + dataset.get('Name')

    raise InvalidDatasetPathName(Message="The path name is not correct")      

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

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'primary-dataset':
	    result.append(DbsPrimaryDataset (Name=str(attrs['primary_name'])))

      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listProcessedDatasets(self, patternPrim="*", patternDT="*", patternProc="*", patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
    """
    Retrieve list of processed datasets matching a shell glob pattern.
    Returns a list of DbsProcessedDataset objects.  If the pattern is
    given, it will be matched against the dataset path as a shell glob
    pattern.

    May raise an DbsApiException.

    """

    # Invoke Server.    
    data = self._server._call ({ 'api' : 'listDatasets', 
		    'primary_datatset_name_pattern' : patternPrim, 
		    'data_tier_name_pattern' : patternDT, 
		    'processed_datatset_name_pattern' : patternProc, 
		    'app_version' : patternVer, 
		    'app_family_name' : patternFam, 
		    'app_executable_name' : patternExe, 
		    'parameterset_name' : patternPS }, 
		    'GET')
 
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        
	def startElement(self, name, attrs):
	  if name == 'processed-dataset':
            self.currDataset = DbsProcessedDataset ( Name=str(attrs['processed_datatset_name']),     
                                                OpenForWriting=str(attrs['open_for_writing']), 
                                                PrimaryDataset=DbsPrimaryDataset(
                                                        Name=str(attrs['primary_datatset_name'])) )
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
  def listParameterSets(self, pattern="*"):
    """
    Retrieve list of parameter sets matching a shell glob pattern.
    Returns a list of DbsParameterSet objects.  If the pattern is
    given, it will be matched against the content as a shell glob
    pattern.

    May raise an DbsApiException.

    """
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listParameterSets', 'pattern' : pattern }, 'GET')

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'parameter-set':
	    result.append(DbsQueryableParameterSet (
					   Hash=str(attrs['parameterset_hash']),
					   content=str(attrs['content'])))
      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listAlgorithms(self, patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
    """
    Retrieve list of applications matching a shell glob pattern.
    Returns a list of DbsApplication objects.  If the pattern is
    given, it will be matched against the application label as
    /family/executable/version as a shell glob pattern.

    May raise an DbsApiException.
    """
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listAlgorithms',
		    'app_version' : patternVer, 
		    'app_family_name' : patternFam, 
		    'app_executable_name' : patternExe, 
		    'parameterset_name' : patternPS }, 
		    'GET')
 

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'algorithm':
            result.append(DbsAlgorithm( ExecutableName=str(attrs['app_executable_name']),
                                                         ApplicationVersion=str(attrs['app_version']),
                                                         ApplicationFamily=str(attrs['app_family_name']),
                                                         ParameterSetID=DbsQueryableParameterSet
                                                                           (
                                                                             hash=str(attrs['ps_hash'])
                                                                           )
                                                        ) )
      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)


  # ------------------------------------------------------------
  def listRuns(self, path="*"):
    """
    Retrieve list of runs matching a shell glob pattern.
    Returns a list of DbsParameterSet objects.  If the pattern is
    given, it will be matched against the content as a shell glob
    pattern.

    May raise an DbsApiException.

    """
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listRuns', 'path' : path }, 'GET')
    print data

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'run':
               self.currRun= DbsRun (
                                   RunNumber=int(attrs['run_number']),
                                   NumberOfEvents=int(attrs['number_of_events']),
                                   NumberOfLumiSections=int(attrs['number_of_lumi_sections']),
                                   TotalLuminosity=int(attrs['total_luminosity']),
                                   StoreNumber=int(attrs['store_number']),
                                   StartOfRun=str(attrs['start_of_run']),
                                   EndOfRun=str(attrs['end_of_run']))
          if name =='processed-dataset':
               self.currRun['Dataset'].append(DbsProcessedDataset (
                                            Name=str(attrs['processed_datatset_name']),
                                            PrimaryDataset=DbsPrimaryDataset(Name=str(attrs['primary_datatset_name']))
                                            ) )

        def endElement(self, name):
            if name == 'run':
               result.append(self.currRun)
      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------

  def listTiers(self, path="*"):

    """
    Retrieve list of runs matching a shell glob pattern.
    Returns a list of DbsParameterSet objects.  If the pattern is
    given, it will be matched against the content as a shell glob
    pattern.

    May raise an DbsApiException.

    """

    # Invoke Server.
    data = self._server._call ({ 'api' : 'listTiers', 'path' : path }, 'GET')

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'data_tier':
               result.append(DbsDataTier(Name=str(attrs['name'])))

      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  #-------------------------------------------------------------------

  def listBlocks(self, path="*"):
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listBlocks', 'path' : path }, 'GET')

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'block':
               result.append(DbsFileBlock(
                                       Name=str(attrs['name']), 
                                       BlockSize=int(attrs['size']),
                                       NumberOfFiles=int(attrs['number_of_files']),
                                       OpenForWriting=str(attrs['open_for_writing'])
                                       )
                             )

      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)


  # ------------------------------------------------------------

  def listFiles(self, path="*"):
    """
    Retrieve list of runs matching a shell glob pattern.
    Returns a list of DbsParameterSet objects.  If the pattern is
    given, it will be matched against the content as a shell glob
    pattern.

    May raise an DbsApiException.

    """
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listFiles', 'path' : path }, 'GET')

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'file':
             self.currFile = DbsFile (
                                       LogicalFileName=str(attrs['lfn']),
                                       FileSize=int(attrs['size']),
                                       NumberOfEvents=int(attrs['number_of_events']),
                                       Status=str(attrs['status']),
                                       Block=DbsFileBlock(Name=str(attrs['block_name'])),
                                       FileType=str(attrs['type']),
                                       Checksum=str(attrs['checksum']),
                                       QueryableMetadata=str(attrs['queryable_meta_data']) 
                                       )

          if name == 'data_tier':
            self.currFile['tierList'].append(str(attrs['name']))

          if name == 'lumi_section':
             self.currFile['lumiList'].append(DbsLumiSection(
                                                   LumiSectionNumber=int(attrs['lumi_section_number']),
                                                   StartEventNumber=int(attrs['start_event']),
                                                   EndEventNumber=int(attrs['end_event']),   
                                                   LumiStartTime=str(attrs['lumi_start']),
                                                   LumiEndTime=str(attrs['lumi_end']),
                                                   RunNumber=int(attrs['run_number']) 
                                              ))
          if name == 'algorithm':
            self.currFile['AlgoList'].append(DbsAlgorithm( ExecutableName=str(attrs['app_executable_name']),
                                                         ApplicationVersion=str(attrs['app_version']),
                                                         ApplicationFamily=str(attrs['app_family_name'])
                                              )) 
        def endElement(self, name):
          if name == 'file':
             result.append(self.currFile)
  
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

  def insertPrimaryDataset(self, dataset):
    """
    Create a new primary dataset.  Instantiates a database entity for
    the dataset, and updates input object for the id of the new row.
    The input object should be a DbsPrimaryDataset with the name set.

    Raises DbsObjectExists if a primary dataset already exists in
    the database, otherwise may raise an DbsApiException.
    """

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<primary-dataset annotation='aaaa' "
    xmlinput += "primary_name='"+dataset.get('Name')+"' "
    xmlinput += "start_date='NOV' end_date='DEC' trigger_path_description='anyTD' "
    xmlinput += "mc_channel_description='MCDesc' mc_production='MCProd' "
    xmlinput += "mc_decay_chain='DC' other_description='OD' type='PDS'>"
    xmlinput += "</primary-dataset>"
    xmlinput += "</dbs>"

    data = self._server._call ({ 'api' : 'insertPrimaryDataset',
                         'xmlinput' : xmlinput }, 'POST')
    try:
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if (name == 'primary-dataset'):
            #This is just useless now 
            dataset['objectId'] = long(attrs['id'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def insertAlgorithm(self, algorithm):

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

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<algorithm app_version='"+algorithm.get('ApplicationVersion', "")+"'"
    xmlinput += " app_family_name='"+algorithm.get('ApplicationFamily', "")+"'"
    xmlinput += " app_executable_name='"+algorithm.get('ExecutableName', "")+"'"
    pset = algorithm.get('ParameterSetID')

    if pset != None: 
       xmlinput += " ps_hash='"+pset.get('Hash', "")+"'"
       xmlinput += " ps_name='"+pset.get('Name', "")+"'"
       xmlinput += " ps_version='"+pset.get('Version', "")+"'"
       xmlinput += " ps_type='"+pset.get('Type', "")+"'"
       xmlinput += " ps_annotation='"+pset.get('Annotation', "")+"'"
       xmlinput += " ps_content='"+pset.get('Content', "")+"'"
    xmlinput += "/>"
    xmlinput += "</dbs>"

    data = self._server._call ({ 'api' : 'insertAlgorithm',
                         'xmlinput' : xmlinput }, 'POST')
    try:
      class Handler (xml.sax.handler.ContentHandler):
	def startElement (self, name, attrs):
	  if (name == 'algorithm'):
            #This is useless for now
	    processing['objectId'] = long(attrs['id'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------

  def insertProcessedDataset(self, dataset):
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

    xmlinput  = "<?xml version='1.0' standalone='yes'?>" 
    xmlinput += "<dbs>" 
    xmlinput += "<processed-dataset "
    primary = dataset.get('PrimaryDataset')
    if primary == None: 
       raise DbsApiException(ErrorMsg="Serious Error Primary Dataset not specified")
    xmlinput += " primary_datatset_name='"+primary.get('Name', "")+"'" 
    xmlinput += " processed_datatset_name='"+dataset.get('Name', "")+"'"
    xmlinput += " open_for_writing='y'"
    xmlinput += " physics_group_name='"+dataset.get('PhysicsGroup', "")+"'"
    xmlinput += " physics_group_convener='"+dataset.get('Convener', "")+"'"
    xmlinput += " status='"+dataset.get('Status', "")+"'>" 
    
    for tier in dataset.get('TierList',[]):
        xmlinput += "<data_tier name='"+tier+"'/>"
 
    # Path of the Parent Dataset(s) must be specified, sever expects a "Path"
    for parentPath in dataset.get('ParentList',[]):
        xmlinput += "<parent path='"+parentPath+"'/>"

    for algorithm in dataset.get('AlgoList',[]):
        xmlinput += "<algorithm app_version='"+algorithm.get('ApplicationVersion', "")+"'"
        xmlinput += " app_family_name='"+algorithm.get('ApplicationFamily', "")+"'"
        xmlinput += " app_executable_name='"+algorithm.get('ExecutableName', "")+"'"
        pset = algorithm.get('ParameterSetID')
        # Server expects a ps_name, it should expect a ps_hash instead 
        if pset != None:
           xmlinput += " ps_hash='"+pset.get('Hash', "")+"'"
           xmlinput += " ps_name='"+pset.get('Name', "")+"'"
           xmlinput += " ps_version='"+pset.get('Version', "")+"'"
           xmlinput += " ps_type='"+pset.get('Type', "")+"'"
           xmlinput += " ps_annotation='"+pset.get('Annotation', "")+"'"
           xmlinput += " ps_content='"+pset.get('Content', "")+"'"
           xmlinput += "/>"
    xmlinput += "</processed-dataset>"
    xmlinput += "</dbs>"

    #print xmlinput

    # Call the method
    data = self._server._call ({ 'api' : 'insertProcessedDataset',
                         'xmlinput' : xmlinput }, 'POST')

    try:
      # Following code is useless so far !!!!!!!!!
      class Handler (xml.sax.handler.ContentHandler):
	def startElement (self, name, attrs):
	  if (name == 'processed-dataset'):
	    dataset['objectId'] = long(attrs['id'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def insertFiles(self, dataset, files, block):
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

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += " <processed_datatset path='"+self._path(dataset)+"'"
    if block != None:
       xmlinput += " block_name='"+block.get("Name", "")+"'"
    xmlinput += " >"
    
    for file in files:
       xmlinput += " <file lfn='"+file.get('LogicalFileName', '')+"'"
       xmlinput += " checksum='"+file.get('Checksum', '')+"'"
       xmlinput += " number_of_events='"+str(file.get('NumberOfEvents', ''))+"'"
       xmlinput += " size='"+str(file.get('FileSize', ''))+"'"
       xmlinput += " file_status='"+file.get('Status', '')+"'" 
       xmlinput += " type= '"+file.get('FileType', '')+"'"
       xmlinput += " validation_status='"+file.get('ValidationStatus', '')+"'"
       xmlinput += " queryable_meta_data='"+file.get('QueryableMetadata', '')+"'"
       xmlinput += " >" 
       
       for lumi in file.get('LumiList', []):
            xmlinput += "<lumi_section lumi_section_number='"+str(lumi.get('LumiSectionNumber', ''))+"'"
            xmlinput += " run_number='"+str(lumi.get('RunNumber', ''))+"'"
            xmlinput += " start_event_number='"+str(lumi.get('StartEventNumber', ''))+"'" 
            xmlinput += " end_event_number='"+str(lumi.get('EndEventNumber', ''))+"'"
            xmlinput += " lumi_start_time='"+lumi.get('LumiStartTime', '')+"'" 
            xmlinput += " lumi_end_time='"+lumi.get('LumiEndTime', '')+"'"
            xmlinput += " />"

       for tier in file.get('TierList',[]):
            xmlinput += "<data_tier name='"+tier+"'/>"
    
       # Path of the Parent Dataset(s) must be specified, sever expects a "Path"
       for parent in file.get('ParentList',[]):
            xmlinput += "<parent lfn='"+parent+"'/>"

       for algorithm in file.get('AlgoList',[]):
           xmlinput += "<algorithm app_version='"+algorithm.get('ApplicationVersion', "")+"'"
           xmlinput += " app_family_name='"+algorithm.get('ApplicationFamily', "")+"'"
           xmlinput += " app_executable_name='"+algorithm.get('ExecutableName', "")+"'"
           pset = algorithm.get('ParameterSetID')
           # Server expects a ps_name, it should expect a ps_hash instead 
           if pset != None:
              xmlinput += " ps_hash='"+pset.get('Hash', "")+"'"
              xmlinput += " ps_name='"+pset.get('Name', "")+"'"
              xmlinput += " ps_version='"+pset.get('Version', "")+"'"
              xmlinput += " ps_type='"+pset.get('Type', "")+"'"
              xmlinput += " ps_annotation='"+pset.get('Annotation', "")+"'"
              xmlinput += " ps_content='"+pset.get('Content', "")+"'"
              xmlinput += "/>"
       xmlinput += "</file>"
       xmlinput += "\n"

    xmlinput += "</processed_datatset>"
    xmlinput += "</dbs>"

    print xmlinput

    # Call the method
    data = self._server._call ({ 'api' : 'insertFiles',
                         'xmlinput' : xmlinput }, 'POST')

  # ------------------------------------------------------------

  def insertBlock(self, block):
    """
    Create a new primary dataset.  Instantiates a database entity for
    the dataset, and updates input object for the id of the new row.
    The input object should be a DbsPrimaryDataset with the name set.

    Raises DbsObjectExists if a primary dataset already exists in
    the database, otherwise may raise an DbsApiException.
    """

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<block name='"+block.get('Name', '')+"'"
    procdataset = block.get('Dataset') 
    xmlinput += " path='"+self._path(procdataset)+"'/>"
    xmlinput += "</dbs>"

    print xmlinput

    data = self._server._call ({ 'api' : 'insertBlock',
                         'xmlinput' : xmlinput }, 'POST')
    try:
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if (name == 'primary-dataset'):
            #This is just useless now 
            dataset['objectId'] = long(attrs['id'])
      xml.sax.parseString (data, Handler())
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

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
 
 
##############################################################################
# Unit testing: see $PWD/UnitTests
