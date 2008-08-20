#!/usr/local/bin/bash
#
# copy the apache logs from /var/logs/httpd to tomcat log area
#
# Author: Lee Lueking
# Date: July 18, 2008
#
datenow=`date +%Y-%m-%d`
varlogdir=/var/log/httpd/
tomcatlogdir=/home/cmsdbs/sw/slc4_ia32_gcc345/external/apache-tomcat/5.5.20/logs/httpd
echo "Begin copy at `date`"
echo "cp ${varlogdir}/access_log ${tomcatlogdir}/access_log.${datenow}.log"
cp ${varlogdir}/access_log ${tomcatlogdir}/access_log.${datenow}.log
echo "chown cmsdbs:zh ${tomcatlogdir}/access_log.${datenow}.log"
chown cmsdbs:zh ${tomcatlogdir}/access_log.${datenow}.log
echo "Copy completed"
