#!/bin/sh
#JAVA_HOME=/usr/java/jdk1.5.0_06/
BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14_g.jar
cd $BASE/bin
#echo "$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.DBSTest"
$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.DBSTest
cd $savePWD
