package tom.ui.bean;

import java.util.List;
import java.util.ArrayList;

import javax.faces.component.html.HtmlForm;

import org.richfaces.component.html.HtmlRichMessage;
import tom.dm.entity.StreamerFile;


public class StreamerFileDetailBackingBean extends BaseBean {
	private HtmlForm form1;
	private HtmlRichMessage generalInputMessage;
	private boolean loadTable;
	private List<StreamerFile> streamerFiles = new ArrayList<StreamerFile>();
	private long jobId;

	public void setJobId(long jobId) {
		this.jobId = jobId;
		this.loadTable = true;
		this.init();
	}
	public long getJobId() {
		return this.jobId;
	}

	public void setForm1(HtmlForm form1) {
		this.form1 = form1;
	}
	public HtmlForm getForm1() {
		return form1;
	}
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
	}
	public void setLoadTable(Boolean loadTable) {
		this.loadTable = loadTable;
	}
	public Boolean getLoadTable() {
		return loadTable;
	}
	public  List<StreamerFile> getStreamerFiles() {
		return this.streamerFiles;
	}
	protected void init() {
		System.out.println("JOB ID is " + this.jobId);
		System.out.println("loadTable is " + this.loadTable);
		if(this.loadTable) {
			try {
				
				this.streamerFiles = getServiceLocator().getFileService().listStreamerFilesByJob(this.jobId);
				this.loadTable = false;
			} catch (Exception e) {
				this.loadTable = true;
			}
		}
	}
}
