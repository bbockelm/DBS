package fnal.gov.web.servlet;

import fnal.gov.session.NVSessionEJBLocal;

import fnal.gov.session.NameObject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import fnal.gov.web.util.Util;

public class NVServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/xml; charset=windows-1252";
    private NVSessionEJBLocal myBean;
    private Util u;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        u = new Util();
        Object obj = u.getEJB("nvs/NVSessionEJB/local");
        if(obj != null)  myBean = (NVSessionEJBLocal) obj;
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, 
                                                           IOException {
        
        String varName = request.getParameter("name");
        String varType = request.getParameter("type");
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<nvs>");
        
        if(myBean == null) out.println("<error message='Enterprize Java Bean could not be initilized'/>");
        else {
            try {
                List<NameObject> results = myBean.validate(varName, varType);
                for (int i = 0 ; i != results.size(); ++i) {
                    NameObject no = results.get(i);
                    out.println("<nvs_name name='" + no.getName() + "' similarity='" + no.getSimilar() + "'/>");
                }
            }catch(Exception e) {
                out.println("<error message='" + e.getMessage() + "'/>");
            }
        }
        out.println("</nvs>");
        
        out.close();
    }
}
