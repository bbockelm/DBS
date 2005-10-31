#include "GSSException.hpp"
using namespace std;
GSSException::GSSException() {}

GSSException::GSSException(string msg) {
              this->message=msg;
}

string GSSException::report(void) {
              return this->message;
}

