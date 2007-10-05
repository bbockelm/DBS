#!/usr/bin/env python

import os, sys, string

name=sys.argv[1]
fout=open(name+".js",'w')
flist = open(name).readlines()
fout.write("/*"+str(flist)+"*/")
for line in open(name).readlines():
    fin=open(line.replace('\n',''),'r')
    fout.write(''.join(fin.readlines()))


