#!/usr/bin/env python
"""



Tool to generate flat name:value text file from config

"""

import sys
import os
import getopt
from string import *
from FWCore.ParameterSet.Types import *
from FWCore.ParameterSet.Config import *
import pdb



#import Spring08_Zmumu_10TeV_cfi as process

#from FWCore.ParameterSet.Config import include
import FWCore.ParameterSet.Config as cms
cfgfile=sys.argv[1].replace(".py","")
print "Processing file:",cfgfile
#process = include(cfgfile)
#import ff000a188610f3ca2f73f6f76cb505d9_cfg as process
process=__import__(cfgfile)

#from FWCore.ParameterSet.Mixins import _SimpleParameterTypeBase, _ParameterTypeBase
#from Mixins import PrintOptions,_ParameterTypeBase,_SimpleParameterTypeBase, _Parameterizable, _ConfigureComponent, _TypedParameterizable, _Labelable,  _Unlabelable,  _ValidatingListBase
#from Mixins import *
#from Types import *

def indexDict(modName, modRef):
    """
    _indexDict_

    Given a PSet like python dictionary, generate an index file from
    it. If it has PSet children, act on them recursively

    """
    result = []
    #pdb.set_trace()
    #  //
    # // Traverse module parameters
    #//
    for key, value in modRef.__dict__.items():
        #  //
        # // Is this a PSet? If so descend into it
        #//
        #isPset = value[0] == "PSet"
        #isPset = isinstance(key,PSet)
        isPSet = isinstance(value,PSet)
        isVPSet = isinstance(value,VPSet)
        isSource= isinstance(value,Source)
        #isProcess = (modName=="process")
        #print "isPSet",isPSet,"isVPSet",isVPSet,"isProcess",isProcess
        #isSchedule=isinstance(value,Schedule)
        if not (isPSet or isVPSet or isSource):
            #  //
            # // This is not a PSet, index it
            #//  Drop it if it is untracked, or an internal key (@)
            if key.startswith("_"):
                continue
            #if value[1] == "untracked":
            #print value
            try:
              value_is_tracked=value.isTracked()
              #print "**** isTracked OK ****: modName:",modName,"modRef:",modRef,"KEY:",key,"VALUE:",value
            except:
              #print findProcess(modName)
              # still need to look at this, may need some work.
              #print "**** isTracked exception ****: modName:",modName,"modRef:",modRef,"KEY:",key,"VALUE:",value
              continue
            if not value.isTracked():
                continue
            if isinstance(value,Schedule):
                # Important note, for now, just ignore these. However theese are HLT paths and 
                # probably should be indexed at some point
                continue
            if isinstance(value,vstring):
                #print "**** isTracked OK ****: modName:",modName,"modRef:",modRef,"KEY:",key,"VALUE:",value
                # special case, if vstring is pythia parameter set, expand
                #print "key",key.find("pythia")
                """ this still needs to be fixed, the following example does not
                    work, need to figure out general case vstring PythiaSource.
                    PythiaParameters.parameterSets=['pythiaUESettings', 'processParameters']
                    vstring PythiaSource.PythiaParameters.processParameters=
                    ['MSEL=1 ! QCD hight pT processes',
                     'CKIN(3)=170.          ! minimum pt hat for hard interactions',
                     'CKIN(4)=230.          ! maximum pt hat for hard interactions']

                    I think the solution may be to find the "PythiaParameters" and
                    look for each entry in the list i.e. in this
                    case 'pythiaUESettings' and 'processParameters'.
                    Need to find out . LL 5/21/2008 (fix for PythiaParameters LL 7/2/2008)
                """
                if key.find("pythiaUESettings")>=0 or key.find("processParameters")>=0 :
                    #pdb.set_trace()
                    for param in value.value():
                       #found case with "=1. 0" (extra space)
                       param=param.replace(" ","")
                       name,val=strip(param.split("!")[0]).split("=")
                       result.append(("double", "%s.%s.%s=%s" % ( modName, key, name, val)))
                else:
                    # not a pythia parameter
                    valueOrValues=value.value()
                    result.append((value.configTypeName(), "%s.%s=%s%s%s" % ( modName, key,'"', valueOrValues,'"'))) #needs to have quotes
                continue
            if isinstance(value,double) or isinstance(value, int32) or isinstance(value, bool) or isinstance(value, uint32) :
                valueOrValues=value.value()
                result.append((value.configTypeName(), "%s.%s=%s" % ( modName, key, valueOrValues)))
                continue
            if isinstance(value,VInputTag):
                # VInputTags need to be converted to a list. There may be others that also need conversion
                try:
                  valueOrValues=[]
                  for  i in value.value(): valueOrValues.append(i.getModuleLabel())
                except:
                  #print "value.value()",value.value()
                  continue
            elif isinstance(value,InputTag):
                # InputTag type
                #pdb.set_trace()
                valueOrValues=value.getModuleLabel()
            #elif isinstance(value,VPSet):
                # VPSet and VSet need to be converted to dictionaries
                #valueOrValues={}
                #for  i in value.value(): valueOrValues.append(i.getModuleLabel())
                #valueOrValues=value.value() #this is not correct, needs to be fixed.
            elif isinstance(value,string) or isinstance(value,vstring) :
                #string needs to have "" around it
                valueOrValues=value.value()
            elif isinstance(value,Sequence):
                continue
            else:
                valueOrValues=value.value()
            result.append((value.configTypeName(), "%s.%s=%s%s%s" % ( modName, key,'"', valueOrValues, '"')))
        else:
            #  //
            # // Recursively descend into PSet
            #//
            children = indexDict(key, value)
            #print "Key:",key
            #print "Value:",value
            #print "Children:",children
            [ result.append( (i[0], "%s.%s" % (modName, i[1]))) for i in children ]
            
    return result
    



