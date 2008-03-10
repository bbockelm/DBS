#!/usr/bin/env python

from sqlalchemy import sql
#from sqlalchemy import Join

def WriteSqlAlchemyGraph(dot,metadata,excludeTables):
    '''This shows how tables relate through foreign keys in a sqlalchemy schema.'''
    if metadata.name:
        dot.Name(metadata.name)
    else:
        dot.Name("A")
    
    for tableName in metadata.tables:
        if tableName in excludeTables: continue
        foreignKeys=metadata.tables[tableName].foreign_keys
        for fk in foreignKeys:
            right=fk.column.table.name
            if right not in excludeTables:
                dot.AddEdge(tableName,right)
    dot.Finish()
    

class MeasureGraph(object):
    '''This is a writer that just counts the number of edges in a graph.'''
    def __init__(self):
        self.name=""
        self.edgeCnt=0
        
    def Name(self,name):
        self.name=name
        
    def AddEdge(self,left,right):
        self.edgeCnt=self.edgeCnt+1

    def Finish(self):
        pass
    
def _WriteSide(dot,join):
    onclause=join.onclause
    dot.AddEdge(onclause.left.table.name,onclause.right.table.name)
    if isinstance(join.left,Join):
        _WriteSide(dot,join.left)
    if isinstance(join.right,Join):
        _WriteSide(dot,join.right)

def WriteQueryAlchemyGraph(dot,query):
    '''This writes a sqlalchemy query as a graph showing which tables
    join with which other tables.'''
    dot.Name("A")
    froms=[x for x in query.froms]
    if froms:
        _WriteSide(dot, froms[0])
    dot.Finish()
    
