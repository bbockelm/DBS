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
	string name = "t_event_collection.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;//Throw exception here instead of retunnnng 0
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	/*msgReceived->addElement(e);
	
	name = "t_processed_dataset.id";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	value = util.getStrValue(aRow, name, dataType);
	e = new Element("t_block.processed_dataset", value, dataType);
	msgReceived->addElement(e);

	
	
	msgReturned.dispose();  // ??????????????????????????????? 
	FileManager fileManager;
	if(!fileManager.write(msgReceived, msgReturned)) {
		return 0;
	}*/
	return 1;
	
  
}

ECWriteManager::~ECWriteManager() {
	//cout<<"Destructor of ECWriteManager"<<endl;
	delete ecTable;
        this->cleanup(); 
}

