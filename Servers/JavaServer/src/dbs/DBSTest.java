
/**
 * @author sekhri
 *
 */
package dbs;
//import dbs.DBSServlet;
import java.io.PrintWriter;
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

                        /* 
                        //INSERT TEST
                        String xml_string = "<?xml version='1.0' standalone='yes'?>" +
                                            "<dbs>"+
                                            "<primary-dataset id='1' annotation='First Primary in new era' primary_name='PrimaryDS_ANZAR_01' trigger_path_description='null' mc_channel_description='null' mc_production='null' mc_decay_chain='null' other_description='null' start_date='2006-10-05' end_date='2007-10-05' file_type='null' created_by='null' creation_date='null' last_modification_by='null' last_modified_by='null'/>"+
                                            "</dbs>";

                                            api.insertPrimaryDatasets(xml_string);
*/

			//while(true) {
				//api.getDatasetInfo("jj");
				//System.out.println("\n\nPrimary Dataset");
				PrintWriter out = new PrintWriter(System.out);
				api.listPrimaryDatasets(out, "*");
				
				//System.out.println("\n\nProcessed Dataset");
				api.listProcessedDatasets(out, "/*/*/*", "/*/*/*");
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

		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
