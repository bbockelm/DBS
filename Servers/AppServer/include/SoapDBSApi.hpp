#ifndef _SOAPDBSAPI_hpp_
#define _SOAPDBSAPI_hpp_

#include "ObjectLayerTables.hpp"  
#include "Util.hpp"
#include "Interface.hpp"
#include "DBSApi.hpp"
#include <iostream>
  
class SoapDBSApi : public DBSApi{

public:
	SoapDBSApi(struct soap* soap);
	~SoapDBSApi();
	DBS__Block* getBlock();
	DBS__File* getFile();
	DBS__EventCollection* getEventCollection();
	int* getInt(int value);
 
private:
        struct soap* soap;
};

#endif
