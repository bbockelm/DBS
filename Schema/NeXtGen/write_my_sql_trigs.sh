#!/bin/sh
table_list=`cat DBS-NeXtGen-MySQL_DEPLOYABLE.sql|grep "CREATE TABLE" | awk '{print $3}'`
for atable in $table_list; do
   echo 
   echo "CREATE TRIGGER TR_TS_${atable} BEFORE INSERT ON ${atable}"
   echo "FOR EACH ROW SET NEW.CreationDate = NOW();"
done


