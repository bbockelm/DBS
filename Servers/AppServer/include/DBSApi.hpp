#ifndef _DBSApi_hpp_
#define _DBSApi_hpp_

#include "Message.hpp"
#include "Configuration.hpp"
#include "ObjectLayerTables.hpp"  
#include "Util.hpp"
#include "common.hpp"
#include <log4cxx/logger.h>
#include <iostream>
  
class DBSApi {

public:
	DBSApi();
	~DBSApi();
  
	//int createPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) throw (const char*);
	int createPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table);
	int readPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) ;
	int createProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) ;
	int readProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) ;
	int createEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) ;
	int readEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) ;
	int insertFiles(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) ;
	int readFiles(Fileviewmultirow* aRow, FileviewMultiTable* table) ;
	int createBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) ;
	int readBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) ;
	int readPdblock(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table) ;
	//int readDatasetProvenenceParent(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table) ;
	//int readDatasetProvenenceChild(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table) ;
	int readCrabEC(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) ;

private:
	int callServer(void);
	bool localServer;
	Util util;
	log4cxx::LoggerPtr logger;
};

#endif
