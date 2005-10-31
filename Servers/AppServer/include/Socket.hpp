#ifndef _Socket_hpp_included_
#define _Socket_hpp_included_


#include <sys/socket.h>
#include <string>


class Socket{
	private:
		std::string fqdn;
		int port;
		int descriptor;
	public:
		Socket(std::string fqdn, int port);
		Socket();
		void setDes(int des);
		void setFqdn(std::string fqdn);
		void setPort(int port);
		int getDes();
		int connect();
};

#endif

