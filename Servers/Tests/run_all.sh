#!/bin/sh
#
# 15, Feb, 2006: Adapted by L. Lueking from 
#                S. Veseli's tests for SAM web services
#
# usage: ./run_all.sh nclientsStart nclientsMax
#    ex: ./run_all.sh 1 10
# 
n=$1
nMax=$2
suffix="a"
dspath="/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
nTimes=1
resultFile=all_results.out
touch $resultFile
while [ "$n" != "${nMax}" ]; do
  n=`expr $n + 1`
  echo "Running WS tests with $n clients"
  outputFile="ws_results.${n}.out"
  ./run_ws_client.sh $n $suffix $dspath $nTimes > $outputFile 2>&1
  wsAve=`tail -1 $outputFile | awk '{print $NF}'`
  
  echo "Running CGI tests with $n clients"
  outputFile="cgi_results.${n}.out"
  ./run_cgi_client.sh $n $suffix $dspath $nTimes > $outputFile 2>&1
  cgiAve=`tail -1 $outputFile | awk '{print $NF}'`

  echo "$n $cgiAve $wsAve" 
  echo "$n $cgiAve $wsAve" >> $resultFile
  echo
done
