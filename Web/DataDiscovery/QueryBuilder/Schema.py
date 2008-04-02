#!/usr/bin/env python
# $Id: Schema.py,v 1.7 2008/03/25 16:29:13 valya Exp $
"""
This class reads sqlalchemy schema metadata in order to construct joins
for an arbitrary query.
"""
__author__ = "Andrew J. Dolgert <ajd27@cornell.edu>"
__revision__ = "$Revision: 1.7 $"


import unittest
from logging import getLogger
from StringIO import StringIO
from sqlalchemy import *
import os

try:
    from UnittestDB import LoadFromFile, UnittestDB
except:
    pass
import constructquery
from DotGraph import DotGraph


_logger = getLogger("ConstructQuery")


def FindTableName(schema, name):
    personTables=[]
    for tableName in schema.tables:
        if tableName.lower() == name.lower():
            personTables.append(tableName)
    if len(personTables)>1:
        raise Exception, "More than one person table %s." % (personTables,)
    elif len(personTables)==0:
        return None
    return personTables[0]

def FindTable(schema, name):
    personTables=[]
    for tableName in schema.tables:
        if tableName.lower() == name.lower():
            personTables.append(schema.tables[tableName])
    if len(personTables)>1:
        raise Exception, "More than one person table %s." % (personTables,)
    elif len(personTables)==0:
        return None
    return personTables[0]



class Schema(object):
    '''This object is created around a sqlalchemy MetaData object. When given
    a sqlalchemy select object in BuildQuery, it constructs determines how to
    join tables from the MetaData to support the query.'''
    def __init__(self,tables,foreignKeys=None,owner=None):
        class MySchema(object):
            def __init__(self,tables):
                self.tables=tables
        # The dictionary keys disagree with table names. Make them agree.
        tableDict={}
        for tableName in tables:
            table=tables[tableName]
#            tableDict[table.name]=table
            tableDict[table.fullname]=table
        self._schema = MySchema(tableDict)
        self._ordered = None
        personTable='person'
        if owner:
           self._owner=owner.lower()
           personTable='%s.person'%self._owner
        else:
           self._owner=owner
        self._foreignTables = {}
