
#ifndef _a_h_included_
#define _a_h_included_
/// This file contains Classes representing Rows of each table
///Generated from SQL
#include <iostream.h>
#include <vector>
#include <string>
#include "common.hpp"
#include "BaseSchemaNConstratints.hpp"
#include "SingleTableInterface.hpp"
#include "RowInterface.hpp"
#include "MultiTableInterface.hpp"


class T_Schema_Revisionrow  : public RowInterface {
public:
     T_Schema_Revisionrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     STRING revision;
};

class  T_Schema_Revisionrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Schema_Revisionrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Schema_Revisionrow> {
   public:
 T_Schema_Revisionrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Personrow  : public RowInterface {
public:
     T_Personrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
     STRING distinguised_name;
     STRING contactinfo;
};

class  T_Personrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Personrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Personrow> {
   public:
 T_Personrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Physics_Grouprow  : public RowInterface {
public:
     T_Physics_Grouprow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
     INTEGER convenor;
};

class  T_Physics_Grouprow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Physics_Grouprow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Physics_Grouprow> {
   public:
 T_Physics_Grouprow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Collection_Typerow  : public RowInterface {
public:
     T_Collection_Typerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Collection_Typerow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Collection_Typerow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Collection_Typerow> {
   public:
 T_Collection_Typerow_DB_BINDING  schemaNconstraints;
};

//##############

class T_App_Familyrow  : public RowInterface {
public:
     T_App_Familyrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_App_Familyrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_App_Familyrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_App_Familyrow> {
   public:
 T_App_Familyrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Applicationrow  : public RowInterface {
public:
     T_Applicationrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING executable;
     STRING app_version;
     INTEGER app_family;
     INTEGER input_type;
     INTEGER output_type;
};

class  T_Applicationrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Applicationrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Applicationrow> {
   public:
 T_Applicationrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_App_Configrow  : public RowInterface {
public:
     T_App_Configrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER application;
     STRING parameter_set;
     STRING conditions_version;
};

class  T_App_Configrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_App_Configrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_App_Configrow> {
   public:
 T_App_Configrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Desc_Triggerrow  : public RowInterface {
public:
     T_Desc_Triggerrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING description;
};

class  T_Desc_Triggerrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Desc_Triggerrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Desc_Triggerrow> {
   public:
 T_Desc_Triggerrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Desc_Mcrow  : public RowInterface {
public:
     T_Desc_Mcrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING description;
     STRING production;
     STRING decay_chain;
};

class  T_Desc_Mcrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Desc_Mcrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Desc_Mcrow> {
   public:
 T_Desc_Mcrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Desc_Primaryrow  : public RowInterface {
public:
     T_Desc_Primaryrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER trigger_path;
     INTEGER mc_channel;
     CHARACTER is_mc_data;
};

class  T_Desc_Primaryrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Desc_Primaryrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Desc_Primaryrow> {
   public:
 T_Desc_Primaryrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Data_Tierrow  : public RowInterface {
public:
     T_Data_Tierrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Data_Tierrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Data_Tierrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Data_Tierrow> {
   public:
 T_Data_Tierrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Primary_Datasetrow  : public RowInterface {
public:
     T_Primary_Datasetrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
     INTEGER description;
     INTEGER physics_group;
};

class  T_Primary_Datasetrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Primary_Datasetrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Primary_Datasetrow> {
   public:
 T_Primary_Datasetrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Processing_Pathrow  : public RowInterface {
public:
     T_Processing_Pathrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER parent;
     INTEGER app_config;
     STRING full_path;
     INTEGER data_tier;
};

class  T_Processing_Pathrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Processing_Pathrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Processing_Pathrow> {
   public:
 T_Processing_Pathrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Processed_Datasetrow  : public RowInterface {
public:
     T_Processed_Datasetrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER primary_dataset;
     INTEGER processing_path;
     STRING name;
     CHARACTER is_open;
};

class  T_Processed_Datasetrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Processed_Datasetrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Processed_Datasetrow> {
   public:
 T_Processed_Datasetrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Event_Collectionrow  : public RowInterface {
public:
     T_Event_Collectionrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER processed_dataset;
     INTEGER collection_index;
};

class  T_Event_Collectionrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Event_Collectionrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Event_Collectionrow> {
   public:
 T_Event_Collectionrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Analysis_Datasetrow  : public RowInterface {
public:
     T_Analysis_Datasetrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER processed_dataset;
     STRING name;
};

class  T_Analysis_Datasetrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Analysis_Datasetrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Analysis_Datasetrow> {
   public:
 T_Analysis_Datasetrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Anads_Datarow  : public RowInterface {
public:
     T_Anads_Datarow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER analysis_dataset;
     INTEGER event_collection;
};

class  T_Anads_Datarow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Anads_Datarow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Anads_Datarow> {
   public:
 T_Anads_Datarow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Parentage_Typerow  : public RowInterface {
public:
     T_Parentage_Typerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Parentage_Typerow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Parentage_Typerow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Parentage_Typerow> {
   public:
 T_Parentage_Typerow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Evcoll_Parentagerow  : public RowInterface {
public:
     T_Evcoll_Parentagerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER parent;
     INTEGER child;
     INTEGER type;
};

class  T_Evcoll_Parentagerow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Evcoll_Parentagerow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Evcoll_Parentagerow> {
   public:
 T_Evcoll_Parentagerow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Block_Statusrow  : public RowInterface {
public:
     T_Block_Statusrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Block_Statusrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Block_Statusrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Block_Statusrow> {
   public:
 T_Block_Statusrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Blockrow  : public RowInterface {
public:
     T_Blockrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER processed_dataset;
     INTEGER status;
     INTEGER files;
     INTEGER bytes;
};

class  T_Blockrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Blockrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Blockrow> {
   public:
 T_Blockrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_File_Statusrow  : public RowInterface {
public:
     T_File_Statusrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_File_Statusrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_File_Statusrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_File_Statusrow> {
   public:
 T_File_Statusrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_File_Typerow  : public RowInterface {
public:
     T_File_Typerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_File_Typerow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_File_Typerow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_File_Typerow> {
   public:
 T_File_Typerow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Filerow  : public RowInterface {
public:
     T_Filerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING guid;
     STRING logical_name;
     INTEGER filesize;
     INTEGER status;
     INTEGER type;
     INTEGER inblock;
};

class  T_Filerow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Filerow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Filerow> {
   public:
 T_Filerow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Evcoll_Filerow  : public RowInterface {
public:
     T_Evcoll_Filerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER evcoll;
     INTEGER fileid;
};

class  T_Evcoll_Filerow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Evcoll_Filerow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Evcoll_Filerow> {
   public:
 T_Evcoll_Filerow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Validation_Statusrow  : public RowInterface {
public:
     T_Validation_Statusrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Validation_Statusrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Validation_Statusrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Validation_Statusrow> {
   public:
 T_Validation_Statusrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Dataset_Statusrow  : public RowInterface {
public:
     T_Dataset_Statusrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Dataset_Statusrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Dataset_Statusrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Dataset_Statusrow> {
   public:
 T_Dataset_Statusrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Evcoll_Statusrow  : public RowInterface {
public:
     T_Evcoll_Statusrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Evcoll_Statusrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Evcoll_Statusrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Evcoll_Statusrow> {
   public:
 T_Evcoll_Statusrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Run_Qualityrow  : public RowInterface {
public:
     T_Run_Qualityrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Run_Qualityrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Run_Qualityrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Run_Qualityrow> {
   public:
 T_Run_Qualityrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Info_Anadsrow  : public RowInterface {
public:
     T_Info_Anadsrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER analysis_dataset;
     INTEGER events;
     STRING estimated_luminiosity;
     INTEGER status;
     INTEGER validation_status;
};

class  T_Info_Anadsrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Info_Anadsrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Info_Anadsrow> {
   public:
 T_Info_Anadsrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Info_Evcollrow  : public RowInterface {
public:
     T_Info_Evcollrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER event_collection;
     INTEGER events;
     STRING estimated_luminosity;
     INTEGER validation_status;
     STRING name;
     INTEGER status;
};

class  T_Info_Evcollrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Info_Evcollrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Info_Evcollrow> {
   public:
 T_Info_Evcollrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Runrow  : public RowInterface {
public:
     T_Runrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER run_number;
     INTEGER run_quality;
};

class  T_Runrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Runrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Runrow> {
   public:
 T_Runrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Evcoll_Runrow  : public RowInterface {
public:
     T_Evcoll_Runrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER event_collection;
     INTEGER run;
};

class  T_Evcoll_Runrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Evcoll_Runrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Evcoll_Runrow> {
   public:
 T_Evcoll_Runrow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Object_Historyrow  : public RowInterface {
public:
     T_Object_Historyrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     STRING object_type;
     INTEGER object_id;
     STRING operation;
     FLOAT at;
     INTEGER person;
     INTEGER mediator;
};

class  T_Object_Historyrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Object_Historyrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Object_Historyrow> {
   public:
 T_Object_Historyrow_DB_BINDING  schemaNconstraints;
};

//##############

class Insertappsmultirow  : public RowInterface {
public:

     Insertappsmultirow();
     ~Insertappsmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_App_Familyrow* T_App_Familyobj;
    T_Collection_Typerow* T_Application_Output_Typeobj;
    T_Collection_Typerow* T_Application_Input_Typeobj;
    T_Applicationrow* T_Applicationobj;
    T_App_Configrow* T_App_Configobj;
};

class  Insertappsmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Insertappsmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Insertappsmultirow> {
   public:
 Insertappsmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Personmultirow  : public RowInterface {
public:

     Personmultirow();
     ~Personmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Personrow* T_Personobj;
};

class  Personmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Personmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Personmultirow> {
   public:
 Personmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Physicsgroupmultirow  : public RowInterface {
public:

     Physicsgroupmultirow();
     ~Physicsgroupmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Personrow* T_Personobj;
    T_Physics_Grouprow* T_Physics_Groupobj;
};

class  Physicsgroupmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Physicsgroupmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Physicsgroupmultirow> {
   public:
 Physicsgroupmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Evcollviewmultirow  : public RowInterface {
public:

     Evcollviewmultirow();
     ~Evcollviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Event_Collectionrow* T_Event_Collectionobj;
    T_Evcoll_Statusrow* T_Evcoll_Statusobj;
    T_Validation_Statusrow* T_Validation_Statusobj;
    T_Info_Evcollrow* T_Info_Evcollobj;
};

class  Evcollviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Evcollviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Evcollviewmultirow> {
   public:
 Evcollviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Fileviewmultirow  : public RowInterface {
public:

     Fileviewmultirow();
     ~Fileviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_File_Typerow* T_File_Typeobj;
    T_File_Statusrow* T_File_Statusobj;
    T_Block_Statusrow* T_Block_Statusobj;
    T_Blockrow* T_Blockobj;
    T_Filerow* T_Fileobj;
    T_Evcoll_Filerow* T_Evcoll_Fileobj;
};

class  Fileviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Fileviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Fileviewmultirow> {
   public:
 Fileviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Primarydatasetmultirow  : public RowInterface {
public:

     Primarydatasetmultirow();
     ~Primarydatasetmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Desc_Triggerrow* T_Desc_Triggerobj;
    T_Desc_Mcrow* T_Desc_Mcobj;
    T_Desc_Primaryrow* T_Desc_Primaryobj;
    T_Physics_Grouprow* T_Physics_Groupobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
};

class  Primarydatasetmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Primarydatasetmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Primarydatasetmultirow> {
   public:
 Primarydatasetmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Processingpathmultirow  : public RowInterface {
public:

     Processingpathmultirow();
     ~Processingpathmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_App_Familyrow* T_App_Familyobj;
    T_Collection_Typerow* T_Application_Output_Typeobj;
    T_Collection_Typerow* T_Application_Input_Typeobj;
    T_Data_Tierrow* T_Data_Tierobj;
    T_Applicationrow* T_Applicationobj;
    T_App_Configrow* T_App_Configobj;
    T_Processing_Pathrow* T_Processing_Pathobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
};

class  Processingpathmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Processingpathmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Processingpathmultirow> {
   public:
 Processingpathmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Analysisdatasetmultirow  : public RowInterface {
public:

     Analysisdatasetmultirow();
     ~Analysisdatasetmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Analysis_Datasetrow* T_Analysis_Datasetobj;
    T_Anads_Datarow* T_Anads_Dataobj;
    T_Dataset_Statusrow* T_Dataset_Statusobj;
    T_Validation_Statusrow* T_Validation_Statusobj;
    T_Info_Anadsrow* T_Info_Anadsobj;
};

class  Analysisdatasetmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Analysisdatasetmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Analysisdatasetmultirow> {
   public:
 Analysisdatasetmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Datasetprovenenceevchildmultirow  : public RowInterface {
public:

     Datasetprovenenceevchildmultirow();
     ~Datasetprovenenceevchildmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Data_Tierrow* T_Data_Tierobj;
    T_Processing_Pathrow* T_Processing_Pathobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
    T_Parentage_Typerow* T_Parentage_Typeobj;
    T_Evcoll_Parentagerow* T_Evcoll_Parentageobj;
};

class  Datasetprovenenceevchildmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Datasetprovenenceevchildmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Datasetprovenenceevchildmultirow> {
   public:
 Datasetprovenenceevchildmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Datasetprovenenceevparentmultirow  : public RowInterface {
public:

     Datasetprovenenceevparentmultirow();
     ~Datasetprovenenceevparentmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Data_Tierrow* T_Data_Tierobj;
    T_Processing_Pathrow* T_Processing_Pathobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
    T_Parentage_Typerow* T_Parentage_Typeobj;
    T_Evcoll_Parentagerow* T_Evcoll_Parentageobj;
};

class  Datasetprovenenceevparentmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Datasetprovenenceevparentmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Datasetprovenenceevparentmultirow> {
   public:
 Datasetprovenenceevparentmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Crabevcollviewmultirow  : public RowInterface {
public:

     Crabevcollviewmultirow();
     ~Crabevcollviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Data_Tierrow* T_Data_Tierobj;
    T_Processing_Pathrow* T_Processing_Pathobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
    T_Blockrow* T_Blockobj;
    T_Info_Evcollrow* T_Info_Evcollobj;
};

class  Crabevcollviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Crabevcollviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Crabevcollviewmultirow> {
   public:
 Crabevcollviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############
typedef SingleTableInterface<T_Schema_Revisionrow>  T_Schema_RevisionTable;
typedef SingleTableInterface<T_Personrow>  T_PersonTable;
typedef SingleTableInterface<T_Physics_Grouprow>  T_Physics_GroupTable;
typedef SingleTableInterface<T_Collection_Typerow>  T_Collection_TypeTable;
typedef SingleTableInterface<T_App_Familyrow>  T_App_FamilyTable;
typedef SingleTableInterface<T_Applicationrow>  T_ApplicationTable;
typedef SingleTableInterface<T_App_Configrow>  T_App_ConfigTable;
typedef SingleTableInterface<T_Desc_Triggerrow>  T_Desc_TriggerTable;
typedef SingleTableInterface<T_Desc_Mcrow>  T_Desc_McTable;
typedef SingleTableInterface<T_Desc_Primaryrow>  T_Desc_PrimaryTable;
typedef SingleTableInterface<T_Data_Tierrow>  T_Data_TierTable;
typedef SingleTableInterface<T_Primary_Datasetrow>  T_Primary_DatasetTable;
typedef SingleTableInterface<T_Processing_Pathrow>  T_Processing_PathTable;
typedef SingleTableInterface<T_Processed_Datasetrow>  T_Processed_DatasetTable;
typedef SingleTableInterface<T_Event_Collectionrow>  T_Event_CollectionTable;
typedef SingleTableInterface<T_Analysis_Datasetrow>  T_Analysis_DatasetTable;
typedef SingleTableInterface<T_Anads_Datarow>  T_Anads_DataTable;
typedef SingleTableInterface<T_Parentage_Typerow>  T_Parentage_TypeTable;
typedef SingleTableInterface<T_Evcoll_Parentagerow>  T_Evcoll_ParentageTable;
typedef SingleTableInterface<T_Block_Statusrow>  T_Block_StatusTable;
typedef SingleTableInterface<T_Blockrow>  T_BlockTable;
typedef SingleTableInterface<T_File_Statusrow>  T_File_StatusTable;
typedef SingleTableInterface<T_File_Typerow>  T_File_TypeTable;
typedef SingleTableInterface<T_Filerow>  T_FileTable;
typedef SingleTableInterface<T_Evcoll_Filerow>  T_Evcoll_FileTable;
typedef SingleTableInterface<T_Validation_Statusrow>  T_Validation_StatusTable;
typedef SingleTableInterface<T_Dataset_Statusrow>  T_Dataset_StatusTable;
typedef SingleTableInterface<T_Evcoll_Statusrow>  T_Evcoll_StatusTable;
typedef SingleTableInterface<T_Run_Qualityrow>  T_Run_QualityTable;
typedef SingleTableInterface<T_Info_Anadsrow>  T_Info_AnadsTable;
typedef SingleTableInterface<T_Info_Evcollrow>  T_Info_EvcollTable;
typedef SingleTableInterface<T_Runrow>  T_RunTable;
typedef SingleTableInterface<T_Evcoll_Runrow>  T_Evcoll_RunTable;
typedef SingleTableInterface<T_Object_Historyrow>  T_Object_HistoryTable;
typedef MultiTableInterface<Insertappsmultirow>  InsertappsMultiTable;
typedef MultiTableInterface<Personmultirow>  PersonMultiTable;
typedef MultiTableInterface<Physicsgroupmultirow>  PhysicsgroupMultiTable;
typedef MultiTableInterface<Evcollviewmultirow>  EvcollviewMultiTable;
typedef MultiTableInterface<Fileviewmultirow>  FileviewMultiTable;
typedef MultiTableInterface<Primarydatasetmultirow>  PrimarydatasetMultiTable;
typedef MultiTableInterface<Processingpathmultirow>  ProcessingpathMultiTable;
typedef MultiTableInterface<Analysisdatasetmultirow>  AnalysisdatasetMultiTable;
typedef MultiTableInterface<Datasetprovenenceevchildmultirow>  DatasetprovenenceevchildMultiTable;
typedef MultiTableInterface<Datasetprovenenceevparentmultirow>  DatasetprovenenceevparentMultiTable;
typedef MultiTableInterface<Crabevcollviewmultirow>  CrabevcollviewMultiTable;

#endif


