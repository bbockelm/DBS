#include "DBSApi.hpp"
#include "ManagerImpls.hpp"
#include "BizLayerException.hpp"
#include <exception>


DBSApi::DBSApi(struct soap * _soap = NULL ) {

      this->soap = _soap;

}


DBSApi::~DBSApi() {

}

int DBSApi::createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId){
	cout<<"Name is "<<primaryDataset->name<<endl;
	if (primaryDataset->name.length() < 1) {
		throw (string)"Primary Dataset name is NULL";
	}
	int ret = 0;
	try {
		PrimarydatasetMultiTable table;
		Primarydatasetmultirow* aRow = new Primarydatasetmultirow();
		Util util;
		util.setValue(aRow, "t_primary_dataset.name", "STRING", primaryDataset->name);
		vector<Primarydatasetmultirow*> pdsVect;
		pdsVect.push_back(aRow);
	        PrimaryDatasetManager pdsMgr;
		ret = pdsMgr.write(pdsVect, &table);

	} catch (BizLayerException &e)  {
		throw e.report();
	}
	return ret;
}

int DBSApi::createProcessedDataset(DBS__ProcessedDataset* processedDataset, int& processedDatasetId){}
int DBSApi::createFileBlock(std::string datasetPathName, DBS__Block* block, int& fileBlockId){}
int DBSApi::insertEventCollections(std::string datasetPathName, std::vector<DBS__EventCollection*> eventCollectionList, int& result){}


int DBSApi::getDatasetContents(std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList) {

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
                                        if (soap) {
						aBlock = soap_new_DBS__Block(soap, -1);
						aBlock->blockId = (int*)soap_malloc(soap, sizeof(int));
					}
					else {
						aBlock = new DBS__Block();
						aBlock->blockId = (int*) new int();
					}
                                        *aBlock->blockId = util.atoi(value);
				}
                                DBS__EventCollection * aEventCollection;
				if (soap) aEventCollection = soap_new_DBS__EventCollection(soap, -1);
				else aEventCollection = new DBS__EventCollection();
 
				if((value = util.getStrValue(tmpRow, "t_info_evcoll.name")) != "" ) {
					aEventCollection->collectionName = value;
				}			
				if((value = util.getStrValue(tmpRow, "t_info_evcoll.events")) != "" ) {
					if (soap) aEventCollection->numberOfEvents =  (int*)soap_malloc(soap, sizeof(int));
					else aEventCollection->numberOfEvents =  (int*) new int();
                                        *aEventCollection->numberOfEvents = util.atoi(value);
				}
				if((value = util.getStrValue(tmpRow, "t_event_collection.id")) != "" ) {
					if (soap) aEventCollection->collectionId = (int*)soap_malloc(soap, sizeof(int));
					else aEventCollection->collectionId = (int*) new int();
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
	return 1;
}

int DBSApi::getDatasetFileBlocks(std::string datasetPathName, std::vector<DBS__Block*>& blockList){}
