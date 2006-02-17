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
	//cout<<"inside doSmartInsert for MultiTableInterface"<<endl;
	LOG4CXX_DEBUG(MultiTableInterface::logger,"MultiTableInterface::doSmartInsert");
	bool exceptionOccured = false;
	string exceptionMessage = "\n";

	for(Keys_iter i = schemaOrder->begin(); i != schemaOrder->end(); ++i) {
		try{
			if( util.isInMultiRef(*i, multiRefrences->begin(), multiRefrences->end()) ) {
				this->insertMulti(aRow,*i);
			} else {
				this->insertSingle(aRow, *i,"");
			}	
		} catch (ObjectLayerException &e) {
			exceptionOccured = true;
			//exceptionMessage += "At row "+*i+": "+ e.report() + " \n";
			exceptionMessage +=  e.report() + " \n";
		} catch (DBException &e) {
			exceptionOccured = true;
			//exceptionMessage += "At row "+*i+": "+ e.report() + " \n";
			exceptionMessage +=  e.report() + " \n";
		} catch (exception &e) {
			exceptionOccured = true;
			//exceptionMessage += "At row "+*i+": "+ e.what() + " \n";
			exceptionMessage += (string) e.what() + " \n";
		}


	}
	if(exceptionOccured) {
		throw ObjectLayerException(exceptionMessage);
	}
}












































template MultiTableInterface<Evcollviewmultirow>;
template MultiTableInterface<Fileviewmultirow>;
template MultiTableInterface<Pdblockviewmultirow>;
template MultiTableInterface<Blockviewmultirow>;
template MultiTableInterface<Primarydatasetmultirow>;
template MultiTableInterface<Processingpathmultirow>;
template MultiTableInterface<Crabevcollviewmultirow>;