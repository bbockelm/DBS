
import java.net.URL;
import java.io.RandomAccessFile;
import org.glite.security.voms.service.User;
import org.glite.security.voms.service.admin.VOMSAdmin;
import org.glite.security.voms.service.admin.VOMSAdminServiceLocator;


public class VO2GridMap {
	

	private String defaultURL = "https://voms.cern.ch:8443/voms/cms/services/VOMSAdmin";
	private String defaultGroup = "/cms/uscms/Role=production";
	private String map = "sekhri";

	public VO2GridMap (){}

	

	public void makeGridMap(String fileName, String url, String group) {
		try{	
			if (url == null) url = defaultURL;
			if (url.length() == 0) url = defaultURL;
			if (group == null) group = defaultGroup;
			if (group.length() == 0) group = defaultGroup;
			System.out.println("URL is "+ url);

			VOMSAdmin stub = makeStub(url);
			User users[]= stub.listMembers(group);
			RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			for(int i=0;i<users.length;i++) {
				System.out.println("\"" + users[i].getDN() + "\" " + map);
				raf.writeBytes("\"" + users[i].getDN() + "\" " + map + "\n");
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
		(new VO2GridMap()).makeGridMap(args[0], args[1], args[2]);
	}
}
