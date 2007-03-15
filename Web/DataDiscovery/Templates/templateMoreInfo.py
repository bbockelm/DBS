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
__CHEETAH_genTime__ = 'Thu Mar 15 12:44:28 2007'
__CHEETAH_srcLastModified__ = 'Sat Mar  3 09:47:26 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateMoreInfo.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateMoreInfo(Template):

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
        
        write('''<div class="float_moreInfo">

<table width="100%">
<tr>
<td align="left">
<span class="sectionhead_tight">
More information
</span>
</td>
<td align="right">
<a href="javascript:HideTag(\'''')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 11, col 30.
        write('''\')">close &#8855;</a>
</td>
</tr>
</table>
<hr class="dbs" />

<div id="navlist">
<ul>
''')
        # 
        # <li>
        # Data location
        # </li>
        write('\n<li class="moreInfo">\n<a href="javascript:popUp(\'')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 25, col 28.
        write('/getDbsData?dbsInst=')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 25, col 53.
        write('&amp;site=All&amp;primD=*&amp;tier=*&amp;proc=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 25, col 107.
        write('''&amp;_idx=0&amp;ajax=0',1000)">
Block information
</a>
</li>

<li class="moreInfo">
<a href="javascript:popUp(\'''')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 31, col 28.
        write('/getRuns?dbsInst=')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 31, col 50.
        write('&amp;site=All&amp;primD=*&amp;tier=*&amp;proc=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 31, col 104.
        write('''&amp;_idx=0&amp;ajax=0',1000)">
Run information
</a>
</li>

<li class="moreInfo">
<a href="javascript:popUp(\'''')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 37, col 28.
        write('/getAppConfigs?dbsInst=')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 37, col 56.
        write('&amp;appPath=')
        _v = VFFSL(SL,"appPath",True) # $appPath
        if _v is not None: write(_filter(_v, rawExpr='$appPath')) # from line 37, col 77.
        write('''&amp;ajax=0',1000)">
Configuration files
</a>
</li>

<li class="moreInfo">
<a href="javascript:popUp(\'''')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 43, col 28.
        write('/getProvenanceForAllDatasets?dbsInst=')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 43, col 70.
        write('&amp;site=All&amp;primD=*&amp;tier=*&amp;proc=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 43, col 124.
        write('''&amp;_idx=0&amp;ajax=0',1000)">
Provenance information
</a>
</li>

<li class="moreInfo">
<a href="javascript:popUp(\'''')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 49, col 28.
        write('/getDataDescription?dbsInst=')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 49, col 61.
        write('&amp;processedDataset=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 49, col 91.
        write('''\',1000)">Data description</a>
</li>
<li class="moreInfo">
<a href="javascript:popUp('https://cmsdoc.cern.ch:8443/cms/aprom/phedex/tbedi/Request::Create?dbschoice=known&amp;dbs=http%3A//cmsdbs.cern.ch/cms/prod/comp/DBS/CGIServer/prodquery%3Finstance%3D''')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 52, col 194.
        write('&amp;data=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 52, col 212.
        write('''&amp;priority=0',1000)">PhEDEx subscription</a>
</li>
<li class="moreInfo">
<a href="javascript:popUp(\'''')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 55, col 28.
        write('/getParameterSet?dbsInst=')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 55, col 58.
        write('&amp;dataset=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 55, col 79.
        write('''\',1000)">
Parameter set(s)
</a>
</li>
<li class="moreInfo">
<a href="javascript:popUp(\'''')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 60, col 28.
        write('/getAnalysisDS?dbsInst=')
        _v = VFFSL(SL,"dbsInst",True) # $dbsInst
        if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 60, col 56.
        write('&amp;dataset=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 60, col 77.
        write('''\',1000)">
Analysis dataset(s)
</a>
</li>
<li class="moreInfo">
<a href="javascript:popUp(\'''')
        _v = VFFSL(SL,"host",True) # $host
        if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 65, col 28.
        write('/crabCfg?dataset=')
        _v = VFFSL(SL,"path",True) # $path
        if _v is not None: write(_filter(_v, rawExpr='$path')) # from line 65, col 50.
        write('''&amp;totEvt=-1',1000)">crab.cfg</a>
</li>
</ul>
</div>

</div>
''')
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateMoreInfo= 'respond'
if not hasattr(templateMoreInfo, '_initCheetahAttributes'):
    templateClass = getattr(templateMoreInfo, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateMoreInfo)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateMoreInfo()).run()


