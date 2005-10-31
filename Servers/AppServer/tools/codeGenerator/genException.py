"""
Module genException
Contains the genException class which is a generic 
exception class based on Shahkar GenericException.

Gregory Edwin Graham  
21-Apr-2005
"""
__revision__ = "$Revision: 1.1 $"
__version__ = "$Id: genException.py,v 1.1 2005/05/18 22:21:18 anzar Exp $"

import exceptions

class genException(exceptions.Exception) : 
    """
    Based on Shahkar GenericException class.
    """
    def __init__(self, msgText = None, **kwargs):
        """
        Constructor takes one optional argument of exception 
        text and an optional set of key-value pairs 
        describing the error and current module.
        """
        exceptions.Exception.__init__(self, msgText)
        self._Dict = kwargs
        self._Arg = msgText
        self._Name = self.__class__.__name__

    def __str__(self):
        """
        Generate string representation of error.
        """
        strn = '%s\n%s\n'% (self._Name, self._Arg)        
        for key in self._Dict.keys():
            strn = strn +'%s : %s\n' % (key, self._Dict[key])
        return strn
