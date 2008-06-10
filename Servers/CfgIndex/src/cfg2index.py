#!/usr/bin/env python
"""



Tool to generate flat name:value text file from config

"""

import sys
import os
import getopt
from string import *
from FWCore.ParameterSet.Types import *
import pdb



#import Spring08_Zmumu_10TeV_cfi as process

from FWCore.ParameterSet.Config import include
cfgfile=sys.argv[1]
process = include(cfgfile)


from FWCore.ParameterSet.Mixins import _SimpleParameterTypeBase, _ParameterTypeBase



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
        if not (isPSet or isVPSet):
            #  //
            # // This is not a PSet, index it
            #//  Drop it if it is untracked, or an internal key (@)
            if key.startswith("_"):
                continue
            #if value[1] == "untracked":
            if not value.isTracked():
                continue
            if isinstance(value,vstring):
                # special case, if vstring is pythia parameter set, expand
                #print "key",key.find("pythia")
                """ this still needs to be fixed, the following example does not work, need to figure out general case
                vstring PythiaSource.PythiaParameters.parameterSets=['pythiaUESettings', 'processParameters']
                vstring PythiaSource.PythiaParameters.processParameters=['MSEL=1               ! QCD hight pT processes',
                'CKIN(3)=170.          ! minimum pt hat for hard interactions', 'CKIN(4)=230.          ! maximum pt hat for hard interactions']

                I think the solution is to find the "PythiaParameters" and look for each entry in the list i.e. in this
                case 'pythiaUESettings' and 'processParameters'. Need to find out . LL 5/21/2008
                """
                if key.find("pythia")>=0:
                    #pdb.set_trace()
                    for param in value.value():
                       name,val=strip(param.split("!")[0]).split("=")
                       result.append(("double", "%s.%s.%s=%s" % ( modName, key, name, val)))
                else:
                    # not a pythia parameter
                    valueOrValues=value.value()
                    result.append((value.configTypeName(), "%s.%s=%s" % ( modName, key, valueOrValues)))
                continue    
            if isinstance(value,VInputTag):
                # VInputTags need to be converted to a list. There may be others that also need conversion
                valueOrValues=[]
                for  i in value.value(): valueOrValues.append(i.getModuleLabel())
            elif isinstance(value,InputTag):
                # InputTag type
                #pdb.set_trace()
                valueOrValues=value.getModuleLabel()
            #elif isinstance(value,VPSet):
                # VPSet and VSet need to be converted to dictionaries
                #valueOrValues={}
                #for  i in value.value(): valueOrValues.append(i.getModuleLabel())
                #valueOrValues=value.value() #this is not correct, needs to be fixed.
            elif isinstance(value,string):
                #string needs to have "" around it
                valueOrValues='"'+value.value()+'"'
            else:
                valueOrValues=value.value()
            result.append((value.configTypeName(), "%s.%s=%s" % ( modName, key, valueOrValues)))
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
    for moduleName in cfgInt.__dict__.keys():
        if (moduleName.startswith("_") or moduleName.endswith("_")):
           print "Skipping:",moduleName
           continue
        print "  Indexing Module: %s" % moduleName
        moduleRef = cfgInt.__dict__[moduleName]
        indexParams = indexDict(moduleName, moduleRef)
        print "####### indexParams ####"
        print indexParams
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
ffn=cfgfile
ffn_hash=ffn_hash=ffn+".hash"
if(not os.access(ffn_hash,os.F_OK)):
   print "Skipping unprepared file",ffn
   exit
fhash=open(ffn_hash,"r")
hashval=fhash.read()
fhash.close()
hashval=hashval.strip()
cfg = process
actOnCfg("",cfg,hashval,ffn)
