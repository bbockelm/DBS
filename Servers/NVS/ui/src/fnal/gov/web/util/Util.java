package fnal.gov.web.util;

import javax.naming.InitialContext;

public class Util {
    public Util() {
    }
    
    public Object getEJB(String jndiName) {
            Object object = null;
            try {
                InitialContext ctx = new InitialContext();
                object = ctx.lookup(jndiName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return object;
    }
}
