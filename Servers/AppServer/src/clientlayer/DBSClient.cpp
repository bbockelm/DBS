#include "DBSClient.hpp"
#include "Log.hpp"
#include "BizLayerException.hpp"
#include <exception>

DBSClient::DBSClient() {
  //cout << "DBSClient() " << endl; 
	static Log l("TableTemplate");
	DBSClient::logger = l.getLogger();
	Configuration* conf = Configuration::instance();
	string serverType = conf->getServerType(); 
	LOG4CXX_INFO(logger, "Server Type is " + serverType);
	//cout << "Server Type: " << serverType << endl;
  if ( serverType == "Local" ) {
    localServer = true;
  }
  else {
  /* try {
        localServer = false; 
        serverHost = cmssrv22.fnal.gov;  //conf->getParameter("DBSServer");
        serverSoc = 9999;  //conf->getParameter("DBSServerSoc");
        backlog = 5;   //conf->getParameter("DBSServerBackLog");
        gSecure = new GSS(); 
     } catch (SocketException &e) {
        LOG4CXX_ERROR(logger, e.report());
     } catch (GSSException &e) {
       LOG4CXX_ERROR(logger, e.report());
     }
  */
   //cout << "Go Figure...!" << endl; 
  }
}  

DBSClient::~DBSClient() {
  
}

//int DBSClient::callServer() throw (BizLayerException, ObjectLayerException) {
int DBSClient::callServer()  {
	try { 
		if (localServer) {
    //cout<<"Calling the DBSDispatcher"<<endl; 
    //cout<<"Invoking Server Operation: " << this->mSend.getName() << endl; 
			DBSDispatcher dispatcher;
			return (dispatcher.run(&this->mSend, this->mRecv));
    //cout<<"Returned From the DBSDispatcher"<<endl;
		}else { //Remote server
    /*
    //open server socket connnection and send the message through
    Socket socket(serverHost, serverSoc);
    gSecure->cOpen(socket.connect());
    Communicate c(g);
    c.send(this->mSend);
    //wait for return message !
    c.recv(this->mRecv);
    */
   //cout << "No Remote Calling...!" << endl;
		}
	} catch (BizLayerException &e)  {
		throw e.report().c_str();
		return 0;
	}
/*string isException = this->mRecv.getException(); 
if (isException != "" ) {
	return 0;
}*/
	return 1;
}



int DBSClient::raiseServerException(void) {

 string sException = this->mRecv.getException();
 string msg = "Server Raised an exception:";
 msg += sException;
 cout << msg << endl;
 throw msg;

}

/*
int DBSClient::buildDataset(string basepathName, string analysisDataset) {

    vector<string> basepathNameTokens;
    util.tokenize(basepathName, basepathNameTokens, "/");
    if ( basepathNameTokens.size() != 3 ) {
       throw "Damn it!...Provide full baseNamePath"; 
    }   
    
    Processingpath_ClientAPIData processPath;

    processPath.abstractdatasetname = basepathNameTokens.at(0);
    processPath.datatiername = basepathNameTokens.at(1);
    processPath.cobraownername = basepathNameTokens.at(2);
    
     
    this->mSend.dispose();
    this->mSend.setName("CreateProcessedDataset"); 
    processPath.makeMessage(this->mSend);
    
    int success = this->callServer();
    return success;  
}
*/



int DBSClient::addPerson(Person_ClientAPIData& personInfo) throw (const char*){
        this->mSend.dispose();
        this->mRecv.dispose();


        this->mSend.setName("AddPerson");
        personInfo.makeMessage(this->mSend);

        int success = this->callServer();

        if ( success == 1 ) {
           string value = this->mRecv.getElementValue("personid");
           if ( value != "NOTFOUND" ) {
              return util.atoi(value);
           }
        }
        return success;
}
/*
int DBSClient::addRole(Role_ClientAPIData& roleInfo) {

        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("AddRole");
        roleInfo.makeMessage(this->mSend);

        int success = this->callServer();

        if ( success == 1 ) {
           string value = this->mRecv.getElementValue("roleid");
           if ( value != "NOTFOUND" ) {
		return util.atoi(value);
           }
        }
        return success;
}

int DBSClient::assignRole(Administrative_ClientAPIData& adminInfo) {

        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("AssignRole");
        adminInfo.makeMessage(this->mSend);

        int success = this->callServer();

        if ( success == 1 ) {
           string value = this->mRecv.getElementValue("assignedroleid");
           if ( value != "NOTFOUND" ) {
		return util.atoi(value);
           }
        }
        return success;
}
*/
int DBSClient::assignPhysicsGroup(Physicsgroup_ClientAPIData& phgrpInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("AssignPhysicsGroup");
        phgrpInfo.makeMessage(this->mSend);

        int success = this->callServer();

        if ( success == 1 ) {
           string value = this->mRecv.getElementValue("physicsgroupid");
           if ( value != "NOTFOUND" ) {
		return util.atoi(value);
           }
        }
        return success;


}

