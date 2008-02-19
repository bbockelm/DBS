/**
 $Revision: 1.2 $"
 $Id: MSApi.java,v 1.2 2008/01/17 16:59:10 sekhri Exp $"
 *
*/


package ms.api;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.Writer;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import xml.DBSXMLParser;
import xml.Element;
import xml.XMLException;
import db.DBManagement;
import ms.MSConstants;
import ms.sql.MSSql;
import ms.MSException;
import ms.util.MSUtil;
import db.DBManagement;
import java.sql.ResultSet;
import ms.util.MSConfig;

/**
 * This class encapsulate <code>ms.api.MSApiLogic</code> , handles database connection management,handle XML parsing for the input provided by the clients, checks the match between schema and api version and handles exceptioms. This class works as the higher level dispatcher for MS API. All the MS API calls are invoked vias a public method call. The interface of this class is this call method which can take a hashtable of key value pairs. It invokes the API call depending upon the value of the api key in the hashtable. The reason for having this higher level class is to separate the lovel level business logic from database connection management and xml parsing.<br>
 * @author sekhri
 * 
 */
public class MSApi {
	
	private MSApiLogic api;
	private String apiStr = null;
	private  Hashtable table;

	public MSApi() {
		apiStr = "";
	}

	public void call(Writer out, Hashtable table) throws Exception {
         	this.table = table;
		Connection conn = null;

		try {
	
			if(table.containsKey("xml")) out.write(MSConstants.XML_HEADER); 
			else out.write(MSConstants.HTTP_HEADER);
			String apiStr = get(table, "api", true);
			this.apiStr = apiStr;
                	MSUtil.writeLog("apiStr: "+apiStr);


			
			conn = getConnection();
			conn.setAutoCommit(false);
			api = new MSApiLogic();
			
			if(table.containsKey("xml")) api.setXml(true);
 
			if (apiStr.equals("addRequest")) {
				api.addRequest(conn, out, 
						get(table, "src_url", true),
						get(table, "dst_url", true),
						get(table, "path", true),
						get(table, "dn", true),
						convert(get(table, "with_parents", false)),
						convert(get(table, "with_force", false)),
						get(table, "notify", false)
						);
			} else if (apiStr.equals("getRequestByID")) {
				api.getRequestByID(conn, out, 
						get(table, "request_id", true)
						);

			} else if (apiStr.equals("getRequestByUser")) {
				api.getRequestByUser(conn, out, 
						get(table, "dn", true)
						);
			} else if (apiStr.equals("updateRequest")) {
				api.updateRequest(conn, out,
						get(table, "src_url", true),
						get(table, "dst_url", true),
						get(table, "path", true),
						get(table, "status", false),
						get(table, "progress", false),
						get(table, "detail", false));

			} else if (apiStr.equals("deleteRequest")) {
				api.deleteRequest(conn, out, 
						get(table, "src_url", false),
						get(table, "dst_url", false),
						get(table, "path", false),
						//get(table, "dn", false),
						"",//Don't use DN
						get(table, "request_id", false)
						);

			} else {
				writeException(out, "Invalid API", "1018", "The api " + apiStr + " provided by the client is not valid");
				return;
			}
			conn.commit();
		} catch (MSException msEx) {
			if(conn != null) conn.rollback();
			if (msEx.getMessage() == null ) {
				writeException(out, "Unexpected execution exception", "1080", "NULL POINTER MSException");
				return;
			}
			writeException(out, msEx.getMessage(), msEx.getCode(), msEx.getDetail());
			return; 
		} catch (SQLException sqlEx) {
			if(conn != null) conn.rollback();
			if (sqlEx.getMessage() == null ) {
				writeException(out, "Unexpected execution exception", "2001", "NULL POINTER SQLException");
				return;
			}
			writeException(out, "Database exception", "2000", sqlEx.getMessage());
			return;
		} catch (Exception ex) {
			if(conn != null) conn.rollback();
			ex.printStackTrace();
			if (ex.getMessage() == null ) {
				writeException(out, "Unexpected execution exception", "4001", "NULL POINTER Exception");
				return;
			}
			writeException(out, "Unexpected execution exception", "4000", ex.getMessage());
			return;
		} finally {
			if(conn != null) {
				conn.clearWarnings();
				conn.close();
			}
		}
                
		if(table.containsKey("xml")) {
			out.write(MSConstants.XML_SUCCESS);
			out.write(MSConstants.XML_FOOTER);
		} else {
			out.write(MSConstants.HTTP_SUCCESS);
			out.write(MSConstants.HTTP_FOOTER);

		}
		out.flush();
                return; 
	}
	
	private boolean convert(String in) {
		if (isNull(in)) return true;
		if (in.toLowerCase().equals("n")) return false;
		return true;
	}
	private String get(Hashtable table, String key, boolean excep) throws Exception {
		String value = "";
		if ( isNull(value = MSUtil.get(table, key)) ) {
			if (excep) throw new MSException("Mandatory data missing", "1004", "Null Fields. Expected a valid " + key);
		}
		return value;
	}
	
	
	public void writeException(Writer out, String message, String code, String detail) throws Exception {
		message = " ____________ API Invoked " + this.apiStr + "____________\n" + message;
		message = message.replace('\'',' ');
		message = message.replace('<',' ');
		message = message.replace('>',' ');
                detail= detail.replace('\'',' ');
		detail = detail.replace('<',' ');
		detail = detail.replace('>',' ');
		code = code.replace('\'',' ');
		code = code.replace('<',' ');
		code = code.replace('>',' ');
		if(table.containsKey("xml")) {
			out.write("<exception message='" + message + "' "); 
			out.write(" code ='" + code + "' "); 
			out.write(" detail ='" + detail + "' />\n"); 
			out.write(MSConstants.XML_FOOTER);
		}else {
			out.write("exception message=" + message + " <BR> "); 
			out.write(" code =" + code + " <BR> "); 
			out.write(" detail =" + detail + " <BR> \n"); 
			out.write(MSConstants.HTTP_FOOTER);
		}

		out.flush();
       		MSUtil.writeErrorLog("<exception message='" + message + "' ");
		MSUtil.writeErrorLog(" code ='" + code + "' ");
		MSUtil.writeErrorLog(" detail ='" + detail + "' />\n");
	}



	public Connection getConnection() throws Exception {
		Connection conn = DBManagement.getDBConnManInstance().getConnection();
		if (conn != null) {
       			MSUtil.writeLog("Pooling at work");
			return conn;
		} else {
			MSUtil.writeLog("Pooling not required for standalone client");
			MSConfig config = MSConfig.getInstance();
			return DBManagement.getConnection( config.getDbDriver(),
					config.getDbURL(), 
					config.getDbUserName(),
					config.getDbUserPasswd());
		}
	}
	private boolean isNull(String pattern) {
		return MSUtil.isNull(pattern);
	}
	
}
