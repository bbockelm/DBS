#include "Managers.hpp"
#include "Util.hpp"

DatasetProvcParentManager::DatasetProvcParentManager() {
}

int DatasetProvcParentManager::write(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table) {
	table->addRow(aRow);
	return this->doWrite((TableInterface*)table, (string)"t_evcoll_parentage.parent");
}

int DatasetProvcParentManager::read(Datasetprovenenceevparentmultirow* aRow, DatasetprovenenceevparentMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}


DatasetProvcParentManager::~DatasetProvcParentManager() {
        this->cleanup(); 
}

