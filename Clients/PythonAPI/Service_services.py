################################################## 
# ./Service_services.py 
# generated by ZSI.wsdl2python 
# 
# 
##################################################


from Service_services_types import *
from Service_services_types import \
    tempuri_org_DBS_xsd as ns1
import urlparse, types
from ZSI.TCcompound import Struct
from ZSI import client
import ZSI

class ServiceInterface:
    def getServicePortType(self, portAddress=None, **kw):
        raise NonImplementationError, "method not implemented"


class ServiceLocator(ServiceInterface):
    ServicePortType_address = "http://venom.fnal.gov:27986"
    def getServicePortTypeAddress(self):
        return ServiceLocator.ServicePortType_address

    def getServicePortType(self, portAddress=None, **kw):
        return ServiceSOAP(portAddress or ServiceLocator.ServicePortType_address, **kw)


class ServiceSOAP:

    def __init__(self, addr, **kw):
        netloc = (urlparse.urlparse(addr)[1]).split(":") + [80,]
        if not kw.has_key("host"):
            kw["host"] = netloc[0]
        if not kw.has_key("port"):
            kw["port"] = int(netloc[1])
        if not kw.has_key("url"):
            kw["url"] =  urlparse.urlparse(addr)[2]
        self.binding = client.Binding(**kw)


    def createFileBlock(self, request):
        """
        @param: request to createFileBlockRequest::
            _block: ns1.Block_Def, optional
              _blockId: int, optional
              _blockName: str, optional
              _blockStatusName: str
              _eventCollectionList: ns1.EventCollection_Def, optional
                _collectionId: int, optional
                _collectionIndex: int, optional
                _collectionName: str
                _datasetPathName: str
                _fileList: ns1.File_Def, optional
                  _checksum: str, optional
                  _fileBlockId: int, optional
                  _fileSize: int, optional
                  _fileStatus: str
                  _fileType: str
                  _guid: str, optional
                  _id: int, optional
                  _logicalFileName: str
                _numberOfEvents: int, optional
                _parent: ns1.EventCollection_Def, optional
                _parentageType: str, optional
                _status: str, optional
              _numberOfBytes: int, optional
              _numberOfFiles: int, optional
            _datasetPathName: str

        @return: response from createFileBlockResponse::
            _fileBlockId: int
        """

        if not isinstance(request, createFileBlockRequest) and\
            not issubclass(createFileBlockRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(createFileBlockResponseWrapper())

        if not isinstance(response, createFileBlockResponse) and\
            not issubclass(createFileBlockResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response


    def createPrimaryDataset(self, request):
        """
        @param: request to createPrimaryDatasetRequest::
            _primaryDataset: ns1.PrimaryDataset_Def, optional
              _name: str
              _primaryDatasetId: int, optional

        @return: response from createPrimaryDatasetResponse::
            _primaryDatasetId: int
        """

        if not isinstance(request, createPrimaryDatasetRequest) and\
            not issubclass(createPrimaryDatasetRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(createPrimaryDatasetResponseWrapper())

        if not isinstance(response, createPrimaryDatasetResponse) and\
            not issubclass(createPrimaryDatasetResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response


    def createProcessedDataset(self, request):
        """
        @param: request to createProcessedDatasetRequest::
            _processedDataset: ns1.ProcessedDataset_Def, optional
              _id: int, optional
              _isDatasetOpen: boolean
              _primaryDatasetName: str
              _processedDatasetName: str
              _processingPath: ns1.ProcessingPath_Def, optional
                _application: ns1.Application_Def, optional
                  _applicationId: int, optional
                  _executable: str
                  _family: str
                  _parameterSet: str
                  _version: str
                _dataTier: str
                _parent: str, optional
                _pathId: int, optional

        @return: response from createProcessedDatasetResponse::
            _processedDatasetId: int
        """

        if not isinstance(request, createProcessedDatasetRequest) and\
            not issubclass(createProcessedDatasetRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(createProcessedDatasetResponseWrapper())

        if not isinstance(response, createProcessedDatasetResponse) and\
            not issubclass(createProcessedDatasetResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response


    def getDatasetContents(self, request):
        """
        @param: request to getDatasetContentsRequest::
            _datasetPathName: str
            _listFiles: boolean

        @return: response from getDatasetContentsResponse::
            _blockList: ns1.Block_Def
              _blockId: int, optional
              _blockName: str, optional
              _blockStatusName: str
              _eventCollectionList: ns1.EventCollection_Def, optional
                _collectionId: int, optional
                _collectionIndex: int, optional
                _collectionName: str
                _datasetPathName: str
                _fileList: ns1.File_Def, optional
                  _checksum: str, optional
                  _fileBlockId: int, optional
                  _fileSize: int, optional
                  _fileStatus: str
                  _fileType: str
                  _guid: str, optional
                  _id: int, optional
                  _logicalFileName: str
                _numberOfEvents: int, optional
                _parent: ns1.EventCollection_Def, optional
                _parentageType: str, optional
                _status: str, optional
              _numberOfBytes: int, optional
              _numberOfFiles: int, optional
        """

        if not isinstance(request, getDatasetContentsRequest) and\
            not issubclass(getDatasetContentsRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(getDatasetContentsResponseWrapper())

        if not isinstance(response, getDatasetContentsResponse) and\
            not issubclass(getDatasetContentsResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response


    def getDatasetFileBlocks(self, request):
        """
        @param: request to getDatasetFileBlocksRequest::
            _datasetPathName: str

        @return: response from getDatasetFileBlocksResponse::
            _blockList: ns1.Block_Def
              _blockId: int, optional
              _blockName: str, optional
              _blockStatusName: str
              _eventCollectionList: ns1.EventCollection_Def, optional
                _collectionId: int, optional
                _collectionIndex: int, optional
                _collectionName: str
                _datasetPathName: str
                _fileList: ns1.File_Def, optional
                  _checksum: str, optional
                  _fileBlockId: int, optional
                  _fileSize: int, optional
                  _fileStatus: str
                  _fileType: str
                  _guid: str, optional
                  _id: int, optional
                  _logicalFileName: str
                _numberOfEvents: int, optional
                _parent: ns1.EventCollection_Def, optional
                _parentageType: str, optional
                _status: str, optional
              _numberOfBytes: int, optional
              _numberOfFiles: int, optional
        """

        if not isinstance(request, getDatasetFileBlocksRequest) and\
            not issubclass(getDatasetFileBlocksRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(getDatasetFileBlocksResponseWrapper())

        if not isinstance(response, getDatasetFileBlocksResponse) and\
            not issubclass(getDatasetFileBlocksResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response


    def insertEventCollections(self, request):
        """
        @param: request to insertEventCollectionsRequest::
            _eventCollectionList: ns1.EventCollection_Def, optional
              _collectionId: int, optional
              _collectionIndex: int, optional
              _collectionName: str
              _datasetPathName: str
              _fileList: ns1.File_Def, optional
                _checksum: str, optional
                _fileBlockId: int, optional
                _fileSize: int, optional
                _fileStatus: str
                _fileType: str
                _guid: str, optional
                _id: int, optional
                _logicalFileName: str
              _numberOfEvents: int, optional
              _parent: ns1.EventCollection_Def, optional
              _parentageType: str, optional
              _status: str, optional

        @return: response from insertEventCollectionsResponse::
            _result: int
        """

        if not isinstance(request, insertEventCollectionsRequest) and\
            not issubclass(insertEventCollectionsRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(insertEventCollectionsResponseWrapper())

        if not isinstance(response, insertEventCollectionsResponse) and\
            not issubclass(insertEventCollectionsResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response


    def listDataset(self, request):
        """
        @param: request to listDatasetRequest::
            _datasetPathName: str

        @return: response from listDatasetResponse::
            _datasetList: str
        """

        if not isinstance(request, listDatasetRequest) and\
            not issubclass(listDatasetRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(listDatasetResponseWrapper())

        if not isinstance(response, listDatasetResponse) and\
            not issubclass(listDatasetResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response


    def mergeEventCollections(self, request):
        """
        @param: request to mergeEventCollectionsRequest::
            _inputEventCollectionList: ns1.EventCollection_Def, optional
              _collectionId: int, optional
              _collectionIndex: int, optional
              _collectionName: str
              _datasetPathName: str
              _fileList: ns1.File_Def, optional
                _checksum: str, optional
                _fileBlockId: int, optional
                _fileSize: int, optional
                _fileStatus: str
                _fileType: str
                _guid: str, optional
                _id: int, optional
                _logicalFileName: str
              _numberOfEvents: int, optional
              _parent: ns1.EventCollection_Def, optional
              _parentageType: str, optional
              _status: str, optional
            _outputEventCollection: ns1.EventCollection_Def, optional

        @return: response from mergeEventCollectionsResponse::
            _result: int
        """

        if not isinstance(request, mergeEventCollectionsRequest) and\
            not issubclass(mergeEventCollectionsRequest, request.__class__):
            raise TypeError, "%s incorrect request type" %(request.__class__)
        kw = {}
        response = self.binding.Send(None, None, request, soapaction="", **kw)
        response = self.binding.Receive(mergeEventCollectionsResponseWrapper())

        if not isinstance(response, mergeEventCollectionsResponse) and\
            not issubclass(mergeEventCollectionsResponse, response.__class__):
            raise TypeError, "%s incorrect response type" %(response.__class__)
        return response



class createFileBlockRequest(ns1.createFileBlock_Dec): 
    if not hasattr( ns1.createFileBlock_Dec(), "typecode" ):
        typecode = ns1.createFileBlock_Dec()

    def __init__(self, name=None, ns=None):
        ns1.createFileBlock_Dec.__init__(self, name=None, ns=None)

class createFileBlockRequestWrapper(createFileBlockRequest):
    """wrapper for document:literal message"""

    typecode = createFileBlockRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        createFileBlockRequest.__init__( self, name=None, ns=None )

class createFileBlockResponse(ns1.createFileBlockResponse_Dec): 
    if not hasattr( ns1.createFileBlockResponse_Dec(), "typecode" ):
        typecode = ns1.createFileBlockResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.createFileBlockResponse_Dec.__init__(self, name=None, ns=None)

class createFileBlockResponseWrapper(createFileBlockResponse):
    """wrapper for document:literal message"""

    typecode = createFileBlockResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        createFileBlockResponse.__init__( self, name=None, ns=None )

class createPrimaryDatasetRequest(ns1.createPrimaryDataset_Dec): 
    if not hasattr( ns1.createPrimaryDataset_Dec(), "typecode" ):
        typecode = ns1.createPrimaryDataset_Dec()

    def __init__(self, name=None, ns=None):
        ns1.createPrimaryDataset_Dec.__init__(self, name=None, ns=None)

class createPrimaryDatasetRequestWrapper(createPrimaryDatasetRequest):
    """wrapper for document:literal message"""

    typecode = createPrimaryDatasetRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        createPrimaryDatasetRequest.__init__( self, name=None, ns=None )

class createPrimaryDatasetResponse(ns1.createPrimaryDatasetResponse_Dec): 
    if not hasattr( ns1.createPrimaryDatasetResponse_Dec(), "typecode" ):
        typecode = ns1.createPrimaryDatasetResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.createPrimaryDatasetResponse_Dec.__init__(self, name=None, ns=None)

class createPrimaryDatasetResponseWrapper(createPrimaryDatasetResponse):
    """wrapper for document:literal message"""

    typecode = createPrimaryDatasetResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        createPrimaryDatasetResponse.__init__( self, name=None, ns=None )

class createProcessedDatasetRequest(ns1.createProcessedDataset_Dec): 
    if not hasattr( ns1.createProcessedDataset_Dec(), "typecode" ):
        typecode = ns1.createProcessedDataset_Dec()

    def __init__(self, name=None, ns=None):
        ns1.createProcessedDataset_Dec.__init__(self, name=None, ns=None)

class createProcessedDatasetRequestWrapper(createProcessedDatasetRequest):
    """wrapper for document:literal message"""

    typecode = createProcessedDatasetRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        createProcessedDatasetRequest.__init__( self, name=None, ns=None )

class createProcessedDatasetResponse(ns1.createProcessedDatasetResponse_Dec): 
    if not hasattr( ns1.createProcessedDatasetResponse_Dec(), "typecode" ):
        typecode = ns1.createProcessedDatasetResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.createProcessedDatasetResponse_Dec.__init__(self, name=None, ns=None)

class createProcessedDatasetResponseWrapper(createProcessedDatasetResponse):
    """wrapper for document:literal message"""

    typecode = createProcessedDatasetResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        createProcessedDatasetResponse.__init__( self, name=None, ns=None )

class getDatasetContentsRequest(ns1.getDatasetContents_Dec): 
    if not hasattr( ns1.getDatasetContents_Dec(), "typecode" ):
        typecode = ns1.getDatasetContents_Dec()

    def __init__(self, name=None, ns=None):
        ns1.getDatasetContents_Dec.__init__(self, name=None, ns=None)

class getDatasetContentsRequestWrapper(getDatasetContentsRequest):
    """wrapper for document:literal message"""

    typecode = getDatasetContentsRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        getDatasetContentsRequest.__init__( self, name=None, ns=None )

class getDatasetContentsResponse(ns1.getDatasetContentsResponse_Dec): 
    if not hasattr( ns1.getDatasetContentsResponse_Dec(), "typecode" ):
        typecode = ns1.getDatasetContentsResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.getDatasetContentsResponse_Dec.__init__(self, name=None, ns=None)

class getDatasetContentsResponseWrapper(getDatasetContentsResponse):
    """wrapper for document:literal message"""

    typecode = getDatasetContentsResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        getDatasetContentsResponse.__init__( self, name=None, ns=None )

class getDatasetFileBlocksRequest(ns1.getDatasetFileBlocks_Dec): 
    if not hasattr( ns1.getDatasetFileBlocks_Dec(), "typecode" ):
        typecode = ns1.getDatasetFileBlocks_Dec()

    def __init__(self, name=None, ns=None):
        ns1.getDatasetFileBlocks_Dec.__init__(self, name=None, ns=None)

class getDatasetFileBlocksRequestWrapper(getDatasetFileBlocksRequest):
    """wrapper for document:literal message"""

    typecode = getDatasetFileBlocksRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        getDatasetFileBlocksRequest.__init__( self, name=None, ns=None )

class getDatasetFileBlocksResponse(ns1.getDatasetFileBlocksResponse_Dec): 
    if not hasattr( ns1.getDatasetFileBlocksResponse_Dec(), "typecode" ):
        typecode = ns1.getDatasetFileBlocksResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.getDatasetFileBlocksResponse_Dec.__init__(self, name=None, ns=None)

class getDatasetFileBlocksResponseWrapper(getDatasetFileBlocksResponse):
    """wrapper for document:literal message"""

    typecode = getDatasetFileBlocksResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        getDatasetFileBlocksResponse.__init__( self, name=None, ns=None )

class insertEventCollectionsRequest(ns1.insertEventCollections_Dec): 
    if not hasattr( ns1.insertEventCollections_Dec(), "typecode" ):
        typecode = ns1.insertEventCollections_Dec()

    def __init__(self, name=None, ns=None):
        ns1.insertEventCollections_Dec.__init__(self, name=None, ns=None)

class insertEventCollectionsRequestWrapper(insertEventCollectionsRequest):
    """wrapper for document:literal message"""

    typecode = insertEventCollectionsRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        insertEventCollectionsRequest.__init__( self, name=None, ns=None )

class insertEventCollectionsResponse(ns1.insertEventCollectionsResponse_Dec): 
    if not hasattr( ns1.insertEventCollectionsResponse_Dec(), "typecode" ):
        typecode = ns1.insertEventCollectionsResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.insertEventCollectionsResponse_Dec.__init__(self, name=None, ns=None)

class insertEventCollectionsResponseWrapper(insertEventCollectionsResponse):
    """wrapper for document:literal message"""

    typecode = insertEventCollectionsResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        insertEventCollectionsResponse.__init__( self, name=None, ns=None )

class listDatasetRequest(ns1.listDataset_Dec): 
    if not hasattr( ns1.listDataset_Dec(), "typecode" ):
        typecode = ns1.listDataset_Dec()

    def __init__(self, name=None, ns=None):
        ns1.listDataset_Dec.__init__(self, name=None, ns=None)

class listDatasetRequestWrapper(listDatasetRequest):
    """wrapper for document:literal message"""

    typecode = listDatasetRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        listDatasetRequest.__init__( self, name=None, ns=None )

class listDatasetResponse(ns1.listDatasetResponse_Dec): 
    if not hasattr( ns1.listDatasetResponse_Dec(), "typecode" ):
        typecode = ns1.listDatasetResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.listDatasetResponse_Dec.__init__(self, name=None, ns=None)

class listDatasetResponseWrapper(listDatasetResponse):
    """wrapper for document:literal message"""

    typecode = listDatasetResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        listDatasetResponse.__init__( self, name=None, ns=None )

class mergeEventCollectionsRequest(ns1.mergeEventCollections_Dec): 
    if not hasattr( ns1.mergeEventCollections_Dec(), "typecode" ):
        typecode = ns1.mergeEventCollections_Dec()

    def __init__(self, name=None, ns=None):
        ns1.mergeEventCollections_Dec.__init__(self, name=None, ns=None)

class mergeEventCollectionsRequestWrapper(mergeEventCollectionsRequest):
    """wrapper for document:literal message"""

    typecode = mergeEventCollectionsRequest( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        mergeEventCollectionsRequest.__init__( self, name=None, ns=None )

class mergeEventCollectionsResponse(ns1.mergeEventCollectionsResponse_Dec): 
    if not hasattr( ns1.mergeEventCollectionsResponse_Dec(), "typecode" ):
        typecode = ns1.mergeEventCollectionsResponse_Dec()

    def __init__(self, name=None, ns=None):
        ns1.mergeEventCollectionsResponse_Dec.__init__(self, name=None, ns=None)

class mergeEventCollectionsResponseWrapper(mergeEventCollectionsResponse):
    """wrapper for document:literal message"""

    typecode = mergeEventCollectionsResponse( name=None, ns=None ).typecode
    def __init__( self, name=None, ns=None, **kw ):
        mergeEventCollectionsResponse.__init__( self, name=None, ns=None )
