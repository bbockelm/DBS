package db;

/*
 * (c) 2004, Slav Boleslawski
 *
 * Released under terms of the Artistic Licence
 * http://www.opensource.org/licences/artistic-licence.php
 */

import java.sql.*;

/**
 * This class defines the <code>executeQuery()</code> method, 
 * which is similar to <code>PreparedStatement.executeQuery()</code> method.
 * The difference is that this method can be interrupted by another thread.
 **/

public class QueryExecutor implements Runnable {
	private Thread worker;
	private Params params;
	private Results results;
	private volatile boolean cancelRequest;
	private volatile boolean closeRequest;
	
	private class Params {
		public PreparedStatement statement;
		public String query;
		public boolean pending;
	}
	
	private class Results {
		public ResultSet rs;
		public SQLException exception;
		public boolean serviced;
	}
	

	public QueryExecutor() {
		params = new Params();
		results = new Results();
		worker = new Thread(this);
		worker.start();
	}
	
	/**
	 * Executes an SQL query.
	 * The method can be interrupted by another thread at any moment.
	 * @return <code>ResultSet</code> if execution successful
	 * @exception SQLException if a database error occurs
	 * @exception InterruptedException if interrupted by another thread
	 **/
	
	public synchronized ResultSet executeQuery(PreparedStatement statement, String query) 
			throws SQLException, InterruptedException {
		//Set query parameters
		synchronized(params) {
			params.statement = statement;
			params.query = query;
			params.pending = true;
			params.notify();
		}

		synchronized(results) {
			try {
				//Wait for the query to complete
				while(!results.serviced) {
					results.wait();
				}
				if (results.exception != null) {
					throw results.exception;
				}
			} catch (InterruptedException e) {
				cancel();
				throw e;
			} finally {
				results.serviced = false;
			}
			return results.rs;
		}
	}
	
	private void cancel() {
		cancelRequest = true;
		try {
			params.statement.cancel();
			/*synchronized(results) {
				while(!results.serviced) {
					results.wait();
				}
			}*/
		} catch (SQLException e) {
			return;
		/*} catch (InterruptedException e) {
			return;*/
		} finally {
			cancelRequest = false;
		}
	}
	
	public void close() {
		closeRequest = true;
		if (params.statement != null) {
			cancel();
		}
		worker.interrupt();
		try {
			worker.join();
		} catch (InterruptedException e) {}
	}
	
	public void run() {	
		ResultSet rs = null;
		SQLException ex = null;
		//while(!closeRequest) {
			synchronized(params) {
				try {
					//Wait for query parameters
					while(!params.pending) {
						params.wait();
					}
					params.pending = false;
				} catch (InterruptedException e) {
					if (closeRequest) {
						return;
					}
				}
				//Execute query
				try {
					//rs = params.statement.executeQuery(params.query);
					rs = params.statement.executeQuery();
				} catch (SQLException e) {
					if (!cancelRequest) {
						ex = e;
					}
				}
			}

			//Set query results
			synchronized(results) {
				results.rs = rs;
				results.exception = ex;
				results.serviced = true;
				results.notify();
			}
		//}
	}
}
