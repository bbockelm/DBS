package tom.ui.bean;

import java.util.List;
import java.util.ArrayList;

import javax.faces.component.html.HtmlForm;

import org.richfaces.component.html.HtmlRichMessage;
import tom.dm.entity.FileBase;
import tom.dm.entity.StreamerFile;


public class FileDetailBackingBean extends BaseBean {
	private HtmlForm form1;
	private HtmlRichMessage generalInputMessage;
	private boolean loadTable;
	private List<FileBase> files = new ArrayList<FileBase>();
	private List<StreamerFile> streamerFiles = new ArrayList<StreamerFile>();
	private long jobId;
	private String navOutput;

	public void setJobId(long jobId) {
		this.jobId = jobId;
		this.loadTable = true;
		this.init();
	}
	public void setNavOutput(String navOutput) {
		this.navOutput = navOutput;
	}
	public long getJobId() {
		return this.jobId;
	}
	public String getNavOutput() {
		return this.navOutput;
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
	public  List<FileBase> getFiles() {
		return this.files;
	}
	public  List<StreamerFile> getStreamerFiles() {
		return this.streamerFiles;
	}
	protected void init() {
		System.out.println("JOB ID is " + this.jobId);
		System.out.println("loadTable is " + this.loadTable);
		if(this.loadTable) {
			try {
				
				this.files = getServiceLocator().getFileService().listFilesByJob(this.jobId);
				this.streamerFiles = getServiceLocator().getFileService().listStreamerFilesByJob(this.jobId);
				this.loadTable = false;
			} catch (Exception e) {
				this.loadTable = true;
			}
		}
	}
	public String fileAction() {
		return this.navOutput;
	}
}
