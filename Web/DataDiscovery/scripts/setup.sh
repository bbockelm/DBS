#!/bin/sh

if [ -z $DLSHOME ]; then
   echo "Please define DLSHOME environment, e.g. /path/COMP/DLS"
   exit 1
fi

if [ -z $DBSHOME ]; then
   echo "Please define DBSHOME environment, e.g. /path/COMP/DBS"
   exit 1
fi

if [ -z $DDHOME ]; then
   echo "Please define DBSHOME environment, e.g. /path/COMP/DBS"
   exit 1
fi

export CVSROOT=:pserver:anonymous@cmscvs.cern.ch:/cvs_server/repositories/CMSSW
export CVSROOT=:kserver:cmscvs.cern.ch:/cvs_server/repositories/CMSSW
export PYTHONPATH=/data/DBSDataDiscovery/install/lib/python2.4/site-packages:$PYTHONPATH
export PYTHONPATH=$DLSHOME/Client/lib:$PYTHONPATH
export PYTHONPATH=$DBSHOME/Clients/PythonAPI/lib:$DBSHOME/Clients/PythonAPI/:$PYTHONPATH
export ORACLE_HOME=/afs/cern.ch/project/oracle/linux/10102gcc323/
export LD_LIBRARY_PATH=/afs/cern.ch/project/oracle/linux/10102gcc323/lib:$LD_LIBRARY_PATH
export DBS_DBPARAM=/data/DBSAccessInfo/DBParam

# LFC setup
. /afs/cern.ch/cms/LCG/LCG-2/UI/cms_ui_env.sh
# DLS setup
#. /data/DLS_Server/RPMInstall/slc3_ia32_gcc323/cms/dls/DLS_0_0_8/etc/profile.d/init.sh
. /data/DLS_Server/CMSRepoRPMInstall/slc3_ia32_gcc323/cms/dls/DLS_0_1_1/etc/profile.d/init.sh

export TNS_ADMIN=$PWD

