//                                                                       
// Makes plots from the DBS logs
//
//
// Adapted to use ROOT Tree file by Lee Lueking
// November 2, 2007
//
// Input is file "dbs_log.root" which is made using DBSTree.C, 
// which uses parsed version of access.log as input
//
// usage: root -b -q 'DBSTreePlotsT1.C(input,begintime,endtime,binsize)' //all times in seconds
// example: root -b -q 'SquidTreePlots.C("cmsfrontier2.access.log.200710250001.rfm.root",1190851200,1197396225,3600)'
// This means between times, bin by 3600 (1 hour) bins. 
//    
#include <iostream>
void SquidTreePlots(char *file, int begintime, int endtime, int timeperbin)
{
  //void main(){
  Int_t begin;  //start time for plots
  Int_t end;  // end time for plot
  Int_t binsize; //seconds in each bin
  Int_t numbins;  // number of bins
  Int_t numdays; //number of days in time range
  //int begin;  //start time for plots
  //int end;  // end time for plot
  //int binsize; //seconds in each bin
  //int numbins;  // number of bins
  //int numdays; //number of days in time range
  //char * line;
  char * time_label;
  begin = begintime;
  end = endtime;
  binsize=timeperbin;
  numbins=(end-begin)/timeperbin;
  numdays=(end-begin)/86400;
  cout << "numdays:" << numdays ;
  char  line [50];
  int n;
  // intime cut
  n=sprintf(line,"Time >= %d && Time <= %d",begin,end);
  //TString line="Time => %d && Time =< %d",&begin,&end;
  TCut intime=line;
  //TCut intime="Time => %d && Time =< %d",begin,end;
  //rate factor correction
  n=sprintf(line,"%f",1./binsize);
  TCut ratefactor=line;

  if ( numdays <= 1) {time_label="%H:%M";} //less than a day
  else {time_label="%y/%m/%d";}
  cout << "Time Format:" << time_label ;
  //TFile *f1 = new TFile("squid.root");
  TChain *chain = new TChain("T");
  T->Add(file);
  //T->Add("cmsfrontier*.access.log.200711*.rfm.root");   

   // Set the display of statistics, see
   // http://root.cern.ch/root/html/TStyle.html#TStyle:SetOptStat
   gStyle->SetOptStat("euo");


   // Set the time offset
   gStyle->SetTimeOffset(0);
   gSystem->Setenv("TZ","UTC"); 


   // Set the frame border mode, and 
   // set the histogram fill color, see
   // http://root.cern.ch/root/html/TStyle.html
   gStyle->SetFrameBorderMode(0);
   gStyle->SetHistFillColor(kBlue);


   // Create a new canvas, see
   // http://root.cern.ch/root/html/TCanvas.html#TCanvas
   TCanvas *canvas1 = new TCanvas("canvas1","Squid Plots",1200,800);
   TCanvas *canvas2 = new TCanvas("canvas2","Squid Plots",800,1200);
 
  canvas1->cd();

   // Set color of the canvas to white 
   // The list of colors can be seen under 
   // http://root.cern.ch/root/html/src/TColor.cxx.html#zawym
   canvas1->SetFillColor(kWhite);


   // Make a pad on the canvas
   TPad *pad1 = new TPad("pad1","Squid Plots",0.0,0.0,1.0,1.0);

   pad1->Draw();
   pad1->cd();


   // Set color of pad to white
   pad1->SetFillColor(kWhite);
   pad1->SetFrameFillColor(kWhite);
   pad1->SetLeftMargin(0.25);

      pad1->cd(1); gPad->SetGrid(); gPad->SetFrameFillColor(33);

/*RAW data*/

      //gPad->SetLogy(1);
      T->Draw("Duration:Time", intime && "CacheInfo == \"TCP_MEM_HIT\"","p0");
      //TH2F htemp= (TH2F*)gPad->GetPrimitave("htemp");
      //htemp->GetXaxis()->SetTimeFormat(time_label);
      //htemp->GetXaxis()->SetLabelSize(0.025);
      //htemp->GetYaxis()->SetLabelSize(0.025);
      //htemp->GetXaxis()->SetTimeDisplay(1);
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("raw-duration-post.png");
      T->Draw("Duration:Time", intime && "CacheInfo != \"TCP_MEM_HIT\"","p0");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("raw-duration-get.png");
      //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime1 = new TProfile("time1",
                          "Response Duration vs. Time (TCP_MEM_HIT)",
                          numbins,begin,end);

   // Label X Axis
   htime1->GetXaxis()->CenterTitle();
   htime1->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime1->GetYaxis()->CenterTitle();
   htime1->SetYTitle("Response time (ms)");
   htime1->GetYaxis()->SetTitleOffset(2.0);
   htime1->GetXaxis()->SetTimeFormat(time_label);
   htime1->GetXaxis()->SetLabelSize(0.025);
   htime1->GetYaxis()->SetLabelSize(0.025);
   htime1->GetXaxis()->SetTimeDisplay(1);
   htime1->SetLineStyle(0);
   htime1->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime1->SetMarkerStyle(kFullCircle);
   htime1->SetMarkerColor(kBlue);
   htime1->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time1", intime && "CacheInfo == \"TCP_MEM_HIT\"","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time1.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime2 = new TProfile("time2",
                          "Response duration vs. time (NOT TCP_MEM_HIT)",
                          numbins,begin,end);

   // Label X Axis
   htime2->GetXaxis()->CenterTitle();
   htime2->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime2->GetYaxis()->CenterTitle();
   htime2->SetYTitle("Response time (ms)");
   htime2->GetYaxis()->SetTitleOffset(2.0);
   htime2->GetXaxis()->SetTimeFormat(time_label);
   htime2->GetXaxis()->SetLabelSize(0.025);
   htime2->GetYaxis()->SetLabelSize(0.025);
   htime2->GetXaxis()->SetTimeDisplay(1);
   htime2->SetLineStyle(0);
   htime2->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime2->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime2->SetMarkerStyle(kFullSquare);
   htime2->SetMarkerColor(kBlue);
   htime2->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time2", intime && "CacheInfo != \"TCP_MEM_HIT\"","prof");
  
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time2.png");
      //ptime.Close();
   //gPad->SetLogy(0);


