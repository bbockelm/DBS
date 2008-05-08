BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar:$PWD/antlrworks-1.1.7.jar:$PWD/commons-collections-3.2.jar:$PWD/jung-1.7.6.jar
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
#$CMD query="find procds.tier, procds" 
#$CMD query="find file, file.tier" 
#$CMD query="find file where file.tier = GEN" 
#$CMD query="find procds,file where procds.tier = GEN" 
#$CMD query="find procds,file,file.tier where dataset like *" 
#$CMD query="find procds.tier where procds.tier like *" 
#$CMD query="find file where file.release  like * or  dataset=/abc/Online/RAW" begin=5 end=20
#$CMD query="find file, file.createdate, file.moddate where file.createdate > 0" begin=5 end=20 type=quera
$CMD query="find dataset, file.id, lumi.id where dataset like *" begin=5 end=20 type=querya
#executeQuery

