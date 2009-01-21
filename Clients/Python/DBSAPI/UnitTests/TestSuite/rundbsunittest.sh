#!/bin/sh

logfile="unittest.output"
python DbsUnitTest.py $logfile
echo "Check the $logfile to see the output."
