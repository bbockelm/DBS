#!/bin/bash
export PATH=$PWD:$PATH
cmd=$DDHOME/tools/DDSearchCLI.py
if [ $# -eq 0 ]; then
   echo -e "\nUsage: testASearch.sh prod|test|new|testbed\n"
   echo -e "If testbed is used you MUST provide its URL"
   exit
fi
if [ "${1}" == "testbed" ]; then
   if [ -z ${2} ]; then
      echo -e "If testbed is used you MUST provide its URL"
      exit
   fi
   host=${2}
elif [ "${1}" == "prod" ]; then
   host=https://cmsweb.cern.ch/dbs_discovery
else
   host=https://cmsweb.cern.ch/dbs_discovery_$1
fi
#echo
#echo "Using host=$host"
#echo
#echo "### TEST plain text output ###"
#for input in \
#"*QCD*" \
#"dataset like *Online* and site=srm.cern.ch " \
#"find dataset where dataset like *" \
#"find dataset where run between 34850-36000 or run in 34850,34890" \
#"find run where dataset like *Online*" \
#"find primds where (path like *Online* or dataset not like *RelVal* ) and release> CMSSW_1_7 " \
#"find file where run=38420" \
#"find file,run where dataset=/Commissioning2008Ecal-A/Online/RAW" \
#"find total(file),run where dataset=/Commissioning2008Ecal-A/Online/RAW" \
#"find dataset where site like T2_*" \
#"find dataset where site like T2_UK*" \
#"find release where release>CMSSW_1_6_7" \
#"find file where release=CMSSW_1_6_7 and site=T2_UK"
#do
#    echo "input=\"$input\""
#    $cmd --host=$host --input="$input" --iface=dd
#    echo -e '\E[47;32m'"\033[1mABOVE TEST SHOULD PASS\033[0m"
#    echo
#done

#echo "### TEST xml output ###"
#for input in \
#"find dataset where dataset like *" \
#"find file,run where dataset=/Commissioning2008Ecal-A/Online/RAW" \
#"find file,total(run) where dataset=/Commissioning2008Ecal-A/Online/RAW" \
#"find file,lumi where dataset=/GlobalMar08-Express/Online/RAW" \
#"find run,run.numevents,run.numlumi,run.totlumi,run.createby,run.createdate where dataset=/GlobalMar08-Express/Online/RAW"
#do
#    echo "input=\"$input\""
#    $cmd --host=$host --input="$input" --iface=dd --details --xml
#    echo -e '\E[47;32m'"\033[1mABOVE TEST SHOULD PASS\033[0m"
#    echo
#done

#echo "### TEST cff output ###"
#for input in \
#"find file where release=CMSSW_1_6_7"
#do
#    echo "input=\"$input\""
#    $cmd --host=$host --input="$input" --iface=dd --cff
#    echo -e '\E[47;32m'"\033[1mABOVE TEST SHOULD PASS\033[0m"
#    echo
#done

#echo "### TEST xml output ###"
#for input in \
#"find file,lumi where dataset=/GlobalMar08-Express/Online/RAW and file=/store/data/GlobalMar08/Express/000/038/341/GlobalMar08.00038341.0011.Express.storageManager.0.0001.dat"
#do
#    echo "input=\"$input\""
#    $cmd --host=$host --input="$input" --iface=dd --details --xml --limit=-1
#    echo -e '\E[47;32m'"\033[1mABOVE TEST SHOULD PASS\033[0m"
#    echo
#done

echo "### TEST DBS-QL using DBSAPI ###"
for input in \
"find primds where primds.createdate > 2007-04-20" \
"find dataset where site like T2_UK*"  \
"*QCD*"
do
    echo "input=\"$input\""
    $cmd --host=$host --input="$input" --iface=dbsapi
    echo -e '\E[47;32m'"\033[1mABOVE TEST SHOULD PASS\033[0m"
    echo
done

input="find dataset where dataset like *"
dbsList=`cat $DDHOME/DBParam  | grep Section | grep -v "#" | awk '{print $2}'`
for dbsInst in $dbsList
do
    echo "TEST for $dbsInst"
    $cmd --host=$host --input="$input" --iface=dbsapi --dbsInst=$dbsInst
    echo -e '\E[47;32m'"\033[1mABOVE TEST SHOULD PASS\033[0m"
    echo
done
