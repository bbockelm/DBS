package fnal.gov.web.servlet;

import fnal.gov.ejb.entity.Request;
//import fnal.gov.ejb.session.MSSessionEJB;
import fnal.gov.ejb.session.MSSessionEJBLocal;
import fnal.gov.web.util.Util;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

//import javax.jms.Queue;

//import javax.jms.QueueConnectionFactory;

import javax.servlet.*;
import javax.servlet.http.*;

//import javax.ejb.EJB;

public class MigrationServlet extends HttpServlet {
    //@EJB(beanInterface=fnal.gov.ejb.session.MSSessionEJBLocal.class, name="ms/MSSessionEJB/local")
    private MSSessionEJBLocal mySessionBean;
    /*private Queue queue;
    private QueueConnectionFactory factory;*/
    private Util u;
    

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        u = new Util();
        Object obj = u.getJNDIObj("ms/MSSessionEJB/local");
        if(obj != null)  mySessionBean = (MSSessionEJBLocal) obj;
        /*obj = u.getJNDIObj("queue/mdb");
        if(obj != null) queue = (Queue) obj;
        obj = u.getJNDIObj("ConnectionFactory");
        if(obj != null) factory = (QueueConnectionFactory)obj;*/
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String header = "<html><head><title>MigrationServlet</title></head><body>";
        String footer = "</body></html>";
        String breakTag = "<BR>";
        String reqTag = "";
        String errorTag = "";
        String tagClose = "";
        String contentType = "text/html";
        try {
            String xmlStr = u.get(request, "xml", false);
            if(!u.isNull(xmlStr)) {
                header = "<?xml version='1.0' standalone='yes'?><ms>";
                breakTag = "";
                reqTag = "<request ";
                errorTag = "<error ";
                tagClose = " />";
                footer = "</ms>";
                contentType = "text/xml";
            }
            response.setContentType(contentType);
            out.println(header);
            
            String apiStr = u.get(request, "api", true);
            if(mySessionBean == null) {
                out.println("Bean is null");
                return;
            }
            if (apiStr.equals("addRequest")) {
                Request r = mySessionBean.addRequest(
                    u.get(request, "src_url", true),
                    u.get(request, "dst_url", true),
                    u.get(request, "path", true),
                    u.get(request, "dn", true),
                    u.get(request, "with_parents", false),
                    u.get(request, "with_force", false),
                    u.get(request, "notify", false)
                );
                //u.sendMsg(factory, queue, "newRequest");
                out.println(reqTag + breakTag);
                out.println(breakTag + "id = '" + r.getId() + "'");
                out.println(tagClose);
            }
            
            else if (apiStr.equals("deleteRequest")) {
                mySessionBean.deleteRequest(
                    u.get(request, "src_url", true),
                    u.get(request, "dst_url", true),
                    u.get(request, "path", true)                
                );
                out.println(reqTag + breakTag);
                out.println(breakTag + "message = 'Request deleted'");
                out.println(tagClose);
            }
            
            else if (apiStr.equals("getRequestByUser")) {
                List<Request> rList = mySessionBean.getRequestByUser(u.get(request, "dn", true));
                for(int i = 0 ; i != rList.size(); ++i) {
                    Request r = rList.get(i);
                    out.println(reqTag + breakTag);
                    out.println(breakTag + "id = '" + r.getId() + "'");    
                    out.println(breakTag + "detail = '" + u.format(r.getDetail()) + "'");
                    out.println(breakTag + "notify = '" + r.getNotify() + "'");
                    out.println(breakTag + "path = '" + r.getPath() + "'");
                    out.println(breakTag + "progress = '" + r.getProgress() + "'");
                    out.println(breakTag + "status = '" + r.getStatus() + "'");
                    out.println(breakTag + "force = '" + r.getWithForce() + "'");
                    out.println(breakTag + "parents = '" + r.getWithParents() + "'");
                    out.println(breakTag + "dn = '" + r.getPerson().getDistinguishedName() + "'");
                    out.println(breakTag + "dstUrl = '" + r.getDstUrl().getUrl() + "'");
                    out.println(breakTag + "srcUrl = '" + r.getSrcUrl().getUrl() + "'");
                    out.println(tagClose);
                }
            }

            else if (apiStr.equals("getRequestById")) {
                List<Request> rList = mySessionBean.getRequestById(Long.parseLong(u.get(request, "request_id", true)));
                for(int i = 0 ; i != rList.size(); ++i) {
                    Request r = rList.get(i);
                    out.println(reqTag + breakTag);
                    out.println(breakTag + "id = '" + r.getId() + "'");    
                    out.println(breakTag + "detail = '" + u.format(r.getDetail()) + "'");
                    out.println(breakTag + "notify = '" + r.getNotify() + "'");
                    out.println(breakTag + "path = '" + r.getPath() + "'");
                    out.println(breakTag + "progress = '" + r.getProgress() + "'");
                    out.println(breakTag + "status = '" + r.getStatus() + "'");
                    out.println(breakTag + "force = '" + r.getWithForce() + "'");
                    out.println(breakTag + "parents = '" + r.getWithParents() + "'");
                    out.println(breakTag + "dn = '" + r.getPerson().getDistinguishedName() + "'");
                    out.println(breakTag + "dstUrl = '" + r.getDstUrl().getUrl() + "'");
                    out.println(breakTag + "srcUrl = '" + r.getSrcUrl().getUrl() + "'");
                    out.println(tagClose);
                }
            }
            else throw new Exception ("API " + apiStr + " not recognized");
            
            /*if (apiStr.equals("updateRequest")) {
                mySessionBean.updateRequest(
                    u.get(request, "src_url", true),
                    u.get(request, "dst_url", true),
                    u.get(request, "path", true),
                    u.get(request, "status", true),
                    Integer.parseInt(u.get(request, "progress", true)),
                    u.get(request, "detail", true)
                );
                out.println(reqTag + breakTag);
                out.println(breakTag + "message = 'Request updated'");
                out.println(tagClose);
            }*/
            
        } catch(Exception e) {
            out.println(errorTag);
            out.println(breakTag + "message = '" + e.getMessage() + "'");
            out.println(tagClose);
        } finally {
            out.println(footer);
            if (out != null) out.close();
        }  
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }
    
    
}
