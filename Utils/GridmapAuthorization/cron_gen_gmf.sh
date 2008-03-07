#!/bin/bash

LOGFILE="cron_"`hostname -s`"_gmf.log"

cd /home/cmsdbs/src/GridMap/GridmapAuthorization

source ./setup.sh
rm -f /tmp/gridmapfile
bash ./vo2gridmap > ./${LOGFILE} 2>&1
if [ -s /tmp/gridmapfile ] ; then
	rm -f ./gridmapfile
	mv /tmp/gridmapfile .
	echo "\"/DC=ch/DC=cern/OU=computers/CN=tier0/lxgate39.cern.ch\" sekhri" >> ./gridmapfile
	chmod 600 /home/cmsdbs/certs/gridmapfile
	cp ./gridmapfile /home/cmsdbs/certs/gridmapfile.new
	mv /home/cmsdbs/certs/gridmapfile.new /home/cmsdbs/certs/gridmapfile
	chmod 400 /home/cmsdbs/certs/gridmapfile
	DPOSTFIX=`date +%Y_%m_%d_%H:%M`
	cp /home/cmsdbs/certs/gridmapfile /home/cmsdbs/src/GridMap/GridmapAuthorization/archive/gridmapfile.$DPOSTFIX
else
	mail -s `hostname`": Gridmafile generation failed" cms-dbs-support@cern.ch < ./${LOGFILE}
	echo "Gridmafile generation failed"
fi




