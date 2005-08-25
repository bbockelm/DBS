"""
test_dbs_proto0.py 

A DBS class to insert information into the DBS MySQL database.  
Example inserts are at the end of the file in the __main__ section.
This script is not robust at all and very rough.... 
This script is just meant for testing/understanding purpose.

Note: the insertion of the event collection parentage and the 
      whole AnalysisDataset are completelly missing!
Tecnnical note: the begin/end transaction are not supported (at least not
in a testing envrionment MySQL 4.0.23 and MySQL-python-1.2.0 )

Alessandra Fanfani 23-Aug-2005
"""

import ConnectionLayer
import exceptions
import os

__version__ = "$Id: $"

class DBS_db:
    """
     Class to connect to DBS with methods for specific SQL query 
    """
    class Error(Exception):
      def __init__(self, value):
         self.value = value
      def __str__(self):
         return repr(self.value)

    def __init__(self,database,user,passwd,host,verbose=0):
      """
        Constructor: define the db parameters and connect to it using ConnectionLayer
      """
      self.__host=host
      self.__user=user
      self.__passwd=passwd
      self.__database=database
      self.__verbose=verbose
      dbParams={'db':self.__database, 'user':self.__user,'passwd':self.__passwd,'host':self.__host}
      try:
       self.__dbConn = ConnectionLayer.getConnection(**dbParams)
      except  DBS_db.Error, e:
       raise DBS_db.Error("Connection Failed")

# ##################################################
#  INSERT methods
# ##################################################
    def insertPrimaryDataset(self,PrimD,GroupName):
      """
        Insert PrimaryDataset
     
          The arguments are:
             PrimD     primary datase
             GroupName physics group name
      """

      ## begin/end trasaction do not work (it might be an incompatibility problem with MySQL-python): use commit instead 
      #self.__dbConn.beginTransaction()

      ## insert PhysicsGroup table
      self.insertavalue('PhysicsGroup','PhysicsGroupName',GroupName)
      GroupID=self.select_id('PhysicsGroup','PhysicsGroupID','PhysicsGroupName',GroupName)

      ## insert MCDescription table
      self.insertavalue('MCDescription','MCChannelDescription',PrimD)
      MCID=dbs.select_id('MCDescription','MCDescriptionID','MCChannelDescription',PrimD)

      ## insert PrimaryDatasetDescription table
      self.insertavalue('PrimaryDatasetDescription','MCChannelDescriptionID',MCID)
      PrimDDescriptionID=dbs.select_id('PrimaryDatasetDescription','PrimaryDatasetDescriptionID','MCChannelDescriptionID',MCID)

      ## insert  PrimaryDataset table
      check=dbs.select_id('PrimaryDataset','PrimaryDatasetID','PrimaryDatasetName',PrimD)
      if ( check == None ):

        query="INSERT INTO PrimaryDataset (PrimaryDatasetName,PrimaryDatasetDescriptionID,PhysicsGroupID) VALUES ('%s',%s,%s)"%(PrimD,PrimDDescriptionID,GroupID)
        if (self.__verbose):
           print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)

      #self.__dbConn.endTransaction()
      self.__dbConn.commit()      

# ##################################################
    def insertApplication(self,exeName, appFamName, appVersion, inputType = None, outputType = None):
      """
        Insert Application
     
          The arguments are:
             exeName    Executable Name
             appFamName Application Family Name
             appVersion Application Version
             inputType  
             outputType
      """

      ## insert ApplicationFamily  table
      self.insertavalue('ApplicationFamily','ApplicationFamilyName',appFamName)
      appFamID=self.select_id('ApplicationFamily','ApplicationFamilyID','ApplicationFamilyName',appFamName)
      
      ## insert input and output CollectionType 
      self.insertavalue('CollectionType','CollectionType',inputType)
      inputTypeID=self.select_id('CollectionType','CollectionTypeID','CollectionType',inputType)
      self.insertavalue('CollectionType','CollectionType',outputType)
      outputTypeID=self.select_id('CollectionType','CollectionTypeID','CollectionType',outputType)

      ## insert  Application table 
      check=self.getApplicationID(exeName, appFamName, appVersion)
      if ( check == None ):

        query="INSERT INTO Application (ExecutableName,ApplicationVersion,ApplicationFamily,InputCollectionType,OutputCollectionType) VALUES ('%s','%s',%s,%s,%s)"%(exeName,appVersion,appFamID,inputTypeID,outputTypeID)
        if (self.__verbose):
            print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)
      
      self.__dbConn.commit()

