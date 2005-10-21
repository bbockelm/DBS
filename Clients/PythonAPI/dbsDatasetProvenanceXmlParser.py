#!/usr/bin/env python
#
# $Id$
#
# Class which parses dataset provenance xml file.
#

import string
import dbsException
import dbsDataset
import dbsLogManager

import dbsXmlParser

DATASET_PARENT_TAG_ = "PARENT"
DATASET_NAME_ATTR_ = "name"
DATASET_PATH_ATTR_ = "PATH"
DATATIER_ATTR_ = "DATATIER"

##############################################################################
# Exception classes.

class DbsDatasetProvenanceXmlParserException(dbsXmlParser.DbsXmlParserException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsXmlParser.DbsXmlParserException.__init__(self, **kwargs)

  
##############################################################################
# Parser class.

class DbsDatasetProvenanceXmlParser(dbsXmlParser.DbsXmlParser):

  def __init__(self, xmlFile):
    """ Constructor. """
    dbsXmlParser.DbsXmlParser.__init__(self, xmlFile)

  def parseDocument(self):
    """ Implementation of the abstract base class method. """
    return self.getDatasetParentList()

  def getDatasetParentList(self):
    """
    Process the document tree, return list of dataset parents.
    """
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetParentList()")
    logManager = dbsLogManager.getInstance()

    # Get list of all parents.
    parentNodeList = self.getDom().getElementsByTagName(
      DATASET_PARENT_TAG_)
    parentList = []
    for parentNode in parentNodeList:
      try:
	# all strings will be unicode, so we modify their encoding
	parentName = parentNode.getAttribute(DATASET_NAME_ATTR_)
	parentName = parentName.encode(dbsXmlParser.DEFAULT_STRING_ENCODING_)

	parentPath = parentNode.getAttribute(DATASET_PATH_ATTR_)
	parentPath = parentPath.encode(dbsXmlParser.DEFAULT_STRING_ENCODING_)

	dataTier = parentNode.getAttribute(DATATIER_ATTR_)
	dataTier = dataTier.encode(dbsXmlParser.DEFAULT_STRING_ENCODING_)

	parent = dbsDataset.DbsDataset(datasetName=parentName,
				       datasetPath=parentPath,
				       dataTier=dataTier)
	logManager.log(
	  what="Found  parent: %s" % parent,
	  where=funcName,
	  logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
      
	parentList.append(parent)
      except Exception, ex:
	logManager.log(what="Parsing XML file failed: %s" % ex,
		       where=funcName,
		       logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise DbsDatasetProvenanceXmlParserException(exception=ex)
    return parentList


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    parser = DbsDatasetProvenanceXmlParser("tmpFile.10134.1129745508.26.xml")
    print parser.getDatasetParentList()
  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
