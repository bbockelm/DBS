package dbs.search.qb;
import java.util.ArrayList;
import java.util.List;

public class Route {
	private ArrayList routes = new ArrayList();
	public Route() {
		String[] obj1 = {"Block", "Files"};
		String[] obj2 = {"PrimaryDataset", "ProcessedDataset"};
		String[] obj3 = {"PrimaryDataset", "ProcessedDataset", "Files"};
		String[] obj4 = {"PrimaryDataset", "ProcessedDataset", "Block", "Files"};
		String[] obj5 = {"Runs", "FileRunLumi", "Files"};
		String[] obj6 = {"Runs", "LumiSection", "FileRunLumi", "Files"};
		routes.add(obj1);
		routes.add(obj2);
		routes.add(obj3);
		routes.add(obj4);
		routes.add(obj5);
		routes.add(obj6);
		
	}
	public List<String[]> getAllRoutes() {
		return routes;
	}
	public boolean isInRoute(String[] aRoute, String entity) {
		for(String s: aRoute ) {
			//System.out.println("Comparing " + entity.toLowerCase() + " with " + s.toLowerCase() );
			if(entity.toLowerCase().equals(s.toLowerCase())) return true;
		}
		return false;
	}
	public String[] getRoute(List<String> keyWords) {
		for (String[] aRoute: getAllRoutes()) {
			boolean potentialRoute = true;
			for(String kw: keyWords) {
				if(!isInRoute(aRoute, kw)) {
					potentialRoute = false;
					break;
				}
			}
			if (potentialRoute) return aRoute;
		}
		return null;
	}
	
}
