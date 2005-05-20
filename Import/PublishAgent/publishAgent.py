"""
publishAgent.py 

This file contains APIP methods that use the ViewObjectLayer to 
populate the DBS.  A utility class to read and parse SQL is included.
Module level representations of the table schemas are constructed and
stored at module level when the module is imported, but this should 
probably go into a class at some point.  A DBSpublisher class is 
included that contains views constructed from the individual schemas.
Example inserts are at the end of the file in the __main__ section.

Gregory Edwin Graham,   16-May-2005
"""
__version__ = "$Id: publishAgent.py,v 1.4 2005/05/20 04:47:54 ggraham Exp $"
__revision__ = "$Revision: 1.4 $"

import os, sys, time, pdb
from ViewObjectLayer import *

class ReadTable: 
    """
    This is a crap SQL parser to be replaced by Anzar and Vijay
    """
    def __init__(self) :
        """
        Constructor
        This should fill the arrays below with the correct values
        """
        self.schema = []    # list of strings
        self.types = {}     # list of 'int' or 'string', could be better
        self.pk = []        # list of strings
        self.name = None    # string table name
        self.uniqueKeys = []  # list of lists of strings
        self.foreignKeys = {} # dictionary of here.attribute references there.attribute
        self.notNulls = []    # list of strings
    
    def getTable(self, i) : 
        """ 
        This function reads a druid generated table definition
        and fills the arrays in the constructor
        """
        if sqlContent[i].find("create table") != 0 : 
            raise "Could not find table"
        self.name = sqlContent[i].split()[2]
        if self.name not in allSchemas : 
            allSchemas[self.name] = None
        j=i+2
        stSchema = 1
        self.schema = []
        while sqlContent[j][0:4] != "  );" : 
            linecon = sqlContent[j].split()
            if stSchema == 1 : 
                if len(linecon) > 0 : 
                  if linecon[0].strip() == '' : 
                      pass
                  elif linecon[0].strip() == ');' : 
                      stSchema = 0
                  elif linecon[0][0:6] in ['check('] : 
                      pass
                  elif sqlContent[j].strip()[0:9] in ['primary k', 'foreign k'] : 
                      if linecon[0] == 'primary' : 
                          self.pk = linecon[1].split('(')[1]
                          self.pk = self.pk.split(')')[0]
                          self.pk = self.pk.split(',')
                      elif linecon[0][0:7] == 'foreign' : 
                          tmp = linecon[1]
                          tmp = tmp.split(')')[0]
                          tmp = tmp.split('(')[1]
                          tbln = linecon[3].split('(')[0]
                          attr = linecon[3].split('(')[1].split(')')[0]
                          self.foreignKeys[tmp] = tbln + '.' + attr
                  elif linecon[0][0:6] == 'unique' : 
                      tmp = linecon[0][6:]
                      tmp = tmp.split(')')[0]
                      tmp = tmp.split('(')[1]
                      tmp = tmp.split(',')
                      self.uniqueKeys.append(tmp)
                  else : 
                      name = linecon[0] 
                      typee = linecon[1]
                      if typee[0:3] in ['var', 'dat', 'cha'] : 
                          typee = 'string'
                      elif typee[0:3] in ['int', 'num'] : 
                          typee = 'int'
                      self.schema.append(name)
                      self.types[name] = typee
                      if sqlContent[j].find("not null") >= 0 : 
                          self.notNulls.append(name)
                      if sqlContent[j].find("unique") >= 0 : 
                          self.uniqueKeys.append([name])
            j = j + 1

    def makeObject(self) : 
        """
        Using the filled arrays in the constructor, this calls
        SingleSchema from the ViewObjectLayer and returns the result.
        It also looks for attributes named "tableid" in tables named 
        "table" and if it finds one, makes it a sequencer.
        """
        autoSeqID = self.name + 'id'
        if autoSeqID in self.pk : 
            seq = {autoSeqID : 0}
        else : 
            seq = {}
        a = SingleSchema(self.name, self.schema, self.types, \
                self.pk, self.uniqueKeys, self.foreignKeys, self.notNulls, {}, seq)
        return a

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

