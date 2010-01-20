package dbs.search.qb;
import java.util.Hashtable;

public class KeyMap {
	private Hashtable map = new Hashtable();
	public KeyMap(){
		map.put("file.name", "Files.LogicalFileName");
		map.put("file.size", "Files.FileSize");
		map.put("file.id", "Files.ID");
		map.put("file.checksum", "Files.Checksum");
		map.put("file.numevents", "Files.NumberOfEvents");
		map.put("file.createdate", "Files.CreationDate");
		map.put("file.moddate", "Files.LastModificationDate");
//		map.put("file.createby", "Files.CreatedBy");
//		map.put("file.modby", "Files.LastModifiedBy");
		
		map.put("tier.moddate", "DataTier.LastModificationDate");
		map.put("tier.createdate", "DataTier.CreationDate");
		map.put("tier.name", "DataTier.Name");
//		map.put("tier.createby", "DataTier.CreatedBy");
//		map.put("tier.modby", "DataTier.LastModifiedBy");

		map.put("procds.name", "ProcessedDataset.Name");
		map.put("procds.id", "ProcessedDataset.ID");
		map.put("procds.xsection", "ProcessedDataset.XtCrossSection");
		map.put("procds.createdate", "ProcessedDataset.CreationDate");
		map.put("procds.moddate", "ProcessedDataset.LastModificationDate");
		map.put("procds.era", "ProcessedDataset.AquisitionEra");
		map.put("procds.tag", "ProcessedDataset.GlobalTag");
//		map.put("procds.createby", "ProcessedDataset.CreatedBy");
//		map.put("procds.modby", "ProcessedDataset.LastModifiedBy");
		
		map.put("dataset.name", "ProcessedDataset.Name");
		map.put("dataset.id", "ProcessedDataset.ID");
		map.put("dataset.xsection", "ProcessedDataset.XtCrossSection");
		map.put("dataset.createdate", "ProcessedDataset.CreationDate");
		map.put("dataset.moddate", "ProcessedDataset.LastModificationDate");
		map.put("dataset.era", "ProcessedDataset.AquisitionEra");
		map.put("dataset.tag", "ProcessedDataset.GlobalTag");
//		map.put("dataset.createby", "ProcessedDataset.CreatedBy");
//		map.put("dataset.modby", "ProcessedDataset.LastModifiedBy");
		map.put("dataset.parent", "Block.Path"); // is this right?? (comment added by dsr, 2010-01-18)

		map.put("primds.name", "PrimaryDataset.Name");
		map.put("primds.annotation", "PrimaryDataset.Annotation");
		map.put("primds.id", "PrimaryDataset.ID");
		map.put("primds.createdate", "PrimaryDataset.CreationDate");
		map.put("primds.moddate", "PrimaryDataset.LastModificationDate");
//		map.put("primds.createby", "PrimaryDataset.CreatedBy");
//		map.put("primds.modby", "PrimaryDataset.LastModifiedBy");
		
		map.put("block.name", "Block.Name");
		map.put("block.id", "Block.ID");
		map.put("block.size", "Block.BlockSize");
		map.put("block.dataset", "Block.Path");
		map.put("block.numevents", "Block.NumberOfEvents");
		map.put("block.numfiles", "Block.NumberOfFiles");
		map.put("block.status", "Block.OpenForWriting");
		map.put("block.createdate", "Block.CreationDate");
		map.put("block.moddate", "Block.LastModificationDate");
//		map.put("block.createby", "Block.CreatedBy");
//		map.put("block.modby", "Block.LastModifiedBy");
		
		map.put("run.number", "Runs.RunNumber");
		map.put("run.id", "Runs.ID");
		map.put("run.numevents", "Runs.NumberOfEvents");
		map.put("run.numlss", "Runs.NumberOfLumiSections");
		map.put("run.totlumi", "Runs.TotalLuminosity");
		map.put("run.store", "Runs.StoreNumber");
		map.put("run.starttime", "Runs.StartOfRun");
		map.put("run.endtime", "Runs.EndOfRun");
		map.put("run.createdate", "Runs.CreationDate");
		map.put("run.moddate", "Runs.LastModificationDate");
//		map.put("run.createby", "Runs.CreatedBy");
//		map.put("run.modby", "Runs.LastModifiedBy");
		
		map.put("lumi.startevnum", "LumiSection.StartEventNumber");
		map.put("lumi.id", "LumiSection.ID");
		map.put("lumi.endevnum", "LumiSection.EndEventNumber");
		map.put("lumi.number", "LumiSection.LumiSectionNumber");
		map.put("lumi.starttime", "LumiSection.LumiStartTime");
		map.put("lumi.endtime", "LumiSection.LumiEndTime");
		map.put("lumi.createdate", "LumiSection.CreationDate");
		map.put("lumi.moddate", "LumiSection.LastModificationDate");
//		map.put("lumi.createby", "LumiSection.CreatedBy");
//		map.put("lumi.modby", "LumiSection.LastModifiedBy");
		
		map.put("branch.name", "Branch.Name");
		map.put("branch.id", "Branch.ID");
		map.put("branch.createdate", "Branch.CreationDate");
		map.put("branch.moddate", "Branch.LastModificationDate");
//		map.put("branch.createby", "Branch.CreatedBy");
//		map.put("branch.modby", "Branch.LastModifiedBy");
		
		map.put("ads.name", "AnalysisDataset.Name");
		map.put("ads.id", "AnalysisDataset.ID");
		map.put("ads.version", "AnalysisDataset.Version");
		map.put("ads.dataset", "AnalysisDataset.Path");
		map.put("ads.desc", "AnalysisDataset.Description");
		map.put("ads.procds", "AnalysisDataset.ProcessedDS");
		map.put("ads.createdate", "AnalysisDataset.CreationDate");
		map.put("ads.moddate", "AnalysisDataset.LastModificationDate");
//		map.put("ads.createby", "AnalysisDataset.CreatedBy");
//		map.put("ads.modby", "AnalysisDataset.LastModifiedBy");
	
		map.put("phygrp.name", "PhysicsGroup.PhysicsGroupName");
		map.put("phygrp.id", "PhysicsGroup.ID");
		map.put("phygrp.createdate", "PhysicsGroup.CreationDate");
		map.put("phygrp.moddate", "PhysicsGroup.LastModificationDate");
		map.put("group.name", "PhysicsGroup.PhysicsGroupName");
		map.put("group.id", "PhysicsGroup.ID");
		map.put("group.createdate", "PhysicsGroup.CreationDate");
		map.put("group.moddate", "PhysicsGroup.LastModificationDate");
//		map.put("group.createby", "PhysicsGroup.CreatedBy");
//		map.put("group.modby", "PhysicsGroup.LastModifiedBy");

		map.put("algo.id", "AlgorithmConfig.ID");
		map.put("algo.version", "AppVersion.Version");
		map.put("algo.family", "AppFamily.FamilyName");
		map.put("algo.exe", "AppExecutable.ExecutableName");
		map.put("algo.createdate", "AlgorithmConfig.CreationDate");
		map.put("algo.moddate", "AlgorithmConfig.LastModificationDate");
//		map.put("algo.createby", "AlgorithmConfig.CreatedBy");
//		map.put("algo.modby", "AlgorithmConfig.LastModifiedBy");

		map.put("datatype.id", "PrimaryDSType.ID");
		map.put("datatype.type", "PrimaryDSType.Type");
		map.put("datatype.createdate", "PrimaryDSType.CreationDate");
		map.put("datatype.moddate", "PrimaryDSType.LastModificationDate");
//		map.put("datatype.createby", "PrimaryDSType.CreatedBy");
//		map.put("datatype.modby", "PrimaryDSType.LastModifiedBy");

		map.put("mcdesc.id", "MCDescription.ID");
		map.put("mcdesc.name", "MCDescription.MCChannelDescription");
		map.put("mcdesc.def", "MCDescription.MCProduction");
		map.put("mcdesc.parent", "MCDescription.MCDecayChain");
		map.put("mcdesc.createdate", "MCDescription.CreationDate");
		map.put("mcdesc.moddate", "MCDescription.LastModificationDate");
//		map.put("mcdesc.createby", "MCDescription.CreatedBy");
//		map.put("mcdesc.modby", "MCDescription.LastModifiedBy");

		map.put("trigdesc.id", "TriggerPathDescription.ID");
		map.put("trigdesc.name", "TriggerPathDescription.TriggerPathDescription");
		map.put("trigdesc.createdate", "TriggerPathDescription.CreationDate");
		map.put("trigdesc.moddate", "TriggerPathDescription.LastModificationDate");
//		map.put("trigdesc.createby", "TriggerPathDescription.CreatedBy");
//		map.put("trigdesc.modby", "TriggerPathDescription.LastModifiedBy");

		map.put("config.id", "QueryableParameterSet.ID");
		map.put("config.name", "QueryableParameterSet.Name");
		map.put("config.hash", "QueryableParameterSet.Hash");
		map.put("config.version", "QueryableParameterSet.Version");
		map.put("config.type", "QueryableParameterSet.Type");
		map.put("config.annotation", "QueryableParameterSet.Annotation");
		map.put("config.content", "QueryableParameterSet.Content");
		map.put("config.createdate", "QueryableParameterSet.CreationDate");
		map.put("config.moddate", "QueryableParameterSet.LastModificationDate");
//		map.put("config.createby", "QueryableParameterSet.CreatedBy");
//		map.put("config.modby", "QueryableParameterSet.LastModifiedBy");

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
