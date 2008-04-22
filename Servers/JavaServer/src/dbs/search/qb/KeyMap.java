package dbs.search.qb;
import java.util.Hashtable;

public class KeyMap {
	private Hashtable map = new Hashtable();
	public KeyMap(){
		map.put("file.name", "Files.LogicalFileName");
		map.put("file.size", "Files.FileSize");
		map.put("file.id", "Files.ID");
		map.put("file.numevents", "Files.NumberOfEvents");
		map.put("file.createdate", "Files.CreationDate");
		map.put("file.moddate", "File.LastModificationDate");

		map.put("procds.name", "ProcessedDataset.Name");
		map.put("procds.createdate", "ProcessedDataset.CreationDate");
		map.put("procds.moddate", "ProcessedDataset.LastModificationDate");
		
		map.put("primds.name", "PrimaryDataset.Name");
		map.put("primds.createdate", "PrimaryDataset.CreationDate");
		map.put("primds.moddate", "PrimaryDataset.LastModificationDate");
		
		map.put("block.name", "Block.Name");
		map.put("block.size", "Block.BlockSize");
		map.put("block.path", "Block.Path");
		map.put("block.createdate", "Block.CreationDate");
		map.put("block.moddate", "Block.LastModificationDate");
		
		map.put("run.number", "Runs.RunNumber");
		map.put("run.numevents", "Runs.NumberOfEvents");
		map.put("run.numlss", "Runs.NumberOfLumiSections");
		map.put("run.starttime", "Runs.StartOfRun");
		map.put("run.endtime", "Runs.EndOfRun");
		map.put("run.createdate", "Runs.CreationDate");
		map.put("run.moddate", "Runs.LastModificationDate");
		
		map.put("ls.startevnum", "LumiSection.StartEventNumber");
		map.put("ls.id", "LumiSection.ID");
		map.put("ls.endevnum", "LumiSection.EndEventNumber");
		map.put("ls.number", "LumiSection.LumiSectionNumber");
		map.put("ls.starttime", "LumiSection.LumiStartTime");
		map.put("ls.endtime", "LumiSection.LumiEndTime");
		map.put("ls.createdate", "LumiSection.CreationDate");
		map.put("ls.moddate", "LumiSection.LastModificationDate");
		
		map.put("dq", "Runs");
	}
	public String getMappedValue(String key) {
		if(!map.containsKey(key.toLowerCase())) return key;
		return (String)map.get(key.toLowerCase());

	}
}

