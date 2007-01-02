#!/bin/sh
#JAVA_HOME=/usr/java/jdk1.5.0_06/
BASE=$PWD/..
if [ "${JAVA_HOME}" = "" ]; then
	echo "Error! Please set your JAVA_HOME variable"
	exit 1
fi
savePWD=$PWD
outFile=$savePWD/result.txt
rm -f $outFile
cd $BASE/lib
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.3-bin.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSCLI apiversion=v00_00_04"
rand=`uuidgen`
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSTest
block='/TestPrimary1164144491.29/TestProcessed1164144491.29#42665801-a716-487e-9220-057e955f3a39'
primary_name="This_is_a_test_primary_$rand"
tier_name1="This_is_a_test_tier_HIT_$rand"
tier_name2="This_is_a_test_tier_SIM_$rand"
processed_name="This_is_a_test_processed_$rand"
path="/$primary_name/$tier_name1/$processed_name"
path_child="/$primary_name/$tier_name2/CHILD_$processed_name"
run_number1="9999"
run_number2="9998"
block_name="/test/test#$rand"
lfn1="TEST_LFN_1_$rand"
lfn2="TEST_LFN_2_$rand"
algo1="<algorithm app_version='MyVersion1_$rand' app_family_name='MyFamily1_$rand' app_executable_name='MyExe1_$rand' ps_name='DUMMYa_ps_name2_$rand' ps_hash='DUMMY_HASH_$rand' ps_version='DUMMY1_$rand' ps_type='DUMMYTYPE1_$rand' ps_annotation='ANN1_$rand' ps_content='DUMMYCO andy thing N_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>"
algo2="<algorithm app_version='MyVersion2_$rand' app_family_name='MyFamily2_$rand' app_executable_name='MyExe2_$rand' ps_name='DUMMYb_ps_name2_$rand' ps_hash='DUaMMY_HASH_$rand' ps_version='DUMMY2_$rand' ps_type='DUMMYTYPE2_$rand' ps_annotation='ANN2_$rand' ps_content='DUMMYCON_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>"
algo3="<algorithm app_version='MyVersion12_$rand' app_family_name='MyFamily12_$rand' app_executable_name='MyExe12_$rand' ps_name='DUMMYc_ps_name2_$rand' ps_hash='DbUMMY_HASH_$rand' ps_version='DUMMY3_$rand' ps_type='DUMMYTYPE3_$rand' ps_annotation='ANN3_$rand' ps_content='DUMMYCON_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>"
algo4="<algorithm app_version='MyVersion22_$rand' app_family_name='MyFamily22_$rand' app_executable_name='MyExe22_$rand' ps_name='DUMMYd_ps_name2_$rand' ps_hash='DUMcMY_HASH_$rand' ps_version='DUMMY4_$rand' ps_type='DUMMYTYPE4_$rand' ps_annotation='ANN4_$rand' ps_content='DUMMYCON_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>"


