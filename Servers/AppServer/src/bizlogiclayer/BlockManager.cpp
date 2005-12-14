#include "Managers.hpp"
#include <string>

BlockManager::BlockManager() {
	blockTable = new BlockviewMultiTable(dbManager);
}


int BlockManager::write(Message* msgReceived, Message& msgReturned) {

  /*****************************
   ******************************/

	Blockviewmultirow* aRow = new Blockviewmultirow();
	int retval = setRowValues(blockTable, aRow, msgReceived,"",0);
	blockTable->addRow(aRow);

	if (!this->doInsert((TableInterface*)blockTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("BlockInsert");
	string name = "t_block.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;

}

BlockManager::~BlockManager() {
        
	//cout<<"Destructor of BlockManager()"<<endl;
	delete blockTable;
        this->cleanup(); 
}

