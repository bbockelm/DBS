#ifndef _Communicate_hpp_included_
#define _Communicate_hpp_included_
#define MAX_MESSAGE_LEN 100
#include "Message.hpp"
#include "GSS.hpp"
#include "GSSException.hpp"
#include <string>
class  Communicate {
	private:
		GSS * g;
	public:
		Communicate(GSS * g);
		~Communicate();
		void send(Message & m);
		void send(std::string message);
		void recv(std::string & message);
		void recv(Message & m);
};
#endif

