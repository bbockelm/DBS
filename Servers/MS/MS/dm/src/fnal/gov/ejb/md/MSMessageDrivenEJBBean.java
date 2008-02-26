package fnal.gov.ejb.md;

import fnal.gov.client.Start;
import fnal.gov.ejb.session.MSSessionEJBLocal;

import javax.annotation.Resource;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import javax.ejb.MessageDrivenContext;

import javax.jms.Message;
import javax.jms.MessageListener;

 @MessageDriven(activationConfig =
 {
   @ActivationConfigProperty(propertyName="destinationType",
     propertyValue="javax.jms.Queue"),
   @ActivationConfigProperty(propertyName="destination",
     propertyValue="queue/mdb")
 })
public class MSMessageDrivenEJBBean implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    @EJB
    private MSSessionEJBLocal ejbObj;
    
    public void onMessage(Message message) {
        try{
            System.out.println("Message recived ");
            Start start = new Start();
            System.out.println("fetching request");
            start.getAllPendingRequest(ejbObj);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            //mdc.setRollbackOnly();
        }
    }
}
