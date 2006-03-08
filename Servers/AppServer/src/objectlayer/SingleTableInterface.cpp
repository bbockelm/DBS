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
	bool checkInDB = false;
	string clause="";
	//cout<<"inside doSmartInsert of SingleTable"<<endl;
	LOG4CXX_DEBUG(TableTemplate<R>::logger,"SingleTableInterface::doSmartInsert");
	//if( util.isKeySet(aRow, primaryKeys->begin(), primaryKeys->end()) ) {
	//cout<<"calling util.isKeySetCheckNull(aRow, primaryKeysReal.begin(), primaryKeysReal.end(), notNullKeys )"<<endl;
	if( util.isKeySetCheckNull(aRow, primaryKeysReal.begin(), primaryKeysReal.end(), notNullKeys )) {
		//cout<<"I GOT PRIMARY KEY"<<endl;
	//if( util.isKeySet(aRow, primaryKeysReal.begin(), primaryKeysReal.end()) ) {
		
		Keys primaryKeysTmp = util.getKey(aRow, primaryKeysReal.begin(), primaryKeysReal.end()) ;
		clause = sql->makeClause(aRow, primaryKeysTmp.begin(), primaryKeysTmp.end(), multiRefrences->begin(), multiRefrences->end());
		checkInDB = true;	

	} else if( util.isListOfKeySet(aRow, uniqueKeys->begin(), uniqueKeys->end() , notNullKeys )) {
		//cout<<"I GOT UNIQUE KEY"<<endl;
		Keys uniqueKeysTmp = *util.getListOfKey(aRow,uniqueKeys->begin(), uniqueKeys->end() , notNullKeys) ;
		clause = sql->makeClause(aRow, uniqueKeysTmp.begin() , uniqueKeysTmp.end(), multiRefrences->begin(), multiRefrences->end());
		checkInDB = true;
	}
	if(checkInDB) {
		//cout<<"calling in SingleTableInterface.cpp  ResultSet* rs = this->doSelect("",clause);"<<endl;
		ResultSet* rs = this->doSelect("",clause);
		this->reSetColNamesInRS(rs);
		//cout<<"called doSelect done"<<endl;
		int noOfRows = rs->getNoOfRows();
		//cout<<"noOfRows returned from DB is "<<noOfRows<<endl;
		LOG4CXX_DEBUG(TableTemplate<R>::logger,"Number of Rows returned from DB is "+util.itoa(noOfRows));
                //cout << "This message is coming from here" << endl; 
		if( noOfRows > 0 ) {
			R* aRowFromDB = new R();
			for(int j = 0; j < noOfRows; j++ ) {
				this->convertIntoRow(rs,j,aRowFromDB);
			}
			delete rs;
			//cout<<"done  convertIntoRow"<<endl;
			string message;
			LOG4CXX_DEBUG(TableTemplate<R>::logger,"Checking consistancy between data provided and data fetched from DB");
			bool isConsistantVal = util.isConsistant(aRowFromDB, aRow, message);
			delete aRowFromDB;
			if( isConsistantVal ) {
				LOG4CXX_DEBUG(TableTemplate<R>::logger,"The data is CONSISTANT and there is no need to do insert");
				return;
			} else {
				throw ObjectLayerException("Data your are trying to insert is inconsistant with data already in DB\n"+message);
			}
		} else {
			delete rs;
			doSimpleInsert(aRow);
		}
	} else {
		//this->fixPKWithSeq(aRow);
		doSimpleInsert(aRow);
		//throw ObjectLayerException("Neither Primary key nor any Unique Key is specified");
	}


}

/*
template <class R>
void SingleTableInterface<R>::delRows() {
	delRow();
}
*/



















































template SingleTableInterface<T_Personrow>;
template SingleTableInterface<T_Object_Historyrow>;
template SingleTableInterface<T_App_Familyrow>;
template SingleTableInterface<T_Applicationrow>;
template SingleTableInterface<T_App_Configrow>;
template SingleTableInterface<T_Data_Tierrow>;
template SingleTableInterface<T_Primary_Datasetrow>;
template SingleTableInterface<T_Processing_Pathrow>;
template SingleTableInterface<T_Processed_Datasetrow>;
template SingleTableInterface<T_Event_Collectionrow>;
template SingleTableInterface<T_Parentage_Typerow>;
template SingleTableInterface<T_Evcoll_Parentagerow>;
template SingleTableInterface<T_Block_Statusrow>;
template SingleTableInterface<T_Blockrow>;
template SingleTableInterface<T_File_Statusrow>;
template SingleTableInterface<T_File_Typerow>;
template SingleTableInterface<T_Filerow>;
template SingleTableInterface<T_Evcoll_Filerow>;
template SingleTableInterface<T_Info_Evcollrow>;