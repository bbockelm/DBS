#include "Managers.hpp"

ECWriteManager::ECWriteManager() {
	ecTable = new EvcollviewMultiTable(dbManager);
}

int ECWriteManager::write(Message* msgReceived, Message& msgReturned) {
	Evcollviewmultirow* aRow = new Evcollviewmultirow(); 
	int retval = setRowValues(ecTable, aRow, msgReceived,"",0);
	ecTable->addRow(aRow);
	if(!this->doInsert((TableInterface*)ecTable, msgReturned)) {
		return 0;
	}
	//Chek for isSetValue first before getValue
	string name = "t_info_evcoll.event_collection";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element("id", value, dataType);
	msgReceived->addElement(e);
	
	msgReturned.dispose();  /* ??????????????????????????????? */
	FileManager fileManager;
	if(!fileManager.write(msgReceived, msgReturned)) {
		return 0;
	}
	return 1;
	
  
}

ECWriteManager::~ECWriteManager() {
	cout<<"Destructor of ECWriteManager"<<endl;
	delete ecTable;
        this->cleanup(); 
}

