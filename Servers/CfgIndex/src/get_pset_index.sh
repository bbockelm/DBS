#!/usr/local/bin/bash
#
# automated loading of DBS Pset Index database
#
# author: Lee Lueking  August 21, 2008
#
# Usage: get_pset_index.sh cmssw-version(ex: 1_8_4) db-pw
# Usage example get_pset_index.sh 1_8_4) the-password
#
# Change the following locations to meet your setup
#
DBS_HOME=~lueking/work/COMP/DBS/Clients/Python
workdir=`pwd`
#srcdir=~lueking/work
scratch=./scratch
basedir=~lueking/work
cfgdir=$basedir/COMP/DBS/Servers/CfgIndex/src
#
# set up symbols
#
vsnnum="$1"
dbpasswd="$2"
version=CMSSW_$vsnnum
if [ ! -d $workdir ]; then mkdir $workdir ;fi
if [ ! -d $scratch ]; then mkdir $scratch ;fi
# setup the cmssw version if it does not alreadyexist
#
cd $workdir
if [ -d $version ];then rm -r $version ;fi
scramv1 project CMSSW $version
cd $version
eval `scramv1 runtime -sh`
#
# Setup DBS
#
cd $DBS_HOME
source setup.sh
#export PYTHONPATH=$DBS_HOME:$PYTHONPATH
#
# Add DBS and cx_Oracle to PYTHONPATH
export PYTHONPATH=$DBS_HOME:$PYTHONPATH:~/install/cx_Oracle/build/lib.linux-x86_64-2.4-10g
export TNS_ADMIN=~/work   # assumes tnsnames.ora in ~/work directory
cd $workdir
#
# Make up the correct suffix
#
vsn=`echo "$vsnnum" | cut -d"_" -f1` #get the major version number
echo "Major version number: $vsn"
if [ "$vsn" == "2" ];then
   suffix="_cfg.py"
   cfg2index="cfg_py2index.py"  #this is used for python config files
else 
   suffix=".cfg"
   cfg2index="cfg2index.py"     #this is used for old config files
fi
#
# get the list of cfg files
#
if [ ! -d $scratch/$version ]; then
 mkdir $scratch/$version
fi
cd $scratch/$version

python $cfgdir/dbsListAlgorithm.py $version $suffix
#
# create the index file and add it to oracle DB
#
cp $cfgdir/$cfg2index .
cp $cfgdir/cfgindex2db_oracle.py .
#
# get the psets and calc the hash
#
if [ -f cfg-list.txt ];then rm cfg-list.txt;touch cfg-list.txt; fi
for file in `ls *$suffix`;do
 echo "$file edmConfigHash $file"
 echo "$file `edmConfigHash $file`" >> cfg-list.txt ;
 #create the index file
 echo "$cfgdir/$cfg2index $file"
 ./$cfg2index $file
 #
 # now add index to oracle
 #
 index_file=`echo $file|sed s/.py/""/`
 python ./cfgindex2db_oracle.py cms_dbs_psetindex_w/${dbpasswd}@CMS_DBS . ${index_file}.index
 #break #temp for testing
done
exit

#
# Done
#
