#include "SingleTableInterface.hpp"
#include "ObjectLayerTables.hpp"
#include "ObjectLayerException.hpp"
#include "ResultSet.hpp"

using namespace std;
///Default constructor
template <class R>
SingleTableInterface<R>::SingleTableInterface() {
}

template <class R>
SingleTableInterface<R>::SingleTableInterface(DBManagement* dbmanager) {
  this->dbmanager = dbmanager;
};

template <class R>
void SingleTableInterface<R>::doSmartInsert(R* aRow) {
        //cout<<"\n\nCalling doSmartInsert"<<endl;
	string clause = this->getClause(aRow);
	cout<<"inside doSmartInsert of SingleTable"<<endl;
	//LOG4CXX_DEBUG(TableTemplate<R>::logger,"SingleTableInterface::doSmartInsert");
	//if( util.isKeySet(aRow, primaryKeys->begin(), primaryKeys->end()) ) {
	//cout<<"calling util.isKeySetCheckNull(aRow, primaryKeysReal.begin(), primaryKeysReal.end(), notNullKeys )"<<endl;
	if(clause.length() > 0) {
		//cout<<"calling in SingleTableInterface.cpp  ResultSet* rs = this->doSelect("",clause);"<<endl;
		ResultSet* rs = this->doSelect("",clause);
		this->reSetColNamesInRS(rs);
		//cout<<"called doSelect done"<<endl;
		//int noOfRows = rs->getNoOfRows();
		//cout<<"noOfRows returned from DB is "<<noOfRows<<endl;
		//LOG4CXX_DEBUG(TableTemplate<R>::logger,"Number of Rows returned from DB is "+util.itoa(noOfRows));
                //cout << "This message is coming from here" << endl; 
		//if( noOfRows > 0 ) {
		if(rs->next()) {
			cout<<"rows more than 0"<<endl;
			R* aRowFromDB = new R();
			this->convertIntoRow(rs,aRowFromDB);
			delete rs;
			string message;
			//LOG4CXX_DEBUG(TableTemplate<R>::logger,"Checking consistancy between data provided and data fetched from DB");
			bool isConsistantVal = util.isConsistant(aRowFromDB, aRow, message);
			delete aRowFromDB;
			if( isConsistantVal ) {
				//LOG4CXX_DEBUG(TableTemplate<R>::logger,"The data is CONSISTANT and there is no need to do insert");
				return;
			} else {
				throw ObjectLayerException("Data your are trying to insert is inconsistant with data already in DB\n"+message);
			}
		} else {
			delete rs;
			//cout<<"inside void SingleTableInterface<R>::doSmartInsert(R* aRow) calling doSimpleInsert(aRow);"<<endl;
			this->doSimpleInsert(aRow);
		}
	} else {
		this->doSimpleInsert(aRow);
	}
	//cout<<"calling NOTHING just retunning"<<endl;


}

template <class R>
string SingleTableInterface<R>::getClause(R* aRow) {
	string clause="";
	if( util.isListOfKeySet(aRow, uniqueKeys->begin(), uniqueKeys->end() , notNullKeys )) {
		Keys uniqueKeysTmp = *util.getListOfKey(aRow,uniqueKeys->begin(), uniqueKeys->end() , notNullKeys) ;
		clause = sql->makeClause(aRow, uniqueKeysTmp.begin() , uniqueKeysTmp.end(), multiRefrences->begin(), multiRefrences->end());
	} else if( util.isKeySetCheckNull(aRow, primaryKeysReal.begin(), primaryKeysReal.end(), notNullKeys )) {
		Keys primaryKeysTmp = util.getKey(aRow, primaryKeysReal.begin(), primaryKeysReal.end()) ;
		clause = sql->makeClause(aRow, primaryKeysTmp.begin(), primaryKeysTmp.end(), multiRefrences->begin(), multiRefrences->end());
	}
	return clause;
}