//int DBSClient::createPrimaryDataset(string primaryDatasetName,  Primarydataset_ClientAPIData& primaryDatasetInfo) throw (const char*) {
int DBSClient::createPrimaryDataset(Primarydataset_ClientAPIData& primaryDatasetInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

	this->mSend.setName("CreatePrimaryDataset");
	primaryDatasetInfo.makeMessage(this->mSend);
	int success = this->callServer();

        if ( success == 1 ) {
	//cout<<"trying to get value of primarydatasetid" <<endl;
           string value = this->mRecv.getElementValue("id");
	//cout<<"value is "<<value<<endl;
           if ( value != "NOTFOUND" ) {
		return util.atoi(value);
           }
        }

	return success;
}

int DBSClient::createProcessedDataset(Processingpath_ClientAPIData& processingPathInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();


        this->mSend.setName("CreateProcessedDataset");
        processingPathInfo.makeMessage(this->mSend);
	//cout<<"Calling server"<<endl;
        int success = this->callServer();
	//cout<<"DONE Calling server"<<endl;
	//cout<<"inside DBSClient::createProcessedDataset"<<endl;
        if ( success == 1 ) {
           string value = this->mRecv.getElementValue("id");
		//cout<<"The processed daraset Value is "<<value<<endl;
           if ( value != "NOTFOUND" ) {
		//cout<<"returnning "<<value<<endl;
		return util.atoi(value);
           }
        }
	cout<<"returnning success"<<endl;
        return success;
}

int DBSClient::createAnalysisDataset(Analysisdataset_ClientAPIData& analysisDatasetInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("CreateAnalysisDataset");
        analysisDatasetInfo.makeMessage(this->mSend);

        int success = this->callServer();
        if ( success == 1 ) {
           string value = this->mRecv.getElementValue("id");
           if ( value != "NOTFOUND" ) {
		return util.atoi(value);
           }
        }

        return success;
}


int DBSClient::insertApps(Insertapps_ClientAPIData& appsInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

	this->mSend.setName("InsertApps");
	appsInfo.makeMessage(this->mSend);

	string m;
	this->mSend.serialize(m);
	//cout<<"Serlized message is "<<m<<endl;


	int success = this->callServer();
	if ( success == 1 ) {
		string value = this->mRecv.getElementValue("id");
		if ( value != "NOTFOUND" ) {
			return util.atoi(value);
		}
	}

	return success;
}


int DBSClient::insertFiles(vector<Fileview_ClientAPIData>& fileInfo) throw (const char*) {
	this->mSend.dispose();
	this->mSend.setName("WriteFiles");


	for(int i=0;i < fileInfo.size(); i++) {
		Fileview_ClientAPIData thisParam = fileInfo.at(i);
		Message tmpMesssage;
		thisParam.makeMessage(tmpMesssage);
		this->mSend.appendToVec( tmpMesssage, "fileparams" );
	}
	int success = this->callServer();
	if ( success == 1 ) {
		string value = this->mRecv.getElementValue("evcoll");
		if ( value != "NOTFOUND" ) {
			return util.atoi(value);
		}
	}
	return success;
}


int DBSClient::insertEventCollections(Evcollview_ClientAPIData& ecInfo) throw (const char*) {
	this->mSend.dispose();
	this->mSend.setName("WriteEventCollection");
	ecInfo.makeMessage(this->mSend);
         
	int success = this->callServer();
	if ( success == 1 ) {
		string value = this->mRecv.getElementValue("id");
		if ( value != "NOTFOUND" ) {
			return util.atoi(value);
		}
	}
	return success;

}



int DBSClient::readPrimaryDataset(Primarydataset_ClientAPIData apiDataToSend, 
                                           vector<Primarydataset_ClientAPIData>& primaryDatasetInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

	this->mSend.setName("ReadPrimaryDataset");
	apiDataToSend.makeMessage(mSend);
	
	int success = this->callServer();

        string message;
        mRecv.serialize(message);
        //cout << "readPrimaryDataset:: WHOLE Message Returned is:-" << message << endl;  

	MapIter m = this->mRecv.getMapIterBegin();
	if(m != this->mRecv.getMapIterEnd()) {
		for(int i = 0; i != ((VecData*)(m->second))->size(); ++i ) {
                        //cout << "readPrimaryDataset:: Individual Item being Read:-" << endl;
			Primarydataset_ClientAPIData apiData;
			apiData.readInMessage(this->mRecv,"ROWS",i);
			primaryDatasetInfo.push_back(apiData);
		}
	}

	return success;
}


