/* DBUtil.cpp

*/

#include "DBUtil.hpp"
#include "DBException.hpp"
#include <iostream.h>


using namespace std;

DBUtil::DBUtil() {
}
DBUtil::~DBUtil() {
}
void DBUtil::setStmtHandle(SQLHSTMT stmtHandle) {
	this->stmtHandle = stmtHandle;
}
void DBUtil::setConnHandle(SQLHDBC connHandle) {
	this->connHandle = connHandle;
}


SQLSMALLINT DBUtil::getNoOfCols() {
	SQLSMALLINT noOfCols;// Number of coloums returned from the SQL query
	returnCode = SQLNumResultCols(stmtHandle, &noOfCols);
	if(!isSuccess()) {
		freeStmtHandle();
		//strcpy(errMessage,"Error in fetching number of colomns from sql: SQLNumResultCols");
		//throw errMessage;
		throw DBException("Error in fetching number of colomns from sql: SQLNumResultCols");
	}
	return(noOfCols);
}

SQLINTEGER DBUtil::getNoOfRows() {
	SQLINTEGER noOfRows;// Number of rows returned from the SQL query

        ///Anzar addded these lines to be deleted
        //hStmt_T *stmt=(hStmt_T*)stmtHandle;
        //noOfRows = (SQLINTEGER)abs(stmt->num_result_rows);
        //return(noOfRows);
        ///end

	returnCode = SQLRowCount(stmtHandle, &noOfRows);
	if(!isSuccess()) {
		freeStmtHandle();
		throw DBException("Error in fetching number of rows from sql: SQLRowCount");
	}
	return(noOfRows);
}
bool DBUtil::isSuccess() {
        //cout << "returnCode is ::::::::: " << returnCode << endl; 

	if ((returnCode != SQL_SUCCESS) && (returnCode != SQL_SUCCESS_WITH_INFO)) {
		return(false);
	}else{
		return(true);
	}
}


void DBUtil::freeStmtHandle() {
	SQLFreeHandle(SQL_HANDLE_STMT,stmtHandle);
	//cout<<"COMMENTED OUT free stmtHandle "<<stmtHandle<<endl;
}

void DBUtil::allocateStmtHandle() {
	returnCode = SQLAllocHandle(SQL_HANDLE_STMT, 
					connHandle, 
					&stmtHandle);
	if (!isSuccess()) {
		errMessage = "Error in allocating handle for SQL_HANDLE_STMT: SQLAllocHandle";
		doDiagnostics();
		throw DBException(errMessage);
	}
	//cout<<"allocate stmtHandle "<<stmtHandle<<endl;
}

void DBUtil::doDiagnostics() {
	SQLINTEGER errCode = 0;// Error codes of Diagnostic operations
	SQLSMALLINT diagMsgLen;// Diagnostic message length
	SQLCHAR diagMsg[SQL_MAX_OPTION_STRING_LENGTH];// Diagnostic message txt
	SQLCHAR sqlStatus[SQL_SQLSTATE_SIZE];// Status SQL
	strcpy((char*)diagMsg,"\0");
	SQLRETURN     rc1;
	rc1= SQLGetDiagRec(SQL_HANDLE_DBC, 
			connHandle,
			1, 
			sqlStatus,
			&errCode,
			diagMsg,
			MAX_ERR_MSG_LEN,
			&diagMsgLen);
	/*if(rc1 != SQL_NO_DATA) {
		cout<<"rc1 is not equal to SQL_NO_DATA"<<endl;
	} else {
		cout<<"rc1 is equal to SQL_NO_DATA"<<endl;
	}*/

	int len = strlen((char*) diagMsg);
	if(len > 0){
		errMessage = (string)((char*)diagMsg);
	}
	cout<<"While Diagnosing ...:"<<diagMsg<<endl<<"sqlstate is "<<sqlStatus<<endl;
	cout<<"errCode "<<errCode<<endl;
	//cout<<"errMessage is "<<errMessage<<endl;

}

