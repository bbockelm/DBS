#!/usr/bin/env python
# $Id: $
"""
Writes a graph in a format for graphviz's dot program to make an image.
"""
__author__ = "Andrew J. Dolgert <ajd27@cornell.edu>"
__revision__ = "$Revision$"

class DotGraph(object):
    def __init__(self, file):
        self.name = "G"
        self.edges = []
        self.out = file
    
    def Name(self,name):
        self.name = name
        
    def AddEdge(self,start,finish):
        self.edges.append((start,finish))
        
    def Finish(self):
        print >>self.out, "digraph %s {" % (self.name)
        for edge in self.edges:
            print >>self.out, "    %s -> %s;" % (edge)
        print >>self.out, "}"
        