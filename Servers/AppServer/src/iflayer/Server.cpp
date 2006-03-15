#include "soapH.h"
#include "DBS.nsmap"
#include "Configuration.hpp"
#include <iostream.h>
#include <pthread.h>
//#define BACKLOG (10) // Max. request backlog
//#define MAX_THR (2) // Max. threads to serve requests
//using namespace std;
int main(int argc, char **argv) {
	struct soap soap;
	soap_init(&soap);
	Configuration* conf = Configuration::instance();
	int MAX_THR = conf->getMaxThreads();
	struct soap *soap_thr[MAX_THR]; // each thread needs a runtime environment
	pthread_t tid[MAX_THR];
	//int port = atoi(argv[1]); // first command-line arg is port
	SOAP_SOCKET m, s;
	int i;
	m = soap_bind(&soap, NULL, conf->getPort(), conf->getBacklog());
	if (!soap_valid_socket(m)) {
		exit(1);
	}
	cout<<"Socket connection successful "<<m<<endl;
	for (i = 0; i < MAX_THR; i++) {
		soap_thr[i] = NULL;
	}

	for (;;) {
		for (i = 0; i < MAX_THR; i++) {
			s = soap_accept(&soap);
			if (!soap_valid_socket(s)) {
				if (soap.errnum) {
					soap_print_fault(&soap, stderr);
					continue; // retry
				} else {
					cout<<"Server timed out"<<endl;
					break;
				}
			}
			cout<<"Thread "<<i<<" accepts socket connection from IP "<<soap.ip<<endl;
			if (!soap_thr[i]) { // first time around
				soap_thr[i] = soap_copy(&soap);
				if (!soap_thr[i]) {
					exit(1); // could not allocate
				}
			} else {// recycle soap environment
				pthread_join(tid[i], NULL);
				cout<<"Thread "<<i<<" completed"<<endl;
				soap_destroy(soap_thr[i]); // deallocate C++ data of old thread
				soap_end(soap_thr[i]); // deallocate data of old thread
			}
			soap_thr[i]->socket = s; // new socket fd
			pthread_create(&tid[i], NULL, (void*(*)(void*))soap_serve, (void*)soap_thr[i]);
		}
	}
	for (i = 0; i < MAX_THR; i++) {
		if (soap_thr[i]) {
			soap_done(soap_thr[i]); // detach context
			free(soap_thr[i]); // free up
		}
	}
	return 0;
}


