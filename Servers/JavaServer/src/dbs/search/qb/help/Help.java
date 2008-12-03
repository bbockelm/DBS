package dbs.search.qb.help;
import java.util.Hashtable;
import java.util.List;
import java.util.Enumeration;
import java.io.Writer;
import org.apache.commons.lang.StringEscapeUtils;

public class Help {
	private Hashtable map = new Hashtable();
	private static Help helpObj;

	private void printHelp(Writer out, Keyword kw) throws Exception {
		out.write("<dbs-ql>\n" +
			"\t<key>\n" +
			 "\t<name>" + kw.entity + "</name>\n");
		for(String attr: kw.attrs) out.write("\t<attr>" + attr + "</attr>\n");
		out.write("\t<example>\n");
		for(Example example: kw.examples) {
			out.write("\t\t<desc>" + StringEscapeUtils.escapeXml(example.desc) + "</desc>\n");
			out.write("\t\t<query>" + StringEscapeUtils.escapeXml(example.query) + "</query>\n");
		}
		out.write("\t</example>\n" +
			 "\t</key>\n" +
			 "</dbs-ql>\n");
	}
	
	private void getAllHelp(Writer out) throws Exception {
		Enumeration e = map.keys();
		while (e.hasMoreElements()) printHelp(out, (Keyword)map.get(e.nextElement()));
	}
	public void getHelp(Writer out, String entityIn) throws Exception {
		try {
			if(entityIn == null) {
				getAllHelp(out);
				return;
			}
			if(entityIn.length() == 0) {
				getAllHelp(out);
				return;
			}
			
			entityIn = entityIn.toLowerCase();
			if(!map.containsKey(entityIn)) {
				out.write("<dbs-ql>\n\t<error>\nThis Keyword " + entityIn + " is NOT implemented. No help available. \n\t</error>\n</dbs-ql>\n");
				return;
			} 
			Keyword kw = (Keyword)map.get(entityIn);
			printHelp(out, kw);
		} finally { 
			out.flush();
		}
		
	}

	public static synchronized Help getInstance() {
		if (Help.helpObj == null) Help.helpObj =  new Help();
		return Help.helpObj;
	}

