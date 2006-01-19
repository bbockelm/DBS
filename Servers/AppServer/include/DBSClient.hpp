#ifndef _DBSClient_hpp_
#define _DBSClient_hpp_

#include "Message.hpp"
#include "Configuration.hpp"
#include "ObjectLayerTables.hpp"  
#include "Util.hpp"
#include "common.hpp"
#include <log4cxx/logger.h>
#include <iostream>
  
class DBSClient {

public:
	DBSClient();
	~DBSClient();
  
	int createPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) throw (const char*);
	int readPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) throw (const char*);
	int createProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) throw (const char*);
	int readProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) throw (const char*);
	int createEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) throw (const char*);
	int readEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) throw (const char*);
	int insertFiles(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) throw (const char*);
	int readFiles(Fileviewmultirow* aRow, FileviewMultiTable* table) throw (const char*);
	int createBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) throw (const char*);
	int readBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) throw (const char*);
	int readDatasetProvenenceParent(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table) throw (const char*);
	int readDatasetProvenenceChild(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table) throw (const char*);
	int readCrabEC(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) throw (const char*);

private:
	int callServer(void);
	bool localServer;
	Util util;
	log4cxx::LoggerPtr logger;
};

#endif
