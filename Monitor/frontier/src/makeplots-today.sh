#!/usr/local/bin/bash
# 1. make root tree files (squid.root) from the input reformatted logs, 
# 2. Generate plots for the day 
#
# Set up root
#
#if [ -z "$ROOTSYS" ]
#then
export ROOTSYS="/afs/cern.ch/sw/lcg/external/root/5.16.00/slc4_ia32_gcc34/root"
PATH="${PATH}:$ROOTSYS/bin"
export LD_LIBRARY_PATH="$ROOTSYS/lib:$LD_LIBRARY_PATH"
#fi
echo "ROOTSYS $ROOTSYS"
echo "PATH $PATH"
echo "LD_LIBRARY_PATH $LD_LIBRARY_PATH"
echo "which root"
which root
#
# Uses temp area for work
#
servicenames="cmsfrontier" # cmst0dbs cmstestdbs"
workdir=/data/squid_logs/temp
plotdir=/home/dbfrontier/local/apache/squidplots
srcdir=/data/squid_logs/src
logdir=/data/squid_logs
#today="`date +%Y%m%d`"
today="`date +%Y-%m-%d`"
#today="2007-12-17" #for testing purposes only
month="`date +%b`"
day="`date +%d`"
#day="17" #for testing purposes only
year="`date +%Y`"
midnight=`date +%s -d"$month $day 00:00:00 CEST $year"` #today midnight
yesterday=`date +%Y-%m-%d -d@$(echo "$midnight - 86400" | bc -l)`
echo "today: $today, midnight: $midnight, yesterday: $yesterday"
#dayofyear="`data +%y%j`"
#
# go to work directory
#
cd $workdir
echo "pwd: `pwd`"
#
# Loop over servicenames
#
for service in $servicenames
do
#
# copy and unzip logs for today into work area
# 
  /bin/cp $logdir/${service}*${today}*.rfm.gz .
  gunzip *.gz
#
# reformat, and make the root tree files
#
#  rm SquidFiles.C
  for file in `ls ${service}*${today}*.rfm`
  do
    echo "Processing file : $file"
    if [ -f $file.root ] 
    then
      echo "Root file $file.root already exists, not reprocessed."
    else
      $ROOTSYS/bin/root -b -q $srcdir/"SquidTree.C++(\"$file\",\"$file.root\")"
    fi
  done
#
# Generate the plots and move to web page
# (need to use TChain)
#
# for today
#
 begin=`/bin/date +%s -d@$midnight`
 end=`/bin/date +%s -d@$(echo "$midnight + 86400" |/usr/bin/bc -l)`
 echo "today: $today, begin: $begin, end: $end, bin: 3600"
 echo "begin: `/bin/date -d@$begin`, end `date -d@$end`"
 $ROOTSYS/bin/root -b -q $srcdir/"SquidTreePlots.C(\"${service}*${today}*.root\",$begin,$end,3600)"
 # move plots into directory with todays date
 if [ ! -d $plotdir/${service}/${today} ];then
   mkdir $plotdir/${service}/${today}
   rm $plotdir/${service}/today
   ln -s $plotdir/${service}/${today} $plotdir/${service}/today
   ln -s $plotdir/${service}/${yesterday} $plotdir/${service}/yesterday
 fi
 cp $srcdir/index.html $plotdir/${service}/${today}
 cp *.png $plotdir/${service}/${today}
 rm update.txt
 echo "`date`"> update.txt
 cp update.txt $plotdir/${service}/${today}/.

#
# Clean up
#
#
  /bin/rm *.rfm
# Done!
#
done
exit
