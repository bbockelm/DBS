#include "DBSApi.hpp"
#include "ManagerImpls.hpp"
#include "BizLayerException.hpp"
#include <exception>

using namespace std;
DBSApi::DBSApi() {
}


DBSApi::~DBSApi() {
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
	util.setValue(&aRow, "t_processed_dataset.name", "STRING", processedDSName);
	DatasetPathManager dsPathMgr;
	dsPathMgr.read(&aRow, &aTable);
	int noOfRows = aTable.getNoOfRows();
	cout<<"No of Rows "<<noOfRows<<endl;
	if ( noOfRows < 1 ) {
		throw BizLayerException((string)"Processing Path not found for "+datasetPathName);
	}

	if ( noOfRows > 1 ) {
		throw BizLayerException((string)"More then one Processing Path found for "+datasetPathName);
	}

	vector<Datasetpathmultirow*> rows = aTable.getRows();
	util.setSchema(aTable.getSchema());
	Datasetpathmultirow* tmpRow = (Datasetpathmultirow*)rows.at(0);
	return util.getIntValue(tmpRow, "t_processing_path.id");


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
        util.setValue(&aRow, "t_processed_dataset.name", "STRING", processedDSName);

        DatasetPathManager dsPathMgr;
        dsPathMgr.read(&aRow, &aTable);
        int noOfRows = aTable.getNoOfRows();

        if ( noOfRows < 1 ) {
           throw BizLayerException((string)"Processed Dataset not found for "+datasetPathName);
        }

        if ( noOfRows > 1 ) {
           throw BizLayerException((string)"More then one Processed Dataset found for "+datasetPathName);
        }

        vector<Datasetpathmultirow*> rows = aTable.getRows();
        util.setSchema(aTable.getSchema());
        Datasetpathmultirow* tmpRow = (Datasetpathmultirow*)rows.at(0);
        return util.getIntValue(tmpRow, "t_processed_dataset.id");


}



int DBSApi::createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId){
	cout<<"Name is "<<primaryDataset->name<<endl;
	if (primaryDataset->name.length() < 1) {
		throw BizLayerException("Primary Dataset name is NULL");
	}
	PrimarydatasetMultiTable table;
	Primarydatasetmultirow* aRow = new Primarydatasetmultirow();
	util.setValue(aRow, "t_primary_dataset.name", "STRING", primaryDataset->name);
	vector<Primarydatasetmultirow*> vect;
	vect.push_back(aRow);
        PrimaryDatasetManager manager;
	primaryDatasetId = manager.write(vect, &table);
	return 1;
}

int DBSApi::createProcessedDataset(DBS__ProcessedDataset* processedDataset, int& processedDatasetId) {
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
		util.setIntValue(aRow, "t_processing_path.parent",  &parentProcPathID);
	}
	util.setValue(aRow, "t_app_family.name", "STRING", app->family);
	util.setValue(aRow, "t_application.executable", "STRING",app->executable);
	util.setValue(aRow, "t_app_config.parameter_set", "STRING", app->parameterSet);
	util.setValue(aRow, "t_application.app_version", "STRING", app->version);
	util.setValue(aRow, "t_data_tier.name","STRING", procPath->dataTier);
	util.setValue(aRow, "t_processed_dataset.is_open", "CHARACTER", isOpen);
	util.setValue(aRow, "t_processed_dataset.name", "STRING", processedDataset->processedDatasetName);
	util.setValue(aRow, "t_primary_dataset.name", "STRING", processedDataset->primaryDatasetName);
	util.setIntValue(aRow, "t_processed_dataset.id", processedDataset->id );

	ProcessingPathManager procPathMgr;
	vector<Processingpathmultirow*> tmpVect;
	tmpVect.push_back(aRow);
	processedDatasetId = procPathMgr.write(tmpVect, &table);
	return 1;

}



int DBSApi::createFileBlock(std::string datasetPathName, DBS__Block* block, int& fileBlockId){
	int processedDatasetID = this->getProcessedDatasetID(datasetPathName);
	BlockviewMultiTable table;
	Blockviewmultirow* aRow = new Blockviewmultirow();
	util.setIntValue(aRow, "t_block.bytes", block->numberOfBytes);
	util.setIntValue(aRow, "t_block.files", block->numberOfFiles);
	util.setIntValue(aRow, "t_block.processed_dataset", &processedDatasetID);
	util.setValue(aRow, "t_block_status.name", "STRING", block->blockStatusName);

	vector<Blockviewmultirow*> vect;
	vect.push_back(aRow);
        BlockViewManager manager;
	fileBlockId = manager.write(vect, &table);
	return 1;

	

}

