#include "Managers.hpp"

FileManager::FileManager() {
	cout<<"fileTable = new FileviewMultiTable(dbManager);"<<endl;
	fileTable = new FileviewMultiTable(dbManager);
	cout<<"DONE fileTable = new FileviewMultiTable(dbManager);"<<endl;
}
int FileManager::read(Message* msgReceived, Message& msgReturned) {
        typedef vector<Fileviewmultirow*> MyRows;
        typedef MyRows::iterator MyRowIter;
        string addQuery = findKeyMakeQuery(msgReceived);

        MyRows myRows = this->fileTable->select(addQuery);
        msgReturned.setName("FileRead");

        Rows rows;
        for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
                rows.push_back((RowInterface*)*r);
        }

        int ret = this->makeMessage((TableInterface*)fileTable, rows.begin(), rows.end(), msgReturned);
        cout << "FileManager::read Retrun from makeMessage " << ret << endl;

}
/*
int FileManager::write(Message* msgReceived, Message& msgReturned) {
	Fileviewmultirow* aRow = new Fileviewmultirow(); 
	int retval = setRowValues(ecTable, aRow, msgReceived,"",0);
	ecTable->addRow(aRow);
	if(!this->doInsert((TableInterface*)ecTable, msgReturned)) {
		return 0;
	}
	string name = "t_info_evcoll.event_collection";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	return 1;
	
  
}

*/


int FileManager::write(Message* msgReceived, Message& msgReturned) {
  	string listName = "fileparams";
	Fileviewmultirow* aRow;
	int noOfRows = 0;
	for(MapIter m = msgReceived->getMapIterBegin(); m != msgReceived->getMapIterEnd(); ++m ) {
		if(m->first == listName) {
			for(int i = 0; i != ((VecData*)(m->second))->size() ; ++i ) {
				++noOfRows;
				aRow = new Fileviewmultirow();
				int retval = setRowValues(fileTable, aRow, msgReceived, listName, i);
				fileTable->addRow(aRow);
			}
		}
	}
	if(noOfRows == 0) {
		return 1;
	}
	if (!this->doInsert((TableInterface*)fileTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("WriteFiles");
	string name = "t_evcoll_file.evcoll";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;
}


FileManager::~FileManager() {
	delete fileTable;
        this->cleanup(); 
}

