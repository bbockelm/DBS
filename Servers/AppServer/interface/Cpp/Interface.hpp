#import "stlvector.h"

class DBS__PrimaryDataset {
	public:
		int* primaryDatasetId 0;
		std::string name 1;

};

class DBS__Application {
	public:
		int* applicationId 0;
		std::string executable 1;
		std::string version 1;
		std::string family 1;
		std::string parameterSet 1;
};
class DBS__ProcessingPath {
	public:
		int* pathId 0;
		//DBS__ProcessingPath* parent 0;
		std::string parent 0;
		DBS__Application* application 1;
		std::string dataTier 1;
		
};

class DBS__ProcessedDataset {
	public:
		int* id 0;
		std::string processedDatasetName 1;
		std::string primaryDatasetName 1;
		DBS__ProcessingPath* processingPath 1;
		//char isDatasetOpen 1;
		bool isDatasetOpen 1;
};

class DBS__File {
	public:
		int* id 0;
		std::string guid 0;
		std::string logicalFileName 1;
		std::string checksum 0;
		//long* filesize 0;
		int* fileSize 0;
		std::string fileStatus 1;
		std::string fileType 1;
		int* fileBlockId 1;
};



class DBS__EventCollection {
	public:
		int* collectionId 0;
		int* collectionIndex 1;
		int* numberOfEvents 0;
		std::string status 0;
		std::string collectionName 1;
		std::string datasetPathName 1;
		DBS__EventCollection* parent 0;
		std::string parentageType 0;
		std::vector<DBS__File*> fileList 0;
};

class DBS__Block {
	public:
		int* blockId 0;
		std::string blockStatusName 1;
		std::string blockName 0;
		int* numberOfFiles 1;
		//long* numberOfBytes 1;
		int* numberOfBytes 1;
		std::vector<DBS__EventCollection*> eventCollectionList 0;
		
};


int DBS__createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, int& primaryDatasetId);
int DBS__createProcessedDataset(DBS__ProcessedDataset* processedDataset, int& processedDatasetId);
int DBS__createFileBlock(std::string datasetPathName, DBS__Block* block, int& fileBlockId);
int DBS__insertEventCollections(std::vector<DBS__EventCollection*> eventCollectionList, int& result);
int DBS__mergeEventCollections(std::vector<DBS__EventCollection*> inputEventCollectionList, DBS__EventCollection* outputEventCollection, int& result);
int DBS__getDatasetContents(std::string datasetPathName, bool listFiles, std::vector<DBS__Block*>& blockList);
int DBS__getDatasetFileBlocks(std::string datasetPathName, std::vector<DBS__Block*>& blockList);
int DBS__listDataset(std::string datasetPathName, std::vector<std::string>& datasetList);
