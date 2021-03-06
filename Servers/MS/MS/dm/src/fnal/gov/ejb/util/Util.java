package fnal.gov.ejb.util;

import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import javax.naming.InitialContext;

public class Util {
    public Util() {
    }
    
    public boolean isNull(String pattern) {
            if(pattern == null) {
                    return true;
            }
            if(pattern.length() < 1 ) {
                    return true;
            }
            return false;
    }
    
    public boolean isNull(Object pattern) {
            if(pattern == null) {
                    return true;
            }
            return false;
    }
    
    
    /*public Object getJNDIObj(String jndiName) {
            Object object = null;
            try {
                InitialContext ctx = new InitialContext();
                object = ctx.lookup(jndiName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return object;
    }*/
    
    //public void sendMsg(QueueConnectionFactory factory, Queue queue, String strMsg) throws Exception{
    public void sendMsg(QueueConnection conn, Queue queue, String strMsg) throws Exception{
        QueueSession session = conn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
        TextMessage msg = session.createTextMessage(strMsg);
        session.createSender(queue).send(msg);        
    }

    public void sendMsg(QueueSession session, Queue queue, MapMessage mapMsg) throws Exception{
        session.createSender(queue).send(mapMsg);        
    }
}
