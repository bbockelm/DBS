
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


class T_Personrow  : public RowInterface {
public:
     T_Personrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
     STRING distinguished_name;
     STRING contact_info;
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

class T_Parameter_Setrow  : public RowInterface {
public:
     T_Parameter_Setrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING hash;
     STRING content;
};

class  T_Parameter_Setrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Parameter_Setrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Parameter_Setrow> {
   public:
 T_Parameter_Setrow_DB_BINDING  schemaNconstraints;
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
     INTEGER parameter_set;
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

class T_Primary_Datasetrow  : public RowInterface {
public:
     T_Primary_Datasetrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
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

class T_Processing_Namerow  : public RowInterface {
public:
     T_Processing_Namerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     STRING name;
};

class  T_Processing_Namerow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Processing_Namerow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Processing_Namerow> {
   public:
 T_Processing_Namerow_DB_BINDING  schemaNconstraints;
};

//##############

class T_Processingrow  : public RowInterface {
public:
     T_Processingrow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
     INTEGER primary_dataset;
     INTEGER app_config;
     INTEGER name;
     CHARACTER is_open;
     INTEGER input;
};

class  T_Processingrow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      T_Processingrow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<T_Processingrow> {
   public:
 T_Processingrow_DB_BINDING  schemaNconstraints;
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
     INTEGER data_tier;
     INTEGER name;
     INTEGER input;
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
     STRING name;
     INTEGER events;
     INTEGER status;
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

class T_Evcoll_Parentagerow  : public RowInterface {
public:
     T_Evcoll_Parentagerow();


     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:
     INTEGER id;
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
     INTEGER processing;
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
     STRING checksum;
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

class Datasetpathmultirow  : public RowInterface {
public:

