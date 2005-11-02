#include "Managers.hpp"
#include <string>

PhysicsGroupManager::PhysicsGroupManager() {
	physicsgroupTable  = new PhysicsgroupMultiTable(dbManager);
}


int PhysicsGroupManager::write(Message* msgReceived, Message& msgReturned) {

	Physicsgroupmultirow* aRow = new Physicsgroupmultirow();
	int retval = setRowValues(physicsgroupTable, aRow, msgReceived, "", 0);
	physicsgroupTable->addRow(aRow);
	if (!this->doInsert((TableInterface*)physicsgroupTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("PhysicsGroupInsert");
	string name = "t_physics_group.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;

}

PhysicsGroupManager::~PhysicsGroupManager() {
	delete physicsgroupTable;
        this->cleanup(); 
}

