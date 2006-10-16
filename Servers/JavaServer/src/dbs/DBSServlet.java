/**
 * 
 */
package dbs;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import dbs.DBSApi;
/**
 * @author sekhri
 *
 */
public class DBSServlet extends HttpServlet{
	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("DN of the user is " + request.getAttribute("org.globus.gsi.authorized.user.dn"));
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		if(! isIn("api", request.getParameterNames())) {
			addHeader(response, "Null api", "200", "api parameter not specified");
			return;
		}
		String apiParam = request.getParameter("api");
		System.out.println("api = "+apiParam);
		DBSApi api = new DBSApi();
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
				api.listProcessedDatasets(out, request.getParameter("pattern"));
			}
			else if (apiParam.equals("listParameterSets")) {
				api.listParameterSets(out, request.getParameter("pattern"));
			}
			else if (apiParam.equals("listApplications")) {
				api.listApplications(out, request.getParameter("pattern"));
			}
			else if (apiParam.equals("listApplicationConfigs")) {
				api.listApplicationConfigs(out, request.getParameter("pattern"));
			}
			else if (apiParam.equals("getDatasetContents")) {
				api.getDatasetContents(out, request.getParameter("path"));
			}
			else if (apiParam.equals("getDatasetFiles")) {
				api.getDatasetFiles(out, request.getParameter("path"));
			}
			else if (apiParam.equals("listRuns")) {
				api.getDatasetFiles(out, request.getParameter("path"));
			}
                        else if (apiParam.equals("createPrimaryDatase")) {
                                api.createPrimaryDataset(request.getParameter("xmlinput"));
                        }

			else {
				setHeader(response, "Invalid API requested", "200", "Api requested is not implemented");
				return;
			}
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
