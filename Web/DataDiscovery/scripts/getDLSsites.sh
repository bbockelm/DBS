#!/bin/bash
cd /data/DBSDataDiscovery/DataDiscoveryDev
. setup.sh

VO=/data/DBSDataDiscovery/DataDiscovery/vomses
voms-proxy-init -vomses $VO/cms-lcg-voms.cern.ch -cert $VO/.globus/usercert.pem \
-key $VO/.globus/userkey.pem -voms cms -q

DIR=$PWD/COMP/DLS/Client/lib

rm -f $PWD/dls.all.tmp
# Global DLS
$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/LFC \
    2> /dev/null | sort -u| awk '{print "MCGlobal/Writer "$0""}' > $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS Global, status=$status"
exit 1
fi

# Local DLS's
$DIR/dls-get-all-locations -i DLS_TYPE_MYSQL -e lxgate10.cern.ch:18081 \
    2> /dev/null | sort -u| awk '{print "MCLocal_1/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ] && [ $status -ne 255 ]; then
echo "Error, while retrieving DLS sites from DLS MCLocal_1, status=$status"
exit 1
fi

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_2 \
    2>/dev/null | sort -u| awk '{print "MCLocal_2/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS MCLocal_2, status=$status"
exit 1
fi

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_3 \
    2>/dev/null | sort -u| awk '{print "MCLocal_3/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS MCLocal_3, status=$status"
exit 1
fi

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_4 \
    2>/dev/null | sort -u| awk '{print "MCLocal_4/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS MCLocal_4, status=$status"
exit 1
fi

cat $PWD/dls.all.tmp | sort -u > $PWD/dls.all

