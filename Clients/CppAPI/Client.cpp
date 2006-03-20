#include "soapH.h"
#include "DBS.nsmap"
#include <iostream.h>
using namespace std;

void createProcessedDataset() {
	struct soap *soap = soap_new();

	DBS__Application app;
	app.executable = "DUMMYexecutable";
	app.version = "DUMMYversion";
	app.family = "DUMMYfamily";
	app.parameterSet = "DUMMYparameterSet";

	DBS__ProcessingPath proPath;
	proPath.application = &app;
	proPath.dataTier = "Digi";
	proPath.parent = "/abc/Digi/def";

	DBS__ProcessedDataset pro;
	pro.processedDatasetName = "abcedf";
	pro.primaryDatasetName = "primary";
	pro.processingPath = &proPath;
	pro.isDatasetOpen = false;
	int result;
	if(soap_call_DBS__createProcessedDataset(soap, "http://venom.fnal.gov:27983", NULL, &pro, result) == 0) {
		cout<<"Processed Dataset ID is "<<result<<endl;
	} else {
		soap_print_fault(soap, stderr);
	}
}


int main(int argc, char** argv){ 
	createProcessedDataset();
	//struct soap *soap = soap_new();
	//int result;
	//std::vector<DBS__Block*> blockList;
	//std::vector<string> datasetList;
        //soap_call_DBS__listDataset(soap, "http://venom.fnal.gov:27983", NULL, "/eg03_jets_1e_pt2550/*/eg_2x1033PU761_TkMu_2_g133_OSC",datasetList);
        //soap_call_DBS__listDataset(soap, "http://venom.fnal.gov:27983", NULL, "/*/Digi/eg_2x1033PU761_TkMu_2_g133_OSC",datasetList);
        //soap_call_DBS__listDataset(soap, "http://venom.fnal.gov:27983", NULL, "/eg03_jets_1e_pt2550/*/*",datasetList);
	/*for(int i = 0; i != datasetList.size(); ++i ) {
		cout<<"PATH is "<<datasetList.at(i)<<endl;
	}*/

	//DBS__PrimaryDataset* parent = new DBS__PrimaryDataset();
	//parent->name = "Iam parent hahhahah";
	//DBS__PrimaryDataset* primaryDataset = new DBS__PrimaryDataset();
	//primaryDataset->name = "jkashdkhakjshsahdadjsah";
	/*DBS__testing* da = new DBS__testing();
	da->name = "kjhjksdhjkdhsls";
	std::vector<DBS__testing*> abc;
	abc.push_back(da);
	primaryDataset->parent = parent;
	std::vector<string> abc;
	abc.push_back("ddddd");
	abc.push_back("eeeee");
	primaryDataset->testlist = abc;*/
	//if(soap_call_DBS__createPrimaryDataset(soap, "http://cmssrv22.fnal.gov:27983", NULL, primaryDataset, result) == 0) {
	//if(soap_call_DBS__getDatasetContents(soap, "http://cmssrv22.fnal.gov:27983", NULL, "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC", false, blockList) == 0) {
	/*soap_call_DBS__getDatasetContents(soap, "http://venom.fnal.gov:27983", NULL, "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC", false, blockList);
	//soap_call_DBS__getDatasetContents(soap, "http://venom.fnal.gov:27983", NULL, "/eg03_jets_1e_pt50170/Digi/eg_2x1033PU761_TkMu_2_g133_OSC", false, blockList);
	//soap_call_DBS__getDatasetContents(soap, "http://cmssrv22.fnal.gov:27983", NULL, "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC", false, blockList);
		cout<<"no of blocks "<<blockList.size()<<endl;
		for(int i = 0; i != blockList.size(); ++i ) {
			DBS__Block* block = blockList.at(i);
		//cout<<"status_name "<<block->status_name<<endl;
			
			cout<<"Block ID "<<*block->blockId<<endl;
			//cout<<"id "<<*block->numberOfFiles<<endl;
			cout<<"Blockstatus name "<<block->blockStatusName<<endl;
			std::vector<DBS__EventCollection*> eventCollectionList = block->eventCollectionList;
			cout<<"no of event collections are "<<eventCollectionList.size();
			for(int k = 0 ; k!= eventCollectionList.size(); ++k) {
				DBS__EventCollection* evColl = eventCollectionList.at(k);
				cout<<"Collection ID is "<<*evColl->collectionId<<endl;
			}
		}
*/
		//cout<<" files "<<block->files<<endl;
		//cout<<" From Server "<<result <<endl;
	/*} else {
		soap_print_fault(soap, stderr);
	}*/
	//delete primaryDataset;
	//delete parent;
}
