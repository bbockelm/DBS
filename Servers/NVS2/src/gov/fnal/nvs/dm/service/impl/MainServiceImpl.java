package gov.fnal.nvs.dm.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.fnal.nvs.dm.exception.*;
import gov.fnal.nvs.dm.entity.NameObject;
import gov.fnal.nvs.dm.service.impl.util.LetterPairSimilarity;
import gov.fnal.nvs.dm.service.*;

public class MainServiceImpl implements MainService {
	private Log logger = LogFactory.getLog(this.getClass());
	private static final String SAFE_WORD = "[-\\w_\\.%]+";
	private TierService tierService;
	private PrimaryService primaryService;
	private ProcessedService processedService;
	private PhysicsService physicsService;
	
	public void setTierService(TierService tierService) {
		this.tierService = tierService;
	}
	public void setPrimaryService(PrimaryService primaryService) {
		this.primaryService = primaryService;
	}
	public void setProcessedService(ProcessedService processedService) {
		this.processedService = processedService;
	}
	public void setPhysicsService(PhysicsService physicsService) {
		this.physicsService = physicsService;
	}

	public List<NameObject> validate(String name, String type) throws ValidateException {
		this.logger.debug(("method validate()"));
		if (!Pattern.matches(SAFE_WORD, name)) {
			String msg = "Name has invalid characters";
			this.logger.error(msg);
			throw new ValidateException(msg);
		}
		try {
			List<NameObject> toReturn = new ArrayList<NameObject>();
			List<String> names = null;
			if (type.equals("Primary")) names = this.primaryService.listNames();
			else if (type.equals("Processed")) names = this.processedService.listNames();
			else if (type.equals("Tier")) names = this.tierService.listNames();
			else if (type.equals("Physics")) names = this.physicsService.listNames();
			else {
				String msg = "Invalid Type " + type;
				this.logger.error(msg);
				throw new ValidateException(msg);
			}
			double threshold = 0.4;
			double similarity = 0;
			for(String aName: names) {
				 //System.out.println("Checking " + aName +  "  with " + name);
				 if ((similarity = LetterPairSimilarity.compareStrings(aName, name)) > threshold) 
					 toReturn.add(makeNO(aName, similarity));
			}	
			return toReturn;
		} catch(Exception e) {
			String msg = "Could not validate the name " + name + " and " + type;
			this.logger.error(msg, e);
			throw new ValidateException(msg, e);
		}
	}

	private NameObject makeNO(String name, double similarity) {
		NameObject no = new NameObject();
		no.setName(name);
		no.setSimilar(new Double(similarity * 100));
		return no;
	}
  

}
