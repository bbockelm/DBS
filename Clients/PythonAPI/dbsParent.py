#!/usr/bin/env python
#
# Parent-child relationship. 

import types
from dbsObject import DbsObject

class DbsParent(DbsObject):
  def __init__(self, parent=None, type=None, dict={}):
    """ Constructor. """
    DbsObject.__init__(self, None, dict)
    if parent is not None:
      self._parent = parent

    if type is not None:
      self._type = str(type)

    assert(isinstance(self._parent, DbsObject))
    assert(self._type)

  def getParent(self):
    """ Retrieve parent object. """
    return self._parent

  def getType(self):
    """ Retrieve parentage type. """
    return self._type

##############################################################################
# Unit testing.

if __name__ == "__main__":
  from dbsEventCollection import DbsEventCollection
  print DbsParent(parent=DbsEventCollection(), type="foo")
  print "Done"
