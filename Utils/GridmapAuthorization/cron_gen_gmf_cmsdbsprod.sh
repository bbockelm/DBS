#!/bin/bash

LOGFILE="cron_"`hostname -s`"_gmf.log"
APP_DIR=/home/cmsdbs/src/GridMap/GridmapAuthorization
cd $APP_DIR

source ./setup.sh
#MAPFILES=("gridmapfile_PRODG_writer" "gridmapfile_PRODL_writer" "gridmapfile_ANALYSIS_writer" "gridmapfile_PRODG_admin" "gridmapfile_PRODL_admin" "gridmapfile_ANALYSIS_admin" "gridmapfile_T0_writer" "gridmapfile_T0_admin")

rm -f /tmp/gridmapfile_*
bash ./vo2gridmap_cmsdbsprod > ./${LOGFILE} 2>&1

TO=cms-dbs-support@cern.ch

MAPFILE="gridmapfile_PRODG_writer"
if [ -s /tmp/${MAPFILE} ] ; then
        rm -f ./${MAPFILE} 
        mv /tmp/${MAPFILE}  .
        echo "\"/DC=ch/DC=cern/OU=computers/CN=tier0/lxgate39.cern.ch\" sekhri" >> ./${MAPFILE} 
        echo "\"/DC=ch/DC=cern/OU=computers/CN=pccmsdqm04.cern.ch\" sekhri" >> ./${MAPFILE} 
        echo "\"/DC=ch/DC=cern/OU=computers/CN=tier0/vocms13.cern.ch\" sekhri" >> ./${MAPFILE} 
	echo "\"/DC=ch/DC=cern/OU=computers/CN=vocms39.cern.ch\" sekhri" >> ./${MAPFILE}
        chmod 600 /home/cmsdbs/certs/${MAPFILE} 
        cp ./${MAPFILE}  /home/cmsdbs/certs/${MAPFILE}.new
        mv /home/cmsdbs/certs/${MAPFILE}.new /home/cmsdbs/certs/${MAPFILE} 
        chmod 400 /home/cmsdbs/certs/${MAPFILE} 
        DPOSTFIX=`date +%Y_%m_%d_%H:%M`
        cp /home/cmsdbs/certs/${MAPFILE}  $APP_DIR/archive/${MAPFILE}.$DPOSTFIX
else
        #mail -s `hostname`": Gridmafile generation failed" cms-dbs-support@cern.ch < ./${LOGFILE}
        #use sendmail to set a different "From":
	TO="cms-dbs-support@cern.ch"
        (echo "From: cmsdbs `basename $0` <cmsdbs@mail.cern.ch>"
        echo "To: ${TO}"
        echo "Subject: `hostname`: ${MAPFILE}  generation failed"
        echo
        cat ${LOGFILE})|/usr/lib/sendmail  ${TO}
        echo "Gridmapfile generation failed"
fi

MAPFILES=("gridmapfile_PRODL_writer"  "gridmapfile_PRODG_admin" "gridmapfile_PRODL_admin" "gridmapfile_ANALYSIS_admin" )
for index in 0 1 2 3 
do 
	if [ -s /tmp/${MAPFILES[index]} ] ; then
	        rm -f ./${MAPFILES[index]} 
        	mv /tmp/${MAPFILES[index]}  .
	        chmod 600 /home/cmsdbs/certs/${MAPFILES[index]} 
        	cp ./${MAPFILES[index]}  /home/cmsdbs/certs/${MAPFILES[index]}.new
	        mv /home/cmsdbs/certs/${MAPFILES[index]}.new /home/cmsdbs/certs/${MAPFILES[index]} 
	        chmod 400 /home/cmsdbs/certs/${MAPFILES[index]} 
	        DPOSTFIX=`date +%Y_%m_%d_%H:%M`
	        cp /home/cmsdbs/certs/${MAPFILES[index]}  $APP_DIR/archive/${MAPFILES[index]}.$DPOSTFIX
	else
        #mail -s `hostname`": Gridmafile generation failed" cms-dbs-support@cern.ch < ./${LOGFILE}
        #use sendmail to set a different "From":
		TO="cms-dbs-support@cern.ch"
	       	(echo "From: cmsdbs `basename $0` <cmsdbs@mail.cern.ch>"
	        echo "To: ${TO}"
	   	echo "Subject: `hostname`: ${MAPFILES[index]}  generation failed"
	       	echo
	       	cat ${LOGFILE})|/usr/lib/sendmail  ${TO}
	       	echo "Gridmapfile generation failed"
	fi
done

MAPFILE="gridmapfile_ANALYSIS_writer"
if [ -s /tmp/${MAPFILE} ] ; then
        rm -f ./${MAPFILE}
        mv /tmp/${MAPFILE}  .
        echo "\"/DC=ch/DC=cern/OU=computers/CN=vocms39.cern.ch\" sekhri" >> ./${MAPFILE}
        chmod 600 /home/cmsdbs/certs/${MAPFILE}
        cp ./${MAPFILE}  /home/cmsdbs/certs/${MAPFILE}.new
        mv /home/cmsdbs/certs/${MAPFILE}.new /home/cmsdbs/certs/${MAPFILE}
        chmod 400 /home/cmsdbs/certs/${MAPFILE}
        DPOSTFIX=`date +%Y_%m_%d_%H:%M`
        cp /home/cmsdbs/certs/${MAPFILE}  $APP_DIR/archive/${MAPFILE}.$DPOSTFIX
else
        #mail -s `hostname`": Gridmafile generation failed" cms-dbs-support@cern.ch < ./${LOGFILE}
        #use sendmail to set a different "From":
        TO="cms-dbs-support@cern.ch"
        (echo "From: cmsdbs `basename $0` <cmsdbs@mail.cern.ch>"
        echo "To: ${TO}"
        echo "Subject: `hostname`: ${MAPFILE}  generation failed"
        echo
        cat ${LOGFILE})|/usr/lib/sendmail  ${TO}
        echo "Gridmapfile generation failed"
fi

