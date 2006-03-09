#include "TableTemplate.hpp"
#include "TableFactory.hpp"
#include "ObjectLayerTables.hpp"
#include "ObjectLayerException.hpp"
#include "DBException.hpp"
#include "NameMaper.hpp"
#include "Util.hpp"
#include "Log.hpp"
#include <log4cxx/logger.h>
#include <exception>


///Default constructor
template <class R>
TableTemplate<R>::TableTemplate(){
  this->init();
};

template <class R>
void TableTemplate<R>::dispose(){
	for(rowIterator = rows.begin(); rowIterator != rows.end(); ++rowIterator ) {
		delete *rowIterator;
	}
}


template <class R>
TableTemplate<R>::~TableTemplate(){
/*  for(rowIterator = rows.begin(); 
      rowIterator != rows.end(); 
      ++rowIterator )	{
    delete *rowIterator;
  }*/
	this->dispose();
	delete sql;
	/*for(tivIterator = tiv.begin(); 
	  tivIterator != tiv.end(); 
	  ++tivIterator )	{
	  delete *tivIterator;
	  }*/
	
};

template <class R>
TableTemplate<R>::TableTemplate(DBManagement* dbmanager){
  this->dbmanager = dbmanager;
  this->init();
};

template <class R>
void TableTemplate<R>::setDBManager(DBManagement* dbmanager){
  this->dbmanager = dbmanager;
}

template <class R>
void TableTemplate<R>::addRow(RowInterface* aRow) {
        this->rows.push_back((R*)aRow);
}


template <class R>
void TableTemplate<R>::addRow(R* aRow) {
  this->rows.push_back(aRow);
}

template <class R>
string* TableTemplate<R>::getTableName(void) {
  return this->tableName;
}

template <class R>
Dictionary* TableTemplate<R>::getSchema() {
  //cout<<"RETURNNING SCHEMA..."<<endl;
  return this->schema;
}

template <class R>
Dictionary* TableTemplate<R>::getMultiRefrence() {
  //cout<<"RETURNNING multiRefrences SCHEMA..."<<endl;
  return this->multiRefrences;
}

template <class R>
vector<R*>& TableTemplate<R>::getRows() {
	return this->rows;
}

template <class R>
int TableTemplate<R>::getNoOfRows() {
	return this->rows.size();
}

template <class R>
string TableTemplate<R>::getStrValue(int index, string name) {
	if(rows.size() < index) {
		return "";
	}
	string dataType = util.getDataType(name);
	return util.getStrValue((RowInterface*)rows.at(index), name, dataType);
}

template <class R>
void TableTemplate<R>::init() {
  //cout<<"initilizing......"<<endl;
  static Log l("TableTemplate");
  TableTemplate::logger = l.getLogger();
	schema = this->schemaNconstraints.schemaNconstraints.getSchema();
	constraints = this->schemaNconstraints.schemaNconstraints.getConstraints();
	primaryKeys = this->schemaNconstraints.schemaNconstraints.getPrimaryKeys();
	//cout<<" Line 1"<<endl;
	uniqueKeys = this->schemaNconstraints.schemaNconstraints.getUniqueKeys();
	notNullKeys = this->schemaNconstraints.schemaNconstraints.getNotNullKeys();
	refrences = this->schemaNconstraints.schemaNconstraints.getReferences();
	multiRefrences = this->schemaNconstraints.schemaNconstraints.getMultiReferences();
	tableName = this->schemaNconstraints.schemaNconstraints.getTableName();
	//cout<<"table name is set now "<<*tableName<<endl;
	//LOG4CXX_DEBUG(TableTemplate::logger, "table name is set now");
	schemaOrder = this->schemaNconstraints.schemaNconstraints.getSchemaOrder();
	util.setSchema(schema);
	sql = new SQL(&util);
	//cout<<"primary keys "<<endl;
	primaryKeysReal = util.getPrimaryKeys(schemaOrder->begin(), schemaOrder->end(), multiRefrences->begin(), multiRefrences->end());
	//cout<<"DONE initilizing......"<<endl;
};


