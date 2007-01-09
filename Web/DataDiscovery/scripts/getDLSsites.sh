#!/bin/sh
# setup environment
. `dirname $0`/setup.sh 2>&1 1>& /dev/null 

# run actual script
cd $DBSHOME/Web/DataDiscovery
python `dirname $0`/getDLSsites.py $*
