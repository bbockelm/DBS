
import dbsclient



def readPrimaryDataset(prdsName):
	priDSVector = dbsclient.PriDSVector()
	client.readPrimaryDataset(prdsName, priDSVector)
	print "no of Primary Datasets ",priDSVector.size()
	for i in range( priDSVector.size() ):
		print "************************************************************************************************"
		primaryDsId = dbsclient.intp_value(priDSVector[i].t_primary_dataset_id.getValue())
		print "primary_dataset_id = ",primaryDsId
		print "primary_dataset_name = ",dbsclient.stringp_value(priDSVector[i].t_primary_dataset_name.getValue())
		print "primary_dataset_description = ",dbsclient.intp_value(priDSVector[i].t_primary_dataset_description.getValue())
		readProcessingPath(primaryDsId)



def readProcessingPath(primaryDsId):
	proPathVector = dbsclient.ProPathVector()
	client.readProcessingPath(primaryDsId, proPathVector)
	print "no of Processing Path ",proPathVector.size()
	for i in range( proPathVector.size() ):
		print "************************************************************************************************"
		proDsId = dbsclient.intp_value(proPathVector[i].t_processed_dataset_id.getValue())
		print "processed_dataset_id = ",proDsId
		print "data tiername = ",dbsclient.stringp_value(proPathVector[i].t_data_tier_name.getValue())
		readEvColls(proDsId)


def readEvColls(proDsId):
	evCollVector = dbsclient.EVCollVector()
	client.readEvColls(proDsId, evCollVector)
	print "no of Event Collections ",evCollVector.size()
	for i in range( evCollVector.size() ):
		evCollId = dbsclient.intp_value(evCollVector[i].t_event_collection_id.getValue())
		print "************************************************************************************************"
		print "status = ",dbsclient.intp_value(evCollVector[i].t_info_evcoll_status.getValue())
		print "validation_status = ",dbsclient.intp_value(evCollVector[i].t_info_evcoll_validation_status.getValue())
		print "processed_dataset = ",dbsclient.intp_value(evCollVector[i].t_event_collection_processed_dataset.getValue())
		print "events = ",dbsclient.intp_value(evCollVector[i].t_info_evcoll_events.getValue())
		print "collection_index = ",dbsclient.intp_value(evCollVector[i].t_event_collection_collection_index.getValue())
		print "id = ",evCollId
		print "name = ",dbsclient.stringp_value(evCollVector[i].t_info_evcoll_name.getValue())
		readEvCollFiles(evCollId)

def readEvCollFiles(evCollId):
	evCollFileVector = dbsclient.EVCollFileVector()
	client.readEvCollFiles(evCollId, evCollFileVector)
	print "no of Files in Event Collection",evCollFileVector.size()
	for i in range( evCollFileVector.size() ):
		print "************************************************************************************************"
		print "file_logical_name = ",dbsclient.stringp_value(evCollFileVector[i].t_file_logical_name.getValue())
		print "file_guid = ",dbsclient.stringp_value(evCollFileVector[i].t_file_guid.getValue())
		print "file_inblock = ",dbsclient.intp_value(evCollFileVector[i].t_file_inblock.getValue())




client = dbsclient.DBSClient()
prdsName = dbsclient.DBSClient.str("mu03b_MBforPU")
readPrimaryDataset(prdsName)

