/*
 * search.java
 *
 * Created on March 4, 2007, 11:40 AM
 * Copyright ANZAR
 */
package dbssearch;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.Page;
import javax.faces.FacesException;
import javax.faces.component.html.HtmlSelectOneMenu;
import com.sun.jsfcl.data.DefaultSelectItemsArray;
import javax.faces.component.UISelectItems;
import com.sun.data.provider.impl.CachedRowSetDataProvider;
import javax.faces.component.html.HtmlInputText;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.model.DefaultOptionsList;
import javax.faces.convert.BigDecimalConverter;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.model.DefaultTableDataProvider;
import com.sun.rave.web.ui.component.TableColumn;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlDataTable;
import com.sun.jsfcl.data.DefaultTableDataModel;
import javax.faces.component.UIColumn;
import javax.faces.component.html.HtmlOutputText;
import com.sun.jsfcl.data.CachedRowSetDataModel;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import javax.faces.event.ValueChangeEvent;
import dbssearch.Page1;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class search extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        algorithmconfigDataProvider_search.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.algorithmconfigRowSet_search}"));
        datatierDataProvider_search.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.datatierRowSet_search}"));
        primarydatasetDataProvider_saerch.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.primarydatasetRowSet_search}"));
        runsDataProvider_search.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.runsRowSet_search}"));
        storageelementDataProvider_search.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.storageelementRowSet_search}"));
        filebranchDataProvider_search.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.filebranchRowSet_search}"));
        processeddatasetDataProvider_tier.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.processeddatasetRowSet_search}"));
        
    }
    
    private Page page1 = new Page();
    
    public Page getPage1() {
        return page1;
    }
    
    public void setPage1(Page p) {
        this.page1 = p;
    }
    
    private Html html1 = new Html();
    
    public Html getHtml1() {
        return html1;
    }
    
    public void setHtml1(Html h) {
        this.html1 = h;
    }
    
    private Head head1 = new Head();
    
    public Head getHead1() {
        return head1;
    }
    
    public void setHead1(Head h) {
        this.head1 = h;
    }
    
    private Link link1 = new Link();
    
    public Link getLink1() {
        return link1;
    }
    
    public void setLink1(Link l) {
        this.link1 = l;
    }
    
    private Body body1 = new Body();
    
    public Body getBody1() {
        return body1;
    }
    
    public void setBody1(Body b) {
        this.body1 = b;
    }
    
    private Form form1 = new Form();
    
    public Form getForm1() {
        return form1;
    }
    
    public void setForm1(Form f) {
        this.form1 = f;
    }

    private CachedRowSetDataProvider algorithmconfigDataProvider_search = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getAlgorithmconfigDataProvider_search() {
        return algorithmconfigDataProvider_search;
    }

    public void setAlgorithmconfigDataProvider_search(CachedRowSetDataProvider crsdp) {
        this.algorithmconfigDataProvider_search = crsdp;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }

    private Listbox listbox1 = new Listbox();

    public Listbox getListbox1() {
        return listbox1;
    }

    public void setListbox1(Listbox l) {
        this.listbox1 = l;
    }

    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }

    private Listbox listbox2 = new Listbox();

    public Listbox getListbox2() {
        return listbox2;
    }

    public void setListbox2(Listbox l) {
        this.listbox2 = l;
    }

    private CachedRowSetDataProvider datatierDataProvider_search = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getDatatierDataProvider_search() {
        return datatierDataProvider_search;
    }

    public void setDatatierDataProvider_search(CachedRowSetDataProvider crsdp) {
        this.datatierDataProvider_search = crsdp;
    }

    private DropDown dropDown1 = new DropDown();

    public DropDown getDropDown1() {
        return dropDown1;
    }

    public void setDropDown1(DropDown dd) {
        this.dropDown1 = dd;
    }

    private CachedRowSetDataProvider primarydatasetDataProvider_saerch = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getPrimarydatasetDataProvider_saerch() {
        return primarydatasetDataProvider_saerch;
    }

    public void setPrimarydatasetDataProvider_saerch(CachedRowSetDataProvider crsdp) {
        this.primarydatasetDataProvider_saerch = crsdp;
    }

    private Listbox listbox3 = new Listbox();

    public Listbox getListbox3() {
        return listbox3;
    }

    public void setListbox3(Listbox l) {
        this.listbox3 = l;
    }

    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }

    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }

    private CachedRowSetDataProvider runsDataProvider_search = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getRunsDataProvider_search() {
        return runsDataProvider_search;
    }

    public void setRunsDataProvider_search(CachedRowSetDataProvider crsdp) {
        this.runsDataProvider_search = crsdp;
    }

    private Listbox listbox4 = new Listbox();

    public Listbox getListbox4() {
        return listbox4;
    }

    public void setListbox4(Listbox l) {
        this.listbox4 = l;
    }

    private CachedRowSetDataProvider storageelementDataProvider_search = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getStorageelementDataProvider_search() {
        return storageelementDataProvider_search;
    }

    public void setStorageelementDataProvider_search(CachedRowSetDataProvider crsdp) {
        this.storageelementDataProvider_search = crsdp;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }

    private CachedRowSetDataProvider filebranchDataProvider_search = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getFilebranchDataProvider_search() {
        return filebranchDataProvider_search;
    }

    public void setFilebranchDataProvider_search(CachedRowSetDataProvider crsdp) {
        this.filebranchDataProvider_search = crsdp;
    }

    private Button button1 = new Button();

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button b) {
        this.button1 = b;
    }

    private CachedRowSetDataModel dataTable1Model = new CachedRowSetDataModel();

    public CachedRowSetDataModel getDataTable1Model() {
        return dataTable1Model;
    }

    public void setDataTable1Model(CachedRowSetDataModel crsdm) {
        this.dataTable1Model = crsdm;
    }

    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }

    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }

    private CachedRowSetDataProvider processeddatasetDataProvider_tier = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getProcesseddatasetDataProvider_tier() {
        return processeddatasetDataProvider_tier;
    }

    public void setProcesseddatasetDataProvider_tier(CachedRowSetDataProvider crsdp) {
        this.processeddatasetDataProvider_tier = crsdp;
    }

    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }

    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText st) {
        this.staticText12 = st;
    }

    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }

    private StaticText staticText13 = new StaticText();

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText st) {
        this.staticText13 = st;
    }

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private StaticText staticText14 = new StaticText();

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText st) {
        this.staticText14 = st;
    }

    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }

    private StaticText staticText15 = new StaticText();

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText st) {
        this.staticText15 = st;
    }

    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private Hyperlink hyperlink1 = new Hyperlink();

    public Hyperlink getHyperlink1() {
        return hyperlink1;
    }

    public void setHyperlink1(Hyperlink h) {
        this.hyperlink1 = h;
    }

    private Table table2 = new Table();

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table t) {
        this.table2 = t;
    }

    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup trg) {
        this.tableRowGroup2 = trg;
    }

    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }

    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }

    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }

    private TableColumn tableColumn11 = new TableColumn();

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tc) {
        this.tableColumn11 = tc;
    }

    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }

    private Hyperlink hyperlink2 = new Hyperlink();

    public Hyperlink getHyperlink2() {
        return hyperlink2;
    }

    public void setHyperlink2(Hyperlink h) {
        this.hyperlink2 = h;
    }

    private Button button2 = new Button();

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button b) {
        this.button2 = b;
    }

    private BigDecimalConverter listbox1Converter = new BigDecimalConverter();

    public BigDecimalConverter getListbox1Converter() {
        return listbox1Converter;
    }

    public void setListbox1Converter(BigDecimalConverter bdc) {
        this.listbox1Converter = bdc;
    }

    private BigDecimalConverter listbox2Converter = new BigDecimalConverter();

    public BigDecimalConverter getListbox2Converter() {
        return listbox2Converter;
    }

    public void setListbox2Converter(BigDecimalConverter bdc) {
        this.listbox2Converter = bdc;
    }

    private BigDecimalConverter dropDown1Converter = new BigDecimalConverter();

    public BigDecimalConverter getDropDown1Converter() {
        return dropDown1Converter;
    }

    public void setDropDown1Converter(BigDecimalConverter bdc) {
        this.dropDown1Converter = bdc;
    }

    private BigDecimalConverter listbox3Converter = new BigDecimalConverter();

    public BigDecimalConverter getListbox3Converter() {
        return listbox3Converter;
    }

    public void setListbox3Converter(BigDecimalConverter bdc) {
        this.listbox3Converter = bdc;
    }

    private BigDecimalConverter listbox4Converter = new BigDecimalConverter();

    public BigDecimalConverter getListbox4Converter() {
        return listbox4Converter;
    }

    public void setListbox4Converter(BigDecimalConverter bdc) {
        this.listbox4Converter = bdc;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public search() {
    }

    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
    }


    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
    }


    /** 
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     * 
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
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
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

    }

    /** 
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    public void preprocess() {
    }

    /** 
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    //public String sql = "SELECT ALL LUEKING.PROCESSEDDATASET.NAME AS PROCNAME, \nLUEKING.PROCESSEDDATASET.CREATEDBY, \nLUEKING.PROCESSEDDATASET.CREATIONDATE,  \nLUEKING.PROCESSEDDATASET.LASTMODIFIEDBY,  \nLUEKING.PROCESSEDDATASET.LASTMODIFICATIONDATE, \nLUEKING.PRIMARYDATASET.NAME AS PRINAME,  \nLUEKING.PROCDSSTATUS.STATUS AS PRODSTATUS,  \nLUEKING.FILES.LOGICALFILENAME,  \nLUEKING.FILES.CHECKSUM,  \nLUEKING.FILES.NUMBEROFEVENTS, \nLUEKING.FILES.FILESIZE,  \nLUEKING.FILES.VALIDATIONSTATUS, \nLUEKING.BLOCK.NAME AS BLOCKNAME,  \nLUEKING.BLOCK.NUMBEROFFILES,  \nLUEKING.BLOCK.NUMBEROFEVENTS,  \nLUEKING.BLOCK.OPENFORWRITING,  \nLUEKING.DATATIER.NAME AS TIERNAME,  \nLUEKING.BRANCH.NAME AS BRANCHNAME,  \nLUEKING.FILESTATUS.STATUS AS FILESTATUS, \nLUEKING.FILETYPE.TYPE AS FILETYPE,  \nLUEKING.BLOCK.BLOCKSIZE,  \nLUEKING.RUNS.RUNNUMBER,  \nLUEKING.RUNS.NUMBEROFEVENTS,  \nLUEKING.RUNS.NUMBEROFLUMISECTIONS, \nLUEKING.RUNS.TOTALLUMINOSITY,  \nLUEKING.RUNS.STORENUMBER,  \nLUEKING.RUNS.STARTOFRUN,  \nLUEKING.RUNS.ENDOFRUN,  \nLUEKING.STORAGEELEMENT.SENAME \nFROM LUEKING.PROCESSEDDATASET  \nINNER JOIN LUEKING.PRIMARYDATASET ON LUEKING.PROCESSEDDATASET.PRIMARYDATASET = LUEKING.PRIMARYDATASET.ID, LUEKING.PROCDSSTATUS, LUEKING.PROCALGO \nINNER JOIN LUEKING.ALGORITHMCONFIG ON LUEKING.PROCALGO.ALGORITHM = LUEKING.ALGORITHMCONFIG.ID, LUEKING.FILES, LUEKING.BLOCK, LUEKING.PROCDSTIER \nINNER JOIN LUEKING.DATATIER ON LUEKING.PROCDSTIER.DATATIER = LUEKING.DATATIER.ID, LUEKING.FILEBRANCH \nINNER JOIN LUEKING.BRANCH ON LUEKING.FILEBRANCH.BRANCH = LUEKING.BRANCH.ID, LUEKING.FILETIER, LUEKING.FILESTATUS, LUEKING.FILETYPE, LUEKING.PROCDSRUNS \nINNER JOIN LUEKING.RUNS ON LUEKING.PROCDSRUNS.RUN = LUEKING.RUNS.ID, LUEKING.SEBLOCK \nINNER JOIN LUEKING.STORAGEELEMENT ON LUEKING.SEBLOCK.SEID = LUEKING.STORAGEELEMENT.ID \nWHERE LUEKING.PROCESSEDDATASET.STATUS = LUEKING.PROCDSSTATUS.ID \nAND LUEKING.PROCALGO.DATASET = LUEKING.PROCESSEDDATASET.ID \nAND LUEKING.FILES.DATASET = LUEKING.PROCESSEDDATASET.ID \nAND LUEKING.BLOCK.DATASET = LUEKING.PROCESSEDDATASET.ID \nAND LUEKING.FILES.BLOCK = LUEKING.BLOCK.ID \nAND LUEKING.PROCDSTIER.DATASET = LUEKING.PROCESSEDDATASET.ID \nAND LUEKING.FILEBRANCH.FILEID = LUEKING.FILES.ID \nAND LUEKING.FILETIER.FILEID = LUEKING.FILES.ID \nAND LUEKING.FILETIER.DATATIER = LUEKING.DATATIER.ID \nAND LUEKING.FILES.FILESTATUS = LUEKING.FILESTATUS.ID \nAND LUEKING.FILES.FILETYPE = LUEKING.FILETYPE.ID \nAND LUEKING.PROCDSRUNS.DATASET = LUEKING.PROCESSEDDATASET.ID \nAND LUEKING.SEBLOCK.BLOCKID = LUEKING.BLOCK.ID \n" 
    public String sql = "SELECT UNIQUE LUEKING.PROCESSEDDATASET.NAME AS PROCNAME, \n"+ 
                    "LUEKING.PROCESSEDDATASET.CREATEDBY, \n"+  
                    "LUEKING.PROCESSEDDATASET.CREATIONDATE, \n"+  
                    "LUEKING.PROCESSEDDATASET.LASTMODIFIEDBY, \n"+  
                    "LUEKING.PROCESSEDDATASET.LASTMODIFICATIONDATE, \n"+  
                    "LUEKING.PRIMARYDATASET.NAME AS PRINAME, \n"+  
                    "LUEKING.PROCDSSTATUS.STATUS AS PRODSTATUS, \n"+  
                    "LUEKING.BLOCK.NAME AS BLOCKNAME,  \n"+ 
                    "LUEKING.BLOCK.NUMBEROFFILES,  \n"+ 
                    "LUEKING.BLOCK.NUMBEROFEVENTS,  \n"+ 
                    "LUEKING.BLOCK.OPENFORWRITING,  \n"+ 
                    //"LUEKING.DATATIER.NAME AS TIERNAME,  \n"+ 
                    "LUEKING.BLOCK.BLOCKSIZE  \n"+ 
                    //"LUEKING.RUNS.RUNNUMBER,  \n"+ 
                    //"LUEKING.RUNS.NUMBEROFEVENTS,  \n"+ 
                    //"LUEKING.RUNS.NUMBEROFLUMISECTIONS,  \n"+ 
                    //"LUEKING.RUNS.TOTALLUMINOSITY,  \n"+ 
                    //"LUEKING.RUNS.STORENUMBER,  \n"+ 
                    //"LUEKING.RUNS.STARTOFRUN,  \n"+ 
                    //"LUEKING.RUNS.ENDOFRUN,  \n"+ 
                    //"LUEKING.STORAGEELEMENT.SENAME \n"+  
"FROM LUEKING.PROCESSEDDATASET  \n"+ 
         "INNER JOIN LUEKING.PRIMARYDATASET ON LUEKING.PROCESSEDDATASET.PRIMARYDATASET = LUEKING.PRIMARYDATASET.ID, LUEKING.PROCDSSTATUS, LUEKING.PROCALGO \n"+ 
          "INNER JOIN LUEKING.ALGORITHMCONFIG ON LUEKING.PROCALGO.ALGORITHM = LUEKING.ALGORITHMCONFIG.ID, LUEKING.BLOCK, LUEKING.PROCDSTIER \n"+ 
          "INNER JOIN LUEKING.DATATIER ON LUEKING.PROCDSTIER.DATATIER = LUEKING.DATATIER.ID, LUEKING.PROCDSRUNS \n"+ 
          "INNER JOIN LUEKING.RUNS ON LUEKING.PROCDSRUNS.RUN = LUEKING.RUNS.ID, LUEKING.SEBLOCK \n"+ 
          "INNER JOIN LUEKING.STORAGEELEMENT ON LUEKING.SEBLOCK.SEID = LUEKING.STORAGEELEMENT.ID \n"+ 
"WHERE LUEKING.PROCESSEDDATASET.STATUS = LUEKING.PROCDSSTATUS.ID \n"+ 
          "AND LUEKING.PROCALGO.DATASET = LUEKING.PROCESSEDDATASET.ID \n"+ 
          "AND LUEKING.BLOCK.DATASET = LUEKING.PROCESSEDDATASET.ID \n"+ 
          "AND LUEKING.PROCDSTIER.DATASET = LUEKING.PROCESSEDDATASET.ID \n"+ 
          "AND LUEKING.PROCDSRUNS.DATASET = LUEKING.PROCESSEDDATASET.ID \n"+ 
          "AND LUEKING.SEBLOCK.BLOCKID = LUEKING.BLOCK.ID \n";
            
                    ;
     public void prerender() {
        try {
            //String sql = getSessionBean1().getProcesseddatasetRowSet().getCommand();
            if (getSessionBean1().getItems_selected() == null ) {
               
                sql += "\n AND LUEKING.PRIMARYDATASET.ID = -1";
                log("What is : getItems_selected"+getSessionBean1().getItems_selected());
                /*//Primary
                getSessionBean1().getProcesseddatasetRowSet().setObject(1, "-1");
                //AlgoConfig
                getSessionBean1().getProcesseddatasetRowSet().setObject(2, "-1");
                //Tier
                getSessionBean1().getProcesseddatasetRowSet().setObject(3, "-1");
                //Branch
                getSessionBean1().getProcesseddatasetRowSet().setObject(4, "-1");
                //Runs
                getSessionBean1().getProcesseddatasetRowSet().setObject(5, "-1");
                //Storage Element
                getSessionBean1().getProcesseddatasetRowSet().setObject(6, "-1");*/
                log("Generated SQL:" + sql);
                //Run the Query
                getSessionBean1().getProcesseddatasetRowSet_search().setCommand(sql);
                
                //Update the results
                processeddatasetDataProvider_tier.refresh();
                //Reset the sql to original
                //getSessionBean1().getProcesseddatasetRowSet().setCommand(sql);
            }
            else {
                
                //log(" SQL B4:" + sql);
                //String nsql=sql;
                //Selected Primary Dataset
                if (dropDown1.getSelected() != null || dropDown1.getSelected() != "0" ) {
                    //String selected_primary = (String)dropDown1.getSelected().toString();
                    sql += "\n AND LUEKING.PRIMARYDATASET.ID = "+(String)dropDown1.getSelected().toString()+"\n";
                }
                //Selected Algos
                for (int i=0; i!=getSessionBean1().getSelected_algos().length; ++i){
                    if (i==0) sql += " AND LUEKING.ALGORITHMCONFIG.ID IN ("+ getSessionBean1().getSelected_algos()[i];
                    else sql += ", "+getSessionBean1().getSelected_algos()[i];
                }
                if (getSessionBean1().getSelected_algos().length > 0 ) sql += ")";
                //Selected Tiers
               for (int i=0; i!=getSessionBean1().getSelected_tiers().length; ++i){
                    if (i==0) sql += " AND LUEKING.DATATIER.ID IN ("+ getSessionBean1().getSelected_tiers()[i];
                    else sql += ", "+getSessionBean1().getSelected_tiers()[i];
                }
                if (getSessionBean1().getSelected_tiers().length > 0 ) sql += ")";
                //Selected Runs
                for (int i=0; i!=getSessionBean1().getSelected_runs().length; ++i){
                    if (i==0) sql += " AND LUEKING.RUNS.ID IN ("+ getSessionBean1().getSelected_runs()[i];
                    else sql += ", "+getSessionBean1().getSelected_runs()[i];
                }
                if (getSessionBean1().getSelected_runs().length > 0 ) sql += ")";
                //Selected Branches
                //for (int i=0; i!=getSessionBean1().getSelected_branches().length; ++i){
                //    if (i==0) sql += " AND LUEKING.BRANCH.ID IN ("+ getSessionBean1().getSelected_branches()[i];
                //    else sql += ", "+getSessionBean1().getSelected_branches()[i];
                //}
                //if (getSessionBean1().getSelected_branches().length > 0 ) sql += ")";
                 //Selected Storage Elements
                for (int i=0; i!=getSessionBean1().getSelected_storageelems().length; ++i){
                    if (i==0) sql += " AND LUEKING.STORAGEELEMENT.ID IN ("+ getSessionBean1().getSelected_storageelems()[i];
                    else sql += ", "+getSessionBean1().getSelected_storageelems()[i];
                }
                if (getSessionBean1().getSelected_storageelems().length > 0 ) sql += ")";
               
                log("Generated SQL2:" + sql);
                //Run the Query
                getSessionBean1().getProcesseddatasetRowSet_search().setCommand(sql);
                //Update the results
                processeddatasetDataProvider_tier.refresh();
                
                //reset sql
                //getSessionBean1().getProcesseddatasetRowSet().setCommand(sql);
                
                log("OTHERWISE");
            }
        } catch (Exception e) {
            log("Exception Raised" + e.getMessage());
      } 
    }

    /** 
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
     */
    public void destroy() {
        processeddatasetDataProvider_tier.close();
        filebranchDataProvider_search.close();
        storageelementDataProvider_search.close();
        runsDataProvider_search.close();
        primarydatasetDataProvider_saerch.close();
        datatierDataProvider_search.close();
        algorithmconfigDataProvider_search.close();
    }



    public String button1_action() {
        // TODO: Replace with your code
            getSessionBean1().setSelected_algos(listbox1.getValueAsStringArray(this.getContext()));
            getSessionBean1().setSelected_tiers(listbox2.getValueAsStringArray(this.getContext()));
            getSessionBean1().setSelected_runs(listbox3.getValueAsStringArray(this.getContext()));
            getSessionBean1().setSelected_storageelems(listbox4.getValueAsStringArray(this.getContext()));
            //getSessionBean1().setSelected_branches(listbox5.getValueAsStringArray(this.getContext()));
            
            //String selected_primary = (String)dropDown1.getSelected().toString();
            //log("Selected Primary is"+selected_primary);
            
            if (    dropDown1.getSelected() != null ||
                    getSessionBean1().getSelected_algos().length !=0 || 
                    getSessionBean1().getSelected_tiers().length !=0 ||
                    getSessionBean1().getSelected_runs().length != 0 ||
                    getSessionBean1().getSelected_storageelems().length !=0                     
                    //getSessionBean1().getSelected_branches().length !=0
                    ) {
                getSessionBean1().setItems_selected("1");
            }
        return null;
    }

    public void dropDown1_processValueChange(ValueChangeEvent event) {
        // TODO: Replace with your code
        
    }

    public String button2_action() {
        // TODO: Replace with your code
        //getSessionBean1().getPrimarydatasetRowSet().first();
        primarydatasetDataProvider_saerch.cursorFirst();
        primarydatasetDataProvider_saerch.refresh();
        getSessionBean1().setItems_selected("0");
        log("\n\nREFRESH CALLED");
        
        return null;
    }

    public String hyperlink1_action() {
        // TODO: Replace with your code
        try {
            String primaryid = (String)hyperlink1.getValue();
            getSessionBean1().getProcesseddatasetRowSet().setObject(
                    1, primaryid);
            //dbssearch.Page1.processeddatasetDataProvider.refresh();
        }catch (Exception e) {
            log("Exception Raised:" + e.getMessage());
    }
        return "case1";
    }

   
}

