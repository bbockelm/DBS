#include "DBSApi.hpp"
#include "ManagerImpls.hpp"
#include "BizLayerException.hpp"
#include "DBException.hpp"
#include "Configuration.hpp"
#include <exception>

using namespace std;
DBSApi::DBSApi() {
	try {
		Configuration* conf = Configuration::instance();
		this->dbManager = new DBManagement(conf->getDsn(), conf->getDbUser() , conf->getDbPasswd());
		this->dbManager->open();
	} catch (DBException &e)  {
		throw BizLayerException(e.report());
	}

}


DBSApi::~DBSApi() {
	this->dbManager->close();
	delete this->dbManager;
}

string DBSApi::makeXMLTag(TableInterface* table, string tagID, vector<string>& keys) {


	string xml = "<"+tagID;

	for (int i = 0; i != keys.size(); ++i ) {
		string value;
                string colname = keys.at(i);
		string col;
		if(( value = table->getStrValue(colname)) != "" ) {
        		string col =  util.getTokenAt(colname,1);
                        xml += " "+col+ "="+value; 
		}
	}
	xml += "/>";
}	


std::string DBSApi::write_xml_header(){

   string header="<?xml version='1.0' standalone='yes'?><dbs>";
   return header;
}

std::string DBSApi::write_xml_footer(){
   string footer="</dbs>";
   return footer;
}


std::vector<std::string> DBSApi::getNameElements(std::string datasetPathName) {
        if (datasetPathName.length() < 1) {
                throw (string)"datasetPathName is NULL";
        }
        Util util;
        vector<string> tokens;
        util.Tokenize(datasetPathName, tokens, (string)"/");
	/*tokens.push_back("abc");
	tokens.push_back("abc");
	tokens.push_back("abc");*/
        if (tokens.size() != 3) {
                throw BizLayerException((string)"datasetPathName is invalid. Correct format is /PrimaryDataset/DataTier/ProcessedDataset");
        }

        return tokens;
}


int DBSApi::getProcessingPathID(std::string datasetPathName) {
	vector<string> elements = getNameElements(datasetPathName);
	string primaryDSName = elements.at(0);
	string dataTier = elements.at(1);
	string processedDSName = elements.at(2);

	Datasetpathmultirow aRow;
	DatasetpathMultiTable aTable;
	util.setValue(&aRow, "t_primary_dataset.name", "STRING", primaryDSName);
	util.setValue(&aRow, "t_data_tier.name", "STRING", dataTier);
	util.setValue(&aRow, "t_processing_name.name", "STRING", processedDSName);
	DatasetPathManager manager(this->dbManager);
	manager.read(&aRow, &aTable);
	
	if(!aTable.next()) {
		throw BizLayerException((string)"Processing Path not found for "+datasetPathName);
	}
	int value = aTable.getIntValue("t_processing.id");
	if(aTable.next()) {
		throw BizLayerException((string)"More then one Processing Path found for "+datasetPathName);
	}
	cout<<"Processing Path ID "<<value<<endl;
	return value;


}

int DBSApi::getProcessedDatasetID(std::string datasetPathName) {
        vector<string> elements = getNameElements(datasetPathName);
        string primaryDSName = elements.at(0);
        string dataTier = elements.at(1);
        string processedDSName = elements.at(2);

        Datasetpathmultirow aRow;
        DatasetpathMultiTable aTable;

        util.setValue(&aRow, "t_primary_dataset.name", "STRING", primaryDSName);
        util.setValue(&aRow, "t_data_tier.name", "STRING", dataTier);
        //util.setValue(&aRow, "t_processed_dataset.name", "STRING", processedDSName);
        util.setValue(&aRow, "t_processing_name.name", "STRING", processedDSName);
        DatasetPathManager manager(this->dbManager);
        manager.read(&aRow, &aTable);
	if(!aTable.next()) {
		throw BizLayerException((string)"Processed Dataset not found for "+datasetPathName);
	}
	int value = aTable.getIntValue("t_processed_dataset.id");
	if(aTable.next()) {
		throw BizLayerException((string)"More then one Processed Dataset found for "+datasetPathName);
	}
	cout<<"Processed Dataset ID "<<value<<endl;
	return value;

}



