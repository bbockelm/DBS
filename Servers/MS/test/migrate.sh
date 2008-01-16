#!/bin/sh
#JAVA_HOME=/usr/java/jdk1.5.0_06/
cd /home/sekhri/MS/
source setup.sh
cd test
BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
outFile=$savePWD/result.txt
rm -f $outFile
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DMS_SERVER_CONFIG=$BASE/etc/context.xml ms.cron.Start"
#CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DMS_SERVER_CONFIG=$BASE/etc/context.xml ms.cron.DbsWebApi"
$CMD
display () {
	echo "$1" >> $outFile
	tmp=`echo "$1" | grep "exception"`
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

run () {
	message="Executing ..."
	echo $message >> $outFile ; echo $message
	out=`$CMD`
	display "$out"
}
#run
#	
#echo 
#echo "*************************************************************"
#echo "For more detail and the output of the APIs look in $outFile"
#cd $savePWD
