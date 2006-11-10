#!/bin/sh
#
all=`ls -1 *.py`
for afile in $all; do
   echo "Profiling $afile"
   time python $afile > /dev/null
done;
#     

