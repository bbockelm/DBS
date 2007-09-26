
import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

from dbsRun import DbsRun
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsProcessedDataset import DbsProcessedDataset
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsLumiSection import DbsLumiSection
from dbsFile import DbsFile
from dbsFileBlock import DbsFileBlock
from dbsDataTier import DbsDataTier
from dbsStorageElement import DbsStorageElement
from dbsFileBranch import DbsFileBranch
from dbsAlgorithm import DbsAlgorithm
from dbsAnalysisDataset import DbsAnalysisDataset
from dbsAnalysisDatasetDefinition import DbsAnalysisDatasetDefinition
from dbsFileTriggerTag import DbsFileTriggerTag
from dbsMigrateApi import DbsMigrateApi
from dbsDQFlag import DbsDQFlag
from dbsRunLumiDQ import DbsRunLumiDQ

from dbsException import DbsException
from dbsApiException import *

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

def dbsApiImplSetApiVersion(self):
    """
    Sets DBS Client Api Version
    Reading for __version__ tag

    Note: Config (dbs.config) and Constructor 
      arguments have higher presedence 
    """

    return __version__