int DBSApi::createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId){
	if(primaryDataset == NULL) {
		throw BizLayerException("primaryDataset is NULL");
	}
	cout<<"Name is "<<primaryDataset->name<<endl;
	if (primaryDataset->name.length() < 1) {
		throw BizLayerException("Primary Dataset name is NULL");
	}
	PrimarydatasetMultiTable table;
	Primarydatasetmultirow* aRow = new Primarydatasetmultirow();
	util.setValue(aRow, "t_primary_dataset.name", "STRING", primaryDataset->name);
	vector<Primarydatasetmultirow*> vect;
	vect.push_back(aRow);
        PrimaryDatasetManager manager(this->dbManager);
	primaryDatasetId = manager.write(vect, &table);
	return 1;
}

int DBSApi::createProcessedDataset(DBS__ProcessedDataset* processedDataset, int& processedDatasetId) {
	if(processedDataset == NULL) {
		throw BizLayerException("processedDataset is NULL");
	}

	ProcessingpathMultiTable table;

	DBS__ProcessingPath* procPath = processedDataset->processingPath;
	DBS__Application* app = procPath->application;
	int parentProcPathID;
	if (procPath->parent != "") {
		parentProcPathID = this->getProcessingPathID(procPath->parent);
	}
	
	string isOpen = "";
	if(processedDataset->isDatasetOpen) {
		isOpen = "y";
	} else {
		isOpen = "n";
	}
	Processingpathmultirow* aRow = new Processingpathmultirow();
	if(procPath->parent != "") {
		//util.setIntValue(aRow, "t_processing_path.parent",  &parentProcPathID);
		util.setIntValue(aRow, "t_processing.input",  &parentProcPathID);
	}
	util.setValue(aRow, "t_app_family.name", "STRING", app->family);
	util.setValue(aRow, "t_application.executable", "STRING",app->executable);
	//util.setValue(aRow, "t_app_config.parameter_set", "STRING", app->parameterSet);
	util.setValue(aRow, "t_application.app_version", "STRING", app->version);
	util.setValue(aRow, "t_data_tier.name","STRING", procPath->dataTier);
	//util.setValue(aRow, "t_processed_dataset.is_open", "CHARACTER", isOpen);
	util.setValue(aRow, "t_processing.is_open", "CHARACTER", isOpen);
	//util.setValue(aRow, "t_processed_dataset.name", "STRING", processedDataset->processedDatasetName);
	util.setValue(aRow, "t_processing_name.name", "STRING", processedDataset->processedDatasetName);
	util.setValue(aRow, "t_primary_dataset.name", "STRING", processedDataset->primaryDatasetName);
	util.setValue(aRow, "t_parameter_set.hash", "STRING", "DUMMY");
	util.setValue(aRow, "t_parameter_set.content", "STRING", "DUMMYCONTENT");
	util.setIntValue(aRow, "t_processed_dataset.id", processedDataset->id );
	//cout<<"Making manager "<<endl;
	ProcessingPathManager manager(this->dbManager);
	vector<Processingpathmultirow*> tmpVect;
	tmpVect.push_back(aRow);
	//cout<<"calling write "<<endl;
	processedDatasetId = manager.write(tmpVect, &table);
	//cout<<"DONE write "<<endl;
	return 1;

}



int DBSApi::createFileBlock(std::string datasetPathName, DBS__Block* block, int& fileBlockId){
	if(block == NULL) {
		throw BizLayerException("block is NULL");
	}

	int processedDatasetID = this->getProcessedDatasetID(datasetPathName);
	BlockviewMultiTable table;
	Blockviewmultirow* aRow = new Blockviewmultirow();
	util.setIntValue(aRow, "t_block.bytes", block->numberOfBytes);
	util.setIntValue(aRow, "t_block.files", block->numberOfFiles);
	util.setIntValue(aRow, "t_block.processing", &processedDatasetID);
	util.setValue(aRow, "t_block_status.name", "STRING", block->blockStatusName);

	vector<Blockviewmultirow*> vect;
	vect.push_back(aRow);
        BlockViewManager manager(this->dbManager);
	fileBlockId = manager.write(vect, &table);
	return 1;

	

}

