#include "Managers.hpp"
#include <string>

PersonManager::PersonManager() {
	personTable  = new PersonMultiTable(dbManager);
}


int PersonManager::write(Message* msgReceived, Message& msgReturned) {

  /*****************************
  ******************************/

	Personmultirow* aRow = new Personmultirow();
	int retval = setRowValues(personTable, aRow, msgReceived, "", 0);
	personTable->addRow(aRow);
	if (!this->doInsert((TableInterface*)personTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("PersonInsert");
	string name = "t_person.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;

}

PersonManager::~PersonManager() {
	delete personTable;
}