int DBSApi::insertEventCollections( std::vector<DBS__EventCollection*> eventCollectionList, int& result) {
	for(int i = 0; i != eventCollectionList.size() ; ++i) {
		DBS__EventCollection* aEventCollection = eventCollectionList.at(i);
		recInsertEventCollection(aEventCollection);
	}
	result = 1;

}

int DBSApi::recInsertEventCollection(DBS__EventCollection* eventCollection) {
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
		processedDatasetID = this->getProcessedDatasetID(eventCollection->datasetPathName);
		procDSIdMap.insert(MapEntry(eventCollection->datasetPathName, processedDatasetID ));
	}
	int parentEventCollectionID = 0;
	if(eventCollection->parent != NULL) {
		parentEventCollectionID = this->recInsertEventCollection(eventCollection->parent);
	}
	if(eventCollection->collectionId != NULL) {
		return *eventCollection->collectionId;
	}
	int eventCollectionID;
	if (eventCollection->parent != NULL) {
		EvcollviewMultiTable table;
		Evcollviewmultirow* aRow = new Evcollviewmultirow();
		this->setECValues(aRow, eventCollection,processedDatasetID);
		
		util.setValue(aRow, "t_parentage_type.name", "STRING", eventCollection->parentageType);
		if(parentEventCollectionID != 0) {
			util.setIntValue(aRow, "t_evcoll_parentage.parent",&parentEventCollectionID);
		}
		vector<Evcollviewmultirow*> vect;
		vect.push_back(aRow);
	        EvCollViewManager manager;
		eventCollectionID = manager.write(vect, &table);
	} else {
		EvcollviewnoparentMultiTable table;
		Evcollviewnoparentmultirow* aRow = new Evcollviewnoparentmultirow();
		this->setECValues(aRow, eventCollection,processedDatasetID);
		vector<Evcollviewnoparentmultirow*> vect;
		vect.push_back(aRow);
	        EvCollViewNoParentManager manager;
		eventCollectionID = manager.write(vect, &table);

	}
	insertFiles(eventCollectionID, eventCollection->fileList);
	return eventCollectionID;


}