/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime3 = new TProfile("time3",
                          "Response Duration vs. Time (Instance PromptProd)",
                          numbins,begin,end);

   // Label X Axis
   htime3->GetXaxis()->CenterTitle();
   htime3->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime3->GetYaxis()->CenterTitle();
   htime3->SetYTitle("Response time (ms)");
   htime3->GetYaxis()->SetTitleOffset(2.0);
   htime3->GetXaxis()->SetTimeFormat(time_label);
   htime3->GetXaxis()->SetLabelSize(0.025);
   htime3->GetYaxis()->SetLabelSize(0.025);
   htime3->GetXaxis()->SetTimeDisplay(1);
   htime3->SetLineStyle(0);
   htime3->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime3->SetMarkerStyle(kFullCircle);
   htime3->SetMarkerColor(kBlue);
   htime3->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time3",intime && "Instance == \"PromptProd\"","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time3.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime4 = new TProfile("time4",
                          "Response Duration vs. Time (Instance FrontierCSA07)",
                          numbins,begin,end);

   // Label X Axis
   htime4->GetXaxis()->CenterTitle();
   htime4->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime4->GetYaxis()->CenterTitle();
   htime4->SetYTitle("Response time (ms)");
   htime4->GetYaxis()->SetTitleOffset(2.0);
   htime4->GetXaxis()->SetTimeFormat(time_label);
   htime4->GetXaxis()->SetLabelSize(0.025);
   htime4->GetYaxis()->SetLabelSize(0.025);
   htime4->GetXaxis()->SetTimeDisplay(1);
   htime4->SetLineStyle(0);
   htime4->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime4->SetMarkerStyle(kFullCircle);
   htime4->SetMarkerColor(kBlue);
   htime4->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time4",intime && "Instance == \"FrontierCSA07\"","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time4.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime5 = new TProfile("time5",
                          "Response Duration vs. Time (Instance FrontierProd)",
                          numbins,begin,end);

   // Label X Axis
   htime5->GetXaxis()->CenterTitle();
   htime5->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime5->GetYaxis()->CenterTitle();
   htime5->SetYTitle("Response time (ms)");
   htime5->GetYaxis()->SetTitleOffset(2.0);
   htime5->GetXaxis()->SetTimeFormat(time_label);
   htime5->GetXaxis()->SetLabelSize(0.025);
   htime5->GetYaxis()->SetLabelSize(0.025);
   htime5->GetXaxis()->SetTimeDisplay(1);
   htime5->SetLineStyle(0);
   htime5->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime5->SetMarkerStyle(kFullCircle);
   htime5->SetMarkerColor(kBlue);
   htime5->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time5",intime && "Instance == \"FrontierProd\"","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time5.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*SIZE vs TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *hsizevt1 = new TProfile("sizevt1",
                          "Response SIZE vs. Time (TCP_MEM_HIT)",
                          numbins,begin,end);

   // Label X Axis
   hsizevt1->GetXaxis()->CenterTitle();
   hsizevt1->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hsizevt1->GetYaxis()->CenterTitle();
   hsizevt1->SetYTitle("Response Size (Bytes)");
   hsizevt1->GetYaxis()->SetTitleOffset(2.0);
   hsizevt1->GetXaxis()->SetTimeFormat(time_label);
   hsizevt1->GetXaxis()->SetLabelSize(0.025);
   hsizevt1->GetYaxis()->SetLabelSize(0.025);
   hsizevt1->GetXaxis()->SetTimeDisplay(1);
   hsizevt1->SetLineStyle(0);
   hsizevt1->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   hsizevt1->SetMarkerStyle(kFullCircle);
   hsizevt1->SetMarkerColor(kBlue);
   hsizevt1->SetLineColor(kBlue);
      T->Draw("Size:Time>>sizevt1", intime && "CacheInfo == \"TCP_MEM_HIT\"","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("sizevt1.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*RATE*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TH1F *htime = new TH1F("time",
                          "Rate (Hz) vs. Time",
                          numbins,begin,end);
   // Label X Axis
   htime->GetXaxis()->CenterTitle();
   htime->SetXTitle("Time of accesses (UTC)");
   htime->GetYaxis()->CenterTitle();
   htime->SetYTitle("Rate (Hz)");
   htime->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime->GetXaxis()->SetTimeFormat(time_label);
   htime->GetXaxis()->SetLabelSize(0.025);
   htime->GetYaxis()->SetLabelSize(0.025);
   htime->GetXaxis()->SetTimeDisplay(1);
   htime->SetLineColor(0);
   htime->SetLineStyle(0);
   htime->SetMarkerStyle(kFullCircle);
   htime->SetMarkerSize(0.4);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   htime->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
      T->Draw("Time>>time",ratefactor,"hist P9");
  
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time.png");
      //ptime.Close();
      // gPad->SetLogy(0);


/*SIZE vs TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *hsizevt2 = new TProfile("sizevt2",
                          "Response SIZE vs. Time (NOT TCP_MEM_HIT)",
                          numbins,begin,end);

   // Label X Axis
   hsizevt2->GetXaxis()->CenterTitle();
   hsizevt2->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hsizevt2->GetYaxis()->CenterTitle();
   hsizevt2->SetYTitle("Response Size (Bytes)");
   hsizevt2->GetYaxis()->SetTitleOffset(2.0);
   hsizevt2->GetXaxis()->SetTimeFormat(time_label);
   hsizevt2->GetXaxis()->SetLabelSize(0.025);
   hsizevt2->GetYaxis()->SetLabelSize(0.025);
   hsizevt2->GetXaxis()->SetTimeDisplay(1);
   hsizevt2->SetLineStyle(0);
   hsizevt2->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   hsizevt2->SetMarkerStyle(kFullCircle);
   hsizevt2->SetMarkerColor(kBlue);
   hsizevt2->SetLineColor(kBlue);
      T->Draw("Size:Time>>sizevt2", intime && "CacheInfo != \"TCP_MEM_HIT\"","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("sizevt2.png");
      //ptime.Close();
   //gPad->SetLogy(0);      

/*DURATION*/
   // Create Histogram "duration"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TH1I *hduration = new TH1I("duration",
                              "Duration of Accesses (TCP_MEM_HIT)",
                              200,0,20000);

   // Label X Axis
   hduration->GetXaxis()->CenterTitle();
   hduration->SetXTitle("Duration of accesses (ms)");
   hduration->GetYaxis()->CenterTitle();
   hduration->SetYTitle("Number of Accesses");
   hduration->GetYaxis()->SetTitleOffset(2.);
      // Draw Histogram "duration"
      gStyle->SetOptStat("oue");
      gPad->SetLogy(1);
      T->Draw("Duration>>duration", intime && "CacheInfo == \"TCP_MEM_HIT\"");
      //duration->SetLineColor(kRed);
      //T->Draw("Duration>>+duration");
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("duration.png");
      //pduration.Close();   
      gPad->SetLogy(0);
