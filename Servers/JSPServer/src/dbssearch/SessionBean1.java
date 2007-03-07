/*
 * SessionBean1.java
 *
 * Created on March 4, 2007, 11:40 AM
 * Copyright ANZAR
 */
package dbssearch;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import javax.faces.FacesException;
import com.sun.sql.rowset.CachedRowSetXImpl;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class SessionBean1 extends AbstractSessionBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        algorithmconfigRowSet_search.setDataSourceName("java:comp/env/jdbc/CMSCALD");
        algorithmconfigRowSet_search.setCommand("SELECT UNIQUE \n                    CONCAT(CONCAT(CONCAT(CONCAT(CONCAT('/', LUEKING.AppExecutable.ExecutableName), '/'), LUEKING.AppFamily.FamilyName), '/'), LUEKING.AppVersion.Version)\n  as APP_PATH,\n LUEKING.ALGORITHMCONFIG.ID\nFROM LUEKING.ALGORITHMCONFIG\n          INNER JOIN LUEKING.APPEXECUTABLE ON LUEKING.ALGORITHMCONFIG.EXECUTABLENAME = LUEKING.APPEXECUTABLE.ID, LUEKING.APPFAMILY, LUEKING.APPVERSION\nWHERE LUEKING.ALGORITHMCONFIG.APPLICATIONFAMILY = LUEKING.APPFAMILY.ID\n          AND LUEKING.ALGORITHMCONFIG.APPLICATIONVERSION = LUEKING.APPVERSION.ID             ");
        algorithmconfigRowSet_search.setTableName("ALGORITHMCONFIG");
        datatierRowSet_search.setDataSourceName("java:comp/env/jdbc/CMSCALD");
        datatierRowSet_search.setCommand("SELECT ALL LUEKING.DATATIER.ID, \n                    LUEKING.DATATIER.NAME \nFROM LUEKING.DATATIER ");
        datatierRowSet_search.setTableName("DATATIER");
        primarydatasetRowSet_search.setDataSourceName("java:comp/env/jdbc/CMSCALD");
        primarydatasetRowSet_search.setCommand("SELECT * FROM LUEKING.PRIMARYDATASET");
        primarydatasetRowSet_search.setTableName("PRIMARYDATASET");
        runsRowSet_search.setDataSourceName("java:comp/env/jdbc/CMSCALD");
        runsRowSet_search.setCommand("SELECT * FROM LUEKING.RUNS");
        runsRowSet_search.setTableName("RUNS");
        storageelementRowSet_search.setDataSourceName("java:comp/env/jdbc/CMSCALD");
        storageelementRowSet_search.setCommand("SELECT * FROM LUEKING.STORAGEELEMENT");
        storageelementRowSet_search.setTableName("STORAGEELEMENT");
        filebranchRowSet_search.setDataSourceName("java:comp/env/jdbc/CMSCALD");
        filebranchRowSet_search.setCommand("SELECT ALL LUEKING.BRANCH.ID, \n                    LUEKING.BRANCH.NAME, \n                    LUEKING.BRANCH.LASTMODIFIEDBY, \n                    LUEKING.BRANCH.LASTMODIFICATIONDATE, \n                    LUEKING.BRANCH.CREATIONDATE, \n                    LUEKING.BRANCH.CREATEDBY \nFROM LUEKING.BRANCH ");
        filebranchRowSet_search.setTableName("FILEBRANCH");
        processeddatasetRowSet_search.setDataSourceName("java:comp/env/jdbc/CMSCALD");
        processeddatasetRowSet_search.setCommand("SELECT ALL LUEKING.PROCESSEDDATASET.NAME AS PROCNAME, \n                    LUEKING.PROCESSEDDATASET.CREATEDBY, \n                    LUEKING.PROCESSEDDATASET.CREATIONDATE, \n                    LUEKING.PROCESSEDDATASET.LASTMODIFIEDBY, \n                    LUEKING.PROCESSEDDATASET.LASTMODIFICATIONDATE, \n                    LUEKING.PRIMARYDATASET.NAME AS PRINAME, \n                    LUEKING.PROCDSSTATUS.STATUS AS PRODSTATUS, \n                    LUEKING.BLOCK.NAME AS BLOCKNAME, \n                    LUEKING.BLOCK.NUMBEROFFILES, \n                    LUEKING.BLOCK.NUMBEROFEVENTS, \n                    LUEKING.BLOCK.OPENFORWRITING, \n                    LUEKING.DATATIER.NAME AS TIERNAME, \n                    LUEKING.BLOCK.BLOCKSIZE, \n                    LUEKING.RUNS.RUNNUMBER, \n                    LUEKING.RUNS.NUMBEROFEVENTS, \n                    LUEKING.RUNS.NUMBEROFLUMISECTIONS, \n                    LUEKING.RUNS.TOTALLUMINOSITY, \n                    LUEKING.RUNS.STORENUMBER, \n                    LUEKING.RUNS.STARTOFRUN, \n                    LUEKING.RUNS.ENDOFRUN, \n                    LUEKING.STORAGEELEMENT.SENAME \nFROM LUEKING.PROCESSEDDATASET\n          INNER JOIN LUEKING.PRIMARYDATASET ON LUEKING.PROCESSEDDATASET.PRIMARYDATASET = LUEKING.PRIMARYDATASET.ID, LUEKING.PROCDSSTATUS, LUEKING.PROCALGO\n          INNER JOIN LUEKING.ALGORITHMCONFIG ON LUEKING.PROCALGO.ALGORITHM = LUEKING.ALGORITHMCONFIG.ID, LUEKING.BLOCK, LUEKING.PROCDSTIER\n          INNER JOIN LUEKING.DATATIER ON LUEKING.PROCDSTIER.DATATIER = LUEKING.DATATIER.ID, LUEKING.PROCDSRUNS\n          INNER JOIN LUEKING.RUNS ON LUEKING.PROCDSRUNS.RUN = LUEKING.RUNS.ID, LUEKING.SEBLOCK\n          INNER JOIN LUEKING.STORAGEELEMENT ON LUEKING.SEBLOCK.SEID = LUEKING.STORAGEELEMENT.ID\nWHERE LUEKING.PROCESSEDDATASET.STATUS = LUEKING.PROCDSSTATUS.ID\n          AND LUEKING.PROCALGO.DATASET = LUEKING.PROCESSEDDATASET.ID\n          AND LUEKING.BLOCK.DATASET = LUEKING.PROCESSEDDATASET.ID\n          AND LUEKING.PROCDSTIER.DATASET = LUEKING.PROCESSEDDATASET.ID\n          AND LUEKING.PROCDSRUNS.DATASET = LUEKING.PROCESSEDDATASET.ID\n          AND LUEKING.SEBLOCK.BLOCKID = LUEKING.BLOCK.ID ");
        processeddatasetRowSet_search.setTableName("PROCESSEDDATASET");
        
        
        //For Deatils Page
        primarydatasetRowSet.setDataSourceName("java:comp/env/jdbc/dataSource");
        primarydatasetRowSet.setCommand("SELECT * FROM PrimaryDataset");
        primarydatasetRowSet.setTableName("PrimaryDataset");
        processeddatasetRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        processeddatasetRowSet.setCommand("SELECT ALL ProcessedDataset.ID, \n                    ProcessedDataset.Name, \n                    ProcessedDataset.PrimaryDataset, \n                    ProcessedDataset.PhysicsGroup, \n                    ProcessedDataset.Status, \n                    ProcessedDataset.CreatedBy, \n                    ProcessedDataset.CreationDate, \n                    ProcessedDataset.LastModifiedBy, \n                    ProcessedDataset.LastModificationDate, \n                    PrimaryDataset.ID, \n                    PrimaryDataset.Name, \n                    PrimaryDataset.Annotation, \n                    PrimaryDataset.Description, \n                    PrimaryDataset.StartDate, \n                    PrimaryDataset.EndDate, \n                    PrimaryDataset.Type, \n                    PrimaryDataset.CreatedBy, \n                    PrimaryDataset.CreationDate, \n                    PrimaryDataset.LastModificationDate, \n                    PrimaryDataset.LastModifiedBy, \n                    ProcDSStatus.Status, \n                    Person.DistinguishedName \nFROM ProcessedDataset\n          INNER JOIN PrimaryDataset ON ProcessedDataset.PrimaryDataset = PrimaryDataset.ID, ProcDSStatus, Person\nWHERE PrimaryDataset.Name = ?\n          AND ProcessedDataset.Status = ProcDSStatus.ID\n          AND ProcessedDataset.LastModifiedBy = Person.ID ");
        processeddatasetRowSet.setTableName("ProcessedDataset");
        filesRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        filesRowSet.setCommand("SELECT ALL Files.ID, \n                    Files.LogicalFileName, \n                    Files.Checksum, \n                    Files.NumberOfEvents, \n                    Files.FileSize, \n                    Files.QueryableMetadata, \n                    Files.CreatedBy, \n                    Files.CreationDate, \n                    Files.LastModifiedBy, \n                    Files.LastModificationDate, \n                    FileType.Type, \n                    FileStatus.Status, \n                    Person.DistinguishedName \nFROM Files\n          INNER JOIN FileType ON Files.FileType = FileType.ID, FileStatus, Person\nWHERE Files.Dataset = ?\n          AND Files.FileStatus = FileStatus.ID\n          AND Files.LastModifiedBy = Person.ID ");
        filesRowSet.setTableName("Files");
        algorithmconfigRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        algorithmconfigRowSet.setCommand("SELECT ALL AlgorithmConfig.ID, \n                    AlgorithmConfig.ExecutableName, \n                    AlgorithmConfig.ApplicationVersion, \n                    AlgorithmConfig.ApplicationFamily, \n                    AlgorithmConfig.ParameterSetID, \n                    AppExecutable.ExecutableName, \n                    AppFamily.FamilyName, \n                    AppVersion.Version, \n                    QueryableParameterSet.Name, \n                    AlgorithmConfig.CreatedBy, \n                    AlgorithmConfig.CreationDate, \n                    AlgorithmConfig.LastModificationDate, \n                    AlgorithmConfig.LastModifiedBy, \n                    ProcAlgo.ID, \n                    ProcAlgo.Dataset, \n                    ProcAlgo.Algorithm, \n                    ProcAlgo.CreationDate, \n                    ProcAlgo.CreatedBy, \n                    ProcAlgo.LastModificationDate, \n                    ProcAlgo.LastModifiedBy \nFROM AlgorithmConfig\n          INNER JOIN AppExecutable ON AlgorithmConfig.ExecutableName = AppExecutable.ID, AppFamily, AppVersion, QueryableParameterSet, ProcAlgo\nWHERE AlgorithmConfig.ApplicationFamily = AppFamily.ID\n          AND AlgorithmConfig.ApplicationVersion = AppVersion.ID\n          AND AlgorithmConfig.ParameterSetID = QueryableParameterSet.ID\n          AND ProcAlgo.Algorithm = AlgorithmConfig.ID\n          AND ProcAlgo.Dataset = ? ");
        algorithmconfigRowSet.setTableName("AlgorithmConfig");
        blockRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        blockRowSet.setCommand("SELECT ALL Block.ID, \n                    Block.Name, \n                    Block.BlockSize, \n                    Block.NumberOfFiles, \n                    Block.NumberOfEvents, \n                    Block.OpenForWriting, \n                    Block.CreatedBy, \n                    Block.CreationDate, \n                    Block.LastModifiedBy, \n                    Block.LastModificationDate, \n                    StorageElement.SEName, \n                    Person.DistinguishedName \nFROM Block\n          LEFT OUTER JOIN SEBlock ON SEBlock.BlockID = Block.ID\n          LEFT OUTER JOIN StorageElement ON SEBlock.SEID = StorageElement.ID, Person\nWHERE Block.Dataset = ?\n          AND Block.LastModifiedBy = Person.ID ");
        blockRowSet.setTableName("Block");
        algorithmconfigRowSet.setColumnNames(new String[] {"QueryableParameter"});
        analysisdatasetRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        analysisdatasetRowSet.setCommand("SELECT ALL AnalysisDataset.ID, \n                    AnalysisDataset.Name, \n                    AnalysisDataset.Annotation, \n                    AnalysisDataset.ProcessedDS, \n                    AnalysisDataset.Definition, \n                    AnalysisDataset.Type, \n                    AnalysisDataset.Status, \n                    AnalysisDataset.Parent, \n                    AnalysisDataset.PhysicsGroup, \n                    AnalysisDataset.CreatedBy, \n                    AnalysisDataset.CreationDate, \n                    AnalysisDataset.LastModifiedBy, \n                    AnalysisDataset.LastModificationDate, \n                    Person.DistinguishedName \nFROM AnalysisDataset\n          INNER JOIN Person ON AnalysisDataset.LastModifiedBy = Person.ID\nWHERE AnalysisDataset.ProcessedDS = ? ");
        analysisdatasetRowSet.setTableName("AnalysisDataset");
        datatierRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        datatierRowSet.setCommand("SELECT ALL DataTier.ID, \n                    DataTier.Name, \n                    DataTier.LastModifiedBy, \n                    DataTier.LastModificationDate, \n                    DataTier.CreationDate, \n                    DataTier.CreatedBy, \n                    ProcDSTier.ID, \n                    ProcDSTier.Dataset, \n                    ProcDSTier.DataTier, \n                    ProcDSTier.CreationDate, \n                    ProcDSTier.CreatedBy, \n                    ProcDSTier.LastModificationDate, \n                    ProcDSTier.LastModifiedBy, \n                    Person.DistinguishedName \nFROM DataTier\n          INNER JOIN ProcDSTier ON ProcDSTier.DataTier = DataTier.ID, Person\nWHERE ProcDSTier.Dataset = ?\n          AND DataTier.CreatedBy = Person.ID ");
        datatierRowSet.setTableName("DataTier");
        runsRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        runsRowSet.setCommand("SELECT ALL Runs.ID, \n                    Runs.RunNumber, \n                    Runs.NumberOfEvents, \n                    Runs.NumberOfLumiSections, \n                    Runs.TotalLuminosity, \n                    Runs.StoreNumber, \n                    Runs.StartOfRun, \n                    Runs.EndOfRun, \n                    Runs.CreatedBy, \n                    Runs.CreationDate, \n                    Runs.LastModifiedBy, \n                    Runs.LastModificationDate, \n                    ProcDSRuns.ID, \n                    ProcDSRuns.Dataset, \n                    ProcDSRuns.Run, \n                    ProcDSRuns.CreationDate, \n                    ProcDSRuns.CreatedBy, \n                    ProcDSRuns.LastModificationDate, \n                    ProcDSRuns.LastModifiedBy, \n                    Person.DistinguishedName \nFROM Runs\n          INNER JOIN ProcDSRuns ON ProcDSRuns.Run = Runs.ID, Person\nWHERE ProcDSRuns.Dataset = ?\n          AND Runs.LastModifiedBy = Person.ID ");
        runsRowSet.setTableName("Runs");
        filesParentRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        filesParentRowSet.setCommand("SELECT ALL Files.LogicalFileName, \n                    Files.Checksum, \n                    Files.NumberOfEvents, \n                    Files.FileSize, \n                    Files.QueryableMetadata, \n                    Files.CreatedBy, \n                    Files.CreationDate, \n                    Files.LastModifiedBy, \n                    Files.LastModificationDate, \n                    FileStatus.Status, \n                    FileType.Type, \n                    FileParentage.CreatedBy, \n                    FileParentage.CreationDate, \n                    FileParentage.LastModifiedBy, \n                    FileParentage.LastModificationDate \nFROM Files\n          INNER JOIN FileParentage ON FileParentage.ItsParent = Files.ID, FileStatus, FileType\nWHERE FileParentage.ThisFile = ?\n          AND Files.FileStatus = FileStatus.ID\n          AND Files.FileType = FileType.ID ");
        filesParentRowSet.setTableName("Files");
        filetierRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        filetierRowSet.setCommand("SELECT ALL DataTier.Name, \n                    FileTier.LastModifiedBy, \n                    FileTier.LastModificationDate, \n                    FileTier.CreatedBy, \n                    FileTier.CreationDate, \n                    FileTier.DataTier, \n                    FileTier.Fileid, \n                    FileTier.ID \nFROM FileTier\n          INNER JOIN DataTier ON FileTier.DataTier = DataTier.ID\nWHERE FileTier.Fileid = ? ");
        filetierRowSet.setTableName("FileTier");
        filebranchRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        filebranchRowSet.setCommand("SELECT ALL FileBranch.ID, \n                    FileBranch.Fileid, \n                    FileBranch.Branch, \n                    FileBranch.CreationDate, \n                    FileBranch.CreatedBy, \n                    FileBranch.LastModificationDate, \n                    FileBranch.LastModifiedBy, \n                    Branch.Name \nFROM FileBranch\n          INNER JOIN Branch ON FileBranch.Branch = Branch.ID\nWHERE FileBranch.Fileid = ? ");
        filebranchRowSet.setTableName("FileBranch");
        lumisectionRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        lumisectionRowSet.setCommand("SELECT ALL LumiSection.ID, \n                    LumiSection.LumiSectionNumber, \n                    LumiSection.RunNumber, \n                    LumiSection.StartEventNumber, \n                    LumiSection.EndEventNumber, \n                    LumiSection.LumiStartTime, \n                    LumiSection.LumiEndTime, \n                    LumiSection.CreatedBy, \n                    LumiSection.CreationDate, \n                    LumiSection.LastModifiedBy, \n                    LumiSection.LastModificationDate \nFROM LumiSection\n          INNER JOIN FileRunLumi ON FileRunLumi.Lumi = LumiSection.ID\nWHERE FileRunLumi.Fileid = ? ");
        lumisectionRowSet.setTableName("LumiSection");
        filealgoRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        filealgoRowSet.setCommand("SELECT ALL FileAlgo.Fileid, \n                    FileAlgo.CreationDate, \n                    FileAlgo.CreatedBy, \n                    FileAlgo.LastModificationDate, \n                    FileAlgo.LastModifiedBy, \n                    AlgorithmConfig.LastModificationDate, \n                    AlgorithmConfig.LastModifiedBy, \n                    AppExecutable.ExecutableName, \n                    AppFamily.FamilyName, \n                    AppVersion.Version, \n                    QueryableParameterSet.Name, \n                    QueryableParameterSet.Type, \n                    QueryableParameterSet.Annotation, \n                    FileAlgo.ID, \n                    FileAlgo.Algorithm \nFROM FileAlgo\n          INNER JOIN AlgorithmConfig ON FileAlgo.Algorithm = AlgorithmConfig.ID\n          INNER JOIN AppExecutable ON AlgorithmConfig.ExecutableName = AppExecutable.ID, AppFamily, AppVersion, QueryableParameterSet\nWHERE AlgorithmConfig.ApplicationFamily = AppFamily.ID\n          AND AlgorithmConfig.ApplicationVersion = AppVersion.ID\n          AND AlgorithmConfig.ParameterSetID = QueryableParameterSet.ID\n          AND FileAlgo.Fileid = ? ");
        filealgoRowSet.setTableName("FileAlgo");
        processedRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        processedRowSet.setCommand("SELECT ALL ProcessedDataset.ID, \n                    ProcessedDataset.Name, \n                    ProcessedDataset.PrimaryDataset \nFROM ProcessedDataset\nWHERE ProcessedDataset.ID = ? ");
        processedRowSet.setTableName("ProcessedDataset");
        fileInfoRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        fileInfoRowSet.setCommand("SELECT ALL Files.LogicalFileName \nFROM Files\nWHERE Files.ID = ? ");
        fileInfoRowSet.setTableName("Files");
        procdsparentRowSet.setDataSourceName("java:comp/env/jdbc/dataSource2");
        procdsparentRowSet.setCommand("SELECT ALL ProcessedDataset.ID, \n                    ProcessedDataset.Name, \n                    ProcessedDataset.CreationDate, \n                    ProcessedDataset.LastModificationDate, \n                    PrimaryDataset.Name, \n                    PrimaryDataset.StartDate, \n                    PrimaryDataset.EndDate, \n                    PrimaryDataset.Type \nFROM ProcDSParent\n          INNER JOIN ProcessedDataset ON ProcDSParent.ItsParent = ProcessedDataset.ID\n          INNER JOIN PrimaryDataset ON ProcessedDataset.PrimaryDataset = PrimaryDataset.ID\nWHERE ProcDSParent.ThisDataset = ? ");
        procdsparentRowSet.setTableName("ProcDSParent");
        algorithmconfigRowSet1.setDataSourceName("java:comp/env/jdbc/dataSource_1172949805625");
        algorithmconfigRowSet1.setCommand("SELECT UNIQUE \n                    CONCAT(CONCAT(CONCAT(CONCAT(CONCAT('/', LUEKING.AppExecutable.ExecutableName), '/'), LUEKING.AppFamily.FamilyName), '/'), LUEKING.AppVersion.Version)\n  as APP_PATH,\n LUEKING.ALGORITHMCONFIG.ID\nFROM LUEKING.ALGORITHMCONFIG\n          INNER JOIN LUEKING.APPEXECUTABLE ON LUEKING.ALGORITHMCONFIG.EXECUTABLENAME = LUEKING.APPEXECUTABLE.ID, LUEKING.APPFAMILY, LUEKING.APPVERSION\nWHERE LUEKING.ALGORITHMCONFIG.APPLICATIONFAMILY = LUEKING.APPFAMILY.ID\n          AND LUEKING.ALGORITHMCONFIG.APPLICATIONVERSION = LUEKING.APPVERSION.ID              ");
        algorithmconfigRowSet1.setTableName("ALGORITHMCONFIG");
        
        
    }

    private CachedRowSetXImpl algorithmconfigRowSet_search = new CachedRowSetXImpl();

    public CachedRowSetXImpl getAlgorithmconfigRowSet_search() {
        return algorithmconfigRowSet_search;
    }

    public void setAlgorithmconfigRowSet_search(CachedRowSetXImpl crsxi) {
        this.algorithmconfigRowSet_search = crsxi;
    }

    private CachedRowSetXImpl datatierRowSet_search = new CachedRowSetXImpl();

    public CachedRowSetXImpl getDatatierRowSet_search() {
        return datatierRowSet_search;
    }

    public void setDatatierRowSet_search(CachedRowSetXImpl crsxi) {
        this.datatierRowSet_search = crsxi;
    }

    private CachedRowSetXImpl primarydatasetRowSet_search = new CachedRowSetXImpl();

    public CachedRowSetXImpl getPrimarydatasetRowSet_search() {
        return primarydatasetRowSet_search;
    }

    public void setPrimarydatasetRowSet_search(CachedRowSetXImpl crsxi) {
        this.primarydatasetRowSet_search = crsxi;
    }

    private CachedRowSetXImpl runsRowSet_search = new CachedRowSetXImpl();

    public CachedRowSetXImpl getRunsRowSet_search() {
        return runsRowSet_search;
    }

    public void setRunsRowSet_search(CachedRowSetXImpl crsxi) {
        this.runsRowSet_search = crsxi;
    }

    private CachedRowSetXImpl storageelementRowSet_search = new CachedRowSetXImpl();

    public CachedRowSetXImpl getStorageelementRowSet_search() {
        return storageelementRowSet_search;
    }

    public void setStorageelementRowSet_search(CachedRowSetXImpl crsxi) {
        this.storageelementRowSet_search = crsxi;
    }

    private CachedRowSetXImpl filebranchRowSet_search = new CachedRowSetXImpl();

    public CachedRowSetXImpl getFilebranchRowSet_search() {
        return filebranchRowSet_search;
    }

    public void setFilebranchRowSet_search(CachedRowSetXImpl crsxi) {
        this.filebranchRowSet_search = crsxi;
    }

    private CachedRowSetXImpl processeddatasetRowSet_search = new CachedRowSetXImpl();

    public CachedRowSetXImpl getProcesseddatasetRowSet_search() {
        return processeddatasetRowSet_search;
    }

    public void setProcesseddatasetRowSet_search(CachedRowSetXImpl crsxi) {
        this.processeddatasetRowSet_search = crsxi;
    }
    
        private CachedRowSetXImpl primarydatasetRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getPrimarydatasetRowSet() {
        return primarydatasetRowSet;
    }

    public void setPrimarydatasetRowSet(CachedRowSetXImpl crsxi) {
        this.primarydatasetRowSet = crsxi;
    }

    private CachedRowSetXImpl processeddatasetRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getProcesseddatasetRowSet() {
        return processeddatasetRowSet;
    }

    public void setProcesseddatasetRowSet(CachedRowSetXImpl crsxi) {
        this.processeddatasetRowSet = crsxi;
    }

    private CachedRowSetXImpl filesRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getFilesRowSet() {
        return filesRowSet;
    }

    public void setFilesRowSet(CachedRowSetXImpl crsxi) {
        this.filesRowSet = crsxi;
    }

    private CachedRowSetXImpl algorithmconfigRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getAlgorithmconfigRowSet() {
        return algorithmconfigRowSet;
    }

    public void setAlgorithmconfigRowSet(CachedRowSetXImpl crsxi) {
        this.algorithmconfigRowSet = crsxi;
    }

    private CachedRowSetXImpl blockRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getBlockRowSet() {
        return blockRowSet;
    }

    public void setBlockRowSet(CachedRowSetXImpl crsxi) {
        this.blockRowSet = crsxi;
    }

    private CachedRowSetXImpl analysisdatasetRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getAnalysisdatasetRowSet() {
        return analysisdatasetRowSet;
    }

    public void setAnalysisdatasetRowSet(CachedRowSetXImpl crsxi) {
        this.analysisdatasetRowSet = crsxi;
    }

    private CachedRowSetXImpl datatierRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getDatatierRowSet() {
        return datatierRowSet;
    }

    public void setDatatierRowSet(CachedRowSetXImpl crsxi) {
        this.datatierRowSet = crsxi;
    }

    private CachedRowSetXImpl runsRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getRunsRowSet() {
        return runsRowSet;
    }

    public void setRunsRowSet(CachedRowSetXImpl crsxi) {
        this.runsRowSet = crsxi;
    }

    private CachedRowSetXImpl filesParentRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getFilesParentRowSet() {
        return filesParentRowSet;
    }

    public void setFilesParentRowSet(CachedRowSetXImpl crsxi) {
        this.filesParentRowSet = crsxi;
    }

    private CachedRowSetXImpl filetierRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getFiletierRowSet() {
        return filetierRowSet;
    }

    public void setFiletierRowSet(CachedRowSetXImpl crsxi) {
        this.filetierRowSet = crsxi;
    }

    private CachedRowSetXImpl filebranchRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getFilebranchRowSet() {
        return filebranchRowSet;
    }

    public void setFilebranchRowSet(CachedRowSetXImpl crsxi) {
        this.filebranchRowSet = crsxi;
    }

    private CachedRowSetXImpl lumisectionRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getLumisectionRowSet() {
        return lumisectionRowSet;
    }

    public void setLumisectionRowSet(CachedRowSetXImpl crsxi) {
        this.lumisectionRowSet = crsxi;
    }

    private CachedRowSetXImpl filealgoRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getFilealgoRowSet() {
        return filealgoRowSet;
    }

    public void setFilealgoRowSet(CachedRowSetXImpl crsxi) {
        this.filealgoRowSet = crsxi;
    }

    private CachedRowSetXImpl processedRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getProcessedRowSet() {
        return processedRowSet;
    }

    public void setProcessedRowSet(CachedRowSetXImpl crsxi) {
        this.processedRowSet = crsxi;
    }

    private CachedRowSetXImpl fileInfoRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getFileInfoRowSet() {
        return fileInfoRowSet;
    }

    public void setFileInfoRowSet(CachedRowSetXImpl crsxi) {
        this.fileInfoRowSet = crsxi;
    }

    private CachedRowSetXImpl procdsparentRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getProcdsparentRowSet() {
        return procdsparentRowSet;
    }

    public void setProcdsparentRowSet(CachedRowSetXImpl crsxi) {
        this.procdsparentRowSet = crsxi;
    }

    private CachedRowSetXImpl algorithmconfigRowSet1 = new CachedRowSetXImpl();

    public CachedRowSetXImpl getAlgorithmconfigRowSet1() {
        return algorithmconfigRowSet1;
    }

    public void setAlgorithmconfigRowSet1(CachedRowSetXImpl crsxi) {
        this.algorithmconfigRowSet1 = crsxi;
    }
    // </editor-fold>


    /** 
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean1() {
    }

    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>This method is called when this bean is initially added to
     * session scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * session scope.</p>
     * 
     * <p>You may customize this method to initialize and cache data values
     * or resources that are required for the lifetime of a particular
     * user session.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here

        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("SessionBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

    }

    /** 
     * <p>This method is called when the session containing it is about to be
     * passivated.  Typically, this occurs in a distributed servlet container
     * when the session is about to be transferred to a different
     * container instance, after which the <code>activate()</code> method
     * will be called to indicate that the transfer is complete.</p>
     * 
     * <p>You may customize this method to release references to session data
     * or resources that can not be serialized with the session itself.</p>
     */
    public void passivate() {
    }

    /** 
     * <p>This method is called when the session containing it was
     * reactivated.</p>
     * 
     * <p>You may customize this method to reacquire references to session
     * data or resources that could not be serialized with the
     * session itself.</p>
     */
    public void activate() {
    }

    /** 
     * <p>This method is called when this bean is removed from
     * session scope.  Typically, this occurs as a result of
     * the session timing out or being terminated by the application.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
    }

    /**
     * Holds value of property items_selected.
     */
    private String items_selected;

    /**
     * Getter for property items_selected.
     * @return Value of property items_selected.
     */
    public String getItems_selected() {

        return this.items_selected;
    }

    /**
     * Setter for property items_selected.
     * @param items_selected New value of property items_selected.
     */
    public void setItems_selected(String items_selected) {

        this.items_selected = items_selected;
    }

    /**
     * Holds value of property selected_algos.
     */
    private String[] selected_algos;

    /**
     * Getter for property selected_algos.
     * @return Value of property selected_algos.
     */
    public String[] getSelected_algos() {

        return this.selected_algos;
    }

    /**
     * Setter for property selected_algos.
     * @param selected_algos New value of property selected_algos.
     */
    public void setSelected_algos(String[] selected_algos) {

        this.selected_algos = selected_algos;
    }

    /**
     * Holds value of property selected_tiers.
     */
    private String[] selected_tiers;

    /**
     * Getter for property selected_tiers.
     * @return Value of property selected_tiers.
     */
    public String[] getSelected_tiers() {

        return this.selected_tiers;
    }

    /**
     * Setter for property selected_tiers.
     * @param selected_tiers New value of property selected_tiers.
     */
    public void setSelected_tiers(String[] selected_tiers) {

        this.selected_tiers = selected_tiers;
    }

    /**
     * Holds value of property selected_branches.
     */
    private String[] selected_branches;

    /**
     * Getter for property selected_branches.
     * @return Value of property selected_branches.
     */
    public String[] getSelected_branches() {

        return this.selected_branches;
    }

    /**
     * Setter for property selected_branches.
     * @param selected_branches New value of property selected_branches.
     */
    public void setSelected_branches(String[] selected_branches) {

        this.selected_branches = selected_branches;
    }
       /**
     * Holds value of property selected_runs.
     */
    private String[] selected_runs;

    /**
     * Getter for property selected_runs.
     * @return Value of property selected_runs.
     */
    public String[] getSelected_runs() {

        return this.selected_runs;
    }

    /**
     * Setter for property selected_runs.
     * @param selected_runs New value of property selected_runs.
     */
    public void setSelected_runs(String[] selected_runs) {

        this.selected_runs = selected_runs;
    }

    /**
     * Holds value of property selected_storageelems.
     */
    private String[] selected_storageelems;

    /**
     * Getter for property selected_storageelems.
     * @return Value of property selected_storageelems.
     */
    public String[] getSelected_storageelems() {

        return this.selected_storageelems;
    }

    /**
     * Setter for property selected_storageelems.
     * @param selected_storageelems New value of property selected_storageelems.
     */
    public void setSelected_storageelems(String[] selected_storageelems) {

        this.selected_storageelems = selected_storageelems;
    }
    
    
    
        /**
     * Holds value of property currentProcDSID.
     */
    private Object currentProcDSID;

    /**
     * Getter for property currentProcDSID.
     * @return Value of property currentProcDSID.
     */
    public Object getCurrentProcDSID() {
        return this.currentProcDSID;
    }

    /**
     * Setter for property currentProcDSID.
     * @param currentProcDSID New value of property currentProcDSID.
     */
    public void setCurrentProcDSID(Object currentProcDSID) {
        this.currentProcDSID = currentProcDSID;
    }

    /**
     * Holds value of property currentFileID.
     */
    private Object currentFileID;

    /**
     * Getter for property currentFileID.
     * @return Value of property currentFileID.
     */
    public Object getCurrentFileID() {
        return this.currentFileID;
    }

    /**
     * Setter for property currentFileID.
     * @param currentFileID New value of property currentFileID.
     */
    public void setCurrentFileID(Object currentFileID) {
        this.currentFileID = currentFileID;
    }
}

