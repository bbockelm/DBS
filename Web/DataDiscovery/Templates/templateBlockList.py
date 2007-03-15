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
__CHEETAH_genTime__ = 'Thu Mar 15 12:44:07 2007'
__CHEETAH_srcLastModified__ = 'Sat Mar  3 09:48:00 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateBlockList.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateBlockList(Template):

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
        
        write('<hr class="dbs" />\n')
        if len(VFFSL(SL,"blockList",True)):
            write('Processed datasets:\n<table class="offset_left">\n')
            for item in VFFSL(SL,"blockList",True):
                path = item[0]
                idPath = VFN(VFFSL(SL,"path",True),"replace",False)("/","___")
                evts = item[1]
                write('<tr>\n<td align="left">\n<b>')
                _v = VFFSL(SL,"path",True) # $path
                if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 11, col 4.
                write('''</b>
</td>
<td>
<div id="''')
                _v = VFFSL(SL,"idPath",True) # $idPath
                if _v is not None: write(_filter(_v, rawExpr='$idPath')) # from line 14, col 10.
                write('''"></div>
</td>
<td align="center">
<a href="javascript:ajaxEngine.registerAjaxElement(\'''')
                _v = VFFSL(SL,"idPath",True) # $idPath
                if _v is not None: write(_filter(_v, rawExpr='$idPath')) # from line 17, col 53.
                write("');ajaxMoreInfo('")
                _v = VFFSL(SL,"dbsInst",True) # $dbsInst
                if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 17, col 77.
                write("','")
                _v = VFFSL(SL,"path",True) # $path
                if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 17, col 88.
                write("','")
                _v = VFFSL(SL,"appPath",True) # $appPath
                if _v is not None: write(_filter(_v, rawExpr='$appPath')) # from line 17, col 96.
                write("','")
                _v = VFFSL(SL,"idPath",True) # $idPath
                if _v is not None: write(_filter(_v, rawExpr='$idPath')) # from line 17, col 107.
                write('\')">&#171; more &#187;</a>\n')
                # #<a href="javascript:ajaxEngine.registerAjaxElement('$idPath');ajaxMoreInfo('$dbsInst','$path','$appPath','$idPath')"><img src="images/mag_glass2_small.png" alt="more info" style="border:none" /></a>
                # 
                # (<a href="javascript:popUp('$host/getDataDescription?dbsInst=$dbsInst&amp;processedDataset=$path',1000)">Description</a>,
                # </td>
                # <td>
                # <a href="javascript:popUp('https://cmsdoc.cern.ch:8443/cms/aprom/phedex/tbedi/Request::Create?dbschoice=known&amp;dbs=http%3A//cmsdbs.cern.ch/cms/prod/comp/DBS/CGIServer/prodquery%3Finstance%3D$dbsInst&amp;data=$path&amp;priority=0',1000)">PhEDEx subscription</a>,
                # </td>
                # <td align="center">
                # <a href="javascript:popUp('$host/crabCfg?dataset=$path&amp;totEvt=$evts',1000)">crab.cfg</a>)
                write('''
</td>
</tr>
''')
            write('</table>\n')
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateBlockList= 'respond'
if not hasattr(templateBlockList, '_initCheetahAttributes'):
    templateClass = getattr(templateBlockList, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateBlockList)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateBlockList()).run()


