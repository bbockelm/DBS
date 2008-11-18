package gov.fnal.nvs.dm.service;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;

import gov.fnal.nvs.dm.exception.ValidateException;
import gov.fnal.nvs.dm.entity.NameObject;


@WebService
public interface MainServiceWS {
	@WebMethod
	public List<NameObject> validate(String name, String type) throws ValidateException;
}
