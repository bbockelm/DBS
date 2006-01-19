#include "Managers.hpp"
#include "Configuration.hpp"
#include "ObjectLayerException.hpp"
#include "BizLayerException.hpp"
#include "DBException.hpp"
#include "TableInterface.hpp"
#include "Log.hpp"

Manager::Manager(){
  //this->dbManager = new DBManagement("mydsn", "anzar", "");
	try {
		static Log l("Manager");
		Manager::logger = l.getLogger();
		Configuration* conf = Configuration::instance();
		//cout<<"this->dbManager = new DBManagement(conf->getDsn(), conf->getDbUser() , conf->getDbPasswd());"<<endl;
		LOG4CXX_INFO(logger,"DSN is " + conf->getDsn() + " DB USER is " + conf->getDbUser() + " PASSOWRD is " + conf->getDbPasswd());
		this->dbManager = new DBManagement(conf->getDsn(), conf->getDbUser() , conf->getDbPasswd());
		this->dbManager->open();
	} catch (DBException &e)  {
		LOG4CXX_DEBUG(logger,e.report());
		throw BizLayerException(e.report());
	}

}

int Manager::doWrite(TableInterface* inTable, string name) {
	inTable->setDBManager(dbManager);
	this->doInsert(inTable);
	if(inTable->getNoOfRows() > 0) {
		string value = inTable->getStrValue(0, name);
		return util.atoi(value);
	}
	return 0;
}

int Manager::doInsert(TableInterface* inTable) {
	// This function actuallly performs the insert operation for the child managers.
	dbManager->beginTransection();
	try {
		cout<<"calling insert for inTable ion Manager"<<endl;
		inTable->insert();
		dbManager->commit();
	} catch (ObjectLayerException &e)  {
		//cout<<"Rolling back the whole transection"<<endl;
		LOG4CXX_DEBUG(logger,"Rolling back the whole transection");
		dbManager->rollback();
		throw BizLayerException(e.report());
	}
	dbManager->endTransection();
	return 1;  //success
}



string Manager::makeClause(TableInterface* inTable, RowInterface* aRow) {
	Dictionary* schema = inTable->getSchema();
	Dictionary* multiRefrences = inTable->getMultiRefrence();
	util.setSchema(schema);
	SQL* sql = new SQL(&util);
	Keys tempKeys;
	for(Dictionary_iter schemaIterator = schema->begin(); schemaIterator != schema->end(); ++schemaIterator) {
		tempKeys.push_back(schemaIterator->first);
	}
	string clause = sql->makeClause(aRow, tempKeys.begin(), tempKeys.end(), multiRefrences->begin(), multiRefrences->end());
	delete sql;
	return clause;
}

void Manager::cleanup() {
        this->dbManager->close();
        delete this->dbManager;
}
 
Manager::~Manager() {
}

	
