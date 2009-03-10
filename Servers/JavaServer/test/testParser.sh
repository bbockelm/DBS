BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
cd $BASE/../../LibValut
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar:$PWD/antlrworks-1.2.2.jar:$PWD/commons-collections-3.2.jar:$PWD/jung-1.7.6.jar:$PWD/commons-lang-2.4.jar:$PWD/msclient.jar
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
#$CMD query="find file, dataset where release = test_Ver1_eaf67699-b539-487d-8713-07df617379db"
#$CMD query="find run where release = test_Ver1_eaf67699-b539-487d-8713-07df617379db"
#$CMD query="find  dataset where procds.release = test_Ver1_eaf67699-b539-487d-8713-07df617379db"
#$CMD query="find file where release = CMSSW_1_6_7"
#$CMD query="find count(file) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM or run > 0"
#$CMD query="find count(run) where run.number > 1 and run.number < 888800"
#$CMD query="find count(block) where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO" 
#$CMD query="find file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.size > 0"
#$CMD query="find dataset where site = heplnx204.pp.rl.ac.uk"
	#$CMD query="find count(phygrp)" 
	#$CMD query="find count(group)" 
	#$CMD query="find dataset" 
	#$CMD query="find run, run.createdate where run.createdate = 2008-05-01 12"
	#$CMD query="find dataset where site like %srm.cern*" 
	#$CMD query="find file where dataset=/test_primary_001/TestProcessedDS111/SIM-GEN" 
	#$CMD query="find sum(file.size), dataset where file.size > 0"
	#$CMD query="find sum(file.size) where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.size > 0"
#executeQuery
	#$CMD query="find file.parent where file.name = NEW_TEST0005" 

	#$CMD query="find run,file  order by file, run"
	#$CMD query="find  sum(file.numevents) where dataset = /DiPhotons_Ene3000/CMSSW_1_4_6-CSA07-2916/GEN-SIM"
	#$CMD query="find file,file.createby,file.createdate, sum(file.size) where dataset=/chaintest/CMSSW_2_0_5-Test-stuartw-1215537045-unmerged/GEN"
	#$CMD query="find  dataset where dataset.era  = test_Era_a13dd785-452b-420b-9905-dc3407cebe56"
	#$CMD query="find count ( file ) , dataset where file.size > 0"
	#$CMD query="find count ( file )  where dataset = /chaintest_standalone/CMSSW_2_0_5-Test-stuartw-1215618413-unmerged/GEN-SIM-DIGI-RECO"
	#$CMD query="find sum(file.numevents), count(file), dataset  where file.size > 0"
	#$CMD query="find  count(file), sum(file.numevents), dataset"
	#$CMD query="find sum(file.size), dataset where dataset like *"
	#$CMD query="find  count(run), sum(file.size), run, count(file), dataset where dataset like *"
	#$CMD query="find   sum(file.numevents), dataset where dataset like *"
	#$CMD query="find   dataset, file.count where site  like abc.in"
	#$CMD query="find sum(file.numevents), file.count, run.count, dataset  where file.size > 0"
	
	#$CMD query="find  sum(file.numevents),  dataset where dataset like *"
	#$CMD query="find  sum(file.numevents), file.count where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
	#$CMD query="find release where release like *"
	#$CMD query="find sum(file.size), count(file), run where dataset = /test_Primary_ee563854-0ed2-4010-82ba-e94e7868cbff/test_processed_M_ee563854-0ed2-4010-82ba-e94e7868cbff/GEN-SIM and site = test_seM_ee563854-0ed2-4010-82ba-e94e7868cbff"
	#$CMD query="find site, dataset where dataset = /test_Primary_ee563854-0ed2-4010-82ba-e94e7868cbff/test_processed_M_ee563854-0ed2-4010-82ba-e94e7868cbff/GEN-SIM"
	
