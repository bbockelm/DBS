package dbs.search.parser;

import org.antlr.runtime.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.util.StringTokenizer;
import dbs.search.qb.QueryBuilder;

public class Wrapper {
	private ArrayList bindValues ;
	public ArrayList getBindValues() {
		return bindValues;
	}
	public String getQuery(String query) throws Exception {
		
		//ByteArrayInputStream bis = new ByteArrayInputStream(query.getBytes());
		ANTLRStringStream input = new ANTLRStringStream(query);
		//ANTLRInputStream input = new ANTLRInputStream();
	        //ANTLRInputStream input = new ANTLRInputStream(bis);
        	SqlLexer lexer = new SqlLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
        	SqlParser parser = new SqlParser(tokens);
	        parser.stmt();
		ArrayList kws = parser.kws;
		ArrayList cs = parser.constraints;
		/*for (int i =0 ; i!= kws.size(); ++i) 
			System.out.println("KEWORD\t" + kws.get(i));*/
		
		/*for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint o = (Constraint)obj;
				//System.out.println("KEY " + o.getKey() + "\t\tOP " + o.getOp() + "\t\tVAL " + o.getValue());

			}//else System.out.println("REL " + (String)obj);
		}*/
		QueryBuilder qb = new QueryBuilder();
		String queryToReturn = qb.genQuery(kws, cs);
		bindValues = qb.getBindValues();
		return queryToReturn;
		//return "";
 		
    }
}
