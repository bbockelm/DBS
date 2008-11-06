package dbs.search.parser;

import org.antlr.runtime.*;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.io.PrintWriter;

import dbs.api.DBSApi;
import dbs.search.qb.QueryBuilder;

public class Test {
	private DBSApi api = null;
	private PrintWriter out = null;
	private Hashtable user = null;
 	public Test(){
		api = new DBSApi();
		out = new PrintWriter(System.out);
		user = new Hashtable();
		user.put("user_dn", "VIJAY_SEKHRI_DN");
		user.put("user_name", "VIJAY_SEKHRI");
		user.put("contact_info", "VIJAY_SEKHRI_HOME");

	}
	public void processLine(String query) throws Exception {
		System.out.println("PROCESSSING " +query);
		Hashtable table = new Hashtable();
		table.put("apiversion", "DBS_2_0_2");
		table.put("api", "executeQuery");
		table.put("query", query);
		api.call(out, table, user);
	}

	public void runAllQueries(String fileName) throws Exception {
		RandomAccessFile raf = null;
		try{
			raf = new RandomAccessFile(fileName, "r");
			String line = "";
			while((line = raf.readLine() )!= null) {
				processLine(line); 
			}
		}finally {
			if (raf != null) raf.close();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
	        /*ANTLRInputStream input = new ANTLRInputStream(System.in);
        	SqlLexer lexer = new SqlLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
        	SqlParser parser = new SqlParser(tokens);
	        parser.stmt();
		ArrayList kws = parser.kws;
		ArrayList cs = parser.constraints;
		for (int i =0 ; i!= kws.size(); ++i) 
			System.out.println("KEWORD\t" + kws.get(i));
		
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint o = (Constraint)obj;
				System.out.println("KEY " + o.getKey() + "\t\tOP " + o.getOp() + "\t\tVAL " + o.getValue());

			}else System.out.println("REL " + (String)obj);
		}
		QueryBuilder qb = new QueryBuilder("mysql");
		qb.genQuery(kws, cs, parser.okws);*/
		Test t = new Test();
		t.runAllQueries(args[0]);
		//t.processLine(args[0]);
 		
    }
}
