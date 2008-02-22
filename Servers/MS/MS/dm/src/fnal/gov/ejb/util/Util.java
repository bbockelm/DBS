package fnal.gov.ejb.util;

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
}
