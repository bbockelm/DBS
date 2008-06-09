package fnal.gov.ejb.md;

import fnal.gov.client.Start;
import fnal.gov.ejb.mail.MailHelper;
import fnal.gov.ejb.session.MSSessionEJBLocal;

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
     propertyValue="queue/mdb")
 })
public class MSMessageDrivenEJBBean implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    @EJB
    private MSSessionEJBLocal ejbObj;
    @Resource(mappedName="java:/Mail")
    private Session session; 
    private MailHelper mh;
    
    public void onMessage(Message message) {
        
        try{
            System.out.println("Message recived ");
            if (message instanceof TextMessage) {
                TextMessage tMsg = (TextMessage) message;
                String msgStr = tMsg.getText();
                if(msgStr.equals("newRequest")) {
                    System.out.println("fetching request");
                    (new Start()).getAllPendingRequest(ejbObj);
                }
            } else if(message instanceof MapMessage) {
                System.out.println("This is a instance of MapMessage");
                MapMessage mMsg = (MapMessage) message;
                mh = new MailHelper(session);
                System.out.println("seinfing mesage ");
                mh.sendMessage(mMsg.getString("recipient"),
                                mMsg.getString("subject"),
                                mMsg.getString("body"));
                System.out.println("rep " + mMsg.getString("recipient"));   
                System.out.println("sub " + mMsg.getString("subject"));   
                System.out.println("body " + mMsg.getString("body"));   
            }
            System.out.println("Finsihed onMessage");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            //mdc.setRollbackOnly();
        }
    }
}
