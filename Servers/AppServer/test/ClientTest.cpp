#include <iostream>
#include "DBSClient.hpp"
#include <exception>
#include "ClientAPIData.hpp"
using namespace std;

int main() {
        DBSClient* dbsclient = new DBSClient();
        vector<Evcollview_ClientAPIData*>
Evcollview_ClientAPIData::Evcollview_ClientAPIData(){

    Schema.insert(Entry("status", "INTEGER"));
    Schema.insert(Entry("validation_status", "INTEGER"));
    Schema.insert(Entry("modified_by", "INTEGER"));
    Schema.insert(Entry("name", "STRING"));
    Schema.insert(Entry("processed_dataset", "INTEGER"));
    Schema.insert(Entry("created_at", "FLOAT"));
    Schema.insert(Entry("modified_at", "FLOAT"));
    Schema.insert(Entry("created_by", "INTEGER"));
    Schema.insert(Entry("events", "INTEGER"));
    Schema.insert(Entry("collection_index", "INTEGER"));
    Schema.insert(Entry("event_collection", "INTEGER"));
    Schema.insert(Entry("id", "INTEGER"));
    Schema.insert(Entry("estimated_luminosity", "STRING"));

}

int main() {
	DBSClient* dbsclient = new DBSClient();
	vector<Primarydataset_ClientAPIData*> primaryDatasetInfo;
	typedef vector<Primarydataset_ClientAPIData*>::iterator PrimaryDatasetInfoIter;
	try {
		dbsclient->readPrimaryDataset(primaryDatasetInfo);
	} catch (exception &ex) {
		cout << ex.what() << endl;  
	}
	delete dbsclient; 
	cout<<"Total number of structs returned "<<primaryDatasetInfo.size()<<endl;
	for(PrimaryDatasetInfoIter i = primaryDatasetInfo.begin(); i != primaryDatasetInfo.end(); ++i) {
		cout<<"\n********************************************************************************"<<endl;
		cout<<"mcchanneldescription "<<(*i)->mcchanneldescription.getValue()<<endl;
		cout<<"abstractdatasetannotation "<<(*i)->abstractdatasetannotation.getValue()<<endl;
		cout<<"triggerdescriptionid "<<(*i)->triggerdescriptionid.getValue()<<endl;
		cout<<"mcproduction "<<(*i)->mcproduction.getValue()<<endl;
		cout<<"abstractdatasetdescriptionid "<<(*i)->abstractdatasetdescriptionid.getValue()<<endl;
		cout<<"streamid "<<(*i)->streamid.getValue()<<endl;
		cout<<"triggerpathdescriptionid "<<(*i)->triggerpathdescriptionid.getValue()<<endl;
		//cout<<"startdate "<<(*i)->startdate.getValue()<<endl;
		cout<<"mcdescriptionid "<<(*i)->mcdescriptionid.getValue()<<endl;
		cout<<"cobradatasetname "<<(*i)->cobradatasetname.getValue()<<endl;
		cout<<"mcchanneldescriptionid "<<(*i)->mcchanneldescriptionid.getValue()<<endl;
		cout<<"mcdecaychain "<<(*i)->mcdecaychain.getValue()<<endl;
		cout<<"streamannotation "<<(*i)->streamannotation.getValue()<<endl;
		cout<<"primarydatasetid "<<(*i)->primarydatasetid.getValue()<<endl;
		//cout<<"physicsgroupconvener "<<(*i)->physicsgroupconvener.getValue()<<endl;
		cout<<"physicsgroupid "<<(*i)->physicsgroupid.getValue()<<endl;
		cout<<"openforwriting "<<(*i)->openforwriting.getValue()<<endl;
		cout<<"triggerpathdescription "<<(*i)->triggerpathdescription.getValue()<<endl;
		//cout<<"enddate "<<(*i)->enddate.getValue()<<endl;
		cout<<"streamname "<<(*i)->streamname.getValue()<<endl;
		cout<<"mcdataset "<<(*i)->mcdataset.getValue()<<endl;
		cout<<"physicsgroupname "<<(*i)->physicsgroupname.getValue()<<endl;
		cout<<"abstractdatasetname "<<(*i)->abstractdatasetname.getValue()<<endl;
		cout<<"********************************************************************************\n"<<endl;
		delete *i;
	}
	
	
}


int maina() {

   cout << "Client test" << endl;
   DBSClient* dbsclient = new DBSClient();
   Primarydataset_ClientAPIData primaryDatasetInfo;

     
   primaryDatasetInfo.mcchanneldescription = (string)"Some Description pata nahee";
   primaryDatasetInfo.mcdecaychain = (string)"Some Decay pata nahee";
   primaryDatasetInfo.mcproduction = (string)"Who knows pata nahee";
   primaryDatasetInfo.triggerpathdescription = (string)"kia aur kioun pata nahee";
   primaryDatasetInfo.streamannotation = (string)"No comments please pata nahee";
   primaryDatasetInfo.cobradatasetname = (string)"AnacondaPataNahee";
   primaryDatasetInfo.abstractdatasetannotation = (string)"Annotation what ? Pata Nahee";
   primaryDatasetInfo.physicsgroupname = (string)"GroupOfItsOwnPataNahee";
   primaryDatasetInfo.streamname = (string)"StreamingLivePataNahee";
   primaryDatasetInfo.abstractdatasetname = (string)"MyFirstPrimaryDatasetPataNahee";
   primaryDatasetInfo.mcdataset = 'y';

	try {
		dbsclient->createPrimaryDataset((string)"MyFirstPrimaryDatasetPataNahee", primaryDatasetInfo);
	} catch (exception &ex) {
		cout << ex.what() << endl;  
	}
	delete dbsclient; 


}
