#import "stlvector.h"

class DBS__PrimaryDataset {
         public:
                   std::string   Annotation 0;
                   std::string   Name 1;
                   std::string   StartDate 0;
                   std::string   EndDate 0;
                   DBS__Type*   Type 0;
                   DBS__PrimaryDatasetDescription* Description 0;
};

class DBS__Algorithm {
         public:
                  std::string ExecutableName 1;
                  std::string ApplicationVersion 1;
                  std::string ApplicationFamily 1;
                  DBS__QueryableParameterSet* ParameterSetID 1;
};


class DBS__QueryableParameterSet {
         public:
                   std::string   Hash 1;
                   std::string   Name 0;
                   std::string   Version 0;
                   std::string   Type 0;
                   std::string   Annotation 0;
                   std::string   Content 0;
};

class DBS__ProcessedDataset {
         public:
                   std::string   Name 1;
                   std::string  PhysicsGroup 0;
                   std::string Status 0;
                   std::string OpenForWriting 0;
                   DBS__PrimaryDataset* PrimaryDataset 1;
                   std::vector<DBS__Algorithm*> AlgoList 0;
                   std::vector<DBS__DataTier*> tierList 0;
};

class DBS__FileBlock {
         public:
                   std::string   Name 0;
                   std::string Status 0;
                   int   BlockSize 0;
                   int   NumberOfFiles 0;
                   bool   OpenForWriting 0;
                   DBS__ProcessedDataset* Dataset 0;
                   std::vector<DBS__File*> fileList 0;
};

class DBS__File {
         public:
                   std::string   Checksum 0;
                   std::string   LogicalFileName 0;
                   std::string   QueryableMetadata 0;
                   int   NumberOfEvents 0;
                   int   FileSize 0;
                   int   Status 0;
                   int   FileType 0;
                   int   ValidationStatus 0;
                   DBS__ProcessedDataset* Dataset 0;
                   DBS__FileBlock* Block 0;
                   std::vector<DBS__LumiSection*> lumiList 0;
                   std::vector<DBS__DataTier*> tierList 0;
};


class DBS__DataTier {
         public:
                   std::string   Name 0;
};

class DBS__Run {
         public:
                   int   RunNumber 1;
                   int   NumberOfEvents 0;
                   int   NumberOfLumiSections 0;
                   int   TotalLuminosity 0;
                   int   StoreNumber 0;
                   std::string   StartOfRun 0;
                   std::string   EndOfRun 0;
                   std::vector<DBS__ProcessedDataset*> Dataset 1;
};

class DBS__LumiSection {
         public:
                   int   LumiSectionNumber 1;
                   #int   RunNumber 1;
                   int   StartEventNumber 0;
                   int   EndEventNumber 0;
                   std::string   LumiStartTime 0;
                   std::string   LumiEndTime 0;
                   DBS__Run* RunNumber 1;
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
