#!/bin/sh
#JAVA_HOME=/usr/java/jdk1.5.0_06/
BASE=$PWD/..
savePWD=$PWD
cd $BASE
source setup.sh
if [ "${JAVA_HOME}" = "" ]; then
        echo "<dbs><exception code='123' detail='Error! Please set your JAVA_HOME variable' message='env variable not set'/></dbs>"
        exit 1
fi
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14_g.jar:$PWD/mysql-connector-java-5.0.3-bin.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
#FIXME DBS_SERVER_CONFIG is not needed
#export DBS_SERVER_CONFIG=$BASE/etc/context.xml
#CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI apiversion=v00_00_04"
CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI"
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
