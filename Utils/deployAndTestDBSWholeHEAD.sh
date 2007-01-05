#!/bin/sh
#
DBSTESTDIR=$PWD/DBSHEADTEST
TOMCATHOME=/home/anzar/devFRONTIER/TOMCAT/apache-tomcat-5.5.15
MYCONFIGDIR=/home/anzar/devDBS/CONFIG
rm -rf $DBSTESTDIR
mkdir $DBSTESTDIR
export CVSROOT=:pserver:anonymous@cmscvs.cern.ch:/cvs_server/repositories/CMSSW 
export CVS_RSH=ssh 
cvs -d `echo $CVSROOT | awk -F@ '{print $1":98passwd\@"$2}'` login 
#
#Check Out and Deploy Schema
cd  $DBSTESTDIR
cvs co DBS/Schema/NeXtGen 
chmod -R +w DBS
cd DBS/Schema/NeXtGen
mysql -uanzar -p????????? < DBS-NeXtGen-MySQL_DEPLOYABLE.sql
cd -
#
#Check Out and Deploy Server
cd  $DBSTESTDIR
cvs co DBS/Servers/JavaServer
chmod -R +w DBS
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
cvs co DBS/Clients/Python
chmod -R +w DBS
cd DBS/Clients/Python
cp $MYCONFIGDIR/setup_client.sh setup.sh
cp $MYCONFIGDIR/dbs.config dbs.config
source setup.sh
cd UnitTests
./runAllTests.sh


