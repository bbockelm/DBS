BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
cd $BASE/../../LibValut
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar:$PWD/antlrworks-1.1.7.jar:$PWD/commons-collections-3.2.jar:$PWD/jung-1.7.6.jar:$PWD/commons-lang-2.4.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/

CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7 api=executeQuery"
executeQuery () {
	echo "Executing executeQuery API..."
	$CMD query="find file where file.release=CMSSW_1_7_1"
	$CMD query="find file,file.status,file.type where procds.release=CMSSW_1_7_1"
	$CMD query="find file.id,lumi.id   where procds.release=CMSSW_1_7_1"
	$CMD query="find file.name, file.createdate where run.number=35672 and run.moddate>2"
	$CMD query="find file,file.size,dataset where procds.name=Online and file.size>546294916"
	$CMD query="find  file,    lumi    where    dataset  =   /CalPrivateGlobal-default/Online/RAW"
	$CMD query="find lumi where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"
	$CMD query="find file.release,file where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"
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
	$CMD query="find dataset where site like %srm.cern*" 
	$CMD query="find file where run > 1 and run < 1000000"
	$CMD query="find procds.status  where dataset = /CalPrivateGlobal-default/Online/RAW"
	$CMD query="find procds, procds.era, procds.tag"
	$CMD query="find procds, procds.era, procds.tag where dataset = /CalPrivateGlobal-default/Online/RAW"
	$CMD query="find site" 
	$CMD query="find file.parent,file.parent, file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM" 
	$CMD query="find procds.parent,procds.name  where procds.name like %Test% or procds.parent like %"
	$CMD query="find file.release   where  dataset=/CalPrivateGlobal-default/Online/RAW  or dataset=/abc/Online/RAW"
	$CMD query="find file where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD" 
	$CMD query="find dataset, procds.createdate where dq = Tracker_Global=GOOD&TIB_Local=GOOD" 
	$CMD query="find file,lumi,dataset where dq = Tracker_Global=GOOD&TIB_Local=GOOD&TIB_DCS=UNKNOWN or file.size > 1000" 
	$CMD query="find file, file.tier" 
	$CMD query="find file where file.tier = GEN" 
	$CMD query="find procds,file where procds.tier = GEN" 
	$CMD query="find procds,file,file.tier where dataset like *" 
	$CMD query="find procds,file where procds.tier in (GEN,SIM,RAW)" 
	$CMD query="find ads,file"
	$CMD query="find ads"
	$CMD query="find lumi,ads"
	$CMD query="find ads, ads.type, ads.status, ads.createby"
	$CMD query="find file,lumi where ads.name = /TestPrimary_001_20080501_10h06m06s/TestProcessed_20080501_10h06m06s/GEN-SIM/TestAnalysisDSDef_005_20080501_10h06m06s"
	$CMD query="find ads, ads.dataset"
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
	$CMD query="find run, ilumi, file"
	$CMD query="find run, ilumi, file where run in (1,2,3)"
	$CMD query="find ilumi, file where run in (1,2,3)"
	$CMD query="find ilumi, file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM"
	$CMD query="find ilumi, file where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM order by file"
	$CMD query="find ilumi, dataset where run > 0"
	$CMD query="find ilumi"
	$CMD query="find block"
	$CMD query="find block, block.size,  block.createdate, block.moddate, block.createby, block.modby where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
	$CMD query="find block.size,  block.createdate, block.moddate, block.createby, block.modby where block = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO#01123fd3-5486-41fe-8b90-7dbb8fbe69b8"



}
#$CMD query="find file,file.parent where dq = Tracker_Global=GOOD&TIB_Local=GOOD" 
#$CMD query="find lumi.id,file.id where dq = Tracker_Global=GOOD&TIB_Local=BAD&TIB_DCS=UNKNOWN and dataset = /CalPrivateGlobal-default/Online/RAW" 
#$CMD query="find file where dq = Tracker_Global=GOOD&TIB_Local=GOOD" 
#$CMD query="find file.parent,file where file.name = NEW_TEST0005"
#$CMD query="find file,file.parent,run,dataset where file.parent like %NEW% or file.parent like %" 
#$CMD query="find procds.parent,procds.name,file,file.parent  where procds.parent like %"

