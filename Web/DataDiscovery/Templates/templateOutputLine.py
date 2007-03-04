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
__CHEETAH_genTime__ = 'Sun Mar  4 15:25:49 2007'
__CHEETAH_srcLastModified__ = 'Sat Mar  3 11:34:29 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateOutputLine.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateOutputLine(Template):

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
        
        nextId = VFFSL(SL,"id",True)+1
        write('<div id="outMenu_')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 2, col 18.
        write('''">
<table class="selMenu">
<tr>
<td><h3>Looking for</ht></td>
<td>
''')
        _v = VFFSL(SL,"commonOutput",True) # $commonOutput
        if _v is not None: write(_filter(_v, rawExpr='$commonOutput')) # from line 7, col 1.
        write('''
</td>

<td>
<b>OR from table</b>
</td>

<td style="width:160px">
<div id="divOutputTables_''')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 15, col 26.
        write('">\n<select id="outputTables_')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 16, col 26.
        write('" onchange="ChangeCols(')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 16, col 52.
        write(',\'outputTables\')" name="outputTables">\n')
        for section in VFFSL(SL,"tableList",True):
            write('<option>')
            _v = VFFSL(SL,"section",True) # $section
            if _v is not None: write(_filter(_v, rawExpr='$section')) # from line 18, col 9.
            write('</option>\n')
        write('''</select>
</div>
</td>

<td><b>column</b></td>

<td style="width:200px">
<div id="outTableCols_''')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 27, col 23.
        write('''"></div>
</td>

<td>
<a href="javascript:ajaxMakeLine(''')
        _v = VFFSL(SL,"nextId",True) # $nextId
        if _v is not None: write(_filter(_v, rawExpr='$nextId')) # from line 31, col 34.
        write(''')"><img src="images/plus2.gif" alt="add" style="border:none" /></a>
</td>
<td>
<a href="javascript:ClearTag('makeOutMenu_''')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 34, col 43.
        write('''\')"><img src="images/minus2.gif" alt="remove" style="border:none" /></a>
</td>
</tr>
</table>
</div>
<div id="makeOutMenu_''')
        _v = VFFSL(SL,"nextId",True) # $nextId
        if _v is not None: write(_filter(_v, rawExpr='$nextId')) # from line 39, col 22.
        write('"></div>\n<script type="text/javascript">ajaxEngine.registerAjaxObject(\'divOutputTables_')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 40, col 79.
        write("', new GetDataUpdater('divOutputTables_")
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 40, col 121.
        write('\',\'replace\',\'noResultsMenu\'))</script>\n<script type="text/javascript">ajaxEngine.registerAjaxObject(\'outTableCols_')
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 41, col 76.
        write("', new GetDataUpdater('outTableCols_")
        _v = VFFSL(SL,"id",True) # $id
        if _v is not None: write(_filter(_v, rawExpr='$id')) # from line 41, col 115.
        write('\',\'replace\',\'noResultsMenu\'))</script>\n<script type="text/javascript">ajaxEngine.registerAjaxObject(\'makeOutMenu_')
        _v = VFFSL(SL,"nextId",True) # $nextId
        if _v is not None: write(_filter(_v, rawExpr='$nextId')) # from line 42, col 75.
        write("',new GetDataUpdater('makeOutMenu_")
        _v = VFFSL(SL,"nextId",True) # $nextId
        if _v is not None: write(_filter(_v, rawExpr='$nextId')) # from line 42, col 116.
        write("','replace','noResultsMenu'))</script>\n")
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateOutputLine= 'respond'
if not hasattr(templateOutputLine, '_initCheetahAttributes'):
    templateClass = getattr(templateOutputLine, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateOutputLine)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateOutputLine()).run()


