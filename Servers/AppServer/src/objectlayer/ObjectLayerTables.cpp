
#include "ObjectLayerTables.hpp"


T_Schema_Revisionrow::T_Schema_Revisionrow(){
    this->rowMap.set("t_schema_revisionrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Schema_Revisionrow::setValue(string key, void* value) {
    if( key.compare("t_schema_revision.revision") == 0) {
         this->revision = *((string*) value) ;
    }
}

void* T_Schema_Revisionrow::getValue(string key) {
   if( key.compare("t_schema_revision.revision") == 0) {
       return (&this->revision.getValue());
    }
}

T_Schema_Revisionrow_DB_BINDING::T_Schema_Revisionrow_DB_BINDING() {
    TableName = "t_schema_revision";

    Schema.insert(Entry("t_schema_revision.revision", "STRING"));


    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;

    NotNullKeys.push_back("t_schema_revision.revision");


    SchemaOrder.push_back("t_schema_revision");
}

string* T_Schema_Revisionrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


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
    if( key.compare("t_person.distinguised_name") == 0) {
         this->distinguised_name = *((string*) value) ;
    }
    if( key.compare("t_person.contactinfo") == 0) {
         this->contactinfo = *((string*) value) ;
    }
}

void* T_Personrow::getValue(string key) {
   if( key.compare("t_person.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_person.name") == 0) {
       return (&this->name.getValue());
    }
   if( key.compare("t_person.distinguised_name") == 0) {
       return (&this->distinguised_name.getValue());
    }
   if( key.compare("t_person.contactinfo") == 0) {
       return (&this->contactinfo.getValue());
    }
}

T_Personrow_DB_BINDING::T_Personrow_DB_BINDING() {
    TableName = "t_person";

    Schema.insert(Entry("t_person.id", "INTEGER"));
    Schema.insert(Entry("t_person.name", "STRING"));
    Schema.insert(Entry("t_person.distinguised_name", "STRING"));
    Schema.insert(Entry("t_person.contactinfo", "STRING"));

    PrimaryKeys.push_back("t_person.id");
    PrimaryKeys.push_back("t_person.distinguised_name");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_person.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_person.id");
    NotNullKeys.push_back("t_person.name");
    NotNullKeys.push_back("t_person.distinguised_name");
    NotNullKeys.push_back("t_person.contactinfo");


    SchemaOrder.push_back("t_person");
}

