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
// example: root -b -q 'DBSTreePlotsT1.C("cmsdbs1_access_log..txt.rfm.root",1190851200,1197396225,3600)'
// This means between times, bin by 3600 (1 hour) bins. 
//    
#include <iostream>
void DBSTreePlots(char *file, int begintime, int endtime, int timeperbin)
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
  // Duration must be greater than 0
  n=sprintf(line,"Duration > 0");
  TCut Duration_gt_0=line;

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
   TCanvas *canvas1 = new TCanvas("canvas1","Squid Plots",1200,600);
   //TCanvas *canvas2 = new TCanvas("canvas2","Squid Plots",800,1200);
 
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
      T->Draw("Duration:Time", intime && Duration_gt_0 && "ConnectType == \"POST\"","p0");
      //TH2F htemp= (TH2F*)gPad->GetPrimitave("htemp");
      //htemp->GetXaxis()->SetTimeFormat(time_label);
      //htemp->GetXaxis()->SetLabelSize(0.025);
      //htemp->GetYaxis()->SetLabelSize(0.025);
      //htemp->GetXaxis()->SetTimeDisplay(1);
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("raw-duration-post.png");
      T->Draw("Duration:Time", intime && Duration_gt_0 && "ConnectType == \"GET\"","p0");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("raw-duration-get.png");
      //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime1 = new TProfile("time1",
                          "Response Duration vs. Time (POST)",
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
      T->Draw("Duration:Time>>time1", Duration_gt_0 && "ConnectType == \"POST\"","prof");

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
                          "Response duration vs. time (GET)",
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
      T->Draw("Duration:Time>>time2", Duration_gt_0 && "ConnectType == \"GET\"","prof");
  
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
                          "Response Duration vs. Time (insertFiles)",
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
      T->Draw("Duration:Time>>time3", Duration_gt_0 && "Api == \"insertFiles\"","prof");

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
                          "Response Duration vs. Time (listFiles)",
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
      T->Draw("Duration:Time>>time4", Duration_gt_0 && "Api == \"listFiles\"","prof");

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
                          "Response Duration vs. Time (listBlocks)",
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
      T->Draw("Duration:Time>>time5", Duration_gt_0 && "Api == \"listBlocks\"","prof");

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
                          "Response SIZE vs. Time (POST)",
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
      T->Draw("Size:Time>>sizevt1","ConnectType == \"POST\"","prof");

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
                          "Response SIZE vs. Time (GET)",
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
      T->Draw("Size:Time>>sizevt2",intime && "ConnectType == \"GET\"","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("sizevt2.png");
      //ptime.Close();
   //gPad->SetLogy(0);      

/*--> begin global instance specific*/
/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime1g = new TProfile("time1g",
                          "Response Duration vs. Time (POST - Global)",
                          numbins,begin,end);

   // Label X Axis
   htime1g->GetXaxis()->CenterTitle();
   htime1g->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime1g->GetYaxis()->CenterTitle();
   htime1g->SetYTitle("Response time (ms)");
   htime1g->GetYaxis()->SetTitleOffset(2.0);
   htime1g->GetXaxis()->SetTimeFormat(time_label);
   htime1g->GetXaxis()->SetLabelSize(0.025);
   htime1g->GetYaxis()->SetLabelSize(0.025);
   htime1g->GetXaxis()->SetTimeDisplay(1);
   htime1g->SetLineStyle(0);
   htime1g->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime1g->SetMarkerStyle(kFullCircle);
   htime1g->SetMarkerColor(kBlue);
   htime1g->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time1g", Duration_gt_0 && "ConnectType == \"POST\" && (Instance == \"prod_global\" || Instance == \"prod_global_writer\" )","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time1g.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime2g = new TProfile("time2g",
                          "Response duration vs. time (GET - Global)",
                          numbins,begin,end);

   // Label X Axis
   htime2g->GetXaxis()->CenterTitle();
   htime2g->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime2g->GetYaxis()->CenterTitle();
   htime2g->SetYTitle("Response time (ms)");
   htime2g->GetYaxis()->SetTitleOffset(2.0);
   htime2g->GetXaxis()->SetTimeFormat(time_label);
   htime2g->GetXaxis()->SetLabelSize(0.025);
   htime2g->GetYaxis()->SetLabelSize(0.025);
   htime2g->GetXaxis()->SetTimeDisplay(1);
   htime2g->SetLineStyle(0);
   htime2g->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime2->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime2g->SetMarkerStyle(kFullSquare);
   htime2g->SetMarkerColor(kBlue);
   htime2g->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time2g", Duration_gt_0 && "ConnectType == \"GET\" && (Instance == \"prod_global\" || Instance == \"prod_global_writer\" )" ,"prof");
  
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time2g.png");
      //ptime.Close();
   //gPad->SetLogy(0);


