#include "Managers.hpp"
#include "Util.hpp"

BlockManager::BlockManager() {
}

int BlockManager::write(Blockviewmultirow* aRow, BlockviewMultiTable* table) {
	table->addRow(aRow);
	return this->doWrite((TableInterface*)table, (string)"t_block.id");
}

int BlockManager::read(Blockviewmultirow* aRow, BlockviewMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}


BlockManager::~BlockManager() {
        this->cleanup(); 
}

