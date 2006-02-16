#!/bin/sh
noOfProcess=$1
if [ -n "$noOfProcess" ]
then
	for i in `seq 1 $noOfProcess`
	do 
		python runTestCases.py | grep "TIME ELAPSED" &
		echo "Forked process $i succesfully"
	done
else
	echo "Please give the number of clients processes to be forked"
	echo "Example ./forkClients.sh 5"
fi
echo ""

