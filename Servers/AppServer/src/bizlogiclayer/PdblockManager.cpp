#include "Managers.hpp"
#include "Util.hpp"

PdblockManager::PdblockManager() {
}

int PdblockManager::write(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table) {
	table->addRow(aRow);
	return this->doWrite((TableInterface*)table, (string)"t_block.id");
}

int PdblockManager::read(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}


PdblockManager::~PdblockManager() {
        this->cleanup(); 
}