int DBSApi::insertEventCollections( std::vector<DBS__EventCollection*> eventCollectionList, int& result) {
	for(int i = 0; i != eventCollectionList.size() ; ++i) {
		DBS__EventCollection* aEventCollection = eventCollectionList.at(i);
		result = recInsertEventCollection(aEventCollection);
	}
}

int DBSApi::recInsertEventCollection(DBS__EventCollection* eventCollection) {
	if(eventCollection == NULL) {
		throw BizLayerException("eventCollection is NULL");
	}
	int fileSize = eventCollection->fileList.size();
	if(fileSize == 0) {
		throw BizLayerException("File List Empty. You must provide FileList to Insert EventCollection");
	}
	int firstBlockID = *(((DBS__File*)eventCollection->fileList.at(0))->fileBlockId);
	for(int i = 1; i != fileSize; ++i) {
		if(firstBlockID  != *(((DBS__File*)eventCollection->fileList.at(i))->fileBlockId) ) {
			throw BizLayerException("All files in same EvColl should have same Block ID");
		}
	}
	int processedDatasetID;
	Map_iter mi = procDSIdMap.find(eventCollection->datasetPathName);
	if(mi != procDSIdMap.end()) {
		processedDatasetID = mi->second;
	} else {
		//cout<<"calling this->getProcessedDatasetID"<<endl;
		processedDatasetID = this->getProcessedDatasetID(eventCollection->datasetPathName);
		cout<<"processedDatasetID "<<processedDatasetID<<endl;
		procDSIdMap.insert(MapEntry(eventCollection->datasetPathName, processedDatasetID ));
	}
	int parentEventCollectionID = 0;
	if(eventCollection->parent != NULL) {
		//cout<<">>>>>>>>>>>>>>>>>>>PARENt is NOT NULL. Calling insert on Parent"<<endl;
		parentEventCollectionID = this->recInsertEventCollection(eventCollection->parent);
		//cout<<"-------------------->parentEventCollectionID "<<parentEventCollectionID<<endl;
	}
	if(eventCollection->collectionId != NULL) {
		return *eventCollection->collectionId;
	}
	int eventCollectionID;
	if (eventCollection->parent != NULL) {
		cout<<"Making EvcollviewMultiTable"<<endl;
		EvcollviewMultiTable table;
		Evcollviewmultirow* aRow = new Evcollviewmultirow();
		this->setECValues(aRow, eventCollection,processedDatasetID);
		
		util.setValue(aRow, "t_parentage_type.name", "STRING", eventCollection->parentageType);
		if(parentEventCollectionID != 0) {
			util.setIntValue(aRow, "t_evcoll_parentage.parent",&parentEventCollectionID);
		}
		vector<Evcollviewmultirow*> vect;
		vect.push_back(aRow);
	        EvCollViewManager manager(this->dbManager);
		manager.write(vect, &table);
		if(table.getNoOfRows() > 0) {
			string value = table.getStrValue(0, "t_event_collection.id");
			eventCollectionID =  util.atoi(value);
		}

	} else {
		cout<<"Making EvcollviewnoparentMultiTable"<<endl;
		EvcollviewnoparentMultiTable table;
		Evcollviewnoparentmultirow* aRow = new Evcollviewnoparentmultirow();
		this->setECValues(aRow, eventCollection,processedDatasetID);
		vector<Evcollviewnoparentmultirow*> vect;
		vect.push_back(aRow);
	        EvCollViewNoParentManager manager(this->dbManager);
		manager.write(vect, &table);
		if(table.getNoOfRows() > 0) {
			string value = table.getStrValue(0, "t_event_collection.id");
			eventCollectionID =  util.atoi(value);
		}

	}

	cout<<"...................>Returnning eventCollectionID "<<eventCollectionID<<endl;
	insertFiles(eventCollectionID, eventCollection->fileList);
	return eventCollectionID;


}