def actOnCfg(nodename, cfgInt, hashValue, cfgFile):
    """
    _actOnCfg_

    Found a cfg, write it out as cfg string and make an index file
    if required.

    """
    #cfgFile = "%s.%s.cfg" % (os.path.basename(workflow), nodename)
    #handle = open(cfgFile, 'w')
    #handle.write(cfgInt.cmsConfig.asConfigurationString())
    #handle.close()
    #print "Wrote cfg file: %s" % cfgFile

    #if not doIndex:
        #return
    print "Generating Index..."
    indices = [("PSetHash", "PSetHash=\"%s\"" % hashValue)]
    #  //
    # // Go through module by module and generate index
    #//
    for moduleName in process.__dict__.keys():
        if (moduleName.startswith("_") or moduleName.endswith("_")):
           print "Skipping:",moduleName
           continue
        print "  Indexing Module: %s" % moduleName
        moduleRef = cfgInt.__dict__[moduleName]
        indexParams = indexDict(moduleName, moduleRef)
        #print "####### indexParams ####"
        #print indexParams
        indices.extend(indexParams)
    print "Found %s Indices" % len(indices)

    #  //
    # // Write index file
    #//
    indexFile = cfgFile+".index"
    handle = open(indexFile, 'w')
    for line in indices:
        handle.write("%s %s\n" % (line[0], line[1]))
    handle.close()
    print "Wrote index file: %s" % indexFile
    return
    
def findCfgFiles(node):
    """
    _findCfgFiles_

    Look for cms cfg file in payload node provided

    """
    try:
        hash = node._OutputDatasets[0]['PSetHash'] 
        cfg = CfgInterface(node.configuration, True)
    except Exception, ex:
        # Not a cfg file
        return
    
    actOnCfg(node.name, cfg, hash)
    return

    

# Generating flat files
#hashval="12345678901234567890123456789012"
hashval=cfgfile.replace("_cfg","")
cfg = process
actOnCfg("",cfg,hashval,cfgfile)
