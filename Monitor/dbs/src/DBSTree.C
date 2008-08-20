// example of macro to read data from an ascii file and
// create a root file with a Tree.
// see also a variant in cernbuild.C
// Author: Lee Lueking
//
// note: must be compiled ie *.C++
// usage: root -b -q 'DBSTree.C++(input,output)'
// usage: root -b -q 'DBSTree.C++("cmsdbs1.rfm","cmsdbs1.root")'
//   

// C++ language includes

#include <iostream>
#include <string>

// Root includes

#include "TSystem.h"
#include "TTree.h"
//#include "TStyle.h"
#include "TFile.h"
//#include "TCanvas.h"
//#include "TPad.h"
//#include "TH1I.h"
//#include "TH2I.h"
//#include "TPostScript.h"
//#include "TImage.h"

   struct squid_t {
// Information from access.reform.log

std::string IpAddress;
std::string NodeName;
std::string DomainName;
Int_t Time;
Int_t Size;
Int_t Duration;
std::string Instance;
std::string Api;
std::string Version;
std::string ConnectType;
std::string ConnectDn;   
    };

void DBSTree(char * input, char * output) {
   

   squid_t *squid = new squid_t;


   TFile *hfile = new TFile(output,"RECREATE");
   hfile->SetCompressionLevel(9);
   TTree *tree = new TTree("T","DBS data from ascii file");
   tree->Branch("stats",&squid);
 
   //  while (fgets(&line,80,fp)) {


   // Loop which reads from a file
   //
   // Open the ASCII file for reading
   //TString filename(input);
   //FILE *fp = fopen64(filename,"r");
   FILE *fp = fopen(input,"r");
   if ( fp != NULL ) {
      char line[512];
      while (fgets(line,512,fp)) {
	//All values are captured here
	char values[11][64];
	int i=0;

	//Various values
	//char ip[512];

	//Tokenizer
	char * pch;
	pch = strtok (line," ");
	while (pch != NULL)
	  {
	    sprintf(values[i], "%s", pch);
	    //printf ("%s\n",pch);
	    pch = strtok (NULL, " ");
	    i++;
	  }
	squid->IpAddress=values[0];squid->NodeName=values[1];squid->DomainName=values[2];squid->Time=atoi(values[3]);
        squid->Size=atoi(values[4]);squid->Duration=atoi(values[5]);
        squid->Instance=values[6];squid->Api=values[7];squid->Version=values[8];squid->ConnectType=values[9];
        squid->ConnectDn=values[10];

        tree->Fill();
      }
   }
   tree->Print();
   tree->Write();

   fclose(fp);
   //fclose(f)
   delete tree;
   delete hfile;
   //delete fp;
   //delete squid;
}
