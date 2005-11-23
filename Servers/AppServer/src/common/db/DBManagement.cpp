/* DBManagement.cpp

*/

#include "DBManagement.hpp"
#include "DBException.hpp"
#include <iostream.h>

//#include "common.h"

using namespace std;

DBManagement::DBManagement(string dataSourceName, string username, string password) {
	//  Connect to the datasource 
	this->dataSourceName = dataSourceName;
	this->username = username;
	this->password = password;
}
DBManagement::~DBManagement() {
}

int DBManagement::executeQuery(string sql) {
	this->allocateStmtHandle();
	this->runGenericQuery(sql);
	this->freeStmtHandle();
	return 0;
}

ResultSet* DBManagement::executeQueryWithResults(string sql) {
	allocateStmtHandle();
	runGenericQuery(sql);
	SQLINTEGER noOfRows = getNoOfRows();
	SQLSMALLINT noOfCols = getNoOfCols();

	//cout<<"executeQueryWithResults::: Number of Columns "<<noOfCols<<endl;
	//cout<<"executeQueryWithResults::: Number of Rows "<<noOfRows<<endl;
	
	SQLSMALLINT colNameLen[noOfCols];
	SQLSMALLINT colType;
	SQLUINTEGER colSize;
	SQLSMALLINT colScale;
	SQLCHAR* buff[noOfCols];
	SQLINTEGER len[noOfCols];
	SQLINTEGER buffLen[noOfCols];
	SQLCHAR colName[MAX_COL_NAME_LEN];
	//ResultSet* rs = new ResultSet((int)noOfRows, (int)noOfCols);
	ResultSet* rs = new ResultSet();
 
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
		if (!isSuccess()) {
			doDiagnostics();
			freeStmtHandle();
			for(int j = 0; j <= i; j++) {
				delete[] buff[j];
			}
			throw DBException("Error in fetching coloum information: SQLDescribeCol");
		}
		rs->addColName((string)((char*)colName));
		//cout<<"colName is "<<colName.at(i)<<endl;
		//cout<<"colName is "<<colName<<" \n colNameLen is "<<colNameLen<<" \ncolType is "<<colType<<" \ncolSize is "<<colSize<<"colDataDisplaySize is "<<colDataDisplaySize<<endl;
		buffLen[i] = colSize + 1;
		buff[i] = new SQLCHAR[buffLen[i]];
		returnCode = SQLBindCol(stmtHandle,
				(SQLSMALLINT)(i + 1 ),
				SQL_C_CHAR,
				buff[i],
				buffLen[i],
				&len[i]);
		if (!isSuccess()) {
			doDiagnostics();
			freeStmtHandle();
			for(int j = 0; j <= i; j++) {
				delete[] buff[j];
			}
			throw DBException("Error in binding data types with coloums: SQLBindCol");
		}
		
	}

	returnCode = SQLFetch(stmtHandle); 
	int row = 0; 
	while(returnCode != SQL_NO_DATA) {
                //cout << "New ROW Added <<<<<<<<<<" << endl;

		for (int i = 0 ; i < noOfCols; i++) {
			//cout<<"(char*)buff[i] "<<(char*)buff[i]<<" len is "<<len[i]<<endl;
			//cout<<"strlen is "<<strlen((char*)buff[i])<<endl;
			//int length = (int)len[i];
			int length = (int)strlen((char*)buff[i]);
			if( length > 0 ) {
                                //cout << "addElement????? " << (char*)buff[i] << endl;
                                //cout << "addElemet WITH BUFF" <<endl;
				rs->addElement((string)((char*)buff[i]));
			} else {
                                //cout << "addElemet EMPTY" <<endl;
				rs->addElement((string)"");
			}
		}
		returnCode = SQLFetch(stmtHandle);  
		++row;
	} 
	freeStmtHandle();

        rs->setNoOfRows(row);
        rs->setNoOfCols((int)noOfCols);

	for(int i = 0; i < noOfCols; i++) {
		delete[] buff[i];
	}
	return rs;
}



int DBManagement::open() {
	// 1. allocate Environment handle and register version 
	this->allocateEnvHandle();
	// 2. allocate connection handle, set timeout
	this->allocateConHandle();
	//cout<<"trying .... SQLConnect"<<endl;
	returnCode = SQLConnect(connHandle, 
				(SQLCHAR*) dataSourceName.c_str(), 
				SQL_NTS,
				(SQLCHAR*) username.c_str(),
				SQL_NTS,
				(SQLCHAR*) password.c_str(), 
				SQL_NTS);

	if (!isSuccess()) {
		doDiagnostics();
		freeEnvConHandle();
		throw errMessage;
	}
	return 0;
}

int DBManagement::beginTransection() {
	returnCode = SQLSetConnectAttr(connHandle, 
				SQL_ATTR_AUTOCOMMIT, 
				(SQLPOINTER)SQL_AUTOCOMMIT_OFF, 
				SQL_NTS);
	if (!isSuccess()) {
		freeEnvConHandle();
		throw DBException("Error in setting SQL_ATTR_AUTOCOMMIT off: SQLSetConnectAttr");
	}
	return 0;
}

