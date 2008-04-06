package dbs.search.qb;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import dbs.search.parser.Constraint;
import dbs.search.graph.GraphUtil;
import dbs.util.Validate;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.Edge;

public class QueryBuilder {
	KeyMap km = new KeyMap();
	RelationMap rm = new RelationMap();
	GraphUtil u = null;
	public QueryBuilder() {
		u = new GraphUtil("/home/sekhri/DBS/Servers/JavaServer/etc/DBSSchemaGraph.xml");
	}

	public String genQuery(ArrayList kws, ArrayList cs) throws Exception{
		//Store all the keywors both from select and where in allKws
		ArrayList allKws = new ArrayList();
		String query = "SELECT \n\t";
		for (int i =0 ; i!= kws.size(); ++i) {
			String aKw = (String)kws.get(i);
			if (i!=0) query += "\n\t,";
			//If path supplied in select then always use block path. If supplied in where then user procDS ID
			if(Util.isSame(aKw, "path")) {
				allKws = addUniqueInList(allKws, "block");
				query += km.getMappedValue("block.path");
			} else {

				StringTokenizer st = new StringTokenizer(aKw, ".");
				int count = st.countTokens();
				String token = st.nextToken();
				Vertex vFirst = u.getMappedVertex(token);
				allKws = addUniqueInList(allKws,u.getRealFromVertex(vFirst));
				if(count == 1) {
					//Get default from vertex
					query += makeQueryFromDefaults(vFirst);
				} else {
					Vertex vCombined = u.getMappedVertex(aKw);
					if(vCombined == null) {
						query += km.getMappedValue(aKw); 
					} else {
						allKws = addUniqueInList(allKws, u.getRealFromVertex(vCombined));
						query += makeQueryFromDefaults(vCombined);			
						
					}
				}
			}
		}
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint o = (Constraint)obj;
				String key = (String)o.getKey();
				if(!Util.isSame(key, "path")) {
					StringTokenizer st = new StringTokenizer(key, ".");
					int count = st.countTokens();
					String token = st.nextToken();
					Vertex vFirst = u.getMappedVertex(token);
					allKws = addUniqueInList(allKws, u.getRealFromVertex(vFirst));
					if(count != 1) {
						Vertex vCombined = u.getMappedVertex(key);
						if(vCombined != null) allKws = addUniqueInList(allKws, u.getRealFromVertex(vCombined));
					}
				}
			}
		}
		
		//Get the route which determines the join table
		allKws = makeCompleteListOfVertexs(allKws);
		query += genJoins(allKws);
		
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
					if(key.indexOf(".") == -1) throw new Exception("In specifying constraints qualify keys with dot operater. Invalid key " + key);
					query += "\t" + km.getMappedValue(key) + " " ;

					StringTokenizer st = new StringTokenizer(key, ".");
					int count = st.countTokens();
					String token = st.nextToken();
					Vertex vFirst = u.getMappedVertex(token);
					Vertex vCombined = u.getMappedVertex(key);
					if(vCombined == null) {
						query += "\t" + km.getMappedValue(key) + " " ;
					} else {
					        query += "\t" + u.getRealFromVertex(vCombined) + "." + u.getDefaultFromVertex(vCombined) + " ";
						//FIXME default can be list
					}


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

	private String makeQueryFromDefaults(Vertex v){
		String realVal = u.getRealFromVertex(v);
		StringTokenizer st = new StringTokenizer(u.getDefaultFromVertex(v), ",");
		int countDefTokens = st.countTokens();
		String query = "";
		for (int j = 0; j != countDefTokens; ++j) {
			if(j != 0) query += ",";
			query += realVal + "." + st.nextToken();
		}
		return query;

	}
	
	private String genJoins(ArrayList lKeywords) {
		ArrayList uniquePassed = new ArrayList();
		String prev = "";
		String query = "\nFROM\n\t" + (String)lKeywords.get(0) + "\n";
		int len = lKeywords.size();
		for(int i = 1 ; i != len ; ++i ) {
			for(int j = 0 ; j != len ; ++j ) {
				if(i != j) {
					String v1 = (String)lKeywords.get(i);
					String v2 = (String)lKeywords.get(j);
					if(! (isIn(uniquePassed, v1 + "," + v2 )) && !(isIn(uniquePassed, v2 + "," + v1))) {
						if(u.doesEdgeExist(v1, v2)) {
							System.out.println("Relation bwteen " + v1 + " and " + v2 + " is " + u.getRealtionFromVertex(v1, v2));
							String tmp = u.getRealtionFromVertex(v1, v2);
							query += "\tJOIN " + v1 + "\n";
							query += "\t\tON " + tmp + "\n";
							uniquePassed.add(v1 + "," + v2);
							break;
						}
					}
				}
			}
		}

		return query;
	}
	
	private boolean isIn(ArrayList aList, String key) {
		for (int i = 0 ; i != aList.size(); ++i) {
			if( ((String)(aList.get(i) )).equals(key)) return true;
		}
		return false;
	}
	/*private String genJoins(String[] routes) {
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
	}*/


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
		ArrayList route = new ArrayList();
		route.add("PrimaryDataset");
		route.add("ProcessedDataset");
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
	private ArrayList addUniqueInList(ArrayList keyWords, String aKw) {
		for(Object kw: keyWords) {
			if(((String)kw).equals(aKw))return keyWords;
		}
		keyWords.add(aKw);
		return keyWords;
	}

	private ArrayList makeCompleteListOfVertexs(ArrayList lKeywords) {
			int len = lKeywords.size();
			for(int i = 0 ; i != len ; ++i ) {
				boolean isEdge = false;
				for(int j = 0 ; j != len ; ++j ) {
					if(i != j) {
						//System.out.println("Checking " + lKeywords.get(i) + " with " + lKeywords.get(j) );
						if(u.doesEdgeExist((String)lKeywords.get(i), (String)lKeywords.get(j)))	{
							isEdge = true;
							break;
						}
					}
				}
				if(!isEdge) {
					//System.out.println("Shoertest edge in " + (String)lKeywords.get(i) + " --- " + (String)lKeywords.get((i+1)%len));
					List<Edge> lEdges =  u.getShortestPath((String)lKeywords.get(i), (String)lKeywords.get((i+1)%len));
					for (Edge e: lEdges) {
						System.out.println("PATH " + u.getFirstNameFromEdge(e) + "  --- " + u.getSecondNameFromEdge(e));
						lKeywords = addUniqueInList(lKeywords, u.getFirstNameFromEdge(e));
						lKeywords = addUniqueInList(lKeywords, u.getSecondNameFromEdge(e));
					}
					//System.out.println("No edge callin again ---------> \n");
					return makeCompleteListOfVertexs (lKeywords);
				}
			}
		return lKeywords;
	}

	public static void main(String args[]) {
		QueryBuilder qb = new QueryBuilder();
		ArrayList tmp = new ArrayList();
		tmp.add("PrimaryDataset");
		tmp.add("FileType");
		tmp.add("Runs");
		//tmp.add("ProcessedDataset");
		//tmp.add("Files");
		tmp = qb.makeCompleteListOfVertexs(tmp);
		for (int i =0 ; i!=tmp.size() ;++i ) {
			System.out.println("ID " + tmp.get(i));
		}
	}

}