# ##################################################
    def insertApplicationConfiguration(self, applicationID, ConditionsVersionTag):
      """
        Insert Application Configuration 
         associate a ConditionsVersionTag to an Application
      """
      ## insert  Application Configuration table  
      check=self.getApplicationConfigurationID(applicationID, ConditionsVersionTag)
      if ( check == None ):
        query="INSERT INTO ApplicationConfiguration (ApplicationID,ConditionsVersionTag) VALUES (%s,'%s')"%(applicationID, ConditionsVersionTag)
        if (self.__verbose):
            print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)

# ##################################################
    def insertProcessedDataset(self, PrimD, ProcD, exeName, appFamName, appVersion, ConditionsVersionTag , DataTier, ParentProcessingPathID = -1 ):
      """
        Insert ProcessedDataset
        arguments are:
             PrimD     primary dataset
             ProcD     processed dataset
             all the arguments needed as input for insertProcessingPath
      """

      ## get PrimaryDatasetID
      PrimDID=self.select_id('PrimaryDataset','PrimaryDatasetID','PrimaryDatasetName',PrimD)

      ## insert ProcessingPath
      self.insertProcessingPath(exeName, appFamName, appVersion, ConditionsVersionTag , DataTier, ParentProcessingPathID)   
      ProcPathID=self.getProcessingPathID(exeName, appFamName, appVersion, ConditionsVersionTag , DataTier)
      ## insert ProcessedDataset
      check=self.getProcessedDatasetID(PrimDID, ProcPathID ,ProcD)
      if ( check == None ):

        query="INSERT INTO ProcessedDataset (ProcessedDatasetName,PrimaryDatasetID,ProcessingPathID,OpenForWriting) VALUES ('%s',%s,%s,'%s')"%(ProcD,PrimDID,ProcPathID,'y')
        if (self.__verbose):
          print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)

      self.__dbConn.commit()      

# ##################################################
    def insertProcessingPath(self,exeName, appFamName, appVersion, ConditionsVersionTag , DataTier, ParentProcessingPathID = -1 , AggregatedPath= None):
      """
        Insert Processing Path 
        arguments are:
            exeName    executable            \
            appFamName application family     | these are to identify the ApplicationID \
            appVersion application version   /                                           | these are to identify the ApplicationConfiguration  
            ConditionsVersionTag                                                        /
            DataTier
            ParentProcessingPathID
      """
      ## insert DataTier and get DataTier ID
      self.insertavalue('DataTier','DataTierName',DataTier)
      DataTierID=self.select_id('DataTier','DataTierID','DataTierName',DataTier)        
      ## get Application ID
      AppID=self.getApplicationID(exeName, appFamName, appVersion)
      ## get ApplicationConfiguration ID
      AppConfID=self.getApplicationConfigurationID(AppID, ConditionsVersionTag)
      check=self.getProcessingPathID(exeName, appFamName, appVersion, ConditionsVersionTag , DataTier)
      if ( check == None ): 
        query="INSERT INTO ProcessingPath (ParentProcessingPathID,ApplicationConfigurationID,DataTierID,AggregatedPath) VALUES (%s,%s,%s,'%s')"%(ParentProcessingPathID,AppConfID,DataTierID,AggregatedPath)
        if (self.__verbose):
          print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)

      self.__dbConn.commit()