/*DURATION*/
   // Create Histogram "duration"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TH1I *hduration2 = new TH1I("duration2",
                              "Duration of Accesses (NOT TCP_MEM_HIT)",
                              200,0,20000);

   // Label X Axis
   hduration2->GetXaxis()->CenterTitle();
   hduration2->SetXTitle("Duration of accesses (ms)");
   hduration2->GetYaxis()->CenterTitle();
   hduration2->SetYTitle("Number of Accesses");
   hduration2->GetYaxis()->SetTitleOffset(2.);
   hduration2->GetXaxis()->SetLabelSize(0.025);
   hduration2->GetYaxis()->SetLabelSize(0.025);
      // Draw Histogram "duration"
      gStyle->SetOptStat("oue");
      gPad->SetLogy(1);
      T->Draw("Duration>>duration2", intime && "CacheInfo != \"TCP_MEM_HIT\"");
      //duration->SetLineColor(kRed);
      //T->Draw("Duration>>+duration");
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("duration2.png");
      //pduration.Close();   
      gPad->SetLogy(0);
/*DOMAIN vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *sdvt = new TH2I("dvt",
                "Domain vs. Time",
                numbins,begin,end,60,0,60); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   //sdvt->SetBit(TH1::kCanRebin);

   // Label X Axis
   sdvt->GetXaxis()->CenterTitle();
   sdvt->SetXTitle("Time of accesses (UTC)");
   sdvt->GetYaxis()->CenterTitle();
   sdvt->SetYTitle("Domain Name");
   sdvt->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   sdvt->GetXaxis()->SetTimeFormat(time_label);
   sdvt->GetXaxis()->SetLabelSize(0.025);
   sdvt->GetXaxis()->SetTimeDisplay(1);
   sdvt->GetYaxis()->SetLabelSize(0.025);

   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   sdvt->GetXaxis()->SetNdivisions(707);

      // Draw Scatterplot "dvt"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      sdvt->LabelsDeflate("Y");
      T->Draw("DomainName:Time>>dvt",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("dvt.png");
      //pdvt.Close();
/*SCHEMAOWNER vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hapivt = new TH2I("apivt",
                "Schema Owner vs. Time",
                numbins,begin,end,60,0,60); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   //hapivt->SetBit(TH1::kCanRebin);

   // Label X Axis
   hapivt->GetXaxis()->CenterTitle();
   hapivt->SetXTitle("Time of accesses (UTC)");
   hapivt->GetYaxis()->CenterTitle();
   hapivt->SetYTitle("Schema Owner");
   hapivt->GetYaxis()->SetTitleOffset(3.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hapivt->GetXaxis()->SetTimeFormat(time_label);
   hapivt->GetXaxis()->SetLabelSize(0.025);
   hapivt->GetXaxis()->SetTimeDisplay(1);
   hapivt->GetYaxis()->SetLabelSize(0.025);

   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //hapivt->GetXaxis()->SetNdivisions(707);

      // Draw Scatterplot "dvt"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hapivt->LabelsDeflate("Y");
      T->Draw("SchemaOwner:Time>>apivt",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("apivt.png");
      //pdvt.Close();

/*Instance vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hinstvt = new TH2I("instvt",
                "Frontier Instance vs. Time",
                numbins,begin,end,12,0,12); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   //hapivt->SetBit(TH1::kCanRebin);

   // Label X Axis
   hinstvt->GetXaxis()->CenterTitle();
   hinstvt->SetXTitle("Time of accesses (UTC)");
   hinstvt->GetYaxis()->CenterTitle();
   hinstvt->SetYTitle("Frontier Instance");
   hinstvt->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hinstvt->GetXaxis()->SetTimeFormat(time_label);
   hinstvt->GetXaxis()->SetLabelSize(0.025);
   hinstvt->GetXaxis()->SetTimeDisplay(1);
   hinstvt->GetYaxis()->SetLabelSize(0.025);

   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //hapivt->GetXaxis()->SetNdivisions(707);

      // Draw Scatterplot "dvt"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hinstvt->LabelsDeflate("Y");
      T->Draw("Instance:Time>>instvt",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("instvt.png");
      //pdvt.Close();

/*SCHEMAOWNER vs. SIZE*/
   // Create Histogram "sizeschema"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH2I *hapivs = new TH2I("apisize",
                "Schema Owner vs. Size",
                100,0,40000,60,0,60);
   //pad1->SetxLog(1);
   hapivt->GetXaxis()->CenterTitle();
   hapivs->SetXTitle("Size (bytes)");
   hapivs->SetYTitle("Schema Owner");   
   hapivs->GetYaxis()->CenterTitle();
   hapivs->GetYaxis()->SetTitleOffset(3.);
   hapivs->GetXaxis()->SetLabelSize(0.025);
   hapivs->GetYaxis()->SetLabelSize(0.025);
   gStyle->SetOptStat("euo");
   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   //hapivs->SetBit(TH2::kCanRebin);
      // Histogram "tag"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hapivs->LabelsDeflate();
   

      // Draw histogram "tag" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf 
      // figure 3-10 on page numbered 33, pdf page 11
      T->Draw("SchemaOwner:Size>>apisize",intime);
      
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("apisize.png");
      //ptag.Close();

