#!/bin/sh 
#
# 15 Feb, 2006: Adapted by L. Lueking  from S. Veseli's SAM tests
#
# usage: ./run_cgi_client.sh nclients outputSuffix dspath
# example: ./run_cgi_client 10 test /a/b/c/d
nClients=$1
outputSuffix=$2
dspath=$3
nCalls=$4

# cft% : 5010 results
# sam% : 1359
cnt=0
echo "Using suffix: $outputSuffix"
echo "N clients: $nClients"
while [ "$cnt" != "$nClients" ]; do
  cnt=`expr $cnt + 1`
  outputFile=cgi_client.${cnt}.${outputSuffix}.out 
  cmd="./cgiLoadTest.py --dspath='$dspath' --n-calls=$nCalls --n-term=1 > $outputFile 2>&1 &"
  echo "Executing: $cmd"
  eval $cmd
done


# Allow for clients to be initialized
while true; do
  attemptStr=`grep Attempt $outputFile`
  if [ "$attemptStr" != "" ]; then
    break
  fi
  sleep 1
done
startTime=`date +%s`
echo "Start time: $startTime"
cnt=0
while [ "$cnt" != "$nClients" ]; do
  cnt=`expr $cnt + 1`
  echo "Waiting for child #$cnt"
  wait 
done
endTime=`date +%s`
echo "End time: $endTime"
diff=`expr $endTime - $startTime`
echo "Time difference: $diff"
averageTime=`echo "$nClients $nCalls $diff" | awk '{print $3/($1 * $2)}'`
averagePyTime=`grep "Average Successful Call Duration" $outputFile | awk '{print $(NF-1)}'`
minPyTime=`grep "Minimum Call Duration" $outputFile | awk '{print $(NF-1)}'`
maxPyTime=`grep "Maximum Call Duration" $outputFile | awk '{print $(NF-1)}'`
echo "Minimum Call Time (Python):  $minPyTime"
echo "Maximum Call Time (Python):  $maxPyTime"
echo "Number of Clients/Average Call Time (Python): $nClients $averagePyTime"
echo "Number of Clients/Average Call Time: $nClients $averageTime"





