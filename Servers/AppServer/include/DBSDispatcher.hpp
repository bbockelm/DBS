#ifndef _DBSDispatcher_hpp_
#define _DBSDispatcher_hpp_


//#include "Communicate.hpp"
#include "Message.hpp"
#include "Managers.hpp"
#include <string>

class DBSDispatcher {

public:
     DBSDispatcher(){};
     ////////////////
     //\\\\\\\\\\\\ Make this return by reference.

     int run(Message*, Message&);
};

#endif

