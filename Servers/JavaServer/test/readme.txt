List Example
./cli.sh api=listPrimaryDatasets pattern=*


Insert Example

./cli.sh api=insertPrimaryDataset "xmlinput=<?xml version='1.0'standalone='yes'?>
                        <dbs>
                                <primary-dataset annotation='aaaa' primary_name='DUMMY_paaarimary_name' start_date='NOV' end_date='DEC' trigger_path_description='anyTD_$rand' mc_channel_description='MCDesc' mc_production='MCProd' mc_decay_chain='DC' other_description='OD' type='VALID'>
                                </primary-dataset>
                        </dbs>"
