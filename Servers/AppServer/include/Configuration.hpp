#include <string>
class Configuration  {

public:
	static Configuration* instance();
	int getPort();
	int getBacklog();
	int getMaxThreads();
	std::string getLog();
	std::string getDsn();
	std::string getDbUser();
	std::string getDbPasswd();
        std::string getServerType(); 
private:
	Configuration();
	Configuration(const Configuration&);
	Configuration& operator= (const Configuration&);
	static Configuration* pinstance;
	int port;
	int backlog;
	int maxthreads;
	std::string logFile;
	std::string dsn;
	std::string dbuser;
	std::string dbpasswd;
        std::string xyz; 
        std::string servertype; 

};
