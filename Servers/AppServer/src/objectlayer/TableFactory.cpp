#include "TableFactory.hpp"

TableFactory::TableFactory() {
}
TableInterface* TableFactory::getTableObject(string tableName) {
      if ( tableName.compare("t_personrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Personrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_object_historyrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Object_Historyrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_parameter_setrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Parameter_Setrow>;
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
      if ( tableName.compare("t_data_tierrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Data_Tierrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_parentage_typerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Parentage_Typerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_evcoll_statusrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Evcoll_Statusrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_primary_datasetrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Primary_Datasetrow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_processing_namerow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Processing_Namerow>;
         return tmpPtr;
      }
      if ( tableName.compare("t_processingrow") == 0 ) {
         TableInterface* tmpPtr = new SingleTableInterface<T_Processingrow>;
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
      if ( tableName.compare("datasetpathmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Datasetpathmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("evcollviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Evcollviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("evcollviewnoparentmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Evcollviewnoparentmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("fileviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Fileviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("pdblockviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Pdblockviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("blockviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Blockviewmultirow>;
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
      if ( tableName.compare("crabevcollfileviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Crabevcollfileviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("crabevcollviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Crabevcollviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("evcollfileviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Evcollfileviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("evcollparentageviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Evcollparentageviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("evcollstatusviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Evcollstatusviewmultirow>;
         return tmpPtr;
      }
      if ( tableName.compare("evcollsingleviewmultirow") == 0 ) {
         TableInterface* tmpPtr = new MultiTableInterface<Evcollsingleviewmultirow>;
         return tmpPtr;
      }
}
TableFactory::~TableFactory() {
}
