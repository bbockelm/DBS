BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar:$PWD/antlrworks-1.1.7.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
#echo $i | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo ""select file where path=abc"" | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name where block=abc and procds=2" | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name,file.size where procds.name=Online and file.size>546294916"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name,file.size,path where procds.name=Online and file.size>546294916"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file,file.size,path where procds.name=Online and file.size>546294916 or path=/CalPrivateGlobal-default/Online/RAW"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name where run.number=35672"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
#echo "select file.name,file.size where procds.name=Online and file.size in (546294916,546580510)"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test

echo "select file.name,ls.starttime,run.endtime where run.number=35672 or ls.number=2 or run.starttime=1"  | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test
