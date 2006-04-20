#include "Interface.hpp"
#include <iostream>
#include "BizLayerException.hpp"
#include <exception>
#include "SoapDBSApi.hpp"

using namespace std;


int DBS__createPrimaryDataset(struct soap* soap, DBS__PrimaryDataset* primaryDataset,int& primaryDatasetId) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.createPrimaryDataset(primaryDataset,primaryDatasetId);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__createProcessedDataset(struct soap* soap, DBS__ProcessedDataset* processedDataset,int& processedDatasetId) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.createProcessedDataset(processedDataset,processedDatasetId);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__createFileBlock(struct soap* soap,std::string datasetPathName, DBS__Block* block,int& fileBlockId) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.createFileBlock(datasetPathName,block,fileBlockId);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__insertEventCollections(struct soap* soap, std::vector<DBS__EventCollection*> eventCollectionList,int& result) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.insertEventCollections(eventCollectionList,result);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__mergeEventCollections(struct soap* soap, std::vector<DBS__EventCollection*> inputEventCollectionList, DBS__EventCollection* outputEventCollection,int& result) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.mergeEventCollections(inputEventCollectionList,outputEventCollection,result);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__getDatasetContents(struct soap* soap,std::string datasetPathName,bool listFiles,std::vector<DBS__Block*>& blockList) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.getDatasetContents(datasetPathName,listFiles,blockList);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__getDatasetFileBlocks(struct soap* soap,std::string datasetPathName,std::vector<DBS__Block*>& blockList) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.getDatasetFileBlocks(datasetPathName,blockList);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}

int DBS__listDataset(struct soap* soap,std::string datasetPathName,std::vector<std::string>& datasetList) {

	try{
		SoapDBSApi dbsapi(soap);
		dbsapi.listDataset(datasetPathName,datasetList);
	} catch (BizLayerException e) {
		return soap_receiver_fault(soap, e.report().c_str() , NULL);
	}
	return SOAP_OK;
}


