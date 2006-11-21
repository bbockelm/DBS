#!/bin/sh
#JAVA_HOME=/usr/java/jdk1.5.0_06/
BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14_g.jar:$PWD/mysql-connector-java-5.0.3-bin.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSTest
path=/TestPrimary1164144491.29/HIT1164144491.29/TestProcessed1164144491.29
block='/TestPrimary1164144491.29/TestProcessed1164144491.29#42665801-a716-487e-9220-057e955f3a39'

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listPrimaryDatasets
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listPrimaryDatasets pattern=*

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listProcessedDatasets
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listProcessedDatasets primary_datatset_name_pattern=* data_tier_name_pattern=* processed_datatset_name_pattern=* app_version=* app_family_name=* app_executable_name=* parameterset_name=*

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listAlgorithms
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listAlgorithms app_version=* app_family_name=* app_executable_name=* parameterset_name=*

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listRuns path=$path

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listTiers path=$path

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listBlocks path=$path

#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listFiles path=$path
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listFiles block_name=$block
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=listFiles path=$path pattern_lfn=*

xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<primary-dataset annotation='aaaa' primary_name='PrimaryDS_VIJAY_01' start_date='NOV' end_date='DEC' trigger_path_description='anyTD' mc_channel_description='MCDesc' mc_production='MCProd' mc_decay_chain='DC' other_description='OD' type='PDS'>
			</primary-dataset>
		</dbs>"
$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=insertPrimaryDataset "xmlinput=$xmlString"


xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH'/>
	</dbs>"
															
$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI api=insertAlgorithm "xmlinput=$xmlString"
cd $savePWD
