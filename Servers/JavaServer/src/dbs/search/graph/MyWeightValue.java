
package dbs.search.graph;

import edu.uci.ics.jung.graph.decorators.UserDatumNumberEdgeValue;
import edu.uci.ics.jung.graph.ArchetypeEdge;
public class MyWeightValue extends UserDatumNumberEdgeValue {
	String key;
	public MyWeightValue(Object key) {
		super(key);
		this.key = (String)key;
	}
	public Number getNumber(ArchetypeEdge e) {
		String weight = (String) e.getUserDatum(key);
		//System.out.println("weight is " + weight);
		return new Double(Double.parseDouble(weight));
	}
}
