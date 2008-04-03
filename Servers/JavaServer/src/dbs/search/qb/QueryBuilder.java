package dbs.search.qb;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import dbs.search.parser.Constraint;
import dbs.util.Validate;

public class QueryBuilder {
	KeyMap km = new KeyMap();
	RelationMap rm = new RelationMap();
	public QueryBuilder() {}

	public String genQuery(ArrayList kws, ArrayList cs) throws Exception{
		//Store all the keywors both from select and where in allKws
		ArrayList allKws = new ArrayList();
		String query = "SELECT \n\t";
		for (int i =0 ; i!= kws.size(); ++i) {
			String aKw = (String)kws.get(i);
			if (i!=0) query += "\n\t,";
			//If path supplied in select then always use block path. If supplied in where then user procDS ID
			if(Util.isSame(aKw, "path")) {
				allKws.add("block");
				query += km.getMappedValue("block.path");
			} else {
				StringTokenizer st = new StringTokenizer(aKw, ".");
				allKws.add(km.getMappedValue(st.nextToken()));
				query += km.getMappedValue(aKw);
			}
		}
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint o = (Constraint)obj;
				String key = (String)o.getKey();
				if(!Util.isSame(key, "path")) {
					StringTokenizer st = new StringTokenizer(key, ".");
					allKws.add(km.getMappedValue(st.nextToken()));
				}
			}
		}
		
		//Get the route which determines the join table
		Route r = new Route();
		Object o =  r.getRoute(allKws);
		if(o != null) query += genJoins((String[])o);
		else throw new Exception("Routes are null");
		
		query += "WHERE\n";
		
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint co = (Constraint)obj;
				String key = (String)co.getKey();
				String op = (String)co.getOp();
				String val = (String)co.getValue();
				
				if(Util.isSame(key, "path")) {
					// If path is given in where clause it should op should always be =
					if(!Util.isSame(op, "=")) throw new Exception("When Path is provided operater should be = . Invalid operater given " + op);
					query += "\tProcessedDataset.ID " + handlePath(val);
				} else {
					query += "\t" + km.getMappedValue(key) + " " ;
					if(Util.isSame(op, "in")) query += handleIn(val);
					else query += op + " '" + val + "'";
				}

			} else {
				//System.out.println("REL " + (String)obj);
				query += "\n" + ((String)obj).toUpperCase() + "\n";
			}
		}
		System.out.println("\n\nFINAL query is \n\n" + query);
		return query;
	}

	private String genJoins(String[] routes) {
		String prev = "";
		String query = "\nFROM\n\t";
		for(String s: routes) {
			if(!prev.equals("")) {
				//System.out.println(prev + "," + s);
				String tmp = rm.getMappedValue(prev + "," + s);
				//System.out.println(tmp);
				query += "\tJOIN " + s + "\n";
				query += "\t\tON " + tmp + "\n";
			} else query += s + "\n";
			prev = s;
		}
		return query;
	}


	private String handleIn(String val) {
    		String query = "IN (";
    		StringTokenizer st = new StringTokenizer(val, ",");
		int count =  st.countTokens();
		for(int k = 0 ; k != count ; ++k) {
			if(k != 0) query += ",";
			query += "'" + st.nextToken() + "'";
		}
		query += ")";
		return query;
	}

	private String handlePath(String path) throws Exception {
		Validate.checkPath(path);
		String[] data = path.split("/");
		if(data.length != 4) {
			throw new Exception("Invalid path " + path);
		}
		String[] route = {"PrimaryDataset", "ProcessedDataset"};
		String query = "IN ( \n" +
			"SELECT \n" +
			"\tProcessedDataset.ID " + genJoins(route) +
			"WHERE \n" + 
			"\tPrimaryDataset.Name = '" + data[1] + "'\n" +
			"\tAND\n" +
			"\tProcessedDataset.Name = '" + data[2] + "'" +
			")";
		return query;
	}

}
