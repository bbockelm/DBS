#include "DBSApi.hpp"
#include "Log.hpp"
#include "Managers.hpp"
#include "BizLayerException.hpp"
#include <exception>

DBSApi::DBSApi() {
	static Log l("TableTemplate");
	DBSApi::logger = l.getLogger();
	Configuration* conf = Configuration::instance();
	string serverType = conf->getServerType(); 
	LOG4CXX_INFO(logger, "Server Type is " + serverType);
	//cout << "Server Type: " << serverType << endl;
	if ( serverType == "Local" ) {
		localServer = true;
	}  else {
	}
}  

DBSApi::~DBSApi() {
}

int DBSApi::callServer()  {
	try { 
		if (localServer) {
		}else { //Remote server
		}
	} catch (BizLayerException &e)  {
		throw e.report();
		return 0;
	}
	return 1;
}


int DBSApi::createPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) {
	try {
		PrimaryDatasetAPIManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::readPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) {
	try {
		PrimaryDatasetAPIManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}
/*
int DBSApi::createProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) {
	try {
		ProcessedDatasetManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::readProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) {
	try {
		ProcessedDatasetManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::createEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) {
	try {
		ECManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::readEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) {
	try {
		ECManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::createBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) {
	try {
		BlockManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::readBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) {
	try {
		BlockManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::readPdblock(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table) {
	try {
		PdblockManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}


int DBSApi::insertFiles(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) {
	try {
		FileManager manager;
                cout<<"FileManager Instantiated"<<endl; 
		return manager.write(rowVector, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::readFiles(Fileviewmultirow* aRow, FileviewMultiTable* table) {
	try {
		FileManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

/*
int DBSApi::readDatasetProvenenceParent(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table) {
	try {
		DatasetProvcParentManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}

int DBSApi::readDatasetProvenenceChild(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table) {
	try {
		DatasetProvcChildManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}
*/
/*
int DBSApi::readCrabEC(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) {
	try {
		CrabECManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report();
	}
}
*/

