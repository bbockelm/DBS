#include "Managers.hpp"
#include "Util.hpp"


ECManager::ECManager() {
}

int ECManager::write(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) {
	Evcollviewmultirow* aNewRow = new Evcollviewmultirow();
	this->copyAndAddRow(table, aRow, aNewRow);
	return this->doWrite((TableInterface*)table, (string)"t_event_collection.id");
}
int ECManager::read(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}

ECManager::~ECManager() {
        this->cleanup(); 
}


