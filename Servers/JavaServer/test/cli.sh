#!/bin/sh
#JAVA_HOME=/usr/java/jdk1.5.0_06/
BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
outFile=$savePWD/result.txt
rm -f $outFile
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14_g.jar:$PWD/mysql-connector-java-5.0.3-bin.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI apiversion=v00_00_01"
if [ $# -eq 1 ] ; then
	$CMD "$1"
fi
if [ $# -eq 2 ] ; then
	$CMD "$1" "$2"
fi
if [ $# -eq 3 ] ; then
	$CMD "$1" "$2" "$3"
fi
if [ $# -eq 4 ] ; then
	$CMD "$1" "$2" "$3" "$4"
fi
if [ $# -eq 5 ] ; then
	$CMD "$1" "$2" "$3" "$4" "$5"
fi
if [ $# -eq 6 ] ; then
	$CMD "$1" "$2" "$3" "$4" "$5" "$6"
fi
if [ $# -eq 7 ] ; then
	$CMD "$1" "$2" "$3" "$4" "$5" "$6" "$7"
fi
if [ $# -eq 8 ] ; then
	$CMD "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8"
fi
if [ $# -eq 9 ] ; then
	$CMD "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8" "$9"
fi

cd $savePWD
