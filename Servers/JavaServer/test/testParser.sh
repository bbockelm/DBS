BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
cd $BASE/../../LibValut
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar:$PWD/antlrworks-1.2.2.jar:$PWD/commons-collections-3.2.jar:$PWD/jung-1.7.6.jar:$PWD/commons-lang-2.4.jar:$PWD/msclient.jar:$PWD/json.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/

CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7 api=executeQuery"
#CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_2_0_6 api=executeQuery"
#CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7 api=countQuery"
#$CMD
executeQuery () {
	echo "Executing executeQuery API..."
	$CMD query="find file where file.release=CMSSW_1_7_1"
	$CMD query="find file,file.status,file.type where procds.release=CMSSW_1_7_1"
	$CMD query="find file.id,lumi.id   where procds.release=CMSSW_1_7_1"
	$CMD query="find file.name, file.createdate where run.number=35672 and run.moddate>2"
	$CMD query="find file,file.size,dataset where procds.name=Online and file.size>546294916"
	$CMD query="find  file,    lumi    where    dataset  =   /CalPrivateGlobal-default/Online/RAW"
	$CMD query="find lumi where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"
	$CMD query="find count(file) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM or run > 0"
	$CMD query="find file.release,file where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"
	$CMD query="find count(run) where run.number > 1 and run.number < 888800"
	$CMD query="find lumi.id,lumi.id,file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM" 
	$CMD query="find run where run.number>1 and run.number<100"
	$CMD query="find run.count where run.number > 1 and run.number < 100"
	$CMD query="find run.count where run > 1 and run < 100"
	$CMD query="find run.count where dataset=/CalPrivateGlobal-default/Online/RAW"
	$CMD query="find file,run,lumi  where dataset=/GlobalMar08-Express/Online/RAW and file.type = STREAMER"
	$CMD query="find file, run,lumi where dataset like %Online%"
	$CMD query="find run,lumi where dataset like %Online%"
	$CMD query="find run,lumi,file where dataset  like  %Online%"
	$CMD query="find file.release   where  dataset=/CalPrivateGlobal-default/Online/RAW"
	$CMD query="find primds where primds.name like %test%"
	$CMD query="find dataset"
	$CMD query="find procds, procds.createdate"
	$CMD query="find primds"
	$CMD query="find procds.createby, run.modby" 
	$CMD query="find file, procds.createby where  file.modby like %sekhri% and dataset = /CalPrivateGlobal-default/Online/RAW" 
	$CMD query="find file, procds.createby, file.modby, procds.createdate" 
	$CMD query="find file where file.modby like %sekhri%" 
	$CMD query="find procds.parent,procds.name,file,file.parent  where procds.parent like %"
	$CMD query="find file where file.createby like %sekhri% or file.createby = abc"
	$CMD query="find file.parent,file where file.name = NEW_TEST0005" 
	$CMD query="find lumi.id,file.id where dataset in (/CalPrivateGlobal-default/Online/RAW,/GlobalMar08-Express/Online/RAW)" 
	$CMD query="find file,file.parent,run,dataset where file.parent like %NEW% or file.parent like %" 
	$CMD query="find file.id,lumi.id,dataset   where procds.release=CMSSW_1_7_1"
	$CMD query="find file.createby,file.modby,file.modby where file.createby like %sekhri% or file.modby like %sekhri% and file.createby like %sek%"
	$CMD query="find procds, procds.createdate,run,lumi.moddate where run.number in (1,2,3)" 
	$CMD query="find run, run.numevents, run.numlss, run.starttime, run.endtime"
	$CMD query="find dataset  where site = T1_FR_CCIN2P3"
	$CMD query="find file where run > 1 and run < 1000000"
	$CMD query="find procds.status  where dataset = /CalPrivateGlobal-default/Online/RAW"
	$CMD query="find procds, procds.era, procds.tag"
	$CMD query="find procds, procds.era, procds.tag where dataset = /CalPrivateGlobal-default/Online/RAW"
	$CMD query="find site" 
	$CMD query="find run, run.createdate where run.createdate > 2008-05-01 12:05:12 order by run, run.createdate"
	$CMD query="find file.parent,file.parent, file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM" 
	$CMD query="find procds.parent,procds.name  where procds.name like %Test% or procds.parent like %"
	$CMD query="find file.release   where  dataset=/CalPrivateGlobal-default/Online/RAW  or dataset=/abc/Online/RAW"
	$CMD query="find file where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD" 
	$CMD query="find dataset, procds.createdate where dq = Tracker_Global=GOOD&TIB_Local=GOOD" 
	$CMD query="find file,lumi,dataset where dq = Tracker_Global=GOOD&TIB_Local=GOOD&TIB_DCS=UNKNOWN or file.size > 1000" 
	$CMD query="find file, file.tier" 
	$CMD query="find file where file.tier = GEN" 
	$CMD query="find run where release = test_Ver1_eaf67699-b539-487d-8713-07df617379db"
	$CMD query="find block , dataset where site = srm-disk.pic.es" 
	$CMD query="find procds,file where procds.tier = GEN" 
	$CMD query="find procds,file,file.tier where dataset like *" 
	$CMD query="find file where site in ( srm-disk.pic.es, castorsrm.cr)" 
	$CMD query="find procds,file where procds.tier in (GEN,SIM,RAW)" 
	$CMD query="find ads,file"
	$CMD query="find release where file like *"
	$CMD query="find release where dataset like *"
	$CMD query="find release where dataset like * or file like *"
	$CMD query="find release where procds like * or file like *"
	$CMD query="find file.release where procds like * or file like *"
	$CMD query="find file,run,lumi where ads.name = MyAds" 
	$CMD query="find lumi,ads"
	$CMD query="find ads, ads.type, ads.status, ads.createby"
	$CMD query="find file,lumi where ads.name = /TestPrimary_001_20080501_10h06m06s/TestProcessed_20080501_10h06m06s/GEN-SIM/TestAnalysisDSDef_005_20080501_10h06m06s"
	$CMD query="find ads, ads.dataset"
	$CMD query="find file,run,lumi where ads.name in ( MyAds, Myades )" 
	$CMD query="find ads,file where dataset = /TestPrimary_001_20080501_10h06m06s/TestProcessed_20080501_10h06m06s/GEN-SIM"
	$CMD query="find ads, ads.def, file"
	$CMD query="find dataset.parent where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.createdate > 0"
	$CMD query="find lumi.startevnum, lumi.endevnum, lumi.id where file.createdate > 0"
	$CMD query="find file where lumi.evnum  = 150"
	$CMD query="find lumi, dataset where lumi.evnum  = 150"
	$CMD query="find file, file.createdate where  file.createdate = 2007-04-20 "
	$CMD query="find file, file.createdate where  file.createdate = 2007-04-20 11:27:21 CDT  or file.moddate > 2008"
	$CMD query="find file, file.createdate where  file.createdate = 2007-04-20 11:27:21"
	$CMD query="find run, run.createdate where run.createdate = 2008-05-01 12:05:12 order by run"
	$CMD query="find run, run.createdate where run.createdate = 2008-05-01 12:05"
	$CMD query="find run, run.createdate where run.createdate = 2008-05-01 12"
	$CMD query="find run, run.createdate where run.createdate = 2008-05-01"
	$CMD query="find run, run.createdate where run.createdate = 2008"
	$CMD query="find run, run.createdate where run.createdate < 2008"
	$CMD query="find run, run.createdate where run.createdate > 2008"
	$CMD query="find run,file  order by file"
	$CMD query="find run,file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM  order by file.createdate"
	$CMD query="find ilumi where run > 0"
	$CMD query="find ilumi where run > 0"
	$CMD query="find ilumi, file.name"
	$CMD query="find run where primds = test_primary_001"
	$CMD query="find count(file), dataset where dataset like *"
	$CMD query="find run, ilumi, file"
	$CMD query="find sum(file.size), dataset where file.size > 0"
	$CMD query="find file, dataset where release = test_Ver1_eaf67699-b539-487d-8713-07df617379db"
	$CMD query="find file where release = test_Ver1_eaf67699-b539-487d-8713-07df617379db"
	$CMD query="find dataset where release = test_Ver1_eaf67699-b539-487d-8713-07df617379db"
	$CMD query="find run, ilumi, file where run in (1,2,3)"
	$CMD query="find ilumi, file where run in (1,2,3)"
	$CMD query="find ilumi, file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM"
	$CMD query="find ilumi, file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM order by file"
	$CMD query="find ilumi, dataset where run > 0"
	$CMD query="find ilumi"
	$CMD query="find block"
	$CMD query="find block, block.size,  block.createdate, block.moddate, block.createby, block.modby where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
	$CMD query="find block.size,  block.createdate, block.moddate, block.createby, block.modby where block = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO#01123fd3-5486-41fe-8b90-7dbb8fbe69b8"
	$CMD query="find block.count where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO" 
	$CMD query="find count(block) where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO" 
	$CMD query="find run.count where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM" 
	$CMD query="find lumi, lumi.starttime, lumi.endtime"
	$CMD query="find dataset.id, dataset.status, dataset.era, dataset.tag, dataset.createdate, dataset.moddate, dataset.createby, dataset.modby where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM"
	$CMD query="find dataset.release, dataset.era, dataset.tag, dataset.status, dataset.createdate, dataset.moddate, dataset.createby, dataset.modby where dataset = /CSA07Muon/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/USER"
	$CMD query="find file.child, file where file = NEW_TEST0002" 
	$CMD query="find file where file.child = NEW_TEST0005" 
	$CMD query="find sum(file.size) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.size > 0"
	$CMD query="find sum(run.numevents) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.size > 0"
	$CMD query="find sum(block.size)  where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
	$CMD query="find phygrp" 
	$CMD query="find phygrp where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM " 
	$CMD query="find dataset where phygrp like *BPositive " 
	$CMD query="find dataset where phygrp in (BPositive,Any )" 
	$CMD query="find file where phygrp in (BPositive,Any )" 
	$CMD query="find file,run where phygrp in (BPositive,Any )" 
	$CMD query="find phygrp.id, phygrp.name" 
	$CMD query="find phygrp, phygrp.id, phygrp.createdate , phygrp.moddate, phygrp.createby, phygrp.modby" 
	$CMD query="find dataset where pset = associatorL25PixelTauIsolated.coneSize>0&associatorL25SingleTau.coneSize>0&associatorL25SingleTau.jets<>a"
	$CMD query="find file, lumi where pset = associatorL25PixelTauIsolated.coneSize>0"
	$CMD query="find dataset  where site = T1_FR_CCIN2P3"
	$CMD query="find dataset  where site in ( T1_FR_CCIN2P3, T1_TW_ASGC)"
	$CMD query="find dataset  where site like *A*"
	$CMD query="find dataset  where site like castorsrm.cr.cnaf*"
	$CMD query="find dataset  where site = castorsrm.cr.cnaf.infn.it"
	$CMD query="find dataset  where site = test_seM_ee563854-0ed2-4010-82ba-e94e7868cbff"
	$CMD query="find dataset, site"
	$CMD query="find site"
	$CMD query="find dataset where run between 51437 and 51450 or run between 0 and 30000" 
	$CMD query="find dataset where dataset.id between 1 and 2 or dataset like *on*" 
	$CMD query="find algo.id, algo.family, algo.exe,algo.hash, algo.content where dataset like *on*" 
	$CMD query="find dataset where  algo.exe  = *CMS* or algo.family=*C*" 
	$CMD query="find file where  algo.exe  = *CMS* or algo.family=*C*" 
	$CMD query="find algo, algo.createby, algo.modby, algo.moddate  where dataset like *on*" 
	$CMD query="find dataset where datatype = cosmic"
	$CMD query="find file where datatype = cosmic"
	$CMD query="find datatype where dataset = *on*"
	$CMD query="find datatype, datatype.id, datatype.createdate, datatype.moddate, datatype.createby, datatype.modby where dataset = *on*"
	$CMD query="find dataset where site != caf.cern.ch "
	$CMD query="find dataset where site = caf.cern.ch "
	$CMD query="find dataset where site = T1_CH_CERN "
	$CMD query="find dataset where site != T1_CH_CERN "
	$CMD query="find dataset where site in (T1_CH_CERN , T0_CH_CERN) "



}
	#$CMD query="find file,file.parent where dq = Tracker_Global=GOOD&TIB_Local=GOOD" 
	#$CMD query="find lumi.id,file.id where dq = Tracker_Global=GOOD&TIB_Local=BAD&TIB_DCS=UNKNOWN and dataset = /CalPrivateGlobal-default/Online/RAW" 