int DBSClient::readProcessingPath(Processingpath_ClientAPIData apiDataToSend, 
                                  vector<Processingpath_ClientAPIData>& procPathInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("ReadProcessedDataset");
        apiDataToSend.makeMessage(mSend);

        int success = this->callServer();

        string message;
        mRecv.serialize(message);
        //cout << "readProcessingPath:: WHOLE Message Returned is:-" << message << endl;

        MapIter m = this->mRecv.getMapIterBegin();
        if(m != this->mRecv.getMapIterEnd()) {
                for(int i = 0; i != ((VecData*)(m->second))->size(); ++i ) {
                        //cout << "readProcessingPath:: Individual Item being Read:-" << endl;
                        Processingpath_ClientAPIData apiData;
                        apiData.readInMessage(this->mRecv,"ROWS",i);
                        procPathInfo.push_back(apiData);
                }
        }

        return success;
}

int DBSClient::getDatasetProvenenceParent(Datasetprovenenceevparent_ClientAPIData apiDataToSend, 
                                       vector<Datasetprovenenceevparent_ClientAPIData>& dspInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("ReadDatasetProvcParent");
        apiDataToSend.makeMessage(mSend);
 
        int success = this->callServer();
        string message; 
        mRecv.serialize(message);
        //cout << "getDatasetProvenence:: WHOLE Message Returned is:-" << message << endl;
                
        MapIter m = this->mRecv.getMapIterBegin();
        if(m != this->mRecv.getMapIterEnd()) {
                for(int i = 0; i != ((VecData*)(m->second))->size(); ++i ) {
                        //cout << "getDatasetProvenence:: Individual Item being Read:-" << endl;
                        Datasetprovenenceevparent_ClientAPIData apiData;
                        apiData.readInMessage(this->mRecv,"ROWS",i);
                        dspInfo.push_back(apiData);
                }
        }

        return success;
}

int DBSClient::getDatasetProvenenceChild(Datasetprovenenceevchild_ClientAPIData apiDataToSend,
                                       vector<Datasetprovenenceevchild_ClientAPIData>& dspInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("ReadDatasetProvcChild");
        apiDataToSend.makeMessage(mSend);

        int success = this->callServer();
        string message; 
        mRecv.serialize(message);
        //cout << "getDatasetProvenence:: WHOLE Message Returned is:-" << message << endl;
                
        MapIter m = this->mRecv.getMapIterBegin();
        if(m != this->mRecv.getMapIterEnd()) {
                for(int i = 0; i != ((VecData*)(m->second))->size(); ++i ) {
                        //cout << "getDatasetProvenence:: Individual Item being Read:-" << endl;
                        Datasetprovenenceevchild_ClientAPIData apiData;
                        apiData.readInMessage(this->mRecv,"ROWS",i);
                        dspInfo.push_back(apiData);
                }
        }

        return success;
}


int DBSClient::readEvColls(Evcollview_ClientAPIData apiDataToSend, 
                                              vector<Evcollview_ClientAPIData>& evCollInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("ReadEventCollection");
        apiDataToSend.makeMessage(mSend);

        int success = this->callServer();
        string message;
        mRecv.serialize(message);
        //cout << "readEvColls:: WHOLE Message Returned is:-" << message << endl;

        MapIter m = this->mRecv.getMapIterBegin();
        if(m != this->mRecv.getMapIterEnd()) {
                for(int i = 0; i != ((VecData*)(m->second))->size(); ++i ) {
                        //cout << "readPrimaryDataset:: Individual Item being Read:-" << endl;
                        Evcollview_ClientAPIData apiData;
                        apiData.readInMessage(this->mRecv,"ROWS",i);
                        evCollInfo.push_back(apiData);
                }
        }

        return success;
}


int DBSClient::readCRABEvColls(Crabevcollview_ClientAPIData apiDataToSend,
                                              vector<Crabevcollview_ClientAPIData>& evCollInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("ReadCRABEventCollection");
        apiDataToSend.makeMessage(mSend);

        int success = this->callServer();
        //string message;
        //mRecv.serialize(message);
        //cout << "readCRABEvColls:: WHOLE Message Returned is:-" << message << endl;

        MapIter m = this->mRecv.getMapIterBegin();
        if(m != this->mRecv.getMapIterEnd()) {
                for(int i = 0; i != ((VecData*)(m->second))->size(); ++i ) {
                        //cout << "readPrimaryDataset:: Individual Item being Read:-" << endl;
                        Crabevcollview_ClientAPIData apiData;
                        apiData.readInMessage(this->mRecv,"ROWS",i);
                        evCollInfo.push_back(apiData);
                }
        }

        return success;
}


