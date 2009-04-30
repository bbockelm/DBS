package tom.ui.bean;

import java.util.List;
import java.util.ArrayList;

import javax.faces.component.html.HtmlForm;
import tom.ui.util.FacesUtils;

import org.richfaces.component.html.HtmlRichMessage;
import tom.dm.entity.RunDetail;
import tom.dm.entity.Job;
import tom.dm.entity.FileBase;
import tom.dm.entity.StreamerFile;


public class RunDetailBackingBean extends BaseBean {
	private HtmlForm form1;
	private HtmlRichMessage generalInputMessage;
	private boolean loadTable;
	private List<RunDetail> runDetails = new ArrayList<RunDetail>();
	private List<FileBase> unmergedFiles = new ArrayList<FileBase>();
	private List<FileBase> mergedFiles = new ArrayList<FileBase>();
	private List<StreamerFile> streamerFiles = new ArrayList<StreamerFile>();
	private List<Job> repackJobs = new ArrayList<Job>();
	private List<Job> mergedJobs = new ArrayList<Job>();
	private List<Job> recoJobs = new ArrayList<Job>();
	private List<Job> alcaSkimJobs = new ArrayList<Job>();
	private long runId;

	public void setRunId(long runId) {
		this.runId = runId;
		System.out.println("inside setRunId RUN ID is " + this.runId);
		this.loadTable = true;
		this.init();
	}
	public long getRunId() {
		return this.runId;
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
	public  List<RunDetail> getRunDetails() {
		Object o = FacesUtils.getRequestParameter("runId");
		if (o == null) return this.runDetails;
		long runIdFromUrl = Long.valueOf((String)o);
		o = FacesUtils.getRequestParameter("reload");
		if((runIdFromUrl != this.runId) || (o != null)) {
			this.loadTable = true;
			this.init();
		}
		return this.runDetails;
	}
	public  List<FileBase> getMergedFiles() {
		return this.mergedFiles;
	}
	public  List<FileBase> getUnmergedFiles() {
		return this.unmergedFiles;
	}
	public  List<StreamerFile> getStreamerFiles() {
		return this.streamerFiles;
	}
	public  List<Job> getRepackJobs() {
		return this.repackJobs;
	}
	public  List<Job> getMergedJobs() {
		return this.mergedJobs;
	}
	public  List<Job> getRecoJobs() {
		return this.recoJobs;
	}
	public  List<Job> getAlcaSkimJobs() {
		return this.alcaSkimJobs;
	}

	protected void init() {
		System.out.println("PARAM runId " + FacesUtils.getRequestParameter("runId"));
		Object o = FacesUtils.getRequestParameter("runId");
                if (o != null)	this.runId = Long.valueOf((String)o);
		System.out.println("RUN ID is " + this.runId);
		System.out.println("loadTable is " + this.loadTable);
		if(this.loadTable) {
			try {
				
				this.runDetails = getServiceLocator().getRunService().listRunDetail(this.runId);
				this.mergedFiles = getServiceLocator().getFileService().listMergedFilesByRun(this.runId);
				this.unmergedFiles = getServiceLocator().getFileService().listUnmergedFilesByRun(this.runId);
				this.streamerFiles = getServiceLocator().getFileService().listStreamerFilesByRun(this.runId);
				this.repackJobs = getServiceLocator().getJobService().listRepackJobsByRun(this.runId);
				this.mergedJobs = getServiceLocator().getJobService().listMergedJobsByRun(this.runId);
				this.recoJobs = getServiceLocator().getJobService().listRecoJobsByRun(this.runId);
				this.alcaSkimJobs = getServiceLocator().getJobService().listAlcaSkimJobsByRun(this.runId);
				this.loadTable = false;
			} catch (Exception e) {
				this.loadTable = true;
			}
		}
	}



}
