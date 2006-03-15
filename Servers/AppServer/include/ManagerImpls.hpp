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

        DatasetPathManager();
        ~DatasetPathManager();
        int write(std::vector<Datasetpathmultirow*> rowVector, DatasetpathMultiTable* table);
        int read(Datasetpathmultirow* aRow, DatasetpathMultiTable* table);
};



/************** Manager for EvCollViewManager*********************/

class EvCollViewManager : public Manager {

public:

        EvCollViewManager();
        ~EvCollViewManager();
        int write(std::vector<Evcollviewmultirow*> rowVector, EvcollviewMultiTable* table);
        int read(Evcollviewmultirow* aRow, EvcollviewMultiTable* table);
};



/************** Manager for EvCollViewNoParentManager*********************/

class EvCollViewNoParentManager : public Manager {

public:

        EvCollViewNoParentManager();
        ~EvCollViewNoParentManager();
        int write(std::vector<Evcollviewnoparentmultirow*> rowVector, EvcollviewnoparentMultiTable* table);
        int read(Evcollviewnoparentmultirow* aRow, EvcollviewnoparentMultiTable* table);
};



/************** Manager for FileViewManager*********************/

class FileViewManager : public Manager {

public:

        FileViewManager();
        ~FileViewManager();
        int write(std::vector<Fileviewmultirow*> rowVector, FileviewMultiTable* table);
        int read(Fileviewmultirow* aRow, FileviewMultiTable* table);
};



/************** Manager for PDBlockViewManager*********************/

class PDBlockViewManager : public Manager {

public:

        PDBlockViewManager();
        ~PDBlockViewManager();
        int write(std::vector<Pdblockviewmultirow*> rowVector, PdblockviewMultiTable* table);
        int read(Pdblockviewmultirow* aRow, PdblockviewMultiTable* table);
};



/************** Manager for BlockViewManager*********************/

class BlockViewManager : public Manager {

public:

        BlockViewManager();
        ~BlockViewManager();
        int write(std::vector<Blockviewmultirow*> rowVector, BlockviewMultiTable* table);
        int read(Blockviewmultirow* aRow, BlockviewMultiTable* table);
};



/************** Manager for PrimaryDatasetManager*********************/

class PrimaryDatasetManager : public Manager {

public:

        PrimaryDatasetManager();
        ~PrimaryDatasetManager();
        int write(std::vector<Primarydatasetmultirow*> rowVector, PrimarydatasetMultiTable* table);
        int read(Primarydatasetmultirow* aRow, PrimarydatasetMultiTable* table);
};



/************** Manager for ProcessingPathManager*********************/

class ProcessingPathManager : public Manager {

public:

        ProcessingPathManager();
        ~ProcessingPathManager();
        int write(std::vector<Processingpathmultirow*> rowVector, ProcessingpathMultiTable* table);
        int read(Processingpathmultirow* aRow, ProcessingpathMultiTable* table);
};



/************** Manager for CrabEvCollFileViewManager*********************/

class CrabEvCollFileViewManager : public Manager {

public:

        CrabEvCollFileViewManager();
        ~CrabEvCollFileViewManager();
        int write(std::vector<Crabevcollfileviewmultirow*> rowVector, CrabevcollfileviewMultiTable* table);
        int read(Crabevcollfileviewmultirow* aRow, CrabevcollfileviewMultiTable* table);
};



/************** Manager for CrabEvCollViewManager*********************/

class CrabEvCollViewManager : public Manager {

public:

        CrabEvCollViewManager();
        ~CrabEvCollViewManager();
        int write(std::vector<Crabevcollviewmultirow*> rowVector, CrabevcollviewMultiTable* table);
        int read(Crabevcollviewmultirow* aRow, CrabevcollviewMultiTable* table);
};


#endif
