#!/bin/sh

calculateAverage() 
{
   # $1: First argument is number of parallel clients
   # $2: second argument is the code needs to be run
   # $3: number of files
   # $4: number of iterations
   # generates output in $2.$3.$4.timelog.txt
   # The average is printed out in TEST_AVERAGE.txt
   # remove older log file if any, otherwise calculations will be wrong  
   rm -f $2.$3.$4.timelog.txt
   for cycle in 1 2 3 ; do
     echo "CYCLE $cycle" >> $2.timelog.txt
     
     echo "Time for $1 parallel clients running $2" >> $2.$3.$4.timelog.txt
     for i in `seq 1 $1` ; do
       { time python $2 $3 $4 > /dev/null 2>&1 ; } 2>> $2.$3.$4.timelog.txt  & 
     done
     # Give enough time to finish (100 sec ?)
     sleep 300
   done
   average=`cat $2.$3.$4.timelog.txt |grep real| awk '{print $2}'|awk -F0m '{print $2}'|awk -Fs '{sum = sum + $1} END {print sum}'| awk '{avg = $1/30} END {print avg}'`
   echo "Average Time Taken By $2 with $3 files in $4 iterations is: $average" >> TEST_AVERAGE.txt
}

date=`date`
echo "Test Starting at $date" >> TEST_AVERAGE.txt

# 10 parallel clients: each inserting 1000 files, 1 at a time
echo "10 parallel clients: each inserting 1000 files, 1 at a time" >> TEST_AVERAGE.txt
calculateAverage 10 dbsStressTest.py 1000 1000
# 10 parallel clients: each inserting 1000 files, 10 at a time
echo "10 parallel clients: each inserting 1000 files, 10 at a time" >> TEST_AVERAGE.txt
calculateAverage 10 dbsStressTest.py 1000 100
# 10 parallel clients: each inserting 1000 files, 100 at a time
echo "10 parallel clients: each inserting 1000 files, 100 at a time" >> TEST_AVERAGE.txt
calculateAverage 10 dbsStressTest.py 1000 10
# 10 parallel clients: each inserting 1000 files, 1000 at a time
echo "10 parallel clients: each inserting 1000 files, 1000 at a time" >> TEST_AVERAGE.txt
calculateAverage 10 dbsStressTest.py 1000 1

# 15 parallel clients: each inserting 1000 files, 1 at a time
echo "15 parallel clients: each inserting 1000 files, 1 at a time" >> TEST_AVERAGE.txt
calculateAverage 15 dbsStressTest.py 1000 1000
# 15 parallel clients: each inserting 1000 files, 10 at a time
echo "15 parallel clients: each inserting 1000 files, 10 at a time" >> TEST_AVERAGE.txt
calculateAverage 15 dbsStressTest.py 1000 100
# 15 parallel clients: each inserting 1000 files, 100 at a time
echo "15 parallel clients: each inserting 1000 files, 100 at a time" >> TEST_AVERAGE.txt
calculateAverage 15 dbsStressTest.py 1000 10
# 15 parallel clients: each inserting 1000 files, 1000 at a time
echo "15 parallel clients: each inserting 1000 files, 1000 at a time" >> TEST_AVERAGE.txt
calculateAverage 15 dbsStressTest.py 1000 1

# 20 parallel clients: each inserting 1000 files, 1 at a time
echo "20 parallel clients: each inserting 1000 files, 1 at a time" >> TEST_AVERAGE.txt
calculateAverage 20 dbsStressTest.py 1000 1000
# 20 parallel clients: each inserting 1000 files, 10 at a time
echo "20 parallel clients: each inserting 1000 files, 10 at a time" >> TEST_AVERAGE.txt
calculateAverage 20 dbsStressTest.py 1000 100
# 20 parallel clients: each inserting 1000 files, 100 at a time
echo "20 parallel clients: each inserting 1000 files, 100 at a time" >> TEST_AVERAGE.txt
calculateAverage 20 dbsStressTest.py 1000 10
# 20 parallel clients: each inserting 1000 files, 1000 at a time
echo "20 parallel clients: each inserting 1000 files, 1000 at a time" >> TEST_AVERAGE.txt
calculateAverage 20 dbsStressTest.py 1000 1

# 30 parallel clients: each inserting 1000 files, 1 at a time
echo "30 parallel clients: each inserting 1000 files, 1 at a time" >> TEST_AVERAGE.txt
calculateAverage 30 dbsStressTest.py 1000 1000
# 30 parallel clients: each inserting 1000 files, 10 at a time
echo "30 parallel clients: each inserting 1000 files, 10 at a time" >> TEST_AVERAGE.txt
calculateAverage 30 dbsStressTest.py 1000 100
# 30 parallel clients: each inserting 1000 files, 100 at a time
echo "30 parallel clients: each inserting 1000 files, 100 at a time" >> TEST_AVERAGE.txt
calculateAverage 30 dbsStressTest.py 1000 10
# 30 parallel clients: each inserting 1000 files, 1000 at a time
echo "30 parallel clients: each inserting 1000 files, 1000 at a time" >> TEST_AVERAGE.txt
calculateAverage 30 dbsStressTest.py 1000 1

cat TEST_AVERAGE.txt | mail -s "Time Profile Test Done" anzar@fnal.gov

date=`date`
echo "Test Finishing at $date" >> TEST_AVERAGE.txt

echo "DONE"

