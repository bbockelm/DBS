#include "Interface.hpp"
#include <iostream>
#include "Log.hpp"
#include "ManagerImpls.hpp"
#include "BizLayerException.hpp"
#include <exception>
#include "DBSApi.hpp"

using namespace std;


int DBS__createPrimaryDataset(struct soap* soap, DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId) {
	try {
                DBSApi dbsapi(soap); 
		int ret = dbsapi.createPrimaryDataset(primaryDataset, primaryDatasetId);
                 
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
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

  DBSApi dbsapi(soap);
  int ret = dbsapi.getDatasetContents(datasetPathName, listFiles, blockList);
  //if (!ret)  return soap_receiver_fault(soap, e.c_str() , NULL);
  return SOAP_OK;
   
}

int DBS__getDatasetFileBlocks(struct soap* soap, std::string datasetPathName, std::vector<DBS__Block*>& blockList) {
	return SOAP_OK;
}


