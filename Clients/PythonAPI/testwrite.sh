#!/bin/sh

instance=Dev/lat
export TIMEFORMAT="Timing information in seconds: %R real, %U user, %S system, %P %%"

source /afs/cern.ch/project/oracle/script/setoraenv.sh -s 10201
for size in 1:1000 10:1000 100:1000 1000:1000; do # 1:10000 10:10000; do
  ops=$(echo $size | sed 's/:.*//')
  num=$(echo $size | sed 's/.*://')

  # Reset database
  (cd ../../Schema/Production;
    sqlplus -S $(PSchema/OracleConnectId -db PSchema/DBParam:$instance) @PSchema/OracleReset.sql </dev/null
    sqlplus -S $(PSchema/OracleConnectId -db PSchema/DBParam:$instance) @Oracle.sql </dev/null
    sqlplus -S $(PSchema/OracleConnectId -db PSchema/DBParam:$instance) @PSchema/OracleStatsEnable.sql </dev/null
    sqlplus -S $(PSchema/OracleConnectId -db PSchema/DBParam:$instance) @PSchema/OracleStatsUpdate.sql </dev/null)
 
  rm -f TEST_WRITE_BIG_SLC4_${ops}_${num}_[123].txt
  for round in 1 2 3; do
    (cd ../../Schema/Production;
      sqlplus -S $(PSchema/OracleConnectId -db PSchema/DBParam:$instance) @PSchema/OracleStatsUpdate.sql </dev/null)
 
    for i in 0 1 2 3 4 5 6 7 8 9; do
       echo "* TEST $ops $num $i $round"
       time python -u dbsCgiTestWriteBig.py $instance $ops $num >> TEST_WRITE_BIG_SLC4_${ops}_${num}_${round}.txt 2>&1
    done
  done
done
