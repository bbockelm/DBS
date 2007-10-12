import os, string, pdb
tbl_map={
'Person':'PN',
'PhysicsGroup':'PG',
'LumiSection':'LS',
'RunLumiQuality':'RLQ',
'QualityHistory':'QH',
'AlgorithmConfig':'AC',
'PrimaryDatasetDescription':'PDD',
'PrimaryDataset':'PS',
'ProcessedDataset':'PD',
'ProcDSTier':'PT',
'ProcDSParent':'PP',
'ProcAlgo':'PA',
'AnalysisDataset':'ADS',
'Block':'BLK',
'Files':'FL',
'FileParentage':'FP',
'FileRunLumi':'FRL',
'FileAlgo':'FA',
'FileTriggerTag':'FTT',
'FileAssoc':'FAC',
'SEBlock':'SB'
}

lines=open("index.txt", "r").readlines()
for aline in lines:	
	if aline.strip() == '':
		continue
	#CREATE INDEX  ON ProcDSTier(Dataset,DataTier);
	stmt1=aline.split('ON ')[1]
	tbl=stmt1.split('(')[0]
        stmt2=stmt1.split('(')[1] 
	stmt3=stmt2.split(')')[0]
	cols=stmt3.split(',')
	for acol in cols:
		print "CREATE INDEX ix_"+tbl_map[tbl]+"_"+acol+" ON "+tbl+"("+acol+");"

