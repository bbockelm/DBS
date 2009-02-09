/**
 * 
 $Revision: 1.46 $"
 $Id: DBSServlet.java,v 1.46 2009/02/02 21:24:31 sekhri Exp $"

 */
package dbs;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Properties;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;

import dbs.api.DBSApi;
import dbs.util.DBSUtil;
import dbs.util.DBSConfig;
import dbs.util.RegisterLock;
import dbs.util.ModeLock;
import dbs.data.DBSDataCache;
import db.DBManagement;
import dbs.search.graph.GraphUtil;

/**
* A Servlet that inherits from <code>javax.servlet.http.HttpServlet</code>. This is the gateway for invoking DBS API. All remote requets pass through this servlet. This class simple implements doPost and doGet method and involes the DBS api that handles the rest.
* @author sekhri
*/
public class DBSServlet extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
    		try {
       			super.init(config);
						
			/*System.out.println ("Config name " + config.getServletName());
			String tomcatHome = System.getProperty("CATALINA_HOME");
			System.out.println("HOME is " + tomcatHome);
			Properties props = System.getProperties();
			Enumeration<Object> keys =  props.keys();
			while(keys.hasMoreElements()){
				String key = (String) keys.nextElement();
				String prop = System.getProperty(key);
				System.out.println(key +" : " + prop);
			}*/
       			ServletContext context = getServletContext();
			//DBSUtil u = new DBSUtil();
			//u.addRegistration(context);

			System.out.println("DBS Servlet INIT is CALLED");
			/*Object[] o = context.getResourcePaths("/").toArray();
			for (int i =0 ; i!= o.length; ++i) {
				System.out.println(" obj " + o[i]);
			}
			
       			//context.log("DBS Servlet Initializing..");
			URL url = context.getResource("/WEB-INF");
			System.out.println("URL is " + url);
			System.out.println("Host is " + url.getHost() );
			System.out.println(" is " + url.getPort());
			System.out.println(" is " + url.getUserInfo());
			*/
			
       			//FIXME: WE must checks the Schema version here
			//Verify why can't we make DBSApi object here ??\
	                //Lets get serever parameters here
			//supportedSchemaVersion = config.getInitParameter("SupportedSchemaVersion");
			//supportedClientVersions = config.getInitParameter("SupportedClientVersions");

			//System.out.println("supportedSchemaVersion: "+supportedSchemaVersion);
			//System.out.println("supportedClientVersions: "+supportedClientVersions);

			String configFilePath = context.getRealPath("META-INF/context.xml");
			String graphFilePath = context.getRealPath("META-INF/DBSSchemaGraph.xml");
       			System.out.println("configFilePath : "+configFilePath);
       			System.out.println("SEREVER INFO: "+context.getServerInfo() );
       			System.out.println("---------------------------------------------------------------");
       			System.out.println("DBS reading configuration file");
       			DBSConfig dbsconfig = DBSConfig.getInstance(configFilePath);
       			System.out.println("DBS configuration file read successfully");
       			System.out.println("DBS reading schema graph file " + graphFilePath);
			GraphUtil.getInstance(graphFilePath);
       			System.out.println("DBS read schema graph file successfully");
			//ANTLRStringStream input = new ANTLRStringStream("find procds");
       			System.out.println("---------------------------------------------------------------\n");
       			System.out.println("DBS making database connection");
       			Connection conn = DBManagement.getDBConnManInstance().getConnection();
       			try {
       				if (conn != null) {
					System.out.println("DBS database connection made successfully");
					System.out.println("---------------------------------------------------------------\n");
					System.out.println("DBS loading data into cache");
					DBSDataCache cache = DBSDataCache.getDBSDataCacheInstance(conn);
					System.out.println("DBS loaded data into cache successfully ");
					System.out.println("---------------------------------------------------------------\n");
       				} else {
      					throw new ServletException(new Exception("Database connection could not be established"));
       				}
       			} finally {
       				if (conn != null)  {
       					conn.clearWarnings();
       					conn.close();
       				}
	       		}
			RegisterLock.getRegisterLockInstance();

			System.out.println("DBS READY");
                } catch(DBSException dbse) {
                        System.out.println("DBS Failed to initialize: "+dbse.getMessage());
                        System.out.println("Exception Details: "+dbse.getDetail());
                        dbse.printStackTrace();
                        throw new ServletException(dbse);

    		} catch(Exception e) {
			System.out.println("DBS Failed to initialize: "+e.getMessage());
			e.printStackTrace();
			throw new ServletException(e);
    		}
	}
	private String supportedSchemaVersion;
	private String supportedClientVersions;

	
	 
	/**
	 * a method that takes the servlet request and packs all the parameters passed in the request into a <code>java.util.Hashtable</code> and then calls the <code>dbs.api.DBSApi</code> object that handles all the client requests. It also makes a <code>java.util.Hashtable</code> that contains user information. Eventually this user infomation will come from the security context established between the client and the server. The tomcat contaier provides api to retrive the user DN from this context. This table is aslo passed to the DBSApi that uses it for bookekeeping purposes.
	 * {@inheritDoc}
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String apiStr = request.getParameter("api");
		try {
			RegisterLock rLock = RegisterLock.getRegisterLockInstance();
			if(apiStr != null) {
				if(apiStr.equals("register")) rLock.setDoneOnce(false);
			}
			if(!rLock.isDoneOnce()) {
				String url = request.getRequestURL().toString();
				DBSUtil u = new DBSUtil();
				u.addRegistration(url);
				rLock.setDoneOnce(true);
			}
	 	} catch(Exception e) {
			System.out.println("REGISTRATION Message "+ e.getMessage());
			e.printStackTrace();
    		}

		response.setContentType("text/xml");
		String dn = (String)request.getAttribute("org.globus.gsi.authorized.user.dn");

		PrintWriter out = response.getWriter();
                DBSApi api = new DBSApi(); 


		try {
			ModeLock mLock = ModeLock.getModeLockInstance();
			if(apiStr.equals("setMode")) { 
				if(!isAuthorized(dn)) {
					api.writeException(out, "Authorization Error", "404", "User " + dn +" is authorized to execute this api setMode");
					return;
				}
				mLock.setMode(true);
				try {
					writeToStream(response, "<dbs message='DBS is set for  maintanence mode'/>" );
				} finally {
					return;
				}
			}else if (apiStr.equals("unsetMode")) {
				if(!isAuthorized(dn)) {
					api.writeException(out, "Authorization Error", "404", "User " + dn +" is authorized to execute this api setMode");
					return;
				}

				mLock.setMode(false);
				try {
					writeToStream(response, "<dbs message='DBS maintanence mode is cleared'/>" );
				} finally {
					return;
				}
			}
			if(mLock.isMode()) {
				try {
					writeToStream(response, "<dbs message='DBS is under maintanence'/>" );
				} finally {
					return;
				}
			}
	 	} catch(Exception e) {
			System.out.println("Maintanence Message "+ e.getMessage());
			e.printStackTrace();
    		}

	                 
		       
		/*
		* If the DN is not set properly then look for DN in the http header sent by the client. If still DN is not found then set the dn to web-client
		*/
		if (dn == null) {
			dn = request.getHeader("UserID");
			if (dn == null) dn = "web-client";
			DBSUtil.writeLog("NO DN, using UserID: " + dn + " from HTTP header");
		}

		
		//PrintWriter out = null;
                //DBSApi api = null; 

		try {
			//StringBuffer url = request.getRequestURL();
			//System.out.println("URL is ------------- > "  + url);

			Hashtable userDN = new Hashtable();
			DBSUtil.writeLog("DN of the user is " + dn);
			userDN.put("user_dn", dn);
			
			//System.out.println("DN of the user is " + dn);
			//response.setContentType("text/xml");
			//out = response.getWriter();
		
			//api = new DBSApi();
			api.call(out, getTable(request), userDN);

		} catch(Exception e) {
			try {
	                        if (api != null) api.writeException(out, "Servlet Error", "500",  e.getMessage()); 
			} catch(Exception ex) {
                        	ex.printStackTrace(); 
				throw new ServletException(ex);
			}
                        e.printStackTrace(); 
			throw new ServletException(e);
		} finally {
			if (out != null) out.close();
		}
	}

	/**
	 * Simply calls the doPoset method
	 * {@inheritDoc}
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	/**
	 * This method packs all the parameters passed in the request into a <code>java.util.Hashtable</code>
	 * @param request an HttpServletRequest object that contains the request the client has made of the servlet
	 * @return a <code>java.util.Hashtable</code> that contains all the parameter that the client has passed into the servlet.
	 */
	private Hashtable getTable(HttpServletRequest request) {
		Hashtable table = new Hashtable();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String)e.nextElement();
			table.put(key, request.getParameter(key));
		}
		return table;
	}

	private void writeToStream(HttpServletResponse response, String msg) throws Exception{
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(DBSConstants.XML_HEADER);
			out.write(msg);
			out.write(DBSConstants.XML_SUCCESS);
			out.write(DBSConstants.XML_FOOTER);
			out.flush();
		} finally {
			if (out != null) out.close();
		}
	}
	private boolean isAuthorized(String dn) throws Exception{
		if(dn == null) return false;
		DBSConfig dbsConfig = DBSConfig.getInstance();
		String adminDns = dbsConfig.getAdminDN();
		String finalDns[] = adminDns.split(",");
		for(String aDn: finalDns) {
			System.out.println("checking with " + aDn);
			if(aDn.trim().equals(dn)) return true;
		}
		//if(dn.equals("sekhri@cmssrv17.fnal.gov")) return true;
		//if(dn.equals("/DC=org/DC=doegrids/OU=People/CN=Vijay Sekhri 579120")) return true;
		return false; 
	}
																	
	
}
