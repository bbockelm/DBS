#!/bin/sh
#
# Setting JAVA_HOME ANT_HOME and DBS_CONFIG
# is users responsibility.
# DBS_SERVER_CONFIG needs to be set before deploying to TomCat 
#
#
#export JAVA_HOME=
#export ANT_HOME=
#export JAVA_HOME=/usr/java/jdk1.5.0_09
export JAVA_HOME=/usr/java/jdk1.5.0_06/
#export ANT_HOME=/home/sekhri/work/frontier/apache-ant-1.6.5/
export ANT_HOME=/home/sekhri/apache-ant-1.6.5/

#export DBS_SERVER_CONFIG=$PWD/etc/context.xml

ret=0

#if [ "${DBS_SERVER_CONFIG}" == "" ]; then
#        echo "Error! Please set your DBS_SERVER_CONFIG variable and source this file again"
#        ret=1
#fi

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


