
#include "ObjectLayerTables.hpp"


T_Personrow::T_Personrow(){
    this->rowMap.set("t_personrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Personrow::setValue(string key, void* value) {
    if( key.compare("t_person.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_person.name") == 0) {
         this->name = *((string*) value) ;
    }
    if( key.compare("t_person.distinguished_name") == 0) {
         this->distinguished_name = *((string*) value) ;
    }
    if( key.compare("t_person.contact_info") == 0) {
         this->contact_info = *((string*) value) ;
    }
}

void* T_Personrow::getValue(string key) {
   if( key.compare("t_person.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_person.name") == 0) {
       return (&this->name.getValue());
    }
   if( key.compare("t_person.distinguished_name") == 0) {
       return (&this->distinguished_name.getValue());
    }
   if( key.compare("t_person.contact_info") == 0) {
       return (&this->contact_info.getValue());
    }
}

T_Personrow_DB_BINDING::T_Personrow_DB_BINDING() {
    TableName = "t_person";

    Schema.insert(Entry("t_person.id", "INTEGER"));
    Schema.insert(Entry("t_person.name", "STRING"));
    Schema.insert(Entry("t_person.distinguished_name", "STRING"));
    Schema.insert(Entry("t_person.contact_info", "STRING"));

    PrimaryKeys.push_back("t_person.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_person.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_person.distinguished_name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_person.id");
    NotNullKeys.push_back("t_person.name");
    NotNullKeys.push_back("t_person.distinguished_name");
    NotNullKeys.push_back("t_person.contact_info");


    SchemaOrder.push_back("t_person");
}

string* T_Personrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Object_Historyrow::T_Object_Historyrow(){
    this->rowMap.set("t_object_historyrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Object_Historyrow::setValue(string key, void* value) {
    if( key.compare("t_object_history.object_type") == 0) {
         this->object_type = *((string*) value) ;
    }
    if( key.compare("t_object_history.object_id") == 0) {
         this->object_id = *((int*) value) ;
    }
    if( key.compare("t_object_history.operation") == 0) {
         this->operation = *((string*) value) ;
    }
    if( key.compare("t_object_history.at") == 0) {
         this->at = *((float*) value) ;
    }
    if( key.compare("t_object_history.person") == 0) {
         this->person = *((int*) value) ;
    }
    if( key.compare("t_object_history.mediator") == 0) {
         this->mediator = *((int*) value) ;
    }
}

void* T_Object_Historyrow::getValue(string key) {
   if( key.compare("t_object_history.object_type") == 0) {
       return (&this->object_type.getValue());
    }
   if( key.compare("t_object_history.object_id") == 0) {
       return (&this->object_id.getValue());
    }
   if( key.compare("t_object_history.operation") == 0) {
       return (&this->operation.getValue());
    }
   if( key.compare("t_object_history.at") == 0) {
       return (&this->at.getValue());
    }
   if( key.compare("t_object_history.person") == 0) {
       return (&this->person.getValue());
    }
   if( key.compare("t_object_history.mediator") == 0) {
       return (&this->mediator.getValue());
    }
}

T_Object_Historyrow_DB_BINDING::T_Object_Historyrow_DB_BINDING() {
    TableName = "t_object_history";

    Schema.insert(Entry("t_object_history.object_type", "STRING"));
    Schema.insert(Entry("t_object_history.object_id", "INTEGER"));
    Schema.insert(Entry("t_object_history.operation", "STRING"));
    Schema.insert(Entry("t_object_history.at", "FLOAT"));
    Schema.insert(Entry("t_object_history.person", "INTEGER"));
    Schema.insert(Entry("t_object_history.mediator", "INTEGER"));


    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;

    NotNullKeys.push_back("t_object_history.object_id");
    NotNullKeys.push_back("t_object_history.operation");
    NotNullKeys.push_back("t_object_history.at");
    NotNullKeys.push_back("t_object_history.person");
    NotNullKeys.push_back("t_object_history.mediator");


    SchemaOrder.push_back("t_object_history");
}

string* T_Object_Historyrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_App_Familyrow::T_App_Familyrow(){
    this->rowMap.set("t_app_familyrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_App_Familyrow::setValue(string key, void* value) {
    if( key.compare("t_app_family.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_app_family.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_App_Familyrow::getValue(string key) {
   if( key.compare("t_app_family.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_app_family.name") == 0) {
       return (&this->name.getValue());
    }
}

T_App_Familyrow_DB_BINDING::T_App_Familyrow_DB_BINDING() {
    TableName = "t_app_family";

    Schema.insert(Entry("t_app_family.id", "INTEGER"));
    Schema.insert(Entry("t_app_family.name", "STRING"));

    PrimaryKeys.push_back("t_app_family.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_app_family.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_app_family.id");
    NotNullKeys.push_back("t_app_family.name");


    SchemaOrder.push_back("t_app_family");
}

string* T_App_Familyrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Applicationrow::T_Applicationrow(){
    this->rowMap.set("t_applicationrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Applicationrow::setValue(string key, void* value) {
    if( key.compare("t_application.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_application.executable") == 0) {
         this->executable = *((string*) value) ;
    }
    if( key.compare("t_application.app_version") == 0) {
         this->app_version = *((string*) value) ;
    }
    if( key.compare("t_application.app_family") == 0) {
         this->app_family = *((int*) value) ;
    }
}

void* T_Applicationrow::getValue(string key) {
   if( key.compare("t_application.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_application.executable") == 0) {
       return (&this->executable.getValue());
    }
   if( key.compare("t_application.app_version") == 0) {
       return (&this->app_version.getValue());
    }
   if( key.compare("t_application.app_family") == 0) {
       return (&this->app_family.getValue());
    }
}

T_Applicationrow_DB_BINDING::T_Applicationrow_DB_BINDING() {
    TableName = "t_application";

    Schema.insert(Entry("t_application.id", "INTEGER"));
    Schema.insert(Entry("t_application.executable", "STRING"));
    Schema.insert(Entry("t_application.app_version", "STRING"));
    Schema.insert(Entry("t_application.app_family", "INTEGER"));

    PrimaryKeys.push_back("t_application.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_application.executable");
    tmplist.push_back("t_application.app_version");
    tmplist.push_back("t_application.app_family");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_application.id");
    NotNullKeys.push_back("t_application.executable");
    NotNullKeys.push_back("t_application.app_version");
    NotNullKeys.push_back("t_application.app_family");


    SchemaOrder.push_back("t_application");
}

string* T_Applicationrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_App_Configrow::T_App_Configrow(){
    this->rowMap.set("t_app_configrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_App_Configrow::setValue(string key, void* value) {
    if( key.compare("t_app_config.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_app_config.application") == 0) {
         this->application = *((int*) value) ;
    }
    if( key.compare("t_app_config.parameter_set") == 0) {
         this->parameter_set = *((string*) value) ;
    }
}

void* T_App_Configrow::getValue(string key) {
   if( key.compare("t_app_config.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_app_config.application") == 0) {
       return (&this->application.getValue());
    }
   if( key.compare("t_app_config.parameter_set") == 0) {
       return (&this->parameter_set.getValue());
    }
}

T_App_Configrow_DB_BINDING::T_App_Configrow_DB_BINDING() {
    TableName = "t_app_config";

    Schema.insert(Entry("t_app_config.id", "INTEGER"));
    Schema.insert(Entry("t_app_config.application", "INTEGER"));
    Schema.insert(Entry("t_app_config.parameter_set", "STRING"));

    PrimaryKeys.push_back("t_app_config.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_app_config.application");
    tmplist.push_back("t_app_config.parameter_set");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_app_config.id");
    NotNullKeys.push_back("t_app_config.application");
    NotNullKeys.push_back("t_app_config.parameter_set");


    SchemaOrder.push_back("t_app_config");
}

string* T_App_Configrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Data_Tierrow::T_Data_Tierrow(){
    this->rowMap.set("t_data_tierrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Data_Tierrow::setValue(string key, void* value) {
    if( key.compare("t_data_tier.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_data_tier.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Data_Tierrow::getValue(string key) {
   if( key.compare("t_data_tier.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_data_tier.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Data_Tierrow_DB_BINDING::T_Data_Tierrow_DB_BINDING() {
    TableName = "t_data_tier";

    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));

    PrimaryKeys.push_back("t_data_tier.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_data_tier.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_data_tier.id");
    NotNullKeys.push_back("t_data_tier.name");


    SchemaOrder.push_back("t_data_tier");
}

string* T_Data_Tierrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Primary_Datasetrow::T_Primary_Datasetrow(){
    this->rowMap.set("t_primary_datasetrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Primary_Datasetrow::setValue(string key, void* value) {
    if( key.compare("t_primary_dataset.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Primary_Datasetrow::getValue(string key) {
   if( key.compare("t_primary_dataset.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_primary_dataset.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Primary_Datasetrow_DB_BINDING::T_Primary_Datasetrow_DB_BINDING() {
    TableName = "t_primary_dataset";

    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));

    PrimaryKeys.push_back("t_primary_dataset.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_primary_dataset.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");


    SchemaOrder.push_back("t_primary_dataset");
}

string* T_Primary_Datasetrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Processing_Pathrow::T_Processing_Pathrow(){
    this->rowMap.set("t_processing_pathrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Processing_Pathrow::setValue(string key, void* value) {
    if( key.compare("t_processing_path.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_processing_path.parent") == 0) {
         this->parent = *((int*) value) ;
    }
    if( key.compare("t_processing_path.app_config") == 0) {
         this->app_config = *((int*) value) ;
    }
    if( key.compare("t_processing_path.data_tier") == 0) {
         this->data_tier = *((int*) value) ;
    }
}

void* T_Processing_Pathrow::getValue(string key) {
   if( key.compare("t_processing_path.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_processing_path.parent") == 0) {
       return (&this->parent.getValue());
    }
   if( key.compare("t_processing_path.app_config") == 0) {
       return (&this->app_config.getValue());
    }
   if( key.compare("t_processing_path.data_tier") == 0) {
       return (&this->data_tier.getValue());
    }
}

T_Processing_Pathrow_DB_BINDING::T_Processing_Pathrow_DB_BINDING() {
    TableName = "t_processing_path";

    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));

    PrimaryKeys.push_back("t_processing_path.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_processing_path.parent");
    tmplist.push_back("t_processing_path.app_config");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_processing_path.id");
    NotNullKeys.push_back("t_processing_path.app_config");
    NotNullKeys.push_back("t_processing_path.data_tier");


    SchemaOrder.push_back("t_processing_path");
}

string* T_Processing_Pathrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Processed_Datasetrow::T_Processed_Datasetrow(){
    this->rowMap.set("t_processed_datasetrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Processed_Datasetrow::setValue(string key, void* value) {
    if( key.compare("t_processed_dataset.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_processed_dataset.primary_dataset") == 0) {
         this->primary_dataset = *((int*) value) ;
    }
    if( key.compare("t_processed_dataset.processing_path") == 0) {
         this->processing_path = *((int*) value) ;
    }
    if( key.compare("t_processed_dataset.name") == 0) {
         this->name = *((string*) value) ;
    }
    if( key.compare("t_processed_dataset.is_open") == 0) {
         this->is_open = *((char*) value) ;
    }
}

void* T_Processed_Datasetrow::getValue(string key) {
   if( key.compare("t_processed_dataset.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_processed_dataset.primary_dataset") == 0) {
       return (&this->primary_dataset.getValue());
    }
   if( key.compare("t_processed_dataset.processing_path") == 0) {
       return (&this->processing_path.getValue());
    }
   if( key.compare("t_processed_dataset.name") == 0) {
       return (&this->name.getValue());
    }
   if( key.compare("t_processed_dataset.is_open") == 0) {
       return (&this->is_open.getValue());
    }
}

T_Processed_Datasetrow_DB_BINDING::T_Processed_Datasetrow_DB_BINDING() {
    TableName = "t_processed_dataset";

    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));

    PrimaryKeys.push_back("t_processed_dataset.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_processed_dataset.primary_dataset");
    tmplist.push_back("t_processed_dataset.processing_path");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");


    SchemaOrder.push_back("t_processed_dataset");
}

string* T_Processed_Datasetrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Event_Collectionrow::T_Event_Collectionrow(){
    this->rowMap.set("t_event_collectionrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Event_Collectionrow::setValue(string key, void* value) {
    if( key.compare("t_event_collection.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_event_collection.processed_dataset") == 0) {
         this->processed_dataset = *((int*) value) ;
    }
    if( key.compare("t_event_collection.collection_index") == 0) {
         this->collection_index = *((int*) value) ;
    }
}

void* T_Event_Collectionrow::getValue(string key) {
   if( key.compare("t_event_collection.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_event_collection.processed_dataset") == 0) {
       return (&this->processed_dataset.getValue());
    }
   if( key.compare("t_event_collection.collection_index") == 0) {
       return (&this->collection_index.getValue());
    }
}

T_Event_Collectionrow_DB_BINDING::T_Event_Collectionrow_DB_BINDING() {
    TableName = "t_event_collection";

    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));

    PrimaryKeys.push_back("t_event_collection.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_event_collection.processed_dataset");
    tmplist.push_back("t_event_collection.collection_index");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_event_collection.id");
    NotNullKeys.push_back("t_event_collection.processed_dataset");
    NotNullKeys.push_back("t_event_collection.collection_index");


    SchemaOrder.push_back("t_event_collection");
}

string* T_Event_Collectionrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Parentage_Typerow::T_Parentage_Typerow(){
    this->rowMap.set("t_parentage_typerow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Parentage_Typerow::setValue(string key, void* value) {
    if( key.compare("t_parentage_type.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_parentage_type.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Parentage_Typerow::getValue(string key) {
   if( key.compare("t_parentage_type.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_parentage_type.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Parentage_Typerow_DB_BINDING::T_Parentage_Typerow_DB_BINDING() {
    TableName = "t_parentage_type";

    Schema.insert(Entry("t_parentage_type.id", "INTEGER"));
    Schema.insert(Entry("t_parentage_type.name", "STRING"));

    PrimaryKeys.push_back("t_parentage_type.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_parentage_type.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_parentage_type.id");
    NotNullKeys.push_back("t_parentage_type.name");


    SchemaOrder.push_back("t_parentage_type");
}

string* T_Parentage_Typerow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Evcoll_Parentagerow::T_Evcoll_Parentagerow(){
    this->rowMap.set("t_evcoll_parentagerow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Evcoll_Parentagerow::setValue(string key, void* value) {
    if( key.compare("t_evcoll_parentage.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_evcoll_parentage.parent") == 0) {
         this->parent = *((int*) value) ;
    }
    if( key.compare("t_evcoll_parentage.child") == 0) {
         this->child = *((int*) value) ;
    }
    if( key.compare("t_evcoll_parentage.type") == 0) {
         this->type = *((int*) value) ;
    }
}

void* T_Evcoll_Parentagerow::getValue(string key) {
   if( key.compare("t_evcoll_parentage.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_evcoll_parentage.parent") == 0) {
       return (&this->parent.getValue());
    }
   if( key.compare("t_evcoll_parentage.child") == 0) {
       return (&this->child.getValue());
    }
   if( key.compare("t_evcoll_parentage.type") == 0) {
       return (&this->type.getValue());
    }
}

T_Evcoll_Parentagerow_DB_BINDING::T_Evcoll_Parentagerow_DB_BINDING() {
    TableName = "t_evcoll_parentage";

    Schema.insert(Entry("t_evcoll_parentage.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.parent", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.child", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.type", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_parentage.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_evcoll_parentage.parent");
    tmplist.push_back("t_evcoll_parentage.child");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_evcoll_parentage.id");
    NotNullKeys.push_back("t_evcoll_parentage.child");
    NotNullKeys.push_back("t_evcoll_parentage.type");


    SchemaOrder.push_back("t_evcoll_parentage");
}

string* T_Evcoll_Parentagerow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Block_Statusrow::T_Block_Statusrow(){
    this->rowMap.set("t_block_statusrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Block_Statusrow::setValue(string key, void* value) {
    if( key.compare("t_block_status.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_block_status.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Block_Statusrow::getValue(string key) {
   if( key.compare("t_block_status.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_block_status.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Block_Statusrow_DB_BINDING::T_Block_Statusrow_DB_BINDING() {
    TableName = "t_block_status";

    Schema.insert(Entry("t_block_status.id", "INTEGER"));
    Schema.insert(Entry("t_block_status.name", "STRING"));

    PrimaryKeys.push_back("t_block_status.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_block_status.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_block_status.id");
    NotNullKeys.push_back("t_block_status.name");


    SchemaOrder.push_back("t_block_status");
}

string* T_Block_Statusrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Blockrow::T_Blockrow(){
    this->rowMap.set("t_blockrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Blockrow::setValue(string key, void* value) {
    if( key.compare("t_block.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_block.processed_dataset") == 0) {
         this->processed_dataset = *((int*) value) ;
    }
    if( key.compare("t_block.status") == 0) {
         this->status = *((int*) value) ;
    }
    if( key.compare("t_block.files") == 0) {
         this->files = *((int*) value) ;
    }
    if( key.compare("t_block.bytes") == 0) {
         this->bytes = *((int*) value) ;
    }
}

void* T_Blockrow::getValue(string key) {
   if( key.compare("t_block.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_block.processed_dataset") == 0) {
       return (&this->processed_dataset.getValue());
    }
   if( key.compare("t_block.status") == 0) {
       return (&this->status.getValue());
    }
   if( key.compare("t_block.files") == 0) {
       return (&this->files.getValue());
    }
   if( key.compare("t_block.bytes") == 0) {
       return (&this->bytes.getValue());
    }
}

T_Blockrow_DB_BINDING::T_Blockrow_DB_BINDING() {
    TableName = "t_block";

    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));

    PrimaryKeys.push_back("t_block.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;

    NotNullKeys.push_back("t_block.id");
    NotNullKeys.push_back("t_block.processed_dataset");
    NotNullKeys.push_back("t_block.status");
    NotNullKeys.push_back("t_block.files");
    NotNullKeys.push_back("t_block.bytes");


    SchemaOrder.push_back("t_block");
}

string* T_Blockrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_File_Statusrow::T_File_Statusrow(){
    this->rowMap.set("t_file_statusrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_File_Statusrow::setValue(string key, void* value) {
    if( key.compare("t_file_status.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_file_status.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_File_Statusrow::getValue(string key) {
   if( key.compare("t_file_status.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_file_status.name") == 0) {
       return (&this->name.getValue());
    }
}

T_File_Statusrow_DB_BINDING::T_File_Statusrow_DB_BINDING() {
    TableName = "t_file_status";

    Schema.insert(Entry("t_file_status.id", "INTEGER"));
    Schema.insert(Entry("t_file_status.name", "STRING"));

    PrimaryKeys.push_back("t_file_status.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_file_status.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_file_status.id");
    NotNullKeys.push_back("t_file_status.name");


    SchemaOrder.push_back("t_file_status");
}

string* T_File_Statusrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_File_Typerow::T_File_Typerow(){
    this->rowMap.set("t_file_typerow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_File_Typerow::setValue(string key, void* value) {
    if( key.compare("t_file_type.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_file_type.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_File_Typerow::getValue(string key) {
   if( key.compare("t_file_type.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_file_type.name") == 0) {
       return (&this->name.getValue());
    }
}

T_File_Typerow_DB_BINDING::T_File_Typerow_DB_BINDING() {
    TableName = "t_file_type";

    Schema.insert(Entry("t_file_type.id", "INTEGER"));
    Schema.insert(Entry("t_file_type.name", "STRING"));

    PrimaryKeys.push_back("t_file_type.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_file_type.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_file_type.id");
    NotNullKeys.push_back("t_file_type.name");


    SchemaOrder.push_back("t_file_type");
}

string* T_File_Typerow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Filerow::T_Filerow(){
    this->rowMap.set("t_filerow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Filerow::setValue(string key, void* value) {
    if( key.compare("t_file.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_file.guid") == 0) {
         this->guid = *((string*) value) ;
    }
    if( key.compare("t_file.logical_name") == 0) {
         this->logical_name = *((string*) value) ;
    }
    if( key.compare("t_file.filesize") == 0) {
         this->filesize = *((int*) value) ;
    }
    if( key.compare("t_file.status") == 0) {
         this->status = *((int*) value) ;
    }
    if( key.compare("t_file.type") == 0) {
         this->type = *((int*) value) ;
    }
    if( key.compare("t_file.inblock") == 0) {
         this->inblock = *((int*) value) ;
    }
}

void* T_Filerow::getValue(string key) {
   if( key.compare("t_file.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_file.guid") == 0) {
       return (&this->guid.getValue());
    }
   if( key.compare("t_file.logical_name") == 0) {
       return (&this->logical_name.getValue());
    }
   if( key.compare("t_file.filesize") == 0) {
       return (&this->filesize.getValue());
    }
   if( key.compare("t_file.status") == 0) {
       return (&this->status.getValue());
    }
   if( key.compare("t_file.type") == 0) {
       return (&this->type.getValue());
    }
   if( key.compare("t_file.inblock") == 0) {
       return (&this->inblock.getValue());
    }
}

T_Filerow_DB_BINDING::T_Filerow_DB_BINDING() {
    TableName = "t_file";

    Schema.insert(Entry("t_file.id", "INTEGER"));
    Schema.insert(Entry("t_file.guid", "STRING"));
    Schema.insert(Entry("t_file.logical_name", "STRING"));
    Schema.insert(Entry("t_file.filesize", "INTEGER"));
    Schema.insert(Entry("t_file.status", "INTEGER"));
    Schema.insert(Entry("t_file.type", "INTEGER"));
    Schema.insert(Entry("t_file.inblock", "INTEGER"));

    PrimaryKeys.push_back("t_file.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_file.logical_name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_file.id");
    NotNullKeys.push_back("t_file.logical_name");
    NotNullKeys.push_back("t_file.type");
    NotNullKeys.push_back("t_file.inblock");


    SchemaOrder.push_back("t_file");
}

string* T_Filerow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Evcoll_Filerow::T_Evcoll_Filerow(){
    this->rowMap.set("t_evcoll_filerow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Evcoll_Filerow::setValue(string key, void* value) {
    if( key.compare("t_evcoll_file.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_evcoll_file.evcoll") == 0) {
         this->evcoll = *((int*) value) ;
    }
    if( key.compare("t_evcoll_file.fileid") == 0) {
         this->fileid = *((int*) value) ;
    }
}

void* T_Evcoll_Filerow::getValue(string key) {
   if( key.compare("t_evcoll_file.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_evcoll_file.evcoll") == 0) {
       return (&this->evcoll.getValue());
    }
   if( key.compare("t_evcoll_file.fileid") == 0) {
       return (&this->fileid.getValue());
    }
}

T_Evcoll_Filerow_DB_BINDING::T_Evcoll_Filerow_DB_BINDING() {
    TableName = "t_evcoll_file";

    Schema.insert(Entry("t_evcoll_file.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.evcoll", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.fileid", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_file.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_evcoll_file.evcoll");
    tmplist.push_back("t_evcoll_file.fileid");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_evcoll_file.id");
    NotNullKeys.push_back("t_evcoll_file.evcoll");
    NotNullKeys.push_back("t_evcoll_file.fileid");


    SchemaOrder.push_back("t_evcoll_file");
}

string* T_Evcoll_Filerow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Info_Evcollrow::T_Info_Evcollrow(){
    this->rowMap.set("t_info_evcollrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Info_Evcollrow::setValue(string key, void* value) {
    if( key.compare("t_info_evcoll.event_collection") == 0) {
         this->event_collection = *((int*) value) ;
    }
    if( key.compare("t_info_evcoll.events") == 0) {
         this->events = *((int*) value) ;
    }
    if( key.compare("t_info_evcoll.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Info_Evcollrow::getValue(string key) {
   if( key.compare("t_info_evcoll.event_collection") == 0) {
       return (&this->event_collection.getValue());
    }
   if( key.compare("t_info_evcoll.events") == 0) {
       return (&this->events.getValue());
    }
   if( key.compare("t_info_evcoll.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Info_Evcollrow_DB_BINDING::T_Info_Evcollrow_DB_BINDING() {
    TableName = "t_info_evcoll";

    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));

    PrimaryKeys.push_back("t_info_evcoll.event_collection");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;

    NotNullKeys.push_back("t_info_evcoll.event_collection");
    NotNullKeys.push_back("t_info_evcoll.events");
    NotNullKeys.push_back("t_info_evcoll.name");


    SchemaOrder.push_back("t_info_evcoll");
}

string* T_Info_Evcollrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Evcollviewmultirow::setValue(string key, void* value) {
    if( key.compare("t_event_collection.processed_dataset") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.processed_dataset", value);
    }
    if( key.compare("t_event_collection.collection_index") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.collection_index", value);
    }
    if( key.compare("t_info_evcoll.events") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.events", value);
    }
    if( key.compare("t_info_evcoll.name") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.name", value);
    }
    if( key.compare("t_parentage_type.name") == 0) {
         ((T_Parentage_Typerow*)this->
          getConstituentRow((string)"t_parentage_typerow", ""))->
           setValue((string)"t_parentage_type.name", value);
    }
    if( key.compare("t_evcoll_parentage.id") == 0) {
         ((T_Evcoll_Parentagerow*)this->
          getConstituentRow((string)"t_evcoll_parentagerow", ""))->
           setValue((string)"t_evcoll_parentage.id", value);
    }
    if( key.compare("t_evcoll_parentage.parent") == 0) {
         ((T_Evcoll_Parentagerow*)this->
          getConstituentRow((string)"t_evcoll_parentagerow", ""))->
           setValue((string)"t_evcoll_parentage.parent", value);
    }
    if( key.compare("t_evcoll_parentage.child") == 0) {
         ((T_Evcoll_Parentagerow*)this->
          getConstituentRow((string)"t_evcoll_parentagerow", ""))->
           setValue((string)"t_evcoll_parentage.child", value);
    }
    if( key.compare("t_info_evcoll.event_collection") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.event_collection", value);
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.id", value);
    }
    if( key.compare("t_event_collection.id") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.event_collection", value);
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.id", value);
    }
    if( key.compare("t_evcoll_parentage.type") == 0) {
         ((T_Evcoll_Parentagerow*)this->
          getConstituentRow((string)"t_evcoll_parentagerow", ""))->
           setValue((string)"t_evcoll_parentage.type", value);
         ((T_Parentage_Typerow*)this->
          getConstituentRow((string)"t_parentage_typerow", ""))->
           setValue((string)"t_parentage_type.id", value);
    }
    if( key.compare("t_parentage_type.id") == 0) {
         ((T_Evcoll_Parentagerow*)this->
          getConstituentRow((string)"t_evcoll_parentagerow", ""))->
           setValue((string)"t_evcoll_parentage.type", value);
         ((T_Parentage_Typerow*)this->
          getConstituentRow((string)"t_parentage_typerow", ""))->
           setValue((string)"t_parentage_type.id", value);
    }
}

void* Evcollviewmultirow::getValue(string key) {
   if( key.compare("t_event_collection.id") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.id");
    }
   if( key.compare("t_event_collection.processed_dataset") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.processed_dataset");
    }
   if( key.compare("t_event_collection.collection_index") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.collection_index");
    }
   if( key.compare("t_info_evcoll.event_collection") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.event_collection");
    }
   if( key.compare("t_info_evcoll.events") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.events");
    }
   if( key.compare("t_info_evcoll.name") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.name");
    }
   if( key.compare("t_parentage_type.id") == 0) {
       return  ((T_Parentage_Typerow*)this->
          getConstituentRow("t_parentage_typerow", ""))->
           getValue((string)"t_parentage_type.id");
    }
   if( key.compare("t_parentage_type.name") == 0) {
       return  ((T_Parentage_Typerow*)this->
          getConstituentRow("t_parentage_typerow", ""))->
           getValue((string)"t_parentage_type.name");
    }
   if( key.compare("t_evcoll_parentage.id") == 0) {
       return  ((T_Evcoll_Parentagerow*)this->
          getConstituentRow("t_evcoll_parentagerow", ""))->
           getValue((string)"t_evcoll_parentage.id");
    }
   if( key.compare("t_evcoll_parentage.parent") == 0) {
       return  ((T_Evcoll_Parentagerow*)this->
          getConstituentRow("t_evcoll_parentagerow", ""))->
           getValue((string)"t_evcoll_parentage.parent");
    }
   if( key.compare("t_evcoll_parentage.child") == 0) {
       return  ((T_Evcoll_Parentagerow*)this->
          getConstituentRow("t_evcoll_parentagerow", ""))->
           getValue((string)"t_evcoll_parentage.child");
    }
   if( key.compare("t_evcoll_parentage.type") == 0) {
       return  ((T_Evcoll_Parentagerow*)this->
          getConstituentRow("t_evcoll_parentagerow", ""))->
           getValue((string)"t_evcoll_parentage.type");
    }
}

Evcollviewmultirow::Evcollviewmultirow(){
    this->T_Event_Collectionobj = new T_Event_Collectionrow();
    this->rowMap.set("t_event_collectionrow", (void*)this->T_Event_Collectionobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Info_Evcollobj = new T_Info_Evcollrow();
    this->rowMap.set("t_info_evcollrow", (void*)this->T_Info_Evcollobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Parentage_Typeobj = new T_Parentage_Typerow();
    this->rowMap.set("t_parentage_typerow", (void*)this->T_Parentage_Typeobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Evcoll_Parentageobj = new T_Evcoll_Parentagerow();
    this->rowMap.set("t_evcoll_parentagerow", (void*)this->T_Evcoll_Parentageobj);
    this->constituentObjects.push_back(this->rowMap);
}

Evcollviewmultirow::~Evcollviewmultirow(){
   delete this->T_Event_Collectionobj;
   delete this->T_Info_Evcollobj;
   delete this->T_Parentage_Typeobj;
   delete this->T_Evcoll_Parentageobj;
}

Evcollviewmultirow_DB_BINDING::Evcollviewmultirow_DB_BINDING() {
    TableName = "EvCollView";

    Schema.insert(Entry("t_evcoll_parentage.id", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));
    Schema.insert(Entry("t_parentage_type.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.child", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.type", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.parent", "INTEGER"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_parentage_type.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));

    PrimaryKeys.push_back("t_info_evcoll.event_collection");
    PrimaryKeys.push_back("t_evcoll_parentage.id");

    ForeignKeys.push_back("t_info_evcoll.event_collection");
    ForeignKeys.push_back("t_evcoll_parentage.parent");
    ForeignKeys.push_back("t_evcoll_parentage.child");
    ForeignKeys.push_back("t_evcoll_parentage.type");
    ForeignKeys.push_back("t_event_collection.processed_dataset");

    list<string> tmplist;
    tmplist.push_back("t_info_evcoll.event_collection");
    tmplist.push_back("t_evcoll_parentage.parent");
    tmplist.push_back("t_evcoll_parentage.child");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_info_evcoll.event_collection");
    tmplist.push_back("t_evcoll_parentage.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_event_collection.id");
    NotNullKeys.push_back("t_event_collection.processed_dataset");
    NotNullKeys.push_back("t_event_collection.collection_index");
    NotNullKeys.push_back("t_info_evcoll.event_collection");
    NotNullKeys.push_back("t_info_evcoll.events");
    NotNullKeys.push_back("t_info_evcoll.name");
    NotNullKeys.push_back("t_parentage_type.id");
    NotNullKeys.push_back("t_parentage_type.name");
    NotNullKeys.push_back("t_evcoll_parentage.id");
    NotNullKeys.push_back("t_evcoll_parentage.child");
    NotNullKeys.push_back("t_evcoll_parentage.type");

    SchemaOrder.push_back("t_event_collection");
    SchemaOrder.push_back("t_info_evcoll");
    SchemaOrder.push_back("t_parentage_type");
    SchemaOrder.push_back("t_evcoll_parentage");

    References.insert(Entry("t_evcoll_parentage.child", "t_event_collection.id"));
    References.insert(Entry("t_info_evcoll.event_collection", "t_event_collection.id"));
    References.insert(Entry("t_evcoll_parentage.type", "t_parentage_type.id"));


    ExternalReferences.insert(Entry("t_event_collection.processed_dataset", "t_processed_dataset.id"));
}

string* Evcollviewmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Fileviewmultirow::setValue(string key, void* value) {
    if( key.compare("t_file_type.name") == 0) {
         ((T_File_Typerow*)this->
          getConstituentRow((string)"t_file_typerow", ""))->
           setValue((string)"t_file_type.name", value);
    }
    if( key.compare("t_file_status.name") == 0) {
         ((T_File_Statusrow*)this->
          getConstituentRow((string)"t_file_statusrow", ""))->
           setValue((string)"t_file_status.name", value);
    }
    if( key.compare("t_file.guid") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.guid", value);
    }
    if( key.compare("t_file.logical_name") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.logical_name", value);
    }
    if( key.compare("t_file.filesize") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.filesize", value);
    }
    if( key.compare("t_file.inblock") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.inblock", value);
    }
    if( key.compare("t_evcoll_file.id") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.id", value);
    }
    if( key.compare("t_evcoll_file.evcoll") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.evcoll", value);
    }
    if( key.compare("t_evcoll_file.fileid") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.fileid", value);
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.id", value);
    }
    if( key.compare("t_file.id") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.fileid", value);
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.id", value);
    }
    if( key.compare("t_file.status") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.status", value);
         ((T_File_Statusrow*)this->
          getConstituentRow((string)"t_file_statusrow", ""))->
           setValue((string)"t_file_status.id", value);
    }
    if( key.compare("t_file_status.id") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.status", value);
         ((T_File_Statusrow*)this->
          getConstituentRow((string)"t_file_statusrow", ""))->
           setValue((string)"t_file_status.id", value);
    }
    if( key.compare("t_file.type") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.type", value);
         ((T_File_Typerow*)this->
          getConstituentRow((string)"t_file_typerow", ""))->
           setValue((string)"t_file_type.id", value);
    }
    if( key.compare("t_file_type.id") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.type", value);
         ((T_File_Typerow*)this->
          getConstituentRow((string)"t_file_typerow", ""))->
           setValue((string)"t_file_type.id", value);
    }
}

void* Fileviewmultirow::getValue(string key) {
   if( key.compare("t_file_type.id") == 0) {
       return  ((T_File_Typerow*)this->
          getConstituentRow("t_file_typerow", ""))->
           getValue((string)"t_file_type.id");
    }
   if( key.compare("t_file_type.name") == 0) {
       return  ((T_File_Typerow*)this->
          getConstituentRow("t_file_typerow", ""))->
           getValue((string)"t_file_type.name");
    }
   if( key.compare("t_file_status.id") == 0) {
       return  ((T_File_Statusrow*)this->
          getConstituentRow("t_file_statusrow", ""))->
           getValue((string)"t_file_status.id");
    }
   if( key.compare("t_file_status.name") == 0) {
       return  ((T_File_Statusrow*)this->
          getConstituentRow("t_file_statusrow", ""))->
           getValue((string)"t_file_status.name");
    }
   if( key.compare("t_file.id") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.id");
    }
   if( key.compare("t_file.guid") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.guid");
    }
   if( key.compare("t_file.logical_name") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.logical_name");
    }
   if( key.compare("t_file.filesize") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.filesize");
    }
   if( key.compare("t_file.status") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.status");
    }
   if( key.compare("t_file.type") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.type");
    }
   if( key.compare("t_file.inblock") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.inblock");
    }
   if( key.compare("t_evcoll_file.id") == 0) {
       return  ((T_Evcoll_Filerow*)this->
          getConstituentRow("t_evcoll_filerow", ""))->
           getValue((string)"t_evcoll_file.id");
    }
   if( key.compare("t_evcoll_file.evcoll") == 0) {
       return  ((T_Evcoll_Filerow*)this->
          getConstituentRow("t_evcoll_filerow", ""))->
           getValue((string)"t_evcoll_file.evcoll");
    }
   if( key.compare("t_evcoll_file.fileid") == 0) {
       return  ((T_Evcoll_Filerow*)this->
          getConstituentRow("t_evcoll_filerow", ""))->
           getValue((string)"t_evcoll_file.fileid");
    }
}

Fileviewmultirow::Fileviewmultirow(){
    this->T_File_Typeobj = new T_File_Typerow();
    this->rowMap.set("t_file_typerow", (void*)this->T_File_Typeobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_File_Statusobj = new T_File_Statusrow();
    this->rowMap.set("t_file_statusrow", (void*)this->T_File_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Fileobj = new T_Filerow();
    this->rowMap.set("t_filerow", (void*)this->T_Fileobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Evcoll_Fileobj = new T_Evcoll_Filerow();
    this->rowMap.set("t_evcoll_filerow", (void*)this->T_Evcoll_Fileobj);
    this->constituentObjects.push_back(this->rowMap);
}

Fileviewmultirow::~Fileviewmultirow(){
   delete this->T_File_Typeobj;
   delete this->T_File_Statusobj;
   delete this->T_Fileobj;
   delete this->T_Evcoll_Fileobj;
}

Fileviewmultirow_DB_BINDING::Fileviewmultirow_DB_BINDING() {
    TableName = "FileView";

    Schema.insert(Entry("t_evcoll_file.fileid", "INTEGER"));
    Schema.insert(Entry("t_file.type", "INTEGER"));
    Schema.insert(Entry("t_file.inblock", "INTEGER"));
    Schema.insert(Entry("t_file.logical_name", "STRING"));
    Schema.insert(Entry("t_file_status.name", "STRING"));
    Schema.insert(Entry("t_file.guid", "STRING"));
    Schema.insert(Entry("t_file_type.name", "STRING"));
    Schema.insert(Entry("t_file_type.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.evcoll", "INTEGER"));
    Schema.insert(Entry("t_file.id", "INTEGER"));
    Schema.insert(Entry("t_file_status.id", "INTEGER"));
    Schema.insert(Entry("t_file.filesize", "INTEGER"));
    Schema.insert(Entry("t_file.status", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.id", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_file.id");

    ForeignKeys.push_back("t_evcoll_file.fileid");
    ForeignKeys.push_back("t_file.status");
    ForeignKeys.push_back("t_file.type");
    ForeignKeys.push_back("t_evcoll_file.evcoll");
    ForeignKeys.push_back("t_file.inblock");

    list<string> tmplist;
    tmplist.push_back("t_evcoll_file.evcoll");
    tmplist.push_back("t_evcoll_file.fileid");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_evcoll_file.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_file_type.id");
    NotNullKeys.push_back("t_file_type.name");
    NotNullKeys.push_back("t_file_status.id");
    NotNullKeys.push_back("t_file_status.name");
    NotNullKeys.push_back("t_file.id");
    NotNullKeys.push_back("t_file.logical_name");
    NotNullKeys.push_back("t_file.type");
    NotNullKeys.push_back("t_file.inblock");
    NotNullKeys.push_back("t_evcoll_file.id");
    NotNullKeys.push_back("t_evcoll_file.evcoll");
    NotNullKeys.push_back("t_evcoll_file.fileid");

    SchemaOrder.push_back("t_file_type");
    SchemaOrder.push_back("t_file_status");
    SchemaOrder.push_back("t_file");
    SchemaOrder.push_back("t_evcoll_file");

    References.insert(Entry("t_evcoll_file.fileid", "t_file.id"));
    References.insert(Entry("t_file.status", "t_file_status.id"));
    References.insert(Entry("t_file.type", "t_file_type.id"));


    ExternalReferences.insert(Entry("t_file.inblock", "t_block.id"));
}

string* Fileviewmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Pdblockviewmultirow::setValue(string key, void* value) {
    if( key.compare("t_block_status.name") == 0) {
         ((T_Block_Statusrow*)this->
          getConstituentRow((string)"t_block_statusrow", ""))->
           setValue((string)"t_block_status.name", value);
    }
    if( key.compare("t_processed_dataset.primary_dataset") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.primary_dataset", value);
    }
    if( key.compare("t_processed_dataset.processing_path") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.processing_path", value);
    }
    if( key.compare("t_processed_dataset.name") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.name", value);
    }
    if( key.compare("t_processed_dataset.is_open") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.is_open", value);
    }
    if( key.compare("t_block.id") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.id", value);
    }
    if( key.compare("t_block.files") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.files", value);
    }
    if( key.compare("t_block.bytes") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.bytes", value);
    }
    if( key.compare("t_block.status") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.status", value);
         ((T_Block_Statusrow*)this->
          getConstituentRow((string)"t_block_statusrow", ""))->
           setValue((string)"t_block_status.id", value);
    }
    if( key.compare("t_block_status.id") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.status", value);
         ((T_Block_Statusrow*)this->
          getConstituentRow((string)"t_block_statusrow", ""))->
           setValue((string)"t_block_status.id", value);
    }
    if( key.compare("t_block.processed_dataset") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.processed_dataset", value);
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.id", value);
    }
    if( key.compare("t_processed_dataset.id") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.processed_dataset", value);
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.id", value);
    }
}

void* Pdblockviewmultirow::getValue(string key) {
   if( key.compare("t_block_status.id") == 0) {
       return  ((T_Block_Statusrow*)this->
          getConstituentRow("t_block_statusrow", ""))->
           getValue((string)"t_block_status.id");
    }
   if( key.compare("t_block_status.name") == 0) {
       return  ((T_Block_Statusrow*)this->
          getConstituentRow("t_block_statusrow", ""))->
           getValue((string)"t_block_status.name");
    }
   if( key.compare("t_processed_dataset.id") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.id");
    }
   if( key.compare("t_processed_dataset.primary_dataset") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.primary_dataset");
    }
   if( key.compare("t_processed_dataset.processing_path") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.processing_path");
    }
   if( key.compare("t_processed_dataset.name") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.name");
    }
   if( key.compare("t_processed_dataset.is_open") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.is_open");
    }
   if( key.compare("t_block.id") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.id");
    }
   if( key.compare("t_block.processed_dataset") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.processed_dataset");
    }
   if( key.compare("t_block.status") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.status");
    }
   if( key.compare("t_block.files") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.files");
    }
   if( key.compare("t_block.bytes") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.bytes");
    }
}

Pdblockviewmultirow::Pdblockviewmultirow(){
    this->T_Block_Statusobj = new T_Block_Statusrow();
    this->rowMap.set("t_block_statusrow", (void*)this->T_Block_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Processed_Datasetobj = new T_Processed_Datasetrow();
    this->rowMap.set("t_processed_datasetrow", (void*)this->T_Processed_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Blockobj = new T_Blockrow();
    this->rowMap.set("t_blockrow", (void*)this->T_Blockobj);
    this->constituentObjects.push_back(this->rowMap);
}

Pdblockviewmultirow::~Pdblockviewmultirow(){
   delete this->T_Block_Statusobj;
   delete this->T_Processed_Datasetobj;
   delete this->T_Blockobj;
}

Pdblockviewmultirow_DB_BINDING::Pdblockviewmultirow_DB_BINDING() {
    TableName = "PDBlockView";

    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_block_status.name", "STRING"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_block_status.id", "INTEGER"));

    PrimaryKeys.push_back("t_block.id");

    ForeignKeys.push_back("t_block.status");
    ForeignKeys.push_back("t_processed_dataset.processing_path");
    ForeignKeys.push_back("t_block.processed_dataset");
    ForeignKeys.push_back("t_processed_dataset.primary_dataset");

    list<string> tmplist;
    tmplist.push_back("t_block.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_block_status.id");
    NotNullKeys.push_back("t_block_status.name");
    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");
    NotNullKeys.push_back("t_block.id");
    NotNullKeys.push_back("t_block.processed_dataset");
    NotNullKeys.push_back("t_block.status");
    NotNullKeys.push_back("t_block.files");
    NotNullKeys.push_back("t_block.bytes");

    SchemaOrder.push_back("t_block_status");
    SchemaOrder.push_back("t_processed_dataset");
    SchemaOrder.push_back("t_block");

    References.insert(Entry("t_block.status", "t_block_status.id"));
    References.insert(Entry("t_block.processed_dataset", "t_processed_dataset.id"));


    ExternalReferences.insert(Entry("t_processed_dataset.primary_dataset", "t_primary_dataset.id"));
}

string* Pdblockviewmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Blockviewmultirow::setValue(string key, void* value) {
    if( key.compare("t_block_status.name") == 0) {
         ((T_Block_Statusrow*)this->
          getConstituentRow((string)"t_block_statusrow", ""))->
           setValue((string)"t_block_status.name", value);
    }
    if( key.compare("t_block.id") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.id", value);
    }
    if( key.compare("t_block.processed_dataset") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.processed_dataset", value);
    }
    if( key.compare("t_block.files") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.files", value);
    }
    if( key.compare("t_block.bytes") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.bytes", value);
    }
    if( key.compare("t_block.status") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.status", value);
         ((T_Block_Statusrow*)this->
          getConstituentRow((string)"t_block_statusrow", ""))->
           setValue((string)"t_block_status.id", value);
    }
    if( key.compare("t_block_status.id") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.status", value);
         ((T_Block_Statusrow*)this->
          getConstituentRow((string)"t_block_statusrow", ""))->
           setValue((string)"t_block_status.id", value);
    }
}

void* Blockviewmultirow::getValue(string key) {
   if( key.compare("t_block_status.id") == 0) {
       return  ((T_Block_Statusrow*)this->
          getConstituentRow("t_block_statusrow", ""))->
           getValue((string)"t_block_status.id");
    }
   if( key.compare("t_block_status.name") == 0) {
       return  ((T_Block_Statusrow*)this->
          getConstituentRow("t_block_statusrow", ""))->
           getValue((string)"t_block_status.name");
    }
   if( key.compare("t_block.id") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.id");
    }
   if( key.compare("t_block.processed_dataset") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.processed_dataset");
    }
   if( key.compare("t_block.status") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.status");
    }
   if( key.compare("t_block.files") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.files");
    }
   if( key.compare("t_block.bytes") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.bytes");
    }
}

Blockviewmultirow::Blockviewmultirow(){
    this->T_Block_Statusobj = new T_Block_Statusrow();
    this->rowMap.set("t_block_statusrow", (void*)this->T_Block_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Blockobj = new T_Blockrow();
    this->rowMap.set("t_blockrow", (void*)this->T_Blockobj);
    this->constituentObjects.push_back(this->rowMap);
}

Blockviewmultirow::~Blockviewmultirow(){
   delete this->T_Block_Statusobj;
   delete this->T_Blockobj;
}

Blockviewmultirow_DB_BINDING::Blockviewmultirow_DB_BINDING() {
    TableName = "BlockView";

    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_block_status.name", "STRING"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));
    Schema.insert(Entry("t_block_status.id", "INTEGER"));

    PrimaryKeys.push_back("t_block.id");

    ForeignKeys.push_back("t_block.status");
    ForeignKeys.push_back("t_block.processed_dataset");

    list<string> tmplist;
    tmplist.push_back("t_block.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_block_status.id");
    NotNullKeys.push_back("t_block_status.name");
    NotNullKeys.push_back("t_block.id");
    NotNullKeys.push_back("t_block.processed_dataset");
    NotNullKeys.push_back("t_block.status");
    NotNullKeys.push_back("t_block.files");
    NotNullKeys.push_back("t_block.bytes");

    SchemaOrder.push_back("t_block_status");
    SchemaOrder.push_back("t_block");

    References.insert(Entry("t_block.status", "t_block_status.id"));


    ExternalReferences.insert(Entry("t_block.processed_dataset", "t_processed_dataset.id"));
}

string* Blockviewmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Primarydatasetmultirow::setValue(string key, void* value) {
    if( key.compare("t_primary_dataset.id") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.id", value);
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.name", value);
    }
}

void* Primarydatasetmultirow::getValue(string key) {
   if( key.compare("t_primary_dataset.id") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.id");
    }
   if( key.compare("t_primary_dataset.name") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.name");
    }
}

Primarydatasetmultirow::Primarydatasetmultirow(){
    this->T_Primary_Datasetobj = new T_Primary_Datasetrow();
    this->rowMap.set("t_primary_datasetrow", (void*)this->T_Primary_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
}

Primarydatasetmultirow::~Primarydatasetmultirow(){
   delete this->T_Primary_Datasetobj;
}

Primarydatasetmultirow_DB_BINDING::Primarydatasetmultirow_DB_BINDING() {
    TableName = "PrimaryDataset";

    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));

    PrimaryKeys.push_back("t_primary_dataset.id");


    list<string> tmplist;
    tmplist.push_back("t_primary_dataset.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_primary_dataset.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");

    SchemaOrder.push_back("t_primary_dataset");



}

string* Primarydatasetmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Processingpathmultirow::setValue(string key, void* value) {
    if( key.compare("t_app_family.name") == 0) {
         ((T_App_Familyrow*)this->
          getConstituentRow((string)"t_app_familyrow", ""))->
           setValue((string)"t_app_family.name", value);
    }
    if( key.compare("t_data_tier.name") == 0) {
         ((T_Data_Tierrow*)this->
          getConstituentRow((string)"t_data_tierrow", ""))->
           setValue((string)"t_data_tier.name", value);
    }
    if( key.compare("t_application.executable") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.executable", value);
    }
    if( key.compare("t_application.app_version") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.app_version", value);
    }
    if( key.compare("t_app_config.parameter_set") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.parameter_set", value);
    }
    if( key.compare("t_processing_path.parent") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.parent", value);
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.name", value);
    }
    if( key.compare("t_processed_dataset.id") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.id", value);
    }
    if( key.compare("t_processed_dataset.name") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.name", value);
    }
    if( key.compare("t_processed_dataset.is_open") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.is_open", value);
    }
    if( key.compare("t_processed_dataset.processing_path") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.processing_path", value);
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.id", value);
    }
    if( key.compare("t_processing_path.id") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.processing_path", value);
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.id", value);
    }
    if( key.compare("t_processing_path.data_tier") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.data_tier", value);
         ((T_Data_Tierrow*)this->
          getConstituentRow((string)"t_data_tierrow", ""))->
           setValue((string)"t_data_tier.id", value);
    }
    if( key.compare("t_data_tier.id") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.data_tier", value);
         ((T_Data_Tierrow*)this->
          getConstituentRow((string)"t_data_tierrow", ""))->
           setValue((string)"t_data_tier.id", value);
    }
    if( key.compare("t_processing_path.app_config") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.app_config", value);
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.id", value);
    }
    if( key.compare("t_app_config.id") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.app_config", value);
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.id", value);
    }
    if( key.compare("t_app_config.application") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.application", value);
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.id", value);
    }
    if( key.compare("t_application.id") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.application", value);
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.id", value);
    }
    if( key.compare("t_processed_dataset.primary_dataset") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.primary_dataset", value);
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.id", value);
    }
    if( key.compare("t_primary_dataset.id") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.primary_dataset", value);
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.id", value);
    }
    if( key.compare("t_application.app_family") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.app_family", value);
         ((T_App_Familyrow*)this->
          getConstituentRow((string)"t_app_familyrow", ""))->
           setValue((string)"t_app_family.id", value);
    }
    if( key.compare("t_app_family.id") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.app_family", value);
         ((T_App_Familyrow*)this->
          getConstituentRow((string)"t_app_familyrow", ""))->
           setValue((string)"t_app_family.id", value);
    }
}

void* Processingpathmultirow::getValue(string key) {
   if( key.compare("t_app_family.id") == 0) {
       return  ((T_App_Familyrow*)this->
          getConstituentRow("t_app_familyrow", ""))->
           getValue((string)"t_app_family.id");
    }
   if( key.compare("t_app_family.name") == 0) {
       return  ((T_App_Familyrow*)this->
          getConstituentRow("t_app_familyrow", ""))->
           getValue((string)"t_app_family.name");
    }
   if( key.compare("t_data_tier.id") == 0) {
       return  ((T_Data_Tierrow*)this->
          getConstituentRow("t_data_tierrow", ""))->
           getValue((string)"t_data_tier.id");
    }
   if( key.compare("t_data_tier.name") == 0) {
       return  ((T_Data_Tierrow*)this->
          getConstituentRow("t_data_tierrow", ""))->
           getValue((string)"t_data_tier.name");
    }
   if( key.compare("t_application.id") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.id");
    }
   if( key.compare("t_application.executable") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.executable");
    }
   if( key.compare("t_application.app_version") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.app_version");
    }
   if( key.compare("t_application.app_family") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.app_family");
    }
   if( key.compare("t_app_config.id") == 0) {
       return  ((T_App_Configrow*)this->
          getConstituentRow("t_app_configrow", ""))->
           getValue((string)"t_app_config.id");
    }
   if( key.compare("t_app_config.application") == 0) {
       return  ((T_App_Configrow*)this->
          getConstituentRow("t_app_configrow", ""))->
           getValue((string)"t_app_config.application");
    }
   if( key.compare("t_app_config.parameter_set") == 0) {
       return  ((T_App_Configrow*)this->
          getConstituentRow("t_app_configrow", ""))->
           getValue((string)"t_app_config.parameter_set");
    }
   if( key.compare("t_processing_path.id") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.id");
    }
   if( key.compare("t_processing_path.parent") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.parent");
    }
   if( key.compare("t_processing_path.app_config") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.app_config");
    }
   if( key.compare("t_processing_path.data_tier") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.data_tier");
    }
   if( key.compare("t_primary_dataset.id") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.id");
    }
   if( key.compare("t_primary_dataset.name") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.name");
    }
   if( key.compare("t_processed_dataset.id") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.id");
    }
   if( key.compare("t_processed_dataset.primary_dataset") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.primary_dataset");
    }
   if( key.compare("t_processed_dataset.processing_path") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.processing_path");
    }
   if( key.compare("t_processed_dataset.name") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.name");
    }
   if( key.compare("t_processed_dataset.is_open") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.is_open");
    }
}

Processingpathmultirow::Processingpathmultirow(){
    this->T_App_Familyobj = new T_App_Familyrow();
    this->rowMap.set("t_app_familyrow", (void*)this->T_App_Familyobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Data_Tierobj = new T_Data_Tierrow();
    this->rowMap.set("t_data_tierrow", (void*)this->T_Data_Tierobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Applicationobj = new T_Applicationrow();
    this->rowMap.set("t_applicationrow", (void*)this->T_Applicationobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_App_Configobj = new T_App_Configrow();
    this->rowMap.set("t_app_configrow", (void*)this->T_App_Configobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Processing_Pathobj = new T_Processing_Pathrow();
    this->rowMap.set("t_processing_pathrow", (void*)this->T_Processing_Pathobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Primary_Datasetobj = new T_Primary_Datasetrow();
    this->rowMap.set("t_primary_datasetrow", (void*)this->T_Primary_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Processed_Datasetobj = new T_Processed_Datasetrow();
    this->rowMap.set("t_processed_datasetrow", (void*)this->T_Processed_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
}

Processingpathmultirow::~Processingpathmultirow(){
   delete this->T_App_Familyobj;
   delete this->T_Data_Tierobj;
   delete this->T_Applicationobj;
   delete this->T_App_Configobj;
   delete this->T_Processing_Pathobj;
   delete this->T_Primary_Datasetobj;
   delete this->T_Processed_Datasetobj;
}

Processingpathmultirow_DB_BINDING::Processingpathmultirow_DB_BINDING() {
    TableName = "ProcessingPath";

    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_application.app_version", "STRING"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_application.id", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_app_config.id", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_application.app_family", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_app_family.name", "STRING"));
    Schema.insert(Entry("t_app_config.parameter_set", "STRING"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_app_config.application", "INTEGER"));
    Schema.insert(Entry("t_application.executable", "STRING"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_app_family.id", "INTEGER"));

    PrimaryKeys.push_back("t_processed_dataset.id");

    ForeignKeys.push_back("t_processing_path.app_config");
    ForeignKeys.push_back("t_processing_path.data_tier");
    ForeignKeys.push_back("t_processed_dataset.processing_path");
    ForeignKeys.push_back("t_app_config.application");
    ForeignKeys.push_back("t_processed_dataset.primary_dataset");
    ForeignKeys.push_back("t_application.app_family");

    list<string> tmplist;
    tmplist.push_back("t_processed_dataset.primary_dataset");
    tmplist.push_back("t_processed_dataset.processing_path");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_processed_dataset.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_app_family.id");
    NotNullKeys.push_back("t_app_family.name");
    NotNullKeys.push_back("t_data_tier.id");
    NotNullKeys.push_back("t_data_tier.name");
    NotNullKeys.push_back("t_application.id");
    NotNullKeys.push_back("t_application.executable");
    NotNullKeys.push_back("t_application.app_version");
    NotNullKeys.push_back("t_application.app_family");
    NotNullKeys.push_back("t_app_config.id");
    NotNullKeys.push_back("t_app_config.application");
    NotNullKeys.push_back("t_app_config.parameter_set");
    NotNullKeys.push_back("t_processing_path.id");
    NotNullKeys.push_back("t_processing_path.app_config");
    NotNullKeys.push_back("t_processing_path.data_tier");
    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");

    SchemaOrder.push_back("t_app_family");
    SchemaOrder.push_back("t_data_tier");
    SchemaOrder.push_back("t_application");
    SchemaOrder.push_back("t_app_config");
    SchemaOrder.push_back("t_processing_path");
    SchemaOrder.push_back("t_primary_dataset");
    SchemaOrder.push_back("t_processed_dataset");

    References.insert(Entry("t_processed_dataset.processing_path", "t_processing_path.id"));
    References.insert(Entry("t_processing_path.data_tier", "t_data_tier.id"));
    References.insert(Entry("t_processing_path.app_config", "t_app_config.id"));
    References.insert(Entry("t_app_config.application", "t_application.id"));
    References.insert(Entry("t_processed_dataset.primary_dataset", "t_primary_dataset.id"));
    References.insert(Entry("t_application.app_family", "t_app_family.id"));


}

string* Processingpathmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Crabevcollviewmultirow::setValue(string key, void* value) {
    if( key.compare("t_data_tier.name") == 0) {
         ((T_Data_Tierrow*)this->
          getConstituentRow((string)"t_data_tierrow", ""))->
           setValue((string)"t_data_tier.name", value);
    }
    if( key.compare("t_processing_path.parent") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.parent", value);
    }
    if( key.compare("t_processing_path.app_config") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.app_config", value);
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.name", value);
    }
    if( key.compare("t_processed_dataset.name") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.name", value);
    }
    if( key.compare("t_processed_dataset.is_open") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.is_open", value);
    }
    if( key.compare("t_event_collection.collection_index") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.collection_index", value);
    }
    if( key.compare("t_block.status") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.status", value);
    }
    if( key.compare("t_block.files") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.files", value);
    }
    if( key.compare("t_block.bytes") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.bytes", value);
    }
    if( key.compare("t_info_evcoll.events") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.events", value);
    }
    if( key.compare("t_info_evcoll.name") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.name", value);
    }
    if( key.compare("t_file.guid") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.guid", value);
    }
    if( key.compare("t_file.logical_name") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.logical_name", value);
    }
    if( key.compare("t_file.filesize") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.filesize", value);
    }
    if( key.compare("t_file.status") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.status", value);
    }
    if( key.compare("t_file.type") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.type", value);
    }
    if( key.compare("t_evcoll_file.id") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.id", value);
    }
    if( key.compare("t_evcoll_file.fileid") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.fileid", value);
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.id", value);
    }
    if( key.compare("t_file.id") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.fileid", value);
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.id", value);
    }
    if( key.compare("t_block.processed_dataset") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.processed_dataset", value);
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.id", value);
    }
    if( key.compare("t_processed_dataset.id") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.processed_dataset", value);
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.id", value);
    }
    if( key.compare("t_processed_dataset.primary_dataset") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.primary_dataset", value);
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.id", value);
    }
    if( key.compare("t_primary_dataset.id") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.primary_dataset", value);
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.id", value);
    }
    if( key.compare("t_processed_dataset.processing_path") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.processing_path", value);
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.id", value);
    }
    if( key.compare("t_processing_path.id") == 0) {
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.processing_path", value);
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.id", value);
    }
    if( key.compare("t_evcoll_file.evcoll") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.evcoll", value);
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.id", value);
    }
    if( key.compare("t_event_collection.id") == 0) {
         ((T_Evcoll_Filerow*)this->
          getConstituentRow((string)"t_evcoll_filerow", ""))->
           setValue((string)"t_evcoll_file.evcoll", value);
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.id", value);
    }
    if( key.compare("t_info_evcoll.event_collection") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.event_collection", value);
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.id", value);
    }
    if( key.compare("t_event_collection.id") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.event_collection", value);
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.id", value);
    }
    if( key.compare("t_processing_path.data_tier") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.data_tier", value);
         ((T_Data_Tierrow*)this->
          getConstituentRow((string)"t_data_tierrow", ""))->
           setValue((string)"t_data_tier.id", value);
    }
    if( key.compare("t_data_tier.id") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.data_tier", value);
         ((T_Data_Tierrow*)this->
          getConstituentRow((string)"t_data_tierrow", ""))->
           setValue((string)"t_data_tier.id", value);
    }
    if( key.compare("t_event_collection.processed_dataset") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.processed_dataset", value);
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.id", value);
    }
    if( key.compare("t_processed_dataset.id") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.processed_dataset", value);
         ((T_Processed_Datasetrow*)this->
          getConstituentRow((string)"t_processed_datasetrow", ""))->
           setValue((string)"t_processed_dataset.id", value);
    }
    if( key.compare("t_file.inblock") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.inblock", value);
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.id", value);
    }
    if( key.compare("t_block.id") == 0) {
         ((T_Filerow*)this->
          getConstituentRow((string)"t_filerow", ""))->
           setValue((string)"t_file.inblock", value);
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.id", value);
    }
}

void* Crabevcollviewmultirow::getValue(string key) {
   if( key.compare("t_data_tier.id") == 0) {
       return  ((T_Data_Tierrow*)this->
          getConstituentRow("t_data_tierrow", ""))->
           getValue((string)"t_data_tier.id");
    }
   if( key.compare("t_data_tier.name") == 0) {
       return  ((T_Data_Tierrow*)this->
          getConstituentRow("t_data_tierrow", ""))->
           getValue((string)"t_data_tier.name");
    }
   if( key.compare("t_processing_path.id") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.id");
    }
   if( key.compare("t_processing_path.parent") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.parent");
    }
   if( key.compare("t_processing_path.app_config") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.app_config");
    }
   if( key.compare("t_processing_path.data_tier") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.data_tier");
    }
   if( key.compare("t_primary_dataset.id") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.id");
    }
   if( key.compare("t_primary_dataset.name") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.name");
    }
   if( key.compare("t_processed_dataset.id") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.id");
    }
   if( key.compare("t_processed_dataset.primary_dataset") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.primary_dataset");
    }
   if( key.compare("t_processed_dataset.processing_path") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.processing_path");
    }
   if( key.compare("t_processed_dataset.name") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.name");
    }
   if( key.compare("t_processed_dataset.is_open") == 0) {
       return  ((T_Processed_Datasetrow*)this->
          getConstituentRow("t_processed_datasetrow", ""))->
           getValue((string)"t_processed_dataset.is_open");
    }
   if( key.compare("t_event_collection.id") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.id");
    }
   if( key.compare("t_event_collection.processed_dataset") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.processed_dataset");
    }
   if( key.compare("t_event_collection.collection_index") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.collection_index");
    }
   if( key.compare("t_block.id") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.id");
    }
   if( key.compare("t_block.processed_dataset") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.processed_dataset");
    }
   if( key.compare("t_block.status") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.status");
    }
   if( key.compare("t_block.files") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.files");
    }
   if( key.compare("t_block.bytes") == 0) {
       return  ((T_Blockrow*)this->
          getConstituentRow("t_blockrow", ""))->
           getValue((string)"t_block.bytes");
    }
   if( key.compare("t_info_evcoll.event_collection") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.event_collection");
    }
   if( key.compare("t_info_evcoll.events") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.events");
    }
   if( key.compare("t_info_evcoll.name") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.name");
    }
   if( key.compare("t_file.id") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.id");
    }
   if( key.compare("t_file.guid") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.guid");
    }
   if( key.compare("t_file.logical_name") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.logical_name");
    }
   if( key.compare("t_file.filesize") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.filesize");
    }
   if( key.compare("t_file.status") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.status");
    }
   if( key.compare("t_file.type") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.type");
    }
   if( key.compare("t_file.inblock") == 0) {
       return  ((T_Filerow*)this->
          getConstituentRow("t_filerow", ""))->
           getValue((string)"t_file.inblock");
    }
   if( key.compare("t_evcoll_file.id") == 0) {
       return  ((T_Evcoll_Filerow*)this->
          getConstituentRow("t_evcoll_filerow", ""))->
           getValue((string)"t_evcoll_file.id");
    }
   if( key.compare("t_evcoll_file.evcoll") == 0) {
       return  ((T_Evcoll_Filerow*)this->
          getConstituentRow("t_evcoll_filerow", ""))->
           getValue((string)"t_evcoll_file.evcoll");
    }
   if( key.compare("t_evcoll_file.fileid") == 0) {
       return  ((T_Evcoll_Filerow*)this->
          getConstituentRow("t_evcoll_filerow", ""))->
           getValue((string)"t_evcoll_file.fileid");
    }
}

Crabevcollviewmultirow::Crabevcollviewmultirow(){
    this->T_Data_Tierobj = new T_Data_Tierrow();
    this->rowMap.set("t_data_tierrow", (void*)this->T_Data_Tierobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Processing_Pathobj = new T_Processing_Pathrow();
    this->rowMap.set("t_processing_pathrow", (void*)this->T_Processing_Pathobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Primary_Datasetobj = new T_Primary_Datasetrow();
    this->rowMap.set("t_primary_datasetrow", (void*)this->T_Primary_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Processed_Datasetobj = new T_Processed_Datasetrow();
    this->rowMap.set("t_processed_datasetrow", (void*)this->T_Processed_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Event_Collectionobj = new T_Event_Collectionrow();
    this->rowMap.set("t_event_collectionrow", (void*)this->T_Event_Collectionobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Blockobj = new T_Blockrow();
    this->rowMap.set("t_blockrow", (void*)this->T_Blockobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Info_Evcollobj = new T_Info_Evcollrow();
    this->rowMap.set("t_info_evcollrow", (void*)this->T_Info_Evcollobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Fileobj = new T_Filerow();
    this->rowMap.set("t_filerow", (void*)this->T_Fileobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Evcoll_Fileobj = new T_Evcoll_Filerow();
    this->rowMap.set("t_evcoll_filerow", (void*)this->T_Evcoll_Fileobj);
    this->constituentObjects.push_back(this->rowMap);
}

Crabevcollviewmultirow::~Crabevcollviewmultirow(){
   delete this->T_Data_Tierobj;
   delete this->T_Processing_Pathobj;
   delete this->T_Primary_Datasetobj;
   delete this->T_Processed_Datasetobj;
   delete this->T_Event_Collectionobj;
   delete this->T_Blockobj;
   delete this->T_Info_Evcollobj;
   delete this->T_Fileobj;
   delete this->T_Evcoll_Fileobj;
}

Crabevcollviewmultirow_DB_BINDING::Crabevcollviewmultirow_DB_BINDING() {
    TableName = "CrabEvCollView";

    Schema.insert(Entry("t_evcoll_file.fileid", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_file.guid", "STRING"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.evcoll", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_file.type", "INTEGER"));
    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_file.logical_name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_file.id", "INTEGER"));
    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_file.status", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.id", "INTEGER"));
    Schema.insert(Entry("t_file.inblock", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_file.filesize", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));

    PrimaryKeys.push_back("t_info_evcoll.event_collection");
    PrimaryKeys.push_back("t_evcoll_file.id");

    ForeignKeys.push_back("t_evcoll_file.fileid");
    ForeignKeys.push_back("t_processed_dataset.processing_path");
    ForeignKeys.push_back("t_file.type");
    ForeignKeys.push_back("t_block.processed_dataset");
    ForeignKeys.push_back("t_processing_path.data_tier");
    ForeignKeys.push_back("t_block.status");
    ForeignKeys.push_back("t_processing_path.app_config");
    ForeignKeys.push_back("t_evcoll_file.evcoll");
    ForeignKeys.push_back("t_info_evcoll.event_collection");
    ForeignKeys.push_back("t_processed_dataset.primary_dataset");
    ForeignKeys.push_back("t_event_collection.processed_dataset");
    ForeignKeys.push_back("t_file.status");
    ForeignKeys.push_back("t_file.inblock");

    list<string> tmplist;
    tmplist.push_back("t_evcoll_file.evcoll");
    tmplist.push_back("t_evcoll_file.fileid");
    tmplist.push_back("t_info_evcoll.event_collection");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_evcoll_file.id");
    tmplist.push_back("t_info_evcoll.event_collection");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_data_tier.id");
    NotNullKeys.push_back("t_data_tier.name");
    NotNullKeys.push_back("t_processing_path.id");
    NotNullKeys.push_back("t_processing_path.app_config");
    NotNullKeys.push_back("t_processing_path.data_tier");
    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");
    NotNullKeys.push_back("t_event_collection.id");
    NotNullKeys.push_back("t_event_collection.processed_dataset");
    NotNullKeys.push_back("t_event_collection.collection_index");
    NotNullKeys.push_back("t_block.id");
    NotNullKeys.push_back("t_block.processed_dataset");
    NotNullKeys.push_back("t_block.status");
    NotNullKeys.push_back("t_block.files");
    NotNullKeys.push_back("t_block.bytes");
    NotNullKeys.push_back("t_info_evcoll.event_collection");
    NotNullKeys.push_back("t_info_evcoll.events");
    NotNullKeys.push_back("t_info_evcoll.name");
    NotNullKeys.push_back("t_file.id");
    NotNullKeys.push_back("t_file.logical_name");
    NotNullKeys.push_back("t_file.type");
    NotNullKeys.push_back("t_file.inblock");
    NotNullKeys.push_back("t_evcoll_file.id");
    NotNullKeys.push_back("t_evcoll_file.evcoll");
    NotNullKeys.push_back("t_evcoll_file.fileid");

    SchemaOrder.push_back("t_data_tier");
    SchemaOrder.push_back("t_processing_path");
    SchemaOrder.push_back("t_primary_dataset");
    SchemaOrder.push_back("t_processed_dataset");
    SchemaOrder.push_back("t_event_collection");
    SchemaOrder.push_back("t_block");
    SchemaOrder.push_back("t_info_evcoll");
    SchemaOrder.push_back("t_file");
    SchemaOrder.push_back("t_evcoll_file");

    References.insert(Entry("t_evcoll_file.fileid", "t_file.id"));
    References.insert(Entry("t_block.processed_dataset", "t_processed_dataset.id"));
    References.insert(Entry("t_processed_dataset.primary_dataset", "t_primary_dataset.id"));
    References.insert(Entry("t_processed_dataset.processing_path", "t_processing_path.id"));
    References.insert(Entry("t_evcoll_file.evcoll", "t_event_collection.id"));
    References.insert(Entry("t_info_evcoll.event_collection", "t_event_collection.id"));
    References.insert(Entry("t_processing_path.data_tier", "t_data_tier.id"));
    References.insert(Entry("t_event_collection.processed_dataset", "t_processed_dataset.id"));
    References.insert(Entry("t_file.inblock", "t_block.id"));


    ExternalReferences.insert(Entry("t_file.inblock", "t_block.id"));
}

string* Crabevcollviewmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}

