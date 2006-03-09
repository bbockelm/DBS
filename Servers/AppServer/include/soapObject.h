/* soapObject.h
   Generated by gSOAP 2.7.6e from ../../interface/Cpp/Interface.hpp
   Copyright (C) 2000-2005, Robert van Engelen, Genivia Inc. All Rights Reserved.
   This part of the software is released under one of the following licenses:
   GPL, the gSOAP public license, or Genivia's license for commercial use.
*/

#ifndef soapService_H
#define soapService_H
#include "soapH.h"
#include <iostream>

using namespace std;
class Service : public soap
{    public:
	Service()
	{ static const struct Namespace namespaces[] =
{
	{"SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/", "http://www.w3.org/*/soap-envelope", NULL},
	{"SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/", "http://www.w3.org/*/soap-encoding", NULL},
	{"xsi", "http://www.w3.org/2001/XMLSchema-instance", "http://www.w3.org/*/XMLSchema-instance", NULL},
	{"xsd", "http://www.w3.org/2001/XMLSchema", "http://www.w3.org/*/XMLSchema", NULL},
	{"DBS", "http://tempuri.org/DBS.xsd", NULL, NULL},
	{NULL, NULL, NULL, NULL}
};
	soap_init(this); if (!this->namespaces) this->namespaces = namespaces; };
	virtual ~Service() { 
		cout<<"DESTROY *************************************"<<endl;
		soap_destroy(this); soap_end(this); soap_done(this); 
	}
	virtual	int serve() { return soap_serve(this); };
};
#endif
