#ifndef _TableInterface_hpp_included_
#define _TableInterface_hpp_included_


#include <iostream>
#include "DBManagement.hpp"
#include "RowInterface.hpp"
#include "common.hpp"


class TableInterface {

public:  
	//Default constructor
	TableInterface(){
		//static Log l("TableTemplate", "../../var/log/server.log");
		//this->logger = l.getLogger();
	};
	TableInterface(DBManagement*){};
	virtual ~TableInterface(){};

	virtual void delRows(){};
	virtual void addRow(RowInterface* aRow){};
	virtual void insert(){};
	virtual void update(){};
	virtual int getNoOfRows(){};
	virtual bool next() {};
	virtual void reset() {};
	virtual std::string getStrValue(std::string colName) {};
	virtual int getIntValue(std::string colName) {};

	//virtual std::vector<RowInterface*>& select(std::string whereClause){cout<<"INSIDE VIRTUAL SELECT"<<endl; };
        virtual std::string* getTableName(){}; 
	virtual std::string getStrValue(int index, string name){};
        virtual void setDBManager(DBManagement*){};
        virtual Dictionary* getSchema(){cout<<"INSIDE VIRTUAL"<<endl; };
        virtual Dictionary* getMultiRefrence(){cout<<"INSIDE VIRTUAL"<<endl; };
protected:
	//static log4cxx::LoggerPtr logger;
};




#endif

