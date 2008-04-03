package dbs.search.qb;
import java.util.Hashtable;

public class RelationMap {
	private Hashtable map = new Hashtable();
	public RelationMap(){
		map.put("PrimaryDataset,ProcessedDataset", "ProcessedDataset.PrimaryDataset = PrimaryDataset.ID");
		map.put("ProcessedDataset,Block", "Block.Dataset = ProcessedDataset.ID");
		map.put("Block,Files", "Files.Block = Block.ID");
		map.put("ProcessedDataset,Files", "Files.Dataset = ProcessedDataset.ID");
	}
	public String getMappedValue(String key) {
		if(!map.containsKey(key)) return key;
		return (String)map.get(key);

	}
}