int DBSClient::readEvCollFiles(Fileview_ClientAPIData apiDataToSend, 
                                              vector<Fileview_ClientAPIData>& fileInfo) throw (const char*) {
        this->mSend.dispose();
        this->mRecv.dispose();

        this->mSend.setName("ReadFiles");
        apiDataToSend.makeMessage(mSend);
 
        int success = this->callServer();
        string message;
        mRecv.serialize(message);
        //cout << "EvCollFiles:::  WHOLE Message Returned is:-" << message << endl;

        MapIter m = this->mRecv.getMapIterBegin();
        if(m != this->mRecv.getMapIterEnd()) {
                for(int i = 0; i != ((VecData*)(m->second))->size(); ++i ) {
                        //cout << "EvCollFiles:: Individual Item being Read:-" << endl;
                        Fileview_ClientAPIData apiData;
                        apiData.readInMessage(this->mRecv,"ROWS",i);
                        fileInfo.push_back(apiData);
                }
        }

        return success;
}

int DBSClient::insertFileBlock(Blockview_ClientAPIData& blockInfo) throw (const char*){
        this->mSend.dispose();
        this->mRecv.dispose();

	this->mSend.setName("InsertFileBlock");
	blockInfo.makeMessage(this->mSend);
	int success = this->callServer();

        if ( success == 1 ) {
           string value = this->mRecv.getElementValue("id");
		//cout<<"value is "<<value<<endl;
           if ( value != "NOTFOUND" ) {
		return util.atoi(value);
           }
        }

	return success;
}

/*
int DBSClient::createPrimaryDataset(string primaryDatasetName, PrimaryDatasetInfo primaryDatasetInfo) {
  //API Call that creates a Primary Dataset//
  //Primary dataset is the ROOT of the path, so no pathInfo//
  
  this->mSend.dispose();
  mSend.setName("CreatePrimaryDataset");

  char c = primaryDatasetInfo.mcDataset.getValue();
  string mcdataset(1, c);
  this->mSend.addElement(new Element((string)"mcdataset", mcdataset, (string)"CHARACTER"));

  if ( (string*)(&(primaryDatasetInfo.mcChannel.getValue())) != NULL ) {
     
    this->mSend.addElement(new Element((string)"mcproductiondesc", (string)primaryDatasetInfo.mcProductionDesc.getValue(), (string)"STRING"));
    this->mSend.addElement(new Element((string)"mcdecaychain", (string)primaryDatasetInfo.mcDecayChain.getValue(), (string)"STRING"));
    this->mSend.addElement(new Element((string)"mcchanneldescription", (string)primaryDatasetInfo.mcChannelDesc.getValue(), (string)"STRING"));
    this->mSend.addElement(new Element((string)"mcchannel", (string)primaryDatasetInfo.mcChannel.getValue(), (string)"STRING"));
  }
  if ( (string*)(&(primaryDatasetInfo.triggerPath.getValue())) != NULL ) {
    cout <<"!!!!!!!!!!!!!!!!!!!! triggerPath "<< (string)primaryDatasetInfo.triggerPathDesc.getValue() << endl;
    this->mSend.addElement(new Element((string)"triggerpathdescription", (string)primaryDatasetInfo.triggerPathDesc.getValue(), (string)"STRING"));
    this->mSend.addElement(new Element((string)"triggerpath", (string)primaryDatasetInfo.triggerPath.getValue(), (string)"STRING"));
  }
  if ( (string*)(&(primaryDatasetInfo.streamComments.getValue())) != NULL ) {
    this->mSend.addElement(new Element((string)"streamcomments", (string)primaryDatasetInfo.streamComments.getValue(), (string)"STRING"));
  }
  this->mSend.addElement(new Element((string)"primarydatasetname", primaryDatasetName,(string)"STRING"));
  this->mSend.addElement(new Element((string)"cobradatasetname", (string)primaryDatasetInfo.cobraDatasetName.getValue(),(string)"STRING"));
  this->mSend.addElement(new Element((string)"primarydatasetannotation", (string)primaryDatasetInfo.primaryDatasetAnnotation.getValue(),(string)"STRING"));
  this->mSend.addElement(new Element((string)"physicsgroupname", (string)primaryDatasetInfo.physicsGroupName.getValue(),(string)"STRING"));
  this->mSend.addElement(new Element((string)"streamname", (string)primaryDatasetInfo.streamName.getValue(),(string)"STRING"));
  
  int success = this->callServer();
  return success;
  // returned Message mRecv has what server done to the operation
  //  Analyse it and return to caller 
}
*/

