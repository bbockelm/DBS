#!/bin/sh
export PATH=$PWD:$PATH
cmd=DDSearchCLI.py
host=https://cmsweb.cern.ch/dbs_discovery_test
#host=https://cmsweb.cern.ch/dbs_discovery
echo "### TEST plain text output ###"
for input in \
"*QCD*" \
"dataset like *Online* and site=srm.cern.ch " \
"find path where path like *" \
"find path where run between 34850-36000 or run in 34850,34890" \
"find run where path like *Online*" \
"find primds where (path like *Online* or path not like *RelVal* ) and release> CMSSW_1_7 " \
"find file where run=38420" \
"find file,run where path=/Commissioning2008Ecal-A/Online/RAW" \
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
"find file,total(run) where dataset=/Commissioning2008Ecal-A/Online/RAW"
do
    echo "input=\"$input\""
    $cmd --host=$host --input="$input" --details --xml
done
