
/**
 * @author sekhri
 *
 */
package dbs.test;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Hashtable;
import dbs.DBSException;
import dbs.api.DBSApi;

public class DBSTest {

	/**
	 * 
	 */
	public DBSTest() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//DBSServlet serv = new DBSServlet();
		try {
			DBSApi api = new DBSApi();

                         
                        //INSERT TEST
	/*		String xml_string = "<?xml version='1.0' standalone='yes'?>" +
						"<dbs>"+
							"<primary-dataset id='1' annotation='First Primary in new era' primary_name='PrimaryDS_ANZAR_01' trigger_path_description='null' mc_channel_description='null' mc_production='null' mc_decay_chain='null' other_description='null' start_date='2006-10-05' end_date='2007-10-05' file_type='null' created_by='null' creation_date='null' last_modification_by='null' last_modified_by='null'/>"+
						"</dbs>";

			api.insertPrimaryDataset(xml_string, null);
			*/
	   		String xml_string = "<?xml version='1.0' standalone='yes'?>" +
						"<dbs>"+
							"<algorithm app_version='MyVersion22a' app_family_name='MyFamily22a' app_executable_name='MyExe22a' ps_hash='DUMMY_ps_hash1' ps_name='DUMMY_ps_name2' ps_version='DUMMY_ps_version' ps_type='DUMMY_ps_type' ps_annotation='DUMMY_ps_annotation' ps_content='DUMMY_ps_content'  created_by='null' last_modification_by='null'  user_dn = 'ANZARDN'/>"+
						"</dbs>";
			Hashtable user = new Hashtable();
			user.put("user_dn", "ANZARDN");

			//api.insertApplication(xml_string, user);

			String xmlString = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<file path='/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-07' block_name='/pri/proc#0001-0002-0031-0065' lfn='LFN33' checksum='CHKSUM' number_of_events='200' size='200' file_status='MERGED' type= 'EVD' validation_status='' queryable_meta_data=''>" +
					"<lumi_section lumi_section_number='200' run_number='2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='201' run_number='2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='202' run_number='2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<data_tier name='HIT'/>" +
					"<data_tier name='DIGI'/>" +
					"<data_tier name='GEN'/>" +
					"<parent lfn='ParentLFN1'/>" +
					"<parent lfn='ParentLFN2'/>" +
					"<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1'/>" +
					"<algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2'/>" +
				"</file>" +
				"<file path='/PrimaryDS_ANZAR_01/RECO/anzar-procds-01' block_name='/pri/proc#0001-0002-0031-0065' lfn='LFN34' checksum='CHKSUM2' number_of_events='300' size='2002' file_status='MERGED' type= 'EVD' validation_status='' queryable_meta_data=''>" +
					"<lumi_section lumi_section_number='1006' run_number='3' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='1017' run_number='3' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<lumi_section lumi_section_number='1028' run_number='3' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>" +
					"<data_tier name='HIT'/>" +
					"<data_tier name='DIGI'/>" +
					"<data_tier name='GEN'/>" +
					"<parent lfn='ParentLFN13'/>" +
					"<parent lfn='ParentLFN22'/>" +
					"<algorithm app_version='MyVersion12' app_family_name='MyFamily12' app_executable_name='MyExe12'/>" +
					"<algorithm app_version='MyVersion22' app_family_name='MyFamily22' app_executable_name='MyExe22'/>" +
				"</file>" +

				"</dbs>";
			
			String xmlStringP = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<file path='/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-07' block_name='/pri/proc#0001-0002-0031-0065' lfn='ParentLFN1' checksum='CHKSUM' number_of_events='200' size='200' file_status='MERGED' type= 'EVD' validation_status='' queryable_meta_data=''>" +
				"</file>" +
				"<file path='/PrimaryDS_ANZAR_01/RECO/anzar-procds-01' block_name='/pri/proc#0001-0002-0031-0065' lfn='ParentLFN2' checksum='CHKSUM2' number_of_events='300' size='2002' file_status='MERGED' type= 'EVD' validation_status='' queryable_meta_data=''>" +
				"</file>" +

				"</dbs>";

			//api.insertFiles(xmlStringP, user);
			//api.insertFiles(xmlString, user);

			String xmlStringPS = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<processed-dataset primary_datatset_name='PrimaryDS_ANZAR_01' processed_datatset_name='anzar-procds-117' open_for_writing='y' physics_group_name='AnyName' physics_group_convener='ANZARDN' status='NEW'>" +
					"<data_tier name='HIT'/>" +
					"<data_tier name='DIGI'/>" +
					"<data_tier name='GEN'/>" +
					"<parent path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05'/>" +
					"<parent path='/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-06'/>" +
					"<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1'/>" +
					"<algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2'/>" +
				"</processed-dataset>" +
				"</dbs>";

			//api.insertProcessedDataset(xmlStringPS, user);
			String xmlStringRun = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<run path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05' run_number='50' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov' end_of_run='dec'>" +
				"</run>" +
				"</dbs>";

			//api.insertRun(xmlStringRun, user);

			String xmlStringBlock = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<block path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05' name='/sdf/sf#8487' size='2' number_of_files='32' open_for_writing='1'>" +
				"</block>" +
				"</dbs>";

			//api.insertBlock(xmlStringBlock, user);

			String xmlStringPriDS = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<primary-dataset annotation='aaaa' primary_name='PrimaryDS_VIJAY_01' start_date='NOV' end_date='DEC' trigger_path_description='anyTD' mc_channel_description='MCDesc' mc_production='MCProd' mc_decay_chain='DC' other_description='OD' type='PDS'>" +
				"</primary-dataset>" +
				"</dbs>";

			api.insertPrimaryDataset(xmlStringPriDS, user);

			//while(true) {
				//api.getDatasetInfo("jj");
				//System.out.println("\n\nPrimary Datasets");
				PrintWriter out = new PrintWriter(System.out);
				//api.listPrimaryDatasets(out, "*");
				
				//System.out.println("\n\nProcessed Datasets");
				//api.listProcessedDatasets(out, "/*/*/*/*/*/*");
				//System.out.println("\n\nProcessed Datasets");
				//api.listApplications(out, "/*/*/*");
				/*System.out.println("\n\nRuns");
				api.listRuns(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01");
				System.out.println("\n\nTiers");
				api.listTiers(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01");
				System.out.println("\n\nBlocks");
				api.listBlocks(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01");
				System.out.println("\n\nFiles");
				api.listFiles(out, "/PrimaryDS_ANZAR_01/No-Reco/anzar-procds-01", null, "*");
				*/
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
			//}
		} catch (DBSException dbsEx) {
			System.out.println("message: " + dbsEx.getMessage() + " code: " + dbsEx.getCode() + " detail: " + dbsEx.getDetail());
			dbsEx.printStackTrace();
		} catch (SQLException sqlEx) {
			System.out.println("message: Connection Refused code: 402 detail : " + sqlEx.getMessage());
			sqlEx.printStackTrace();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
