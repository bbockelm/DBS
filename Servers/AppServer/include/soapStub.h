/* soapStub.h
   Generated by gSOAP 2.7.6e from ../../interface/Cpp/Interface.hpp
   Copyright (C) 2000-2005, Robert van Engelen, Genivia Inc. All Rights Reserved.
   This part of the software is released under one of the following licenses:
   GPL, the gSOAP public license, or Genivia's license for commercial use.
*/

#ifndef soapStub_H
#define soapStub_H
#include <vector>
#include "stdsoap2.h"

/******************************************************************************\
 *                                                                            *
 * Enumerations                                                               *
 *                                                                            *
\******************************************************************************/


/******************************************************************************\
 *                                                                            *
 * Classes and Structs                                                        *
 *                                                                            *
\******************************************************************************/


#if 0 /* volatile type: do no redeclare */

#endif

#ifndef SOAP_TYPE_DBS__PrimaryDataset
#define SOAP_TYPE_DBS__PrimaryDataset (7)
/* DBS:PrimaryDataset */
class SOAP_CMAC DBS__PrimaryDataset
{
public:
	int *primaryDatasetId;	/* optional element of type xsd:int */
	std::string name;	/* required element of type xsd:string */
public:
	virtual int soap_type() const { return 7; } /* = unique id SOAP_TYPE_DBS__PrimaryDataset */
	virtual void soap_default(struct soap*);
	virtual void soap_serialize(struct soap*) const;
	virtual int soap_put(struct soap*, const char*, const char*) const;
	virtual int soap_out(struct soap*, const char*, int, const char*) const;
	virtual void *soap_get(struct soap*, const char*, const char*);
	virtual void *soap_in(struct soap*, const char*, const char*);
	         DBS__PrimaryDataset() : primaryDatasetId(NULL) { }
	virtual ~DBS__PrimaryDataset() { }
};
#endif

#ifndef SOAP_TYPE_DBS__Application
#define SOAP_TYPE_DBS__Application (10)
/* DBS:Application */
class SOAP_CMAC DBS__Application
{
public:
	int *applicationId;	/* optional element of type xsd:int */
	std::string executable;	/* required element of type xsd:string */
	std::string version;	/* required element of type xsd:string */
	std::string family;	/* required element of type xsd:string */
	std::string parameterSet;	/* required element of type xsd:string */
public:
	virtual int soap_type() const { return 10; } /* = unique id SOAP_TYPE_DBS__Application */
	virtual void soap_default(struct soap*);
	virtual void soap_serialize(struct soap*) const;
	virtual int soap_put(struct soap*, const char*, const char*) const;
	virtual int soap_out(struct soap*, const char*, int, const char*) const;
	virtual void *soap_get(struct soap*, const char*, const char*);
	virtual void *soap_in(struct soap*, const char*, const char*);
	         DBS__Application() : applicationId(NULL) { }
	virtual ~DBS__Application() { }
};
#endif

#ifndef SOAP_TYPE_DBS__ProcessingPath
#define SOAP_TYPE_DBS__ProcessingPath (11)
/* DBS:ProcessingPath */
class SOAP_CMAC DBS__ProcessingPath
{
public:
	int *pathId;	/* optional element of type xsd:int */
	std::string parent;	/* optional element of type xsd:string */
	DBS__Application *application;	/* required element of type DBS:Application */
	std::string dataTier;	/* required element of type xsd:string */
public:
	virtual int soap_type() const { return 11; } /* = unique id SOAP_TYPE_DBS__ProcessingPath */
	virtual void soap_default(struct soap*);
	virtual void soap_serialize(struct soap*) const;
	virtual int soap_put(struct soap*, const char*, const char*) const;
	virtual int soap_out(struct soap*, const char*, int, const char*) const;
	virtual void *soap_get(struct soap*, const char*, const char*);
	virtual void *soap_in(struct soap*, const char*, const char*);
	         DBS__ProcessingPath() : pathId(NULL), application(NULL) { }
	virtual ~DBS__ProcessingPath() { }
};
#endif

