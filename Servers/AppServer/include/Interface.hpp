//#include "stlvector.h"
#include "soapH.h"
#include <vector>
#include <string>

/*
class DBS__PrimaryDataset {
	public:
		int* primaryDatasetId;
		std::string name ;

};

class DBS__Application {
	public:
		int* applicationId ;
		std::string executable ;
		std::string version ;
		std::string family ;
		std::string parameterSet ;
};
class DBS__ProcessingPath {
	public:
		int* pathId ;
		//DBS__ProcessingPath* parent ;
		std::string parent ;
		DBS__Application* application ;
		std::string dataTier ;
		
};

class DBS__ProcessedDataset {
	public:
		int* id ;
		std::string processedDatasetName ;
		std::string primaryDatasetName ;
		DBS__ProcessingPath* processingPath ;
		char isDatasetOpen ;
		
};

class DBS__File {
	public:
		int* id ;
		std::string guid ;
		std::string logical_name ;
		std::string checksum ;
		long* filesize ;
		std::string status ;
		std::string type ;
		int* inblock ;
};



class DBS__EventCollection {
	public:
		int* collectionId ;
		int* collection_index ;
		int* numberOfEvents ;
		std::string collectionName ;
		DBS__EventCollection* parent ;
		std::string parentageType ;
		std::vector<DBS__File*> fileList ;
};

class DBS__Block {
	public:
		int* blockId ;
		std::string blockStatusName ;
		int* numberOfFiles ;
		long* numberOfBytes ;
		std::vector<DBS__EventCollection*> eventCollectionList ;
};
*/

int createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId);
int createProcessedDataset(DBS__ProcessedDataset* processedDataset, int& processedDatasetId);
int createFileBlock(std::string datasetPathName, DBS__Block* block, int& fileBlockId);
int insertEventCollections(std::vector<DBS__EventCollection*> eventCollectionList, int& result);
int getDatasetContents(std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList);
int getDatasetFileBlocks(std::string datasetPathName, std::vector<DBS__Block*>& blockList);
int listDataset(std::string datasetPathName, std::vector<std::string>& datasetList);

