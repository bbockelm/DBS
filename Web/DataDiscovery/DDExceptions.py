#!/usr/bin/env python
#-*- coding: ISO-8859-1 -*-
#
# Copyright 2006 Cornell University, Ithaca, NY 14853.
#
# Author:  Valentin Kuznetsov, 2006
#

# system modules
import os, sys, string, stat, re, types
from exceptions import Exception

def printExcept(msg=""):
    """
       print exception type, value and traceback on stderr
       @type  msg: string
       @param msg: message
       @rtype : none
       @return: none
    """
    if msg:
       print msg
    sys.excepthook(sys.exc_info()[0],sys.exc_info()[1],sys.exc_info()[2])

class DDException(Exception):

  def __init__(self, **kwargs):
    """
    Data Discovery exception can be initialized in following ways:
      DDException(args=exceptionString)
      DDException(exception=exceptionObject)      
    """ 
    args = kwargs.get("args", "")
    ex = kwargs.get("exception", None)
    if ex != None:
      if isinstance(ex, Exception):
         exArgs = "%s" % (ex)
         if args == "":
           args = exArgs
         else:
           args = "%s (%s)" % (args, exArgs)
    Exception.__init__(self, args)

  def getArgs(self):
    """ Return exception arguments. """
    return self.args

  def getErrorMessage(self):
    """ Return exception error. """
    return "%s" % (self.args)

  def getClassName(self):
    """ Return class name. """
    return "%s" % (self.__class__.__name__)

class DbsDatabaseError(DDException):
  """
      DBS error handler class
  """
  def __init__ (self, **kwargs):
      """
         @type  kwargs: dict
         @param kwargs: input dict
         @rtype : none
         @return: none
      """
      printExcept()
      DDException.__init__(self, **kwargs)
#
# main
#
if __name__ == "__main__":
   ddEx = DDException()