/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime3g = new TProfile("time3g",
                          "Response Duration vs. Time (insertFiles - Global)",
                          numbins,begin,end);

   // Label X Axis
   htime3g->GetXaxis()->CenterTitle();
   htime3g->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime3g->GetYaxis()->CenterTitle();
   htime3g->SetYTitle("Response time (ms)");
   htime3g->GetYaxis()->SetTitleOffset(2.0);
   htime3g->GetXaxis()->SetTimeFormat(time_label);
   htime3g->GetXaxis()->SetLabelSize(0.025);
   htime3g->GetYaxis()->SetLabelSize(0.025);
   htime3g->GetXaxis()->SetTimeDisplay(1);
   htime3g->SetLineStyle(0);
   htime3g->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime3g->SetMarkerStyle(kFullCircle);
   htime3g->SetMarkerColor(kBlue);
   htime3g->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time3g", Duration_gt_0 && "Api == \"insertFiles\" && (Instance == \"prod_global\" || Instance == \"prod_global_writer\" )","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time3g.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime4g = new TProfile("time4g",
                          "Response Duration vs. Time (listFiles - Global)",
                          numbins,begin,end);

   // Label X Axis
   htime4g->GetXaxis()->CenterTitle();
   htime4g->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime4g->GetYaxis()->CenterTitle();
   htime4g->SetYTitle("Response time (ms)");
   htime4g->GetYaxis()->SetTitleOffset(2.0);
   htime4g->GetXaxis()->SetTimeFormat(time_label);
   htime4g->GetXaxis()->SetLabelSize(0.025);
   htime4g->GetYaxis()->SetLabelSize(0.025);
   htime4g->GetXaxis()->SetTimeDisplay(1);
   htime4g->SetLineStyle(0);
   htime4g->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime4g->SetMarkerStyle(kFullCircle);
   htime4g->SetMarkerColor(kBlue);
   htime4g->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time4g", Duration_gt_0 && "Api == \"listFiles\" && (Instance == \"prod_global\" || Instance == \"prod_global_writer\" )","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time4g.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *htime5g = new TProfile("time5g",
                          "Response Duration vs. Time (listBlocks - Global)",
                          numbins,begin,end);

   // Label X Axis
   htime5g->GetXaxis()->CenterTitle();
   htime5g->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htime5g->GetYaxis()->CenterTitle();
   htime5g->SetYTitle("Response time (ms)");
   htime5g->GetYaxis()->SetTitleOffset(2.0);
   htime5g->GetXaxis()->SetTimeFormat(time_label);
   htime5g->GetXaxis()->SetLabelSize(0.025);
   htime5g->GetYaxis()->SetLabelSize(0.025);
   htime5g->GetXaxis()->SetTimeDisplay(1);
   htime5g->SetLineStyle(0);
   htime5g->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   htime5g->SetMarkerStyle(kFullCircle);
   htime5g->SetMarkerColor(kBlue);
   htime5g->SetLineColor(kBlue);
      T->Draw("Duration:Time>>time5g", Duration_gt_0 && "Api == \"listBlocks\" && (Instance == \"prod_global\" || Instance == \"prod_global_writer\" )","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("time5g.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*SIZE vs TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *hsizevt1g = new TProfile("sizevt1g",
                          "Response SIZE vs. Time (POST - Global)",
                          numbins,begin,end);

   // Label X Axis
   hsizevt1g->GetXaxis()->CenterTitle();
   hsizevt1g->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hsizevt1g->GetYaxis()->CenterTitle();
   hsizevt1g->SetYTitle("Response Size (Bytes)");
   hsizevt1g->GetYaxis()->SetTitleOffset(2.0);
   hsizevt1g->GetXaxis()->SetTimeFormat(time_label);
   hsizevt1g->GetXaxis()->SetLabelSize(0.025);
   hsizevt1g->GetYaxis()->SetLabelSize(0.025);
   hsizevt1g->GetXaxis()->SetTimeDisplay(1);
   hsizevt1g->SetLineStyle(0);
   hsizevt1g->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   hsizevt1g->SetMarkerStyle(kFullCircle);
   hsizevt1g->SetMarkerColor(kBlue);
   hsizevt1g->SetLineColor(kBlue);
      T->Draw("Size:Time>>sizevt1g","ConnectType == \"POST\" && (Instance == \"prod_global\" || Instance == \"prod_global_writer\" )","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("sizevt1g.png");
      //ptime.Close();
   //gPad->SetLogy(0);

/*RATE*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TH1F *htimeg = new TH1F("timeg",
                          "Rate (Hz) vs. Time (Global)",
                          numbins,begin,end);
   // Label X Axis
   htimeg->GetXaxis()->CenterTitle();
   htimeg->SetXTitle("Time of accesses (UTC)");
   htimeg->GetYaxis()->CenterTitle();
   htimeg->SetYTitle("Rate (Hz)");
   htimeg->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   htimeg->GetXaxis()->SetTimeFormat(time_label);
   htimeg->GetXaxis()->SetLabelSize(0.025);
   htimeg->GetYaxis()->SetLabelSize(0.025);
   htimeg->GetXaxis()->SetTimeDisplay(1);
   htimeg->SetLineColor(0);
   htimeg->SetLineStyle(0);
   htimeg->SetMarkerStyle(kFullCircle);
   htimeg->SetMarkerSize(0.4);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   htimeg->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
      T->Draw("Time>>timeg",ratefactor * "(Instance == \"prod_global\" || Instance == \"prod_global_writer\" )","hist P9");
  
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("timeg.png");
      //ptime.Close();
      // gPad->SetLogy(0);

/*SIZE vs TIME*/
   // Create Histogram "time"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TProfile *hsizevt2g = new TProfile("sizevt2g",
                          "Response SIZE vs. Time (GET - Global)",
                          numbins,begin,end);

   // Label X Axis
   hsizevt2g->GetXaxis()->CenterTitle();
   hsizevt2g->SetXTitle("Time of accesses (UTC)");
   
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hsizevt2g->GetYaxis()->CenterTitle();
   hsizevt2g->SetYTitle("Response Size (Bytes)");
   hsizevt2g->GetYaxis()->SetTitleOffset(2.0);
   hsizevt2g->GetXaxis()->SetTimeFormat(time_label);
   hsizevt2g->GetXaxis()->SetLabelSize(0.025);
   hsizevt2g->GetYaxis()->SetLabelSize(0.025);
   hsizevt2g->GetXaxis()->SetTimeDisplay(1);
   hsizevt2g->SetLineStyle(0);
   hsizevt2g->SetMarkerSize(0.5);
   //gPad->SetLogy(1);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //htime1->GetXaxis()->SetNdivisions(707);
      // Draw Histogram "time"
      gStyle->SetOptStat("oue");
   hsizevt2g->SetMarkerStyle(kFullCircle);
   hsizevt2g->SetMarkerColor(kBlue);
   hsizevt2g->SetLineColor(kBlue);
      T->Draw("Size:Time>>sizevt2g",intime && "ConnectType == \"GET\"  && (Instance == \"prod_global\" || Instance == \"prod_global_writer\" )","prof");

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("sizevt2g.png");
      //ptime.Close();
   //gPad->SetLogy(0);      

/*--> end global instance specific*/

/*DURATION*/
   // Create Histogram "duration"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TH1I *hduration = new TH1I("duration",
                              "Duration of Accesses (GET)",
                              200,0,20000);

   // Label X Axis
   hduration->GetXaxis()->CenterTitle();
   hduration->SetXTitle("Duration of accesses (ms)");
   hduration->GetYaxis()->CenterTitle();
   hduration->SetYTitle("Number of Accesses");
   hduration->GetYaxis()->SetTitleOffset(2.);
      // Draw Histogram "duration"
      gStyle->SetOptStat("oue");
      // **gPad->SetLogy(1);
      T->Draw("Duration>>duration", Duration_gt_0 && intime && "ConnectType == \"GET\"");
      //duration->SetLineColor(kRed);
      //T->Draw("Duration>>+duration");
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("duration.png");
      //pduration.Close();   
      // **gPad->SetLogy(0);