class DBSpublisher : 
    """
    This class contains the entire DBS schema in 
    terms of approporiate ViewObjects.
    """
    def __init__(self) : 
        """
        Constructor
        This builds all of the required ViewObjects.
        """

        # We will ignore createdby and lastmodifiedby attributes that will 
        # be filled in automatically in the future by some other mechanism
        self.fkExclusionAttributes = ['lastmodifiedby', 'createdby']
        self.cParms = {}

        # View that descibes Application Configurations
        self.Applications  = MultiSchema(self.fkExclusionAttributes)
        self.Applications.addSchema(allSchemas['application'])
        self.Applications.addSchema(allSchemas['applicationfamily']) 
        self.Applications.addSchema(allSchemas['collectiontype']) 
        self.Applications.addSchema(allSchemas['parameterset']) 
        self.Applications.addSchema(allSchemas['parameterbinding'], 1) 
        self.Applications.addSchema(allSchemas['applicationconfiguration']) 
        self.Applications.addCondition("application.applicationfamily = " + \
                                       "applicationfamily.applicationfamilyid")
        self.Applications.addCondition("applicationconfiguration.applicationid = " + \
                                       "application.applicationid")
        self.Applications.addCondition("applicationconfiguration.parametersetid = " + \
                                       "parameterset.parametersetid")
        self.Applications.addCondition("parameterbinding.parametersetid = " + \
                                       "parameterset.parametersetid")
        self.ApplicationsTable = Table(self.Applications, **self.cParms)
        self.ApplicationsTable.initializeSequencers()

        # View that describes Administrative roles
        self.Administrative = MultiSchema(self.fkExclusionAttributes)
        self.Administrative.addSchema(allSchemas['person'])
        self.Administrative.addSchema(allSchemas['role'])
        self.Administrative.addSchema(allSchemas['assignedrole'])
        self.Administrative.addCondition('assignedrole.roleid = role.roleid')
        self.Administrative.addCondition('assignedrole.personid = person.personid')
        self.AdministrativeTable = Table(self.Administrative, **self.cParms)
        self.AdministrativeTable.initializeSequencers()

        # View that describes Person
        self.Person = MultiSchema(self.fkExclusionAttributes)
        self.Person.addSchema(allSchemas['person'])
        self.PersonTable = Table(self.Person, **self.cParms)
        self.PersonTable.initializeSequencers()

        # View that describes Role
        self.Role = MultiSchema(self.fkExclusionAttributes)
        self.Role.addSchema(allSchemas['role'])
        self.RoleTable = Table(self.Role, **self.cParms)
        self.RoleTable.initializeSequencers()

        # View that describes PhysicsGroup
        self.PhysicsGroup = MultiSchema(self.fkExclusionAttributes)
        self.PhysicsGroup.addSchema(allSchemas['physicsgroup'])
        self.PhysicsGroup.addSchema(allSchemas['person'])
        self.PhysicsGroup.addCondition('physicsgroup.physicsgroupconvener = person.personid')
        self.PhysicsGroupTable = Table(self.PhysicsGroup, **self.cParms)
        self.PhysicsGroupTable.initializeSequencers()

        # View that describes EventCollections
        self.EventCollections = MultiSchema(self.fkExclusionAttributes)
        self.EventCollections.addSchema(allSchemas['filetype'])
        self.EventCollections.addSchema(allSchemas['filestatus'])
        self.EventCollections.addSchema(allSchemas['file'])
        self.EventCollections.addSchema(allSchemas['evcollfile'])
        self.EventCollections.addSchema(allSchemas['eventcollection'])
        self.EventCollections.addSchema(allSchemas['analysiscollectiondata'])
        self.EventCollections.addSchema(allSchemas['validationstatus'])
        self.EventCollections.addSchema(allSchemas['analysiscollectionstatus'])
        self.EventCollections.addSchema(allSchemas['parameterset'])
        self.EventCollections.addSchema(allSchemas['parameterbinding'],1)
        self.EventCollections.addCondition('evcollfile.fileid = file.fileid')
        self.EventCollections.addCondition('parameterset.parametersetid = ' + \
                                           'parameterbinding.parametersetid')
        self.EventCollections.addCondition('analysiscollectiondata.otherqueryablemetadata' + \
                                               ' = parameterset.parametersetid')
        self.EventCollections.addCondition('file.filetype = filetype.filetypeid')
        self.EventCollections.addCondition('file.filestatus = filestatus.filestatusid')
        self.EventCollections.addCondition('evcollfile.evcollid = ' + \
                                           'eventcollection.eventcollectionid')
        self.EventCollections.addCondition('analysiscollectiondata.eventcollectionid = ' + \
                'eventcollection.eventcollectionid')
        self.EventCollections.addCondition('analysiscollectiondata.validationstatus = ' + \
                'validationstatus.validationstatusid')
        self.EventCollections.addCondition('analysiscollectiondata.analysiscollectionstatus' +\
                ' = analysiscollectionstatus.analysiscollectionstatusid')
        self.EventCollectionsTable = Table(self.EventCollections, **self.cParms)
        self.EventCollectionsTable.initializeSequencers()

        # View that describes EventCollections with complex 
        # parentage
        self.EventCollections2 = MultiSchema(self.fkExclusionAttributes)
        self.EventCollections2.addSchema(allSchemas['filetype'])
        self.EventCollections2.addSchema(allSchemas['filestatus'])
        self.EventCollections2.addSchema(allSchemas['file'])
        self.EventCollections2.addSchema(allSchemas['evcollfile'])
        self.EventCollections2.addSchema(allSchemas['eventcollection'])
        self.EventCollections2.addSchema(allSchemas['compositeeventcollection'],1)
        self.EventCollections2.addSchema(allSchemas['analysiscollectiondata'])
        self.EventCollections2.addSchema(allSchemas['validationstatus'])
        self.EventCollections2.addSchema(allSchemas['analysiscollectionstatus'])
        self.EventCollections2.addSchema(allSchemas['parameterset'])
        self.EventCollections2.addSchema(allSchemas['parameterbinding'],1)
        self.EventCollections2.addCondition('evcollfile.fileid = file.fileid')
        self.EventCollections2.addCondition('parameterset.parametersetid = ' + \
                                           'parameterbinding.parametersetid')
        self.EventCollections2.addCondition('analysiscollectiondata.otherqueryablemetadata' + \
                                               ' = parameterset.parametersetid')
        self.EventCollections2.addCondition('file.filetype = filetype.filetypeid')
        self.EventCollections2.addCondition('file.filestatus = filestatus.filestatusid')
        self.EventCollections2.addCondition('compositeeventcollection.childecid = ' + \
                                           'eventcollection.eventcollectionid')
        self.EventCollections2.addCondition('evcollfile.evcollid = ' + \
                                           'eventcollection.eventcollectionid')
        self.EventCollections2.addCondition('analysiscollectiondata.eventcollectionid = ' + \
                'eventcollection.eventcollectionid')
        self.EventCollections2.addCondition('analysiscollectiondata.validationstatus = ' + \
                'validationstatus.validationstatusid')
        self.EventCollections2.addCondition('analysiscollectiondata.analysiscollectionstatus' +\
                ' = analysiscollectionstatus.analysiscollectionstatusid')
        self.EventCollectionsTable2 = Table(self.EventCollections2, **self.cParms)
        self.EventCollectionsTable2.initializeSequencers()

        # View that describes primary/processed dataset parameters
        self.PrimaryDataset = MultiSchema(self.fkExclusionAttributes)
        self.PrimaryDataset.addSchema(allSchemas['triggerpathdescription'])
        self.PrimaryDataset.addSchema(allSchemas['mcdescription'])
        self.PrimaryDataset.addSchema(allSchemas['primarydatasetdescription'])
        self.PrimaryDataset.addSchema(allSchemas['primarydataset'])
        self.PrimaryDataset.addSchema(allSchemas['stream'])
        self.PrimaryDataset.addSchema(allSchemas['physicsgroup'])
        self.PrimaryDataset.addCondition('mcdescription.mcdescriptionid = ' + \
               'primarydatasetdescription.mcchanneldescriptionid')
        self.PrimaryDataset.addCondition('triggerpathdescription.triggerpathdescriptionid' + \
               ' = primarydatasetdescription.triggerdescriptionid')
        self.PrimaryDataset.addCondition('primarydataset.primarydatasetdescriptionid = ' + \
               'primarydatasetdescription.primarydatasetdescriptionid')
        self.PrimaryDataset.addCondition('primarydataset.streamid = stream.streamid')
        self.PrimaryDataset.addCondition('primarydataset.physicsgroupid = ' +\
               'physicsgroup.physicsgroupid')
        self.PrimaryDatasetTable = Table(self.PrimaryDataset, **self.cParms)
        self.PrimaryDatasetTable.initializeSequencers()

        # View that describes processing path - self referencing
        self.ProcessingPath = MultiSchema(self.fkExclusionAttributes)
        self.ProcessingPath.addSchema(allSchemas['processingpath'])
        self.ProcessingPath.addSchema(allSchemas['processeddataset'])
        self.ProcessingPath.addSchema(allSchemas['primarydataset'])
        self.ProcessingPath.addCondition('primarydataset.primarydatasetid = ' + \
              'processeddataset.primarydatasetid')
        self.ProcessingPath.addCondition('processeddataset.processingpathid = ' + \
              'processingpath.processingpathid')
        self.ProcessingPathTable = Table(self.ProcessingPath, **self.cParms)
        self.ProcessingPathTable.initializeSequencers()

        # View that describes AnalysisDatasets
        self.AnalysisDataset = MultiSchema(self.fkExclusionAttributes)
        self.AnalysisDataset.addSchema(allSchemas['analysisdataset'])
        self.AnalysisDataset.addSchema(allSchemas['evcollandata'])
        self.AnalysisDataset.addSchema(allSchemas['analysiscollectiondata'])
        self.AnalysisDataset.addSchema(allSchemas['analysisdatasetsubtype'])
        self.AnalysisDataset.addSchema(allSchemas['block'])
        self.AnalysisDataset.addSchema(allSchemas['snapshot'])
        self.AnalysisDataset.addSchema(allSchemas['productionassignment'])
        self.AnalysisDataset.addSchema(allSchemas['usercollection'])
        self.AnalysisDatasetTable = Table(self.AnalysisDataset, **self.cParms)
        self.AnalysisDatasetTable.initializeSequencers()
        self._Connection = self.AnalysisDatasetTable.getConnection()
        print self._Connection.connectionName()

    def resetTransaction(self) : 
        """ 
        This clears the transaction with implicit rollback.
        Implements TableInterface::resetTransaction
        """
        self._Connection.rollback()

    def saveTransaction(self) : 
        """ 
        This clears the transaction with implicit commit.
        Implements TableInterface::saveTransaction
        """
        self._Connection.commit()

    def publishApplicationConfiguration(self, appFamName, exeName, appVersion, paramSetName, \
                  paramSetVersion, paramSetComments, parameterBindings, inputType = None, \
                  outputType = None) : 
        """
        API method for publishing an Application Configuration.
        The arguments are in order: 

          appFamName
            Application Family Name (eg- CMKIN, OSCAR, ORCA)

          exeName
            Executable Name  

          appVersion
            Application Version

          paramSetName
            Parameter Set Name  (A unique name to be given by 
                  client for future reference.  This will be
                  automatically generated in teh future.)
            
          paramSetVersion
            Parameter Set Version  (To be given by client for 
                  future reference - this will be automatically 
                  generated in the future..)

          paramSetVersion
            Parameter Set Comments 

          parameterBindings
            Parameter Bindings  (dictionary of key/value pairs)

          inputType (optional)
            Input Type (eg- NoInput, CMKIN, OSCAR, etc.)

          outputType (optional)
            OutputType (eg- CMKIN, OSCAR, ORCA, NoOutput, etc.) 
        """
        rowData = Row(self.Applications)
        if inputType != None : 
            rowData['collectiontype.collectiontype(application.inputcollectiontype)'] = inputType
        if outputType != None : 
            rowData['collectiontype.collectiontype(application.outputcollectiontype)'] = outputType
        rowData['applicationfamily.applicationfamilyname'] = appFamName
        rowData['application.executablename'] = exeName
        rowData['application.applicationversion'] = appVersion
        rowData['parameterset.parametersetname'] = paramSetName
        rowData['parameterset.parametersetversion'] = paramSetVersion
        rowData['parameterset.parametersetannotation'] = paramSetComments
        rowData['parameterset.composite'] = 'n'
        firstInsert = 1
        for key, val in parameterBindings.items() : 
            if firstInsert == 0 : 
                rowData.newData('parameterbinding')
            firstInsert = 0
            rowData['parameterbinding.parametername'] = key
            rowData['parameterbinding.parametervalue'] = val
        self.ApplicationsTable.smartInsert(rowData, rowData.keys())
        self.saveTransaction()
        return rowData['applicationconfiguration.applicationconfigurationid']

    def adminAssignRole(self, name, role) : 
        """
        API Method to assign a role.
        The arguments are in order : 

          name
            UNIX style name of the person

          role
            name of the role
        """
        rowData = Row(self.Administrative)
        rowData['person.name'] = name
        rowData['role.rolename'] = role
        self.AdministrativeTable.smartInsert(rowData, rowData.keys())
        self.saveTransaction()
        return rowData['assignedrole.assignedroleid']

    def adminPhysicsGroup(self, groupName, personName) : 
        """
        API method to publish a physics group
        The arguments are in order:

          groupName 
            the physics group name

          personName
            the name of the convener
        """
        rowData = Row(self.PhysicsGroup)
        rowData['physicsgroup.physicsgroupname'] = groupName
        rowData['person.name'] = personName
        self.PhysicsGroupTable.smartInsert(rowData, rowData.keys())
        self.saveTransaction()
        return rowData['physicsgroup.physicsgroupid']         

    def adminPerson(self, name, distinguishedname, contactinfo) : 
        """
        API method to publish a person
        The arguments are in order:  

          name
            the UNIX style name of the person

          distinguishedname
            the grid credential distinguished name of the person

          contactinfo
            contact information
        """
        rowData = Row(self.Person) 
        rowData['person.name'] = name
        rowData['person.distinguishedname'] = distinguishedname
        rowData['person.contactinfo'] = contactinfo
        self.PersonTable.smartInsert(rowData, rowData.keys())
        return rowData['person.personid']

    def adminRole(self, rolename, description) : 
        """
        API method to publish a role
        The arguments are in order: 

          rolename
            the name of the role

          roledescription
            description of the role
        """
        rowData = Row(self.Role)
        rowData['role.rolename'] = rolename
        rowData['role.roledescription'] = description
        self.RoleTable.smartInsert(rowData, rowData.keys())
        return rowData['role.roleid']

    def publishPrimaryDataset(self, description, datasetName, \
         datasetComments, streamName, physicsGroupName, streamComments = None) : 
        """
        API method to publish a primary dataset
        The arguments are in order: 

          description
            A dictionary description of the dataset
                The dictionary can have MCChannel, MCProduction, and MCDecayChain
                   OR
                The dictionary can have TriggerPath
   
          datasetName
            A dataset name (eg- the COBRA dataset name) 

          datasetComments
            Comments

          streamName
            A Stream name

          physicsGroupName
            A physics group name
        """
        rowData = Row(self.PrimaryDataset) 
        rowData.setSkipOnEmpty('mcdescription')
        rowData.setSkipOnEmpty('triggerpathdescription') 
        if description.has_key('MCChannel') : 
            rowData['mcdescription.mcchanneldescription'] = description['MCChannel']
            rowData['mcdescription.mcproduction'] = description['MCProduction']
            rowData['mcdescription.mcdecaychain'] = description['MCDecayChain']
            rowData['primarydatasetdescription.mcdataset'] = 'y'
            rowData['primarydatasetdescription.triggerdescriptionid'] = None
        elif description.has_key('TriggerPath') : 
            rowData['triggerpathdescription.triggerpathdescription'] = description['TriggerPath']
            rowData['primarydatasetdescription.mcdataset'] = 'n'
            rowData['primarydatasetdescription.mcchanneldescriptionid'] = None
        rowData['stream.streamname'] = streamName
        if streamComments != None : 
            rowData['stream.streamannotation'] = streamComments
        rowData['primarydataset.primarydatasetname'] = datasetName
        rowData['primarydataset.cobradatasetname'] = datasetName
        rowData['primarydataset.primarydatasetannotation'] = datasetComments
        rowData['primarydataset.openforwriting'] = 'y'
        rowData['physicsgroup.physicsgroupname'] = physicsGroupName
        self.PrimaryDatasetTable.smartInsert(rowData, rowData.keys())
        self.saveTransaction()
        return rowData['primarydataset.primarydatasetid']

    def publishProcessedDataset(self, datasetName, ownerName, parentProcPathID, applicationConfigID, agPath = None) : 
        """
        API method to publish a processed dataset
        The arguments are in order

          datasetName
            dataset name of the primary dataset
   
          ownerName
            owner name (eg- the COBRA owner name)
 
          parantProcPathID
            parent processing path id (can be null) 

          applicationConfiguration
            application configuration id (returned by publishApplicationConfiguration)
        """
        rowData = Row(self.ProcessingPath)
        rowData['primarydataset.primarydatasetname'] = datasetName
        rowData['processingpath.parentprocessingpathid'] = parentProcPathID
        rowData['processingpath.processingrecordid'] = applicationConfigID
        if agPath != None : 
            rowData['processingpath.aggregatedpath'] = agPath
        rowData['processeddataset.cobraownername'] = ownerName
        rowData['processeddataset.openforwriting'] = 'y'
        self.ProcessingPathTable.smartInsert(rowData, rowData.keys()) 
        self.saveTransaction() 
        return rowData['processeddataset.processeddatasetid']

    def publishEventCollection(self, evcollStatus, validationStatus, nEvents, \
          luminosity, collectionName, procDatasetID, evCollIndex, primaryEC, \
          paramSetName, parameterBindings, filelist) : 
        """
        API to publish an EventCollection
        The arguments are in order: 

          evcollStatus
            event collection status (eg- "OK")  

          validationStatus
            validation status (eg- "OK") 

          nEvents
            number of events

          luminosity
            estimated luminosity

          collectionName
            collection name (eg- COBRA collection nam, can be None) 

          procDatasetID
            processed dataset ID  (returned by publishProcessedDataset) 

          evCollIndex
            event collection index  (eg- Run number)

          primaryEC
            boolean: is this a "primary" evCollection?  'y' for CMKIN, 'n' 
                       otherwise.

          paramSetName
            parameter set name : this is a unique name for the parameter set
                       associated with the Event Collection.  (This will be
                       automatically generated in the future.)

          parameterBindings
            parameters : this is a dictionary containing the parameters 
                       describing this Event Collection. 

          filelist
            file list: list of tuples of 
                 (logical file name, checksum, size, status, type)            
     
        NOTES: 
            This API is useful when event collections can be lined up  
            on the event collection index exactly across different 
            processing levels.  Parentage information is assumed to 
            be "collection 123 @ processing X"  is a parent of 
            "collection 123 @ processing Y" if Y follows X in 
            ProcessingPath.
        """
        rowData = Row(self.EventCollections) 
        rowData['analysiscollectionstatus.analysiscollectionstatus'] = evcollStatus
        rowData['validationstatus.validationstatus'] = validationStatus
        rowData['analysiscollectiondata.numberofevents'] = nEvents
        rowData['analysiscollectiondata.estimatedluminosity'] = luminosity
        rowData['analysiscollectiondata.cobracollectionname'] = collectionName
        rowData['eventcollection.processeddatasetid'] = procDatasetID
        rowData['eventcollection.primaryeventcollection'] = primaryEC
        rowData['eventcollection.compositeeventcollection'] = 'n'
        rowData['eventcollection.eventcollectionindex'] = evCollIndex
        rowData['parameterset.parametersetname'] = paramSetName
        rowData['parameterset.parametersetversion'] = '1.0'
        rowData['parameterset.parametersetannotation'] = \
                   'Parameter set describing event collection ' + paramSetName
        rowData['parameterset.composite'] = 'n'
        firstInsert = 1
        for key, val in parameterBindings.items() : 
            if firstInsert == 0 : 
                rowData.newData('parameterbinding')
            firstInsert = 0
            rowData['parameterbinding.parametername'] = key
            rowData['parameterbinding.parametervalue'] = val
        for i in range(len(filelist)) : 
            rowData['file.logicalfilename'] = filelist[i][0]
            rowData['file.checksum'] = filelist[i][1]
            rowData['file.size'] = filelist[i][2]
            rowData['filestatus.filestatus'] = filelist[i][3]
            rowData['filetype.filetype'] = filelist[i][4]
