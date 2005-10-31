#include "ObjectLayerException.hpp"
using namespace std;

ObjectLayerException::ObjectLayerException() {}

ObjectLayerException::ObjectLayerException(string msg) {
	this->message=msg;
}

string ObjectLayerException::report(void) {
	return this->message;
}

