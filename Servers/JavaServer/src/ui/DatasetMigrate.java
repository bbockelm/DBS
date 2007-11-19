package ui;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import org.richfaces.component.html.HtmlRichMessage;
import org.richfaces.event.DropEvent;
import org.richfaces.component.html.HtmlDndParam;
import javax.faces.context.FacesContext;



import java.util.Vector;
import java.util.List;

import dm.DbsDataset;
import dbs.util.DBSUtil;
import ui.util.Util;
import ui.util.DbsWebApi;
import ui.util.JSFUtils;

public class DatasetMigrate {
	private HtmlInputText datasetInputText;
	private HtmlRichMessage generalInputMessage;
    	private HtmlDndParam instanceNameParam;
    	private HtmlDndParam datasetNameParam;
	private HtmlOutputText dstInstanceNameOutputText;
	private HtmlOutputText srcInstanceNameOutputText;
	private HtmlSelectManyCheckbox parameter;
	private Boolean manaulInputDisabled = new Boolean(true);
	//Util u ;
		
	public void DatasetMigrate(){
		//u = new Util();
	}
	
	public void setDatasetInputText(HtmlInputText datasetInputText) {
		this.datasetInputText = datasetInputText;
	}
	public HtmlInputText getDatasetInputText() {
		return datasetInputText;
	}
	
	public void setParameter(HtmlSelectManyCheckbox parameter) {
		this.parameter = parameter;
	}
	public HtmlSelectManyCheckbox getParameter() {
		return parameter;
	}

		
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     
      	public void setInstanceNameParam(HtmlDndParam instanceNameParam) {
		this.instanceNameParam = instanceNameParam;
    	}
    	public HtmlDndParam getInstanceNameParam() {
		return instanceNameParam;
    	}

      	public void setDatasetNameParam(HtmlDndParam datasetNameParam) {
		this.datasetNameParam = datasetNameParam;
    	}
    	public HtmlDndParam getDatasetNameParam() {
		return datasetNameParam;
    	}

	public void setDstInstanceNameOutputText(HtmlOutputText dstInstanceNameOutputText) {
		this.dstInstanceNameOutputText = dstInstanceNameOutputText;
    	}
    	public HtmlOutputText getDstInstanceNameOutputText() {
		return dstInstanceNameOutputText;
    	}

   	public void setSrcInstanceNameOutputText(HtmlOutputText srcInstanceNameOutputText) {
		this.srcInstanceNameOutputText = srcInstanceNameOutputText;
    	}
    	public HtmlOutputText getSrcInstanceNameOutputText() {
		return srcInstanceNameOutputText;
    	}

   	public void setManaulInputDisabled(Boolean manaulInputDisabled) {
		this.manaulInputDisabled = manaulInputDisabled;
    	}
    	public Boolean getManaulInputDisabled() {
		return manaulInputDisabled;
    	}

	
	public void processSrcInstanceDrop(DropEvent event) {
		String passedInstance = JSFUtils.getDropValue("instanceNameLabel");
		if(!passedInstance.equals((String)dstInstanceNameOutputText.getValue())) {
			srcInstanceNameOutputText.setValue(passedInstance);
		} else {
			(new Util()).setExceptionMessage("Destination DBS is already set to this instance", generalInputMessage);
		}
		resetManualInput();
	}

