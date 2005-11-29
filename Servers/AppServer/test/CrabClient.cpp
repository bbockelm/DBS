#include <iostream>
#include "DBSClient.hpp"
#include <exception>
#include "ClientAPIData.hpp"
#include "Util.hpp"

using namespace std;


class CrabClient {

public:
CrabClient() {
   cout << "DBS CRAB Client Initialization done..." << endl;
}

~CrabClient() {
}
/*
int getDatasetContents(string basePathName) {

   cout << "CrabClient: getDatasetContents " << endl;
   vector<string> tokens;
   cout << "CrabClient: getDatasetContents calling util.tokenize" << basePathName << endl;

   util.tokenize(basePathName, tokens, (string) "/");
 
   if ( tokens.size() < 3 ) {
      cout << "ERROR : Provide the full basePathName " << endl;
   } 

   cout << "CrabClient: getDatasetContents  Tokens Created " << tokens.size() << endl;
   
   string prdsName = tokens.at(0);
   string dataTier = tokens.at(1);
   string procDS = tokens.at(2);


   cout << prdsName << dataTier << procDS << endl; 
    
        int primaryDsId = getPrimaryDatasetID(prdsName);

        vector<int> procDsIds = getProcDSIDs(primaryDsId, dataTier, procDS);

        for (int i=0; i != procDsIds.size() ; ++i) {
            int procDSId = procDsIds.at(i);
            vector<int> evcollIds = getEvColls(procDSId);
            for (int i=0; i != evcollIds.size() ; ++i) {
                int evcollid = evcollIds.at(i);
                getEvCollFiles(evcollid);
            }
        }

}

int getDatasetProvenance(string basePathName, vector<string> parent_data_tier) {

   vector<string> tokens;
   util.tokenize(basePathName, tokens, (string) "/");
   if ( tokens.size() < 3 ) {
      cout << "ERROR : Provide the full basePathName " << endl;
   }

   string primaryDSName = tokens[0];
   string dataTier = tokens[1];
   string procDSName = tokens[2];

        dbsclient = new DBSClient();
        typedef vector<Datasetprovenence_ClientAPIData*> DspInfoVec;
        typedef vector<Datasetprovenence_ClientAPIData*>::iterator DspInfoVecIter;
        DspInfoVec DspInfo;

        try {
              dbsclient->getDatasetProvenence(primaryDSName, procDSName, DspInfo);
        } catch (exception &ex) {
                cout << ex.what() << endl;
        }

        cout<<"Total number of EvColl structs returned "<< DspInfo.size()<<endl;
        for(DspInfoVecIter i = DspInfo.begin(); i != DspInfo.end(); ++i) {
             cout<<"\n********************************************************************************"<<endl;
             cout<<"\n\nParents Name::: " << (*i)->t_parentage_type_name.getValue();
             string parent_type = (*i)->t_parentage_type_name.getValue();
             string parent_procds = (*i)->t_processed_dataset_name.getValue();
             string parent_primds = (*i)->t_primary_dataset_name.getValue();
             cout<<"Parent Path: /" << parent_primds << "/" << dataTier 
                                    <<  "/" << parent_procds << endl;

             //for each t_parentage_type.name (DT) and primds etc....
             //find the ownername (processed_dataset_name)

             //t_primary_dataset.id
             //t_processing_path.data_tier
             //t_processed_dataset.name
        }
        delete dbsclient;
}
*/

//private:

int getPrimaryDatasetID(string prdsName) {

        
        dbsclient = new DBSClient(); 
	Primarydataset_ClientAPIData apiData;
	apiData.t_primary_dataset_name = prdsName;
        typedef vector<Primarydataset_ClientAPIData*> PrimaryDatasetVec;
        typedef vector<Primarydataset_ClientAPIData*>::iterator PrimaryDatasetVecIter;
        PrimaryDatasetVec primaryDatasetInfo;
        
        try {   
                //dbsclient->readPrimaryDataset(prdsName, primaryDatasetInfo);
                dbsclient->readPrimaryDataset(apiData, primaryDatasetInfo);
        } catch (exception &ex) {
                cout << ex.what() << endl;  
        }
        cout<<"Total number of primaryDatasetInfo structs returned "<<primaryDatasetInfo.size()<<endl;

        Primarydataset_ClientAPIData* primaryDs = primaryDatasetInfo.at(0);
        int primaryDsId = primaryDs->t_primary_dataset_id.getValue();
        cout<<"primary_dataset id "<<primaryDs->t_primary_dataset_id.getValue()<<endl;
        cout<<"primary_dataset name "<<primaryDs->t_primary_dataset_name.getValue()<<endl;
        cout<<"primary_dataset description "<<primaryDs->t_primary_dataset_description.getValue()<<endl;
      
        /*  
        for(PrimaryDatasetVecIter i = primaryDatasetInfo.begin(); i != primaryDatasetInfo.end(); ++i) {
                cout<<"\n********************************************************************************"<<endl;
                primaryDsId = (*i)->t_primary_dataset_id.getValue();
                cout<<"primary_dataset id "<<(*i)->t_primary_dataset_id.getValue()<<endl;
                cout<<"primary_dataset name "<<(*i)->t_primary_dataset_name.getValue()<<endl;
                cout<<"primary_dataset description "<<(*i)->t_primary_dataset_description.getValue()<<endl;
        } 
        */

       for(PrimaryDatasetVecIter i = primaryDatasetInfo.begin(); i != primaryDatasetInfo.end(); ++i) {
          delete *i;
       }
       delete dbsclient;
       return primaryDsId;
}
/*
vector<int> getProcDSIDs(int priDsId, string dataTier, string procDS) {
 
        vector<int> ppidVec;
        dbsclient = new DBSClient();
        typedef vector<Processingpath_ClientAPIData*> ProcPathVec;
        typedef vector<Processingpath_ClientAPIData*>::iterator ProcPathVecIter;
        ProcPathVec procPathInfo; 
        
        try {
                cout << "Calling readProcessingPath" << endl; 
                dbsclient->readProcessingPath(priDsId, dataTier, procDS, procPathInfo);
        } catch (exception &ex) {
                cout << ex.what() << endl;
        }
        cout<<"Total number of procPathInfo structs returned "<<procPathInfo.size()<<endl;
        for(ProcPathVecIter i = procPathInfo.begin(); i != procPathInfo.end(); ++i) {
           cout<<"\n********************************************************************************"<<endl;
           ppidVec.push_back((*i)->t_processed_dataset_id.getValue());  
           cout<<"data tiername = " << (*i)->t_data_tier_name.getValue() << endl;
           cout<<"processed dataset id = " << (*i)->t_processed_dataset_id.getValue() << endl;
           //cout<<"processing_path parent  = " << (*i)->t_processing_path_parent.getValue() << endl;
           delete *i;
        } 
        delete dbsclient;
        return  ppidVec;
}


vector<int> getEvColls(int processed_dataset) {
      
        vector<int> evcollIds; 
        dbsclient = new DBSClient(); 

        typedef vector<Evcollview_ClientAPIData*> EvCollVec;
        typedef vector<Evcollview_ClientAPIData*>::iterator EvCollVecIter;
        EvCollVec EvCollections;

        try {
              dbsclient->readEvColls(processed_dataset, EvCollections);
        } catch (exception &ex) {
                cout << ex.what() << endl;
        }

        cout<<"Total number of EvColl structs returned "<< EvCollections.size()<<endl;
        for(EvCollVecIter i = EvCollections.begin(); i != EvCollections.end(); ++i) {
             cout<<"\n********************************************************************************"<<endl; 
             cout<<"status = "<< (*i)->t_info_evcoll_status.getValue()<<endl;
             cout<<"validation_status = "<< (*i)->t_info_evcoll_validation_status.getValue()<<endl;
             cout<<"processed_dataset = "<< (*i)->t_event_collection_processed_dataset.getValue()<<endl;
             cout<<"events = "<< (*i)->t_info_evcoll_events.getValue()<<endl;
             cout<<"collection_index "<< (*i)->t_event_collection_collection_index.getValue()<<endl;
             cout<<"id = "<< (*i)->t_event_collection_id.getValue()<<endl;
             cout<<"name = "<< (*i)->t_info_evcoll_name.getValue()<<endl;
             evcollIds.push_back((*i)->t_event_collection_id.getValue());
        }
        delete dbsclient; 
        return evcollIds;
}

int getEvCollFiles(int evcoll_id) {

        dbsclient = new DBSClient();
        typedef vector<Fileview_ClientAPIData*> EvFilesVec;
        typedef vector<Fileview_ClientAPIData*>::iterator EvFilesVecIter;
        EvFilesVec EvFiles;

        try {
              dbsclient->readEvCollFiles(evcoll_id, EvFiles);
        } catch (exception &ex) {
                cout << ex.what() << endl;
        }

        cout<<"Total number of EvFilesVec structs returned "<< EvFiles.size()<<endl;
        
        for( EvFilesVecIter i = EvFiles.begin(); i != EvFiles.end(); ++i) {
             cout<<"\n********************************************************************************"<<endl;

             cout<<"file logical_name = "<< (*i)->t_file_logical_name.getValue()<<endl;
             cout<<"file guid = "<< (*i)->t_file_guid.getValue()<<endl;
             cout<<"file inblock = "<< (*i)->t_file_inblock.getValue()<<endl;
        }
        delete dbsclient;
        return 1;
}
*/

int getEVColl(string dataTier, string primaryDSName, string processedDSName) {

        
        dbsclient = new DBSClient(); 
	Crabevcollview_ClientAPIData apiData;
	apiData.t_data_tier_name = dataTier;
	apiData.t_primary_dataset_name = primaryDSName;
	apiData.t_processed_dataset_name = processedDSName;

        typedef vector<Crabevcollview_ClientAPIData> CrabEvcollVector;
        typedef CrabEvcollVector::iterator CrabEvcollVecIter;
        CrabEvcollVector crabInfo;
        
        try {   
                //dbsclient->readPrimaryDataset(prdsName, primaryDatasetInfo);
                dbsclient->readCRABEvColls(apiData, crabInfo);
        } catch (exception &ex) {
                cout << ex.what() << endl;  
        }
        cout<<"Total number of primaryDatasetInfo structs returned "<<crabInfo.size()<<endl;

       for(CrabEvcollVecIter i = crabInfo.begin(); i != crabInfo.end(); ++i) {
		cout<<"ProcessedDataset name "<<(*i).t_processed_dataset_name.getValue()<<endl;
		cout<<"t_data_tier_name "<<(*i).t_data_tier_name.getValue()<<endl;
		cout<<"t_info_evcoll_events "<<(*i).t_info_evcoll_events.getValue()<<endl;
		cout<<"t_event_collection_collection_index "<<(*i).t_event_collection_collection_index.getValue()<<endl;
          //delete *i;
       }
       delete dbsclient;
       return 1;
}

  DBSClient* dbsclient;
  Util util;

};

int main() {

        cout << "Crab Client Starts Now" << endl;

        CrabClient crabbing;

        string basePathName = "/bt03_gg_bbh200_2taujmu/DST/bt_DST8713_2x1033PU_g133_CMS";

        cout << "basePathName: " << basePathName << endl;

        //crabbing.getDatasetContents(basePathName); 
					
        //crabbing.getPrimaryDatasetID("bt03_gg_bbh200_2taujmu"); 
	//crabbing.getPrimaryDatasetID("mu05_SingleMuMinus_pT100"); 
	while(true)
	crabbing.getEVColl("DST","bt03_gg_bbh200_2taujmu", "bt_DST8713_2x1033PU_g133_CMS"); 

        /*vector<string> parent_data_tier;
        
        parent_data_tier.push_back("Hit");
        parent_data_tier.push_back("Digi");
        parent_data_tier.push_back("DST");*/

        //crabbing.getDatasetProvenance(basePathName, parent_data_tier);
}