#        self._personTable=FindTable(self._schema,'person')
        self._personTable=FindTable(self._schema,personTable)

        if self._personTable:
            toExclude=set([self._personTable])
        else:
            toExclude=set()
        connectivity = self.GraphFromSchema(self._schema,
                                            self.MakeForeignKeys(foreignKeys,self._schema),
                                            toExclude)
        self.cq = constructquery.ConstructQuery(connectivity)
        
        
    def PullOperatorSide(self,clause,tablesOfConcern):
        if clause.__dict__.has_key('left'):
            if issubclass(clause.left.__class__,Column):
                tablesOfConcern.add(clause.left.table)
        if clause.__dict__.has_key('right'):
            if issubclass(clause.right.__class__,Column):
                tablesOfConcern.add(clause.right.table)
        
    def BuildQuery(self,query):
        rootJoin = self.RootJoin(query)
        query.append_from(rootJoin)
        return query

    def BuildQueryWithSel(self,sel,query):
        rootJoin = self.RootJoin(query)
        sel.append_from(rootJoin)
        return sel

    def RootJoin(self,query):
        '''Query is a sqlalchemy query with select elements and where clauses.
        This method looks through the elements and clauses to determine which
        tables need to be joined in order to support the query. It then adds
        those joins to the query and returns it.'''

        tablesOfConcern=set()
        # col.table returns a select statement. We want the metadata table.
        # This is unsupported in sqlalchemy!
        for col in query._raw_columns:
            tablesOfConcern.add(col.table)
        if  query.__dict__.has_key('whereclause'): # SQLAlchemy 0.3
            whereclause=query.whereclause
        elif query.__dict__.has_key('_whereclause'): # SQLAlchemy 0.4
            whereclause=query._whereclause
        else:
            raise "Query '%s' does not contain whereclause"%query
        if whereclause:
            if whereclause.__dict__.has_key('clauses'):
                for clause in whereclause.clauses:
                    self.PullOperatorSide(clause,tablesOfConcern)
            else:
                self.PullOperatorSide(whereclause,tablesOfConcern)

        # No need to calculate joins if there is only one table involved.
        # We actually make mistakes if that single table is Person.
        if len(tablesOfConcern)==1: return query
                
        # Remove Person table because it is not in any spanning trees.
        bUsePersonTable=False
        if self._personTable in tablesOfConcern:
            tablesOfConcern.remove(self._personTable)
            bUsePersonTable=True
        
        # Find the smallest spanning tree containing tables for SELECT and WHERE.
        tableIndices = [self._ordered.index(table) for table in tablesOfConcern]

        subtree = self.cq.GetSmallestSubtree(tableIndices)
        if subtree is None:
            return None
        _logger.debug("Schema.BuildQuery: query tree length %d" % (subtree.NumberOfNodes(),))
        _logger.debug("Schema.BuildQuery: query tree itself %s" % (subtree,))
    
        # The subtree tells us the order of tables in the join, but it loses
        # information on which table is the foreign key, so we need to search
        # through tables to find which had the foreign key and which the
        # primary key.
        rootJoin = None
        for (nodeIdx,parentIdx) in subtree.BreadthFirst():
            if parentIdx is None:
                rootTable = self._ordered[nodeIdx]
                rootJoin = rootTable
                if bUsePersonTable and rootTable.c.has_key('CreatedBy'):
                    rootJoin = rootJoin.join(self._personTable,
                                             rootTable.c.CreatedBy==self._personTable.c.ID)
            else:
                if nodeIdx in self._foreignTables[parentIdx]:
                    (leftColumn,rightColumn) = self._foreignTables[parentIdx][nodeIdx]
                elif parentIdx in self._foreignTables[nodeIdx]:
                    (rightColumn,leftColumn) = self._foreignTables[nodeIdx][parentIdx]
                rootJoin = rootJoin.join(rightColumn.table, leftColumn==rightColumn)
                
        return rootJoin
#        query.append_from(rootJoin)
#        return query

    def MakeForeignKeys(self,foreignKeys,schema):
        if not foreignKeys:
            foreignKeys={}
            for tableName in schema.tables:
                table=schema.tables[tableName]
                foreignKeys[table]=table.foreign_keys
        return foreignKeys

    def GraphFromSchema(self,metadata,tableForeignKeys,exclude=set()):
        '''The input is a SQLAlchemy MetaData or BoundMetaData object. The output
        is a graph with edges for foreign keys.'''
                
        self._ordered = [metadata.tables[tableName] for tableName in metadata.tables
                         if metadata.tables[tableName] not in exclude]
        relations = []
        # VK, use names rather then compare tables objects
        orderedNames=[]
        for item in self._ordered:
            orderedNames.append(item.fullname)
        eNames=[]
        for i in exclude: eNames.append(i.fullname)
        excludeNames=set(eNames)
        oList=list(orderedNames)
        oList.sort()
#        print "\n\nSchema:"
#        print oList
#        print excludeNames
#        print "owner",self._owner
        try:
            searchName=None
            for tableIdx in xrange(0,len(self._ordered)):
                table = self._ordered[tableIdx]
                foreignKeys = tableForeignKeys[table]
                indexSet = set()
                shortTables = {}
                for fk in foreignKeys:
                    if fk.column.table in exclude: continue
    
                    searchName=fk.column.table
                    if searchName.fullname in excludeNames: continue