#ifndef SOAP_TYPE_DBS__ProcessedDataset
#define SOAP_TYPE_DBS__ProcessedDataset (13)
/* DBS:ProcessedDataset */
class SOAP_CMAC DBS__ProcessedDataset
{
public:
	int *id;	/* optional element of type xsd:int */
	std::string processedDatasetName;	/* required element of type xsd:string */
	std::string primaryDatasetName;	/* required element of type xsd:string */
	DBS__ProcessingPath *processingPath;	/* required element of type DBS:ProcessingPath */
	bool isDatasetOpen;	/* required element of type xsd:boolean */
public:
	virtual int soap_type() const { return 13; } /* = unique id SOAP_TYPE_DBS__ProcessedDataset */
	virtual void soap_default(struct soap*);
	virtual void soap_serialize(struct soap*) const;
	virtual int soap_put(struct soap*, const char*, const char*) const;
	virtual int soap_out(struct soap*, const char*, int, const char*) const;
	virtual void *soap_get(struct soap*, const char*, const char*);
	virtual void *soap_in(struct soap*, const char*, const char*);
	         DBS__ProcessedDataset() : id(NULL), processingPath(NULL) { }
	virtual ~DBS__ProcessedDataset() { }
};
#endif

#ifndef SOAP_TYPE_DBS__File
#define SOAP_TYPE_DBS__File (16)
/* DBS:File */
class SOAP_CMAC DBS__File
{
public:
	int *id;	/* optional element of type xsd:int */
	std::string guid;	/* optional element of type xsd:string */
	std::string logicalFileName;	/* required element of type xsd:string */
	std::string checksum;	/* optional element of type xsd:string */
	int *fileSize;	/* optional element of type xsd:int */
	std::string fileStatus;	/* required element of type xsd:string */
	std::string fileType;	/* required element of type xsd:string */
	int *fileBlockId;	/* required element of type xsd:int */
public:
	virtual int soap_type() const { return 16; } /* = unique id SOAP_TYPE_DBS__File */
	virtual void soap_default(struct soap*);
	virtual void soap_serialize(struct soap*) const;
	virtual int soap_put(struct soap*, const char*, const char*) const;
	virtual int soap_out(struct soap*, const char*, int, const char*) const;
	virtual void *soap_get(struct soap*, const char*, const char*);
	virtual void *soap_in(struct soap*, const char*, const char*);
	         DBS__File() : id(NULL), fileSize(NULL), fileBlockId(NULL) { }
	virtual ~DBS__File() { }
};
#endif

#ifndef SOAP_TYPE_DBS__EventCollection
#define SOAP_TYPE_DBS__EventCollection (17)
/* DBS:EventCollection */
class SOAP_CMAC DBS__EventCollection
{
public:
	int *collectionId;	/* optional element of type xsd:int */
	int *collectionIndex;	/* required element of type xsd:int */
	int *numberOfEvents;	/* optional element of type xsd:int */
	std::string collectionName;	/* required element of type xsd:string */
	std::string datasetPathName;	/* required element of type xsd:string */
	DBS__EventCollection *parent;	/* optional element of type DBS:EventCollection */
	std::string parentageType;	/* optional element of type xsd:string */
	std::vector<DBS__File * >fileList;	/* optional element of type DBS:File */
public:
	virtual int soap_type() const { return 17; } /* = unique id SOAP_TYPE_DBS__EventCollection */
	virtual void soap_default(struct soap*);
	virtual void soap_serialize(struct soap*) const;
	virtual int soap_put(struct soap*, const char*, const char*) const;
	virtual int soap_out(struct soap*, const char*, int, const char*) const;
	virtual void *soap_get(struct soap*, const char*, const char*);
	virtual void *soap_in(struct soap*, const char*, const char*);
	         DBS__EventCollection() : collectionId(NULL), collectionIndex(NULL), numberOfEvents(NULL), parent(NULL) { }
	virtual ~DBS__EventCollection() { }
};
#endif

