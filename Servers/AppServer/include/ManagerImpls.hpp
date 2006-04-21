#ifndef _MANAGER_IMPL_H_
#define _MANAGER_IMP_H_
#include "ObjectLayerTables.hpp"
#include "TableInterface.hpp"
#include <string>
#include <log4cxx/logger.h>
#include "Managers.hpp"

/************** Manager for DatasetPathManager*********************/

class DatasetPathManager : public Manager {

public:

        DatasetPathManager(DBManagement* dbManager);
        ~DatasetPathManager();
        int write(std::vector<Datasetpathmultirow*> rowVector, DatasetpathMultiTable* table);
        int update(std::vector<Datasetpathmultirow*> rowVector, DatasetpathMultiTable* table);
        int read(Datasetpathmultirow* aRow, DatasetpathMultiTable* table);
};



/************** Manager for EvCollViewManager*********************/

class EvCollViewManager : public Manager {

public:

        EvCollViewManager(DBManagement* dbManager);
        ~EvCollViewManager();
        int write(std::vector<Evcollviewmultirow*> rowVector, EvcollviewMultiTable* table);
        int update(std::vector<Evcollviewmultirow*> rowVector, EvcollviewMultiTable* table);
        int read(Evcollviewmultirow* aRow, EvcollviewMultiTable* table);
};



/************** Manager for EvCollViewNoParentManager*********************/

class EvCollViewNoParentManager : public Manager {

public:

        EvCollViewNoParentManager(DBManagement* dbManager);
        ~EvCollViewNoParentManager();
        int write(std::vector<Evcollviewnoparentmultirow*> rowVector, EvcollviewnoparentMultiTable* table);
        int update(std::vector<Evcollviewnoparentmultirow*> rowVector, EvcollviewnoparentMultiTable* table);
        int read(Evcollviewnoparentmultirow* aRow, EvcollviewnoparentMultiTable* table);
};



/************** Manager for FileViewManager*********************/

class FileViewManager : public Manager {

public:

        FileViewManager(DBManagement* dbManager);
        ~FileViewManager();
        int write(std::vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table);
        int update(std::vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table);
        int read(Fileviewmultirow* aRow, FileviewMultiTable* table);
};



/************** Manager for PDBlockViewManager*********************/

class PDBlockViewManager : public Manager {

public:

        PDBlockViewManager(DBManagement* dbManager);
        ~PDBlockViewManager();
        int write(std::vector<Pdblockviewmultirow*> rowVector, PdblockviewMultiTable* table);
        int update(std::vector<Pdblockviewmultirow*> rowVector, PdblockviewMultiTable* table);
        int read(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table);
};



/************** Manager for BlockViewManager*********************/

class BlockViewManager : public Manager {

public:

        BlockViewManager(DBManagement* dbManager);
        ~BlockViewManager();
        int write(std::vector<Blockviewmultirow*> rowVector, BlockviewMultiTable* table);
        int update(std::vector<Blockviewmultirow*> rowVector, BlockviewMultiTable* table);
        int read(Blockviewmultirow* aRow, BlockviewMultiTable* table);
};



/************** Manager for PrimaryDatasetManager*********************/

class PrimaryDatasetManager : public Manager {

public:

        PrimaryDatasetManager(DBManagement* dbManager);
        ~PrimaryDatasetManager();
        int write(std::vector<Primarydatasetmultirow*> rowVector, PrimarydatasetMultiTable* table);
        int update(std::vector<Primarydatasetmultirow*> rowVector, PrimarydatasetMultiTable* table);
        int read(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table);
};



/************** Manager for ProcessingPathManager*********************/

class ProcessingPathManager : public Manager {

public:

        ProcessingPathManager(DBManagement* dbManager);
        ~ProcessingPathManager();
        int write(std::vector<Processingpathmultirow*> rowVector, ProcessingpathMultiTable* table);
        int update(std::vector<Processingpathmultirow*> rowVector, ProcessingpathMultiTable* table);
        int read(Processingpathmultirow* aRow, ProcessingpathMultiTable* table);
};



/************** Manager for CrabEvCollFileViewManager*********************/

class CrabEvCollFileViewManager : public Manager {

public:

        CrabEvCollFileViewManager(DBManagement* dbManager);
        ~CrabEvCollFileViewManager();
        int write(std::vector<Crabevcollfileviewmultirow*> rowVector, CrabevcollfileviewMultiTable* table);
        int update(std::vector<Crabevcollfileviewmultirow*> rowVector, CrabevcollfileviewMultiTable* table);
        int read(Crabevcollfileviewmultirow* aRow, CrabevcollfileviewMultiTable* table);
};



/************** Manager for CrabEvCollViewManager*********************/

class CrabEvCollViewManager : public Manager {

public:

        CrabEvCollViewManager(DBManagement* dbManager);
        ~CrabEvCollViewManager();
        int write(std::vector<Crabevcollviewmultirow*> rowVector, CrabevcollviewMultiTable* table);
        int update(std::vector<Crabevcollviewmultirow*> rowVector, CrabevcollviewMultiTable* table);
        int read(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table);
};



/************** Manager for EvCollFileViewManager*********************/

class EvCollFileViewManager : public Manager {

public:

        EvCollFileViewManager(DBManagement* dbManager);
        ~EvCollFileViewManager();
        int write(std::vector<Evcollfileviewmultirow*> rowVector, EvcollfileviewMultiTable* table);
        int update(std::vector<Evcollfileviewmultirow*> rowVector, EvcollfileviewMultiTable* table);
        int read(Evcollfileviewmultirow* aRow, EvcollfileviewMultiTable* table);
};



/************** Manager for EvCollParentageViewManager*********************/

class EvCollParentageViewManager : public Manager {

public:

        EvCollParentageViewManager(DBManagement* dbManager);
        ~EvCollParentageViewManager();
        int write(std::vector<Evcollparentageviewmultirow*> rowVector, EvcollparentageviewMultiTable* table);
        int update(std::vector<Evcollparentageviewmultirow*> rowVector, EvcollparentageviewMultiTable* table);
        int read(Evcollparentageviewmultirow* aRow, EvcollparentageviewMultiTable* table);
};



/************** Manager for EvCollStatusViewManager*********************/

class EvCollStatusViewManager : public Manager {

public:

        EvCollStatusViewManager(DBManagement* dbManager);
        ~EvCollStatusViewManager();
        int write(std::vector<Evcollstatusviewmultirow*> rowVector, EvcollstatusviewMultiTable* table);
        int update(std::vector<Evcollstatusviewmultirow*> rowVector, EvcollstatusviewMultiTable* table);
        int read(Evcollstatusviewmultirow* aRow, EvcollstatusviewMultiTable* table);
};



/************** Manager for EvCollSingleViewManager*********************/

class EvCollSingleViewManager : public Manager {

public:

        EvCollSingleViewManager(DBManagement* dbManager);
        ~EvCollSingleViewManager();
        int write(std::vector<Evcollsingleviewmultirow*> rowVector, EvcollsingleviewMultiTable* table);
        int update(std::vector<Evcollsingleviewmultirow*> rowVector, EvcollsingleviewMultiTable* table);
        int read(Evcollsingleviewmultirow* aRow, EvcollsingleviewMultiTable* table);
};


#endif
