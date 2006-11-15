#!/bin/sh
cd ..
source setup.sh
cd -
python dbsListUnitTests.py > /dev/null
echo "Test results are written in $PWD/result.txt"
echo ""
cat result.txt | grep FAILED
