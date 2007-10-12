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
CLASSPATH=.:$PWD/ojdbc14.jar:$PWD/mysql-connector-java-5.0.5-bin.jar:$PWD/sqlitejdbc-v036-nested.jar
cd $BASE/bin
CLASSPATH=$CLASSPATH:$PWD/WEB-INF/classes/
CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH -DDBS_SERVER_CONFIG=$BASE/etc/context.xml dbs.test.DBSCLI apiversion=DBS_1_0_7"
rand=`uuidgen`
#$JAVA_HOME/bin/java -classpath $CLASSPATH dbs.test.DBSTest
block='/TestPrimary1164144491.29/TestProcessed1164144491.29#42665801-a716-487e-9220-057e955f3a39'
primary_name="This_is_a_test_primary_$rand"
tier_name1="GEN"
tier_name2="SIM"
#tier_name1="This_is_a_test_tier_HIT_$rand"
#tier_name1="This_is_a_test_tier_HIT"
#tier_name2="This_is_a_test_tier_SIM_$rand"
#tier_name2="This_is_a_test_tier_SIM"
processed_name="This_is_a_test_processed_$rand"
#path="/$primary_name/$tier_name1/$processed_name"
path="/$primary_name/$processed_name/$tier_name1"
path_child="/$primary_name/CHILD_$processed_name/$tier_name2"
run_number1="9999"
run_number2="9998"
#block_name="/test/test/test#$rand"
block_name="$path_child#$rand"
block_name2="$path_child#2_$rand"
lfn1="TEST_LFN_1_$rand"
lfn2="TEST_LFN_2_$rand"
#algo1="<algorithm app_version='MyVersion1_$rand' app_family_name='MyFamily1_$rand' app_executable_name='MyExe1_$rand' ps_name='DUMMYa_ps_name2_$rand' ps_hash='DUMMY_HASH_$rand' ps_version='DUMMY1_$rand' ps_type='DUMMYTYPE1_$rand' ps_annotation='ANN1_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"
algo1="<algorithm app_version='MyVersion1_$rand' app_family_name='MyFamily1_$rand' app_executable_name='MyExe1_$rand' ps_name='DUMMYa_ps_name2_$rand' ps_hash='DUMMY_HASH_$rand' ps_version='DUMMY1_$rand' ps_type='DUMMYTYPE1_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"
algo2="<algorithm app_version='MyVersion2_$rand' app_family_name='MyFamily2_$rand' app_executable_name='MyExe2_$rand' ps_name='DUMMYb_ps_name2_$rand' ps_hash='DUaMMY_HASH_$rand' ps_version='DUMMY2_$rand' ps_type='DUMMYTYPE2_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"
algo3="<algorithm app_version='MyVersion12_$rand' app_family_name='MyFamily12_$rand' app_executable_name='MyExe12_$rand' ps_name='DUMMYc_ps_name2_$rand' ps_hash='DbUMMY_HASH_$rand' ps_version='DUMMY3_$rand' ps_type='DUMMYTYPE3_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"
algo4="<algorithm app_version='MyVersion22_$rand' app_family_name='MyFamily22_$rand' app_executable_name='MyExe22_$rand' ps_name='DUMMYd_ps_name2_$rand' ps_hash='DUMcMY_HASH_$rand' ps_version='DUMMY4_$rand' ps_type='DUMMYTYPE4_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"

falgo1="<file_algorithm app_version='MyVersion1_$rand' app_family_name='MyFamily1_$rand' app_executable_name='MyExe1_$rand' ps_name='DUMMYa_ps_name2_$rand' ps_hash='DUMMY_HASH_$rand' ps_version='DUMMY1_$rand' ps_type='DUMMYTYPE1_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"
falgo2="<file_algorithm app_version='MyVersion2_$rand' app_family_name='MyFamily2_$rand' app_executable_name='MyExe2_$rand' ps_name='DUMMYb_ps_name2_$rand' ps_hash='DUaMMY_HASH_$rand' ps_version='DUMMY2_$rand' ps_type='DUMMYTYPE2_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"
falgo3="<file_algorithm app_version='MyVersion12_$rand' app_family_name='MyFamily12_$rand' app_executable_name='MyExe12_$rand' ps_name='DUMMYc_ps_name2_$rand' ps_hash='DbUMMY_HASH_$rand' ps_version='DUMMY3_$rand' ps_type='DUMMYTYPE3_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"
falgo4="<file_algorithm app_version='MyVersion22_$rand' app_family_name='MyFamily22_$rand' app_executable_name='MyExe22_$rand' ps_name='DUMMYd_ps_name2_$rand' ps_hash='DUMcMY_HASH_$rand' ps_version='DUMMY4_$rand' ps_type='DUMMYTYPE4_$rand' ps_content='aW50IGE9IHt9LCBiPXtjPTEsIGQ9MzN9LCBmPXt9LCB4LCB5LCB4' created_by='Let_me_try_this' creation_date='1066729598999'/>"

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

