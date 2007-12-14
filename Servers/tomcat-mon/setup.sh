#!/bin/sh
#
export CATALINA_HOME=/home/anzar/devFRONTIER/TOMCAT/apache-tomcat-5.5.15
export ANT_HOME=/home/sekhri/apache-ant-1.6.5/
export JAVA_HOME=/usr/java/jdk1.5.0_10
#
ret=0

if [ "${CATALINA_HOME}" == "" ]; then
        echo "Error! Please set your CATALINA_HOME variable and source this file again"
        ret=1
fi

if [ "${JAVA_HOME}" == "" ]; then
	echo "Error! Please set your JAVA_HOME variable and source this file again"
	ret=1
fi
if [ "${ANT_HOME}" == "" ]; then
	echo "Error! Please set your ANT_HOME variable and source this file again"
	ret=1
fi

if [ $ret == 0 ]; then
	export PATH=${JAVA_HOME}/bin:${ANT_HOME}/bin:${CATALINA_HOME}/bin:$PATH
	alias ant='ant  --noconfig'
fi

#        <Valve className="org.cms.dbs.catalina.valves.GridAccessLogValve"
#                 directory="logs"  prefix="localhost_access_log." suffix=".txt"
#                 rotatable="true"
#                 pattern='%h %l %u %t "%r" %s %b "%{Referer}i" "%{User-Agent}i" %D' resolveHosts="false"/>

