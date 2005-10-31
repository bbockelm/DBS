#include "Managers.hpp"

DatasetProvcManagerChild::DatasetProvcManagerChild() {
	this->dspTable = new DatasetprovenenceevchildMultiTable(dbManager);
}

int DatasetProvcManagerChild::read(Message* msgReceived, Message& msgReturned) {

        typedef vector<Datasetprovenenceevchildmultirow*> MyRows;
        typedef MyRows::iterator MyRowIter;

        string addQuery = findKeyMakeQuery(msgReceived);

        MyRows myRows = this->dspTable->select(addQuery);
        msgReturned.setName("ReadDatasetProvcChild");

        Rows rows;
        for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
                rows.push_back((RowInterface*)*r);
        }
        this->makeMessage((TableInterface*)dspTable, rows.begin(), rows.end(), msgReturned);

}

DatasetProvcManagerChild::~DatasetProvcManagerChild() {
	delete dspTable;
}

