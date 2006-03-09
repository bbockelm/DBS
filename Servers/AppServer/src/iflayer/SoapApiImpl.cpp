#include "Interface.hpp"
#include <iostream>
#include "Log.hpp"
#include "ManagerImpls.hpp"
#include "BizLayerException.hpp"
#include <exception>


using namespace std;


int DBS__createPrimaryDataset(struct soap* soap, DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId) {
	try {
		createPrimaryDataset(primaryDataset, primaryDatasetId);
	} catch (string e) {
		return soap_receiver_fault(soap, e.c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__createProcessedDataset(struct soap* soap, DBS__ProcessedDataset* processedDataset, int& processedDatasetId){
	return SOAP_OK;
}

int DBS__createFileBlock(struct soap* soap, std::string datasetPathName, DBS__Block* block, int& fileBlockId){
	return SOAP_OK;
}

int DBS__insertEventCollections(struct soap* soap, std::string datasetPathName, std::vector<DBS__EventCollection*> eventCollectionList, int& result) {
	return SOAP_OK;
}



int DBS__getDatasetContents(struct soap* soap, 
                            std::string datasetPathName, 
                            bool listFiles, std::vector<DBS__Block*>& blockList) {
	int ret = 0;
	
	if (datasetPathName.length() < 1) {
		throw (string)"datasetPathName is NULL";
	}
        
	Util util;
	vector<string> tokens;
	util.Tokenize(datasetPathName, tokens, (string)"/");
	if (tokens.size() != 3) {
		throw (string)"datasetPathName is invalid. Exampple is /PrimaryDataset/DataTier/ProcessedDataset";
	}

	string primaryDSName = tokens.at(0);
	string dataTier = tokens.at(1);
	string processedDSName = tokens.at(2);


	try {
		CrabevcollviewMultiTable table;
		Crabevcollviewmultirow aRow;
		util.setValue(&aRow, "t_primary_dataset.name", "STRING", primaryDSName);
		util.setValue(&aRow, "t_data_tier.name", "STRING", dataTier);
		util.setValue(&aRow, "t_processed_dataset.name", "STRING", processedDSName);
		//util.setValue(&aRow, "t_event_collection.id", "INTEGER", "273210");
	
	        CrabEvCollViewManager evMgr;
		ret = evMgr.read(&aRow, &table);
		int noOfRows = table.getNoOfRows();
		cout << "Number of Rows from DB is "<<noOfRows<<endl;
		vector<Crabevcollviewmultirow*> rows = table.getRows();
		util.setSchema(table.getSchema());
		for (int i = 0 ; i != noOfRows; ++i) {
			Crabevcollviewmultirow* tmpRow = (Crabevcollviewmultirow*)rows.at(i);

			string value;
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
					aBlock = soap_new_DBS__Block(soap, -1);
					aBlock->blockId = (int*)soap_malloc(soap, sizeof(int)); 
                                        *aBlock->blockId = util.atoi(value);
				}


				DBS__EventCollection * aEventCollection = soap_new_DBS__EventCollection(soap, -1);
				if((value = util.getStrValue(tmpRow, "t_info_evcoll.name")) != "" ) {
					aEventCollection->collectionName = value;
				}			
				if((value = util.getStrValue(tmpRow, "t_info_evcoll.events")) != "" ) {
					aEventCollection->numberOfEvents =  (int*)soap_malloc(soap, sizeof(int));
                                        *aEventCollection->numberOfEvents = util.atoi(value);
				}
				if((value = util.getStrValue(tmpRow, "t_event_collection.id")) != "" ) {
					aEventCollection->collectionId = (int*)soap_malloc(soap, sizeof(int));
                                        *aEventCollection->collectionId = util.atoi(value);
				}
				aBlock->eventCollectionList.push_back(aEventCollection);

				if (!found) {
                                        cout << "\n\n\nAdding a new Block" << endl;
					blockList.push_back(aBlock);
				}

			}
		}
		
		ret = blockList.size();
	} catch (BizLayerException &e)  {
		throw e.report();
	}
	return SOAP_OK;
}





/*
int DBS__getDatasetContents(struct soap* soap, std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList) {
        cout<<"datasetPathName "<<datasetPathName<<endl;
        //DBS__Block* block = new DBS__Block();
        DBS__Block* block = soap_new_DBS__Block(soap, -1);;
        block->blockStatusName = "some kabara";
	block->blockId = (int*)soap_malloc(soap, sizeof(int)); 
	*block->blockId = 8776;

	//block->numberOfFiles = new int(10);
        blockList.push_back(block);
        return SOAP_OK;

*/

/*	DBS__Block* aBlock = soap_new_DBS__Block(soap, -1);
	cout<<"DONE allocating"<<endl;
	//DBS__Block* aBlock = new DBS__Block();
	//aBlock->blockId = new int(45);
	DBS__EventCollection * aEventCollection = new DBS__EventCollection();
	aEventCollection->collectionId = new int(56);
	aBlock->eventCollectionList.push_back(aEventCollection);
	blockList.push_back(aBlock);
	int ret = 0;

	try {
		getDatasetContents(datasetPathName, listFiles, blockList);
	} catch (string e) {
		return soap_receiver_fault(soap, e.c_str() , NULL);
	}
	return SOAP_OK;*/
//} */

int DBS__getDatasetFileBlocks(struct soap* soap, std::string datasetPathName, std::vector<DBS__Block*>& blockList) {
	return SOAP_OK;
}


