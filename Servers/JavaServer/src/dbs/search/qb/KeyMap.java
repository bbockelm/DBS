package dbs.search.qb;
import java.util.Hashtable;

public class KeyMap {
	private Hashtable map = new Hashtable();
	public KeyMap(){
		map.put("file", "Files");
		map.put("file.name", "Files.LogicalFileName");
		map.put("file.size", "Files.FileSize");
		map.put("procds", "ProcessedDataset");
		map.put("procds.name", "ProcessedDataset.Name");
		map.put("primds", "PrimaryDataset");
		map.put("primds.name", "PrimaryDataset.Name");
		map.put("block", "Block");
		map.put("block.name", "Block.Name");
		map.put("block.path", "Block.Path");
		map.put("run", "Runs");
		map.put("run.number", "Runs.RunNumber");
		map.put("run.starttime", "Runs.StartOfRun");
		map.put("run.endtime", "Runs.EndOfRun");
		map.put("ls", "LumiSection");
		map.put("ls.number", "LumiSection.LumiSectionNumber");
		map.put("ls.starttime", "LumiSection.LumiStartTime");
		map.put("ls.endtime", "LumiSection.LumiEndTime");
	}
	public String getMappedValue(String key) {
		if(!map.containsKey(key.toLowerCase())) return key;
		return (String)map.get(key.toLowerCase());

	}
}

