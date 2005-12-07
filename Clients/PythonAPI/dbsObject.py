#!/usr/bin/env python
#
# $Id: dbsObject.py,v 1.1 2005/11/23 18:30:31 sveseli Exp $
#
# Base dbs object class. 
#

import UserDict
import UserList
import types

try:
  import SOAPpy
  __imported_SOAPpy = True
except:
  __imported_SOAPpy = False

DATA_KEY_ = "_data"
DEFAULT_STRING_ENCODING_ = "ascii"

##############################################################################
# Define base object class, and redefine it in case we have soappy (web services).

class DbsObjectBase(UserDict.UserDict):
    
  def __init__(self, dict={}):
    """ Constructor. """
    UserDict.UserDict.__init__(self, dict)

    self._typename = self.__class__.__name__
    self._ns = None
    
  def setNamespace(self, ns=None):
    """ Set namespace. """
    self._ns = ns
    if self._ns != None:
      self._validURIs = (self._ns)

  def getNamespace(self):
    """ Get namespace. """
    return self._ns

  def __str__(self):
    return "%s" % self.data    

  def __repr__(self):
    return "%s" % self.data    

class DbsObjectListBase(UserList.UserList):
    
  def __init__(self, list=[]):
    """ Constructor. """
    UserList.UserList.__init__(self, list)
    self._ns = None 
    self._elemsname = None
   
  def setNamespace(self, ns=None):
    """ Set namespace. """
    self._ns = ns
    if self._ns != None:
      self._validURIs = (self._ns)

  def getNamespace(self):
    """ Get namespace. """
    return self._ns

  def setElementsName(self):
    """ Set elements name. """
    if len(self):
      elementsName = self[0].__class__.__name__
      self._elemsname = elementsName

  def getElementsName(self):
    """ Get elements name. """
    return self._elemsname

  def __str__(self):
    return "%s" % self.data    

  def __repr__(self):
    return "%s" % self.data    


##############################################################################
# Make distinction between web service class and the ordinary one.

if __imported_SOAPpy:
  
  class DbsObject(DbsObjectBase, SOAPpy.structType):
    
    def __init__(self, dict={}):
      """ Constructor. """
      if isinstance(dict, types.DictType) \
	     or isinstance(dict, UserDict.UserDict):
	DbsObjectBase.__init__(self, dict)
      elif isinstance(dict, SOAPpy.structType):
	# Convert all relevant keys. Since the base class has no knowledge
	# of what types are values belonging to different keys, the
	# derived class has to fix complex structs.
	DbsObjectBase.__init__(self)
	for key in dict._keyord:
	  self[key.encode(DEFAULT_STRING_ENCODING_)] = dict[key]
      SOAPpy.structType.__init__(self, self.data)
	
	
	
    def getWsRep(self):
      """ Representation for web services. """
      dict = {}
      for key in self.data.keys():
	item = self.data[key]
	if isinstance(item, DbsObject):
	  dict[key] = item.getWsRep()
	elif isinstance(item, DbsObjectList):
	  dict[key] = item.getWsRep()
	else:
	  dict[key] = item
      wsRep = SOAPpy.structType(dict)
      wsRep._typename = self.__class__.__name__
      wsRep._ns = self.getNamespace()
      wsRep._validURIs = (wsRep._ns)
      return wsRep

    def __str__(self):
      return "%s" % self.data    

    def __repr__(self):
      return "%s" % self.data    

    
  class DbsObjectList(DbsObjectListBase, SOAPpy.arrayType):
    
    def __init__(self, list=[]):
      """ Constructor. """
      DbsObjectListBase.__init__(self, list)
      SOAPpy.arrayType.__init__(self, data=self.data)

    def getWsRep(self):
      """ Representation for web services. """
      self.setElementsName()
      data = []
      for d in self.data:
	data.append(d.getWsRep())
      wsRep = SOAPpy.arrayType(data=data, elemsname=self._elemsname) 
      wsRep._ns = self.getNamespace()
      wsRep._validURIs = (wsRep._ns)
      return wsRep
    
else:

  class DbsObject(DbsObjectBase):
    
    def __init__(self, dict={}):
      """ Constructor. """
      DbsObjectBase.__init__(self, dict)

  class DbsObjectList(DbsObjectListBase):
    
    def __init__(self, list=[]):
      """ Constructor. """
      DbsObjectListBase.__init__(self, list)

  
##############################################################################
# Unit testing.

if __name__ == "__main__":

    
  print "Done"