/*DURATION*/
   // Create Histogram "duration"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   TH1I *hduration2 = new TH1I("duration2",
                              "Duration of Accesses (POST)",
                              200,0,20000);

   // Label X Axis
   hduration2->GetXaxis()->CenterTitle();
   hduration2->SetXTitle("Duration of accesses (ms)");
   hduration2->GetYaxis()->CenterTitle();
   hduration2->SetYTitle("Number of Accesses");
   hduration2->GetYaxis()->SetTitleOffset(2.);
      // Draw Histogram "duration"
      gStyle->SetOptStat("oue");
      // **gPad->SetLogy(1);
      T->Draw("Duration>>duration2", Duration_gt_0 && intime && "ConnectType == \"POST\"");
      //duration->SetLineColor(kRed);
      //T->Draw("Duration>>+duration",intime);
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("duration2.png");
      //pduration.Close();   
      // **gPad->SetLogy(0);
/*DOMAIN vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *sdvt = new TH2I("dvt",
                "Domain vs. Time",
                numbins,begin,end,10,0,10); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   sdvt->SetBit(TH1::kCanRebin);

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
      //T->Draw("DomainName:Time>>dvt","1");
      T->Draw("DomainName:Time>>dvt",intime);

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("dvt.png");
      //pdvt.Close();

/*API vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hapivt = new TH2I("apivt",
                "API vs. Time",
                numbins,begin,end,30,0,30); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   //hapivt->SetBit(TH1::kCanRebin);

   // Label X Axis
   hapivt->GetXaxis()->CenterTitle();
   hapivt->SetXTitle("Time of accesses (UTC)");
   hapivt->GetYaxis()->CenterTitle();
   hapivt->SetYTitle("DBS API");
   hapivt->GetYaxis()->SetTitleOffset(2.);
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
      T->Draw("Api:Time>>apivt",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("apivt.png");
      //pdvt.Close();
/*Instance vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hinstvt = new TH2I("instvt",
                "DBS Instance vs. Time",
                numbins,begin,end,50,0,50); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   //hapivt->SetBit(TH1::kCanRebin);

   // Label X Axis
   hinstvt->GetXaxis()->CenterTitle();
   hinstvt->SetXTitle("Time of accesses (UTC)");
   hinstvt->GetYaxis()->CenterTitle();
   hinstvt->SetYTitle("DBS Instance");
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

/*VERSION vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hvsnvt = new TH2I("vsnvt",
                "Client Version vs. Time",
                numbins,begin,end,10,0,10); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hvsnvt->SetBit(TH1::kCanRebin);

   // Label X Axis
   hvsnvt->GetXaxis()->CenterTitle();
   hvsnvt->SetXTitle("Time of accesses (UTC)");
   hvsnvt->GetYaxis()->CenterTitle();
   hvsnvt->SetYTitle("DBS Version");
   hvsnvt->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hvsnvt->GetXaxis()->SetTimeFormat(time_label);
   hvsnvt->GetXaxis()->SetLabelSize(0.025);
   hvsnvt->GetXaxis()->SetTimeDisplay(1);
   hvsnvt->GetYaxis()->SetLabelSize(0.025);

   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //hapivt->GetXaxis()->SetNdivisions(707);

      // Draw Scatterplot "dvt"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      //hvsnvt->LabelsDeflate("Y");
      T->Draw("Version:Time>>vsnvt",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("vsnvt.png");
      //pdvt.Close();

      
/*API vs. SIZE*/
   // Create Histogram "sizeschema"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH2I *hapivs = new TH2I("apisize",
                "API vs. Size",
                100,0,40000,30,0,30);
   //pad1->SetxLog(1);
   hapivt->GetXaxis()->CenterTitle();
   hapivs->SetXTitle("Size (bytes)");
   hapivs->SetYTitle("DBS API");   
   hapivs->GetYaxis()->CenterTitle();
   hapivs->GetYaxis()->SetTitleOffset(2.);
   hapivs->GetXaxis()->SetLabelSize(0.025);
   hapivs->GetYaxis()->SetLabelSize(0.025);
   gStyle->SetOptStat("euo");
   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hapivs->SetBit(TH2::kCanRebin);
      // Histogram "tag"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hapivs->LabelsDeflate();
   

      // Draw histogram "tag" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf 
      // figure 3-10 on page numbered 33, pdf page 11
      T->Draw("Api:Size>>apisize",intime);
      
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("apisize.png");
      //hapivs->Close();

