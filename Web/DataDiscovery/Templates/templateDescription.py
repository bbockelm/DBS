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
__CHEETAH_genTime__ = 'Thu Mar 15 12:44:15 2007'
__CHEETAH_srcLastModified__ = 'Sat Mar  3 09:55:03 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateDescription.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateDescription(Template):

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
        
        # #<table class="float_box" width="100%">
        write('''<table width="100%">
<tr valign="top">
<td>

<table width="100%">
<tr>
<td align="left">
<span class="sectionhead">
DATA DESCRIPTION
</span>
</td>
''')
        # 
        # <td align="right">
        # <a href="javascript:HideTag('floatDataDescription')">close &#8855;</a>
        # </td>
        write('''
</tr>
</table>
<hr class="dbs" />
<table>
<tr>
<td>
''')
        # #<div class="div_scroll">
        _v = VFFSL(SL,"description",True) # $description
        if _v is not None: write(_filter(_v, rawExpr='$description')) # from line 25, col 1.
        write('\n')
        # #</div>
        write('''</td>
</tr>
</table>

</td>
</tr>
</table>
''')
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateDescription= 'respond'
if not hasattr(templateDescription, '_initCheetahAttributes'):
    templateClass = getattr(templateDescription, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateDescription)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateDescription()).run()


