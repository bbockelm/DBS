package fnal.gov.client.util;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Util {
    public Util() {
    }
    private void addVersion(Hashtable table) {
            table.put("apiversion", "DBS_1_0_7");
    }

    public String makeUrl(Hashtable table) throws Exception {
            addVersion(table);
            String url = "";
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;) {
                    String key = (String)e.nextElement();
                    url += key + "=" + URLEncoder.encode(get(table, key)) + "&";
            }
            return url;
    }

    public boolean isException(String message) {
            if(message.indexOf("exception") == -1) return false;
            else return true;
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
    public boolean isNull(String pattern) {
            if(pattern == null) {
                    return true;
            }
            if(pattern.length() < 1 ) {
                    return true;
            }
            return false;
    }
    
    
    public Context getInitialContext() throws NamingException {
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        //environment.put(Context.PROVIDER_URL, "jnp://plasma.dhcp.fnal.gov:1099"); // remote machine IP
        return new InitialContext(environment);
    }
    
}
