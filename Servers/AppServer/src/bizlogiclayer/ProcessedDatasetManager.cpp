#include "Managers.hpp"
#include <string>

ProcessedDatasetManager::ProcessedDatasetManager() {
	processedDatasetTable = new ProcessingpathMultiTable(dbManager);
}


int ProcessedDatasetManager::write(Message* msgReceived, Message& msgReturned) {

  /*****************************
   API method to publish a processed dataset
        The arguments are in order

          datasetName
            dataset name of the primary dataset
   
          ownerName
            owner name (eg- the COBRA owner name)
 
          parantProcPathID
            parent processing path id (can be null) 

          applicationConfiguration
            application configuration id (returned by publishApplicationConfiguration)

          agPath 
   ******************************/

	Processingpathmultirow* aRow = new Processingpathmultirow();
	int retval = setRowValues(processedDatasetTable, aRow, msgReceived,"",0);
	processedDatasetTable->addRow(aRow);

	if (!this->doInsert((TableInterface*)processedDatasetTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("ProcessingPathInsert");
	string name = "t_processed_dataset.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;

}

int ProcessedDatasetManager::read(Message* msgReceived, Message& msgReturned) {
        typedef vector<Processingpathmultirow*> MyRows;
        typedef MyRows::iterator MyRowIter;

        string addQuery = findKeyMakeQuery(msgReceived);

        MyRows myRows = this->processedDatasetTable->select(addQuery);
        
        cout << "ProcessedDatasetManager::read Retrun from select()" << endl;
        cout << "ProcessedDatasetManager::read Rows Read " << myRows.size() << endl;

        msgReturned.setName("ReadProcessedDataset");

        Rows rows;
        for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
                rows.push_back((RowInterface*)*r);
        }

        int ret = this->makeMessage((TableInterface*)processedDatasetTable, rows.begin(), rows.end(), msgReturned);
        cout << "ProcessedDatasetManager::read Retrun from makeMessage " << ret << endl;

}


ProcessedDatasetManager::~ProcessedDatasetManager() {
	delete processedDatasetTable;
}

