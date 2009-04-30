package tom.ui.bean;

import java.util.List;
import java.util.ArrayList;

import javax.faces.component.html.HtmlForm;

import org.richfaces.component.html.HtmlRichMessage;
import tom.ui.util.FacesUtils;
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
		Object o = FacesUtils.getRequestParameter("jobId");
		if (o == null) return this.streamerFiles;
		long jobIdFromUrl = Long.valueOf((String)o);
		o = FacesUtils.getRequestParameter("reload");
		if((jobIdFromUrl != this.jobId) || (o != null)) {
			this.loadTable = true;
			this.init();
		}

		return this.streamerFiles;
	}
	protected void init() {
		Object o = FacesUtils.getRequestParameter("jobId");
                if (o != null)	this.jobId = Long.valueOf((String)o);

		System.out.println("JOB ID is " + this.jobId);
		System.out.println("loadTable is " + this.loadTable);
		//System.out.println("In streamer file back bean page is " + ((javax.servlet.http.HttpServletRequest)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL() + "\n\n");
		//System.out.println("VIEW ID " + javax.faces.context.FacesContext.getCurrentInstance().getViewRoot().getViewId());
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
