package dbs.search.qb;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

import dbs.search.parser.Constraint;
import dbs.search.graph.GraphUtil;
import dbs.util.Validate;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.Edge;

public class QueryBuilder {
	KeyMap km;
	//RelationMap rm = new RelationMap();
	private ArrayList bindValues;
	GraphUtil u = null;
	public QueryBuilder() {
		bindValues = new ArrayList();
		km = new KeyMap();
		//u = GraphUtil.getInstance("/home/sekhri/DBS/Servers/JavaServer/etc/DBSSchemaGraph.xml");
		u = GraphUtil.getInstance("WEB-INF/DBSSchemaGraph.xml");
	}
	public ArrayList getBindValues() {
		return bindValues;
	}
	public String genQuery(ArrayList kws, ArrayList cs) throws Exception{
		//Store all the keywors both from select and where in allKws
		ArrayList allKws = new ArrayList();
		String query = "SELECT DISTINCT \n\t";
		for (int i =0 ; i!= kws.size(); ++i) {
			String aKw = (String)kws.get(i);
			if (i!=0) query += "\n\t,";
			//If path supplied in select then always use block path. If supplied in where then user procDS ID
			if(Util.isSame(aKw, "dataset")) {
				allKws = addUniqueInList(allKws, "Block");
				query += "Block.Path";
			} else {

				StringTokenizer st = new StringTokenizer(aKw, ".");
				int count = st.countTokens();
				String token = st.nextToken();
				Vertex vFirst = u.getMappedVertex(token);
				String real = u.getRealFromVertex(vFirst);
				allKws = addUniqueInList(allKws, real);
				//if(Util.isSame(real, "LumiSection")) allKws = addUniqueInList(allKws, "Runs");
				if(count == 1) {
					//Get default from vertex
					query += makeQueryFromDefaults(vFirst);
				} else {

					boolean addQuery = true;
					String token2 = st.nextToken();
					/*if(Util.isSame(token2, "algo")) {
						allKws = addUniqueInList(allKws, "AppFamily");
						allKws = addUniqueInList(allKws, "AppVersion");
						allKws = addUniqueInList(allKws, "AppExecutable");
						allKws = addUniqueInList(allKws, "QueryableParameterSet");
						query += makeQueryFromDefaults(u.getVertex("AppFamily"));			
						query += makeQueryFromDefaults(u.getVertex("AppVersion"));			
						query += makeQueryFromDefaults(u.getVertex("AppExecutable"));			
						query += makeQueryFromDefaults(u.getVertex("QueryableParameterSet"));
						adQuery = false;
					}*/
					if(Util.isSame(token2, "release")) {
						String realName = u.getMappedRealName(token2);//AppVersion
						allKws = addUniqueInList(allKws, realName);
						query += makeQueryFromDefaults(u.getVertex(realName));			
						addQuery = false;
					}
					if(Util.isSame(token2, "count")) {
						query += "COUNT(*)";			
						addQuery = false;
					}

					Vertex vCombined = u.getMappedVertex(aKw);
					if(vCombined == null) {
						String mapVal =  km.getMappedValue(aKw);
						if(addQuery) query += mapVal + makeAs(mapVal); 
					} else {
						allKws = addUniqueInList(allKws, u.getRealFromVertex(vCombined));
						if(addQuery) query += makeQueryFromDefaults(vCombined);			
						
					}
				}
			}
		}
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint o = (Constraint)obj;
				String key = (String)o.getKey();
				if(!Util.isSame(key, "dataset")) {
					StringTokenizer st = new StringTokenizer(key, ".");
					int count = st.countTokens();
					allKws = addUniqueInList(allKws, u.getMappedRealName(st.nextToken()));
					if(count != 1) {
						String token2 = st.nextToken();
						/*if(Util.isSame(token2, "release")) {
							//allKws = addUniqueInList(allKws,  u.getMappedRealName(token2));//AppVersion
							//Do nothing
						} */
						Vertex vCombined = u.getMappedVertex(key);
						if(vCombined != null) allKws = addUniqueInList(allKws, u.getRealFromVertex(vCombined));
							
					}
				} /*else {
					//allKws = addUniqueInList(allKws, "ProcessedDataset");
					allKws = addUniqueInList(allKws, "Block");
					
				}*/
			}
		}
		
		//Get the route which determines the join table
		allKws = makeCompleteListOfVertexs(allKws);

		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint o = (Constraint)obj;
				String key = (String)o.getKey();
				if(Util.isSame(key, "dataset")) {
					if(!isIn(allKws, "Files")) allKws = addUniqueInList(allKws, "Block");
				}
			}
		}
		allKws = makeCompleteListOfVertexs(allKws);

		/*int len = allKws.size();
		for(int i = 0 ; i != len ; ++i ) {
			System.out.println("kw " + (String)allKws.get(i));
		}*/

		
		allKws = sortVertexs(allKws);
		query += genJoins(allKws);

		if (cs.size() > 0) query += "\nWHERE\n";
		
		for (int i =0 ; i!= cs.size(); ++i) {
			Object obj = cs.get(i);
			if(i%2 == 0) {
				Constraint co = (Constraint)obj;
				String key = (String)co.getKey();
				String op = (String)co.getOp();
				String val = (String)co.getValue();
				
				if(Util.isSame(key, "dataset")) {
					// If path is given in where clause it should op should always be =
					//if(!Util.isSame(op, "=")) throw new Exception("When Path is provided operater should be = . Invalid operater given " + op);
					//query += "\tProcessedDataset.ID " + handlePath(val);
					if(isIn(allKws, "Files")) query += "\tFiles.Block ";
					else query += "\tBlock.ID ";
					query += handlePath(val, op);
				} else if(Util.isSame(key, "file.release")) {
					if(!Util.isSame(op, "=")) throw new Exception("When release is provided operater should be = . Invalid operater given " + op);
					query += "\tFileAlgo.Algorithm" + handleRelease(val);
				} else if(Util.isSame(key, "procds.release")) {
					if(!Util.isSame(op, "=")) throw new Exception("When release is provided operater should be = . Invalid operater given " + op);
					query += "\tProcAlgo.Algorithm " + handleRelease(val);
				} else {


					if(key.indexOf(".") == -1) throw new Exception("In specifying constraints qualify keys with dot operater. Invalid key " + key);

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
					else {
						//query += op + " '" + val + "'";
						query += op + " ?";
						bindValues.add(val);
					}
				}

			} else {
				//System.out.println("REL " + (String)obj);
				query += "\n" + ((String)obj).toUpperCase() + "\n";
			}
		}
		//System.out.println("\n\nFINAL query is \n\n" + query);
		return query;
	}

	private String makeAs(String in) {
		return " AS " + in.replace('.', '_') + " ";
	}
	private String makeQueryFromDefaults(Vertex v){
		String realVal = u.getRealFromVertex(v);
		StringTokenizer st = new StringTokenizer(u.getDefaultFromVertex(v), ",");
		int countDefTokens = st.countTokens();
		String query = "";
		for (int j = 0; j != countDefTokens; ++j) {
			if(j != 0) query += ",";
			String token = st.nextToken();
			query += realVal + "." + token + makeAs(realVal + "." + token);
		}
		return query;

	}
	
	private String genJoins(ArrayList lKeywords) {
		//ArrayList uniquePassed = new ArrayList();
		String prev = "";
		String query = "\nFROM\n\t" + (String)lKeywords.get(0) + "\n";
		int len = lKeywords.size();
		for(int i = 1 ; i != len ; ++i ) {
			
			for(int j = (i-1) ; j != -1 ; --j ) {
					String v1 = (String)lKeywords.get(i);
					String v2 = (String)lKeywords.get(j);
					//if(! (isIn(uniquePassed, v1 + "," + v2 )) && !(isIn(uniquePassed, v2 + "," + v1))) {
						if(u.doesEdgeExist(v1, v2)) {
							//System.out.println("Relation bwteen " + v1 + " and " + v2 + " is " + u.getRealtionFromVertex(v1, v2));
							String tmp = u.getRealtionFromVertex(v1, v2);
							query += "\tJOIN " + v1 + "\n";
							query += "\t\tON " + tmp + "\n";
							//uniquePassed.add(v1 + "," + v2);
							break;
						}
					//}
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
			//query += "'" + st.nextToken() + "'";
			query += "?";
			bindValues.add(st.nextToken());
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
		String query = " IN ( \n" +
			"SELECT \n" +
			"\tProcessedDataset.ID " + genJoins(route) +
			"WHERE \n" + 
			//"\tPrimaryDataset.Name = '" + data[1] + "'\n" +
			"\tPrimaryDataset.Name = ?\n" +
			"\tAND\n" +
			//"\tProcessedDataset.Name = '" + data[2] + "'" +
			"\tProcessedDataset.Name = ?" +
			")";
		bindValues.add(data[1]);
		bindValues.add(data[2]);
		return query;
	}

	private String handlePath(String path, String op) throws Exception {
		String query = " IN ( \n" +
			"SELECT \n" +
			"\tBlock.ID FROM Block" +
			"\tWHERE \n" + 
			//"\tBlock.Path " + op + " '" + path + "'\n" +
			"\tBlock.Path " + op + " ?\n" +
			")";
		
		bindValues.add(path);
		return query;
	}

	private String handleRelease(String version) throws Exception {
		Validate.checkWord("AppVersion", version);
		ArrayList route = new ArrayList();
		route.add("AlgorithmConfig");
		route.add("AppVersion");
		String query = " IN ( \n" +
			"SELECT \n" +
			"\tAlgorithmConfig.ID " + genJoins(route) +
			"WHERE \n" + 
			//"\tAppVersion.Version = '" + version + "'\n" +
			"\tAppVersion.Version = ?\n" +
			")";
		bindValues.add(version);
		return query;
	}

	private ArrayList addUniqueInList(ArrayList keyWords, String aKw) {
		for(Object kw: keyWords) {
			if(((String)kw).equals(aKw))return keyWords;
		}
		keyWords.add(aKw);
		return keyWords;
	}

	private ArrayList makeCompleteListOfVertexsOld(ArrayList lKeywords) {
		int len = lKeywords.size();
		if(len <= 1) return lKeywords;
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
					//System.out.println("PATH " + u.getFirstNameFromEdge(e) + "  --- " + u.getSecondNameFromEdge(e));
					lKeywords = addUniqueInList(lKeywords, u.getFirstNameFromEdge(e));
					lKeywords = addUniqueInList(lKeywords, u.getSecondNameFromEdge(e));
				}
				//System.out.println("No edge callin again ---------> \n");
				lKeywords =  makeCompleteListOfVertexs (lKeywords);
				return lKeywords;

			}
		}
		return lKeywords;
	}


	private ArrayList makeCompleteListOfVertexs(ArrayList lKeywords) {
		ArrayList myRoute = new ArrayList();
		myRoute.add(lKeywords.get(0));
		lKeywords.remove(0);
		int len = lKeywords.size();
		int prevLen = 0;
		while(len != 0) {
			boolean breakFree = false;
			for(int i = 0 ; i != len ; ++i ) {
				int lenRount = myRoute.size();
				for(int j = 0 ; j != lenRount ; ++j ) {
					String keyInMyRoute = (String)myRoute.get(j);
					String keyInArray = (String)lKeywords.get(i);
					if(keyInArray.equals(keyInMyRoute)) {
						lKeywords.remove(i);
						breakFree = true;
						break;
					} else if(u.doesEdgeExist(keyInMyRoute, keyInArray))	{
						myRoute = addUniqueInList(myRoute, keyInArray);
						lKeywords.remove(i);
						breakFree = true;
						break;
					}
				}
				if(breakFree) break;
				
			}
			if(prevLen == len) {
				System.out.println("Shortest edge in " + (String)lKeywords.get(0) + " --- " + (String)myRoute.get(0));
				List<Edge> lEdges =  u.getShortestPath((String)lKeywords.get(0), (String)myRoute.get(0));
				for (Edge e: lEdges) {
					//System.out.println("PATH " + u.getFirstNameFromEdge(e) + "  --- " + u.getSecondNameFromEdge(e));
					myRoute = addUniqueInList(myRoute, u.getFirstNameFromEdge(e));
					myRoute = addUniqueInList(myRoute, u.getSecondNameFromEdge(e));
				}
				if(lEdges.size() > 0) lKeywords.remove(0);
			}
			
			prevLen = len;
			len = lKeywords.size();
		}
		return myRoute;
	}


	
	public ArrayList sortVertexs(ArrayList lKeywords) {
		//System.out.println("INSIDE sortVertexs");
		int len = lKeywords.size();
		String leaf = "";
		for(int i = 0 ; i != len ; ++i ) {
			String aVertex = (String)lKeywords.get(i);
			if(isLeaf(aVertex, lKeywords)) {
				leaf = aVertex;
				break;
			}
		}
		if(leaf.equals("")) leaf = (String)lKeywords.get(0);
		ArrayList toReturn = new ArrayList();
		toReturn.add(leaf);
		
		int reps = -1;
		while( toReturn.size() != len) {
			++reps;
			for(int j = 0 ; j != len ; ++j ) {
				String aVertex = (String)lKeywords.get(j);
				if(!aVertex.equals(leaf)) {
					if(!isIn(toReturn, aVertex)) {
						if(isLeaf(aVertex, lKeywords)) {
							//System.out.println(aVertex + " is a leaf toreturn size " + toReturn.size() + " len -1 " + (len - 1));
							if(toReturn.size() == (len - 1)) toReturn = addUniqueInList(toReturn, aVertex);
							else if(reps > len) toReturn = addUniqueInList(toReturn, aVertex); 
						} else {
							for (int k = (toReturn.size() - 1) ; k != -1 ; --k) {
								//System.out.println("Cheking edge between " + (String)toReturn.get(k) + " and " + aVertex);
								if(u.doesEdgeExist((String)toReturn.get(k), aVertex)) {
									toReturn = addUniqueInList(toReturn, aVertex);
									break;
								} //else System.out.println("no edge between " +   (String)toReturn.get(k) + " and " + aVertex);
							}
						}
					}
				}
			}
		}
		
		return toReturn;
	}
	private boolean isLeaf(String aVertex, ArrayList lKeyword) {
		int count = 0;
		Set s = u.getVertex(aVertex).getNeighbors();
		for (Iterator eIt = s.iterator(); eIt.hasNext(); ) {
			String neighbor = u.getRealFromVertex((Vertex) eIt.next());
			if(isIn(lKeyword, neighbor)) ++count;
		}
		if(count == 1) return true;
		return false;

	}

	public static void main(String args[]) {
		QueryBuilder qb = new QueryBuilder();
		ArrayList tmp = new ArrayList();
		GraphUtil u = GraphUtil.getInstance("/home/sekhri/DBS/Servers/JavaServer/etc/DBSSchemaGraph.xml");
		List<Edge> lEdges =  u.getShortestPath("ProcessedDataset", "LumiSection");
		for (Edge e: lEdges) {
			System.out.println("PATH " + u.getFirstNameFromEdge(e) + "  --- " + u.getSecondNameFromEdge(e));
		}

		//tmp.add("PrimaryDataset");
		/*tmp.add("Files");
		tmp.add("LumiSection");
		tmp.add("Runs");
		tmp.add("FileRunLumi");
		//tmp.add("ProcessedDataset");
		//tmp.add("FileType");
		//tmp.add("ProcDSRuns");
		tmp = qb.sortVertexs(tmp);
		//tmp = qb.makeCompleteListOfVertexs(tmp);
		for (int i =0 ; i!=tmp.size() ;++i ) {
			System.out.println("ID " + tmp.get(i));
		}*/
	}

}
