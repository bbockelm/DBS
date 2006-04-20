#include "soapH.h"
#include <vector>
#include <string>

int createPrimaryDataset(DBS__PrimaryDataset* primaryDataset,int& primaryDatasetId);
int createProcessedDataset(DBS__ProcessedDataset* processedDataset,int& processedDatasetId);
int createFileBlock(std::string datasetPathName,DBS__Block* block,int& fileBlockId);
int insertEventCollections(std::vector<DBS__EventCollection*> eventCollectionList,int& result);
int mergeEventCollections(std::vector<DBS__EventCollection*> inputEventCollectionList,DBS__EventCollection* outputEventCollection,int& result);
int getDatasetContents(std::string datasetPathName,bool listFiles,std::vector<DBS__Block*>& blockList);
int getDatasetFileBlocks(std::string datasetPathName,std::vector<DBS__Block*>& blockList);
int listDataset(std::string datasetPathName,std::vector<std::string>& datasetList);