#$CMD query="find lumi.id,file.id where dataset in (/CalPrivateGlobal-default/Online/RAW,/GlobalMar08-Express/Online/RAW)" 
#$CMD query="find dataset where site.name like %srm.cern*" 
#$CMD query="find file,lumi,site where dataset = /CalPrivateGlobal-default/Online/RAW" 
#$CMD query="find file,file.parent where dataset = /CalPrivateGlobal-default/Online/RAW" 
#$CMD query="find file,file.parent" 
#$CMD query="find procds,procds.parent" 
#$CMD query="find lumi where file in ( NEW_TEST0005, NEW_TEST0004, NEW_TEST0003 ) or dataset = /test_primary_001/TestProcessedDS002/GEN-SIM or run in (1,2,3)" 
#$CMD query="find file where file.tier = GEN" 
#$CMD query="find procds,file where procds.tier = GEN" 
#$CMD query="find procds,file,file.tier where dataset like *" 
#$CMD query="find procds.tier where procds.tier like *" 
#$CMD query="find file where file.release  like * or  dataset=/abc/Online/RAW" begin=5 end=20
#$CMD query="find file, file.createdate, file.moddate where file.createdate > 0" begin=5 end=20 type=quera
#$CMD query="find dataset, file.id, lumi.id where file.createdate > 0" begin=5 end=20 type=querya
#$CMD query="find dataset.parent  where dataset = /test_primary_001/TestProcessedDS002/GEN-SIM"


#$CMD query="find file, file.createdate, file.moddate where  dataset = /test_primary_001/TestProcessedDS002/GEN-SIM "


#$CMD query="find dataset"
#$CMD query="find run where dataset = /TestPrimary_001_20080602_11h30m20s/TestProcessed_20080602_11h30m20s/GEN-SIM"
#$CMD query="find file where run = 432.233"
#$CMD query="find dataset, procds.createdate where dq = Tracker_Global=GOOD&TIB_Local=GOOD"
#$CMD query="find run, run.createdate where run.createdate = 2008-05-01 12:05:12 order by run"
#$CMD query="find ads"
#$CMD query="find procds, procds.era, procds.tag"
#$CMD query="find procds, lumi where run > 0"
#$CMD query="find file where dataset like *Cosmics/CRUZET2_CRUZET2_V1_v2/RECO* or run < 46873"

#$CMD query="find dataset where dataset like /CSA0*/CMSSW_*/*"
#$CMD query="find file, file.release, file.size, file.type, file.status where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
#$CMD query="find file, file.parent where file = /store/user/ndefilip/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/NicolaDeFilippis/Skim-zToTauTau_MuTau-Tier0-A1-Chowder_85f0790dd16f9aff6ccde9b27395c4d2/zToTauTau_MuTau_372.root"
#$CMD query="find dataset where procds.createdate > 2007-04-20"
#$CMD query="find dataset.parent where dataset = /CSA07Muon/Skim-zToTauTau_MuTau-Tier0-A1-Chowder/USER"
#$CMD query="find primds"
#$CMD query="find block, block.size "
#$CMD query="find block, block.size,  block.createdate, block.moddate, block.createby, block.modby where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
	$CMD query="find block.dataset, block.size,  block.createdate, block.moddate, block.createby, block.modby where block = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO#01123fd3-5486-41fe-8b90-7dbb8fbe69b8"
#$CMD query="find ads where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO"
#$CMD query="find primds.createdate, primds.moddate, primds.createby, primds.modby where primds = CSA07Muon"
#$CMD query="find lumi, lumi.startevnum, lumi.endevnum where file = NEW_TEST0005"
#$CMD query="find site where dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO" 
#$CMD query="find file where site like castorsrm.cr*" 
#$CMD query="find file, run where dq = TIB_DCS=UNKNOWN&Tracker_Global=GOOD&TIB_Local=GOOD and dataset = /CSA07Muon/CMSSW_1_6_7-CSA07-Tier0-A1-Chowder/RECO" 
#$CMD query="find primds where primds.createdate > 2007-04-20"
#$CMD query="find procds.release, procds.era, procds.tag, procds.status, procds.createdate, procds.moddate, procds.createby, procds.modby where procds = Skim-zToTauTau_MuTau-Tier0-A1-Chowder/USER"
#$CMD query="find procds, procds.release, procds.era, procds.tag, procds.status, procds.createdate, procds.moddate, procds.createby, procds.modby "

#executeQuery

