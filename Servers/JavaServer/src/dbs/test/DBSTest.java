/**
 * @author sekhri
 $Revision: 1.6 $"
 $Id: DBSTest.java,v 1.6 2006/10/31 22:20:41 afaq Exp $"
 *
 */

package dbs.test;
import dbs.api.DBSApi;
import java.io.PrintWriter;
import java.util.Hashtable;


public class DBSTest {

        public DBSApi api;
        public PrintWriter out;

        public DBSTest() {
           try { 
               api = new DBSApi();
               out = new PrintWriter(System.out);
 
           } catch(Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }
        }

        public void insertPrimary() {
            /**
            Insert Primary Dataset Test 
            */
            try {
                 String xml_string= "<?xml version='1.0' standalone='yes'?>" +
                                    "<dbs> "+
                                    "<primary-dataset id='1' annotation='First Primary in new era' "+
                                    "primary_name='PrimaryDS_ANZAR_22' trigger_path_description='null' "+
                                    " mc_channel_description='null' mc_production='null' "+
                                    " mc_decay_chain='null' "+
                                    "other_description='null' start_date='2006-10-05' end_date='2007-10-05' "+
                                    "file_type='null' created_by='null' creation_date='null' "+
                                    "last_modification_by='null' last_modified_by='null'i "+
                                    "/> </dbs>";

                api.insertPrimaryDataset(xml_string, null);

           } catch(Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }

        }

        public void insertProcessedDataset() {
           /**
           INSERT ProcessedDataset test
           */
           try {
                 String xml_string= "<?xml version='1.0' standalone='yes'?>"+
                                    "<!-- DBS Version 1 --> <dbs> "+
                                    " <processed-dataset processed_name='anzar-procds-07' "+
                                    " primary_name='PrimaryDS_ANZAR_01'"+
                                    " app_version='cmssw_1_0_0_pre1' "+ 
                                    " app_family_name='CMSSW' " +
                                    " app_executable_name='CMSSW' "+
                                    " tier_name='test-tier-02' "+
                                    " /> " +
                                    " </dbs>";

                 api.insertProcessedDataset(xml_string); 

           } catch(Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }
        } 

        public void insertBlock() {
           /**
           INSERT ProcessedDataset test
           */

           try {
                 String xml_string= "<?xml version='1.0' standalone='yes'?>" +
                                    "<!-- DBS Version 1 -->" +
                                    "<dbs>" +
                                    "<block id='1' block_name='0001-0002-0031-0065' block_size='20' "+
                                    " number_of_files='2' " +
                                    " open_for_writing='y' creation_date='2006-10-13 14:57:19.0' " +
                                    " last_modification_date='2006-10-13 14:57:19.0' " +
                                    " created_by='null' last_modified_by='null' " +
                                    " processed_name='anzar-procds-07' "+
                                    " primary_name='PrimaryDS_ANZAR_01' "+ 
                                    " />" + 
                                    "</dbs>";

                 api.insertBlock(xml_string);
           } catch(Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }
        }


        public void insertRun() {
           /**
           INSERT Run
           */

           try {
                 String xml_string= "<?xml version='1.0' standalone='yes'?>"+
                                    "<!-- DBS Version 1 -->"+
                                    "<dbs>"+
                                    "<run run_number='5' "+
                                    "primary_name='PrimaryDS_ANZAR_01' "+
                                    "processed_name='anzar-procds-07' "+
                                    "number_of_events='100' number_of_lumi_sections='2' "+
                                    "total_luminosity='233' strore_number='2' "+
                                    "start_of_run='NOW' end_of_run='NEVER' "+
                                    " />" +
                                    "</dbs>";

                 api.insertRun(xml_string);
           } catch(Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }
        }



        public void insertLumiSection() {
           /**
           INSERT Lumi Section test
           */

           try {
                 String xml_string= "<?xml version='1.0' standalone='yes'?>" +
                                    "<!-- DBS Version 1 -->" +
                                    "<dbs>" +
                                    "<lumi_section lumi_number='00010002' run_number='2' "+
                                    " start_event='20' " +
                                    " end_event='200' start_time='2006-10-13 14:57:19.0' " +
                                    " end_time='2006-10-13 14:57:19.0' " +
                                    " />" +
                                    "</dbs>";

                 api.insertLumiSection(xml_string);
           } catch(Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }
        }

        public void runListAPIs() {
        /**
          Run all list API calls
        */
            try {
                    //api.listPrimaryDatasets(out, "*");
                    //System.out.println("\n\nProcessed Datasets");
                    //api.listProcessedDatasets(out, "/*/*/*/*/*/*");
                    //System.out.println("\n\nProcessed Datasets");
                    //api.listApplications(out, "/*/*/*");
                    System.out.println("\n\nRuns");
                    api.listRuns(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01");
                    //System.out.println("\n\nTiers");
                    //api.listTiers(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01");
                    //System.out.println("\n\nBlocks");
                    //api.listBlocks(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01");
                    //System.out.println("\n\nFiles");
                    //api.listFiles(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01", null, "*");
                    //api.listFiles(out, null, "Block_001", "*");
                    //api.listFiles(out, null, null, "*");
                    /*System.out.println("\n\nParameter Sets");
                    api.listParameterSets(out, "*");
                    System.out.println("\n\nApplications");
                    api.listApplications(out, "*");
                    System.out.println("\n\nApplications Configs");
                    api.listApplicationConfigs(out, "*");
                    System.out.println("\n\nDataset Contents");
                    api.getDatasetContents(out, "/PreProdR2Mu10GeV/DIGI/GEN-SIM-DIGI");
                    System.out.println("\n\nDataset Files");
                    api.getDatasetFiles(out, "/PreProdR2Mu10GeV/DIGI/GEN-SIM-DIGI");
                                */
                    out.flush();
                    System.out.println();
                    } catch(Exception e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                    }
        }

        static public void main(String[] args) {
		//DBSServlet serv = new DBSServlet();
                try {
                     DBSTest test= new DBSTest();

                     //test.insertPrimary();
                     //test.insertProcessedDataset(); 
                     //test.insertBlock();
                     //test.insertLumiSection();
                     test.insertRun();
 
                     //test.runListAPIs(); 

		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

