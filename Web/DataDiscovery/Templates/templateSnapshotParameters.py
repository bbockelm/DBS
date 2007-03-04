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
__CHEETAH_genTime__ = 'Sun Mar  4 15:26:01 2007'
__CHEETAH_srcLastModified__ = 'Sat Mar  3 09:49:25 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateSnapshotParameters.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateSnapshotParameters(Template):

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
        
        write('<span id="snapshot_dbsInst" class="hide">')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 1, col 42.
        write('</span>\n<span id="snapshot_site"    class="hide">')
        _v = VFFSL(SL,"site",True) # $site
        if _v is not None: write(_filter(_v, rawExpr='$site')) # from line 2, col 42.
        write('</span>\n<span id="snapshot_app"     class="hide">')
        _v = VFFSL(SL,"app",True) # $app
        if _v is not None: write(_filter(_v, rawExpr='$app')) # from line 3, col 42.
        write(' </span>\n<span id="snapshot_prim"    class="hide">')
        _v = VFFSL(SL,"prim",True) # $prim
        if _v is not None: write(_filter(_v, rawExpr='$prim')) # from line 4, col 42.
        write('</span>\n<span id="snapshot_tier"    class="hide">')
        _v = VFFSL(SL,"tier",True) # $tier
        if _v is not None: write(_filter(_v, rawExpr='$tier')) # from line 5, col 42.
        write('</span>\n<span id="snapshot_proc"    class="hide">')
        _v = VFFSL(SL,"proc",True) # $proc
        if _v is not None: write(_filter(_v, rawExpr='$proc')) # from line 6, col 42.
        write('</span>\n')
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateSnapshotParameters= 'respond'
if not hasattr(templateSnapshotParameters, '_initCheetahAttributes'):
    templateClass = getattr(templateSnapshotParameters, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateSnapshotParameters)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateSnapshotParameters()).run()


