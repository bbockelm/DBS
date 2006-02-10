#include "Managers.hpp"
#include "Util.hpp"

CrabECManager::CrabECManager() {
}

int CrabECManager::write(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) {
	Crabevcollviewmultirow* aNewRow = new Crabevcollviewmultirow();
	this->copyAndAddRow(table, aRow, aNewRow);
	//table->addRow(aRow);
	return this->doWrite((TableInterface*)table, (string)"t_event_collection.id");
}

int CrabECManager::read(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}


CrabECManager::~CrabECManager() {
        this->cleanup(); 
}

