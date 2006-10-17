#!/bin/bash
cd $DDHOME
. scripts/setup.sh

#VO=$DDHOME/vomses
#voms-proxy-init -vomses $VO/cms-lcg-voms.cern.ch -cert $VO/.globus/usercert.pem \
#-key $VO/.globus/userkey.pem -voms cms -q 2> /dev/null
#status=$?
#if [ $status -ne 0 ]; then
#echo "Error, while retrieving grid credentials, status=$status"
#exit 1
#fi
#cat $DDHOME/pp.txt | grid-proxy-init -pwstdin
cat $HOME/.globus/pp.txt | grid-proxy-init -pwstdin -q
#cat $DDHOME/pp.txt | voms-proxy-init -voms cms -pwstdin -q

DIR=$DLSHOME/Client/lib

rm -f $PWD/dls.all.tmp
# Global DLS
$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/LFC \
    2> /dev/null | sort -u| awk '{print "MCGlobal/Writer "$0"\nDev/Writer "$0""}' > $PWD/dls.all.tmp
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

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_5 \
    2>/dev/null | sort -u| awk '{print "MCLocal_5/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS MCLocal_5, status=$status"
exit 1
fi

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_6 \
    2>/dev/null | sort -u| awk '{print "MCLocal_6/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS MCLocal_6, status=$status"
exit 1
fi

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_7 \
    2>/dev/null | sort -u| awk '{print "MCLocal_7/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS MCLocal_7, status=$status"
exit 1
fi

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/MCLocal_Test \
    2>/dev/null | sort -u| awk '{print "DevMC/Writer "$0"\nDev/fanfani "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS DevMC, status=$status"
exit 1
fi

$DIR/dls-get-all-locations -i DLS_TYPE_LFC -e prod-lfc-cms-central.cern.ch/grid/cms/DLS/RelVal \
    2>/dev/null | sort -u| awk '{print "RelVal/Writer "$0""}' >> $PWD/dls.all.tmp
status=$?
if [ $status -ne 0 ]; then
echo "Error, while retrieving DLS sites from DLS RelVal, status=$status"
exit 1
fi

cat $PWD/dls.all.tmp | sort -u > $PWD/dls.all

