package ms.cron;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import ms.sql.MSSql;
import ms.api.MSApi;
import ms.api.MSApiLogic;
import ms.util.MSUtil;

public class Start {

	public void getAllPendingRequest() throws Exception {
		MSApi api = new MSApi();
		MSApiLogic apiLogic = new MSApiLogic();
		PrintWriter out =  new PrintWriter(System.out);
		Connection conn = api.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String srcUrl = "";
		String dstUrl = "";
		String path = "";
		try {
			ps =  MSSql.listRequest(conn, "", "", "", "", "", "Queued");
			rs =  ps.executeQuery();
			while(rs.next()) {
				srcUrl = get(rs, "SRC_URL");
				dstUrl = get(rs, "DST_URL");
				path = get(rs, "PATH");
				apiLogic.updateRequest(conn, out, srcUrl, dstUrl, path, "InProgress", "", "");

				System.out.println( "<request id='" + get(rs, "ID") +
							"'\n src_url='" + srcUrl +
							"'\n dst_url='" + dstUrl +
							"'\n path='" + path +
							"'\n dn='" + get(rs, "CREATED_BY") +
							"'\n with_parents='" + get(rs, "WITH_PARENTS") +
							"'\n with_force='" + get(rs, "WITH_FORCE") +
							"'\n notify='" + get(rs, "NOTIFY") +
							"'\n detail='" + get(rs, "DETAIL") +
							"'\n progress='" + get(rs, "PROGRESS") +
							"'\n status='" + get(rs, "STATUS") +
							"'\n />");
				DbsWebApi dwApi = new DbsWebApi("http://cmssrv48.fnal.gov:8282/DBS/servlet/DBSServlet");
				boolean withParents = true;
				boolean withForce = true;
				if(get(rs, "WITH_PARENTS").equals("n")) withParents = false;
				if(get(rs, "WITH_FORCE").equals("n")) withForce = false;
				dwApi.migrateDataset(srcUrl, dstUrl, path, withParents, withForce);
				apiLogic.updateRequest(conn, out, srcUrl, dstUrl, path, "Finished", "100", "Migration is Done");
			}
		}catch(Exception e) {
			apiLogic.updateRequest(conn, out, srcUrl, dstUrl, path, "Halted", "", e.getMessage().replace('\'',' ').replace('<','[').replace('>',']'));
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
       			if(conn != null) {
				conn.clearWarnings();
				conn.close();
			}
			 
		}
	}
	protected String get(ResultSet rs, String key) throws Exception {
		String value = rs.getString(key);
		if(MSUtil.isNull(value)) return "";
		return value;
	}

	public static void main(String args[]){
		Start start = new Start();
		try {
			start.getAllPendingRequest();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