	public void processDstInstanceDrop(DropEvent event) {
		String passedInstance = JSFUtils.getDropValue("instanceNameLabel");
		if(!passedInstance.equals((String)srcInstanceNameOutputText.getValue())) {
			dstInstanceNameOutputText.setValue(passedInstance);
		} else {
			(new Util()).setExceptionMessage("Source DBS is already set to this instance", generalInputMessage);
		}
		resetManualInput();
     	}	
	private void resetManualInput() {
		if(!(DBSUtil.isNull((String)dstInstanceNameOutputText.getValue())) &&
			!(DBSUtil.isNull((String)srcInstanceNameOutputText.getValue())) ) {
			setManaulInputDisabled(new Boolean(false));
			JSFUtils.storeBeanValue("DatasetMigrate.manaulInputDisabled", new Boolean(false));
			//System.out.println("Undisabling it .....");
			Boolean a = (Boolean)JSFUtils.retriveBeanValue("DatasetMigrate.manaulInputDisabled");
			//System.out.println("bean value is " + a.booleanValue());
		}
		//else{
		//	System.out.println("Disabling it .....");
		//}
		System.out.println("dst name " + (String)dstInstanceNameOutputText.getValue());
		System.out.println("src name " + (String)srcInstanceNameOutputText.getValue());

	}

	public void processDstPathDrop(DropEvent event) {
		generalInputMessage.setPassedLabel("");
		String path = JSFUtils.getDropValue("pathLabel");
		migrateDataset(path);
	}


	private void migrateDataset(String path) {
		try {
			String srcUrl = (String)srcInstanceNameOutputText.getValue();
			String dstUrl = (String)dstInstanceNameOutputText.getValue();
			if(DBSUtil.isNull(srcUrl)) {
				(new Util()).setExceptionMessage("Set a Source DBS instance first", generalInputMessage);
				return;
			}
			if(DBSUtil.isNull(dstUrl)) {
				(new Util()).setExceptionMessage("Set a Destination DBS instance first", generalInputMessage);
				return;
			}
			DbsWebApi dwApi = new DbsWebApi(srcUrl);
			dwApi.setGeneralInputMessage(generalInputMessage);
			String[] paramValues = getParamValues();
			boolean parents = false;
			boolean force = false;
			print(paramValues);
			if (isIn(paramValues, "PARENTS")) parents = true;
			if (isIn(paramValues, "FORCE")) force = true;

			dwApi.migrateDataset(srcUrl, dstUrl, path, parents, force);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			(new Util()).setExceptionMessage(ex.getMessage(), generalInputMessage);
       		} 
	}

	
	private boolean isIn(String[] array, String key) {
		for (int i = 0 ; i != array.length; ++i ) {
			if (key.equals(array[i])) return true;
		}
		return false;
	}

	private String[] getParamValues() {
		String[] toReturn = new String[1];
		Object o = parameter.getValue();
		if (o != null) return (String[])o;
		return toReturn;
	}

	private void print(String[] params) {
		System.out.println("params len " + params.length);
			for (int i = 0 ; i != params.length; ++i ) {
				System.out.println("param at " + i  + " is  " + params[i]);
			}
	}
	

	
	public List<DbsDataset> getAllDatasets(String instanceUrl) {
		try {
			if(DBSUtil.isNull(instanceUrl)) return (new Vector());
			return (new DbsWebApi(instanceUrl)).listDatasetPaths();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			(new Util()).setExceptionMessage(ex.getMessage(), generalInputMessage);
       		} 

		return (new Vector());


	}
	
	public List<DbsDataset> getAllDstDatasets() {
		String instanceUrl = (String)dstInstanceNameOutputText.getValue();
		//System.out.println("DST Instance VALUE is ------------> " + instanceUrl);
		return (getAllDatasets(instanceUrl));
		
	}
	
	
	public List<DbsDataset> getAllSrcDatasets() {
		String instanceUrl = (String)srcInstanceNameOutputText.getValue();
		////System.out.println("SRC Instance is ------------> " + instanceUrl);
		return (getAllDatasets(instanceUrl));
	}
		

	public String migrateDatasetAction() {
			generalInputMessage.setPassedLabel("");
			print(getParamValues());
			migrateDataset((String)datasetInputText.getValue());
			/*Object o = datasetInputText.getValue();
			if(o == null) 	System.out.println("datasetInputText.getValue  is null");
			else {
				System.out.println("datasetInputText.getValue  is " + (String)datasetInputText.getValue());
			}*/

		return "";
	}

}
