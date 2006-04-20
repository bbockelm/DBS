#ifndef _DBUtil_hpp_included_
#define _DBUtil_hpp_included_

/* DBUtil.h

*/
#include <stdlib.h>
#include <stdio.h>
#include <sql.h>
#include <sqlext.h>
#include <sqltypes.h>
#include <string>
//#include "ResultSet.hpp"

#define MAX_ERR_MSG_LEN 100
#define MAX_USERNAME_LEN 25
#define MAX_PASSWORD_LEN 25
#define MAX_COL_NAME_LEN 100

/**
* A DBUtil Class.
* This class handles all the lower level Database managemenet operations. It well encapusales the Database operations and provides a simpler and generic light weight API on the top.
*/

class DBUtil {
	private:
	SQLHSTMT stmtHandle;// Handle SQL Statement
	SQLHDBC connHandle;// Handle connection
	std::string errMessage;

	public:
	SQLINTEGER returnCode;/*!< This stores an interger value of the result of any lower level operations or method call to the unixODBC api. */


	DBUtil();
	
	~DBUtil();
	void setStmtHandle(SQLHSTMT stmtHandle);
	void setConnHandle(SQLHDBC connHandle);
	void freeStmtHandle();
	void allocateStmtHandle();
	void doDiagnostics();
	bool isSuccess();
	SQLINTEGER getNoOfRows();
	SQLSMALLINT getNoOfCols();


	
};
#endif

