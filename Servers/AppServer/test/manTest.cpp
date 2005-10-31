#include "DBSDispatcher.hpp"
#include "Message.hpp"
#include "RowNSchemaBinding.hpp"
#include "BaseSchemaNConstratints.hpp"
#include "ObjectLayerTables.hpp"
#include "Configuration.hpp"
#include <iostream>

int test_file_mview(DBManagement* dbManager) {

  FileviewMultiTable* fTable = new FileviewMultiTable(dbManager);
  typedef vector<Fileviewmultirow*> MyRows;
  typedef MyRows::iterator MyRowIter;
  //MyRows myRows = fTable->select("t_file.id=1");
  //MyRows myRows = fTable->select("t_evcoll_file.id=1");
  MyRows myRows = fTable->select("t_evcoll_file.evcoll=200");
  for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
     //cout << "ANOTHER File Read from DB......";
     Fileviewmultirow* aRow = (Fileviewmultirow*)*r; 
     cout<<"\n\nThe File Name is::: " << *(string*)aRow->getValue((string)"t_file.logical_name");
     }
 delete fTable;
}


int test_evcoll_mview(DBManagement* dbManager) {

   EvcollviewMultiTable* ecTable = new EvcollviewMultiTable(dbManager);
   typedef vector<Evcollviewmultirow*> MyRows;
   typedef MyRows::iterator MyRowIter;

   cout << "!!!! calling select on EvcollviewMultiTable !!!" << endl;
   MyRows myRows = ecTable->select("processed_dataset=1");
   //MyRows myRows = ecTable->select("(t_event_collection.processed_dataset=1) AND (t_event_collection.id=1)");
   //MyRows myRows = ecTable->select("(t_event_collection.processed_dataset=1) AND (t_event_collection.id=1)");
   cout << "!!!! select called on EvcollviewMultiTable !!!" << endl;
   for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
        //cout << "ANOTHER File Read from DB......";
        Evcollviewmultirow* aRow = (Evcollviewmultirow*)*r;
        cout<<"\n "     << *(string*)aRow->getValue((string)"t_info_evcoll.name") 
                        << " "
                        << *(int*)aRow->getValue((string)"t_info_evcoll.events") << endl;
     }
   delete ecTable;
}


int test_crabevcoll_manager(DBManagement* dbManager) {
        Message mSend;  
        Message mRecv;
  
        mSend.setName("ReadCRABEventCollection");
        Element* e1 = new Element("t_primary_dataset.name","bt03_gg_bbh200_2taujmu","STRING");
        //Element* e1 = new Element("t_event_collection.processed_dataset","1","INTEGER");
        mSend.addElement(e1);

        cout<<"Calling the DBSDispatcher"<<endl;
        cout<<"Invoking Server Operation: " << mSend.getName() << endl;
        DBSDispatcher dispatcher;
        dispatcher.run(&mSend, mRecv);
        cout<<"Returned From the DBSDispatcher"<<endl;


        /*cout << "Dumping Contents of Returned Information" << endl;
        cout<<"Exception is "<<mRecv.getException()<<endl;
         Display only certain
        for(int i = 0 ; i < mRecv.getNoOfElements(); ++i) {
                Element* e = mRecv.getElement(i);
                cout<<"Key is "<<e->getKey()<<endl;
                cout<<"Value is "<<e->getValue()<<endl;
                cout<<"Type is "<<e->getType()<<endl;
       
        }*/
        string message;
        mRecv.serialize(message);
        cout<<"Serialized MESSAGE is \n"<<message<<endl;
}


int test_evcoll_manager(DBManagement* dbManager) {
        Message mSend;  
        Message mRecv;

        mSend.setName("ReadEventCollection");
        Element* e1 = new Element("processed_dataset","1","STRING");
        //Element* e1 = new Element("t_event_collection.processed_dataset","1","INTEGER");
        mSend.addElement(e1);

        cout<<"Calling the DBSDispatcher"<<endl;
        cout<<"Invoking Server Operation: " << mSend.getName() << endl;
        DBSDispatcher dispatcher;
        dispatcher.run(&mSend, mRecv);
        cout<<"Returned From the DBSDispatcher"<<endl;


        cout << "Dumping Contents of Returned Information" << endl;
        cout<<"Exception is "<<mRecv.getException()<<endl;
        /* Display only certain
        for(int i = 0 ; i < mRecv.getNoOfElements(); ++i) {
                Element* e = mRecv.getElement(i);
                cout<<"Key is "<<e->getKey()<<endl;
                cout<<"Value is "<<e->getValue()<<endl;
                cout<<"Type is "<<e->getType()<<endl;
       
        }*/
        string message;
        mRecv.serialize(message);
        cout<<"Serialized MESSAGE is \n"<<message<<endl;
}

