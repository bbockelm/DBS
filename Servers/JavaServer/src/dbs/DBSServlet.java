/**
 * 
 $Revision: 1.24 $"
 $Id: DBSServlet.java,v 1.24 2006/11/30 16:29:36 sekhri Exp $"

 */
package dbs;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import dbs.api.DBSApi;
import dbs.util.DBSUtil;
import xml.XMLException;

public class DBSServlet extends HttpServlet{
	/**
	 * 
	 */

         //We must have a better way of responding in XML    ANZAR
         //following is interim solution
         private static String XML_HEADER =  "<?xml version='1.0' standalone='yes'?>\n<!-- DBS Version 1 -->\n<dbs>\n";
         private static String XML_FOOTER = "</dbs>\n";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = null;
		try {
	                //Another interim solution no user DN so make one up, 
        	        //will not work for Fresh DB deployments, unless a DN is inserted by hand   ANZAR
                	Hashtable userDN = new Hashtable();
	                userDN.put("user_dn", "ANZARDN");
                        DBSUtil.writeLog("SERIOUS WARNING HARD CODED DN USED ????");
			DBSUtil.writeLog("DN of the user is " + 
                          request.getAttribute("org.globus.gsi.authorized.user.dn"));

			response.setContentType("text/xml");
			out = response.getWriter();
		
			DBSApi api = new DBSApi();
			api.call(out, getTable(request), userDN);
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			if (out != null) out.close();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {
		doPost(request, response);
	}

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
