#ifndef _MANAGER_H_
#define _MANAGER_H_
///Class that creates a manager
///parent class of all managers
/// creates DBManager 


#define USERDSN "mydsnvijay"
//#define USERDSN "thinservertest"
#define USERNAME "ggraham"
//#define USERNAME "anzar"

#include "DBManagement.hpp"
#include "TableInterface.hpp"
#include "ObjectLayerTables.hpp"
#include "Message.hpp"
#include "TableInterface.hpp"
#include "Util.hpp"
#include <string>
#include "RowInterface.hpp"
#include <log4cxx/logger.h>


typedef vector<RowInterface*> Rows;
typedef Rows::iterator RowIter;

class Manager {

public:

	Manager();
	Manager(std::string, std::string);

	int doInsert(TableInterface* inTable, Message& msgReturned);
	//int doSelect(TableInterface* inTable, std::string whereClause, Message& msgReturned);
	int makeMessage(TableInterface* inTable, RowIter b, RowIter e, Message& msgReturned);
	int setRowValues(TableInterface* inTable, RowInterface* rowPtr, Message* message, std::string listName, int index);
        string findKeyMakeQuery(Message* msgReceived);

	std::string getElementValue(std::string name, Message*);
	virtual ~Manager();
	//virtual ~Manager(){};
        void cleanup();   
	//virtual int read(Message*, Message&){};
	//virtual void write(Message*, Message&);
  
protected:
	DBManagement* dbManager;
	Util util;
	Dictionary* schema;
	log4cxx::LoggerPtr logger;
};



class DatasetProvcManagerParent: public Manager {
public:
        DatasetProvcManagerParent();
        int read(Message*, Message&);
        ~DatasetProvcManagerParent();

private:
        DatasetprovenenceevparentMultiTable* dspTable;
};



class DatasetProvcManagerChild: public Manager {
public:
        DatasetProvcManagerChild();
        int read(Message*, Message&);
        ~DatasetProvcManagerChild();

private:
        DatasetprovenenceevchildMultiTable* dspTable;
};


class ECReadManager : public Manager {
public:
	ECReadManager();
	int read(Message*, Message&);
	~ECReadManager();

private:
        EvcollviewMultiTable* ecTable;
};


class CRABECReadManager : public Manager {
public: 
        CRABECReadManager();
        int read(Message*, Message&);
        ~CRABECReadManager();

private:
        CrabevcollviewMultiTable* ecTable;
};


class PrimaryDatasetManager : public Manager {
public:
	PrimaryDatasetManager();
	int write(Message*, Message&);
	int read(Message*, Message&);
	~PrimaryDatasetManager();

private:
	PrimarydatasetMultiTable* priDatasetTable;
};



class ProcessedDatasetManager : public Manager {
public:
	ProcessedDatasetManager();
	int write(Message*, Message&);
        int read(Message* , Message& );
	~ProcessedDatasetManager();  

private:
	ProcessingpathMultiTable* processedDatasetTable;
};

/*
class GenParamManager : public Manager {
public:
	GenParamManager();
	int write(Message*, Message&);
	~GenParamManager();  

private:
	GenparametersetsMultiTable* genparametersetsTable;
};
*/

class AppConfigManager : public Manager {
public:
	AppConfigManager();
	int write(Message*, Message&);
	~AppConfigManager();  

private:
	InsertappsMultiTable* appsConfigTable;
};


class FileManager : public Manager {
public:
	FileManager();
	int write(Message*, Message&);
        int read(Message*, Message&);
	~FileManager();  

private:
	FileviewMultiTable* fileTable;
};


class ECWriteManager : public Manager {
public:
	ECWriteManager();
	int write(Message*, Message&);
	~ECWriteManager();  

private:
	EvcollviewMultiTable* ecTable;
};

/*
class AssignRoleManager : public Manager {
public:
	AssignRoleManager();
	int write(Message*, Message&);
	~AssignRoleManager();  

private:
	AdministrativeMultiTable* assignRoleTable;
};

*/
class PersonManager : public Manager {
public:
	PersonManager();
	int write(Message*, Message&);
	~PersonManager();  

private:
	PersonMultiTable* personTable;
};
/*
class RoleManager : public Manager {
public:
	RoleManager();
	int write(Message*, Message&);
	~RoleManager();  

private:
	RoleMultiTable* roleTable;
};
*/

class AnalysisDatasetManager : public Manager {
public:
	AnalysisDatasetManager();
	int write(Message*, Message&);
	~AnalysisDatasetManager();  

private:
	AnalysisdatasetMultiTable* analysisDatasetTable;
};

class PhysicsGroupManager : public Manager {
public:
	PhysicsGroupManager();
	int write(Message*, Message&);
	~PhysicsGroupManager();  

private:
	PhysicsgroupMultiTable* physicsgroupTable;
};


#endif
