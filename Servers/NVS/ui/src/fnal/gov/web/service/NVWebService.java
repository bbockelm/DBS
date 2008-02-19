package fnal.gov.web.service;

import fnal.gov.session.NVSessionEJBLocal;
import fnal.gov.session.NameObject;

import fnal.gov.web.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class NVWebService {
    
    public NVWebService() {}
    
    @WebMethod(operationName = "validateWS")
    public List<NameObject> validateWS(@WebParam(name = "name") String name, @WebParam(name = "type") String type) throws Exception{
            Util u = new Util();
            Object obj = u.getEJB("nvs/NVSessionEJB/local");
            if(obj != null) {
                NVSessionEJBLocal myBean = (NVSessionEJBLocal) obj;
                return myBean.validate(name, type);
            }
            return new ArrayList();
        }
}
