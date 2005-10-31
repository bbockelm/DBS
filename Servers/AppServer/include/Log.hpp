#ifndef _Log_hpp_included_
#define _Log_hpp_included_
#include <log4cxx/logger.h>
#include <string>

class  Log {
	private:
		log4cxx::LoggerPtr logger;
	public:
		Log(std::string className);
		~Log();
		log4cxx::LoggerPtr getLogger();
};
#endif

