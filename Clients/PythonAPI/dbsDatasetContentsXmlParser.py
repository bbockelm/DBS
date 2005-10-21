#!/usr/bin/env python
#
# $Id$
#
# Class which parses dataset contents xml file.
#

import string
import dbsFileBlock
import dbsEventCollection
import dbsLogManager
import dbsXmlParser

FILE_BLOCK_TAG_ = "FILEBLOCKS"
FILE_BLOCK_NAME_ATTR_ = "BLOCKNAME"
FILE_BLOCK_ID_ATTR_ = "ID"

EVENT_COLLECTION_TAG_ = "EVCOLL"
EVENT_COLLECTION_NAME_ATTR_ = "EVCNAME"
EVENT_COLLECTION_NUMBER_OF_EVENTS_ATTR_ = "EVCEVTS"


##############################################################################
# Exception classes.

class DbsDatasetContentsXmlParserException(dbsXmlParser.DbsXmlParserException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsXmlParser.DbsXmlParserException.__init__(self, **kwargs)

  
##############################################################################
# Parser class.

class DbsDatasetContentsXmlParser(dbsXmlParser.DbsXmlParser):

  def __init__(self, xmlFile):
    """ Constructor. """
    dbsXmlParser.DbsXmlParser.__init__(self, xmlFile)

  def parseDocument(self):
    """ Implementation of the abstract base class method. """
    return self.getFileBlockList()

  def getFileBlockList(self):
    """
    Process the document tree, return list of file blocks, each
    containing a list of event collections.
    """
    funcName = "%s.%s" % (self.__class__.__name__, "getFileBlockList()")
    logManager = dbsLogManager.getInstance()

    # Get list of all file blocks.
    fileBlockMap = {}
    fileBlockNodeList = self.getDom().getElementsByTagName(
      FILE_BLOCK_TAG_)
    for fileBlockNode in fileBlockNodeList:
      try:
	# blockName will be unicode string, so we modify its encoding
	blockName = fileBlockNode.getAttribute(FILE_BLOCK_NAME_ATTR_)
	blockName = blockName.encode(dbsXmlParser.DEFAULT_STRING_ENCODING_)

	blockId = int(fileBlockNode.getAttribute(FILE_BLOCK_ID_ATTR_))
	if not fileBlockMap.has_key(blockId):
	  fileBlockMap[blockId] = dbsFileBlock.DbsFileBlock(
	    blockName=blockName, blockId=blockId)
	logManager.log(
	  what="Found file block: %s (id: %s)" % (blockName, blockId),
	  where=funcName,
	  logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
      
	# Get list of all event collections, sort them according to the
	# file block.
	eventCollectionNodeList = self.getDom().getElementsByTagName(
	  EVENT_COLLECTION_TAG_)
	for eventCollectionNode in eventCollectionNodeList:
	  collectionName = eventCollectionNode.getAttribute(
	    EVENT_COLLECTION_NAME_ATTR_)
	  collectionName = collectionName.encode(
	    dbsXmlParser.DEFAULT_STRING_ENCODING_)
	  numberOfEvents = eventCollectionNode.getAttribute(
	    EVENT_COLLECTION_NUMBER_OF_EVENTS_ATTR_)
	  eventCollection = dbsEventCollection.DbsEventCollection(
	    collectionName=collectionName, numberOfEvents=numberOfEvents)
	  parentBlockId = int(eventCollectionNode.parentNode.getAttribute(FILE_BLOCK_ID_ATTR_))
	  fileBlockMap[parentBlockId].addEventCollection(eventCollection)
      except Exception, ex:
	logManager.log(what="Parsing XML file failed: %s" % ex,
		       where=funcName,
		       logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise DbsDatasetContentsXmlParserException(exception=ex)
    return fileBlockMap.values()


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    parser = DbsDatasetContentsXmlParser("tmpFile.10134.1129745508.25.xml")
    print parser.getFileBlockList()
  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