template <class R>
Dictionary TableTemplate<R>::getSatisfiedRefrences(ResultSet* rs, int rowIndex) {
  Dictionary satisfiedRefrences;
  for(Dictionary_iter i = multiRefrences->begin(); i != multiRefrences->end(); ++i) {
    if( (string) rs->getElement(rowIndex, rs->getColIndex(i->first)) == 
	(string) rs->getElement(rowIndex, rs->getColIndex(i->second + "." + i->first)) ) {
      //cout<<"Satisfied relation "<<util.getTokenAt(i->second,0)<<" is equal to "<<i->first<<" value is "<<(string) rs->getElement(rowIndex, rs->getColIndex(i->first))<<endl;
      //LOG4CXX_DEBUG(TableTemplate::logger, "Satisfied relation "+util.getTokenAt(i->second,0) + " is equal to " + i->first + " value is " + (string) rs->getElement(rowIndex, rs->getColIndex(i->first)) );
			satisfiedRefrences.insert(Entry(util.getTokenAt(i->second,0),i->first));
    }
    
  }
  return(satisfiedRefrences);
}

template <class R>
void TableTemplate<R>::convertIntoRow(ResultSet* rs, int rowIndex, R* tmpRow) {
  //cout << "Entering convertIntoRow\n"<<endl;
  //NOTE NOTE NOTE
  //COMMENTING Satisfied refrences beacus ether are no MultiRefrences in schema.
  // UNcomment them if you multi refrences come back
  //Dictionary satisfiedRefrences = this->getSatisfiedRefrences(rs,rowIndex);
	for(int colIndex = 0; colIndex < rs->getNoOfCols(); ++colIndex) {
		string name = rs->getColName(colIndex);
		/*if( name.length() == 63 ) {
		  for(Dictionary_iter schemaIterator = schema->begin(); 
		      schemaIterator != schema->end(); schemaIterator++) {
		    string nameFromSchema = schemaIterator->first;
		    if(nameFromSchema.substr(0,63) == name) {
		      name = nameFromSchema;
		      break;
				}
		  }
		}
		if(!util.toSetCol(name, satisfiedRefrences.begin(), satisfiedRefrences.end())) {
		  continue;
		} */
		string value = rs->getElement(rowIndex, colIndex);
		if(value.length() == 0) { 
			continue;
		}
		string dataType = util.getDataType(name);
		util.setValue(tmpRow, name, dataType, value);
	}
}

template <class R>
string TableTemplate<R>::makeSelectQuery(string userGivenWhereClause="") {
	//cout<<"inside ::makeSelectQuery"<<endl;
	//cout<<"TableTemplate<R>::makeSelectQuery userGivenWhereClause "<<userGivenWhereClause<<endl;
	string sqlQuery = sql->makeSelectClause(schema->begin(), schema->end()) +
		 " FROM " +  
		sql->makeTableClause(schemaOrder->begin(), schemaOrder->end());
	string whereClause = this->makeWhereClause(userGivenWhereClause);
	if( whereClause.length() > 0 ) {
		sqlQuery = sqlQuery + "\nWHERE\n" + whereClause;
	}
	cout<<"Query is "<<endl<<sqlQuery<<endl;
	//LOG4CXX_INFO(logger,"Query is " + sqlQuery);
	return (sqlQuery);
}


template <class R>
string TableTemplate<R>::makeWhereClause(string userGivenWhereClause="") {
	//cout<<"inside ::makeWhereClause"<<endl;
  string whereClause = sql->makeRefClause(refrences->begin(), refrences->end()) ;
  string whereMultiRefClause = sql->makeMultiRefClause(multiRefrences->begin(), multiRefrences->end()) ;
  int whereClauseLen = whereClause.length();
  if(whereClauseLen > 0) {
    if(whereMultiRefClause.length() > 0) {
      whereClause += " AND " + whereMultiRefClause;
    }
	}else if(whereMultiRefClause.length() > 0) {
    whereClause = whereMultiRefClause;
  }
  if( whereClauseLen == 0 ) {
    return(userGivenWhereClause);
  }
  if( userGivenWhereClause.length() > 0 ) {
		whereClause += " AND " + userGivenWhereClause ;
  }
  return(whereClause);
}

