#!/usr/bin/env python
#
# Read in a dump of hash and cfg file names from DBS
# produced as follows using the DBS command line interface
#
#   dbs lsa --algopattern=/*/CMSSW_1_8_4" > cmssw_1_8_4-algorithms.txt
#
# The file produced with that is read in to this procedure. The
# output of gethash.py is a file for each entry with name file.cfg.hash with
# the hash valiue inside.
#
from string import *
from sys import *

def getcfgname(input):
    cfgname=1


infile=argv[1]
inf=open(infile,"r")
for line in inf.readlines():
    if line=="":
        break
    if line.startswith("{"):
        start=line.find("$Source:")
        if start<1:
            continue
        else:
            part=line.split("$Source: ")
            cfgname=part[1].split("$")[0].replace("/cvs_server/repositories/CMSSW/CMSSW/Configuration/Spring08Production/data/","").replace(",v","").replace(" ","")
    elif line.startswith("CreationDate:"):
        continue
    elif line.startswith("LastModificationDate:"):
        continue
    elif line.startswith("       /cmsRun"):
        hash=line.split("/")[4].replace("\n","")
        if not cfgname.startswith("/"):
             outf=open(cfgname+".hash","w")
             outf.write("%s" %hash)
             outf.close()
inf.close()
            
