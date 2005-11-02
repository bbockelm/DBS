# include "Managers.hpp"
#include <string>

AnalysisDatasetManager::AnalysisDatasetManager() {
	analysisDatasetTable = new AnalysisdatasetMultiTable(dbManager);
}

int AnalysisDatasetManager::write(Message* msgReceived, Message& msgReturned) {

    /*****************************

      analysisdatasetannotation
      analysiscollectionstatus
      numberofevents
      analysisdatasetstatus
      validationstatus
      eventcollectionid   <--------This is int type ????

      ???Other Quertyable Parameters ?????

    ******************************/

    /*Lets decoide about EXTRA Parameters later on. We don't need this much detail at this time.
    otherQueryAbleEventsData = Message->getElementValue("otherQueryAbleEventsData");
    otherQueryAbleAnalysisData = Message->getElementValue("otherQueryAbleAnalysisData");
    */

	Analysisdatasetmultirow* aRow =  new Analysisdatasetmultirow();
	int retval = setRowValues(analysisDatasetTable, aRow, msgReceived,"",0);

	analysisDatasetTable->addRow(aRow);
	if (!this->doInsert((TableInterface*)analysisDatasetTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("AnalysisDatasetInsert");
	string name = "t_analysis_dataset.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;

    
}

AnalysisDatasetManager::~AnalysisDatasetManager() {
	delete analysisDatasetTable;
        this->cleanup(); 
}

