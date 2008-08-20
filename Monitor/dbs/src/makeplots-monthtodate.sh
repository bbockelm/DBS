#!/usr/local/bin/bash
#
# 1. use existing root tree files 
# 2. Generate plots for the month through the current date
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
month="`date +%b`"
imonth="`date +%m`"
day="`date +%d`"
year="`date +%Y`"
midnight=`date +%s -d"$month $day 00:00:00 CEST $year"` #today midnight
monthdayone=`date +%s -d"$month 1 00:00:00 CEST $year"` #first day of month
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
# Generate the plots and move to web page
# (need to use TChain)
#
# for month to yesterday, midnight
#
 end=`/bin/date +%s -d@$midnight`
 weekago=$(echo "$end - 10*86400" | bc -l)
 begin=$weekago
 echo "monthdayone: $monthdayone, begin: $begin, end: $end, bin: 3600"
 echo "begin: `/bin/date -d@$begin`, end `date -d@$end`"
 $ROOTSYS/bin/root -b -q $srcdir/"DBSTreePlots.C(\"${service}*${year}-${imonth}*.root\",$begin,$end,3600)"
 if [ ! -d "$plotdir/$service/$year$imonth" ]; then
   echo "Directory $plotdir/$service/$year$imonth does not exist, create it"
   mkdir $plotdir/$service/$year$imonth
   cd $plotdir/$service/$year$imonth
   #make link to index.html 
   ln -s $plotdir/index.html index.html
   cd $workdir
 fi

 cp *.png $plotdir/${service}/$year$imonth
 rm monthly-update.txt
 echo "`date`"> monthly-update.txt
 cp monthly-update.txt $plotdir/${service}/$year$imonth/update.txt
 rm $plotdir/${service}/last-10-days
 ln -s $plotdir/${service}/$year$imonth $plotdir/${service}/last-10-days
#
# Done!
#
done
exit
