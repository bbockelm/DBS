#!/usr/bin/env python




##################################################
## DEPENDENCIES
import sys
import os
import os.path
from os.path import getmtime, exists
import time
import types
import __builtin__
from Cheetah.Template import Template
from Cheetah.DummyTransaction import DummyTransaction
from Cheetah.NameMapper import NotFound, valueForName, valueFromSearchList, valueFromFrameOrSearchList
from Cheetah.CacheRegion import CacheRegion
import Cheetah.Filters as Filters
import Cheetah.ErrorCatchers as ErrorCatchers

##################################################
## MODULE CONSTANTS
try:
    True, False
except NameError:
    True, False = (1==1), (1==0)
VFFSL=valueFromFrameOrSearchList
VFSL=valueFromSearchList
VFN=valueForName
currentTime=time.time
__CHEETAH_docstring__ = 'Autogenerated by CHEETAH: The Python-Powered Template Engine'
__CHEETAH_genTime__ = 'Sun Mar  4 15:25:48 2007'
__CHEETAH_srcLastModified__ = 'Sat Mar  3 11:33:58 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateOperators.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateOperators(Template):

    ##################################################
    ## CHEETAH GENERATED METHODS


    def __init__(self, *args, **KWs):

        Template.__init__(self, *args, **KWs)
        if not self._CHEETAH__instanceInitialized:
            cheetahKWArgs = {}
            allowedKWs = 'searchList filter filtersLib errorCatcher'.split()
            for k,v in KWs.items():
                if k in allowedKWs: cheetahKWArgs[k] = v
            self._initCheetahAttributes(**cheetahKWArgs)
        

    def respond(self, trans=None):



        ## CHEETAH: main method generated for this template
        if not trans and not callable(self.transaction):
            trans = self.transaction # is None unless self.awake() was called
        if not trans:
            trans = DummyTransaction()
            _dummyTrans = True
        else: _dummyTrans = False
        write = trans.response().write
        SL = self._CHEETAH__searchList
        _filter = self._CHEETAH__currentFilter
        
        ########################################
        ## START - generated method body
        
        write('<select id="')
        _v = VFFSL(SL,"tag",True) # $tag
        if _v is not None: write(_filter(_v, rawExpr='$tag')) # from line 1, col 13.
        write('''">
<option>=</option>
<option>&lt;</option>
<option>&gt;</option>
<option>&lt;=</option>
<option>&gt;=</option>
<option>:=</option>
<option>=~</option>
</select>
''')
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateOperators= 'respond'
if not hasattr(templateOperators, '_initCheetahAttributes'):
    templateClass = getattr(templateOperators, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateOperators)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateOperators()).run()


