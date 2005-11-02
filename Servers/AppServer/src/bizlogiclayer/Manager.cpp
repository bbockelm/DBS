#include "Managers.hpp"
#include "Configuration.hpp"
#include "ObjectLayerException.hpp"
#include "Message.hpp"
#include "TableInterface.hpp"

Manager::Manager(){
  //this->dbManager = new DBManagement("mydsn", "anzar", "");
	cout<<"Configuration* conf = Configuration::instance()"<<endl;
	Configuration* conf = Configuration::instance();
	cout<<"this->dbManager = new DBManagement(conf->getDsn(), conf->getDbUser() , conf->getDbPasswd());"<<endl;
	cout<<"conf->getDsn() "<<conf->getDsn()<<" conf->getDbUser() "<<conf->getDbUser()<<" conf->getDbPasswd() "<<conf->getDbPasswd()<<endl;
	this->dbManager = new DBManagement(conf->getDsn(), conf->getDbUser() , conf->getDbPasswd());
  //this->dbManager = new DBManagement(USERDSN, USERNAME, "");
	cout<<"this->dbManager->open(); "<<endl;
  this->dbManager->open();
}


int Manager::doInsert(TableInterface* inTable, Message& msgReturned) {
	// This function actuallly performs the insert operation for the child managers.
	dbManager->beginTransection();
	try {
		inTable->insert();
		dbManager->commit();
	} catch (ObjectLayerException &e)  {
		cout<<"Rolling back the whole transection"<<endl;
		dbManager->rollback();
		msgReturned.setException(e.report());
		return 0;  //Error condition
	}
	dbManager->endTransection();
	return 1;  //success
}

int Manager::makeMessage(TableInterface* inTable, RowIter b, RowIter e, Message& msgReturned) {
	// This function actuallly performs the select operation for the child managers.
	typedef std::vector<Element*> Data;
	typedef std::vector<Data*> VecData;

        cout << "Manager::makeMessage" << endl; 

	this->schema = inTable->getSchema();
	try {
		VecData* vd = new VecData();
		for(RowIter i = b; i != e; ++i) {
			Data* d = new Data();
			for(Dictionary_iter j = this->schema->begin(); j != this->schema->end(); ++j) {
                                //cout << "Manager::makeMessage Doing isSet" << j->first << endl;
				if(this->util.isSet(*i, j->first, j->second ) ) {
                                        //cout << "Manager::makeMessage Doing getStrValue" << endl;
					//Element* e = new Element(this->util.getTokenAt(j->first,1), 
					Element* e = new Element(j->first, 
							this->util.getStrValue(*i, j->first, j->second) , 
							j->second);
                                        //cout << "Manager::makeMessage Done getStrValue" << endl; 
					d->push_back(e);
				}
			}
			vd->push_back(d);
		}
		msgReturned.addVecOfVecOfElement(vd,"ROWS");

	} catch (ObjectLayerException &e)  {
		msgReturned.setException(e.report());
		return 0;  //Error condition
	}
	return 1;  //success
}


string Manager::findKeyMakeQuery(Message* msgReceived) {

      string queryPlus = "";
      int first = 1;
       for(int i = 0 ; i < msgReceived->getNoOfElements(); ++i) {
                Element* e = msgReceived->getElement(i);
                string key = e->getKey();
                string value = e->getValue();
                string type = e->getType();

                if ( value == "NOTFOUND" ) {
                    continue;
                } 

                cout<<"Key is "<<e->getKey()<<endl;
                cout<<"Value is "<<e->getValue()<<endl;
                cout<<"Type is "<<e->getType()<<endl;
                /* 
                if (first != 1) {
                    queryPlus += " AND ";
                }*/
                if ( type == "STRING") {
                   queryPlus += key+"='"+value+"' AND ";
                }  else {
                   queryPlus += key+"="+value+" AND ";  
                } 
		
       }  
	if(queryPlus.length() > 0) {
		queryPlus = util.eraseEndChars(queryPlus,5);
	}
       cout <<"queryPlus " << queryPlus << endl;
       return queryPlus;
}


//int Manager::setRowValues(TableInterface* inTable, RowInterface* rowPtr, Message* message) {
int Manager::setRowValues(TableInterface* inTable, RowInterface* rowPtr, Message* message, string listName, int index) {
	Dictionary* schema = inTable->getSchema();
	for(Dictionary_iter schemaIterator = schema->begin();
		schemaIterator != schema->end(); schemaIterator++) {
		//string paramName = util.getTokenAt(schemaIterator->first, 1);
		string paramName = schemaIterator->first;
                cout << "NAME OF PAram in setRowValues " << paramName << endl;
		string value = message->getElementValue(paramName);
		if ( value == "NOTFOUND" ) {
			value = message->getElementValue(paramName, listName, index);
			cout<<paramName <<" 's value NOTFOUND"<<endl;
      			if ( value == "NOTFOUND" ) {
				continue; 
			}
		} else {
			cout<<"paramName is "<<value<<endl;
		}
		cout<<"calling util.setValue"<<endl;
		util.setValue(rowPtr, schemaIterator->first, schemaIterator->second, value);
		cout<<"Done calling util.setValue"<<endl;
   	}
	cout<<"returnning from Manager::setRowValues"<<endl;
	return 1;
}

void Manager::cleanup() {
        cout<<"closing inside Suoerclass Manager"<<endl;
        this->dbManager->close();
        cout<<"Deleting dbManager"<<endl;
        delete this->dbManager;
        cout<<"Out of manager"<<endl;
}
 
Manager::~Manager() {
        /*
	cout<<"closing inside Suoerclass Manager"<<endl;
	this->dbManager->close();
	cout<<"Deleting dbManager"<<endl;
	delete this->dbManager;
	cout<<"Out of manager"<<endl;
        */
}

	
