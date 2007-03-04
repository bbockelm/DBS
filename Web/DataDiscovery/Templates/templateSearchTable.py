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
__CHEETAH_genTime__ = 'Sun Mar  4 15:25:57 2007'
__CHEETAH_srcLastModified__ = 'Sat Mar  3 09:39:24 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateSearchTable.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateSearchTable(Template):

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
        
        write('<div class="div_scroll">\n')
        # #<form action="javascript:ajaxSearch();" method="get">
        write('<form action="')
        _v = VFFSL(SL,"searchFunction",True) # $searchFunction
        if _v is not None: write(_filter(_v, rawExpr='$searchFunction')) # from line 3, col 15.
        write('" method="get">\n\n')
        # 
        # <p>
        # Use this form to search in particular DBS instance by keywords.
        # <br />
        # The search is case insensitive and the following special symbols are supported:
        # <span class="box">'(', ')', 'and', 'or' and 'not'</span>.
        # <br />
        # You may use boolean expressions, e.g.,
        # <span class="box">( word1 or (word3 and word4) and not word2 )</span>
        # </p>
        write('''

<table width="100%">
<tr>
<td align="left">
''')
        if VFFSL(SL,"userMode",True):
            write('<input type="hidden" name="dbsInst" value="')
            _v = VFFSL(SL,"dbsList",True)[0] # $dbsList[0]
            if _v is not None: write(_filter(_v, rawExpr='$dbsList[0]')) # from line 21, col 44.
            write('" id="kw_dbsSelector" />\n')
        else:
            write('DBS instance:\n<select name="keywordsSearch_dbsInst" id="kw_dbsSelector">\n')
            # #<option value="All">All</option>
            for dbs in VFFSL(SL,"dbsList",True):
                write('<option value="')
                _v = VFFSL(SL,"dbs",True) # $dbs
                if _v is not None: write(_filter(_v, rawExpr='$dbs')) # from line 27, col 16.
                write('">')
                _v = VFFSL(SL,"dbs",True) # $dbs
                if _v is not None: write(_filter(_v, rawExpr='$dbs')) # from line 27, col 22.
                write('</option>\n')
            write('</select>\n')
        write('''</tr>
</table>

''')
        # 
        # <p>
        # $outputLine
        # </p>
        # <hr class="dbs" />
        # <h3>Where the following selection applied:</h3>
        write('\n<p>\n')
        _v = VFFSL(SL,"selectLine",True) # $selectLine
        if _v is not None: write(_filter(_v, rawExpr='$selectLine')) # from line 42, col 1.
        write('''
</p>
<p />
<div align="right">
<input type="button" value="Show all" id="showAll-button" onclick="javascript:SelectAll('selectDBS')" />
<input type="reset"  value="Reset" id="reset-button"  />
<input type="submit" value="Search" id="submit-button" onclick="javascript:showWaitingMessage();" />
</div>

''')
        # 
        # <div id="selMenu1">
        # <table class="selMenu">
        # <tr>
        # <td align="left">
        # <select onchange="javascript:ajaxGetSectionTables()" id="selSection">
        # #for section in $sectionList
        # <option>$section</option>
        # #end for
        # </select>
        # </td>
        # <td>
        # <div id="sectionTables"></div>
        # </td>
        # <td>
        # <div id="tableCols"></div>
        # </td>
        # </tr>
        # </table>
        # <script type="text/javascript">ajaxGetSectionTables('$sectionList[0]')</script>
        # </div>
        write('\n\n')
        # 
        # <table class="selMenu">
        # <tr>
        # <td align="left">
        # Algorithm menu
        # </td>
        # <td align="right">
        # <a href="javascript:ShowTag('algo_sel')">show</a>
        # &nbsp;
        # <a href="javascript:HideTag('algo_sel')">hide</a>
        # </td>
        # </tr>
        # </table>
        # <div id="algo_sel" class="hide">
        # $algoTables
        # </div>
        # 
        # <p />
        # <p />
        # 
        # <table class="selMenu">
        # <tr>
        # <td align="left">
        # Run/Limu menu
        # </td>
        # <td align="right">
        # <a href="javascript:ShowTag('runLumi_sel')">show</a>
        # &nbsp;
        # <a href="javascript:HideTag('runLumi_sel')">hide</a>
        # </td>
        # </tr>
        # </table>
        # <div id="runLumi_sel" class="hide">
        # $runsTables
        # </div>
        # 
        # <p />
        # <p />
        # 
        # <table class="selMenu">
        # <tr>
        # <td align="left">
        # File menu
        # </td>
        # <td align="right">
        # <a href="javascript:ShowTag('file_sel')">show</a>
        # &nbsp;
        # <a href="javascript:HideTag('file_sel')">hide</a>
        # </td>
        # </tr>
        # </table>
        # <div id="file_sel" class="hide">
        # $filesTables
        # </div>
        # 
        # <p />
        # <p />
        # 
        # <table class="selMenu">
        # <tr>
        # <td align="left">
        # Dataset menu
        # </td>
        # <td align="right">
        # <a href="javascript:ShowTag('dataset_sel')">show</a>
        # &nbsp;
        # <a href="javascript:HideTag('dataset_sel')">hide</a>
        # </td>
        # </tr>
        # </table>
        # <div id="dataset_sel" class="hide">
        # $datasetsTables
        # </div>
        # 
        # <p />
        # <p />
        # 
        # <table class="selMenu">
        # <tr>
        # <td align="left">
        # Your selections:
        # </td>
        # </tr>
        # <tr>
        # <td>
        # <div id="userSelections"></div>
        # </td>
        # </tr>
        # <tr>
        # <td align="right">
        # <input type="submit" value="Search" id="submit-button" onclick="javascript:showWaitingMessage();" />
        # &nbsp;
        # <input type="reset" value="Clear form" id="kw_clear_button"  />
        # </td>
        # </tr>
        # </table>
        write('\n\n')
        # 
        # <p>
        # Choose DBS instance:
        # <select name="keywordsSearch_dbsInst" id="keywordSearch_dbsSelector">
        # #for dbs in $dbsList
        # #if $dbs==$firstDBS
        # <option value="$dbs" selected="selected">$dbs</option>
        # #else
        # <option value="$dbs">$dbs</option>
        # #end if
        # #end for
        # <option value="All">All</option>
        # </select>
        # Keywords:
        # <select id="keywordSearch_keywordsSelector" onchange="PutKeyword(this.value);ClearKeywordsSelector()">
        # <option value="None" selected="selected">...</option>
        # #for key in $keyList
        # <option value="$key">$key</option>
        # #end for
        # </select>
        # </p>
        # 
        # <p>
        # <input type="text" name="keywords" size="60" id="keywordSelector" />
        # ###<textarea rows="5" cols="100" id="keywordSelector"></textarea>
        # </p>
        # <script type="text/javascript">ClearKeywordsSelector();ClearKeywordsInputValue()</script>
        # <hr class="dbs" />
        write('\n\n')
        # 
        # <table>
        # <tr>
        # <td align="right">DBS instance:</td>
        # <td><span class="gray_box" onMouseOver="KeywordHelp('DBS description')" onMouseOut="ClearTag('kw_help')">?</span></td>
        # <td>
        # <select name="keywordsSearch_dbsInst" id="keywordSearch_dbsSelector">
        # #for dbs in $dbsList
        # #if $dbs==$firstDBS
        # <option value="$dbs" selected="selected">$dbs</option>
        # #else
        # <option value="$dbs">$dbs</option>
        # #end if
        # #end for
        # <option value="All">All</option>
        # </select>
        # </td>
        # 
        # <td rowspan="8" valign="top"><span id="kw_help"></span></td>
        # </tr>
        # 
        # <tr>
        # <td><hr class="dbs" /></td>
        # <td></td>
        # <td></td>
        # </tr>
        # 
        # <tr>
        # <td align="right">Software release:</td>
        # <td><span class="gray_box" onMouseOver="KeywordHelp('software description')" onMouseOut="ClearTag('kw_help')">?</span></td>
        # <td><input type="text" size="60" name="kw_app" id="kw_algoSelector" /></td>
        # </tr>
        # 
        # <tr>
        # <td align="right">Primary dataset:</td>
        # <td><span class="gray_box" onMouseOver="KeywordHelp('Primary dataset description')" onMouseOut="ClearTag('kw_help')">?</span></td>
        # <td><input type="text" size="60" name="kw_prim" id="kw_primSelector" /></td>
        # </tr>
        # 
        # <tr>
        # <td align="right">Data Type:</td>
        # <td><span class="gray_box" onMouseOver="KeywordHelp('Data type description')" onMouseOut="ClearTag('kw_help')">?</span></td>
        # <td><input type="text" size="60" name="kw_tier" id="kw_tierSelector" /></td>
        # </tr>
        # 
        # <tr>
        # <td align="right">Run:</td>
        # <td><span class="gray_box" onMouseOver="KeywordHelp('Run description')" onMouseOut="ClearTag('kw_help')">?</span></td>
        # <td><input type="text" size="60" name="kw_run" id="kw_runsSelector" /></td>
        # </tr>
        # 
        # <tr>
        # <td><hr class="dbs" /></td>
        # <td></td>
        # <td></td>
        # </tr>
        # 
        # <tr>
        # <td align="right">Processed dataset:</td>
        # <td><span class="gray_box" onMouseOver="KeywordHelp('Processed dataset description')" onMouseOut="ClearTag('kw_help')">?</span></td>
        # <td><input type="text" size="60" name="kw_proc" id="kw_procSelector" /></td>
        # </tr>
        # 
        # 
        # <tr>
        # <td></td>
        # <td></td>
        # <td>
        # <input type="submit" value="Search" id="submit-button" onclick="javascript:showWaitingMessage();" />
        # &nbsp;
        # <input type="reset" value="Clear form" id="kw_clear_button"  />
        # ###<input type="reset" value="Clear form" id="kw_clear_button" onclick="javascript:ClearKeywordForm();" />
        # </td>
        # </tr>
        # </table>
        write('''

</form>
</div>
''')
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateSearchTable= 'respond'
if not hasattr(templateSearchTable, '_initCheetahAttributes'):
    templateClass = getattr(templateSearchTable, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateSearchTable)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateSearchTable()).run()