template <class R>
ResultSet * TableTemplate<R>::doSelect(string query="", string whereClause="") {
	//return query.length() == 0 ? 
	//	dbmanager->executeQueryWithResults(this->makeSelectQuery(whereClause)) : 
	//	dbmanager->executeQueryWithResults(query) ;
  string tempQuery = "";
  if( query.length() == 0 ) {
    tempQuery = this->makeSelectQuery(whereClause);
	} else {
    tempQuery = query;
  }
  //LOG4CXX_INFO(TableTemplate::logger,"Query is " + tempQuery);
  //cout<<"calling dbmanager->executeQueryWithResults(tempQuery)"<<endl;
  return dbmanager->executeQueryWithResults(tempQuery) ;

}

template <class R>
void TableTemplate<R>::reSetColNamesInRS(ResultSet * rs) {
  NameMaper nm;
	for(int i = 0; i < rs->getNoOfCols(); ++i) {
	  string colName = rs->getColName(i);
	  bool found = false;
	  //for(NmIterator ni = nm.NameMap.begin(); ni != nm.NameMap.end(); ++ni) {
	  for(Dictionary_iter ni = nm.NameMap.begin(); ni != nm.NameMap.end(); ++ni) {
	    if(ni->second == colName) {
				rs->setColName(i, ni->first);
				found = true;
				break;
	    }
	  }
		if(!found) {
		  throw ObjectLayerException("Name Maper could not found key "+colName);
		}
	}
}

template <class R>
vector<R*>& TableTemplate<R>::select(string whereClause=""){
	bool exceptionOccured = false;
	string exceptionMessage = "\n";
  
	//ResultSet * rs = this->doSelect("",whereClause);
	ResultSet * rs;
	try {
		rs = this->doSelect("",whereClause);
		this->reSetColNamesInRS(rs);
    
		//cout<<"NOOFROWS iS "<<rs->getNoOfRows()<<endl<<endl;
                //cout<<"HERE>>>>>>>>>>>MESSAGE...comes..."<< endl; 
		//LOG4CXX_DEBUG(TableTemplate::logger,"Number of Rows returned from DB is "+util.itoa(rs->getNoOfRows()));
		for(int rowIndex = 0; rowIndex < rs->getNoOfRows(); rowIndex++) {
			//cout << "\nChecking.... " << endl;
			/*bool pKEqual = false;
			for(rowIterator = rows.begin(); rowIterator != rows.end(); ++rowIterator ) {
				R* aRow = (R*)*rowIterator;
				pKEqual = false;
				for(Keys_iter i = primaryKeys->begin(); i!= primaryKeys->end(); ++i) {
         					string dataType = util.getDataType(*i) ;
						if (util.isSet(aRow, *i ,dataType) ) {
							if(dataType == "INTEGER") {
								int compareA = util.atoi(rs->getElement(rowIndex, rs->getColIndex(*i)));
								int compareB =  util.atoi(util.getStrValue(aRow, *i , dataType));
								if(compareA != compareB) {
									pKEqual = false;
									break;
								} else {
									pKEqual = true;
								}
							
							} else {
								//cout<<"\nPK IS"<< *i;
								//cout <<"\n(string) rs->getElement(rowIndex, rs->getColIndex(*i))" << (string) rs->getElement(rowIndex, rs->getColIndex(*i));
								//cout<<"\nutil.getStrValue(aRow, *i , dataType)" << util.getStrValue(aRow, *i , dataType);
								if( ((string) rs->getElement(rowIndex, rs->getColIndex(*i))).compare(util.getStrValue(aRow, *i , dataType) ) != 0){
                                                        
							//if( (string) rs->getElement(rowIndex, rs->getColIndex(*i)) != 
							//	util.getStrValue(aRow, *i , dataType) ) {
									pKEqual = false;
									break;
          
								} else {
									pKEqual = true;
								}
							}
						}
				}
				if(pKEqual) {
					 //cout<<"ITSEQUAL "<<endl;
					////LOG4CXX_DEBUG(TableTemplate::logger,"ITSEQUAL");
					this->convertIntoRow(rs,rowIndex,aRow);
					break;
				}
			}*/
			//if(!pKEqual) {
				//cout<<"INSERT BEACUSE it is UNEQUAL"<<endl;
				//LOG4CXX_DEBUG(TableTemplate::logger,"INSERT BEACUSE it is UNEQUAL");
				R* tempRow = new R();
				//cout<<"calling convert into rows"<<endl;
				this->convertIntoRow(rs, rowIndex,tempRow);
				rows.push_back(tempRow);
				//cout<<"line11"<<endl;
			//}
		}
		delete rs;
		//LOG4CXX_DEBUG(TableTemplate::logger,"ROWS BUILD SUCCESFULLY");
		//rowIterator = rows.begin();
	} catch (ObjectLayerException &e) {
		exceptionOccured = true;
		exceptionMessage += e.report() + "\n";
		delete rs;
	} catch (DBException &e) {
		exceptionOccured = true;
		exceptionMessage += e.report() + "\n";
	} catch (exception &e) {
		exceptionOccured = true;
		exceptionMessage += e.what();
		delete rs;
	}
  
	//return this->rowIterator;
	if(exceptionOccured) {
		//LOG4CXX_ERROR(TableTemplate::logger,exceptionMessage);
		throw ObjectLayerException(exceptionMessage);
	}
  
	return rows;
	//return *this;
}