# ##################################################
    def insertEventCollection(self, ProcDID, CollectionName, nEvents, luminosity, evcStatus, validationStatus, RunNumber,RunQualityName, filelist , EventCollectionIndex, PrimaryEventCollection='n'):
      """
        Insert EventCollection
        arguments are:
           ProcDID           ProcessedDatasetID
           CollectionName    name of the EventCollection
           nEvents           number of events within the event collection
           luminosity        luminosity/cross section (?) 
           evcStatus         Status of the EventCollection (?)
           validationStatus  Validation status (?) 
           RunNumber         run number
           RunQualityName    some string identigying the quality of the run
           filelist          list of files dictionary
                             each file being a dictionary as:       
                             { 'LFN':logicalfilename ,'Checksum':checksum , 'Size':size , 'FileStatus':filestatus , 'FileType':filetype } 
           EventCollectionIndex    index defined equal to the Run for the time being (?)
           PrimaryEventCollection  flag to define whether or not it is a primary dataset (?)

      """


      ## insert into EventCollection Table

      # set EventCollectionIndex to Run number
      EventCollectionIndex=RunNumber
  
      check=self.getEventCollectionID(ProcDID,EventCollectionIndex)
      if ( check == None ): 
        query="INSERT INTO EventCollection (ProcessedDatasetID,EventCollectionIndex,PrimaryEventCollection) VALUES (%s,%s,'%s')"%(ProcDID,EventCollectionIndex,PrimaryEventCollection)
        if (self.__verbose):
          print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)
      
      evcID=self.getEventCollectionID(ProcDID,EventCollectionIndex)

      ## set EventCollection Status
      self.insertavalue('EventCollectionStatus','AnalysisCollectionStatus',evcStatus)
      evcStatusID=self.select_id('EventCollectionStatus','EventCollectionStatusID','AnalysisCollectionStatus',evcStatus)
      ## set Validation Status
      # set validationstatus equal to evcstatus (since I do not know what validationstatus is meant for)
      validStatus=evcStatus
      self.insertavalue('ValidationStatus','ValidationStatus',validStatus)
      validStatusID=self.select_id('ValidationStatus','ValidationStatusID','ValidationStatus',validStatus)

      ## insert into EventCollectionData Table
      check=self.getEventCollectionDataID(evcID,evcStatusID,validStatusID)
      if ( check == None ): 
        query="INSERT INTO EventCollectionData (EventCollectionID,CollectionName,NumberOfEvents,EstimatedLuminosity,EventCollectionStatus,ValidationStatus) VALUES (%s,'%s',%s,'%s',%s,%s)"%(evcID,CollectionName,nEvents,luminosity,evcStatusID,validStatusID)
        if (self.__verbose):
          print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)

      ## insert Run
      self.insertRun(RunNumber,RunQualityName)
      RunID=self.select_int_id('Run','RunID','RunNumber',RunNumber)
      ## insert EVC to Run mapping
      self.insertEventCollectionRun(evcID,RunID)
      #evcRunID=getEventCollectionRunID(evcID,RunID)

      ## Loop over Files
      for filemap in filelist:
        LogicalFileName=filemap['LFN']
        Checksum=filemap['Checksum']
        Size=filemap['Size']
        FileStatus=filemap['FileStatus']
        FileType=filemap['FileType']
        ## insert File
        self.insertFile(LogicalFileName,Checksum,Size,FileStatus,FileType)
        FileID=self.select_id('File','FileID','LogicalFileName',LogicalFileName)
        ## insert EVC to File mapping
        self.insertEvCollFile(evcID,FileID)


      ## Parentage: missing! 
      ## mapping a parent Event Collection ID to a child Event Collection ID with something like:
      #self.insertEventCollectionParentage(self,ParentevcID,evcID)
      ## but how to get the parent Event Collection ID? 

      self.__dbConn.commit()

# ##################################################
    def insertRun(self,RunNumber,RunQualityName):
      """
        insert run
      """
      ## insert Run Quality
      self.insertavalue('RunQuality','RunQualityName',RunQualityName)
      RunQualityID=self.select_id('RunQuality','RunQualityID','RunQualityName',RunQualityName)
      ## insert Run
      check= self.select_int_id('Run','RunID','RunNumber',RunNumber)
      if ( check == None ):
         query="INSERT INTO Run (RunNumber,RunQuality) VALUES (%s,%s)"%(RunNumber,RunQualityID)
         if (self.__verbose):
          print ">>> verbose: "+query
         self.__dbConn.queryExecute(query)
      self.__dbConn.commit()

