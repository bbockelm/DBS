#!/usr/bin/env python
#
# $Id: dbsStaticMethod.py,v 1.2 2005/11/09 21:37:59 sveseli Exp $
#
# Class which allows one to write static methods. 
#

class DbsStaticMethod:
  
  def __init__(self, anycallable):
    self.__call__ = anycallable


##############################################################################
# Unit testing.

if __name__ == "__main__":
  print "Done"