/*SIZE vs. DURATION*/
   // Create Scatterplot "svds"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *ssvds = new TH2I("svds",
                 "Size vs. Duration",
                 200,0,30000,400,0,150000);

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
      T->Draw("Size:Duration>>svds", Duration_gt_0 && intime); // from DB
      //T->Draw("Size:Duration>>+svds");  // from memory
      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("svds.png");
      //gPad->SetLogx(0);   gPad->SetLogy(0);
      //psvds.Close();


/*DOMAINNAME*/
   // Create Histogram "domain"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *hdomain = new TH1I("domain",
                   "Accesses by Domain",
                   30,0,30);
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
   hdomain->SetBit(TH1::kCanRebin);

      // Histogram "domain"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hdomain->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      // figure 3-10 on page numbered 33, pdf page 11
      //T->Draw("DomainName>>domain","DomainName == \"Frontier\"","hbar2");
      gPad->SetLogx(1);

      T->Draw("DomainName>>domain",intime,"hbar2");


   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("domainname.png");
      gPad->SetLogx(0);


/*API*/
   // Create Histogram "api"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *hapi = new TH1I("api",
                   "Accesses by API",
                   30,0,30);
   hapi->SetStats(1);
   hapi->GetXaxis()->CenterTitle();
   hapi->SetXTitle("DBS API");
   hapi->GetYaxis()->CenterTitle();
   hapi->SetYTitle("Number of Requests");
   hapi->GetXaxis()->SetTitleOffset(2.);
   hapi->GetXaxis()->SetLabelSize(0.025);
   hapi->GetYaxis()->SetLabelSize(0.025);
   // Allow number of bins to change, see
   // http://root.cern.ch/root/html/TH1.html
   hapi->SetBit(TH1::kCanRebin);

      // Histogram "api"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hapi->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      gPad->SetLogx(1);
      T->Draw("Api>>api",intime,"hbar2");


   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("api.png");
      gPad->SetLogx(0);


