#include "Managers.hpp"
#include "ManagerImpls.hpp"
#include "Util.hpp"

/************** Manager for DatasetPathManager*********************/
DatasetPathManager::DatasetPathManager() {
}

int DatasetPathManager::write(vector<Datasetpathmultirow*> rowVector, DatasetpathMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int DatasetPathManager::read(Datasetpathmultirow* aRow, DatasetpathMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

DatasetPathManager::~DatasetPathManager() {
      this->cleanup();
}


/************** Manager for EvCollViewManager*********************/
EvCollViewManager::EvCollViewManager() {
}

int EvCollViewManager::write(vector<Evcollviewmultirow*> rowVector, EvcollviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_parentage.id");
}

int EvCollViewManager::read(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

EvCollViewManager::~EvCollViewManager() {
      this->cleanup();
}


/************** Manager for EvCollViewNoParentManager*********************/
EvCollViewNoParentManager::EvCollViewNoParentManager() {
}

int EvCollViewNoParentManager::write(vector<Evcollviewnoparentmultirow*> rowVector, EvcollviewnoparentMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_info_evcoll.event_collection");
}

int EvCollViewNoParentManager::read(Evcollviewnoparentmultirow* aRow, EvcollviewnoparentMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

EvCollViewNoParentManager::~EvCollViewNoParentManager() {
      this->cleanup();
}


/************** Manager for FileViewManager*********************/
FileViewManager::FileViewManager() {
}

int FileViewManager::write(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int FileViewManager::read(Fileviewmultirow* aRow, FileviewMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

FileViewManager::~FileViewManager() {
      this->cleanup();
}


/************** Manager for PDBlockViewManager*********************/
PDBlockViewManager::PDBlockViewManager() {
}

int PDBlockViewManager::write(vector<Pdblockviewmultirow*> rowVector, PdblockviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_block.id");
}

int PDBlockViewManager::read(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

PDBlockViewManager::~PDBlockViewManager() {
      this->cleanup();
}


/************** Manager for BlockViewManager*********************/
BlockViewManager::BlockViewManager() {
}

int BlockViewManager::write(vector<Blockviewmultirow*> rowVector, BlockviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_block.id");
}

int BlockViewManager::read(Blockviewmultirow* aRow, BlockviewMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

BlockViewManager::~BlockViewManager() {
      this->cleanup();
}


/************** Manager for PrimaryDatasetManager*********************/
PrimaryDatasetManager::PrimaryDatasetManager() {
}

int PrimaryDatasetManager::write(vector<Primarydatasetmultirow*> rowVector, PrimarydatasetMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_primary_dataset.id");
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


/************** Manager for ProcessingPathManager*********************/
ProcessingPathManager::ProcessingPathManager() {
}

int ProcessingPathManager::write(vector<Processingpathmultirow*> rowVector, ProcessingpathMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int ProcessingPathManager::read(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

ProcessingPathManager::~ProcessingPathManager() {
      this->cleanup();
}


/************** Manager for CrabEvCollFileViewManager*********************/
CrabEvCollFileViewManager::CrabEvCollFileViewManager() {
}

int CrabEvCollFileViewManager::write(vector<Crabevcollfileviewmultirow*> rowVector, CrabevcollfileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int CrabEvCollFileViewManager::read(Crabevcollfileviewmultirow* aRow, CrabevcollfileviewMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

CrabEvCollFileViewManager::~CrabEvCollFileViewManager() {
      this->cleanup();
}


/************** Manager for CrabEvCollViewManager*********************/
CrabEvCollViewManager::CrabEvCollViewManager() {
}

int CrabEvCollViewManager::write(vector<Crabevcollviewmultirow*> rowVector, CrabevcollviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_info_evcoll.event_collection");
}

int CrabEvCollViewManager::read(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) {
      table->setDBManager(dbManager);
      string clause = this->makeClause(table, aRow);
      table->select(clause);
      return 1;
}

CrabEvCollViewManager::~CrabEvCollViewManager() {
      this->cleanup();
}

