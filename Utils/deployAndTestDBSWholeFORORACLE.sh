#!/bin/sh
#
DBSTESTDIR=$PWD/DBS_STRESS_ORACLE_2
TOMCATHOME=/home/anzar/CMSSRV18TOMCAT/TOMCAT/apache-tomcat-5.5.15
SchemaVersion=v00_00_06
ServerVersion=v00_00_06
ClientVersion=v00_00_06
MYCONFIGDIR=/home/anzar/devDBS/ORA_CONFIG
rm -rf $DBSTESTDIR
mkdir $DBSTESTDIR
export CVSROOT=:pserver:anonymous@cmscvs.cern.ch:/cvs_server/repositories/CMSSW 
export CVS_RSH=ssh 
cvs -d `echo $CVSROOT | awk -F@ '{print $1":98passwd\@"$2}'` login 
#
#Check Out and Deploy Schema
cd  $DBSTESTDIR
cvs co -r $SchemaVersion DBS/Schema/NeXtGen 
cd /home/anzar/DBS-ORACLE/oracle-10.2.0.1/
source oraenv.sh
cd  $DBSTESTDIR
cd DBS/Schema/NeXtGen
sqlplus ?????????/??????@CMSCALD  < OracleReset.sql
sqlplus ?????????/??????@CMSCALD  < DBS-NeXtGen-Oracle_DEPLOYABLE.sql
cd -
#
#Check Out and Deploy Server
cd  $DBSTESTDIR
cvs co -r $ServerVersion DBS/Servers/JavaServer
cd DBS/Servers/JavaServer
cp $MYCONFIGDIR/setup_server.sh setup.sh
cp $MYCONFIGDIR/context.xml etc
source setup.sh
ant dist
cp DBS.war $TOMCATHOME/webapps
#
# Sleep on it for few seconds
sleep 30
#
# Check Out and Deploy Client
cd  $DBSTESTDIR
cvs co -r $ClientVersion DBS/Clients/Python
cd DBS/Clients/Python
cp $MYCONFIGDIR/setup_client.sh setup.sh
cp $MYCONFIGDIR/dbs.config dbs.config
source setup.sh
cd UnitTests
./runAllTests.sh


