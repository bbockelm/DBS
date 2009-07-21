import org.jcouchdb.db.Database;
import org.jcouchdb.db.Response;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.ViewResult;
import org.jcouchdb.exception.DataAccessException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class RegSrvClient {
	String hostName = "localhost";
	String dbName = "registrationservice";
	Database db;

	private void init() {
		db = new Database(this.hostName, this.dbName);
	}
	public RegSrvClient() {
		init();
	}
	public RegSrvClient(String hostName) {
		this.hostName = hostName;
		init();
	}

	public RegSrvClient(String hostName, String dbName) {
		this.hostName = hostName;
		this.dbName = dbName;
		init();
	}

	public void register(Map<String, String> doc) throws InvalidDocumentException {
		try {
			if (!doc.containsKey("url")) throw new InvalidDocumentException("URL is mandatory");
			String url = doc.get("url");
			List<String> urlAndIds = listIDsByURL(url);
			if(urlAndIds.size() > 0) { // Update old
				for (String aUrlAndId: urlAndIds) {
					Map<String, String> retrivedDoc = db.getDocument(Map.class, aUrlAndId);
					//System.out.println("Before " + retrivedDoc);
					retrivedDoc.putAll(doc);
					//System.out.println("Again  " + retrivedDoc);
					db.updateDocument(retrivedDoc);
				}
			} else { // Insert new
				db.createDocument(doc);
			}
			//System.out.println("DOC  " + doc);

		}catch(DataAccessException e) {
			Response r = e.getResponse();
			int code = r.getCode();
			switch (code) {
				case 403: throw new InvalidDocumentException("Invalid Document format");
				default : throw e; 	  
			}
		}
	}

	//public List<Map<String, String>>  listIDsByURL(String url) {
	public List<String>  listIDsByURL(String url) {
		ViewResult<Map> results = db.queryView("registrationservice/idbyurl?key=%22" + url + "%22", Map.class, null, null);
		List<ValueRow<Map>> rows = results.getRows();
		//List<Map<String, String>> toReturn = new ArrayList<Map<String, String>>();
		List<String> toReturn = new ArrayList<String>();
		for(ValueRow aRow: rows) {
			//Map<String, String> tmpMap = new HashMap<String, String>();
			//System.out.println("key " + aRow.getKey());
			//System.out.println("value " + aRow.getValue());
			//tmpMap.put((String)aRow.getKey(), (String)aRow.getValue());
			//toReturn.add(tmpMap);
			toReturn.add((String)aRow.getValue());
		}
		return toReturn;
	}

	/*public void  listServices() {
		ViewResult<Map> results = db.listDocuments(null, null);
		List<ValueRow<Map>> rows = results.getRows();
		for(ValueRow aRow: rows) {
			System.out.println("key " + aRow.getKey());
			System.out.println("value " + aRow.getValue());
		}
	}*/


	public static void main(String args[]) {
		//Session s = new Session("localhost",5984);
		//Database db = s.getDatabase("registrationservice");
		RegSrvClient client = new RegSrvClient("localhost", "registrationservice");
		Map<String,String> doc = new HashMap<String, String>();
		//doc.put("id", "HAHAH");
		doc.put("url", "http:/kljfd.cpom");
		doc.put("alias", "testing alis ");
		doc.put("type", "testing type");
		doc.put("admin", "AdminQQQQQQQQQQQQQQQQQQQQQQQQQQQQQq");
		//doc.put("_id", "LALALALLALAAL");
		try {
			client.register(doc);
			//client.listServices();
			//client.listIDsByURL("test");
		}catch(Exception e) {
			System.out.println(e);


		}
	}
}


