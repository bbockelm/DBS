#include "RowNSchemaBinding.hpp"

RowNSchemaBinding::RowNSchemaBinding() {
      BaseSchemaNConstraintsBinding* T_Personrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Personrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_personrow", T_Personrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Object_Historyrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Object_Historyrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_object_historyrow", T_Object_Historyrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Parameter_Setrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Parameter_Setrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_parameter_setrow", T_Parameter_Setrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_App_Familyrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_App_Familyrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_app_familyrow", T_App_Familyrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Applicationrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Applicationrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_applicationrow", T_Applicationrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_App_Configrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_App_Configrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_app_configrow", T_App_Configrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Data_Tierrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Data_Tierrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_data_tierrow", T_Data_Tierrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Parentage_Typerow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Parentage_Typerow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_parentage_typerow", T_Parentage_Typerow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Evcoll_Statusrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Evcoll_Statusrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_evcoll_statusrow", T_Evcoll_Statusrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Primary_Datasetrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Primary_Datasetrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_primary_datasetrow", T_Primary_Datasetrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Processing_Namerow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Processing_Namerow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_processing_namerow", T_Processing_Namerow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Processingrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Processingrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_processingrow", T_Processingrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Processed_Datasetrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Processed_Datasetrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_processed_datasetrow", T_Processed_Datasetrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Event_Collectionrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Event_Collectionrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_event_collectionrow", T_Event_Collectionrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Evcoll_Parentagerow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Evcoll_Parentagerow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_evcoll_parentagerow", T_Evcoll_Parentagerow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Block_Statusrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Block_Statusrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_block_statusrow", T_Block_Statusrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Blockrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Blockrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_blockrow", T_Blockrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_File_Statusrow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_File_Statusrow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_file_statusrow", T_File_Statusrow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_File_Typerow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_File_Typerow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_file_typerow", T_File_Typerow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Filerow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Filerow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_filerow", T_Filerow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* T_Evcoll_Filerow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new T_Evcoll_Filerow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("t_evcoll_filerow", T_Evcoll_Filerow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Datasetpathmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Datasetpathmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("datasetpathmultirow", Datasetpathmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Evcollviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Evcollviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("evcollviewmultirow", Evcollviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Evcollviewnoparentmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Evcollviewnoparentmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("evcollviewnoparentmultirow", Evcollviewnoparentmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Fileviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Fileviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("fileviewmultirow", Fileviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Pdblockviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Pdblockviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("pdblockviewmultirow", Pdblockviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Blockviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Blockviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("blockviewmultirow", Blockviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Primarydatasetmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Primarydatasetmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("primarydatasetmultirow", Primarydatasetmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Processingpathmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Processingpathmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("processingpathmultirow", Processingpathmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Crabevcollfileviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Crabevcollfileviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("crabevcollfileviewmultirow", Crabevcollfileviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Crabevcollviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Crabevcollviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("crabevcollviewmultirow", Crabevcollviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Evcollfileviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Evcollfileviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("evcollfileviewmultirow", Evcollfileviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Evcollparentageviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Evcollparentageviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("evcollparentageviewmultirow", Evcollparentageviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Evcollstatusviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Evcollstatusviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("evcollstatusviewmultirow", Evcollstatusviewmultirow_DB_BINDINGObj));
      BaseSchemaNConstraintsBinding* Evcollsingleviewmultirow_DB_BINDINGObj = (BaseSchemaNConstraintsBinding*) new Evcollsingleviewmultirow_DB_BINDING();
      this->RowNSchemaBindingMap.insert(SchemaMapEntry("evcollsingleviewmultirow", Evcollsingleviewmultirow_DB_BINDINGObj));
}
RowNSchemaBinding::~RowNSchemaBinding() {
      for (SchemaMap_iter i = this->RowNSchemaBindingMap.begin(); i != this->RowNSchemaBindingMap.end(); i++) {
          delete i->second;

      }
}

BaseSchemaNConstraintsBinding* RowNSchemaBinding::getSchemaObject(string tableName) {
    for (SchemaMap_iter i = this->RowNSchemaBindingMap.begin(); i != this->RowNSchemaBindingMap.end(); i++) {
       if (tableName.compare(i->first)  == 0) {
          return i->second;
       }
    }
}
