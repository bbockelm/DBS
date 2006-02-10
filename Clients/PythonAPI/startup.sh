#!/bin/sh
python dbsWebServices.py --config-file=dbs_web_services_config &
if [ $? -eq 0 ]; then
   echo "DBS SERVER STARTED"
else
   echo "DBS SERVER FAILED TO START"
fi