#           Bug to be fixed later.  The files inserts should be batched.  16-May-2005!!!
            if 'file.fileid' in rowData.keys() : 
                del rowData['file.fileid']
                del rowData['evcollfile.evcollfileid']
                del rowData['evcollfile.fileid']
            self.EventCollectionsTable.smartInsert(rowData, rowData.keys()) 
        self.saveTransaction() 
        return rowData['eventcollection.eventcollectionid']

    def publishEventCollection2(self, evcollStatus, validationStatus, nEvents, \
          luminosity, collectionName, procDatasetID, evCollIndex, parentECList, \
          paramSetName, parameterBindings, filelist) : 
        """
        Another API to publish an EventCollection
        The arguments are in order: 

          evcollStatus
            event collection status (eg- "OK")  

          validationStatus
            validation status (eg- "OK") 

          nEvents
            number of events

          luminosity
            estimated luminosity

          collectionName
            collection name (eg- COBRA collection nam, can be None) 

          procDatasetID
            processed dataset ID  (returned by publishProcessedDataset) 

          evCollIndex
            event collection index  (eg- Run number)

          parentECList
            list of event collection IDs of the parents of this event collection.

          paramSetName
            parameter set name : this is a unique name for the parameter set
                       associated with the Event Collection.  (This will be
                       automatically generated in the future.)

          parameterBindings
            parameters : this is a dictionary containing the parameters 
                       describing this Event Collection. 

          filelist
            file list: list of tuples of 
                 (logical file name, checksum, size, status, type)            
     
        NOTES: 
            This API is useful when event collections must be loaded 
            with multiple parents.  The "CompositeEventCollection" 
            table is used to track complex parentage relationships 
            directly.  This API method is not yet tested.
        """
        rowData = Row(self.EventCollections2) 
        rowData['analysiscollectionstatus.analysiscollectionstatus'] = evcollStatus
        rowData['validationstatus.validationstatus'] = validationStatus
        rowData['analysiscollectiondata.numberofevents'] = nEvents
        rowData['analysiscollectiondata.estimatedluminosity'] = luminosity
        rowData['analysiscollectiondata.cobracollectionname'] = collectionName
        rowData['eventcollection.processeddatasetid'] = procDatasetID
        if len(parentECList) == 0 : 
            rowData['eventcollection.primaryeventcollection'] = 'y'
        else : 
            rowData['eventcollection.primaryeventcollection'] = 'n'
        rowData['eventcollection.compositeeventcollection'] = 'y'
        rowData['eventcollection.eventcollectionindex'] = evCollIndex

        firstInsert = 1
        for elem in parentECList : 
            if firstInsert == 0 : 
                rowData.newData('compositeeventcollection')
            firstInsert = 0
            rowData['compositeeventcollection.compositeecid'] = elem

        rowData['parameterset.parametersetname'] = paramSetName
        rowData['parameterset.parametersetversion'] = '1.0'
        rowData['parameterset.parametersetannotation'] = \
                   'Parameter set describing event collection ' + paramSetName
        rowData['parameterset.composite'] = 'n'
        firstInsert = 1
        for key, val in parameterBindings.items() : 
            if firstInsert == 0 : 
                rowData.newData('parameterbinding')
            firstInsert = 0
            rowData['parameterbinding.parametername'] = key
            rowData['parameterbinding.parametervalue'] = val
        for i in range(len(filelist)) : 
            rowData['file.logicalfilename'] = filelist[i][0]
            rowData['file.checksum'] = filelist[i][1]
            rowData['file.size'] = filelist[i][2]
            rowData['filestatus.filestatus'] = filelist[i][3]
            rowData['filetype.filetype'] = filelist[i][4]
