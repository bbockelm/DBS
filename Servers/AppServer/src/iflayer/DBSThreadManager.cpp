#include "DBSThreadManager.hpp"
#include "DBSDispatcher.hpp"
#include "SocketException.hpp"
#include "Communicate.hpp"
#include "Message.hpp"
#include "GSSException.hpp"

DBSThreadManager::DBSThreadManager() {

 cout << "DBSThread Constructor" << endl;

}

int DBSThreadManager::startNewThread(GSS* gsecure, Socket* clientsock) {
 
    ClientInfo clientinfo;
    clientinfo.gsecure = gsecure;
    clientinfo.clientsock = clientsock;
    (void) pthread_create(&ThreadId_, NULL, DBSThreadManager::run, &clientinfo); 
}

void* DBSThreadManager::run(void* clientinfo) {

   cout << "I am in Thread run()" << endl;
   
   GSS* cgsecure = ((ClientInfo*)clientinfo)->gsecure;
   Socket* csock = ((ClientInfo*)clientinfo)->clientsock;  

   cout << " Craeted GSS*, Socket* " << endl;
 
   cgsecure->sOpen(csock->getDes());   

   cout << "Creating comm object" <<endl;   
   Communicate comm(cgsecure);

   cout << "Done creating comm object" <<endl; 


   Message msgReceived;
   comm.recv(msgReceived);
   
   cout << "Received the Message" << endl; 
   cout << "Looking for Message Name" <<endl;
   cout << msgReceived.getName();

   DBSDispatcher dispatcher;
   Message msgReturned; 
   dispatcher.run(&msgReceived, msgReturned);
   comm.send(msgReturned); 
   cgsecure->close();
   close(csock->getDes());
}


/*
int main (void) {

DBSThreadManager *newThread;
int thread_count=0;
int keep = true;
char threadornot;
while(keep)
  {
      cin >> threadornot;
      if (threadornot=='t') {
        newThread = new DBSThreadManager();
        ++thread_count; 
        newThread->Start((void*)&thread_count); 
      }
      if (threadornot=='q')
        break;

      cout << "You entered " << threadornot << endl; 

  }
 return 0;


}

*/


