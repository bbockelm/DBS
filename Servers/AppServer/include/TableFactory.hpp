#ifndef _TableFactory_hpp_
#define _TableFactory_hpp_

#include <string>
#include "TableInterface.hpp"
#include "SingleTableInterface.hpp"
#include "MultiTableInterface.hpp"
#include "ObjectLayerTables.hpp"
#include "RowInterface.hpp"

class TableFactory {

public: 
  TableFactory();
  ~TableFactory(); 
  
  
  TableInterface* getTableObject(string);
  //TableInterface<RowInterface>* getTableObject(string);
};

#endif

