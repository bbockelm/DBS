/**
 * 
 $Revision: 1.9 $"
 $Id: DBSServlet.java,v 1.9 2006/11/06 16:51:14 afaq Exp $"

 */
package dbs;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import dbs.api.DBSApi;

public class DBSServlet extends HttpServlet{
	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("DN of the user is " + request.getAttribute("org.globus.gsi.authorized.user.dn"));

		response.setContentType("text/xml");

		PrintWriter out = response.getWriter();
		if(! isIn("api", request.getParameterNames())) {
			addHeader(response, "api parameter missing", "200", "No API call specified");
			return;
		}

		String apiParam = request.getParameter("api");
		DBSApi api = new DBSApi();

                if(! isIn("apiversion", request.getParameterNames())) {
                        addHeader(response, "No Api Version", "200", "API version not specified");
                        return;
                }

                Enumeration verEnum = api.getApiVersions().elements();
                String msg = "Incorrect API version specified, \nSupported version are:";
                      
                for (Enumeration e = api.getApiVersions().elements() ; e.hasMoreElements() ;) {
                    msg += " "+(String)e.nextElement();
                }

                if ( ! isIn(request.getParameter("apiversion"), verEnum ) ) {
                        addHeader(response, "Wrong Api Version", "200", msg );
                        return;
                }

		addHeader(response, "Success", "100", "");

		try {
			if(apiParam.equals("listPrimaryDatasets")
				|| apiParam.equals("listProcessedDatasets")
				|| apiParam.equals("listDatasets")
				|| apiParam.equals("listParameterSets")
				|| apiParam.equals("listApplications")
				|| apiParam.equals("listApplicationConfigs")
			) {
				if(! isIn("pattern", request.getParameterNames())) {
					setHeader(response, "Null pattern", "200", "pattern parameter not specified");
					return;
				}
			}

			if(apiParam.equals("getDatasetFiles")
				|| apiParam.equals("getDatasetContents")
				|| apiParam.equals("listRuns")
			) {
				if(! isIn("path", request.getParameterNames())) {
					setHeader(response, "Null path", "200", "path parameter not specified");
					return;
				}
			}
			
			if(apiParam.equals("listPrimaryDatasets")) {
				api.listPrimaryDatasets(out, request.getParameter("pattern"));
			} 
			else if (apiParam.equals("listProcessedDatasets")
				|| apiParam.equals("listDatasets")) {
				api.listProcessedDatasets(out, request.getParameter("pattern"),"*","*","*","*","*", "*");
			}
			else if (apiParam.equals("listApplications")) {
				api.listAlgorithms(out, request.getParameter("pattern"), "*", "*", "*");
			}
			else if (apiParam.equals("listRuns")) {
				api.listRuns(out, request.getParameter("path"));
			}
			else if (apiParam.equals("listTiers")) {
				api.listTiers(out, request.getParameter("path"));
			}
			else if (apiParam.equals("listBlocks")) {
				api.listBlocks(out, request.getParameter("path"));
			}
			else if (apiParam.equals("listFiles")) {
				api.listFiles(out, request.getParameter("path"),
							request.getParameter("blockName"), 
							request.getParameter("patternLFN"));
			}
                        else if (apiParam.equals("insertPrimaryDataset")) {
				//Make a hastable of the user
                                api.insertPrimaryDataset(request.getParameter("xmlinput"), null);
                        }

			else {
				setHeader(response, "Invalid API requested", "200", "Api requested is not implemented");
				return;
			}

                out.flush();

		} catch (DBSException dbsEx) {
			setHeader(response, dbsEx.getMessage(), dbsEx.getCode(), dbsEx.getDetail());
		} catch (SQLException sqlEx) {
			setHeader(response, "Connection Refused", "402", sqlEx.getMessage());
		} catch (Exception ex) {
			setHeader(response, "Execution error", "401", ex.getMessage());
		} /*catch(org.xml.sax.SAXException saxEx) {
			setHeader(response, "Bad Data", "300", saxEx.getMessage());
		}*/

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	private void addHeader(HttpServletResponse response , String message, String code, String detail) {
		response.addHeader("Dbs-status-code", code);
		response.addHeader("Dbs-status-message", message.replace('\n', ' '));
		response.addHeader("Dbs-status-detail", detail.replace('\n', ' '));

		System.out.println("Dbs-status-code " + code);
		System.out.println("Dbs-status-message " + message);
		System.out.println("Dbs-status-detail " +  detail);
	}

	private void setHeader(HttpServletResponse response , String message, String code, String detail) {
		response.setHeader("Dbs-status-code", code);
		response.setHeader("Dbs-status-message", message.replace('\n', ' '));
		response.setHeader("Dbs-status-detail", detail.replace('\n', ' '));

		System.out.println("Dbs-status-code " + code);
		System.out.println("Dbs-status-message " + message);
		System.out.println("Dbs-status-detail " +  detail);
	}

	private boolean isIn(String param, Enumeration e) {
		while (e.hasMoreElements()) {
			if( param.equals((String)e.nextElement()) ) {
				return true;
			}
		}
		return false;
	}
	
}