/*VERSION*/
   // Create Histogram "version"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *hversion = new TH1I("version",
                   "Accesses by DBS client version",
                   20,0,20);
   hversion->SetStats(1);
   hversion->GetXaxis()->CenterTitle();
   hversion->SetXTitle("Client Version");
   hversion->GetYaxis()->CenterTitle();
   hversion->SetYTitle("Number of Requests");
   hversion->GetXaxis()->SetTitleOffset(2.0);
   hversion->GetXaxis()->SetLabelSize(0.025);
   hversion->GetYaxis()->SetLabelSize(0.025);
   // Allow number of bins to change, see
   // http://root.cern.ch/root/html/TH1.html
   hversion->SetBit(TH1::kCanRebin);

      // Histogram "domain"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      hversion->LabelsDeflate();


      // Draw histogram "domain" as a Horizontal Bar Chart, see
      // ftp://root.cern.ch/root/doc/chapter3.pdf
      // figure 3-10 on page numbered 33, pdf page 11
      //T->Draw("DomainName>>domain","DomainName == \"Frontier\"","hbar2");
      gPad->SetLogx(1);
      T->Draw("Version>>version",intime,"hbar2");

   // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      //pdomain.Close();
      pad1->Print("version.png"); 
      gPad->SetLogx(0);



