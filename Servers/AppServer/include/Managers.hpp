#ifndef _MANAGER_H_
#define _MANAGER_H_
///Class that creates a manager
///parent class of all managers
/// creates DBManager 


#include "DBManagement.hpp"
#include "TableInterface.hpp"
#include "ObjectLayerTables.hpp"
#include "TableInterface.hpp"
#include "Util.hpp"
#include <string>
#include "RowInterface.hpp"
#include <log4cxx/logger.h>


typedef vector<RowInterface*> Rows;
typedef Rows::iterator RowIter;

class Manager {

public:

	Manager();
	Manager(std::string, std::string);

	int doInsert(TableInterface* inTable);
	int doWrite(TableInterface* inTable, std::string name);
	int doWrite(TableInterface* inTable, RowInterface* aRow, std::string name);
	std::string makeClause(TableInterface* inTable, RowInterface* aRow);
	virtual ~Manager();
        void cleanup();   
  
protected:
	DBManagement* dbManager;
	Util util;
	Dictionary* schema;
	log4cxx::LoggerPtr logger;
	void copyAndAddRow(TableInterface* table, RowInterface* aRow, RowInterface* aNewRow);
};

//class PrimaryDatasetAPIManager : public Manager {
class PrimaryDatasetAPIManager  {
public:
	PrimaryDatasetAPIManager();
	int write(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table);
	//int write(std::vector<Primarydatasetmultirow*> rowVector, PrimarydatasetMultiTable* table);
	int read(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table);
	~PrimaryDatasetAPIManager();

};

/*
class ProcessedDatasetManager : public Manager {
public:
	ProcessedDatasetManager();
	int write(std::vector<Processingpathmultirow*> rowVector, ProcessingpathMultiTable* table);
	int read(Processingpathmultirow* aRow, ProcessingpathMultiTable* table);
	~ProcessedDatasetManager();  
};

class EvCollViewManager: public Manager {
public:
	EvCollViewManager();
	int write(std::vector<Evcollviewmultirow*> rowVector, EvcollviewMultiTable* table);
	int read(Evcollviewmultirow* aRow, EvcollviewMultiTable* table);
	~EvCollViewManager();  
};

class FileViewManager: public Manager {
public:
	FileViewManager();
	int write(std::vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table);
	int read(Fileviewmultirow* aRow, FileviewMultiTable* table);
	~FileViewManager();  
};

class BlockManager : public Manager {
public:
	BlockManager();
	int write(std::vector<Blockviewmultirow*> rowVector, BlockviewMultiTable* table);
	int read(Blockviewmultirow* aRow, BlockviewMultiTable* table);
	~BlockManager();  
};

class PDBlockViewManager: public Manager {
public:
	PDBlockViewManager();
	int write(std::vector<Pdblockviewmultirow*> rowVector, PdblockviewMultiTable* table);
	int read(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table);
	~PdblockManager();  
};

/*
class DatasetProvcChildManager : public Manager {
public:
	DatasetProvcChildManager();
	int write(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table);
	int read(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table);
	~DatasetProvcChildManager();  
};

class DatasetProvcParentManager : public Manager {
public:
	DatasetProvcParentManager();
	int write(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table);
	int read(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table);
	~DatasetProvcParentManager();  
};
*/
/*
class CrabECManager : public Manager {
public:
	CrabECManager();
	int write(std::vector<Crabevcollviewmultirow*> rowVector, CrabevcollviewMultiTable* table);
	int read(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table);
	~CrabECManager();  
};
*/
#endif

