package cms.dbs.servlets;

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
import org.json.JSONObject;
import org.json.JSONArray;


import cms.dbs.commons.db.DBManagement;

/*
need to be updated for DBS3

import dbs.api.DBSApi;
import dbs.util.DBSUtil;
*/

/**
* A Servlet that inherits from <code>javax.servlet.http.HttpServlet</code>. This is the gateway for invoking DBS API. 
* All remote requets pass through this servlet. This class simple implements doPost and doGet method and involes the 
* DBS api that handles the rest.
* Tomcat will call DBSServlet and the init will be invoked with the correct config file
* automatically. The servlet config is described in etc/web.xml.
*/
public class DBSServlet extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
    		try {
       			super.init(config);
       			ServletContext context = getServletContext();
			System.out.println("DBS Servlet INIT is CALLED");
			//FIXME: WE must checks the Schema version here
			//Verify why can't we make DBSApi object here ??\
	                //Lets get serever parameters here
			//supportedSchemaVersion = config.getInitParameter("SupportedSchemaVersion");
			//supportedClientVersions = config.getInitParameter("SupportedClientVersions");

			//System.out.println("supportedSchemaVersion: "+supportedSchemaVersion);
			//System.out.println("supportedClientVersions: "+supportedClientVersions);

			String configFilePath = context.getRealPath("META-INF/context.xml");
       			System.out.println("configFilePath : "+configFilePath);
       			System.out.println("SEREVER INFO: "+context.getServerInfo() );
       			System.out.println("---------------------------------------------------------------");
       			System.out.println("DBS reading configuration file");
       			DBSConfig dbsconfig = DBSConfig.getInstance(configFilePath);
       			System.out.println("DBS configuration file read successfully");
       			System.out.println("---------------------------------------------------------------\n");
       			System.out.println("DBS making database connection");
       			Connection conn = DBManagement.getDBConnManInstance().getConnection();
       			try {
       				if (conn != null) {
					System.out.println("DBS database connection made successfully");
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
	 * Override HttpServlet.post, a method that takes the servlet request and packs all the parameters 
	 * passed in the request into a <code>java.util.Hashtable</code> and then calls the <code>dbs.api.DBSApi</code> 
	 * object that handles all the client requests. It also makes a <code>java.util.Hashtable</code> 
	 * that contains user information. Eventually this user infomation will come from the security context 
	 * established between the client and the server. The tomcat contaier provides api to retrive the user 
	 * DN from this context. This table is aslo passed to the DBSApi that uses it for bookekeeping purposes.
	 * {@inheritDoc}
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String apiStr = request.getParameter("api");
		String dn = (String)request.getAttribute("org.globus.gsi.authorized.user.dn");
		response.setContentType("application/x-json");;
		PrintWriter out = response.getWriter();
		DBSDispatcher DSP = new DBSDispatcher(); 
      
		/*
		* If the DN is not set properly then look for DN in the http header sent by the client. 
		* If still DN is not found then set the dn to web-client
		*/
		if (dn == null) {
			dn = request.getHeader("UserID");
			if (dn == null) dn = "web-client";
			DBSUtil.writeLog("NO DN, using UserID: " + dn + " from HTTP header");
		}
		
		try {
			//StringBuffer url = request.getRequestURL();
			//System.out.println("URL is ------------- > "  + url);
                        
			//only writeLog when (DBSConstants.DEBUG=true
			DBSUtil.writeLog("DN of the user is " + dn);
			
			/***
                         Not sure about this part if the client send a JSON object.
                        ***/ 
                        //String charset = request.getCharacterEncoding();
	        	//if(charset == null) charset = "UTF-8";
        		//BufferedReader in = new BufferedReader
	            	//(new InputStreamReader(request.getInputStream(), charset));
			int buf_size = request.getContentLength;
			BufferedReader in = request.getReader();
        		// Read the request
	        	CharArrayWriter data = new CharArrayWriter();
	        	char buf[] = new char[buf_size];
        		int ret;
	        	while((ret = in.read(buf, 0, buf_size)) != -1)
        	    		data.write(buf, 0, ret);

			// Process the request
        		JSONObject json_req = null;
            		json_req = new JSONObject(data.toString());

			DSP.ApiCall(out, json_req, dn);

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
}