# ##################################################
    def insertEventCollectionRun(self,evcID,RunID):
      """
        Insert evc to run mapping
      """
      check=self.getEventCollectionRunID(evcID,RunID)
      if ( check == None ):
         query="INSERT INTO EventCollectionRun (EventCollectionID,RunID) VALUES (%s,%s)"%(evcID,RunID)
         if (self.__verbose):
          print ">>> verbose: "+query
         self.__dbConn.queryExecute(query)

# ##################################################
    def insertFile(self,LogicalFileName,Checksum,Size,FileStatus,FileType):
      """
        Insert file
      """
      ## insert FileStatus
      self.insertavalue('FileStatus','FileStatus',FileStatus)
      FileStatusID=self.select_id('FileStatus','FileStatusID','FileStatus',FileStatus)
      ## insert FileType
      self.insertavalue('FileType','FileType',FileType)
      FileTypeID=self.select_id('FileType','FileTypeID','FileType',FileType)
      
      ## insert File
      check= self.select_id('File','FileID','LogicalFileName',LogicalFileName)
      if ( check == None ):
         query="INSERT INTO File (LogicalFileName,Checksum,Size,FileStatus,FileType) VALUES ('%s',%s,%s,%s,%s)"%(LogicalFileName,Checksum,Size,FileStatusID,FileTypeID)
         if (self.__verbose):
          print ">>> verbose: "+query
         self.__dbConn.queryExecute(query)

      self.__dbConn.commit()

# ##################################################
    def insertEvCollFile(self,evcID,FileID):
      """
        Insert evc to file mapping
      """
      check=self.getEvCollFileID(evcID,FileID)
      if ( check == None ):
         query="INSERT INTO EvCollFile (EvCollID,FileID) VALUES (%s,%s)"%(evcID,FileID)
         if (self.__verbose):
          print ">>> verbose: "+query
         self.__dbConn.queryExecute(query)

# ##################################################
    def insertEventCollectionParentage(self,ParentevcID,evcID):
      """
        Insert parent evc to evc mapping
      """
      check=checkEventCollectionParentage(ParentevcID,evcID)
      if ( check == None ):
         query="INSERT INTO EventCollectionParentage (ParentECID,ChildECID) VALUES (%s,%s)"%(ParentevcID,evcID)
         if (self.__verbose):
          print ">>> verbose: "+query
         self.__dbConn.queryExecute(query)

# ##################################################
    def insertavalue(self,table,field,value):
      """
        insert a value into a table 
      """
      ## check if that value already exist
      check= self.select_id(table,field,field,value)
      ## do the insert only if it do not exist
      if ( check == None ):
        query="INSERT INTO %s (%s) VALUES ('%s')"%(table,field,value)
        if (self.__verbose):
          print ">>> verbose: "+query
        self.__dbConn.queryExecute(query)

# ##################################################
#  SELECT methods
# ##################################################
    def getSchemaRevision(self):
      """
        Get Schema Revision 
      """
      query="SELECT * FROM SchemaRevision"
      result = self.__dbConn.queryExecuteWithResults(query)
      return result

    def getApplicationID(self,exeName, appFamName, appVersion):
      """
        Get Application ID
      """
      ## get application family
      appFamID=self.select_id('ApplicationFamily','ApplicationFamilyID','ApplicationFamilyName',appFamName)
      ## get ApplicationID
      query="SELECT ApplicationID FROM Application WHERE ExecutableName='%s' AND ApplicationVersion='%s' AND ApplicationFamily=%s"%(exeName, appVersion,appFamID)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ##################################################
    def getApplicationConfigurationID(self,ApplicationID,ConditionsVersionTag):
      """
        Get Application Configuration ID
      """
      query="SELECT ApplicationConfigurationID FROM ApplicationConfiguration WHERE ApplicationID=%s AND ConditionsVersionTag='%s'"%(ApplicationID, ConditionsVersionTag)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ##################################################
    def getProcessingPathID(self, exeName, appFamName, appVersion, ConditionsVersionTag , DataTier, AggregatedPath = None ):
      """
        Get ProcessingPath ID
      """
      ## get DataTier ID
      DataTierID=self.select_id('DataTier','DataTierID','DataTierName',DataTier)
      ## get Application ID
      AppID=self.getApplicationID(exeName, appFamName, appVersion)
      ## get ApplicationConfiguration ID
      AppConfID=self.getApplicationConfigurationID(AppID, ConditionsVersionTag)

      ## Note: the ParentProcessingPath is ignored in this SELECT (I don't understand it much) 
      query="SELECT ProcessingPathID FROM ProcessingPath WHERE ApplicationConfigurationID=%s AND DataTierID=%s AND AggregatedPath='%s'"%(AppConfID,DataTierID,AggregatedPath)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value
