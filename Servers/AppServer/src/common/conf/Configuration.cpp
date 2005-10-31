#include "Configuration.hpp"
#include <iostream>
#include "Talos.hxx"
#include <cstdlib>

using namespace Talos;
using namespace std;

Configuration* Configuration::pinstance = 0;// initialize pointer
Configuration* Configuration::instance() {
	if (pinstance == 0) {  
		pinstance = new Configuration; // create sole instance
	}
	return pinstance; // address of sole instance
}
Configuration::Configuration() { 
        char * configFile = getenv("DBSCONFIG");
        if (configFile) {
		//ConfigStream file("../../etc/server.conf");
                ConfigStream file(configFile); 
		cout << "Reading " << file.GetFileName() << "..." << endl;

		file.Find("[Server]");
		file.GetValue("port", this->port);
		file.GetValue("backlog", this->backlog);
		file.GetValue("servertype", this->servertype);
	        cout << "backlog "<<this->backlog<<endl;

		file.FindFromBeginning("[Files]");
		file.GetValue("log", this->logFile);
	
		file.FindFromBeginning("[Database]");
		file.GetValue("dsn", this->dsn);
		file.GetValue("dbuser", this->dbuser);
		file.GetValue("dbpasswd", this->dbpasswd);
         
		cout << "port "<<this->port<<endl;
		cout << "backlog "<<this->backlog<<endl;
		cout << "log "<<this->logFile<<endl;
		cout << "dsn "<<this->dsn<<endl;
		cout << "dbuser "<<this->dbuser<<endl;
		cout << "dbpasswd "<<this->dbpasswd<<endl;
        	cout << "servertype" << this->servertype<<endl;
        }

        else {
           cout << "ERROR Configuration file pointed by $DBSCONFIG is invalid";
        }

}

int Configuration::getPort() {
	return this->port;
}
int Configuration::getBacklog() {
	return this->backlog;
}
string Configuration::getLog() {
	return this->logFile;
}
string Configuration::getDsn() {
	return this->dsn;
}
string Configuration::getDbUser() {
	return this->dbuser;
}
string Configuration::getDbPasswd() {
	return this->dbpasswd;
}
string Configuration::getServerType() {
       return this->servertype;
}

