#ifndef _DBSClient_hpp_
#define _DBSClient_hpp_

//#include "Socket.hpp"
//#include "GSS.hpp"
//#include "SocketException.hpp"
//#include "GSSException.hpp"
#include "Message.hpp"
//#include "Communicate.hpp"
#include "Configuration.hpp"
#include "DBSDispatcher.hpp"
#include "ClientAPIData.hpp"  
#include "Util.hpp"
#include "common.hpp"
#include <log4cxx/logger.h>
#include <iostream>
  
class DBSClient {

public:
	DBSClient();
	~DBSClient();
  
	//std::string buildDataset(std::string basepathName, std::string analysisDataset);
  /*  
	int buildDataset(std::string basepathName, std::string analysisDataset);

	int addEventCollection(std::string basePathName, ECInfo);
	pathName buildDataset(std::string basepathName, AD, std::list<EventColllections>);
	pathName buildDataset(styring basepathName, AD, predicate);    
	pathName buildDataset(std::string basepathName, AD); 

	std::list<EventColllections> displayDatasetContents(std::string pathName); 
	std::list<EventColllections> displayDatasetContents(sttring initialPathName, std::string predicate); 
  
	closeDataset();
	deleteDataset(pathName, deepFlag);
  
	addToDataset(pathName, std::list<EventColllections>); 
  
	globalPathName = publishDataset(pathName);
  
	std::list<EventColllections>, foundLumi = partialDataset(pathName, lumiDesired); */

        int addPerson(Person_ClientAPIData& personInfo) throw (const char*);
        //int addRole(Role_ClientAPIData& roleInfo);
        //int assignRole(Administrative_ClientAPIData& adminInfo);
        int assignPhysicsGroup(Physicsgroup_ClientAPIData& phgrpInfo) throw (const char*);
        int createProcessedDataset(Processingpath_ClientAPIData& processingPathInfo) throw (const char*);
        int createAnalysisDataset(Analysisdataset_ClientAPIData& analysisDatasetInfo) throw (const char*);

	int createPrimaryDataset(Primarydataset_ClientAPIData& primaryDatasetInfo) throw (const char*);
	int insertFileBlock(Blockview_ClientAPIData& blockInfo) throw (const char*);

	int insertApps(Insertapps_ClientAPIData& appsInfo) throw (const char*);
	int insertEventCollections(Evcollview_ClientAPIData& ecInfo) throw (const char*);
	int insertFiles(std::vector<Fileview_ClientAPIData>& fileInfo) throw (const char*);

	int readPrimaryDataset(Primarydataset_ClientAPIData, std::vector<Primarydataset_ClientAPIData>& primaryDatasetInfo) throw (const char*);
        int readEvColls(Evcollview_ClientAPIData, std::vector<Evcollview_ClientAPIData>& evCollInfo) throw (const char*);
        int readProcessingPath(Processingpath_ClientAPIData, vector<Processingpath_ClientAPIData>& procPathInfo) throw (const char*);
        int readEvCollFiles(Fileview_ClientAPIData, vector<Fileview_ClientAPIData>& fileInfo) throw (const char*);
        int getDatasetProvenenceParent(Datasetprovenenceevparent_ClientAPIData, 
                                   vector<Datasetprovenenceevparent_ClientAPIData>& dspInfo) throw (const char*);
        int getDatasetProvenenceChild(Datasetprovenenceevchild_ClientAPIData, 
                                   vector<Datasetprovenenceevchild_ClientAPIData>& dspInfo) throw (const char*);
	int readCRABEvColls(Crabevcollview_ClientAPIData apiDataToSend,
                                              vector<Crabevcollview_ClientAPIData>& evCollInfo) throw (const char*);

	//createProcessedDataset(/primary/DT/processedDatasetName, processedDatasetInfo);
	//showDatasetInfo(outputSelector);

private:
	int raiseServerException(void);

	Message mSend;
	Message mRecv;
	int callServer(void);
	bool localServer;
	//GSS* gSecure;
	Util util;
	log4cxx::LoggerPtr logger;
};

#endif
