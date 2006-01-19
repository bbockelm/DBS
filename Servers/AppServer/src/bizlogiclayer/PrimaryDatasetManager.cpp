#include "Managers.hpp"
#include "Util.hpp"

PrimaryDatasetManager::PrimaryDatasetManager() {
}

int PrimaryDatasetManager::write(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) {
	//return this->doWrite((TableInterface*)table, (RowInterface*)aRow, (string)"t_primary_dataset.id");
	table->addRow(aRow);
	return this->doWrite((TableInterface*)table, (string)"t_primary_dataset.id");
}

int PrimaryDatasetManager::read(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}


PrimaryDatasetManager::~PrimaryDatasetManager() {
        this->cleanup(); 
}

