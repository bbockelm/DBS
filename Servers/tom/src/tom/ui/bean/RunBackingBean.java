package tom.ui.bean;

import java.util.List;
import java.util.ArrayList;

import javax.faces.component.html.HtmlForm;


import org.richfaces.component.html.HtmlRichMessage;
import tom.dm.entity.Run;
import tom.dm.entity.Job;
import tom.dm.entity.RepackedStat;
import tom.dm.entity.Stat;
import tom.ui.util.FacesUtils;


public class RunBackingBean extends BaseBean {
	private HtmlForm form1;
	private HtmlRichMessage generalInputMessage;
	private boolean loadTable;
	private List<Run> runs = new ArrayList<Run>();
	private List<Job> repackMergedAcquiredJobs = new ArrayList<Job>();
	private List<Job> recoMergedAcquiredJobs = new ArrayList<Job>();
	private List<Job> alcaSkimMergedAcquiredJobs = new ArrayList<Job>();
	private List<Job> recoAcquiredJobs = new ArrayList<Job>();
	private List<Job> alcaSkimAcquiredJobs = new ArrayList<Job>();
	private List<Job> repackRunningJobs = new ArrayList<Job>();
	private RepackedStat repackedStat;
	private Stat mergedStat;
	private Stat recoStat;
	private Stat alcaSkimStat;

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
	public  List<Run> getRuns() {
		Object o = FacesUtils.getRequestParameter("reload");
		if(o != null) {
			this.loadTable = false;
			this.init();
		}
		return this.runs;
	}
	public  List<Job> getRepackMergedAcquiredJobs() {
		return this.repackMergedAcquiredJobs;
	}
	public  List<Job> getRecoMergedAcquiredJobs() {
		return this.recoMergedAcquiredJobs;
	}
	public  List<Job> getAlcaSkimMergedAcquiredJobs() {
		return this.alcaSkimMergedAcquiredJobs;
	}
	public  List<Job> getRecoAcquiredJobs() {
		return this.recoAcquiredJobs;
	}
	public  List<Job> getAlcaSkimAcquiredJobs() {
		return this.alcaSkimAcquiredJobs;
	}
	public  List<Job> getRepackRunningJobs() {
		return this.repackRunningJobs;
	}
	public RepackedStat getRepackedStat() {
		return this.repackedStat;
	}
	public Stat getMergedStat() {
		return this.mergedStat;
	}
	public Stat getRecoStat() {
		return this.recoStat;
	}
	public Stat getAlcaSkimStat() {
		return this.alcaSkimStat;
	}

	protected void init() {
		if(!loadTable) {
			try {
				this.runs = getServiceLocator().getRunService().listRuns();
				this.repackMergedAcquiredJobs = getServiceLocator().getJobService().listRepackMergedAcquiredJobs();
				this.recoMergedAcquiredJobs = getServiceLocator().getJobService().listRecoMergedAcquiredJobs();
				this.alcaSkimMergedAcquiredJobs = getServiceLocator().getJobService().listAlcaSkimMergedAcquiredJobs();
				this.recoAcquiredJobs = getServiceLocator().getJobService().listRecoAcquiredJobs();
				this.alcaSkimAcquiredJobs = getServiceLocator().getJobService().listAlcaSkimAcquiredJobs();
				this.repackRunningJobs = getServiceLocator().getJobService().listRepackRunningJobs();
				this.mergedStat = getServiceLocator().getStatService().getMergedStat();
				this.repackedStat = getServiceLocator().getStatService().getRepackedStat();
				this.recoStat = getServiceLocator().getStatService().getRecoStat();
				this.alcaSkimStat = getServiceLocator().getStatService().getAlcaSkimStat();
				loadTable = true;
			} catch (Exception e) {
				loadTable = false;
			}
		}
	}

	public String reload() {
		System.out.println("Invaliding the session BEGIN");
		FacesUtils.invalidateSession();
		System.out.println("Invaliding the session DONE");
		return "";
	}
}
