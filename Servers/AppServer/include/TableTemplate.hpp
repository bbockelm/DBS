#ifndef _TableTemplate_hpp_included_
#define _TableTemplate_hpp_included_

/// This file contains a Tempelate Class that 
/// Represents a RDMS Table, containing a vector 
/// of Rows, where each Row is a self describing
/// Object.

#include <iostream.h>
#include <vector>
#include "common.hpp"
#include "ResultSet.hpp"
#include "DBManagement.hpp"
#include "BaseSchemaNConstratints.hpp"
#include "Util.hpp"
#include "SQL.hpp"
#include "TableInterface.hpp"
#include <log4cxx/logger.h>

/// Template Class for a Table
template <class R>
class TableTemplate : public TableInterface {

public:  
	//Default constructor
	TableTemplate();
	~TableTemplate();
	void dispose();
	TableTemplate(DBManagement* dbmanager);
        void addRow(RowInterface* aRow);
	void addRow(R* aRow);
	void delRows();
	//RowIter getRowsBegin();
	//RowIter getRowsEnd();
	std::vector<R*>& select(std::string whereClause);
	//std::vector<RowInterface*>& select(std::string whereClause);
	void insert();
	void update();
	std::string* getTableName(); 
	void setDBManager(DBManagement* dbmanager);
	Dictionary* getSchema();
	Dictionary* getMultiRefrence();
	std::vector<R*>& getRows();
	int getNoOfRows();
	std::string getStrValue(int index, string name);

private:
	void init();
	RowSchemaNConstraintsBinding<R> schemaNconstraints;
	int getSeqValue(std::string, std::string);

	std::string makeSelectQuery(std::string);
	std::string makeWhereClause(std::string);
	std::vector<std::string> makeInsertQuery(R*);
  	Dictionary getSatisfiedRefrences(ResultSet*,int);
	void makeRefrences(void);

	Dictionary* schema;
	Dictionary* constraints;
	Dictionary* refrences;
  	

	Keys* primaryKeys;
	Keys* notNullKeys;
	Keys* foreignKeys;

	std::string *tableName;
	std::vector<R*> rows;
	typedef std::vector<R*>::iterator RowIter;
	RowIter rowIterator; 


protected :
	log4cxx::LoggerPtr logger;
	virtual void doSmartInsert(R*){cout <<"doSmartInsert virtual"<<endl; };
	void doSimpleInsert(R*);
	void insertSingle(R*, std::string, std::string);
	void insertMulti(R*, std::string);
	void fixPKWithSeq(R*);
  	void setTimeInRow(R*);
  	void setPersonInRow(R*);
	ResultSet* doSelect(std::string, std::string);
	void convertIntoRow(ResultSet*,int,R*);
	void reSetColNamesInRS(ResultSet* rs);
	Dictionary* multiRefrences;
	Keys primaryKeysReal;
	ListOfLists* uniqueKeys;

	Util util;
	SQL* sql;
	DBManagement* dbmanager;
	Keys* schemaOrder;
  	
};

#endif