void DBSApi::setECValues(RowInterface* aRow, DBS__EventCollection* eventCollection, int processedDatasetID) {
	util.setIntValue(aRow, "t_event_collection.collection_index", eventCollection->collectionIndex);
	util.setIntValue(aRow, "t_event_collection.events", eventCollection->numberOfEvents);
	util.setValue(aRow, "t_event_collection.name", "STRING", eventCollection->collectionName);
	util.setValue(aRow, "t_evcoll_status.name", "STRING", eventCollection->status);
	util.setIntValue(aRow, "t_event_collection.processed_dataset", &processedDatasetID);
	//cout<<"length of status is "<<eventCollection->status.length()<<endl;
}

int DBSApi::getDatasetContents(std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList) {
	/*DBS__Block* aBlock = this->getBlock();
	aBlock->blockId = this->getInt(util.atoi("1234"));
	blockList.push_back(aBlock);
	return 1;*/
	if(listFiles) {
		return this->getDatasetContents(datasetPathName,blockList);
	}
	vector<string> tokens = this->getNameElements(datasetPathName);

	string primaryDSName = tokens.at(0);
	string dataTier = tokens.at(1);
	string processedDSName = tokens.at(2);

	CrabevcollviewMultiTable table;
	Crabevcollviewmultirow aRow;
	util.setValue(&aRow, "t_primary_dataset.name", "STRING", primaryDSName);
	util.setValue(&aRow, "t_data_tier.name", "STRING", dataTier);
	util.setValue(&aRow, "t_processing_name.name", "STRING", processedDSName);
	//util.setValue(&aRow, "t_event_collection.id", "INTEGER", "271478");
        CrabEvCollViewManager manager(this->dbManager);
	manager.read(&aRow, &table);
	


	vector<Crabevcollviewmultirow*> rows = table.getRows();
	//return 1;
	while (table.next()) {
		string value;
		if((value = table.getStrValue("t_block.id")) != "" ) {
			bool found = false;
			DBS__Block* aBlock;
			for (int j = 0 ; j != blockList.size() ; ++j ) {
				aBlock = (DBS__Block*)blockList.at(j);
				if ( *(aBlock->blockId) == util.atoi(value) ) {
					found = true;
					break;
				}
			}

			if (!found) {
				aBlock = this->getBlock();
				aBlock->blockId = this->getInt(util.atoi(value));
			        aBlock->blockName = datasetPathName.substr(1, (datasetPathName.length() - 1) ) + "/#/" + value;
			}
			DBS__EventCollection * aEventCollection = this->getEventCollection();

			if((value = table.getStrValue("t_event_collection.name")) != "" ) {
				aEventCollection->collectionName = value;
			}			
			if((value = table.getStrValue("t_event_collection.events")) != "" ) {
				aEventCollection->numberOfEvents = this->getInt(util.atoi(value));
			}
			if((value = table.getStrValue("t_event_collection.id")) != "" ) {
				aEventCollection->collectionId = this->getInt(util.atoi(value));
			}

			aBlock->eventCollectionList.push_back(aEventCollection);

			if (!found) {
				cout << "\n\n\nAdding a new Block" << endl;
				blockList.push_back(aBlock);
			}
		}
	}
	return 1;
}

