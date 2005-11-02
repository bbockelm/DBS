# include "Managers.hpp"

AppConfigManager::AppConfigManager() {
	appsConfigTable = new InsertappsMultiTable(dbManager);
}

int AppConfigManager::write(Message* msgReceived, Message& msgReturned) {

  /*****************************
        API method for publishing an Application Configuration.
        The arguments are in order: 

          appFamName
            Application Family Name (eg- CMKIN, OSCAR, ORCA)

          exeName
            Executable Name  

          appVersion
            Application Version

          paramSetName
            Parameter Set Name  (A unique name to be given by 
                  client for future reference.  This will be
                  automatically generated in teh future.)
            
          paramSetVersion
            Parameter Set Version  (To be given by client for 
                  future reference - this will be automatically 
                  generated in the future..)

          paramSetVersion
            Parameter Set Comments 

          parameterBindings
            Parameter Bindings  (dictionary of key/value pairs)

          inputType (optional)
            Input Type (eg- NoInput, CMKIN, OSCAR, etc.)

          outputType (optional)
            OutputType (eg- CMKIN, OSCAR, ORCA, NoOutput, etc.) 

          CalibrationVersionTag

          ConditionsVersionTag

   ******************************/

	Insertappsmultirow* aRow = new Insertappsmultirow();
	int retval = setRowValues(appsConfigTable, aRow, msgReceived,"",0);
	appsConfigTable->addRow(aRow);

	if (!this->doInsert((TableInterface*)appsConfigTable, msgReturned) ) { 
		return 0;
	}
	msgReturned.setName("AppsConfigManager");
	string name = "t_app_config.id";
	string dataType = "INTEGER";
	if(!util.isSet(aRow, name, dataType ) ) {
		return 0;
	}
	string value = util.getStrValue(aRow, name, dataType);
	Element* e = new Element(util.getTokenAt(name,1), value, dataType);
	msgReturned.addElement(e);
	
	return 1;

}

AppConfigManager::~AppConfigManager() {
   	delete appsConfigTable;
        this->cleanup(); 
}

