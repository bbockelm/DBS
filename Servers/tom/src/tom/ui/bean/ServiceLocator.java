package tom.ui.bean;
import tom.dm.service.*;

public interface ServiceLocator {
	public RunService getRunService();
	public JobService getJobService();
	public FileService getFileService();
	public StatService getStatService();
}
