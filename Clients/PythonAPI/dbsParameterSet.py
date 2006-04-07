#!/usr/bin/env python
#
# ParameterSet class. 

from dbsObject import DbsObject

class DbsParameterSet(DbsObject):
  def __init__(self, objectId=None, hash=None, content=None, dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if hash is not None:
      self._hash = str(hash)

    if content is not None:
      self._content = str(content)

    assert (self._hash)
    assert (self._content)

  def getHash(self):
    """ Retrieve hash. """
    return self._hash

  def getContent(self):
    """ Retrieve content. """
    return self._content

##############################################################################
# Unit testing.

if __name__ == "__main__":
  print DbsParameterSet(hash="reco", content="dummy")
  print DbsParameterSet(dict={ 'hash' : '1234', 'content' : 'foobar' })
  print "Done"
