#ifndef _SingleTableInterface_hpp_included_
#define _SingleTableInterface_hpp_included_

#include "TableTemplate.hpp"
#include "DBManagement.hpp"

template <class R>
class SingleTableInterface : public TableTemplate<R> {

public:
  ///Default constructor
	SingleTableInterface();
	SingleTableInterface(DBManagement*);


private:
	void doSmartInsert(R*);
};
#endif


