package gov.fnal.ms.dm.md;

import gov.fnal.ms.dm.util.Start;
import gov.fnal.ms.dm.mail.MailHelper;
import gov.fnal.ms.dm.session.MSSessionEJBLocal;

import javax.annotation.Resource;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import javax.ejb.MessageDrivenContext;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import javax.mail.Session;

@MessageDriven(activationConfig =
 {
   @ActivationConfigProperty(propertyName="destinationType",
     propertyValue="javax.jms.Queue"),
   @ActivationConfigProperty(propertyName="destination",
     propertyValue="queue/requestmdb")
 })
public class RequestMessageDrivenBean implements MessageListener {
    //@Resource
    //private MessageDrivenContext mdc;
    @EJB
    private MSSessionEJBLocal ejbObj;
    
    public void onMessage(Message message) {
        
        try{
            System.out.println("Message recived ");
	if(message instanceof MapMessage) {
                System.out.println("This is a instance of MapMessage");
                MapMessage mMsg = (MapMessage) message;
                System.out.println("Request message ");
		long rId = mMsg.getLong("request");
		(new Start()).processRequest(rId, ejbObj);
            }
            System.out.println("Finsihed onMessage");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            //mdc.setRollbackOnly();
        }
    }
}
