package gov.fnal.nvs.dm.service;

import java.util.List;
import gov.fnal.nvs.dm.exception.ValidateException;
import gov.fnal.nvs.dm.entity.NameObject;

public interface MainService {
	public List<NameObject> validate(String name, String type) throws ValidateException;
}
