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
	bool checkInDB = false;
	string clause="";
	//cout<<"inside doSmartInsert of SingleTable"<<endl;
	LOG4CXX_DEBUG(TableTemplate<R>::logger,"inside doSmartInsert for SingleTable");
	//if( util.isKeySet(aRow, primaryKeys->begin(), primaryKeys->end()) ) {
	if( util.isKeySet(aRow, primaryKeysReal.begin(), primaryKeysReal.end()) ) {
		
		//Keys primaryKeysTmp = util.getKey(aRow, primaryKeys->begin(), primaryKeys->end()) ;
		Keys primaryKeysTmp = util.getKey(aRow, primaryKeysReal.begin(), primaryKeysReal.end()) ;
		/*for(Keys_iter i = primaryKeysTmp.begin(); i != primaryKeysTmp.end(); ++i) {
			cout<<"key is "<<*i<<endl;
		}*/
		clause = sql->makeClause(aRow, primaryKeysTmp.begin(), primaryKeysTmp.end(), multiRefrences->begin(), multiRefrences->end());
		checkInDB = true;	

	//} else if( isUniqueKeySet(aRow) ) {
	} else if( util.isListOfKeySet(aRow, uniqueKeys->begin(), uniqueKeys->end()) ) {
		Keys uniqueKeysTmp = *util.getListOfKey(aRow,uniqueKeys->begin(), uniqueKeys->end()) ;
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
		LOG4CXX_DEBUG(TableTemplate<R>::logger,"noOfRows returned from DB is ");
		LOG4CXX_DEBUG(TableTemplate<R>::logger,noOfRows);
		if( noOfRows > 0 ) {
			R* aRowFromDB = new R();
			for(int j = 0; j < noOfRows; j++ ) {
				this->convertIntoRow(rs,j,aRowFromDB);
			}
			delete rs;
			//cout<<"done  convertIntoRow"<<endl;
			bool isConsistantVal = util.isConsistant(aRowFromDB,aRow);
			delete aRowFromDB;
			if( isConsistantVal ) {
				cout<<"The data is consistant and there is no need to do insert"<<endl;
				return;
				//throw ObjectLayerException("The data is consistant and there is no need to do insert");
			} else {
				throw ObjectLayerException("Data your are trying to insert is inconsistant with data already in DB");
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


template SingleTableInterface<T_Schema_Revisionrow>;
template SingleTableInterface<T_Personrow>;
template SingleTableInterface<T_Physics_Grouprow>;
template SingleTableInterface<T_Collection_Typerow>;
template SingleTableInterface<T_App_Familyrow>;
template SingleTableInterface<T_Applicationrow>;
template SingleTableInterface<T_App_Configrow>;
template SingleTableInterface<T_Desc_Triggerrow>;
template SingleTableInterface<T_Desc_Mcrow>;
template SingleTableInterface<T_Desc_Primaryrow>;
template SingleTableInterface<T_Data_Tierrow>;
template SingleTableInterface<T_Primary_Datasetrow>;
template SingleTableInterface<T_Processing_Pathrow>;
template SingleTableInterface<T_Processed_Datasetrow>;
template SingleTableInterface<T_Event_Collectionrow>;
template SingleTableInterface<T_Analysis_Datasetrow>;
template SingleTableInterface<T_Anads_Datarow>;
template SingleTableInterface<T_Parentage_Typerow>;
template SingleTableInterface<T_Evcoll_Parentagerow>;
template SingleTableInterface<T_Block_Statusrow>;
template SingleTableInterface<T_Blockrow>;
template SingleTableInterface<T_File_Statusrow>;
template SingleTableInterface<T_File_Typerow>;
template SingleTableInterface<T_Filerow>;
template SingleTableInterface<T_Evcoll_Filerow>;
template SingleTableInterface<T_Validation_Statusrow>;
template SingleTableInterface<T_Dataset_Statusrow>;
template SingleTableInterface<T_Evcoll_Statusrow>;
template SingleTableInterface<T_Run_Qualityrow>;
template SingleTableInterface<T_Info_Anadsrow>;
template SingleTableInterface<T_Info_Evcollrow>;
template SingleTableInterface<T_Runrow>;
template SingleTableInterface<T_Evcoll_Runrow>;