void DBSApi::setECValues(RowInterface* aRow, DBS__EventCollection* eventCollection, int processedDatasetID) {
	util.setIntValue(aRow, "t_event_collection.collection_index", eventCollection->collectionIndex);
	util.setIntValue(aRow, "t_info_evcoll.events", eventCollection->numberOfEvents);
	util.setValue(aRow, "t_info_evcoll.name", "STRING", eventCollection->collectionName);
	util.setIntValue(aRow, "t_event_collection.processed_dataset", &processedDatasetID);
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
	util.setValue(&aRow, "t_processed_dataset.name", "STRING", processedDSName);
	//util.setValue(&aRow, "t_event_collection.id", "INTEGER", "271478");
        CrabEvCollViewManager manager;
	manager.read(&aRow, &table);
	
	int noOfRows = table.getNoOfRows();
	cout << "Number of Rows from DB is "<<noOfRows<<endl;
	vector<Crabevcollviewmultirow*> rows = table.getRows();
	return 1;
	for (int i = 0 ; i != noOfRows; ++i) {
		Crabevcollviewmultirow* tmpRow = (Crabevcollviewmultirow*)rows.at(i);
		string value;
		util.setSchema(table.getSchema());
		if((value = util.getStrValue(tmpRow, "t_block.id")) != "" ) {
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

			if((value = util.getStrValue(tmpRow, "t_info_evcoll.name")) != "" ) {
				aEventCollection->collectionName = value;
			}			
			if((value = util.getStrValue(tmpRow, "t_info_evcoll.events")) != "" ) {
				aEventCollection->numberOfEvents = this->getInt(util.atoi(value));
			}
			if((value = util.getStrValue(tmpRow, "t_event_collection.id")) != "" ) {
				aEventCollection->collectionId = this->getInt(util.atoi(value));
			}

			//if(listFiles) {
			//	//vector<DBS__File*> fileList;
			//	this->listFiles(*(aEventCollection->collectionId), *(aBlock->blockId), aEventCollection->fileList);
			//	cout<<"size of fileList "<<aEventCollection->fileList.size()<<endl;
			//}
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
	util.setValue(&aRow, "t_processed_dataset.name", "STRING", processedDSName);
	//util.setValue(&aRow, "t_event_collection.id", "INTEGER", "272232");

        CrabEvCollFileViewManager manager;
	manager.read(&aRow, &table);
	int noOfRows = table.getNoOfRows();
	cout << "Number of Rows from DB is "<<noOfRows<<endl;
	vector<Crabevcollfileviewmultirow*> rows = table.getRows();
	for (int i = 0 ; i != noOfRows; ++i) {
		Crabevcollfileviewmultirow* tmpRow = (Crabevcollfileviewmultirow*)rows.at(i);
		string value;
		util.setSchema(table.getSchema());
		if((value = util.getStrValue(tmpRow, "t_block.id")) != "" ) {
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
			if((value = util.getStrValue(tmpRow, "t_event_collection.id")) != "" ) {
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
					if((value = util.getStrValue(tmpRow, "t_info_evcoll.name")) != "" ) {
						aEventCollection->collectionName = value;
					}			
					if((value = util.getStrValue(tmpRow, "t_info_evcoll.events")) != "" ) {
						aEventCollection->numberOfEvents = this->getInt(util.atoi(value));
					}
				}
				this->addFile(tmpRow, aEventCollection->fileList);
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
	PDBlockViewManager pdbMgr;
	pdbMgr.read(&aRow, &table);

	int noOfRows = table.getNoOfRows();
	cout << "Number of Rows from DB is "<<noOfRows<<endl;
	vector<Pdblockviewmultirow*> rows = table.getRows();
	util.setSchema(table.getSchema());
	for (int i = 0 ; i != noOfRows; ++i) {
		Pdblockviewmultirow* tmpRow = (Pdblockviewmultirow*)rows.at(i);

		DBS__Block* newBlock = this->getBlock();
		string value;
		if((value = util.getStrValue(tmpRow, "t_block.id")) != "" ) {
			newBlock->blockId = this->getInt(util.atoi(value));
			newBlock->blockName = datasetPathName.substr(1, (datasetPathName.length() - 1) ) + "/#/" + value;
		}
		if((value = util.getStrValue(tmpRow, "t_block_status.name")) != "" ) {
			newBlock->blockStatusName = value;
		}
		if((value = util.getStrValue(tmpRow, "t_block.files")) != "" ) {
			newBlock->numberOfFiles = this->getInt(util.atoi(value));
		}
		if((value = util.getStrValue(tmpRow, "t_block.bytes")) != "" ) {
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
/*
int DBSApi::getEventCollectionID(DBS__EventCollection* eventCollection) {
	if(eventCollection->collectionId != NULL) {
		return eventCollection->collectionId;
	}

	EvcollviewMultiTable table;
	Evcollviewmultirow aRow;
	util.setIntValue(aRow, "t_event_collection.collection_index", eventCollection->collection_index);
	util.setIntValue(aRow, "t_info_evcoll.events", eventCollection->numberOfEvents);
	util.setValue(&aRow, "t_info_evcoll.name", "STRING", eventCollection->collectionName);
	util.setValue(&aRow, "t_parentage_type.name", "STRING", eventCollection->parentageType);

        EvCollViewManager manager;
	manager.read(&aRow, &table);
	int noOfRows = table.getNoOfRows();
	cout << "Number of Rows from DB is "<<noOfRows<<endl;
	if(noOfRows > 1) {
		throw BizLayerException("More than one EventCollection found.");
	}
	if(noOfRows == 0) {
		return -1;
	}
	Evcollviewmultirow* tmpRow = (Evcollviewmultirow*)rows.at(0);
	string value;
	if((value = util.getStrValue(tmpRow, "t_event_collection.id")) != "" ) {
		return(util.atoi(value));
	}
	return -1;
}
*/
int DBSApi::insertFiles(int eventCollectionID, vector<DBS__File*> fileList) {
	FileviewMultiTable table;
	vector<Fileviewmultirow*> vect;
	for(int i = 0; i != fileList.size(); ++i) {
		Fileviewmultirow* aRow = new Fileviewmultirow();
		DBS__File* aFile = fileList.at(i);
		util.setValue(aRow, "t_file.guid", "STRING", aFile->guid);
		util.setValue(aRow, "t_file.logical_name", "STRING", aFile->logicalFileName);
		util.setValue(aRow, "t_file_type.name", "STRING", aFile->fileType);
		util.setIntValue(aRow, "t_file.inblock", aFile->fileBlockId);
		util.setIntValue(aRow, "t_file.filesize", aFile->fileSize);
		util.setIntValue(aRow, "t_evcoll_file.evcoll", &eventCollectionID);
		vect.push_back(aRow);
	}
        FileViewManager manager;
	return manager.write(vect, &table);

}

int DBSApi::listFiles(int eventCollectionID, int blockID, vector<DBS__File*>& fileList) {
	FileviewMultiTable aTable;
	Fileviewmultirow aRow;
	util.setIntValue(&aRow, "t_evcoll_file.evcoll", &eventCollectionID);
	util.setIntValue(&aRow, "t_file.inblock", &blockID);
        FileViewManager manager;
	manager.read(&aRow, &aTable);
	int noOfRows = aTable.getNoOfRows();
	cout<<"no of rows "<<noOfRows<<endl;
	vector<Fileviewmultirow*> rows = aTable.getRows();
	util.setSchema(aTable.getSchema());
	for( int i = 0 ; i != noOfRows; ++i) {
		Fileviewmultirow* tmpRow = (Fileviewmultirow*)rows.at(i);
		this->addFile(tmpRow,fileList);
		/*DBS__File* newFile = this->getFile();
		//cout<<"created a new file"<<endl;
		string value;

		if((value = util.getStrValue(tmpRow, "t_file.guid")) != "" ) {
			newFile->guid = value;
		}
		if((value = util.getStrValue(tmpRow, "t_file.logical_name")) != "" ) {
			newFile->logicalFileName = value;
		}
		if((value = util.getStrValue(tmpRow, "t_file_type.name")) != "" ) {
			newFile->fileType = value;
		}
		if((value = util.getStrValue(tmpRow, "t_file.inblock")) != "" ) {
			newFile->fileBlockId = this->getInt(util.atoi(value));
		}
		if((value = util.getStrValue(tmpRow, "t_file.filesize")) != "" ) {
			newFile->fileSize = this->getInt(util.atoi(value));
		}
		fileList.push_back(newFile);*/
	}
	return 1;
}


int DBSApi::addFile(RowInterface* aRow, vector<DBS__File*>& fileList) {
		string value;
		DBS__File* newFile;
		if((value = util.getStrValue(aRow, "t_file.logical_name")) != "" ) {
			newFile = this->getFile();
			newFile->logicalFileName = value;
			cout<<"Creating a new file"<<endl;
		} else {
			cout<<"t_file.logical_name not found "<<endl;
			return 0;
		}
		if((value = util.getStrValue(aRow, "t_file.guid")) != "" ) {
			newFile->guid = value;
		}
		
		if((value = util.getStrValue(aRow, "t_file_type.name")) != "" ) {
			newFile->fileType = value;
		}
		if((value = util.getStrValue(aRow, "t_file.inblock")) != "" ) {
			newFile->fileBlockId = this->getInt(util.atoi(value));
		}
		if((value = util.getStrValue(aRow, "t_file.filesize")) != "" ) {
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
	DatasetPathManager dsPathMgr;
	dsPathMgr.read(&aRow, &aTable);
	int noOfRows = aTable.getNoOfRows();
	cout<<"no of rows are "<<noOfRows<<endl;


	vector<Datasetpathmultirow*> rows = aTable.getRows();
	util.setSchema(aTable.getSchema());
	for( int i = 0 ; i != noOfRows; ++i) {
		
		Datasetpathmultirow* tmpRow = (Datasetpathmultirow*)rows.at(i);
		string fullPath = "/" +
			util.getStrValue(tmpRow, "t_primary_dataset.name") +
			"/" +
			util.getStrValue(tmpRow, "t_data_tier.name") +
			"/" +
			util.getStrValue(tmpRow, "t_processed_dataset.name");
		datasetList.push_back(fullPath);
	}

}
