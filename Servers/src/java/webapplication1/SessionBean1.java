/*
 * SessionBean1.java
 *
 * Created on February 15, 2007, 10:51 AM
 * Copyright sekhri
 */
package webapplication1;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.sql.rowset.CachedRowSetXImpl;
import javax.faces.FacesException;

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
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
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
        analysisdsfilelumiRowSet.setDataSourceName("java:comp/env/jdbc/dataSource");
        analysisdsfilelumiRowSet.setCommand("SELECT ALL AnalysisDSFileLumi.ID, \n                    AnalysisDSFileLumi.AnalysisDataset, \n                    AnalysisDSFileLumi.Fileid, \n                    Files.LogicalFileName, \n                    Files.Block, \n                    Files.NumberOfEvents, \n                    Files.FileSize, \n                    Files.CreatedBy, \n                    Files.CreationDate, \n                    Files.LastModifiedBy, \n                    Files.LastModificationDate, \n                    Block.Name, \n                    AnalysisDSFileLumi.Lumi \nFROM AnalysisDSFileLumi\n          INNER JOIN Files ON AnalysisDSFileLumi.Fileid = Files.ID\n          INNER JOIN Block ON Files.Block = Block.ID\nWHERE AnalysisDSFileLumi.AnalysisDataset = ? ");
        analysisdsfilelumiRowSet.setTableName("AnalysisDSFileLumi");
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

    private CachedRowSetXImpl analysisdsfilelumiRowSet = new CachedRowSetXImpl();

    public CachedRowSetXImpl getAnalysisdsfilelumiRowSet() {
        return analysisdsfilelumiRowSet;
    }

    public void setAnalysisdsfilelumiRowSet(CachedRowSetXImpl crsxi) {
        this.analysisdsfilelumiRowSet = crsxi;
    }
    // </editor-fold>
    
    /**
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean1() {
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
        
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
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
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
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

    /**
     * Holds value of property currentAnalysisDSID.
     */
    private String currentAnalysisDSID;

    /**
     * Getter for property currentAnalysisDSID.
     * @return Value of property currentAnalysisDSID.
     */
    public String getCurrentAnalysisDSID() {
        return this.currentAnalysisDSID;
    }

    /**
     * Setter for property currentAnalysisDSID.
     * @param currentAnalysisDSID New value of property currentAnalysisDSID.
     */
    public void setCurrentAnalysisDSID(String currentAnalysisDSID) {
        this.currentAnalysisDSID = currentAnalysisDSID;
    }
}
