#!/bin/sh

# setup environment
. `dirname $0`/setup_dbsenv.sh

listFiles='CheetahDBSTemplate.py DBSAuth.py DBSDataDiscoveryServer.py DBSHelper.py DBSInst.py DBSOptions.py DBSUtil.py'

epydoc --check -o Documentation -n "DBS Data Discovery API" $listFiles
rm -rf pdf
epydoc -o pdf --pdf -n "DBS Data Discovery API" $listFiles
epydoc -o Documentation -n "DBS Data Discovery API" $listFiles
mv -f pdf/api.pdf Documentation
rm -rf pdf
