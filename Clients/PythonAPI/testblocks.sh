#!/bin/sh

TIMEFORMAT="Timing information in seconds: %R real, %U user, %S system, %P %%"

echo "*** Testing timing of individual runs"
for i in 0 1 2 3 4 5 6 7 8 9 10; do
 echo -n "* Run #$i start: "; date
 time python dbsCgiTestBlocks.py > TEST_BLOCKS_OUT_$i.txt
 echo -n "* Run #$i end: "; date
done

echo
echo "*** Testing timing of concurrent runs"
date
for i in 0 1 2 3 4; do time python dbsCgiTestBlocks.py > TEST_BLOCKS_OUT_P$i.txt & done; wait
date
