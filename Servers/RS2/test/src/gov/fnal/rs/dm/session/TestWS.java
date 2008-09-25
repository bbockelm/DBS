package gov.fnal.rs.dm.session;
import java.util.List;
public class TestWS {
 
 	static RSSessionEJBBeanService service = new RSSessionEJBBeanService ();
 	
 	public static void main(String[] args) throws java.lang.Exception {
 		RSSessionEJBWebService port = service.getRSSessionEJBBeanPort();
 		List<Registration> rList = port.queryRegistrationFindAll();
		for (Registration rIn: rList) {
			System.out.println("_________________________________________________");
			String alias = rIn.getAlias();
			String url = rIn.getUrl();
			String accName = rIn.getAccountName();
			String critical = rIn.getCritical();
			String dbName = rIn.getDbName();
			Integer dbPort = rIn.getDbPort();
			String nodeName = rIn.getNodeName();
			String physicalLocation = rIn.getPhysicalLocation();
			String schemaVersion = rIn.getSchemaVersion();
			String serverVersion = rIn.getServerVersion();
			System.out.println("alias " + alias);
			System.out.println("critical " + critical);
			System.out.println("dbName " + dbName);
			System.out.println("dbPort " + dbPort);
			System.out.println("nodeName " + nodeName);
			System.out.println("physicalLocation " + physicalLocation);
			System.out.println("schemaVersion " + schemaVersion);
			System.out.println("serverVersion " + serverVersion);
			System.out.println("url " + url);
			System.out.println("_________________________________________________");
		}
										      

 	}
 
 }

