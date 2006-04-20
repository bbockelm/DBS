#include <iostream.h>
#include "ResultSet.hpp"
#include "DBException.hpp"
using namespace std;

/*ResultSet::ResultSet(int noOfRows, int noOfCols) {
	this->ResultSet::noOfRows = noOfRows;
	this->ResultSet::noOfCols = noOfCols;
}*/



ResultSet::ResultSet(SQLHDBC connHandle,SQLHSTMT stmtHandle) {
	dbUtil.setStmtHandle(stmtHandle);
	dbUtil.setConnHandle(connHandle);
	this->stmtHandle = stmtHandle;

	SQLSMALLINT noOfCols = dbUtil.getNoOfCols();
	SQLSMALLINT colNameLen[noOfCols];
	SQLSMALLINT colType;
	SQLUINTEGER colSize;
	SQLSMALLINT colScale;
	buff = new SQLCHAR*[noOfCols];
	len = new SQLINTEGER[noOfCols];
	buffLen = new SQLINTEGER[noOfCols];
	SQLCHAR colName[MAX_COL_NAME_LEN];
	//cout<<"dbUtil.getNoOfCols() "<<noOfCols<<endl;
	this->noOfCols = noOfCols;
	for (int i = 0 ; i < noOfCols; i++) {
		returnCode = SQLDescribeCol(stmtHandle,
					(SQLSMALLINT)(1 + i),
					colName,
					MAX_COL_NAME_LEN,
					&colNameLen[i],
					&colType,
					&colSize,
					&colScale,
					NULL);
		if (!dbUtil.isSuccess()) {
			dbUtil.doDiagnostics();
			dbUtil.freeStmtHandle();
			for(int j = 0; j <= i; j++) {
				delete[] buff[j];
			}
			delete[] len;
			delete[] buffLen;
			delete[] buff;
			throw DBException("Error in fetching coloum information: SQLDescribeCol");
		}
		this->addColName((string)((char*)colName));
		buffLen[i] = colSize + 1;
		buff[i] = new SQLCHAR[buffLen[i]];
		returnCode = SQLBindCol(stmtHandle,
				(SQLSMALLINT)(i + 1 ),
				SQL_C_CHAR,
				buff[i],
				buffLen[i],
				&len[i]);
		if (!dbUtil.isSuccess()) {
			dbUtil.doDiagnostics();
			dbUtil.freeStmtHandle();
			for(int j = 0; j <= i; j++) {
				delete[] buff[j];
			}
			delete[] len;
			delete[] buffLen;
			delete[] buff;

			throw DBException("Error in binding data types with coloums: SQLBindCol");
		}
		
	}

}
/*ResultSet::ResultSet() {
}


void ResultSet::setStmtHandle(SQLHSTMT stmtHandle) {
	this->stmtHandle = stmtHandle;
}
*/
void ResultSet::setNoOfRows(int noOfRows) {
        this->ResultSet::noOfRows = noOfRows;
}

void ResultSet::setNoOfCols(int noOfCols){
        this->ResultSet::noOfCols = noOfCols;
}



ResultSet::~ResultSet(){
	dbUtil.freeStmtHandle();
	for(int i = 0; i < noOfCols; i++) {
		delete[] buff[i];
	}
	delete[] len;
	delete[] buffLen;
	delete[] buff;

}

void ResultSet::reset() {
	SQLSetPos(this->stmtHandle, 0,SQL_POSITION,SQL_LOCK_NO_CHANGE);
}

bool ResultSet::next() {
	//cout<<"NEXT of ResultSet"<<endl;
	if ( (returnCode = SQLFetch(stmtHandle) ) == SQL_NO_DATA ) {
		return false;
	} else {
		return true;
	}
}

string ResultSet::getElement(int col) {
	if(col < 0) {
		return (string)"";
	}
	if (returnCode != SQL_NO_DATA) {
		int length = (int)strlen((char*)buff[col]);
		if( length > 0 ) {
			return (string)((char*)buff[col]);
		} else {
			return (string)"";
		}
	}
	return (string)"";
}

string ResultSet::getElement(int row, int col) {
	int index = row*noOfCols + col;
	return(index < data.size() ? *(data.begin() + index) : "");
}

void ResultSet::addElement(string value){
	//cout<<"value "<<value<<endl;
	data.push_back(value);
}


void ResultSet::addColName(string value){
	colName.push_back(value);
}
string ResultSet::getColName(int index){
	return(index < colName.size() ? *(colName.begin() + index) : "");
}
void ResultSet::setColName(int index, string value) {
	if( index < colName.size() ) {
		colName[index] = value;
	}
}

int ResultSet::getColIndex(string colNameIn) {
	int colNameInLen = colNameIn.length();
	int index = 0;
	for(ColIterator i = colName.begin(); 
			i != colName.end() ;
			++i ) {
		string name = *i;
		int nameLen = name.length();
		if( colNameIn == name ) {
			return(index);
		}
		if( (colNameInLen > nameLen ) && ( nameLen == 63 ) ) {
			if(colNameIn.substr(0,nameLen) == name) {
				return(index);
			}
		}
		++index;

	}
	return(-1);
}

int ResultSet::getNoOfRows(){
	return(noOfRows);
}

int ResultSet::getNoOfCols(){
	return(noOfCols);
}



/*int main(int argc,char *argv[]) {
	int maxDataLenInEachCol[3];
	for(int j = 0; j < 3; j++) {
		maxDataLenInEachCol[j]=j+5;
	}
	while(true){
	ResultSet* rs = new ResultSet(5,3,maxDataLenInEachCol);
	rs->setElement(0,0,"aaaa");
	rs->setElement(0,1,"bbbb");
	rs->setElement(0,2,"cccc");
	rs->setElement(1,0,"dddd");
	rs->setElement(1,1,"eeee");
	rs->setElement(1,2,"ffffff");
	rs->setElement(2,0,"gggg");
	rs->setElement(2,1,"hhhh");
	rs->setElement(2,2,"iiiii");
	rs->setElement(3,0,"jjjjj");
	rs->setElement(3,1,"kkkk");
	rs->setElement(3,2,"llll");
	rs->setElement(4,0,"mmmm");
	rs->setElement(4,1,"nnnn");
	rs->setElement(4,2,"oooooo");

	for(int i = 0; i< 5; i++) {
		for(int j = 0; j < 3; j++) {
			cout<<"\t["<<i<<","<<j<<"] : "<<rs->getElement(i,j);
		}
		cout<<endl;
	}
	delete rs;
	}

}*/

