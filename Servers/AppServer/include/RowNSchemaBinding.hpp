#ifndef _RowNSchemaBinding_h_
#define _RowNSchemaBinding_h_

#include "BaseSchemaNConstratints.hpp"
#include "ObjectLayerTables.hpp"
#include <string>

typedef map<string, BaseSchemaNConstraintsBinding*> SchemaMap;
typedef map<string, BaseSchemaNConstraintsBinding*>::value_type SchemaMapEntry;
typedef map<string, BaseSchemaNConstraintsBinding*>::iterator SchemaMap_iter;

class RowNSchemaBinding {

public:

  RowNSchemaBinding();
  ~RowNSchemaBinding();

  BaseSchemaNConstraintsBinding* getSchemaObject(string);

private:
  SchemaMap RowNSchemaBindingMap;
};

#endif



