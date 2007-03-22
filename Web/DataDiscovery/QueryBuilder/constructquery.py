#!/usr/bin/env python
# $Id: $
"""
Given a graph representing a schema and nodes to hit, this class does a poor
man's travelling salesman solution.
"""
__author__ = "Andrew J. Dolgert <ajd27@cornell.edu>"
__revision__ = "$Revision$"

import unittest
from logging import getLogger
from DotGraph import DotGraph
from Graph import Graph, RootedGraph
#import yaml

_logger = getLogger("ConstructQuery")


class ConstructQuery(object):
    def __init__(self, connectivity):
        '''Connectivity describes which tables have foreign keys
        into which other tables.'''
        gs = Graph(connectivity)
        undirected = gs.GetUndirected()
        self._spanning = []
        for nodeIdx in range(0,len(undirected)):
            span = undirected.BreadthFirstSearch(nodeIdx)
            self._spanning.append(span)
    
    def PrintSpans(self):
        spanIdx=0
        for span in self._spanning:
            dot = DotGraph(file("span%d.dot" % (spanIdx),"w"))
            span.WriteGraph(dot)
            spanIdx=spanIdx+1

    def GetSmallestSubtree(self, queryElements):
        '''Find the subtree containing the query elements.'''
        querySet = set(queryElements)
        minLen = len(self._spanning)+1
        smallestSpan = None
        spanIdx=0
        for span in self._spanning:
            subSpan = span.SubtreeIncluding(querySet)
            if subSpan is not None:
                if subSpan.NumberOfEdges()<minLen:
                    minLen = subSpan.NumberOfEdges()
                    smallestSpan = subSpan
            spanIdx=spanIdx+1
                    
        return smallestSpan

    def GetStatementJoins(self, queryElements):
        '''The argument is a list of which tables contain the desired
        elements. The return value is a list of joins between those tables.'''
        querySet = set(queryElements)
        
        edgeSets = []
        minLen = len(self._spanning)
        minIdx = -1
        for nodeIdx in range(0,len(self._spanning)):
            subs = self._spanning[nodeIdx].EdgesOfSubtreeIncluding(querySet)
            if len(subs)<minLen:
                minLen = len(subs)
                minIdx = nodeIdx
            edgeSets.append(subs)
        return (minIdx, edgeSets[minIdx])
    
class TestConstructQuery(unittest.TestCase):
    def setUp(self):
        pass


    def testTextGraph(self):
        schemaString = """
ProcessedDataset:
  - member : status
  - member : group
  - member   : PrimaryDataset
    join     : PrimaryDataset
PrimaryDataset:
  - Description, PrimaryDatasetDescription
  - joinType, PrimaryDSType
"""
        #schemaDict = yaml.load(schemaString)
        #print schemaDict
        
    def listTest(self,result,test):
        res =list(result)
        res.sort()
        self.assertEqual(tuple(res), test)
    
    def testSmallGraph(self):
        schema = (
            (1,2), # 0->1,2
            (3,),   # 1->3
            (),
            ()
            )
        cq = ConstructQuery(schema)
        (min,res0) = cq.GetStatementJoins((0,3))
        self.listTest(res0, ((1, 0), (3, 1)))
        (min,res1) = cq.GetStatementJoins((3,1,0))
        self.listTest(res1, ((1,0), (3,1)))
        (min,res2) = cq.GetStatementJoins((0,2))
        self.listTest(res2, ((2,0),))
        (min,res3) = cq.GetStatementJoins((0,))
        self.listTest(res3, ())
            
    def testSubtree(self):
        schema = (
            (1,2), # 0->1,2
            (3,),   # 1->3
            (),
            ()
            )
        cq = ConstructQuery(schema)
        res = cq.GetSmallestSubtree((0,3))
        print "subtree 0 3", res
        #self.assertEqual(res, RootedGraph())
        res = cq.GetSmallestSubtree((3,1,0))
        print "subtree 0 1 3", res
        #self.listTest(res, ((1,0), (3,1)))
        res = cq.GetSmallestSubtree((0,2))
        print "subtree 0 2", res
        #self.listTest(res, ((2,0),))
        res = cq.GetSmallestSubtree((0,))
        print "subtree 0", res
        
def suite():
    suite = unittest.TestSuite()
    suite.addTest(unittest.makeSuite(TestGraph))
    suite.addTest(unittest.makeSuite(TestConstructQuery))
    return suite
        
        
if __name__ == '__main__':
    unittest.TextTestRunner(verbosity=2).run(suite())

