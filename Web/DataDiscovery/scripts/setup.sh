#!/bin/sh

if [ -z $DDHOME ] ; then
   export DDHOME=$PWD
   echo "Set DDHOME='$DDHOME'"
fi
if [ -z $DBSHOME ] ; then
   echo "No DBSHOME environment found, will use $DDHOME/../../../DBS"
   export DBSHOME=$DDHOME/../../../DBS
fi
if [ -z $DLSHOME ] ; then
   echo "No DBSHOME environment found, will use $DDHOME/../../../DLS"
   export DLSHOME=$DDHOME/../../../DLS
fi
if [ -z $DLSHOME ]; then
   echo "Please define DLSHOME environment, e.g. /path/COMP/DLS"
   exit
fi

if [ -z $DBSHOME ]; then
   echo "Please define DBSHOME environment, e.g. /path/COMP/DBS"
   exit
fi

if [ -z $DDHOME ]; then
   echo "Please define DBSHOME environment, e.g. /path/COMP/DBS"
   exit
fi

ver=`python -V 2>&1 | awk '{split($2,a,"."); print ""a[1]"."a[2]""}'`

# LFC setup, CERN screw up and I need to invoke upfront, but after I determine
# python version, since afs setup python2.2. and I need to put it at the end of the path
if [ `uname -s` == 'Linux' ]; then
   h=`hostname -f | gawk '{z=split($1,a,"."); print ""a[z-1]"."a[z]""}'`
else
   h=`hostname | gawk '{z=split($1,a,"."); print ""a[z-1]"."a[z]""}'`
fi
if [ $h == 'cern.ch' ]; then
#   . /afs/cern.ch/cms/LCG/LCG-2/UI/cms_ui_env.sh
   . /afs/cern.ch/project/gd/LCG-share/3.0.0/etc/profile.d/grid_env.sh
fi

# DLS setup
#. /data/DLS_Server/RPMInstall/slc3_ia32_gcc323/cms/dls/DLS_0_0_8/etc/profile.d/init.sh
DLSSERVER='/data/DLS_Server/CMSRepoRPMInstall/slc3_ia32_gcc323/cms/dls/DLS_0_1_1/'
if [ -d $DLSSERVER/etc/profile.d/ ]; then
. $DLSSERVER/etc/profile.d/init.sh
fi

if [ -d /usr/local/lib/python${ver} ]; then
   export PYTHONPATH=/usr/local/lib/python${ver}:$PYTHONPATH
fi

export PYTHONPATH=/data/DBSDataDiscovery/install/lib/python${ver}/site-packages:$PYTHONPATH
export PYTHONPATH=$DLSHOME/Client/lib:$PYTHONPATH
export PYTHONPATH=$DBSHOME/Web/DataDiscovery:$PYTHONPATH
export PYTHONPATH=$DBSHOME/Clients/Python/:$PYTHONPATH
#export PYTHONPATH=$DBSHOME/Clients/PythonAPI/lib:$DBSHOME/Clients/PythonAPI/:$PYTHONPATH

export CVSROOT=:pserver:anonymous@cmscvs.cern.ch:/cvs_server/repositories/CMSSW
export CVSROOT=:kserver:cmscvs.cern.ch:/cvs_server/repositories/CMSSW

export ORACLE_HOME=/afs/cern.ch/project/oracle/linux/10102gcc323/
export LD_LIBRARY_PATH=/afs/cern.ch/project/oracle/linux/10102gcc323/lib:$LD_LIBRARY_PATH
export DBS_DBPARAM=/data/DBSAccessInfo/DBParam
export TNS_ADMIN=$DDHOME


