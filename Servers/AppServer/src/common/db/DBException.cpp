#include "DBException.hpp"

using namespace std;

DBException::DBException() {}

DBException::DBException(string msg) {
              this->message=msg;
}

string DBException::report(void) {
              return this->message;
}