	private Help() {
		Example e;
		Keyword kw;
		String attr;
		


		kw = new Keyword();
		kw.entity = "primds";
		kw.attrs.add("name");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find all primary datasets";
		e.query = "find primds";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all primary datasets created after 2007-04-20";
		e.query = "find primds where primds.createdate > 2007-04-20";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all primary datasets created before 2007-04-20";
		e.query = "find primds where primds.createdate < 2007-04-20";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find attributes like creation date, modification date, created by, modified by of a primary datasets";
		e.query = "find primds.createdate, primds.moddate, primds.createby, primds.modby where primds = CSA07Muon";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "procds";
		kw.attrs.add("name");
		kw.attrs.add("parent");
		kw.attrs.add("release");
		kw.attrs.add("tier");
		kw.attrs.add("status");
		kw.attrs.add("era");
		kw.attrs.add("tag");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find all processed datasets";
		e.query = "find procds";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find attributes like release, era, tag, status, creation date, modification date, created by, modified by of a processed datasets";
		e.query = "find procds.release, procds.era, procds.tag, procds.status, procds.createdate, procds.moddate, procds.createby, procds.modby where procds = Skim-zToTauTau_MuTau-Tier0-A1-Chowder";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "dataset";
		kw.attrs.add("parent");
		kw.attrs.add("release");
		kw.attrs.add("tier");
		kw.attrs.add("status");
		kw.attrs.add("era");
		kw.attrs.add("tag");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find all datasets paths";
		e.query = "find dataset";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all dataset with name like Online";
		e.query = "find dataset where dataset like *Online*";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all dataset with name like some pattern";
		e.query = "find dataset where dataset like /CSA0*/CMSSW_*/*";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find parent of a dataset";
		e.query = "find dataset.parent where dataset = /CSA07Muon/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/USER";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find attributes like release, era, tag, status, creation date, modification date, created by, modified by of a particular dataset";
		e.query = "find dataset.release, dataset.era, dataset.tag, dataset.status, dataset.createdate, dataset.moddate, dataset.createby, dataset.modby where dataset = /CSA07Muon/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/USER";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "block";
		kw.attrs.add("name");
		kw.attrs.add("size");
		kw.attrs.add("dataset");
		kw.attrs.add("numfiles");
		kw.attrs.add("numevents");
		kw.attrs.add("status");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find blocks in a dataset";
		e.query = "find block where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find blocks and its attributes like  its size, number of files, number of events, its status, creation date, modification date, created by, modified by in a dataset";
		e.query = "find block, block.size, block.numfiles, block.numevents, block.status, block.createdate, block.moddate, block.createby, block.modby where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all the  attributes like dataset, size,  creation date, modification date, created by, modified by in a dataset";
		e.query = "find block.dataset, block.size,  block.createdate, block.moddate, block.createby, block.modby where block = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO#01123fd3-5486-41fe-8b90-7dbb8fbe69b8";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to count all the blocks in a dataset";
		e.query = "find block.count where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e.query = "find count(block) where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to sum up the size of all the blocks in a particular dataset";
		e.query = "find sum(block.size)  where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "file";
		kw.attrs.add("name");
		kw.attrs.add("size");
		kw.attrs.add("status");
		kw.attrs.add("release");
		kw.attrs.add("parent");
		kw.attrs.add("child");
		kw.attrs.add("tier");
		kw.attrs.add("numevents");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");


		e = new Example();
		e.desc = "I want to find files in a dataset";
		e.query = "find file where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to count all files in a dataset";
		e.query = "find file.count where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find files which are invalid  in a dataset";
		e.query = "find file where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO and file.status= INVALID";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find file and its release, size, type, tier and status in a dataset";
		e.query = "find file, file.release, file.size, file.type, file.tier, file.status where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find a parent of a file";
		e.query = "find file.parent where file = /store/user/ndefilip/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/NicolaDeFilippis/Skim-zToTauTau_MuTau-Tier0-A1-Chowder_85f0790dd16f9aff6ccde9b27395c4d2/zToTauTau_MuTau_372.root";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find file whose parent I know";
		e.query = "find file where file.parent = /store/user/ndefilip/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/NicolaDeFilippis/Skim-zToTauTau_MuTau-Tier0-A1-Chowder_85f0790dd16f9aff6ccde9b27395c4d2/zToTauTau_MuTau_372.root";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find a child of a file";
		e.query = "find file.child where file = /store/user/ndefilip/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/NicolaDeFilippis/Skim-zToTauTau_MuTau-Tier0-A1-Chowder_85f0790dd16f9aff6ccde9b27395c4d2/zToTauTau_MuTau_372.root";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find a file whose child I know";
		e.query = "find file where file.child = /store/user/ndefilip/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/NicolaDeFilippis/Skim-zToTauTau_MuTau-Tier0-A1-Chowder_85f0790dd16f9aff6ccde9b27395c4d2/zToTauTau_MuTau_372.root";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files created between 2007-04-20 and 2007-04-21";
		e.query = "find file where  file.createdate = 2007-04-20";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files created exactly at 2007-04-20 11:27:21 CDT or modified in 2008";
		e.query = "find file where  file.createdate = 2007-04-20 11:27:21 CDT  or file.moddate > 2008";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to sum up the size of all the files in a particular dataset";
		e.query = "find sum(file.size) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "lumi";
		kw.attrs.add("evnum");
		kw.attrs.add("startevnum");
		kw.attrs.add("endevnum");
		kw.attrs.add("number");
		kw.attrs.add("starttime");
		kw.attrs.add("endtime");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find lumi section of a file";
		e.query = "find lumi where file = NEW_TEST0005";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to count lumi section of a file";
		e.query = "find lumi.count where file = NEW_TEST0005";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find lumi section and its attributes like start time, end time, start event number and end event number in a file";
		e.query = "find lumi, lumi.starttime, lumi.endtime, lumi.startevnum, lumi.endevnum where file = NEW_TEST0005";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find lumi section in a set of files";
		e.query = "find lumi where file in ( NEW_TEST0005, NEW_TEST0004, NEW_TEST0003 )";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find lumi section either in a set of files or in a dataset";
		e.query = "find lumi where file in ( NEW_TEST0005, NEW_TEST0004, NEW_TEST0003 ) or dataset = /test_primary_001/TestProcessedDS002/GEN-SIM";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find lumi section either in a set of files or in a dataset or in a set of runs";
		e.query = "find lumi where file in ( NEW_TEST0005, NEW_TEST0004, NEW_TEST0003 ) or dataset = /test_primary_001/TestProcessedDS002/GEN-SIM or run in (1,2,3)";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find lumi section for a particular event number";
		e.query = "find lumi where lumi.evnum  = 150";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find file for a particular event number";
		e.query = "find file where lumi.evnum  = 150";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "run";
		kw.attrs.add("number");
		kw.attrs.add("numevents");
		kw.attrs.add("numlss");
		kw.attrs.add("starttime");
		kw.attrs.add("endtime");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find all runs in a particular primary dataset";
		e.query = "find run where primds = test_primary_001";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find attributes of like number of events, number of lumi sections, start time and end time of a particular run";
		e.query = "find run.numevents, run.numlss, run.starttime, run.endtime where run = 23";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to count all the runs in a particular dataset";
		e.query = "find run.count where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM";
		kw.examples.add(e);
		e.query = "find count(run) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all runs created exactly between 2008-05-01 12:05 2008-05-01 12:06";
		e.query = "find run where run.createdate = 2008-05-01 12:05";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all runs created exactly between 2008-05-01 12 2008-05-01 1";
		e.query = "find run where run.createdate = 2008-05-01 12";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all runs created exactly between 2008-05-01 2008-05-02";
		e.query = "find run where run.createdate = 2008-05-01";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all runs created exactly between 2008-05 2008-06";
		e.query = "find run where run.createdate = 2008-05";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all runs created exactly between 2008 2009";
		e.query = "find run where run.createdate = 2008";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to sum up the events of all the runs in a particular dataset such that the file have size greater than some value";
		e.query = "find sum(run.numevents) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.size > 20000";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "site";
		kw.attrs.add("name");
		e = new Example();
		e.desc = "I want to find all storage elements";
		e.query = "find site";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all storage elements in a dataset";
		e.query = "find site where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files at a storage element that start with castorsrm.cr";
		e.query = "find file where site like castorsrm.cr*";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all datasets at a storage element that start with castorsrm.cr";
		e.query = "find dataset where site like castorsrm.cr*";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files at a particular storage element";
		e.query = "find file where site = T1_FR_CCIN2P3";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files at a set of storage elements";
		e.query = "find file where site in ( srm-disk.pic.es, castorsrm.cr, T1_FR_CCIN2P3, T1_TW_ASGC )";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all blocks and all datasets at a particular storage element";
		e.query = "find block , dataset where site = srm-disk.pic.es";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "phygrp";
		kw.attrs.add("name");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");
		e = new Example();
		e.desc = "I want to find all physics groups";
		e.query = "find phygrp";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find physics groups of a particular dataset";
		e.query = "find phygrp where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all datasets with a particular physics group";
		e.query = "find dataset where phygrp = BPositive";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all datasets with a particular physics groups pattern";
		e.query = "find dataset where phygrp like *BPositive";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find file and run in two particular physics group";
		e.query = "find file,run where phygrp in (BPositive, Any)";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find attributes like id, creation date, modification date, created by, modified by of a physics group";
		e.query = "find phygrp, phygrp.id, phygrp.createdate , phygrp.moddate, phygrp.createby, phygrp.modby";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to count all the physics groups";
		e.query = "find phygrp.count";
		kw.examples.add(e);
		e.query = "find count(phygrp)";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "dq";
		e = new Example();
		e.desc = "I want to find all files with data quality as TIB_DCS=UNKNOWN, Tracker_Global=GOOD and TIB_Local=GOOD";
		e.query = "find file where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all runs with data quality as TIB_DCS=UNKNOWN, Tracker_Global=GOOD and TIB_Local=GOOD";
		e.query = "find run where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files and runs with data quality as TIB_DCS=UNKNOWN, Tracker_Global=GOOD and TIB_Local=GOOD";
		e.query = "find file, run where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files and runs with data quality as TIB_DCS=UNKNOWN, Tracker_Global=GOOD and TIB_Local=GOOD and in dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		e.query = "find file, run where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD and dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "pset";
		e = new Example();
		e.desc = "I want to find all dataset with these parameter set values associatorL25PixelTauIsolated.coneSize > 0 ,associatorL25SingleTau.coneSize > 0, associatorL25SingleTau.jets like a";
		e.query = "find dataset where pset = associatorL25PixelTauIsolated.coneSize>0&associatorL25SingleTau.coneSize>0&associatorL25SingleTau.jets<>a";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files, runs with these parameter set values associatorL25PixelTauIsolated.coneSize > 0";
		e.query = "find file, runs where pset = associatorL25PixelTauIsolated.coneSize>0";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "ads";
		kw.attrs.add("name");
		kw.attrs.add("version");
		kw.attrs.add("dataset");
		kw.attrs.add("type");
		kw.attrs.add("status");
		kw.attrs.add("def");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("count");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find all analysis datasets";
		e.query = "find ads";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all analysis datasets in a particular dataset (processed)";
		e.query = "find ads where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find analysis datasets attributes like its definition, type, status, created by , modified by, creation date, last modification date of a particular analysis dataset";
		e.query = "find ads.def, ads.type, ads.status, ads.createby, ads.modby, ads.createdate, ads.moddate where ads.name = MyAds";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files in a particular ads";
		e.query = "find file where ads.name = MyAds";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find files, run and lumi in a particular ads";
		e.query = "find file, run, lumi where ads.name = MyAds";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find files, run and lumi in a set of ads";
		e.query = "find file,run,lumi where ads.name in ( MyAds, Myades )";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "algo";
		kw.attrs.add("hash");
		kw.attrs.add("version");
		kw.attrs.add("exe");
		kw.attrs.add("family");
		kw.attrs.add("content");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find all algorithms and its attribute like family, version, executable, hash, modified by, created by, create date and modified date";
		e.query = "find algo.family, algo.version, algo.exe, algo.hash, almo.modby, algo.createby, algo.createdate, algo.moddate";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all  datasets that has a specified algo version or a specified algo family";
		e.query = "find  dataset where algo.version = CMSSW or algo.family = CMS*";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find algo configuration of a specified file ";
		e.query = "find algo.family, algo.version, algo.exe, algo.hash where file = MyLogocalFileName";
		kw.examples.add(e);
		
		map.put(kw.entity, kw);



		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "ilumi";
		e = new Example();
		e.desc = "I want to find integrated luminosity of a set of runs";
		e.query = "find ilumi where run in (1,2,3)";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find integrated luminosity of a set of runs on file by file basis";
		e.query = "find ilumi, file where run in (1,2,3)";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find integrated luminosity per file basis of a set of runs";
		e.query = "find ilumi, file where run in (1,2,3)";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find integrated luminosity per file basis of in a particular dataset";
		e.query = "find ilumi, file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find integrated luminosity per dataset whener run is greater than 2000";
		e.query = "find ilumi, dataset where run > 2000";
		kw.examples.add(e);
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------


		kw = new Keyword();
		kw.entity = "datatype";
		kw.attrs.add("type");
		kw.attrs.add("createdate");
		kw.attrs.add("moddate");
		kw.attrs.add("createby");
		kw.attrs.add("modby");
		kw.attrs.add("id");

		e = new Example();
		e.desc = "I want to find all datatypes";
		e.query = "find datatype";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all datatypes and its attribute like type, id,  modified by, created by, create date and modified date";
		e.query = "find datatype, datatype.id, datatype.createdate, datatype.moddate, datatype.createby, datatype.modby";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all datasets that has a specified datatype";
		e.query = "find  dataset where datatype = cosmic";
		kw.examples.add(e);
		e = new Example();
		e.desc = "I want to find all files that has a specified datatype or whose size is > 1000";
		e.query = "find file where datatype = cosmic or file.size > 1000";
		kw.examples.add(e);
		
		map.put(kw.entity, kw);


		//-------------------------------------------------------------------------



	}

	public static void main(String args[]) throws Exception{
		Help h = Help.getInstance();
		h.getHelp(new java.io.PrintWriter(System.out), args[0]);
		//h.getHelp(new java.io.PrintWriter(System.out), "");

	}
}