int DBManagement::endTransection() {
	returnCode = SQLSetConnectAttr(connHandle, 
				SQL_ATTR_AUTOCOMMIT, 
				(SQLPOINTER)SQL_AUTOCOMMIT_ON, 
				SQL_NTS);
	if (!isSuccess()) {
		freeEnvConHandle();
		throw DBException("Error in setting SQL_ATTR_AUTOCOMMIT on: SQLSetConnectAttr");
	}
	return 0;
}

int DBManagement::commit() {
	returnCode = SQLEndTran(SQL_HANDLE_DBC, connHandle, SQL_COMMIT);
	if (!isSuccess()) {
		freeEnvConHandle();
		throw DBException("Error in commiting the transections: SQLEndTran");
	}
	return 0;
}

int DBManagement::rollback() {
	returnCode = SQLEndTran(SQL_HANDLE_DBC, connHandle, SQL_ROLLBACK);
	if (!isSuccess()) {
		freeEnvConHandle();
		throw DBException("Error in rollbacking the transections: SQLEndTran");
	}
	return 0;
}



void DBManagement::close() {
	//cout<<"calling DBManagement::close()"<<endl;
	SQLDisconnect(connHandle);
	//cout<<"calling between DBManagement::close()"<<endl;
	freeEnvConHandle();
	//cout<<"DONE calling DBManagement::close()"<<endl;
}

void DBManagement::runGenericQuery(string sql) {
	//cout<<"runGenericQuery::: SQL QUERY is \n"
	//	<<"***********************************************\n"
	//	<<sql
	//	<<"\n***********************************************\n"<<endl;
	returnCode = SQLExecDirect(stmtHandle,(SQLCHAR*)sql.c_str(),SQL_NTS);
	if (!isSuccess()) {
		errMessage = "Error in executing the SQL query: SQLExecDirect";
		doDiagnostics();
		freeStmtHandle();
		throw DBException(errMessage);
	}
}

SQLSMALLINT DBManagement::getNoOfCols() {
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

SQLINTEGER DBManagement::getNoOfRows() {
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
bool DBManagement::isSuccess() {
        //cout << "returnCode is ::::::::: " << returnCode << endl; 

	if ((returnCode != SQL_SUCCESS) && (returnCode != SQL_SUCCESS_WITH_INFO)) {
		return(false);
	}else{
		return(true);
	}
}

void DBManagement::freeEnvHandle() {
	SQLFreeHandle(SQL_HANDLE_ENV, envHandle);
	//cout<<"free envHandle "<<envHandle<<endl;

}

void DBManagement::freeEnvConHandle() {
	SQLFreeHandle(SQL_HANDLE_DBC,connHandle);
	this->freeEnvHandle();
	//cout<<"free connHandle "<<connHandle<<endl;
}

void DBManagement::freeStmtHandle() {
	SQLFreeHandle(SQL_HANDLE_STMT,stmtHandle);
	//cout<<"COMMENTED OUT free stmtHandle "<<stmtHandle<<endl;
}


void DBManagement::allocateEnvHandle() {

	returnCode = SQLAllocHandle(SQL_HANDLE_ENV,
					SQL_NULL_HANDLE,
					&envHandle);
	if (!isSuccess()) {
		//cout<<"Error in allocating handle for SQL_HANDLE_ENV: SQLAllocHandle"<<endl;
		throw DBException("Error in allocating handle for SQL_HANDLE_ENV: SQLAllocHandle");
	}
	returnCode = SQLSetEnvAttr(envHandle, 
					SQL_ATTR_ODBC_VERSION, 
					(void*)SQL_OV_ODBC3, 
					0); 
	if (!isSuccess()) {
		this->freeEnvHandle();
		//cout<<"Error in registering ODBC version: SQLSetEnvAttr"<<endl;
		throw DBException("Error in registering ODBC version: SQLSetEnvAttr");
	}
	//cout<<"allocate envHandle "<<envHandle<<endl;
}

void DBManagement::allocateConHandle() {
	returnCode = SQLAllocHandle(SQL_HANDLE_DBC, 
					envHandle, 
					&connHandle); 
	if (!isSuccess()) {
		this->freeEnvHandle();
		//cout<<"Error in allocating handle for SQL_HANDLE_DBC: SQLAllocHandle"<<endl;
		throw DBException("Error in allocating handle for SQL_HANDLE_DBC: SQLAllocHandle");
	}
	returnCode = SQLSetConnectAttr(connHandle, 
				SQL_LOGIN_TIMEOUT, 
				(SQLPOINTER *)5, 
				0);
	if (!isSuccess()) {
		//cout<<"Error in setting SQL_LOGIN_TIMEOUT: SQLSetConnectAttr"<<endl;
		this->freeEnvConHandle();
		throw DBException("Error in setting SQL_LOGIN_TIMEOUT: SQLSetConnectAttr");
	}

	//cout<<"allocate connHandle "<<connHandle<<endl;
}

void DBManagement::allocateStmtHandle() {
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

void DBManagement::doDiagnostics() {
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

