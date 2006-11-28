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
CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI apiversion=v00_00_01"
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSTest
path=/TestPrimary1164144491.29/HIT1164144491.29/TestProcessed1164144491.29
block='/TestPrimary1164144491.29/TestProcessed1164144491.29#42665801-a716-487e-9220-057e955f3a39'

#$CMD api=listPrimaryDatasets
#$CMD api=listPrimaryDatasets
#$CMD api=listPrimaryDatasets pattern=*

#$CMD api=listProcessedDatasets
#$CMD api=listProcessedDatasets primary_datatset_name_pattern=* data_tier_name_pattern=* processed_datatset_name_pattern=* app_version=* app_family_name=* app_executable_name=* parameterset_name=* 

#$CMD api=listAlgorithms
#$CMD api=listAlgorithms app_version=* app_family_name=* app_executable_name=* parameterset_name=* 

#$CMD api=listRuns path=$path 

#$CMD api=listTiers path=$path 

#$CMD api=listBlocks path=$path 

#$CMD api=listFiles path=$path 
#$CMD api=listFiles block_name=$block 
#$CMD api=listFiles path=$path pattern_lfn=* 

#insert primary dataset
xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<primary-dataset annotation='aaaa' primary_name='PrimaryDabcS_VIJAY_02' start_date='NOV' end_date='DEC' trigger_path_description='anyTD' mc_channel_description='MCDesc' mc_production='MCProd' mc_decay_chain='DC' other_description='OD' type='VALID'>
			</primary-dataset>
		</dbs>"
$CMD api=insertPrimaryDataset "xmlinput=$xmlString" 


#insert Algorithm
xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH'/>
	</dbs>"
#$CMD api=insertAlgorithm "xmlinput=$xmlString" 


xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH'/>
	</dbs>"
#$CMD api=insertAlgorithm "xmlinput=$xmlString" 

xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH'/>
	</dbs>"
#$CMD api=insertAlgorithm "xmlinput=$xmlString" 

xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<algorithm app_version='MyVersion12' app_family_name='MyFamily12' app_executable_name='MyExe12' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH'/>
	</dbs>"
#$CMD api=insertAlgorithm "xmlinput=$xmlString" 

xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<algorithm app_version='MyVersion22' app_family_name='MyFamily22' app_executable_name='MyExe22' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH'/>
	</dbs>"
#$CMD api=insertAlgorithm "xmlinput=$xmlString" 


# insert tier
#$CMD api=insertTier tier_name="MY_TIER" 

xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<run run_number='52' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov' end_of_run='dec'/>
	</dbs>"
#$CMD api=insertRun "xmlinput=$xmlString" 
xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<run run_number='3' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov' end_of_run='dec'/>
	</dbs>"
#$CMD api=insertRun "xmlinput=$xmlString" 

xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<lumi lumi_section_number='102' run_number='52' start_event_number='20' end_event_number='200' lumi_start_time='nov' lumi_end_time='dec'/>
	</dbs>"

#$CMD api=insertLumiSection "xmlinput=$xmlString" 

xmlString="<?xml version='1.0' standalone='yes'?>
	<dbs>
		<processed-dataset primary_datatset_name='PrimaryDS_ANZAR_01' processed_datatset_name='anzar-procds-218' open_for_writing='y' physics_group_name='AnyName' physics_group_convener='ANZARDN' status='NEW'>
			<data_tier name='HIT'/>
			<data_tier name='DIGI'/>
			<data_tier name='GEN'/>
			<parent path='/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05'/>
			<parent path='/PrimaryDS_ANZAR_01/test-tier-02/anzar-procds-06'/>
			<algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2'/>
			<algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2' ps_name='DUMMY_ps_name2'/>
			<run run_number='52'/>
			<run run_number='3'/>
		</processed-dataset>
	</dbs>"
	
#$CMD api=insertProcessedDataset "xmlinput=$xmlString" 




cd $savePWD
