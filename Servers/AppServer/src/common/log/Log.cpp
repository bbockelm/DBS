#include "Log.hpp"
#include "Configuration.hpp"
//#include <log4cxx/patternlayout.h>
#include <log4cxx/propertyconfigurator.h>


using namespace log4cxx;
using namespace log4cxx::helpers;
using namespace std;
Log::Log(string className) {
	this->logger = Logger::getLogger(className);
	Configuration* conf = Configuration::instance();
	PropertyConfigurator::configure(conf->getLog());
	//PropertyConfigurator::configure("/home/sekhri/work/dm/new3/cms_dm_dev/ThinServer/etc/log4cxx.properties");
	/*Log::logger = Logger::getLogger(className);
	static const char *layoutPattern = "[%t] %p %c %x - %m%n";
	PatternLayout* pl = new PatternLayout(layoutPattern);
	RollingFileAppender* rollingFileAppender = new RollingFileAppender( pl, fileName );
	rollingFileAppender->setMaxBackupIndex( 10 );
	rollingFileAppender->setMaxFileSize( "1MB" );
	this->logger->addAppender(rollingFileAppender);*/
}

Log::~Log(){}
LoggerPtr Log::getLogger() {
	return this->logger;
}

