#include "BizLayerException.hpp"
using namespace std;

BizLayerException::BizLayerException() {}

BizLayerException::BizLayerException(string msg) {
	this->message=msg;
}

string BizLayerException::report(void) {
	return this->message;
}