template <class R>
vector<string> TableTemplate<R>::makeInsertQuery(R* aRow) {
  vector<string> toReturn;
	for(Keys_iter i = schemaOrder->begin();
	    i != schemaOrder->end(); i++) {
	  if( util.isInMultiRef(*i, multiRefrences->begin(), multiRefrences->end()) ) {
	    for(Dictionary_iter m = multiRefrences->begin(); 
		m != multiRefrences->end(); 
				m++) {
	      if(*i == util.getTokenAt(m->second,0) ) {
		//cout<<"Multi FOUND" <<endl;
		toReturn.push_back( sql->makeInsertQuery(aRow,*i,m->first, schema->begin(), schema->end()) );
	      }
	    }
	  } else {
			toReturn.push_back( sql->makeInsertQuery(aRow, *i, "", schema->begin(), schema->end()) );
	  }
        }
	return(toReturn);
}



/*
  template <class R>
  void TableTemplate<R>::insert() {
  for(rowIterator = rows.begin(); rowIterator != rows.end(); rowIterator++ ) {
  R* aRow = (R*)*rowIterator;
  makeInsertQuery(aRow);
  }
}
*/

template <class R>
void TableTemplate<R>::insert() {
	//cout<<"inside insert of TableTemplate"<<endl;
	bool exceptionOccured = false;
	string exceptionMessage = "\n";
	int i = -1;
	for(rowIterator = rows.begin(); rowIterator != rows.end(); ++rowIterator ) {
	  ++i;
	  R* aRow = (R*)*rowIterator;
	  try{
	    //cout<<endl<<"inserting ROW no "<<i<<endl;
			//cout<<"\n\n\n"<<endl;
			//LOG4CXX_DEBUG(TableTemplate::logger,"");
			//LOG4CXX_DEBUG(TableTemplate::logger,"*******************BEGIN**********************");
			//LOG4CXX_DEBUG(TableTemplate::logger,"Inserting Row Number " + util.itoa(i));
			this->doSmartInsert(aRow);
			//LOG4CXX_DEBUG(TableTemplate::logger,"*******************END**********************");
			//LOG4CXX_DEBUG(TableTemplate::logger,"");
			//cout<<"\n\n\n"<<endl;
			//cout<<"out of smart insert"<<endl;
			//doSimpleInsert(aRow);
		} catch (ObjectLayerException &e) {
			exceptionOccured = true;
			//exceptionMessage += "At row "+util.itoa(i)+": "+ e.report() + " \n";
			exceptionMessage +=  e.report() + " \n";
		} catch (DBException &e) {
			exceptionOccured = true;
			//exceptionMessage += "At row "+util.itoa(i)+": "+ e.report() + " \n";
			exceptionMessage +=  e.report() + " \n";
		} catch (exception &e) {
			exceptionOccured = true;
			//exceptionMessage += "At row "+util.itoa(i)+": "+ e.what() + " \n";
			exceptionMessage +=  (string)e.what() + (string)" \n";
		}
	  
    	}
	//cout<<"OUT of FOR Loop"<<endl;
	if(exceptionOccured) {
	  //LOG4CXX_ERROR(TableTemplate::logger,exceptionMessage);
	  throw ObjectLayerException(exceptionMessage);
	}
	//cout<<"returnning from insert in TableTamplate"<<endl;
}


