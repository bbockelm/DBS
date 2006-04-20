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

void getDatasetContents() {
	struct soap *soap = soap_new();
	string path="/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC";
	std::vector<DBS__Block*> blockList;
	if(soap_call_DBS__getDatasetContents(soap, "http://cmslcgco01.cern.ch:27983", NULL, path, true, blockList) == 0) {
		cout<<"size of blockList is  "<<blockList.size()<<endl;
		for(int i = 0; i != blockList.size(); ++i) {
			vector<DBS__EventCollection*> eventCollectionList = blockList.at(i)->eventCollectionList;
			for(int j = 0; j != eventCollectionList.size(); ++j) {
				DBS__EventCollection* aEventCollection = eventCollectionList.at(j);
				cout<<"collectionName "<<aEventCollection->collectionName<<endl;
				cout<<"************************************************"<<endl;
				vector<DBS__File*> fileList = aEventCollection->fileList;
				for(int k = 0; k != fileList.size(); ++k) {
					DBS__File* aFile = fileList.at(k);
					cout<<"  id "<<aFile->id<<endl;
					cout<<"  guid "<<aFile->guid<<endl;
					cout<<"  logicalFileName "<<aFile->logicalFileName<<endl;
					cout<<"  checksum "<<aFile->checksum<<endl;
					cout<<"  fileSize "<<aFile->fileSize<<endl;
					cout<<"  fileStatus "<<aFile->fileStatus<<endl;
					cout<<"  fileType "<<aFile->fileType<<endl;
					cout<<"  fileBlockId "<<aFile->fileBlockId<<endl;
					cout<<"************************************************"<<endl;
				}
			}
		}
	} else {
		soap_print_fault(soap, stderr);
	}
}


void insertEventCollection() {
	int tmp1 = 1;
	int tmp2 = 1;
	int tmp3 = 1;
	int tmp4 = 1;

	struct soap *soap = soap_new();
	vector<DBS__EventCollection*> eventCollectionList;

	for (int i =0; i != 1000 ; ++i) {
	DBS__File* aFile = new DBS__File();
	char s[100];
	sprintf(s,"%d",i);
	strcat(s,"absdjkdhfcdef");
	aFile->guid = s;
	aFile->logicalFileName = s;
	aFile->checksum = s;
	aFile->fileStatus = "FILaE";
	aFile->fileType = "EVDa";
	aFile->fileSize = &tmp1;
	aFile->fileBlockId = &tmp2;
	vector<DBS__File*> fileList;
	fileList.push_back(aFile);
	int x = 11234 + i;
	DBS__EventCollection* ec1 = new DBS__EventCollection();
	ec1->status = "NEW";
	ec1->collectionName = s;
	ec1->datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec1->parentageType = "HIT";
	ec1->collectionIndex = &x;
	ec1->numberOfEvents = &tmp4;
	ec1->fileList = fileList;

	eventCollectionList.push_back(ec1);
	}
	/*DBS__File aFile;
	aFile.guid = "abcdef";
	aFile.logicalFileName = "Logical";
	aFile.checksum = "Dummychecksum";
	aFile.fileStatus = "FILE";
	aFile.fileType = "EVD";
	aFile.fileSize = &tmp1;
	aFile.fileBlockId = &tmp2;
	vector<DBS__File*> fileList;
	fileList.push_back(&aFile);

	DBS__EventCollection ec1;
	ec1.status = "NEW";
	ec1.collectionName = "COLL1";
	ec1.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec1.parentageType = "HIT";
	ec1.collectionIndex = &tmp3;
	ec1.numberOfEvents = &tmp4;
	ec1.fileList = fileList;

	DBS__EventCollection ec2;
	ec2.status = "NEW";
	ec2.collectionName = "COLL2";
	ec2.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec2.parentageType = "HIT2";
	ec2.collectionIndex = &tmp3;
	ec2.numberOfEvents = &tmp4;
	ec2.fileList = fileList;
	ec2.parent = &ec1;

	DBS__EventCollection ec3;
	ec3.status = "NEW";
	ec3.collectionName = "COLL3";
	ec3.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec3.parentageType = "HIT3";
	ec3.collectionIndex = &tmp3;
	ec3.numberOfEvents = &tmp4;
	ec3.fileList = fileList;
	ec3.parent = &ec2;

	DBS__EventCollection ec4;
	ec4.status = "NEW";
	ec4.collectionName = "COLL4";
	ec4.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec4.parentageType = "HIT4";
	ec4.collectionIndex = &tmp3;
	ec4.numberOfEvents = &tmp4;
	ec4.fileList = fileList;
	
	DBS__EventCollection ec5;
	ec5.status = "NEW";
	ec5.collectionName = "COLL5";
	ec5.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec5.parentageType = "HIT5";
	ec5.collectionIndex = &tmp3;
	ec5.numberOfEvents = &tmp4;
	ec5.fileList = fileList;
	ec5.parent = &ec4;

	DBS__EventCollection ec6;
	ec6.status = "NEW";
	ec6.collectionName = "COLL6";
	ec6.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec6.parentageType = "HIT6";
	ec6.collectionIndex = &tmp3;
	ec6.numberOfEvents = &tmp4;
	ec6.fileList = fileList;
	ec6.parent = &ec5;
*/
	//eventCollectionList.push_back(&ec6);

	int result;
	if(soap_call_DBS__insertEventCollections(soap, "http://cmslcgco01.cern.ch:27987", NULL, eventCollectionList, result) == 0) {
		cout<<"Event Collection  ID is "<<result<<endl;
	} else {
		soap_print_fault(soap, stderr);
	}
}


