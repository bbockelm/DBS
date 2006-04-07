#!/usr/bin/env python
#
# Application class. 

from dbsObject import DbsObject

class DbsApplication(DbsObject):
  def __init__(self, objectId=None, version=None, family=None,
	       executable=None, dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if version is not None:
      self._version = str(version)

    if family is not None:
      self._family = str(family)

    if executable is not None:
      self._executable = str(executable)

    assert (self._version)
    assert (self._family)
    assert (self._executable)

  def getVersion(self):
    """ Retrieve version. """
    return self._version

  def getFamily(self):
    """ Retrieve family. """
    return self._family

  def getExecutable(self):
    """ Retrieve executable. """
    return self._executable

##############################################################################
# Unit testing.

if __name__ == "__main__":
  app = DbsApplication(family="reco", executable="dummy", version="p1")
  print app
  print "Done"
