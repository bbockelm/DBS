#!/bin/sh
rm -f $PWD/result.txt
touch $PWD/result.txt
cd ..
source setup.sh
cd -
echo "Please wait ... The tests can take up to 5 minutes"
python dbsInsertUnitTests.py --verbose=2 > /dev/null
if [ $? -eq 0 ] ; then 
   python dbsListUnitTests.py --verbose=2 > /dev/null 
else
   echo "dbsInsertUnitTests.py test cases probably FAILED"
   echo "Check result.txt for details"
   exit 0
fi
echo "Test results are written in $PWD/result.txt"
echo ""
message=`cat result.txt | grep FAILED`
len=${#message}
if [ "$len" -lt "1" ] ; then
	echo "All tests PASSED OK. For more details look in the result.txt file"
else
	echo "$message"
fi

