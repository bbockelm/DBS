package tom.dm.dao.jdbc;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.RowMapper;

import tom.dm.dao.FileDao;
import tom.dm.exception.FileException;
import tom.dm.entity.FileBase;
import tom.dm.entity.StreamerFile;



public class FileDaoImpl extends JdbcDaoSupport implements FileDao {
	public FileDaoImpl() {
		super();
	}
	public List<FileBase> listFilesByRun(final long runId, final String name) throws FileException { 
		String sql = "SELECT DISTINCT wmbs_file_details.id AS Id, " +
			"wmbs_file_details.filesize AS FileSize, " +
			"wmbs_file_details.lfn AS Lfn, " +
			"wmbs_file_details.events AS Events, " +
			"primary_dataset.name AS PrimaryDataset, " +
			"processed_dataset.name AS ProcessedDataset, " +
			"data_tier.name as DataTier " +
			"FROM wmbs_file_details " +
				"INNER JOIN wmbs_file_runlumi_map ON wmbs_file_details.ID = wmbs_file_runlumi_map.fileid " +
				"INNER JOIN wmbs_file_dataset_path_assoc ON wmbs_file_dataset_path_assoc.file_id = wmbs_file_details.ID " +
				"INNER JOIN dataset_path ON dataset_path.id = wmbs_file_dataset_path_assoc.dataset_path_id " +
				"INNER JOIN primary_dataset ON primary_dataset.id = dataset_path.primary_dataset " +
				"INNER JOIN wmbs_fileset_files ON wmbs_fileset_files.fileid = wmbs_file_details.id " +
				"INNER JOIN data_tier ON data_tier.id = dataset_path.data_tier " +
				"INNER JOIN processed_dataset on processed_dataset.id = dataset_path.processed_dataset " +
			"WHERE wmbs_fileset_files.fileset = (SELECT id FROM wmbs_fileset WHERE name = ?) " +
			"AND wmbs_file_runlumi_map.run = ? " ;
		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("listFilesByRun QUERY ----> " + sql);
		System.out.println("________________________________________________________________________\n\n" );
     		return getJdbcTemplate().query(sql, 
						new Object[] {name, runId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								FileBase aFile = new FileBase();
								aFile.setId(rs.getLong("Id"));
								aFile.setFileSize(rs.getLong("FileSize"));
								aFile.setEvents(rs.getLong("Events"));
								aFile.setLfn(rs.getString("Lfn"));
								aFile.setPrimaryDataset(rs.getString("PrimaryDataset"));
								aFile.setProcessedDataset(rs.getString("ProcessedDataset"));
								aFile.setDataTier(rs.getString("DataTier"));
								return aFile;
							}
						});

	}
	public List<StreamerFile> listStreamerFilesByRun(final long runId) throws FileException {
		String sql = "SELECT STREAMER_ID as StreamerId, " +
			"LUMI_ID as LumiId, " +
			"FILESIZE as FileSize, " +
			"EVENTS as Events, " +
			"LFN as Lfn,  " +
			"Stream.name as Name " +
			"FROM Streamer " +
			"JOIN Stream ON Stream.id = Streamer.Stream_id " +
			"WHERE Streamer.run_id = ?";
		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("listStreamerFilesByRun QUERY ----> " + sql);
		System.out.println("________________________________________________________________________\n\n" );
     		return getJdbcTemplate().query(sql, 
						new Object[] {runId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								StreamerFile aFile = new StreamerFile();
								aFile.setLumiId(rs.getLong("LumiId"));
								aFile.setStreamerId(rs.getLong("StreamerId"));
								aFile.setFileSize(rs.getLong("FileSize"));
								aFile.setEvents(rs.getLong("Events"));
								aFile.setLfn(rs.getString("Lfn"));
								aFile.setStreamerName(rs.getString("Name"));
								//aFile.setDataset(rs.getString("Dataset"));
								return aFile;
							}
						});
	
	}

	public List<FileBase> listFilesByJob(final long jobId) throws FileException {
		String sql = "SELECT DISTINCT wmbs_file_details.id AS Id, " +
			"wmbs_file_details.filesize AS FileSize, " +
			"wmbs_file_details.lfn AS Lfn, " +
			"wmbs_file_details.events AS Events, " +
			"primary_dataset.name AS PrimaryDataset, " +
			"processed_dataset.name AS ProcessedDataset, " +
			"data_tier.name as DataTier " +
			"FROM wmbs_file_details " +
			"INNER JOIN wmbs_file_dataset_path_assoc ON wmbs_file_dataset_path_assoc.file_id = wmbs_file_details.ID " +
			"INNER JOIN dataset_path ON dataset_path.id = wmbs_file_dataset_path_assoc.dataset_path_id " +
			"INNER JOIN primary_dataset ON primary_dataset.id = dataset_path.primary_dataset " +
			"INNER JOIN data_tier ON data_tier.id = dataset_path.data_tier " +
			"INNER JOIN processed_dataset on processed_dataset.id = dataset_path.processed_dataset " +
			"INNER JOIN wmbs_job_assoc on wmbs_job_assoc.fileid =  wmbs_file_details.id " +
			"WHERE wmbs_job_assoc.job = ?" ;
		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("listFilesByJob QUERY ----> " + sql);
		System.out.println("________________________________________________________________________\n\n" );
     		return getJdbcTemplate().query(sql, 
						new Object[] {jobId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								FileBase aFile = new FileBase();
								aFile.setId(rs.getLong("Id"));
								aFile.setFileSize(rs.getLong("FileSize"));
								aFile.setEvents(rs.getLong("Events"));
								aFile.setLfn(rs.getString("Lfn"));
								aFile.setPrimaryDataset(rs.getString("PrimaryDataset"));
								aFile.setProcessedDataset(rs.getString("ProcessedDataset"));
								aFile.setDataTier(rs.getString("DataTier"));
								return aFile;

							}
						});

	}
 
	public List<StreamerFile> listStreamerFilesByJob(final long jobId) throws FileException {
		String sql = " SELECT DISTINCT STREAMER_ID as StreamerId, " +
			"LUMI_ID as LumiId, " +
			"FILESIZE as FileSize, " +
			"EVENTS as Events, " +
			"LFN as Lfn,  " +
			"Stream.name as Name " +
			"FROM Streamer " +
			"JOIN Stream ON Stream.id = Streamer.Stream_id " +
			"INNER JOIN job_streamer_dataset_assoc on job_streamer_dataset_assoc.streamer_id = Streamer.streamer_id " +
			"INNER JOIN primary_dataset ON primary_dataset.id = job_streamer_dataset_assoc.primary_dataset_id " +
			"WHERE job_streamer_dataset_assoc.job_id = ?";
		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("listStreamerFilesByJob QUERY ----> " + sql);
		System.out.println("________________________________________________________________________\n\n" );
     		return getJdbcTemplate().query(sql, 
						new Object[] {jobId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								StreamerFile aFile = new StreamerFile();
								aFile.setLumiId(rs.getLong("LumiId"));
								aFile.setStreamerId(rs.getLong("StreamerId"));
								aFile.setFileSize(rs.getLong("FileSize"));
								aFile.setEvents(rs.getLong("Events"));
								aFile.setLfn(rs.getString("Lfn"));
								aFile.setStreamerName(rs.getString("Name"));
								//aFile.setDataset(rs.getString("Dataset"));
								return aFile;
							}
						});
	
	}
     
}

