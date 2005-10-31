#include "SQL.hpp"
#include "NameMaper.hpp"
#include "RowNSchemaBinding.hpp"

#include <iostream>
using namespace std;
SQL::SQL(Util * util){
	this->SQL::util = util;
}

string SQL::makeInsertQuery(RowInterface* aRow, string tableName, string refrence, Dictionary_iter b, Dictionary_iter e) {//Schema
	string intoClause = "INSERT INTO "+ tableName  +" (";
	string valueClause = "VALUES (";
	for(Dictionary_iter i = b; i != e; ++i) {
		string tableNameTwo = util->getTokenAt(i->first,2);
		string nameTwoWithDot = "";
		if(tableNameTwo.length() != 0 ) {
			nameTwoWithDot = tableNameTwo + "." + util->getTokenAt(i->first,3);//colNameTwo
		}
		if ( (tableName != util->getTokenAt(i->first,0)) || 
			( (nameTwoWithDot != refrence) && (refrence.length() != 0) ) ) {
			continue;
		}
		if( util->isSet(aRow, i->first, i->second) ) {
			intoClause = intoClause + util->getTokenAt(i->first,1) + ",";//colNameOne
			string strValue = util->getStrValue(aRow, i->first, i->second);
			valueClause += this->formatValue(strValue, i->second) + ",";
			//cout<<"value is "<<strValue<<endl;
		} 
	}
	int len = intoClause.length() - 1;
	intoClause.erase(len,1);
	intoClause = intoClause + ") ";
	len = valueClause.length() - 1;
	valueClause.erase(len,1);
	valueClause = valueClause + ")";
	string strQuery = intoClause + " " + valueClause;
	//cout<<strQuery<<"\n"<<endl;
	return(strQuery);
}

string SQL::formatValue(string value, string dataType){
	if(dataType == "STRING" || dataType == "CHARACTER") {
		return "'"+value+"'";
	}
	if(dataType == "INTEGER" ||  dataType == "FLOAT") {
		return value;
	}
}


string SQL::makeClause(RowInterface* aRow, Keys_iter bk, Keys_iter ek, Dictionary_iter bd, Dictionary_iter ed) {
	string clause="(";
	Keys multi;
	Keys single;
	for(Keys_iter i = bk; i != ek; ++i) {
		if( util->isInMultiRef((*i), bd, ed )) {
			multi.push_back(*i);
		} else {
			single.push_back(*i);
		}
	}
	for(Keys_iter i = single.begin(); i != single.end(); ++i) {
		string dataType = util->getDataType(*i);
		clause += *i +
			 "=" + 
			this->formatValue(util->getStrValue(aRow, *i , dataType), dataType) +
			") AND (";
		
	}
	if(multi.size() > 0) {
		string lastNameOne = util->getTokenAt(*(multi.begin()),0) + "." + util->getTokenAt(*(multi.begin()),1);
		string lastTableTwo = util->getTokenAt(*(multi.begin()),2);
		for(Keys_iter i = multi.begin(); i != multi.end(); ++i) {
			string tempNameOne = util->getTokenAt(*i,0) + "." + util->getTokenAt(*i,1);
			string tempTableTwo = util->getTokenAt(*i,2);
			string dataType = util->getDataType(*i);
			clause += tempNameOne +
				 "=" + 
				this->formatValue(util->getStrValue(aRow, *i , dataType), dataType);
			if(lastNameOne == tempNameOne &&
			lastTableTwo == tempTableTwo ) {
				clause +=  "   OR  ";
			} else {
				clause +=  ") AND (";
			}
			lastNameOne = tempNameOne;
			lastTableTwo = tempTableTwo;
		}
	}

	if( clause.length() > 1 ) {
			clause = util->eraseEndChars(clause,7) + ")";
		} else {
			 clause = "";
		}
	//cout<<"clause "<<clause<<endl;
	return clause;
}


string SQL::makeRefClause(Dictionary_iter b, Dictionary_iter e) {
	string whereClause = "" ;
	for(Dictionary_iter i = b; i != e; ++i) {
		whereClause = whereClause + "(" + 
				i->first + " = " + 
				i->second + " ) AND ";
	}
	return util->eraseEndChars(whereClause,4);
}

string SQL::makeMultiRefClause(Dictionary_iter b, Dictionary_iter e) {
	string multiWhereClause = "";
	if( b != e ) {
		string lastTableOne = util->getTokenAt(b->first,0);
		string lastNameTwo = b->second;
		multiWhereClause = "(";
		for(Dictionary_iter i = b; i != e; ++i) {
			string tableOne = util->getTokenAt(i->first,0);
			if( (lastTableOne == tableOne) && (lastNameTwo == i->second) ) {
				multiWhereClause = multiWhereClause +  
							i->first + " = " + 
							i->second + " OR " ;
			} else {
				multiWhereClause = util->eraseEndChars(multiWhereClause,3) + 
							") AND ( " + i->first + 
							" = " + i->second + " OR ";
			}
			lastTableOne = tableOne;
			lastNameTwo = i->second;
		}
		if( multiWhereClause.length() > 1 ) {
			multiWhereClause = util->eraseEndChars(multiWhereClause,3) + ")";
		} else {
			 multiWhereClause = "";
		}
	}
	return multiWhereClause;
} 
/*string SQL::makeSelectClause(Dictionary_iter b, Dictionary_iter e) {//Schema
	string selectClause = "SELECT ";
	for(Dictionary_iter i = b; i != e; ++i) {
		//cout<<selectClause<<"\n"<<endl;
		selectClause = selectClause + util->getTokenAt(i->first,0) + 
				"." + util->getTokenAt(i->first, 1) + 
				" AS \"" + i->first + "\",\n" ;
	}
	selectClause = util->eraseEndChars(selectClause,2);
	return selectClause;
}*/
string SQL::makeSelectClause(Dictionary_iter b, Dictionary_iter e) {//Schema
	NameMaper nm;
	string selectClause = "SELECT ";
	for(Dictionary_iter i = b; i != e; ++i) {
		//cout<<selectClause<<"\n"<<endl;
		//NmIterator ni = nm.NameMap.find(i->first);
		Dictionary_iter ni = nm.NameMap.find(i->first);
		if(ni == nm.NameMap.end()) {
			cout<<"NOT FOUND in NAMEMAPPER "<<i->first<<endl;
			return("");
		}
		
		selectClause = selectClause + util->getTokenAt(i->first,0) + 
				"." + util->getTokenAt(i->first, 1) + 
				" AS \"" + ni->second + "\",\n" ;
	}
	selectClause = util->eraseEndChars(selectClause,2);
	return selectClause;
}

string SQL::makeTableClause(Keys_iter b, Keys_iter e) {
	string tableNames = "";
	for(Keys_iter i = b; i != e; ++i) {
		tableNames = tableNames + *i + ",";
	}
	tableNames = util->eraseEndChars(tableNames,1);
	return tableNames;
}


string SQL::makeSeqQuery(string tableName, string colName) {
	return (string)("SELECT max("+colName+") FROM "+tableName);
}