int DBSApi::getDatasetContents(std::string datasetPathName, std::vector<DBS__Block*>& blockList) {

	vector<string> tokens = this->getNameElements(datasetPathName);

	string primaryDSName = tokens.at(0);
	string dataTier = tokens.at(1);
	string processedDSName = tokens.at(2);

	CrabevcollfileviewMultiTable table;
	Crabevcollfileviewmultirow aRow;
	util.setValue(&aRow, "t_primary_dataset.name", "STRING", primaryDSName);
	util.setValue(&aRow, "t_data_tier.name", "STRING", dataTier);
	util.setValue(&aRow, "t_processing_name.name", "STRING", processedDSName);
	//util.setValue(&aRow, "t_event_collection.id", "INTEGER", "272232");

        CrabEvCollFileViewManager manager(this->dbManager);
	manager.read(&aRow, &table);
	while (table.next()) {
		string value;
		if((value = table.getStrValue("t_block.id")) != "" ) {
			bool found = false;
			DBS__Block* aBlock;
			for (int j = 0 ; j != blockList.size() ; ++j ) {
				aBlock = (DBS__Block*)blockList.at(j);
				if ( *(aBlock->blockId) == util.atoi(value) ) {
					found = true;
					break;
				}
			}
			if (!found) {
				aBlock = this->getBlock();
				aBlock->blockId = this->getInt(util.atoi(value));
			        aBlock->blockName = datasetPathName.substr(1, (datasetPathName.length() - 1) ) + "/#/" + value;
			}
			if((value = table.getStrValue("t_event_collection.id")) != "" ) {
				bool foundEC = false;
				DBS__EventCollection * aEventCollection;
				for (int j = 0 ; j != aBlock->eventCollectionList.size() ; ++j ) {
					aEventCollection = (DBS__EventCollection*)aBlock->eventCollectionList.at(j);
					if ( *(aEventCollection->collectionId) == util.atoi(value) ) {
						foundEC = true;
						break;
					}
				}
				if (!foundEC) {
					aEventCollection = this->getEventCollection();
					aEventCollection->collectionId = this->getInt(util.atoi(value));
					if((value = table.getStrValue("t_event_collection.name")) != "" ) {
						aEventCollection->collectionName = value;
					}			
					if((value = table.getStrValue("t_event_collection.events")) != "" ) {
						aEventCollection->numberOfEvents = this->getInt(util.atoi(value));
					}
				}
				//this->addFile(tmpRow, aEventCollection->fileList);
				this->addFile(&table, aEventCollection->fileList);
				//cout<<"size of fileList "<<aEventCollection->fileList.size()<<endl;
				
				if (!foundEC) {
					aBlock->eventCollectionList.push_back(aEventCollection);
				}
			}

			if (!found) {
				blockList.push_back(aBlock);
			}
		}
	}
	return 1;
}

int DBSApi::getDatasetFileBlocks(std::string datasetPathName, std::vector<DBS__Block*>& blockList){

	int processedDatasetID = getProcessedDatasetID(datasetPathName);

	Pdblockviewmultirow aRow;
	PdblockviewMultiTable table;
	util.setIntValue(&aRow, "t_processed_dataset.id", &processedDatasetID );
	PDBlockViewManager manager(this->dbManager);
	manager.read(&aRow, &table);

	while (table.next()) {

		DBS__Block* newBlock = this->getBlock();
		string value;
		if((value = table.getStrValue("t_block.id")) != "" ) {
			newBlock->blockId = this->getInt(util.atoi(value));
			newBlock->blockName = datasetPathName.substr(1, (datasetPathName.length() - 1) ) + "/#/" + value;
		}
		if((value = table.getStrValue("t_block_status.name")) != "" ) {
			newBlock->blockStatusName = value;
		}
		if((value = table.getStrValue("t_block.files")) != "" ) {
			newBlock->numberOfFiles = this->getInt(util.atoi(value));
		}
		if((value = table.getStrValue("t_block.bytes")) != "" ) {
			newBlock->numberOfBytes = this->getInt(util.atoi(value));
		}
		blockList.push_back(newBlock);
	}
	return 1;

}



DBS__Block* DBSApi::getBlock() {
	//cout<<"DBSApi::getBlock()"<<endl;
	return new DBS__Block();
}
DBS__File* DBSApi::getFile() {
	//cout<<"DBSApi::getFile()"<<endl;
	return new DBS__File();
}
DBS__EventCollection* DBSApi::getEventCollection() {
	//cout<<"DBSApi::getEventCollection()"<<endl;
	return new DBS__EventCollection();
}

int* DBSApi::getInt(int value) {
	//cout<<"DBSApi::getInt"<<endl;
	int* i = (int*)new int(value);
	return i;
}

