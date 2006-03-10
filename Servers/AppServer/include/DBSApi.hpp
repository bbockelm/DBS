#ifndef _DBSAPI_hpp_
#define _DBSAPI_hpp_

#include "ObjectLayerTables.hpp"  
#include "Util.hpp"
#include "Interface.hpp"
//#include <log4cxx/logger.h>
#include <iostream>
  
class DBSApi {

public:
	DBSApi(struct soap* soap);
	~DBSApi();

        int createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId);
        int createProcessedDataset(DBS__ProcessedDataset* processedDataset, int& processedDatasetId);
        int createFileBlock(std::string datasetPathName, DBS__Block* block, int& fileBlockId);
        int insertEventCollections(std::string datasetPathName, std::vector<DBS__EventCollection*> eventCollectionList, int& result);
        int getDatasetContents(std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList);
        int getDatasetFileBlocks(std::string datasetPathName, std::vector<DBS__Block*>& blockList);

 
private:
	Util util;
        struct soap* soap;
};

#endif
