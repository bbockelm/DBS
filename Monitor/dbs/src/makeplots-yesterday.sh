#!/usr/local/bin/bash
#alias date=/bin/date
#alias ls=/bin/ls
#alias cp=/bin/cp
#alias rm=/bin/rm
#alias bc=/usr/bin/bc
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
servicenames="cmsdbs cmst0dbs cmsdevdbs"
workdir=/data/dbs_logs/temp
plotdir=/data/cmsdbs/public/dbsplots
srcdir=/data/dbs_logs/src
logdir=/data/dbs_logs
today="`date +%Y-%m-%d`"
#today="2007-12-17" #for testing purposes only
month="`date +%b`"
day="`date +%d`"
#day="17" #for testing purposes only
year="`date +%Y`"
midnight=`date +%s -d"$month $day 00:00:00 CEST $year"` #today midnight
yesterday=`date +%Y-%m-%d -d@$(echo "$midnight - 86400" | bc -l)`
#midnight="$(echo "$midnight - 1" | bc -l)"
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
  /bin/cp $logdir/${service}*${yesterday}*.txt .
 # gunzip *.gz
#
# reformat, and make the root tree files
#
#  rm SquidFiles.C
  for file in `ls ${service}*${yesterday}*.txt`
  do
    echo "Processing file : $file"
    echo "$srcdir/reform-dbs2-log.py --input=$logdir/$file --output=$file.rfm"
    $srcdir/reform-dbs2-log.py --input=$logdir/$file --output=$file.rfm
    $ROOTSYS/bin/root -b -q $srcdir/"DBSTree.C++(\"$file.rfm\",\"$file.root\")"
    #mv squid.root ${file}.root
  done
#
# Generate the plots and move to web page
# (need to use TChain)
#
# for today
#
 end=`/bin/date +%s -d@$midnight`
 begin=`/bin/date +%s -d@$(echo "$midnight - 86400" |/usr/bin/bc -l)`
 end="$(echo "$midnight - 30" |/usr/bin/bc -l)" 
 echo "yesterday: $yesterday, begin: $begin, end: $end, bin: 3600"
 echo "begin: `/bin/date -d@$begin`, end `date -d@$end`"
 $ROOTSYS/bin/root -b -q $srcdir/"DBSTreePlots.C(\"${service}*${yesterday}*.root\",$begin,$end,3600)"
 cp *.png $plotdir/${service}/yesterday
 rm update.txt
 echo "`date`"> yesterday-update.txt
 cp yesterday-update.txt $plotdir/${service}/yesterday/update.txt
#
# Clean up
#
#
  /bin/rm *.rfm
# Done!
#
done
exit
