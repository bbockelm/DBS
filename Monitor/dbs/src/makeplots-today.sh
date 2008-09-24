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
#export ROOTSYS="/afs/cern.ch/sw/lcg/external/root/5.16.00/slc4_ia32_gcc34/root"
#PATH="${PATH}:$ROOTSYS/bin"
#export LD_LIBRARY_PATH="$ROOTSYS/lib:$LD_LIBRARY_PATH"
export ROOTSYS="/afs/cern.ch/cms/sw/slc4_amd64_gcc345/lcg/root/5.18.00-CMS19a/"
export PATH="$ROOTSYS/bin:${PATH}"
#X11R6="/usr/X11R6/lib64"
export LD_LIBRARY_PATH="$ROOTSYS/lib:$ORACLE_HOME/lib:$LD_LIBRARY_PATH"
echo "ROOTSYS $ROOTSYS"
echo "PATH $PATH"
echo "LD_LIBRARY_PATH $LD_LIBRARY_PATH"
echo "which root"
which root
#
# Uses temp area for work
#
servicenames="cmsdbs cmst0dbs cmsdevdbs"  
# cmstestdbs"
basedir=/home/dbfrontier
workdir=$basedir/data/dbs_logs/temp
plotdir=$basedir/local/apache/dbsplots
srcdir=$basedir/apps/DBS/Monitor/dbs/src
logdir=$basedir/data/dbs_logs
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
echo "Service: $service"
#
# copy and unzip logs for today into work area
# 
if [ $service = "cmsdbs" -o $service = "cmsdevdbs" -o $service = "cmst0dbs" ]
then
  /bin/cp $logdir/${service}*${today}*.txt .
 # gunzip *.gz
#
# reformat, and make the root tree files
#
#  rm SquidFiles.C
  for file in `ls ${service}*${today}*.txt`
  do
    echo "Processing file : $file"
    echo "$srcdir/reform-dbs2-log.py --input=$logdir/$file --output=$file.rfm"
    $srcdir/reform-dbs2-log.py --input=$logdir/$file --output=${file}.rfm
    $ROOTSYS/bin/root -b -q $srcdir/"DBSTree.C++(\"${file}.rfm\",\"${file}.root\")"
  done
else  # for service cmst0dbs (no longer needed, changed with deployment of t0 in May 2008)
   /bin/cp $logdir/${service}*${today}*.rfm.gz .
   gunzip *.gz
#
# Make the root tree files
#
#
  for file in `ls ${service}*${today}*.rfm`
  do
    echo "Processing file : $file"
    if [ -f ${file}.root ]
    then
      echo "File $file.root already exists"
    else
      $ROOTSYS/bin/root -b -q $srcdir/"DBSTree.C++(\"$file\",\"$file.root\")"
    fi
  done
fi
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
 $ROOTSYS/bin/root -b -q $srcdir/"DBSTreePlots.C(\"${service}*${today}*.root\",$begin,$end,3600)"
 # move plots into directory with todays date
 if [ ! -d $plotdir/${service}/${today} ];then
   mkdir $plotdir/${service}/${today}
   rm $plotdir/${service}/yesterday
   rm $plotdir/${service}/today
   ln -s $plotdir/${service}/${today} $plotdir/${service}/today
   ln -s $plotdir/${service}/${yesterday} $plotdir/${service}/yesterday
 fi
 cp $srcdir/index.html $plotdir/${service}/${today}
 echo "Copy plots to $plotdir/${service}/${today}"
 cp *.png $plotdir/${service}/${today}
 rm update.txt
 echo "`date`"> update.txt
 cp update.txt $plotdir/${service}/${today}/.
#
#
# Clean up
#
#
  /bin/rm *.rfm
# Done!
#
done
exit
