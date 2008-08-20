#!/usr/local/bin/bash
#
# Copy today's tomcat log to cmsdbs.cern.ch machine
#    if it is the first time today, copy yesterday's also
#
# Author: Lee Lueking   Dec. 13, 2007
#
# Usage: ./copy_to_cmsdbs.sh
#
host="cmsdbs1"
logdir=/home/cmsdbs/sw/slc4_ia32_gcc345/external/apache-tomcat/5.5.20/logs
today=`date +%Y-%m-%d`
month="`date +%b`"
day="`date +%d`"
year="`date +%Y`"
hour="`date +%H`"
midnight=`date +%s -d"$month $day 00:00:00 CEST $year"` #today midnight
#yesterday=`date +%Y-%m-%d -d@$(echo "$midnight - 86400" | bc -l)`
#temp fix
yesterday=$today
echo "today: $today, midnight: $midnight, yesterday: $yesterday"
scp -q -F /data/cmsdbs/.ssh/config ${logdir}/localhost_access_log.${today}.txt dbfrontier@cmsdbs:/data/dbs_logs/${host}_access_log.${today}.txt
scp -q -F /data/cmsdbs/.ssh/config ${logdir}/httpd/access_log.${today}.log dbfrontier@cmsdbs:/data/dbs_logs/${host}_access_log_httpd.${today}.txt
if [ "$hour" = 0 ] 
then
scp -q -F /data/cmsdbs/.ssh/config ${logdir}/localhost_access_log.${yesterday}.txt dbfrontier@cmsdbs:/data/dbs_logs/${host}_access_log.${today}.txt
fi

exit
