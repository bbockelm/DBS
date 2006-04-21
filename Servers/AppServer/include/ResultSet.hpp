#ifndef _ResultSet_hpp_included_
#define _ResultSet_hpp_included_

#include <string>
#include <vector>
#include "DBUtil.hpp"
#include <sql.h>
#include <sqlext.h>
#include <sqltypes.h>
#include <stdlib.h>
#include <stdio.h>

/**
* A ResultSet Class
* This class holds all the data indexed by rows and columns that is returned by executing the sql queries.
* There are methods to retrive and set the data at any particular index. It also holds the column names of the columns fetched from the database.
*/

class ResultSet {
	private:
	int noOfCols;/* An integer to store the number of columns in this ResultSet */
	int noOfRows;/* An integer to store the number of rows in this ResultSet */
	std::vector<std::string> data; /*A vector of string to hold the data of the ResultSet */
	std::vector<std::string> colName; /* A vector of string to hold colNames retuned from database */
	typedef std::vector<std::string>::iterator ColIterator; /*Iterator for vector of colNames*/
	SQLCHAR** buff;
	SQLINTEGER* len;
	SQLINTEGER* buffLen;
	SQLINTEGER returnCode;
	SQLHSTMT stmtHandle;// Handle SQL Statement
	SQLHDBC connHandle;
	DBUtil dbUtil;
	//DBManagement* dbManager;
	
	public:

	/**
	* @param noOfRows an integer that represents the number of rows in the ResultSet.
	* @param noOfCols an integer that represents the number of coloums in the ResultSet.
	*/

	//ResultSet(int noOfRows, int noOfCols);

      
        //Anzar is adding these three functions.
	ResultSet();
	//ResultSet(DBManagement* dbManager, SQLHSTMT stmtHandle);
	ResultSet(SQLHDBC connHandle, SQLHSTMT stmtHandle);
        void setNoOfRows(int); 
        void setNoOfCols(int);
	//void setStmtHandle(SQLHSTMT stmtHandle);
	/**
	* A default destructor . 
	*/
	~ResultSet();

	/**
	* This method is to fetch the data element at a particular index from the ResultSet.
	* @param row an integer representing the row at which the data value has to be retrived.
	* @param col an integer representing the column at which the data value has to be retrived.
	* @return a string representing the actual value of the ResultSet at the given index.
	*/
	std::string ResultSet::getElement(int row, int col);
	std::string getElement(int col);
	bool next();
	void reset();
	/*
	* This method is to fetch colName at the give index
	* @param index at which the colName needs to be fetched.
	* @return a string representating the colName
	*/
	std::string ResultSet::getColName(int index);
	void ResultSet::setColName(int index, std::string value);
	/**
	* This method is to add the data element inside the data vector of the ResultSet.
	* @param value a string representing the actual value that has to be inserted.
	*/
	void ResultSet::addElement(std::string value);

	/**
	* This method is to add the colName in the colName vector of the ResultSet.
	* @param value a string representing the actual name that has to be inserted.
	*/
	void ResultSet::addColName(std::string value);

	/**
	* This method is used to get the number of rows in this ResultSet.
	* @return an integer representing the value of the number of rows.
	*/
	int getNoOfRows();

	/**
	* This method is used to get the number of columns in this ResultSet.
	* @return an integer representing the value of the number of columns.
	*/
	int getNoOfCols();

	
	/**
	* This method is used to get the index of the colName inside the colName vector in this ResultSet.
	* @param colNameIn a string representing the actual name of the coloumn for which index needs to be fetched.
	* @return an integer representing the index of the colName in colName vector.
	*/
	int getColIndex(std::string colNameIn);
};

#endif

