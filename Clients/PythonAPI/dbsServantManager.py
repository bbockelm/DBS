#!/usr/bin/env python
#
# $Id: dbsServantManager.py,v 1.3 2005/11/07 21:40:02 sveseli Exp $
#
# Servant manager class, provides simple way of controlling web service
# servants.
#

import threading


import dbsWebServiceException
import dbsLogManager
import dbsConfigManager


###############################################################################
# Exception classes.

class ServantManagerException(dbsWebServiceException.DbsWebServiceException):

  def __init__(self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

class NoAvailableServants(ServantManagerException):

  def __init__(self, **kwargs):
    """ Initialization. """
    ServantManagerException.__init__(self, **kwargs)

class InvalidServantId(ServantManagerException):

  def __init__(self, **kwargs):
    """ Initialization. """
    ServantManagerException.__init__(self, **kwargs)

class ServantNotAcquired(ServantManagerException):

  def __init__(self, **kwargs):
    """ Initialization. """
    ServantManagerException.__init__(self, **kwargs)

###############################################################################
# Servant manager, controls number of servants. Exception will be thrown
# if there are no more available servants.

class DbsServantManager:

  def __init__(self, nServants, servantClass):
    """ Initialization. """
    self._lock = threading.RLock()
    self._nServants = nServants
    self._servantClass = "%s" % servantClass
    self._servantUsageMap = {}
    for id in range (0, self._nServants):
      self._servantUsageMap[id] = False

  def acquireServant(self):
    """ Acquire servant. """
    self._lock.acquire()
    try:
      for id in range (0, self._nServants):
        if self._servantUsageMap[id] == False:
          self._servantUsageMap[id] = True
          return id
      errMsg = "All of %s available servants for %s are in use." % (
        self._nServants, self._servantClass)
      raise NoAvailableServants(args=errMsg)
                                
    finally:
      self._lock.release()

  def releaseServant(self, servantId):
    """ Release servant. """
    self._lock.acquire()
    try:
      inUse = self._servantUsageMap.get(servantId, None)
      if inUse == None: 
        errMsg = "Invalid servant id: %s" % servantId
        raise InvalidServantId(args=errMsg)
      else:
        if inUse == False:
          errMsg = "Servant id %s is not in use." % servantId
          raise ServantNotAcquired(args=errMsg)
        else:
          self._servantUsageMap[servantId] = False
    finally:
      self._lock.release()


###############################################################################
# Unit testing.

if __name__ == "__main__":
  sm = DbsServantManager(3, "trkserv")
  id = sm.acquireServant()
  print "Acquired servant: ", id
  id = sm.acquireServant()
  print "Acquired servant: ", id
  id = sm.acquireServant()
  print "Acquired servant: ", id
  sm.releaseServant(id)
  print "Released servant: ", id
  id = sm.acquireServant()
  print "Acquired servant: ", id
  print "Done"
