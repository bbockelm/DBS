#include "Managers.hpp"
#include "Util.hpp"


ProcessedDatasetManager::ProcessedDatasetManager() {
}

int ProcessedDatasetManager::write(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) {
	table->addRow(aRow);
	//return this->doWrite((TableInterface*)table, (RowInterface*)aRow, (string)"t_processed_dataset.id");
	return this->doWrite((TableInterface*)table, (string)"t_processed_dataset.id");
}
int ProcessedDatasetManager::read(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}

ProcessedDatasetManager::~ProcessedDatasetManager() {
        this->cleanup(); 
}