# ##################################################
    def getProcessedDatasetID(self, PrimDID, ProcPathID ,ProcD):
      """
        Get ProcessedDataset ID
      """
      query="SELECT ProcessedDatasetID FROM ProcessedDataset WHERE PrimaryDatasetID=%s AND ProcessingPathID=%s AND ProcessedDatasetName='%s'"%(PrimDID, ProcPathID ,ProcD)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# #################################################
    def getEventCollectionID(self,ProcDID,EvcIndex):
      """
        get event collection
      """
      query="SELECT EventCollectionID FROM EventCollection WHERE ProcessedDatasetID=%s AND EventCollectionIndex=%s "%(ProcDID,EvcIndex)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ##################################################
    def getEventCollectionDataID(self,evcID,evcStatusID,validStatusID):
      """
        get event collection data 
      """
      query="SELECT EventCollectionDataID FROM EventCollectionData WHERE EventCollectionID=%s AND EventCollectionStatus=%s AND ValidationStatus=%s"%(evcID,evcStatusID,validStatusID)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ##################################################
    def getEventCollectionRunID(self,evcID,RunID):
      """
        get evc to run mapping
      """
      query="SELECT EventCollectionRunID FROM EventCollectionRun WHERE  EventCollectionID=%s AND RunID=%s"%(evcID,RunID)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ##################################################
    def getEvCollFileID(self,evcID,FileID):
      """
        get evc to file mapping
      """
      query="SELECT EvCollFileID FROM EvCollFile WHERE EvCollID=%s AND FileID=%s"%(evcID,FileID)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ##################################################
    def checkEventCollectionParentage(ParentevcID,evcID):
      """
        get parent evc to evc mapping
      """
      query="SELECT * FROM EventCollectionParentage WHERE ParentECID=%s AND ChildECID=%s"%(ParentevcID,evcID)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ####################################################
    def select_id(self,table,field_id,field,value):
      """
        Get id from a table 
      """
      query="SELECT %s FROM %s WHERE %s='%s'"%(field_id,table,field,value)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ####################################################
    def select_int_id(self,table,field_id,field,value):
      """
        Get id from a table 
      """
      query="SELECT %s FROM %s WHERE %s=%s"%(field_id,table,field,value)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      if len(result)>0 :
        value = result[0][0]
      else:
        value=None
      return value

# ##################################################
    def select_lastid(self,table):
      """
        Get last id from a table 
      """
      query="SELECT LAST_INSERT_ID() FROM %s"%(table)
      if (self.__verbose):
        print ">>> verbose: "+query
      result=self.__dbConn.queryExecuteWithResults(query)
      value = result[0][0]
      return value

#############################################################

if __name__ == "__main__":

  ## connect to DBS database providing db parameteres (last argument is the verbose switch)
  verbose=1
  dbs=DBS_db('DBS_v14','dbs','dbs','localhost',verbose)

  version=dbs.getSchemaRevision()
  print '----- Schema version %s'%version

  ## test
  #dbs.insertavalue('PhysicsGroup','PhysicsGroupName','test')
  #ida=dbs.select_id('PhysicsGroup','PhysicsGroupID','PhysicsGroupName','test') 
  #print ' PhysicsGroupName=test has PhysicsGroupID= %d'%ida

  print "----> insert a PrimaryDataset "
  dbs.insertPrimaryDataset('AFDataset_mu','AFGroup_mu')
  PrimDID=dbs.select_id('PrimaryDataset','PrimaryDatasetID','PrimaryDatasetName','AFDataset_mu')

  print "----> insert an Application for Digi"
  dbs.insertApplication('writeDigi','ORCA','ORCA_7_X_Y','Hit','Digi')
  applicationID=dbs.getApplicationID('writeDigi','ORCA','ORCA_7_X_Y')
  dbs.insertApplicationConfiguration(applicationID,'VersionTestDigiTag')

  print "----> insert an Application for DST"
  dbs.insertApplication('writeDST','ORCA','ORCA_X_Y_Z','Digi','DST')
