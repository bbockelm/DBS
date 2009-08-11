#!/bin/bash

LOGFILE="cron_"`hostname -s`"_gmf.log"

cd /home/cmsdbs/GridmapAuthorization

source ./setup.sh
#MAPFILES=("gridmapfile_PRODG_writer" "gridmapfile_PRODL_writer" "gridmapfile_ANALYSIS_writer" "gridmapfile_PRODG_admin" "gridmapfile_PRODL_admin" "gridmapfile_ANALYSIS_admin" "gridmapfile_T0_writer" "gridmapfile_T0_admin")

rm -f /tmp/gridmapfile_*
bash ./vo2gridmap_cmst0dbs > ./${LOGFILE} 2>&1

TO=cms-dbs-support@cern.ch


MAPFILES=( "gridmapfile_T0_writer" "gridmapfile_T0_admin" )
for i in 0 1 
do 
	if [ -s /tmp/${MAPFILES[i]} ] ; then
	        rm -f ./${MAPFILES[i]} 
	        mv /tmp/${MAPFILES[i]}  .
	        echo "\"/DC=ch/DC=cern/OU=computers/CN=tier0/lxgate39.cern.ch\" sekhri" >> ./${MAPFILES[i]} 
	        echo "\"/DC=ch/DC=cern/OU=computers/CN=tier0/vocms13.cern.ch\" sekhri" >> ./${MAPFILES[i]} 
		echo "\"/DC=ch/DC=cern/OU=computers/CN=vocms39.cern.ch\" sekhri" >> ./${MAPFILES[i]}
	        chmod 600 /home/cmsdbs/certs/${MAPFILES[i]} 
	        cp ./${MAPFILES[i]}  /home/cmsdbs/certs/${MAPFILES[i]}.new
	        mv /home/cmsdbs/certs/${MAPFILES[i]}.new /home/cmsdbs/certs/${MAPFILES[i]} 
	        chmod 400 /home/cmsdbs/certs/${MAPFILES[i]} 
	        DPOSTFIX=`date +%Y_%m_%d_%H:%M`
	        cp /home/cmsdbs/certs/${MAPFILES[i]}  /home/cmsdbs/GridmapAuthorization/archive/${MAPFILES[i]}.$DPOSTFIX
	else
        #mail -s `hostname`": Gridmafile generation failed" cms-dbs-support@cern.ch < ./${LOGFILE}
        #use sendmail to set a different "From":
		TO="cms-dbs-support@cern.ch"
	        (echo "From: cmsdbs `basename $0` <cmsdbs@mail.cern.ch>"
	        echo "To: ${TO}"
	        echo "Subject: `hostname`: ${MAPFILES[i]}  generation failed"
	        echo
	        cat ${LOGFILE})|/usr/lib/sendmail ${TO}
	        echo "Gridmapfile generation failed"
	fi
done


