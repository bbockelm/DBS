
import dbsclient

client = dbsclient.DBSClient()
def stringp(inStr) :
	return client.str(inStr)

def delStringp(inStr) :
	dbsclient.delete_stringp(inStr)

def string(key) :
	return dbsclient.ASTR(key)

def integer(key) :
	return dbsclient.AINT(key)

def character(key) :
	return dbsclient.ACHR(key)

def setStrValue(aRow, key, value) :
	if(value != None and key != None):
		key = stringp(key)
		aRow.setValue(key, string(value))
		delStringp(key)

def setIntValue(aRow, key, value) :
	key = stringp(key)
	aRow.setValue(key, integer(int(value)))
	delStringp(key)

def setChrValue(aRow, key, value) :
	key = stringp(key)
	aRow.setValue(key, character(value))
	delStringp(key)

def getStrValue(table, key, index) :
	if(index != None and key != None):
		if(index > -1) :
			key = stringp(key)
			value = table.getStrValue(index, key)
			delStringp(key)
			return value
def writeFile():

            table = dbsclient.FileviewMultiTable()
            aRow = dbsclient.Fileviewmultirow()
            fileVector = dbsclient.FileVector()

            setStrValue(aRow, "t_file_status.name", "afile")
            setStrValue(aRow, "t_file.guid", "Test-1234-Guid")
            setStrValue(aRow, "t_file.checksum", "1234")
            setStrValue(aRow, "t_file.logical_name", "afilexgetLogicalFileNamex")
            setIntValue(aRow, "t_file.inblock", 10)
            setStrValue(aRow, "t_file_type.name", "ROOT_All")
            setStrValue(aRow, "t_file.filesize", "sucks")
            setIntValue(aRow, "t_evcoll_file.evcoll", 1840)
            fileVector.push_back(aRow)
            client.insertFiles(fileVector, table)



def writePrimary() :
	aRow = dbsclient.Primarydatasetmultirow() 
	table = dbsclient.PrimarydatasetMultiTable()

	setStrValue(aRow, "t_desc_mc.description", "dummy_value")
	setStrValue(aRow, "t_desc_trigger.description", "t_desc_trigger.descriptiondummy_value")
	setStrValue(aRow, "t_desc_mc.decay_chain", "dummyt_desc_mc.decay_chain")
	setStrValue(aRow, "t_desc_mc.production", "dummyt_desc_mc.production")
	setStrValue(aRow, "t_physics_group.name", "t_physics_group.namedummy_value")
	setChrValue(aRow, "t_desc_primary.is_mc_data", "y")

	a = client.createPrimaryDataset(aRow, table)
	print "ID is ",a
	#key = stringp("t_primary_dataset.id")
	#print "table.getStrValue ", table.getStrValue(0,key)
	#delStringp(key)

def readPrimary() :
	aRow = dbsclient.Primarydatasetmultirow() 
	table = dbsclient.PrimarydatasetMultiTable()
	setStrValue(aRow, "t_desc_mc.description", "dummy_value")

	client.readPrimaryDataset(aRow, table)
	noOfRows = table.getNoOfRows()
	print "no of Rows ",noOfRows
	for j in range(noOfRows) :
		print "table.getStrValue", getStrValue(table, "t_desc_mc.description", j)
	table.dispose()


def writeProcessed() :
	aRow = dbsclient.Processingpathmultirow() 
	table = dbsclient.ProcessingpathMultiTable()

	setStrValue(aRow, "t_processed_dataset.name", "dummy_valuet_processed_dataset.name")
	setStrValue(aRow, "t_app_family.name", "dummyvaluet_app_family.name")
	setStrValue(aRow, "t_data_tier.name", "t_data_tier.namedummy_value")
	setStrValue(aRow, "t_application.executable", "t_application.executablemmy_value")
	setStrValue(aRow, "t_app_config.parameter_set", "dummyt_app_config.parameter_set")
	setChrValue(aRow, "t_processed_dataset.is_open", "y")
	setStrValue(aRow, "t_application.app_version", "dummyt_application.app_version")
	setStrValue(aRow, "t_app_config.conditions_version", "dummyt_app_config.conditions_version")
	setStrValue(aRow, "t_processing_path.full_path", "dummyt_processing_path.full_path")
	setStrValue(aRow, "t_primary_dataset.name", "t_primary_dataset.name")
	setStrValue(aRow, "t_collection_type.name.t_application.output_type", "dummyt_collection_type.name.t_application.output_type")
	setStrValue(aRow, "t_collection_type.name.t_application.input_type", "dummyt_collection_type.name.t_application.input_type")

	a = client.createProcessedDataset(aRow, table)
	print "ID is ",a


def readProcessed() :
	aRow = dbsclient.Processingpathmultirow() 
	table = dbsclient.ProcessingpathMultiTable()

	setStrValue(aRow, "t_primary_dataset.name", "t_primary_dataset.name")

	client.readProcessedDataset(aRow, table)
	noOfRows = table.getNoOfRows()
	print "no of Rows ",noOfRows
	for j in range(noOfRows) :
		print "table.getStrValue", getStrValue(table, "t_primary_dataset.name", j)
	table.dispose()


def writeEC() :
	aRow = dbsclient.Processingpathmultirow() 
	table = dbsclient.ProcessingpathMultiTable()

	setStrValue(aRow, "t_processed_dataset.name", "dummy_valuet_processed_dataset.name")

def readCrabEC() :
	aRow = dbsclient.Crabevcollviewmultirow() 
	table = dbsclient.CrabevcollviewMultiTable()

	setIntValue(aRow, "t_block.id", 270)

	client.readCrabEC(aRow, table)
	noOfRows = table.getNoOfRows()
	print "no of Rows ",noOfRows
	for j in range(noOfRows) :
		print "table.getStrValue", getStrValue(table, "t_info_evcoll.name", j)
	table.dispose()

#while(1):
for i in range(1) :
	#readPrimary()
	#writePrimary()
	#writeProcessed()
	#readProcessed()
	#readCrabEC()
        writeFile()
