BASE=..
BACK=$PWD
if [ "${JAVA_HOME}" = "" ]; then
        echo "Error! Please set your JAVA_HOME variable"
        exit 1
fi
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14_g.jar:$PWD/mysql-connector-java-5.0.3-bin.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
cd $BACK
#export $CLASSPATH
echo "export CLASSPATH=$CLASSPATH"
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.whatever.class etc etc

