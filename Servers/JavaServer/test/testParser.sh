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
#echo $i | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo ""select file where path=abc"" | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name where block.name=abc and procds.name=2" | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name,file.size where procds.name=Online and file.size>546294916"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name,file.size,path where procds.name=Online and file.size>546294916"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file,file.size,path where procds.name=Online and file.size>546294916 or path=/CalPrivateGlobal-default/Online/RAW"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file,file.size,path where procds.name=Online and file.size>546294916"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name where run.number=35672"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name,file.size where procds.name=Online and file.size in (546294916,546580510)"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test

#echo "select file.name,ls.starttime,run.endtime where run.number=35672 or ls.number=2 or run.starttime=1"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test


CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7 api=executeQuery"
executeQuery () {
	echo "Executing executeQuery API..."
	$CMD query="select file where file.release=CMSSW_1_7_1"
	$CMD query="select file,file.status,file.type where procds.release=CMSSW_1_7_1"
	$CMD query="find file.id,ls.id   where procds.release=CMSSW_1_7_1"
	$CMD query="select file.name, file.createdate where run.number=35672 and run.moddate>2"
	$CMD query="select file,file.size,dataset where procds.name=Online and file.size>546294916"
	$CMD query="select  file,    ls    where    dataset  =   /CalPrivateGlobal-default/Online/RAW"
	$CMD query="select ls where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"
	$CMD query="select file.release,file where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"
	$CMD query="select run where run.number>1 and run.number<100"
	$CMD query="select run.count where run.number > 1 and run.number < 100"
	$CMD query="select run.count where dataset=/CalPrivateGlobal-default/Online/RAW"
	$CMD query="select file,run,ls  where dataset=/GlobalMar08-Express/Online/RAW and file.type = STREAMER"
	$CMD query="select file, run,ls where dataset like %Online%"
	$CMD query="select run,ls where dataset like %Online%"
	$CMD query="select run,ls,file where dataset  like  %Online%"
	$CMD query="find file.release   where  dataset=/CalPrivateGlobal-default/Online/RAW"
	$CMD query="select primds where primds.name like %test%"
	$CMD query="select dataset"
	$CMD query="select procds, procds.createdate"
	$CMD query="select primds"
	$CMD query="select procds.createby, run.modby" 
	$CMD query="select file, procds.createby where  file.modby like %sekhri% and dataset = /CalPrivateGlobal-default/Online/RAW" 
	$CMD query="select file, procds.createby, file.modby, procds.createdate" 
	$CMD query="select file where file.modby like %sekhri%" 
}
#$CMD query="select ls.id,file.id where dataset = /CalPrivateGlobal-default/Online/RAW" 
$CMD query="select procds, procds.createdate,run,ls.moddate" 
#executeQuery
