package tom.dm.dao.jdbc;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.RowMapper;

import tom.dm.dao.JobDao;
import tom.dm.exception.JobException;
import tom.dm.entity.Job;



public class JobDaoImpl extends JdbcDaoSupport implements JobDao {
	public JobDaoImpl() {
		super();
	}
	//FIXME implement these methods when queries are known
	public List<Job> listRepackMergedAcquiredJobs() throws JobException{ return new ArrayList<Job>();}
	public List<Job> listRecoMergedAcquiredJobs() throws JobException{ return new ArrayList<Job>();}
	public List<Job> listAlcaSkimMergedAcquiredJobs() throws JobException{ return new ArrayList<Job>();}
	public List<Job> listRecoAcquiredJobs() throws JobException{ return new ArrayList<Job>();}
	public List<Job> listAlcaSkimAcquiredJobs() throws JobException{ return new ArrayList<Job>();}
	public List<Job> listRepackRunningJobs() throws JobException{ return new ArrayList<Job>();}

	public  List<Job> listJobsByRun(final String name, final long runId) throws JobException{

		String sql = "SELECT DISTINCT wmbs_job.id as JobId, count(distinct wmbs_job_assoc.fileid) as NoOfFiles, " +
			"wmbs_job.name AS Name, last_update as DefinitionTime,  " +
			"primary_dataset.name AS Dataset,  'acquired' as Status FROM wmbs_job  " +
				"INNER JOIN wmbs_job_assoc ON wmbs_job.id = wmbs_job_assoc.job  " +
				"INNER JOIN wmbs_file_dataset_path_assoc ON wmbs_file_dataset_path_assoc.file_id = wmbs_job_assoc.fileid " +
				"INNER JOIN dataset_path ON dataset_path.id = wmbs_file_dataset_path_assoc.dataset_path_id " +
				"INNER JOIN primary_dataset ON primary_dataset.id = dataset_path.primary_dataset " +
				"INNER JOIN wmbs_file_runlumi_map ON wmbs_job_assoc.fileid = wmbs_file_runlumi_map.fileid  " +
				"INNER JOIN wmbs_group_job_acquired ON wmbs_job.id = wmbs_group_job_acquired.job  " +
				"WHERE wmbs_job.name LIKE ? AND wmbs_file_runlumi_map.run = ? " +
			"GROUP BY wmbs_job.id, wmbs_job.name, last_update, primary_dataset.name " +
			"UNION " +
			"SELECT DISTINCT wmbs_job.id as JobId, count(distinct wmbs_job_assoc.fileid) as NoOfFiles, " + 
			"wmbs_job.name AS Name, last_update as DefinitionTime,  " +
			"primary_dataset.name AS Dataset,  'completed' as Status FROM wmbs_job  " +
				"INNER JOIN wmbs_job_assoc ON wmbs_job.id = wmbs_job_assoc.job  " +
				"INNER JOIN wmbs_file_dataset_path_assoc ON wmbs_file_dataset_path_assoc.file_id = wmbs_job_assoc.fileid " +
				"INNER JOIN dataset_path ON dataset_path.id = wmbs_file_dataset_path_assoc.dataset_path_id " +
				"INNER JOIN primary_dataset ON primary_dataset.id = dataset_path.primary_dataset " +
				"INNER JOIN wmbs_file_runlumi_map ON wmbs_job_assoc.fileid = wmbs_file_runlumi_map.fileid  " +
				"INNER JOIN wmbs_group_job_complete ON wmbs_job.id = wmbs_group_job_complete.job  " +
			"WHERE wmbs_job.name LIKE ? AND wmbs_file_runlumi_map.run = ? " +
			"GROUP BY wmbs_job.id, wmbs_job.name, last_update, primary_dataset.name " +
			"UNION " +
			"SELECT DISTINCT wmbs_job.id as JobId, count(distinct wmbs_job_assoc.fileid) as NoOfFiles,  " +
			"wmbs_job.name AS Name, last_update as DefinitionTime,  " +
			"primary_dataset.name AS Dataset,  'failed' as Status FROM wmbs_job  " +
				"INNER JOIN wmbs_job_assoc ON wmbs_job.id = wmbs_job_assoc.job  " +
				"INNER JOIN wmbs_file_dataset_path_assoc ON wmbs_file_dataset_path_assoc.file_id = wmbs_job_assoc.fileid " +
				"INNER JOIN dataset_path ON dataset_path.id = wmbs_file_dataset_path_assoc.dataset_path_id " +
				"INNER JOIN primary_dataset ON primary_dataset.id = dataset_path.primary_dataset " +
				"INNER JOIN wmbs_file_runlumi_map ON wmbs_job_assoc.fileid = wmbs_file_runlumi_map.fileid  " +
				"INNER JOIN wmbs_group_job_failed ON wmbs_job.id = wmbs_group_job_failed.job  " +
			"WHERE wmbs_job.name LIKE ? AND wmbs_file_runlumi_map.run = ? " +
			"GROUP BY wmbs_job.id, wmbs_job.name, last_update, primary_dataset.name";

		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("Sql = " + sql);
		System.out.println("________________________________________________________________________\n\n" );
      		return getJdbcTemplate().query(sql, 
						new Object[] {name, runId, name, runId, name, runId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								Job job = new Job();
								job.setJobId(rs.getLong("JobId"));
								job.setNumberOfFiles(rs.getLong("NoOfFiles"));
								job.setDataset(rs.getString("Dataset"));
								job.setName(rs.getString("Name"));
								job.setStatus(rs.getString("Status"));
								job.setDefinitionTime(new Date(rs.getLong("DefinitionTime") * 1000));
								return job;
							}
						});
	}

    

	public  List<Job> listRepackJobsByRun(final long runId) throws JobException {
		String sql ="SELECT JOB_ID as JobId,  job_status.status AS Status, definition_time as DefinitionTime, " +
			"count(distinct job_streamer_dataset_assoc.streamer_id) AS NoOfFIles " +
			"FROM repack_job_def " +
				"INNER JOIN job_status ON repack_job_def.job_status = job_status.id " +
				"INNER JOIN job_streamer_dataset_assoc ON job_streamer_dataset_assoc.job_id = repack_job_def.job_id " +
				"INNER JOIN streamer ON job_streamer_dataset_assoc.streamer_id = streamer.streamer_id " +
				"WHERE streamer.run_id = ? " +
			"GROUP BY JOB_ID,  job_status.status, definition_time"; 


		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("Sql = " + sql);
		System.out.println("________________________________________________________________________\n\n" );
      		return getJdbcTemplate().query(sql, 
						new Object[] {runId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								Job job = new Job();
								job.setJobId(rs.getLong("JobId"));
								job.setNumberOfFiles(rs.getLong("NoOfFiles"));
								job.setStatus(rs.getString("Status"));
								job.setDefinitionTime(new Date(rs.getLong("DefinitionTime") * 1000));
								return job;
							}
						});

	}

}

