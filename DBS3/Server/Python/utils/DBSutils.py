#!/usr/bin/env python
""" DBS utility class """

__revision__ = "$Revision:$"
__version__  = "$Id:$ "

from time import time

class DBSUtils:
    """DBSUtils class provides time, client names, etc functions."""

    def __init__(self, logger):
	pass

    def getTime(self):
	return time()

    def getCreateBy(self):
	return "Client Name"

    def getModifiedBy(self):
	return getCreatedBy()		