#$CMD query="find file where dq = Tracker_Global=GOOD&TIB_Local=GOOD" 
	#$CMD query="find block.parent where dataset = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW"
	#$CMD query="find  dataset, dataset.parent"
	#$CMD query="find  file, file.parent"
	#$CMD query="find  file, file.child"
	#$CMD query="find file.child where block = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW#7f0e573b-9200-41a7-a8af-76268ab3f970"
	#$CMD query="find dataset.xsection where dataset like *"
	#$CMD query="find dataset.era"
	#$CMD query="find  block, block.child"
	#$CMD query="find dataset where dataset like * and ((run = 1) or (run between 1 and 2)) and run=222"
	#$CMD query="find dataset where dataset like * and (run = 1)  and run=222"
	#$CMD query="find  block, block.parent"
	#$CMD query="find  procds, procds.parent"
	#$CMD query="find  procds, procds.child"
	#$CMD query="find  block, block.child, dataset, dataset.parent"
	#$CMD query="find  file, file.child where file = test_file_name_5_21beab91-fa63-4551-970c-7c582f04f4d6"
	#$CMD query="find  dataset.parent, dataset where block = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW"
	#$CMD query="find  dataset.parent where dataset = /test_Primary_21beab91-fa63-4551-970c-7c582f04f4d6/test_processed_M_21beab91-fa63-4551-970c-7c582f04f4d6/GEN-SIM or file = *"
	#$CMD query="find dataset,  dataset.parent"
	#$CMD query="find dataset,  dataset.child where dataset = *"
	#$CMD query="find file.parent where block = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW#7f0e573b-9200-41a7-a8af-76268ab3f970"
	
	#$CMD query="find config, config.name, config.type, config.version, config.content, config.hash, config.id, config.createdate, config.createby, config.moddate, config.modby"
	#$CMD query="find file where tier = GEN-SIM-DIGI-RECO"
	#$CMD query="find file.tier where tier = GEN-SIM-DIGI-RECO"
	#$CMD query="find file.tier where dataset = /DY_mumu_10/CMSSW_1_3_1-Spring07-1349/GEN-SIM-DIGI-RECO"
	#$CMD query="find dataset where dataset.parent = /DY_mumu_10/CMSSW_1_2_3-Spring07-EWK-1175695062/GEN-SIM"
	#$CMD query="find dataset.tag, dataset.createdate where dataset = /DY_mumu_10/CMSSW_1_2_3-Spring07-EWK-1175695062/GEN-SIM"
	#$CMD query="find procds.tag, procds.createdate where dataset = /DY_mumu_10/CMSSW_1_2_3-Spring07-EWK-1175695062/GEN-SIM"
	
	#$CMD query="find datatype.type, datatype.id, datatype.createdate, datatype.moddate where dataset = *on*"
	#$CMD query="find datatype, datatype.id, datatype.createdate, datatype.moddate, datatype.createby, datatype.modby"
	#$CMD query="find sum(file.size), count(file.a), dataseta where dataset = ** "
	#$CMD query="find dataset where     (    dataset = *abc*   and file.size > 2 ) or   (file.size =1 and    dataset like xyz*  )"
	#$CMD query="find datatype, dataset, run.number, run.numevents, run.numlss, run.totlumi, run.store, run.starttime, run.endtime, run.createby, run.createdate, run.modby, run.moddate, count(file), sum(file.size) where dataset = /EndcapsMuon/CRUZET3_CRUZET3_V2P_v3/RECO "
	#$CMD query="find dataset where site = caf.cern.ch "
	#$CMD query="find dataset,site,sum(block.numevents),sum(block.size) where dataset=/TTbar-madgraph/geenen-TTBarWinter09IDEAL_V11_FastSim_v1_IIIb_PAT_DBSTEST-23e0a7a80519399331d983d3a29007f8/USER"
	#$CMD query="find datatype, dataset, run.number, run.numevents, run.numlss, run.totlumi, run.store, run.starttime, run.endtime, run.createby,run.createdate, run.modby, run.moddate, count(file), sum(file.size) where dataset = /EndcapsMuon/CRUZET3_CRUZET3_V2P_v3/RECO"
	#$CMD query="find count(dataset)  where file.size > 10"
	#$CMD query="find count(file.id)  where file.size > 10"
	#$CMD query="find count(file)  where file.size > 10"
	#$CMD query="find count(file.type)  where file.size > 10"
	#$CMD query="find count(file.status) where file.size > 0"
	#$CMD query="find dataset"
	#$CMD query="find sum (file.size  ), dataset where dataset = *"
	#$CMD query="find sum(block.size  ), block where dataset = *"
	#$CMD query="find sum(block.numfiles ), block where dataset = *"
	$CMD query="find count(file) where block = /CosmicMC_BON_10GeV_AllCMS/Winter09_COSMMC_22X_V6_v1/GEN-SIM-RAW#ff4fc7c8-41d2-4bad-9b55-2835a71625d8"
	#$CMD query="find min(file.status)"
	#$CMD query="find avg(file.size) where dataset = *RAW"
	#$CMD query="find max(file.size), min(file.size), avg(file.size), run where dataset = *RAW"
	#$CMD query="find max(file.size), min(file.size), avg(block.size), run where dataset = *RAW"
	#$CMD query="find avg(run.starttime)"
	#$CMD query="find max(run.starttime)"
	#$CMD query="find dataset where dataset = *"
	#$CMD query="select primds, primds.createby where primds.createby = *Sekhri*"
	#$CMD query="find primds, primds.createby where primds.createby = Sekhri"
	#$CMD query="find file.type where file.createby = null"
	#$CMD query="find site where site = null or file.size > 1000"
	#$CMD query="find dataset, file, lumi where ( run<=66533 OR (run>=66893 AND run<=67085) OR (run>=67264 AND run<=67432) OR (run>=67676 AND run<=67777) OR (run>=69536 AND run<=69671) OR (run>=70088 AND run<=99999)) and dataset=/Cosmics/Commissioning08_CRAFT_ALL_V11_227_Tosca090216_ReReco_FromTrackerPointing_v2/RAW-RECO"
	#$CMD query="find run,  count(file.size) where dataset = *RAW"
	#$CMD query="find dataset, file, lumi where ( run<=66533 OR (( run>=66893 AND run<=67085 ) or (run>=66896 AND run<=67086) ) ) and dataset=/Cosmics/*"
	#$CMD query="find dataset, file, lumi where (((run<=66533 OR (run>=66893 AND (run<=67085)))  or (run>=66896 AND run<=67086))   and dataset=/Cosmics/*)"
	#$CMD query="find site, block.id, block where dataset = /SiStripCommissioning08-edm/Online/RAW"
	#$CMD query="find dataset, run.number, count(file), sum(file.size) where dataset = /HcalHPDNoise/Commissioning08-CRUZET4_v1/RAW order by run.number, dataset desc" begin=0 end=5
	#$CMD query="find dataset, run.number, count(file), sum(file.size) where dataset = /HcalHPDNoise/Commissioning08-CRUZET4_v1/RAW" begin=0 end=5
	#$CMD query="find branch.name, branch.createby, branch.createdate, branch.modby, branch.moddate where file=/store/temp/backfill/data/AllRunsTest/Cosmics/RECO/OneOfEachLumi_v1/000/064/818/F0071E05-D6D9-DD11-9CD6-000423D174FE.root"
	#$CMD query="find dataset where site in (T1_CH_CERN , T0_CH_CERN) "
	#$CMD query="find branch.name, branch.createby, branch.createdate, branch.modby, branch.moddate where file=/store/temp/backfill/data/AllRunsTest/Cosmics/RECO/OneOfEachLumi_v1/000/064/818/F0071E05-D6D9-DD11-9CD6-000423D174FE.root"

