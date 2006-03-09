#include "soapH.h"
#include "DBS.nsmap"
#include <iostream.h>
using namespace std;
int main(int argc, char** argv){ 
	struct soap *soap = soap_new();
	int result;
	std::vector<DBS__Block*> blockList;
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
	soap_call_DBS__getDatasetContents(soap, "http://cmssrv22.fnal.gov:27983", NULL, "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC", false, blockList);
		cout<<"no of blcocks "<<blockList.size()<<endl;
		for(int i = 0; i != blockList.size(); ++i ) {
			DBS__Block* block = blockList.at(i);
		//cout<<"status_name "<<block->status_name<<endl;
		
			cout<<"Block ID "<<*block->blockId<<endl;
			//cout<<"id "<<*block->numberOfFiles<<endl;
			cout<<"Blockstatus name "<<block->blockStatusName<<endl;
		}
		//cout<<" files "<<block->files<<endl;
		//cout<<" From Server "<<result <<endl;
		
	/*} else {
		soap_print_fault(soap, stderr);
	}*/
	//delete primaryDataset;
	//delete parent;
}
