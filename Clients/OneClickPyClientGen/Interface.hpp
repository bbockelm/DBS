#import "stlvector.h"

class DBS__PrimaryDataset {
	public:
		long* objectId 0;
		std::string datasetName 1;

};

class DBS__Application {
	public:
		long* objectId 0;
		std::string executable 1;
		std::string version 1;
		std::string family 1;
		//std::string parameterSet 1;
};

class DBS__ParameterSet {
	public:
		long* objectId 0;
		std::string hash 1;
		std::string content 1;
};

class DBS__ApplicationConfig {
	public:
		long* objectId 0;
		DBS__Application* application 1;
		DBS__ParameterSet* parameterSet 1;
};

class DBS__Processing {
	public:
		long* objectId 0;
		std::string parent 0;
		DBS__PrimaryDataset* primaryDataset 1;
		std::string processingName 1;
		DBS__ApplicationConfig* applicationConfig 1;
		bool isOpen 1;
};

class DBS__ProcessedDataset {
	public:
		long* objectId 0;
		DBS__PrimaryDataset* primaryDataset 1;
		DBS__Processing* processing 1;
		std::string datasetName 1;
		std::string dataTier 1;
		std::string datasetPathName 0;
		bool isDatasetOpen 1;
};
class DBS__EventCollection;
class DBS__Parent {
	public:
		DBS__EventCollection* parent 1;
		std::string type 1;
};
class DBS__File;
class DBS__FileBlock {
	public:
		long* objectId 0;
		std::string blockName 0;
		DBS__Processing* processing 0;
		std::string blockStatusName 1;
		int* numberOfBytes 1;
		int* numberOfFiles 1;
		std::vector<DBS__File*> fileList 0;
		std::vector<DBS__EventCollection*> eventCollectionList 0;
		
};

class DBS__File {
	public:
		long* objectId 0;
		std::string logicalFileName 1;
		std::string guid 0;
		std::string checkSum 0;
		std::string fileType 1;
		std::string fileStatus 1;
		int* fileSize 0;
		DBS__FileBlock* block 1;
};



class DBS__EventCollection {
	public:
		long* objectId 0;
		std::string collectionName 1;
		int* numberOfEvents 0;
		std::string collectionStatus 0;
		std::string datasetPathName 1;
		std::vector<DBS__Parent*> parentageList 0;
		std::vector<DBS__File*> fileList 0;
};


int DBS__createPrimaryDataset(DBS__PrimaryDataset* primaryDataset, std::string& primaryDatasetResult);
int DBS__createProcessedDataset(DBS__ProcessedDataset* processedDataset, std::string& processedDatasetResult);
int DBS__createProcessing(DBS__Processing* processing, std::string& processingResult);
int DBS__createFileBlock(DBS__ProcessedDataset* processedDataset, DBS__FileBlock* block, std::string& fileBlockResult);
int DBS__insertEventCollections(DBS__ProcessedDataset* processedDataset, std::vector<DBS__EventCollection*> eventCollectionList, int& result);
int DBS__insertFiles(DBS__Block* block, std::vector<DBS__File*> fileList, int& result);
int DBS__mergeEventCollections(std::vector<DBS__EventCollection*> inputEventCollectionList, DBS__EventCollection* outputEventCollection, int& result);
int DBS__getDatasetProvenance(std::string datasetPathName, std::vector<std::string> dataTierList);
int DBS__getDatasetFileBlocks(DBS__ProcessedDataset* processedDataset, std::string& blockList);
int DBS__closeFileBlock(DBS__FileBlock* block, std::string& fileBlockResult);
int DBS__listDataset(std::string datasetPathName, std::string& datasetList);
int DBS__listPrimaryDatasets(std::string primaryDatasetName, std::string& primaryDatasetResult);
int DBS__listProcessedDatasets(std::string processedDatasetName, std::string& processedDatasetResult);
int DBS__listProcessing(std::string processingName,std::string& processingResult);