int DBSApi::getEventCollectionID(DBS__EventCollection* eventCollection) {
	cout<<"Inside getEventCollectionID"<<endl;
	if(eventCollection->collectionId != NULL) {
		return *eventCollection->collectionId;
	}
	EvcollfileviewMultiTable table;
	Evcollfileviewmultirow aRow;
	util.setIntValue(&aRow, "t_event_collection.collection_index", eventCollection->collectionIndex);
	util.setIntValue(&aRow, "t_event_collection.events", eventCollection->numberOfEvents);
	util.setValue(&aRow, "t_event_collection.name", "STRING", eventCollection->collectionName);
	util.setValue(&aRow, "t_evcoll_status.name", "STRING", eventCollection->status);

	//Assuming only one file per event collection
	DBS__File* aFile = eventCollection->fileList.at(0);
	util.setValue(&aRow, "t_file.guid", "STRING", aFile->guid);
	util.setValue(&aRow, "t_file.logical_name", "STRING", aFile->logicalFileName);
	util.setValue(&aRow, "t_file_type.name", "STRING", aFile->fileType);
	util.setValue(&aRow, "t_file_status.name", "STRING", aFile->fileStatus);
	util.setIntValue(&aRow, "t_file.inblock", aFile->fileBlockId);
	util.setIntValue(&aRow, "t_file.filesize", aFile->fileSize);
	//util.setIntValue(aRow, "t_evcoll_file.evcoll", &eventCollectionID);

	EvCollFileViewManager manager(this->dbManager);

	manager.read(&aRow, &table);
	if(!table.next()) {
		throw BizLayerException((string)"No EventCollection found.");
	}
	int value = table.getIntValue("t_event_collection.id");
	if(table.next()) {
		throw BizLayerException((string)"More than one EventCollection found.");
	}
	cout<<"EventCollection ID "<<value<<endl;
	return value;

}

int DBSApi::insertFiles(int eventCollectionID, vector<DBS__File*> fileList) {
	FileviewMultiTable table;
	vector<Fileviewmultirow*> vect;
	for(int i = 0; i != fileList.size(); ++i) {
		Fileviewmultirow* aRow = new Fileviewmultirow();
		DBS__File* aFile = fileList.at(i);
		util.setValue(aRow, "t_file.guid", "STRING", aFile->guid);
		util.setValue(aRow, "t_file.logical_name", "STRING", aFile->logicalFileName);
		util.setValue(aRow, "t_file_type.name", "STRING", aFile->fileType);
		util.setValue(aRow, "t_file_status.name", "STRING", aFile->fileStatus);
		util.setValue(aRow, "t_file.checksum", "STRING", aFile->checksum);
		util.setIntValue(aRow, "t_file.inblock", aFile->fileBlockId);
		util.setIntValue(aRow, "t_file.filesize", aFile->fileSize);
		util.setIntValue(aRow, "t_evcoll_file.evcoll", &eventCollectionID);
		vect.push_back(aRow);
	}
        FileViewManager manager(this->dbManager);
	return manager.write(vect, &table);

}

int DBSApi::listFiles(int eventCollectionID, int blockID, vector<DBS__File*>& fileList) {
	FileviewMultiTable aTable;
	Fileviewmultirow aRow;
	util.setIntValue(&aRow, "t_evcoll_file.evcoll", &eventCollectionID);
	util.setIntValue(&aRow, "t_file.inblock", &blockID);
        FileViewManager manager(this->dbManager);
	manager.read(&aRow, &aTable);
	while(aTable.next()) {
		//this->addFile(tmpRow,fileList);
		this->addFile(&aTable,fileList);
	}
	return 1;
}