     Datasetpathmultirow();
     ~Datasetpathmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Data_Tierrow* T_Data_Tierobj;
    T_Processing_Namerow* T_Processing_Nameobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
    T_Processingrow* T_Processingobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
};

class  Datasetpathmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Datasetpathmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Datasetpathmultirow> {
   public:
 Datasetpathmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Evcollviewmultirow  : public RowInterface {
public:

     Evcollviewmultirow();
     ~Evcollviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Evcoll_Statusrow* T_Evcoll_Statusobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
    T_Parentage_Typerow* T_Parentage_Typeobj;
    T_Evcoll_Parentagerow* T_Evcoll_Parentageobj;
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

class Evcollviewnoparentmultirow  : public RowInterface {
public:

     Evcollviewnoparentmultirow();
     ~Evcollviewnoparentmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Evcoll_Statusrow* T_Evcoll_Statusobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
};

class  Evcollviewnoparentmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Evcollviewnoparentmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Evcollviewnoparentmultirow> {
   public:
 Evcollviewnoparentmultirow_DB_BINDING  schemaNconstraints;
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

class Pdblockviewmultirow  : public RowInterface {
public:

     Pdblockviewmultirow();
     ~Pdblockviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Block_Statusrow* T_Block_Statusobj;
    T_Blockrow* T_Blockobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
};

class  Pdblockviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Pdblockviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Pdblockviewmultirow> {
   public:
 Pdblockviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Blockviewmultirow  : public RowInterface {
public:

     Blockviewmultirow();
     ~Blockviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Block_Statusrow* T_Block_Statusobj;
    T_Blockrow* T_Blockobj;
};

class  Blockviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Blockviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Blockviewmultirow> {
   public:
 Blockviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Primarydatasetmultirow  : public RowInterface {
public:

     Primarydatasetmultirow();
     ~Primarydatasetmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

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
    T_Data_Tierrow* T_Data_Tierobj;
    T_Applicationrow* T_Applicationobj;
    T_Parameter_Setrow* T_Parameter_Setobj;
    T_App_Configrow* T_App_Configobj;
    T_Processing_Namerow* T_Processing_Nameobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
    T_Processingrow* T_Processingobj;
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

class Crabevcollfileviewmultirow  : public RowInterface {
public:

     Crabevcollfileviewmultirow();
     ~Crabevcollfileviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Processing_Namerow* T_Processing_Nameobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
    T_Processingrow* T_Block_Processingobj;
    T_Data_Tierrow* T_Data_Tierobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
    T_Blockrow* T_Blockobj;
    T_File_Typerow* T_File_Typeobj;
    T_Filerow* T_Fileobj;
    T_Evcoll_Filerow* T_Evcoll_Fileobj;
};

class  Crabevcollfileviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Crabevcollfileviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Crabevcollfileviewmultirow> {
   public:
 Crabevcollfileviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Crabevcollviewmultirow  : public RowInterface {
public:

     Crabevcollviewmultirow();
     ~Crabevcollviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Processing_Namerow* T_Processing_Nameobj;
    T_Primary_Datasetrow* T_Primary_Datasetobj;
    T_Processingrow* T_Block_Processingobj;
    T_Data_Tierrow* T_Data_Tierobj;
    T_Processed_Datasetrow* T_Processed_Datasetobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
    T_Blockrow* T_Blockobj;
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

class Evcollfileviewmultirow  : public RowInterface {
public:

     Evcollfileviewmultirow();
     ~Evcollfileviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Evcoll_Statusrow* T_Evcoll_Statusobj;
    T_Event_Collectionrow* T_Event_Collectionobj;
    T_File_Typerow* T_File_Typeobj;
    T_File_Statusrow* T_File_Statusobj;
    T_Filerow* T_Fileobj;
    T_Evcoll_Filerow* T_Evcoll_Fileobj;
};

class  Evcollfileviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Evcollfileviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Evcollfileviewmultirow> {
   public:
 Evcollfileviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Evcollparentageviewmultirow  : public RowInterface {
public:

     Evcollparentageviewmultirow();
     ~Evcollparentageviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Evcoll_Parentagerow* T_Evcoll_Parentageobj;
};

class  Evcollparentageviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Evcollparentageviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Evcollparentageviewmultirow> {
   public:
 Evcollparentageviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Evcollstatusviewmultirow  : public RowInterface {
public:

     Evcollstatusviewmultirow();
     ~Evcollstatusviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Evcoll_Statusrow* T_Evcoll_Statusobj;
};

class  Evcollstatusviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Evcollstatusviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Evcollstatusviewmultirow> {
   public:
 Evcollstatusviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############

class Evcollsingleviewmultirow  : public RowInterface {
public:

     Evcollsingleviewmultirow();
     ~Evcollsingleviewmultirow();

     virtual void* getValue(string key);
     virtual void setValue(string key, void* value);

private:

    T_Event_Collectionrow* T_Event_Collectionobj;
};

class  Evcollsingleviewmultirow_DB_BINDING : public BaseSchemaNConstraintsBinding {
    public:
      Evcollsingleviewmultirow_DB_BINDING(); 
      virtual string* getTableName(void);
    private:
 string TableName;
};

template<>
class RowSchemaNConstraintsBinding<Evcollsingleviewmultirow> {
   public:
 Evcollsingleviewmultirow_DB_BINDING  schemaNconstraints;
};

//##############
typedef SingleTableInterface<T_Personrow>  T_PersonTable;
typedef SingleTableInterface<T_Object_Historyrow>  T_Object_HistoryTable;
typedef SingleTableInterface<T_Parameter_Setrow>  T_Parameter_SetTable;
typedef SingleTableInterface<T_App_Familyrow>  T_App_FamilyTable;
typedef SingleTableInterface<T_Applicationrow>  T_ApplicationTable;
typedef SingleTableInterface<T_App_Configrow>  T_App_ConfigTable;
typedef SingleTableInterface<T_Data_Tierrow>  T_Data_TierTable;
typedef SingleTableInterface<T_Parentage_Typerow>  T_Parentage_TypeTable;
typedef SingleTableInterface<T_Evcoll_Statusrow>  T_Evcoll_StatusTable;
typedef SingleTableInterface<T_Primary_Datasetrow>  T_Primary_DatasetTable;
typedef SingleTableInterface<T_Processing_Namerow>  T_Processing_NameTable;
typedef SingleTableInterface<T_Processingrow>  T_ProcessingTable;
typedef SingleTableInterface<T_Processed_Datasetrow>  T_Processed_DatasetTable;
typedef SingleTableInterface<T_Event_Collectionrow>  T_Event_CollectionTable;
typedef SingleTableInterface<T_Evcoll_Parentagerow>  T_Evcoll_ParentageTable;
typedef SingleTableInterface<T_Block_Statusrow>  T_Block_StatusTable;
typedef SingleTableInterface<T_Blockrow>  T_BlockTable;
typedef SingleTableInterface<T_File_Statusrow>  T_File_StatusTable;
typedef SingleTableInterface<T_File_Typerow>  T_File_TypeTable;
typedef SingleTableInterface<T_Filerow>  T_FileTable;
typedef SingleTableInterface<T_Evcoll_Filerow>  T_Evcoll_FileTable;
typedef MultiTableInterface<Datasetpathmultirow>  DatasetpathMultiTable;
typedef MultiTableInterface<Evcollviewmultirow>  EvcollviewMultiTable;
typedef MultiTableInterface<Evcollviewnoparentmultirow>  EvcollviewnoparentMultiTable;
typedef MultiTableInterface<Fileviewmultirow>  FileviewMultiTable;
typedef MultiTableInterface<Pdblockviewmultirow>  PdblockviewMultiTable;
typedef MultiTableInterface<Blockviewmultirow>  BlockviewMultiTable;
typedef MultiTableInterface<Primarydatasetmultirow>  PrimarydatasetMultiTable;
typedef MultiTableInterface<Processingpathmultirow>  ProcessingpathMultiTable;
typedef MultiTableInterface<Crabevcollfileviewmultirow>  CrabevcollfileviewMultiTable;
typedef MultiTableInterface<Crabevcollviewmultirow>  CrabevcollviewMultiTable;
typedef MultiTableInterface<Evcollfileviewmultirow>  EvcollfileviewMultiTable;
typedef MultiTableInterface<Evcollparentageviewmultirow>  EvcollparentageviewMultiTable;
typedef MultiTableInterface<Evcollstatusviewmultirow>  EvcollstatusviewMultiTable;
typedef MultiTableInterface<Evcollsingleviewmultirow>  EvcollsingleviewMultiTable;

#endif


