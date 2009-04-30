package tom.dm.dao.jdbc;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.RowMapper;

import tom.dm.dao.RunDao;
import tom.dm.entity.Run;
import tom.dm.entity.Repack;
import tom.dm.entity.Reco;
import tom.dm.entity.AlcaSkim;
import tom.dm.entity.Tier1Skim;
import tom.dm.entity.RunDetail;



public class RunDaoImpl extends JdbcDaoSupport implements RunDao {
	public RunDaoImpl() {
		super();
	}
	public  List<RunDetail> listRunDetail(final long runId){
		String sql = "SELECT * FROM  " +    
				"(SELECT DISTINCT primary_dataset.name AS Dataset,  " +
					"reco_config.do_reco AS RecoEnabled, " +
					"reco_config.proc_version AS RecoProcVersion, " +
					"cmssw_version.name AS RecoCmsswVersion, " +
					"reco_config.global_tag AS RecoGlobalTag, " +
					"reco_config.config_url AS RecoConfigUrl " +
				"FROM reco_config " +
				"INNER JOIN primary_dataset ON reco_config.primary_dataset_id = primary_dataset.id  " +
				"INNER JOIN cmssw_version ON reco_config.cmssw_version_id = cmssw_version.id " +
				"WHERE reco_config.run_id = ?) myreco " +
			"INNER JOIN " +
    				"(SELECT DISTINCT primary_dataset.name AS Dataset,  " +
					"alca_config.do_alca AS AlcaEnabled, " +
					"alca_config.proc_version AS AlcaProcVersion, " +
					"cmssw_version.name AS AlcaCmsswVersion, " +
					"alca_config.config_url AS AlcaConfigUrl " +
				"FROM alca_config " +
				"INNER JOIN primary_dataset ON alca_config.primary_dataset_id = primary_dataset.id  " +
				"INNER JOIN cmssw_version ON alca_config.cmssw_version_id = cmssw_version.id " +
				"WHERE alca_config.run_id = ?) myalca  " +
			"ON myreco.Dataset = myalca.Dataset " +
			"INNER JOIN " +
    				"(SELECT DISTINCT primary_dataset.name AS Dataset,  " +
					"repack_config.proc_version AS RepackProcVersion, " +
					"repack_config.hltdebug AS RepackHltDebug " +
				"FROM repack_config " +
				"INNER JOIN primary_dataset  ON repack_config.primary_dataset_id = primary_dataset.id " +
				"WHERE repack_config.run_id = ?) myrepack  " +
			"ON myrepack.Dataset = myreco.Dataset " +
    			"LEFT OUTER JOIN " +
				"(SELECT  primary_dataset.name AS Dataset, " +
					"t1skim_config.CONFIG_URL AS T1skimConfigUrl, " +
					"cmssw_version.name AS T1SkimCmssswVersion, " +
					"t1skim_config.proc_version AS T1SkimProcVersion, " +
					"t1skim_config.skim_name AS T1SkimName, " +
					"data_tier.name As DataTierName " +
				"FROM primary_dataset " +
				"INNER JOIN t1skim_config ON t1skim_config.primary_dataset_id = primary_dataset.id " +
				"INNER JOIN cmssw_version ON t1skim_config.cmssw_version_id = cmssw_version.id " +
				"INNER JOIN data_tier ON data_tier.id = t1skim_config.data_tier_id " +
				"WHERE  t1skim_config.run_id = ?) myt1skim " +
			"ON myt1skim.Dataset = myrepack.Dataset";
		System.out.println("________________________________________________________________________\n\n" );
		System.out.println("Sql = " + sql);
		System.out.println("________________________________________________________________________\n\n" );
		List<RunDetail> runDetail = getJdbcTemplate().query(sql,
						new Object[] {runId, runId, runId, runId},
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								RunDetail runDetail = new RunDetail();
								Repack repack = new Repack();
								Reco reco = new Reco();
								AlcaSkim alcaSkim = new AlcaSkim();
								Tier1Skim tier1Skim = new Tier1Skim();

								runDetail.setDataset(rs.getString("Dataset"));
								
								repack.setProcVersion(rs.getString("RepackProcVersion"));
								repack.setHltDebug(rs.getString("RepackHltDebug"));

								reco.setCmsswVersion(rs.getString("RecoCmsswVersion"));
								reco.setConfigUrl(rs.getString("RecoConfigUrl"));
								reco.setGlobalTag(rs.getString("RecoGlobalTag"));
								reco.setProcVersion(rs.getString("RecoProcVersion"));
								reco.setRecoEnabled(rs.getBoolean("RecoEnabled"));

								alcaSkim.setCmsswVersion(rs.getString("AlcaCmsswVersion"));
								alcaSkim.setConfigUrl(rs.getString("AlcaConfigUrl"));
								alcaSkim.setProcVersion(rs.getString("AlcaProcVersion"));
								alcaSkim.setAlcaEnabled(rs.getBoolean("AlcaEnabled"));
								
								tier1Skim.setCmsswVersion(rs.getString("T1SkimCmssswVersion"));
								tier1Skim.setConfigUrl(rs.getString("T1skimConfigUrl"));
								tier1Skim.setProcVersion(rs.getString("T1SkimProcVersion"));
								tier1Skim.setSkimName(rs.getString("T1SkimName"));
								tier1Skim.setTierName(rs.getString("DataTierName"));

								runDetail.setRepack(repack);
								runDetail.setReco(reco);
								runDetail.setAlcaSkim(alcaSkim);
								runDetail.setTier1Skim(tier1Skim);

								return runDetail;
							}
						});
		return runDetail;

	}
	public List<Run> listRuns() {
		//List<Run> runs = getJdbcTemplate().query("select r.RUN_ID, r.START_TIME, r.END_TIME, r.PROCESS, r.VERSION, r.HLTKEY, r.ACQ_ERA, r.LAST_STREAMER, st.STATUS from RUN r, RUN_STATUS st where r.RUN_STATUS = st.ID",
		List<Run> runs = getJdbcTemplate().query("select r.RUN_ID, r.START_TIME, r.END_TIME, r.PROCESS, r.VERSION, r.HLTKEY,  " +
						"r.ACQ_ERA, st.STATUS , count(distinct str.streamer_id) AS STREAMERS " +
						"FROM RUN r, RUN_STATUS st, streamer str where str.run_id = r.run_id " +
						"AND r.RUN_STATUS = st.ID  " +
						"GROUP BY r.RUN_ID, r.START_TIME, r.END_TIME, r.PROCESS, r.VERSION, r.HLTKEY, r.ACQ_ERA, st.STATUS " +
						"ORDER BY r.RUN_ID  ",
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
								Run run = new Run();
								run.setId(rs.getLong("RUN_ID"));
								run.setProcess(rs.getString("PROCESS"));
								run.setVersion(rs.getString("VERSION"));
								run.setHltKey(rs.getString("HLTKEY"));
								run.setAcqEra(rs.getString("ACQ_ERA"));
								run.setStreamers(rs.getLong("STREAMERS"));
								run.setStatus(rs.getString("STATUS"));
								run.setStart(new Date(rs.getLong("START_TIME") * 1000));
								run.setEnd(new Date(rs.getLong("END_TIME") * 1000));
								return run;
							}
						});
		return runs;
	}
	/*public List<Run> listRuns() {
		Object result = getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection con) {
				 return resultOfUpdateOperation2();
			}
		});
	}

	public RunSeq getRunSeq(final String name) {
		List toReturn =  getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(RunSeq.class)
					.add(Restrictions.eq("name", name))
					.setLockMode(LockMode.UPGRADE)
					.list();
		     	}
		});
		if (toReturn.size() > 0) return (RunSeq)toReturn.get(0);
		return null;		
	}
	
	public void updateRunSeq(RunSeq runSeq) {
		this.getHibernateTemplate().update(runSeq);
	}
	public RunSeq saveRunSeq(RunSeq runSeq) {
		this.getHibernateTemplate().save(runSeq);
		return runSeq;
	}*/
	      
}

