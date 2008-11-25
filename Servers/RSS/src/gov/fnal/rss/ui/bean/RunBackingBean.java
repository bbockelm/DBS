package gov.fnal.rss.ui.bean;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import gov.fnal.rss.dm.exception.RunSeqException;
import gov.fnal.rss.dm.exception.DuplicateRunSeqException;


public class RunBackingBean extends BaseBean {
	private HtmlForm form1;
	private HtmlInputText nameCreateInputText;
	private HtmlInputText nameCurrInputText;
	private HtmlInputText nameNextInputText;
	private HtmlOutputText createRunSequenceText;
	private HtmlOutputText nextRunNumberText;
	private HtmlOutputText currRunNumberText;
	public void setForm1(HtmlForm form1) {
		this.form1 = form1;
	}
	public HtmlForm getForm1() {
		return form1;
	}
	public void setNameCreateInputText(HtmlInputText nameCreateInputText) {
		this.nameCreateInputText = nameCreateInputText;
	}
	public HtmlInputText getNameCreateInputText() {
		return nameCreateInputText;
	}

	public void setNameCurrInputText(HtmlInputText nameCurrInputText) {
		this.nameCurrInputText = nameCurrInputText;
	}
	public HtmlInputText getNameCurrInputText() {
		return nameCurrInputText;
	}

	public void setNameNextInputText(HtmlInputText nameNextInputText) {
		this.nameNextInputText = nameNextInputText;
	}
	public HtmlInputText getNameNextInputText() {
		return nameNextInputText;
	}

	public void setCreateRunSequenceText(HtmlOutputText createRunSequenceText) {
		this.createRunSequenceText = createRunSequenceText;
	}
	public HtmlOutputText getCreateRunSequenceText() {
		return createRunSequenceText;
	}

	public void setNextRunNumberText(HtmlOutputText nextRunNumberText) {
		this.nextRunNumberText = nextRunNumberText;
	}
	public HtmlOutputText getNextRunNumberText() {
		return nextRunNumberText;
	}

	public void setCurrRunNumberText(HtmlOutputText currRunNumberText) {
		this.currRunNumberText = currRunNumberText;
	}
	public HtmlOutputText getCurrRunNumberText() {
		return currRunNumberText;
	}

	public String createRunSequenceAction() {
		String msg = "Sequence Created successfully";
		try {
			getServiceLocator().getRunSeqService().createRunSequence((String)nameCreateInputText.getValue(), 0,0 );
		} catch (DuplicateRunSeqException dre) {
			msg = dre.getMessage();
		} catch (RunSeqException re) {
			msg =  re.getMessage();
		} catch (Exception e) {
			msg =  e.getMessage();
		}
		createRunSequenceText.setValue(msg);
		return "#";
	}

	public String getNextRunNumberAction() {
		String msg = "0";
		try {
			msg = String.valueOf(getServiceLocator().getRunSeqService().getNextRunNumber((String)nameNextInputText.getValue()));
		} catch (RunSeqException re) {
			msg =  re.getMessage();
		} catch (Exception e) {
			msg =  e.getMessage();
		}
		nextRunNumberText.setValue(msg);
		return "#";
	}

	public String getCurrRunNumberAction() {
		String msg = "0";
		try {
			msg = String.valueOf(getServiceLocator().getRunSeqService().getCurrRunNumber((String)nameCurrInputText.getValue()));
		} catch (RunSeqException re) {
			msg =  re.getMessage();
		} catch (Exception e) {
			msg =  e.getMessage();
		}
		currRunNumberText.setValue(msg);
		return "#";
	}

}
