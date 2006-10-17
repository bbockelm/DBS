#!/bin/sh
cd $DDHOME
. scripts/setup.sh

# Generate dict for global DBS instances
rm -f dbsDict.global.tmp
./DBSHelper.py --dict=Global 
if [ $? -eq 0 ] && [ -s dbsDict.global.tmp ]; then
mv -f dbsDict.global.tmp dbsDict.global
else
exit 1
fi

# generate dict for all DBS instances
rm -f dbsDict.all.tmp
./DBSHelper.py --dict=all 
if [ $? -eq 0 ] && [ -s dbsDict.all.tmp ]; then
mv -f dbsDict.all.tmp dbsDict.all
else
exit 1
fi
