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


CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7"
executeQuery () {
	message="Executing executeQuery API..."
	echo $message
	#out=`$CMD api=executeQuery query="select file where file.release=CMSSW_2_1_0_pre1"`
	#out=`$CMD api=executeQuery query="select file,file.status,file.type where procds.release=CMSSW_1_7_1"`
	#out=`$CMD api=executeQuery query="find file.id,ls.id where procds.release=CMSSW_1_7_1"`
	#out=`$CMD api=executeQuery query="select file.name,file.createdate where run.number=35672 and run.moddate>2"`
	#out=`$CMD api=executeQuery query="select file,file.size,dataset where procds.name=Online and file.size>546294916"`
	#out=`$CMD api=executeQuery query="select  file,    ls    where    dataset  =   /CalPrivateGlobal-default/Online/RAW"`
	#out=`$CMD api=executeQuery query="select ls where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"`
	#out=`$CMD api=executeQuery query="select file.release,file where dataset=/CalPrivateGlobal-default/Online/RAW and procds.release=CMSSW_1_7_1"`
	#out=`$CMD api=executeQuery query="select run where run.number>1 and run.number<100"`
	out=`$CMD api=executeQuery query="select run.count where run.number > 1 and run.number < 100"`
	#out=`$CMD api=executeQuery query="select run.count where dataset=/CalPrivateGlobal-default/Online/RAW"`
	#out=`$CMD api=executeQuery query="select file,run,ls where dataset=/GlobalMar08-Express/Online/RAW"`
	#out=`$CMD api=executeQuery query="select file,run,ls where dataset like %Online%"`
	#out=`$CMD api=executeQuery query="select run,ls where dataset like %Online%"`
	#out=`$CMD api=executeQuery query="select run,ls,file where dataset  like  %Online%"`
	#out=`$CMD api=executeQuery query="find file.release   where  dataset=/CalPrivateGlobal-default/Online/RAW"`
	#out=`$CMD api=executeQuery query="select primds where primds.name like %"`
	#out=`$CMD api=executeQuery query="select procds where procds.name like %"`
	#out=`$CMD api=executeQuery query="select dataset where block.name like %"`
	#out=`$CMD api=executeQuery query="select procds"`
	echo "$out"
}
#$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7 api=executeQuery query="select run,ls where path like %Online%"
executeQuery