display () {
	echo "$1" >> $outFile
	tmp=`echo "$1" | grep "exception"`
	len=${#tmp}
	if [ "$len" -lt "1" ] ; then
		echo "PASSED"
		echo "*****************************************************************"
		echo 
	else
		echo "FAILED $tmp"
		echo "*****************************************************************"
		echo 
	fi
}

listPrimaryDatasets () {
	message="Executing listPrimaryDatasets API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listPrimaryDatasets`
	display "$out"
	#$CMD api=listPrimaryDatasets pattern=*
}

listProcessedDatasets () {
	message="Executing listProcessedDatasets API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listProcessedDatasets`
	display "$out"
	#$CMD api=listProcessedDatasets primary_datatset_name_pattern=* data_tier_name_pattern=* processed_datatset_name_pattern=* app_version=* app_family_name=* app_executable_name=* parameterset_name=* 
}

listAlgorithms () {
	message="Executing listAlgorithms API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listAlgorithms`
	display "$out"
	#$CMD api=listAlgorithms app_version=* app_family_name=* app_executable_name=* parameterset_name=* 
}

listRuns() {
	message="Executing listRuns API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listRuns path=$path_child`
	display "$out"
}

listTiers () {
	message="Executing listTiers API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listTiers path=$path_child`
	display "$out"
}

listBlocks() {
	message="Executing listBlocks API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listBlocks path=$path_child`
	display "$out"
	echo "$out"
}

listFiles () {
	message="Executing listFiles API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listFiles path=$path_child`
	display "$out"
	#$CMD api=listFiles block_name=$block 
	#$CMD api=listFiles path=$path pattern_lfn=* 
}

listFileParents () {
	message="Executing listFileParents API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listFileParents lfn=$lfn2`
	display "$out"
}
listFileAlgorithms () {
	message="Executing listFileAlgorithms API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listFileAlgorithms lfn=$lfn2`
	display "$out"
}
listFileTiers () {
	message="Executing listFileTiers API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listFileTiers lfn=$lfn2`
	display "$out"
}
listFileLumis () {
	message="Executing listFileLumis API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listFileLumis lfn=$lfn2`
	display "$out"
}

listDatasetContents () {
	message="Executing listDatasetContents API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listDatasetContents path=$path_child block_name=$block_name`
	display "$out"
}

listDatasetParents () {
	message="Executing listDatasetParents API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listDatasetParents path=$path_child`
	display "$out"
}

#insert primary dataset
insertPrimaryDataset () {
	xmlString="<?xml version='1.0' standalone='yes'?>
			<dbs>
				<primary-dataset annotation='aaaa$rand' primary_name='$primary_name' start_date='NOV_$rand' end_date='DEC_$rand' trigger_path_description='anyTD_$rand' mc_channel_description='MCDesc_$rand' mc_production='MCProd_$rand' mc_decay_chain='DC_$rand' other_description='OD_$rand' type='VALID' created_by='Let_me_try_this' creation_date='1066729598999'>
				</primary-dataset>
			</dbs>"
	message="Executing insertPrimaryDataset API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertPrimaryDataset "xmlinput=$xmlString"`
	display "$out"
	#echo "$out"
}


#insert Algorithm
insertAlgorithm () {
	xmlString1="<?xml version='1.0' standalone='yes'?>
		<dbs>
			$algo1
		</dbs>"

	xmlString2="<?xml version='1.0' standalone='yes'?>
		<dbs>
			$algo2
		</dbs>"

	xmlString3="<?xml version='1.0' standalone='yes'?>
		<dbs>
			$algo3
		</dbs>"

	xmlString4="<?xml version='1.0' standalone='yes'?>
		<dbs>
			$algo4
		</dbs>"

	message="Executing insertAlgorithm API 4 times with different inputs..."	
	echo $message >> $outFile ; echo $message

	out=`$CMD api=insertAlgorithm "xmlinput=$xmlString1"`
	display "$out"
	out=`$CMD api=insertAlgorithm "xmlinput=$xmlString2"`
	display "$out"
	out=`$CMD api=insertAlgorithm "xmlinput=$xmlString3"`
	display "$out"
	out=`$CMD api=insertAlgorithm "xmlinput=$xmlString4"`
	display "$out"
}

# insert tier
insertTier () {
	message="Executing insertTier API..."	
	echo $message >> $outFile ; echo $message
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<tier name='This_is_a_test_TIER_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"

	out=`$CMD api=insertTier "xmlinput=$xmlString"`
	display "$out"
}


# insert run
insertRun () {
	xmlString1="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<run run_number='$run_number1' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov' end_of_run='dec' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"
	
	xmlString2="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<run run_number='$run_number2' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov_$rand' end_of_run='dec_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"

	message="Executing insertRun API 2 times with different inputs..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertRun "xmlinput=$xmlString1"`
	display "$out"
	out=`$CMD api=insertRun "xmlinput=$xmlString2"`
	display "$out"
}



# insert lumi section
insertLumiSection () {
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<lumi lumi_section_number='1111' run_number='$run_number1' start_event_number='20' end_event_number='200' lumi_start_time='nov_$rand' lumi_end_time='dec_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"
	message="Executing insertLumiSection API..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertLumiSection "xmlinput=$xmlString"`
	display "$out"
}

# insert processed dataset
insertProcessedDataset () {
	xmlString1="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<processed-dataset primary_datatset_name='$primary_name' processed_datatset_name='$processed_name' open_for_writing='y' physics_group_name='AnyName_$rand' physics_group_convener='ANZARDN' status='VALID' created_by='Let_me_try_this' creation_date='1066729598999'>
				<data_tier name='$tier_name1'/>
				$algo1
				$algo2
				<run run_number='$run_number1'/>
				<run run_number='$run_number2'/>
			</processed-dataset>
		</dbs>"

	xmlString2="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<processed-dataset primary_datatset_name='$primary_name' processed_datatset_name='CHILD_$processed_name' open_for_writing='y' physics_group_name='AnyName_$rand' physics_group_convener='ANZARDN' status='VALID' created_by='Let_me_try_this' creation_date='1066729598999'>
				<data_tier name='$tier_name2'/>
				<data_tier name='TEST_DIGI_$rand'/>
				<data_tier name='TEST_GEN_$rand'/>
				<parent path='$path'/>
				$algo3
				<run run_number='$run_number1'/>
			</processed-dataset>
		</dbs>"

	message="Executing insertProcessedDataset API 2 times with different inputs..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertProcessedDataset "xmlinput=$xmlString1"`
	display "$out"
	out=`$CMD api=insertProcessedDataset "xmlinput=$xmlString2"`
	display "$out"
}

insertBlock () {
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<block path='$path_child' name='$block_name' open_for_writing='1' created_by='Let_me_try_this' creation_date='1066729598999'/>
			<storage_element storage_element_name='SE1_$rand'/>
			<storage_element storage_element_name='SE2_$rand'/>
		</dbs>"
	message="Executing insertBlock API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertBlock "xmlinput=$xmlString"`
	display "$out"
	echo "$out"
}

insertFiles () {
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
		<processed_datatset path='$path_child' block_name='$block_name'>
			<file lfn='$lfn1' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EVD' validation_status='VALID' queryable_meta_data='any' created_by='Let_me_try_this' creation_date='1066729598999'>
				<lumi_section lumi_section_number='9997' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>
				<lumi_section lumi_section_number='9996' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>
				<lumi_section lumi_section_number='9995' run_number='$run_number2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>
				<data_tier name='$tier_name1'/>
				<data_tier name='$tier_name2'/>
				$algo1
				$algo1
			</file>
			<file lfn='$lfn2' checksum='CHKSUM2' number_of_events='300' size='2002' file_status='VALID' type= 'EVD' validation_status='VALID' queryable_meta_data='any' created_by='Let_me_try_this' creation_date='1066729598999'>
				<lumi_section lumi_section_number='1006' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>
				<lumi_section lumi_section_number='1017' run_number='$run_number2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>
				<lumi_section lumi_section_number='1028' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/>
				<data_tier name='$tier_name1'/>
				<data_tier name='$tier_name2'/>
				<parent lfn='TEST_LFN_1_$rand'/>
				$algo3
				$algo4
			</file>
		</processed_datatset>
		</dbs>"
	message="Executing insertFiles API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertFiles "xmlinput=$xmlString"`
	display "$out"

}

#insert primary dataset
createAnalysisDatasetFromPD () {
        xmlString="<?xml version='1.0' standalone='yes'?>
                        <dbs>
                                <analysis-dataset annotation='aaaa$rand' name='AnalysisDS_$rand' type='VALID' status='TEST' path='$path_child' physics_group_name='AnyName_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>
                        </dbs>"
        message="Executing  createAnalysisDatasetFromPD API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=createAnalysisDatasetFromPD "xmlinput=$xmlString"`
        display "$out"
}




insertPrimaryDataset
insertAlgorithm
insertTier
insertRun
insertLumiSection
insertProcessedDataset
insertBlock
insertFiles
createAnalysisDatasetFromPD
listPrimaryDatasets
listProcessedDatasets
listAlgorithms
listRuns
listTiers
listBlocks
listFiles
listDatasetContents
listDatasetParents
listFileParents
listFileAlgorithms
listFileTiers
listFileLumis
													
echo 
echo "*************************************************************"
echo "For more detail and the output of the APIs look in $outFile"
cd $savePWD
