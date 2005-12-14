import dbsclient
import dbsApi
import dbsPrimaryDataset
import dbsFileBlock
import dbsEventCollection
import dbsMonteCarloDescription
from dbsDataset import * 

class DBSInterface(dbsApi.DbsApi):
   """ A Class that provides Python interface 
       for CRAB to DBS Server
   """

   def __init__(self):
      """Constructor"""
      self.client = dbsclient.DBSClient()


   def createPrimaryDataset(self, primaryDataset):
      try:
        apidata = dbsclient.Primarydataset_ClientAPIData()
        apidata.t_desc_mc_description = dbsclient.ASTR(primaryDataset.getMonteCarloDescription().getDescription())
        apidata.t_primary_dataset_name = dbsclient.ASTR(primaryDataset['datasetName'])
        apidata.t_desc_mc_decay_chain = dbsclient.ASTR(primaryDataset.getMonteCarloDescription().getDecayChain())
        apidata.t_desc_mc_production = dbsclient.ASTR(primaryDataset.getMonteCarloDescription().getProduction())
        apidata.t_physics_group_name = dbsclient.ASTR(primaryDataset.getPhysicsGroupName())
        type(primaryDataset.getMonteCarloDescription().getIsMcData())
        apidata.t_desc_primary_is_mc_data = dbsclient.ACHR(primaryDataset.getMonteCarloDescription().getIsMcData())
        apidata.t_desc_trigger_description = dbsclient.ASTR(primaryDataset.getTriggerDescription())
        primaryDatasetID = self.client.createPrimaryDataset(apidata)
      except RuntimeError,e:
         print "RT Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      except Exception ,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "Primary Dataset ID ",primaryDatasetID
      return primaryDatasetID

   def createProcessedDataset(self, processedDataset):
      try:
        apidata = dbsclient.Processingpath_ClientAPIData()
        apidata.t_processed_dataset_name = dbsclient.ASTR(processedDataset['datasetName'])
        apidata.t_application_app_version = dbsclient.ASTR(processedDataset.getProcessingPath().getApplication().getVersion())
        apidata.t_app_config_conditions_version = dbsclient.ASTR(processedDataset.getProcessingPath().getApplication().getConfigConditionsVersion())
        apidata.t_collection_type_name_t_application_output_type = dbsclient.ASTR(processedDataset.getProcessingPath().getApplication().getOutputTypeName())
        apidata.t_collection_type_name_t_application_input_type = dbsclient.ASTR(processedDataset.getProcessingPath().getApplication().getInputTypeName())
        apidata.t_processing_path_parent = dbsclient.AINT(int(processedDataset.getProcessingPath().getPathId()))#parentPath has to return id
        apidata.t_data_tier_name = dbsclient.ASTR(processedDataset.getProcessingPath().getDataTier())
        apidata.t_app_family_name = dbsclient.ASTR(processedDataset.getProcessingPath().getApplication().getFamily())
        apidata.t_app_config_parameter_set = dbsclient.ASTR(processedDataset.getProcessingPath().getApplication().getParameterSet())
        apidata.t_processed_dataset_is_open = dbsclient.ACHR(processedDataset.getIsDatasetOpen())
        apidata.t_application_executable = dbsclient.ASTR(processedDataset.getProcessingPath().getApplication().getExecutable())
        apidata.t_processing_path_full_path = dbsclient.ASTR(processedDataset.getProcessingPath().getFullPath())
        apidata.t_primary_dataset_name = dbsclient.ASTR(processedDataset.getPrimaryDatasetName())
        processedDatasetID = self.client.createProcessedDataset(apidata)
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "Processed Dataset ID ",processedDatasetID
      return processedDatasetID

   def getProcessedDatasetID(self, processedDataset):
      try:
        print "inside getProcessedDatasetID >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        apidata = dbsclient.Processingpath_ClientAPIData()
        result = processedDataset['datasetName']
        if result != None:
          apidata.t_processed_dataset_name = dbsclient.ASTR(result)
        app = processedDataset.getProcessingPath().getApplication()
        if app != None:
           result = app.getVersion()
           if result != None:
             apidata.t_application_app_version = dbsclient.ASTR(result)
           result = app.getConfigConditionsVersion()
           if result != None:
             apidata.t_app_config_conditions_version = dbsclient.ASTR(result)
           result = app.getOutputTypeName()
           if result != None:
             apidata.t_collection_type_name_t_application_output_type = dbsclient.ASTR(result)
           result = app.getInputTypeName()
           if result != None:
             apidata.t_collection_type_name_t_application_input_type = dbsclient.ASTR(result)
           result = app.getFamily()
           if result != None:
             apidata.t_app_family_name = dbsclient.ASTR(result)
           result = app.getParameterSet()
           if result != None:
             apidata.t_app_config_parameter_set = dbsclient.ASTR(result)
           result = app.getExecutable()
           if result != None:
             apidata.t_application_executable = dbsclient.ASTR(result)

        result = processedDataset.getProcessingPath().getPathId()
        if result != None:
          apidata.t_processing_path_parent = dbsclient.AINT(int(result))
        result = processedDataset.getProcessingPath().getDataTier()
        if result != None:
          apidata.t_data_tier_name = dbsclient.ASTR(result)
        result = processedDataset.getIsDatasetOpen()
        if result != None:
          apidata.t_processed_dataset_is_open = dbsclient.ACHR(result)
        result = processedDataset.getProcessingPath().getFullPath()
        if result != None:
          apidata.t_processing_path_full_path = dbsclient.ASTR(result)
        result = processedDataset.getPrimaryDatasetName()
        if result != None:
          apidata.t_primary_dataset_name = dbsclient.ASTR(result)

        #print "now calling readProcessingPath"
        proPathVector = dbsclient.ProPathVector()
        self.client.readProcessingPath(apidata, proPathVector)
	print "no of Processed Dataset are ",proPathVector.size()
        if proPathVector.size() != 1:
	   raise dbsApi.DbsApiException(exception="One Processed Dataset could not be found")
        proDsId = dbsclient.intp_value(proPathVector[0].t_processed_dataset_id.getValue())
        print "*********************************************************************"
	print "Processed DatasetID = ",proDsId
        print "*********************************************************************"
        return proDsId
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      #print "Processed Dataset ID ",processedDatasetID
      #return processedDatasetID



   def createFileBlock(self, processedDataset, fileBlock):
	
      try:
        processedDatasetID = self.getProcessedDatasetID(processedDataset)
        print "processedDatasetID ",processedDatasetID
        apidata = dbsclient.Blockview_ClientAPIData()
        apidata.t_block_bytes = dbsclient.AINT(int(fileBlock.getNumberOfBytes()))
        apidata.t_block_files = dbsclient.AINT(int(fileBlock.getNumberOfFiles()))
        apidata.t_block_processed_dataset = dbsclient.AINT(int(processedDatasetID))
        apidata.t_block_status_name = dbsclient.ASTR(fileBlock.getBlockStatusName())
        blockID = self.client.insertFileBlock(apidata)
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
        processedDatasetID = self.getProcessedDatasetID(processedDataset)
        print "processedDatasetID ",processedDatasetID
	for eventCollection in eventCollectionList:
              apidata = dbsclient.Evcollview_ClientAPIData()
              apidata.t_info_evcoll_name = dbsclient.ASTR(eventCollection.getCollectionName())
              apidata.t_event_collection_collection_index = dbsclient.AINT(int(eventCollection.getCollectionIndex()))
              apidata.t_info_evcoll_events = dbsclient.AINT(int(eventCollection.getNumberOfEvents()))
              apidata.t_validation_status_name = dbsclient.ASTR("Dummy Validation status ")
              apidata.t_event_collection_processed_dataset = dbsclient.AINT(int(processedDatasetID))
              apidata.t_event_collection_is_primary = dbsclient.ACHR(eventCollection.getIsPrimary())
              apidata.t_evcoll_status_name = dbsclient.ASTR("Dummy Status")
              #apidata.t_info_evcoll_estimated_luminosity = dbsclient.ASTR("test_value_estimatedluminosity")
              evCollID = self.client.insertEventCollections(apidata)
              print "*********************************************************************"
              print "Event Collection ID ",evCollID
              print "*********************************************************************"
              self.insertFiles(evCollID, eventCollection.getFileList())
	
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "Event Collections inserted succesfully  ",len(eventCollectionList)

   def insertFiles(self, evCollID, files):
      try: 
         fileVector = dbsclient.EVCollFileVector()
         for afile in files:
            apidata = dbsclient.Fileview_ClientAPIData()
            apidata.t_file_status_name = dbsclient.ASTR(afile.getFileStatus())
            apidata.t_file_guid = dbsclient.ASTR(afile.getGuid())#BA7C8A55-DE62-D811-892C-00E081250436
            apidata.t_file_checksum = dbsclient.ASTR(afile.getCheckSum())#BA7C8A55-DE62-D811-892C-00E081250436
            apidata.t_file_logical_name = dbsclient.ASTR(afile.getLogicalFileName())
            apidata.t_file_inblock = dbsclient.AINT(int(afile.getFileBlockId()))
            apidata.t_file_type_name = dbsclient.ASTR(afile.getFileType())#EVDZip/ROOT_All
            #apidata.t_file_filesize = dbsclient.AINT(afile.getFileSize())#GET IT FROM SOMEWHERE
            apidata.t_file_filesize = dbsclient.ASTR(str(afile.getFileSize()))#GET IT FROM SOMEWHERE
            apidata.t_evcoll_file_evcoll = dbsclient.AINT(evCollID);#EventCollectionID
            fileVector.push_back(apidata)
         self.client.insertFiles(fileVector)

      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      print "File inserted succesfully ",len(files)



   def getDatasetContents(self, pathName):
      """public api method, that return details regarding EvColls
         contained in Processed Dataset
         Specifically the File Blocks and file there in.
         * Take pathName of form /PriDS/DT/ProcDS as input
         * For now dumps information on screen.
      """
      # get the names of primary dataset, data tier and processed dataset
      print "Inside getDatasetContents pathname is ",pathName
      tokens = pathName.split('/')
      primaryDSName = tokens[1]
      dataTier = tokens[2]
      processedDSName = tokens[3]

      try :
         evcollInfo = dbsclient.Crabevcollview_ClientAPIData()
         evcollInfo.t_data_tier_name = dbsclient.ASTR(dataTier)
         evcollInfo.t_primary_dataset_name = dbsclient.ASTR(primaryDSName)
         evcollInfo.t_processed_dataset_name = dbsclient.ASTR(processedDSName)
         evcollInfoRet = dbsclient.CrabEvcollVector()
         self.client.readCRABEvColls(evcollInfo, evcollInfoRet)
      except RuntimeError,e:
         print "Exception ", e
         raise dbsApi.DbsApiException(exception=e)
      blockECMap = {}
      for i in range( evcollInfoRet.size() ) :
          print "************************************************************************************************"
          currECInfo = evcollInfoRet[i]
          blockid = dbsclient.intp_value(currECInfo.t_block_id.getValue())
          evcollName = dbsclient.stringp_value(currECInfo.t_info_evcoll_name.getValue())
          events = dbsclient.intp_value(currECInfo.t_info_evcoll_events.getValue())
          print "blockid, evcollName, events", blockid, evcollName, events
          if blockECMap.has_key(blockid):
             blockECMap[blockid].append((evcollName, events)) 
          else:
             blockECMap[blockid] = [(evcollName, events)] 
      #print blockECMap    
      fileBlockList = []
      for eachBlockId in blockECMap.keys():
         fileblockName = pathName+'/#'+str(eachBlockId)
         fileBlock = dbsFileBlock.DbsFileBlock(blockId=eachBlockId, blockName=fileblockName) 
         for evcollName, events in blockECMap[eachBlockId]: 
             eventCollection = dbsEventCollection.DbsEventCollection(collectionName=evcollName, numberOfEvents=events)
             fileBlock.addEventCollection(eventCollection)
         print fileBlock
         fileBlockList.append(fileBlock)

      return fileBlockList  


   def getDatasetProvenance(self, pathName, parentDataTiers):
      """ API Call that returns list of DBS 
           Datasets (Processed/Analysis) corresponding 
           to all parent datatiers requested by user
      """ 
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

      dt_procDS_map = {}

      for i in range( provInfoRet.size() ) :
          print "************************************************************************************************"
          currProvInfo = provInfoRet[i]
          processed_dataset_name = dbsclient.stringp_value(currProvInfo.t_processed_dataset_name.getValue())
          print "    t_processed_dataset_name: " ,processed_dataset_name
          #print "    t_processing_path_data_tier: ", dbsclient.intp_value(currProvInfo.t_processing_path_data_tier.getValue())
          #print "    t_processing_path_id: ", dbsclient.intp_value(currProvInfo.t_processing_path_id.getValue())
          data_tier_name = dbsclient.stringp_value(currProvInfo.t_parentage_type_name.getValue())  
          print "    t_parentage_type_name: " ,data_tier_name
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

          if not dt_procDS_map.has_key(data_tier_name):
             dt_procDS_map[data_tier_name]=processed_dataset_name

      parentPathList = []
      dsList = DbsDatasetList()
      print "dt_procDS_map: ", dt_procDS_map    
      for aAskedTier in parentDataTiers:
         parentPath = "/"+primaryDSName+"/"+aAskedTier+"/"+dt_procDS_map[aAskedTier]
         dsList.append(DbsDataset(primaryDSName, parentPath, aAskedTier, 'PU'))
         parentPathList.append(parentPath)
         print parentPath
      #return parentPathList
      return dsList


   def readProvInfoParent(self, provInfo):
      provInfoRet = dbsclient.DSProvParentVector()
 
      self.client.getDatasetProvenenceParent(provInfo, provInfoRet)
      print "No of Prov Info records returned ", provInfoRet.size()

      return provInfoRet


   def readProvInfoChild(self, provInfo):
      provInfoRet = dbsclient.DSProvChildVector()
 
      self.client.getDatasetProvenenceChild(provInfo, provInfoRet)
      print "No of Prov Info records returned ", provInfoRet.size()

      return provInfoRet


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
   
