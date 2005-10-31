#ifndef _ClientDataStructure_hpp_
#define _ClientDataStructure_hpp_

#include "Message.hpp"
#include "common.hpp"
#include "Util.hpp"

class ClientDataStructure {
public:

     ClientDataStructure();
     ~ClientDataStructure();
     virtual int makeMessage(Message& messageOut){};
     virtual int readInMessage(Message& messageIn, std::string lisName, int index){};
     //virtual int readInMessage(Message* messageIn){};
     Dictionary* getSchema(void);


protected:
 //Message messageOut;
 Dictionary Schema;
 Util util;
 //bool messageNotCreated;
};


#endif 