#ifndef SOAP_TYPE_DBS__Block
#define SOAP_TYPE_DBS__Block (21)
/* DBS:Block */
class SOAP_CMAC DBS__Block
{
public:
	int *blockId;	/* optional element of type xsd:int */
	std::string blockStatusName;	/* required element of type xsd:string */
	std::string blockName;	/* optional element of type xsd:string */
	int *numberOfFiles;	/* required element of type xsd:int */
	int *numberOfBytes;	/* required element of type xsd:int */
	std::vector<DBS__EventCollection * >eventCollectionList;	/* optional element of type DBS:EventCollection */
public:
	virtual int soap_type() const { return 21; } /* = unique id SOAP_TYPE_DBS__Block */
	virtual void soap_default(struct soap*);
	virtual void soap_serialize(struct soap*) const;
	virtual int soap_put(struct soap*, const char*, const char*) const;
	virtual int soap_out(struct soap*, const char*, int, const char*) const;
	virtual void *soap_get(struct soap*, const char*, const char*);
	virtual void *soap_in(struct soap*, const char*, const char*);
	         DBS__Block() : blockId(NULL), numberOfFiles(NULL), numberOfBytes(NULL) { }
	virtual ~DBS__Block() { }
};
#endif

#ifndef SOAP_TYPE_DBS__createPrimaryDatasetResponse
#define SOAP_TYPE_DBS__createPrimaryDatasetResponse (26)
/* DBS:createPrimaryDatasetResponse */
struct DBS__createPrimaryDatasetResponse
{
public:
	int primaryDatasetId;	/* RPC return element */	/* required element of type xsd:int */
};
#endif

#ifndef SOAP_TYPE_DBS__createPrimaryDataset
#define SOAP_TYPE_DBS__createPrimaryDataset (27)
/* DBS:createPrimaryDataset */
struct DBS__createPrimaryDataset
{
public:
	DBS__PrimaryDataset *primaryDataset;	/* optional element of type DBS:PrimaryDataset */
};
#endif

#ifndef SOAP_TYPE_DBS__createProcessedDatasetResponse
#define SOAP_TYPE_DBS__createProcessedDatasetResponse (30)
/* DBS:createProcessedDatasetResponse */
struct DBS__createProcessedDatasetResponse
{
public:
	int processedDatasetId;	/* RPC return element */	/* required element of type xsd:int */
};
#endif

#ifndef SOAP_TYPE_DBS__createProcessedDataset
#define SOAP_TYPE_DBS__createProcessedDataset (31)
/* DBS:createProcessedDataset */
struct DBS__createProcessedDataset
{
public:
	DBS__ProcessedDataset *processedDataset;	/* optional element of type DBS:ProcessedDataset */
};
#endif

#ifndef SOAP_TYPE_DBS__createFileBlockResponse
#define SOAP_TYPE_DBS__createFileBlockResponse (34)
/* DBS:createFileBlockResponse */
struct DBS__createFileBlockResponse
{
public:
	int fileBlockId;	/* RPC return element */	/* required element of type xsd:int */
};
#endif

#ifndef SOAP_TYPE_DBS__createFileBlock
#define SOAP_TYPE_DBS__createFileBlock (35)
/* DBS:createFileBlock */
struct DBS__createFileBlock
{
public:
	std::string datasetPathName;	/* required element of type xsd:string */
	DBS__Block *block;	/* optional element of type DBS:Block */
};
#endif

#ifndef SOAP_TYPE_DBS__insertEventCollectionsResponse
#define SOAP_TYPE_DBS__insertEventCollectionsResponse (37)
/* DBS:insertEventCollectionsResponse */
struct DBS__insertEventCollectionsResponse
{
public:
	int result;	/* RPC return element */	/* required element of type xsd:int */
};
#endif

#ifndef SOAP_TYPE_DBS__insertEventCollections
#define SOAP_TYPE_DBS__insertEventCollections (38)
/* DBS:insertEventCollections */
struct DBS__insertEventCollections
{
public:
	std::vector<DBS__EventCollection * >eventCollectionList;	/* required element of type DBS:EventCollection */
};
#endif

