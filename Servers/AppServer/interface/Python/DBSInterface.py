import dbsclient
import dbsApi
import dbsProcessedDataset
import dbsProcessingPath
import dbsApplication
import dbsPrimaryDataset
import dbsEventCollection
import dbsFileBlock
import dbsFile
import dbsMonteCarloDescription
from dbsDataset import * 

class DBSInterface(dbsApi.DbsApi):
   """ A Class that provides Python interface 
       for CRAB to DBS Server
   """

   def __init__(self):
      """Constructor"""
      self.client = dbsclient.DBSClient()
      #self.x = 1

   def stringp(self, inStr) :
       return self.client.str(inStr)

   def delStringp(self, inStr) :
       dbsclient.delete_stringp(inStr)

   def string(self, key) :
       return dbsclient.ASTR(key)

   def integer(self, key) :
       return dbsclient.AINT(key)

   def character(self, key) :
       return dbsclient.ACHR(key)

   def setStrValue(self, aRow, key, value) :
        if(value != None and key != None):
           key = self.stringp(key)
           aRow.setValue(key, self.string(value))
           self.delStringp(key)

   def setIntValue(self, aRow, key, value) :
        if(value != None and key != None):
           key = self.stringp(key)
           aRow.setValue(key, self.integer(int(value)))
           self.delStringp(key)

   def setChrValue(self, aRow, key, value) :
        if(value != None and key != None):
           key = self.stringp(key)
           aRow.setValue(key, self.character(value))
           self.delStringp(key)

   def getStrValue(self, table, key, index) :
        if(index != None and key != None):
                if(index > -1) :
                        key = self.stringp(key)
                        value = table.getStrValue(index, key)
                        self.delStringp(key)
                        return value


   def createPrimaryDataset(self, primaryDataset):
      try:

        aRow = dbsclient.Primarydatasetmultirow()
        table = dbsclient.PrimarydatasetMultiTable()
        self.setStrValue(aRow, "t_desc_mc.description", primaryDataset.getMonteCarloDescription().getDescription())
        self.setStrValue(aRow, "t_desc_trigger.description", primaryDataset.getTriggerDescription())
        self.setStrValue(aRow, "t_desc_mc.decay_chain", primaryDataset.getMonteCarloDescription().getDecayChain())
        self.setStrValue(aRow, "t_desc_mc.production", primaryDataset.getMonteCarloDescription().getProduction())
        self.setStrValue(aRow, "t_physics_group.name", primaryDataset.getPhysicsGroupName())
        self.setChrValue(aRow, "t_desc_primary.is_mc_data", primaryDataset.getMonteCarloDescription().getIsMcData())
        self.setStrValue(aRow, "t_primary_dataset.name", primaryDataset['datasetName'])
	
        primaryDatasetID = self.client.createPrimaryDataset(aRow, table)
      except RuntimeError,e:
         print "RT Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      except Exception ,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "Primary Dataset ID ",primaryDatasetID
      return primaryDatasetID

   def createProcessedDataset(self, processedDataset):
      #return 23
      try:
        processingPathID = processedDataset.getProcessingPath().getPathId()
        if processingPathID == None:
          processingPathID = 0
          pp = processedDataset.getProcessingPath().getParentPath();
          if pp != None:
             app = pp.getApplication()
             if app != None:
                tempApplication = dbsApplication.DbsApplication(
                  family=app.getFamily(),
                  executable=app.getExecutable(),
                  version=app.getVersion(),
                  configConditionsVersion =app.getConfigConditionsVersion(),
                  parameterSet=app.getParameterSet(),
                  outputTypeName=app.getOutputTypeName(),
                  inputTypeName=app.getInputTypeName())
  
                tempProcessingPath = dbsProcessingPath.DbsProcessingPath(
                  fullPath=pp.getFullPath(),
                  dataTier=pp.getDataTier(),
                  application=tempApplication)

                dataset = dbsProcessedDataset.DbsProcessedDataset(
                  primaryDatasetName=processedDataset.getPrimaryDatasetName(),
                  processingPath=tempProcessingPath)

             processingPathID = self.getProcessingPathID(dataset)
	     print "processingPathID is ",processingPathID

        #apidata = dbsclient.Processingpath_ClientAPIData()
        pp = processedDataset.getProcessingPath()
        app = pp.getApplication()

        aRow = dbsclient.Processingpathmultirow()
        table = dbsclient.ProcessingpathMultiTable()

        self.setStrValue(aRow, "t_processed_dataset.name", processedDataset.getDatasetName())
        self.setStrValue(aRow, "t_app_family.name", app.getFamily())
        self.setStrValue(aRow, "t_data_tier.name", pp.getDataTier())
        self.setStrValue(aRow, "t_application.executable", app.getExecutable())
        self.setStrValue(aRow, "t_app_config.parameter_set", app.getParameterSet())
        self.setChrValue(aRow, "t_processed_dataset.is_open", processedDataset.getIsDatasetOpen())
        self.setStrValue(aRow, "t_application.app_version", app.getVersion())
        self.setStrValue(aRow, "t_app_config.conditions_version", app.getConfigConditionsVersion())
        self.setStrValue(aRow, "t_processing_path.full_path", pp.getFullPath())
        self.setStrValue(aRow, "t_primary_dataset.name", processedDataset.getPrimaryDatasetName())
        self.setStrValue(aRow, "t_collection_type.name.t_application.output_type", app.getOutputTypeName())
        self.setStrValue(aRow, "t_collection_type.name.t_application.input_type", app.getInputTypeName())
        self.setIntValue(aRow, "t_processing_path.parent", processingPathID)
	
        #print "************************length*****************",len(pp.getFullPath().split('/'))
        #print "pp.getFullPath().split('/') ",pp.getFullPath().split('/')
        if(len(pp.getFullPath().split('/')) < 4 ):
             raise dbsApi.DbsApiException(args="Fullpath in t_processing_path is incorrect")
	processedDatasetID = self.client.createProcessedDataset(aRow, table)
        #print processedDatasetID
        processinPathID = self.getStrValue(table, "t_processing_path.id", 0)
        #print processinPathID 
        print "processedDatasetID " ,processedDatasetID
        print "processingPathID " ,processinPathID
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "Processed Dataset ID ",processedDatasetID
      #return processedDatasetID,processinPathID
      return processedDatasetID

   def getProcessedDatasetID(self, processedDataset):
        table = self.getProcessedDataset(processedDataset)
        proDsId = 0
        if table.getNoOfRows() > 0:
          if table.getNoOfRows() > 1:
              table.dispose()
              raise dbsApi.DbsApiException(args="More than one Processed Dataset found")
          else: 
              proDsId = self.getStrValue(table, "t_processed_dataset.id", 0)
        else: 
           print "Processed Dataset not found"
           table.dispose()
           raise dbsApi.DbsApiException(args="Processed Dataset not found")   
        
        table.dispose()
        print "*********************************************************************"
        print "Processed DatasetID = ",proDsId
        print "*********************************************************************"
        return proDsId

   def getProcessingPathID(self, processedDataset):
        table = self.getProcessedDataset(processedDataset)
        proPathId = 0;
        if table.getNoOfRows() > 0:
           if table.getNoOfRows() > 1:
              table.dispose()
              raise dbsApi.DbsApiException(args="More than one processing path found") 
           else :
              proPathId = self.getStrValue(table, "t_processing_path.id", 0)
        else:
           table.dispose()
           raise dbsApi.DbsApiException(args="No Processing Path found")
        table.dispose()

        print "*********************************************************************"
        print "Processing PathID = ",proPathId
        print "*********************************************************************"
        return proPathId



   def getProcessedDataset(self, processedDataset):
      try:
        print "inside getProcessedDatasetID >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        aRow = dbsclient.Processingpathmultirow()
        table = dbsclient.ProcessingpathMultiTable()

	self.setStrValue(aRow, "t_processed_dataset.name", processedDataset.getDatasetName())
        app = processedDataset.getProcessingPath().getApplication()
        if app != None:
           self.setStrValue(aRow, "t_application.app_version", app.getVersion())
           self.setStrValue(aRow, "t_app_config.conditions_version", app.getConfigConditionsVersion())
           self.setStrValue(aRow, "t_collection_type.name.t_application.output_type",  app.getOutputTypeName())
           self.setStrValue(aRow, "t_collection_type.name.t_application.input_type", app.getInputTypeName())
           self.setStrValue(aRow, "t_app_family.name", app.getFamily()) 
           self.setStrValue(aRow, "t_app_config.parameter_set", app.getParameterSet())  
           self.setStrValue(aRow, "t_application.executable", app.getExecutable())

        self.setIntValue(aRow, "t_processing_path.id", processedDataset.getProcessingPath().getPathId())
        self.setStrValue(aRow, "t_data_tier.name", processedDataset.getProcessingPath().getDataTier())  
        self.setChrValue(aRow, "t_processed_dataset.is_open", processedDataset.getIsDatasetOpen())
        self.setStrValue(aRow, "t_processing_path.full_path", processedDataset.getProcessingPath().getFullPath())
        self.setStrValue(aRow, "t_primary_dataset.name", processedDataset.getPrimaryDatasetName())

        print "now calling readProcessingPath"
        self.client.readProcessedDataset(aRow, table)
	print "no of Processed Dataset are ", table.getNoOfRows()
        return table
      except RuntimeError,e:
         table.dispose()
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)


   def getDatasetFileBlocks(self, processedDataset):

      fileBlockList = []

      #print "RETURNING AN EMPTY FILEBLOCK LIST" 
      #return fileBlockList

      try:
        processedDatasetID = processedDataset.getProcessedDatasetID()
        if processedDatasetID == None:
           processedDatasetID = self.getProcessedDatasetID(processedDataset)
        print "processedDatasetID ",processedDatasetID
 
        aRow = dbsclient.Blockviewmultirow()
        table = dbsclient.BlockviewMultiTable()

        self.setIntValue(aRow, "t_processed_dataset.id",processedDatasetID )
        self.client.readBlock(aRow, table)

        #print "type(table)", type(table)
        #print "dir(table)", dir(table)
        #print "table.getNoOfRows() ", table.getNoOfRows()
        
        #print "RETURNING AN EMPTY FILEBLOCK LIST" 
        #return fileBlockList
        nrow = table.getNoOfRows() 
        if nrow >= 1:
           indx = 0
           while indx < nrow :
               blockId = self.getStrValue(table, "t_block.id", indx)
               blockName = str(blockId)
               blockStatusName = self.getStrValue(table, "t_block_status.name", indx)
               numberOfFiles = self.getStrValue(table, "t_block.files", indx)
               numberOfBytes = self.getStrValue(table, "t_block.bytes", indx)
               processedDatasetName = self.getStrValue(table, "t_processed_dataset.name", indx) 

               fileBlock = dbsFileBlock.DbsFileBlock(blockId=blockId, \
                                                     blockName=blockName, \
                                                     blockStatusName=blockStatusName, \
                                                     numberOfFiles=numberOfFiles, \
                                                     numberOfBytes=numberOfBytes ) 
               fileBlockList.append(fileBlock) 
               indx += 1
        else:
           print "No Blocks found in Processed Dataset"
        table.dispose()

      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)

      return fileBlockList


   def createFileBlock(self, processedDataset, fileBlock):
	
      try:
        processedDatasetID = processedDataset.getProcessedDatasetID()
        if processedDatasetID == None:
           processedDatasetID = self.getProcessedDatasetID(processedDataset)
        print "processedDatasetID ",processedDatasetID
        aRow = dbsclient.Blockviewmultirow()
        table = dbsclient.BlockviewMultiTable()

        self.setIntValue(aRow, "t_block.bytes", fileBlock.getNumberOfBytes())
        self.setIntValue(aRow, "t_block.files", fileBlock.getNumberOfFiles())	  	
        self.setIntValue(aRow, "t_block.processed_dataset",processedDatasetID )	  	
        self.setStrValue(aRow, "t_block_status.name", fileBlock.getBlockStatusName())	  	
	
        blockID = self.client.createBlock(aRow, table)
        print "*********************************************************************"
        print "Block ID ",blockID
        print "*********************************************************************"
	
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "Block inserted succesfully  ",blockID
      return blockID


   def insertEventCollections(self, processedDataset, eventCollectionList):
	
      try:
        processedDatasetID = processedDataset.getProcessedDatasetID()
        if processedDatasetID == None:
           processedDatasetID = self.getProcessedDatasetID(processedDataset)
        print "processedDatasetID ",processedDatasetID
        if len(eventCollectionList) > 0:
           print "In the loop!" 
           #return self.recInsertEC(eventCollectionList[0],processedDatasetID,parentageType)
           for ec in eventCollectionList:
           #return self.recInsertEC(eventCollectionList[0],processedDatasetID)
              #print "calling loop ec ",ec 
              id = self.recInsertEC(ec,processedDatasetID)
        return id
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "Event Collections inserted succesfully  ",len(eventCollectionList)

   
   #def recInsertEC(self, eventCollection, processedDatasetID, parentageType):
   def recInsertEC(self, eventCollection, processedDatasetID):
      #print "\n**************************************************\n"
      #print "\n**************************************************\n"
      #print "entring recInsertEC"
      #print "\n**************************************************\n"
      #print "\n**************************************************\n"
      evCollID = 0

      #Biz Rule  ##Assert That user must provide FileList
      if eventCollection.getFileList() in ([], None) :
         print "You must provide FileList to Insert EventCollection"
         raise dbsApi.DbsApiException(args="You must provide FileList to Insert EventCollection")    
         return 

      #Biz Rule test that all Files have same Block ID
      firstBlockID = eventCollection.getFileList()[0].getFileBlockId()
      for afile in eventCollection.getFileList():
         blockId=afile.getFileBlockId()
         if blockId != firstBlockID :
            print "All files in same EvColl should have same Block ID"
            raise dbsApi.DbsApiException(args="All files in same EvColl should have same Block ID")            
            return
 
      if eventCollection.getParentEventCollection() != None:
         parentEC=eventCollection.getParentEventCollection()
         
         #evCollID = recInsertEC(eventCollection.getParentEventCollection(),processedDatasetID,parentageType)
         print "\n\n\n\n PARENT FOUND \n\n\n\n\n"
         evCollID = self.recInsertEC(parentEC,processedDatasetID)
         #print "setting id ..........................",evCollID
         #parentEC.setCollectionId(evCollID) 
         #print "getting id ", parentEC.getCollectionId()
         #print "parentEC is ", parentEC
      else:
         print "\n\n\n\n CHILD FOUND \n\n\n\n\n"
         #print "eventCollection.getParentEventCollection() ", eventCollection.getParentEventCollection()
         #print "eventCollection " , eventCollection
      
      aRow = dbsclient.Evcollviewmultirow()
      table = dbsclient.EvcollviewMultiTable()
      if eventCollection.getCollectionId() != None:
         print "This is a haack..............."
         return eventCollection.getCollectionId()
      
      self.setStrValue(aRow, "t_info_evcoll.name", eventCollection.getCollectionName())
      self.setIntValue(aRow, "t_event_collection.collection_index", eventCollection.getCollectionIndex()) 
      self.setIntValue(aRow, "t_info_evcoll.events", eventCollection.getNumberOfEvents())
      self.setStrValue(aRow, "t_parentage_type.name", eventCollection.getParentageType())
      self.setIntValue(aRow, "t_event_collection.processed_dataset", processedDatasetID)
      self.setChrValue(aRow, "t_event_collection.is_primary", eventCollection.getIsPrimary())
      self.setIntValue(aRow, "t_evcoll_parentage.parent", evCollID)
      self.setStrValue(aRow, "t_evcoll_status.name", "dummy status")
      self.setStrValue(aRow, "t_validation_status.name", "Dummy Validation status ")
       
      toReturnEvCollID = self.client.createEventCollection(aRow, table)
      #toReturnEvCollID = self.x
      #self.x += 1
      #eventCollection.setCollectionId(toReturnEvCollID)
      print "eventCollection ",eventCollection
      print "*********************************************************************"
      print "Event Collection ID ",toReturnEvCollID
      print "*********************************************************************"
      self.insertFiles(toReturnEvCollID, eventCollection.getFileList())
      print "\n**************************************************\n"
      print "\n**************************************************\n"
      print "exiting recInsertEC"
      print "\n**************************************************\n"
      print "\n**************************************************\n"
      return toReturnEvCollID


   def listFilesByBlock(self, evCollID, blockID):

      """ Get the list of files in a FileBlock/EventCollection"""
      fileList=[]
      try:
         aRow = dbsclient.Fileviewmultirow()
         table = dbsclient.FileviewMultiTable()

         self.setIntValue(aRow, "t_evcoll_file.evcoll", evCollID)
         self.setIntValue(aRow, "t_file.inblock", blockID)
         self.client.readFiles(aRow, table)
  
         nrow = table.getNoOfRows()
         print "listFilesByBlock:::::::::table.getNoOfRows()", table.getNoOfRows()
         if nrow >= 1:
           indx = 0
           while indx < nrow :
                #print "indx", indx
                fileStatus = self.getStrValue(table, "t_file_status.name", indx)
                #print "fileStatus", fileStatus
                guid = self.getStrValue(table, "t_file.guid", indx)
                #print "guid", guid 
                #checkSum = self.getStrValue(table, "t_file.checksum", indx)
                #print "checkSum", checkSum
                logicalFileName = self.getStrValue(table, "t_file.logical_name", indx)
                #print "logicalFileName", logicalFileName
                fileBlockId = self.getStrValue(table, "t_file.inblock", indx)
                #print "inblock", fileBlockId
                fileType = self.getStrValue(table, "t_file_type.name", indx)
                #print "fileType", fileType
                fileSize = self.getStrValue(table, "t_file.filesize", indx)
                #print "fileSize", fileSize
                
                afile = dbsFile.DbsFile(logicalFileName=logicalFileName, \
                                guid=guid, \
                                #checkSum=checkSum, \
                                fileType=fileType, fileStatus=fileStatus, \
                                fileBlockId=fileBlockId, fileSize=fileSize)
                fileList.append(afile)  
                indx += 1
         else:
           print "No File found in the Block/EventCollection"
         table.dispose()
 
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      return fileList

   def insertFiles(self, evCollID, files):
      try: 
         fileVector = dbsclient.FileVector()
         table = dbsclient.FileviewMultiTable()
         for afile in files:
            aRow = dbsclient.Fileviewmultirow()

            self.setStrValue(aRow, "t_file_status.name", afile.getFileStatus())
            self.setStrValue(aRow, "t_file.guid", afile.getGuid())
            self.setStrValue(aRow, "t_file.checksum", afile.getCheckSum())#BA7C8A55-DE62-D811-892C-00E081250436
            self.setStrValue(aRow, "t_file.logical_name", afile.getLogicalFileName())
            self.setIntValue(aRow, "t_file.inblock", afile.getFileBlockId())
            self.setStrValue(aRow, "t_file_type.name", afile.getFileType())#EVDZip/ROOT_All
            self.setStrValue(aRow, "t_file.filesize", str(afile.getFileSize()))
            self.setIntValue(aRow, "t_evcoll_file.evcoll", evCollID)
	     
            fileVector.push_back(aRow)
         self.client.insertFiles(fileVector, table)

      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "File inserted succesfully ",len(files)


   def getDatasetContents(self, pathName, listFiles=False):
      """public api method, that return details regarding EvColls
         contained in Processed Dataset
         Specifically the File Blocks and file there in.
         * Take pathName of form /PriDS/DT/ProcDS as input
         * For now dumps information on screen.
      """

      fileBlockList = []
      # get the names of primary dataset, data tier and processed dataset
      print "Inside getDatasetContents,  PathName is ", pathName

      try: 

        tokens = pathName.split('/')
        primaryDSName = tokens[1]
        dataTier = tokens[2]
        processedDSName = tokens[3]

        table = dbsclient.CrabevcollviewMultiTable()
        aRow = dbsclient.Crabevcollviewmultirow()

        self.setStrValue(aRow, "t_data_tier.name", dataTier) 
        self.setStrValue(aRow, "t_primary_dataset.name", primaryDSName)
        self.setStrValue(aRow, "t_processed_dataset.name", processedDSName)
        
        self.client.readCrabEC(aRow, table)

        nrow = table.getNoOfRows()
        print "nrow:::::", nrow

        if nrow >= 1:
           blockECMap = {}
           indx = 0
           while indx < nrow :
              print "indx:::", indx
              blockId = self.getStrValue(table, "t_block.id", indx)
              print "blockId", blockId
              evcollName = self.getStrValue(table, "t_info_evcoll.name", indx)
              print "evcollName", evcollName
              events = self.getStrValue(table, "t_info_evcoll.events", indx)
              print "events", events
              evCollId = self.getStrValue(table, "t_event_collection.id", indx)
              print "blockId, evcollName, events", blockId, evcollName, events, evCollId  

              if blockECMap.has_key(blockId):
                 blockECMap[blockId].append((evcollName, events, evCollId))
              else:
                 blockECMap[blockId] = [(evcollName, events, evCollId)] 
              indx += 1
           for eachBlockId in blockECMap.keys():
                  fileblockName = pathName+'/#'+str(eachBlockId)
                  
                  fileBlock = dbsFileBlock.DbsFileBlock(blockId=eachBlockId, blockName=fileblockName)
                  # This list makes sure that we get Unique EvColls
                  evCollList = []
                  for evcollName, events, evCollId in blockECMap[eachBlockId]: 
                      #print "CALLING listFilesByBlock"
                      if listFiles != False :
                         fileList = self.listFilesByBlock(evCollId, eachBlockId)
                      else:
                         fileList = [] 
                      if evcollName not in evCollList :
                         eventCollection = dbsEventCollection.DbsEventCollection(collectionName=evcollName, numberOfEvents=events, fileList=fileList)
                         fileBlock.addEventCollection(eventCollection)
                         evCollList.append(evcollName)
                  print fileBlock
                  fileBlockList.append(fileBlock)
        else :
            print "No file blocks found for the PathName", pathName 
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)

      return fileBlockList  

   def getDatasetProvenance(self, pathName, parentDataTiers):
      """ API Call that returns list of DBS 
           Datasets (Processed/Analysis) corresponding 
           to all parent datatiers requested by user
      """ 

      raise dbsApi.DbsApiException(args="getDatasetProvenance() API Call Not Implemented")
      
      # get the names of primary dataset, data tier and processed dataset
      tokens = pathName.split('/')
      primaryDSName = tokens[1]
      dataTier = tokens[2]
      processedDSName = tokens[3]


      #Get the list existing Parent DataTiers
      provInfo = dbsclient.Datasetprovenenceevchild_ClientAPIData()
      provInfo.t_primary_dataset_name = dbsclient.ASTR(primaryDSName)
      provInfo.t_data_tier_name = dbsclient.ASTR(dataTier)
      provInfo.t_processed_dataset_name = dbsclient.ASTR(processedDSName)

      childIds=[] 
      provInfoRet = self.readProvInfoChild(provInfo) 
      for i in range( provInfoRet.size() ) :
         childId = dbsclient.intp_value(provInfoRet[i].t_evcoll_parentage_child.getValue())
         if childId not in childIds:
            childIds.append(childId)  

      print "CHILD IDs ", childIds

      if len(childIds) == 0  :
         print "No Parents found for this Dataset"
         print pathName
         return pathName 

      provInfo = dbsclient.Datasetprovenenceevparent_ClientAPIData()
      #provInfo.t_evcoll_parentage_child = dbsclient.AINT(224)
      provInfo.t_evcoll_parentage_child = dbsclient.AINT(childIds[0])
      provInfoRet = self.readProvInfoParent(provInfo)
      print "After self.readProvInfoParent provInfoRet is ",provInfoRet
      dt_procDS_map = {}

      for i in range( provInfoRet.size() ) :
          print "************************************************************************************************"
          currProvInfo = provInfoRet[i]
          processed_dataset_name = dbsclient.stringp_value(currProvInfo.t_processed_dataset_name.getValue())
          print "    t_processed_dataset_name: " ,processed_dataset_name
          #print "    t_processing_path_data_tier: ", dbsclient.intp_value(currProvInfo.t_processing_path_data_tier.getValue())
          #print "    t_processing_path_id: ", dbsclient.intp_value(currProvInfo.t_processing_path_id.getValue())
          parentage_type_name = dbsclient.stringp_value(currProvInfo.t_parentage_type_name.getValue())  
          print "    t_parentage_type_name: " ,parentage_type_name
          primaryDsId = dbsclient.intp_value(currProvInfo.t_primary_dataset_id.getValue())
          print "    t_primary_dataset_id: " , primaryDsId
          #print "    t_processing_path_full_path: " ,dbsclient.stringp_value(currProvInfo.t_processing_path_full_path.getValue())
          #print "    t_primary_dataset_name: ",dbsclient.stringp_value(currProvInfo.t_primary_dataset_name.getValue())
          #print "    t_evcoll_parentage_parent: ", dbsclient.intp_value(currProvInfo.t_evcoll_parentage_parent.getValue())
          procDSId = dbsclient.intp_value(currProvInfo.t_processed_dataset_id.getValue())
          print "    t_processed_dataset_id: ", procDSId
          dTier=dbsclient.stringp_value(currProvInfo.t_data_tier_name.getValue())
          print "  t_data_tier.name: ", dTier
          
          #print "    t_evcoll_parentage_child: ", dbsclient.intp_value(currProvInfo.t_evcoll_parentage_child.getValue())
          
          if not dt_procDS_map.has_key(dTier):
             dt_procDS_map[dTier]=processed_dataset_name, parentage_type_name

          #if not dt_procDS_map.has_key(data_tier_name):
          #   dt_procDS_map[data_tier_name]=processed_dataset_name
          
      parentPathList = []
      dsList = DbsDatasetList()
      print "dt_procDS_map: ", dt_procDS_map    
      if len(dt_procDS_map) < 1:
          raise dbsApi.DbsApiException(args="No parent path found")   
      for aAskedTier in parentDataTiers:
         l_processed_dataset_name, l_parentage_type_name = dt_procDS_map[aAskedTier]
         #parentPath = "/"+primaryDSName+"/"+aAskedTier+"/"+dt_procDS_map[aAskedTier]
         parentPath = "/"+primaryDSName+"/"+aAskedTier+"/"+l_processed_dataset_name
         dsList.append(DbsDataset(primaryDSName, parentPath, aAskedTier, l_parentage_type_name))
         parentPathList.append(parentPath)
         print parentPath
      #return parentPathList
      return dsList
     
      #print "Ziyada sey ziyada CRASHHHHHHHHHHHHHHHHHHhhhhhhhhhh" 

   def readProvInfoParent(self, provInfo):
      provInfoRet = dbsclient.DSProvParentVector()
 
      self.client.getDatasetProvenenceParent(provInfo, provInfoRet)
      print "No of Prov Info records returned for Parent ", provInfoRet.size()

      return provInfoRet


   def readProvInfoChild(self, provInfo):
      provInfoRet = dbsclient.DSProvChildVector()
 
      self.client.getDatasetProvenenceChild(provInfo, provInfoRet)
      print "No of Prov Info records returned for Child", provInfoRet.size()

      return provInfoRet

   """
   def readPrimaryDataset(self, prdsName):
        primaryDSInfo = dbsclient.Primarydataset_ClientAPIData()
        primaryDSInfo.t_primary_dataset_name = dbsclient.ASTR(prdsName)

	priDSVector = dbsclient.PriDSVector()
	self.client.readPrimaryDataset(primaryDSInfo, priDSVector)
	print "No of Primary Datasets ",priDSVector.size()
	for i in range( priDSVector.size() ):
		print "************************************************************************************************"
		primaryDsId = dbsclient.intp_value(priDSVector[i].t_primary_dataset_id.getValue())
		print "primary_dataset_id = ",primaryDsId
		print "primary_dataset_name = ",dbsclient.stringp_value(priDSVector[i].t_primary_dataset_name.getValue())
		print "primary_dataset_description = ",dbsclient.intp_value(priDSVector[i].t_primary_dataset_description.getValue())
		return primaryDsId

   def readProcessingPath(self, primaryDsId, dataTier, procDS):
        
        procDSIds = []
	proPathVector = dbsclient.ProPathVector()
        procPathInfo = dbsclient.Processingpath_ClientAPIData()
        procPathInfo.t_primary_dataset_id = dbsclient.AINT(primaryDsId)
        procPathInfo.t_data_tier_name = dbsclient.ASTR(dataTier)
        procPathInfo.t_processed_dataset_name = dbsclient.ASTR(procDS)

	self.client.readProcessingPath(procPathInfo, proPathVector)
	print "no of Processing Path ",proPathVector.size()
	for i in range( proPathVector.size() ):
		print "************************************************************************************************"
		proDsId = dbsclient.intp_value(proPathVector[i].t_processed_dataset_id.getValue())
                procDSIds.append(proDsId)
		print "processed_dataset_id = ",proDsId
		print "data tiername = ",dbsclient.stringp_value(proPathVector[i].t_data_tier_name.getValue())

        return procDSIds 
   """
   def readEvColls(self, proDsId):
        evCollInfoList = []
	evCollVector = dbsclient.EVCollVector()
        evCollInfo = dbsclient.Evcollview_ClientAPIData()
        evCollInfo.t_event_collection_processed_dataset = dbsclient.AINT(proDsId)
        
	self.client.readEvColls(evCollInfo, evCollVector)
	print "no of Event Collections ",evCollVector.size()
	for i in range( evCollVector.size() ):
		evCollId = dbsclient.intp_value(evCollVector[i].t_event_collection_id.getValue())
                events = dbsclient.intp_value(evCollVector[i].t_info_evcoll_events.getValue())
                name = dbsclient.stringp_value(evCollVector[i].t_info_evcoll_name.getValue())
                evcollInfo = (evCollId, events, name)
                evCollInfoList.append(evcollInfo)
		print "************************************************************************************************"
		print "status = ",dbsclient.intp_value(evCollVector[i].t_info_evcoll_status.getValue())
		print "validation_status = ",dbsclient.intp_value(evCollVector[i].t_info_evcoll_validation_status.getValue())
		print "processed_dataset = ",dbsclient.intp_value(evCollVector[i].t_event_collection_processed_dataset.getValue())
		print "events = ", events
		print "collection_index = ",dbsclient.intp_value(evCollVector[i].t_event_collection_collection_index.getValue())
		print "id = ",evCollId
		print "name = ", name
        return evCollInfoList

   def readEvCollFiles(self, evCollId):

        filesByBlock = {}
	evCollFileVector = dbsclient.EVCollFileVector()
        evCollInfo = dbsclient.Fileview_ClientAPIData()
        evCollInfo.t_evcoll_file_evcoll = dbsclient.AINT(evCollId)
 
	self.client.readEvCollFiles(evCollInfo, evCollFileVector)
	print "no of Files in Event Collection",evCollFileVector.size()
	for i in range( evCollFileVector.size() ):
		print "************************************************************************************************"
                lfn = dbsclient.stringp_value(evCollFileVector[i].t_file_logical_name.getValue())
                guid = dbsclient.stringp_value(evCollFileVector[i].t_file_guid.getValue()) 
                inBlock = dbsclient.intp_value(evCollFileVector[i].t_file_inblock.getValue())
		print "file_logical_name = ", lfn
		print "file_guid = ", guid
		print "file_inblock = ", inBlock
                if not filesByBlock.has_key(inBlock):         
                   filesByBlock[inBlock] = [(lfn, guid)]
                else :
                   filesByBlock[inBlock].append((lfn, guid)) 
         
        return filesByBlock
                

