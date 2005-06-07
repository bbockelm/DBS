import os, sys, time, pdb
from ViewObjectLayer import *
from publishAgent import ReadTable

# For now read in the schema in lowercase
sqlContent = map(lambda x : x.lower(), \
        open("../../dbs-schema/sql/DBS-DB.sql",'r').readlines())
# This is the module level array holding all individual schemas
allSchemas = {}
# Fill the schemas array.
for i in range(len(sqlContent)) : 
    line = sqlContent[i]
    if line[0:5] == 'creat' : 
        a = ReadTable()
        a.getTable(i)
        allSchemas[a.name] =  a.makeObject()

class DBSquery : 
    """
    This class contains the entire DBS schema in 
    terms of approporiate ViewObjects for querying.
    """
    def __init__(self) : 
        """
        Constructor
        This builds all of the required ViewObjects.
        """

        # We will ignore createdby and lastmodifiedby attributes that will 
        # be filled in automatically in the future by some other mechanism
        self.fkExclusionAttributes = ['lastmodifiedby', 'createdby', 'inputcollectiontype']
        self.cParms = {}

        # View that descibes test query for CRAB
        self.EventCollections  = MultiSchema(fkExcl = self.fkExclusionAttributes)
        self.EventCollections.addSchema(allSchemas['application'])
        self.EventCollections.addSchema(allSchemas['applicationconfiguration'])
        self.EventCollections.addSchema(allSchemas['collectiontype']) 
        self.EventCollections.addSchema(allSchemas['processingpath']) 
        self.EventCollections.addSchema(allSchemas['processeddataset']) 
        self.EventCollections.addSchema(allSchemas['primarydataset']) 
        self.EventCollections.addSchema(allSchemas['eventcollection']) 
        self.EventCollections.addSchema(allSchemas['analysiscollectiondata']) 
        self.EventCollections.addCondition("applicationconfiguration.applicationid = " + \
                                       "application.applicationid")
        self.EventCollections.addCondition("application.outputcollectiontype = " + \
                                       "collectiontype.collectiontypeid")
        self.EventCollections.addCondition("applicationconfiguration.applicationconfigurationid = " + \
                                       "processingpath.processingrecordid")
        self.EventCollections.addCondition("processingpath.processingpathid = " + \
                                       "processeddataset.processingpathid")
        self.EventCollections.addCondition("primarydataset.primarydatasetid = " + \
                                       "processeddataset.primarydatasetid")
        self.EventCollections.addCondition("eventcollection.processeddatasetid = " + \
                                       "processeddataset.processeddatasetid")
        self.EventCollections.addCondition("eventcollection.eventcollectionid = " + \
                                       "analysiscollectiondata.eventcollectionid")
        self.EventCollectionsTable = Table(self.EventCollections, **self.cParms)
        self.EventCollectionsTable.initializeSequencers()


if __name__ == "__main__" : 

     DBS = DBSquery()
     dataset = sys.argv[1]
     ctype = sys.argv[2]
     nSumEvent = -1
     if len(sys.argv) > 3 :   
         nSumEvent = int(sys.argv[3])

     results = DBS.EventCollectionsTable.smartSelect(["collectiontype.collectiontype = '"+ctype+"'", \
                          "primarydataset.cobradatasetname = '"+dataset+"'"])
     sum = 0
     for item in results : 
         nEvent = int(item['numberofevents'])
         if nSumEvent == -1 : 
             print item['cobradatasetname'], item['cobraownername'], item['eventcollectionindex'], nEvent
         else :  
             print item['cobradatasetname'], item['cobraownername'], item['eventcollectionindex'], nEvent
             sum = sum + nEvent
             if sum > nSumEvent : 
                 break

                  