template <class R>
void SingleTableInterface<R>::doSmartUpdate(R* aRow) {
        //cout<<"\n\nCalling doSmartUpdate"<<endl;
	string clause = this->getClause(aRow);
	//cout<<"inside doSmartUpdate of SingleTable"<<endl;
	if(clause.length() < 1 ) {
		throw ObjectLayerException("Unique key or Primary key not provided for update\n");
	}
	//cout<<"calling doSimpleUpdate"<<endl;
	string query = sql->makeUpdateQuery(aRow, *schemaOrder->begin(), "", schema->begin(), schema->end(), &primaryKeysReal ) + " WHERE " + clause;
	cout<<"query is "<<query<<endl;
	if(dbmanager->executeQuery(query) != 0) {
		throw ObjectLayerException("Query: " + query + " could not be executed");
	}
	//this->doSimpleUpdate(aRow);


}


template <class R>
void SingleTableInterface<R>::doSimpleInsert(R* aRow) {
	cout<<"inside doSimpleInsert"<<endl;
  //LOG4CXX_DEBUG(TableTemplate::logger,"TableTemplate::doSimpleInsert");
  //if(!isNotNullKeySet(aRow)) {
  //this->fixPKWithSeq(aRow);
  //this->setTimeInRow(aRow);
  //this->setPersonInRow(aRow);

	//if(!util.isKeySet(aRow, notNullKeys->begin(), notNullKeys->end()) ) {
	if(!util.isKeySetCheckPK(aRow, notNullKeys->begin(), notNullKeys->end() , &primaryKeysReal) ) {
		string message = "Coloumn name "+*util.getNullKeyCheckPK(aRow, notNullKeys->begin(), notNullKeys->end(), &primaryKeysReal)+" is NULL for inserting in table " + *schemaOrder->begin();
    //LOG4CXX_ERROR(TableTemplate::logger,message);
		throw ObjectLayerException(message);
	}
      //if(isPrimaryKeySet(aRow)) {
  //if( util.isKeySet(aRow, primaryKeys->begin(), primaryKeys->end()) ) {
	//cout<<"this->makeInsertQuery(aRow)"<<endl;
  //if( util.isKeySet(aRow, primaryKeysReal.begin(), primaryKeysReal.end())) {
	vector<string> strQuery = this->makeInsertQuery(aRow);
	typedef vector<string>::iterator QueryIter;
	for(QueryIter qi = strQuery.begin(); qi != strQuery.end(); ++qi ) {
		//cout<<"Query to execute is "<<endl<<*qi<<endl;
		//LOG4CXX_INFO(TableTemplate::logger,"Query is "+*qi);
		int returnCode = dbmanager->executeQuery(*qi);
		if(returnCode != 0) {
			throw ObjectLayerException("Query: "+*qi+" could not be executed");
		}
		this->fixPKWithSeq(aRow);
	}
}

















































template SingleTableInterface<T_Personrow>;
template SingleTableInterface<T_Object_Historyrow>;
template SingleTableInterface<T_Parameter_Setrow>;
template SingleTableInterface<T_App_Familyrow>;
template SingleTableInterface<T_Applicationrow>;
template SingleTableInterface<T_App_Configrow>;
template SingleTableInterface<T_Data_Tierrow>;
template SingleTableInterface<T_Parentage_Typerow>;
template SingleTableInterface<T_Evcoll_Statusrow>;
template SingleTableInterface<T_Primary_Datasetrow>;
template SingleTableInterface<T_Processing_Namerow>;
template SingleTableInterface<T_Processingrow>;
template SingleTableInterface<T_Processed_Datasetrow>;
template SingleTableInterface<T_Event_Collectionrow>;
template SingleTableInterface<T_Evcoll_Parentagerow>;
template SingleTableInterface<T_Block_Statusrow>;
template SingleTableInterface<T_Blockrow>;
template SingleTableInterface<T_File_Statusrow>;
template SingleTableInterface<T_File_Typerow>;
template SingleTableInterface<T_Filerow>;
template SingleTableInterface<T_Evcoll_Filerow>;