void mergeEventCollection() {
	int tmp1 = 1;
	int tmp2 = 1;
	int tmp3 = 1;
	int tmp4 = 1;
	int tmp5 = 1;
	int tmp6 = 1;
	int tmp7 = 1;
	int tmp8 = 1;
	int tmp9 = 1;
	int tmp10 = 1;
	int tmp11 = 1;
	int tmp12 = 1;
	int tmp13 = 1;
	int tmp14 = 1;
	int tmp15 = 1;
	int tmp16 = 1;
	int tmp17 = 1;
	int tmp18 = 1;

	struct soap *soap = soap_new();
	DBS__File aFile1;
	aFile1.guid = "abcdef";
	aFile1.logicalFileName = "Logical";
	aFile1.checksum = "Dummychecksum";
	aFile1.fileStatus = "FILE";
	aFile1.fileType = "EVD";
	aFile1.fileSize = &tmp13;
	aFile1.fileBlockId = &tmp14;
	vector<DBS__File*> fileList1;
	fileList1.push_back(&aFile1);

	DBS__File aFile2;
	aFile2.guid = "abcdef";
	aFile2.logicalFileName = "Logical";
	aFile2.checksum = "Dummychecksum";
	aFile2.fileStatus = "FILE";
	aFile2.fileType = "EVD";
	aFile2.fileSize = &tmp15;
	aFile2.fileBlockId = &tmp16;
	vector<DBS__File*> fileList2;
	fileList2.push_back(&aFile2);

	DBS__File aFile3;
	aFile3.guid = "abcdef";
	aFile3.logicalFileName = "Logical";
	aFile3.checksum = "Dummychecksum";
	aFile3.fileStatus = "FILE";
	aFile3.fileType = "EVD";
	aFile3.fileSize = &tmp17;
	aFile3.fileBlockId = &tmp18;
	vector<DBS__File*> fileList3;
	fileList3.push_back(&aFile3);


	/*DBS__EventCollection ec1;
	ec1.status = "NEW";
	ec1.collectionName = "COLL1";
	ec1.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec1.parentageType = "HIT";
	ec1.collectionIndex = &tmp3;
	ec1.numberOfEvents = &tmp4;
	ec1.fileList = fileList;*/
	
	DBS__EventCollection ec2;
	ec2.status = "NEW";
	ec2.collectionName = "COLL2";
	ec2.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec2.parentageType = "HIT2";
	ec2.collectionIndex = &tmp5;
	ec2.numberOfEvents = &tmp6;
	ec2.fileList = fileList1;
	/*ec2.parent = &ec1;

	DBS__EventCollection ec4;
	ec4.status = "NEW";
	ec4.collectionName = "COLL4";
	ec4.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec4.parentageType = "HIT4";
	ec4.collectionIndex = &tmp7;
	ec4.numberOfEvents = &tmp8;
	ec4.fileList = fileList;*/
	
	DBS__EventCollection ec5;
	ec5.status = "NEW";
	ec5.collectionName = "COLL5";
	ec5.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec5.parentageType = "HIT5";
	ec5.collectionIndex = &tmp9;
	ec5.numberOfEvents = &tmp10;
	ec5.fileList = fileList2;
	//ec5.parent = &ec4;

	vector<DBS__EventCollection*> eventCollectionList;
	eventCollectionList.push_back(&ec2);
	eventCollectionList.push_back(&ec5);

	DBS__EventCollection ecOutput;
	ec5.status = "NEW";
	ec5.collectionName = "COLLOutput";
	ec5.datasetPathName = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset";
	ec5.parentageType = "HITOutput";
	ec5.collectionIndex = &tmp11;
	ec5.numberOfEvents = &tmp12;
	ec5.fileList = fileList3;

	int result;
	if(soap_call_DBS__mergeEventCollections(soap, "http://venom.fnal.gov:27986", NULL, eventCollectionList, &ecOutput, result) == 0) {
		cout<<"Event Collection  ID is "<<result<<endl;
	} else {
		soap_print_fault(soap, stderr);
	}
}

int main(int argc, char** argv){ 
	//createProcessedDataset();
	//getDatasetContents();
	insertEventCollection();
	//mergeEventCollection();
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