/*VERSION vs INSTANCE*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hvsnvd = new TH2I("vsnvd",
                "DBS Instance vs. Client Version",
                20,0,20,50,0,50); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hvsnvd->SetBit(TH2::kCanRebin);

   // Label X Axis
   hvsnvd->GetXaxis()->CenterTitle();
   hvsnvd->SetXTitle("Version");
   hvsnvd->GetYaxis()->CenterTitle();
   hvsnvd->SetYTitle("Instance");
   hvsnvd->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   //hvsnvd->GetXaxis()->SetTimeFormat(time_label);
   hvsnvd->GetXaxis()->SetLabelSize(0.025);
   hvsnvd->GetXaxis()->SetTitleOffset(1.0);
   //hvsnvd->GetXaxis()->SetTimeDisplay(1);
   hvsnvd->GetYaxis()->SetLabelSize(0.025);
   //hvsnvd->GetZaxis()->CenterTitle();
   //hvsnvd->SetZTitle("DBS Instance");
   //hvsnvd->GetZaxis()->SetTitleOffset(2.);
   //hvsnvd->GetZaxis()->SetLabelSize(0.025);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //hapivt->GetXaxis()->SetNdivisions(707);

      // Draw Scatterplot "dvt"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      //hvsnvt->LabelsDeflate("Y");
      T->Draw("Instance:Version>>vsnvd",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("vsnvd.png");
      //hvsnvdvt->Close();


/*API vs INSTANCE*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hapivi = new TH2I("apivi",
                "DBS Instance vs. DBS API",
                30,0,30,50,0,50); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hapivi->SetBit(TH2::kCanRebin);

   // Label X Axis
   hapivi->GetXaxis()->CenterTitle();
   hapivi->SetXTitle("API");
   hapivi->GetYaxis()->CenterTitle();
   hapivi->SetYTitle("Instance");
   hapivi->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   //hapivd->GetXaxis()->SetTimeFormat(time_label);
   hapivi->GetXaxis()->SetLabelSize(0.025);
   hapivi->GetXaxis()->SetTitleOffset(1.0);
   //hapivd->GetXaxis()->SetTimeDisplay(1.0);
   hapivi->GetYaxis()->SetLabelSize(0.025);
      T->Draw("Instance:Api>>apivi",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("apivi.png");
      //hvsnvdvt->Close();


/*Domain vs INSTANCE*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hdomvi = new TH2I("domvi",
                "DBS Instance vs. Domain",
                40,0,40,50,0,50); 

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
     T->Draw("Instance:DomainName>>domvi",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("domvi.png");
      //hvsnvdvt->Close();

/*API vs DOMAIN*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH2I *hapivd = new TH2I("apivd",
                "Domain vs. DBS API",
                30,0,30,40,0,40); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hapivd->SetBit(TH2::kCanRebin);

   // Label X Axis
   hapivd->GetXaxis()->CenterTitle();
   hapivd->SetXTitle("API");
   hapivd->GetYaxis()->CenterTitle();
   hapivd->SetYTitle("Domain");
   hapivd->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   //hapivd->GetXaxis()->SetTimeFormat(time_label);
   hapivd->GetXaxis()->SetLabelSize(0.025);
   hapivd->GetXaxis()->SetTitleOffset(1.0);
   //hapivd->GetXaxis()->SetTimeDisplay(1.0);
   hapivd->GetYaxis()->SetLabelSize(0.025);
      //hvsnvt->LabelsDeflate("Y");
      T->Draw("DomainName:Api>>apivd",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("apivd.png");
      //hvsnvdvt->Close();



/*INSTANCE*/ 
   // Create Histogram "instance"
   // See http://root.cern.ch/root/html/examples/hsum.C.html
   // and http://root.cern.ch/root/html/examples/hlabels1.C.html
   TH1I *hinstance = new TH1I("instance",
                     "Accesses by DBS instance",50,0,50);
   hinstance->SetStats(1);
   hinstance->SetYTitle("Number of Requests");
   hinstance->GetXaxis()->CenterTitle();
   hinstance->GetXaxis()->SetTitleOffset(2.);
   hinstance->SetXTitle("DBS Instance");
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
      //pad1->cd(1); gPad->SetGrid(); 
      gPad->SetLogx(1); 

      //gPad->SetFrameFillColor(33);
      T->Draw("Instance>>instance",intime && "Instance !=\"malformed\"","hbar4"); 

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("instance.png");
      //pinstance.Close();
      gPad->SetLogx(0);
  