#updatePrimDSType () {
#	message="Executing updatePrimDSType API..."
#	echo $message >> $outFile ; echo $message
#	out=`$CMD api=updatePrimDSType primary_dataset_name=$primary_name status=INVALID`
#	display "$out"
#}

updateProcDSStatus () {
	message="Executing updateProcDSStatus API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateProcDSStatus path=$path_child status=INVALID`
	#out=`$CMD api=updateProcDSStatus path=/TestPrimary_002_20070207_16h08m26s/TestProcessed_20070207_16h08m26s/SIM_20070207_16h08m26s status=INVALID`
	display "$out"
}

updateAnalDSStatus () {
	message="Executing updateAnalDSStatus API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateAnalDSStatus analysis_dataset_name=$path_child/AnalysisDS_Defination1_$rand status=NEW`
	display "$out"
}

updateFileStatus () {
	message="Executing updateFileStatus API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateFileStatus lfn=$lfn1 status=INVALID`
	display "$out"
}

updateFileMetaData () {
	message="Executing updateFileMetaData API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateFileMetaData lfn=$lfn1 queryable_meta_data=A_NEW_META_DATA`
	display "$out"
}

updateAnalDSType () {
	message="Executing updateAnalDSType API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateAnalDSType analysis_dataset_name=$path_child/AnalysisDS_Defination1_$rand type=TEST`
	display "$out"
}

updateFileType () {
	message="Executing updateFileType API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateFileType lfn=$lfn1 type=EDM`
	display "$out"
}


