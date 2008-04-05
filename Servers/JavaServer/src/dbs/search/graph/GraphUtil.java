
package dbs.search.graph;

import edu.uci.ics.jung.graph.impl.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.io.GraphMLFile;
import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.utils.Pair;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

public class GraphUtil{
	Graph g;
	public GraphUtil(Graph g){
		this.g = g;
	}
	public GraphUtil(){}
	
	public GraphUtil(String fileName){	
		g = (new GraphMLFile()).load(fileName);
	}

	public Graph loadGraph(String fileName){	
		g = (new GraphMLFile()).load(fileName);
		return g;
	}
	public Graph getGraph(){
		return g;
	}
		
	
	public Vertex getVertex(String vName){
		Set s = g.getVertices();
		for (Iterator eIt = s.iterator(); eIt.hasNext(); ) {
			Vertex v = (Vertex) eIt.next();
			String vNameFromGraph =  (String) v.getUserDatum("real");
			if (vName.equals(vNameFromGraph)) return v;
		}
		return null;
	}
	public List<Edge> getShortestPath( Vertex v1, Vertex v2){
		return  (new DijkstraShortestPath(g)).getPath(v1,v2);
	}
	public List<Edge> getShortestPath(String v1, String v2){
		return  (new DijkstraShortestPath(g)).getPath(getVertex(v1), getVertex(v2));
	}



}
