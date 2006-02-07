#include "Managers.hpp"
#include "Util.hpp"

DatasetProvcChildManager::DatasetProvcChildManager() {
}

int DatasetProvcChildManager::write(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table) {
	table->addRow(aRow);
	return this->doWrite((TableInterface*)table, (string)"t_evcoll_parentage.child");
}

int DatasetProvcChildManager::read(Datasetprovenenceevchildmultirow* aRow, DatasetprovenenceevchildMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}


DatasetProvcChildManager::~DatasetProvcChildManager() {
        this->cleanup(); 
}


