#include "GSS.hpp"
#include "GSSException.hpp"
#include "ServerSocket.hpp"
#include "SocketException.hpp"
#include "Log.hpp"
#include "Message.hpp"
#include "Communicate.hpp"
#include "Managers.hpp"
#include <log4cxx/logger.h>
#include <string>
#include <iostream.h>
#include <sys/wait.h>
#include "DBSThreadManager.hpp"


using namespace std;
using namespace log4cxx;

class DBSServer {

public:
    DBSServer();
    ~DBSServer();
    int TestDB();
    int run();
private:
    LoggerPtr logger;
    GSS* gsecure;
    DBSThreadManager* threadmgr;
    ServerSocket* serverSoc;
};

DBSServer::DBSServer(){

    Log l("DBSServer");
    logger = l.getLogger();
    LOG4CXX_INFO(logger, "Starting DBS Server");

    try {
        gsecure = new GSS();
        serverSoc= new ServerSocket(9999,5);
        if (TestDB()) {
        run();
        }
    } catch (SocketException &e) {
        LOG4CXX_ERROR(logger, e.report());
    } catch (GSSException &e) {
        LOG4CXX_ERROR(logger, e.report());
    }
}

int DBSServer::TestDB(void) {

   return 1;

}

int DBSServer::run(void) {
    threadmgr = new DBSThreadManager();
    cout << "DBS Server is now listening...." << endl;
    while(true) {
       Socket* clientsock = serverSoc->accept();
       threadmgr->startNewThread(gsecure, clientsock);
   } 
}

DBSServer::~DBSServer(){

  delete gsecure;
  delete threadmgr;
  delete serverSoc;

}

int main() {

DBSServer dbs;
return 0;


}
