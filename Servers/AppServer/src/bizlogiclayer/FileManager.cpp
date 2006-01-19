#include "Managers.hpp"
#include "Util.hpp"


FileManager::FileManager() {
}

int FileManager::write(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) {
	for(int i = 0 ; i != rowVector.size(); ++i) {
		table->addRow((Fileviewmultirow*)rowVector.at(i));
	}
	return this->doWrite((TableInterface*)table, (string)"t_evcoll_file.evcoll" );
	
}
int FileManager::read(Fileviewmultirow* aRow, FileviewMultiTable* table) {
	table->setDBManager(dbManager);
	string clause = this->makeClause(table, aRow);
 	table->select(clause);
	return 1;
}

FileManager::~FileManager() {
        this->cleanup(); 
}