/*VERSION vs INSTANCE vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH3I *hvsnvdvt = new TH3I("vsnvdvt",
                "Client Version vs. DBS Instance vs. Time",
                numbins,begin,end,30,0,30,50,0,50); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hvsnvdvt->SetBit(TH3::kCanRebin);

   // Label X Axis
   hvsnvdvt->GetXaxis()->CenterTitle();
   hvsnvdvt->SetXTitle("Time of accesses (UTC)");
   hvsnvdvt->GetYaxis()->CenterTitle();
   hvsnvdvt->SetYTitle("DBS Version");
   hvsnvdvt->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hvsnvdvt->GetXaxis()->SetTimeFormat(time_label);
   hvsnvdvt->GetXaxis()->SetLabelSize(0.025);
   hvsnvdvt->GetXaxis()->SetTitleOffset(2.);
   hvsnvdvt->GetXaxis()->SetTimeDisplay(1);
   hvsnvdvt->GetYaxis()->SetLabelSize(0.025);
   hvsnvdvt->GetZaxis()->CenterTitle();
   hvsnvdvt->SetZTitle("DBS Instance");
   hvsnvdvt->GetZaxis()->SetTitleOffset(2.);
   hvsnvdvt->GetZaxis()->SetLabelSize(0.025);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //hapivt->GetXaxis()->SetNdivisions(707);

      // Draw Scatterplot "dvt"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      //hvsnvt->LabelsDeflate("Y");
      T->Draw("Instance:Version:Time>>vsnvdvt",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("vsnvdvt.png");
      //hvsnvdvt->Close();


/*API vs INSTANCE vs TIME*/
   // Create Scatterplot "dvt"
   // See http://root.cern.ch/root/html/examples/DynamicSlice.C.html
   TH3I *hapivdvt = new TH3I("apivdvt",
                "DBS API vs. DBS Instance vs. Time",
                numbins,begin,end,30,0,30,50,0,50); 

   // Allow number of bins to change, see 
   // http://root.cern.ch/root/html/TH1.html
   hapivdvt->SetBit(TH3::kCanRebin);

   // Label X Axis
   hapivdvt->GetXaxis()->CenterTitle();
   hapivdvt->SetXTitle("Time of accesses (UTC)");
   hapivdvt->GetYaxis()->CenterTitle();
   hapivdvt->SetYTitle("DBS API");
   hapivdvt->GetYaxis()->SetTitleOffset(2.);
   // Set X axis to display in time units
   // See http://root.cern.ch/root/html/examples/timeonaxis.C.html
   hapivdvt->GetXaxis()->SetTimeFormat(time_label);
   hapivdvt->GetXaxis()->SetLabelSize(0.025);
   hapivdvt->GetXaxis()->SetTitleOffset(2.);
   hapivdvt->GetXaxis()->SetTimeDisplay(1);
   hapivdvt->GetYaxis()->SetLabelSize(0.025);
   hapivdvt->GetZaxis()->CenterTitle();
   hapivdvt->SetZTitle("DBS Instance");
   hapivdvt->GetZaxis()->SetTitleOffset(2.);
   hapivdvt->GetZaxis()->SetLabelSize(0.025);
   // Set axis divisions
   // See http://root.cern.ch/root/html/TStyle.html#TStyle:SetNdivisions 
   //hapivt->GetXaxis()->SetNdivisions(707);

      // Draw Scatterplot "dvt"
      // Trim number of bins to match number of labels, see
      // http://root.cern.ch/root/html/TH1.html
      //hvsnvt->LabelsDeflate("Y");
      T->Draw("Instance:Api:Time>>apivdvt",intime);
   

      // Need Modified and Update to get pad drawn in PostScript file
      pad1->Modified();
      pad1->Update();
      pad1->Print("apivdvt.png");
      //hvsnvdvt->Close();




}