#	CMD query="find file where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD" 
	#$CMD query="find file  where  pset = associatorL25PixelTauIsolated.coneSize 0 "
	#$CMD query="find dataset.parent where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.createdate > 0"
	#$CMD query="find dataset where pset = associatorL25PixelTauIsolated.coneSize>0&associatorL25SingleTau.coneSize>0&associatorL25SingleTau.jets<>a"
	#$CMD query="find dataset  where site = T1_FR_CCIN2P3"
	#$CMD query="find dataset  where site like T1_FR_*"
	#$CMD query="find dataset  where site in (T1_FR_CCIN2P3, dcache-se-cms.desy.de)"

	#$CMD query="find file, lumi where pset = associatorL25PixelTauIsolated.coneSize>0"
	#$CMD query="find dataset.parent where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM and file.createdate > 0"
	#$CMD query="find dataset, dataset.modby where dataset = *online*" 
	#$CMD query="find procds, procds.modby, procds.createby, procds.moddate where dataset = *online*" 
	#$CMD query="find dataset where dataset like *-CRUZET4*RECO or dataset like *-CRUZET4*RAW or dataset like *_CRUZET4*ALCARECO" 
	#$CMD query="*tes t_primary_00" 
	#$CMD query="find dataset where dataset like *csa*" 
	#$CMD query="find dataset where dataset like *csa* and dataset not    like *online*" 
	#$CMD query="find dataset where dataset.tier = RAW " 
	#$CMD query="find dataset where dataset.id between 1   and   2 or dataset like *on*" 
	#$CMD query="find dataset where run between 51437 and 51450 or run between 0 and 30000" 
	#$CMD query="find file where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD and dataset =/test_primary_001/TestProcessedDS002/GEN-SIM" 
	#$CMD query="find dataset where procds.tier = RAW " 
	#$CMD query="find dataset where file.tier = RAW " 
	#$CMD query="find dataset where dataset = *RAW " 
	#$CMD query="find dataset where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO" 
	#$CMD query="find dataset where dataset like /csa07* or file like abc* or file.size > 0" 
	#$CMD query="find ilumi where run > 0"
	#$CMD query="find file where dataset.release = CMS"
	#$CMD query="find dataset where dataset = /*RAW order by dataset asc   "
	#$CMD query="find dataset where dataset = /*RAW order by dataset asc   "
	#$CMD query="find dataset where dataset != /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
	#$CMD query="find file where dataset != /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO and file != NEW_TEST_df0d58a5-9a6f-4dda-9e15-4d849c4768e0_311" 
	#$CMD query="find dataset where dataset = /*RAW"
	#$CMD query="find dataset where dataset = /*RAW"
	#$CMD query="find file where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD and dataset like *Commissioning08-GRtoBeam_v1/RAW or dataset = /BeamHalo/Commissioning08-GRtoBeam_v1/RAW and dataset in (/Cosmics/CRUZET3-v1/RAWaa, /BeamHalo/Commissioning08-GRtoBeam_v1/RAW)" 
	
	
	#$CMD query="find dataset where dataset = *RAW" upper="False"
	#$CMD query="find dataset where file = *RAW" upper="False"
	#$CMD query="find dataset"
	#$CMD query="find dataset.parent where dataset = /DY_mumu_10/CMSSW_1_3_1-Spring07-1349/GEN-SIM-DIGI-RECO"
	#$CMD query="find procds.parent where dataset = /DY_mumu_10/CMSSW_1_3_1-Spring07-1349/GEN-SIM-DIGI-RECO"
	#$CMD query="find dataset where site = caf.cern.ch"
	#$CMD query="find tier where dataset  = /DY_mumu_10/CMSSW_1_3_1-Spring07-1349/GEN-SIM-DIGI-RECO"
	#$CMD query="find tier where procds  = CMSSW_1_3_1-Spring07-1349"
	#$CMD query="find procds.tier where procds  = CMSSW_1_3_1-Spring07-1349"
	#$CMD query="find dataset.tier where procds  = CMSSW_1_3_1-Spring07-1349"
	#$CMD query="find dataset where tier = GEN-SIM-DIGI-RECO"
	#$CMD query="find dataset where tier in (GEN,   GEN-SIM-DIGI-RECO)"
	#$CMD query="find file.child where block = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW#7f0e573b-9200-41a7-a8af-76268ab3f970"
	#$CMD query="find block.child where block = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW#7f0e573b-9200-41a7-a8af-76268ab3f970"
	#$CMD query="find block.parent where block = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW#7f0e573b-9200-41a7-a8af-76268ab3f970"
	#$CMD query="find block where block.parent = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW#7f0e573b-9200-41a7-a8af-76268ab3f970"
	#$CMD query="find block.parent where dataset = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW"
	#$CMD query="find  dataset, dataset.parent"
	#$CMD query="find  file, file.parent"
	#$CMD query="find  file, file.child"
	#$CMD query="find file.child where block = /AH115bb_tau_tau_2l/Summer08_IDEAL_V9_v1/GEN-SIM-RAW#7f0e573b-9200-41a7-a8af-76268ab3f970"
	#$CMD query="find dataset.xsection where dataset like *"
	#$CMD query="find dataset.era"
	#$CMD query="find  block, block.child"
	#$CMD query="find dataset where dataset like * and ((run = 1) or (run between 1 and 2) and run.totlumi=222)"
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
	
	$CMD query="find config, config.name, config.type, config.version, config.content, config.hash, config.id, config.createdate, config.createby, config.moddate, config.modby"
	#$CMD query="find file where tier = GEN-SIM-DIGI-RECO"
	#$CMD query="find file.tier where tier = GEN-SIM-DIGI-RECO"
	#$CMD query="find file.tier where dataset = /DY_mumu_10/CMSSW_1_3_1-Spring07-1349/GEN-SIM-DIGI-RECO"
	#$CMD query="find dataset where dataset.parent = /DY_mumu_10/CMSSW_1_2_3-Spring07-EWK-1175695062/GEN-SIM"
	#$CMD query="find dataset.tag, dataset.createdate where dataset = /DY_mumu_10/CMSSW_1_2_3-Spring07-EWK-1175695062/GEN-SIM"
	#$CMD query="find procds.tag, procds.createdate where dataset = /DY_mumu_10/CMSSW_1_2_3-Spring07-EWK-1175695062/GEN-SIM"
	
	#$CMD query="find datatype.type, datatype.id, datatype.createdate, datatype.moddate where dataset = *on*"
	#$CMD query="find datatype, datatype.id, datatype.createdate, datatype.moddate, datatype.createby, datatype.modby"
	#$CMD query="find dataset where site != caf.cern.ch "
	#$CMD query="find dataset where site = caf.cern.ch "
	#$CMD query="find dataset where site = T1_CH_CERN "
	#$CMD query="find datatype, dataset, run.number, run.numevents, run.numlss, run.totlumi, run.store, run.starttime, run.endtime, run.createby,run.createdate, run.modby, run.moddate, count(file), sum(file.size) where dataset = /EndcapsMuon/CRUZET3_CRUZET3_V2P_v3/RECO"
	#$CMD query="find dataset where site in (T1_CH_CERN , T0_CH_CERN) "
	#$CMD query="find dataset where site in (T1_CH_CERN , T0_CH_CERN) "
	#$CMD query="find branch.name, branch.createby, branch.createdate, branch.modby, branch.moddate where file=/store/temp/backfill/data/AllRunsTest/Cosmics/RECO/OneOfEachLumi_v1/000/064/818/F0071E05-D6D9-DD11-9CD6-000423D174FE.root"