if __name__ == "__main__" :
   
   #while(1) :
     mycrab = DBSInterface()
     #mycrab.getDatasetContents("/bt03_gg_bbh200_2taujmu/DST/bt_DST8713_2x1033PU_g133_CMS")
     mc = dbsMonteCarloDescription.DbsMonteCarloDescription(
      description="MyMonteCarloDescription",
      production="production",
      decayChain="decayChain",
      isMcData='y')

     dataset = dbsPrimaryDataset.DbsPrimaryDataset(datasetName="ds1",
                                datasetDescription="my dataset desc",
                                physicsGroupName="top",
                                triggerDescription="lalal",
                                monteCarloDescription=mc)
     primaryDatasetId = mycrab.createPrimaryDataset(dataset)


   #mycrab.getDatasetContents("/hg03_H2mu_ma300_tb30/Hit/hg_Hit752_g133") 

   #mycrab.getDatasetContents("/jm03b_qcd_80_120/Hit/jm_Hit245_2_g133")
   #mycrab.getDatasetContents("/jm03b_qcd_80_120/Hit/jm_Hit245_2_g133") 
   #mycrab.getDatasetProvenance("/bt03_gg_bbh200_2taujmu/DST/bt_DST8713_2x1033PU_g133_CMS", \
   #                           ["Hit", "Digi", "PU"])
   #mycrab.getDatasetProvenance("/jm03b_qcd_80_120/Hit/jm_Hit245_2_g133", \
   #mycrab.getDatasetProvenance("/bt_2x1033PU761_TkMu_2_g133_OSC/Hit/bt03_B0sCombBkg",["Hit"])
   
