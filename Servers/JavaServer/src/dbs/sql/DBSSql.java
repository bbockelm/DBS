
/**
 $Revision: 1.191 $"
 $Id: DBSSql.java,v 1.191 2008/10/14 17:30:16 afaq Exp $"
 *
 */
package dbs.sql;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Timestamp;
//import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dbs.util.DBSUtil;
import db.DBManagement;
import java.util.Date;
import dbs.util.DBSConfig;



/**
 * This class is the SQL generator. All the methods in this class are static method. The main function of this class is to construct a <code>java.sql.PreparedStatement</code> with the proper query. All the methods returns the constructed <code>java.sql.PreparedStatement</code> after packing it with proper variables' values that are passed in as parameters to various method calls. All the methods can throw and <code>java.sql.SQLException</code> if the prepared statement fails to construct.
 */
public class DBSSql {
	/**
	 * 
	 */

        public static String owner() throws SQLException {

                String schemaOwner = null;
                try {
                        DBSConfig config = DBSConfig.getInstance();
                        schemaOwner = config.getSchemaOwner();
                } catch (dbs.DBSException ex) {
                        throw new SQLException("Failed to setup Schema Owner Name");
                }

                return schemaOwner;
        }

	public static String getDual() throws SQLException {
		return "SELECT 1 FROM dual";
	}

	public static PreparedStatement getSchemaVersion(Connection conn) throws SQLException {

		String sql = "SELECT SchemaVersion, InstanceName FROM "+owner()+"SchemaVersion";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
        }
	public static PreparedStatement getQuery(Connection conn, String query, List<String> bindValues, List<Integer> bindIntValues) throws SQLException {

		PreparedStatement ps = DBManagement.getStatement(conn, query);
		int columnIndx = 1;
		for(String s: bindValues) ps.setString(columnIndx++, s);
		for(Integer i: bindIntValues) ps.setInt(columnIndx++, i.intValue());
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
        }


        public static PreparedStatement getQueryScrollable(Connection conn, String query, List<String> bindValues, List<Integer> bindIntValues) throws SQLException {

                PreparedStatement ps = DBManagement.getStatementScrollable(conn, query);
                int columnIndx = 1;
                for(String s: bindValues) ps.setString(columnIndx++, s);
                for(Integer i: bindIntValues) ps.setInt(columnIndx++, i.intValue());
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }


	public static PreparedStatement updateValue(Connection conn, String tableName, String ID, String key, String value, String lmbUserID) throws SQLException {
		String sql = "UPDATE " + owner() + tableName + " \n" +
			"SET " + key + " = ?, \n" +
			"LastModifiedBy = ? \n" +
			"WHERE ID = ?" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, value);
		ps.setString(columnIndx++, lmbUserID);
		ps.setString(columnIndx++, ID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement updateMap(Connection conn, String tableName, String key1, String key2, String value1, String value2New, String value2Old, String lmbUserID) throws SQLException {
		String sql = "UPDATE " + owner()+  tableName + " \n" +
			"SET " + key2 + " = ?, \n" +
			"LastModifiedBy = ? \n" +
			"WHERE " + key1 + " = ? \n" +
			"AND " + key2 + " = ? \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, value2New);
		ps.setString(columnIndx++, lmbUserID);
		ps.setString(columnIndx++, value1);
		ps.setString(columnIndx++, value2Old);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	public static PreparedStatement updateName(Connection conn, String tableName, String key, String valueFrom, String valueTo, String lmbUserID) 
	throws Exception {
		String sql = "UPDATE " +  owner()+tableName + " \n" +
			"SET " + key + " = ?, \n" +
			"LastModifiedBy = ? \n" +
			"WHERE " + key + " = ?" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, valueTo);
		ps.setString(columnIndx++, lmbUserID);
		ps.setString(columnIndx++, valueFrom);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	 public static PreparedStatement updateProcDSXtCrossSection(Connection conn, String procDSID, String xSection, String lmbUserID) throws SQLException {
                String sql = "UPDATE " +  owner()+"ProcessedDataset \n" +
			"SET XtCrossSection = ?, \n" +
			"LastModifiedBy = ? \n" +
			"WHERE ID = ?";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, xSection);
                ps.setString(columnIndx++, lmbUserID);
                ps.setString(columnIndx++, procDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

       public static PreparedStatement updateFileAutoCrossSection(Connection conn, String fileID, String xSection, String lmbUserID) throws SQLException {
                String sql = "UPDATE " +  owner()+"Files \n" +
			"SET AutoCrossSection = ?, \n" +
			"LastModifiedBy = ? \n" +
			"WHERE ID = ?";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, xSection);
                ps.setString(columnIndx++, lmbUserID);
                ps.setString(columnIndx++, fileID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

       public static PreparedStatement insertTimeLog(Connection conn, String action, String cause, 
									String effect, String description,
									String cbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("Action", action);
		table.put("Cause", cause);
		table.put("Effect", effect);
		table.put("Description", description);
		table.put("CreatedBy", cbUserID);
		table.put("CreationDate", cDate);
                return getInsertSQL(conn, "TimeLog", table);
        }

       	public static PreparedStatement insertRecycleBin(Connection conn, String path, String blockName, String xml, String cbUserID, String lmbUserID, String cDate) throws SQLException {	
		Hashtable table = new Hashtable();
		table.put("Path", path);
		table.put("BlockName", blockName);
		table.put("Xml", xml);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "RecycleBin", table);
	}


       	public static PreparedStatement insertName(Connection conn, String tableName, String key, String value, String cbUserID, String lmbUserID, String cDate) throws SQLException {	
		Hashtable table = new Hashtable();
		table.put(key, value);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, tableName, table);
	}
	
	public static PreparedStatement insertMap(Connection conn, String tableName, String key1, String key2, String value1, String value2, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put(key1, value1);
		table.put(key2, value2);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, tableName, table);
	}
	
	public static PreparedStatement insertMap(Connection conn, String tableName, String key1, String key2, String key3, String value1, String value2, String value3, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put(key1, value1);
		table.put(key2, value2);
		table.put(key3, value3);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, tableName, table);
	}

        public static PreparedStatement insertMapBatch(Connection conn, String tableName, String key1, String key2, String mapTo,
                        java.util.ArrayList values, String cbUserID, String lmbUserID, String cDate) throws SQLException {

                String sql = "INSERT INTO "+owner()+tableName+" \n"+
                                "("+key1+","+key2+", \n"+
                                " CreatedBy, LastModifiedBy, CreationDate) \n"+
                                " select ?, ?, ?, ?, ? FROM DUAL ";
                                //" select ?, ?, "+cbUserID+", "+lmbUserID+", "+cDate+" FROM DUAL \n";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                for (int j = 0; j < values.size(); ++j) {
                        int columnIndx = 1;
                        String value=(String)values.get(j);

                        ps.setString(columnIndx++, mapTo);
                        ps.setString(columnIndx++, value);

			ps.setString(columnIndx++, cbUserID);
			ps.setString(columnIndx++, lmbUserID);
			ps.setString(columnIndx++, cDate);

                        ps.addBatch();
                }
                DBSUtil.writeLog("\n" + ps + "\n");

                return ps;
        }


        public static PreparedStatement insertMultiMapBatch(Connection conn, String tableName, String key1, String key2,
                        java.util.ArrayList values1, java.util.ArrayList values2, String cbUserID, String lmbUserID, String cDate) throws SQLException {

                String sql = "INSERT INTO "+owner()+tableName+" \n"+
                                "("+key1+","+key2+", \n"+
                                " CreatedBy, LastModifiedBy, CreationDate) \n"+
                                " select ?, ?, ?, ?, ? FROM DUAL ";
                                //" select ?, ?, "+cbUserID+", "+lmbUserID+", "+cDate+" FROM DUAL \n";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                if (values1.size() !=  values2.size()) {
                        throw new SQLException("insertMultiMapBatch: Size mismatch");
                }
 	
                for (int j = 0; j < values1.size(); ++j) {
                        int columnIndx = 1;
                        String value1=(String)values1.get(j);
			String value2=(String)values2.get(j);

                        ps.setString(columnIndx++, value1);
                        ps.setString(columnIndx++, value2);

                        ps.setString(columnIndx++, cbUserID);
                        ps.setString(columnIndx++, lmbUserID);
                        ps.setString(columnIndx++, cDate);

                        ps.addBatch();
                }

                DBSUtil.writeLog("\n" + ps + "\n");

                return ps;
        }


        //3-Key version
         public static PreparedStatement insertMapBatch(Connection conn, String tableName, String key1, String key2, String key3,
                        String mapTo, java.util.ArrayList values, String mapK3, String cbUserID, String lmbUserID, String cDate) throws SQLException {

                String sql = "INSERT INTO "+owner()+tableName+" \n"+
                        "("+key1+","+key2+","+key3+", \n"+
                                " CreatedBy, LastModifiedBy, CreationDate) \n"+
                                " select ?, ?, ?, ?, ?, ? FROM DUAL \n" +
				" WHERE not exists \n" +
                                " (select * from " + tableName + " \n" +
                                " where " + key1 + "=? AND " + key2 + "=? AND " + key3 + "=?) \n" ;


                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                for (int j = 0; j < values.size(); ++j) {
                        int columnIndx = 1;
			
                        ps.setString(columnIndx++, mapTo);
                        ps.setString(columnIndx++, (String)values.get(j));
                        ps.setString(columnIndx++, mapK3);

			ps.setString(columnIndx++, cbUserID);
			ps.setString(columnIndx++, lmbUserID);
			ps.setString(columnIndx++, cDate);

                       //For the WHERE not exists Clause
			ps.setString(columnIndx++, mapTo);
                        ps.setString(columnIndx++, (String)values.get(j));
                        ps.setString(columnIndx++, mapK3);

                        ps.addBatch();
                }
                DBSUtil.writeLog("\n\n" + ps + "\n\n");

                return ps;
        }
