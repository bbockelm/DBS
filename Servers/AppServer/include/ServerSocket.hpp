#ifndef _ServerSocket_hpp_included_
#define _ServerSocket_hpp_included_

#include <sys/socket.h>
#include "Socket.hpp"

class ServerSocket {
	private: 
		int listenfd;
	public:
		ServerSocket(int port, int backlog);
		Socket* accept();
};
#endif
