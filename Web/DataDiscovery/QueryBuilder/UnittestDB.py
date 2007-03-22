#!/usr/bin/env python
# $Id: $
"""
This creates a database for unit tests.
"""
__author__ = "Andrew J. Dolgert <ajd27@cornell.edu>"
__revision__ = "$Revision$"

from sqlalchemy import *
import re
from yaml import load as yamlload
from logging import getLogger
import unittest
from DotGraph import DotGraph
from WriteSqlAlchemyGraph import WriteSqlAlchemyGraph
from os import unlink, path

_logger = getLogger("ConstructQuery")

def LoadFromFile(filename):
    udb = UnittestDB()
    return udb.LoadFromFile(filename)

class UnittestDB(object):
    def __init__(self):
        self._metadata = None
    
    def CreateFrom(self,dInput):
        metadata = DynamicMetaData(name="onthefly")
        
        for dTableName in dInput.keys():
            dTable = dInput[dTableName]
            sColumns = []
            sVals = {}
            for dColumn in dTable:
                dColumnName = dColumn['member']
                _logger.debug("UnittestDB.CreateFrom: adding %s.%s." % (dTableName, dColumnName))
                if dColumn.has_key('foreignKey'):
                    sColumns.append(Column(dColumnName, Integer,
                                           ForeignKey(dColumn["foreignKey"]+".ID")))
                elif dColumn.has_key("primaryKey"):
                    sColumns.append(Column(dColumnName, Integer, primary_key=True))
                else:
                    sColumns.append(Column(dColumnName, Integer))
                sVals[dColumnName]=0
            sTable = apply(Table, [dTableName, metadata]+sColumns)
        return metadata

    def ColumnCount(self,metadata):
        colCnt = 0
        for table in metadata.table_iterator():
            name = table.name
            for column in table.c:
                colCnt=colCnt+1
        return colCnt

    def LoadFromFile(self,filename):
        schemaFile = file(filename)
        pg = yamlload(schemaFile)
        schemaFile.close()
        md = self.CreateFrom(pg)
        return md

    def FillTables(self,md,rowCnt):
        for t in md.table_iterator(reverse=False):
            name = t.name
            insertClause=t.insert()
            idIdx=0
            runningIdx=0
            colNames=[c.name for c in t.c]
            inserts=[]
            for rowIdx in range(0,rowCnt):
                insertDict={}
                for colName in colNames:
                    if t.c[colName].primary_key:
                        insertDict[colName]=idIdx
                        idIdx=idIdx+1
                    elif t.c[colName].foreign_key:
                        insertDict[colName]=((idIdx+1)%rowCnt)
                    else:
                        insertDict[colName]=runningIdx
                    runningIdx=runningIdx+1
                _logger.debug("insert %s %s" % (name,insertDict))
                inserts.append(insertDict)
                
            apply(insertClause.execute,inserts)

    def LoadWithFakeData(self,filename,dbname):
        udb=UnittestDB()
        if filename.endswith("yaml"):
            md=udb.LoadFromFile(filename)
        elif filename.endswith("sql"):
            md=udb.ReadFromOracle(filename)
        md.connect(dbname)
        md.create_all()
        udb.FillTables(md,10)
        return md

    def ReadFromOracle(self,filename="oracle.sql"):
        metadata=DynamicMetaData(name="fromoracle")
        
        f=file(filename,"r")
        line = f.readline()
        
        matchTable=re.compile("CREATE TABLE (\w+)")
        matchColumn=re.compile("\s+(\w+)\s+(.*),")
        matchConstraint=re.compile("ALTER TABLE\s+(\w+)\s+ADD CONSTRAINT")
        matchForeign=re.compile("key\((\w+)\)\s+references\s+(\w+)\(ID\)")
        
        BEGIN=0
        CREATE=1
        CONSTRAINT=2
        tables={}
        foreignKeys={}
        state=BEGIN
        while line:
            if state is BEGIN:
                tableMatch=matchTable.match(line)
                constraintMatch=matchConstraint.match(line)
                if tableMatch:
                    currentTable=tableMatch.group(1)
                    tables[currentTable]=[]
                    foreignKeys[currentTable]={}
                    state=CREATE
                elif constraintMatch:
                    currentTable=constraintMatch.group(1)
                    state=CONSTRAINT
            elif state is CREATE:
                colMatch=matchColumn.match(line)
                if line.find(";")>0:
                    state=BEGIN
                elif colMatch:
                    colName=colMatch.group(1)
                    if not colName=="primary":
                        tables[currentTable].append(colName)
            elif state is CONSTRAINT:
                foreignMatch=matchForeign.search(line)
                if foreignMatch:
                    leftCol=foreignMatch.group(1)
                    rightTable=foreignMatch.group(2)
                    foreignKeys[currentTable][leftCol]=rightTable
                state=BEGIN
            line = f.readline()
        f.close()
        
        for tableName in tables:
            cols=[]
            for col in tables[tableName]:
                if col == "ID":
                    cols.append(Column(col,Integer,primary_key=True))
                elif foreignKeys[tableName].has_key(col):
                    rightTable=foreignKeys[tableName][col]
                    cols.append(Column(col,Integer,ForeignKey(rightTable+".ID")))
                else:
                    cols.append(Column(col,Integer))
            apply(Table,[tableName,metadata]+cols)
        return metadata

class TestUnittestDB(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass
            
    def testSampleQuery(self):
        db = UnittestDB()
        class zCol(object):
            def __init__(self,prim,ex):
                self.primaryKey = prim
                self.foreignKey = ex
        input = { "t0" : [{ "member" : "ID", "primaryKey" : None},
                          {"member" : "zCol"}
                         ],
                  "t1" : [{ "member" : "ID", "primaryKey" : None},
                          { "member" : "who", "foreignKey" : "t0"}
                         ]
                }
        md = db.CreateFrom(input)
        colCnt = db.ColumnCount(md)
        self.assertEqual(colCnt,4)

    def testFromYaml(self):
        md = LoadFromFile('starting_db.yaml')
        udb = UnittestDB()
        colCnt=udb.ColumnCount(md)
        self.assertEqual(colCnt,36)
    
    def testFill(self):
        if path.exists('unittest2.db'):
            unlink('unittest2.db')
        udb=UnittestDB()
        md=udb.LoadFromFile('starting_db.yaml')
        md.connect('sqlite:///unittest2.db')
        md.create_all()
        udb.FillTables(md,10)
        md.engine.dispose()

    def testOracleWrite(self):
        udb=UnittestDB()
        md=udb.ReadFromOracle('oracle.sql')
        dot=DotGraph(file("oracle.dot","w"))
        WriteSqlAlchemyGraph(dot,md,set(['Person']))
        
    def testFromOracle(self):
        if path.exists('unittest2.db'):
            unlink('unittest2.db')
        udb=UnittestDB()
        md=udb.ReadFromOracle('oracle.sql')
        md.connect('sqlite:///unittest2.db')
        md.create_all()
        udb.FillTables(md,10)
        md.engine.dispose()
        
def suite():
    suite = unittest.TestSuite()
    suite.addTest(unittest.makeSuite(TestUnittestDB))
    return suite
                         
if __name__ == '__main__':
    import ConfigureLog
    ConfigureLog.ConfigureLog()
    unittest.TextTestRunner(verbosity=2).run(suite())
