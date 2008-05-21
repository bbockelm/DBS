package fnal.gov.rs.client.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Util {
    private String url;
    
    public Util(String url) {
        this.url = url;
    }
    
    public InitialContext getInitialContext() throws NamingException {
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        //environment.put(Context.PROVIDER_URL, "jnp://plasma.dhcp.fnal.gov:1099"); // remote machine IP
        environment.put(Context.PROVIDER_URL, url); // remote machine IP
        return new InitialContext(environment);
    }
    
    public Object getEJB(String jndiName) {
            Object object = null;
            try {
                InitialContext ctx = getInitialContext();
                object = ctx.lookup(jndiName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return object;
    }
}
