#include "DBSClient.hpp"
#include "Log.hpp"
#include "Managers.hpp"
#include "BizLayerException.hpp"
#include <exception>

DBSClient::DBSClient() {
	static Log l("TableTemplate");
	DBSClient::logger = l.getLogger();
	Configuration* conf = Configuration::instance();
	string serverType = conf->getServerType(); 
	LOG4CXX_INFO(logger, "Server Type is " + serverType);
	//cout << "Server Type: " << serverType << endl;
	if ( serverType == "Local" ) {
		localServer = true;
	}  else {
	}
}  

DBSClient::~DBSClient() {
}

int DBSClient::callServer()  {
	try { 
		if (localServer) {
		}else { //Remote server
		}
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
		return 0;
	}
	return 1;
}


int DBSClient::createPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) throw (const char*) {
	try {
		PrimaryDatasetManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readPrimaryDataset(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) throw (const char*) {
	try {
		PrimaryDatasetManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::createProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) throw (const char*) {
	try {
		ProcessedDatasetManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readProcessedDataset(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) throw (const char*) {
	try {
		ProcessedDatasetManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::createEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) throw (const char*) {
	try {
		ECManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readEventCollection(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) throw (const char*) {
	try {
		ECManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::createBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) throw (const char*) {
	try {
		BlockManager manager;
		return manager.write(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readBlock(Blockviewmultirow* aRow, BlockviewMultiTable* table) throw (const char*) {
	try {
		BlockManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}


int DBSClient::insertFiles(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) throw (const char*) {
	try {
		FileManager manager;
		return manager.write(rowVector, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readFiles(Fileviewmultirow* aRow, FileviewMultiTable* table) throw (const char*) {
	try {
		FileManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readDatasetProvenenceParent(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table) throw (const char*) {
	try {
		DatasetProvcParentManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readDatasetProvenenceChild(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table) throw (const char*) {
	try {
		DatasetProvcChildManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}

int DBSClient::readCrabEC(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) throw (const char*) {
	try {
		CrabECManager manager;
		return manager.read(aRow, table);
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
	}
}