#                    fkIdx = self._ordered.index(searchName)
                    fkIdx = orderedNames.index(searchName.fullname)
                    indexSet.add(fkIdx)
                    shortTables[fkIdx]=(fk.parent, fk.column)
    
                relations.append(indexSet)
                self._foreignTables[tableIdx] = shortTables
        except ValueError, ve:
            print ve
            _logger.error("""Schema.GraphFromSchema ValueError %s.
                          Could not find fk.column.table %s in
                          self._ordered %s. Len(ordered)=%d
                          Len(metadata.tables)=%d""" % (str(ve),
                          searchName,self._ordered,len(self._ordered),len(metadata.tables)))
            raise Exception("Could not find the table for a given foreign key constraint.",
                            '''Constraint table %s len(ordered)=%d len(metadata.tables)=%d
                            ordered=%s''' % (searchName, len(self._ordered),
                                             len(metadata.tables),str([x.name for x in self._ordered])))
        return relations
    
    def WriteGraph(self,dot, name="A"):
        connectivity = self.GraphFromSchema(self._schema,self.MakeForeignKeys(None,self._schema))
        order = self._ordered
        dot.Name(name)
        for startNodeIdx in range(0,len(connectivity)):
            startNode = connectivity[startNodeIdx]
            for endNodeIdx in startNode:
                dot.AddEdge(order[startNodeIdx], order[endNodeIdx])
        dot.Finish()

def MakeViewWithoutTable(metadata,ridName,ridReplace):
    '''The input is
    metadata=sqlalchemy.MetaData
    ridTable=String name of table.
    ridReplace=String column name of column to use from ridTable
    returns: new MetaData object containing only the tables we want.
    
    The subroutine looks through all tables in metadata, copying them
    to the new metadata instance. When it finds a table has a foreign
    key to the ridTable, it creates a view of that table where the
    foreign keys are replaced by the ridReplace column from the ridTable.
    
    For example, ridName='Person', ridReplace='FullName'.
    '''
    md=MetaData(metadata.name+"view")
    ridTable=metadata.tables[ridName]
    for tableName in metadata.tables:
        if tableName==ridName: continue
        table=metadata.tables[tableName]
    
        # list the foreign keys we will replace
        replaceAlias={}
        for fk in table.foreign_keys:
            if fk.column.table==ridTable:
#                replaceAlias[fk.parent.name]=(ridTable.alias(fk.parent.name),fk)
                replaceAlias[fk.parent.fullname]=(ridTable.alias(fk.parent.fullname),fk)
    
        if replaceAlias:
            # make columns for new view
            elements=[]
            for col in table.c:
                if replaceAlias.has_key(col.name):
                    (alias,fk)=replaceAlias[col.name]
                    elements.append(alias.c[ridReplace].label(col.name))
                else:
                    elements.append(col.label(col.name))
    
            # make joins for the new view
            aliases=replaceAlias.keys()
            jname=aliases[0]
            (jtable,fk)=replaceAlias[jname]
            joinTable=table.join(jtable,
                                 fk.parent==jtable.c[fk.column.name])
            for jname in aliases[1:]:
                (jtable,fk)=replaceAlias[jname]
                joinTable=joinTable.join(jtable,
                         fk.parent==jtable.c[fk.column.name])
            
            # create the view
            view = select(elements,from_obj=[joinTable],use_labels=True)
            
            md.tables[tableName+"View"]=view.alias(tableName+"View")
        else:
            md.tables[tableName+"View"]=table.alias(tableName+"View")
    
    # Now construct our new foreign keys which refer only to View tables.
    # We can't actually change the foreign key in sqlalchemy, so we keep
    # a separate list.
    class FK:
        def __init__(self,col,par):
            self.column=col
            self.parent=par
            
    foreignKeys={}
    for tableName in md.tables:
        table=md.tables[tableName]
        tableKeys=set()
        for fk in table.foreign_keys:
#            fro=table.c[fk.parent.name]
            fro=table.c[fk.parent.fullname]
#            to=md.tables[fk.column.table.name+"View"].c[fk.column.name]
            to=md.tables[fk.column.table.fullname+"View"].c[fk.column.name]
            tableKeys.add(FK(to,fro))
        foreignKeys[table]=tableKeys
        
    return (md,foreignKeys)



