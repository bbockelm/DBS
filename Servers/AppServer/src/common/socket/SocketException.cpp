#include "SocketException.hpp"
using namespace std;

SocketException::SocketException() {}

SocketException::SocketException(string msg) {
              this->message=msg;
}

string SocketException::report(void) {
              return this->message;
}