/*SIZE vs. DURATION*/
   // Create Scatterplot "svds"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *ssvds = new TH2I("svds",
                 "Size vs. Duration",
                 200,0,20000,400,0,10000);

   // Label Axes
   ssvds->GetXaxis()->CenterTitle();
   ssvds->SetXTitle("Duration (ms)");
   //ssvds->GetXaxis()->SetTitleOffset(0.04);

   ssvds->GetYaxis()->CenterTitle();
   ssvds->SetYTitle("Size (Bytes)");
   ssvds->GetYaxis()->SetTitleOffset(2.0);
   ssvds->GetXaxis()->SetLabelSize(0.025);
   ssvds->GetYaxis()->SetLabelSize(0.025);
   //gPad->SetLogx(1);   gPad->SetLogy(1);
      // Draw Scatterplot "svds"
     //temp  T->Draw("Size:Duration>>svds"); // from DB
      T->Draw("Size:Duration>>svds",intime);  // from memory
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("svds.png");
      //gPad->SetLogx(0);   gPad->SetLogy(0);
      //psvds.Close();


/*Domain vs INSTANCE*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hdomvi = new TH2I("domvi",
                "Frontier Instance vs. Domain",
                40,0,40,60,0,60); 

   // Allow number of bins to change, see 
   hdomvi->SetBit(TH2::kCanRebin);
   // Label X Axis
   hdomvi->GetXaxis()->CenterTitle();
   hdomvi->SetXTitle("Domain");
   hdomvi->GetYaxis()->CenterTitle();
   hdomvi->SetYTitle("Instance");
   hdomvi->GetYaxis()->SetTitleOffset(2.);
   //hdomvi->GetXaxis()->SetTimeFormat(time_label);
   hdomvi->GetXaxis()->SetLabelSize(0.025);
   hdomvi->GetXaxis()->SetTitleOffset(1.0);
   //hdomvi->GetXaxis()->SetTimeDisplay(1);
   hdomvi->GetYaxis()->SetLabelSize(0.025);
   T->Draw("Instance:DomainName>>domvi",intime ); //&& Instance !=\"malformed\"");
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("domvi.png");
      //hvsnvdvt->Close();


/*INSTANCE*/ 
   // Create Histogram "instance"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *hinstance = new TH1I("instance",
                     "Accesses by Frontier instance",12,0,12);
   hinstance->SetStats(1);
   hinstance->SetYTitle("Number of Requests");
   hinstance->GetXaxis()->CenterTitle();
   hinstance->GetXaxis()->SetTitleOffset(2.);
   hinstance->SetXTitle("Frontier Instance");
   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hinstance->SetBit(TH1::kCanRebin);
      // Histogram "instance"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hinstance->LabelsDeflate();
      hinstance->GetXaxis()->SetLabelSize(0.025);
      hinstance->GetYaxis()->SetLabelSize(0.025);
      //hinstance->SetLog();
      // Draw histogram "instance" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf 
      // figure 3-10 on page numbered 33, pdf page 11
      pad1->cd(1); gPad->SetGrid(); gPad->SetLogx(); gPad->SetFrameFillColor(33);
      T->Draw("Instance>>instance", intime,"hbar4");// && "Instance !=\"malformed\"","hbar4"); 

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("instance.png");
      //pinstance.Close();

