package dbs.search.parser;

import org.antlr.runtime.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import dbs.search.qb.Route;
import dbs.search.qb.KeyMap;
import dbs.search.qb.RelationMap;

public class Test {
	public static void main(String[] args) throws Exception {
	        ANTLRInputStream input = new ANTLRInputStream(System.in);
        	SqlLexer lexer = new SqlLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
        	SqlParser parser = new SqlParser(tokens);
	        parser.stmt();
		ArrayList kws = parser.kws;
		ArrayList allKws = new ArrayList();
		KeyMap km = new KeyMap();
		RelationMap rm = new RelationMap();
		String query = "SELECT \n";
		for (int i =0 ; i!= kws.size(); ++i) {
			String aKw = (String)kws.get(i);
			System.out.println("KEWORD\t" + aKw);
			StringTokenizer st = new StringTokenizer(aKw, ".");
			allKws.add(km.getMappedValue(st.nextToken()));
			if (i!=0) query += "\n,";
			query += km.getMappedValue(aKw);
		}
		ArrayList cs = parser.constraints;
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint o = (Constraint)obj;
				System.out.println("KEY " + o.getKey() + "\t\tOP " + o.getOp() + "\t\tVAL " + o.getValue());
				StringTokenizer st = new StringTokenizer((String)o.getKey(), ".");
				allKws.add(km.getMappedValue(st.nextToken()));

			}else System.out.println("REL " + (String)obj);
		}
		Route r = new Route();
		Object o =  r.getRoute(allKws);
		if(o != null) {
			String[] routes = (String[])o;
			String prev = "";
			query += "\nFROM\n";
			for(String s: routes) {
				if(!prev.equals("")) {
					System.out.println(prev + "," + s);
					String tmp = rm.getMappedValue(prev + "," + s);
					System.out.println(tmp);
					query += "JOIN " + s + "\n";
					query += "\tON " + tmp + "\n";
				} else query += s + "\n";
				prev = s;
			}
		} else {
			System.out.println("Routes are nul");
		}
		
		query += "WHERE\n";
		
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint co = (Constraint)obj;
				String key = (String)co.getKey();
				String op = (String)co.getOp();
				String val = (String)co.getValue();
				
				System.out.println("KEY " + key + "\t\tOP " + op + "\t\tVAL " + val);
				StringTokenizer st = new StringTokenizer(key, ".");
				allKws.add(km.getMappedValue(st.nextToken()));
				query += km.getMappedValue(key) + " " ;
				if(op.toLowerCase().equals("in")) {
					query += "IN (";
					st = new StringTokenizer(val, ",");
					System.out.println("tokenizing " + val + " count is  " + st.countTokens());
					int count =  st.countTokens();
					for(int k = 0 ; k != count ; ++k) {
						System.out.println("k is " + k);
						if(k != 0) query += ",";
						query += "'" + st.nextToken() + "'";
					}
					query += ")";
				}else {
					query += op + " '" + val + "'";
				}

			}else {
				System.out.println("REL " + (String)obj);
				query += "\n" + (String)obj + "\n";
			}
		}
	System.out.println("\n\nFINAL query is \n\n" + query);
 		
    }
}
