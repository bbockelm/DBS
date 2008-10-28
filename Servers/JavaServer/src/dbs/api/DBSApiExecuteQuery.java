package dbs.api;

/*
 * (c) 2004, Slav Boleslawski
 *
 * Released under terms of the Artistic Licence
 * http://www.opensource.org/licences/artistic-licence.php
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import db.QueryExecutor;
import dbs.util.DBSUtil;
import dbs.search.qb.DateUtil;

/**
 * This class implements a singlethreaded version of <code>DatabaseQuerier</code> by
 * synchronizing access to the <code>getRecords()</code> method.
 */

public class DBSApiExecuteQuery {
	//private Connection conn;
	private QueryExecutor queryExecutor;	
	
	public DBSApiExecuteQuery() throws SQLException {
		//this.conn = conn;
		queryExecutor = new QueryExecutor();
	}
	
	private String get(ResultSet rs, String key) throws Exception {
		String value = rs.getString(key);
		if(DBSUtil.isNull(value)) return "";
		return value;
	}

	public synchronized void runQuery(Writer out, String query, PreparedStatement statement) throws Exception {
		ResultSet rs = null;
		try {
			rs = queryExecutor.executeQuery(statement, query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			String[] colNames =new String[numberOfColumns];
			for (int i = 0; i != numberOfColumns; ++i) {
				colNames[i] = rsmd.getColumnLabel(i + 1);
			}
			while(rs.next()) {
				out.write(((String) "<result "));
				for (int i = 0; i != numberOfColumns; ++i) {
					out.write(((String) colNames[i] + "='"));
					if(colNames[i].toLowerCase().indexOf("date") != -1) {
						out.write(((String)DateUtil.epoch2DateStr(String.valueOf(Long.valueOf(get(rs, colNames[i]))*1000)) + "' "));
										}
					else out.write(((String) get(rs, colNames[i] ) +"' "));
				}
				out.write(((String) "/>\n"));
			}

		} finally {
			statement.cancel();
			if (rs != null)
				rs.close();
			if (statement != null) {
				statement.close();
			}
		}
	}

	public void close() {
		queryExecutor.close();
	}
}