/*DOMAINNAME*/
   // Create Histogram "domain"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *hdomain = new TH1I("domain",
                   "Accesses by Domain",
                   60,0,60);
   hdomain->SetStats(1);
   hdomain->GetXaxis()->CenterTitle();
   hdomain->SetXTitle("Domain Name");
   hdomain->GetYaxis()->CenterTitle();
   hdomain->SetYTitle("Number of Requests");
   hdomain->GetXaxis()->SetTitleOffset(2.);
   hdomain->GetXaxis()->SetLabelSize(0.025);
   hdomain->GetYaxis()->SetLabelSize(0.025);
   // Allow number of bins to change, see
   // http://root.cern.ch/root/html/TH1.html
   //hdomain->SetBit(TH1::kCanRebin);

      // Histogram "domain"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hdomain->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      // figure 3-10 on page numbered 33, pdf page 11
      //T->Draw("DomainName>>domain","DomainName == \"Frontier\"","hbar2");
      T->Draw("DomainName>>domain",intime,"hbar2");


   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("domainname.png");

/*TableNAME*/
   // Create Histogram "domain"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *htable1 = new TH1I("table1",
                   "Accesses by Table (FrontierProd)",
                   120,0,120);
   htable1->SetStats(1);
   htable1->GetXaxis()->CenterTitle();
   htable1->SetXTitle("Table Name");
   htable1->GetYaxis()->CenterTitle();
   htable1->SetYTitle("Number of Requests");
   htable1->GetXaxis()->SetTitleOffset(2.);
   htable1->GetXaxis()->SetLabelSize(0.010);
   htable1->GetYaxis()->SetLabelSize(0.025);
   // Allow number of bins to change, see
   // http://root.cern.ch/root/html/TH1.html
   //htable1->SetBit(TH1::kCanRebin);

      // Histogram "domain"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      htable1->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      // figure 3-10 on page numbered 33, pdf page 11
      //T->Draw("DomainName>>domain","DomainName == \"Frontier\"","hbar2");
      T->Draw("TableName>>table1",intime && "Instance == \"FrontierProd\"","hbar2");


   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("table1.png"); 
