#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
# DBS API class. Interfacing to Server using http/https or local
#

# system modules
import os, re, string, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

# DBS specific modules
from dbsHttpService import DbsHttpService
from dbsExecService import DbsExecService

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
import urlparse
import urllib2

def getInt(value = None):
	#print "value is ", value
	if (value == None ) :
		return 0
	if (len(value) < 1 ) :
		return 0
	return int(value)

class DbsApi(DbsConfig):
  """
  DbsApi class, provides access to DBS Server, 
  all clients must use this interface 
  """ 

  def __init__(self, Args={}):
    """ 
    Constructor. 
    Initializes the DBS Api by reading configuration 
    parameters from dbs.config file OR from the parameters 
    passed through Args as a Python dictionary.
    Parameters passed through Args take precedence 
    over parameters in dbs.config
    
    For MODE=POST (Default Mode): Creates a http proxy, to be able to talk to DBS Server
    """

    DbsConfig.__init__(self,Args)
    if self.verbose():
       print "configuration dictionary:", self.configDict
       print "using version",self.version()
       print "using mode   ",self.mode()
    #
    # Connect to the Server proxy
    #
    self._server = ""
    if self.mode() == "EXEC" :
	    self._server = DbsExecService(self.dbshome(), self.javahome(), self.version(), Args)
    else :
	    spliturl = urlparse.urlparse(self.url())
	    hostport=urllib2.splitport(spliturl[1])  
	    host=hostport[0]
	    port=hostport[1]
	    servlet=spliturl[2] 
            if self.verbose():
               print "using url   ", self.url()
            self._server = DbsHttpService(host, port, servlet, self.version(), Args)

  def getApiVersion(self):
    """
    Returns the API version of the API
    """
    return sel.version()

  def _path (self, dataset):
    """
    Determine the dataset path of a dataset.  If the argument is a
    string, it's assumed to be the path and is returned.  If the 
    argument is an object, its assumed to be a processed datatset 
    and this function make a path (string) out of its  primary dataset, 
    tier and processed dataset name.

    Note: takes the FIRST Tier from the list of tiers
          A dataset can have multiple tiers, and however you contruct the path
          using any tier, still leads to same processed dataset, so picking 
          first tier doen't havm the operations. 
    """

    if dataset == None:
	    return ""
    if type(dataset) == type(''):
       return dataset
 
    if dataset.get('Name') not in ('', None):
         primary = dataset.get('PrimaryDataset')
         if primary != None:
            tier= dataset.get('TierList', [])
            if tier != []:
               return "/" + primary.get('Name') \
                     + "/" + tier[0] + "/" + dataset.get('Name')

    # Anything missing (name, primary or tier) thats an error 
    raise InvalidDatasetPathName(Message="The dataset/path provided is incorrect")      

  def _name (self, obj):
    """
    A utility function, that gets "Name" from an Object.
    Not a very cool function !
    """

    if obj == None:
	    return "";
    if type(obj) == type(''):
       return obj
    name = obj.get('Name')
    if name ==  None:
	    return ""
    return name

  # ------------------------------------------------------------
  #  dbsApi API Implementation follows
  # ------------------------------------------------------------
  def listPrimaryDatasets(self, pattern="*"):
    """
    Retrieve list of primary datasets matching a shell glob pattern.
    Returns a list of DbsPrimaryDataset objects.  If the pattern is
    given, it will be matched against the dataset name as a shell
    glob pattern.

    
    params:
          pattern:  takes a dataset path pattern, defult value is "*"
    returns: 
          list of DbsPrimaryDataset objects  
    examples: 
          api.listPrimaryDatasets() : List ALL primary Datasets in DBS
          api.listPrimaryDatasets("*") : List ALL primary Datasets in DBS
          api.listPrimaryDatasets('MyPrimaryDataset001') : List MyPrimaryDataset001
          api.listPrimaryDatasets('MyPrimaryDataset*') : List All Primary datasets whoes names start with MyPrimaryDataset

    raise: DbsApiException, DbsBadResponse
             
    """
 
    # Invoke Server.    
    data = self._server._call ({ 'api' : 'listPrimaryDatasets', 'pattern' : pattern  }, 'GET')
    if self.verbose():
       print data

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
	def startElement(self, name, attrs):
	  if name == 'primary-dataset':
	    result.append(DbsPrimaryDataset (
                                             Name=str(attrs['primary_name']),
                                             CreationDate=str(attrs['creation_date']),
                                             CreatedBy=str(attrs['created_by']),
                                             LastModificationDate=str(attrs['last_modification_date']),
                                             LastModifiedBy=str(attrs['last_modified_by']),
                                            )
                         )

      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listProcessedDatasets(self, patternPrim="*", patternDT="*", patternProc="*",   
                                  patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
    """
    Retrieve list of processed datasets matching shell glob patterns for dataset and application
    User can provide a comprehensive list of parameters for which he/she want to find dataset(s).
    The search criteria can be based of a pattern for Primary dataset, Processed Dataset, Data Tier,
    Application version, Application Family, Application executyable name and ParameterSet name.

    Returns a list of DbsProcessedDataset objects.  If the pattern(s) are

    params:
        patternPrim: glob pattern for Primary Dataset, MyPrimaryDataset001, MyPrimaryDataSets*, *
        patternDT: glob pattern for Data Tier, SIM, RECO, SIM-DIGI, SIM*, *
        patternProc: glob pattern for Processed Dataset, MyProcDataset001, MyProcDataset*, *
        patternVer: glob pattern for Application Version, v00_00_01, *
        patternFam: glob pattern for Application Family, GEN, *
        patternExe: glob pattern for Application Executable Name, CMSSW, writeDigi, *
        patternPS: glob pattern for PSet Name, whatever, *
 
    returns: list of DbsProcessedDataset objects  

    examples:
           Say I want to list all datasets that have a Primary Dataset of type MyPrimaryDatasets*, with SIM data tier,
           and application version v00_00_03, produced by Application CMSSW, I can make my call as,

           api.listProcessedDatasets('MyPrimaryDatasets*', 'SIM', '*', 'v00_00_03', '*', 'CMSSW', '*') 
   
    raise: DbsApiException, DbsBadResponse

    """

    # Invoke Server.    
    data = self._server._call ({ 'api' : 'listProcessedDatasets', 
		    'primary_datatset_name_pattern' : patternPrim, 
		    'data_tier_name_pattern' : patternDT, 
		    'processed_datatset_name_pattern' : patternProc, 
		    'app_version' : patternVer, 
		    'app_family_name' : patternFam, 
		    'app_executable_name' : patternExe, 
		    'ps_hash' : patternPS }, 
		    'GET')
 
    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        
	def startElement(self, name, attrs):
	  if name == 'processed-dataset':
            self.currDataset = DbsProcessedDataset ( 
                                                Name=str(attrs['processed_datatset_name']),     
                                                #OpenForWriting=str(attrs['open_for_writing']), 
                                                PrimaryDataset=DbsPrimaryDataset(Name=str(attrs['primary_datatset_name'])),
                                                CreationDate=str(attrs['creation_date']),
                                                CreatedBy=str(attrs['created_by']),
                                                LastModificationDate=str(attrs['last_modification_date']),
                                                LastModifiedBy=str(attrs['last_modified_by']),
                                                )
          if name == 'data_tier':
            self.currDataset['TierList'].append(str(attrs['name']))

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

    except Exception, ex:
	raise DbsBadResponse(exception=ex)

  # ------------------------------------------------------------
  def listAlgorithms(self, patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
    """
    Retrieve list of applications/algorithms matching a shell glob pattern.
    User can base his/her search on patters for Application Version, 
    Application Family, Application Executable Name or Parameter Set.

    returns:  list of DbsApplication objects.  

    params:
        patternVer: glob pattern for Application Version, v00_00_01, *
        patternFam: glob pattern for Application Family, GEN, *
        patternExe: glob pattern for Application Executable Name, CMSSW, writeDigi, *
        patternPS: glob pattern for PSet Name, whatever, *
 
    raise: DbsApiException.
    examples:
           Say I want to list all listAlgorithms that have application version v00_00_03, 
           produced by Application CMSSW, I can make my call as,

                 api.listAlgorithms('v00_00_03', '*', 'CMSSW', '*') 

           List ALL Algorithms know to DBS

                 api.listAlgorithms("*")

    """
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listAlgorithms',
		    'app_version' : patternVer, 
		    'app_family_name' : patternFam, 
		    'app_executable_name' : patternExe, 
		    'ps_hash' : patternPS }, 
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
                                                           Hash=str(attrs['ps_hash']),
                                                           Name=str(attrs['ps_name']),
                                                           Version=str(attrs['ps_version']),
                                                           Type=str(attrs['ps_type']),
                                                           Annotation=str(attrs['ps_annotation']),
                                                           Content=base64.decodestring(str(attrs['ps_content']))
                                                           ),
                                                         CreationDate=str(attrs['creation_date']),
                                                         CreatedBy=str(attrs['created_by']),
                                                         LastModificationDate=str(attrs['last_modification_date']),
                                                         LastModifiedBy=str(attrs['last_modified_by']),
                                                        ) )
      xml.sax.parseString (data, Handler ())
      return result
    except Exception, ex:
      raise DbsBadResponse(exception=ex)


  # ------------------------------------------------------------
  def listRuns(self, dataset):
    """
    Retrieve list of runs matching a shell glob pattern within a dataset (Processed Dataset).

    returns: list of DbsRun objects.
  
    params:
        dataset: No Default user has to provide a value. 

    examples: 

        List ALL Runs for Dataset Path /test_primary_anzar_001/SIM/TestProcessedDS002 

           api.listRuns("/test_primary_anzar_001/SIM/TestProcessedDS002")
        
        List ALL Runs for ALL Datasets
       
           api.listRuns("*")
                Or
           api.listRuns() 

       Using a Dataset Object 
            primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
            proc = DbsProcessedDataset (
                            PrimaryDataset=primary,
                            Name="TestProcessedDS002",
                            PhysicsGroup="BPositive",
                            Status="VALID",
                            TierList=['SIM', 'RECO'],
                            AlgoList=[WhateverAlgoObject],
                            )
             api.listRuns(proc)


    raise: an DbsApiException.

    """
    path = self._path(dataset)
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listRuns', 'path' : path }, 'GET')
    #print data

    # Parse the resulting xml output.
    try:
      result = []

      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'run':
               self.currRun= DbsRun (
                                   RunNumber=getInt(attrs['run_number']),
                                   NumberOfEvents=getInt(attrs['number_of_events']),
                                   NumberOfLumiSections=getInt(attrs['number_of_lumi_sections']),
                                   TotalLuminosity=getInt(attrs['total_luminosity']),
                                   StoreNumber=getInt(attrs['store_number']),
                                   StartOfRun=str(attrs['start_of_run']),
                                   EndOfRun=str(attrs['end_of_run']),
                                   CreationDate=str(attrs['creation_date']),
                                   CreatedBy=str(attrs['created_by']),
                                   LastModificationDate=str(attrs['last_modification_date']),
                                   LastModifiedBy=str(attrs['last_modified_by']),
                                  )
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

  def listTiers(self, dataset):

    """
    Retrieve list of Tiers matching a shell glob pattern for a dataset (Processed Dataset).

    returns: list of DbsDataTier objects.

    params:
        dataset: Not Defaulted user need to provide a dataset path.

    examples: 

        List ALL Data Tiers for Dataset Path /test_primary_anzar_001/SIM/TestProcessedDS002 

           api.listTiers("/test_primary_anzar_001/SIM/TestProcessedDS002")

       Using a Dataset Object 
            primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
            proc = DbsProcessedDataset (
                            PrimaryDataset=primary,
                            Name="TestProcessedDS002",
                            PhysicsGroup="BPositive",
                            Status="VALID",
                            TierList=['SIM', 'RECO'],
                            AlgoList=[WhateverAlgoObject],
                            )
             api.listTiers(proc)
        
    raise: DbsApiException.

    """

    path = self._path(dataset)
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listTiers', 'path' : path }, 'GET')

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'data_tier':
               result.append(DbsDataTier(
                                          Name=str(attrs['name']),
                                          CreationDate=str(attrs['creation_date']),
                                          CreatedBy=str(attrs['created_by']),
                                          LastModificationDate=str(attrs['last_modification_date']),
                                          LastModifiedBy=str(attrs['last_modified_by']),
                                        )
                            )

      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  #-------------------------------------------------------------------

  def listBlocks(self, dataset, block_name="*", storage_element_name="*"):
    """
    Retrieve list of Blocks matching shell glob pattern for Block Name and/or 
    Storage Element Name, for a dataset path (or glob pattern for dataset path).

    returns: list of DbsFileBlock objects.

    params:
        dataset: Not Defaulted user need to provide a dataset path 
        block_name: pattern, if provided it will be matched against the content as a shell glob pattern
        storage_element_name: pattern, if provided it will be matched against the content as a shell glob pattern
         
    raise: DbsApiException.

    examples:

      All Blocks from path /test_primary_001/SIM/TestProcessedDS001
         api.listBlocks("/test_primary_001/SIM/TestProcessedDS001") 
      Block from path /test_primary_001/SIM/TestProcessedDS001 whoes name is /this/hahah#12345
           api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/this/hahah#12345"):
      All Blocks from path /test_primary_001/SIM/TestProcessedDS001 whoes name starts with /this/*
           api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/this/*"):

      Using a Dataset Object 
            primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
            proc = DbsProcessedDataset (
                            PrimaryDataset=primary,
                            Name="TestProcessedDS002",
                            PhysicsGroup="BPositive",
                            Status="VALID",
                            TierList=['SIM', 'RECO'],
                            AlgoList=[WhateverAlgoObject],
                            )
             api.listBlocks(proc)

    """

    # Invoke Server.
    path = self._path(dataset)
    data = self._server._call ({ 'api' : 'listBlocks', 'path' : path, 'block_name' : block_name, 'storage_element_name' : storage_element_name }, 'GET')

    # Parse the resulting xml output.
    try:
      result = []
      class Handler (xml.sax.handler.ContentHandler):
        def startElement(self, name, attrs):
          if name == 'block':
               self.currBlock = DbsFileBlock(
                                       Name=str(attrs['name']), 
                                       BlockSize=int(attrs['size']),
                                       NumberOfFiles=int(attrs['number_of_files']),
                                       NumberOfEvents=int(attrs['number_of_events']),
                                       OpenForWriting=str(attrs['open_for_writing']),
                                       CreationDate=str(attrs['creation_date']),
                                       CreatedBy=str(attrs['created_by']),
                                       LastModificationDate=str(attrs['last_modification_date']),
                                       LastModifiedBy=str(attrs['last_modified_by']),
                                       )
          if name == 'storage_element':
               self.currBlock['StorageElementList'].append(str(attrs['storage_element_name']))
	       
        def endElement(self, name):
          if name == 'block':
             result.append(self.currBlock)
  

      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)


  # ------------------------------------------------------------

  def listFiles(self, dataset, blockName="", patternLFN="*"):
    """
    Retrieve list of files in a dataset, in a block, or matching pattern of LFNs, 
    or any combinition of dataset, block and or LFN pattern.

    returns: list of DbsFile objects

    params: 
        dataset: Not Defaulted User must provide a value
         
        blockName: Defaulted to "" means files (That match dataset and/or LFN pattern criteria). 
        If the blockName is given, it will be matched against the block name.
         
        patternLFN: Defaulted to "*" means files (That match dataset and/or LFN pattern criteria). 
        If the patternLFN patterm is given, it will be matched against the content as a shell glob pattern.
         
    raise: DbsApiException.

    examples:
          List all files in path /PrimaryDS_01/SIM/procds-01
             api.listFiles("/PrimaryDS_01/SIM/procds-01")
          List all files in path /PrimaryDS_01/SIM/procds-01, with LFNs that start with 'GoodFile'
             api.listFiles("/PrimaryDS_01/SIM/procds-01", "", "GoodFile*")
          List all files in block /this/block#1230-87698
             api.listFiles("", "/this/block#1230-87698")

          Using a Dataset Object 
            primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
            proc = DbsProcessedDataset (
                            PrimaryDataset=primary,
                            Name="TestProcessedDS002",
                            PhysicsGroup="BPositive",
                            Status="VALID",
                            TierList=['SIM', 'RECO'],
                            AlgoList=[WhateverAlgoObject],
                            )
             api.listFiles(proc)

          etc etc.
    """
    path = self._path(dataset)
    # Invoke Server.
    data = self._server._call ({ 'api' : 'listFiles', 'path' : path, 'block_name' : blockName, 'pattern_lfn' : patternLFN }, 'GET')

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
                                       QueryableMetadata=str(attrs['queryable_meta_data']),
                                       CreationDate=str(attrs['creation_date']),
                                       CreatedBy=str(attrs['created_by']),
                                       LastModificationDate=str(attrs['last_modification_date']),
                                       LastModifiedBy=str(attrs['last_modified_by']),
                                       )

          if name == 'data_tier':
            self.currFile['TierList'].append(str(attrs['name']))

          if name == 'lumi_section':
             self.currFile['LumiList'].append(DbsLumiSection(
                                                   LumiSectionNumber=int(attrs['lumi_section_number']),
                                                   StartEventNumber=int(attrs['start_event']),
                                                   EndEventNumber=int(attrs['end_event']),   
                                                   LumiStartTime=str(attrs['lumi_start']),
                                                   LumiEndTime=str(attrs['lumi_end']),
                                                   RunNumber=int(attrs['run_number']),
                                                   CreationDate=str(attrs['creation_date']),
                                                   CreatedBy=str(attrs['created_by']),
                                                   LastModificationDate=str(attrs['last_modification_date']),
                                                   LastModifiedBy=str(attrs['last_modified_by']), 
                                              ))
          if name == 'algorithm':
            self.currFile['AlgoList'].append(DbsAlgorithm( ExecutableName=str(attrs['app_executable_name']),
                                                         ApplicationVersion=str(attrs['app_version']),
                                                         ApplicationFamily=str(attrs['app_family_name']),
                                                         CreationDate=str(attrs['creation_date']),
                                                         CreatedBy=str(attrs['created_by']),
                                                         LastModificationDate=str(attrs['last_modification_date']),
                                                         LastModifiedBy=str(attrs['last_modified_by']),
                                              ) ) 
        def endElement(self, name):
          if name == 'file':
             result.append(self.currFile)
  
      xml.sax.parseString (data, Handler ())
      return result

    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  #-------------------------------------------------------------------

  def listDatasetContents(self, path, block_name):
    """
    Dumps contents of a block in dataset in XML format.
    This API call is used for insertDatasetContents, which actually use this XML dump and
    repopulates (another) instance of DBS with same dataset 

    params: 
        path : Not Defaulted. Its the dataset path for which API is being invoked (can be provided as dataset object).
        block_name : Name of the Block thats being dumped.

    examples:
        Dump the contents of Block /this/block#1230-87698 for Dataset /PrimaryDS_01/SIM/procds-01
                  api.listDatasetContents("/PrimaryDS_01/SIM/procds-01", "/this/block#1230-87698") 
        
        Using a Dataset Object 
            primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
            proc = DbsProcessedDataset (
                            PrimaryDataset=primary,
                            Name="TestProcessedDS002",
                            PhysicsGroup="BPositive",
                            Status="VALID",
                            TierList=['SIM', 'RECO'],
                            AlgoList=[WhateverAlgoObject],
                            )
             api.listDatasetContents(proc, "/this/block#1230-87698")
 
    raisei: DbsApiException.

    """

    try:
       # Invoke Server.
       path = self._path(path)
       data = self._server._call ({ 'api' : 'listDatasetContents', 'path' : path, 'block_name' : block_name }, 'GET')
       return data

    except Exception, ex:
      raise DbsBadResponse(exception=ex)

  #-------------------------------------------------------------------

  def insertDatasetContents(self, xmlinput):
    """

    This API call is used for inserting Dataset from XML dump generated by listDatasetContents
    The APIrepopulates (another) instance of DBS with same dataset as 
    produced by the listDatasetContents counterpart 

    params: 
        xmlinput : XML dump generated by listDatasetContents

    examples:

        Dump the contents of Block /this/block#1230-87698 for Dataset /PrimaryDS_01/SIM/procds-01
                  api.listDatasetContents("/PrimaryDS_01/SIM/procds-01", "/this/block#1230-87698") 
        
        Using a Dataset Object 
            primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
            proc = DbsProcessedDataset (
                            PrimaryDataset=primary,
                            Name="TestProcessedDS002",
                            PhysicsGroup="BPositive",
                            Status="VALID",
                            TierList=['SIM', 'RECO'],
                            AlgoList=[WhateverAlgoObject],
                            )
             xmldataset = api.listDatasetContents(proc, "/this/block#1230-87698")
             api.insertDatasetContents(xmldataset)

    May raise an DbsApiException.

    """

    try:
       # Invoke Server.
       data = self._server._call ({ 'api' : 'insertDatasetContents', 'xmlinput' : xmlinput }, 'POST')
       return data

    except Exception, ex:
      raise DbsBadResponse(exception=ex)


 # ------------------------------------------------------------

  def insertPrimaryDataset(self, dataset):
    """
    Inserts a new primary dataset in the DBS databse. 
    
    param: 
	dataset : The primary dataset passed in as DbsPrimaryDataset object.  The following are mandatory and should be present
	          in the dbs primary dataset object:  primary_name	  
		  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
         api.insertPrimaryDataset (primary)

    """

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<primary-dataset annotation='"+dataset.get('Annotation', '')+"' "
    xmlinput += " primary_name='"+dataset.get('Name', '')+"' "
    xmlinput += " start_date='"+dataset.get('StartDate', '')+"' end_date='"+dataset.get('EndDate', '')+"'"
    xmlinput += " description='"+dataset.get('Description', '')+"'"
    #xmlinput += " trigger_path_description='"+dataset.get('TriggerPathDesc', '')+"'"
    #xmlinput += " mc_channel_description='"+dataset.get('McChannelDesc', '')+"' mc_production='"+dataset.get('McProdDesc', '')+"'"
    #xmlinput += " mc_decay_chain='"+dataset.get('McDecayChain', '')+"' other_description='"+dataset.get('OtherDesc', '')
    xmlinput += "' type='"+dataset.get('Type', '')+"'>"
    xmlinput += " </primary-dataset>"
    xmlinput += "</dbs>"

    if self.verbose():
       print "insertPrimaryDataset, xmlinput",xmlinput
    data = self._server._call ({ 'api' : 'insertPrimaryDataset',
                         'xmlinput' : xmlinput }, 'POST')
  # ------------------------------------------------------------
  def insertAlgorithm(self, algorithm):

    """
    Inserts a new dbs algorithm/application . An algorithm is uniquely identified by appverion, appfamily , 
    appexecutable and parametersetname collectively. If the algorithm already exist then it just displays a warnning.
    
    param: 
        algorithm : The dbs algorithm passed in as an DbsAlgorithm object. The following are mandatory and should be present
	          in the dbs algorithm object:
		  app_version, app_family_name, app_executable_name and ps_name
		  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         algo = DbsAlgorithm (
                ExecutableName="TestExe01",
                ApplicationVersion= "TestVersion01",
                ApplicationFamily="AppFamily01",
                ParameterSetID=DbsQueryableParameterSet(
                     Hash="001234565798685",
                     Name="MyFirstParam01",
                     Version="V001",
                     Type="test",
                     Annotation="This is test",
                     Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                )
         )

         api.insertAlgorithm (algo)
	
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
       # Converting Content to base64 encoded string, otherwise it can leave the xml invalid
       xmlinput += " ps_content='"+base64.binascii.b2a_base64(pset.get('Content', ""))+"'"
    xmlinput += "/>"
    xmlinput += "</dbs>"
    #print xmlinput 
    if self.verbose():
       print "insertAlgorithm, xmlinput",xmlinput
    data = self._server._call ({ 'api' : 'insertAlgorithm',
                         'xmlinput' : xmlinput }, 'POST')

  # ------------------------------------------------------------

  def insertProcessedDataset(self, dataset):
    """
    Inserts a new dbs processed dataset in an existing primary dataset . It insert all the parents of the processed dataset, 
    insert and assocaite  all the tiers of the processed dataset, associate all the algorithms of the processed dataset and 
    associate all the runs of the processed dataset. 
    The parents, algorithms and runs of the processed dataset should exist before the  processed dataset could be inserted.
    
    param: 
        dataset : The procsssed dataset can be passed as an DbsProcessedDataset object. The following are mandatory and should be present
	          in the dbs procsssed dataset object:
		  processed_datatset_name and primary_datatset_name
		  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         algo = DbsAlgorithm (
                ExecutableName="TestExe01",
                ApplicationVersion= "TestVersion01",
                ApplicationFamily="AppFamily01",
                ParameterSetID=DbsQueryableParameterSet(
                     Hash="001234565798685",
                     Name="MyFirstParam01",
                     Version="V001",
                     Type="test",
                     Annotation="This is test",
                     Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                )
         )

         primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
	 
         proc = DbsProcessedDataset (
                PrimaryDataset=primary, 
                Name="TestProcessedDS002", 
                PhysicsGroup="BPositive",
                Status="Valid",
                TierList=['SIM', 'RECO'],
                AlgoList=[algo],
         )

         api.insertProcessedDataset (proc)
	 
    """

    xmlinput  = "<?xml version='1.0' standalone='yes'?>" 
    xmlinput += "<dbs>" 
    xmlinput += "<processed-dataset "
    primary = dataset.get('PrimaryDataset')
    if primary == None: 
       raise DbsApiException(ErrorMsg="Serious Error Primary Dataset not specified")
    xmlinput += " primary_datatset_name='"+primary.get('Name', "")+"'" 
    xmlinput += " processed_datatset_name='"+dataset.get('Name', "")+"'"
    #xmlinput += " open_for_writing='y'"
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
           #xmlinput += " ps_name='"+pset.get('Name', "")+"'"
           #xmlinput += " ps_version='"+pset.get('Version', "")+"'"
           #xmlinput += " ps_type='"+pset.get('Type', "")+"'"
           #xmlinput += " ps_annotation='"+pset.get('Annotation', "")+"'"
           #xmlinput += " ps_content='"+base64.binascii.b2a_base64(pset.get('Content', ""))+"'"
        xmlinput += "/>"

    for run in dataset.get('RunList',[]):
        xmlinput += "<run run_number='"+run+"'/>"

    xmlinput += "</processed-dataset>"
    xmlinput += "</dbs>"

    if self.verbose():
       print "insertProcessedDataset, xmlinput",xmlinput
    
    # Call the method
    data = self._server._call ({ 'api' : 'insertProcessedDataset',
                         'xmlinput' : xmlinput }, 'POST')

# ------------------------------------------------------------

  def insertRun(self, run):

    """
    Inserts a new run in the DBS databse. 
    
    param: 
	run : The dbs run passed in as DbsRun object. The following are mandatory and should be present
	      in the dbs run object: 
	      RunNumber, number_of_events, number_of_lumi_sections, total_luminosity and store_number
			  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
    
         run = DbsRun (
                 RunNumber=1,
                 NumberOfEvents= 100,
                 NumberOfLumiSections= 20,
                 TotalLuminosity= 2222,
                 StoreNumber= 123,
                 StartOfRun= 'now',
                 EndOfRun= 'never',
         )
 
         api.insertRun (run)

    """

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<run"
    xmlinput += " run_number='"+str(run.get('RunNumber', ''))+"'"
    xmlinput += " number_of_events='"+str(run.get('NumberOfEvents', ''))+"'"
    xmlinput += " number_of_lumi_sections='"+str(run.get('NumberOfLumiSections', ''))+"'"
    xmlinput += " total_luminosity='"+str(run.get('TotalLuminosity', ''))+"'"
    xmlinput += " store_number='"+str(run.get('StoreNumber', ''))+"'"
    xmlinput += " start_of_run='"+run.get('StartOfRun', '')+"'"
    xmlinput += " end_of_run='"+run.get('EndOfRun', '')+"'"
    xmlinput += " />"
    xmlinput += "</dbs>"

    if self.verbose():
       print "insertRun, xmlinput",xmlinput
       
    data = self._server._call ({ 'api' : 'insertRun',
                         'xmlinput' : xmlinput }, 'POST')
 
  # ------------------------------------------------------------
  def insertFiles(self, dataset, files, block):
    """ 
    Inserts a new dbs file in an existing block in a given processed dataset. It also insertes lumi sections
    assocated with the file. It insert all the parents of the file, assocaite  all the tiers of the file and 
    associate all the algorithms of the file. The parents, tiers and algorithms of the file should exist before 
    the file could be inserted.
    
    param: 
        dataset : The procsssed dataset can be passed as an DbsProcessedDataset object or just as a dataset 
	          path in the format of /prim/dt/proc
	
	files : The list of dbs files in the format of DbsFile obejct. The following are mandatory and should be present 
		in the dbs file object:	lfn
		  
	block : The dbs file block passed in as an DbsFileBlock obejct. This object can be passed in also, 
        	as a string containing the block name, instead of DbsFileBlock object. The following fields 
		are mandatory and should be present in the dbs file block object: block_name
			  
		  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         algo = DbsAlgorithm (
                ExecutableName="TestExe01",
                ApplicationVersion= "TestVersion01",
                ApplicationFamily="AppFamily01",
                ParameterSetID=DbsQueryableParameterSet(
                     Hash="001234565798685",
                     Name="MyFirstParam01",
                     Version="V001",
                     Type="test",
                     Annotation="This is test",
                     Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                )
         )

         primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
	 
         proc = DbsProcessedDataset (
                PrimaryDataset=primary, 
                Name="TestProcessedDS002", 
                PhysicsGroup="BPositive",
                Status="Valid",
                TierList=['SIM', 'RECO'],
                AlgoList=[algo],
         )

         lumi1 = DbsLumiSection (
                 LumiSectionNumber=1222,
                 StartEventNumber=100,
                 EndEventNumber=200,
                 LumiStartTime='notime',
                 LumiEndTime='neverending',
                 RunNumber=1,
         )

         lumi2 = DbsLumiSection (
                 LumiSectionNumber=1333,
                 StartEventNumber=100,
                 EndEventNumber=200,
                 LumiStartTime='notime',
                 LumiEndTime='neverending',
                 RunNumber=1,
         )

         myfile1= DbsFile (
                Checksum= '999',
                LogicalFileName= 'aaa1122-0909-9767-8764aaa',
                NumberOfEvents= 10000,
                FileSize= 12340,
                Status= 'VALID',
        	ValidationStatus = 'VALID',
                FileType= 'EVD',
                Dataset= proc,
                LumiList= [lumi1, lumi2],
                TierList= ['SIM', 'RECO'],
         )


        myfile2= DbsFile (
                 Checksum= '000',
                 LogicalFileName= 'aaaa2233-0909-9767-8764aaa',
                 NumberOfEvents= 10000,
                 FileSize= 12340,
                 Status= 'VALID',
         	 ValidationStatus = 'VALID',
                 FileType= 'EVD',
                 Dataset= proc,
                 TierList= ['SIM', 'RECO'],
                 AlgoList = [algo],
                 ParentList = ['aaa1122-0909-9767-8764aaa']  
         )
                            
         block = DbsFileBlock (
                 Name="/this/hahah#12345"
         )

         api.insertFiles (proc, [myfile1, myfile2], block)

         api.insertFiles ("/test_primary_anzar_001/SIM/TestProcessedDS002",[myfile1, myfile2], "/this/hahah#12345")
	 
         api.insertFiles (proc, [myfile1, myfile2], "/this/hahah#12345")
   
         api.insertFiles ("/test_primary_anzar_001/SIM/TestProcessedDS002", [myfile1, myfile2],  block)


    """
    # Prepare XML description of the input

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += " <processed_datatset path='"+self._path(dataset)+"'"
    if block != None:
       #if (block.get("Name", "") == ""):
       #     print "BLOCK NAME IS NULL"
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
              #xmlinput += " ps_name='"+pset.get('Name', "")+"'"
              #xmlinput += " ps_version='"+pset.get('Version', "")+"'"
              #xmlinput += " ps_type='"+pset.get('Type', "")+"'"
              #xmlinput += " ps_annotation='"+pset.get('Annotation', "")+"'"
              #xmlinput += " ps_content='"+base64.binascii.b2a_base64(pset.get('Content', ""))+"'"
              xmlinput += "/>"
       xmlinput += "</file>"
       xmlinput += "\n"

    xmlinput += "</processed_datatset>"
    xmlinput += "</dbs>"

    if self.verbose():
       print "insertFiles, xmlinput",xmlinput

    # Call the method
    data = self._server._call ({ 'api' : 'insertFiles',
                         'xmlinput' : xmlinput }, 'POST')

  # ------------------------------------------------------------

  def insertBlock(self, dataset, block=None, storage_element=None):
    """
    Inserts a new dbs file block in a given processed dataset. 
    
    param: 
        dataset : The procsssed dataset can be passed as an DbsProcessedDataset object or just as a dataset 
	          path in the format of /prim/dt/proc

	block : The dbs file block passed in as a string containing the block name. This field is not mandatory.
	        If the block name is not provided the server creates one based on the primary dataset name, processed
		dataset name and a random GUID. It retuirns back this newly created block
			  
	storage_element : The list of storage element names in the string format. This field is not mandatory. If 
	                  this field is not provided then just the block is inserted without any storage element 
			  associated with it.
			  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
	 proc = DbsProcessedDataset (
               PrimaryDataset=primary,
               Name="TestProcessedDS002",
               TierList=['SIM', 'RECO'],
         )
         api.insertBlock (proc)

         api.insertBlock (proc, "/this/hahah#12345")
    
         api.insertBlock (proc, "/this/hahah#12345",  ['se1', 'se2', 'se3'])
	 
         api.insertBlock (proc, "",  ['se1', 'se2', 'se3'])
    
         api.insertBlock ("/test_primary_anzar_001/SIM/TestProcessedDS002" , "/this/hahah#12345")
    
         api.insertBlock ("/test_primary_anzar_001/SIM/TestProcessedDS002" , "/this/hahah#12345",  ['se1', 'se2', 'se3'])
	 
         api.insertBlock ("/test_primary_anzar_001/SIM/TestProcessedDS002" , "",  ['se1', 'se2', 'se3'])
    

    """

    path = self._path(dataset)
    name = self._name(block)
    
    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<block name='"+ name +"'"
    xmlinput += " path='"+path+"'>"
    if (storage_element not in ( [], None)) : 
         for aSe in storage_element:
            xmlinput += " <storage_element storage_element_name='"+aSe+"'/>"
    xmlinput += "</block>"  
    xmlinput += "</dbs>"

    if self.verbose():
       print "insertBlock, xmlinput",xmlinput

    data = self._server._call ({ 'api' : 'insertBlock',
                         'xmlinput' : xmlinput }, 'POST')

   # ------------------------------------------------------------

  def insertStorageElement(self, block, storageElement):
	  
    """
    Inserts a new storage element in a given block. 
    
    param: 
	block : The dbs file block passed in as an DbsFileBlock obejct. This object can be  passed in also, 
	        as a string containing the block name, instead of DbsFileBlock object. The following fields 
		are mandatory and should be present in the dbs file block object: 
		block_name and storage_element_name
			  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         block = DbsFileBlock (
                Name="/TestPrimary1164751189.48/HIT1164751189.48/TestProcessed1164751189.48"
         )
	 api.insertStorageElement ( block , 'se1')
	 
	 api.insertStorageElement ( "/this/hahah#12345" , 'se2')

    """


    name = self._name(block)
    
    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    if (storageElement not in ( '', None)) : 
          xmlinput += " <storage_element block_name='" + name + "' storage_element_name='"+ storageElement +"'/>"
    xmlinput += "</dbs>"

    #print "insertStorageElement, xmlinput",xmlinput
    if self.verbose():
       print "insertStorageElement, xmlinput",xmlinput

    data = self._server._call ({ 'api' : 'insertStorageElement',
                         'xmlinput' : xmlinput }, 'POST')


  # ------------------------------------------------------------

  def insertTier(self, tier_name):
    """
    Inserts a new tier in the DBS databse. 
    
    param: 
	tier_name : The data tier name passed in as string 
			  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         tier_name = "GEN-SIM-TEST"
         api.insertTier (tier_name)

    """

 
    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<tier tier_name='"+ tier_name +"' />"
    xmlinput += "</dbs>"

    if self.verbose():
       print "insertTier, xmlinput",xmlinput

    data = self._server._call ({ 'api' : 'insertTier', 
                         'xmlinput' : xmlinput }, 'POST')
  # ------------------------------------------------------------

  def insertLumiSection(self, lumi):
	  
    """
    Inserts a new lumi section in the DBS databse. 
    
    param: 
	lumi : The lumi section passed as an DbsLumiSection obejct. The following fields 
	       are mandatory and should be present in the lumi section object : 
               lumi_section_number, run_number, start_event_number and end_event_number
			  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
         lumi = DbsLumiSection (
                LumiSectionNumber=99,
                StartEventNumber=100,
                EndEventNumber=200,
                LumiStartTime='notime',
                LumiEndTime='neverending',
                RunNumber=1,
         )
         api.insertLumiSection(lumi)

    """

  
    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>"
    xmlinput += "<lumi "
    xmlinput += " lumi_section_number='"+str(lumi.get('LumiSectionNumber', ''))+"'"
    xmlinput += " run_number='"+str(lumi.get('RunNumber', ''))+"'"
    xmlinput += " start_event_number='"+str(lumi.get('StartEventNumber', ''))+"'"
    xmlinput += " end_event_number='"+str(lumi.get('EndEventNumber', ''))+"'"
    xmlinput += " lumi_start_time='"+lumi.get('LumiStartTime', '')+"'"
    xmlinput += " lumi_end_time='"+lumi.get('LumiEndTime', '')+"'"
    xmlinput += " />"
    xmlinput += "</dbs>"

    if self.verbose():
       print "insertLumiSection, xmlinput",xmlinput

    data = self._server._call ({ 'api' : 'insertLumiSection',
                         'xmlinput' : xmlinput }, 'POST')
  # ------------------------------------------------------------

  def createAnalysisDatasetFromPD(self, dataset, analysisdataset ):

    """
    Creates a new analysis dataset dataset from a given processed dataset. This analysis dataset will have 
    the same contents as that of the processed dataset. 
    
    param: 
    	dataset : The procsssed dataset can be passed as an DbsProcessedDataset object or just as a dataset 
	          path in the format of /prim/dt/proc
	analysisdataset : The analysis dataset object passed as an DbsAnalysisDataset obejct. The following 
	                  fields are mandatory and should be present in the analysis dataset object : 
			  name, type, status, path, physics_group_name and annotation
			  
    raise: DbsApiException, DbsBadRequest, DbsBadData, DbsNoObject, DbsExecutionError, DbsConnectionError, 
           DbsToolError, DbsDatabaseError, DbsBadXMLData, InvalidDatasetPathName, DbsException	
	   
    examples:
	analysis = DbsAnalysisDataset(
		Name='TestAnalysisDataset001',
		Annotation='This is a test analysis dataset',
		Type='KnowTheType',
		Status='VALID',
		PhysicsGroup='BPositive'
	)
	api.createAnalysisDatasetFromPD ("/test_primary_anzar_001/SIM/TestProcessedDS002/", analysis)

    """
    
    path = self._path(dataset)

    xmlinput  = "<?xml version='1.0' standalone='yes'?>"
    xmlinput += "<dbs>" 
    xmlinput += "<analysis-dataset name='"+ analysisdataset.get('Name', '') +"'"
    xmlinput += " annotation='"+ analysisdataset.get('Annotation', '') +"'"
    xmlinput += " type='"+ analysisdataset.get('Type', '') +"'"
    xmlinput += " status='"+ analysisdataset.get('Status', '') +"'"
    xmlinput += " physics_group_name='"+ analysisdataset.get('PhysicsGroup', '') +"'" 
    xmlinput += " path='"+path+"'/>"
    xmlinput += "</dbs>"

    #print xmlinput

    if self.verbose(): 
       print "createAnalysisDatasetFromPD, xmlinput",xmlinput

    data = self._server._call ({ 'api' : 'createAnalysisDatasetFromPD',
                         'xmlinput' : xmlinput }, 'POST')



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

 
   

#############################################################################
# Unit testing: see $PWD/UnitTests
