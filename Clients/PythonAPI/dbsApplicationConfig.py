#!/usr/bin/env python
#
# Application class. 

from dbsObject import DbsObject
from dbsApplication import DbsApplication
from dbsParameterSet import DbsParameterSet

class DbsApplicationConfig(DbsObject):
  def __init__(self, objectId=None, application=None, parameterSet=None, dict={}):
    """ Constructor. """
    DbsObject.__init__(self, objectId, dict)
    if application is not None:
      self._application = application

    if parameterSet is not None:
      self._parameterSet = parameterSet

    if not isinstance(self._application, DbsApplication):
      self._application = DbsApplication(dict=self._application)

    if not isinstance(self._parameterSet, DbsParameterSet):
      self._parameterSet = DbsParameterSet(dict=self._parameterSet)

    assert (self._application)
    assert (self._parameterSet)

  def getApplication(self):
    """ Retrieve application. """
    return self._application

  def getParameterSet(self):
    """ Retrieve parameter set. """
    return self._parameterSet

##############################################################################
# Unit testing.

if __name__ == "__main__":
  app = DbsApplication(executable="cmsRun", version="0_6_0", family="Framework")
  pset = DbsParameterSet (hash="1234", content="duubi duu da daa")
  print app
  print pset
  print DbsApplicationConfig (application=app, parameterSet=pset)
  print "Done"
