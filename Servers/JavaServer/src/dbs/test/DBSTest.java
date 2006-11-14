/**
 * @author sekhri
 $Revision: 1.14 $"
 $Id: DBSTest.java,v 1.14 2006/11/14 19:14:36 sekhri Exp $"
 *
 */

package dbs.test;
import dbs.api.DBSApi;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.sql.SQLException;
import dbs.DBSException;
import xml.XMLException;
import dbs.api.DBSApi;


public class DBSTest {

        public DBSApi api;
        public PrintWriter out;
	Hashtable user = new Hashtable();
						
        public DBSTest() {
           try { 
               api = new DBSApi();
		user.put("user_dn", "ANZARDN");
               out = new PrintWriter(System.out);
 
           } catch(Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }
        }

        public void insertPrimary() throws Exception {
            /**
            Insert Primary Dataset Test 
            */
		String xmlString = "<?xml version='1.0' standalone='yes'?>" +
					"<dbs>" +
					"<primary-dataset annotation='aaaa' primary_name='PrimaryDS_VIJAY_01' start_date='NOV' end_date='DEC' trigger_path_description='anyTD' mc_channel_description='MCDesc' mc_production='MCProd' mc_decay_chain='DC' other_description='OD' type='PDS'>" +
					"</primary-dataset>" +
					"</dbs>";
					    
		api.insertPrimaryDataset(xmlString, user);
        }

        public void insertProcessedDataset() throws Exception {
           /**
           INSERT ProcessedDataset test
           */
		String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<processed-dataset primary_datatset_name='PrimaryDS_ANZAR_01' processed_datatset_name='anzar-procds-218' open_for_writing='y' physics_group_name='AnyName' physics_group_convener='ANZARDN' status='NEW'>" +
					"<data_tier name='HIT'/>" +
					"<data_tier name='DIGI'/>" +
					"<data_tier name='GEN'/>" +
					"<parent path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05'/>" +
					"<parent path='/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-06'/>" +
					"<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2'/>" +
					"<algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2' ps_name='DUMMY_ps_name2'/>" +
					"<run run_number='52'/>" +
					"<run run_number='3'/>" +
				"</processed-dataset>" +
				"</dbs>";

		//api.insertProcessedDataset(xmlString, user); 
		api.insertProcessedDataset(" ", user); 

        } 

        public void insertFiles() throws Exception {
		String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<processed_datatset path='/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-07' block_name='/pri/proc#0001-0002-0031-0065'>" +
				"<file lfn='LFN40' checksum='CHKSUM' number_of_events='200' size='200' file_status='MERGED' type= 'EVD' validation_status='' queryable_meta_data=''>" +
					"<lumi_section lumi_section_number='200' run_number='2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='201' run_number='2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='202' run_number='2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<data_tier name='HIT'/>" +
					"<data_tier name='DIGI'/>" +
					"<data_tier name='GEN'/>" +
					"<parent lfn='ParentLFN1'/>" +
					"<parent lfn='ParentLFN2'/>" +
					"<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2'/>" +
					"<algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2' ps_name='DUMMY_ps_name2'/>" +
				"</file>" +
				"<file lfn='LFN39' checksum='CHKSUM2' number_of_events='300' size='2002' file_status='MERGED' type= 'EVD' validation_status='' queryable_meta_data=''>" +
					"<lumi_section lumi_section_number='1006' run_number='3' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='1017' run_number='3' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='1028' run_number='3' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<data_tier name='HIT'/>" +
					"<data_tier name='DIGI'/>" +
					"<data_tier name='GEN'/>" +
					"<parent lfn='ParentLFN13'/>" +
					"<parent lfn='ParentLFN22'/>" +
					"<algorithm app_version='MyVersion12' app_family_name='MyFamily12' app_executable_name='MyExe12' ps_name='DUMMY_ps_name2'/>" +
					"<algorithm app_version='MyVersion22' app_family_name='MyFamily22' app_executable_name='MyExe22' ps_name='DUMMY_ps_name2'/>" +
				"</file>" +
				"</processed_datatset>" +
				"</dbs>";
		api.insertFiles(xmlString, user); 
	}

