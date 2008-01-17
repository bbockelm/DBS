/**
 * 
 $Revision: 1.1 $"
 $Id: MSServlet.java,v 1.1 2008/01/16 22:33:29 sekhri Exp $"

 */
package ms;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;

import ms.api.MSApi;
import ms.util.MSUtil;
import ms.util.MSConfig;
import db.DBManagement;


/**
* A Servlet that inherits from <code>javax.servlet.http.HttpServlet</code>. This is the gateway for invoking MS API. All remote requets pass through this servlet. This class simple implements doPost and doGet method and involes the MS api that handles the rest.
* @author sekhri
*/
public class MSServlet extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
    		try {
       			super.init(config);
       			ServletContext context = getServletContext();
			System.out.println("MS Servlet INIT is CALLED");
       			
			String configFilePath = context.getRealPath("META-INF/context.xml");
       			System.out.println("configFilePath : "+configFilePath);
       			System.out.println("SEREVER INFO: "+context.getServerInfo() );
       			System.out.println("---------------------------------------------------------------");
       			System.out.println("MS reading configuration file");
       			MSConfig msconfig = MSConfig.getInstance(configFilePath);
       			System.out.println("MS configuration file read successfully");
       			System.out.println("---------------------------------------------------------------\n");
       			System.out.println("MS making database connection");
       			System.out.println("MS READY");
    		} catch(Exception e) {
			throw new ServletException(e);
    		}
	}

	
	 
	/**
	 * a method that takes the servlet request and packs all the parameters passed in the request into a <code>java.util.Hashtable</code> and then calls the <code>ms.api.MSApi</code> object that handles all the client requests. It also makes a <code>java.util.Hashtable</code> that contains user information. Eventually this user infomation will come from the security context established between the client and the server. The tomcat contaier provides api to retrive the user DN from this context. This table is aslo passed to the MSApi that uses it for bookekeeping purposes.
	 * {@inheritDoc}
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = null;
                MSApi api = null; 

		try {

			String dn = (String)request.getAttribute("org.globus.gsi.authorized.user.dn");
			
			/*
			 * If the DN is not set properly then look for DN in the http header sent by the client. If still DN is not found then set the dn to web-client
			 */
			if (dn == null) {
	                        dn = request.getHeader("UserID");
				if (dn == null) {
					dn = "web-client";
				}
				MSUtil.writeLog("NO DN, using UserID: " + dn + " from HTTP header");
			}
			MSUtil.writeLog("DN of the user is " + dn);
			
			//System.out.println("DN of the user is " + dn);
			//response.setContentType("text/xml");
			response.setContentType("text/html");
			out = response.getWriter();
		
			api = new MSApi();
			Hashtable table = getTable(request);
			table.put("dn" , dn);
			api.call(out, table);

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
	
}
