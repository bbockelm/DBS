#include "TableFactory.hpp"

TableFactory::TableFactory() {
}
TableInterface* TableFactory::getTableObject(string tableName) {
      if ( tableName.compare("t_schema_revisionrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Schema_Revisionrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_personrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Personrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_physics_grouprow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Physics_Grouprow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_collection_typerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Collection_Typerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_app_familyrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_App_Familyrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_applicationrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Applicationrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_app_configrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_App_Configrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_desc_triggerrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Desc_Triggerrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_desc_mcrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Desc_Mcrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_desc_primaryrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Desc_Primaryrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_data_tierrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Data_Tierrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_primary_datasetrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Primary_Datasetrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_processing_pathrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Processing_Pathrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_processed_datasetrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Processed_Datasetrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_event_collectionrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Event_Collectionrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_analysis_datasetrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Analysis_Datasetrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_anads_datarow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Anads_Datarow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_parentage_typerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Parentage_Typerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_evcoll_parentagerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Evcoll_Parentagerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_block_statusrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Block_Statusrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_blockrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Blockrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_file_statusrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_File_Statusrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_file_typerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_File_Typerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_filerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Filerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_evcoll_filerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Evcoll_Filerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_validation_statusrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Validation_Statusrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_dataset_statusrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Dataset_Statusrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_evcoll_statusrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Evcoll_Statusrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_run_qualityrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Run_Qualityrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_info_anadsrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Info_Anadsrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_info_evcollrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Info_Evcollrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_runrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Runrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_evcoll_runrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Evcoll_Runrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_object_historyrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Object_Historyrow>;
         return tmpPtr;
      }
      if ( tableName.compare("insertappsmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Insertappsmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("personmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Personmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("physicsgroupmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Physicsgroupmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("evcollviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Evcollviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("fileviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Fileviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("primarydatasetmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Primarydatasetmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("processingpathmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Processingpathmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("analysisdatasetmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Analysisdatasetmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("datasetprovenenceevchildmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Datasetprovenenceevchildmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("datasetprovenenceevparentmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Datasetprovenenceevparentmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("crabevcollviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Crabevcollviewmultirow>;
         return tmpPtr;
      }
}
TableFactory::~TableFactory() {
}
