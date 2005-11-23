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

        int addPerson(Person_ClientAPIData& personInfo);
        //int addRole(Role_ClientAPIData& roleInfo);
        //int assignRole(Administrative_ClientAPIData& adminInfo);
        int assignPhysicsGroup(Physicsgroup_ClientAPIData& phgrpInfo);
        int createProcessedDataset(Processingpath_ClientAPIData& processingPathInfo);
        int createAnalysisDataset(Analysisdataset_ClientAPIData& analysisDatasetInfo);

	int createPrimaryDataset(std::string primaryDatasetName, Primarydataset_ClientAPIData& primaryDatasetInfo);

	int insertApps(Insertapps_ClientAPIData& appsInfo);
	int insertEventCollections(Evcollview_ClientAPIData& ecInfo);
	int insertFiles(std::vector<Fileview_ClientAPIData*>& fileInfo);

	int readPrimaryDataset(Primarydataset_ClientAPIData, std::vector<Primarydataset_ClientAPIData*>& primaryDatasetInfo);
        int readEvColls(Evcollview_ClientAPIData, std::vector<Evcollview_ClientAPIData*>& evCollInfo);
        int readProcessingPath(Processingpath_ClientAPIData, vector<Processingpath_ClientAPIData*>& procPathInfo);
        int readEvCollFiles(Fileview_ClientAPIData, vector<Fileview_ClientAPIData*>& fileInfo);
        int getDatasetProvenenceParent(Datasetprovenenceevparent_ClientAPIData, 
                                   vector<Datasetprovenenceevparent_ClientAPIData*>& dspInfo);
        int getDatasetProvenenceChild(Datasetprovenenceevchild_ClientAPIData, 
                                   vector<Datasetprovenenceevchild_ClientAPIData*>& dspInfo);
        int readCRABEvColls(Crabevcollview_ClientAPIData apiDataToSend,
                                              vector<Crabevcollview_ClientAPIData*>& evCollInfo);
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
