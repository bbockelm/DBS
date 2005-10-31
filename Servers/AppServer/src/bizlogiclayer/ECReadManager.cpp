#include "Managers.hpp"

ECReadManager::ECReadManager() {
	this->ecTable = new EvcollviewMultiTable(dbManager);
}

int ECReadManager::read(Message* msgReceived, Message& msgReturned) {

        typedef vector<Evcollviewmultirow*> MyRows;
        typedef MyRows::iterator MyRowIter;

        string addQuery = findKeyMakeQuery(msgReceived);

        MyRows myRows = this->ecTable->select(addQuery);
        msgReturned.setName("ReadEventCollection");

        Rows rows;
        for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
                rows.push_back((RowInterface*)*r);
        }
        this->makeMessage((TableInterface*)ecTable, rows.begin(), rows.end(), msgReturned);

}


ECReadManager::~ECReadManager() {
	delete ecTable;
}

/*
void ECReadManager::read(Message* msgReceived, Message& msgReturned) {
	string collType = msgReceived->getElementValue("collType");
	string dsName = msgReceived->getElementValue("dsName");
  
	typedef vector<Eventcollectionsmultirow*> MyRows;
	typedef MyRows::iterator MyRowIter;
	MyRows myRows = this->ecTable->select("(collectiontype.collectiontype = '"+collType+"') AND (primarydataset.cobradatasetname = '"+dsName+"')");
	msgReturned.setName("ECRead");
	Rows rows;
	for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
		rows.push_back((RowInterface*)*r);
	}
	this->makeMessage((TableInterface*)this->ecTable, rows.begin(), rows.end(), msgReturned);
}
*/