//int DBSApi::addFile(RowInterface* aRow, vector<DBS__File*>& fileList) {
int DBSApi::addFile(TableInterface* table, vector<DBS__File*>& fileList) {
		string value;
		DBS__File* newFile;
		if((value = table->getStrValue("t_file.logical_name")) != "" ) {
			newFile = this->getFile();
			newFile->logicalFileName = value;
			cout<<"Creating a new file"<<endl;
		} else {
			cout<<"t_file.logical_name not found "<<endl;
			return 0;
		}
		if((value = table->getStrValue("t_file.guid")) != "" ) {
			newFile->guid = value;
		}
		if((value = table->getStrValue("t_file_type.name")) != "" ) {
			newFile->fileType = value;
		}
		if((value = table->getStrValue("t_file.checksum")) != "" ) {
			newFile->checksum = value;
		}
		if((value = table->getStrValue( "t_file.inblock")) != "" ) {
			newFile->fileBlockId = this->getInt(util.atoi(value));
		}
		if((value = table->getStrValue("t_file.filesize")) != "" ) {
			newFile->fileSize = this->getInt(util.atoi(value));
		}
		fileList.push_back(newFile);
	return 1;
}

int DBSApi::listDataset(std::string datasetPathName, std::vector<std::string>& datasetList) {
	vector<string> elements = getNameElements(datasetPathName);
	string primaryDSName = elements.at(0);
	string dataTier = elements.at(1);
	string processedDSName = elements.at(2);

	Datasetpathmultirow aRow;
	DatasetpathMultiTable aTable;
	if(primaryDSName != "*") {
		util.setValue(&aRow, "t_primary_dataset.name", "STRING", primaryDSName);
	}
	if(dataTier  != "*") {
		util.setValue(&aRow, "t_data_tier.name", "STRING", dataTier);
	}
	if(processedDSName != "*") {
		util.setValue(&aRow, "t_processed_dataset.name", "STRING", processedDSName);
	}
	DatasetPathManager manager(this->dbManager);
	manager.read(&aRow, &aTable);

	while(aTable.next()) {
		
		string fullPath = "/" +
			aTable.getStrValue("t_primary_dataset.name") +
			"/" +
			aTable.getStrValue("t_data_tier.name") +
			"/" +
			aTable.getStrValue("t_processed_dataset.name");
		datasetList.push_back(fullPath);
	}

}

