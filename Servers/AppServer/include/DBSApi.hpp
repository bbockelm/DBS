#ifndef _DBSAPI_hpp_
#define _DBSAPI_hpp_

#include "ObjectLayerTables.hpp"  
#include "Util.hpp"
#include "Interface.hpp"
#include "TableInterface.hpp"
//#include <log4cxx/logger.h>
#include <iostream>
 
typedef std::map<std::string, int> Map;
typedef std::map<std::string, int>::value_type MapEntry;
typedef std::map<std::string, int>::iterator Map_iter;

 
class DBSApi {

public:
	//DBSApi(struct soap* soap);
	DBSApi();
	~DBSApi();

        int createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId);
        int createProcessedDataset(DBS__ProcessedDataset* processedDataset, int& processedDatasetId);
        int createFileBlock(std::string datasetPathName, DBS__Block* block, int& fileBlockId);
        int insertEventCollections(std::vector<DBS__EventCollection*> eventCollectionList, int& result);
	int mergeEventCollections(std::vector<DBS__EventCollection*> inputEventCollectionList,DBS__EventCollection* outputEventCollection,int& result);
        int getDatasetContents(std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList);
        int getDatasetFileBlocks(std::string datasetPathName, std::vector<DBS__Block*>& blockList);
	int listDataset(std::string datasetPathName, std::vector<std::string>& datasetList);
	virtual DBS__Block* getBlock();
	virtual DBS__File* getFile();
	virtual DBS__EventCollection* getEventCollection();
	virtual int* getInt(int value);
 
private:
	Util util;
	DBManagement* dbManager;
	int getEventCollectionID(DBS__EventCollection* eventCollection);
	int insertFiles(int eventCollectionID, std::vector<DBS__File*> fileList);
	int getProcessedDatasetID(std::string datasetPathName);
	int getProcessingPathID(std::string datasetPathName);
	int listFiles(int eventCollectionID, int blockID, std::vector<DBS__File*>& fileList);
	std::vector<std::string> getNameElements(std::string datasetPathName);
	int recInsertEventCollection(DBS__EventCollection* eventCollection);
	void setECValues(RowInterface* aRow, DBS__EventCollection* eventCollection, int processedDatasetID);
	//int addFile(RowInterface* aRow, vector<DBS__File*>& fileList);
	int addFile(TableInterface* table, vector<DBS__File*>& fileList);
	int getDatasetContents(std::string datasetPathName, std::vector<DBS__Block*>& blockList);
	int insertEVParents(std::vector<int> evParentList, int eventCollectionID);
	int updateEVChilds(std::vector<int> evChildList, int eventCollectionID);
	int updateStatus(std::vector<int> evIDList, int statusID);
	int createStatus(std::string status);
	int getEVChildOrParents(int eventCollectionID, bool type,  std::vector<int>& evChildList);
	Map procDSIdMap;
};

#endif
