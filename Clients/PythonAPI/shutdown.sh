#!/bin/sh
if [ -f .dbsProcessIDFile ]; then
  kill -9 `cat .dbsProcessIDFile` > /dev/null
  rm -f .dbsProcessIDFile
  echo "DBS SERVER STOPPED"
else
  echo "DBS SERVER NOT FOUND" 
fi
