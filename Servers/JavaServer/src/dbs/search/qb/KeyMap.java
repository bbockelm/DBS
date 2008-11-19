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
		map.put("file.moddate", "Files.LastModificationDate");

		map.put("procds.name", "ProcessedDataset.Name");
		map.put("procds.id", "ProcessedDataset.ID");
		map.put("procds.createdate", "ProcessedDataset.CreationDate");
		map.put("procds.moddate", "ProcessedDataset.LastModificationDate");
		map.put("procds.era", "ProcessedDataset.AquisitionEra");
		map.put("procds.tag", "ProcessedDataset.GlobalTag");
		
		map.put("dataset.name", "ProcessedDataset.Name");
		map.put("dataset.id", "ProcessedDataset.ID");
		map.put("dataset.createdate", "ProcessedDataset.CreationDate");
		map.put("dataset.moddate", "ProcessedDataset.LastModificationDate");
		map.put("dataset.era", "ProcessedDataset.AquisitionEra");
		map.put("dataset.tag", "ProcessedDataset.GlobalTag");
		map.put("dataset.parent", "Block.Path");

		map.put("primds.name", "PrimaryDataset.Name");
		map.put("primds.id", "PrimaryDataset.ID");
		map.put("primds.createdate", "PrimaryDataset.CreationDate");
		map.put("primds.moddate", "PrimaryDataset.LastModificationDate");
		
		map.put("block.name", "Block.Name");
		map.put("block.id", "Block.ID");
		map.put("block.size", "Block.BlockSize");
		map.put("block.dataset", "Block.Path");
		map.put("block.createdate", "Block.CreationDate");
		map.put("block.moddate", "Block.LastModificationDate");
		
		map.put("run.number", "Runs.RunNumber");
		map.put("run.id", "Runs.ID");
		map.put("run.numevents", "Runs.NumberOfEvents");
		map.put("run.numlss", "Runs.NumberOfLumiSections");
		map.put("run.starttime", "Runs.StartOfRun");
		map.put("run.endtime", "Runs.EndOfRun");
		map.put("run.createdate", "Runs.CreationDate");
		map.put("run.moddate", "Runs.LastModificationDate");
		
		map.put("lumi.startevnum", "LumiSection.StartEventNumber");
		map.put("lumi.id", "LumiSection.ID");
		map.put("lumi.endevnum", "LumiSection.EndEventNumber");
		map.put("lumi.number", "LumiSection.LumiSectionNumber");
		map.put("lumi.starttime", "LumiSection.LumiStartTime");
		map.put("lumi.endtime", "LumiSection.LumiEndTime");
		map.put("lumi.createdate", "LumiSection.CreationDate");
		map.put("lumi.moddate", "LumiSection.LastModificationDate");
		
		map.put("ads.name", "AnalysisDataset.Name");
		map.put("ads.id", "AnalysisDataset.ID");
		map.put("ads.version", "AnalysisDataset.Version");
		map.put("ads.dataset", "AnalysisDataset.Path");
		map.put("ads.desc", "AnalysisDataset.Description");
		map.put("ads.procds", "AnalysisDataset.ProcessedDS");
		map.put("ads.createdate", "AnalysisDataset.CreationDate");
		map.put("ads.moddate", "AnalysisDataset.LastModificationDate");
	
		map.put("phygrp.name", "PhysicsGroup.PhysicsGroupName");
		map.put("phygrp.id", "PhysicsGroup.ID");
		map.put("phygrp.createdate", "PhysicsGroup.CreationDate");
		map.put("phygrp.moddate", "PhysicsGroup.LastModificationDate");
		map.put("group.name", "PhysicsGroup.PhysicsGroupName");
		map.put("group.id", "PhysicsGroup.ID");
		map.put("group.createdate", "PhysicsGroup.CreationDate");
		map.put("group.moddate", "PhysicsGroup.LastModificationDate");

		map.put("algo.id", "AlgorithmConfig.ID");
		//map.put("algo.version", "AppVersion.Version");
		//map.put("algo.family", "AppFamily.FamilyName");
		//map.put("algo.exe", "AppExecutable.ExecutableName");
		//map.put("algo.hash", "QueryableParameterSet.Hash");
		map.put("algo.content", "QueryableParameterSet.Content");
		map.put("algo.createdate", "AlgorithmConfig.CreationDate");
		map.put("algo.moddate", "AlgorithmConfig.LastModificationDate");

		map.put("datatype.id", "PrimaryDSType.ID");
		map.put("datatype.type", "PrimaryDSType.Type");
		map.put("datatype.createdate", "PrimaryDSType.CreationDate");
		map.put("datatype.moddate", "PrimaryDSType.LastModificationDate");


		map.put("site.name", "StorageElement.SEName");
		map.put("dq", "Runs");
		map.put("pset", "QueryableParameterSet");
	}
	public String getMappedValue(String key, boolean excep) throws Exception{
		if(!map.containsKey(key.toLowerCase())) {
			if(excep) throw new Exception("The keyword " + key+ " not yet implemented in Query Builder" );
			else return key;
		}
		return (String)map.get(key.toLowerCase());

	}
	public Hashtable getMap() {
		return map;
	}

}

