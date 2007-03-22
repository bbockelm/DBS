#!/usr/bin/env python
# $Id: $
"""
This represents a directed graph.
"""
__author__ = "Andrew J. Dolgert <ajd27@cornell.edu>"
__revision__ = "$Revision$"

import unittest
from logging import getLogger
from DotGraph import DotGraph
#import yaml

_logger = getLogger("ConstructQuery")


class Graph(object):
    def __init__(self,graph):
        '''The graph is a tuple, where each tuple member represents a
        node and the node contains a list of the other nodes to which
        it points. For instance, ((1,2),(3,),(),()) means node 0 points
        to nodes 1 and 2, node 1 points to 3, and nodes 3 and 4 have
        no edges leaving them.'''
        self._graph = tuple([tuple(x) for x in graph])
        self._coverage = None
        self._reverse  = None
        self._edgeCnt  = None
    
    def __eq__(self,other):
        '''Order of the edges doesn't count when measuring equality.'''
        if len(self._graph) is not len(other._graph):
            return False
        equal = True
        for nodeIdx in range(0,len(self._graph)):
            left =list(self._graph[nodeIdx])
            right=list(other._graph[nodeIdx])
            left.sort()
            right.sort()
            if left != right:
                equal = False
        return equal
    
    def __repr__(self):
        return repr(self._graph)
    
    def __len__(self):
        return len(self._graph)

    def NumberOfEdges(self):
        if not self._edgeCnt:
            self._edgeCnt = reduce( lambda x, y: x+len(y), self._graph, 0)
        return self._edgeCnt

    def NumberOfNodes(self):
        visited = [False for x in self._graph]
        for n in xrange(0,len(self._graph)):
            endpts = self._graph[n]
            if endpts:
                visited[n] = True
                for endpt in endpts:
                    visited[endpt] = True
        return len(filter(lambda x: x is True, visited))

    def BreadthFirstSearch(self, nodeIdx):
        '''Find a breadth first spanning tree of the graph. The
        return value is another graph.'''
        unexplored = [nodeIdx]
        visited    = [False for x in self._graph]
        span       = [[]    for x in self._graph]
        
        startingNodeIdx = nodeIdx
        while unexplored:
            nodeIdx = unexplored[0]
            if nodeIdx>=len(visited) or nodeIdx<0:
                _logger.debug("graph %s nodeIdx %d start %d" % (repr(self._graph),
                                                                nodeIdx,startingNodeIdx))
            visited[nodeIdx] = True
            
            for adjacent in self._graph[nodeIdx]:
                if not visited[adjacent]:
                    unexplored.append(adjacent)
                    visited[adjacent] = True
                    span[nodeIdx].append(adjacent)

            del unexplored[0]
        return RootedGraph(span, startingNodeIdx)

    def BreadthFirst(self, nodeIdx):
        '''Return an iterator whose value is (child,parent)
        for every edge in the graph.'''
        unexplored = [(nodeIdx, None)]
        visited    = [False for x in self._graph]
        
        startingNodeIdx = nodeIdx
        yield unexplored[0]
        
        while unexplored:
            (nodeIdx, parent) = unexplored[0]
            visited[nodeIdx] = True
            
            for adjacent in self._graph[nodeIdx]:
                if not visited[adjacent]:
                    unexplored.append((adjacent, nodeIdx))
                    visited[adjacent] = True
                    yield (adjacent, nodeIdx)

            del unexplored[0]
        return


    def EdgesOfSubtreeIncluding(self, nodeSet):
        '''Assumes we are working with a spanning tree with
        single directed edges. Finds edges including the nodeSet.'''
        reverse = self.GetReverse()
        edges = set()
        for node in nodeSet:
            nodeEdges = reverse.EdgesFromNode(node)
            edges = edges.union(nodeEdges)
        return edges
        
    def GetUndirected(self):
        undirected = [set(edges) for edges in self._graph]
        for nodeIdx in range(0,len(undirected)):
            for edgeEnd in undirected[nodeIdx]:
                undirected[edgeEnd].add(nodeIdx)
        return Graph((tuple(x) for x in undirected))

    def GetCoverage(self):
        '''Coverage returns the set of nodes in the graph. We say a node
        is in the graph if it is on an edge.'''
        if self._coverage:
            return self._coverage
        self._coverage = set()
        for nodeIdx in range(0,len(self._graph)):
            node = self._graph[nodeIdx]
            if node:
                self._coverage.add(nodeIdx)
                for endIdx in node:
                    self._coverage.add(endIdx)
        return self._coverage
    
    def GetReverse(self):
        '''Reverse the direction of the graph.'''
        if self._reverse:
            return self._reverse
        
        trackback = [set() for x in self._graph]
        for nodeIdx in range(0,len(self._graph)):
            for edge in self._graph[nodeIdx]:
                trackback[edge].add(nodeIdx)
        self._reverse = Graph(trackback)
        return self._reverse
    
    def EdgesFromNode(self, nodeIdx):
        '''Given a node, follow its edges until
        the end. Return the edges. Assumes only one
        edge out of each node.'''
        edges = set()
        nextNodes = self._graph[nodeIdx]
        while nextNodes:
            nextNode = nextNodes[0]
            edges.add((nodeIdx, nextNode))
            nodeIdx = nextNode
            nextNodes = self._graph[nodeIdx]
        return edges
    
    def WriteGraph(self, dot):
        dot.Name("G")
        for startIdx in range(0,len(self._graph)):
            end=self._graph[startIdx]
            for endIdx in end:
                dot.AddEdge(repr(startIdx), repr(endIdx))
        dot.Finish()