/*
int test_primaryds_managher(DBManagement* dbManager) {
        Message mSend;
        Message mRecv;

        mSend.setName("ReadPrimaryDataset");
        

*/

/*

int test_dsProv_mview(DBManagement* dbManager) {

  DatasetprovenenceMultiTable* fTable = new DatasetprovenenceMultiTable(dbManager);
  typedef vector<Datasetprovenencemultirow*> MyRows;
  typedef MyRows::iterator MyRowIter;
  //MyRows myRows = fTable->select("t_event_collection.processed_dataset=3 AND t_evcoll_parentage.parent != t_event_collection.id ");
  //MyRows myRows = fTable->select("t_event_collection.processed_dataset=4 AND t_evcoll_parentage.parent != t_event_collection.id ");
  MyRows myRows = fTable->select("t_event_collection.processed_dataset=4");
  cout <<"After the Dark !!!!!!!!!" << endl; 
  for(MyRowIter r = myRows.begin(); r != myRows.end(); ++r) {
     //cout << "ANOTHER File Read from DB......";
     Datasetprovenencemultirow* aRow = (Datasetprovenencemultirow*)*r;
     cout<<"\n\nParents Name::: " << *(string*)aRow->getValue((string)"t_parentage_type.name");
     //cout<<"\nt_processing_path.data_tier: " << *(string*)aRow->getValue((string)"t_processing_path.data_tier");
     cout<<"\nt_processed_dataset.name: " << *(string*)aRow->getValue((string)"t_processed_dataset.name");
     //t_primary_dataset.id
     //t_processing_path.data_tier
     //t_processed_dataset.name
     }
 delete fTable;
}
*/



int main(int argc, char* argv[]) {
	//while(true) {
		cout<<"Configuration* conf = Configuration::instance()"<<endl;
		Configuration* conf = Configuration::instance();
		cout<<"this->dbManager = new DBManagement(conf->getDsn(), conf->getDbUser() , conf->getDbPasswd());"<<endl;
		cout<<"conf->getDsn() "<<conf->getDsn()
                    <<" conf->getDbUser() "<<conf->getDbUser()
                    <<" conf->getDbPasswd() "<<conf->getDbPasswd()<<endl;
		DBManagement* dbManager = new DBManagement(conf->getDsn(), conf->getDbUser() , conf->getDbPasswd());
		dbManager->open();

                //test_file_mview(dbManager);
                //test_evcoll_mview(dbManager);
                //test_evcoll_manager(dbManager);
                //test_dsProv_mview(dbManager);
                test_crabevcoll_manager(dbManager); 
                
                cout << "All tests done by the manager" << endl;

		dbManager->close();
		delete dbManager;
	/*Message mSend;
	Message mRecv;
	ECWriteManager manager;
	manager.write(&mSend,mRecv);*/
	//}
	/*cout<<"no of elements "<<mRecv.getNoOfElements()<<endl;
	cout<<"Exception is "<<mRecv.getException()<<endl;
	for(int i = 0 ; i < mRecv.getNoOfElements(); ++i) {
		Element* e = mRecv.getElement(i);
		cout<<"Key is "<<e->getKey()<<endl;
		cout<<"Value is "<<e->getValue()<<endl;
		cout<<"Type is "<<e->getType()<<endl;
	}
	string message;
	mRecv.serialize(message);
	cout<<"Serialized MESSAGE is \n"<<message<<endl;*/

}


int mainb(int argc, char* argv[]) {
	Message mSend;
	Message mRecv;
	PrimaryDatasetManager* manager = new PrimaryDatasetManager();
	manager->read(&mSend,mRecv);
	delete manager;

	cout<<"no of elements "<<mRecv.getNoOfElements()<<endl;
	cout<<"Exception is "<<mRecv.getException()<<endl;
	for(int i = 0 ; i < mRecv.getNoOfElements(); ++i) {
		Element* e = mRecv.getElement(i);
		cout<<"Key is "<<e->getKey()<<endl;
		cout<<"Value is "<<e->getValue()<<endl;
		cout<<"Type is "<<e->getType()<<endl;
	}
	string message;
	mRecv.serialize(message);
	cout<<"Serialized MESSAGE is \n"<<message<<endl;

}

