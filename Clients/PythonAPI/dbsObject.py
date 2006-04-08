#!/usr/bin/env python
#
# Base dbs object class. 

import types
from dbsException import InvalidArgument

class DbsObject:
  def __init__(self, objectId=None, dict={}):
    """ Constructor. """
    try:
      for k, v in dict.items():
	self.__dict__ ["_" + k] = v
    except Exception, ex:
      raise InvalidArgument (args="Failed to convert parameters in %s: %s" % (dict, ex))

    if objectId is not None:
      self._objectId = long(objectId)

  def getObjectId (self):
    return self._objectId

  def __setitem__ (self, k, v):
    self.__dict__ ["_" + k] = v

  def __str__(self):
    return "%s" % self.__dict__

  def __repr__(self):
    return "%s" % self.__dict__