#ifndef SOAP_TYPE_DBS__getDatasetContentsResponse
#define SOAP_TYPE_DBS__getDatasetContentsResponse (42)
/* DBS:getDatasetContentsResponse */
struct DBS__getDatasetContentsResponse
{
public:
	std::vector<DBS__Block * >blockList;	/* RPC return element */	/* required element of type DBS:Block */
};
#endif

#ifndef SOAP_TYPE_DBS__getDatasetContents
#define SOAP_TYPE_DBS__getDatasetContents (43)
/* DBS:getDatasetContents */
struct DBS__getDatasetContents
{
public:
	std::string datasetPathName;	/* required element of type xsd:string */
	bool listFiles;	/* required element of type xsd:boolean */
};
#endif

#ifndef SOAP_TYPE_DBS__getDatasetFileBlocksResponse
#define SOAP_TYPE_DBS__getDatasetFileBlocksResponse (45)
/* DBS:getDatasetFileBlocksResponse */
struct DBS__getDatasetFileBlocksResponse
{
public:
	std::vector<DBS__Block * >blockList;	/* RPC return element */	/* required element of type DBS:Block */
};
#endif

#ifndef SOAP_TYPE_DBS__getDatasetFileBlocks
#define SOAP_TYPE_DBS__getDatasetFileBlocks (46)
/* DBS:getDatasetFileBlocks */
struct DBS__getDatasetFileBlocks
{
public:
	std::string datasetPathName;	/* required element of type xsd:string */
};
#endif

#ifndef SOAP_TYPE_DBS__listDatasetResponse
#define SOAP_TYPE_DBS__listDatasetResponse (50)
/* DBS:listDatasetResponse */
struct DBS__listDatasetResponse
{
public:
	std::vector<std::string >datasetList;	/* RPC return element */	/* required element of type xsd:string */
};
#endif

