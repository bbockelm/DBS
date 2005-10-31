#include "Managers.hpp"
#include "Util.hpp"
#include "ObjectLayerException.hpp"

PrimaryDatasetManager::PrimaryDatasetManager() {
	cout<<"in constructor of PrimaryDatasetManager "<<endl;
	priDatasetTable = new PrimarydatasetMultiTable(dbManager);
	cout<<"Done calling priDatasetTable = new PrimarydatasetMultiTable(dbManager); "<<endl;
}

int PrimaryDatasetManager::write(Message* msgReceived, Message& msgReturned) {

  /*****************************
     API method to publish a primary dataset
     The arguments are in order: 

          MCChannel, MCProduction, and MCDecayChain
                   OR
          TriggerPath
   
          datasetName
            A dataset name (eg- the COBRA dataset name) 

          datasetComments
            Comments

          streamName
            A Stream name

          physicsGroupName
            A physics group name
	******************************/
	Primarydatasetmultirow* aRow = new Primarydatasetmultirow();
        int retval = setRowValues(priDatasetTable, aRow, msgReceived, "",0);
	priDatasetTable->addRow(aRow);
	cout<<"calling do insert "<<endl;
	if (!this->doInsert((TableInterface*)priDatasetTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("PrimaryDatasetInsert");
	string name = "t_primary_dataset.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;

}

int PrimaryDatasetManager::read(Message* msgReceived, Message& msgReturned) {
	typedef vector<Primarydatasetmultirow*> MyRows;
	typedef MyRows::iterator MyRowIter;

        string addQuery = findKeyMakeQuery(msgReceived);
 
	MyRows myRows = this->priDatasetTable->select(addQuery);
	msgReturned.setName("PrimaryDatasetRead");

	Rows rows;
	for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
		rows.push_back((RowInterface*)*r);
	}
         
	int ret = this->makeMessage((TableInterface*)priDatasetTable, rows.begin(), rows.end(), msgReturned);
        cout << "PrimaryDatasetManager::read Retrun from makeMessage " << ret << endl;

}


PrimaryDatasetManager::~PrimaryDatasetManager() {
	delete priDatasetTable;
}