/*------- Data Quality Calls Collected in one place, later we can separate them out ------*/

        public static PreparedStatement insertDQFlagHistory(Connection conn, String rowID) throws SQLException {

		//HistoryTimeStamp  MUSt be provided by Trigger later ON.
                String sql = "INSERT INTO "+owner()+"QualityHistory \n "+
                                " (HistoryOf, Dataset, Run,  Lumi, SubSystem, DQValue, \n" +
                                " CreationDate, CreatedBy, LastModificationDate, LastModifiedBy, \n"+
                                " HistoryTimeStamp) select ID, Dataset, Run, Lumi, SubSystem, DQValue, CreationDate, \n" +
                                " CreatedBy, LastModificationDate, LastModifiedBy, " + 
				Long.toString( (new Date()).getTime() / 1000 ) +
                                " from "+ owner()+"RunLumiQuality where ID = ?";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, rowID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

        public static PreparedStatement insertDQIntFlagHistory(Connection conn, String rowID) throws SQLException {

                //HistoryTimeStamp  MUSt be provided by Trigger later ON.
                String sql = "INSERT INTO "+owner()+"IntQualityHistory \n "+
                                " (HistoryOf, Run,  Lumi, SubSystem, IntDQValue, \n" +
                                " CreationDate, CreatedBy, LastModificationDate, LastModifiedBy, \n"+
                                " HistoryTimeStamp) select ID, Dataset, Run, Lumi, SubSystem, IntDQValue, CreationDate, \n" +
                                " CreatedBy, LastModificationDate, LastModifiedBy, " +
                                Long.toString( (new Date()).getTime() / 1000 ) +
                                " from "+ owner()+"RunLumiDQInt where ID = ?";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, rowID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }


        public static PreparedStatement updateDQFlag(Connection conn,
                                                        String rowID,
                                                        String valueID,
                                                        String lmbUserID) throws SQLException {

                String sql = "UPDATE "+ owner()+"RunLumiQuality \n" +
                                "SET DQValue = ? , \n"+
                                "LastModifiedBy = ? \n" +
                                "WHERE ID = ?";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, valueID);
                ps.setString(columnIndx++, lmbUserID);
                ps.setString(columnIndx++, rowID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

	//AA-06/12/2008
        public static PreparedStatement updateDQIntFlag(Connection conn,
                                                        String rowID,
                                                        String value,
                                                        String lmbUserID) throws SQLException {

                String sql = "UPDATE "+ owner()+"RunLumiDQInt\n" +
                                "SET IntDQValue = ? , \n"+
                                "LastModifiedBy = ? \n" +
                                "WHERE ID = ?";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, value);
                ps.setString(columnIndx++, lmbUserID);
                ps.setString(columnIndx++, rowID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }


        public static PreparedStatement insertDQFlag(Connection conn, String procDSID, String runID, String lumiID,
                                                        String flagID, String valueID,
                                                        String cbUserID, String lmbUserID, String cDate) throws SQLException
	{
                Hashtable table = new Hashtable();
		table.put("Dataset", procDSID);
                table.put("Run", runID);
                if (!DBSUtil.isNull(lumiID)) table.put("Lumi", lumiID);
                table.put("SubSystem", flagID);
                table.put("DQValue", valueID);
                table.put("CreatedBy", cbUserID);
                table.put("LastModifiedBy", lmbUserID);
                table.put("CreationDate", cDate);
                return getInsertSQL(conn, "RunLumiQuality", table);
        }

        public static PreparedStatement insertDQIntFlag(Connection conn, String procDSID, String runID, String lumiID,
                                                        String flagID, String valueID,
                                                        String cbUserID, String lmbUserID, String cDate) throws SQLException
        {
                Hashtable table = new Hashtable();
		table.put("Dataset", procDSID);
                table.put("Run", runID);
                if (!DBSUtil.isNull(lumiID)) table.put("Lumi", lumiID);
                table.put("SubSystem", flagID);
                table.put("IntDQValue", valueID);
                table.put("CreatedBy", cbUserID);
                table.put("LastModifiedBy", lmbUserID);
                table.put("CreationDate", cDate);
                return getInsertSQL(conn, "RunLumiDQInt", table);
        }

        public static PreparedStatement insertDQVersion(Connection conn, String versionName, String descrp,
							String cbUserID, String lmbUserID, String cDate)  throws SQLException 
	{
		Hashtable table = new Hashtable();
		table.put("Version", versionName);
		table.put("VersionTimeStamp", Long.toString((new Date()).getTime()/1000));
		if (!DBSUtil.isNull(descrp)) table.put("Description", descrp);
                table.put("CreatedBy", cbUserID);
                table.put("LastModifiedBy", lmbUserID);
                table.put("CreationDate", cDate);
                return getInsertSQL(conn, "QualityVersion", table);
	}

        public static PreparedStatement getDQVerTimeStamp(Connection conn, String dqVersion) throws SQLException {
		String sql = "SELECT VersionTimeStamp as TIMESTAMP \n" +
				" FROM "+owner()+"QualityVersion \n" +
				" WHERE Version = ? ";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, dqVersion);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
	
	}

	public static PreparedStatement listSubSystems(Connection conn) throws SQLException
	{
		String sql = "SELECT Name as SUBSYSTEM, \n"+
				" Parent as PARENT \n" +
				" FROM "+owner()+"SubSystem \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;

	}

       public static PreparedStatement listRunLumiDQ(Connection conn, java.util.ArrayList dsParents, Vector runDQList, String timeStamp)
        throws SQLException
        {

                //JUST for testing 
                //timeStamp = "1182285735";
		java.util.Vector bindvals = new java.util.Vector();
		String sql = "";

                String sqlStr = "SELECT distinct rq.ID as ID, r.RunNumber as RUN_NUMBER, \n"+
				"rq.Dataset as DATASET, \n"+
				"ls.LumiSectionNumber as LUMI_SECTION_NUMBER, \n"+
				"ss.Name as DQ_FLAG, qv.Value as QVALUE, \n"+
				"ss.Parent as PARENT, \n"+
				"rq.LastModificationDate as LASTMODIFICATIONDATE \n"+
				"FROM  "+owner()+"RunLumiQuality rq \n"+
				"join "+owner()+"Runs r \n"+
				       "on rq.Run = r.ID \n"+
				"LEFT OUTER JOIN "+owner()+"LumiSection ls \n"+
				       "on ls.ID = rq.Lumi \n"+
				"join "+owner()+"SubSystem ss \n"+
			       		"on ss.ID = rq.SubSystem \n"+
				"join "+owner()+"QualityValues qv \n"+
       					"on qv.ID = rq.DQValue\n" +
				" WHERE \n";

		String sqlInt = "SELECT distinct rq.ID as ID, r.RunNumber as RUN_NUMBER, \n"+
				"rq.Dataset as DATASET, \n"+
                                "ls.LumiSectionNumber as LUMI_SECTION_NUMBER, \n"+
                                "ss.Name as DQ_FLAG, TO_CHAR(rq.IntDQValue) as QVALUE, \n"+
                                "ss.Parent as PARENT, \n"+
                                "rq.LastModificationDate as LASTMODIFICATIONDATE \n"+
                                "FROM  "+owner()+"RunLumiDQInt rq \n"+
                                "join "+owner()+"Runs r \n"+
                                       "on rq.Run = r.ID \n"+
                                "LEFT OUTER JOIN "+owner()+"LumiSection ls \n"+
                                       "on ls.ID = rq.Lumi \n"+
                                "join "+owner()+"SubSystem ss \n"+
                                        "on ss.ID = rq.SubSystem \n"+
				" WHERE \n";

		String dsSql = " Dataset IN (?";
		for (int i=0; i != dsParents.size();++i){
                        if (i!=0) dsSql+=",?"; 
			bindvals.add((String)dsParents.get(i));
                }
                dsSql+=")";

		sqlStr += dsSql;
		sqlInt += dsSql;

                String rlsql = "";
                if (runDQList.size() > 0) {
                	for (int i = 0; i < runDQList.size() ; ++i) {
                        	Hashtable runDQ = (Hashtable) runDQList.get(i);

                                String runnumber = DBSUtil.get(runDQ, "run_number");
                                String lumisec = DBSUtil.get(runDQ, "lumi_section_number");

                                if (!DBSUtil.isNull(runnumber)) {
                                	if (i==0) rlsql = " AND ( r.RunNumber = ? ";
                                        else    rlsql = " OR ( r.RunNumber = ? ";
                                        if ( !DBSUtil.isNull(lumisec) ) {
						rlsql += " AND ";
					} else {
						bindvals.add(runnumber);
					}
                              	}

                                else {
                                	if (i==0) rlsql = " AND ( ";
                                        else rlsql = " OR ( ";
         
                 	        }

                                if (!DBSUtil.isNull(lumisec)) {
					rlsql += "ls.LumiSectionNumber=? ";
					bindvals.add(runnumber);
					bindvals.add(lumisec);
				}
				if (!DBSUtil.isNull(rlsql) )
					rlsql += " ) ";
			}

		sqlStr += rlsql;
		sqlInt += rlsql;

		}

		String tsSql="";	
		if (!DBSUtil.isNull(timeStamp)) {
	                tsSql += " AND rq.CreationDate <=? \n";
                        tsSql +=   " AND rq.LastModificationDate <= ? \n";
			bindvals.add(timeStamp);
			bindvals.add(timeStamp);
			sqlStr += tsSql;
			sqlInt += tsSql;
		}

		String sqlStrHist = "";
		String sqlIntHist = "";

		if (!DBSUtil.isNull(timeStamp)) {
				sqlStrHist += "SELECT distinct qh.HistoryOf as ID, \n"+
				" r.RunNumber as RUN_NUMBER, \n"+
				"qh.Dataset as DATASET, \n"+
				"ls.LumiSectionNumber as LUMI_SECTION_NUMBER, \n"+
				"ss.Name as DQ_FLAG, qv.Value as QVALUE, \n"+
				"ss.Parent as PARENT, \n"+
				"qh.LastModificationDate as LASTMODIFICATIONDATE \n"+
				"FROM  "+owner()+"QualityHistory qh \n"+
				"join "+owner()+"Runs r \n"+
       					"on qh.Run = r.ID \n"+
				"LEFT OUTER JOIN "+owner()+"LumiSection ls \n"+
       					"on ls.ID = qh.Lumi \n"+
				"join "+owner()+"SubSystem ss \n"+
       					"on ss.ID = qh.SubSystem \n"+
				"join "+owner()+"QualityValues qv \n"+
       					"on qv.ID = qh.DQValue \n" +
				" WHERE ";
				sqlStrHist += dsSql;
				if (runDQList.size() > 0) sqlStrHist += rlsql;
				sqlStrHist += " AND qh.CreationDate <=?  AND \n"+
				"qh.LastModificationDate <=?  \n";

                                sqlIntHist += "SELECT distinct qh.HistoryOf as ID, \n" +
				" r.RunNumber as RUN_NUMBER, \n"+
				"qh.Dataset as DATASET, \n"+
                                "ls.LumiSectionNumber as LUMI_SECTION_NUMBER, \n"+
                                "ss.Name as DQ_FLAG, TO_CHAR(qh.IntDQValue) as QVALUE, \n"+
                                "ss.Parent as PARENT, \n"+
                                "qh.LastModificationDate as LASTMODIFICATIONDATE \n"+
                                "FROM  "+owner()+"IntQualityHistory qh \n"+
                                "join "+owner()+"Runs r \n"+
                                        "on qh.Run = r.ID \n"+
                                "LEFT OUTER JOIN "+owner()+"LumiSection ls \n"+
                                        "on ls.ID = qh.Lumi \n"+
                                "join "+owner()+"SubSystem ss \n"+
                                        "on ss.ID = qh.SubSystem \n" +
				" WHERE ";
                                sqlIntHist += dsSql;
				if (runDQList.size() > 0) sqlIntHist += rlsql;
				sqlIntHist += " AND qh.CreationDate <=?  AND \n"+
                                "qh.LastModificationDate <=?  \n";
		}

		int bindCnt=2; //Add the bind variables two times
		sql = sqlStr + "\n UNION \n" + sqlInt; 
		if (!DBSUtil.isNull(timeStamp)) {
				sql += "\n UNION \n" + sqlStrHist + "\n UNION \n" + sqlIntHist;
				bindCnt += 2; //Add the bind variables two times more
		}

		sql += " order by DATASET DESC, LASTMODIFICATIONDATE DESC";
		//Order by is very important, Change it ONLY if BUSH becomes president third times!
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;

		for (int j=0; j != bindCnt; ++j)
                for (int i=0; i != bindvals.size(); ++i)
                        ps.setString(columnIndx++, (String)bindvals.elementAt(i) );
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;

	}

        public static ArrayList listRunsForRunLumiDQ(Connection conn, ArrayList dsBinds, String query) throws SQLException {

                String run_sql = "select RQ.Run from "+owner()+"RunLumiQuality RQ  join "+owner()
                                        +"SubSystem SS on SS.ID = RQ.SubSystem JOIN "+owner()+"QualityValues QV on RQ.DQValue=QV.ID \n";

                String good_clause="";
                String bad_clause="";
                String unknown_clause="";
                String rlsql="";

                java.util.Vector bindvals = new java.util.Vector();
                java.util.Vector rbindvals = new java.util.Vector();
                java.util.Vector subSys = new java.util.Vector();

                java.util.ArrayList intersects = new java.util.ArrayList();
                java.util.ArrayList intersectBinds = new java.util.ArrayList();

                //This should probably be loaded from database in the cache once !
                java.util.ArrayList valueList = new java.util.ArrayList();
                valueList.add("GOOD");
                valueList.add("BAD");
                valueList.add("UNKNOWN");

                int goodSysCount = 0;
                int badSysCount = 0;
                int unknownSysCount = 0;
		int firstrun=0;
		int onlyRun=1;

		String[] key_vals = query.split("&");
		System.out.println("key_vals.length::"+key_vals.length);
     		for (int i=0; i!= key_vals.length; ++i) {
			String[] key_val = key_vals[i].split("=");

			System.out.println("key_:"+key_val[0]);

			if (key_val[0].equals("Dataset")) {
				//System.out.println("DATASET KEY FOUND and MUST BE IGNORED");
				continue;
			}
			if (key_val[0].equals("RunNumber")) {
				if (firstrun==0) {
                                                rlsql += " (select ID from Runs where RunNumber in (?";
                                                rbindvals.add(key_val[1]);
						firstrun=1;
                                        }
                                        else {
                                                rlsql += " ,?";
                                                rbindvals.add(key_val[1]);
                                        }
			}
			if (!subSys.contains(key_vals[i])){
				subSys.add(key_vals[i]);
			}
		}
		
		//Lets handle Dataset and it Parents
		String dsSql=" Dataset in (?";
		for (int i=0; i != dsBinds.size();++i){
			if (i!=0) dsSql+=",?";
		}
		dsSql+=")";
		
                if (!DBSUtil.isNull(rlsql)) rlsql += ") )";
                                //Loop over each item and make good, bad, unknown queries
                                for (int j = 0; j != subSys.size() ; ++j) {

					String oper = null;
					String[] key_val = ((String)subSys.get(j)).split(">=");

					if ( key_val.length == 1) { key_val=((String)subSys.get(j)).split("<="); oper = " <= "; } 
					if ( key_val.length == 1) { key_val=((String)subSys.get(j)).split(">"); oper = " > "; }
					if ( key_val.length == 1) { key_val=((String)subSys.get(j)).split("<"); oper = " < "; }
					if ( key_val.length == 1) { key_val=((String)subSys.get(j)).split("="); oper = " = "; }
					if ( key_val.length == 1) throw new SQLException("Incorrect Data, Invalid operator used in : "+((String)subSys.get(j)));

                                        String subsys=key_val[0];
                                        String value=key_val[1];

                                       if (j == 0) {
                                                run_sql += " where ";
                                        }

					if (value.trim().startsWith("/")) 
					{ //System.out.println("FOUND PATH value:="+value); 
						continue;
					}
					System.out.println("subsys: "+subsys+" value:"+value);
 
                                        if ( ! valueList.contains(value) && !subsys.equals("RunNumber")) {

                                                //Probably its a number
                                                //lets test that 
                                                try {
                                                        int i = Integer.valueOf(value).intValue();
                                                } catch (java.lang.NumberFormatException e) {
                                                        throw new SQLException("Incorrect Data, Invalid value: " + value + " For QIM " + subsys);
                                                }

                                                String tmpquery = "SELECT RQI.Run from "+owner()+"RunLumiDQInt RQI join "+owner()+"SubSystem SSI ";
                                                        tmpquery += " on SSI.ID = RQI.SubSystem where SSI.Name=? and IntDQValue"+oper+"?";
                                                intersectBinds.add(subsys);
                                                intersectBinds.add(value);
                                                if (!DBSUtil.isNull(rlsql))  {
                                                                tmpquery += " AND RQI.Run in " + rlsql ;//+ " ) ";
                                                                intersectBinds.addAll(rbindvals);
                                                }
                                                intersects.add(tmpquery);
						onlyRun=0;
                                        } else {

                                        	if (value.equals("GOOD")) {
                                                	if ( goodSysCount == 0 ) {
								good_clause += dsSql + " AND ";
								bindvals.addAll(dsBinds);			
                                                        	if (!DBSUtil.isNull(rlsql))  {
                                                                	good_clause += " RQ.Run in " + rlsql + " AND ";
	                                                                bindvals.addAll(rbindvals);
        	                                                }
                	                                        good_clause += " QV.Value=? and SS.Name in (?";
								bindvals.add("GOOD");
                        	                                bindvals.add(subsys);
                                	                        goodSysCount++;

                                        	        } else {
                                                	        good_clause += ",?";
                                                        	bindvals.add(subsys);
	                                                        goodSysCount++;
        	                                        }
                	                        }

                        	                if (value.equals("BAD")) {
                                	                if ( badSysCount == 0 ) {
								bad_clause += dsSql + " AND ";
								bindvals.addAll(dsBinds);			
                                        	                if (!DBSUtil.isNull(rlsql))  {
                                                	                bad_clause += " RQ.Run in " + rlsql + " AND ";
                                                        	        bindvals.addAll(rbindvals);
	                                                        }
        	                                                bad_clause += " QV.Value=? and SS.Name in (?";
								bindvals.add("BAD");
                	                                        bindvals.add(subsys);
                        	                                badSysCount++;

                                	                } else {
                                        	                bad_clause += ",?";
                                                	        bindvals.add(subsys);
	                                                        badSysCount++;
        	                                        }
                	                        }

                        	                if (value.equals("UNKNOWN")) {
                                	                if ( unknownSysCount == 0 ) {
								unknown_clause += dsSql + " AND ";
								bindvals.addAll(dsBinds);			
                                        	                if (!DBSUtil.isNull(rlsql))  {
                                                	                unknown_clause += " RQ.Run in " + rlsql + " AND ";
	                                                                bindvals.addAll(rbindvals);
        	                                                }
                	                                        unknown_clause += " QV.Value=? and SS.Name in (?";
								bindvals.add("UNKNOWN");
                        	                                bindvals.add(subsys);
                                	                        unknownSysCount++;

                                        	        } else {
                                                	        unknown_clause += ",?";
	                                                        bindvals.add(subsys);
        	                                                unknownSysCount++;
                	                                }
                        	                }
				
                                	}
			}

                        if (!DBSUtil.isNull(good_clause)) good_clause+=") group by RQ.Run having count(*)>="+goodSysCount;
                        if (!DBSUtil.isNull(bad_clause)) bad_clause+=") group by RQ.Run having count(*)>="+badSysCount;
                        if (!DBSUtil.isNull(unknown_clause)) unknown_clause+=") group by RQ.Run having count(*)>="+unknownSysCount;

                String sql = "";
                if ( DBSUtil.isNull(good_clause) && DBSUtil.isNull(bad_clause) && DBSUtil.isNull(unknown_clause) && (intersects.size() <= 0)
                                && !DBSUtil.isNull(rlsql)  )  {
                        sql += rlsql; 
			if ( onlyRun==0 ) sql += ")" ;
                        bindvals.addAll(rbindvals);
                }

		else {
                        int put_first_intersect=0;
                        if (!DBSUtil.isNull(good_clause)) { sql += run_sql + good_clause ; put_first_intersect=1; }
                        if (!DBSUtil.isNull(bad_clause)) { 
					if (!DBSUtil.isNull(good_clause)) sql += " UNION ";
					sql += run_sql + bad_clause ; put_first_intersect=1; 
					
			}
                        if (!DBSUtil.isNull(unknown_clause)) { 
				if (!DBSUtil.isNull(good_clause) || !DBSUtil.isNull(bad_clause) ) sql += " UNION ";
				sql += run_sql + unknown_clause; put_first_intersect=1; 
			}
                        for (int i=0; i!= intersects.size(); ++i) {
                                if (i==0 && put_first_intersect==1) sql += " INTERSECT ";
                                //if (i==0) sql +=  run_sql + intersects.get(i); 
                                if (i==0) sql +=  intersects.get(i);
                                //else sql += " INTERSECT "+ run_sql + intersects.get(i);
                                else sql += " INTERSECT "+ intersects.get(i);
                        }
                }

		ArrayList toReturn = new ArrayList();
		toReturn.add(sql);
		bindvals.addAll(intersectBinds);
		toReturn.add(bindvals);
/*
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                for (int i=0; i != bindvals.size(); ++i)
                        ps.setString(columnIndx++, (String)bindvals.elementAt(i) );
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
*/
		return toReturn;
	}

        public static PreparedStatement getDQFlag(Connection conn, String procDSID, String runID, String lumiID,
                                                        String flagID,
                                                        String value) throws SQLException
        {
                String sql = "SELECT DISTINCT ID \n " +
                        "FROM "+owner()+"RunLumiQuality \n " +
                        "WHERE Run = ? \n";
                if (!DBSUtil.isNull(flagID)) sql +=  "AND SubSystem = ? \n";
                if (!DBSUtil.isNull(value))  sql +=  "AND DQValue = ? \n";
                if (!DBSUtil.isNull(lumiID)) sql +=  "AND Lumi = ? \n";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, runID);
                if (!DBSUtil.isNull(flagID)) ps.setString(columnIndx++, flagID);
                if (!DBSUtil.isNull(value))  ps.setString(columnIndx++, value);

                if (!DBSUtil.isNull(lumiID)) ps.setString(columnIndx++, lumiID);

                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;

        }


        public static PreparedStatement getDQIntFlag(Connection conn, String procDSID, String runID, String lumiID,
                                                        String flagID,
                                                        String value) throws SQLException
        {
                String sql = "SELECT DISTINCT ID \n " +
                        "FROM "+owner()+"RunLumiDQInt \n " +
                        "WHERE Run = ? \n";
                if (!DBSUtil.isNull(flagID)) sql +=  "AND SubSystem = ? \n";
                if (!DBSUtil.isNull(value))  sql +=  "AND IntDQValue = ? \n";
                if (!DBSUtil.isNull(lumiID)) sql +=  "AND Lumi = ? \n";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, runID);
                if (!DBSUtil.isNull(flagID)) ps.setString(columnIndx++, flagID);
                if (!DBSUtil.isNull(value))  ps.setString(columnIndx++, value);

                if (!DBSUtil.isNull(lumiID)) ps.setString(columnIndx++, lumiID);

                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;

        }



        public static PreparedStatement listDQVersions(Connection conn) throws SQLException
	{
		String sql = "SELECT Version as DQ_VERSION, VersionTimeStamp as TIME_STAMP from "+owner()+"QualityVersion";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement insertSubSystem(Connection conn, String name, String parent,
                                                    String cbUserID, String lmbUserID, String cDate)  throws SQLException
        {
                Hashtable table = new Hashtable();
                table.put("Name", name);
                if (!DBSUtil.isNull(parent)) table.put("Parent", parent);
                table.put("CreatedBy", cbUserID);
                table.put("LastModifiedBy", lmbUserID);
                table.put("CreationDate", cDate);
                return getInsertSQL(conn, "SubSystem", table);
        }


/*----------------END DQ Calls ------------------*/

	public static PreparedStatement insertPrimaryDataset(Connection conn, String ann, String name, String descID, String startDate, String endDate, String typeID , String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("Annotation", ann);
		table.put("Name", name);
		table.put("Description", descID);
		table.put("StartDate", startDate);
		table.put("EndDate", endDate);
		table.put("Type", typeID);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "PrimaryDataset", table);
	}

	public static PreparedStatement insertRun(Connection conn, String runNumber, String nOfEvents, String nOfLumiSections, String totalLumi, String storeNumber, String startOfRun, String endOfRun, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("RunNumber", runNumber);
		table.put("NumberOfEvents", nOfEvents);
		table.put("NumberOfLumiSections", nOfLumiSections);
		table.put("TotalLuminosity", totalLumi);
		table.put("StoreNumber", storeNumber);
		table.put("StartOfRun", startOfRun);
		table.put("EndOfRun", endOfRun);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "Runs", table);
	}


        public static PreparedStatement updateRunLumiCount(Connection conn, String runID) throws SQLException {

		String sql = "UPDATE "+owner()+"Runs SET \n" +
			"NumberOfLumiSections = (SELECT COUNT(*) FROM "+owner()+"LumiSection WHERE RunNumber = ?) \n" +
			"WHERE ID = ? ";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
	 	ps.setString(columnIndx++, runID);
	 	ps.setString(columnIndx++, runID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");

                return ps;
        }

								
	 public static PreparedStatement updateLumiSection(Connection conn, String lumiNumber, String runNumber, String startEvNumber, String endEvNumber, String lStartTime, String lEndTime, String lmbUserID) throws SQLException {
		String sql = "UPDATE "+owner()+"LumiSection SET \n";
		if ( !DBSUtil.isNull(startEvNumber) ) sql += "StartEventNumber = ? ,";
		if ( !DBSUtil.isNull(endEvNumber) ) sql += "EndEventNumber = ? ,";
		if ( !DBSUtil.isNull(lStartTime) ) sql += "LumiStartTime = ? ,";
		if ( !DBSUtil.isNull(lEndTime) ) sql += "LumiEndTime = ? ,";
		
		sql += "LastModifiedBy = ? \n" +
			"WHERE RunNumber = ? AND LumiSectionNumber = ?";

		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
		if ( !DBSUtil.isNull(startEvNumber) ) ps.setString(columnIndx++, startEvNumber);
		if ( !DBSUtil.isNull(endEvNumber) ) ps.setString(columnIndx++, endEvNumber);
		if ( !DBSUtil.isNull(lStartTime) ) ps.setString(columnIndx++, lStartTime);
		if ( !DBSUtil.isNull(lEndTime) ) ps.setString(columnIndx++, lEndTime);
		
		ps.setString(columnIndx++, lmbUserID);
		ps.setString(columnIndx++, runNumber);
		ps.setString(columnIndx++, lumiNumber);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");

		return ps;
        }

        public static PreparedStatement updateRun(Connection conn, String runNumber, String nOfEvents, String nOfLumiSections, String totalLumi, String startOfRun, String endOfRun, String lmbUserID) throws SQLException {
		String sql = "UPDATE "+owner()+"Runs SET \n";
		if ( !DBSUtil.isNull(nOfEvents) ) sql += "NumberOfEvents = ? ,";
		if ( !DBSUtil.isNull(nOfLumiSections) ) sql += "NumberOfLumiSections = ? ,";
		if ( !DBSUtil.isNull(totalLumi) ) sql += "TotalLuminosity = ? ,";
		if ( !DBSUtil.isNull(startOfRun) ) sql += "StartOfRun = ? ,";
		if ( !DBSUtil.isNull(endOfRun) ) sql += "EndOfRun = ? ,";
		
		sql += "LastModifiedBy = ? \n" +
			"WHERE RunNumber = ?";

		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
		if ( !DBSUtil.isNull(nOfEvents) ) ps.setString(columnIndx++, nOfEvents);
		if ( !DBSUtil.isNull(nOfLumiSections) ) ps.setString(columnIndx++, nOfLumiSections);
		if ( !DBSUtil.isNull(totalLumi) ) ps.setString(columnIndx++, totalLumi);
		if ( !DBSUtil.isNull(startOfRun) ) ps.setString(columnIndx++, startOfRun);
		if ( !DBSUtil.isNull(endOfRun) ) ps.setString(columnIndx++, endOfRun);
		
		ps.setString(columnIndx++, lmbUserID);
		ps.setString(columnIndx++, runNumber);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");

		return ps;
        }


	public static PreparedStatement insertBlock(Connection conn, String size, String name, String procDSID, String nOfFiles, String nOfEvts, String openForWriting, String path, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("BlockSize", size);
		table.put("Name", name);
		table.put("Dataset", procDSID);
		table.put("NumberOfFiles", nOfFiles);
		table.put("NumberOfEvents", nOfEvts);
		table.put("OpenForWriting", openForWriting);
		table.put("Path", path);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "Block", table);
	}

	public static PreparedStatement insertLumiSection(Connection conn, String lsNumber, String runID, String startEvNumber, String endEvNumber, String lStartTime, String lEndTime, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("LumiSectionNumber", lsNumber);
		table.put("RunNumber", runID);
		table.put("StartEventNumber", startEvNumber);
		table.put("EndEventNumber", endEvNumber);
		table.put("LumiStartTime", lStartTime);
		table.put("LumiEndTime", lEndTime);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "LumiSection", table);
	}
		       
        public static PreparedStatement insertPerson(Connection conn, String userName, String userDN, String contactInfo, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("Name", userName);
		table.put("DistinguishedName", userDN);
		table.put("ContactInfo", contactInfo);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "Person", table);
	}

        public static PreparedStatement insertBranchHash(Connection conn, String hash, String content, String description,
								String cbUserID, String lmbUserID, String cDate) throws SQLException {

		Hashtable table = new Hashtable();
                table.put("Hash", hash);
                table.put("Content", content);
                table.put("Description", description);
		table.put("CreatedBy", cbUserID);
                table.put("LastModifiedBy", lmbUserID);
                table.put("CreationDate", cDate);
                return getInsertSQL(conn, "BranchHash", table);

	}

	public static PreparedStatement insertParameterSet(Connection conn, String hash, String name, String version, String type, String annotation, String content, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("Hash", hash);
		table.put("Name", name);
		table.put("Version", version);
		table.put("Type", type);
		table.put("Annotation", annotation);
		table.put("Content", content);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "QueryableParameterSet", table);
	}
				       
        public static PreparedStatement insertApplication(Connection conn, String exeID, String versionID, String familyID, String psID, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("ExecutableName", exeID);
		table.put("ApplicationVersion", versionID);
		table.put("ApplicationFamily", familyID);
		table.put("ParameterSetID", psID);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "AlgorithmConfig", table);
	}

	// SQL for inserting ProcessedDatatset and its related tables.
	// ____________________________________________________

	public static PreparedStatement insertProcessedDatatset(Connection conn, String name, String primDSID, String openForWriting, String phyGroupID, String statusID, String aquisitionEra, String globalTag, String xtCrossSection, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("Name", name);
		table.put("PrimaryDataset", primDSID);
		//table.put("OpenForWriting", );OpenForWriting flag can be managed through Status why we duplicate
		table.put("PhysicsGroup", phyGroupID);
		table.put("Status", statusID);
		if (!DBSUtil.isNull(aquisitionEra)) table.put("AquisitionEra", aquisitionEra);
		if (!DBSUtil.isNull(globalTag)) table.put("GlobalTag", globalTag);
		if (!DBSUtil.isNull(xtCrossSection)) table.put("XtCrossSection", xtCrossSection);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "ProcessedDataset", table);
	}
	
	public static PreparedStatement insertPhysicsGroup(Connection conn, String name, String conID, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("PhysicsGroupName", name);
		table.put("PhysicsGroupConvener", conID);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "PhysicsGroup", table);
	}


	// SQL for inserting File and its related tables.
	// ____________________________________________________
	
	//public static String insertFile(Connection conn, String procDSID, String blockID, String lfn, String checksum, String nOfEvents, String size, String fileStatusID, String typeID, String valStatusID, String qMetaData, String cbUserID, String lmbUserID) throws SQLException {
	public static PreparedStatement insertFile(Connection conn, String procDSID, String blockID, String lfn, String checksum, String nOfEvents, String size, String fileStatusID, String typeID, String valStatusID, String qMetaData, String branchHash, String autoCrossSection, String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("LogicalFileName", lfn);
		table.put("Dataset", procDSID);
		table.put("Block", blockID);
		table.put("Checksum", checksum);
		table.put("NumberOfEvents", nOfEvents);
		table.put("FileSize", size);
		table.put("FileStatus", fileStatusID);
		table.put("FileType", typeID);
		table.put("ValidationStatus", valStatusID);
		table.put("QueryableMetadata", qMetaData);
		if (!DBSUtil.isNull(branchHash)) table.put("FileBranch", branchHash);
		if (!DBSUtil.isNull(autoCrossSection)) table.put("AutoCrossSection", autoCrossSection);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "Files", table);
	}
	

        public static PreparedStatement insertAnalysisDataset(Connection conn, String ann,
                             String name,
                             String query,
                             String processedDSID,
                             String typeID,
                             String statusID, 
                             String phyGroupID, 
                             String cbUserID, String lmbUserID, String cDate) throws SQLException
        {
		Hashtable table = new Hashtable();
		table.put("Annotation", ann);
		table.put("Name", name);
		table.put("Query", query);
		table.put("ProcessedDS", processedDSID);
		table.put("Type", typeID);
		table.put("Status", statusID);
		table.put("PhysicsGroup", phyGroupID);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "AnalysisDataset", table);
        }

	public static PreparedStatement insertAnalysisDatasetDefinition(Connection conn, String adsDefName, 
			String path, String userInput, String sqlQuery, String desc,
			String cbUserID, String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("Name", adsDefName);
		table.put("Path", path);
		table.put("UserInput", userInput);
		table.put("SqlQuery", sqlQuery);
		table.put("Description", desc);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "AnalysisDSDef", table);
	}

	public static PreparedStatement insertAnalysisDataset(Connection conn, String analysisDatasetName, 
			String path, String procDSID, String anaDSDefID,
			String adsVer,
			String type, String status, String phyGroupID,
			String desc, String cbUserID, 
			String lmbUserID, String cDate) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("Name", analysisDatasetName);
		table.put("Path", path);
		table.put("ProcessedDS", procDSID);
		table.put("Definition", anaDSDefID);
		table.put("Version", adsVer);
		table.put("Type", type);
		table.put("Status", status);
		table.put("PhysicsGroup", phyGroupID);
		table.put("Description", desc);
		table.put("CreatedBy", cbUserID);
		table.put("LastModifiedBy", lmbUserID);
		table.put("CreationDate", cDate);
		return getInsertSQL(conn, "AnalysisDataset", table);
	}

        public static PreparedStatement insertCompADS(Connection conn, String compADSName,
			String desc,
			String cbUserID,
                        String lmbUserID, String cDate) throws SQLException {
                Hashtable table = new Hashtable();
                table.put("Name", compADSName);
                table.put("Description", desc);
                table.put("CreatedBy", cbUserID);
                table.put("LastModifiedBy", lmbUserID);
                table.put("CreationDate", cDate);
                return getInsertSQL(conn, "CompositeADS", table);
        }

        public static PreparedStatement listExADSFileLumiIDs(Connection conn,  String adsID) throws SQLException {
		String sql = "SELECT DISTINCT \n" +
                        "adsfl.Lumi as LUMIID, \n" +
                        "adsfl.Fileid as FILEID \n" +
                        "FROM "+owner()+"AnalysisDSFileLumi adsfl \n" +
			"WHERE AnalysisDataset = ? \n\t" +
			"ORDER BY adsfl.Fileid, adsfl.Lumi";
		//PreparedStatement ps = DBManagement.getStatement(conn, sql);
		PreparedStatement ps = DBManagement.getStatementScrollable(conn, sql);
		int columnIndx = 1;
                ps.setString(columnIndx++, adsID);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }
	
          
        public static PreparedStatement getADSVersion(Connection conn, String adsName) throws SQLException {
                String sql = "SELECT DISTINCT ads.Version as VERSION \n " +
                        "FROM "+owner()+"AnalysisDataset ads \n " +
                        "WHERE Name = ? \n" +
                        "ORDER by ads.Version desc\n";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                ps.setString(1, adsName);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

        public static PreparedStatement getADSVersionID(Connection conn, String adsName, String version) throws SQLException {
                String sql = "SELECT DISTINCT ads.ID as ID, Version \n " +
                        "FROM "+owner()+"AnalysisDataset ads \n " +
                        "WHERE Name = ? \n" +
                        "AND Version = ? \n";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                ps.setString(1, adsName);
                ps.setString(2, version);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                //return ((String)("SELECT ID AS id FROM " + table + " WHERE " + key + " = '" + value + "'")); 
                return ps;
        }


        public static PreparedStatement getADSID(Connection conn, String adsName) throws SQLException {
                String sql = "SELECT DISTINCT ads.ID as ID, Version \n " +
                        "FROM "+owner()+"AnalysisDataset ads \n " +
                        "WHERE Name = ? \n" +
			"ORDER by Version desc\n";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                ps.setString(1, adsName);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                //return ((String)("SELECT ID AS id FROM " + table + " WHERE " + key + " = '" + value + "'")); 
                return ps;
        }

	public static PreparedStatement listAnalysisDSFileLumi(Connection conn,  String procDSID, 
			Vector algoIDList, Vector fileList, 
                        Vector lumiIDList,Vector runIDList, 
			Vector lumiRangeList, Vector runRangeList, 
			String userCut,	String op,
			String cbUserID, String lmbUserID, String cDate) throws SQLException {
		String sql = "SELECT DISTINCT \n" + 
			"f.ID as FILEID, \n" +
			"ls.ID as LUMIID \n" +
                        "FROM "+owner()+"Files f \n" +
                        "LEFT OUTER JOIN "+owner()+"FileRunLumi fl \n"+
				"ON fl.Fileid = f.ID \n" +
                        "LEFT OUTER JOIN "+owner()+"LumiSection ls \n"+
				"ON fl.Lumi = ls.ID \n";
		
		if(algoIDList.size() > 0) 
			sql += "LEFT OUTER JOIN "+owner()+"FileAlgo fa \n\t" +
			"ON fa.Fileid = f.ID \n";
		
		if((runIDList.size() > 0) || (runRangeList.size() > 0)) 
			sql += "LEFT OUTER JOIN "+owner()+"Runs r \n\t" +
			"ON r.ID = fl.Run \n";

		sql += "WHERE f.Dataset = ? \n\t" ;



		if(algoIDList.size() > 0) {
			String tmpSql = "";
			for(int i = 0 ; i != algoIDList.size(); ++i) {
				if(!DBSUtil.isNull(tmpSql)) tmpSql += ",";
				tmpSql += "?";
			}
			sql += op + " fa.Algorithm IN (" + tmpSql + ") \n\t";
		}
		
		if(fileList.size() > 0) {
			String tmpSql = "";
			for(int i = 0 ; i != fileList.size(); ++i) {
				if(!DBSUtil.isNull(tmpSql)) tmpSql += ",";
				tmpSql += "?";
			}
			sql += op + " f.LogicalFileName IN (" + tmpSql + ") \n\t";
		}


                if(lumiIDList.size() > 0) {
                        String tmpSql = "";
                        for(int i = 0 ; i != lumiIDList.size(); ++i) {
                                if(!DBSUtil.isNull(tmpSql)) tmpSql += ",";
                                tmpSql += "?";
                        }
                        sql += op + " ls.ID IN (" + tmpSql + ") \n\t";
                }

                if(runIDList.size() > 0) {
                        String tmpSql = "";
                        for(int i = 0 ; i != runIDList.size(); ++i) {
                                if(!DBSUtil.isNull(tmpSql)) tmpSql += ",";
                                tmpSql += "?";
                        }
                        sql += op + " r.ID IN (" + tmpSql + ") \n\t";
                }

		//if(!DBSUtil.isNull(algoIDList)) sql += op + " fa.Algorithm IN (?) \n\t";
		//if(!DBSUtil.isNull(fileList)) sql += op + " f.LogicalFileName IN (?) \n\t";
		if(lumiRangeList.size() > 0) { 
			String tmpSql = "";
			for(int i = 0 ; i != lumiRangeList.size(); ++i) {
				if(!DBSUtil.isNull(tmpSql)) tmpSql += " OR ";
				tmpSql += "(ls.LumiSectionNumber BETWEEN ? AND ?)\n\t";
			}
			sql += op + "( " + tmpSql + " )\n\t";
		}
		if(runRangeList.size() > 0) { 
			String tmpSql = "";
			for(int i = 0 ; i != runRangeList.size(); ++i) {
				if(!DBSUtil.isNull(tmpSql)) tmpSql += " OR ";
				tmpSql += "(r.RunNumber BETWEEN ? AND ?)\n\t";
			}
			sql += op + "( " + tmpSql + " )\n\t";
		}
		
		
		sql += "ORDER BY f.ID, ls.ID";
		//System.out.println("The SQL query is " + sql);

		//PreparedStatement ps = DBManagement.getStatement(conn, sql);
		PreparedStatement ps = DBManagement.getStatementScrollable(conn, sql);
		//System.out.println("Line 1" );
                int columnIndx = 1;
		ps.setString(columnIndx++, procDSID);
		for(int i = 0 ; i != algoIDList.size(); ++i) ps.setString(columnIndx++, (String)algoIDList.get(i));
		for(int i = 0 ; i != fileList.size(); ++i) ps.setString(columnIndx++, (String)fileList.get(i));
                for(int i = 0 ; i != lumiIDList.size(); ++i) ps.setString(columnIndx++, (String)lumiIDList.get(i));
                for(int i = 0 ; i != runIDList.size(); ++i) ps.setString(columnIndx++, (String)runIDList.get(i));
 		
		for(int i = 0 ; i != lumiRangeList.size(); ++i) {
			String tmp[] = (String[])lumiRangeList.get(i);
			ps.setString(columnIndx++, tmp[0]);
			ps.setString(columnIndx++, tmp[1]);
		}
		for(int i = 0 ; i != runRangeList.size(); ++i) {
			String tmp[] = (String[])runRangeList.get(i);
			ps.setString(columnIndx++, tmp[0]);
			ps.setString(columnIndx++, tmp[1]);
		}
		//System.out.println("The SQL query is " + ps);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listBlockValues(Connection conn, String blockID) throws SQLException {
		String sql = "SELECT count(*) AS NUMBER_OF_FILES, \n" +
 			"SUM(NumberOfEvents) AS NUMBER_OF_EVENTS, \n" +
  			"SUM(FileSize) AS FILE_SIZE \n" + 
			"FROM "+owner()+"Files f \n" +
			"JOIN "+owner()+"FileStatus st \n" +
				"ON st.id = f.FileStatus \n" +
			"WHERE  st.Status <> 'INVALID' \n" +
			"AND f.Block = ?";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, blockID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;

	}

	public static PreparedStatement updateBlock(Connection conn, String blockID, 
			String blockSize, String numberOfFiles, 
			String numberOfEvents, String lmbUserID) throws SQLException {
		String sql = "UPDATE "+owner()+"Block \n" +
			"SET LastModifiedBy = ? ,\n" +
			"BlockSize = ? , \n" +
			"NumberOfFiles = ? , \n" +
			"NumberOfEvents = ? \n" +
			"WHERE ID = ?" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		//Do this when there are no files in the block. If this is done in the somgle query then Sum of NumberOfFiles can be Null
		if(DBSUtil.isNull(blockSize)) blockSize = "0";
		if(DBSUtil.isNull(numberOfEvents)) numberOfEvents = "0";
		if(DBSUtil.isNull(numberOfFiles)) numberOfFiles = "0";
                int columnIndx = 1;
		ps.setString(columnIndx++, lmbUserID);
		ps.setString(columnIndx++, blockSize);
		ps.setString(columnIndx++, numberOfFiles);
		ps.setString(columnIndx++, numberOfEvents);
		ps.setString(columnIndx++, blockID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement openBlock(Connection conn, String blockID, String lmbUserID) throws SQLException {
                String sql = "UPDATE "+owner()+"Block \n" +
                        "SET LastModifiedBy = ? , \n" +
                        "OpenForWriting = 1 \n" +
                        "WHERE ID = ?" ;
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, lmbUserID);
                ps.setString(columnIndx++, blockID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }


	public static PreparedStatement closeBlock(Connection conn, String blockID, String lmbUserID) throws SQLException {
		String sql = "UPDATE "+owner()+"Block \n" +
			"SET LastModifiedBy = ? , \n" +
			"OpenForWriting = 0 \n" +
			"WHERE ID = ?" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
		ps.setString(columnIndx++, lmbUserID);
		ps.setString(columnIndx++, blockID);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
        }


	public static PreparedStatement deleteMap(Connection conn, String tableName, String key1, String key2, String value1, String value2) throws SQLException {	
		String sql = "DELETE FROM \n" +owner()+
			tableName + "\n" +
			"WHERE \n" +
			key1 + " = ?\n" +
			"AND " + key2 + " = ?\n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
		ps.setString(columnIndx++, value1);
		ps.setString(columnIndx++, value2);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement deleteName(Connection conn, String tableName, String key, String value) throws SQLException {	
		String sql = "DELETE FROM \n" +owner()+
			tableName + "\n" +
			"WHERE \n" +
			key + " = ?\n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
		ps.setString(columnIndx++, value);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listDatasetSummary(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT COUNT(*) as COUNT,\n " + 
			"SUM(NumberOfEvents) as NUMBER_OF_EVENTS,\n " + 
			"SUM(NumberOfFiles) as NUMBER_OF_FILES,\n " + 
			"SUM(BlockSize) as TOTAL_SIZE\n " + 
			"FROM "+owner()+"Block b\n " + 
			"WHERE b.Dataset = ? \n ";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
		ps.setString(columnIndx++, procDSID);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	//FIXME Just use this and delete all other getBlockIds
	public static PreparedStatement getBlock(Connection conn, String procDSID,  String blockName) throws SQLException {
		String sql = "SELECT DISTINCT b.ID as ID, \n " +
			"b.Name as NAME, \n" +
			"b.BlockSize as BLOCKSIZE, \n" +
			"b.NumberOfFiles as NUMBER_OF_FILES, \n" +
			"b.OpenForWriting as OPEN_FOR_WRITING \n" +
			"FROM "+owner()+"Block b \n";
		if(procDSID != null && blockName != null) {
			sql += "WHERE b.Dataset = ? AND  b.Name = ? \n";
		} else if(procDSID != null) {
			sql += "WHERE b.Dataset = ? \n";
		} else if(blockName != null) {
			sql += "WHERE b.Name = ? \n";
		}

		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		if(procDSID != null && blockName != null) {
                	ps.setString(columnIndx++, procDSID);
                	ps.setString(columnIndx++, blockName);
		} else if(procDSID != null) {
                	ps.setString(columnIndx++, procDSID);
		} else if(blockName != null) {
                	ps.setString(columnIndx++, blockName);
		}

                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement getOpenBlockID(Connection conn, String processedDSID, String blockNamePattern, Vector seVector) throws SQLException {
                String sql = "SELECT DISTINCT blk.ID as ID, \n" +
                             "blk.BlockSize as BLOCKSIZE, \n" +
                             "blk.NumberOfFiles as NUMBER_OF_FILES \n" +
                             "From Block blk \n";
                              if (seVector.size() > 0) {
                                      sql += " LEFT OUTER JOIN "+owner()+"SEBlock seb \n" +
                                                " ON seb.BlockID = blk.ID \n" +
                                             " LEFT OUTER JOIN "+owner()+"StorageElement se \n" +
                                                " ON se.ID = seb.SEID \n";
                              }
                              sql += " WHERE blk.Dataset = ?";

                if (seVector.size() > 0) {
                        String tmpSql = "";
                        for(int i = 0 ; i !=seVector.size(); ++i) {
                                if(!DBSUtil.isNull(tmpSql)) tmpSql += ",";
                                tmpSql += "?";
                        }
                        sql += " AND se.SEName IN ("+ tmpSql + ") \n\t";
                }

                if ( !DBSUtil.isNull(blockNamePattern) ) {
                        sql += " AND blk.Name like '%"+blockNamePattern+"%'";
                }
                sql += " AND blk.OpenForWriting=1";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, processedDSID);
                for(int i = 0 ; i != seVector.size(); ++i) ps.setString(columnIndx++, (String)seVector.get(i));

                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }


        public static PreparedStatement getBlockID(Connection conn, String blockName) throws SQLException {
                String sql = "SELECT DISTINCT b.ID as ID, \n" +
                             "b.BlockSize as BLOCKSIZE, \n" +
                             "b.NumberOfFiles as NUMBER_OF_FILES, \n" +
                             "b.OpenForWriting as OPEN_FOR_WRITING \n" +
                             "FROM "+owner()+"Block b \n" +
                             "WHERE b.Name = ?";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, blockName);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }


	// ____________________________________________________
	
        public static PreparedStatement getDataTierOrder(Connection conn) throws SQLException {
                String sql = "SELECT DataTierOrder as DATATIERORDER \n" +
                                "FROM "+owner()+"DataTierOrder";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }
	
	
        public static PreparedStatement listRowsInTable(Connection conn, String tableName, String from, String rows) 
        throws SQLException
        {
                String sql = "SELECT * \n"+
                                "FROM "+owner()+tableName ;
                if (!DBSUtil.isNull(from) || !rows.equals("*") ) {
                        sql +=  " WHERE\n" ;
                        sql +=  " ID between ? and ? \n";
                }
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                if (!DBSUtil.isNull(from) || !rows.equals("*") ) {

                        Integer iFrom = Integer.parseInt(from);
                        Integer iRows = Integer.parseInt(rows);

                        String to = String.valueOf(iFrom.intValue() + iRows.intValue() - 1);

                        ps.setString(1, from);
                        ps.setString(2, to);

                }
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
              return ps;
        }

	
        public static PreparedStatement listLumiIDsForRun(Connection conn, String runNumber)
        throws SQLException
        {
              //We can expand this query if we need, for now we don't !
              String sql = "SELECT DISTINCT ls.id as ID \n "+
                           "FROM "+owner()+"LumiSection ls \n"+
                           "WHERE ls.RunNumber = ? \n";
              PreparedStatement ps = DBManagement.getStatement(conn, sql);
              ps.setString(1, runNumber);
              DBSUtil.writeLog("\n\n" + ps + "\n\n");
              return ps;
        }

        public static PreparedStatement listLumiSections(Connection conn, String procDSID) throws SQLException {
		//FIXME We can expand this query if we need, for now we don't !
		String sql = "SELECT DISTINCT ls.id as ID, \n " +
			"ls.LumiSectionNumber as LUMISECTIONNUMBER, \n " +
			"ls.RunNumber as RUNNUMBER \n" +
			"FROM "+owner()+"LumiSection ls \n" +
			"JOIN Runs rs\n" +	
				"ON rs.ID = ls.ID\n" +
			"JOIN ProcDSRuns pdsr\n" +
				"ON pdsr.Run = rs.ID\n" +
			"WHERE pdsr.Dataset = ?";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listPersons(Connection conn) throws SQLException {
		String sql = "SELECT DISTINCT p.ID as ID, \n" +
			"p.DistinguishedName as DN \n" +
			"FROM "+owner()+"Person p\n" +
			"ORDER BY p.DistinguishedName DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;

	}

	public static PreparedStatement listProcessedDatasets(Connection conn) throws SQLException {
		String sql = "SELECT procds.id as ID, \n" +
			"primds.Name as PRIMARY_DATATSET_NAME, \n" +
			"procds.name as PROCESSED_DATATSET_NAME \n" +
			"FROM "+owner()+"ProcessedDataset procds \n" +
			"JOIN "+owner()+"PrimaryDataset primds \n" +
				"ON primds.id = procds.PrimaryDataset \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}






	
	public static PreparedStatement listPrimaryDatasets(Connection conn, String pattern) throws SQLException {
		String sql = "SELECT DISTINCT pd.ID as ID, \n" +
			"pd.Annotation as ANNOTATION, \n" +
			"pd.Name as PRIMARY_NAME, \n" +
			"pd.StartDate as START_DATE, \n"  +
			"pd.EndDate as END_DATE, \n" + 
			"pd.CreationDate as CREATION_DATE, \n" +
			"pd.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"tpd.TriggerPathDescription as TRIGGER_PATH_DESCRIPTION, \n" +
			"md.MCChannelDescription as MC_CHANNEL_DESCRIPTION, \n" +
			"md.MCProduction as MC_PRODUCTION, \n" +
			"md.MCDecayChain as MC_DECAY_CHAIN, \n" +
			"od.Description as OTHER_DESCRIPTION, \n" +
			"ty.Type as TYPE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"PrimaryDataset pd \n" +
			"LEFT OUTER JOIN "+owner()+"PrimaryDSType ty \n" +
				"ON ty.id = pd.Type \n" +
			"LEFT OUTER JOIN "+owner()+"PrimaryDatasetDescription pdd \n" +
				"ON pdd.id = pd.Description \n" +
			"LEFT OUTER JOIN "+owner()+"TriggerPathDescription tpd \n" +
				"ON tpd.id = pdd.TriggerDescriptionID \n" + 
			"LEFT OUTER JOIN "+owner()+"MCDescription md \n" +
				"ON md.id = pdd.MCChannelDescriptionID \n" +
			"LEFT OUTER JOIN "+owner()+"OtherDescription od \n" +
				"ON od.id = pdd.OtherDescriptionID \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = pd.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = pd.LastModifiedBy \n";
			if(pattern.indexOf('%') == -1) sql += "WHERE pd.Name = ?\n";
			else sql += "WHERE pd.Name like ?\n";
			//else sql += "WHERE pd.Name like :abc\n";
			sql += "ORDER BY pd.Name DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, pattern);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listProcessedDatasets(Connection conn, String patternPrim, String patternDT, String patternProc, String patternVer, String patternFam, String patternExe, String patternPS, boolean all) throws SQLException {
		String sql = "SELECT procds.id as id, \n" +
			/*"concat( \n" +
				"concat( \n" +
					"concat( \n" +
						"concat( \n" +
							"concat('/', primds.Name \n" +
							"),'/' \n" +
						"),dt.Name \n" +
					"),'/' \n" +
				"), procds.name \n" +
			") as path, \n" +*/
			"primds.Name as PRIMARY_DATATSET_NAME, \n" +
			"dt.Name as DATA_TIER, \n" +
			"procds.name as PROCESSED_DATATSET_NAME, \n" +
                        //Could be managed by Status field
			//"procds.OpenForWriting as OPEN_FOR_WRITING, \n" +
			"pds.Status as STATUS, \n" +
			"procds.AquisitionEra as ACQUISITION_ERA, \n" +
			"procds.GlobalTag as GLOBAL_TAG, \n" +
                        "procds.XtCrossSection as XT_CROSS_SECTION, \n" +
			"procds.CreationDate as CREATION_DATE, \n" +
			"procds.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"pg.PhysicsGroupName as PHYSICS_GROUP_NAME, \n" +
			"perpg.DistinguishedName as PHYSICS_GROUP_CONVENER, \n" +
			"av.Version as APP_VERSION, \n" +
			"af.FamilyName as APP_FAMILY_NAME, \n" +
			"ae.ExecutableName as APP_EXECUTABLE_NAME, \n" +
			"ps.Name as PS_NAME, \n" +
			"ps.Hash as PS_HASH, \n" +
			"ps.Version as PS_VERSION, \n" +
			"ps.Type as PS_TYPE, \n" +
			"ps.Annotation as PS_ANNOTATION, \n" +
			"ps.Content as PS_CONTENT, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY, \n" +
			"blk.Path as PATH \n" +
			"FROM "+owner()+"ProcessedDataset procds \n" +
			"JOIN "+owner()+"PrimaryDataset primds \n" +
				"ON primds.id = procds.PrimaryDataset \n" +
			"LEFT OUTER JOIN "+owner()+"ProcDSTier pdst \n" +
				"ON pdst.Dataset = procds.id \n" +
			"LEFT OUTER JOIN "+owner()+"DataTier dt \n" +
				"ON dt.id = pdst.DataTier \n" +
			"LEFT OUTER JOIN "+owner()+"PhysicsGroup pg \n" +
				"ON pg.id = procds.PhysicsGroup \n" +
			"LEFT OUTER JOIN "+owner()+"Person perpg \n" +
				"ON perpg.id = pg.PhysicsGroupConvener \n" +
			"LEFT OUTER JOIN "+owner()+"ProcAlgo pa \n" +
				"ON pa.Dataset = procds.id \n" +
			"LEFT OUTER JOIN "+owner()+"AlgorithmConfig algo \n" +
				"ON algo.id = pa.Algorithm \n" +
			"LEFT OUTER JOIN "+owner()+"AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"LEFT OUTER JOIN "+owner()+"AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"LEFT OUTER JOIN "+owner()+"AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"LEFT OUTER JOIN "+owner()+"QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n" +
			"LEFT OUTER JOIN "+owner()+"ProcDSStatus pds \n" +
				"ON pds.id = procds.Status \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = procds.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = procds.LastModifiedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Block blk \n" +
				"ON blk.Dataset = procds.id \n";


		/*if(patternPrim == null) patternPrim = "%";
		if(patternDT == null) patternDT = "%";
		if(patternProc == null) patternProc = "%";
		if(patternVer == null) patternVer = "%";
		if(patternFam == null) patternFam = "%";
		if(patternExe == null) patternExe = "%";
		if(patternPS == null) patternPS = "%";*/
		boolean useAnd = false;
		//if(!patternPrim.equals("%") || !patternDT.equals("%") || !patternProc.equals("%") || !patternVer.equals("%") || !patternFam.equals("%") || !patternExe.equals("%") || !patternPS.equals("%")) {
			sql += "WHERE \n";
		if(!patternPrim.equals("%")) {
			sql += " primds.Name like ? \n";
			useAnd = true;
		}
		if(!patternDT.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "dt.Name like ?  \n";
			useAnd = true;
		}
		if(!patternProc.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "procds.name like ? \n";
			useAnd = true;
		}
		if(!patternVer.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "av.Version like ? \n";
			useAnd = true;
		}
		if(!patternFam.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "af.FamilyName like ? \n";
			useAnd = true;
		}
		if(!patternExe.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "ae.ExecutableName like ? \n";
			useAnd = true;
		}
		if(!patternPS.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "ps.Name like ? \n";
			useAnd = true;
		}


		if(!all) {
			if(useAnd) sql += " AND ";
			sql += " pds.Status <> 'INVALID' \n ";
		}
		sql +=	"ORDER BY procds.id, av.Version, af.FamilyName, ae.ExecutableName, ps.Name, dt.Name DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1; 
		if(!patternPrim.equals("%")) ps.setString(columnIndx++, patternPrim);
		if(!patternDT.equals("%")) ps.setString(columnIndx++, patternDT);
		if(!patternProc.equals("%")) ps.setString(columnIndx++, patternProc);
		if(!patternVer.equals("%")) ps.setString(columnIndx++, patternVer);
		if(!patternFam.equals("%")) ps.setString(columnIndx++, patternFam);
		if(!patternExe.equals("%")) ps.setString(columnIndx++, patternExe);
		if(!patternPS.equals("%")) ps.setString(columnIndx++, patternPS);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}



	
	public static PreparedStatement listDatasetPaths(Connection conn) throws SQLException {
		String sql = "SELECT DISTINCT blk.Path as PATH \n" +
			"FROM "+owner()+"Block blk \n" +
			"ORDER BY blk.Path DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement listDatasetADSParent(Connection conn, String procDSID) throws SQLException {

		String sql = "SELECT DISTINCT ads.Name as NAME \n" +
				" FROM " +owner()+"AnalysisDataset ads \n" +
				" JOIN "+owner()+"ProcADSParent padsp \n" +
					" ON ads.ID = padsp.ItsParentADS \n" +
					" AND padsp.ThisDataset = ? \n";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
	}

	/*public static String listPathParent() throws SQLException {
		String sql = "SELECT b.Path as PATH\n" +
			"FROM " + owner() + "Block b \n" +
			"WHERE b.ID in  \n" +
				"\t(SELECT fl.Block \n" +
				"\tFROM " + owner() + "Files fl \n" +
				"\tWHERE fl.ID in ( \n" +
					"\t\tSELECT fp.ItsParent    \n" +
					"\t\tFROM  " + owner() + "FileParentage fp \n" +
					"\t\tWHERE fp.ThisFile in ( \n" +
						"\t\t\tSELECT f.ID from " + owner() + "Files f  \n" +
						"\t\t\tWHERE f.Block in ( \n" +
							"\t\t\t\tSELECT bl.ID FROM " + owner() + "Block   bl WHERE bl.Path = ? \n" +
						") \n" +
					") \n" +
				") \n" +
			")\n";
		
		return sql;
	}*/

	public static String listPathParent() throws SQLException {
		String sql = "\tSELECT fl.Block \n" +
				"\tFROM " + owner() + "Files fl \n" +
				"\tWHERE fl.ID in ( \n" +
					"\t\tSELECT fp.ItsParent    \n" +
					"\t\tFROM  " + owner() + "FileParentage fp \n" +
					"\t\tWHERE fp.ThisFile in ( \n" +
						"\t\t\tSELECT f.ID from " + owner() + "Files f  \n" +
						"\t\t\tWHERE f.Block in ( \n" +
							"\t\t\t\tSELECT bl.ID FROM " + owner() + "Block   bl WHERE bl.Path = ? \n" +
						") \n" +
					") \n" +
				") \n";
		
		return sql;
	}


	public static PreparedStatement listPathParent(Connection conn, String path) throws SQLException {
		String sql = "SELECT DISTINCT b.Path as PATH\n" +
			"FROM " + owner() + "Block b \n" +
			"WHERE b.ID in  \n" +
				"\t(" + listPathParent() + ")\n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, path);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;

	}

	//public static PreparedStatement listDatasetParents(Connection conn, String procDSID) throws SQLException {
	public static PreparedStatement listDatasetProvenence(Connection conn, String procDSID, boolean parentOrChild) throws SQLException {
		//parentOrChild if true means we need to get the parents of the dataset
		//parentOrChild if false means we need to get the childern of the dataset

		String joinStr = "";
		String whereStr = "";
		if (parentOrChild) {
			joinStr = "ON dp.ItsParent = procds.id \n";
			whereStr = "WHERE dp.ThisDataset = ? \n";
		} else {
			joinStr = "ON dp.ThisDataset = procds.id \n";
			whereStr = "WHERE dp.ItsParent = ? \n";
		}

		String sql = "SELECT DISTINCT procds.id as id, \n" +
			"primds.Name as PRIMARY_DATASET_NAME, \n" +
			"procds.name as PROCESSED_DATASET_NAME, \n" +
			"procds.CreationDate as CREATION_DATE, \n" +
			"procds.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"pg.PhysicsGroupName as PHYSICS_GROUP_NAME, \n" +
			"perpg.DistinguishedName as PHYSICS_GROUP_CONVENER, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"ProcessedDataset procds \n" +
			"JOIN "+owner()+"PrimaryDataset primds \n" +
				"ON primds.id = procds.PrimaryDataset \n" +
			"LEFT OUTER JOIN "+owner()+"ProcDSTier pdst \n" +
				"ON pdst.Dataset = procds.id \n" +
			"LEFT OUTER JOIN "+owner()+"PhysicsGroup pg \n" +
				"ON pg.id = procds.PhysicsGroup \n" +
			"JOIN "+owner()+"ProcDSParent dp \n" +
				joinStr +
			"LEFT OUTER JOIN "+owner()+"Person perpg \n" +
				"ON perpg.id = pg.PhysicsGroupConvener \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = procds.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = procds.LastModifiedBy \n";
		
		if(!DBSUtil.isNull(procDSID)) sql += whereStr;

		sql +=	"ORDER BY primds.Name, procds.name DESC";
		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(!DBSUtil.isNull(procDSID)) ps.setString(1, procDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


/*	public static PreparedStatement listDatasetParents(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT DISTINCT procds.id as id, \n" +
			"concat( \n" +
				"concat( \n" +
					"concat( \n" +
						"concat( \n" +
							"concat('/', primds.Name \n" +
							"),'/' \n" +
						//"),dt.Name \n" +
						"),procds.name \n" +
					"),'/' \n" +
				"), 'TIER_DOES_NOT_MATTER' \n" +
			") as PATH, \n" +
			"procds.CreationDate as CREATION_DATE, \n" +
			"procds.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"pg.PhysicsGroupName as PHYSICS_GROUP_NAME, \n" +
			"perpg.DistinguishedName as PHYSICS_GROUP_CONVENER, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM ProcessedDataset procds \n" +
			"JOIN "+owner()+"PrimaryDataset primds \n" +
				"ON primds.id = procds.PrimaryDataset \n" +
			"LEFT OUTER JOIN "+owner()+"ProcDSTier pdst \n" +
				"ON pdst.Dataset = procds.id \n" +
			//"LEFT OUTER JOIN "+owner()+"DataTier dt \n" +
			//	"ON dt.id = pdst.DataTier \n" +
			"LEFT OUTER JOIN "+owner()+"PhysicsGroup pg \n" +
				"ON pg.id = procds.PhysicsGroup \n" +
			"JOIN ProcDSParent dp \n" +
				"ON dp.ItsParent = procds.id \n" +
			"LEFT OUTER JOIN "+owner()+"Person perpg \n" +
				"ON perpg.id = pg.PhysicsGroupConvener \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = procds.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = procds.LastModifiedBy \n";
		
		if(procDSID != null) {
			sql += "WHERE dp.ThisDataset = ? \n";
		}
		sql +=	"ORDER BY PATH DESC";
		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(procDSID != null) {
			ps.setString(1, procDSID);
		}
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}
*/


	public static PreparedStatement listAlgorithms(Connection conn) throws SQLException {
		String sql = "SELECT algo.id as ID, \n" +
			"av.Version as APP_VERSION, \n" +
			"af.FamilyName as APP_FAMILY_NAME, \n" +
			"ae.ExecutableName as APP_EXECUTABLE_NAME, \n" +
			"ps.Hash as PS_HASH \n" +
			"FROM "+owner()+"AlgorithmConfig algo \n" +
			"JOIN "+owner()+"AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN "+owner()+"AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN "+owner()+"AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"JOIN "+owner()+"QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listAlgorithms(Connection conn, String patternVer, String patternFam, String patternExe, String patternPS) throws SQLException {
		String sql = "SELECT algo.id as ID, \n" +
			"av.Version as APP_VERSION, \n" +
			"af.FamilyName as APP_FAMILY_NAME, \n" +
			"ae.ExecutableName as APP_EXECUTABLE_NAME, \n" +
			"ps.Name as PS_NAME, \n" +
			"ps.Hash as PS_HASH, \n" +
			"ps.Version as PS_VERSION, \n" +
			"ps.Type as PS_TYPE, \n" +
			"ps.Annotation as PS_ANNOTATION, \n" +
			"ps.Content as PS_CONTENT, \n" +
			"algo.CreationDate as CREATION_DATE, \n" +
			"algo.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"AlgorithmConfig algo \n" +
			"JOIN "+owner()+"AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN "+owner()+"AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN "+owner()+"AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"JOIN "+owner()+"QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = algo.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = algo.LastModifiedBy \n";

		/*if(patternVer == null) patternVer = "%";
		if(patternFam == null) patternFam = "%";
		if(patternExe == null) patternExe = "%";
		if(patternPS == null) patternPS = "%";*/
		
		sql += "WHERE av.Version like ? \n" +
			"and af.FamilyName like ? \n" +
			"and ae.ExecutableName like ? \n" +
			"and ps.Hash like ? \n" +
			"ORDER BY af.FamilyName, ae.ExecutableName, av.Version, ps.Name DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, patternVer);
		ps.setString(columnIndx++, patternFam);
		ps.setString(columnIndx++, patternExe);
		ps.setString(columnIndx++, patternPS);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listAlgorithms(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT algo.id as ID, \n" +
			"av.Version as APP_VERSION, \n" +
			"af.FamilyName as APP_FAMILY_NAME, \n" +
			"ae.ExecutableName as APP_EXECUTABLE_NAME, \n" +
			"ps.Name as PS_NAME, \n" +
			"ps.Hash as PS_HASH, \n" +
			"ps.Version as PS_VERSION, \n" +
			"ps.Type as PS_TYPE, \n" +
			"ps.Annotation as PS_ANNOTATION, \n" +
			"ps.Content as PS_CONTENT, \n" +
			"algo.CreationDate as CREATION_DATE, \n" +
			"algo.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"AlgorithmConfig algo\n" +
			"JOIN "+owner()+"ProcAlgo pa \n" +
				"ON pa.Algorithm = algo.id \n" +
			"JOIN "+owner()+"AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN "+owner()+"AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN "+owner()+"AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"JOIN "+owner()+"QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = algo.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = algo.LastModifiedBy \n" +
			"WHERE pa.Dataset = ? \n" +
			"ORDER BY af.FamilyName, ae.ExecutableName, av.Version, ps.Name DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, procDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listRuns(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT DISTINCT run.ID as ID, \n " +
			"run.RunNumber as RUN_NUMBER, \n" +
			"run.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
			"run.NumberOfLumiSections as NUMBER_OF_LUMI_SECTIONS, \n" +
			"run.TotalLuminosity as TOTAL_LUMINOSITY, \n" +
			"run.StoreNumber as STRORE_NUMBER, \n" +
			"run.StartOfRun as START_OF_RUN, \n" +
			"run.EndOfRun as END_OF_RUN, \n" +
			"run.CreationDate as CREATION_DATE, \n" +
			"run.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"Runs run \n" +
			"JOIN "+owner()+"ProcDSRuns pdsr \n" +
				"ON pdsr.Run = run.id \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = run.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = run.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE pdsr.Dataset = ? \n";
		}
		sql +=	"ORDER BY run.RunNumber DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	public static PreparedStatement listTiers(Connection conn) throws SQLException {
		String sql = "SELECT dt.ID as ID, \n " +
			"dt.Name as NAME \n" +
			"FROM "+owner()+"DataTier dt \n" +
			"ORDER BY dt.Name DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}
	
	public static PreparedStatement listFileStatus(Connection conn) throws SQLException {
		String sql = "SELECT fs.ID as ID, \n " +
			"fs.Status as STATUS \n" +
			"FROM "+owner()+"FileStatus fs \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listFileTypes(Connection conn) throws SQLException {
		String sql = "SELECT ft.ID as ID, \n " +
			"ft.Type as TYPE \n" +
			"FROM "+owner()+"FileType ft \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listFileValStatus(Connection conn) throws SQLException {
		String sql = "SELECT fs.ID as ID, \n " +
			"fs.Status as STATUS \n" +
			"FROM "+owner()+"FileValidStatus fs \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listTiers(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT DISTINCT dt.ID as ID, \n " +
			"dt.Name as NAME, \n" +
			"dt.CreationDate as CREATION_DATE, \n" +
			"dt.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"DataTier dt \n" +
			"JOIN "+owner()+"ProcDSTier pdst \n" +
				"ON pdst.DataTier = dt.id \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = dt.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = dt.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE pdst.Dataset = ? \n";
		}
		sql +=	"ORDER BY dt.Name DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}
	

	public static PreparedStatement listProcDSStatus(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT DISTINCT pds.Status as STATUS \n " +
			"FROM "+owner()+"ProcDSStatus pds \n" +
			"JOIN "+owner()+"ProcessedDataset pd \n" +
				"ON pd.Status = pds.id \n";

		if(procDSID != null) {
			sql += "WHERE pd.id = ? \n";
		}
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


        public static PreparedStatement listBlockInfo(Connection conn, String blockName) throws SQLException {
		String sql = "SELECT b.ID as ID, \n " +
                        "b.Name as NAME, \n" +
                        "b.Path as PATH, \n" +
                        "b.Dataset as DATASET, \n" +
                        "b.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
                        "b.BlockSize as BLOCKSIZE, \n" +
                        "b.NumberOfFiles as NUMBER_OF_FILES, \n" +
                        "b.OpenForWriting as OPEN_FOR_WRITING \n" +
			"FROM "+owner()+"Block b \n" +
			"WHERE \n"+
			"b.Name = ? \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, blockName);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
        }


	public static PreparedStatement listBlocks(Connection conn, String procDSID, String patternPath, String blockName, String seName) throws SQLException {
		String sql = "SELECT DISTINCT b.ID as ID, \n " +
			"b.Name as NAME, \n" +
			"b.Path as PATH, \n" +
			"b.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
			"b.BlockSize as BLOCKSIZE, \n" +
			"b.NumberOfFiles as NUMBER_OF_FILES, \n" +
			"b.OpenForWriting as OPEN_FOR_WRITING, \n" +
			"b.CreationDate as CREATION_DATE, \n" +
			"b.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"se.SEName as STORAGE_ELEMENT_NAME, \n" +
			"seb.Roles as ROLES, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"Block b \n" +
			"LEFT OUTER JOIN "+owner()+"SEBlock seb \n" +
				"ON seb.BlockID = b.ID \n" +
			"LEFT OUTER JOIN "+owner()+"StorageElement se \n" +
				"ON se.ID = seb.SEID \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = b.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = b.LastModifiedBy \n";

		boolean useAnd = false;
		if(!DBSUtil.isNull(procDSID)) {
			sql += "WHERE \n";
		} else if(!seName.equals("%") || !blockName.equals("%") ){//Assumming seName will never be null
			 sql += "WHERE \n";
		}
		if(!DBSUtil.isNull(procDSID)) {
			sql += "b.Dataset = ? \n";
			useAnd = true;
		}
		if(!DBSUtil.isNull(patternPath)) {
			if(useAnd) sql += " AND ";
			sql += "b.Path = ? \n";
			//sql += "b.Name like ? \n";
			useAnd = true;
		}

		if(!blockName.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "b.Name like ? \n";
			useAnd = true;
		}
		if(!seName.equals("%")) {
			if(useAnd) sql += " AND ";
			sql += "se.SEName like ? \n";
		}
		
		sql +=	"ORDER BY b.Name DESC";
                int columnIndx = 1;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(!DBSUtil.isNull(procDSID)) ps.setString(columnIndx++, procDSID);
		if(!DBSUtil.isNull(patternPath)) ps.setString(columnIndx++, patternPath);
		if(!blockName.equals("%")) ps.setString(columnIndx++, blockName);
		if(!seName.equals("%")) ps.setString(columnIndx++, seName);
		
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listBlocks(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT b.Name as NAME \n" +
			"FROM "+owner()+"Block b \n" +
			"WHERE b.Dataset = ? \n";

                int columnIndx = 1;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(columnIndx++, procDSID);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listBlockContentsInRecycleBin(Connection conn, String path, String blockName) throws SQLException {
		String sql = "SELECT rb.BlockName as BLOCK_NAME, \n" +
			"rb.Xml as XML \n" +
			"FROM "+owner()+"RecycleBin rb \n" +
			"WHERE rb.Path = ? \n";

		if(!DBSUtil.isNull(blockName)) sql += "AND rb.BlockName = ? \n";
                int columnIndx = 1;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(columnIndx++, path);
		if(!DBSUtil.isNull(blockName)) ps.setString(columnIndx++, blockName);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	public static PreparedStatement listStorageElements(Connection conn, String seName) throws SQLException {
		String sql = "SELECT DISTINCT se.ID as ID, \n " +
			"se.SEName as STORAGE_ELEMENT_NAME, \n" +
			"se.CreationDate as CREATION_DATE, \n" +
			"se.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"StorageElement se \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = se.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = se.LastModifiedBy \n";

		if(!seName.equals("%")){
			 sql += "WHERE \n" +
			 	"se.SEName like ? \n";
		}
		sql +=	"ORDER BY se.SEName DESC";
                int columnIndx = 1;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(!seName.equals("%")) ps.setString(columnIndx++, seName);
		
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}
	/*
        public static PreparedStatement listFiles(Connection conn, String procDSID, String path, String runID, boolean listInvalidFiles) throws SQLException {

                String sql = "SELECT DISTINCT f.ID as ID, \n " +
                        "f.LogicalFileName as LFN, \n" +
                        "f.Checksum as CHECKSUM, \n" +
                        "f.FileSize as FILESIZE, \n" +
                        "f.FileBranch as FILE_BRANCH, \n" +
                        "f.QueryableMetaData as QUERYABLE_META_DATA, \n" +
                        "f.CreationDate as CREATION_DATE, \n" +
                        "f.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "f.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
                        "vst.Status as VALIDATION_STATUS, \n" +
                        "st.Status as STATUS, \n" +
                        "ty.Type as TYPE, \n" +
                        "b.Name as BLOCK_NAME, \n"+
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
                        "FROM Files f \n" ;
			if (!DBSUtil.isNull(runID)) {
                        	sql += "JOIN FileRunLumi fr \n" +
                                		"ON fr.Fileid = f.id \n" +
                        		"JOIN Runs r \n" +
                                		"ON r.ID = fr.Run \n";
			}
                        sql += "LEFT OUTER JOIN "+owner()+"Block b \n" +
                                "ON b.id = f.Block \n "+
                        "LEFT OUTER JOIN "+owner()+"FileType ty \n" +
                                "ON ty.id = f.FileType \n" +
                        "LEFT OUTER JOIN "+owner()+"FileStatus st \n" +
                                "ON st.id = f.FileStatus \n" +
                        "LEFT OUTER JOIN "+owner()+"FileValidStatus vst \n" +
                                "ON vst.id = f.ValidationStatus \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = f.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = f.LastModifiedBy \n";
                sql += "WHERE b.Path = ? \n" ;
                sql += "AND f.Dataset = ? \n";
		if (!DBSUtil.isNull(runID)) sql += "AND fr.Run = ? \n";
		if (!listInvalidFiles)	sql +=  "AND st.Status <> 'INVALID' \n";
		sql += "ORDER BY f.LogicalFileName DESC";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);

                int columnIndx=1;

                ps.setString(columnIndx++, path);
                ps.setString(columnIndx++, procDSID);
		if (!DBSUtil.isNull(runID)) ps.setString(columnIndx++, runID);

                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;

	}

	public static PreparedStatement listFiles(Connection conn, String lfn) throws SQLException {

                String sql = "SELECT DISTINCT f.ID as ID, \n " +
                        "f.LogicalFileName as LFN, \n" +
                        "f.Checksum as CHECKSUM, \n" +
                        "f.FileSize as FILESIZE, \n" +
                        "f.FileBranch as FILE_BRANCH, \n" +
                        "f.QueryableMetaData as QUERYABLE_META_DATA, \n" +
                        "f.CreationDate as CREATION_DATE, \n" +
                        "f.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "f.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
                        "vst.Status as VALIDATION_STATUS, \n" +
                        "st.Status as STATUS, \n" +
                        "ty.Type as TYPE, \n" +
                        "b.Name as BLOCK_NAME, \n"+
                        "b.ID as BLOCK_ID, \n"+
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
                        "FROM Files f \n" +
                        "LEFT OUTER JOIN "+owner()+"Block b \n" +
                                "ON b.id = f.Block \n "+
                        "LEFT OUTER JOIN "+owner()+"FileType ty \n" +
                                "ON ty.id = f.FileType \n" +
                        "LEFT OUTER JOIN "+owner()+"FileStatus st \n" +
                                "ON st.id = f.FileStatus \n" +
                        "LEFT OUTER JOIN "+owner()+"FileValidStatus vst \n" +
                                "ON vst.id = f.ValidationStatus \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = f.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = f.LastModifiedBy \n";
                sql += "WHERE f.LogicalFileName = ?\n" ;

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, lfn);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;

	}*/

	
	public static PreparedStatement listFiles(Connection conn, String lfn) throws SQLException {
 		String sql = "SELECT f.ID as ID, \n " +
			"b.ID as BLOCK_ID \n"+
			"FROM "+owner()+"Files f \n" +
			"JOIN "+owner()+"Block b \n" +
				"ON b.id = f.Block \n "+
			"WHERE f.LogicalFileName = ?\n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
		ps.setString(columnIndx++, lfn);
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement listFileBranchID(Connection conn, String lfn) throws SQLException {
		String sql = "SELECT f.FileBranch as FILE_BRANCH \n" +
				"FROM "+owner()+"Files f \n" +
				"WHERE f.LogicalFileName = ?\n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, lfn);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
	}

	public static PreparedStatement listLFNs(Connection conn, String procDSID, String patternMetaData) throws SQLException {
		String sql = "SELECT DISTINCT f.LogicalFileName as LFN, \n" +
			"ftrig.TriggerTag as TRIGGER_TAG, \n" +
			"ftrig.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
			"f.CreationDate as CREATION_DATE, \n" +
			"f.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"Files f \n" +
			"LEFT OUTER JOIN "+owner()+"Block b \n" +
				"ON b.id = f.Block \n "+  
			"LEFT OUTER JOIN "+owner()+"FileTriggerTag ftrig \n" +
				"ON ftrig.Fileid = f.id \n "+  
			"LEFT OUTER JOIN "+owner()+"FileStatus st \n" +
				"ON st.id = f.FileStatus \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = f.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = f.LastModifiedBy \n" +
			"WHERE f.Dataset = ? \n" +
			"AND b.OpenForWriting = 0 \n " ;
		if(!patternMetaData.equals("%")) sql += "AND f.QueryableMetaData like ? \n";

		sql +=	"AND st.Status <> 'INVALID' \n" +
			"ORDER BY f.LogicalFileName DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                
                int columnIndx=1;
		ps.setString(columnIndx++, procDSID);
		if(!patternMetaData.equals("%")) ps.setString(columnIndx++, patternMetaData);
		
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

/*
	public static PreparedStatement listFiles(Connection conn, String procDSID, String aDSID, String blockID, Vector tierIDList, String patternLFN, boolean listInvalidFiles) throws SQLException {
		String joinStrAna = "";
		if(!DBSUtil.isNull(aDSID)) {
			joinStrAna = "JOIN AnalysisDSFileLumi adfl \n" +
				"ON adfl.fileid = f.ID \n";
		}
		String joinStrTier = "";
		for(int i = 0 ; i != tierIDList.size(); ++i) {
			String index = String.valueOf(i);
			joinStrTier += "LEFT OUTER JOIN "+owner()+"FileTier fdt" + index + "\n" +
				"ON fdt" + index + ".Fileid = f.id \n";
		}


		//System.out.println("Block ID is "+blockID);
		String sql = "SELECT DISTINCT f.ID as ID, \n " +
			"f.LogicalFileName as LFN, \n" +
			"f.Checksum as CHECKSUM, \n" +
			"f.FileSize as FILESIZE, \n" +
                        "f.FileBranch as FILE_BRANCH, \n" +
			"f.QueryableMetaData as QUERYABLE_META_DATA, \n" +
			"f.CreationDate as CREATION_DATE, \n" +
			"f.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"f.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
			"vst.Status as VALIDATION_STATUS, \n" +
			"st.Status as STATUS, \n" +
			"ty.Type as TYPE, \n" +
			"b.Name as BLOCK_NAME, \n"+ 
    			//"b.ID as BLOCK_ID, \n"+ 
			//"dt.Name as DATA_TIER, \n"+ 
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM Files f \n" +
			"LEFT OUTER JOIN "+owner()+"Block b \n" +
				"ON b.id = f.Block \n "+  
			//"LEFT OUTER JOIN "+owner()+"FileTier fdt \n" +
			//	"ON fdt.Fileid = f.id \n" +
			joinStrTier +
			joinStrAna +
			//"LEFT OUTER JOIN "+owner()+"DataTier dt \n" +
			//	"ON dt.id = fdt.DataTier " +
			"LEFT OUTER JOIN "+owner()+"FileType ty \n" +
				"ON ty.id = f.FileType \n" +
			"LEFT OUTER JOIN "+owner()+"FileStatus st \n" +
				"ON st.id = f.FileStatus \n" +
			"LEFT OUTER JOIN "+owner()+"FileValidStatus vst \n" +
				"ON vst.id = f.ValidationStatus \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = f.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = f.LastModifiedBy \n";
		sql += "WHERE f.LogicalFileName like ? \n" ;
		if(!DBSUtil.isNull(procDSID)) sql += "AND f.Dataset = ? \n";
		if(!DBSUtil.isNull(blockID)) sql += "AND f.Block = ? \n";
		for(int i = 0 ; i != tierIDList.size(); ++i) sql += "AND fdt" + String.valueOf(i) + ".DataTier = ?\n\t";

		*/
		/*if(!DBSUtil.isNull(tierID)){
			sql += "AND fdt.DataTier = ? \n";
		}*/
/*		if(!DBSUtil.isNull(aDSID)) {
			sql += "AND adfl.AnalysisDataset = ? \n";
		}

		if (!listInvalidFiles)	sql +=  "AND st.Status <> 'INVALID' \n";
		//sql +=	"AND st.Status <> 'INVALID' \n" +
		sql +=	"ORDER BY f.LogicalFileName DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                
                int columnIndx=1;
  
		ps.setString(columnIndx++, patternLFN);
		if(!DBSUtil.isNull(procDSID)){
			ps.setString(columnIndx++, procDSID);
		}
		if(!DBSUtil.isNull(blockID)){
			ps.setString(columnIndx++, blockID);
		}
*/
		/*if(!DBSUtil.isNull(tierID)){
			ps.setString(columnIndx++, tierID);
		}*/
/*		for(int i = 0 ; i != tierIDList.size(); ++i) ps.setString(columnIndx++, (String)tierIDList.get(i));
		if(!DBSUtil.isNull(aDSID)) {
			ps.setString(columnIndx++, aDSID);
		}
		
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}
*/
	public static PreparedStatement listFiles(Connection conn, 
			String procDSID, 
			String path, 
			String runID, 
			String aDSID, 
			String blockID, 
			Vector tierIDList, 
			String patternLFN, 
			ArrayList attributes) throws SQLException {
		String sql = "SELECT DISTINCT f.ID as ID, \n " +
			"f.LogicalFileName as LFN, \n" +
			"f.Checksum as CHECKSUM, \n" +
			"f.FileSize as FILESIZE, \n" +
                        "f.FileBranch as FILE_BRANCH, \n" +
			"f.AutoCrossSection as AUTO_CROSS_SECTION, \n" +
			"f.QueryableMetaData as QUERYABLE_META_DATA, \n";
		if(DBSUtil.contains(attributes, "retrive_date")) 
			sql += "f.CreationDate as CREATION_DATE, \n" + 
				"f.LastModificationDate as LAST_MODIFICATION_DATE, \n" ;
		if(DBSUtil.contains(attributes, "retrive_status") || !DBSUtil.contains(attributes, "retrive_invalid_files")) 
			sql += "vst.Status as VALIDATION_STATUS, \n" +
				"st.Status as STATUS, \n" ;
		if(DBSUtil.contains(attributes, "retrive_type")) 
			sql += "ty.Type as TYPE, \n";
		if(DBSUtil.contains(attributes, "retrive_block")) 
			sql += "b.Name as BLOCK_NAME, \n";
		if(DBSUtil.contains(attributes, "retrive_person")) 
			sql += "percb.DistinguishedName as CREATED_BY, \n" +
				"perlm.DistinguishedName as LAST_MODIFIED_BY, \n" ;
			sql += "f.NumberOfEvents as NUMBER_OF_EVENTS \n" +
				"FROM "+owner()+"Files f \n";
		if(DBSUtil.contains(attributes, "retrive_block")) { 
			String whereClause = "";
			if(!DBSUtil.isNull(path)) whereClause = "WHERE Path = ? ";
			else if(!DBSUtil.isNull(procDSID)) whereClause = "WHERE Dataset = ? ";
			sql += "JOIN (SELECT Name, ID from "+owner()+"Block " + whereClause + ") b \n" +
					"ON b.id = f.Block \n ";
		}
		if(DBSUtil.contains(attributes, "retrive_type")) 
			sql += "JOIN "+owner()+"FileType ty \n" +
					"ON ty.id = f.FileType \n" ;
		if(DBSUtil.contains(attributes, "retrive_status") || !DBSUtil.contains(attributes, "retrive_invalid_files")) 
			sql += "JOIN "+owner()+"FileStatus st \n" +
					"ON st.id = f.FileStatus \n" +
				"JOIN "+owner()+"FileValidStatus vst \n" +
					"ON vst.id = f.ValidationStatus \n" ;
		if(DBSUtil.contains(attributes, "retrive_person")) 
			sql += "JOIN "+owner()+"Person percb \n" +
					"ON percb.id = f.CreatedBy \n" +
				"JOIN "+owner()+"Person perlm \n" +
					"ON perlm.id = f.LastModifiedBy \n";
		if(!DBSUtil.isNull(aDSID)) 
			sql += "JOIN "+owner()+"AnalysisDSFileLumi adfl \n" +
					"ON adfl.fileid = f.ID \n";
		if(!DBSUtil.isNull(runID)) 
			sql += "JOIN "+owner()+"FileRunLumi fr \n" + 
					"ON fr.Fileid = f.id \n" +
				"JOIN "+owner()+"Runs r \n" +
					"ON r.ID = fr.Run \n";

		for(int i = 0 ; i != tierIDList.size(); ++i) {
			String index = String.valueOf(i);
			sql += "LEFT OUTER JOIN "+owner()+"FileTier fdt" + index + "\n" +
				"ON fdt" + index + ".Fileid = f.id \n";
		}
		sql += "WHERE ";
		boolean useAnd = false;
		if(!DBSUtil.isNull(patternLFN))  {
                        if(patternLFN.indexOf('%') == -1) sql += "f.LogicalFileName = ? \n";
                        else sql += "f.LogicalFileName like ? \n";
			//sql += "f.LogicalFileName like ? \n" ;
			useAnd = true;
		}
		if(!DBSUtil.isNull(procDSID) && DBSUtil.isNull(path)) {
			if(useAnd) sql += " AND ";
			sql += "f.Dataset = ? \n";
			useAnd = true;
		}
		if(!DBSUtil.isNull(blockID)) {
			if(useAnd) sql += " AND ";
			sql += "f.Block = ? \n";
			useAnd = true;
		}
		for(int i = 0 ; i != tierIDList.size(); ++i) {
			if(useAnd) sql += " AND ";
			sql += "fdt" + String.valueOf(i) + ".DataTier = ?\n\t";
			useAnd = true;
		}
		if(!DBSUtil.isNull(aDSID)) {
			if(useAnd) sql += " AND ";
			sql += "adfl.AnalysisDataset = ? \n";
			useAnd = true;
		}
		if (!DBSUtil.isNull(runID)) {
			if(useAnd) sql += " AND ";
			sql += "fr.Run = ? \n";
			useAnd = true;
		}
		if (!DBSUtil.isNull(path)) {
			if(useAnd) sql += " AND ";
			sql += "f.Block IN (SELECT bl.ID from "+owner()+"Block bl where bl.Path = ? ) \n" ;
			useAnd = true;
		}
		if (!DBSUtil.contains(attributes, "retrive_invalid_files")) {
			if(useAnd) sql += " AND ";
			sql +=  "st.Status <> 'INVALID' \n";
			useAnd = true;
		}


		//sql +=	"ORDER BY f.LogicalFileName DESC";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx=1;
		if(DBSUtil.contains(attributes, "retrive_block")) { 
			if(!DBSUtil.isNull(path)) ps.setString(columnIndx++, path);
			else if(!DBSUtil.isNull(procDSID)) ps.setString(columnIndx++, procDSID);
		}
		if(!DBSUtil.isNull(patternLFN)) ps.setString(columnIndx++, patternLFN);
		if(!DBSUtil.isNull(procDSID) && DBSUtil.isNull(path)) ps.setString(columnIndx++, procDSID);
		if(!DBSUtil.isNull(blockID)) ps.setString(columnIndx++, blockID);
		for(int i = 0 ; i != tierIDList.size(); ++i) ps.setString(columnIndx++, (String)tierIDList.get(i));
		if(!DBSUtil.isNull(aDSID)) ps.setString(columnIndx++, aDSID);
		if(!DBSUtil.isNull(runID)) ps.setString(columnIndx++, runID);
		if(!DBSUtil.isNull(path)) ps.setString(columnIndx++, path);
		
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	//public static PreparedStatement listFileProvenence(Connection conn, String fileID, boolean parentOrChild) throws SQLException {
	public static PreparedStatement listFileProvenence(Connection conn, String fileID, boolean parentOrChild, boolean listInvalidFiles) throws SQLException {
		return listFileProvenence(conn, fileID, parentOrChild, listInvalidFiles, true) ;
	}
	public static PreparedStatement listFileProvenence(Connection conn, String fileID, boolean parentOrChild, boolean listInvalidFiles, boolean detail) throws SQLException {
		//parentOrChild if true means we need to get the parents of the file 
		//parentOrChild if false means we need to get the childern of the file
		String joinStr = "";
		String whereStr = "";
		if (parentOrChild) {
			joinStr = "ON fp.ItsParent = f.ID \n";
			whereStr = "WHERE fp.ThisFile = ? \n";
		} else {
			joinStr = "ON fp.ThisFile = f.ID \n";
			whereStr = "WHERE fp.ItsParent = ? \n";
		}
			
		String sql = "SELECT DISTINCT f.ID as ID, \n " +
			"f.LogicalFileName as LFN \n"; 
		
			if(detail) sql += ",f.Checksum as CHECKSUM, \n" +
				"f.FileSize as FILESIZE, \n" +
				"f.QueryableMetaData as QUERYABLE_META_DATA, \n" +
				"f.CreationDate as CREATION_DATE, \n" +
				"f.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
				"f.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
				"f.ValidationStatus as VALIDATION_STATUS, \n" +
				"st.Status as STATUS, \n" +
				"ty.Type as TYPE, \n" +
                        	"b.Name as BLOCK_NAME, \n"+ 
				"percb.DistinguishedName as CREATED_BY, \n" +
				"perlm.DistinguishedName as LAST_MODIFIED_BY \n";
			
			sql += "FROM "+owner()+"Files f \n" +
				"JOIN "+owner()+"FileParentage fp \n" +
					joinStr +
				"LEFT OUTER JOIN "+owner()+"FileStatus st \n" +
					"ON st.id = f.FileStatus \n";
			
			if(detail) sql += "LEFT OUTER JOIN "+owner()+"Block b \n" +
				"ON b.id = f.Block \n "+  
				"LEFT OUTER JOIN "+owner()+"FileType ty \n" +
					"ON ty.id = f.FileType \n" +
                	        //"LEFT OUTER JOIN "+owner()+"FileValidStatus vst \n" +
                        	//        "ON vst.id = f.ValidationStatus \n" +
				"LEFT OUTER JOIN "+owner()+"Person percb \n" +
					"ON percb.id = f.CreatedBy \n" +
				"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
					"ON perlm.id = f.LastModifiedBy \n";
	
		if(!DBSUtil.isNull(fileID)) {
			sql += whereStr;
			if (!listInvalidFiles) sql += " AND \n";
		} else {
			if (!listInvalidFiles) sql += " WHERE\n";
		}
		if (!listInvalidFiles)	sql +=  "st.Status <> 'INVALID' \n";
		sql +=	"ORDER BY f.LogicalFileName DESC";
		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(!DBSUtil.isNull(fileID)) {
			ps.setString(1, fileID);
		}
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listFilesChildern(Connection conn, String blockID) throws SQLException {
			
		String sql = "SELECT DISTINCT f.ID as ID \n " +
			"FROM "+owner()+"Files f \n" +
			"JOIN "+owner()+"FileParentage fp \n" +
				"ON fp.ThisFile = f.ID \n" +
			" WHERE\n" +
			"fp.ItsParent IN (SELECT ID from Files where Block = ?)\n";
		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, blockID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listFileTiers(Connection conn, String fileID) throws SQLException {
		return listFileTiers(conn, fileID, true);
	}
	public static PreparedStatement listFileTiers(Connection conn, String fileID, boolean detail) throws SQLException {
		String sql = "SELECT DISTINCT dt.ID as ID, \n " +
			"dt.Name as NAME \n";
		if(detail) sql += ",dt.CreationDate as CREATION_DATE, \n" +
			"dt.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n";
		sql +=	"FROM "+owner()+"DataTier dt \n" +
			"JOIN "+owner()+"FileTier ft \n" +
				"ON ft.DataTier = dt.id \n";
		if(detail) sql += "LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = dt.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = dt.LastModifiedBy \n";
		if(fileID != null) {
			sql += "WHERE ft.Fileid = ? \n";
		}

		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(fileID != null) {
			ps.setString(1, fileID);
		}
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement listBranch(Connection conn, String branchID) throws SQLException {
		return listBranch(conn, branchID, true);
	}
        public static PreparedStatement listBranch(Connection conn, String branchID, boolean detail) throws SQLException {
                String sql = "SELECT br.ID as ID, \n " +
			"br.Description as DESCRIPTION, \n" +
                        "br.Hash as HASH, \n" +
                        "br.Content as CONTENT \n";
		if(detail) sql += ",br.CreationDate as CREATION_DATE, \n" +
			"br.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
				"percb.DistinguishedName as CREATED_BY, \n" +
				"perlm.DistinguishedName as LAST_MODIFIED_BY \n";
		sql += "FROM "+owner()+"BranchHash br \n";
		if(detail) sql += "LEFT OUTER JOIN "+owner()+"Person percb \n" +
			"ON percb.id = br.CreatedBy \n" +
				"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = br.LastModifiedBy \n";
		sql +=	"WHERE br.id = ? \n";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                ps.setString(1, branchID);
                
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

       public static PreparedStatement listFileTrigs(Connection conn, String fileID) throws SQLException {
                String sql = "SELECT DISTINCT ftrig.ID as ID, \n " +
                        	"ftrig.TriggerTag as TRIGGER_TAG, \n" +
				"ftrig.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
				"ftrig.CreationDate as CREATION_DATE, \n" +
				"ftrig.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
	                        "percb.DistinguishedName as CREATED_BY, \n" +
	                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
        	                "FROM "+owner()+"FileTriggerTag ftrig \n" +
		                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
        	                        	"ON percb.id = ftrig.CreatedBy \n" +
                		        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                		"ON perlm.id = ftrig.LastModifiedBy \n";
				if(fileID != null) {
		                        sql += "WHERE ftrig.Fileid = ? \n";
                		}

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                if(fileID != null) {
                        ps.setString(1, fileID);
                }
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

       public static PreparedStatement listFileAssoc(Connection conn, String fileID) throws SQLException {
               String sql = "SELECT DISTINCT f.ID as ID, \n " +
                        "f.LogicalFileName as LFN, \n" +
                        "f.Checksum as CHECKSUM, \n" +
                        "f.FileSize as FILESIZE, \n" +
                        "f.QueryableMetaData as QUERYABLE_META_DATA, \n" +
                        "f.CreationDate as CREATION_DATE, \n" +
                        "f.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "f.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
                        "f.ValidationStatus as VALIDATION_STATUS, \n" +
                        "st.Status as STATUS, \n" +
                        "ty.Type as TYPE, \n" +
                        "b.Name as BLOCK_NAME, \n"+
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
                        "FROM "+owner()+"Files f \n" +
                        "JOIN "+owner()+"FileAssoc fa \n" +
				"ON fa.ItsAssoc = f.ID \n" +
                        "LEFT OUTER JOIN "+owner()+"Block b \n" +
                                "ON b.id = f.Block \n "+
                        "LEFT OUTER JOIN "+owner()+"FileType ty \n" +
                                "ON ty.id = f.FileType \n" +
                        "LEFT OUTER JOIN "+owner()+"FileStatus st \n" +
                                "ON st.id = f.FileStatus \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = f.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = f.LastModifiedBy \n";

                if(!DBSUtil.isNull(fileID)) {
                        sql +=  "WHERE fa.ThisFile = ? \n";
                }

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                if(fileID != null) {
                        ps.setString(1, fileID);
                }
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

	public static PreparedStatement listFileAlgorithms(Connection conn, String fileID) throws SQLException {
		return listFileAlgorithms(conn, fileID, true);
	}
	public static PreparedStatement listFileAlgorithms(Connection conn, String fileID, boolean detail) throws SQLException {
		String sql = "SELECT DISTINCT algo.id as ID, \n" +
			"av.Version as APP_VERSION, \n" +
			"af.FamilyName as APP_FAMILY_NAME, \n" +
			"ae.ExecutableName as APP_EXECUTABLE_NAME, \n" +
			"ps.Hash as PS_HASH \n";
		if(detail) sql += ",ps.Name as PS_NAME, \n" +
			"algo.CreationDate as CREATION_DATE, \n" +
			"algo.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n";
		sql += 	"FROM "+owner()+"AlgorithmConfig algo \n" +
			"JOIN "+owner()+"FileAlgo fa \n" +
				"ON fa.Algorithm = algo.id \n" +
			"JOIN "+owner()+"AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN "+owner()+"AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN "+owner()+"AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"JOIN "+owner()+"QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n";
		if(detail) sql += "LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = algo.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = algo.LastModifiedBy \n";
		if(fileID != null) {
			sql += "WHERE fa.Fileid = ? \n";
		}
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(fileID != null) {
			ps.setString(1, fileID);
		}
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listFileLumis(Connection conn, String fileID) throws SQLException {
		return listFileLumis(conn, fileID, true);
	}
	public static PreparedStatement listFileLumis(Connection conn, String fileID, boolean detail) throws SQLException {
		String sql = "SELECT DISTINCT lumi.id as ID, \n" +
			"lumi.LumiSectionNumber as LUMI_SECTION_NUMBER, \n" +
			"r.RunNumber as RUN_NUMBER, \n" +
			"lumi.StartEventNumber as START_EVENT_NUMBER, \n" +
			"lumi.EndEventNumber as END_EVENT_NUMBER, \n" +
			"lumi.LumiStartTime as LUMI_START_TIME, \n" +
			"lumi.LumiEndTime as LUMI_END_TIME \n";
		if(detail) sql += ",lumi.CreationDate as CREATION_DATE, \n" +
			"lumi.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n";
		sql +=	"FROM "+owner()+"LumiSection lumi \n" +
			"JOIN "+owner()+"FileRunLumi fl \n" +
				"ON fl.Lumi = lumi.id \n" +
			"JOIN "+owner()+"Runs r \n" +
				"ON r.ID = fl.Run \n";
		if(detail) sql += "LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = lumi.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = lumi.LastModifiedBy \n";
		if(fileID != null) {
			sql += "WHERE fl.Fileid = ? \n";
		}
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		if(fileID != null) {
			ps.setString(1, fileID);
		}
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement listADSFileLumis(Connection conn, String aDSID, String fileID) throws SQLException {
                String sql = "SELECT DISTINCT lumi.id as ID, \n" +
                        "lumi.LumiSectionNumber as LUMI_SECTION_NUMBER, \n" +
                        "r.RunNumber as RUN_NUMBER, \n" +
                        "lumi.StartEventNumber as START_EVENT_NUMBER, \n" +
                        "lumi.EndEventNumber as END_EVENT_NUMBER, \n" +
                        "lumi.LumiStartTime as LUMI_START_TIME, \n" +
                        "lumi.LumiEndTime as LUMI_END_TIME, \n" +
                        "lumi.CreationDate as CREATION_DATE, \n" +
                        "lumi.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
                        "FROM "+owner()+"LumiSection lumi \n" +
                        "JOIN "+owner()+"AnalysisDSFileLumi adsfl \n" +
                                "ON adsfl.Lumi = lumi.id \n" +
                        "JOIN "+owner()+"Runs r \n" +
                                "ON r.ID = lumi.RunNumber \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = lumi.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = lumi.LastModifiedBy \n" +
			 "WHERE adsfl.Fileid = ? AND adsfl.AnalysisDataset=? \n";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, fileID);
                ps.setString(columnIndx++, aDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

        public static PreparedStatement listADSFileRuns(Connection conn, String aDSID, String fileID) throws SQLException {
                String sql = "SELECT DISTINCT run.id as ID, \n" +
                        "run.RunNumber as RUN_NUMBER, \n" +
                        "run.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
                        "run.NumberOfLumiSections as NUMBER_OF_LUMI_SECTIONS, \n" +
                        "run.TotalLuminosity as TOTAL_LUMINOSITY, \n" +
                        "run.StoreNumber as STRORE_NUMBER, \n" +
                        "run.StartOfRun as START_OF_RUN, \n" +
                        "run.EndOfRun as END_OF_RUN, \n" +
                        "run.CreationDate as CREATION_DATE, \n" +
                        "run.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"Runs run \n" +
			"JOIN "+owner()+"LumiSection ls \n"+
				"ON ls.RunNumber=run.ID\n"+
                        "JOIN "+owner()+"AnalysisDSFileLumi adsfl \n" +
                                "ON adsfl.lumi = ls.ID \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = run.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = run.LastModifiedBy \n" +
                        "WHERE adsfl.Fileid = ? AND adsfl.AnalysisDataset=? \n";
	
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
		int columnIndx = 1;
                ps.setString(columnIndx++, fileID);
		ps.setString(columnIndx++, aDSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }

	public static PreparedStatement listADSFileLumisExcluded(Connection conn, String aDSID, String fileID) throws SQLException {
		 String sql = "SELECT DISTINCT lumi.id as LUMI_ID, \n" +
			"lumi.LumiSectionNumber as LUMI_SECTION_NUMBER,  \n" +
			"r.RunNumber as RUN_NUMBER,  \n" +
			"lumi.StartEventNumber as START_EVENT_NUMBER,  \n" +
			"lumi.EndEventNumber as END_EVENT_NUMBER,  \n" +
			"lumi.LumiStartTime as LUMI_START_TIME,  \n" +
			"lumi.LumiEndTime as LUMI_END_TIME,  \n" +
			"lumi.CreationDate as CREATION_DATE,  \n" +
			"lumi.LastModificationDate as LAST_MODIFICATION_DATE,  \n" +
			"percb.DistinguishedName as CREATED_BY,  \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY  \n" +
			"FROM "+owner()+"LumiSection lumi  \n" +
			"JOIN "+owner()+"Runs r  \n" +
				"ON r.ID = lumi.RunNumber  \n" +
			"JOIN "+owner()+"ProcDSRuns procdsr \n" +
				"ON r.ID = procdsr.Run  \n" +
			"JOIN "+owner()+"ProcessedDataset procds \n" +
		       		"ON procds.ID = procdsr.Dataset \n" +
			"JOIN "+owner()+"AnalysisDataset ads \n" +
			       	"ON ads.ProcessedDS = procds.ID \n" +
			"JOIN "+owner()+"FileRunLumi frl \n" +
			       	"ON frl.Lumi = lumi.ID \n" +
			       	"AND frl.Run = r.ID \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb  \n" +
				"ON percb.id = lumi.CreatedBy  \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm  \n" +
				"ON perlm.id = lumi.LastModifiedBy  \n" +
			"WHERE ads.ID = ? and frl.Fileid = ? \n" +
			"AND \n" +
				"(lumi.id, lumi.LumiSectionNumber , r.RunNumber ,  \n" +
				" lumi.StartEventNumber , lumi.EndEventNumber ,  \n" +
				" lumi.LumiStartTime , lumi.LumiEndTime , lumi.CreationDate , \n" +
				" lumi.LastModificationDate , percb.DistinguishedName , perlm.DistinguishedName ) \n" +
			"NOT IN \n" +
			"( \n" +
			" SELECT DISTINCT lumi.id as LUMI_ID, \n" +
			" lumi.LumiSectionNumber as LUMI_SECTION_NUMBER,  \n" +
			" r.RunNumber as RUN_NUMBER,  \n" +
			" lumi.StartEventNumber as START_EVENT_NUMBER,  \n" +
			" lumi.EndEventNumber as END_EVENT_NUMBER,  \n" +
			" lumi.LumiStartTime as LUMI_START_TIME,  \n" +
			" lumi.LumiEndTime as LUMI_END_TIME,  \n" +
			" lumi.CreationDate as CREATION_DATE,  \n" +
			" lumi.LastModificationDate as LAST_MODIFICATION_DATE,  \n" +
			" percb.DistinguishedName as CREATED_BY,  \n" +
			" perlm.DistinguishedName as LAST_MODIFIED_BY  \n" +
			" FROM "+owner()+"LumiSection lumi  \n" +
			" JOIN "+owner()+"AnalysisDSFileLumi adsfl  \n" +
				" ON adsfl.Lumi = lumi.id  \n" +
			" JOIN "+owner()+"Runs r  \n" +
				" ON r.ID = lumi.RunNumber  \n" +
			" LEFT OUTER JOIN "+owner()+"Person percb  \n" +
				" ON percb.id = lumi.CreatedBy  \n" +
			" LEFT OUTER JOIN "+owner()+"Person perlm  \n" +
				" ON perlm.id = lumi.LastModifiedBy  \n" +
			" WHERE  adsfl.AnalysisDataset = ? AND adsfl.Fileid = ?\n" +
			") \n";

 		 PreparedStatement ps = DBManagement.getStatement(conn, sql);
 		 int columnIndx = 1;
 		 ps.setString(columnIndx++, aDSID);
 		 ps.setString(columnIndx++, fileID);
 		 ps.setString(columnIndx++, aDSID);
 		 ps.setString(columnIndx++, fileID);
 		 DBSUtil.writeLog("\n\n" + ps + "\n\n");
 		 return ps;


	}
	
	public static PreparedStatement listFileRuns(Connection conn, String fileID) throws SQLException {
		String sql = "SELECT DISTINCT run.id as ID, \n" +                        "run.RunNumber as RUN_NUMBER, \n" +
                        "run.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
                        "run.NumberOfLumiSections as NUMBER_OF_LUMI_SECTIONS, \n" +
                        "run.TotalLuminosity as TOTAL_LUMINOSITY, \n" +
                        "run.StoreNumber as STRORE_NUMBER, \n" +
                        "run.StartOfRun as START_OF_RUN, \n" +
                        "run.EndOfRun as END_OF_RUN, \n" +
                        "run.CreationDate as CREATION_DATE, \n" +
                        "run.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
                        "FROM "+owner()+"Runs run \n" +
                        "JOIN "+owner()+"FileRunLumi fl \n" +
                                "ON run.ID = fl.Run \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = run.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = run.LastModifiedBy \n";
                if(fileID != null) {
                        sql += "WHERE fl.Fileid = ? \n";
                }
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                if(fileID != null) {
                        ps.setString(1, fileID);
                }
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
        }



	public static PreparedStatement listAnalysisDatasetDefinition(Connection conn, String patternName) throws SQLException {
		//String sql = "SELECT DISTINCT adsdef.ID as ID, \n " +
		String sql = "SELECT adsdef.ID as ID, \n " +
			"adsdef.Name as ANALYSIS_DATASET_DEF_NAME, \n" +
			"adsdef.Path as PATH, \n" +
			"adsdef.UserInput as USER_INPUT, \n" +
			"adsdef.SQLQuery as SQL_QUERY, \n" +
			"adsdef.Description as DESCRIPTION, \n" +
			"adsdef.CreationDate as CREATION_DATE, \n" +
			"adsdef.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM "+owner()+"AnalysisDSDef adsdef \n" +
			"LEFT OUTER JOIN "+owner()+"Person percb \n" +
				"ON percb.id = adsdef.CreatedBy \n" +
			"LEFT OUTER JOIN "+owner()+"Person perlm \n" +
				"ON perlm.id = adsdef.LastModifiedBy \n" +
			"WHERE adsdef.Name like  ? \n" +
				"ORDER BY adsdef.Name DESC";

		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, patternName);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

        public static PreparedStatement listCompADS(Connection conn, String patternName) throws SQLException {
                //String sql = "SELECT DISTINCT adsdef.ID as ID, \n " +
                String sql = "SELECT comads.ID as ID, \n " +
			"comads.Name as COMPADS_NAME, \n" +
                        "comads.Description as DESCRIPTION, \n" +
                        "comads.CreationDate as CREATION_DATE, \n" +
                        "comads.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
                        "FROM "+owner()+"CompositeADS comads \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = comads.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = comads.LastModifiedBy \n" +
                        "WHERE comads.Name like  ? \n" +
                                "ORDER BY comads.Name DESC";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                ps.setString(1, patternName);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
	}

      public static PreparedStatement listADSForCompADS(Connection conn, String comADSID) throws SQLException {

                String sql = "SELECT ads.ID as ID, \n " +
                        "ads.Name as ANALYSIS_DATASET_NAME, \n " +
                        "ads.Path as ANALYSIS_DATASET_PATH, \n " +
                        "ads.ProcessedDS as PROCDSID, \n" +
                        "ads.Definition as DEFID, \n" +
                        "ads.Type as TYPE, \n" +
                        "ads.Status as STATUS, \n" +
                        "ads.Description as DESCRIPTION, \n" +
                        "ads.CreationDate as CREATION_DATE, \n" +
                        "ads.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
                        "ads.Version as VERSION, \n" +
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY, \n" +
                        "pg.PhysicsGroupName as PHYSICS_GROUP_NAME \n" +

                        "FROM "+owner()+"AnalysisDataset ads \n" +
                        "JOIN CompADSMap compadsmap \n"+
                                "ON ads.ID = compadsmap.ADS \n"+
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = ads.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = ads.LastModifiedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"PhysicsGroup pg \n" +
                                "ON pg.id = ads.PhysicsGroup \n" +
                        "WHERE compadsmap.CompADS= ? \n";

                sql += "ORDER BY ads.NAME, ads.Version DESC";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, comADSID);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
	}


      public static PreparedStatement listAnalysisDataset(Connection conn, String patternName, String version, String procDSID) throws SQLException {
                //String sql = "SELECT DISTINCT ads.ID as ID, \n " +
		/*If version is not provided must pick LATEST Version only		
		if ( DBSUtil.isNull(version)){	
			sql += "MAX(ads.Version) as VERSION, \n";
		} else {
			sql += "ads.Version as VERSION, \n";
		}*/

                String sql = "SELECT ads.ID as ID, \n " +
			"ads.Name as ANALYSIS_DATASET_NAME, \n " +
			"ads.Path as ANALYSIS_DATASET_PATH, \n " +
			"ads.ProcessedDS as PROCDSID, \n" +  
			"ads.Definition as DEFID, \n" +  
			"ads.Type as TYPE, \n" +  
			"ads.Status as STATUS, \n" +  
			"ads.Description as DESCRIPTION, \n" +
                        "ads.CreationDate as CREATION_DATE, \n" +
                        "ads.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"ads.Version as VERSION, \n" +
                        "percb.DistinguishedName as CREATED_BY, \n" +
                        "perlm.DistinguishedName as LAST_MODIFIED_BY, \n" +
			"pg.PhysicsGroupName as PHYSICS_GROUP_NAME, \n" +
			"adsdef.ID as ADDID, \n" +
                        "adsdef.Name as ANALYSIS_DATASET_DEF_NAME, \n" +
                        "adsdef.Path as ANALYSIS_DATASET_DEF_PATH, \n" +
                        "adsdef.UserInput as USER_INPUT, \n" +
                        "adsdef.SQLQuery as SQL_QUERY, \n" +
                        "adsdef.Description as ADD_DESCRIPTION, \n" +
                        "adsdef.CreationDate as ADD_CREATION_DATE, \n" +
                        "adsdef.LastModificationDate as ADD_LAST_MODIFICATION_DATE, \n" +
                        "adsdefpercb.DistinguishedName as ADD_CREATED_BY, \n" +
                        "adsdefperlm.DistinguishedName as ADD_LAST_MODIFIED_BY \n" +
                        "FROM "+owner()+"AnalysisDataset ads \n" +
                        "JOIN "+owner()+"AnalysisDSDef adsdef \n"+
                                "ON adsdef.ID = ads.Definition \n"+
                        "LEFT OUTER JOIN "+owner()+"Person adsdefpercb \n" +
                                "ON adsdefpercb.id = adsdef.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person adsdefperlm \n" +
                                "ON adsdefperlm.id = adsdef.LastModifiedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person percb \n" +
                                "ON percb.id = ads.CreatedBy \n" +
                        "LEFT OUTER JOIN "+owner()+"Person perlm \n" +
                                "ON perlm.id = ads.LastModifiedBy \n" +
			"LEFT OUTER JOIN "+owner()+"PhysicsGroup pg \n" +
                                "ON pg.id = ads.PhysicsGroup \n" +
                        "WHERE ads.Name like ? \n";
			
		if (! DBSUtil.isNull(procDSID)) sql += "AND ProcessedDS = ? \n";
		//If version is not provided must pick LATEST Version only		
		if (! DBSUtil.isNull(version))	sql += "AND ads.Version = ? \n";

		/*if ( DBSUtil.isNull(version)){
                	sql += " GROUP BY ads.NAME ";
		} else {
			sql += "AND ads.Version = ? \n";
		}*/

                sql += "ORDER BY ads.NAME, ads.Version DESC";
                PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
                ps.setString(columnIndx++, patternName);
                if (! DBSUtil.isNull(procDSID)) {
			ps.setString(columnIndx++, procDSID);
		}
		if (! DBSUtil.isNull(version)){
			ps.setString(columnIndx++, version);
		}
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
                return ps;
	}

	public static PreparedStatement getID(Connection conn, String table, String key, String value) throws SQLException {
		String sql = "SELECT DISTINCT ID \n " +
			"FROM " + owner()+table + "\n " +
			"WHERE " + key + " = ? \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, value);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		//return ((String)("SELECT ID AS id FROM " + table + " WHERE " + key + " = '" + value + "'")); 
		return ps;
	}

	public static PreparedStatement getMap(Connection conn, String table, String key1, String key2, String value1, String value2) throws SQLException {
		String sql = "SELECT DISTINCT ID, " + key1 + ", " + key2 + " \n " +
			"FROM " + owner()+table + "\n ";
		if(!DBSUtil.isNull(value1) || !DBSUtil.isNull(value2)) sql += "WHERE \n";
		boolean useAnd = false;
		
		if(!DBSUtil.isNull(value1)) { 
			sql += key1 + " = ? \n";
			useAnd = true;
		}
		if(!DBSUtil.isNull(value2)) { 
			if(useAnd) sql += " AND ";
			sql += key2 + " = ? \n";
		}
		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		if(!DBSUtil.isNull(value1)) ps.setString(columnIndx++, value1);
		if(!DBSUtil.isNull(value2)) ps.setString(columnIndx++, value2);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getMapID(Connection conn, String table, String key1, String key2, String value1, String value2) throws SQLException {
		String sql = "SELECT DISTINCT ID \n " +
			"FROM " + owner()+table + "\n " +
			"WHERE " + key1 + " = ? \n" +
			"AND " + key2 + " = ? \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, value1);
		ps.setString(columnIndx++, value2);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}
	
	public static PreparedStatement getMapID(Connection conn, String table, String key1, String key2, String key3, String value1, String value2, String value3) throws SQLException {
		String sql = "SELECT DISTINCT ID \n " +
			"FROM " + owner()+table + "\n " +
			"WHERE " + key1 + " = ? \n" +
			"AND " + key2 + " = ? \n" +
			"AND " + key3 + " = ? \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, value1);
		ps.setString(columnIndx++, value2);
		ps.setString(columnIndx++, value3);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getProcessedDSID(Connection conn, String prim, String proc) throws SQLException {
		String sql = "SELECT DISTINCT procds.ID as ID \n" +
				"FROM "+owner()+"ProcessedDataset procds \n" +
				"JOIN "+owner()+"PrimaryDataset primds \n" +
					"ON primds.id = procds.PrimaryDataset \n" ;
		if(DBSUtil.isNull(prim) || DBSUtil.isNull(proc)) {
			return DBManagement.getStatement(conn, sql);
		}
		sql += "WHERE primds.Name = ? \n" +
			"and procds.Name = ? \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1; 
		ps.setString(columnIndx++, prim);
		ps.setString(columnIndx++, proc);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}

	/*public static PreparedStatement getProcessedDSID(Connection conn, String prim, String dt ,String proc) throws SQLException {
		String sql = "SELECT DISTINCT procds.ID as ID \n" +
				"FROM "+owner()+"ProcessedDataset procds \n" +
				"JOIN "+owner()+"PrimaryDataset primds \n" +
					"ON primds.id = procds.PrimaryDataset \n" +
				"JOIN "+owner()+"ProcDSTier pdst \n" +
					"ON pdst.Dataset = procds.id \n" +
				"JOIN "+owner()+"DataTier dt \n" +
					"ON dt.id = pdst.DataTier \n";
		if(prim == null || dt == null || proc == null) {
			return DBManagement.getStatement(conn, sql);
		}
		sql += "WHERE primds.Name = ? \n" +
			"and dt.Name = ? \n" +
			"and procds.Name = ? \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1; 
		ps.setString(columnIndx++, prim);
		ps.setString(columnIndx++, dt);
		ps.setString(columnIndx++, proc);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}*/


	//public static PreparedStatement getAlgorithmID(Connection conn, String ver, String fam, String exe, String psName) throws SQLException {
	public static PreparedStatement getAlgorithmID(Connection conn, String ver, String fam, String exe, String psHash) throws SQLException {
		String sql = "SELECT DISTINCT algo.id \n" +
			"FROM "+owner()+"AlgorithmConfig algo \n" +
			"JOIN "+owner()+"AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN "+owner()+"AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN "+owner()+"AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"JOIN "+owner()+"QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n" +
			"WHERE av.Version = ? \n" +
			"and af.FamilyName = ? \n" +
			"and ae.ExecutableName = ? \n" +
			"and ps.Hash = ? \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, ver);
		ps.setString(columnIndx++, fam);
		ps.setString(columnIndx++, exe);
		ps.setString(columnIndx++, psHash);
                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	public static PreparedStatement getIntegratedLuminosity(Connection conn, String procDSID, String aDSID, String run, String runRange, String tag) throws SQLException {
		String lumiSO = "CMS_LUMI_PROD_OFFLINE";
		//String dbsSO = "cms_dbs_prod_local_10_reader";
		String sql = "SELECT \n" +
			//r.RunNumber, ls.LumiSectionNumber, ldblt.TAG_NAME,
			"ldblsum.INSTANT_LUMI AS INSTANT_LUMI, \n" +
 			"ldblsum.INSTANT_LUMI_ERR AS INSTANT_LUMI_ERR, \n" +
 			"ldblsum.NORMALIZATION AS NORMALIZATION, \n" +
 			"ldblsum.DEADTIME_NORMALIZATION AS DEADTIME_NORMALIZATION \n" +
	 		"FROM " + lumiSO + ".LUMI_SUMMARIES ldblsum \n" +
	 		"JOIN " + lumiSO + ".LUMI_SECTIONS ldbls \n" +
 				"ON ldblsum.SECTION_ID = ldbls.SECTION_ID \n" +
 			"JOIN " + lumiSO + ".LUMI_VERSION_TAG_MAPS ldblvtm \n" +
				"ON ldblvtm.LUMI_SUMMARY_ID = ldblsum.LUMI_SUMMARY_ID \n" +
			"JOIN " + lumiSO + ".LUMI_TAGS ldblt \n" +
				"ON ldblt.LUMI_TAG_ID =  ldblvtm.LUMI_TAG_ID \n" +
			"JOIN  " + owner() + "Runs r \n" +
				"ON r.RunNumber = ldbls.RUN_NUMBER \n" +
			"JOIN " + owner() + "LumiSection ls \n" +
				"ON ls.RunNumber = r.ID AND ls.LumiSectionNumber = ldbls.LUMI_SECTION_NUMBER \n";
		if (! DBSUtil.isNull(procDSID)) 
			sql +="JOIN " + owner() + "ProcDSRuns pdr \n" +
				"ON pdr.Run = r.ID \n";
		if (! DBSUtil.isNull(aDSID)) 
			sql +="JOIN " + owner() + "AnalysisDSFileLumi adsfl \n" +
				"ON adsfl.Lumi = ls.ID \n";

		sql += "WHERE \n" +
			"ldblt.TAG_NAME = ? \n";
			
               	if (! DBSUtil.isNull(procDSID)) sql += "AND pdr.Dataset = ? \n";
               	if (! DBSUtil.isNull(aDSID)) sql += "AND adsfl.AnalysisDataset = ? \n";
               	if (! DBSUtil.isNull(run)) sql += "AND r.RunNumber = ? \n";

		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx = 1;
		ps.setString(columnIndx++, tag);
		if (! DBSUtil.isNull(procDSID)) ps.setString(columnIndx++, procDSID);
               	if (! DBSUtil.isNull(aDSID)) ps.setString(columnIndx++, aDSID);
               	if (! DBSUtil.isNull(run)) ps.setString(columnIndx++, run);

                DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


        public static PreparedStatement getInsertSQLBatch (Connection conn, String tableName, ArrayList keys, ArrayList values) throws SQLException  {
		String sql = "INSERT INTO " + owner()+tableName + " ( ";
		int first=0;
		String slct = "select ";
		for (int i=0; i!=keys.size();++i) {
			String k=(String)keys.get(i);
			if (first==0) {	
				sql += k;
				slct += " ?";
				first=1;
			} else {
			sql +=	", "+k;
			slct += ", ?";
			}
			
		}
		sql += " ) " + slct + " from dual ";

                PreparedStatement ps = DBManagement.getStatement(conn, sql);

		for ( int j = 0; j < values.size(); j += keys.size() ) {
                	int columnIndx = 1;
			for (int k = 0; k != keys.size(); ++k) {
                        	ps.setString(columnIndx++, (String)values.get(j+k));
			}
                        ps.addBatch(); 
		}

		DBSUtil.writeLog("\n\n" + ps + "\n\n");

                return ps;
	}


	public static PreparedStatement getSelectSQL (Connection conn, String sql, ArrayList bindvals) throws SQLException  {

                        PreparedStatement ps = DBManagement.getStatement(conn, sql);
                        int columnIndx = 1;
                        for (int i=0; i != bindvals.size(); ++i)
                                ps.setString(columnIndx++, (String)bindvals.get(i) );
                        DBSUtil.writeLog("\n\n" + ps + "\n\n");
			return ps;
	}


	private static PreparedStatement getInsertSQL (Connection conn, String tableName, Hashtable table) throws SQLException	{
		String sql = "INSERT INTO " + owner()+tableName + " ( \n";
		String sqlKeys = "  ";
		String sqlValues = "  ";
		Enumeration e = table.keys();
		while(e.hasMoreElements()) {
			String key = (String)e.nextElement();
			if(!DBSUtil.isNull( DBSUtil.get(table, key) )) {
				sqlKeys += "\t" + key + ",\n";
				sqlValues += "\t?,\n";
			}
		}
		sql += sqlKeys.substring(0, sqlKeys.length() - 2) + 
			"\n ) VALUES ( \n" + 
			sqlValues.substring(0, sqlValues.length() - 2) + 
			"\n)\n";
		
		//System.out.println("THE QUERY IS " +sql);
		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		e = table.keys();
                int columnIndx = 1;
		while(e.hasMoreElements()) {
			String key = (String)e.nextElement();
			String value = DBSUtil.get(table, key);
			if(!DBSUtil.isNull(value)) {
				//
				//Special treatment for CreationDate
				//is removed, CreationDate (and LastModificationDate) is a number
				//only and this is how it will be read and stored
				// Anzar Afaq - 03/28/2007
				
				//if(key.equals("CreationDate")) {
				//	ps.setTimestamp(columnIndx++, new Timestamp(Long.valueOf(value)) );
				//} else if(key.equals("Content")) {
				//	ps.setCharacterStream(columnIndx++, (new StringReader(value)), value.length());
					//ps.setClob(columnIndx++, (Clob)(value)) ;
				//} else {
					ps.setString(columnIndx++, value);
				//}
			}
		}
		DBSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;

	}
	
	private static String getSQL(String key, String value) {
		if(!DBSUtil.isNull(value)) {
			return key;
		}
		return "";

	}
	

}