class RootedGraph(Graph):
    def __init__(self, graph, root, *args):
        apply(Graph.__init__, (self,graph)+args)
        self.rootIdx = root

    def __repr__(self):
        return "Root(%d) %s" % (self.rootIdx, Graph.__repr__(self))

    def AddBranchIfInSet(self, nodeIdx, nodeSet, addTo):
        branches = self._graph[nodeIdx]
        add = []
        if branches:
            for branch in branches:
                if self.AddBranchIfInSet(branch, nodeSet, addTo):
                    add.append(branch)
                    
        addTo[nodeIdx] = add
        if add or (nodeIdx in nodeSet):
            return True
        return False
    
    def BreadthFirst(self):
        return apply(Graph.BreadthFirst, (self, self.rootIdx))
    
    def SubtreeIncluding(self, nodeSet):
        '''Find a subtree of a directed, acyclic graph pruning
        branches which don't contain nodes in the nodeSet.'''
        if not nodeSet.issubset(self.GetCoverage()): return None
        
        addTo = [[] for x in self._graph]
        self.AddBranchIfInSet(self.rootIdx, nodeSet, addTo)
        subtree = RootedGraph(addTo, self.rootIdx)
        return subtree

class TestGraph(unittest.TestCase):
    def setUp(self):
        self.graph0 = (
            (1,2), # 0->1,2
            (3,),   # 1->3
            (),
            ()
            )
        self.graph1 = (
            (),
            (2,),
            (),
            ()
            )

    def testBreadthIter(self):
        g = Graph(self.graph0)
        for (node, parent) in g.BreadthFirst(0):
            print node, parent
        g = RootedGraph(self.graph0, 0)
        for (node, parent) in g.BreadthFirst():
            print node, parent

    def testBreadthSearch(self):
        g = Graph(self.graph0)
        span = g.BreadthFirstSearch(0)
        print "span 2",span
        self.assertEqual(span, Graph(( (1,2), (3,), (), ())))
        span = g.BreadthFirstSearch(1)
        print "span 1", span
        self.assertEqual(span, Graph((
            (), (3,), (), ()
            )))
        span = g.BreadthFirstSearch(3)
        print "span 3", span
        self.assertEqual(span, Graph((
            (), (), (), ()
            )))
        
    def testCoverage(self):
        g = Graph(self.graph0)
        cover = g.GetCoverage()
        self.assertEqual(cover, set((0,1,2,3)))
        cover = Graph(self.graph1).GetCoverage()
        self.assertEqual(cover, set((1,2)))

    def testDouble(self):
        g = Graph(self.graph1)
        do = g.GetUndirected()
        self.assertEqual(do,Graph((
            (),(2,),(1,),())))
        g = Graph(self.graph0)
        do = g.GetUndirected()
        self.assertEqual(do,Graph((
            (1,2),(0,3),(0,),(1,))))
        
    def testSubtree(self):
        g = RootedGraph(self.graph0,0)
        h = g.SubtreeIncluding(set((0,)))
        self.assertEqual(h, RootedGraph((
            (), (), (), ()), 0))
        h = g.SubtreeIncluding(set((0,1)))
        self.assertEqual(h, RootedGraph((
            (1,), (), (), ()), 0))
        h = g.SubtreeIncluding(set((2,)))
        self.assertEqual(h, RootedGraph((
            (2,), (), (), ()), 0))
        h = g.SubtreeIncluding(set((0,2,3)))
        self.assertEqual(h, RootedGraph((
            (1,2), (3,), (), ()), 0))

        
    def testReverse(self):
        g = Graph(self.graph0)
        do = g.GetReverse()
        print do
        self.assertEqual(do,Graph((
            (), (0,), (0,), (1,)
            )))
    
    def testEdgesFromNodes(self):
        g = Graph(self.graph0)
        do = g.EdgesFromNode(0)
        print do
        self.assertEqual(do,set(((0,1),(1,3))))
        do = g.EdgesFromNode(1)
        print do
        self.assertEqual(do,set(((1,3),)))



def suite():
    suite = unittest.TestSuite()
    suite.addTest(unittest.makeSuite(TestGraph))
    return suite
        
        
if __name__ == '__main__':
    unittest.TextTestRunner(verbosity=2).run(suite())

