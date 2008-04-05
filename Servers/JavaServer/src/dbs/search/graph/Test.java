
package dbs.search.graph;

//import edu.uci.ics.jung.graph.impl.DirectedSparseGraph;
//import edu.uci.ics.jung.graph.Graph;
//import edu.uci.ics.jung.io.GraphMLFile;
import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.utils.Pair;
//import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
//import java.util.Iterator;
//import java.util.Set;
import java.util.List;

public class Test{


	public static void main(String args[]) {
		GraphUtil u = new GraphUtil("DBSSchemaGraph.xml");
		/*Graph g = u.getGraph();
		
		for (Iterator eIt = g.getEdges().iterator(); eIt.hasNext(); ) {
			Edge e = (Edge) eIt.next();
			System.out.println(e.getUserDatum("relation"));
		}
		Test t = new Test();*/
		Vertex v1 = null;
		Vertex v2 = null;
		
		//List<Edge> lEdges =  u.getShortestPath( "PrimaryDataset","Files");
		//List<Edge> lEdges =  u.getShortestPath("Files","FileStatus");
		List<Edge> lEdges =  u.getShortestPath("PrimaryDataset","FileStatus");
		for (Edge e: lEdges) {
			Pair p = e.getEndpoints();
			v1 = (Vertex) p.getFirst();
			v2 = (Vertex) p.getSecond();
			System.out.println(v1.getUserDatum("real") + "-->" + v2.getUserDatum("real"));
		}
	}
}
