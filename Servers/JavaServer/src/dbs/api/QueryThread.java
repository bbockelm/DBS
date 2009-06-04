package dbs.api;

import java.sql.PreparedStatement;
import java.io.Writer;

public class QueryThread extends Thread {
	private Writer out;
	private String userquery;
	private String query;
	private Exception error = null;
	private PreparedStatement statement;
	private DBSApiExecuteQuery querier;
	private String clientVersion;
	public QueryThread(Writer out, DBSApiExecuteQuery querier, String userQuery, String query, PreparedStatement statement, String clientVersion) {
		this.out = out;
		this.query = query;
		this.userquery = userQuery;
		this.statement = statement;
		this.querier = querier;
		this.clientVersion = clientVersion;
		//this.error = null;
		
	}
	public void run() {
		String threadName = Thread.currentThread().getName();
		try {
			if(clientVersion.compareTo("DBS_2_0_6") < 0) querier.runQueryForOldClients(out, query, statement);
			else querier.runQuery(out, userquery, query, statement);
			//System.out.println("Thread " + threadName + " completed");
		} catch (Exception e) {
			System.out.println("\tInterruptedException in thread " + threadName);
			System.out.println("ERROR is " + e.getMessage());
			this.error = e;
		}
	}
	public Exception getError() {
		return this.error;
	}

}
