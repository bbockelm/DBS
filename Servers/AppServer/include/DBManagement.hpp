#ifndef _DBManagement_hpp_included_
#define _DBManagement_hpp_included_

/* DBManagement.h

*/
#include <stdlib.h>
#include <stdio.h>
#include <sql.h>
#include <sqlext.h>
#include <sqltypes.h>
#include <string>
#include "ResultSet.hpp"

#define MAX_ERR_MSG_LEN 100
#define MAX_USERNAME_LEN 25
#define MAX_PASSWORD_LEN 25
#define MAX_COL_NAME_LEN 100

/**
* A DBManagement Class.
* This class handles all the lower level Database managemenet operations. It well encapusales the Database operations and provides a simpler and generic light weight API on the top.
*/

class DBManagement {
	private:
	SQLHENV envHandle;// Handle ODBC environment
	SQLHSTMT stmtHandle;// Handle SQL Statement
	SQLHDBC connHandle;// Handle connection
	std::string username;// Username to connect to DB
	std::string password;//Password used to connect to DB associated with username
	std::string dataSourceName;// Data Source Name
	std::string errMessage;
	bool isSuccess();
	SQLINTEGER getNoOfRows();
	SQLSMALLINT getNoOfCols();
	void disposeResultSet();
	void freeEnvHandle();
	void freeEnvConHandle();
	void freeStmtHandle();
	void freeEnvConStmtHandle();
	void allocateEnvHandle();
	void allocateConHandle();
	//void allocateStmtHandle(SQLHSTMT &stmtHandle);
	void allocateStmtHandle();
	void doDiagnostics();
	void runGenericQuery(std::string sql);

	public:
	SQLINTEGER returnCode;/*!< This stores an interger value of the result of any lower level operations or method call to the unixODBC api. */


	/**
	* A constructor that stores the parameters passed, so they can be used later on by other methods.  
	* @param dataSourceName a string representing the value of the Data Source Name used to connect to the actual Databse amd load the driver.
	* @param username a string representing the name of the user, required to access the database.
	* @param password a string representing the password of the user, required to access the database.
	*
	*/
	DBManagement(std::string dataSourceName, std::string username, std::string password);
	
	/*A default destructor */
	~DBManagement();

	/**
	* This method allocates internal data structures (handles) memory space and then uses them to actually make a socket connection to the database by using the parameters supplied in the constructor. Note that if this method raises an exception, it doesnot free up the internal memory alloacted. The user of this method has to call the close method which will free up the memory even iof the mothod succeeds or not.
	@return If it return 0 that means the method succeded in establishing the socket connection to the Database.
	*/
	int open();


	/**
	* This method frees up all the memory allocated to the internal data structures and then closes the socket connection to the Database.
	*/
	void close();


	/**
	* This method initializes the internal data structures so that all the Database operation will be in the transection mode. This means the database will not get updated unless the user explictly calls commit to finalize all the Database operations.
	* @return If it return 0 that means the method succeded in initializing the internal data structures as per the requirements.
	*/
	int beginTransection();

	
	/**
	* This method initializes the internal data structures so that all the Database operation will be in the non-transection mode. This means the database will not get updated right away once the queries re executed. It will not wait for user to explictly calls commit to finalize all the Database operations.
	* @return If it return 0 that means the method succeded in initializing the internal data structures as per the requirements.
	*/
	int endTransection();

	/**
	* This method is used when the user has started working in transection mode by calling beginTransection. It commits all the changes that the user might have done to the database. Unless users explictely calls this method the database will not get updated if operating under transection mode.
	* @return If it return 0 that means the method succeded in commiting all the changes that the user might have done to the database.
	*/
	int commit();


	/**
	* This method is used when the user has started working in transection mode by calling beginTransection. It will  undo all the changes that the user might have done to the database. 
	* @return If it return 0 that means the method succeded in undoing all the changes that the user might have done to the database.
	*/
	int rollback();


	/**
	* This method is able to execute all the insert, update and delete sql queries. This method can be called in the transection or regular mode. In former case, the user have to call commit or rollback methods to commit or undo changes to the database. In later case it will directly update the database right away.
	* @param sql a string representing the actual sql query to be executed.
	* @return If it return 0 that means the method succeded in executing the query in the database.
	*/
	int executeQuery(std::string sql);


	/**
	* This method is able to execute all the select sql queries. 
	* @param sql a string representing the actual sql query to be executed.
	* @return ResultSet* An object containning the data retrived from the database.
	*/

	ResultSet* executeQueryWithResults(std::string sql);
	
};
#endif

