#include "soapH.h"
#include "Util.hpp"
#include "ObjectLayerTables.hpp"
#include "BizLayerException.hpp"
#include "DBSApi.hpp"
#include <iostream>
using namespace std;


int DBS__createPrimaryDataset(struct soap* soap, DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId) {
	cout<<"Name is "<<primaryDataset->name<<endl;
	//return soap_receiver_fault(soap, "Resource temporarily unavailable hahahhah", "lllllalalallal");
	if (primaryDataset->name.length() < 1) {
		return soap_receiver_fault(soap, "Primary Dataset name is NULL",NULL);
	}
	PrimarydatasetMultiTable* table = new PrimarydatasetMultiTable();
	Primarydatasetmultirow* aRow = new Primarydatasetmultirow();
	Util util;
	util.setValue(aRow, "t_primary_dataset.name", "STRING", primaryDataset->name);
	DBSApi api;
	try {
		api.createPrimaryDataset(aRow, table);
	} catch (string e) {
		cout<<"INSIDE (char e"<<endl;
		delete table;
		//delete aRow;
		return soap_receiver_fault(soap, e.c_str() , NULL);
	}
	delete table;
	//delete aRow;

	
	/*if(primaryDataset->testlist.size() < 1) {
		cout<<"PARENT IS NULL"<<endl;
	} else {
			cout<<"SIZE is "<<primaryDataset->testlist.size()<<endl;
			cout<<"val is "<<((DBS__testing*)(primaryDataset->testlist.at(0)))->name<<endl;
	}
	if(primaryDataset->parent == NULL) {
		cout<<"PARENT IS NULL"<<endl;
	} else {
		cout<<"Parent Name is "<<primaryDataset->parent->name<<endl;
	}*/
	primaryDatasetId = 10;
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

int DBS__getDatasetContents(struct soap* soap, std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList) {
	cout<<"datasetPathName "<<datasetPathName<<endl;
	DBS__Block* block = new DBS__Block();
	block->blockStatusName = "some kabara";
	*block->numberOfFiles = 10;
	blockList.push_back(block);
	return SOAP_OK;
}

int DBS__getDatasetFileBlocks(struct soap* soap, std::string datasetPathName, std::vector<DBS__Block*>& blockList) {
	return SOAP_OK;
}


