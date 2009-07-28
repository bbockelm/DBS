
import java.net.URL;
import java.io.RandomAccessFile;
import org.glite.security.voms.service.User;
import org.glite.security.voms.service.admin.VOMSAdmin;
import org.glite.security.voms.service.admin.VOMSAdminServiceLocator;
import java.lang.Exception;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class VO2GridMap {
	

	private String defaultURL = "https://voms.cern.ch:8443/voms/cms/services/VOMSAdmin";
	//AA-07/28/2009 : No default role is required
	//private String defaultGroup = "/cms/uscms/Role=production";
	private String map = "cmsdbs";

	public VO2GridMap (){}

	public void makeGridMap(String fileName, String url, String groups) {
		try{	
			if (url == null) url = defaultURL;
			if (url.length() == 0) url = defaultURL;
			if (groups == null) { 
					System.out.println("No VOMS group specified");
					throw new Exception("No VOMS group specified");
			}
			System.out.println("URL is "+ url);

			VOMSAdmin stub = makeStub(url);
			ArrayList<String> allDN = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(groups, ",");
                        while (st.hasMoreTokens()) {
				String group=st.nextToken();
                                System.out.println("Now Procssing VOMS Group :"+group);
				User users[]= stub.listMembers(group);
				for(int i=0;i<users.length;i++) {
					String dn = users[i].getDN();
					if (!allDN.contains(dn)) {
						allDN.add(dn);		
						System.out.println(dn);
					}
				}
                        }

			RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			for(int i=0;i != allDN.size(); ++i) {
				System.out.println("\"" + allDN.get(i) + "\" " + map);
				raf.writeBytes("\"" + allDN.get(i) + "\" " + map + "\n");
			}
			raf.close();
				
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


	}

	private static VOMSAdmin makeStub(final String location) throws Exception{
		final VOMSAdminServiceLocator locator = new VOMSAdminServiceLocator();
		System.out.println("locator.getVOMSAdmin");
		if(location.length() == 0) {
			System.out.println("Using default location: " + locator.getVOMSAdminAddress());
			return locator.getVOMSAdmin();
		}
		return locator.getVOMSAdmin(new URL(location));
	}

	public static void main(String args[]){
		if (args.length < 3) {
			System.out.println("No VOMS group specified, specify at least one voms group");
			System.out.println("USAGE: $JAVA_HOME/bin/java -classpath $CLASSPATH -Daxis.socketSecureFactory=org.glite.security.trustmanager.axis.AXISSocketFactory -DsslConfigFile=$AF -Daxis.ClientConfigFile=client-config.wsdd VO2GridMap <outputfile> <url> <group1,group2,group3...>");
		} else
			(new VO2GridMap()).makeGridMap(args[0], args[1], args[2]);
	}
}