listAlgorithms () {
	message="Executing listAlgorithms API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listAlgorithms`
	display "$out"
	#$CMD api=listAlgorithms app_version=* app_family_name=* app_executable_name=* parameterset_hash=* 
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

listStorageElements () {
	message="Executing listStorageElements API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listStorageElements`
	#out=`$CMD api=listStorageElements storage_element_name="SE2*"`
	#echo $out
	display "$out"
}

listBlocks() {
	message="Executing listBlocks API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listBlocks path=$path_child`
	#out=`$CMD api=listBlocks`
	display "$out"
	#echo "$out"
}

listFiles () {
	message="Executing listFiles API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listFiles path=$path_child detail="true"`
	#out=`$CMD api=listFiles "path=$path_child-$tier_name1"`
	display "$out"
	#$CMD api=listFiles block_name=$block 
	#$CMD api=listFiles path=$path pattern_lfn=* 
}

listLFNs () {
	message="Executing listLFNs API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listLFNs path=$path_child`
	display "$out"
}


listRowsInTable () {
	message="Executing listRowsInTable API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=listRowsInTable table_name=PrimaryDataset from=10 rows=10`
        display "$out"
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

listAnalysisDatasetDefinition () {
	message="Executing listAnalysisDatasetDefinition API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listAnalysisDatasetDefinition`
	display "$out"
}

listAnalysisDataset () {
	message="Executing listAnalysisDataset API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=listAnalysisDataset`
	display "$out"
}

deleteSEFromBlock () {
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<storage_element storage_element_name='SE1_$rand' block_name='$block_name'/>
		</dbs>"

	message="Executing deleteSEFromBlock API..."
	echo $message >> $outFile ; echo $message
	#out=`$CMD api=deleteSEFromBlock storage_element_name="SE2_c13b4c7f-80af-4b9c-8a83-dfe095a6d1d8"`
	out=`$CMD api=deleteSEFromBlock "xmlinput=$xmlString"`
	#echo $out
	display "$out"
}



#insert primary dataset
insertPrimaryDataset () {
	xmlString="<?xml version='1.0' standalone='yes'?>
			<dbs>
				<primary_dataset annotation='aaaa$rand' primary_name='$primary_name' start_date='NOV_$rand' end_date='DEC_$rand' trigger_path_description='anyTD_$rand' mc_channel_description='MCDesc_$rand' mc_production='MCProd_$rand' mc_decay_chain='DC_$rand' other_description='OD_$rand' type='cosmic' created_by='Let_me_try_this' creation_date='1066729598999'>
				</primary_dataset>
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
	#echo "$out"
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
			<tier tier_name='This_is_a_test_TIER_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"

	out=`$CMD api=insertTier "xmlinput=$xmlString"`
	display "$out"
}


# insert run
insertRun () {
	xmlString1="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<run run_number='$run_number1' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='1066729598999' end_of_run='1066729348999' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"
	
	xmlString2="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<run run_number='$run_number2' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='1022729598999' end_of_run='1066729596699' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"

	message="Executing insertRun API 2 times with different inputs..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertRun "xmlinput=$xmlString1"`
	display "$out"
	out=`$CMD api=insertRun "xmlinput=$xmlString2"`
	display "$out"
}

# update run
updateRun () {
        xmlString1="<?xml version='1.0' standalone='yes'?>
                <dbs>
                        <run run_number='$run_number1' number_of_events='3000' end_of_run='1066711118999' />
                </dbs>"

        message="Executing update Run API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=updateRun "xmlinput=$xmlString1"`
        display "$out"
}

updateLumiSection () {
        xmlString1="<?xml version='1.0' standalone='yes'?>
                <dbs>
                        <lumi_section lumi_section_number='4445' run_number='$run_number1' start_event_number='33' end_event_number='999'			lumi_start_time='1011129598999' lumi_end_time='1066729598000'/>
                </dbs>"

        message="Executing updateLumiSection API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=updateLumiSection "xmlinput=$xmlString1"`
        display "$out"
}


# insert lumi section
insertLumiSection () {
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<lumi_section lumi_section_number='4445' run_number='$run_number1' start_event_number='20' end_event_number='200' lumi_start_time='1066729598555' lumi_end_time='1066766698999' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"
	message="Executing insertLumiSection API..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertLumiSection "xmlinput=$xmlString"`
	display "$out"

	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<lumi_section lumi_section_number='4444' run_number='$run_number1' start_event_number='20' end_event_number='200' lumi_start_time='1999729598999' lumi_end_time='1066729555999' created_by='Let_me_try_this' creation_date='1066729598999'/>
		</dbs>"
	message="Executing insertLumiSection API..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertLumiSection "xmlinput=$xmlString"`
	display "$out"

	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<lumi_section lumi_section_number='9996' run_number='$run_number1' start_event_number='20' end_event_number='200' lumi_start_time='1066111198999' lumi_end_time='1066712128999' created_by='Let_me_try_this' creation_date='1066729598999'/>
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
			<processed_dataset primary_datatset_name='$primary_name' processed_datatset_name='$processed_name' open_for_writing='y' physics_group_name='AnyName_$rand' physics_group_convener='ANZARDN' status='VALID' created_by='Let_me_try_this' creation_date='1066729598999'>
				<data_tier name='$tier_name1'/>
				<data_tier name='$tier_name2'/>
				$algo1
				$algo2
				<run run_number='$run_number1'/>
				<run run_number='$run_number2'/>
			</processed_dataset>
		</dbs>"

	xmlString2="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<processed_dataset primary_datatset_name='$primary_name' processed_datatset_name='CHILD_$processed_name' open_for_writing='y' physics_group_name='AnyName_$rand' physics_group_convener='ANZARDN' status='VALID' created_by='Let_me_try_this' creation_date='1066729598999'>
				<data_tier name='$tier_name1'/>
				<data_tier name='$tier_name2'/>
				<!--data_tier name='TEST_DIGI_$rand'/-->
				<!--data_tier name='TEST_GEN_$rand'/-->
				<parent path='$path'/>
				$algo3
				<run run_number='$run_number1'/>
			</processed_dataset>
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

	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<block path='$path_child' name='$block_name2' open_for_writing='1' created_by='Let_me_try_this' creation_date='1066729598999'/>
			<storage_element storage_element_name='SE3_$rand'/>
			<storage_element storage_element_name='SE4_$rand'/>
		</dbs>"
	message="Executing insertBlock API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertBlock "xmlinput=$xmlString"`
	display "$out"

	#echo "$out"
}

closeBlock () {
	message="Executing closeBlock API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=closeBlock block_name=$block_name`
	display "$out"
}

deleteProcDS () {
	message="Executing deleteProcDS API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=deleteProcDS path=$path_child`
	display "$out"
}

undeleteProcDS () {
	message="Executing undeleteProcDS API..."
	echo $message >> $outFile ; echo $message
	out=`$CMD api=undeleteProcDS path=$path_child`
	#out=`$CMD api=undeleteProcDS "path=/This_is_a_test_primary_c6faf185-cc5e-4d45-9392-ad4d51d14dc2/CHILD_This_is_a_test_processed_c6faf185-cc5e-4d45-9392-ad4d51d14dc2/SIMaa"`
	display "$out"
}


updateSEName () {
	message="Executing updateSEName API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateSEName storage_element_name_from=SE1_$rand storage_element_name_to=HAHAHHHAHAHH`
	display "$out"
}

updateSEBlock () {
	message="Executing updateSEBlock API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=updateSEBlock block_name=$block_name storage_element_name_from=SE1_$rand storage_element_name_to=SE4_$rand`
	display "$out"
}

		#<!--processed_datatset path='$path_child' block_name='$block_name'--/>
insertFiles () {
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
		<processed_datatset path='$path_child'>
			<file lfn='$lfn1' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any' created_by='Let_me_try_this' creation_date='1066729598999'>
				<file_lumi_section lumi_section_number='9997' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='1066729598999' lumi_end_time='1066333398999'/>
				<file_lumi_section lumi_section_number='9996' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='1066729598999' lumi_end_time='1066333398999'/>
				<file_lumi_section lumi_section_number='9995' run_number='$run_number2' start_event_number='4' end_event_number='7' lumi_start_time='1066729598999' lumi_end_time='1066333398999'/>
				<file_data_tier name='$tier_name2'/>
				$falgo1
				$falgo1
			</file>
			<file lfn='$lfn2' checksum='CHKSUM2' number_of_events='300' size='2002' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any' created_by='Let_me_try_this' creation_date='1066729598999'>
				<file_lumi_section lumi_section_number='1006' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='1066729598999' lumi_end_time='1066333398999'/>
				<file_lumi_section lumi_section_number='1017' run_number='$run_number2' start_event_number='4' end_event_number='7' lumi_start_time='1066729598999' lumi_end_time='1066333398999'/>
				<file_lumi_section lumi_section_number='1028' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='1066729598999' lumi_end_time='1066333398999'/>
				<file_data_tier name='$tier_name2'/>
				$falgo3
				$falgo4
			</file>
		</processed_datatset>
		</dbs>"
	message="Executing insertFiles API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertFiles "xmlinput=$xmlString"`
	display "$out"

}

#insert analysis dataset
createAnalysisDatasetFromPD () {
        xmlString="<?xml version='1.0' standalone='yes'?>
                        <dbs>
                                <analysis_dataset annotation='aaaa$rand' name='AnalysisDS_$rand' type='VALID' status='NEW' path='$path_child' physics_group_name='AnyName_$rand' created_by='Let_me_try_this' creation_date='1066729598999'/>
                        </dbs>"
        message="Executing  createAnalysisDatasetFromPD API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=createAnalysisDatasetFromPD "xmlinput=$xmlString"`
        display "$out"
}

createAnalysisDatasetDefinition () {
        xmlString="<?xml version='1.0' standalone='yes'?>
                        <dbs>
                                <analysis_dataset_definition analysisds_def_name='AnalysisDS_Defination1_$rand' path='$path_child' created_by='Let_me_try_this' creation_date='1066729598999' user_cut='RunNumber = 2' description='This is a test defination'/>
                        </dbs>"
	#echo "$xmlString"
        message="Executing  createAnalysisDatasetDefinition API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=createAnalysisDatasetDefinition "xmlinput=$xmlString"`
        display "$out"

        xmlString="<?xml version='1.0' standalone='yes'?>
                        <dbs>
                                <analysis_dataset_definition analysisds_def_name='AnalysisDS_Defination2_$rand' path='$path_child' created_by='Let_me_try_this' creation_date='1066729598999' user_cut='RunNumber = 2' description='This is a test defination'/>
				<run run_number='$run_number1, $run_number2' run_range='1,10000'/>
				<run run_number='$run_number2' run_range='20000,25000'/>
				<lumi_section lumi_section_number='9997' lumi_section_range='1,5000'/>
				<lumi_section lumi_section_number='9996' lumi_section_range='9000,10000'/>
				$algo1
				$algo3
				$algo4
				<file lfn='$lfn1'/>
				<file lfn='$lfn2'/>

                        </dbs>"
	#echo "$xmlString"
        message="Executing  createAnalysisDatasetDefinition API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=createAnalysisDatasetDefinition "xmlinput=$xmlString"`
        display "$out"


	
}

createAnalysisDataset () {
        xmlString="<?xml version='1.0' standalone='yes'?>
                        <dbs>
                                <analysis_dataset annotation='aaaa$rand' name='AnalysisDS1_$rand' analysisds_def_name='AnalysisDS_Defination1_$rand' type='TEST' status='NEW' physics_group_name='AnyName_$rand' description='This is a test dataset' created_by='Let_me_try_this' creation_date='1066729598999'/>
                        </dbs>"
        message="Executing  createAnalysisDataset API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=createAnalysisDataset "xmlinput=$xmlString"`
        display "$out"

	xmlString="<?xml version='1.0' standalone='yes'?>
                        <dbs>
                                <analysis_dataset annotation='aaaa$rand' name='AnalysisDS2_$rand' analysisds_def_name='AnalysisDS_Defination2_$rand' type='TEST' status='NEW' physics_group_name='AnyName_$rand' description='This is a test dataset' created_by='Let_me_try_this' creation_date='1066729598999'/>
                        </dbs>"
        message="Executing  createAnalysisDataset API..."
        echo $message >> $outFile ; echo $message
        out=`$CMD api=createAnalysisDataset "xmlinput=$xmlString"`
        display "$out"

}

remapFiles () {
	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
		<processed_datatset path='$path_child' block_name='$block_name'>
			<file lfn='${lfn1}_parent_1' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_data_tier name='$tier_name2'/>
			</file>
			
			<file lfn='${lfn1}_parent_2' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_data_tier name='$tier_name2'/>
			</file>
			
			<file lfn='${lfn1}_parent_3' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_data_tier name='$tier_name2'/>
			</file>

			
			<file lfn='${lfn1}_input_1' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_parent lfn='${lfn1}_parent_1'/>
				<file_parent lfn='${lfn1}_parent_2'/>
				<file_data_tier name='$tier_name2'/>
			</file>
	
			<file lfn='${lfn1}_input_2' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_parent lfn='${lfn1}_parent_2'/>
				<file_parent lfn='${lfn1}_parent_3'/>
				<file_data_tier name='$tier_name2'/>
			</file>

			<file lfn='${lfn1}_child_1' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_parent lfn='${lfn1}_input_1'/>
				<file_data_tier name='$tier_name2'/>
			</file>

			<file lfn='${lfn1}_child_2' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_parent lfn='${lfn1}_input_2'/>
				<file_data_tier name='$tier_name2'/>
			</file>

			<file lfn='${lfn1}_OUTPUT_MERGED' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EDM' validation_status='VALID' queryable_meta_data='any'>
				<file_data_tier name='$tier_name2'/>
			</file>

		</processed_datatset>
		</dbs>"
	message="Executing insertFiles API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=insertFiles "xmlinput=$xmlString"`
	display "$out"

	xmlString="<?xml version='1.0' standalone='yes'?>
		<dbs>
			<in_file lfn='${lfn1}_input_1'/>
			<in_file lfn='${lfn1}_input_2'/>
			<out_file lfn='${lfn1}_OUTPUT_MERGED'/>
		</dbs>"
	message="Executing remapFiles API ..."	
	echo $message >> $outFile ; echo $message
	out=`$CMD api=remapFiles "xmlinput=$xmlString"`
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

#deleteProcDS
#undeleteProcDS
createAnalysisDatasetDefinition
createAnalysisDataset
listAnalysisDatasetDefinition
listAnalysisDataset
#
listPrimaryDatasets
listProcessedDatasets
listAlgorithms
listRuns
listTiers
listBlocks
listFiles
listLFNs
##listDatasetContents
listDatasetParents
listFileParents
listFileAlgorithms
listFileTiers
listFileLumis
listRowsInTable
listStorageElements
deleteSEFromBlock
updateProcDSStatus
updateAnalDSStatus
updateFileStatus
updateFileMetaData
updateAnalDSType
updateFileType
updateSEName
updateSEBlock
closeBlock
updateRun
updateLumiSection
##
##	
#												
echo 
echo "*************************************************************"
echo "For more detail and the output of the APIs look in $outFile"
cd $savePWD