#  dbs.insertApplication('writeDST','ORCA','ORCA_X_Y_Z')
  applicationID=dbs.getApplicationID('writeDST','ORCA','ORCA_X_Y_Z')
  dbs.insertApplicationConfiguration(applicationID,'VersionWhatheverTag')

  print "----> insert a ProcessedDataset for Digi"
  # assume No parentProcessingPath for the Digi (of course this is fake)
  dbs.insertProcessedDataset('AFDataset_mu','AFDigiOwner_mu', 'writeDigi','ORCA','ORCA_7_X_Y', 'VersionTestDigiTag', 'Digi')
  ProcPathID=dbs.getProcessingPathID('writeDigi','ORCA','ORCA_7_X_Y', 'VersionTestDigiTag', 'Digi')
  ProcDID=dbs.getProcessedDatasetID(PrimDID, ProcPathID ,'AFDigiOwner_mu')
  
  print "----> insert EventCollections for the Digi ProcessedDataset"
  Runlist=[1600001,1600002]
  for Run in Runlist:
   print "------------> EventCollection for Run %d"%(Run)
   CollectionName="EvC_Run%d"%Run
   nEvents=250
   luminosity="whateverlumi"
   RunQualityName="ok"
   evcStatus="someStatus" 
   validationStatus="someStatus"
   file1="lfnDigi1_Run%d"%Run
   file2="lfnDigi2_Run%d"%Run
   Digi_filelist = [ {'LFN':file1,'Checksum':'123123123','Size':'123','FileStatus':'afilestatus','FileType':'afiletype'}, 
   {'LFN':file2,'Checksum':'456456456','Size':'456','FileStatus':'afilestatus','FileType':'afiletype'} ]

   dbs.insertEventCollection(ProcDID, CollectionName, nEvents, luminosity, evcStatus, validationStatus, Run, RunQualityName, Digi_filelist, Run) 

  print "----> insert a ProcessedDataset for DST "
  ParentProcessingPathID=dbs.getProcessingPathID('writeDigi','ORCA','ORCA_7_X_Y', 'VersionTestDigiTag', 'Digi')
  dbs.insertProcessedDataset('AFDataset_mu','AFDSTOwner_mu', 'writeDST','ORCA','ORCA_X_Y_Z', 'VersionWhatheverTag', 'DST', ParentProcessingPathID)
  ProcPathID=dbs.getProcessingPathID('writeDST','ORCA','ORCA_X_Y_Z', 'VersionWhatheverTag', 'DST')
  ProcDID=dbs.getProcessedDatasetID(PrimDID, ProcPathID ,'AFDSTOwner_mu')

  print "----> insert EventCollections for the DST ProcessedDataset"
  Runlist=[1600001,1600002]
  for Run in Runlist:
   print "   --------> EventCollection for Run %d"%(Run)
   CollectionName="EvC_Run%d"%Run
   nEvents=250
   luminosity="whateverlumi"
   RunQualityName="ok"
   evcStatus="someStatus"
   validationStatus="someStatus"
   file1="lfnDST1_Run%d"%Run
   file2="lfnDST2_Run%d"%Run
   DST_filelist = [ {'LFN':file1,'Checksum':'123123123','Size':'123','FileStatus':'afilestatus','FileType':'afiletype'},
   {'LFN':file2,'Checksum':'456456456','Size':'456','FileStatus':'afilestatus','FileType':'afiletype'} ]

   dbs.insertEventCollection(ProcDID, CollectionName, nEvents, luminosity, evcStatus, validationStatus, Run, RunQualityName, DST_filelist, Run)


