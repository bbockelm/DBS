################################################## 
# ./Service_services_types.py 
# generated by ZSI.wsdl2python 
# 
# 
##################################################


import ZSI
from ZSI.TCcompound import Struct

############################## 
# targetNamespace 
#
# http://tempuri.org/DBS.xsd 
##############################


# imported as: ns1
class tempuri_org_DBS_xsd:
    targetNamespace = 'http://tempuri.org/DBS.xsd'

    class PrimaryDataset_Def(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        type = 'PrimaryDataset'

        def __init__(self, name=None, ns=None, **kw):
            # internal vars
            self._primaryDatasetId = None
            self._name = None

            TClist = [ZSI.TCnumbers.Iint(pname="primaryDatasetId",aname="_primaryDatasetId", optional=1), ZSI.TC.String(pname="name",aname="_name"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_primaryDatasetId(self):
            return self._primaryDatasetId

        def Set_primaryDatasetId(self,_primaryDatasetId):
            self._primaryDatasetId = _primaryDatasetId

        def Get_name(self):
            return self._name

        def Set_name(self,_name):
            self._name = _name


    class Application_Def(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        type = 'Application'

        def __init__(self, name=None, ns=None, **kw):
            # internal vars
            self._applicationId = None
            self._executable = None
            self._version = None
            self._family = None
            self._parameterSet = None

            TClist = [ZSI.TCnumbers.Iint(pname="applicationId",aname="_applicationId", optional=1), ZSI.TC.String(pname="executable",aname="_executable"), ZSI.TC.String(pname="version",aname="_version"), ZSI.TC.String(pname="family",aname="_family"), ZSI.TC.String(pname="parameterSet",aname="_parameterSet"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_applicationId(self):
            return self._applicationId

        def Set_applicationId(self,_applicationId):
            self._applicationId = _applicationId

        def Get_executable(self):
            return self._executable

        def Set_executable(self,_executable):
            self._executable = _executable

        def Get_version(self):
            return self._version

        def Set_version(self,_version):
            self._version = _version

        def Get_family(self):
            return self._family

        def Set_family(self,_family):
            self._family = _family

        def Get_parameterSet(self):
            return self._parameterSet

        def Set_parameterSet(self,_parameterSet):
            self._parameterSet = _parameterSet


    class File_Def(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        type = 'File'

        def __init__(self, name=None, ns=None, **kw):
            # internal vars
            self._id = None
            self._guid = None
            self._logicalFileName = None
            self._checksum = None
            self._fileSize = None
            self._fileStatus = None
            self._fileType = None
            self._fileBlockId = None

            TClist = [ZSI.TCnumbers.Iint(pname="id",aname="_id", optional=1), ZSI.TC.String(pname="guid",aname="_guid", optional=1), ZSI.TC.String(pname="logicalFileName",aname="_logicalFileName"), ZSI.TC.String(pname="checksum",aname="_checksum", optional=1), ZSI.TCnumbers.Iint(pname="fileSize",aname="_fileSize", optional=1), ZSI.TC.String(pname="fileStatus",aname="_fileStatus"), ZSI.TC.String(pname="fileType",aname="_fileType"), ZSI.TCnumbers.Iint(pname="fileBlockId",aname="_fileBlockId", optional=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_id(self):
            return self._id

        def Set_id(self,_id):
            self._id = _id

        def Get_guid(self):
            return self._guid

        def Set_guid(self,_guid):
            self._guid = _guid

        def Get_logicalFileName(self):
            return self._logicalFileName

        def Set_logicalFileName(self,_logicalFileName):
            self._logicalFileName = _logicalFileName

        def Get_checksum(self):
            return self._checksum

        def Set_checksum(self,_checksum):
            self._checksum = _checksum

        def Get_fileSize(self):
            return self._fileSize

        def Set_fileSize(self,_fileSize):
            self._fileSize = _fileSize

        def Get_fileStatus(self):
            return self._fileStatus

        def Set_fileStatus(self,_fileStatus):
            self._fileStatus = _fileStatus

        def Get_fileType(self):
            return self._fileType

        def Set_fileType(self,_fileType):
            self._fileType = _fileType

        def Get_fileBlockId(self):
            return self._fileBlockId

        def Set_fileBlockId(self,_fileBlockId):
            self._fileBlockId = _fileBlockId


    class getDatasetFileBlocks_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'getDatasetFileBlocks'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._datasetPathName = None

            TClist = [ZSI.TC.String(pname="datasetPathName",aname="_datasetPathName"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_datasetPathName(self):
            return self._datasetPathName

        def Set_datasetPathName(self,_datasetPathName):
            self._datasetPathName = _datasetPathName


    class createProcessedDatasetResponse_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'createProcessedDatasetResponse'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._processedDatasetId = None

            TClist = [ZSI.TCnumbers.Iint(pname="processedDatasetId",aname="_processedDatasetId"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_processedDatasetId(self):
            return self._processedDatasetId

        def Set_processedDatasetId(self,_processedDatasetId):
            self._processedDatasetId = _processedDatasetId


    class listDatasetResponse_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'listDatasetResponse'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._datasetList = None

            TClist = [ZSI.TC.String(pname="datasetList",aname="_datasetList", repeatable=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           hasextras=1, **kw)
        def Get_datasetList(self):
            return self._datasetList

        def Set_datasetList(self,_datasetList):
            self._datasetList = _datasetList


    class listDataset_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'listDataset'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._datasetPathName = None

            TClist = [ZSI.TC.String(pname="datasetPathName",aname="_datasetPathName"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_datasetPathName(self):
            return self._datasetPathName

        def Set_datasetPathName(self,_datasetPathName):
            self._datasetPathName = _datasetPathName


    class insertEventCollectionsResponse_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'insertEventCollectionsResponse'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._result = None

            TClist = [ZSI.TCnumbers.Iint(pname="result",aname="_result"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_result(self):
            return self._result

        def Set_result(self,_result):
            self._result = _result


    class createFileBlockResponse_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'createFileBlockResponse'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._fileBlockId = None

            TClist = [ZSI.TCnumbers.Iint(pname="fileBlockId",aname="_fileBlockId"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_fileBlockId(self):
            return self._fileBlockId

        def Set_fileBlockId(self,_fileBlockId):
            self._fileBlockId = _fileBlockId


    class getDatasetContents_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'getDatasetContents'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._datasetPathName = None
            self._listFiles = None

            TClist = [ZSI.TC.String(pname="datasetPathName",aname="_datasetPathName"), ZSI.TC.Boolean(pname="listFiles",aname="_listFiles"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_datasetPathName(self):
            return self._datasetPathName

        def Set_datasetPathName(self,_datasetPathName):
            self._datasetPathName = _datasetPathName

        def Get_listFiles(self):
            return self._listFiles

        def Set_listFiles(self,_listFiles):
            self._listFiles = _listFiles


    class createPrimaryDatasetResponse_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'createPrimaryDatasetResponse'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._primaryDatasetId = None

            TClist = [ZSI.TCnumbers.Iint(pname="primaryDatasetId",aname="_primaryDatasetId"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_primaryDatasetId(self):
            return self._primaryDatasetId

        def Set_primaryDatasetId(self,_primaryDatasetId):
            self._primaryDatasetId = _primaryDatasetId


    class createPrimaryDataset_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'createPrimaryDataset'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._primaryDataset = None

            TClist = [ns1.PrimaryDataset_Def(name="primaryDataset", ns=ns, optional=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_primaryDataset(self):
            return self._primaryDataset

        def Set_primaryDataset(self,_primaryDataset):
            self._primaryDataset = _primaryDataset


    class ProcessingPath_Def(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        type = 'ProcessingPath'

        def __init__(self, name=None, ns=None, **kw):
            # internal vars
            self._pathId = None
            self._parent = None
            self._application = None
            self._dataTier = None

            TClist = [ZSI.TCnumbers.Iint(pname="pathId",aname="_pathId", optional=1), ZSI.TC.String(pname="parent",aname="_parent", optional=1), ns1.Application_Def(name="application", ns=ns, optional=1), ZSI.TC.String(pname="dataTier",aname="_dataTier"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_pathId(self):
            return self._pathId

        def Set_pathId(self,_pathId):
            self._pathId = _pathId

        def Get_parent(self):
            return self._parent

        def Set_parent(self,_parent):
            self._parent = _parent

        def Get_application(self):
            return self._application

        def Set_application(self,_application):
            self._application = _application

        def Get_dataTier(self):
            return self._dataTier

        def Set_dataTier(self,_dataTier):
            self._dataTier = _dataTier


    class EventCollection_Def(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        type = 'EventCollection'

        def __init__(self, name=None, ns=None, **kw):
            # internal vars
            self._collectionId = None
            self._collectionIndex = None
            self._numberOfEvents = None
            self._collectionName = None
            self._datasetPathName = None
            self._parent = None
            self._parentageType = None
            self._fileList = None

            if "something" not in kw :
                TClist = [ZSI.TCnumbers.Iint(pname="collectionId",aname="_collectionId", optional=1), ZSI.TCnumbers.Iint(pname="collectionIndex",aname="_collectionIndex", optional=1), ZSI.TCnumbers.Iint(pname="numberOfEvents",aname="_numberOfEvents", optional=1), ZSI.TC.String(pname="collectionName",aname="_collectionName"), ZSI.TC.String(pname="datasetPathName",aname="_datasetPathName"), ns1.EventCollection_Def(name="parent", ns=ns, optional=1, something=""), ZSI.TC.String(pname="parentageType",aname="_parentageType", optional=1), ns1.File_Def(name="fileList", ns=ns, repeatable=1, optional=1), ]
            else:
               TClist = [ZSI.TCnumbers.Iint(pname="collectionId",aname="_collectionId", optional=1), ZSI.TCnumbers.Iint(pname="collectionIndex",aname="_collectionIndex", optional=1), ZSI.TCnumbers.Iint(pname="numberOfEvents",aname="_numberOfEvents", optional=1), ZSI.TC.String(pname="collectionName",aname="_collectionName"), ZSI.TC.String(pname="datasetPathName",aname="_datasetPathName"), ZSI.TC.String(pname="parentageType",aname="_parentageType", optional=1), ns1.File_Def(name="fileList", ns=ns, repeatable=1, optional=1), ]


            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           hasextras=1, **kw)
        def Get_collectionId(self):
            return self._collectionId

        def Set_collectionId(self,_collectionId):
            self._collectionId = _collectionId

        def Get_collectionIndex(self):
            return self._collectionIndex

        def Set_collectionIndex(self,_collectionIndex):
            self._collectionIndex = _collectionIndex

        def Get_numberOfEvents(self):
            return self._numberOfEvents

        def Set_numberOfEvents(self,_numberOfEvents):
            self._numberOfEvents = _numberOfEvents

        def Get_collectionName(self):
            return self._collectionName

        def Set_collectionName(self,_collectionName):
            self._collectionName = _collectionName

        def Get_datasetPathName(self):
            return self._datasetPathName

        def Set_datasetPathName(self,_datasetPathName):
            self._datasetPathName = _datasetPathName

        def Get_parent(self):
            return self._parent

        def Set_parent(self,_parent):
            self._parent = _parent

        def Get_parentageType(self):
            return self._parentageType

        def Set_parentageType(self,_parentageType):
            self._parentageType = _parentageType

        def Get_fileList(self):
            return self._fileList

        def Set_fileList(self,_fileList):
            self._fileList = _fileList


    class ProcessedDataset_Def(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        type = 'ProcessedDataset'

        def __init__(self, name=None, ns=None, **kw):
            # internal vars
            self._id = None
            self._processedDatasetName = None
            self._primaryDatasetName = None
            self._processingPath = None
            self._isDatasetOpen = None

            TClist = [ZSI.TCnumbers.Iint(pname="id",aname="_id", optional=1), ZSI.TC.String(pname="processedDatasetName",aname="_processedDatasetName"), ZSI.TC.String(pname="primaryDatasetName",aname="_primaryDatasetName"), ns1.ProcessingPath_Def(name="processingPath", ns=ns, optional=1), ZSI.TC.Boolean(pname="isDatasetOpen",aname="_isDatasetOpen"), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_id(self):
            return self._id

        def Set_id(self,_id):
            self._id = _id

        def Get_processedDatasetName(self):
            return self._processedDatasetName

        def Set_processedDatasetName(self,_processedDatasetName):
            self._processedDatasetName = _processedDatasetName

        def Get_primaryDatasetName(self):
            return self._primaryDatasetName

        def Set_primaryDatasetName(self,_primaryDatasetName):
            self._primaryDatasetName = _primaryDatasetName

        def Get_processingPath(self):
            return self._processingPath

        def Set_processingPath(self,_processingPath):
            self._processingPath = _processingPath

        def Get_isDatasetOpen(self):
            return self._isDatasetOpen

        def Set_isDatasetOpen(self,_isDatasetOpen):
            self._isDatasetOpen = _isDatasetOpen


    class Block_Def(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        type = 'Block'

        def __init__(self, name=None, ns=None, **kw):
            # internal vars
            self._blockId = None
            self._blockStatusName = None
            self._blockName = None
            self._numberOfFiles = None
            self._numberOfBytes = None
            self._eventCollectionList = None

            TClist = [ZSI.TCnumbers.Iint(pname="blockId",aname="_blockId", optional=1), ZSI.TC.String(pname="blockStatusName",aname="_blockStatusName"), ZSI.TC.String(pname="blockName",aname="_blockName", optional=1), ZSI.TCnumbers.Iint(pname="numberOfFiles",aname="_numberOfFiles", optional=1), ZSI.TCnumbers.Iint(pname="numberOfBytes",aname="_numberOfBytes", optional=1), ns1.EventCollection_Def(name="eventCollectionList", ns=ns, repeatable=1, optional=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           hasextras=1, **kw)
        def Get_blockId(self):
            return self._blockId

        def Set_blockId(self,_blockId):
            self._blockId = _blockId

        def Get_blockStatusName(self):
            return self._blockStatusName

        def Set_blockStatusName(self,_blockStatusName):
            self._blockStatusName = _blockStatusName

        def Get_blockName(self):
            return self._blockName

        def Set_blockName(self,_blockName):
            self._blockName = _blockName

        def Get_numberOfFiles(self):
            return self._numberOfFiles

        def Set_numberOfFiles(self,_numberOfFiles):
            self._numberOfFiles = _numberOfFiles

        def Get_numberOfBytes(self):
            return self._numberOfBytes

        def Set_numberOfBytes(self,_numberOfBytes):
            self._numberOfBytes = _numberOfBytes

        def Get_eventCollectionList(self):
            return self._eventCollectionList

        def Set_eventCollectionList(self,_eventCollectionList):
            self._eventCollectionList = _eventCollectionList


    class insertEventCollections_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'insertEventCollections'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._eventCollectionList = None

            TClist = [ns1.EventCollection_Def(name="eventCollectionList", ns=ns, repeatable=1, optional=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           hasextras=1, **kw)
        def Get_eventCollectionList(self):
            return self._eventCollectionList

        def Set_eventCollectionList(self,_eventCollectionList):
            self._eventCollectionList = _eventCollectionList


    class createProcessedDataset_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'createProcessedDataset'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._processedDataset = None

            TClist = [ns1.ProcessedDataset_Def(name="processedDataset", ns=ns, optional=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_processedDataset(self):
            return self._processedDataset

        def Set_processedDataset(self,_processedDataset):
            self._processedDataset = _processedDataset


    class getDatasetContentsResponse_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'getDatasetContentsResponse'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._blockList = None

            TClist = [ns1.Block_Def(name="blockList", ns=ns, repeatable=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           hasextras=1, **kw)
        def Get_blockList(self):
            return self._blockList

        def Set_blockList(self,_blockList):
            self._blockList = _blockList


    class createFileBlock_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'createFileBlock'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._datasetPathName = None
            self._block = None

            TClist = [ZSI.TC.String(pname="datasetPathName",aname="_datasetPathName"), ns1.Block_Def(name="block", ns=ns, optional=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           **kw)
        def Get_datasetPathName(self):
            return self._datasetPathName

        def Set_datasetPathName(self,_datasetPathName):
            self._datasetPathName = _datasetPathName

        def Get_block(self):
            return self._block

        def Set_block(self,_block):
            self._block = _block


    class getDatasetFileBlocksResponse_Dec(ZSI.TCcompound.Struct):
        schema = 'http://tempuri.org/DBS.xsd'
        literal = 'getDatasetFileBlocksResponse'

        def __init__(self, name=None, ns=None, **kw):
            name = name or self.__class__.literal
            ns = ns or self.__class__.schema

            # internal vars
            self._blockList = None

            TClist = [ns1.Block_Def(name="blockList", ns=ns, repeatable=1), ]

            oname = name

            if name:
                aname = '_%s' % name
                if ns:
                    oname += ' xmlns="%s"' % ns
                else:
                    oname += ' xmlns="%s"' % self.__class__.schema
            else:
                aname = None

            ZSI.TCcompound.Struct.__init__(self, self.__class__, TClist,
                                           pname=name, inorder=0,
                                           aname=aname, oname=oname,
                                           hasextras=1, **kw)
        def Get_blockList(self):
            return self._blockList

        def Set_blockList(self,_blockList):
            self._blockList = _blockList


# define class alias for subsequent ns classes
ns1 = tempuri_org_DBS_xsd



