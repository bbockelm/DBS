cd ../..
CLASSPATH=.
CLASSPATH=$PWD/client/build:$CLASSPATH 
cd dm/build
java gov.fnal.ms.dm.util.DbsWebMigrateApi 
cd ../test

