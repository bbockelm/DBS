#!/usr/local/bin/bash
#
# Copy today's tomcat log to cmsdbs.cern.ch machine
#    if it is the first time today, copy yesterday's also
#    (needs to run right after the top of the hour)
#
# Author: Lee Lueking   Dec. 13, 2007
#
# Usage: ./copy_to_cmsdbs.sh
#
thisnode=`uname -n`
host=""
for node in cmsdbs1 cmsdbs2 cmst0dbs1 cmst0dbs2; do
    case `host $node 2>/dev/null` in
        *"is an alias for $thisnode"*)
            host=$node
            break
            ;;
    esac
done
if [ -z "$host" ]; then
    exit
fi
case $host in
    cmst0dbs*)
	logdir=/home/cmsdbs/sw/slc3_ia32_gcc323/external/apache-tomcat/5.5.20/logs;;
    *)  logdir=/home/cmsdbs/sw/slc4_ia32_gcc345/external/apache-tomcat/5.5.20/logs;;
esac
today=`date +%Y-%m-%d`
month="`date +%b`"
day="`date +%d`"
year="`date +%Y`"
#
#The following lines allows to pass log files for particular hour, and test, with hour in arg
#
if [ -n "$1" ]; then
  hour="$1"
else
  hour="`date +%k`"
fi
echo "hour=$hour"
let yestersec=($midnight - 86400)
midnight=`date +%s -d"$month $day 00:00:00 CET $year"` #today midnight
let yestersec=($midnight - 86400)
yesterday=`date +%Y-%m-%d -d@$yestersec`
#temp fix
#yesterday=$today
#echo "today: $today, midnight: $midnight, yesterday: $yesterday"
if [ "$hour" -gt 0 ]; then
echo "Copying at `date`:localhost_access_log.${today}.txt"
scp -q -F /data/cmsdbs/.ssh/config ${logdir}/localhost_access_log.${today}.txt dbfrontier@cmsdbs:data/dbs_logs/${host}_access_log.${today}.txt
if [ -d ${logdir}/httpd ]; then
    let hour="$hour-1"
    hour=`date +%H -d $hour` #this adds leading 0
    echo "Copying at `date`:access_log.${today}.${hour}.log"
    scp -q -F /data/cmsdbs/.ssh/config ${logdir}/httpd/access_log.${today}.${hour}.log dbfrontier@cmsdbs:data/dbs_logs/${host}_access_log_httpd.${today}.${hour}.txt
fi
else 
#if [ "$hour" = 0 ] 
#then
echo "Copying at `date`:localhost_access_log.${today}.txt"
scp -q -F /data/cmsdbs/.ssh/config ${logdir}/localhost_access_log.${yesterday}.txt dbfrontier@cmsdbs:data/dbs_logs/${host}_access_log.${today}.txt
if [ -d ${logdir}/httpd ]; then
   hour=23
   echo "Copying at `date`:access_log.${today}.${hour}.log"
    scp -q -F /data/cmsdbs/.ssh/config ${logdir}/httpd/access_log.${yesterday}.$hour.log dbfrontier@cmsdbs:data/dbs_logs/${host}_access_log_httpd.${today}.${hour}.txt
#
# Make link for lemon monitoring
#
rm /home/cmsdbs/log/tomcat_access_log
echo "ln -s ${logdir}/localhost_access_log.${today}.txt /home/cmsdbs/log/tomcat_access_log"
ln -s ${logdir}/localhost_access_log.${today}.txt /home/cmsdbs/log/tomcat_access_log
fi
fi
cd ${logdir}
find *.txt -mtime +0 -exec gzip {} \;
exit