template <class R>
void TableTemplate<R>::doSimpleInsert(R* aRow) {
  //cout<<"inside doSimpleInsert"<<endl;
  //LOG4CXX_DEBUG(TableTemplate::logger,"TableTemplate::doSimpleInsert");
  //if(!isNotNullKeySet(aRow)) {
  this->fixPKWithSeq(aRow);
  //this->setTimeInRow(aRow);
  //this->setPersonInRow(aRow);
  if(!util.isKeySet(aRow, notNullKeys->begin(), notNullKeys->end()) ) {
    string message = "Coloumn name "+*util.getNullKey(aRow, notNullKeys->begin(), notNullKeys->end())+" is NULL";
    //LOG4CXX_ERROR(TableTemplate::logger,message);
    throw ObjectLayerException(message);
    
  }
  //this->fixPKWithSeq(aRow);
  //if(isPrimaryKeySet(aRow)) {
  //if( util.isKeySet(aRow, primaryKeys->begin(), primaryKeys->end()) ) {
  if( util.isKeySet(aRow, primaryKeysReal.begin(), primaryKeysReal.end())) {
    vector<string> strQuery = this->makeInsertQuery(aRow);
    typedef vector<string>::iterator QueryIter;
		for(QueryIter qi = strQuery.begin(); qi != strQuery.end(); ++qi ) {
		  //cout<<"Query to execute is "<<endl<<*qi<<endl;
		  
		  //LOG4CXX_INFO(TableTemplate::logger,"Query is "+*qi);
		  int returnCode = dbmanager->executeQuery(*qi);
		  if(returnCode != 0) {
		    throw ObjectLayerException("Query: "+*qi+" could not be executed");
		  }
		}
  } else {
    throw ObjectLayerException("Sequencers falied to set Primary key ");
  }
}

/*
This is very important . This will make sure the rows are emptied so that they do not get deleted from ::insertSingle
*/
template <class R>
void TableTemplate<R>::delRows() {
  //cout<<"ERASING rows"<<endl;
  rows.clear();
}


template <class R>
void TableTemplate<R>::insertSingle(R* aRow, string name, string fkey) {
  name += "row";
 // cout<<"name is "<<name<<" fkey is "<<fkey<<endl;
	////LOG4CXX_INFO(TableTemplate::logger,"------------------------------------------");
	//LOG4CXX_INFO(TableTemplate::logger,"Table Name is "+ name + " fkey is " + fkey);
	//LOG4CXX_INFO(TableTemplate::logger,"------------------------------------------");
        //cout<<"PRE RowInterface* subRow"<<endl;
	RowInterface* subRow = (RowInterface*)aRow->getConstituentRow(name,fkey);
        //cout<<"POST RowInterface* subRow"<<endl;
	//LOG4CXX_INFO(TableTemplate::logger,"RowInterface* subRow = (RowInterface*)aRow->getConstituentRow(name,fkey)");
	//cout<<"RowInterface* subRow = (RowInterface*)aRow->getConstituentRow(name,fkey) "<<endl;	  
TableFactory tf;
  //cout<<"calling tf.getTableObject"<<endl;
  TableInterface* ti = tf.getTableObject(name);
  ti->setDBManager(dbmanager);
  //cout<<"Done calling tf.getTableObject"<<endl;
  ti->addRow(subRow);
	try {
		ti->insert();
	} catch (ObjectLayerException &e) {
		ti->delRows();
		delete ti;
		util.equatePKWithRef(aRow, refrences->begin(), refrences->end() );
		util.equatePKWithMultiRef(aRow, multiRefrences->begin(), multiRefrences->end());
		throw ObjectLayerException(e.report());
	}
  ti->delRows();
  delete ti;
  
  util.equatePKWithRef(aRow, refrences->begin(), refrences->end() );
  util.equatePKWithMultiRef(aRow, multiRefrences->begin(), multiRefrences->end());
  //LOG4CXX_INFO(TableTemplate::logger,"Out from insertSingle");
  
}




template <class R>
void TableTemplate<R>::insertMulti(R* aRow, string name) {
  bool exceptionOccured = false;
  string exceptionMessage = "\n";
  
	for(Dictionary_iter m = multiRefrences->begin(); m != multiRefrences->end(); ++m) {
	  try {
	    //cout<<"checking name "<<name<<" with util.getTokenAt(m->second,0) "<<util.getTokenAt(m->second,0)<<endl;
	    if(name == util.getTokenAt(m->second,0) ) {
	     this->insertSingle(aRow, name, m->first);
	    }
	  } catch (ObjectLayerException &e) {
			exceptionOccured = true;
			exceptionMessage += name+": "+ e.report() + " \n";
	  } catch (DBException &e) {
	    exceptionOccured = true;
	    exceptionMessage += name+": "+ e.report() + " \n";
	  } catch (exception &e) {
			exceptionOccured = true;
			exceptionMessage += name+": "+ e.what() + " \n";
	  }
	  
	}
	if(exceptionOccured) {
		throw ObjectLayerException(exceptionMessage);
	}
}

