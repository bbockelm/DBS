#!/usr/local/bin/bash
#
# Reformat and copy log information to monitoring machine
#
# Author: Lee Lueking August 13, 2008
#
# Run once an hour, or so, will work with the reformatter 
# to process the info needed. The complication is that there 
# is a cron job that runs every 15 minutes that will rotate 
# the logs, so need to check first if a rotation has occured 
# in the last extraction period. 
#
# Note: may be some problem if rotation occurs while this script is running. 
#       Need to time this script to avoid this happening, i.e. right after potential rotation.
#
echo "########################################################################"
echo "Begin time: `date`"
extraction_period=3600 # in seconds 
srcdir=/home/dbfrontier/local/etc
workdir=/home/data/squid_logs
logdir=/home/data/squid_logs
logfile=access.log
today=`date +%Y-%m-%d`
hour="`date +%H`"
#
# Get the host name
#
THISNODE=`uname -n`
host=""
for N in 1 2 3 4 5; do
    NODE=cmsfrontier$N
    case `host $NODE 2>/dev/null` in
        *"is an alias for $THISNODE"*)
            host=$NODE
            break
            ;;
    esac
done
echo "Host node: $host"
#
# auto test stuff
#
if [ "$1" = "test" ]; then
  echo "#### TEST MODE ####"
  extraction_period=5 # in seconds 
  logdir="."
  workdir="."
  logfile=access.log
  for suffix in `seq 10 -1 0`;do
    touch $logdir/${logfile}.$suffix
    echo "$logdir/${logfile}.$suffix, Time: `date +%s`"
    sleep 2
  done
  touch $logdir/${logfile}
  echo "$logdir/${logfile}, Time: `date +%s`"
fi
#
# check if file has rotated in last hour
#
#cd $workdir
current_time=`date +%s`
begin_sequence=""
for suffix in `seq 0 9`; do 
  rotate_time=$(stat -c %Y $logdir/${logfile}.$suffix)
  time_since_rotate=$(echo "$current_time - $rotate_time" | bc -l)
  echo "Current Time: $current_time, Time of last rotate: $rotate_time, suffix: $suffix" 
  if [ "$time_since_rotate" -le "$extraction_period" ]; then
    echo "log file to begin with is $logdir/${logfile}.$suffix"
    begin_sequence=$suffix
  else
    break
  fi
done
#    
#if offset file is not there, make it
#
if [ -e offset.dat ]; then
  echo "Offset value is: `cat offset.dat`"
else
  echo 0 > offset.dat
fi
#
# work backward starting with last file in sequence, unless sequence is empty
#
if [ "$begin_sequence" != "" ]; then
  for suffix in `seq $begin_sequence -1 0`; do 
    echo "Reformat for file $logdir/${logfile}.$suffix"
    # copy rfm file to monitor node, 
    offset="`cat offset.dat`"
    $srcdir/reform-squid-log-offset.py --input=$logdir/$logfile.$suffix --output=$logdir/${host}_access_log.$today.$hour.$suffix.rfm --offset=$offset
    gzip $logdir/${host}_access_log.$today.$hour.$suffix.rfm
    scp $logdir/${host}_access_log.$today.$hour.$suffix.rfm.gz dbfrontier@cmsdbs:/data/squid_logs/.
    echo "scp $logdir/${host}_access_log.$today.$hour.$suffix.rfm.gz dbfrontier@cmsdbs:/data/squid_logs/."
    # remove rfm file
    rm $logdir/${host}_access_log.$today.$hour.$suffix.rfm.gz
    # set offset to 0
    echo 0 > offset.dat
  done
fi
#
# reformat current log file using existing offset
#
echo "Reformatting for file $logdir/${logfile}"
# copy rfm file to monitor node, 
offset="`cat offset.dat`"
$srcdir/reform-squid-log-offset.py --input=$logdir/$logfile --output=$logdir/${host}_access_log.$today.$hour.rfm --offset=$offset
gzip $logdir/${host}_access_log.$today.$hour.rfm
scp $logdir/${host}_access_log.$today.$hour.rfm.gz dbfrontier@cmsdbs:/data/squid_logs/.
echo "scp $logdir/${host}_access_log.$today.$hour.rfm.gz dbfrontier@cmsdbs:/data/squid_logs/."
# remove rfm file
rm $logdir/${host}_access_log.$today.$hour.rfm.gz
echo "Ending file offset: `cat offset.dat`"
echo "Complete time: `date`"
exit