string* T_Personrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Physics_Grouprow::T_Physics_Grouprow(){
    this->rowMap.set("t_physics_grouprow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Physics_Grouprow::setValue(string key, void* value) {
    if( key.compare("t_physics_group.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_physics_group.name") == 0) {
         this->name = *((string*) value) ;
    }
    if( key.compare("t_physics_group.convener") == 0) {
         this->convener = *((int*) value) ;
    }
}

void* T_Physics_Grouprow::getValue(string key) {
   if( key.compare("t_physics_group.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_physics_group.name") == 0) {
       return (&this->name.getValue());
    }
   if( key.compare("t_physics_group.convener") == 0) {
       return (&this->convener.getValue());
    }
}

T_Physics_Grouprow_DB_BINDING::T_Physics_Grouprow_DB_BINDING() {
    TableName = "t_physics_group";

    Schema.insert(Entry("t_physics_group.id", "INTEGER"));
    Schema.insert(Entry("t_physics_group.name", "STRING"));
    Schema.insert(Entry("t_physics_group.convener", "INTEGER"));

    PrimaryKeys.push_back("t_physics_group.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_physics_group.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_physics_group.id");
    NotNullKeys.push_back("t_physics_group.name");


    SchemaOrder.push_back("t_physics_group");
}

string* T_Physics_Grouprow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Collection_Typerow::T_Collection_Typerow(){
    this->rowMap.set("t_collection_typerow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Collection_Typerow::setValue(string key, void* value) {
    if( key.compare("t_collection_type.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_collection_type.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Collection_Typerow::getValue(string key) {
   if( key.compare("t_collection_type.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_collection_type.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Collection_Typerow_DB_BINDING::T_Collection_Typerow_DB_BINDING() {
    TableName = "t_collection_type";

    Schema.insert(Entry("t_collection_type.id", "INTEGER"));
    Schema.insert(Entry("t_collection_type.name", "STRING"));

    PrimaryKeys.push_back("t_collection_type.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_collection_type.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_collection_type.id");
    NotNullKeys.push_back("t_collection_type.name");


    SchemaOrder.push_back("t_collection_type");
}

string* T_Collection_Typerow_DB_BINDING::getTableName(void) {
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
    if( key.compare("t_application.input_type") == 0) {
         this->input_type = *((int*) value) ;
    }
    if( key.compare("t_application.output_type") == 0) {
         this->output_type = *((int*) value) ;
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
   if( key.compare("t_application.input_type") == 0) {
       return (&this->input_type.getValue());
    }
   if( key.compare("t_application.output_type") == 0) {
       return (&this->output_type.getValue());
    }
}

T_Applicationrow_DB_BINDING::T_Applicationrow_DB_BINDING() {
    TableName = "t_application";

    Schema.insert(Entry("t_application.id", "INTEGER"));
    Schema.insert(Entry("t_application.executable", "STRING"));
    Schema.insert(Entry("t_application.app_version", "STRING"));
    Schema.insert(Entry("t_application.app_family", "INTEGER"));
    Schema.insert(Entry("t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_application.output_type", "INTEGER"));

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
    NotNullKeys.push_back("t_application.input_type");
    NotNullKeys.push_back("t_application.output_type");


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
    if( key.compare("t_app_config.conditions_version") == 0) {
         this->conditions_version = *((string*) value) ;
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
   if( key.compare("t_app_config.conditions_version") == 0) {
       return (&this->conditions_version.getValue());
    }
}

T_App_Configrow_DB_BINDING::T_App_Configrow_DB_BINDING() {
    TableName = "t_app_config";

    Schema.insert(Entry("t_app_config.id", "INTEGER"));
    Schema.insert(Entry("t_app_config.application", "INTEGER"));
    Schema.insert(Entry("t_app_config.parameter_set", "STRING"));
    Schema.insert(Entry("t_app_config.conditions_version", "STRING"));

    PrimaryKeys.push_back("t_app_config.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_app_config.application");
    tmplist.push_back("t_app_config.parameter_set");
    tmplist.push_back("t_app_config.conditions_version");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_app_config.id");
    NotNullKeys.push_back("t_app_config.application");
    NotNullKeys.push_back("t_app_config.parameter_set");
    NotNullKeys.push_back("t_app_config.conditions_version");


    SchemaOrder.push_back("t_app_config");
}

string* T_App_Configrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Desc_Triggerrow::T_Desc_Triggerrow(){
    this->rowMap.set("t_desc_triggerrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Desc_Triggerrow::setValue(string key, void* value) {
    if( key.compare("t_desc_trigger.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_desc_trigger.description") == 0) {
         this->description = *((string*) value) ;
    }
}

void* T_Desc_Triggerrow::getValue(string key) {
   if( key.compare("t_desc_trigger.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_desc_trigger.description") == 0) {
       return (&this->description.getValue());
    }
}

T_Desc_Triggerrow_DB_BINDING::T_Desc_Triggerrow_DB_BINDING() {
    TableName = "t_desc_trigger";

    Schema.insert(Entry("t_desc_trigger.id", "INTEGER"));
    Schema.insert(Entry("t_desc_trigger.description", "STRING"));

    PrimaryKeys.push_back("t_desc_trigger.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_desc_trigger.description");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_desc_trigger.id");
    NotNullKeys.push_back("t_desc_trigger.description");


    SchemaOrder.push_back("t_desc_trigger");
}

string* T_Desc_Triggerrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Desc_Mcrow::T_Desc_Mcrow(){
    this->rowMap.set("t_desc_mcrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Desc_Mcrow::setValue(string key, void* value) {
    if( key.compare("t_desc_mc.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_desc_mc.description") == 0) {
         this->description = *((string*) value) ;
    }
    if( key.compare("t_desc_mc.production") == 0) {
         this->production = *((string*) value) ;
    }
    if( key.compare("t_desc_mc.decay_chain") == 0) {
         this->decay_chain = *((string*) value) ;
    }
}

void* T_Desc_Mcrow::getValue(string key) {
   if( key.compare("t_desc_mc.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_desc_mc.description") == 0) {
       return (&this->description.getValue());
    }
   if( key.compare("t_desc_mc.production") == 0) {
       return (&this->production.getValue());
    }
   if( key.compare("t_desc_mc.decay_chain") == 0) {
       return (&this->decay_chain.getValue());
    }
}

T_Desc_Mcrow_DB_BINDING::T_Desc_Mcrow_DB_BINDING() {
    TableName = "t_desc_mc";

    Schema.insert(Entry("t_desc_mc.id", "INTEGER"));
    Schema.insert(Entry("t_desc_mc.description", "STRING"));
    Schema.insert(Entry("t_desc_mc.production", "STRING"));
    Schema.insert(Entry("t_desc_mc.decay_chain", "STRING"));

    PrimaryKeys.push_back("t_desc_mc.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_desc_mc.description");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_desc_mc.production");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_desc_mc.decay_chain");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_desc_mc.id");
    NotNullKeys.push_back("t_desc_mc.description");


    SchemaOrder.push_back("t_desc_mc");
}

string* T_Desc_Mcrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Desc_Primaryrow::T_Desc_Primaryrow(){
    this->rowMap.set("t_desc_primaryrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Desc_Primaryrow::setValue(string key, void* value) {
    if( key.compare("t_desc_primary.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_desc_primary.trigger_path") == 0) {
         this->trigger_path = *((int*) value) ;
    }
    if( key.compare("t_desc_primary.mc_channel") == 0) {
         this->mc_channel = *((int*) value) ;
    }
    if( key.compare("t_desc_primary.is_mc_data") == 0) {
         this->is_mc_data = *((char*) value) ;
    }
}

void* T_Desc_Primaryrow::getValue(string key) {
   if( key.compare("t_desc_primary.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_desc_primary.trigger_path") == 0) {
       return (&this->trigger_path.getValue());
    }
   if( key.compare("t_desc_primary.mc_channel") == 0) {
       return (&this->mc_channel.getValue());
    }
   if( key.compare("t_desc_primary.is_mc_data") == 0) {
       return (&this->is_mc_data.getValue());
    }
}

T_Desc_Primaryrow_DB_BINDING::T_Desc_Primaryrow_DB_BINDING() {
    TableName = "t_desc_primary";

    Schema.insert(Entry("t_desc_primary.id", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.trigger_path", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.mc_channel", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.is_mc_data", "CHARACTER"));

    PrimaryKeys.push_back("t_desc_primary.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_desc_primary.trigger_path");
    tmplist.push_back("t_desc_primary.mc_channel");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_desc_primary.id");
    NotNullKeys.push_back("t_desc_primary.trigger_path");


    SchemaOrder.push_back("t_desc_primary");
}

string* T_Desc_Primaryrow_DB_BINDING::getTableName(void) {
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
    if( key.compare("t_primary_dataset.description") == 0) {
         this->description = *((int*) value) ;
    }
    if( key.compare("t_primary_dataset.physics_group") == 0) {
         this->physics_group = *((int*) value) ;
    }
}

void* T_Primary_Datasetrow::getValue(string key) {
   if( key.compare("t_primary_dataset.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_primary_dataset.name") == 0) {
       return (&this->name.getValue());
    }
   if( key.compare("t_primary_dataset.description") == 0) {
       return (&this->description.getValue());
    }
   if( key.compare("t_primary_dataset.physics_group") == 0) {
       return (&this->physics_group.getValue());
    }
}

T_Primary_Datasetrow_DB_BINDING::T_Primary_Datasetrow_DB_BINDING() {
    TableName = "t_primary_dataset";

    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));

    PrimaryKeys.push_back("t_primary_dataset.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_primary_dataset.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_primary_dataset.description");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_primary_dataset.physics_group");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_primary_dataset.description");
    NotNullKeys.push_back("t_primary_dataset.physics_group");


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
    if( key.compare("t_processing_path.full_path") == 0) {
         this->full_path = *((string*) value) ;
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
   if( key.compare("t_processing_path.full_path") == 0) {
       return (&this->full_path.getValue());
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
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
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
    NotNullKeys.push_back("t_processing_path.full_path");
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
    if( key.compare("t_event_collection.is_primary") == 0) {
         this->is_primary = *((char*) value) ;
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
   if( key.compare("t_event_collection.is_primary") == 0) {
       return (&this->is_primary.getValue());
    }
}

T_Event_Collectionrow_DB_BINDING::T_Event_Collectionrow_DB_BINDING() {
    TableName = "t_event_collection";

    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));

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
    NotNullKeys.push_back("t_event_collection.is_primary");


    SchemaOrder.push_back("t_event_collection");
}

string* T_Event_Collectionrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Analysis_Datasetrow::T_Analysis_Datasetrow(){
    this->rowMap.set("t_analysis_datasetrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Analysis_Datasetrow::setValue(string key, void* value) {
    if( key.compare("t_analysis_dataset.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_analysis_dataset.processed_dataset") == 0) {
         this->processed_dataset = *((int*) value) ;
    }
    if( key.compare("t_analysis_dataset.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Analysis_Datasetrow::getValue(string key) {
   if( key.compare("t_analysis_dataset.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_analysis_dataset.processed_dataset") == 0) {
       return (&this->processed_dataset.getValue());
    }
   if( key.compare("t_analysis_dataset.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Analysis_Datasetrow_DB_BINDING::T_Analysis_Datasetrow_DB_BINDING() {
    TableName = "t_analysis_dataset";

    Schema.insert(Entry("t_analysis_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_analysis_dataset.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_analysis_dataset.name", "STRING"));

    PrimaryKeys.push_back("t_analysis_dataset.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_analysis_dataset.processed_dataset");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_analysis_dataset.id");
    NotNullKeys.push_back("t_analysis_dataset.processed_dataset");
    NotNullKeys.push_back("t_analysis_dataset.name");


    SchemaOrder.push_back("t_analysis_dataset");
}

string* T_Analysis_Datasetrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Anads_Datarow::T_Anads_Datarow(){
    this->rowMap.set("t_anads_datarow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Anads_Datarow::setValue(string key, void* value) {
    if( key.compare("t_anads_data.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_anads_data.analysis_dataset") == 0) {
         this->analysis_dataset = *((int*) value) ;
    }
    if( key.compare("t_anads_data.event_collection") == 0) {
         this->event_collection = *((int*) value) ;
    }
    if( key.compare("t_anads_data.is_primary") == 0) {
         this->is_primary = *((char*) value) ;
    }
}

void* T_Anads_Datarow::getValue(string key) {
   if( key.compare("t_anads_data.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_anads_data.analysis_dataset") == 0) {
       return (&this->analysis_dataset.getValue());
    }
   if( key.compare("t_anads_data.event_collection") == 0) {
       return (&this->event_collection.getValue());
    }
   if( key.compare("t_anads_data.is_primary") == 0) {
       return (&this->is_primary.getValue());
    }
}

T_Anads_Datarow_DB_BINDING::T_Anads_Datarow_DB_BINDING() {
    TableName = "t_anads_data";

    Schema.insert(Entry("t_anads_data.id", "INTEGER"));
    Schema.insert(Entry("t_anads_data.analysis_dataset", "INTEGER"));
    Schema.insert(Entry("t_anads_data.event_collection", "INTEGER"));
    Schema.insert(Entry("t_anads_data.is_primary", "CHARACTER"));

    PrimaryKeys.push_back("t_anads_data.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_anads_data.analysis_dataset");
    tmplist.push_back("t_anads_data.event_collection");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_anads_data.id");
    NotNullKeys.push_back("t_anads_data.analysis_dataset");
    NotNullKeys.push_back("t_anads_data.event_collection");
    NotNullKeys.push_back("t_anads_data.is_primary");

    Constraints.insert(Entry("t_anads_data.analysis_dataset", "DELETERULE:CASCADE"));

    SchemaOrder.push_back("t_anads_data");
}

string* T_Anads_Datarow_DB_BINDING::getTableName(void) {
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

    Schema.insert(Entry("t_evcoll_parentage.parent", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.child", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.type", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_parentage.parent");
    PrimaryKeys.push_back("t_evcoll_parentage.child");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_evcoll_parentage.parent");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_evcoll_parentage.child");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_evcoll_parentage.parent");
    NotNullKeys.push_back("t_evcoll_parentage.child");
    NotNullKeys.push_back("t_evcoll_parentage.type");

    Constraints.insert(Entry("t_evcoll_parentage.parent", "DELETERULE:CASCADE"));
    Constraints.insert(Entry("t_evcoll_parentage.child", "DELETERULE:SET"));
    Constraints.insert(Entry("t_evcoll_parentage.type", "DELETERULE:CASCADE"));

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
         this->filesize = *((string*) value) ;
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
    Schema.insert(Entry("t_file.filesize", "STRING"));
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
    NotNullKeys.push_back("t_file.filesize");
    NotNullKeys.push_back("t_file.status");
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
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
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


T_Validation_Statusrow::T_Validation_Statusrow(){
    this->rowMap.set("t_validation_statusrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Validation_Statusrow::setValue(string key, void* value) {
    if( key.compare("t_validation_status.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_validation_status.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Validation_Statusrow::getValue(string key) {
   if( key.compare("t_validation_status.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_validation_status.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Validation_Statusrow_DB_BINDING::T_Validation_Statusrow_DB_BINDING() {
    TableName = "t_validation_status";

    Schema.insert(Entry("t_validation_status.id", "INTEGER"));
    Schema.insert(Entry("t_validation_status.name", "STRING"));

    PrimaryKeys.push_back("t_validation_status.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_validation_status.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_validation_status.id");
    NotNullKeys.push_back("t_validation_status.name");


    SchemaOrder.push_back("t_validation_status");
}

string* T_Validation_Statusrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Dataset_Statusrow::T_Dataset_Statusrow(){
    this->rowMap.set("t_dataset_statusrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Dataset_Statusrow::setValue(string key, void* value) {
    if( key.compare("t_dataset_status.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_dataset_status.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Dataset_Statusrow::getValue(string key) {
   if( key.compare("t_dataset_status.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_dataset_status.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Dataset_Statusrow_DB_BINDING::T_Dataset_Statusrow_DB_BINDING() {
    TableName = "t_dataset_status";

    Schema.insert(Entry("t_dataset_status.id", "INTEGER"));
    Schema.insert(Entry("t_dataset_status.name", "STRING"));

    PrimaryKeys.push_back("t_dataset_status.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_dataset_status.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_dataset_status.id");
    NotNullKeys.push_back("t_dataset_status.name");


    SchemaOrder.push_back("t_dataset_status");
}

string* T_Dataset_Statusrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Evcoll_Statusrow::T_Evcoll_Statusrow(){
    this->rowMap.set("t_evcoll_statusrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Evcoll_Statusrow::setValue(string key, void* value) {
    if( key.compare("t_evcoll_status.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_evcoll_status.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Evcoll_Statusrow::getValue(string key) {
   if( key.compare("t_evcoll_status.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_evcoll_status.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Evcoll_Statusrow_DB_BINDING::T_Evcoll_Statusrow_DB_BINDING() {
    TableName = "t_evcoll_status";

    Schema.insert(Entry("t_evcoll_status.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_status.name", "STRING"));

    PrimaryKeys.push_back("t_evcoll_status.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_evcoll_status.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_evcoll_status.id");
    NotNullKeys.push_back("t_evcoll_status.name");


    SchemaOrder.push_back("t_evcoll_status");
}

string* T_Evcoll_Statusrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Run_Qualityrow::T_Run_Qualityrow(){
    this->rowMap.set("t_run_qualityrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Run_Qualityrow::setValue(string key, void* value) {
    if( key.compare("t_run_quality.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_run_quality.name") == 0) {
         this->name = *((string*) value) ;
    }
}

void* T_Run_Qualityrow::getValue(string key) {
   if( key.compare("t_run_quality.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_run_quality.name") == 0) {
       return (&this->name.getValue());
    }
}

T_Run_Qualityrow_DB_BINDING::T_Run_Qualityrow_DB_BINDING() {
    TableName = "t_run_quality";

    Schema.insert(Entry("t_run_quality.id", "INTEGER"));
    Schema.insert(Entry("t_run_quality.name", "STRING"));

    PrimaryKeys.push_back("t_run_quality.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_run_quality.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_run_quality.id");
    NotNullKeys.push_back("t_run_quality.name");


    SchemaOrder.push_back("t_run_quality");
}

string* T_Run_Qualityrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Info_Anadsrow::T_Info_Anadsrow(){
    this->rowMap.set("t_info_anadsrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Info_Anadsrow::setValue(string key, void* value) {
    if( key.compare("t_info_anads.analysis_dataset") == 0) {
         this->analysis_dataset = *((int*) value) ;
    }
    if( key.compare("t_info_anads.events") == 0) {
         this->events = *((int*) value) ;
    }
    if( key.compare("t_info_anads.estimated_luminiosity") == 0) {
         this->estimated_luminiosity = *((string*) value) ;
    }
    if( key.compare("t_info_anads.status") == 0) {
         this->status = *((int*) value) ;
    }
    if( key.compare("t_info_anads.validation_status") == 0) {
         this->validation_status = *((int*) value) ;
    }
}

void* T_Info_Anadsrow::getValue(string key) {
   if( key.compare("t_info_anads.analysis_dataset") == 0) {
       return (&this->analysis_dataset.getValue());
    }
   if( key.compare("t_info_anads.events") == 0) {
       return (&this->events.getValue());
    }
   if( key.compare("t_info_anads.estimated_luminiosity") == 0) {
       return (&this->estimated_luminiosity.getValue());
    }
   if( key.compare("t_info_anads.status") == 0) {
       return (&this->status.getValue());
    }
   if( key.compare("t_info_anads.validation_status") == 0) {
       return (&this->validation_status.getValue());
    }
}

T_Info_Anadsrow_DB_BINDING::T_Info_Anadsrow_DB_BINDING() {
    TableName = "t_info_anads";

    Schema.insert(Entry("t_info_anads.analysis_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_anads.events", "INTEGER"));
    Schema.insert(Entry("t_info_anads.estimated_luminiosity", "STRING"));
    Schema.insert(Entry("t_info_anads.status", "INTEGER"));
    Schema.insert(Entry("t_info_anads.validation_status", "INTEGER"));


    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_info_anads.analysis_dataset");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_info_anads.events");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_info_anads.analysis_dataset");
    NotNullKeys.push_back("t_info_anads.events");
    NotNullKeys.push_back("t_info_anads.status");
    NotNullKeys.push_back("t_info_anads.validation_status");

    Constraints.insert(Entry("t_info_anads.analysis_dataset", "DELETERULE:CASCADE"));

    SchemaOrder.push_back("t_info_anads");
}

string* T_Info_Anadsrow_DB_BINDING::getTableName(void) {
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
    if( key.compare("t_info_evcoll.estimated_luminosity") == 0) {
         this->estimated_luminosity = *((string*) value) ;
    }
    if( key.compare("t_info_evcoll.validation_status") == 0) {
         this->validation_status = *((int*) value) ;
    }
    if( key.compare("t_info_evcoll.name") == 0) {
         this->name = *((string*) value) ;
    }
    if( key.compare("t_info_evcoll.status") == 0) {
         this->status = *((int*) value) ;
    }
}

void* T_Info_Evcollrow::getValue(string key) {
   if( key.compare("t_info_evcoll.event_collection") == 0) {
       return (&this->event_collection.getValue());
    }
   if( key.compare("t_info_evcoll.events") == 0) {
       return (&this->events.getValue());
    }
   if( key.compare("t_info_evcoll.estimated_luminosity") == 0) {
       return (&this->estimated_luminosity.getValue());
    }
   if( key.compare("t_info_evcoll.validation_status") == 0) {
       return (&this->validation_status.getValue());
    }
   if( key.compare("t_info_evcoll.name") == 0) {
       return (&this->name.getValue());
    }
   if( key.compare("t_info_evcoll.status") == 0) {
       return (&this->status.getValue());
    }
}

T_Info_Evcollrow_DB_BINDING::T_Info_Evcollrow_DB_BINDING() {
    TableName = "t_info_evcoll";

    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.estimated_luminosity", "STRING"));
    Schema.insert(Entry("t_info_evcoll.validation_status", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.status", "INTEGER"));

    PrimaryKeys.push_back("t_info_evcoll.event_collection");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_info_evcoll.event_collection");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_info_evcoll.event_collection");
    NotNullKeys.push_back("t_info_evcoll.events");
    NotNullKeys.push_back("t_info_evcoll.validation_status");
    NotNullKeys.push_back("t_info_evcoll.name");
    NotNullKeys.push_back("t_info_evcoll.status");


    SchemaOrder.push_back("t_info_evcoll");
}

string* T_Info_Evcollrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Runrow::T_Runrow(){
    this->rowMap.set("t_runrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Runrow::setValue(string key, void* value) {
    if( key.compare("t_run.id") == 0) {
         this->id = *((int*) value) ;
    }
    if( key.compare("t_run.run_number") == 0) {
         this->run_number = *((int*) value) ;
    }
    if( key.compare("t_run.run_quality") == 0) {
         this->run_quality = *((int*) value) ;
    }
}

void* T_Runrow::getValue(string key) {
   if( key.compare("t_run.id") == 0) {
       return (&this->id.getValue());
    }
   if( key.compare("t_run.run_number") == 0) {
       return (&this->run_number.getValue());
    }
   if( key.compare("t_run.run_quality") == 0) {
       return (&this->run_quality.getValue());
    }
}

T_Runrow_DB_BINDING::T_Runrow_DB_BINDING() {
    TableName = "t_run";

    Schema.insert(Entry("t_run.id", "INTEGER"));
    Schema.insert(Entry("t_run.run_number", "INTEGER"));
    Schema.insert(Entry("t_run.run_quality", "INTEGER"));

    PrimaryKeys.push_back("t_run.id");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_run.run_number");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_run.id");
    NotNullKeys.push_back("t_run.run_number");
    NotNullKeys.push_back("t_run.run_quality");


    SchemaOrder.push_back("t_run");
}

string* T_Runrow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


T_Evcoll_Runrow::T_Evcoll_Runrow(){
    this->rowMap.set("t_evcoll_runrow", (void*)this);
    this->constituentObjects.push_back(this->rowMap);
}

void T_Evcoll_Runrow::setValue(string key, void* value) {
    if( key.compare("t_evcoll_run.event_collection") == 0) {
         this->event_collection = *((int*) value) ;
    }
    if( key.compare("t_evcoll_run.run") == 0) {
         this->run = *((int*) value) ;
    }
}

void* T_Evcoll_Runrow::getValue(string key) {
   if( key.compare("t_evcoll_run.event_collection") == 0) {
       return (&this->event_collection.getValue());
    }
   if( key.compare("t_evcoll_run.run") == 0) {
       return (&this->run.getValue());
    }
}

T_Evcoll_Runrow_DB_BINDING::T_Evcoll_Runrow_DB_BINDING() {
    TableName = "t_evcoll_run";

    Schema.insert(Entry("t_evcoll_run.event_collection", "INTEGER"));
    Schema.insert(Entry("t_evcoll_run.run", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_run.event_collection");

    ///Unique Keys being written as Set of list

    ///List of Lists in C++

    list<string> tmplist;
    tmplist.push_back("t_evcoll_run.run");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_evcoll_run.event_collection");
    NotNullKeys.push_back("t_evcoll_run.run");


    SchemaOrder.push_back("t_evcoll_run");
}

string* T_Evcoll_Runrow_DB_BINDING::getTableName(void) {
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

    NotNullKeys.push_back("t_object_history.object_type");
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


void Insertappsmultirow::setValue(string key, void* value) {
    if( key.compare("t_app_family.name") == 0) {
         ((T_App_Familyrow*)this->
          getConstituentRow((string)"t_app_familyrow", ""))->
           setValue((string)"t_app_family.name", value);
    }
    if( key.compare("t_collection_type.id.t_application.output_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.output_type"))->
           setValue((string)"t_collection_type.id", value);
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.output_type", value);
    }
    if( key.compare("t_collection_type.name.t_application.output_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.output_type"))->
           setValue((string)"t_collection_type.name", value);
    }
    if( key.compare("t_collection_type.id.t_application.input_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.input_type"))->
           setValue((string)"t_collection_type.id", value);
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.input_type", value);
    }
    if( key.compare("t_collection_type.name.t_application.input_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.input_type"))->
           setValue((string)"t_collection_type.name", value);
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
    if( key.compare("t_application.input_type") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.input_type", value);
    }
    if( key.compare("t_application.output_type") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.output_type", value);
    }
    if( key.compare("t_app_config.id") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.id", value);
    }
    if( key.compare("t_app_config.parameter_set") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.parameter_set", value);
    }
    if( key.compare("t_app_config.conditions_version") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.conditions_version", value);
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

void* Insertappsmultirow::getValue(string key) {
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
   if( key.compare("t_collection_type.id.t_application.output_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.output_type"))->
           getValue((string)"t_collection_type.id");
    }
   if( key.compare("t_collection_type.name.t_application.output_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.output_type"))->
           getValue((string)"t_collection_type.name");
    }
   if( key.compare("t_collection_type.id.t_application.input_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.input_type"))->
           getValue((string)"t_collection_type.id");
    }
   if( key.compare("t_collection_type.name.t_application.input_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.input_type"))->
           getValue((string)"t_collection_type.name");
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
   if( key.compare("t_application.input_type") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.input_type");
    }
   if( key.compare("t_application.output_type") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.output_type");
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
   if( key.compare("t_app_config.conditions_version") == 0) {
       return  ((T_App_Configrow*)this->
          getConstituentRow("t_app_configrow", ""))->
           getValue((string)"t_app_config.conditions_version");
    }
}

Insertappsmultirow::Insertappsmultirow(){
    this->T_App_Familyobj = new T_App_Familyrow();
    this->rowMap.set("t_app_familyrow", (void*)this->T_App_Familyobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Application_Output_Typeobj = new T_Collection_Typerow();
    this->rowMap.set("t_application.output_type", (void*)this->T_Application_Output_Typeobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Application_Input_Typeobj = new T_Collection_Typerow();
    this->rowMap.set("t_application.input_type", (void*)this->T_Application_Input_Typeobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Applicationobj = new T_Applicationrow();
    this->rowMap.set("t_applicationrow", (void*)this->T_Applicationobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_App_Configobj = new T_App_Configrow();
    this->rowMap.set("t_app_configrow", (void*)this->T_App_Configobj);
    this->constituentObjects.push_back(this->rowMap);
}

Insertappsmultirow::~Insertappsmultirow(){
   delete this->T_App_Familyobj;
   delete this->T_Application_Output_Typeobj;
   delete this->T_Application_Input_Typeobj;
   delete this->T_Applicationobj;
   delete this->T_App_Configobj;
}

Insertappsmultirow_DB_BINDING::Insertappsmultirow_DB_BINDING() {
    TableName = "InsertApps";

    Schema.insert(Entry("t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_app_family.name", "STRING"));
    Schema.insert(Entry("t_collection_type.name.t_application.output_type", "STRING"));
    Schema.insert(Entry("t_app_config.parameter_set", "STRING"));
    Schema.insert(Entry("t_app_config.conditions_version", "STRING"));
    Schema.insert(Entry("t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_app_config.id", "INTEGER"));
    Schema.insert(Entry("t_collection_type.id.t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_application.app_version", "STRING"));
    Schema.insert(Entry("t_app_config.application", "INTEGER"));
    Schema.insert(Entry("t_application.app_family", "INTEGER"));
    Schema.insert(Entry("t_application.id", "INTEGER"));
    Schema.insert(Entry("t_application.executable", "STRING"));
    Schema.insert(Entry("t_collection_type.id.t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_collection_type.name.t_application.input_type", "STRING"));
    Schema.insert(Entry("t_app_family.id", "INTEGER"));

    PrimaryKeys.push_back("t_app_config.id");

    ForeignKeys.push_back("t_app_config.application");
    ForeignKeys.push_back("t_application.output_type");
    ForeignKeys.push_back("t_application.input_type");
    ForeignKeys.push_back("t_application.app_family");

    list<string> tmplist;
    tmplist.push_back("t_app_config.application");
    tmplist.push_back("t_app_config.parameter_set");
    tmplist.push_back("t_app_config.conditions_version");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_app_config.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_app_family.id");
    NotNullKeys.push_back("t_app_family.name");
    NotNullKeys.push_back("t_collection_type.id.t_application.output_type");
    NotNullKeys.push_back("t_collection_type.name.t_application.output_type");
    NotNullKeys.push_back("t_collection_type.id.t_application.input_type");
    NotNullKeys.push_back("t_collection_type.name.t_application.input_type");
    NotNullKeys.push_back("t_application.id");
    NotNullKeys.push_back("t_application.executable");
    NotNullKeys.push_back("t_application.app_version");
    NotNullKeys.push_back("t_application.app_family");
    NotNullKeys.push_back("t_application.input_type");
    NotNullKeys.push_back("t_application.output_type");
    NotNullKeys.push_back("t_app_config.id");
    NotNullKeys.push_back("t_app_config.application");
    NotNullKeys.push_back("t_app_config.parameter_set");
    NotNullKeys.push_back("t_app_config.conditions_version");

    SchemaOrder.push_back("t_app_family");
    SchemaOrder.push_back("t_collection_type");
    SchemaOrder.push_back("t_application");
    SchemaOrder.push_back("t_app_config");

    References.insert(Entry("t_app_config.application", "t_application.id"));
    References.insert(Entry("t_application.app_family", "t_app_family.id"));

    MultiReferences.insert(Entry("t_application.output_type", "t_collection_type.id"));
    MultiReferences.insert(Entry("t_application.input_type", "t_collection_type.id"));

}

string* Insertappsmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Personmultirow::setValue(string key, void* value) {
    if( key.compare("t_person.id") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", ""))->
           setValue((string)"t_person.id", value);
    }
    if( key.compare("t_person.name") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", ""))->
           setValue((string)"t_person.name", value);
    }
    if( key.compare("t_person.distinguised_name") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", ""))->
           setValue((string)"t_person.distinguised_name", value);
    }
    if( key.compare("t_person.contactinfo") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", ""))->
           setValue((string)"t_person.contactinfo", value);
    }
}

void* Personmultirow::getValue(string key) {
   if( key.compare("t_person.id") == 0) {
       return  ((T_Personrow*)this->
          getConstituentRow("t_personrow", ""))->
           getValue((string)"t_person.id");
    }
   if( key.compare("t_person.name") == 0) {
       return  ((T_Personrow*)this->
          getConstituentRow("t_personrow", ""))->
           getValue((string)"t_person.name");
    }
   if( key.compare("t_person.distinguised_name") == 0) {
       return  ((T_Personrow*)this->
          getConstituentRow("t_personrow", ""))->
           getValue((string)"t_person.distinguised_name");
    }
   if( key.compare("t_person.contactinfo") == 0) {
       return  ((T_Personrow*)this->
          getConstituentRow("t_personrow", ""))->
           getValue((string)"t_person.contactinfo");
    }
}

Personmultirow::Personmultirow(){
    this->T_Personobj = new T_Personrow();
    this->rowMap.set("t_personrow", (void*)this->T_Personobj);
    this->constituentObjects.push_back(this->rowMap);
}

Personmultirow::~Personmultirow(){
   delete this->T_Personobj;
}

Personmultirow_DB_BINDING::Personmultirow_DB_BINDING() {
    TableName = "Person";

    Schema.insert(Entry("t_person.distinguised_name", "STRING"));
    Schema.insert(Entry("t_person.id", "INTEGER"));
    Schema.insert(Entry("t_person.name", "STRING"));
    Schema.insert(Entry("t_person.contactinfo", "STRING"));

    PrimaryKeys.push_back("t_person.id");
    PrimaryKeys.push_back("t_person.distinguised_name");


    list<string> tmplist;
    tmplist.push_back("t_person.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_person.id");
    tmplist.push_back("t_person.distinguised_name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_person.id");
    NotNullKeys.push_back("t_person.name");
    NotNullKeys.push_back("t_person.distinguised_name");
    NotNullKeys.push_back("t_person.contactinfo");

    SchemaOrder.push_back("t_person");



}

string* Personmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Physicsgroupmultirow::setValue(string key, void* value) {
    if( key.compare("t_person.id.t_physics_group.convener") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", "t_physics_group.convener"))->
           setValue((string)"t_person.id", value);
    }
    if( key.compare("t_person.name.t_physics_group.convener") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", "t_physics_group.convener"))->
           setValue((string)"t_person.name", value);
    }
    if( key.compare("t_person.distinguised_name.t_physics_group.convener") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", "t_physics_group.convener"))->
           setValue((string)"t_person.distinguised_name", value);
    }
    if( key.compare("t_person.contactinfo.t_physics_group.convener") == 0) {
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", "t_physics_group.convener"))->
           setValue((string)"t_person.contactinfo", value);
    }
    if( key.compare("t_physics_group.id") == 0) {
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.id", value);
    }
    if( key.compare("t_physics_group.name") == 0) {
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.name", value);
    }
    if( key.compare("t_physics_group.convener") == 0) {
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.convener", value);
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", ""))->
           setValue((string)"t_person.id", value);
    }
    if( key.compare("t_person.id") == 0) {
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.convener", value);
         ((T_Personrow*)this->
          getConstituentRow((string)"t_personrow", ""))->
           setValue((string)"t_person.id", value);
    }
}

void* Physicsgroupmultirow::getValue(string key) {
   if( key.compare("t_person.id.t_physics_group.convener") == 0) {
       return   ((T_Personrow*)this->
          getConstituentRow("t_personrow", "t_physics_group.convener"))->
           getValue((string)"t_person.id");
    }
   if( key.compare("t_person.name.t_physics_group.convener") == 0) {
       return   ((T_Personrow*)this->
          getConstituentRow("t_personrow", "t_physics_group.convener"))->
           getValue((string)"t_person.name");
    }
   if( key.compare("t_person.distinguised_name.t_physics_group.convener") == 0) {
       return   ((T_Personrow*)this->
          getConstituentRow("t_personrow", "t_physics_group.convener"))->
           getValue((string)"t_person.distinguised_name");
    }
   if( key.compare("t_person.contactinfo.t_physics_group.convener") == 0) {
       return   ((T_Personrow*)this->
          getConstituentRow("t_personrow", "t_physics_group.convener"))->
           getValue((string)"t_person.contactinfo");
    }
   if( key.compare("t_physics_group.id") == 0) {
       return  ((T_Physics_Grouprow*)this->
          getConstituentRow("t_physics_grouprow", ""))->
           getValue((string)"t_physics_group.id");
    }
   if( key.compare("t_physics_group.name") == 0) {
       return  ((T_Physics_Grouprow*)this->
          getConstituentRow("t_physics_grouprow", ""))->
           getValue((string)"t_physics_group.name");
    }
   if( key.compare("t_physics_group.convener") == 0) {
       return  ((T_Physics_Grouprow*)this->
          getConstituentRow("t_physics_grouprow", ""))->
           getValue((string)"t_physics_group.convener");
    }
}

Physicsgroupmultirow::Physicsgroupmultirow(){
    this->T_Physics_Group_Convenerobj = new T_Personrow();
    this->rowMap.set("t_physics_group.convener", (void*)this->T_Physics_Group_Convenerobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Physics_Groupobj = new T_Physics_Grouprow();
    this->rowMap.set("t_physics_grouprow", (void*)this->T_Physics_Groupobj);
    this->constituentObjects.push_back(this->rowMap);
}

Physicsgroupmultirow::~Physicsgroupmultirow(){
   delete this->T_Physics_Group_Convenerobj;
   delete this->T_Physics_Groupobj;
}

Physicsgroupmultirow_DB_BINDING::Physicsgroupmultirow_DB_BINDING() {
    TableName = "PhysicsGroup";

    Schema.insert(Entry("t_person.id.t_physics_group.convener", "INTEGER"));
    Schema.insert(Entry("t_person.distinguised_name.t_physics_group.convener", "STRING"));
    Schema.insert(Entry("t_person.name.t_physics_group.convener", "STRING"));
    Schema.insert(Entry("t_physics_group.id", "INTEGER"));
    Schema.insert(Entry("t_person.contactinfo.t_physics_group.convener", "STRING"));
    Schema.insert(Entry("t_physics_group.name", "STRING"));
    Schema.insert(Entry("t_physics_group.convener", "INTEGER"));

    PrimaryKeys.push_back("t_person.distinguised_name(t_physics_group.convener)");
    PrimaryKeys.push_back("t_physics_group.id");

    ForeignKeys.push_back("t_physics_group.convener");

    list<string> tmplist;
    tmplist.push_back("t_physics_group.name");
    tmplist.push_back("t_person.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_physics_group.name");
    tmplist.push_back("t_person.id");
    tmplist.push_back("t_person.distinguised_name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_physics_group.id");
    tmplist.push_back("t_person.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_physics_group.id");
    tmplist.push_back("t_person.id");
    tmplist.push_back("t_person.distinguised_name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_person.id.t_physics_group.convener");
    NotNullKeys.push_back("t_person.name.t_physics_group.convener");
    NotNullKeys.push_back("t_person.distinguised_name.t_physics_group.convener");
    NotNullKeys.push_back("t_person.contactinfo.t_physics_group.convener");
    NotNullKeys.push_back("t_physics_group.id");
    NotNullKeys.push_back("t_physics_group.name");

    SchemaOrder.push_back("t_person");
    SchemaOrder.push_back("t_physics_group");

    References.insert(Entry("t_physics_group.convener", "t_person.id"));


}

string* Physicsgroupmultirow_DB_BINDING::getTableName(void) {
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
    if( key.compare("t_event_collection.is_primary") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.is_primary", value);
    }
    if( key.compare("t_evcoll_status.name") == 0) {
         ((T_Evcoll_Statusrow*)this->
          getConstituentRow((string)"t_evcoll_statusrow", ""))->
           setValue((string)"t_evcoll_status.name", value);
    }
    if( key.compare("t_validation_status.name") == 0) {
         ((T_Validation_Statusrow*)this->
          getConstituentRow((string)"t_validation_statusrow", ""))->
           setValue((string)"t_validation_status.name", value);
    }
    if( key.compare("t_info_evcoll.events") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.events", value);
    }
    if( key.compare("t_info_evcoll.estimated_luminosity") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.estimated_luminosity", value);
    }
    if( key.compare("t_info_evcoll.name") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.name", value);
    }
    if( key.compare("t_info_evcoll.validation_status") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.validation_status", value);
         ((T_Validation_Statusrow*)this->
          getConstituentRow((string)"t_validation_statusrow", ""))->
           setValue((string)"t_validation_status.id", value);
    }
    if( key.compare("t_validation_status.id") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.validation_status", value);
         ((T_Validation_Statusrow*)this->
          getConstituentRow((string)"t_validation_statusrow", ""))->
           setValue((string)"t_validation_status.id", value);
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
    if( key.compare("t_info_evcoll.status") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.status", value);
         ((T_Evcoll_Statusrow*)this->
          getConstituentRow((string)"t_evcoll_statusrow", ""))->
           setValue((string)"t_evcoll_status.id", value);
    }
    if( key.compare("t_evcoll_status.id") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.status", value);
         ((T_Evcoll_Statusrow*)this->
          getConstituentRow((string)"t_evcoll_statusrow", ""))->
           setValue((string)"t_evcoll_status.id", value);
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
   if( key.compare("t_event_collection.is_primary") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.is_primary");
    }
   if( key.compare("t_evcoll_status.id") == 0) {
       return  ((T_Evcoll_Statusrow*)this->
          getConstituentRow("t_evcoll_statusrow", ""))->
           getValue((string)"t_evcoll_status.id");
    }
   if( key.compare("t_evcoll_status.name") == 0) {
       return  ((T_Evcoll_Statusrow*)this->
          getConstituentRow("t_evcoll_statusrow", ""))->
           getValue((string)"t_evcoll_status.name");
    }
   if( key.compare("t_validation_status.id") == 0) {
       return  ((T_Validation_Statusrow*)this->
          getConstituentRow("t_validation_statusrow", ""))->
           getValue((string)"t_validation_status.id");
    }
   if( key.compare("t_validation_status.name") == 0) {
       return  ((T_Validation_Statusrow*)this->
          getConstituentRow("t_validation_statusrow", ""))->
           getValue((string)"t_validation_status.name");
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
   if( key.compare("t_info_evcoll.estimated_luminosity") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.estimated_luminosity");
    }
   if( key.compare("t_info_evcoll.validation_status") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.validation_status");
    }
   if( key.compare("t_info_evcoll.name") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.name");
    }
   if( key.compare("t_info_evcoll.status") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.status");
    }
}

Evcollviewmultirow::Evcollviewmultirow(){
    this->T_Event_Collectionobj = new T_Event_Collectionrow();
    this->rowMap.set("t_event_collectionrow", (void*)this->T_Event_Collectionobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Evcoll_Statusobj = new T_Evcoll_Statusrow();
    this->rowMap.set("t_evcoll_statusrow", (void*)this->T_Evcoll_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Validation_Statusobj = new T_Validation_Statusrow();
    this->rowMap.set("t_validation_statusrow", (void*)this->T_Validation_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Info_Evcollobj = new T_Info_Evcollrow();
    this->rowMap.set("t_info_evcollrow", (void*)this->T_Info_Evcollobj);
    this->constituentObjects.push_back(this->rowMap);
}

Evcollviewmultirow::~Evcollviewmultirow(){
   delete this->T_Event_Collectionobj;
   delete this->T_Evcoll_Statusobj;
   delete this->T_Validation_Statusobj;
   delete this->T_Info_Evcollobj;
}

Evcollviewmultirow_DB_BINDING::Evcollviewmultirow_DB_BINDING() {
    TableName = "EvCollView";

    Schema.insert(Entry("t_info_evcoll.validation_status", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.status", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_validation_status.name", "STRING"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_evcoll_status.id", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_validation_status.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_status.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.estimated_luminosity", "STRING"));

    PrimaryKeys.push_back("t_info_evcoll.event_collection");

    ForeignKeys.push_back("t_info_evcoll.validation_status");
    ForeignKeys.push_back("t_event_collection.processed_dataset");
    ForeignKeys.push_back("t_info_evcoll.event_collection");
    ForeignKeys.push_back("t_info_evcoll.status");

    list<string> tmplist;
    tmplist.push_back("t_info_evcoll.event_collection");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_info_evcoll.event_collection");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_event_collection.id");
    NotNullKeys.push_back("t_event_collection.processed_dataset");
    NotNullKeys.push_back("t_event_collection.collection_index");
    NotNullKeys.push_back("t_event_collection.is_primary");
    NotNullKeys.push_back("t_evcoll_status.id");
    NotNullKeys.push_back("t_evcoll_status.name");
    NotNullKeys.push_back("t_validation_status.id");
    NotNullKeys.push_back("t_validation_status.name");
    NotNullKeys.push_back("t_info_evcoll.event_collection");
    NotNullKeys.push_back("t_info_evcoll.events");
    NotNullKeys.push_back("t_info_evcoll.validation_status");
    NotNullKeys.push_back("t_info_evcoll.name");
    NotNullKeys.push_back("t_info_evcoll.status");

    SchemaOrder.push_back("t_event_collection");
    SchemaOrder.push_back("t_evcoll_status");
    SchemaOrder.push_back("t_validation_status");
    SchemaOrder.push_back("t_info_evcoll");

    References.insert(Entry("t_info_evcoll.validation_status", "t_validation_status.id"));
    References.insert(Entry("t_info_evcoll.event_collection", "t_event_collection.id"));
    References.insert(Entry("t_info_evcoll.status", "t_evcoll_status.id"));


    ExternalReferences.insert(Entry("t_info_evcoll.status", "t_evcoll_status.id"));
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
    if( key.compare("t_block_status.name") == 0) {
         ((T_Block_Statusrow*)this->
          getConstituentRow((string)"t_block_statusrow", ""))->
           setValue((string)"t_block_status.name", value);
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
    this->T_Block_Statusobj = new T_Block_Statusrow();
    this->rowMap.set("t_block_statusrow", (void*)this->T_Block_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Blockobj = new T_Blockrow();
    this->rowMap.set("t_blockrow", (void*)this->T_Blockobj);
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
   delete this->T_Block_Statusobj;
   delete this->T_Blockobj;
   delete this->T_Fileobj;
   delete this->T_Evcoll_Fileobj;
}

Fileviewmultirow_DB_BINDING::Fileviewmultirow_DB_BINDING() {
    TableName = "FileView";

    Schema.insert(Entry("t_evcoll_file.fileid", "INTEGER"));
    Schema.insert(Entry("t_file.type", "INTEGER"));
    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_file.inblock", "INTEGER"));
    Schema.insert(Entry("t_file.logical_name", "STRING"));
    Schema.insert(Entry("t_file_status.name", "STRING"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_file.guid", "STRING"));
    Schema.insert(Entry("t_file_type.name", "STRING"));
    Schema.insert(Entry("t_file_type.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_file.evcoll", "INTEGER"));
    Schema.insert(Entry("t_block_status.id", "INTEGER"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_file.id", "INTEGER"));
    Schema.insert(Entry("t_file_status.id", "INTEGER"));
    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_file.filesize", "STRING"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));
    Schema.insert(Entry("t_file.status", "INTEGER"));
    Schema.insert(Entry("t_block_status.name", "STRING"));
    Schema.insert(Entry("t_evcoll_file.id", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_file.id");

    ForeignKeys.push_back("t_evcoll_file.fileid");
    ForeignKeys.push_back("t_file.type");
    ForeignKeys.push_back("t_block.processed_dataset");
    ForeignKeys.push_back("t_file.inblock");
    ForeignKeys.push_back("t_block.status");
    ForeignKeys.push_back("t_evcoll_file.evcoll");
    ForeignKeys.push_back("t_file.status");

    list<string> tmplist;
    tmplist.push_back("t_evcoll_file.evcoll");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
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
    NotNullKeys.push_back("t_block_status.id");
    NotNullKeys.push_back("t_block_status.name");
    NotNullKeys.push_back("t_block.id");
    NotNullKeys.push_back("t_block.processed_dataset");
    NotNullKeys.push_back("t_block.status");
    NotNullKeys.push_back("t_block.files");
    NotNullKeys.push_back("t_block.bytes");
    NotNullKeys.push_back("t_file.id");
    NotNullKeys.push_back("t_file.logical_name");
    NotNullKeys.push_back("t_file.filesize");
    NotNullKeys.push_back("t_file.status");
    NotNullKeys.push_back("t_file.type");
    NotNullKeys.push_back("t_file.inblock");
    NotNullKeys.push_back("t_evcoll_file.id");
    NotNullKeys.push_back("t_evcoll_file.evcoll");
    NotNullKeys.push_back("t_evcoll_file.fileid");

    SchemaOrder.push_back("t_file_type");
    SchemaOrder.push_back("t_file_status");
    SchemaOrder.push_back("t_block_status");
    SchemaOrder.push_back("t_block");
    SchemaOrder.push_back("t_file");
    SchemaOrder.push_back("t_evcoll_file");

    References.insert(Entry("t_evcoll_file.fileid", "t_file.id"));
    References.insert(Entry("t_block.status", "t_block_status.id"));
    References.insert(Entry("t_file.type", "t_file_type.id"));
    References.insert(Entry("t_file.status", "t_file_status.id"));
    References.insert(Entry("t_file.inblock", "t_block.id"));


    ExternalReferences.insert(Entry("t_file.status", "t_file_status.id"));
}

string* Fileviewmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Primarydatasetmultirow::setValue(string key, void* value) {
    if( key.compare("t_desc_trigger.description") == 0) {
         ((T_Desc_Triggerrow*)this->
          getConstituentRow((string)"t_desc_triggerrow", ""))->
           setValue((string)"t_desc_trigger.description", value);
    }
    if( key.compare("t_desc_mc.description") == 0) {
         ((T_Desc_Mcrow*)this->
          getConstituentRow((string)"t_desc_mcrow", ""))->
           setValue((string)"t_desc_mc.description", value);
    }
    if( key.compare("t_desc_mc.production") == 0) {
         ((T_Desc_Mcrow*)this->
          getConstituentRow((string)"t_desc_mcrow", ""))->
           setValue((string)"t_desc_mc.production", value);
    }
    if( key.compare("t_desc_mc.decay_chain") == 0) {
         ((T_Desc_Mcrow*)this->
          getConstituentRow((string)"t_desc_mcrow", ""))->
           setValue((string)"t_desc_mc.decay_chain", value);
    }
    if( key.compare("t_desc_primary.is_mc_data") == 0) {
         ((T_Desc_Primaryrow*)this->
          getConstituentRow((string)"t_desc_primaryrow", ""))->
           setValue((string)"t_desc_primary.is_mc_data", value);
    }
    if( key.compare("t_physics_group.name") == 0) {
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.name", value);
    }
    if( key.compare("t_physics_group.convener") == 0) {
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.convener", value);
    }
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
    if( key.compare("t_primary_dataset.physics_group") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.physics_group", value);
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.id", value);
    }
    if( key.compare("t_physics_group.id") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.physics_group", value);
         ((T_Physics_Grouprow*)this->
          getConstituentRow((string)"t_physics_grouprow", ""))->
           setValue((string)"t_physics_group.id", value);
    }
    if( key.compare("t_desc_primary.mc_channel") == 0) {
         ((T_Desc_Primaryrow*)this->
          getConstituentRow((string)"t_desc_primaryrow", ""))->
           setValue((string)"t_desc_primary.mc_channel", value);
         ((T_Desc_Mcrow*)this->
          getConstituentRow((string)"t_desc_mcrow", ""))->
           setValue((string)"t_desc_mc.id", value);
    }
    if( key.compare("t_desc_mc.id") == 0) {
         ((T_Desc_Primaryrow*)this->
          getConstituentRow((string)"t_desc_primaryrow", ""))->
           setValue((string)"t_desc_primary.mc_channel", value);
         ((T_Desc_Mcrow*)this->
          getConstituentRow((string)"t_desc_mcrow", ""))->
           setValue((string)"t_desc_mc.id", value);
    }
    if( key.compare("t_primary_dataset.description") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.description", value);
         ((T_Desc_Primaryrow*)this->
          getConstituentRow((string)"t_desc_primaryrow", ""))->
           setValue((string)"t_desc_primary.id", value);
    }
    if( key.compare("t_desc_primary.id") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.description", value);
         ((T_Desc_Primaryrow*)this->
          getConstituentRow((string)"t_desc_primaryrow", ""))->
           setValue((string)"t_desc_primary.id", value);
    }
    if( key.compare("t_desc_primary.trigger_path") == 0) {
         ((T_Desc_Primaryrow*)this->
          getConstituentRow((string)"t_desc_primaryrow", ""))->
           setValue((string)"t_desc_primary.trigger_path", value);
         ((T_Desc_Triggerrow*)this->
          getConstituentRow((string)"t_desc_triggerrow", ""))->
           setValue((string)"t_desc_trigger.id", value);
    }
    if( key.compare("t_desc_trigger.id") == 0) {
         ((T_Desc_Primaryrow*)this->
          getConstituentRow((string)"t_desc_primaryrow", ""))->
           setValue((string)"t_desc_primary.trigger_path", value);
         ((T_Desc_Triggerrow*)this->
          getConstituentRow((string)"t_desc_triggerrow", ""))->
           setValue((string)"t_desc_trigger.id", value);
    }
}

void* Primarydatasetmultirow::getValue(string key) {
   if( key.compare("t_desc_trigger.id") == 0) {
       return  ((T_Desc_Triggerrow*)this->
          getConstituentRow("t_desc_triggerrow", ""))->
           getValue((string)"t_desc_trigger.id");
    }
   if( key.compare("t_desc_trigger.description") == 0) {
       return  ((T_Desc_Triggerrow*)this->
          getConstituentRow("t_desc_triggerrow", ""))->
           getValue((string)"t_desc_trigger.description");
    }
   if( key.compare("t_desc_mc.id") == 0) {
       return  ((T_Desc_Mcrow*)this->
          getConstituentRow("t_desc_mcrow", ""))->
           getValue((string)"t_desc_mc.id");
    }
   if( key.compare("t_desc_mc.description") == 0) {
       return  ((T_Desc_Mcrow*)this->
          getConstituentRow("t_desc_mcrow", ""))->
           getValue((string)"t_desc_mc.description");
    }
   if( key.compare("t_desc_mc.production") == 0) {
       return  ((T_Desc_Mcrow*)this->
          getConstituentRow("t_desc_mcrow", ""))->
           getValue((string)"t_desc_mc.production");
    }
   if( key.compare("t_desc_mc.decay_chain") == 0) {
       return  ((T_Desc_Mcrow*)this->
          getConstituentRow("t_desc_mcrow", ""))->
           getValue((string)"t_desc_mc.decay_chain");
    }
   if( key.compare("t_desc_primary.id") == 0) {
       return  ((T_Desc_Primaryrow*)this->
          getConstituentRow("t_desc_primaryrow", ""))->
           getValue((string)"t_desc_primary.id");
    }
   if( key.compare("t_desc_primary.trigger_path") == 0) {
       return  ((T_Desc_Primaryrow*)this->
          getConstituentRow("t_desc_primaryrow", ""))->
           getValue((string)"t_desc_primary.trigger_path");
    }
   if( key.compare("t_desc_primary.mc_channel") == 0) {
       return  ((T_Desc_Primaryrow*)this->
          getConstituentRow("t_desc_primaryrow", ""))->
           getValue((string)"t_desc_primary.mc_channel");
    }
   if( key.compare("t_desc_primary.is_mc_data") == 0) {
       return  ((T_Desc_Primaryrow*)this->
          getConstituentRow("t_desc_primaryrow", ""))->
           getValue((string)"t_desc_primary.is_mc_data");
    }
   if( key.compare("t_physics_group.id") == 0) {
       return  ((T_Physics_Grouprow*)this->
          getConstituentRow("t_physics_grouprow", ""))->
           getValue((string)"t_physics_group.id");
    }
   if( key.compare("t_physics_group.name") == 0) {
       return  ((T_Physics_Grouprow*)this->
          getConstituentRow("t_physics_grouprow", ""))->
           getValue((string)"t_physics_group.name");
    }
   if( key.compare("t_physics_group.convener") == 0) {
       return  ((T_Physics_Grouprow*)this->
          getConstituentRow("t_physics_grouprow", ""))->
           getValue((string)"t_physics_group.convener");
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
   if( key.compare("t_primary_dataset.description") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.description");
    }
   if( key.compare("t_primary_dataset.physics_group") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.physics_group");
    }
}

Primarydatasetmultirow::Primarydatasetmultirow(){
    this->T_Desc_Triggerobj = new T_Desc_Triggerrow();
    this->rowMap.set("t_desc_triggerrow", (void*)this->T_Desc_Triggerobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Desc_Mcobj = new T_Desc_Mcrow();
    this->rowMap.set("t_desc_mcrow", (void*)this->T_Desc_Mcobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Desc_Primaryobj = new T_Desc_Primaryrow();
    this->rowMap.set("t_desc_primaryrow", (void*)this->T_Desc_Primaryobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Physics_Groupobj = new T_Physics_Grouprow();
    this->rowMap.set("t_physics_grouprow", (void*)this->T_Physics_Groupobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Primary_Datasetobj = new T_Primary_Datasetrow();
    this->rowMap.set("t_primary_datasetrow", (void*)this->T_Primary_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
}

Primarydatasetmultirow::~Primarydatasetmultirow(){
   delete this->T_Desc_Triggerobj;
   delete this->T_Desc_Mcobj;
   delete this->T_Desc_Primaryobj;
   delete this->T_Physics_Groupobj;
   delete this->T_Primary_Datasetobj;
}

Primarydatasetmultirow_DB_BINDING::Primarydatasetmultirow_DB_BINDING() {
    TableName = "PrimaryDataset";

    Schema.insert(Entry("t_desc_mc.id", "INTEGER"));
    Schema.insert(Entry("t_desc_mc.description", "STRING"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_desc_mc.decay_chain", "STRING"));
    Schema.insert(Entry("t_physics_group.id", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.id", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.trigger_path", "INTEGER"));
    Schema.insert(Entry("t_desc_trigger.description", "STRING"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_desc_trigger.id", "INTEGER"));
    Schema.insert(Entry("t_desc_mc.production", "STRING"));
    Schema.insert(Entry("t_physics_group.name", "STRING"));
    Schema.insert(Entry("t_desc_primary.mc_channel", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_desc_primary.is_mc_data", "CHARACTER"));
    Schema.insert(Entry("t_physics_group.convener", "INTEGER"));

    PrimaryKeys.push_back("t_primary_dataset.id");

    ForeignKeys.push_back("t_primary_dataset.physics_group");
    ForeignKeys.push_back("t_desc_primary.mc_channel");
    ForeignKeys.push_back("t_primary_dataset.description");
    ForeignKeys.push_back("t_desc_primary.trigger_path");
    ForeignKeys.push_back("t_physics_group.convener");

    list<string> tmplist;
    tmplist.push_back("t_primary_dataset.name");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_primary_dataset.description");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_primary_dataset.physics_group");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_primary_dataset.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_desc_trigger.id");
    NotNullKeys.push_back("t_desc_trigger.description");
    NotNullKeys.push_back("t_desc_mc.id");
    NotNullKeys.push_back("t_desc_mc.description");
    NotNullKeys.push_back("t_desc_primary.id");
    NotNullKeys.push_back("t_desc_primary.trigger_path");
    NotNullKeys.push_back("t_physics_group.id");
    NotNullKeys.push_back("t_physics_group.name");
    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_primary_dataset.description");
    NotNullKeys.push_back("t_primary_dataset.physics_group");

    SchemaOrder.push_back("t_desc_trigger");
    SchemaOrder.push_back("t_desc_mc");
    SchemaOrder.push_back("t_desc_primary");
    SchemaOrder.push_back("t_physics_group");
    SchemaOrder.push_back("t_primary_dataset");

    References.insert(Entry("t_primary_dataset.physics_group", "t_physics_group.id"));
    References.insert(Entry("t_desc_primary.mc_channel", "t_desc_mc.id"));
    References.insert(Entry("t_primary_dataset.description", "t_desc_primary.id"));
    References.insert(Entry("t_desc_primary.trigger_path", "t_desc_trigger.id"));


    ExternalReferences.insert(Entry("t_physics_group.convener", "t_person.id"));
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
    if( key.compare("t_collection_type.id.t_application.output_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.output_type"))->
           setValue((string)"t_collection_type.id", value);
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.output_type", value);
    }
    if( key.compare("t_collection_type.name.t_application.output_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.output_type"))->
           setValue((string)"t_collection_type.name", value);
    }
    if( key.compare("t_collection_type.id.t_application.input_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.input_type"))->
           setValue((string)"t_collection_type.id", value);
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.input_type", value);
    }
    if( key.compare("t_collection_type.name.t_application.input_type") == 0) {
         ((T_Collection_Typerow*)this->
          getConstituentRow((string)"t_collection_typerow", "t_application.input_type"))->
           setValue((string)"t_collection_type.name", value);
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
    if( key.compare("t_application.input_type") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.input_type", value);
    }
    if( key.compare("t_application.output_type") == 0) {
         ((T_Applicationrow*)this->
          getConstituentRow((string)"t_applicationrow", ""))->
           setValue((string)"t_application.output_type", value);
    }
    if( key.compare("t_app_config.parameter_set") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.parameter_set", value);
    }
    if( key.compare("t_app_config.conditions_version") == 0) {
         ((T_App_Configrow*)this->
          getConstituentRow((string)"t_app_configrow", ""))->
           setValue((string)"t_app_config.conditions_version", value);
    }
    if( key.compare("t_processing_path.parent") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.parent", value);
    }
    if( key.compare("t_processing_path.full_path") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.full_path", value);
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.name", value);
    }
    if( key.compare("t_primary_dataset.description") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.description", value);
    }
    if( key.compare("t_primary_dataset.physics_group") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.physics_group", value);
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
   if( key.compare("t_collection_type.id.t_application.output_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.output_type"))->
           getValue((string)"t_collection_type.id");
    }
   if( key.compare("t_collection_type.name.t_application.output_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.output_type"))->
           getValue((string)"t_collection_type.name");
    }
   if( key.compare("t_collection_type.id.t_application.input_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.input_type"))->
           getValue((string)"t_collection_type.id");
    }
   if( key.compare("t_collection_type.name.t_application.input_type") == 0) {
       return   ((T_Collection_Typerow*)this->
          getConstituentRow("t_collection_typerow", "t_application.input_type"))->
           getValue((string)"t_collection_type.name");
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
   if( key.compare("t_application.input_type") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.input_type");
    }
   if( key.compare("t_application.output_type") == 0) {
       return  ((T_Applicationrow*)this->
          getConstituentRow("t_applicationrow", ""))->
           getValue((string)"t_application.output_type");
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
   if( key.compare("t_app_config.conditions_version") == 0) {
       return  ((T_App_Configrow*)this->
          getConstituentRow("t_app_configrow", ""))->
           getValue((string)"t_app_config.conditions_version");
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
   if( key.compare("t_processing_path.full_path") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.full_path");
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
   if( key.compare("t_primary_dataset.description") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.description");
    }
   if( key.compare("t_primary_dataset.physics_group") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.physics_group");
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
    this->T_Application_Output_Typeobj = new T_Collection_Typerow();
    this->rowMap.set("t_application.output_type", (void*)this->T_Application_Output_Typeobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Application_Input_Typeobj = new T_Collection_Typerow();
    this->rowMap.set("t_application.input_type", (void*)this->T_Application_Input_Typeobj);
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
   delete this->T_Application_Output_Typeobj;
   delete this->T_Application_Input_Typeobj;
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
    Schema.insert(Entry("t_collection_type.name.t_application.output_type", "STRING"));
    Schema.insert(Entry("t_application.app_version", "STRING"));
    Schema.insert(Entry("t_app_config.conditions_version", "STRING"));
    Schema.insert(Entry("t_application.id", "INTEGER"));
    Schema.insert(Entry("t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_app_config.id", "INTEGER"));
    Schema.insert(Entry("t_collection_type.id.t_application.input_type", "INTEGER"));
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
    Schema.insert(Entry("t_collection_type.name.t_application.input_type", "STRING"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_collection_type.id.t_application.output_type", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_app_family.id", "INTEGER"));
    Schema.insert(Entry("t_application.input_type", "INTEGER"));
    Schema.insert(Entry("t_application.executable", "STRING"));

    PrimaryKeys.push_back("t_processed_dataset.id");

    ForeignKeys.push_back("t_primary_dataset.physics_group");
    ForeignKeys.push_back("t_application.output_type");
    ForeignKeys.push_back("t_primary_dataset.description");
    ForeignKeys.push_back("t_processing_path.app_config");
    ForeignKeys.push_back("t_processing_path.data_tier");
    ForeignKeys.push_back("t_processed_dataset.processing_path");
    ForeignKeys.push_back("t_app_config.application");
    ForeignKeys.push_back("t_processed_dataset.primary_dataset");
    ForeignKeys.push_back("t_application.app_family");
    ForeignKeys.push_back("t_application.input_type");

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
    NotNullKeys.push_back("t_collection_type.id.t_application.output_type");
    NotNullKeys.push_back("t_collection_type.name.t_application.output_type");
    NotNullKeys.push_back("t_collection_type.id.t_application.input_type");
    NotNullKeys.push_back("t_collection_type.name.t_application.input_type");
    NotNullKeys.push_back("t_data_tier.id");
    NotNullKeys.push_back("t_data_tier.name");
    NotNullKeys.push_back("t_application.id");
    NotNullKeys.push_back("t_application.executable");
    NotNullKeys.push_back("t_application.app_version");
    NotNullKeys.push_back("t_application.app_family");
    NotNullKeys.push_back("t_application.input_type");
    NotNullKeys.push_back("t_application.output_type");
    NotNullKeys.push_back("t_app_config.id");
    NotNullKeys.push_back("t_app_config.application");
    NotNullKeys.push_back("t_app_config.parameter_set");
    NotNullKeys.push_back("t_app_config.conditions_version");
    NotNullKeys.push_back("t_processing_path.id");
    NotNullKeys.push_back("t_processing_path.app_config");
    NotNullKeys.push_back("t_processing_path.full_path");
    NotNullKeys.push_back("t_processing_path.data_tier");
    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_primary_dataset.description");
    NotNullKeys.push_back("t_primary_dataset.physics_group");
    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");

    SchemaOrder.push_back("t_app_family");
    SchemaOrder.push_back("t_collection_type");
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

    MultiReferences.insert(Entry("t_application.output_type", "t_collection_type.id"));
    MultiReferences.insert(Entry("t_application.input_type", "t_collection_type.id"));

    ExternalReferences.insert(Entry("t_application.input_type", "t_collection_type.id"));
}

string* Processingpathmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Analysisdatasetmultirow::setValue(string key, void* value) {
    if( key.compare("t_analysis_dataset.processed_dataset") == 0) {
         ((T_Analysis_Datasetrow*)this->
          getConstituentRow((string)"t_analysis_datasetrow", ""))->
           setValue((string)"t_analysis_dataset.processed_dataset", value);
    }
    if( key.compare("t_analysis_dataset.name") == 0) {
         ((T_Analysis_Datasetrow*)this->
          getConstituentRow((string)"t_analysis_datasetrow", ""))->
           setValue((string)"t_analysis_dataset.name", value);
    }
    if( key.compare("t_anads_data.id") == 0) {
         ((T_Anads_Datarow*)this->
          getConstituentRow((string)"t_anads_datarow", ""))->
           setValue((string)"t_anads_data.id", value);
    }
    if( key.compare("t_anads_data.event_collection") == 0) {
         ((T_Anads_Datarow*)this->
          getConstituentRow((string)"t_anads_datarow", ""))->
           setValue((string)"t_anads_data.event_collection", value);
    }
    if( key.compare("t_anads_data.is_primary") == 0) {
         ((T_Anads_Datarow*)this->
          getConstituentRow((string)"t_anads_datarow", ""))->
           setValue((string)"t_anads_data.is_primary", value);
    }
    if( key.compare("t_dataset_status.name") == 0) {
         ((T_Dataset_Statusrow*)this->
          getConstituentRow((string)"t_dataset_statusrow", ""))->
           setValue((string)"t_dataset_status.name", value);
    }
    if( key.compare("t_validation_status.name") == 0) {
         ((T_Validation_Statusrow*)this->
          getConstituentRow((string)"t_validation_statusrow", ""))->
           setValue((string)"t_validation_status.name", value);
    }
    if( key.compare("t_info_anads.events") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.events", value);
    }
    if( key.compare("t_info_anads.estimated_luminiosity") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.estimated_luminiosity", value);
    }
    if( key.compare("t_info_anads.analysis_dataset") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.analysis_dataset", value);
         ((T_Analysis_Datasetrow*)this->
          getConstituentRow((string)"t_analysis_datasetrow", ""))->
           setValue((string)"t_analysis_dataset.id", value);
    }
    if( key.compare("t_analysis_dataset.id") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.analysis_dataset", value);
         ((T_Analysis_Datasetrow*)this->
          getConstituentRow((string)"t_analysis_datasetrow", ""))->
           setValue((string)"t_analysis_dataset.id", value);
    }
    if( key.compare("t_info_anads.validation_status") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.validation_status", value);
         ((T_Validation_Statusrow*)this->
          getConstituentRow((string)"t_validation_statusrow", ""))->
           setValue((string)"t_validation_status.id", value);
    }
    if( key.compare("t_validation_status.id") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.validation_status", value);
         ((T_Validation_Statusrow*)this->
          getConstituentRow((string)"t_validation_statusrow", ""))->
           setValue((string)"t_validation_status.id", value);
    }
    if( key.compare("t_info_anads.status") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.status", value);
         ((T_Dataset_Statusrow*)this->
          getConstituentRow((string)"t_dataset_statusrow", ""))->
           setValue((string)"t_dataset_status.id", value);
    }
    if( key.compare("t_dataset_status.id") == 0) {
         ((T_Info_Anadsrow*)this->
          getConstituentRow((string)"t_info_anadsrow", ""))->
           setValue((string)"t_info_anads.status", value);
         ((T_Dataset_Statusrow*)this->
          getConstituentRow((string)"t_dataset_statusrow", ""))->
           setValue((string)"t_dataset_status.id", value);
    }
    if( key.compare("t_anads_data.analysis_dataset") == 0) {
         ((T_Anads_Datarow*)this->
          getConstituentRow((string)"t_anads_datarow", ""))->
           setValue((string)"t_anads_data.analysis_dataset", value);
         ((T_Analysis_Datasetrow*)this->
          getConstituentRow((string)"t_analysis_datasetrow", ""))->
           setValue((string)"t_analysis_dataset.id", value);
    }
    if( key.compare("t_analysis_dataset.id") == 0) {
         ((T_Anads_Datarow*)this->
          getConstituentRow((string)"t_anads_datarow", ""))->
           setValue((string)"t_anads_data.analysis_dataset", value);
         ((T_Analysis_Datasetrow*)this->
          getConstituentRow((string)"t_analysis_datasetrow", ""))->
           setValue((string)"t_analysis_dataset.id", value);
    }
}

void* Analysisdatasetmultirow::getValue(string key) {
   if( key.compare("t_analysis_dataset.id") == 0) {
       return  ((T_Analysis_Datasetrow*)this->
          getConstituentRow("t_analysis_datasetrow", ""))->
           getValue((string)"t_analysis_dataset.id");
    }
   if( key.compare("t_analysis_dataset.processed_dataset") == 0) {
       return  ((T_Analysis_Datasetrow*)this->
          getConstituentRow("t_analysis_datasetrow", ""))->
           getValue((string)"t_analysis_dataset.processed_dataset");
    }
   if( key.compare("t_analysis_dataset.name") == 0) {
       return  ((T_Analysis_Datasetrow*)this->
          getConstituentRow("t_analysis_datasetrow", ""))->
           getValue((string)"t_analysis_dataset.name");
    }
   if( key.compare("t_anads_data.id") == 0) {
       return  ((T_Anads_Datarow*)this->
          getConstituentRow("t_anads_datarow", ""))->
           getValue((string)"t_anads_data.id");
    }
   if( key.compare("t_anads_data.analysis_dataset") == 0) {
       return  ((T_Anads_Datarow*)this->
          getConstituentRow("t_anads_datarow", ""))->
           getValue((string)"t_anads_data.analysis_dataset");
    }
   if( key.compare("t_anads_data.event_collection") == 0) {
       return  ((T_Anads_Datarow*)this->
          getConstituentRow("t_anads_datarow", ""))->
           getValue((string)"t_anads_data.event_collection");
    }
   if( key.compare("t_anads_data.is_primary") == 0) {
       return  ((T_Anads_Datarow*)this->
          getConstituentRow("t_anads_datarow", ""))->
           getValue((string)"t_anads_data.is_primary");
    }
   if( key.compare("t_dataset_status.id") == 0) {
       return  ((T_Dataset_Statusrow*)this->
          getConstituentRow("t_dataset_statusrow", ""))->
           getValue((string)"t_dataset_status.id");
    }
   if( key.compare("t_dataset_status.name") == 0) {
       return  ((T_Dataset_Statusrow*)this->
          getConstituentRow("t_dataset_statusrow", ""))->
           getValue((string)"t_dataset_status.name");
    }
   if( key.compare("t_validation_status.id") == 0) {
       return  ((T_Validation_Statusrow*)this->
          getConstituentRow("t_validation_statusrow", ""))->
           getValue((string)"t_validation_status.id");
    }
   if( key.compare("t_validation_status.name") == 0) {
       return  ((T_Validation_Statusrow*)this->
          getConstituentRow("t_validation_statusrow", ""))->
           getValue((string)"t_validation_status.name");
    }
   if( key.compare("t_info_anads.analysis_dataset") == 0) {
       return  ((T_Info_Anadsrow*)this->
          getConstituentRow("t_info_anadsrow", ""))->
           getValue((string)"t_info_anads.analysis_dataset");
    }
   if( key.compare("t_info_anads.events") == 0) {
       return  ((T_Info_Anadsrow*)this->
          getConstituentRow("t_info_anadsrow", ""))->
           getValue((string)"t_info_anads.events");
    }
   if( key.compare("t_info_anads.estimated_luminiosity") == 0) {
       return  ((T_Info_Anadsrow*)this->
          getConstituentRow("t_info_anadsrow", ""))->
           getValue((string)"t_info_anads.estimated_luminiosity");
    }
   if( key.compare("t_info_anads.status") == 0) {
       return  ((T_Info_Anadsrow*)this->
          getConstituentRow("t_info_anadsrow", ""))->
           getValue((string)"t_info_anads.status");
    }
   if( key.compare("t_info_anads.validation_status") == 0) {
       return  ((T_Info_Anadsrow*)this->
          getConstituentRow("t_info_anadsrow", ""))->
           getValue((string)"t_info_anads.validation_status");
    }
}

Analysisdatasetmultirow::Analysisdatasetmultirow(){
    this->T_Analysis_Datasetobj = new T_Analysis_Datasetrow();
    this->rowMap.set("t_analysis_datasetrow", (void*)this->T_Analysis_Datasetobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Anads_Dataobj = new T_Anads_Datarow();
    this->rowMap.set("t_anads_datarow", (void*)this->T_Anads_Dataobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Dataset_Statusobj = new T_Dataset_Statusrow();
    this->rowMap.set("t_dataset_statusrow", (void*)this->T_Dataset_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Validation_Statusobj = new T_Validation_Statusrow();
    this->rowMap.set("t_validation_statusrow", (void*)this->T_Validation_Statusobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Info_Anadsobj = new T_Info_Anadsrow();
    this->rowMap.set("t_info_anadsrow", (void*)this->T_Info_Anadsobj);
    this->constituentObjects.push_back(this->rowMap);
}

Analysisdatasetmultirow::~Analysisdatasetmultirow(){
   delete this->T_Analysis_Datasetobj;
   delete this->T_Anads_Dataobj;
   delete this->T_Dataset_Statusobj;
   delete this->T_Validation_Statusobj;
   delete this->T_Info_Anadsobj;
}

Analysisdatasetmultirow_DB_BINDING::Analysisdatasetmultirow_DB_BINDING() {
    TableName = "AnalysisDataset";

    Schema.insert(Entry("t_dataset_status.name", "STRING"));
    Schema.insert(Entry("t_info_anads.analysis_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_anads.status", "INTEGER"));
    Schema.insert(Entry("t_anads_data.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_anads_data.event_collection", "INTEGER"));
    Schema.insert(Entry("t_info_anads.estimated_luminiosity", "STRING"));
    Schema.insert(Entry("t_analysis_dataset.name", "STRING"));
    Schema.insert(Entry("t_info_anads.validation_status", "INTEGER"));
    Schema.insert(Entry("t_anads_data.id", "INTEGER"));
    Schema.insert(Entry("t_dataset_status.id", "INTEGER"));
    Schema.insert(Entry("t_anads_data.analysis_dataset", "INTEGER"));
    Schema.insert(Entry("t_validation_status.name", "STRING"));
    Schema.insert(Entry("t_analysis_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_validation_status.id", "INTEGER"));
    Schema.insert(Entry("t_info_anads.events", "INTEGER"));
    Schema.insert(Entry("t_analysis_dataset.processed_dataset", "INTEGER"));

    PrimaryKeys.push_back("t_anads_data.id");

    ForeignKeys.push_back("t_info_anads.analysis_dataset");
    ForeignKeys.push_back("t_anads_data.event_collection");
    ForeignKeys.push_back("t_info_anads.validation_status");
    ForeignKeys.push_back("t_anads_data.analysis_dataset");
    ForeignKeys.push_back("t_info_anads.status");
    ForeignKeys.push_back("t_analysis_dataset.processed_dataset");

    list<string> tmplist;
    tmplist.push_back("t_anads_data.analysis_dataset");
    tmplist.push_back("t_anads_data.event_collection");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_anads_data.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_analysis_dataset.id");
    NotNullKeys.push_back("t_analysis_dataset.processed_dataset");
    NotNullKeys.push_back("t_analysis_dataset.name");
    NotNullKeys.push_back("t_anads_data.id");
    NotNullKeys.push_back("t_anads_data.analysis_dataset");
    NotNullKeys.push_back("t_anads_data.event_collection");
    NotNullKeys.push_back("t_anads_data.is_primary");
    NotNullKeys.push_back("t_dataset_status.id");
    NotNullKeys.push_back("t_dataset_status.name");
    NotNullKeys.push_back("t_validation_status.id");
    NotNullKeys.push_back("t_validation_status.name");
    NotNullKeys.push_back("t_info_anads.analysis_dataset");
    NotNullKeys.push_back("t_info_anads.events");
    NotNullKeys.push_back("t_info_anads.status");
    NotNullKeys.push_back("t_info_anads.validation_status");

    SchemaOrder.push_back("t_analysis_dataset");
    SchemaOrder.push_back("t_anads_data");
    SchemaOrder.push_back("t_dataset_status");
    SchemaOrder.push_back("t_validation_status");
    SchemaOrder.push_back("t_info_anads");

    References.insert(Entry("t_info_anads.analysis_dataset", "t_analysis_dataset.id"));
    References.insert(Entry("t_info_anads.validation_status", "t_validation_status.id"));
    References.insert(Entry("t_info_anads.status", "t_dataset_status.id"));
    References.insert(Entry("t_anads_data.analysis_dataset", "t_analysis_dataset.id"));


    ExternalReferences.insert(Entry("t_analysis_dataset.processed_dataset", "t_processed_dataset.id"));
}

string* Analysisdatasetmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Datasetprovenenceevchildmultirow::setValue(string key, void* value) {
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
    if( key.compare("t_processing_path.full_path") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.full_path", value);
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.name", value);
    }
    if( key.compare("t_primary_dataset.description") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.description", value);
    }
    if( key.compare("t_primary_dataset.physics_group") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.physics_group", value);
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
    if( key.compare("t_event_collection.is_primary") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.is_primary", value);
    }
    if( key.compare("t_parentage_type.name") == 0) {
         ((T_Parentage_Typerow*)this->
          getConstituentRow((string)"t_parentage_typerow", ""))->
           setValue((string)"t_parentage_type.name", value);
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
}

void* Datasetprovenenceevchildmultirow::getValue(string key) {
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
   if( key.compare("t_processing_path.full_path") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.full_path");
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
   if( key.compare("t_primary_dataset.description") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.description");
    }
   if( key.compare("t_primary_dataset.physics_group") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.physics_group");
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
   if( key.compare("t_event_collection.is_primary") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.is_primary");
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

Datasetprovenenceevchildmultirow::Datasetprovenenceevchildmultirow(){
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
    this->T_Parentage_Typeobj = new T_Parentage_Typerow();
    this->rowMap.set("t_parentage_typerow", (void*)this->T_Parentage_Typeobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Evcoll_Parentageobj = new T_Evcoll_Parentagerow();
    this->rowMap.set("t_evcoll_parentagerow", (void*)this->T_Evcoll_Parentageobj);
    this->constituentObjects.push_back(this->rowMap);
}

Datasetprovenenceevchildmultirow::~Datasetprovenenceevchildmultirow(){
   delete this->T_Data_Tierobj;
   delete this->T_Processing_Pathobj;
   delete this->T_Primary_Datasetobj;
   delete this->T_Processed_Datasetobj;
   delete this->T_Event_Collectionobj;
   delete this->T_Parentage_Typeobj;
   delete this->T_Evcoll_Parentageobj;
}

Datasetprovenenceevchildmultirow_DB_BINDING::Datasetprovenenceevchildmultirow_DB_BINDING() {
    TableName = "DatasetProvenenceEvChild";

    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.type", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.parent", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_parentage_type.id", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_parentage_type.name", "STRING"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.child", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_parentage.parent");
    PrimaryKeys.push_back("t_evcoll_parentage.child");

    ForeignKeys.push_back("t_primary_dataset.physics_group");
    ForeignKeys.push_back("t_primary_dataset.description");
    ForeignKeys.push_back("t_processed_dataset.primary_dataset");
    ForeignKeys.push_back("t_event_collection.processed_dataset");
    ForeignKeys.push_back("t_evcoll_parentage.type");
    ForeignKeys.push_back("t_processing_path.app_config");
    ForeignKeys.push_back("t_evcoll_parentage.parent");
    ForeignKeys.push_back("t_processing_path.data_tier");
    ForeignKeys.push_back("t_processed_dataset.processing_path");
    ForeignKeys.push_back("t_evcoll_parentage.child");

    list<string> tmplist;
    tmplist.push_back("t_evcoll_parentage.parent");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_evcoll_parentage.child");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_evcoll_parentage.parent");
    tmplist.push_back("t_evcoll_parentage.child");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_data_tier.id");
    NotNullKeys.push_back("t_data_tier.name");
    NotNullKeys.push_back("t_processing_path.id");
    NotNullKeys.push_back("t_processing_path.app_config");
    NotNullKeys.push_back("t_processing_path.full_path");
    NotNullKeys.push_back("t_processing_path.data_tier");
    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_primary_dataset.description");
    NotNullKeys.push_back("t_primary_dataset.physics_group");
    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");
    NotNullKeys.push_back("t_event_collection.id");
    NotNullKeys.push_back("t_event_collection.processed_dataset");
    NotNullKeys.push_back("t_event_collection.collection_index");
    NotNullKeys.push_back("t_event_collection.is_primary");
    NotNullKeys.push_back("t_parentage_type.id");
    NotNullKeys.push_back("t_parentage_type.name");
    NotNullKeys.push_back("t_evcoll_parentage.parent");
    NotNullKeys.push_back("t_evcoll_parentage.child");
    NotNullKeys.push_back("t_evcoll_parentage.type");

    SchemaOrder.push_back("t_data_tier");
    SchemaOrder.push_back("t_processing_path");
    SchemaOrder.push_back("t_primary_dataset");
    SchemaOrder.push_back("t_processed_dataset");
    SchemaOrder.push_back("t_event_collection");
    SchemaOrder.push_back("t_parentage_type");
    SchemaOrder.push_back("t_evcoll_parentage");

    References.insert(Entry("t_processed_dataset.primary_dataset", "t_primary_dataset.id"));
    References.insert(Entry("t_evcoll_parentage.type", "t_parentage_type.id"));
    References.insert(Entry("t_processed_dataset.processing_path", "t_processing_path.id"));
    References.insert(Entry("t_processing_path.data_tier", "t_data_tier.id"));
    References.insert(Entry("t_event_collection.processed_dataset", "t_processed_dataset.id"));
    References.insert(Entry("t_evcoll_parentage.child", "t_event_collection.id"));


    ExternalReferences.insert(Entry("t_evcoll_parentage.child", "t_event_collection.id"));
}

string* Datasetprovenenceevchildmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}


void Datasetprovenenceevparentmultirow::setValue(string key, void* value) {
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
    if( key.compare("t_processing_path.full_path") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.full_path", value);
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.name", value);
    }
    if( key.compare("t_primary_dataset.description") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.description", value);
    }
    if( key.compare("t_primary_dataset.physics_group") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.physics_group", value);
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
    if( key.compare("t_event_collection.is_primary") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.is_primary", value);
    }
    if( key.compare("t_parentage_type.name") == 0) {
         ((T_Parentage_Typerow*)this->
          getConstituentRow((string)"t_parentage_typerow", ""))->
           setValue((string)"t_parentage_type.name", value);
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
}

void* Datasetprovenenceevparentmultirow::getValue(string key) {
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
   if( key.compare("t_processing_path.full_path") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.full_path");
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
   if( key.compare("t_primary_dataset.description") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.description");
    }
   if( key.compare("t_primary_dataset.physics_group") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.physics_group");
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
   if( key.compare("t_event_collection.is_primary") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.is_primary");
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

Datasetprovenenceevparentmultirow::Datasetprovenenceevparentmultirow(){
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
    this->T_Parentage_Typeobj = new T_Parentage_Typerow();
    this->rowMap.set("t_parentage_typerow", (void*)this->T_Parentage_Typeobj);
    this->constituentObjects.push_back(this->rowMap);
    this->T_Evcoll_Parentageobj = new T_Evcoll_Parentagerow();
    this->rowMap.set("t_evcoll_parentagerow", (void*)this->T_Evcoll_Parentageobj);
    this->constituentObjects.push_back(this->rowMap);
}

Datasetprovenenceevparentmultirow::~Datasetprovenenceevparentmultirow(){
   delete this->T_Data_Tierobj;
   delete this->T_Processing_Pathobj;
   delete this->T_Primary_Datasetobj;
   delete this->T_Processed_Datasetobj;
   delete this->T_Event_Collectionobj;
   delete this->T_Parentage_Typeobj;
   delete this->T_Evcoll_Parentageobj;
}

Datasetprovenenceevparentmultirow_DB_BINDING::Datasetprovenenceevparentmultirow_DB_BINDING() {
    TableName = "DatasetProvenenceEvParent";

    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.type", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.parent", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_parentage_type.id", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_parentage_type.name", "STRING"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_evcoll_parentage.child", "INTEGER"));

    PrimaryKeys.push_back("t_evcoll_parentage.parent");
    PrimaryKeys.push_back("t_evcoll_parentage.child");

    ForeignKeys.push_back("t_primary_dataset.physics_group");
    ForeignKeys.push_back("t_primary_dataset.description");
    ForeignKeys.push_back("t_processed_dataset.primary_dataset");
    ForeignKeys.push_back("t_event_collection.processed_dataset");
    ForeignKeys.push_back("t_evcoll_parentage.type");
    ForeignKeys.push_back("t_processing_path.app_config");
    ForeignKeys.push_back("t_evcoll_parentage.parent");
    ForeignKeys.push_back("t_processing_path.data_tier");
    ForeignKeys.push_back("t_processed_dataset.processing_path");
    ForeignKeys.push_back("t_evcoll_parentage.child");

    list<string> tmplist;
    tmplist.push_back("t_evcoll_parentage.parent");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_evcoll_parentage.child");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_evcoll_parentage.parent");
    tmplist.push_back("t_evcoll_parentage.child");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_data_tier.id");
    NotNullKeys.push_back("t_data_tier.name");
    NotNullKeys.push_back("t_processing_path.id");
    NotNullKeys.push_back("t_processing_path.app_config");
    NotNullKeys.push_back("t_processing_path.full_path");
    NotNullKeys.push_back("t_processing_path.data_tier");
    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_primary_dataset.description");
    NotNullKeys.push_back("t_primary_dataset.physics_group");
    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");
    NotNullKeys.push_back("t_event_collection.id");
    NotNullKeys.push_back("t_event_collection.processed_dataset");
    NotNullKeys.push_back("t_event_collection.collection_index");
    NotNullKeys.push_back("t_event_collection.is_primary");
    NotNullKeys.push_back("t_parentage_type.id");
    NotNullKeys.push_back("t_parentage_type.name");
    NotNullKeys.push_back("t_evcoll_parentage.parent");
    NotNullKeys.push_back("t_evcoll_parentage.child");
    NotNullKeys.push_back("t_evcoll_parentage.type");

    SchemaOrder.push_back("t_data_tier");
    SchemaOrder.push_back("t_processing_path");
    SchemaOrder.push_back("t_primary_dataset");
    SchemaOrder.push_back("t_processed_dataset");
    SchemaOrder.push_back("t_event_collection");
    SchemaOrder.push_back("t_parentage_type");
    SchemaOrder.push_back("t_evcoll_parentage");

    References.insert(Entry("t_processed_dataset.primary_dataset", "t_primary_dataset.id"));
    References.insert(Entry("t_evcoll_parentage.type", "t_parentage_type.id"));
    References.insert(Entry("t_processed_dataset.processing_path", "t_processing_path.id"));
    References.insert(Entry("t_evcoll_parentage.parent", "t_event_collection.id"));
    References.insert(Entry("t_processing_path.data_tier", "t_data_tier.id"));
    References.insert(Entry("t_event_collection.processed_dataset", "t_processed_dataset.id"));


    ExternalReferences.insert(Entry("t_evcoll_parentage.child", "t_event_collection.id"));
}

string* Datasetprovenenceevparentmultirow_DB_BINDING::getTableName(void) {
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
    if( key.compare("t_processing_path.full_path") == 0) {
         ((T_Processing_Pathrow*)this->
          getConstituentRow((string)"t_processing_pathrow", ""))->
           setValue((string)"t_processing_path.full_path", value);
    }
    if( key.compare("t_primary_dataset.name") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.name", value);
    }
    if( key.compare("t_primary_dataset.description") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.description", value);
    }
    if( key.compare("t_primary_dataset.physics_group") == 0) {
         ((T_Primary_Datasetrow*)this->
          getConstituentRow((string)"t_primary_datasetrow", ""))->
           setValue((string)"t_primary_dataset.physics_group", value);
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
    if( key.compare("t_event_collection.is_primary") == 0) {
         ((T_Event_Collectionrow*)this->
          getConstituentRow((string)"t_event_collectionrow", ""))->
           setValue((string)"t_event_collection.is_primary", value);
    }
    if( key.compare("t_block.id") == 0) {
         ((T_Blockrow*)this->
          getConstituentRow((string)"t_blockrow", ""))->
           setValue((string)"t_block.id", value);
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
    if( key.compare("t_info_evcoll.estimated_luminosity") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.estimated_luminosity", value);
    }
    if( key.compare("t_info_evcoll.validation_status") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.validation_status", value);
    }
    if( key.compare("t_info_evcoll.name") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.name", value);
    }
    if( key.compare("t_info_evcoll.status") == 0) {
         ((T_Info_Evcollrow*)this->
          getConstituentRow((string)"t_info_evcollrow", ""))->
           setValue((string)"t_info_evcoll.status", value);
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
   if( key.compare("t_processing_path.full_path") == 0) {
       return  ((T_Processing_Pathrow*)this->
          getConstituentRow("t_processing_pathrow", ""))->
           getValue((string)"t_processing_path.full_path");
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
   if( key.compare("t_primary_dataset.description") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.description");
    }
   if( key.compare("t_primary_dataset.physics_group") == 0) {
       return  ((T_Primary_Datasetrow*)this->
          getConstituentRow("t_primary_datasetrow", ""))->
           getValue((string)"t_primary_dataset.physics_group");
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
   if( key.compare("t_event_collection.is_primary") == 0) {
       return  ((T_Event_Collectionrow*)this->
          getConstituentRow("t_event_collectionrow", ""))->
           getValue((string)"t_event_collection.is_primary");
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
   if( key.compare("t_info_evcoll.estimated_luminosity") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.estimated_luminosity");
    }
   if( key.compare("t_info_evcoll.validation_status") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.validation_status");
    }
   if( key.compare("t_info_evcoll.name") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.name");
    }
   if( key.compare("t_info_evcoll.status") == 0) {
       return  ((T_Info_Evcollrow*)this->
          getConstituentRow("t_info_evcollrow", ""))->
           getValue((string)"t_info_evcoll.status");
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
}

Crabevcollviewmultirow::~Crabevcollviewmultirow(){
   delete this->T_Data_Tierobj;
   delete this->T_Processing_Pathobj;
   delete this->T_Primary_Datasetobj;
   delete this->T_Processed_Datasetobj;
   delete this->T_Event_Collectionobj;
   delete this->T_Blockobj;
   delete this->T_Info_Evcollobj;
}

Crabevcollviewmultirow_DB_BINDING::Crabevcollviewmultirow_DB_BINDING() {
    TableName = "CrabEvCollView";

    Schema.insert(Entry("t_info_evcoll.validation_status", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.name", "STRING"));
    Schema.insert(Entry("t_block.status", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.physics_group", "INTEGER"));
    Schema.insert(Entry("t_processing_path.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.is_primary", "CHARACTER"));
    Schema.insert(Entry("t_event_collection.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.estimated_luminosity", "STRING"));
    Schema.insert(Entry("t_primary_dataset.description", "INTEGER"));
    Schema.insert(Entry("t_block.bytes", "INTEGER"));
    Schema.insert(Entry("t_data_tier.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.parent", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.processing_path", "INTEGER"));
    Schema.insert(Entry("t_block.files", "INTEGER"));
    Schema.insert(Entry("t_processing_path.data_tier", "INTEGER"));
    Schema.insert(Entry("t_primary_dataset.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.collection_index", "INTEGER"));
    Schema.insert(Entry("t_data_tier.name", "STRING"));
    Schema.insert(Entry("t_block.processed_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.events", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.is_open", "CHARACTER"));
    Schema.insert(Entry("t_processing_path.app_config", "INTEGER"));
    Schema.insert(Entry("t_block.id", "INTEGER"));
    Schema.insert(Entry("t_event_collection.id", "INTEGER"));
    Schema.insert(Entry("t_processing_path.full_path", "STRING"));
    Schema.insert(Entry("t_primary_dataset.name", "STRING"));
    Schema.insert(Entry("t_info_evcoll.status", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.primary_dataset", "INTEGER"));
    Schema.insert(Entry("t_info_evcoll.event_collection", "INTEGER"));
    Schema.insert(Entry("t_processed_dataset.id", "INTEGER"));

    PrimaryKeys.push_back("t_block.id");
    PrimaryKeys.push_back("t_info_evcoll.event_collection");

    ForeignKeys.push_back("t_primary_dataset.physics_group");
    ForeignKeys.push_back("t_info_evcoll.validation_status");
    ForeignKeys.push_back("t_primary_dataset.description");
    ForeignKeys.push_back("t_block.processed_dataset");
    ForeignKeys.push_back("t_processing_path.data_tier");
    ForeignKeys.push_back("t_info_evcoll.status");
    ForeignKeys.push_back("t_block.status");
    ForeignKeys.push_back("t_event_collection.processed_dataset");
    ForeignKeys.push_back("t_processing_path.app_config");
    ForeignKeys.push_back("t_info_evcoll.event_collection");
    ForeignKeys.push_back("t_processed_dataset.primary_dataset");
    ForeignKeys.push_back("t_processed_dataset.processing_path");

    list<string> tmplist;
    tmplist.push_back("t_info_evcoll.event_collection");
    tmplist.push_back("t_block.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();
    tmplist.push_back("t_info_evcoll.event_collection");
    tmplist.push_back("t_block.id");
    UniqueKeys.push_back(tmplist);
    tmplist.clear();

    NotNullKeys.push_back("t_data_tier.id");
    NotNullKeys.push_back("t_data_tier.name");
    NotNullKeys.push_back("t_processing_path.id");
    NotNullKeys.push_back("t_processing_path.app_config");
    NotNullKeys.push_back("t_processing_path.full_path");
    NotNullKeys.push_back("t_processing_path.data_tier");
    NotNullKeys.push_back("t_primary_dataset.id");
    NotNullKeys.push_back("t_primary_dataset.name");
    NotNullKeys.push_back("t_primary_dataset.description");
    NotNullKeys.push_back("t_primary_dataset.physics_group");
    NotNullKeys.push_back("t_processed_dataset.id");
    NotNullKeys.push_back("t_processed_dataset.primary_dataset");
    NotNullKeys.push_back("t_processed_dataset.processing_path");
    NotNullKeys.push_back("t_processed_dataset.name");
    NotNullKeys.push_back("t_processed_dataset.is_open");
    NotNullKeys.push_back("t_event_collection.id");
    NotNullKeys.push_back("t_event_collection.processed_dataset");
    NotNullKeys.push_back("t_event_collection.collection_index");
    NotNullKeys.push_back("t_event_collection.is_primary");
    NotNullKeys.push_back("t_block.id");
    NotNullKeys.push_back("t_block.processed_dataset");
    NotNullKeys.push_back("t_block.status");
    NotNullKeys.push_back("t_block.files");
    NotNullKeys.push_back("t_block.bytes");
    NotNullKeys.push_back("t_info_evcoll.event_collection");
    NotNullKeys.push_back("t_info_evcoll.events");
    NotNullKeys.push_back("t_info_evcoll.validation_status");
    NotNullKeys.push_back("t_info_evcoll.name");
    NotNullKeys.push_back("t_info_evcoll.status");

    SchemaOrder.push_back("t_data_tier");
    SchemaOrder.push_back("t_processing_path");
    SchemaOrder.push_back("t_primary_dataset");
    SchemaOrder.push_back("t_processed_dataset");
    SchemaOrder.push_back("t_event_collection");
    SchemaOrder.push_back("t_block");
    SchemaOrder.push_back("t_info_evcoll");

    References.insert(Entry("t_block.processed_dataset", "t_processed_dataset.id"));
    References.insert(Entry("t_processing_path.data_tier", "t_data_tier.id"));
    References.insert(Entry("t_processed_dataset.processing_path", "t_processing_path.id"));
    References.insert(Entry("t_info_evcoll.event_collection", "t_event_collection.id"));
    References.insert(Entry("t_processed_dataset.primary_dataset", "t_primary_dataset.id"));
    References.insert(Entry("t_event_collection.processed_dataset", "t_processed_dataset.id"));


    ExternalReferences.insert(Entry("t_processed_dataset.processing_path", "t_processing_path.id"));
}

string* Crabevcollviewmultirow_DB_BINDING::getTableName(void) {
      return &this->TableName;
}

