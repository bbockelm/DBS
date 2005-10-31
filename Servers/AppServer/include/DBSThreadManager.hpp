#ifndef _DBSThreadManager_hpp_
#define  _DBSThreadManager_hpp_

#include <iostream>
#include <pthread.h>
#include "GSS.hpp"
#include "Socket.hpp"


struct ClientInfo {

    GSS* gsecure;
    Socket* clientsock;

};

class DBSThreadManager
{
   public:
      DBSThreadManager();
      int startNewThread(GSS* , Socket* );
   protected:
      static void* run(void *);
   private:
      pthread_t ThreadId_;
};

#endif

