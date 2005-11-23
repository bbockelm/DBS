#!/usr/bin/env python
#
# $Id: dbsWebServiceException.py,v 1.3 2005/11/07 21:40:02 sveseli Exp $
#
# Base DBS Web Service Exception class. 
#

import types

import dbsException
import SOAPpy

FAULT_CODE_KWD_ = "faultCode"
FAULT_STRING_KWD_ = "faultString"
FAULT_DETAILS_KWD_ = "faultDetails"

SERVER_FAULT_CODE_ = "Server"
CLIENT_FAULT_CODE_ = "Client"

DEFAULT_FAULT_CODE_ = SERVER_FAULT_CODE_
DEFAULT_FAULT_STRING_ = "Internal Exception"
DEFAULT_FAULT_DETAILS_ = ""


###############################################################################
# Base exception class and some common faults.

class DbsWebServiceException(dbsException.DbsException, SOAPpy.faultType):
  def __init__ (self, **kwargs):
    """ Initialization. """
    faultCode = kwargs.get(FAULT_CODE_KWD_, DEFAULT_FAULT_CODE_)
    faultString = kwargs.get(FAULT_STRING_KWD_, DEFAULT_FAULT_STRING_)
    faultDetails = kwargs.get(FAULT_DETAILS_KWD_, DEFAULT_FAULT_DETAILS_)

    # Try determining fault string.
    if faultString == DEFAULT_FAULT_STRING_:
      if kwargs.has_key(dbsException.ARGS_KWD_):
	faultString = kwargs.get(dbsException.ARGS_KWD_)
      elif kwargs.has_key(dbsException.EXCEPTION_KWD_):
	faultString = "%s" % kwargs.get(dbsException.EXCEPTION_KWD_)
      
    dbsException.DbsException.__init__(self, **kwargs)
    SOAPpy.faultType.__init__(self, faultCode, faultString, faultDetails)

  def getFaultCode(self):
    """ Return fault code. """
    return self.faultcode

  def getFaultString(self):
    """ Return fault string. """
    return self.faultstring

  def getFaultDetails(self):
    """ Return fault details. """
    return self.detail

  def setNamespace(self, ns=None):
    """ Set namespace. """
    self._ns = ns
    if self._ns != None:
      self._validURIs = (self._ns)

  def getNamespace(self):
    """ Get namespace. """
    return self._ns

  def setType(self, type):
    """ Set namespace. """
    self._type = type

  def getType(self):
    """ Get namespace. """
    return self._type

class DbsWebServiceFault(DbsWebServiceException):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsWebServiceException.__init__(self, **kwargs)

class DbsWebServiceClientFault(DbsWebServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    faultCode = "%s.%s" % (CLIENT_FAULT_CODE_, self.__class__.__name__)
    kwargs[FAULT_CODE_KWD_] = faultCode
    DbsWebServiceFault.__init__(self, **kwargs)

class DbsWebServiceServerFault(DbsWebServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    faultCode = "%s.%s" % (SERVER_FAULT_CODE_, self.__class__.__name__)
    kwargs[FAULT_CODE_KWD_] = faultCode
    DbsWebServiceFault.__init__(self, **kwargs)

class MissingParameterFault(DbsWebServiceClientFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsWebServiceClientFault.__init__(self, **kwargs)    
  
##############################################################################
# Unit testing.

if __name__ == "__main__":
  print "Done"

