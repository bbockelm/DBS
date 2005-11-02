#include "Managers.hpp"

DatasetProvcManagerParent::DatasetProvcManagerParent() {
	this->dspTable = new DatasetprovenenceevparentMultiTable(dbManager);
}

int DatasetProvcManagerParent::read(Message* msgReceived, Message& msgReturned) {

        typedef vector<Datasetprovenenceevparentmultirow*> MyRows;
        typedef MyRows::iterator MyRowIter;

        string addQuery = findKeyMakeQuery(msgReceived);

        MyRows myRows = this->dspTable->select(addQuery);
        msgReturned.setName("ReadDatasetProvc");

        Rows rows;
        for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
                rows.push_back((RowInterface*)*r);
        }
        this->makeMessage((TableInterface*)dspTable, rows.begin(), rows.end(), msgReturned);

}

DatasetProvcManagerParent::~DatasetProvcManagerParent() {
	delete dspTable;
        this->cleanup(); 
}

