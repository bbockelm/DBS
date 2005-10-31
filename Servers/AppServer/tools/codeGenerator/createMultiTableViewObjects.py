#!/usr/bin/env python
import os
import commands
import string
from ViewObjectLayer import SingleSchema
from multiRowRepresentation import multiRowRepresentation

import pdb


class createMultiTableViewObjects :
    """
    This class contains the entire DBS schema in 
    terms of approporiate ViewObjects.
    """
    multiTableViewObjects = []
    
    def __init__(self) : 
        """
        Constructor
        This builds all of the required ViewObjects.
        """
        # We will ignore createdby and lastmodifiedby attributes that will 
        # be filled in automatically in the future by some other mechanism
        self.fkExclusionAttributes = ['modified_at', 'modified_by', 'created_at', 'created_by']
        self.cParms = {}
        self.allSchemas={}

    def initializeAllSchema(self, allRows):
        """Converts all Rows into SingleSchema format
           and then generates a dictionary representation for 
           self.allSchemas
        """ 
       
        for aRow in allRows: 
           name = string.split(aRow.tablename, 'Row')[0]
           fk = {}

           for aDict in aRow.references :
               underscored = aDict.values()[0]
               dotted = underscored.split('.')
               fk[aDict.keys()[0]] = dotted[0]+'.'+dotted[1]

           attributes = []
           types = {}
           for aDict in aRow.schema :
              attributes.append(aDict.keys()[0])
              types[aDict.keys()[0]] = aDict.values()[0]
           singleschemarow = SingleSchema(name, attributes, \
                             types, aRow.primarykeys, aRow.uniquekeys, fk, \
                             aRow.notnulls, {}, {}, []) 
           self.allSchemas[name.lower()] = singleschemarow

    def buildMultiTableViewObjects(self): 
        #['t_file_type', 't_info_evcoll', 't_dataset_status', 't_run', 't_info_anads', 't_desc_mc', 't_app_family', 't_app_config', 't_evcoll_run', 't_desc_trigger', 't_validation_status', 't_collection_type', 't_processing_path', 't_event_collection', 't_file_status', 't_evcoll_status', 't_anads_data', 't_file', 't_data_tier', 't_application', 't_physics_group', 't_block_status', 't_schema_revision', 't_evcoll_file', 't_evcoll_parentage', 't_parentage_type', 't_run_quality', 't_analysis_dataset', 't_processed_dataset', 't_desc_primary', 't_primary_dataset', 't_person', 't_block']

        # View that descibes Application Configurations
        self.InsertApps  = multiRowRepresentation("InsertApps",self.fkExclusionAttributes)
        self.InsertApps.addSchema(self.allSchemas['t_application'])
        self.InsertApps.addSchema(self.allSchemas['t_app_family'])
        self.InsertApps.addSchema(self.allSchemas['t_collection_type']) 
        self.InsertApps.addSchema(self.allSchemas['t_app_config']) 
        self.InsertApps.addCondition("t_application.app_family = " + \
                                       "t_app_family.id")
        self.InsertApps.addCondition("t_app_config.application = " + \
                                       "t_application.id")
        self.InsertApps.addCondition("t_application.input_type = " + \
                                     "t_collection_type.id")
        self.InsertApps.addCondition("t_application.output_type = " + \
                                     "t_collection_type.id")

        self.multiTableViewObjects.append(self.InsertApps)

        # View that describes Person
        self.Person = multiRowRepresentation("Person", self.fkExclusionAttributes)
        self.Person.addSchema(self.allSchemas['t_person'])
        self.multiTableViewObjects.append(self.Person)

        # View that describes PhysicsGroup
        self.PhysicsGroup = multiRowRepresentation("PhysicsGroup", self.fkExclusionAttributes)
        self.PhysicsGroup.addSchema(self.allSchemas['t_physics_group'])
        self.PhysicsGroup.addSchema(self.allSchemas['t_person'])
        self.PhysicsGroup.addCondition('t_physics_group.convenor = t_person.id')
        self.multiTableViewObjects.append(self.PhysicsGroup)

        # View on EvenetCollection without File
        self.EvCollView = multiRowRepresentation("EvCollView", self.fkExclusionAttributes)
        self.EvCollView.addSchema(self.allSchemas['t_event_collection'])
        self.EvCollView.addSchema(self.allSchemas['t_info_evcoll'])
        self.EvCollView.addSchema(self.allSchemas['t_evcoll_status'])
        self.EvCollView.addSchema(self.allSchemas['t_validation_status'])
        #self.EvCollView.addSchema(self.allSchemas['t_evcoll_run'])
        #self.EvCollView.addSchema(self.allSchemas['t_run'])
        #self.EvCollView.addSchema(self.allSchemas['t_run_quality'])
        self.EvCollView.addCondition('t_info_evcoll.event_collection = t_event_collection.id')
        self.EvCollView.addCondition('t_info_evcoll.validation_status = ' + \
                't_validation_status.id')
        self.EvCollView.addCondition('t_info_evcoll.status = ' + \
                't_evcoll_status.id')
        #self.EvCollView.addCondition('t_evcoll_run.event_collection = ' + \
        #        't_event_collection.id')        
        #self.EvCollView.addCondition('t_evcoll_run.run = ' + \
        #        't_run.id')
        #self.EvCollView.addCondition('t_run.run_quality = ' + \
        #        't_run_quality.id')
        self.multiTableViewObjects.append(self.EvCollView)
        
        # View on Files, Helpful for file insertion in batch.
        self.Fileview = multiRowRepresentation("FileView", self.fkExclusionAttributes)
        self.Fileview.addSchema(self.allSchemas['t_file_type'])
        self.Fileview.addSchema(self.allSchemas['t_file_status'])
        self.Fileview.addSchema(self.allSchemas['t_file'])
        self.Fileview.addSchema(self.allSchemas['t_block'])
        self.Fileview.addSchema(self.allSchemas['t_block_status']) 
        self.Fileview.addSchema(self.allSchemas['t_evcoll_file'])
        self.Fileview.addCondition('t_file.type = t_file_type.id')
        self.Fileview.addCondition('t_file.status = t_file_status.id')
        self.Fileview.addCondition('t_file.inblock = t_block.id')
        self.Fileview.addCondition('t_block.status = t_block_status.id')
        #self.Fileview.addCondition('t_evcoll_file.evcollid = ' + \
        #                   't_event_collection.id')         
        self.Fileview.addCondition('t_evcoll_file.fileid = t_file.id')
        self.multiTableViewObjects.append(self.Fileview)
       
        # View that descibes test query for CRAB
        #self.EventCollectionsForCrab  = multiRowRepresentation("EventCollectionsForCrab", self.fkExclusionAttributes)
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_application'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_app_config'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_collection_type'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_processing_path'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_processed_dataset'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_primary_dataset'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_event_collection'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_info_evcoll'])
        #self.EventCollectionsForCrab.addSchema(self.allSchemas['t_anads_data'])
       # 
        #self.EventCollectionsForCrab.addCondition("applicationconfiguration.applicationid = " + \
        #                               "application.applicationid")
        #self.EventCollectionsForCrab.addCondition("application.outputcollectiontype = " + \
        #                               "collectiontype.collectiontypeid")
        #self.EventCollectionsForCrab.addCondition("applicationconfiguration.applicationconfigurationid = " + \
        #                               "processingpath.processingrecordid")
        #self.EventCollectionsForCrab.addCondition("processingpath.processingpathid = " + \
        #                               "processeddataset.processingpathid")
        #self.EventCollectionsForCrab.addCondition("primarydataset.primarydatasetid = " + \
        #                               "processeddataset.primarydatasetid")
        #self.EventCollectionsForCrab.addCondition("eventcollection.processeddatasetid = " + \
        #                               "processeddataset.processeddatasetid")
        #self.EventCollectionsForCrab.addCondition("eventcollection.eventcollectionid = " + \
        #                               "analysiscollectiondata.eventcollectionid")
        #self.multiTableViewObjects.append(self.EventCollectionsForCrab)


        # View that describes primary/processed dataset parameters
        self.PrimaryDataset = multiRowRepresentation("PrimaryDataset", self.fkExclusionAttributes)
        #self.PrimaryDataset.addSchema(self.allSchemas['t_desc_trigger'])
        self.PrimaryDataset.addSchema(self.allSchemas['t_desc_mc'])
        self.PrimaryDataset.addSchema(self.allSchemas['t_desc_primary'])
        self.PrimaryDataset.addSchema(self.allSchemas['t_primary_dataset'])
        self.PrimaryDataset.addSchema(self.allSchemas['t_physics_group'])
        self.PrimaryDataset.addCondition('t_primary_dataset.physics_group = t_physics_group.id')
        self.PrimaryDataset.addCondition('t_primary_dataset.description = t_desc_primary.id')
        #self.PrimaryDataset.addCondition('t_desc_primary.trigger_path = t_desc_trigger.id')
        self.PrimaryDataset.addCondition('t_desc_primary.mc_channel = t_desc_mc.id')
        self.multiTableViewObjects.append(self.PrimaryDataset)


        # View that describes processing path - self referencing
        self.ProcessingPath = multiRowRepresentation("ProcessingPath", self.fkExclusionAttributes)
        self.ProcessingPath.addSchema(self.allSchemas['t_processing_path'])
        self.ProcessingPath.addSchema(self.allSchemas['t_processed_dataset'])
        self.ProcessingPath.addSchema(self.allSchemas['t_primary_dataset'])
        self.ProcessingPath.addSchema(self.allSchemas['t_data_tier'])
        #self.ProcessingPath.addSchema(self.allSchemas['t_block'])
        self.ProcessingPath.addCondition('t_processed_dataset.processing_path = ' + \
                                         't_processing_path.id')
        self.ProcessingPath.addCondition('t_processing_path.data_tier = t_data_tier.id')
        self.ProcessingPath.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        #self.ProcessingPath.addCondition('t_processing_path.parent = t_processing_path.id')
        self.multiTableViewObjects.append(self.ProcessingPath)

        # View that describes AnalysisDatasets
        self.AnalysisDataset = multiRowRepresentation("AnalysisDataset", self.fkExclusionAttributes)
        self.AnalysisDataset.addSchema(self.allSchemas['t_analysis_dataset'])
        self.AnalysisDataset.addSchema(self.allSchemas['t_anads_data'])
        self.AnalysisDataset.addSchema(self.allSchemas['t_info_anads'])
        self.AnalysisDataset.addSchema(self.allSchemas['t_dataset_status'])
        self.AnalysisDataset.addSchema(self.allSchemas['t_validation_status'])
        self.AnalysisDataset.addCondition('t_anads_data.analysis_dataset = t_analysis_dataset.id')
        self.AnalysisDataset.addCondition('t_info_anads.analysis_dataset = t_analysis_dataset.id')
        self.AnalysisDataset.addCondition('t_info_anads.status = t_dataset_status.id')
        self.AnalysisDataset.addCondition('t_info_anads.validation_status = t_validation_status.id')  
        self.multiTableViewObjects.append(self.AnalysisDataset)


        # View that describes the Parentage Relation of a EvColl.
        self.DatasetProvenenceEvChild= multiRowRepresentation("DatasetProvenenceEvChild", self.fkExclusionAttributes)
        self.DatasetProvenenceEvChild.addSchema(self.allSchemas['t_event_collection'], 1)
        self.DatasetProvenenceEvChild.addSchema(self.allSchemas['t_evcoll_parentage'])
        self.DatasetProvenenceEvChild.addSchema(self.allSchemas['t_processed_dataset'])
        self.DatasetProvenenceEvChild.addSchema(self.allSchemas['t_processing_path'])
        self.DatasetProvenenceEvChild.addSchema(self.allSchemas['t_primary_dataset'])
        self.DatasetProvenenceEvChild.addSchema(self.allSchemas['t_parentage_type'])
        self.DatasetProvenenceEvChild.addSchema(self.allSchemas['t_data_tier'])
        self.DatasetProvenenceEvChild.addCondition('t_processing_path.data_tier = t_data_tier.id')
        self.DatasetProvenenceEvChild.addCondition('t_evcoll_parentage.child = t_event_collection.id')
        self.DatasetProvenenceEvChild.addCondition('t_evcoll_parentage.parent = t_event_collection.id')
        self.DatasetProvenenceEvChild.addCondition('t_event_collection.processed_dataset = t_processed_dataset.id')
        self.DatasetProvenenceEvChild.addCondition('t_processed_dataset.processing_path = t_processing_path.id')
        self.DatasetProvenenceEvChild.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        self.DatasetProvenenceEvChild.addCondition('t_evcoll_parentage.type = t_parentage_type.id')

        self.DatasetProvenenceEvChild.ExcludeThisMultiRelation({'t_evcoll_parentage.child':'t_event_collection.id'}) 
        self.DatasetProvenenceEvChild.ExcludeThisMultiRelation({'t_evcoll_parentage.parent':'t_event_collection.id'})
        self.DatasetProvenenceEvChild.AddThisRelation({'t_evcoll_parentage.child':'t_event_collection.id'})

        self.multiTableViewObjects.append(self.DatasetProvenenceEvChild)


        # View that describes the Parentage Relation of a EvColl.
        self.DatasetProvenenceEvParent= multiRowRepresentation("DatasetProvenenceEvParent", self.fkExclusionAttributes)
        self.DatasetProvenenceEvParent.addSchema(self.allSchemas['t_event_collection'], 1)
        self.DatasetProvenenceEvParent.addSchema(self.allSchemas['t_evcoll_parentage'])
        self.DatasetProvenenceEvParent.addSchema(self.allSchemas['t_processed_dataset'])
        self.DatasetProvenenceEvParent.addSchema(self.allSchemas['t_processing_path'])
        self.DatasetProvenenceEvParent.addSchema(self.allSchemas['t_primary_dataset'])
        self.DatasetProvenenceEvParent.addSchema(self.allSchemas['t_parentage_type'])
        self.DatasetProvenenceEvParent.addSchema(self.allSchemas['t_data_tier'])
        self.DatasetProvenenceEvParent.addCondition('t_processing_path.data_tier = t_data_tier.id')
        self.DatasetProvenenceEvParent.addCondition('t_evcoll_parentage.child = t_event_collection.id')
        self.DatasetProvenenceEvParent.addCondition('t_evcoll_parentage.parent = t_event_collection.id')
        self.DatasetProvenenceEvParent.addCondition('t_event_collection.processed_dataset = t_processed_dataset.id')
        self.DatasetProvenenceEvParent.addCondition('t_processed_dataset.processing_path = t_processing_path.id')
        self.DatasetProvenenceEvParent.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        self.DatasetProvenenceEvParent.addCondition('t_evcoll_parentage.type = t_parentage_type.id')

        self.DatasetProvenenceEvParent.ExcludeThisMultiRelation({'t_evcoll_parentage.child':'t_event_collection.id'})
        self.DatasetProvenenceEvParent.ExcludeThisMultiRelation({'t_evcoll_parentage.parent':'t_event_collection.id'})
        self.DatasetProvenenceEvParent.AddThisRelation({'t_evcoll_parentage.parent':'t_event_collection.id'})

        self.multiTableViewObjects.append(self.DatasetProvenenceEvParent)



        # View that describes the Parentage Relation of a EvColl.
        self.CrabEvCollView= multiRowRepresentation("CrabEvCollView", self.fkExclusionAttributes)
        self.CrabEvCollView.addSchema(self.allSchemas['t_event_collection'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_processed_dataset'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_processing_path'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_data_tier'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_primary_dataset'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_block'])
        #self.CrabEvCollView.addSchema(self.allSchemas['t_block_status'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_info_evcoll'])
        #self.CrabEvCollView.addSchema(self.allSchemas['t_evcoll_status'])
        #self.CrabEvCollView.addSchema(self.allSchemas['t_validation_status'])


        self.CrabEvCollView.addCondition('t_processing_path.data_tier = t_data_tier.id')
        self.CrabEvCollView.addCondition('t_event_collection.processed_dataset = t_processed_dataset.id')
        self.CrabEvCollView.addCondition('t_processed_dataset.processing_path = t_processing_path.id')
        self.CrabEvCollView.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        self.CrabEvCollView.addCondition('t_block.processed_dataset = t_processed_dataset.id')
        #self.CrabEvCollView.addCondition('t_block.status = t_block_status.id')
        self.CrabEvCollView.addCondition('t_info_evcoll.event_collection = t_event_collection.id')
        #self.CrabEvCollView.addCondition('t_info_evcoll.validation_status = ' + \
        #        't_validation_status.id')
        #self.CrabEvCollView.addCondition('t_info_evcoll.status = ' + \
        #        't_evcoll_status.id')
        self.multiTableViewObjects.append(self.CrabEvCollView)


