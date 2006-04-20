#include "Managers.hpp"
#include "ManagerImpls.hpp"
#include "ObjectLayerException.hpp"
#include "BizLayerException.hpp"
#include "Util.hpp"

/************** Manager for DatasetPathManager*********************/
DatasetPathManager::DatasetPathManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int DatasetPathManager::write(vector<Datasetpathmultirow*> rowVector, DatasetpathMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int DatasetPathManager::update(vector<Datasetpathmultirow*> rowVector, DatasetpathMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int DatasetPathManager::read(Datasetpathmultirow* aRow, DatasetpathMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

DatasetPathManager::~DatasetPathManager() {
      this->cleanup();
}


/************** Manager for EvCollViewManager*********************/
EvCollViewManager::EvCollViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int EvCollViewManager::write(vector<Evcollviewmultirow*> rowVector, EvcollviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_parentage.id");
}

int EvCollViewManager::update(vector<Evcollviewmultirow*> rowVector, EvcollviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_evcoll_parentage.id");
}

int EvCollViewManager::read(Evcollviewmultirow* aRow, EvcollviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

EvCollViewManager::~EvCollViewManager() {
      this->cleanup();
}


/************** Manager for EvCollViewNoParentManager*********************/
EvCollViewNoParentManager::EvCollViewNoParentManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int EvCollViewNoParentManager::write(vector<Evcollviewnoparentmultirow*> rowVector, EvcollviewnoparentMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_event_collection.id");
}

int EvCollViewNoParentManager::update(vector<Evcollviewnoparentmultirow*> rowVector, EvcollviewnoparentMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_event_collection.id");
}

int EvCollViewNoParentManager::read(Evcollviewnoparentmultirow* aRow, EvcollviewnoparentMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

EvCollViewNoParentManager::~EvCollViewNoParentManager() {
      this->cleanup();
}


/************** Manager for FileViewManager*********************/
FileViewManager::FileViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int FileViewManager::write(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int FileViewManager::update(vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int FileViewManager::read(Fileviewmultirow* aRow, FileviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

FileViewManager::~FileViewManager() {
      this->cleanup();
}


/************** Manager for PDBlockViewManager*********************/
PDBlockViewManager::PDBlockViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int PDBlockViewManager::write(vector<Pdblockviewmultirow*> rowVector, PdblockviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int PDBlockViewManager::update(vector<Pdblockviewmultirow*> rowVector, PdblockviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int PDBlockViewManager::read(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

PDBlockViewManager::~PDBlockViewManager() {
      this->cleanup();
}


/************** Manager for BlockViewManager*********************/
BlockViewManager::BlockViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int BlockViewManager::write(vector<Blockviewmultirow*> rowVector, BlockviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_block.id");
}

int BlockViewManager::update(vector<Blockviewmultirow*> rowVector, BlockviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_block.id");
}

int BlockViewManager::read(Blockviewmultirow* aRow, BlockviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

BlockViewManager::~BlockViewManager() {
      this->cleanup();
}


/************** Manager for PrimaryDatasetManager*********************/
PrimaryDatasetManager::PrimaryDatasetManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int PrimaryDatasetManager::write(vector<Primarydatasetmultirow*> rowVector, PrimarydatasetMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_primary_dataset.id");
}

int PrimaryDatasetManager::update(vector<Primarydatasetmultirow*> rowVector, PrimarydatasetMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_primary_dataset.id");
}

int PrimaryDatasetManager::read(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

PrimaryDatasetManager::~PrimaryDatasetManager() {
      this->cleanup();
}


/************** Manager for ProcessingPathManager*********************/
ProcessingPathManager::ProcessingPathManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int ProcessingPathManager::write(vector<Processingpathmultirow*> rowVector, ProcessingpathMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int ProcessingPathManager::update(vector<Processingpathmultirow*> rowVector, ProcessingpathMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_processed_dataset.id");
}

int ProcessingPathManager::read(Processingpathmultirow* aRow, ProcessingpathMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

ProcessingPathManager::~ProcessingPathManager() {
      this->cleanup();
}


/************** Manager for CrabEvCollFileViewManager*********************/
CrabEvCollFileViewManager::CrabEvCollFileViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int CrabEvCollFileViewManager::write(vector<Crabevcollfileviewmultirow*> rowVector, CrabevcollfileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int CrabEvCollFileViewManager::update(vector<Crabevcollfileviewmultirow*> rowVector, CrabevcollfileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int CrabEvCollFileViewManager::read(Crabevcollfileviewmultirow* aRow, CrabevcollfileviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

CrabEvCollFileViewManager::~CrabEvCollFileViewManager() {
      this->cleanup();
}


/************** Manager for CrabEvCollViewManager*********************/
CrabEvCollViewManager::CrabEvCollViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int CrabEvCollViewManager::write(vector<Crabevcollviewmultirow*> rowVector, CrabevcollviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_block.id");
}

int CrabEvCollViewManager::update(vector<Crabevcollviewmultirow*> rowVector, CrabevcollviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_block.id");
}

int CrabEvCollViewManager::read(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

CrabEvCollViewManager::~CrabEvCollViewManager() {
      this->cleanup();
}


/************** Manager for EvCollFileViewManager*********************/
EvCollFileViewManager::EvCollFileViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int EvCollFileViewManager::write(vector<Evcollfileviewmultirow*> rowVector, EvcollfileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int EvCollFileViewManager::update(vector<Evcollfileviewmultirow*> rowVector, EvcollfileviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_evcoll_file.id");
}

int EvCollFileViewManager::read(Evcollfileviewmultirow* aRow, EvcollfileviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

EvCollFileViewManager::~EvCollFileViewManager() {
      this->cleanup();
}


/************** Manager for EvCollParentageViewManager*********************/
EvCollParentageViewManager::EvCollParentageViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int EvCollParentageViewManager::write(vector<Evcollparentageviewmultirow*> rowVector, EvcollparentageviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_parentage.id");
}

int EvCollParentageViewManager::update(vector<Evcollparentageviewmultirow*> rowVector, EvcollparentageviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_evcoll_parentage.id");
}

int EvCollParentageViewManager::read(Evcollparentageviewmultirow* aRow, EvcollparentageviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

EvCollParentageViewManager::~EvCollParentageViewManager() {
      this->cleanup();
}


/************** Manager for EvCollStatusViewManager*********************/
EvCollStatusViewManager::EvCollStatusViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int EvCollStatusViewManager::write(vector<Evcollstatusviewmultirow*> rowVector, EvcollstatusviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_evcoll_status.id");
}

int EvCollStatusViewManager::update(vector<Evcollstatusviewmultirow*> rowVector, EvcollstatusviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_evcoll_status.id");
}

int EvCollStatusViewManager::read(Evcollstatusviewmultirow* aRow, EvcollstatusviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

EvCollStatusViewManager::~EvCollStatusViewManager() {
      this->cleanup();
}


/************** Manager for EvCollSingleViewManager*********************/
EvCollSingleViewManager::EvCollSingleViewManager(DBManagement* dbManager) {
	this->dbManager = dbManager;
}

int EvCollSingleViewManager::write(vector<Evcollsingleviewmultirow*> rowVector, EvcollsingleviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doWrite((TableInterface*)table, 
                  (string)"t_event_collection.id");
}

int EvCollSingleViewManager::update(vector<Evcollsingleviewmultirow*> rowVector, EvcollsingleviewMultiTable* table) {

      for(int i = 0 ; i != rowVector.size(); ++i) {
         table->addRow(rowVector.at(i));
      }
      return this->doUpdate((TableInterface*)table, 
                  (string)"t_event_collection.id");
}

int EvCollSingleViewManager::read(Evcollsingleviewmultirow* aRow, EvcollsingleviewMultiTable* table) {
      try{
          table->setDBManager(dbManager);
          string clause = this->makeClause(table, aRow);
          table->select(clause);
      } catch (ObjectLayerException &e) {
          throw BizLayerException(e.report());
      }
      return 1;
}

EvCollSingleViewManager::~EvCollSingleViewManager() {
      this->cleanup();
}

