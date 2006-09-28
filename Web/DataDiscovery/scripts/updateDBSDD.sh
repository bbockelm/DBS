#!/bin/sh
cd /data/DBSDataDiscovery/DataDiscoveryDev
. setup.sh

# Generate dict for global DBS instances
rm -f dbsDict.global.tmp
./DBSHelper.py --dict=Global > dbsDict.global.tmp
if [ $? -eq 0 ]; then
mv -f dbsDict.global.tmp dbsDict.global
else
exit 1
fi

# generate dict for all DBS instances
rm -f dbsDict.all.tmp
./DBSHelper.py --dict=all > dbsDict.all.tmp
if [ $? -eq 0 ]; then
mv -f dbsDict.all.tmp dbsDict.all
else
exit 1
fi
