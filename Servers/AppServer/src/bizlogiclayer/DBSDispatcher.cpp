#include "DBSDispatcher.hpp"
#include "BizLayerException.hpp"
#include "ObjectLayerException.hpp"
#include "Log.hpp"

int DBSDispatcher::run(Message* msgReceived, Message& msgReturned) {
	static Log l("DBSDispatcher");
	log4cxx::LoggerPtr logger = l.getLogger();
	string operation = msgReceived->getName();
	LOG4CXX_INFO(logger,"Client Asked For : " + operation);
	//cout << "Client Asked For : " << operation << endl;

	try {

		if (operation.compare("AddPerson")==0) {
			PersonManager manager;
			manager.write(msgReceived, msgReturned);
		}
                /*
		else if (operation.compare("AddRole")==0) {
			RoleManager manager;
			manager.write(msgReceived, msgReturned);
		}
		else if (operation.compare("AssignRole")==0) {	
			AssignRoleManager manager;
			manager.write(msgReceived, msgReturned);
		}
                */
		else if (operation.compare("AssignPhysicsGroup")==0) {	
			PhysicsGroupManager manager;
			manager.write(msgReceived, msgReturned);
		}
		else if (operation.compare("CreatePrimaryDataset")==0) {	
			PrimaryDatasetManager manager;
			manager.write(msgReceived, msgReturned);
		}
                else if (operation.compare("ReadProcessedDataset")==0) {
                        ProcessedDatasetManager manager;
                        manager.read(msgReceived, msgReturned);
                }

		else if (operation.compare("ReadPrimaryDataset")==0) {	
			PrimaryDatasetManager manager;
			manager.read(msgReceived, msgReturned);
		}
		else if (operation.compare("CreateProcessedDataset")==0) {	
			ProcessedDatasetManager manager;
			manager.write(msgReceived, msgReturned);
		}
		else if (operation.compare("CreateAnalysisDataset")==0) {	
			AnalysisDatasetManager manager;
			manager.write(msgReceived, msgReturned);
		}
		else if (operation.compare("InsertApps")==0) {	
			AppConfigManager manager;
			manager.write(msgReceived, msgReturned);
		}

                else if (operation.compare("ReadFiles")==0) {
                        FileManager manager;
                        manager.read(msgReceived, msgReturned);
                }

		else if (operation.compare("ReadEventCollection")==0) {	
			ECReadManager manager;
			manager.read(msgReceived, msgReturned);
		} 
                else if (operation.compare("ReadCRABEventCollection")==0) {
                        CRABECReadManager manager;
                        manager.read(msgReceived, msgReturned);
                }
		else if (operation.compare("WriteFiles")==0) {	
			FileManager manager;
			manager.write(msgReceived, msgReturned);
		}

		else if (operation.compare("WriteEventCollection")==0) {	
			ECWriteManager manager;
			manager.write(msgReceived, msgReturned);
		}
                else if (operation.compare("ReadDatasetProvcParent")==0) {
                        DatasetProvcManagerParent manager;
                        manager.read(msgReceived, msgReturned);
                } 
                else if (operation.compare("ReadDatasetProvcChild")==0) {
                        DatasetProvcManagerChild manager;
                        manager.read(msgReceived, msgReturned);
                }
		else {
			msgReturned.setException("Operation " + operation + " Not Found"); 
			throw BizLayerException("Operation " + operation + " Not Found");
		} 
	} catch (exception &e)  {
		msgReturned.setException(e.what());
		throw BizLayerException(e.what());
	} catch (ObjectLayerException &e)  {
		msgReturned.setException(e.report());
		throw BizLayerException(e.report());
	}



}