        public void insertBlock() throws Exception {
           /**
           INSERT ProcessedDataset test
           */

		   String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				//"<block path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05' name='/sdf/sf#8487' size='2' number_of_files='32' open_for_writing='1'>" +
				"<block path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05' open_for_writing='1'>" +
				"</block>" +
				"</dbs>";

		api.insertBlock(xmlString, user);
        }

/*        public void closeBlock() {
           /**
           INSERT ProcessedDataset test
           */
/*
                 String block_name = "/pri/proc#0001-0002-0031-0065";

                 api.closeBlock(block_name);

        }
*/
        public void insertRun() throws Exception {
           /**
           INSERT Run
           */

		   String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				//"<run path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05' run_number='52' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov' end_of_run='dec'>" +
				"<run run_number='52' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov' end_of_run='dec'>" +
				"</run>" +
				"</dbs>";

		api.insertRun(xmlString, user);
		//api.insertRun(null, user);
        }

/*        public void insertLumiSection() throws Exception {
           /**
           INSERT Lumi Section test
           */
/*
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
        }
*/

	public void insertTier() throws Exception {
		api.insertTier("MY-TIER", user);
        }
	public void insertParentInPD() throws Exception {
		api.insertParentInPD("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", "/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-06",  user);
        }
	public void insertRunInPD() throws Exception {
		api.insertRunInPD("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", "52",  user);
        }
	public void insertTierInPD() throws Exception {
		api.insertTierInPD("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", "MY-TIER",  user);
        }
	public void insertAlgoInPD() throws Exception {
		String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
					"<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2'/>" +
				"</dbs>";

		api.insertAlgoInPD("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", xmlString,  user);
        }

	public void insertTierInFile() throws Exception {
		api.insertTierInFile("LFN40", "MY-TIER",  user);
        }
	public void insertParentInFile() throws Exception {
		api.insertParentInFile("LFN40", "LFN39",  user);
        }
	public void insertLumiInFile() throws Exception {
		api.insertLumiInFile("LFN40", "1028",  user);
        }
	public void insertAlgoInFile() throws Exception {
		String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
					"<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2'/>" +
				"</dbs>";
		api.insertAlgoInFile("LFN40", xmlString,  user);
        }

	public void insertAlgorithm() throws Exception {
		String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
					"<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps1_name2' ps_hash='DUMMY_HASH'/>" +
				"</dbs>";
		api.insertAlgorithm(xmlString,  user);
        }

        public void runListAPIs() throws Exception {
        /**
          Run all list API calls
        */
		System.out.println("\n\nPrimary Datasets");
		api.listPrimaryDatasets(out, "*");
		System.out.println("\n\nProcessed Datasets");
		api.listProcessedDatasets(out, "*");
		System.out.println("\n\nAlgorithms");
		api.listAlgorithms(out, "*");
		System.out.println("\n\nRuns");
		api.listRuns(out, "/PrimaryDS_ANZAR_01/HIT/anzar-procds-117");
		System.out.println("\n\nTiers");
		api.listTiers(out, "/PrimaryDS_ANZAR_01/HIT/anzar-procds-117");
		System.out.println("\n\nBlocks");
		api.listBlocks(out, "/PrimaryDS_ANZAR_01/HIT/anzar-procds-117");
		System.out.println("\n\nFiles");
		api.listFiles(out, "/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-07", null, "*");
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
        }

	static public void main(String[] args) {
		//DBSServlet serv = new DBSServlet();
		try {
			DBSTest test= new DBSTest();

			test.runListAPIs(); 
			//test.insertPrimary();
			//test.insertProcessedDataset(); 
			//test.insertFiles(); 
			//test.insertBlock();
			//test.insertLumiSection();
			//test.insertRun();
			//test.closeBlock();
			//test.insertTier();
			//test.insertTierInPD();
			//test.insertParentInPD();
			//test.insertAlgoInPD();
			//test.insertRunInPD();
			//test.insertTierInFile();
			//test.insertParentInFile();
			//test.insertAlgoInFile();
			//test.insertLumiInFile();
			//test.insertAlgorithm();
 
		} catch (DBSException dbsEx) {
			System.out.println("message: " + dbsEx.getMessage() + " code: " + dbsEx.getCode() + " detail: " + dbsEx.getDetail());
			dbsEx.printStackTrace();
		} catch (XMLException xmlEx) {
			System.out.println("message: " + xmlEx.getMessage() + " code: " + xmlEx.getCode() + " detail: " + xmlEx.getDetail());
			xmlEx.printStackTrace();
		} catch (SQLException sqlEx) {
			System.out.println("message: Database Exception code: 402 detail : " + sqlEx.getMessage());
			sqlEx.printStackTrace();
		} catch(Exception e) {
			System.out.println("message: " +e.getMessage());
			e.printStackTrace();
		}

	}
}

