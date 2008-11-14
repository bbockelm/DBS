#!/bin/bash

if [ $# -eq 0 ]; then
   echo -e "Usage: testNClients.sh N <prod|test>, where N is a number of clients"
   exit
fi
nClients=$1
if [ "${2}" == "testbed" ]; then
   if [ -z ${3} ]; then
      echo -e "If testbed is used you MUST provide its URL"
      exit
   fi
   host=${3}
elif [ "${2}" == "prod" ]; then
   host=https://cmsweb.cern.ch/dbs_discovery
else
   host=https://cmsweb.cern.ch/dbs_discovery_$2
fi
echo -e "Using host: $host"

COUNTER=0
rm -f testNClients.log
touch testNClients.log

while [  $COUNTER -lt $nClients ]; do
 echo Request $COUNTER
 $DDHOME/tools/DDSearchCLI.py \
               --input="find site where site like *" \
               --host=${host} \
               --iface=dbsapi \
        2>&1 1>> testNClients.log < /dev/null &
 $DDHOME/tools/DDSearchCLI.py \
               --input="find site where site like *" \
               --host=${host} \
               --iface=dbsapi \
               --dbsInst=cms_dbs_ph_analysis_01 \
        2>&1 1>> testNClients.log < /dev/null &
 $DDHOME/tools/DDSearchCLI.py \
               --input="find site where site like *" \
               --host=${host} \
               --iface=dbsapi \
               --dbsInst=cms_dbs_caf_analysis_01 \
        2>&1 1>> testNClients.log < /dev/null &
 let COUNTER=COUNTER+1 
done

echo "Check results in testNClients.log"
