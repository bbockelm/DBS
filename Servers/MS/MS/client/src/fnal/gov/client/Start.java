package fnal.gov.client;

import fnal.gov.client.util.Util;

import fnal.gov.ejb.entity.Request;
import fnal.gov.ejb.session.MSSessionEJB;


import fnal.gov.ejb.session.MSSessionEJBLocal;

import java.util.List;


public class Start {

        /*public void getAllPendingRequest(MSSessionEJB ejbObj) throws Exception {
                
                
                /*Util u = new Util();
                Object obj = u.getInitialContext().lookup("ms/MSSessionEJB/remote");
                System.out.println("class of obj is : "+obj);
                MSSessionEJB ejbObj = (MSSessionEJB)obj;*/
          /*      String srcUrl = "";
                String dstUrl = "";
                String path = "";
                try {
                    List<Request> rList = ejbObj.getRequestByStatus("Queued");
                    for(int i = 0 ; i != rList.size(); ++i) {
                        Request r = rList.get(i);
                        srcUrl = r.getSrcUrl().getUrl();
                        dstUrl = r.getDstUrl().getUrl();
                        path = r.getPath();
                        ejbObj.updateRequest(srcUrl, dstUrl, path, "InProgress", null, "Transferring..");
                        
                        System.out.println(  "id = '" + r.getId() + "'");    
                        System.out.println(  "detail = '" + r.getDetail() + "'");
                        System.out.println(  "notify = '" + r.getNotify() + "'");
                        System.out.println(  "path = '" + r.getPath() + "'");
                        System.out.println(  "progress = '" + r.getProgress() + "'");
                        System.out.println(  "status = '" + r.getStatus() + "'");
                        System.out.println(  "force = '" + r.getWithForce() + "'");
                        System.out.println(  "parents = '" + r.getWithParents() + "'");
                        System.out.println(  "dn = '" + r.getPerson().getDistinguishedName() + "'");
                        System.out.println(  "dstUrl = '" + r.getDstUrl().getUrl() + "'");
                        System.out.println(  "srcUrl = '" + r.getSrcUrl().getUrl() + "'");
                        DbsWebApi dwApi = new DbsWebApi("http://cmssrv48.fnal.gov:8282/DBS/servlet/DBSServlet");
                        boolean withParents = true;
                        boolean withForce = true;
                        if(r.getWithParents().equals("n")) withParents = false;
                        if(r.getWithForce().equals("n")) withForce = false;
                        dwApi.migrateDataset(srcUrl, dstUrl, path, withParents, withForce);
                        ejbObj.updateRequest(srcUrl, dstUrl, path, "Finished", 100, "Migration is Done");
                    }
                }catch(Exception e) {
                        ejbObj.updateRequest(srcUrl, dstUrl, path, "Halted", null, e.toString().replace('\'',' ').replace('<','[').replace('>',']'));
                        e.printStackTrace();
                } 
        }*/



    public void getAllPendingRequest(MSSessionEJBLocal ejbObj) throws Exception {
            
            String srcUrl = "";
            String dstUrl = "";
            String path = "";
            try {
                List<Request> rList = ejbObj.getRequestByStatus("Queued");
                for(int i = 0 ; i != rList.size(); ++i) {
                    Request r = rList.get(i);
                    srcUrl = r.getSrcUrl().getUrl();
                    dstUrl = r.getDstUrl().getUrl();
                    path = r.getPath();
                    ejbObj.updateRequest(srcUrl, dstUrl, path, "InProgress", null, "Transferring..");
                    
                    System.out.println(  "id = '" + r.getId() + "'");    
                    System.out.println(  "detail = '" + r.getDetail() + "'");
                    System.out.println(  "notify = '" + r.getNotify() + "'");
                    System.out.println(  "path = '" + r.getPath() + "'");
                    System.out.println(  "progress = '" + r.getProgress() + "'");
                    System.out.println(  "status = '" + r.getStatus() + "'");
                    System.out.println(  "force = '" + r.getWithForce() + "'");
                    System.out.println(  "parents = '" + r.getWithParents() + "'");
                    System.out.println(  "dn = '" + r.getPerson().getDistinguishedName() + "'");
                    System.out.println(  "dstUrl = '" + r.getDstUrl().getUrl() + "'");
                    System.out.println(  "srcUrl = '" + r.getSrcUrl().getUrl() + "'");
                    //DbsWebApi dwApi = new DbsWebApi("http://cmssrv48.fnal.gov:8282/DBS/servlet/DBSServlet");
                    DbsWebMigrateApi dwApi = new DbsWebMigrateApi("http://cmssrv48.fnal.gov:8282/DBS/servlet/DBSServlet", ejbObj);
                    boolean withParents = true;
                    boolean withForce = true;
                    if(r.getWithParents().equals("n")) withParents = false;
                    if(r.getWithForce().equals("n")) withForce = false;
                    dwApi.migrateDataset(srcUrl, dstUrl, path, withParents, withForce);
                    ejbObj.updateRequest(srcUrl, dstUrl, path, "Finished", 100, "Migration is Done");
                }
            }catch(Exception e) {
                    ejbObj.updateRequest(srcUrl, dstUrl, path, "Halted", null, e.toString().replace('\'',' ').replace('<','[').replace('>',']'));
                    e.printStackTrace();
            } 
    }
    
        

        /*public static void main(String args[]){
                Start start = new Start();
                try {
                    Util u = new Util();
                    Object obj = u.getInitialContext().lookup("ms/MSSessionEJB/remote");
                    System.out.println("class of obj is : "+obj);
                    MSSessionEJB ejbObj = (MSSessionEJB)obj;
                    start.getAllPendingRequest(ejbObj);
                }catch(Exception e){
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                }
        }*/
}