class TestQueryBuilder(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testYamlGraph(self):
        qb=Schema(LoadFromFile("starting_db.yaml").tables)
        dot=DotGraph(file("z.dot","w"))
        qb.WriteGraph(dot)

    def runElements(self,qb,elements):
        query=qb.BuildQuery(elements)
        return str(query)

    def testSingleQuery(self):
        md=LoadFromFile("starting_db.yaml")
        pd=FindTable(md,'ProcessedDataset')
        s=select([pd.c.Name], pd.c.ID==0)
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        print str(query)

    def testYamlQuery(self):
        md=LoadFromFile("starting_db.yaml")
        pd=FindTable(md,'ProcessedDataset')
        dt=FindTable(md,'DataTier')
        pd=FindTable(md,'PrimaryDataset')
        files=FindTable(md,'Files')
        s=select([pd.c.Name,dt.c.Name,pd.c.Description])
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        print query
        s1=select([pd.c.Name,dt.c.Name,files.c.LogicalFileName])
        query = qb.BuildQuery(s1)
        print query
    
    def testOracleQuery(self):
        _logger.debug("testOracleQuery start")
        udb=UnittestDB()
        md=udb.ReadFromOracle('oracle.sql')
        pd=FindTable(md,'ProcessedDataset')
        ae=FindTable(md,'AppExecutable')
        s=select([pd.c.Name,ae.c.ExecutableName])
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        print query
        person=FindTable(md,'Person')
        s=select([pd.c.Name,ae.c.ExecutableName,person.c.Name])
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        from DotGraph import DotGraph
        dot=DotGraph(file("testOracleQuery.dot","w"))
        from WriteSqlAlchemyGraph import WriteQueryAlchemyGraph
        WriteQueryAlchemyGraph(dot,query)
        print query
        _logger.debug("testOracleQuery finish")
        

    def testOracleSimple(self):
        _logger.debug("testOracleSimple start")
        udb=UnittestDB()
        md=udb.ReadFromOracle('oracle.sql')
        pd=FindTable(md,'ProcessedDataset')
        ae=FindTable(md,'AppExecutable')
        person=FindTable(md,'Person')
        s=select([person.c.Name])
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        print query
        s=select([pd.c.Name])
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        print query
        _logger.debug("testOracleSimple finish")
        
    def testOperators(self):
        md=LoadFromFile("starting_db.yaml")
        pd=FindTable(md,'ProcessedDataset')
        dt=FindTable(md,'DataTier')
        pd=FindTable(md,'PrimaryDataset')
        files=FindTable(md,'Files')
        s=select([pd.c.Name,dt.c.Name,pd.c.Description],
            pd.c.ID==0)
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        print query


    def testDotGraph(self):
        qb=Schema(LoadFromFile("starting_db.yaml").tables)
        output = StringIO()
        from DotGraph import DotGraph
        dot=DotGraph(output)
        qb.WriteGraph(dot)
        print output.getvalue()

    def testViews(self):
        md=LoadFromFile("complex_db.yaml")
        personName=FindTableName(md,'Person')
        (view,foreignKeys)=MakeViewWithoutTable(md,personName,'FullName')

        pd=FindTable(view,'ProcessedDatasetView')
        dt=FindTable(view,'DataTierView')
        pd=FindTable(view,'PrimaryDatasetView')
        files=FindTable(view,'FilesView')

        # This shows what happens to the foreign keys when you make a select statement.
        for tableName in view.tables:
            table=view.tables[tableName]
            for fk in table.foreign_keys:
                vals={}
                vals['table']=tableName
                vals['column']=fk.parent.name
                if fk.parent.table.__dict__.has_key('name'):
                    vals['from']=fk.parent.table.name
                else:
                    vals['from']="none"
                if fk.column.table.__dict__.has_key('name'):
                    vals['to']=fk.column.table
                else:
                    vals['to']="none"
                _logger.debug("testViews %(table)s.%(column)s %(from)s->%(to)s" %
                              vals)

    def testViewBuild(self):
        _logger.debug("testViewBuild start")
        md=LoadFromFile("complex_db.yaml")
        personName=FindTableName(md,'Person')
        (view,foreignKeys)=MakeViewWithoutTable(md,personName,'FullName')
        for tableName in view.tables:
            print tableName, list(view.tables[tableName].c)
        files=FindTable(view,'FilesView')
        pd=FindTable(view,'ProcessedDatasetView')
        dt=FindTable(view,'DataTierView')
        pd=FindTable(view,'PrimaryDatasetView')
        qb=Schema(view.tables,foreignKeys)
        s=select([pd.c.Name,dt.c.Name,pd.c.Description],
            pd.c.ID==0)
        query=qb.BuildQuery(s)
        _logger.debug("testViewBuild query: "+str(query))
        _logger.debug("testViewBuild finish")

class TestLive(unittest.TestCase):
    def setUp(self):
        if os.path.exists('unittest.db'):
            os.unlink('unittest.db')
        udb=UnittestDB()
        self.md=udb.LoadWithFakeData('oracle.sql','sqlite:///unittest.db')

    def tearDown(self):
        self.md.engine.dispose()
        os.unlink('unittest.db')

    def displayRows(self,rows):
        _logger.debug(rows[0].keys())
        for row in rows:
            _logger.debug(row)

    def testReadQuery(self):
        md=self.md
        pd=FindTable(md,'ProcessedDataset')
        dt=FindTable(md,'DataTier')
        pd=FindTable(md,'PrimaryDataset')
        files=FindTable(md,'Files')
        
        # First try a regular select query.
        s=select([pd.c.Name,dt.c.Name,pd.c.Description],pd.c.ID==0)
        r=s.execute()
        rows=r.fetchall()
        self.displayRows(rows)
        
        # Then use our software to modify one.
        s=select([pd.c.Name,dt.c.Name,pd.c.Description])
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        _logger.debug(query)
        selectClause=query
        r=selectClause.execute()
        rows=r.fetchall()
        self.displayRows(rows)
        self.assertEqual(len(rows[0]),3)
        
        s=select([pd.c.ID,pd.c.Name,dt.c.ID,pd.c.Description],
            pd.c.ID==0)
        qb=Schema(md.tables)
        query = qb.BuildQuery(s)
        _logger.debug(query)
        selectClause=query
        r=selectClause.execute()
        rows=r.fetchall()
        self.displayRows(rows)
        self.assertEqual(len(rows[0]),4)
        
    def testLiveView(self):
        _logger.debug("testLiveView start")
        md=self.md
        personName=FindTableName(md,'Person')
        (view,foreignKeys)=MakeViewWithoutTable(md,personName,'DistinguishedName')
        for tableName in view.tables:
            print tableName, list(view.tables[tableName].c)
        pd=FindTable(view,'ProcessedDatasetView')
        ae=FindTable(view,'AppExecutableView')
        s=select([pd.c.Name,ae.c.ExecutableName])
        qb=Schema(view.tables,foreignKeys)
        query=qb.BuildQuery(s)
        r=query.execute()
        rows=r.fetchall()
        _logger.debug("testLiveView query: "+str(query))
        _logger.debug("testLiveView result: %s" % (rows,))
        _logger.debug("testLiveView finish")

def suite():
    suite = unittest.TestSuite()
    suite.addTest(unittest.makeSuite(TestQueryBuilder))
    suite.addTest(unittest.makeSuite(TestLive))
    return suite
                         
if __name__ == '__main__':
    import ConfigureLog
    ConfigureLog.ConfigureLog()
    unittest.TextTestRunner(verbosity=2).run(suite())
