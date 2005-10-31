#include <iostream>
#include <sys/socket.h>
#include <arpa/inet.h>
#include "ServerSocket.hpp"
#include "SocketException.hpp"
using namespace std;

ServerSocket::ServerSocket(int port, int backlog) {
	struct sockaddr_in serverAddress;
	bzero((char*)&serverAddress,sizeof(serverAddress));//initializing the serveraddress to zero
	serverAddress.sin_family = AF_INET;//specifying the parameters of socket address structure
	serverAddress.sin_port = htons(port);
	serverAddress.sin_addr.s_addr = htonl(INADDR_ANY);
	listenfd = socket(AF_INET , SOCK_STREAM,0);
	if(listenfd < 0){
		//cout<<"error:server not able to complete the connection\n";
		throw SocketException("ERROR : Socket creation failed");
	}
	if(bind(listenfd,
		((struct sockaddr*)&serverAddress),
		sizeof(serverAddress))<0) {
		throw SocketException("ERROR: Socket bind failed");
		//perror("server: bind");
	}
	listen(listenfd, backlog);
}

Socket* ServerSocket::accept(){
	struct sockaddr_in clientAddress;
	int clientLength=sizeof(clientAddress);
	int des = ::accept(listenfd,
			(struct sockaddr*)&clientAddress,
			(socklen_t*)&clientLength);
	if(des < 0) {
		throw SocketException("ERROR: Client connection failed");
		//cout<<"connection not accepted\n";
	}
	Socket* socketPointer = new Socket();
	socketPointer->setDes(des);
	return(socketPointer);
}

/*int main(){
	ServerSocket ss(9999,5);
	Socket* cl = ss.accept();
}*/

