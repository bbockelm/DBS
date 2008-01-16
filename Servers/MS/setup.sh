#!/bin/sh
#
export ANT_HOME=/home/sekhri/apache-ant-1.6.5/
#export JAVA_HOME=/home/sekhri/j2ee/SDK/jdk/

export JAVA_HOME=/usr/java/jdk1.5.0_10

ret=0

if [ "${JAVA_HOME}" == "" ]; then
	echo "Error! Please set your JAVA_HOME variable and source this file again"
	ret=1
fi
if [ "${ANT_HOME}" == "" ]; then
	echo "Error! Please set your ANT_HOME variable and source this file again"
	ret=1
fi

if [ $ret == 0 ]; then
	export PATH=${JAVA_HOME}/bin:${ANT_HOME}/bin:$PATH
	alias ant='ant  --noconfig'
fi


