#include "SoapDBSApi.hpp"
#include "ManagerImpls.hpp"
#include "BizLayerException.hpp"
#include <exception>


SoapDBSApi::SoapDBSApi(struct soap * _soap = NULL ) {
	this->soap = _soap;
}


SoapDBSApi::~SoapDBSApi() {

}

DBS__Block* SoapDBSApi::getBlock() {
	//cout<<"SoapDBSApi::getBlock()"<<endl;
	return soap_new_DBS__Block(soap, -1);
}
DBS__File* SoapDBSApi::getFile() {
	return soap_new_DBS__File(soap, -1);
}

DBS__EventCollection* SoapDBSApi::getEventCollection() {
	//cout<<"SoapDBSApi::getEventCollection()"<<endl;
	return soap_new_DBS__EventCollection(soap, -1);
}

int* SoapDBSApi::getInt(int value) {
	//cout<<"SoapDBSApi::getInt"<<endl;
	int * i = (int*)soap_malloc(soap, sizeof(int));
	*i = value;
	return i;
}


