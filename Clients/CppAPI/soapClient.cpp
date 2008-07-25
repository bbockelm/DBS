/* soapClient.cpp
   Generated by gSOAP 2.7.6e from ../../interface/Cpp/Interface.hpp
   Copyright (C) 2000-2005, Robert van Engelen, Genivia Inc. All Rights Reserved.
   This part of the software is released under one of the following licenses:
   GPL, the gSOAP public license, or Genivia's license for commercial use.
*/
#include "soapH.h"

SOAP_SOURCE_STAMP("@(#) soapClient.cpp ver 2.7.6e 2006-04-20 14:35:30 GMT")


SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__createPrimaryDataset(struct soap *soap, const char *soap_endpoint, const char *soap_action, DBS__PrimaryDataset *primaryDataset, int &primaryDatasetId)
{	struct DBS__createPrimaryDataset soap_tmp_DBS__createPrimaryDataset;
	struct DBS__createPrimaryDatasetResponse *soap_tmp_DBS__createPrimaryDatasetResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__createPrimaryDataset.primaryDataset = primaryDataset;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__createPrimaryDataset(soap, &soap_tmp_DBS__createPrimaryDataset);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__createPrimaryDataset(soap, &soap_tmp_DBS__createPrimaryDataset, "DBS:createPrimaryDataset", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__createPrimaryDataset(soap, &soap_tmp_DBS__createPrimaryDataset, "DBS:createPrimaryDataset", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_int(soap, &primaryDatasetId);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__createPrimaryDatasetResponse = soap_get_DBS__createPrimaryDatasetResponse(soap, NULL, "DBS:createPrimaryDatasetResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	primaryDatasetId = soap_tmp_DBS__createPrimaryDatasetResponse->primaryDatasetId;
	return soap_closesock(soap);
}

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__createProcessedDataset(struct soap *soap, const char *soap_endpoint, const char *soap_action, DBS__ProcessedDataset *processedDataset, int &processedDatasetId)
{	struct DBS__createProcessedDataset soap_tmp_DBS__createProcessedDataset;
	struct DBS__createProcessedDatasetResponse *soap_tmp_DBS__createProcessedDatasetResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__createProcessedDataset.processedDataset = processedDataset;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__createProcessedDataset(soap, &soap_tmp_DBS__createProcessedDataset);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__createProcessedDataset(soap, &soap_tmp_DBS__createProcessedDataset, "DBS:createProcessedDataset", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__createProcessedDataset(soap, &soap_tmp_DBS__createProcessedDataset, "DBS:createProcessedDataset", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_int(soap, &processedDatasetId);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__createProcessedDatasetResponse = soap_get_DBS__createProcessedDatasetResponse(soap, NULL, "DBS:createProcessedDatasetResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	processedDatasetId = soap_tmp_DBS__createProcessedDatasetResponse->processedDatasetId;
	return soap_closesock(soap);
}

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__createFileBlock(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, DBS__Block *block, int &fileBlockId)
{	struct DBS__createFileBlock soap_tmp_DBS__createFileBlock;
	struct DBS__createFileBlockResponse *soap_tmp_DBS__createFileBlockResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__createFileBlock.datasetPathName = datasetPathName;
	soap_tmp_DBS__createFileBlock.block = block;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__createFileBlock(soap, &soap_tmp_DBS__createFileBlock);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__createFileBlock(soap, &soap_tmp_DBS__createFileBlock, "DBS:createFileBlock", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__createFileBlock(soap, &soap_tmp_DBS__createFileBlock, "DBS:createFileBlock", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_int(soap, &fileBlockId);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__createFileBlockResponse = soap_get_DBS__createFileBlockResponse(soap, NULL, "DBS:createFileBlockResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	fileBlockId = soap_tmp_DBS__createFileBlockResponse->fileBlockId;
	return soap_closesock(soap);
}

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__insertEventCollections(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::vector<DBS__EventCollection * >eventCollectionList, int &result)
{	struct DBS__insertEventCollections soap_tmp_DBS__insertEventCollections;
	struct DBS__insertEventCollectionsResponse *soap_tmp_DBS__insertEventCollectionsResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__insertEventCollections.eventCollectionList = eventCollectionList;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__insertEventCollections(soap, &soap_tmp_DBS__insertEventCollections);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__insertEventCollections(soap, &soap_tmp_DBS__insertEventCollections, "DBS:insertEventCollections", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__insertEventCollections(soap, &soap_tmp_DBS__insertEventCollections, "DBS:insertEventCollections", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_int(soap, &result);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__insertEventCollectionsResponse = soap_get_DBS__insertEventCollectionsResponse(soap, NULL, "DBS:insertEventCollectionsResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	result = soap_tmp_DBS__insertEventCollectionsResponse->result;
	return soap_closesock(soap);
}

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__mergeEventCollections(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::vector<DBS__EventCollection * >inputEventCollectionList, DBS__EventCollection *outputEventCollection, int &result)
{	struct DBS__mergeEventCollections soap_tmp_DBS__mergeEventCollections;
	struct DBS__mergeEventCollectionsResponse *soap_tmp_DBS__mergeEventCollectionsResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__mergeEventCollections.inputEventCollectionList = inputEventCollectionList;
	soap_tmp_DBS__mergeEventCollections.outputEventCollection = outputEventCollection;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__mergeEventCollections(soap, &soap_tmp_DBS__mergeEventCollections);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__mergeEventCollections(soap, &soap_tmp_DBS__mergeEventCollections, "DBS:mergeEventCollections", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__mergeEventCollections(soap, &soap_tmp_DBS__mergeEventCollections, "DBS:mergeEventCollections", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_int(soap, &result);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__mergeEventCollectionsResponse = soap_get_DBS__mergeEventCollectionsResponse(soap, NULL, "DBS:mergeEventCollectionsResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	result = soap_tmp_DBS__mergeEventCollectionsResponse->result;
	return soap_closesock(soap);
}

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__getDatasetContents(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, bool listFiles, std::vector<DBS__Block * >&blockList)
{	struct DBS__getDatasetContents soap_tmp_DBS__getDatasetContents;
	struct DBS__getDatasetContentsResponse *soap_tmp_DBS__getDatasetContentsResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__getDatasetContents.datasetPathName = datasetPathName;
	soap_tmp_DBS__getDatasetContents.listFiles = listFiles;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__getDatasetContents(soap, &soap_tmp_DBS__getDatasetContents);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__getDatasetContents(soap, &soap_tmp_DBS__getDatasetContents, "DBS:getDatasetContents", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__getDatasetContents(soap, &soap_tmp_DBS__getDatasetContents, "DBS:getDatasetContents", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_std__vectorTemplateOfPointerToDBS__Block(soap, &blockList);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__getDatasetContentsResponse = soap_get_DBS__getDatasetContentsResponse(soap, NULL, "DBS:getDatasetContentsResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	blockList = soap_tmp_DBS__getDatasetContentsResponse->blockList;
	return soap_closesock(soap);
}

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__getDatasetFileBlocks(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, std::vector<DBS__Block * >&blockList)
{	struct DBS__getDatasetFileBlocks soap_tmp_DBS__getDatasetFileBlocks;
	struct DBS__getDatasetFileBlocksResponse *soap_tmp_DBS__getDatasetFileBlocksResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__getDatasetFileBlocks.datasetPathName = datasetPathName;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__getDatasetFileBlocks(soap, &soap_tmp_DBS__getDatasetFileBlocks);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__getDatasetFileBlocks(soap, &soap_tmp_DBS__getDatasetFileBlocks, "DBS:getDatasetFileBlocks", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__getDatasetFileBlocks(soap, &soap_tmp_DBS__getDatasetFileBlocks, "DBS:getDatasetFileBlocks", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_std__vectorTemplateOfPointerToDBS__Block(soap, &blockList);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__getDatasetFileBlocksResponse = soap_get_DBS__getDatasetFileBlocksResponse(soap, NULL, "DBS:getDatasetFileBlocksResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	blockList = soap_tmp_DBS__getDatasetFileBlocksResponse->blockList;
	return soap_closesock(soap);
}

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__listDataset(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, std::vector<std::string >&datasetList)
{	struct DBS__listDataset soap_tmp_DBS__listDataset;
	struct DBS__listDatasetResponse *soap_tmp_DBS__listDatasetResponse;
	soap->encodingStyle = NULL;
	soap_tmp_DBS__listDataset.datasetPathName = datasetPathName;
	soap_begin(soap);
	soap_serializeheader(soap);
	soap_serialize_DBS__listDataset(soap, &soap_tmp_DBS__listDataset);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_DBS__listDataset(soap, &soap_tmp_DBS__listDataset, "DBS:listDataset", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	}
	if (soap_end_count(soap))
		return soap->error;
	if (soap_connect(soap, soap_endpoint, soap_action)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_DBS__listDataset(soap, &soap_tmp_DBS__listDataset, "DBS:listDataset", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap_closesock(soap);
	soap_default_std__vectorTemplateOfstd__string(soap, &datasetList);
	if (soap_begin_recv(soap)
	 || soap_envelope_begin_in(soap)
	 || soap_recv_header(soap)
	 || soap_body_begin_in(soap))
		return soap_closesock(soap);
	soap_tmp_DBS__listDatasetResponse = soap_get_DBS__listDatasetResponse(soap, NULL, "DBS:listDatasetResponse", "");
	if (soap->error)
	{	if (soap->error == SOAP_TAG_MISMATCH && soap->level == 2)
			return soap_recv_fault(soap);
		return soap_closesock(soap);
	}
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap_closesock(soap);
	datasetList = soap_tmp_DBS__listDatasetResponse->datasetList;
	return soap_closesock(soap);
}

/* End of soapClient.cpp */