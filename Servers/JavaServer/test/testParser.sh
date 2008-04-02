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
echo ""select file where path=abc"" | $JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.search.parser.Test

