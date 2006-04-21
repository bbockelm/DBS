#ifndef _MultiTableInterface_hpp_included_
#define _MultiTableInterface_hpp_included_

#include "TableTemplate.hpp"
#include "DBManagement.hpp"

template <class R>
class MultiTableInterface : public TableTemplate<R> {

public:
  ///Default constructor
	MultiTableInterface();
	MultiTableInterface(DBManagement*);


private:
	void doSmartInsert(R*);
};
#endif


