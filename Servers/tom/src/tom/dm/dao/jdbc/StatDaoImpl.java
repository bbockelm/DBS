package tom.dm.dao.jdbc;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import tom.dm.dao.StatDao;
import tom.dm.entity.Stat;
//import tom.dm.entity.JobStat;
import tom.dm.entity.RepackedStat;



public class StatDaoImpl extends JdbcDaoSupport implements StatDao {
	public StatDaoImpl() {
		super();
	}
	public  Stat getStat(final String name){
		String sql = "SELECT " +
			"(SELECT count(wmbs_job.id) FROM wmbs_job INNER JOIN wmbs_group_job_acquired ON wmbs_job.id = wmbs_group_job_acquired.job WHERE wmbs_job.name LIKE ? ) AS Acquired, " +
			"(SELECT count(wmbs_job.id) FROM wmbs_job INNER JOIN wmbs_group_job_complete ON wmbs_job.id = wmbs_group_job_complete.job WHERE wmbs_job.name LIKE ? ) AS Success, " +
			"(SELECT count(wmbs_job.id) FROM wmbs_job INNER JOIN wmbs_group_job_failed ON wmbs_job.id = wmbs_group_job_failed.job WHERE wmbs_job.name LIKE ? ) AS Failed " +
			"FROM DUAL ";
		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("Sql = " + sql);
		System.out.println("________________________________________________________________________\n\n" );
		return (Stat) getJdbcTemplate().queryForObject(sql, 
						new Object[] {name, name, name},
						new ParameterizedRowMapper<Stat>() {
							public Stat mapRow(ResultSet rs, int rowNum) throws SQLException {
								Stat stat = new Stat();
								stat.setSuccessfulJobs(rs.getLong("Success"));
								stat.setFailedJobs(rs.getLong("Failed"));
								stat.setAcquiredJobs(rs.getLong("Acquired"));
								return stat;
							}
						});

	}
	public RepackedStat getRepackedStat() {
		String sql = "SELECT " +
			"(SELECT COUNT(st.status) FROM repack_job_def J, job_status st WHERE J.job_status = ST.id AND st.status = ?) AS Success, " +
			"(SELECT COUNT(st.status) FROM repack_job_def J, job_status st WHERE J.job_status = ST.id AND st.status = ?) AS Failed, " +
			"(SELECT COUNT(st.status) FROM repack_job_def J, job_status st WHERE J.job_status = ST.id AND st.status = ?) AS New, " +
			"(SELECT COUNT(st.status) FROM repack_job_def J, job_status st WHERE J.job_status = ST.id AND st.status = ?) AS Used  " +
			"FROM Dual ";
		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("Sql = " + sql);
		System.out.println("________________________________________________________________________\n\n" );
				
		return (RepackedStat) getJdbcTemplate().queryForObject(sql, 
						new Object[] {"Success", "Failure", "New", "Used"},
						new ParameterizedRowMapper<RepackedStat>() {
							public RepackedStat mapRow(ResultSet rs, int rowNum) throws SQLException {
								RepackedStat stat = new RepackedStat();
								stat.setSuccessfulJobs(rs.getLong("Success"));
								stat.setFailedJobs(rs.getLong("Failed"));
								stat.setUsedJobs(rs.getLong("New"));
								stat.setNewJobs(rs.getLong("Used"));
								return stat;
							}
						});
	}

	/*
	public  List<JobStat> listStatsByRun(final String name, final long runId){
		String sql = "SELECT DISTINCT Id,Name,'acquired' as Status FROM wmbs_job " +
				"INNER JOIN wmbs_job_assoc ON wmbs_job.id = wmbs_job_assoc.job " +
				"INNER JOIN wmbs_file_runlumi_map ON wmbs_job_assoc.fileid = wmbs_file_runlumi_map.fileid " +
				"INNER JOIN wmbs_group_job_acquired ON wmbs_job.id = wmbs_group_job_acquired.job " +
			"WHERE wmbs_job.name LIKE ? " +
			"AND wmbs_file_runlumi_map.run = ? " +
			"UNION " +
			"SELECT DISTINCT Id,Name,'completed' as Status FROM wmbs_job " +
				"INNER JOIN wmbs_job_assoc ON wmbs_job.id = wmbs_job_assoc.job " + 
				"INNER JOIN wmbs_file_runlumi_map ON wmbs_job_assoc.fileid = wmbs_file_runlumi_map.fileid  " +
				"INNER JOIN wmbs_group_job_complete ON wmbs_job.id = wmbs_group_job_complete.job  " +
			"WHERE wmbs_job.name LIKE ? " +
			"AND wmbs_file_runlumi_map.run = ? " +
			"UNION " +
			"SELECT DISTINCT Id, Name,'failed' as Status FROM wmbs_job  " +
				"INNER JOIN wmbs_job_assoc ON wmbs_job.id = wmbs_job_assoc.job  " +
				"INNER JOIN wmbs_file_runlumi_map ON wmbs_job_assoc.fileid = wmbs_file_runlumi_map.fileid  " +
				"INNER JOIN wmbs_group_job_failed ON wmbs_job.id = wmbs_group_job_failed.job  " +
			"WHERE wmbs_job.name LIKE ?  " +
			"AND wmbs_file_runlumi_map.run = ?";

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

		return getJdbcTemplate().query(sql, 
						new Object[] {name, runId, name, runId, name, runId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								JobStat stat = new JobStat();
								stat.setJobId(rs.getLong("Id"));
								stat.setName(rs.getString("Name"));
								stat.setStatus(rs.getString("Status"));
								return stat;
							}
						});

	}*/

	      
}