#ifndef SOAP_TYPE_DBS__listDataset
#define SOAP_TYPE_DBS__listDataset (51)
/* DBS:listDataset */
struct DBS__listDataset
{
public:
	std::string datasetPathName;	/* required element of type xsd:string */
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Header
#define SOAP_TYPE_SOAP_ENV__Header (54)
/* SOAP Header: */
struct SOAP_ENV__Header
{
public:
	void *dummy;	/* transient */
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Code
#define SOAP_TYPE_SOAP_ENV__Code (55)
/* SOAP Fault Code: */
struct SOAP_ENV__Code
{
public:
	char *SOAP_ENV__Value;	/* optional element of type QName */
	struct SOAP_ENV__Code *SOAP_ENV__Subcode;	/* optional element of type SOAP-ENV:Code */
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Detail
#define SOAP_TYPE_SOAP_ENV__Detail (57)
/* SOAP-ENV:Detail */
struct SOAP_ENV__Detail
{
public:
	int __type;	/* any type of element <fault> (defined below) */
	void *fault;	/* transient */
	char *__any;
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Reason
#define SOAP_TYPE_SOAP_ENV__Reason (58)
/* SOAP-ENV:Reason */
struct SOAP_ENV__Reason
{
public:
	char *SOAP_ENV__Text;	/* optional element of type xsd:string */
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Fault
#define SOAP_TYPE_SOAP_ENV__Fault (59)
/* SOAP Fault: */
struct SOAP_ENV__Fault
{
public:
	char *faultcode;	/* optional element of type QName */
	char *faultstring;	/* optional element of type xsd:string */
	char *faultactor;	/* optional element of type xsd:string */
	struct SOAP_ENV__Detail *detail;	/* optional element of type SOAP-ENV:Detail */
	struct SOAP_ENV__Code *SOAP_ENV__Code;	/* optional element of type SOAP-ENV:Code */
	struct SOAP_ENV__Reason *SOAP_ENV__Reason;	/* optional element of type SOAP-ENV:Reason */
	char *SOAP_ENV__Node;	/* optional element of type xsd:string */
	char *SOAP_ENV__Role;	/* optional element of type xsd:string */
	struct SOAP_ENV__Detail *SOAP_ENV__Detail;	/* optional element of type SOAP-ENV:Detail */
};
#endif

/******************************************************************************\
 *                                                                            *
 * Types with Custom Serializers                                              *
 *                                                                            *
\******************************************************************************/


/******************************************************************************\
 *                                                                            *
 * Typedefs                                                                   *
 *                                                                            *
\******************************************************************************/

#ifndef SOAP_TYPE__XML
#define SOAP_TYPE__XML (4)
typedef char *_XML;
#endif

#ifndef SOAP_TYPE__QName
#define SOAP_TYPE__QName (5)
typedef char *_QName;
#endif


/******************************************************************************\
 *                                                                            *
 * Typedef Synonyms                                                           *
 *                                                                            *
\******************************************************************************/


/******************************************************************************\
 *                                                                            *
 * Externals                                                                  *
 *                                                                            *
\******************************************************************************/


/******************************************************************************\
 *                                                                            *
 * Service Operations                                                         *
 *                                                                            *
\******************************************************************************/


SOAP_FMAC5 int SOAP_FMAC6 DBS__createPrimaryDataset(struct soap*, DBS__PrimaryDataset *primaryDataset, int &primaryDatasetId);

SOAP_FMAC5 int SOAP_FMAC6 DBS__createProcessedDataset(struct soap*, DBS__ProcessedDataset *processedDataset, int &processedDatasetId);

SOAP_FMAC5 int SOAP_FMAC6 DBS__createFileBlock(struct soap*, std::string datasetPathName, DBS__Block *block, int &fileBlockId);

SOAP_FMAC5 int SOAP_FMAC6 DBS__insertEventCollections(struct soap*, std::vector<DBS__EventCollection * >eventCollectionList, int &result);

SOAP_FMAC5 int SOAP_FMAC6 DBS__getDatasetContents(struct soap*, std::string datasetPathName, bool listFiles, std::vector<DBS__Block * >&blockList);

SOAP_FMAC5 int SOAP_FMAC6 DBS__getDatasetFileBlocks(struct soap*, std::string datasetPathName, std::vector<DBS__Block * >&blockList);

SOAP_FMAC5 int SOAP_FMAC6 DBS__listDataset(struct soap*, std::string datasetPathName, std::vector<std::string >&datasetList);

/******************************************************************************\
 *                                                                            *
 * Stubs                                                                      *
 *                                                                            *
\******************************************************************************/


SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__createPrimaryDataset(struct soap *soap, const char *soap_endpoint, const char *soap_action, DBS__PrimaryDataset *primaryDataset, int &primaryDatasetId);

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__createProcessedDataset(struct soap *soap, const char *soap_endpoint, const char *soap_action, DBS__ProcessedDataset *processedDataset, int &processedDatasetId);

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__createFileBlock(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, DBS__Block *block, int &fileBlockId);

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__insertEventCollections(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::vector<DBS__EventCollection * >eventCollectionList, int &result);

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__getDatasetContents(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, bool listFiles, std::vector<DBS__Block * >&blockList);

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__getDatasetFileBlocks(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, std::vector<DBS__Block * >&blockList);

SOAP_FMAC5 int SOAP_FMAC6 soap_call_DBS__listDataset(struct soap *soap, const char *soap_endpoint, const char *soap_action, std::string datasetPathName, std::vector<std::string >&datasetList);

/******************************************************************************\
 *                                                                            *
 * Skeletons                                                                  *
 *                                                                            *
\******************************************************************************/

SOAP_FMAC5 int SOAP_FMAC6 soap_serve(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_request(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_DBS__createPrimaryDataset(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_DBS__createProcessedDataset(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_DBS__createFileBlock(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_DBS__insertEventCollections(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_DBS__getDatasetContents(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_DBS__getDatasetFileBlocks(struct soap*);

SOAP_FMAC5 int SOAP_FMAC6 soap_serve_DBS__listDataset(struct soap*);

#endif

/* End of soapStub.h */
