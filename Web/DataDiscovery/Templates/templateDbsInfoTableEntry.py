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
from DDUtil import sizeFormat, colorSizeHTMLFormat
from DDUtil import splitString 

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
__CHEETAH_genTime__ = 'Sun Mar  4 15:25:33 2007'
__CHEETAH_srcLastModified__ = 'Sun Mar  4 13:01:16 2007'
__CHEETAH_src__ = 'Templates/tmpl/templateDbsInfoTableEntry.tmpl'
__CHEETAH_version__ = '2.0b5'

##################################################
## CLASSES

class templateDbsInfoTableEntry(Template):

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
        
        for dbsDict in VFFSL(SL,"dbsList",True):
            bName = VFFSL(SL,"dbsDict",True)['Name']
            sName = VFFSL(SL,"splitString",False)(VFFSL(SL,"dbsDict",True)['Name'],30,"\\n")
            write('<tr valign="top" bgcolor="#FFFADC" name="dbs_row_sumInfo" id="dbs_row_sumInfo">\n<td align="left" class="td20">')
            _v = VFFSL(SL,"sName",True) # $sName
            if _v is not None: write(_filter(_v, rawExpr='$sName')) # from line 7, col 31.
            write('</td>\n<td align="right">')
            _v = VFFSL(SL,"dbsDict",True)['NumberOfEvents'] # $dbsDict['NumberOfEvents']
            if _v is not None: write(_filter(_v, rawExpr="$dbsDict['NumberOfEvents']")) # from line 8, col 19.
            write('</td>\n<td align="right">')
            _v = VFFSL(SL,"dbsDict",True)['NumberOfFiles'] # $dbsDict['NumberOfFiles']
            if _v is not None: write(_filter(_v, rawExpr="$dbsDict['NumberOfFiles']")) # from line 9, col 19.
            write('</td>\n<td align="right">')
            _v = VFFSL(SL,"colorSizeHTMLFormat",False)(VFFSL(SL,"dbsDict",True)['BlockSize']) # $colorSizeHTMLFormat($dbsDict['BlockSize'])
            if _v is not None: write(_filter(_v, rawExpr="$colorSizeHTMLFormat($dbsDict['BlockSize'])")) # from line 10, col 19.
            write('</td>\n<td align="center">\n<a href="javascript:popUp(\'')
            _v = VFFSL(SL,"host",True) # $host
            if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 12, col 28.
            write('/getLFN_cfg?dbsInst=')
            _v = VFFSL(SL,"dbsInst",True) # $dbsInst
            if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 12, col 53.
            write('&amp;blockName=')
            _v = VFFSL(SL,"bName",True) # $bName
            if _v is not None: write(_filter(_v, rawExpr='$bName')) # from line 12, col 76.
            write('\',1000)">cff</a>, \n<a href="javascript:popUp(\'')
            _v = VFFSL(SL,"host",True) # $host
            if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 13, col 28.
            write('/getLFN_txt?dbsInst=')
            _v = VFFSL(SL,"dbsInst",True) # $dbsInst
            if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 13, col 53.
            write('&amp;blockName=')
            _v = VFFSL(SL,"bName",True) # $bName
            if _v is not None: write(_filter(_v, rawExpr='$bName')) # from line 13, col 76.
            write('\',900)">txt</a>,\n<a href="javascript:popUp(\'')
            _v = VFFSL(SL,"host",True) # $host
            if _v is not None: write(_filter(_v, rawExpr='$host')) # from line 14, col 28.
            write('/getLFNlist?dbsInst=')
            _v = VFFSL(SL,"dbsInst",True) # $dbsInst
            if _v is not None: write(_filter(_v, rawExpr='$dbsInst')) # from line 14, col 53.
            write('&amp;blockName=')
            _v = VFFSL(SL,"bName",True) # $bName
            if _v is not None: write(_filter(_v, rawExpr='$bName')) # from line 14, col 76.
            write('\',1000)">details</a>\n</td>\n')
            if int(VFFSL(SL,"dbsDict",True)['OpenForWriting'])==1:
                write('<td align="center" style="background-color:AliceBlue;">OPEN</td>\n')
            else:
                write('<td align="center" style="background-color:AntiqueWhite;">CLOSED</td>\n')
            write('<td align="right">')
            _v = VFFSL(SL,"dbsDict",True)['CreatedBy'] # $dbsDict['CreatedBy']
            if _v is not None: write(_filter(_v, rawExpr="$dbsDict['CreatedBy']")) # from line 21, col 19.
            write('</td>\n<td align="center">')
            _v = VFFSL(SL,"dbsDict",True)['CreationDate'] # $dbsDict['CreationDate']
            if _v is not None: write(_filter(_v, rawExpr="$dbsDict['CreationDate']")) # from line 22, col 20.
            write('</td>\n<td align="right">')
            _v = VFFSL(SL,"dbsDict",True)['LastModifiedBy'] # $dbsDict['LastModifiedBy']
            if _v is not None: write(_filter(_v, rawExpr="$dbsDict['LastModifiedBy']")) # from line 23, col 19.
            write('</td>\n<td align="center">')
            _v = VFFSL(SL,"dbsDict",True)['LastModificationDate'] # $dbsDict['LastModificationDate']
            if _v is not None: write(_filter(_v, rawExpr="$dbsDict['LastModificationDate']")) # from line 24, col 20.
            write('</td>\n</tr>\n')
        
        ########################################
        ## END - generated method body
        
        return _dummyTrans and trans.response().getvalue() or ""
        
    ##################################################
    ## CHEETAH GENERATED ATTRIBUTES


    _CHEETAH__instanceInitialized = False

    def __str__(self): return self.respond()

    _mainCheetahMethod_for_templateDbsInfoTableEntry= 'respond'
if not hasattr(templateDbsInfoTableEntry, '_initCheetahAttributes'):
    templateClass = getattr(templateDbsInfoTableEntry, '_CHEETAH_templateClass', Template)
    templateClass._assignRequiredMethodsToClass(templateDbsInfoTableEntry)


# CHEETAH was developed by Tavis Rudd and Mike Orr
# with code, advice and input from many other volunteers.
# For more information visit http://www.CheetahTemplate.org/

##################################################
## if run from command line:
if __name__ == '__main__':
    from Cheetah.TemplateCmdLineIface import CmdLineIface
    CmdLineIface(templateObj=templateDbsInfoTableEntry()).run()


