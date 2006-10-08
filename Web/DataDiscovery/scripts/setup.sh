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

export CVSROOT=:pserver:anonymous@cmscvs.cern.ch:/cvs_server/repositories/CMSSW
export CVSROOT=:kserver:cmscvs.cern.ch:/cvs_server/repositories/CMSSW
ver=`python -V 2>&1 | awk '{split($2,a,"."); print ""a[1]"."a[2]""}'`
export PYTHONPATH=/data/DBSDataDiscovery/install/lib/python${ver}/site-packages:$PYTHONPATH
export PYTHONPATH=$DLSHOME/Client/lib:$PYTHONPATH
export PYTHONPATH=$DBSHOME/Clients/PythonAPI/lib:$DBSHOME/Clients/PythonAPI/:$PYTHONPATH
export ORACLE_HOME=/afs/cern.ch/project/oracle/linux/10102gcc323/
export LD_LIBRARY_PATH=/afs/cern.ch/project/oracle/linux/10102gcc323/lib:$LD_LIBRARY_PATH
export DBS_DBPARAM=/data/DBSAccessInfo/DBParam

# LFC setup
. /afs/cern.ch/cms/LCG/LCG-2/UI/cms_ui_env.sh
# DLS setup
#. /data/DLS_Server/RPMInstall/slc3_ia32_gcc323/cms/dls/DLS_0_0_8/etc/profile.d/init.sh
DLSSERVER='/data/DLS_Server/CMSRepoRPMInstall/slc3_ia32_gcc323/cms/dls/DLS_0_1_1/'
if [ -d $DLSSERVER/etc/profile.d/ ]; then
. $DLSSERVER/etc/profile.d/init.sh
fi

export TNS_ADMIN=$DDHOME


