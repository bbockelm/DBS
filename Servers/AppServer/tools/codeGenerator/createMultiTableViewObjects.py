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

# WARNNING **** WARNNING **** WARNNING **** WARNNING **** WARNNING **** WARNNING

# NO MATTER WHAT DO NOT INSERT MULTI RELATIONSHIP IN ADDCONDITIONS
# THIS MEANS THAT IF THERE IS A TBLE IN THE VIEW THAT WOULD EXIST MORE THAN ONE TIMES BEACUSE OF IT FK RELATION, DO NOT ADD IT IN CONDITIONS OR IT WILL YEILDS WRONG RESULTS
# WARNNING **** WARNNING **** WARNNING **** WARNNING **** WARNNING **** WARNNING
        # View that describes /PrimaryDS/DataTier/ProcDS path - self referencing
        self.DatasetPath = multiRowRepresentation("DatasetPath", self.fkExclusionAttributes)   
        self.DatasetPath.addSchema(self.allSchemas['t_data_tier'])    
        self.DatasetPath.addSchema(self.allSchemas['t_processing'])    
        self.DatasetPath.addSchema(self.allSchemas['t_processing_name'])    
        self.DatasetPath.addSchema(self.allSchemas['t_processed_dataset'])   
        self.DatasetPath.addSchema(self.allSchemas['t_primary_dataset'])   
        self.DatasetPath.addCondition('t_processed_dataset.name = t_processing_name.id')     
        self.DatasetPath.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')     
        self.DatasetPath.addCondition('t_processed_dataset.data_tier = t_data_tier.id')     
        self.DatasetPath.addCondition('t_processing.primary_dataset = t_primary_dataset.id') 
        self.DatasetPath.addCondition('t_processing.name = t_processing_name.id') 
        self.multiTableViewObjects.append(self.DatasetPath)

        
        # View on EvenetCollection without File
        self.EvCollView = multiRowRepresentation("EvCollView", self.fkExclusionAttributes)
        self.EvCollView.addSchema(self.allSchemas['t_event_collection'])
        self.EvCollView.addSchema(self.allSchemas['t_evcoll_parentage'])
        self.EvCollView.addSchema(self.allSchemas['t_evcoll_status'])
        self.EvCollView.addSchema(self.allSchemas['t_parentage_type'])
        self.EvCollView.addCondition('t_evcoll_parentage.child = t_event_collection.id')
        self.EvCollView.addCondition('t_evcoll_parentage.parent = t_event_collection.id')
        self.EvCollView.addCondition('t_evcoll_parentage.type = t_parentage_type.id')        
        self.EvCollView.addCondition('t_event_collection.status = t_evcoll_status.id')        
        self.EvCollView.ExcludeThisMultiRelation({'t_evcoll_parentage.child':'t_event_collection.id'})
        self.EvCollView.ExcludeThisMultiRelation({'t_evcoll_parentage.parent':'t_event_collection.id'})
        self.EvCollView.AddThisRelation({'t_evcoll_parentage.child':'t_event_collection.id'})

        self.multiTableViewObjects.append(self.EvCollView)
 

        # View on EvenetCollection without File and without Parent
        self.EvCollViewNoParent = multiRowRepresentation("EvCollViewNoParent", self.fkExclusionAttributes)
        self.EvCollViewNoParent.addSchema(self.allSchemas['t_event_collection'])
        self.EvCollViewNoParent.addSchema(self.allSchemas['t_evcoll_status'])
        self.EvCollViewNoParent.addCondition('t_event_collection.status = t_evcoll_status.id')        

        self.multiTableViewObjects.append(self.EvCollViewNoParent)
       
        # View on Files, Helpful for file insertion in batch.
        self.Fileview = multiRowRepresentation("FileView", self.fkExclusionAttributes)
        self.Fileview.addSchema(self.allSchemas['t_file_type'])
        self.Fileview.addSchema(self.allSchemas['t_file'])
        self.Fileview.addSchema(self.allSchemas['t_evcoll_file'])
        self.Fileview.addSchema(self.allSchemas['t_file_status'])
        self.Fileview.addCondition('t_file.status = t_file_status.id')
        self.Fileview.addCondition('t_file.type = t_file_type.id')
        self.Fileview.addCondition('t_evcoll_file.fileid = t_file.id')
        self.multiTableViewObjects.append(self.Fileview)


        # View on Files, Helpful for file insertion in batch.
        self.PDBlockview = multiRowRepresentation("PDBlockView", self.fkExclusionAttributes)
        self.PDBlockview.addSchema(self.allSchemas['t_block'])
        self.PDBlockview.addSchema(self.allSchemas['t_block_status'])
        self.PDBlockview.addSchema(self.allSchemas['t_processed_dataset'])
        self.PDBlockview.addCondition('t_block.status = t_block_status.id')
        self.PDBlockview.addCondition('t_block.processed_dataset = t_processed_dataset.id')
        self.multiTableViewObjects.append(self.PDBlockview)

 

        # View on Files, Helpful for file insertion in batch.
        self.Blockview = multiRowRepresentation("BlockView", self.fkExclusionAttributes)
        self.Blockview.addSchema(self.allSchemas['t_block'])
        self.Blockview.addSchema(self.allSchemas['t_block_status']) 
        self.Blockview.addCondition('t_block.status = t_block_status.id')
        self.multiTableViewObjects.append(self.Blockview)
       
       
        # View that describes primary/processed dataset parameters
        self.PrimaryDataset = multiRowRepresentation("PrimaryDataset", self.fkExclusionAttributes)
        self.PrimaryDataset.addSchema(self.allSchemas['t_primary_dataset'])
        self.multiTableViewObjects.append(self.PrimaryDataset)


        # View that describes processing path - self referencing
        self.ProcessingPath = multiRowRepresentation("ProcessingPath", self.fkExclusionAttributes)
        self.ProcessingPath.addSchema(self.allSchemas['t_app_family'])
        self.ProcessingPath.addSchema(self.allSchemas['t_data_tier'])
        self.ProcessingPath.addSchema(self.allSchemas['t_application'])
        self.ProcessingPath.addSchema(self.allSchemas['t_app_config'])
        self.ProcessingPath.addSchema(self.allSchemas['t_parameter_set'])
        self.ProcessingPath.addSchema(self.allSchemas['t_processing'])
        self.ProcessingPath.addSchema(self.allSchemas['t_processing_name'])
        self.ProcessingPath.addSchema(self.allSchemas['t_processed_dataset'])
        self.ProcessingPath.addSchema(self.allSchemas['t_primary_dataset'])
        self.ProcessingPath.addCondition('t_processed_dataset.name = t_processing_name.id')
        self.ProcessingPath.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        self.ProcessingPath.addCondition('t_processed_dataset.data_tier = t_data_tier.id')
        self.ProcessingPath.addCondition('t_processing.primary_dataset = t_primary_dataset.id')
        self.ProcessingPath.addCondition('t_processing.app_config = t_app_config.id')
        self.ProcessingPath.addCondition('t_processing.name = t_processing_name.id')
        self.ProcessingPath.addCondition("t_application.app_family = t_app_family.id")
        self.ProcessingPath.addCondition("t_app_config.parameter_set = t_parameter_set.id")
        self.ProcessingPath.addCondition('t_app_config.application = t_application.id')
        self.multiTableViewObjects.append(self.ProcessingPath)


        
        # View that describes the Parentage Relation of a EvColl.
        self.CrabEvCollFileView= multiRowRepresentation("CrabEvCollFileView", self.fkExclusionAttributes)
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_event_collection'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_processed_dataset'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_processing'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_processing_name'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_data_tier'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_primary_dataset'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_block'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_evcoll_file'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_file'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_file_type'])
        self.CrabEvCollFileView.addCondition('t_processed_dataset.name = t_processing_name.id')
        self.CrabEvCollFileView.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        self.CrabEvCollFileView.addCondition('t_processed_dataset.data_tier = t_data_tier.id')
        self.CrabEvCollFileView.addCondition('t_processing.primary_dataset = t_primary_dataset.id')
        self.CrabEvCollFileView.addCondition('t_processing.name = t_processing_name.id')
        self.CrabEvCollFileView.addCondition('t_event_collection.processed_dataset = t_processed_dataset.id')
        self.CrabEvCollFileView.addCondition('t_block.processed_dataset = t_processed_dataset.id')
        self.CrabEvCollFileView.addCondition('t_evcoll_file.evcoll = t_event_collection.id')
        self.CrabEvCollFileView.addCondition('t_evcoll_file.fileid = t_file.id')
        self.CrabEvCollFileView.addCondition('t_file.inblock = t_block.id')
        self.CrabEvCollFileView.addCondition('t_file.type = t_file_type.id')
        self.multiTableViewObjects.append(self.CrabEvCollFileView)
        



        # View that describes the Parentage Relation of a EvColl.
        self.CrabEvCollView= multiRowRepresentation("CrabEvCollView", self.fkExclusionAttributes)
        self.CrabEvCollView.addSchema(self.allSchemas['t_event_collection'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_processed_dataset'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_processing'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_processing_name'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_data_tier'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_primary_dataset'])
        self.CrabEvCollView.addSchema(self.allSchemas['t_block'])
        self.CrabEvCollView.addCondition('t_processed_dataset.name = t_processing_name.id')
        self.CrabEvCollView.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        self.CrabEvCollView.addCondition('t_processed_dataset.data_tier = t_data_tier.id')
        self.CrabEvCollView.addCondition('t_processing.primary_dataset = t_primary_dataset.id')
        self.CrabEvCollView.addCondition('t_processing.name = t_processing_name.id')
        self.CrabEvCollView.addCondition('t_event_collection.processed_dataset = t_processed_dataset.id')
        self.CrabEvCollView.addCondition('t_block.processed_dataset = t_processed_dataset.id')
        self.multiTableViewObjects.append(self.CrabEvCollView)

        # View on EvenetCollection without File
        self.EvCollFileView = multiRowRepresentation("EvCollFileView", self.fkExclusionAttributes)
        self.EvCollFileView.addSchema(self.allSchemas['t_event_collection'])
        #self.EvCollFileView.addSchema(self.allSchemas['t_evcoll_parentage'])
        #self.EvCollFileView.addSchema(self.allSchemas['t_parentage_type'])
        self.EvCollFileView.addSchema(self.allSchemas['t_evcoll_file'])
        self.EvCollFileView.addSchema(self.allSchemas['t_evcoll_status'])
        self.EvCollFileView.addSchema(self.allSchemas['t_file'])
        self.EvCollFileView.addSchema(self.allSchemas['t_file_type'])
        self.EvCollFileView.addSchema(self.allSchemas['t_file_status'])
        self.EvCollFileView.addCondition('t_file.status = t_file_status.id')
        self.EvCollFileView.addCondition('t_file.type = t_file_type.id')
        self.EvCollFileView.addCondition('t_evcoll_file.evcoll = t_event_collection.id')
        self.EvCollFileView.addCondition('t_evcoll_file.fileid = t_file.id')
        #self.EvCollFileView.addCondition('t_evcoll_parentage.child = t_event_collection.id')
        #self.EvCollFileView.addCondition('t_evcoll_parentage.parent = t_event_collection.id')
        #self.EvCollFileView.addCondition('t_evcoll_parentage.type = t_parentage_type.id')        
        self.EvCollFileView.addCondition('t_event_collection.status = t_evcoll_status.id')        
        #self.EvCollFileView.ExcludeThisMultiRelation({'t_evcoll_parentage.child':'t_event_collection.id'})
        #self.EvCollFileView.ExcludeThisMultiRelation({'t_evcoll_parentage.parent':'t_event_collection.id'})
        #self.EvCollFileView.AddThisRelation({'t_evcoll_parentage.child':'t_event_collection.id'})
        self.multiTableViewObjects.append(self.EvCollFileView)

	# View on EvenetCollection Parentage only 
        self.EvCollParentageView = multiRowRepresentation("EvCollParentageView", self.fkExclusionAttributes)
        self.EvCollParentageView.addSchema(self.allSchemas['t_evcoll_parentage'])
        self.multiTableViewObjects.append(self.EvCollParentageView)
        
	# View on EvenetCollection Status only 
        self.EvCollStatusView = multiRowRepresentation("EvCollStatusView", self.fkExclusionAttributes)
        self.EvCollStatusView.addSchema(self.allSchemas['t_evcoll_status'])
        self.multiTableViewObjects.append(self.EvCollStatusView)

	# View on EvenetCollection without File and without Parent or status
        self.EvCollSingleView = multiRowRepresentation("EvCollSingleView", self.fkExclusionAttributes)
        self.EvCollSingleView.addSchema(self.allSchemas['t_event_collection'])
        self.multiTableViewObjects.append(self.EvCollSingleView)

        # View that describes the Parentage Relation of a EvColl.
        """
        self.EvCollFileView= multiRowRepresentation("EvCollFileView", self.fkExclusionAttributes)
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_event_collection'])
        self.EvCollView.addSchema(self.allSchemas['t_evcoll_parentage'])
        self.EvCollView.addSchema(self.allSchemas['t_parentage_type'])

        self.CrabEvCollFileView.addSchema(self.allSchemas['t_info_evcoll'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_evcoll_file'])
        self.CrabEvCollFileView.addSchema(self.allSchemas['t_file'])
        self.CrabEvCollFileView.addCondition('t_processing_path.data_tier = t_data_tier.id')
        self.CrabEvCollFileView.addCondition('t_processed_dataset.processing_path = t_processing_path.id')
        self.CrabEvCollFileView.addCondition('t_processed_dataset.primary_dataset = t_primary_dataset.id')
        self.CrabEvCollFileView.addCondition('t_event_collection.processed_dataset = t_processed_dataset.id')
        self.CrabEvCollFileView.addCondition('t_info_evcoll.event_collection = t_event_collection.id')
        self.CrabEvCollFileView.addCondition('t_block.processed_dataset = t_processed_dataset.id')
        self.CrabEvCollFileView.addCondition('t_evcoll_file.evcoll = t_event_collection.id')
        self.CrabEvCollFileView.addCondition('t_evcoll_file.fileid = t_file.id')
        self.CrabEvCollFileView.addCondition('t_file.inblock = t_block.id')
        self.multiTableViewObjects.append(self.CrabEvCollFileView)
        """
        # View that describes the Parentage Relation of a EvColl.
        """
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
        """

