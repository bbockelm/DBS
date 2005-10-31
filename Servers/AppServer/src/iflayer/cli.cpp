#include "Socket.hpp"
#include "GSS.hpp"
#include "SocketException.hpp"
#include "GSSException.hpp"
#include "Message.hpp"
#include "Communicate.hpp"
#include <exception>

# include "Configuration.hpp"



#include <iostream>
using namespace std;


GSS* g;
void sendM(string message) {
	//string a;
	for(int i = 0; i < 500 ; i++ ) {
		//a +="ccccccccccccccccccccccccccccccccccccccccccccc";
		g->send("cccccccccccccccccccccccccccccccccccccjjjk");
	}
	//g->send(a);
	g->send("DONE");
	/*int len = message.length();
	cout<<"len is "<<len<<endl;
	int index = 0;
	while( index < len ) {
		if((index + MAX_MESSAGE_LEN) < len) {
			cout<<"Sending "<<message.substr(index, MAX_MESSAGE_LEN)<<endl;
			g->send(message.substr(index, MAX_MESSAGE_LEN));
		} else {
			cout<<"Sending "<<message.substr(index, len - index)<<endl;
			g->send(message.substr(index, len - index ));
		}
		index += MAX_MESSAGE_LEN;
	}
	cout<<"DONE"<<endl;
	g->send("DONE");*/

}

//void recvM(string& message1) {

void recvM(string& message) {
	message = "";
	string z ="";
	//char data[100];
	while((z = g->recv()) != "DONE") {
	//while(strcmp(data,"DONE") != 0) {
		cout<<"inside loop"<<endl;
		//g->recv(data);
		//cout<<"data is "<<data<<endl;
		//z = (string)data;
		cout<<z<<endl;
		message += z;
	}
	cout<<"outside loop"<<endl;
	/*string message="";
	string temp;
	while ( (temp = g->recv() ) != "DONE" ) {
		cout<<"Recvd "<<temp<<endl;
		cout<<"len is "<<temp.length()<<endl;
		//message = message + temp;
		//message = message + temp.substr(0,temp.length());
		cout<<"message is done"<<endl;
		temp = "";
		cout<<"done temp = "<<endl;
	}
	//cout<<"Recvd is "<<message<<endl;*/
}



int mainOLD() {
	g = new GSS();
	//while(true) {
	try{
		cout<<"tying Socket "<<endl;
		//Socket s("cmssrv22.fnal.gov",9999);
		Socket s("venom.fnal.gov",9999);
		cout<<"tying g->cOpen(s.connect()) "<<endl;
		g->cOpen(s.connect());
		/*cout<<"cOpen done now I am sending..."<<endl;
		string z = "";
		sendM("kdshdsjjdsfh;sdhflkjsdflkasdhfsdhfsgdajkfhsfgsfgahsjkgfhsgfsfgsdajkfsadfgdshlfghjsagfsakfjsgfkjsfdsfjkasfgjksfgjkssjafsgkjfgkjsagfjsd");
		cout<<"sending done...Trying recv"<<endl;
		recvM(z);
		cout<<"message recvd is "<<z<<endl;
		cout<<"recv done."<<endl;*/
		/*for(int i = 0; i < 500 ; i++ ) {
			g->send("cccccccccccccccccccccccccccccccccccccjjjk");
		}
		g->send("DONE");
		string z ="";
		while((z = g->recv()) != "DONE") {
			cout<<z<<endl;
		}*/
		/*g->send("hhhhhhhhhhhhhhhhhhhhhhhddddddddddddddddddddddddddkkkkkkkkkllllw");
		g->send("hhhhhhhhhhhhhhhhhhhhhhhddddddddddddddddddddd");
		g->send("DONE");
		while(z != "DONE") {
			z = g->recv();
			cout<<z<<endl;
		}*/

		//recvM(z);
		//string fromServer;
		//c.recv(fromServer);
		//recvM(fromServer);
		//cout<<"from server "<<fromServer<<endl;
		//delete g;

		//s.connect();
		//g.cOpen(s.connect());
		//cout<<"abc"<<endl;
		Communicate c(g);
		
		Message mSend;
		mSend.setName("ecreadmanager");
		Element* e1 = new Element("collType","CMKIN","STRING");
		Element* e2 = new Element("dsName","mu03c_hzz_4mu_550","STRING");
		mSend.addElement(e1);
		mSend.addElement(e2);
		c.send(mSend);
		//g.send("Howdy..");
		//c.send("kdshdsjjdsfh;sdhflkjsdflkasdhfsdhfsgdajkfhsfgsfgahsjkgfhsgfsfgsdajkfsadfgdshlfghjsagfsakfjsgfkjsfdsfjkasfgjksfgjkssjafsgkjfgkjsagfjsd");
		

		Message mRecv;
		c.recv(mRecv);
		cout<<"noOfElements "<<mRecv.getNoOfElements()<<endl;
		cout<<"getName "<<mRecv.getName()<<endl;
		for(int i = 0 ; i < mRecv.getNoOfElements() ; ++i) {
			Element* e = mRecv.getElement(i);
			cout<<"Key is "<<e->getKey()<<endl;
			cout<<"Value is "<<e->getValue()<<endl;
			cout<<"Type is "<<e->getType()<<endl;
		}

		/*g.send("Howdy from Client");
		string a;
		while( (a = g.recv()) != "DONE" ) {
			cout<<"From server "<<a<<endl;
		}*/
		cout<<"trying g->close()"<<endl;
		g->close();
		cout<<"trying close(s.getDes())"<<endl;
		close(s.getDes());
		cout<<"Done close(s.getDes())"<<endl;

	} catch (GSSException &e) {
		cout<<e.report()<<endl;
	} catch (SocketException &e) {
		cout<<e.report()<<endl;
	}
	//}

}




int main() {

	try{

		Configuration* conf = Configuration::instance();
	  //string serverType = conf->getServerType();
	  //cout << "Server Type: " << serverType << endl;
	} catch (...) {
		cout<<"wwww"<< endl;
//e.what();
	}


	
}

