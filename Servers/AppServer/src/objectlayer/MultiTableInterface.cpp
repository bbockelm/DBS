#include "MultiTableInterface.hpp"
#include "ObjectLayerTables.hpp"
#include "ObjectLayerException.hpp"
#include "DBException.hpp"



using namespace std;
///Default constructor
template <class R>
MultiTableInterface<R>::MultiTableInterface() {
}

template <class R>
MultiTableInterface<R>::MultiTableInterface(DBManagement* dbmanager) {
  this->dbmanager = dbmanager;
};



template <class R>
void MultiTableInterface<R>::doSmartInsert(R* aRow) {
	cout<<"inside doSmartInsert for MultiTableInterface"<<endl;
	//LOG4CXX_DEBUG(MultiTableInterface::logger,"MultiTableInterface::doSmartInsert");
	this->doSmartOperation(aRow, "insert");
}


template <class R>
void MultiTableInterface<R>::doSmartUpdate(R* aRow) {
	cout<<"inside doSmartUpdate for MultiTableInterface"<<endl;
	//LOG4CXX_DEBUG(MultiTableInterface::logger,"MultiTableInterface::doSmartUpdate");
	this->doSmartOperation(aRow, "update");
}

template <class R>
void MultiTableInterface<R>::doSmartOperation(R* aRow, string op) {
	bool exceptionOccured = false;
	string exceptionMessage = "\n";

	for(Keys_iter i = schemaOrder->begin(); i != schemaOrder->end(); ++i) {
		try{
			if( util.isInMultiRef(*i, multiRefrences->begin(), multiRefrences->end()) ) {
				operationMulti(aRow, *i, op);
			} else {
				operationSingle(aRow, *i, "", op);
			}	
		} catch (ObjectLayerException &e) {
			exceptionOccured = true;
			exceptionMessage +=  e.report() + " \n";
		} catch (DBException &e) {
			exceptionOccured = true;
			exceptionMessage +=  e.report() + " \n";
		} catch (exception &e) {
			exceptionOccured = true;
			exceptionMessage += (string) e.what() + " \n";
		}
	}
	if(exceptionOccured) {
		throw ObjectLayerException(exceptionMessage);
	}
}





















































template MultiTableInterface<Datasetpathmultirow>;
template MultiTableInterface<Evcollviewmultirow>;
template MultiTableInterface<Evcollviewnoparentmultirow>;
template MultiTableInterface<Fileviewmultirow>;
template MultiTableInterface<Pdblockviewmultirow>;
template MultiTableInterface<Blockviewmultirow>;
template MultiTableInterface<Primarydatasetmultirow>;
template MultiTableInterface<Processingpathmultirow>;
template MultiTableInterface<Crabevcollfileviewmultirow>;
template MultiTableInterface<Crabevcollviewmultirow>;
template MultiTableInterface<Evcollfileviewmultirow>;
template MultiTableInterface<Evcollparentageviewmultirow>;
template MultiTableInterface<Evcollstatusviewmultirow>;
template MultiTableInterface<Evcollsingleviewmultirow>;
