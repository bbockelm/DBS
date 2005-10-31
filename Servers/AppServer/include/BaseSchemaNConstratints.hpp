#ifndef _BaseSchemaNConstratints_hpp_included_
#define _BaseSchemaNConstratints_hpp_included_

/// This file contains Base Class for alll Table type of 
// Schema and Constaints Classes that are generated from Databasde.

# include "common.hpp"

class BaseSchemaNConstraintsBinding {
  
public:
  
  BaseSchemaNConstraintsBinding();
  virtual ~BaseSchemaNConstraintsBinding();

  Dictionary* getSchema(void);
  Dictionary* getConstraints(void);
  Dictionary* getReferences(void);
  Dictionary* getMultiReferences(void);

  Keys* getPrimaryKeys(void);
  Keys* getForeignKeys(void);
  Keys* getNotNullKeys(void);
  Keys* getSchemaOrder(void); 
 
  ListOfLists* getUniqueKeys(void);

  virtual string* getTableName(void){};

protected :
  
  Keys PrimaryKeys;
  Keys ForeignKeys;
  Keys NotNullKeys;
  Keys SchemaOrder;
 
  ListOfLists UniqueKeys;

  Dictionary Schema;
  Dictionary Constraints;
  Dictionary References;
  Dictionary MultiReferences;
  Dictionary ExternalReferences;

};

template<class R>
class RowSchemaNConstraintsBinding {
public:
  RowSchemaNConstraintsBinding(){};
};


#endif