int DBSApi::mergeEventCollections(vector<DBS__EventCollection*> inputEventCollectionList,DBS__EventCollection* outputEventCollection, int& result){
}
/*int DBSApi::mergeEventCollections(vector<DBS__EventCollection*> inputEventCollectionList,DBS__EventCollection* outputEventCollection, int& result){
	if(outputEventCollection == NULL) {
		throw BizLayerException("outputEventCollection is NULL");
	}
	if(inputEventCollectionList.size() < 2) {
		throw BizLayerException("Less than two input EventCollection given. Expected atleast two");
	}
	vector<int> evChildList;
	vector<int> evParentList;
	vector<int> evIDList;
	cout<<"size of inputEventCollectionList "<<inputEventCollectionList.size()<<endl;
	for(int i = 0; i != inputEventCollectionList.size() ; ++i) {
		cout<<"Calling getEventCollectionID for "<<i<<endl;
		evIDList.push_back(this->getEventCollectionID(inputEventCollectionList.at(i)));
		cout<<"Done calling getEventCollectionID . ID is "<<evIDList.at(i)<<endl;
	}
	//Create a New Status called Megred in t_evcoll_status table
	int statusID = this->createStatus("MERGED");
	cout<<"statusID "<<statusID<<endl;
	//Set Status of all input Event Collection as Merged
	this->updateStatus(evIDList, statusID);	

	for(int i = 0; i != inputEventCollectionList.size() ; ++i) {
		DBS__EventCollection* aEventCollection = inputEventCollectionList.at(i);
		//Get Childs of this Event Collection
		this->getEVChildOrParents(evIDList.at(i), true, evChildList);
		//Get Parents of this Event Collection
		this->getEVChildOrParents(evIDList.at(i), false, evParentList);
	}
	vector<DBS__EventCollection*> ecToInsert;
	ecToInsert.push_back(outputEventCollection);
	int newECID;
	this->insertEventCollections(ecToInsert, newECID);
	cout<<"New EC that got inserted has ID "<<newECID<<endl;
	this->insertEVParents(evParentList, newECID);
	this->updateEVChilds(evChildList, newECID);

}

int DBSApi::insertEVParents(vector<int> evParentList, int eventCollectionID) {
	cout<<"Inserting new parents"<<endl;
	vector<Evcollparentageviewmultirow*> vect;
	EvcollparentageviewMultiTable table;
	for(int i = 0; i != evParentList.size() ; ++i) {
		Evcollparentageviewmultirow* aRow = new Evcollparentageviewmultirow();
		util.setIntValue(aRow, "t_evcoll_parentage.child", &eventCollectionID);
		util.setIntValue(aRow, "t_evcoll_parentage.parent", &evParentList.at(i));
		vect.push_back(aRow);
	}
	EvCollParentageViewManager manager(this->dbManager);
	return manager.write(vect, &table);
}

int DBSApi::updateEVChilds(vector<int> evChildList, int eventCollectionID) {
	cout<<"Updating childs"<<endl;
	vector<Evcollparentageviewmultirow*> vect;
	EvcollparentageviewMultiTable table;
	for(int i = 0; i != evChildList.size() ; ++i) {
		Evcollparentageviewmultirow* aRow = new Evcollparentageviewmultirow();
		util.setIntValue(aRow, "t_evcoll_parentage.child", &evChildList.at(i));
		util.setIntValue(aRow, "t_evcoll_parentage.parent", &eventCollectionID);
		vect.push_back(aRow);
	}
	EvCollParentageViewManager manager(this->dbManager);
	return manager.update(vect, &table);
}

int DBSApi::updateStatus(vector<int> evIDList, int statusID) {
	cout<<"Updating the status of EC as "<<statusID<<endl;
	cout<<"evIDList size is "<<evIDList.size()<<endl;
	vector<Evcollsingleviewmultirow*> vect;
	EvcollsingleviewMultiTable table;
	for(int i = 0; i != evIDList.size() ; ++i) {
		Evcollsingleviewmultirow* aRow = new Evcollsingleviewmultirow();
		util.setIntValue(aRow, "t_event_collection.status", &statusID);
		util.setIntValue(aRow, "t_event_collection.id", &evIDList.at(i));
		vect.push_back(aRow);
	}
       	EvCollSingleViewManager manager(this->dbManager);
	return manager.update(vect, &table);
}

int DBSApi::createStatus(string status) {
	cout<<"Creating a new Status "<<status<<endl;
	EvcollstatusviewMultiTable table;
	vector<Evcollstatusviewmultirow*> vect;
	Evcollstatusviewmultirow* aRow = new Evcollstatusviewmultirow();
	util.setValue(aRow, "t_evcoll_status.name", "STRING", status);
	vect.push_back(aRow);
       	EvCollStatusViewManager manager(this->dbManager);
	return manager.write(vect, &table);
}

int DBSApi::getEVChildOrParents(int eventCollectionID, bool type , vector<int>& evChildList) {
	//type True is Child , type False is Parent

	cout<<"Inside getEVChildOrParents "<<endl;
	EvcollparentageviewMultiTable table;
	Evcollparentageviewmultirow aRow;
	if(type) {
		util.setIntValue(&aRow, "t_evcoll_parentage.parent", &eventCollectionID);
	} else {
		util.setIntValue(&aRow, "t_evcoll_parentage.child", &eventCollectionID);
	}	

	EvCollParentageViewManager manager(this->dbManager);

	manager.read(&aRow, &table);
	int noOfRows = table.getNoOfRows();
	cout << "Number of Rows from DB is "<<noOfRows<<endl;
	vector<Evcollparentageviewmultirow*> rows = table.getRows();
	util.setSchema(table.getSchema());

	for(int i = 0; i != noOfRows; ++i) {
		Evcollparentageviewmultirow* tmpRow = (Evcollparentageviewmultirow*)rows.at(i);
		string value;
		if(type) {
			if((value = util.getStrValue(tmpRow, "t_evcoll_parentage.child")) != "" ) {
				evChildList.push_back(util.atoi(value));
			}
		} else {
			if((value = util.getStrValue(tmpRow, "t_evcoll_parentage.parent")) != "" ) {
				evChildList.push_back(util.atoi(value));
			}
		}
	}
}
*/
