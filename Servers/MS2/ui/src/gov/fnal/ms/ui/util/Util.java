package gov.fnal.ms.ui.util;

import java.util.Hashtable;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import javax.naming.InitialContext;

import javax.servlet.http.HttpServletRequest;

public class Util {
    public Util() {
    }
    
    public Object getJNDIObj(String jndiName) {
            Object object = null;
            try {
                InitialContext ctx = new InitialContext();
                object = ctx.lookup(jndiName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return object;
    }
    
    public void sendMsg(QueueConnectionFactory factory, Queue queue, String strMsg) throws Exception{
        QueueConnection conn = null;
        try {
            conn = factory.createQueueConnection();
            QueueSession session = conn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
            TextMessage msg = session.createTextMessage(strMsg);
            session.createSender(queue).send(msg);
        
        } finally {
            if (conn != null ) conn.close();
        }
    }
    public String get(Hashtable table, String key, boolean excep) throws Exception {
            String value = "";
            if ( isNull(value = get(table, key)) ) 
                    if (excep) throw new Exception("Mandatory data missing. Null Fields. Expected a valid " + key);
            
            return value;
    }
    
    
    public String get(HttpServletRequest request, String key, boolean excep) throws Exception {
            String value = "";
            if ( isNull(value = request.getParameter(key)) ) 
                    if (excep) throw new Exception("Mandatory data missing. Null Fields. Expected a valid " + key);
            
            return value;
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
    
    
    public String get(Hashtable table, String key) {
            if(key == null ||  table == null) {
                    return "";
            }
            if(!table.containsKey(key)) {
                    return "";
            }
            Object tmp = table.get(key);
            if(tmp == null) {
                    return "";
            }
            return (String)tmp;
    }

    public String format(String in){
        in= in.replace('\'',' ');
        in = in.replace('<',' ');
        in = in.replace('>',' ');
        in = in.replace('=',' ');
        in = in.replace('?',' ');
        in = in.replace('&',' ');
        return in;
    }

}