int maina(int argc, char* argv[]) {
	/*Primarydatasetmultirow * a = new Primarydatasetmultirow();
	a->getValue((string)"triggerpathdescription.triggerpathdescriptionid");
*/
	Message mSend;
	Message mRecv;
	/*mSend.setName("PrimaryDatasetManager");
	Element* e1 = new Element("MCChannel","MCChannel","STRING");
	Element* e2 = new Element("MCChannelDesc","MCChannelDesc","STRING");
	Element* e3 = new Element("MCProductionDesc","MCProductionDesc","STRING");
	Element* e4 = new Element("MCDecayChain","MCDecayChain","STRING");
	Element* e5 = new Element("TriggerPath","TriggerPath","STRING");
	Element* e6 = new Element("TriggerPathDesc","TriggerPathDesc","STRING");
	Element* e7 = new Element("streamComments","streamCommentsaaa","STRING");
	Element* e8 = new Element("primarydatasetname","primarydatasetnamebbbb","STRING");
	Element* e9 = new Element("cobradatasetname","cobraddddddd","STRING");
	Element* e10 = new Element("primarydatasetannotation","primary tset","STRING");
	Element* e11 = new Element("physicsgroupname","physicsgroupnameeeee","STRING");
	Element* e12 = new Element("streamname","streamnambbbb","STRING");
	*/

	Element* e1 = new Element("qpsannotation","qpsannotation","STRING");
	Element* e2 = new Element("qpsname","qpsname","STRING");
	Element* e3 = new Element("composite","y","CHARACTER");
	Element* e4 = new Element("qpsversion","qpsversion","STRING");

	Data* d1 = new Data();
	Element* e5 = new Element("externaldatatype","externaldatatype1","STRING");
	Element* e6 = new Element("parametername","parametername1","STRING");
	Element* e7 = new Element("parametervalue","parametervalue1","STRING");
	d1->push_back(e5);
	d1->push_back(e6);
	d1->push_back(e7);

	Data* d2 = new Data();
	e5 = new Element("externaldatatype","externaldatatype2","STRING");
	e6 = new Element("parametername","parametername2","STRING");
	e7 = new Element("parametervalue","parametervalue2","STRING");
	d2->push_back(e5);
	d2->push_back(e6);
	d2->push_back(e7);

	Data* d3 = new Data();
	e5 = new Element("externaldatatype","externaldatatype3","STRING");
	e6 = new Element("parametername","parametername3","STRING");
	e7 = new Element("parametervalue","parametervalue3","STRING");
	d3->push_back(e5);
	d3->push_back(e6);
	d3->push_back(e7);

	Data* d4 = new Data();
	e5 = new Element("externaldatatype","externaldatatype4","STRING");
	e6 = new Element("parametername","parametername4","STRING");
	e7 = new Element("parametervalue","parametervalue4","STRING");
	d4->push_back(e5);
	d4->push_back(e6);
	d4->push_back(e7);

	Data* d5 = new Data();
	e5 = new Element("externaldatatype","externaldatatype5","STRING");
	e6 = new Element("parametername","parametername5","STRING");
	e7 = new Element("parametervalue","parametervalue5","STRING");
	d5->push_back(e5);
	d5->push_back(e6);
	d5->push_back(e7);
	VecData* vd = new VecData();

	vd->push_back(d1);
	vd->push_back(d2);
	vd->push_back(d3);
	vd->push_back(d4);
	vd->push_back(d5);

	mSend.addElement(e1);
	mSend.addElement(e2);
	mSend.addElement(e3);
	mSend.addElement(e4);
	mSend.addVecOfVecOfElement(vd,"extraparams");

	/*mSend.addElement(e5);
	mSend.addElement(e6);
	mSend.addElement(e7);
	mSend.addElement(e8);
	mSend.addElement(e9);
	mSend.addElement(e10);
	mSend.addElement(e11);
	mSend.addElement(e12);
	PrimaryDatasetManager* manager = new PrimaryDatasetManager();*/
	/*GenParamManager* manager = new GenParamManager();
	manager->write(&mSend,mRecv);
        cout << "Deleting Manager" << endl; 
	delete manager;
        
	cout<<"no of elements "<<mRecv.getNoOfElements()<<endl;
	cout<<"Exception is "<<mRecv.getException()<<endl;
	for(int i = 0 ; i < mRecv.getNoOfElements(); ++i) {
		Element* e = mRecv.getElement(i);
		cout<<"Key is "<<e->getKey()<<endl;
		cout<<"Value is "<<e->getValue()<<endl;
		cout<<"Type is "<<e->getType()<<endl;
	}
        */
  return 0;
}

