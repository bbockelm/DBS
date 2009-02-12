#!/bin/sh
#JAVA_HOME=/usr/java/jdk1.5.0_06/
BASE=$PWD/..
#BASE=${DBSSERVERHOME}
#if [ "${DBSSERVERHOME}" = "" ]; then
#        echo "Error! Please set your DBSSERVERHOME variable"
#        exit 1
#fi
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
outFile=$savePWD/result.txt
rm -f $outFile

cd $BASE/../../LibValut
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar:$PWD/antlrworks-1.2.2.jar:$PWD/commons-collections-3.2.jar:$PWD/jung-1.7.6.jar:$PWD/commons-lang-2.4.jar:$PWD/msclient.jar


cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7"

display () {
	echo "$1" >> $outFile
	tmp=`echo "$1" | grep -i "exception"`
	len=${#tmp}
	if [ "$len" -lt "1" ] ; then
		echo "PASSED"
		echo "*****************************************************************"
		echo 
	else
		echo "FAILED $tmp"
		echo "*****************************************************************"
		echo 
	fi
}

executeSummary () {
	message="Executing getSummary API..."
        echo $message $viewName >> $outFile
        echo $message $viewName
        out=`$CMD api=executeSummary query="find primds" begin=0 end=1 2>&1`
#        out=`$CMD api=executeSummary query="find tier" begin=0 end=1 2>&1`
        display "$out"
}

# run command
executeSummary

echo 
echo "*************************************************************"
echo "For more detail and the output of the APIs look in $outFile"
cd $savePWD