/*TableNAME*/
   // Create Histogram "domain"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *htable2 = new TH1I("table2",
                   "Accesses by Table (FrontierCSA07)",
                   120,0,120);
   htable2->SetStats(1);
   htable2->GetXaxis()->CenterTitle();
   htable2->SetXTitle("Table Name");
   htable2->GetYaxis()->CenterTitle();
   htable2->SetYTitle("Number of Requests");
   htable2->GetXaxis()->SetTitleOffset(2.);
   htable2->GetXaxis()->SetLabelSize(0.010);
   htable2->GetYaxis()->SetLabelSize(0.025);
   // Allow number of bins to change, see
   // http://root.cern.ch/root/html/TH1.html
   //htable2->SetBit(TH1::kCanRebin);

      // Histogram "domain"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      htable2->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      // figure 3-10 on page numbered 33, pdf page 11
      //T->Draw("DomainName>>domain","DomainName == \"Frontier\"","hbar2");
      T->Draw("TableName>>table2",intime && "Instance == \"FrontierCSA07\"","hbar2");


   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("table2.png"); 
/*TableNAME*/
   // Create Histogram "domain"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *htable3 = new TH1I("table3",
                   "Accesses by Table (PromptProd)",
                   120,0,120);
   htable3->SetStats(1);
   htable3->GetXaxis()->CenterTitle();
   htable3->SetXTitle("Table Name");
   htable3->GetYaxis()->CenterTitle();
   htable3->SetYTitle("Number of Requests");
   htable3->GetXaxis()->SetTitleOffset(2.);
   htable3->GetXaxis()->SetLabelSize(0.010);
   htable3->GetYaxis()->SetLabelSize(0.025);
   // Allow number of bins to change, see
   // http://root.cern.ch/root/html/TH1.html
   //htable2->SetBit(TH1::kCanRebin);

      // Histogram "domain"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      htable3->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      // figure 3-10 on page numbered 33, pdf page 11
      //T->Draw("DomainName>>domain","DomainName == \"Frontier\"","hbar2");
      T->Draw("TableName>>table3",intime && "Instance == \"PromptProd\"","hbar2");


   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("table3.png"); 

/*SCHEMAOWNER*/
   // Create Histogram "domain"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *hschemaowner = new TH1I("schemaowner",
                   "Accesses by SchemaOwner",
                   60,0,60);
   hschemaowner->SetStats(1);
   hschemaowner->GetXaxis()->CenterTitle();
   hschemaowner->SetXTitle("Schema Owner");
   hschemaowner->GetYaxis()->CenterTitle();
   hschemaowner->SetYTitle("Number of Requests");
   hschemaowner->GetXaxis()->SetTitleOffset(3.);
   hschemaowner->GetXaxis()->SetLabelSize(0.02);
   hschemaowner->GetYaxis()->SetLabelSize(0.025);
   // Allow number of bins to change, see
   // http://root.cern.ch/root/html/TH1.html
   //htable2->SetBit(TH1::kCanRebin);

      // Histogram "domain"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hschemaowner->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      // figure 3-10 on page numbered 33, pdf page 11
      //T->Draw("DomainName>>domain","DomainName == \"Frontier\"","hbar2");
      T->Draw("SchemaOwner>>schemaowner",intime,"hbar2");


   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("schemaowner.png"); 

}