template <class R>
void TableTemplate<R>::update(){
	  cout << "Hello I am in Update" << endl;
}

template <class R>
int TableTemplate<R>::getSeqValue(string tableName, string colName) {
	ResultSet* rs = this->doSelect(sql->makeSeqQuery(tableName,colName),"");
	if(rs->getNoOfRows() == 0) {
		delete rs;
		return 0;
	}
	int intValue  = atoi(rs->getElement(0,0).c_str());
	//cout<<"intValue is "<<intValue<<endl;
	delete rs;
	return ++intValue;
	
}

template <class R>
void TableTemplate<R>::fixPKWithSeq(R* aRow) {
	for(Keys_iter i = primaryKeysReal.begin(); i != primaryKeysReal.end(); ++i) {

		//string dataType = "INTEGER";
		string dataType = util.getDataType(*i);
		if( util.isSet(aRow, *i, dataType) ) {
			continue;
		}
		if(dataType != "INTEGER") {
			cout<<"Sequencer for non int type is invalid"<<endl;
			//LOG4CXX_ERROR(TableTemplate::logger,"Sequencer for non int type is invalid");
			continue;
		}
		int value = this->getSeqValue(util.getTokenAt(*i, 0), util.getTokenAt(*i, 1));	
		//int value = 1;	
		cout<<"Setting sequencer value of "<<*i<<" "<<value<<endl;
		//LOG4CXX_DEBUG(TableTemplate::logger,"Setting sequencer value of " + *i);
		//LOG4CXX_DEBUG(TableTemplate::logger,value);
		aRow->setValue(*i,&value);
	}
}

template <class R>
void TableTemplate<R>::setTimeInRow(R* aRow) {
	string tableName = this->util.getTokenAt(schema->begin()->first,0);
	long value = this->util.getTime();
	//float value = this->util.getTime();
	//cout<<"setting "<<tableName<<".created_at to "<<value<<endl;
	aRow->setValue(tableName+".created_at",&value);
	aRow->setValue(tableName+".modified_at",&value);
}

template <class R>
void TableTemplate<R>::setPersonInRow(R* aRow) {
	string tableName = this->util.getTokenAt(schema->begin()->first,0);
	int value = 1;
	//cout<<"setting "<<tableName<<".created_by to "<<value<<endl;
	aRow->setValue(tableName+".created_by",&value);
	aRow->setValue(tableName+".modified_by",&value);
}



















































template TableTemplate<T_Personrow>;
template TableTemplate<T_Object_Historyrow>;
template TableTemplate<T_App_Familyrow>;
template TableTemplate<T_Applicationrow>;
template TableTemplate<T_App_Configrow>;
template TableTemplate<T_Data_Tierrow>;
template TableTemplate<T_Primary_Datasetrow>;
template TableTemplate<T_Processing_Pathrow>;
template TableTemplate<T_Processed_Datasetrow>;
template TableTemplate<T_Event_Collectionrow>;
template TableTemplate<T_Parentage_Typerow>;
template TableTemplate<T_Evcoll_Parentagerow>;
template TableTemplate<T_Block_Statusrow>;
template TableTemplate<T_Blockrow>;
template TableTemplate<T_File_Statusrow>;
template TableTemplate<T_File_Typerow>;
template TableTemplate<T_Filerow>;
template TableTemplate<T_Evcoll_Filerow>;
template TableTemplate<T_Info_Evcollrow>;
template TableTemplate<Evcollviewmultirow>;
template TableTemplate<Fileviewmultirow>;
template TableTemplate<Pdblockviewmultirow>;
template TableTemplate<Blockviewmultirow>;
template TableTemplate<Primarydatasetmultirow>;
template TableTemplate<Processingpathmultirow>;
template TableTemplate<Crabevcollviewmultirow>;
