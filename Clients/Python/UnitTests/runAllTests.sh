#!/bin/sh
rm -f $PWD/result.txt
touch $PWD/result.txt
cd ..
source setup.sh
cd -
echo "Please wait ... The tests can take up to 5 minutes"
python dbsInsertUnitTests.py > /dev/null
python dbsListUnitTests.py   > /dev/null
echo "Test results are written in $PWD/result.txt"
echo ""
message=`cat result.txt | grep FAILED`
len=${#message}
if [ "$len" -lt "1" ] ; then
	echo "All tests PASSED OK. For more details look in the result.txt file"
else
	echo "$message"
fi
