#include <sys/socket.h>
#include <netdb.h>
#include "Socket.hpp"
#include "SocketException.hpp"

using namespace std;
Socket::Socket(string fqdn, int port) {
	this->fqdn = fqdn;
	this->port = port;
	
}

Socket::Socket(){
}

void Socket::setDes(int des){
	this->descriptor = des;
}
void Socket::setFqdn(string fqdn){
	this->fqdn = fqdn;
}
void Socket::setPort(int port){
	this->port = port;
}
int Socket::getDes(){
	return this->descriptor;
}

int Socket::connect(){
	struct sockaddr_in serverAddress;
	struct hostent* hostName;

	bzero(&serverAddress,sizeof(serverAddress));//initializing the serveraddress to zero
	serverAddress.sin_family = AF_INET;//specifying the parameters of socket address structure
	hostName = gethostbyname(fqdn.c_str());
	if (hostName == NULL) {
		throw SocketException("Socket FQAN lookup failed");
	}
	serverAddress.sin_family = hostName->h_addrtype;
	serverAddress.sin_port = htons(port);
	memcpy((char *)&serverAddress.sin_addr, 
		hostName->h_addr, 
		sizeof(serverAddress.sin_addr));

	this->descriptor = socket(AF_INET, SOCK_STREAM, 0);
	if(this->descriptor < 0) {
		throw SocketException("Socket Descriptor creation failed");
	}

	if( ::connect(this->descriptor, 
			(struct sockaddr*) &serverAddress, 
			sizeof(serverAddress)) 
		< 0) {
		throw SocketException("Socket Binding failed");
	}

	return this->descriptor;
}