#           Bug to be fixed later.  The files inserts should be batched.  16-May-2005!!!
            if 'file.fileid' in rowData.keys() : 
                del rowData['file.fileid']
                del rowData['evcollfile.evcollfileid']
                del rowData['evcollfile.fileid']
            self.EventCollectionsTable2.smartInsert(rowData, rowData.keys()) 
        self.saveTransaction() 
        return rowData['eventcollection.eventcollectionid']

if __name__ == "__main__" : 

  a = DBSpublisher() 
  b = a.publishApplicationConfiguration('OSCAR', 'oscar_new.exe', '3_6-2', 'test44', \
         'v1', 'test parameters', {'a':'1', 'b':'2', 'c':'3', 'd':'4'}, 'CMKIN', 'OSCAR')
  print b
  c =  a.adminPerson('ggraham', 'Gregory Graham 123456', '1055 W. Addison')
  print c
  d = a.adminRole('admin','administrative')
  print d
  e = a.adminAssignRole('ggraham','admin')
  print e
  f = a.adminPhysicsGroup('egammaGroup', 'ggraham')
  print f
  g = a.publishPrimaryDataset({'MCChannel':'blahblah', 'MCProduction':'blahblah', \
                'MCDecayChain':'blahblah'}, 'testDataset3', 'A test dataset', \
                'testStream', 'egammaGroup', 'This is a test stream.')
  print g
  h = a.publishProcessedDataset('testDataset3', 'testOwner3', None, b, '/')
  print h
  i = a.publishEventCollection('OK', 'Valid', 100, '10e-15', 'testCollection3', h, 1, 'y', \
           'testDataset3-testOwner3-testCollection3', {'ranseed':'123456', 'runnum':'1'}, \
           [('file3', '364239', '125251', 'OK', 'Data'), \
            ('file4', '01273421', '120970112', 'OK', 'Data')] )
  print i

