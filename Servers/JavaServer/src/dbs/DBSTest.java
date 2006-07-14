
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
			//while(true) {
				//api.getDatasetInfo("jj");
				//System.out.println("\n\nPrimary Dataset");
				PrintWriter out = new PrintWriter(System.out);
				api.listPrimaryDatasets(out, "*");
				
				System.out.println("\n\nProcessed Dataset");
				api.listProcessedDatasets(out, "/*/*/*");
				System.out.println("\n\nParameter Sets");
				api.listParameterSets(out, "*");
				System.out.println("\n\nApplications");
				api.listApplications(out, "*");
				System.out.println("\n\nApplications Configs");
				api.listApplicationConfigs(out, "*");
				System.out.println("\n\nDataset Contents");
				api.getDatasetContents(out, "/PreProdR2Mu10GeV/DIGI/GEN-SIM-DIGI");
				System.out.println("\n\nDataset Files");
				api.getDatasetFiles(out, "/PreProdR2Mu10GeV/DIGI/GEN-SIM-DIGI");
				
				//System.out.println();
				//out.flush();
			//}

		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
