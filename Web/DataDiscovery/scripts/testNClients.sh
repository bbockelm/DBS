#!/bin/bash

if [ $# -ne 1 ]; then
   echo "\nUsage: testNClients.sh N, where N is a number of clients\n"
   exit
fi
nClients=$1

COUNTER=0
rm -f testNClients.log

while [  $COUNTER -lt $nClients ]; do
 echo Request $COUNTER
 $DDHOME/dd.py --input="find run where dataset like *" \
               --host=https://cmsweb.cern.ch/dbs_discovery_test/ \
               --iface=dbsapi --xml | \
        2>&1 1>> testNClients.log < /dev/null &
 let COUNTER=COUNTER+1 
done

echo "Check results in testNClients.log"
