/****
 * $Id: DBSServlet.java,v 1.3 2009/09/21 15:22:20 yuyi Exp $
 *
 ****/
package cms.dbs.servlets;

import java.io.CharArrayWriter;
import java.io.BufferedReader;
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
import cms.dbs.commons.exceptions.DBSException;
import cms.dbs.commons.utils.DBSSrvcConfig;
import cms.dbs.commons.utils.DBSSrvcUtil;

//Adding REST interface
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Router;
import org.restlet.Context;


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

public class DBSServlet extends Application {
//public class DBSServlet extends HttpServlet{
//	public void init(ServletConfig config) throws ServletException {


    /** 
     * Creates a root Restlet that will receive all incoming calls. 
     */
    @Override
    public Restlet createRoot() {
        // Create a router Restlet that routes each call to a  
        // new instance of HelloWorldResource.

        Context ctx = getContext();

        Router router = new Router(getContext());

    		try {
       			System.out.println("---------------------------------------------------------------\n");
       			System.out.println("DBS making database connection");
       			Connection conn = DBManagement.getDBConnManInstance().getConnection();
       			try {
       				if (conn != null) {
					System.out.println("DBS database connection made successfully");
					System.out.println("---------------------------------------------------------------\n");
       				} else {
      					throw new Exception("Database connection could not be established");
       				}
       			} finally {
       				if (conn != null)  {
       					conn.clearWarnings();
       					conn.close();
       				}
	       		}

		        // Defines only one route  
        		router.attachDefault(HelloWorldResource.class);

			System.out.println("DBS READY");

/*                } catch(DBSException dbse) {
                        System.out.println("DBS Failed to initialize: "+dbse.getMessage());
                        System.out.println("Exception Details: "+dbse.getDetail());
                        dbse.printStackTrace();
                        throw new ServletException(dbse);
*/
    		} catch(Exception e) {
			System.out.println("DBS Failed to initialize: "+e.getMessage());
			e.printStackTrace();
			//throw new ServletException(e);
    		}
        return router;
	}
	private String supportedSchemaVersion;
	private String supportedClientVersions;
}
