#!/bin/sh
export PATH=$PWD:$PATH
cmd=DDSearchCLI.py
host=https://cmsweb.cern.ch/dbs_discovery_test
#host=https://cmsweb.cern.ch/dbs_discovery_new
#host=https://cmsweb.cern.ch/dbs_discovery
echo
echo "Using host=$host"
echo
echo "### TEST plain text output ###"
for input in \
"*QCD*" \
"dataset like *Online* and site=srm.cern.ch " \
"find path where path like *" \
"find path where run between 34850-36000 or run in 34850,34890" \
"find run where path like *Online*" \
"find primds where (path like *Online* or path not like *RelVal* ) and release> CMSSW_1_7 " \
"find file where run=38420" \
"find file,run where dataset=/Commissioning2008Ecal-A/Online/RAW" \
"find total(file),run where dataset=/Commissioning2008Ecal-A/Online/RAW" \
"find dataset where site like T2_*" \
"find dataset where site like T2_UK*"
do
    echo "input=\"$input\""
    $cmd --host=$host --input="$input"
done

echo "### TEST xml output ###"
for input in \
"find dataset where dataset like *" \
"find file,run where dataset=/Commissioning2008Ecal-A/Online/RAW" \
"find file,total(run) where dataset=/Commissioning2008Ecal-A/Online/RAW" \
"find file,lumi where dataset=/GlobalMar08-Express/Online/RAW"
do
    echo "input=\"$input\""
    $cmd --host=$host --input="$input" --details --xml
done

echo "### TEST xml output ###"
for input in \
"find file,lumi where dataset=/GlobalMar08-Express/Online/RAW and file=/store/data/GlobalMar08/Express/000/038/341/GlobalMar08.00038341.0011.Express.storageManager.0.0001.dat"
do
    echo "input=\"$input\""
    $cmd --host=$host --input="$input" --details --xml --limit=-1
done
