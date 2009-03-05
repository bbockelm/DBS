#!/bin/sh
DBSTEST=/home/anzar/TEST/dbstest_206_03032009_01
rm -rf $DBSTEST
mkdir -p $DBSTEST
cd $DBSTEST
TESTRESULTS=$DBSTEST/TestResults
mkdir -p $TESTRESULTS
#
SERVER_VER=HEAD
SCHEMA_VER=HEAD
LIB_VER=HEAD
#
export CVSROOT=:pserver:anonymous@cmscvs.cern.ch:/cvs_server/repositories/CMSSW 
export CVS_RSH=ssh 
cvs -d `echo $CVSROOT | awk -F@ '{print $1":98passwd\@"$2}'` login 
#
echo "Checking out..."
cvs -Q co -r $SERVER_VER DBS/Servers/JavaServer
cvs -Q co -r $SCHEMA_VER DBS/Schema/NeXtGen
cvs -Q co -r $LIB_VER DBS/LibValut
#
cd DBS/Schema/NeXtGen
#
sqlplus cms_dbs_test/cms_dbs_test_2007@cmscald < OracleReset.sql
sqlplus cms_dbs_test/cms_dbs_test_2007@cmscald < DBS-NeXtGen-Oracle_DEPLOYABLE.sql
#
#
cd $DBSTEST
cd DBS/Servers/JavaServer
source setup.sh.tobe
cp /home/anzar/contexts/context.xml.anzar.ora.206 etc/context.xml
ant dist
cp DBS.war /home/anzar/devFRONTIER/TOMCAT/apache-tomcat-5.5.15/webapps/DBSTEST.war
sleep 20
#####################################
cd $DBSTEST
CLIENT_VER=DBS_2_0_4_patch1
cvs -Q co -r $CLIENT_VER DBS/Clients/Python
cvs -Q co -A DBS/Clients/Python/DBSAPI/UnitTests/UnitTests204
cd DBS/Clients/Python
source setup.sh
cd DBSAPI
cp /home/anzar/contexts/dbs.config.204 dbs.config
#
echo "Running tests with 204 client"
echo "Running migration tests"
cd UserExamples
#
python dbsMigrateWithParents.py http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet http://cmssrv17.fnal.gov:8989/DBSTEST/servlet/DBSServlet /zcc2j-alpgen/CMSSW_1_6_7-HLT-1204121116/GEN-SIM-DIGI-RECO
#
cd $DBSTEST
cd DBS/Clients/Python/DBSAPI/UnitTests/UnitTests204
echo "Running runAllTests.sh..."
./runAllTests.sh &> runAllTests.log
cp runAllTests.log $TESTRESULTS/runAllTests.log.204
res=`cat runAllTests.log|grep "Test results are written in"| awk '{print $6}'`
cp  $res $TESTRESULTS/results.txt.204
#
echo "Running validation tests..."
python validate.py &> validate.log
cp validate.log $TESTRESULTS/validate.log.204
#
echo "Running QL tests..."
python dbsqlTest.py &> dbsqlTest.log
cp dbsqlTest.log $TESTRESULTS/dbsqlTest.log.204
#
echo
cat validate.log
echo "DONE testing with 204 client"
#
########################################
#
cd $DBSTEST
mv DBS/Clients DBS/Clients_2_0_4
CLIENT_VER=DBS_2_0_5
cvs -Q co -r $CLIENT_VER DBS/Clients/Python
cvs -Q co -A DBS/Clients/Python/DBSAPI/UnitTests/UnitTests205
cd DBS/Clients/Python
source setup.sh
cd DBSAPI
cp /home/anzar/contexts/dbs.config.205 dbs.config
##
echo "Running tests with 204 client"
echo "Running migration tests"
cd UserExamples
#
python dbsMigrateWithParents.py http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet http://cmssrv17.fnal.gov:8989/DBSTEST/servlet/DBSServlet /RelValTTbar/CMSSW_2_1_0_pre6-RelVal-1213920853-IDEAL_V2-2nd/GEN-SIM-DIGI-RAW-HLTDEBUG-RECO
#
cd ../UnitTests/UnitTests205
echo "Running runAllTests.sh..."
./runAllTests.sh &> runAllTests.log
cp runAllTests.log $TESTRESULTS/runAllTests.log.205
res=`cat runAllTests.log|grep "Test results are written in"| awk '{print $6}'`
cp  $res $TESTRESULTS/results.txt.205

echo "Running validation tests..."
python validate.py &> validate.log
cp validate.log $TESTRESULTS/validate.log.205
#
echo "Running QL tests..."
python dbsqlTest.py &> dbsqlTest.log
cp dbsqlTest.log $TESTRESULTS/dbsqlTest.log.205
#
cat validate.log
echo "DONE testing with 205 client"
#
###########################################################
#
cd $DBSTEST
mv DBS/Clients DBS/Clients_2_0_5
CLIENT_VER=HEAD
cvs -Q co -r $CLIENT_VER DBS/Clients/Python
cd DBS/Clients/Python
source setup.sh
cd DBSAPI
cp /home/anzar/contexts/dbs.config.206 dbs.config
#
echo "Running tests with 206 client"
echo "Running migration tests"
cd UserExamples
#python dbsMigrateWithParents.py http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet http://cmssrv17.fnal.gov:8989/DBSTEST/servlet/DBSServlet /RelValTTbar/CMSSW_2_1_0_pre6-RelVal-1213920853-IDEAL_V2-2nd/GEN-SIM-DIGI-RAW-HLTDEBUG-RECO
#
#
cd ../UnitTests
echo "Running runAllTests.sh..."
./runAllTests.sh &> runAllTests.log
cp runAllTests.log $TESTRESULTS/runAllTests.log.206
res=`cat runAllTests.log|grep "Test results are written in"| awk '{print $6}'`
cp  $res $TESTRESULTS/results.txt.206
#
echo "Running validation tests..."
python validate.py &> validate.log
cp validate.log $TESTRESULTS/validate.log.206
#
echo "Running QL tests..."
python dbsqlTest.py &> dbsqlTest.log
cp dbsqlTest.log $TESTRESULTS/dbsqlTest.log.206
#
echo
cat validate.log
echo "DONE testing with 206 client"
echo "DONE"

