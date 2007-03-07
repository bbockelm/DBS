/*
 * Page1.java
 *
 * Created on February 15, 2007, 10:51 AM
 * Copyright sekhri
 */
package dbssearch;

import com.sun.data.provider.impl.CachedRowSetDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Calendar;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.ImageHyperlink;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.PageSeparator;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.PanelLayout;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Tab;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.model.DefaultOptionsList;
import com.sun.rave.web.ui.model.DefaultTableDataProvider;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import javax.faces.FacesException;
import javax.faces.convert.BigDecimalConverter;
import javax.faces.event.ValueChangeEvent;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Page1 extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        primarydatasetDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.primarydatasetRowSet}"));
        processeddatasetDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.processeddatasetRowSet}"));
        filesDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.filesRowSet}"));
        algorithmconfigDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.algorithmconfigRowSet}"));
        blockDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.blockRowSet}"));
        analysisdatasetDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.analysisdatasetRowSet}"));
        datatierDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.datatierRowSet}"));
        runsDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.runsRowSet}"));
        filesParentDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.filesParentRowSet}"));
        filetierDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.filetierRowSet}"));
        filebranchDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.filebranchRowSet}"));
        lumisectionDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.lumisectionRowSet}"));
        filealgoDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.filealgoRowSet}"));
        processedDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.processedRowSet}"));
        fileInfoDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.fileInfoRowSet}"));
        procdsparentDataProvider.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.procdsparentRowSet}"));
        algorithmconfigDataProvider1.setCachedRowSet((javax.sql.rowset.CachedRowSet)getValue("#{SessionBean1.algorithmconfigRowSet1}"));
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
    
    private CachedRowSetDataProvider primarydatasetDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getPrimarydatasetDataProvider() {
        return primarydatasetDataProvider;
    }
    
    public void setPrimarydatasetDataProvider(CachedRowSetDataProvider crsdp) {
        this.primarydatasetDataProvider = crsdp;
    }
    
    private DropDown primaryName = new DropDown();
    
    public DropDown getPrimaryName() {
        return primaryName;
    }
    
    public void setPrimaryName(DropDown dd) {
        this.primaryName = dd;
    }
    
    private CachedRowSetDataProvider processeddatasetDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getProcesseddatasetDataProvider() {
        return processeddatasetDataProvider;
    }
    
    public void setProcesseddatasetDataProvider(CachedRowSetDataProvider crsdp) {
        this.processeddatasetDataProvider = crsdp;
    }
    
    private TabSet tabSet1 = new TabSet();
    
    public TabSet getTabSet1() {
        return tabSet1;
    }
    
    public void setTabSet1(TabSet ts) {
        this.tabSet1 = ts;
    }
    
    private Tab tab1 = new Tab();
    
    public Tab getTab1() {
        return tab1;
    }
    
    public void setTab1(Tab t) {
        this.tab1 = t;
    }
    
    private PanelLayout layoutPanel1 = new PanelLayout();
    
    public PanelLayout getLayoutPanel1() {
        return layoutPanel1;
    }
    
    public void setLayoutPanel1(PanelLayout pl) {
        this.layoutPanel1 = pl;
    }
    
    private Tab tab2 = new Tab();
    
    public Tab getTab2() {
        return tab2;
    }
    
    public void setTab2(Tab t) {
        this.tab2 = t;
    }
    
    private PanelLayout layoutPanel2 = new PanelLayout();
    
    public PanelLayout getLayoutPanel2() {
        return layoutPanel2;
    }
    
    public void setLayoutPanel2(PanelLayout pl) {
        this.layoutPanel2 = pl;
    }
    
    private Tab tab3 = new Tab();
    
    public Tab getTab3() {
        return tab3;
    }
    
    public void setTab3(Tab t) {
        this.tab3 = t;
    }
    
    private PanelLayout layoutPanel3 = new PanelLayout();
    
    public PanelLayout getLayoutPanel3() {
        return layoutPanel3;
    }
    
    public void setLayoutPanel3(PanelLayout pl) {
        this.layoutPanel3 = pl;
    }
    
    private PageSeparator pageSeparator1 = new PageSeparator();
    
    public PageSeparator getPageSeparator1() {
        return pageSeparator1;
    }
    
    public void setPageSeparator1(PageSeparator ps) {
        this.pageSeparator1 = ps;
    }
    
    private Table table3 = new Table();
    
    public Table getTable3() {
        return table3;
    }
    
    public void setTable3(Table t) {
        this.table3 = t;
    }
    
    private TableRowGroup tableRowGroup3 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup3() {
        return tableRowGroup3;
    }
    
    public void setTableRowGroup3(TableRowGroup trg) {
        this.tableRowGroup3 = trg;
    }
    
    private CachedRowSetDataProvider filesDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getFilesDataProvider() {
        return filesDataProvider;
    }
    
    public void setFilesDataProvider(CachedRowSetDataProvider crsdp) {
        this.filesDataProvider = crsdp;
    }
    
    private Label label2 = new Label();
    
    public Label getLabel2() {
        return label2;
    }
    
    public void setLabel2(Label l) {
        this.label2 = l;
    }
    
    private CachedRowSetDataProvider algorithmconfigDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getAlgorithmconfigDataProvider() {
        return algorithmconfigDataProvider;
    }
    
    public void setAlgorithmconfigDataProvider(CachedRowSetDataProvider crsdp) {
        this.algorithmconfigDataProvider = crsdp;
    }
    
    private CachedRowSetDataProvider blockDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getBlockDataProvider() {
        return blockDataProvider;
    }
    
    public void setBlockDataProvider(CachedRowSetDataProvider crsdp) {
        this.blockDataProvider = crsdp;
    }
    
    private TableColumn tableColumn17 = new TableColumn();
    
    public TableColumn getTableColumn17() {
        return tableColumn17;
    }
    
    public void setTableColumn17(TableColumn tc) {
        this.tableColumn17 = tc;
    }
    
    private StaticText staticText16 = new StaticText();
    
    public StaticText getStaticText16() {
        return staticText16;
    }
    
    public void setStaticText16(StaticText st) {
        this.staticText16 = st;
    }
    
    private TableColumn tableColumn30 = new TableColumn();
    
    public TableColumn getTableColumn30() {
        return tableColumn30;
    }
    
    public void setTableColumn30(TableColumn tc) {
        this.tableColumn30 = tc;
    }
    
    private StaticText staticText29 = new StaticText();
    
    public StaticText getStaticText29() {
        return staticText29;
    }
    
    public void setStaticText29(StaticText st) {
        this.staticText29 = st;
    }
    
    private TableColumn tableColumn31 = new TableColumn();
    
    public TableColumn getTableColumn31() {
        return tableColumn31;
    }
    
    public void setTableColumn31(TableColumn tc) {
        this.tableColumn31 = tc;
    }
    
    private StaticText staticText30 = new StaticText();
    
    public StaticText getStaticText30() {
        return staticText30;
    }
    
    public void setStaticText30(StaticText st) {
        this.staticText30 = st;
    }
    
    private TableColumn tableColumn32 = new TableColumn();
    
    public TableColumn getTableColumn32() {
        return tableColumn32;
    }
    
    public void setTableColumn32(TableColumn tc) {
        this.tableColumn32 = tc;
    }
    
    private StaticText staticText31 = new StaticText();
    
    public StaticText getStaticText31() {
        return staticText31;
    }
    
    public void setStaticText31(StaticText st) {
        this.staticText31 = st;
    }
    
    private TableColumn tableColumn33 = new TableColumn();
    
    public TableColumn getTableColumn33() {
        return tableColumn33;
    }
    
    public void setTableColumn33(TableColumn tc) {
        this.tableColumn33 = tc;
    }
    
    private StaticText staticText32 = new StaticText();
    
    public StaticText getStaticText32() {
        return staticText32;
    }
    
    public void setStaticText32(StaticText st) {
        this.staticText32 = st;
    }
    
    private TableColumn tableColumn34 = new TableColumn();
    
    public TableColumn getTableColumn34() {
        return tableColumn34;
    }
    
    public void setTableColumn34(TableColumn tc) {
        this.tableColumn34 = tc;
    }
    
    private StaticText staticText33 = new StaticText();
    
    public StaticText getStaticText33() {
        return staticText33;
    }
    
    public void setStaticText33(StaticText st) {
        this.staticText33 = st;
    }
    
    private TableColumn tableColumn35 = new TableColumn();
    
    public TableColumn getTableColumn35() {
        return tableColumn35;
    }
    
    public void setTableColumn35(TableColumn tc) {
        this.tableColumn35 = tc;
    }
    
    private StaticText staticText34 = new StaticText();
    
    public StaticText getStaticText34() {
        return staticText34;
    }
    
    public void setStaticText34(StaticText st) {
        this.staticText34 = st;
    }
    
    private TableColumn tableColumn36 = new TableColumn();
    
    public TableColumn getTableColumn36() {
        return tableColumn36;
    }
    
    public void setTableColumn36(TableColumn tc) {
        this.tableColumn36 = tc;
    }
    
    private StaticText staticText35 = new StaticText();
    
    public StaticText getStaticText35() {
        return staticText35;
    }
    
    public void setStaticText35(StaticText st) {
        this.staticText35 = st;
    }
    
    private TableColumn tableColumn37 = new TableColumn();
    
    public TableColumn getTableColumn37() {
        return tableColumn37;
    }
    
    public void setTableColumn37(TableColumn tc) {
        this.tableColumn37 = tc;
    }
    
    private StaticText staticText36 = new StaticText();
    
    public StaticText getStaticText36() {
        return staticText36;
    }
    
    public void setStaticText36(StaticText st) {
        this.staticText36 = st;
    }
    
    private TableColumn tableColumn39 = new TableColumn();
    
    public TableColumn getTableColumn39() {
        return tableColumn39;
    }
    
    public void setTableColumn39(TableColumn tc) {
        this.tableColumn39 = tc;
    }
    
    private StaticText staticText37 = new StaticText();
    
    public StaticText getStaticText37() {
        return staticText37;
    }
    
    public void setStaticText37(StaticText st) {
        this.staticText37 = st;
    }
    
    private TabSet tabSet2 = new TabSet();
    
    public TabSet getTabSet2() {
        return tabSet2;
    }
    
    public void setTabSet2(TabSet ts) {
        this.tabSet2 = ts;
    }
    
    private Tab tab4 = new Tab();
    
    public Tab getTab4() {
        return tab4;
    }
    
    public void setTab4(Tab t) {
        this.tab4 = t;
    }
    
    private PanelLayout layoutPanel4 = new PanelLayout();
    
    public PanelLayout getLayoutPanel4() {
        return layoutPanel4;
    }
    
    public void setLayoutPanel4(PanelLayout pl) {
        this.layoutPanel4 = pl;
    }
    
    private Tab tab5 = new Tab();
    
    public Tab getTab5() {
        return tab5;
    }
    
    public void setTab5(Tab t) {
        this.tab5 = t;
    }
    
    private PanelLayout layoutPanel5 = new PanelLayout();
    
    public PanelLayout getLayoutPanel5() {
        return layoutPanel5;
    }
    
    public void setLayoutPanel5(PanelLayout pl) {
        this.layoutPanel5 = pl;
    }
    
    private Tab tab6 = new Tab();
    
    public Tab getTab6() {
        return tab6;
    }
    
    public void setTab6(Tab t) {
        this.tab6 = t;
    }
    
    private PanelLayout layoutPanel6 = new PanelLayout();
    
    public PanelLayout getLayoutPanel6() {
        return layoutPanel6;
    }
    
    public void setLayoutPanel6(PanelLayout pl) {
        this.layoutPanel6 = pl;
    }
    
    private Tab tab7 = new Tab();
    
    public Tab getTab7() {
        return tab7;
    }
    
    public void setTab7(Tab t) {
        this.tab7 = t;
    }
    
    private PanelLayout layoutPanel7 = new PanelLayout();
    
    public PanelLayout getLayoutPanel7() {
        return layoutPanel7;
    }
    
    public void setLayoutPanel7(PanelLayout pl) {
        this.layoutPanel7 = pl;
    }
    
    private Tab tab8 = new Tab();
    
    public Tab getTab8() {
        return tab8;
    }
    
    public void setTab8(Tab t) {
        this.tab8 = t;
    }
    
    private PanelLayout layoutPanel8 = new PanelLayout();
    
    public PanelLayout getLayoutPanel8() {
        return layoutPanel8;
    }
    
    public void setLayoutPanel8(PanelLayout pl) {
        this.layoutPanel8 = pl;
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
    
    private TableColumn tableColumn18 = new TableColumn();
    
    public TableColumn getTableColumn18() {
        return tableColumn18;
    }
    
    public void setTableColumn18(TableColumn tc) {
        this.tableColumn18 = tc;
    }
    
    private TableColumn tableColumn38 = new TableColumn();
    
    public TableColumn getTableColumn38() {
        return tableColumn38;
    }
    
    public void setTableColumn38(TableColumn tc) {
        this.tableColumn38 = tc;
    }
    
    private StaticText staticText3 = new StaticText();
    
    public StaticText getStaticText3() {
        return staticText3;
    }
    
    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    
    private TableColumn tableColumn40 = new TableColumn();
    
    public TableColumn getTableColumn40() {
        return tableColumn40;
    }
    
    public void setTableColumn40(TableColumn tc) {
        this.tableColumn40 = tc;
    }
    
    private StaticText staticText4 = new StaticText();
    
    public StaticText getStaticText4() {
        return staticText4;
    }
    
    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    
    private TableColumn tableColumn41 = new TableColumn();
    
    public TableColumn getTableColumn41() {
        return tableColumn41;
    }
    
    public void setTableColumn41(TableColumn tc) {
        this.tableColumn41 = tc;
    }
    
    private StaticText staticText15 = new StaticText();
    
    public StaticText getStaticText15() {
        return staticText15;
    }
    
    public void setStaticText15(StaticText st) {
        this.staticText15 = st;
    }
    
    private TableColumn tableColumn45 = new TableColumn();
    
    public TableColumn getTableColumn45() {
        return tableColumn45;
    }
    
    public void setTableColumn45(TableColumn tc) {
        this.tableColumn45 = tc;
    }
    
    private StaticText staticText40 = new StaticText();
    
    public StaticText getStaticText40() {
        return staticText40;
    }
    
    public void setStaticText40(StaticText st) {
        this.staticText40 = st;
    }
    
    private TableColumn tableColumn47 = new TableColumn();
    
    public TableColumn getTableColumn47() {
        return tableColumn47;
    }
    
    public void setTableColumn47(TableColumn tc) {
        this.tableColumn47 = tc;
    }
    
    private StaticText staticText42 = new StaticText();
    
    public StaticText getStaticText42() {
        return staticText42;
    }
    
    public void setStaticText42(StaticText st) {
        this.staticText42 = st;
    }
    
    private TableColumn tableColumn48 = new TableColumn();
    
    public TableColumn getTableColumn48() {
        return tableColumn48;
    }
    
    public void setTableColumn48(TableColumn tc) {
        this.tableColumn48 = tc;
    }
    
    private StaticText staticText43 = new StaticText();
    
    public StaticText getStaticText43() {
        return staticText43;
    }
    
    public void setStaticText43(StaticText st) {
        this.staticText43 = st;
    }
    
    private TabSet tabSet3 = new TabSet();
    
    public TabSet getTabSet3() {
        return tabSet3;
    }
    
    public void setTabSet3(TabSet ts) {
        this.tabSet3 = ts;
    }
    
    private Tab tab9 = new Tab();
    
    public Tab getTab9() {
        return tab9;
    }
    
    public void setTab9(Tab t) {
        this.tab9 = t;
    }
    
    private PanelLayout layoutPanel9 = new PanelLayout();
    
    public PanelLayout getLayoutPanel9() {
        return layoutPanel9;
    }
    
    public void setLayoutPanel9(PanelLayout pl) {
        this.layoutPanel9 = pl;
    }
    
    private Tab tab10 = new Tab();
    
    public Tab getTab10() {
        return tab10;
    }
    
    public void setTab10(Tab t) {
        this.tab10 = t;
    }
    
    private PanelLayout layoutPanel10 = new PanelLayout();
    
    public PanelLayout getLayoutPanel10() {
        return layoutPanel10;
    }
    
    public void setLayoutPanel10(PanelLayout pl) {
        this.layoutPanel10 = pl;
    }
    
    private Tab tab11 = new Tab();
    
    public Tab getTab11() {
        return tab11;
    }
    
    public void setTab11(Tab t) {
        this.tab11 = t;
    }
    
    private PanelLayout layoutPanel11 = new PanelLayout();
    
    public PanelLayout getLayoutPanel11() {
        return layoutPanel11;
    }
    
    public void setLayoutPanel11(PanelLayout pl) {
        this.layoutPanel11 = pl;
    }
    
    private Tab tab12 = new Tab();
    
    public Tab getTab12() {
        return tab12;
    }
    
    public void setTab12(Tab t) {
        this.tab12 = t;
    }
    
    private PanelLayout layoutPanel12 = new PanelLayout();
    
    public PanelLayout getLayoutPanel12() {
        return layoutPanel12;
    }
    
    public void setLayoutPanel12(PanelLayout pl) {
        this.layoutPanel12 = pl;
    }
    
    private Tab tab13 = new Tab();
    
    public Tab getTab13() {
        return tab13;
    }
    
    public void setTab13(Tab t) {
        this.tab13 = t;
    }
    
    private PanelLayout layoutPanel13 = new PanelLayout();
    
    public PanelLayout getLayoutPanel13() {
        return layoutPanel13;
    }
    
    public void setLayoutPanel13(PanelLayout pl) {
        this.layoutPanel13 = pl;
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
    
    private TableColumn tableColumn1 = new TableColumn();
    
    public TableColumn getTableColumn1() {
        return tableColumn1;
    }
    
    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    
    private TableColumn tableColumn2 = new TableColumn();
    
    public TableColumn getTableColumn2() {
        return tableColumn2;
    }
    
    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }
    
    private StaticText staticText2 = new StaticText();
    
    public StaticText getStaticText2() {
        return staticText2;
    }
    
    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    
    private TableColumn tableColumn7 = new TableColumn();
    
    public TableColumn getTableColumn7() {
        return tableColumn7;
    }
    
    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }
    
    private StaticText staticText9 = new StaticText();
    
    public StaticText getStaticText9() {
        return staticText9;
    }
    
    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }
    
    private TableColumn tableColumn9 = new TableColumn();
    
    public TableColumn getTableColumn9() {
        return tableColumn9;
    }
    
    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }
    
    private StaticText staticText11 = new StaticText();
    
    public StaticText getStaticText11() {
        return staticText11;
    }
    
    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }
    
    private TableColumn tableColumn11 = new TableColumn();
    
    public TableColumn getTableColumn11() {
        return tableColumn11;
    }
    
    public void setTableColumn11(TableColumn tc) {
        this.tableColumn11 = tc;
    }
    
    private StaticText staticText13 = new StaticText();
    
    public StaticText getStaticText13() {
        return staticText13;
    }
    
    public void setStaticText13(StaticText st) {
        this.staticText13 = st;
    }
    
    private Hyperlink hyperlink1 = new Hyperlink();
    
    public Hyperlink getHyperlink1() {
        return hyperlink1;
    }
    
    public void setHyperlink1(Hyperlink h) {
        this.hyperlink1 = h;
    }
    
    private Table table4 = new Table();
    
    public Table getTable4() {
        return table4;
    }
    
    public void setTable4(Table t) {
        this.table4 = t;
    }
    
    private TableRowGroup tableRowGroup4 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup4() {
        return tableRowGroup4;
    }
    
    public void setTableRowGroup4(TableRowGroup trg) {
        this.tableRowGroup4 = trg;
    }
    
    private CachedRowSetDataProvider analysisdatasetDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getAnalysisdatasetDataProvider() {
        return analysisdatasetDataProvider;
    }
    
    public void setAnalysisdatasetDataProvider(CachedRowSetDataProvider crsdp) {
        this.analysisdatasetDataProvider = crsdp;
    }
    
    private TableColumn tableColumn24 = new TableColumn();
    
    public TableColumn getTableColumn24() {
        return tableColumn24;
    }
    
    public void setTableColumn24(TableColumn tc) {
        this.tableColumn24 = tc;
    }
    
    private StaticText staticText26 = new StaticText();
    
    public StaticText getStaticText26() {
        return staticText26;
    }
    
    public void setStaticText26(StaticText st) {
        this.staticText26 = st;
    }
    
    private TableColumn tableColumn25 = new TableColumn();
    
    public TableColumn getTableColumn25() {
        return tableColumn25;
    }
    
    public void setTableColumn25(TableColumn tc) {
        this.tableColumn25 = tc;
    }
    
    private StaticText staticText27 = new StaticText();
    
    public StaticText getStaticText27() {
        return staticText27;
    }
    
    public void setStaticText27(StaticText st) {
        this.staticText27 = st;
    }
    
    private TableColumn tableColumn27 = new TableColumn();
    
    public TableColumn getTableColumn27() {
        return tableColumn27;
    }
    
    public void setTableColumn27(TableColumn tc) {
        this.tableColumn27 = tc;
    }
    
    private StaticText staticText45 = new StaticText();
    
    public StaticText getStaticText45() {
        return staticText45;
    }
    
    public void setStaticText45(StaticText st) {
        this.staticText45 = st;
    }
    
    private TableColumn tableColumn28 = new TableColumn();
    
    public TableColumn getTableColumn28() {
        return tableColumn28;
    }
    
    public void setTableColumn28(TableColumn tc) {
        this.tableColumn28 = tc;
    }
    
    private StaticText staticText46 = new StaticText();
    
    public StaticText getStaticText46() {
        return staticText46;
    }
    
    public void setStaticText46(StaticText st) {
        this.staticText46 = st;
    }
    
    private TableColumn tableColumn29 = new TableColumn();
    
    public TableColumn getTableColumn29() {
        return tableColumn29;
    }
    
    public void setTableColumn29(TableColumn tc) {
        this.tableColumn29 = tc;
    }
    
    private StaticText staticText47 = new StaticText();
    
    public StaticText getStaticText47() {
        return staticText47;
    }
    
    public void setStaticText47(StaticText st) {
        this.staticText47 = st;
    }
    
    private TableColumn tableColumn51 = new TableColumn();
    
    public TableColumn getTableColumn51() {
        return tableColumn51;
    }
    
    public void setTableColumn51(TableColumn tc) {
        this.tableColumn51 = tc;
    }
    
    private StaticText staticText49 = new StaticText();
    
    public StaticText getStaticText49() {
        return staticText49;
    }
    
    public void setStaticText49(StaticText st) {
        this.staticText49 = st;
    }
    
    private TableColumn tableColumn53 = new TableColumn();
    
    public TableColumn getTableColumn53() {
        return tableColumn53;
    }
    
    public void setTableColumn53(TableColumn tc) {
        this.tableColumn53 = tc;
    }
    
    private StaticText staticText51 = new StaticText();
    
    public StaticText getStaticText51() {
        return staticText51;
    }
    
    public void setStaticText51(StaticText st) {
        this.staticText51 = st;
    }
    
    private TableColumn tableColumn55 = new TableColumn();
    
    public TableColumn getTableColumn55() {
        return tableColumn55;
    }
    
    public void setTableColumn55(TableColumn tc) {
        this.tableColumn55 = tc;
    }
    
    private StaticText staticText53 = new StaticText();
    
    public StaticText getStaticText53() {
        return staticText53;
    }
    
    public void setStaticText53(StaticText st) {
        this.staticText53 = st;
    }
    
    private Table table5 = new Table();
    
    public Table getTable5() {
        return table5;
    }
    
    public void setTable5(Table t) {
        this.table5 = t;
    }
    
    private TableRowGroup tableRowGroup5 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup5() {
        return tableRowGroup5;
    }
    
    public void setTableRowGroup5(TableRowGroup trg) {
        this.tableRowGroup5 = trg;
    }
    
    private CachedRowSetDataProvider datatierDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getDatatierDataProvider() {
        return datatierDataProvider;
    }
    
    public void setDatatierDataProvider(CachedRowSetDataProvider crsdp) {
        this.datatierDataProvider = crsdp;
    }
    
    private TableColumn tableColumn26 = new TableColumn();
    
    public TableColumn getTableColumn26() {
        return tableColumn26;
    }
    
    public void setTableColumn26(TableColumn tc) {
        this.tableColumn26 = tc;
    }
    
    private StaticText staticText28 = new StaticText();
    
    public StaticText getStaticText28() {
        return staticText28;
    }
    
    public void setStaticText28(StaticText st) {
        this.staticText28 = st;
    }
    
    private TableColumn tableColumn56 = new TableColumn();
    
    public TableColumn getTableColumn56() {
        return tableColumn56;
    }
    
    public void setTableColumn56(TableColumn tc) {
        this.tableColumn56 = tc;
    }
    
    private StaticText staticText54 = new StaticText();
    
    public StaticText getStaticText54() {
        return staticText54;
    }
    
    public void setStaticText54(StaticText st) {
        this.staticText54 = st;
    }
    
    private TableColumn tableColumn57 = new TableColumn();
    
    public TableColumn getTableColumn57() {
        return tableColumn57;
    }
    
    public void setTableColumn57(TableColumn tc) {
        this.tableColumn57 = tc;
    }
    
    private StaticText staticText55 = new StaticText();
    
    public StaticText getStaticText55() {
        return staticText55;
    }
    
    public void setStaticText55(StaticText st) {
        this.staticText55 = st;
    }
    
    private Table table6 = new Table();
    
    public Table getTable6() {
        return table6;
    }
    
    public void setTable6(Table t) {
        this.table6 = t;
    }
    
    private TableRowGroup tableRowGroup6 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup6() {
        return tableRowGroup6;
    }
    
    public void setTableRowGroup6(TableRowGroup trg) {
        this.tableRowGroup6 = trg;
    }
    
    private CachedRowSetDataProvider runsDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getRunsDataProvider() {
        return runsDataProvider;
    }
    
    public void setRunsDataProvider(CachedRowSetDataProvider crsdp) {
        this.runsDataProvider = crsdp;
    }
    
    private TableColumn tableColumn59 = new TableColumn();
    
    public TableColumn getTableColumn59() {
        return tableColumn59;
    }
    
    public void setTableColumn59(TableColumn tc) {
        this.tableColumn59 = tc;
    }
    
    private StaticText staticText57 = new StaticText();
    
    public StaticText getStaticText57() {
        return staticText57;
    }
    
    public void setStaticText57(StaticText st) {
        this.staticText57 = st;
    }
    
    private TableColumn tableColumn60 = new TableColumn();
    
    public TableColumn getTableColumn60() {
        return tableColumn60;
    }
    
    public void setTableColumn60(TableColumn tc) {
        this.tableColumn60 = tc;
    }
    
    private StaticText staticText58 = new StaticText();
    
    public StaticText getStaticText58() {
        return staticText58;
    }
    
    public void setStaticText58(StaticText st) {
        this.staticText58 = st;
    }
    
    private TableColumn tableColumn61 = new TableColumn();
    
    public TableColumn getTableColumn61() {
        return tableColumn61;
    }
    
    public void setTableColumn61(TableColumn tc) {
        this.tableColumn61 = tc;
    }
    
    private StaticText staticText59 = new StaticText();
    
    public StaticText getStaticText59() {
        return staticText59;
    }
    
    public void setStaticText59(StaticText st) {
        this.staticText59 = st;
    }
    
    private TableColumn tableColumn62 = new TableColumn();
    
    public TableColumn getTableColumn62() {
        return tableColumn62;
    }
    
    public void setTableColumn62(TableColumn tc) {
        this.tableColumn62 = tc;
    }
    
    private StaticText staticText60 = new StaticText();
    
    public StaticText getStaticText60() {
        return staticText60;
    }
    
    public void setStaticText60(StaticText st) {
        this.staticText60 = st;
    }
    
    private TableColumn tableColumn63 = new TableColumn();
    
    public TableColumn getTableColumn63() {
        return tableColumn63;
    }
    
    public void setTableColumn63(TableColumn tc) {
        this.tableColumn63 = tc;
    }
    
    private StaticText staticText61 = new StaticText();
    
    public StaticText getStaticText61() {
        return staticText61;
    }
    
    public void setStaticText61(StaticText st) {
        this.staticText61 = st;
    }
    
    private TableColumn tableColumn64 = new TableColumn();
    
    public TableColumn getTableColumn64() {
        return tableColumn64;
    }
    
    public void setTableColumn64(TableColumn tc) {
        this.tableColumn64 = tc;
    }
    
    private StaticText staticText62 = new StaticText();
    
    public StaticText getStaticText62() {
        return staticText62;
    }
    
    public void setStaticText62(StaticText st) {
        this.staticText62 = st;
    }
    
    private TableColumn tableColumn65 = new TableColumn();
    
    public TableColumn getTableColumn65() {
        return tableColumn65;
    }
    
    public void setTableColumn65(TableColumn tc) {
        this.tableColumn65 = tc;
    }
    
    private StaticText staticText63 = new StaticText();
    
    public StaticText getStaticText63() {
        return staticText63;
    }
    
    public void setStaticText63(StaticText st) {
        this.staticText63 = st;
    }
    
    private TableColumn tableColumn67 = new TableColumn();
    
    public TableColumn getTableColumn67() {
        return tableColumn67;
    }
    
    public void setTableColumn67(TableColumn tc) {
        this.tableColumn67 = tc;
    }
    
    private StaticText staticText65 = new StaticText();
    
    public StaticText getStaticText65() {
        return staticText65;
    }
    
    public void setStaticText65(StaticText st) {
        this.staticText65 = st;
    }
    
    private TableColumn tableColumn69 = new TableColumn();
    
    public TableColumn getTableColumn69() {
        return tableColumn69;
    }
    
    public void setTableColumn69(TableColumn tc) {
        this.tableColumn69 = tc;
    }
    
    private StaticText staticText67 = new StaticText();
    
    public StaticText getStaticText67() {
        return staticText67;
    }
    
    public void setStaticText67(StaticText st) {
        this.staticText67 = st;
    }
    
    private Table table7 = new Table();
    
    public Table getTable7() {
        return table7;
    }
    
    public void setTable7(Table t) {
        this.table7 = t;
    }
    
    private TableRowGroup tableRowGroup7 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup7() {
        return tableRowGroup7;
    }
    
    public void setTableRowGroup7(TableRowGroup trg) {
        this.tableRowGroup7 = trg;
    }
    
    private TableColumn tableColumn23 = new TableColumn();
    
    public TableColumn getTableColumn23() {
        return tableColumn23;
    }
    
    public void setTableColumn23(TableColumn tc) {
        this.tableColumn23 = tc;
    }
    
    private StaticText staticText1 = new StaticText();
    
    public StaticText getStaticText1() {
        return staticText1;
    }
    
    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    
    private TableColumn tableColumn70 = new TableColumn();
    
    public TableColumn getTableColumn70() {
        return tableColumn70;
    }
    
    public void setTableColumn70(TableColumn tc) {
        this.tableColumn70 = tc;
    }
    
    private StaticText staticText68 = new StaticText();
    
    public StaticText getStaticText68() {
        return staticText68;
    }
    
    public void setStaticText68(StaticText st) {
        this.staticText68 = st;
    }
    
    private TableColumn tableColumn71 = new TableColumn();
    
    public TableColumn getTableColumn71() {
        return tableColumn71;
    }
    
    public void setTableColumn71(TableColumn tc) {
        this.tableColumn71 = tc;
    }
    
    private StaticText staticText69 = new StaticText();
    
    public StaticText getStaticText69() {
        return staticText69;
    }
    
    public void setStaticText69(StaticText st) {
        this.staticText69 = st;
    }
    
    private TableColumn tableColumn72 = new TableColumn();
    
    public TableColumn getTableColumn72() {
        return tableColumn72;
    }
    
    public void setTableColumn72(TableColumn tc) {
        this.tableColumn72 = tc;
    }
    
    private StaticText staticText70 = new StaticText();
    
    public StaticText getStaticText70() {
        return staticText70;
    }
    
    public void setStaticText70(StaticText st) {
        this.staticText70 = st;
    }
    
    private TableColumn tableColumn78 = new TableColumn();
    
    public TableColumn getTableColumn78() {
        return tableColumn78;
    }
    
    public void setTableColumn78(TableColumn tc) {
        this.tableColumn78 = tc;
    }
    
    private StaticText staticText76 = new StaticText();
    
    public StaticText getStaticText76() {
        return staticText76;
    }
    
    public void setStaticText76(StaticText st) {
        this.staticText76 = st;
    }
    
    private TableColumn tableColumn79 = new TableColumn();
    
    public TableColumn getTableColumn79() {
        return tableColumn79;
    }
    
    public void setTableColumn79(TableColumn tc) {
        this.tableColumn79 = tc;
    }
    
    private StaticText staticText77 = new StaticText();
    
    public StaticText getStaticText77() {
        return staticText77;
    }
    
    public void setStaticText77(StaticText st) {
        this.staticText77 = st;
    }
    
    private Table table8 = new Table();
    
    public Table getTable8() {
        return table8;
    }
    
    public void setTable8(Table t) {
        this.table8 = t;
    }
    
    private TableRowGroup tableRowGroup8 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup8() {
        return tableRowGroup8;
    }
    
    public void setTableRowGroup8(TableRowGroup trg) {
        this.tableRowGroup8 = trg;
    }
    
    private CachedRowSetDataProvider filesParentDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getFilesParentDataProvider() {
        return filesParentDataProvider;
    }
    
    public void setFilesParentDataProvider(CachedRowSetDataProvider crsdp) {
        this.filesParentDataProvider = crsdp;
    }
    
    private TableColumn tableColumn74 = new TableColumn();
    
    public TableColumn getTableColumn74() {
        return tableColumn74;
    }
    
    public void setTableColumn74(TableColumn tc) {
        this.tableColumn74 = tc;
    }
    
    private StaticText staticText72 = new StaticText();
    
    public StaticText getStaticText72() {
        return staticText72;
    }
    
    public void setStaticText72(StaticText st) {
        this.staticText72 = st;
    }
    
    private TableColumn tableColumn81 = new TableColumn();
    
    public TableColumn getTableColumn81() {
        return tableColumn81;
    }
    
    public void setTableColumn81(TableColumn tc) {
        this.tableColumn81 = tc;
    }
    
    private StaticText staticText79 = new StaticText();
    
    public StaticText getStaticText79() {
        return staticText79;
    }
    
    public void setStaticText79(StaticText st) {
        this.staticText79 = st;
    }
    
    private TableColumn tableColumn82 = new TableColumn();
    
    public TableColumn getTableColumn82() {
        return tableColumn82;
    }
    
    public void setTableColumn82(TableColumn tc) {
        this.tableColumn82 = tc;
    }
    
    private StaticText staticText80 = new StaticText();
    
    public StaticText getStaticText80() {
        return staticText80;
    }
    
    public void setStaticText80(StaticText st) {
        this.staticText80 = st;
    }
    
    private TableColumn tableColumn83 = new TableColumn();
    
    public TableColumn getTableColumn83() {
        return tableColumn83;
    }
    
    public void setTableColumn83(TableColumn tc) {
        this.tableColumn83 = tc;
    }
    
    private StaticText staticText81 = new StaticText();
    
    public StaticText getStaticText81() {
        return staticText81;
    }
    
    public void setStaticText81(StaticText st) {
        this.staticText81 = st;
    }
    
    private TableColumn tableColumn87 = new TableColumn();
    
    public TableColumn getTableColumn87() {
        return tableColumn87;
    }
    
    public void setTableColumn87(TableColumn tc) {
        this.tableColumn87 = tc;
    }
    
    private StaticText staticText85 = new StaticText();
    
    public StaticText getStaticText85() {
        return staticText85;
    }
    
    public void setStaticText85(StaticText st) {
        this.staticText85 = st;
    }
    
    private TableColumn tableColumn92 = new TableColumn();
    
    public TableColumn getTableColumn92() {
        return tableColumn92;
    }
    
    public void setTableColumn92(TableColumn tc) {
        this.tableColumn92 = tc;
    }
    
    private Hyperlink hyperlink2 = new Hyperlink();
    
    public Hyperlink getHyperlink2() {
        return hyperlink2;
    }
    
    public void setHyperlink2(Hyperlink h) {
        this.hyperlink2 = h;
    }
    
    private StaticText staticText90 = new StaticText();
    
    public StaticText getStaticText90() {
        return staticText90;
    }
    
    public void setStaticText90(StaticText st) {
        this.staticText90 = st;
    }
    
    private TableColumn tableColumn73 = new TableColumn();
    
    public TableColumn getTableColumn73() {
        return tableColumn73;
    }
    
    public void setTableColumn73(TableColumn tc) {
        this.tableColumn73 = tc;
    }
    
    private StaticText staticText71 = new StaticText();
    
    public StaticText getStaticText71() {
        return staticText71;
    }
    
    public void setStaticText71(StaticText st) {
        this.staticText71 = st;
    }
    
    private TableColumn tableColumn75 = new TableColumn();
    
    public TableColumn getTableColumn75() {
        return tableColumn75;
    }
    
    public void setTableColumn75(TableColumn tc) {
        this.tableColumn75 = tc;
    }
    
    private StaticText staticText73 = new StaticText();
    
    public StaticText getStaticText73() {
        return staticText73;
    }
    
    public void setStaticText73(StaticText st) {
        this.staticText73 = st;
    }
    
    private TableColumn tableColumn93 = new TableColumn();
    
    public TableColumn getTableColumn93() {
        return tableColumn93;
    }
    
    public void setTableColumn93(TableColumn tc) {
        this.tableColumn93 = tc;
    }
    
    private StaticText staticText91 = new StaticText();
    
    public StaticText getStaticText91() {
        return staticText91;
    }
    
    public void setStaticText91(StaticText st) {
        this.staticText91 = st;
    }
    
    private TableColumn tableColumn94 = new TableColumn();
    
    public TableColumn getTableColumn94() {
        return tableColumn94;
    }
    
    public void setTableColumn94(TableColumn tc) {
        this.tableColumn94 = tc;
    }
    
    private StaticText staticText92 = new StaticText();
    
    public StaticText getStaticText92() {
        return staticText92;
    }
    
    public void setStaticText92(StaticText st) {
        this.staticText92 = st;
    }
    
    private Table table9 = new Table();
    
    public Table getTable9() {
        return table9;
    }
    
    public void setTable9(Table t) {
        this.table9 = t;
    }
    
    private TableRowGroup tableRowGroup9 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup9() {
        return tableRowGroup9;
    }
    
    public void setTableRowGroup9(TableRowGroup trg) {
        this.tableRowGroup9 = trg;
    }
    
    private CachedRowSetDataProvider filetierDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getFiletierDataProvider() {
        return filetierDataProvider;
    }
    
    public void setFiletierDataProvider(CachedRowSetDataProvider crsdp) {
        this.filetierDataProvider = crsdp;
    }
    
    private TableColumn tableColumn84 = new TableColumn();
    
    public TableColumn getTableColumn84() {
        return tableColumn84;
    }
    
    public void setTableColumn84(TableColumn tc) {
        this.tableColumn84 = tc;
    }
    
    private StaticText staticText82 = new StaticText();
    
    public StaticText getStaticText82() {
        return staticText82;
    }
    
    public void setStaticText82(StaticText st) {
        this.staticText82 = st;
    }
    
    private TableColumn tableColumn86 = new TableColumn();
    
    public TableColumn getTableColumn86() {
        return tableColumn86;
    }
    
    public void setTableColumn86(TableColumn tc) {
        this.tableColumn86 = tc;
    }
    
    private StaticText staticText84 = new StaticText();
    
    public StaticText getStaticText84() {
        return staticText84;
    }
    
    public void setStaticText84(StaticText st) {
        this.staticText84 = st;
    }
    
    private TableColumn tableColumn89 = new TableColumn();
    
    public TableColumn getTableColumn89() {
        return tableColumn89;
    }
    
    public void setTableColumn89(TableColumn tc) {
        this.tableColumn89 = tc;
    }
    
    private StaticText staticText87 = new StaticText();
    
    public StaticText getStaticText87() {
        return staticText87;
    }
    
    public void setStaticText87(StaticText st) {
        this.staticText87 = st;
    }
    
    private TableColumn tableColumn42 = new TableColumn();
    
    public TableColumn getTableColumn42() {
        return tableColumn42;
    }
    
    public void setTableColumn42(TableColumn tc) {
        this.tableColumn42 = tc;
    }
    
    private StaticText staticText17 = new StaticText();
    
    public StaticText getStaticText17() {
        return staticText17;
    }
    
    public void setStaticText17(StaticText st) {
        this.staticText17 = st;
    }
    
    private TableColumn tableColumn43 = new TableColumn();
    
    public TableColumn getTableColumn43() {
        return tableColumn43;
    }
    
    public void setTableColumn43(TableColumn tc) {
        this.tableColumn43 = tc;
    }
    
    private StaticText staticText38 = new StaticText();
    
    public StaticText getStaticText38() {
        return staticText38;
    }
    
    public void setStaticText38(StaticText st) {
        this.staticText38 = st;
    }
    
    private Tab tab14 = new Tab();
    
    public Tab getTab14() {
        return tab14;
    }
    
    public void setTab14(Tab t) {
        this.tab14 = t;
    }
    
    private PanelLayout layoutPanel14 = new PanelLayout();
    
    public PanelLayout getLayoutPanel14() {
        return layoutPanel14;
    }
    
    public void setLayoutPanel14(PanelLayout pl) {
        this.layoutPanel14 = pl;
    }
    
    private Table table10 = new Table();
    
    public Table getTable10() {
        return table10;
    }
    
    public void setTable10(Table t) {
        this.table10 = t;
    }
    
    private TableRowGroup tableRowGroup10 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup10() {
        return tableRowGroup10;
    }
    
    public void setTableRowGroup10(TableRowGroup trg) {
        this.tableRowGroup10 = trg;
    }
    
    private CachedRowSetDataProvider filebranchDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getFilebranchDataProvider() {
        return filebranchDataProvider;
    }
    
    public void setFilebranchDataProvider(CachedRowSetDataProvider crsdp) {
        this.filebranchDataProvider = crsdp;
    }
    
    private TableColumn tableColumn96 = new TableColumn();
    
    public TableColumn getTableColumn96() {
        return tableColumn96;
    }
    
    public void setTableColumn96(TableColumn tc) {
        this.tableColumn96 = tc;
    }
    
    private StaticText staticText94 = new StaticText();
    
    public StaticText getStaticText94() {
        return staticText94;
    }
    
    public void setStaticText94(StaticText st) {
        this.staticText94 = st;
    }
    
    private TableColumn tableColumn98 = new TableColumn();
    
    public TableColumn getTableColumn98() {
        return tableColumn98;
    }
    
    public void setTableColumn98(TableColumn tc) {
        this.tableColumn98 = tc;
    }
    
    private StaticText staticText96 = new StaticText();
    
    public StaticText getStaticText96() {
        return staticText96;
    }
    
    public void setStaticText96(StaticText st) {
        this.staticText96 = st;
    }
    
    private TableColumn tableColumn100 = new TableColumn();
    
    public TableColumn getTableColumn100() {
        return tableColumn100;
    }
    
    public void setTableColumn100(TableColumn tc) {
        this.tableColumn100 = tc;
    }
    
    private StaticText staticText98 = new StaticText();
    
    public StaticText getStaticText98() {
        return staticText98;
    }
    
    public void setStaticText98(StaticText st) {
        this.staticText98 = st;
    }
    
    private Table table11 = new Table();
    
    public Table getTable11() {
        return table11;
    }
    
    public void setTable11(Table t) {
        this.table11 = t;
    }
    
    private TableRowGroup tableRowGroup11 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup11() {
        return tableRowGroup11;
    }
    
    public void setTableRowGroup11(TableRowGroup trg) {
        this.tableRowGroup11 = trg;
    }
    
    private CachedRowSetDataProvider lumisectionDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getLumisectionDataProvider() {
        return lumisectionDataProvider;
    }
    
    public void setLumisectionDataProvider(CachedRowSetDataProvider crsdp) {
        this.lumisectionDataProvider = crsdp;
    }
    
    private TableColumn tableColumn90 = new TableColumn();
    
    public TableColumn getTableColumn90() {
        return tableColumn90;
    }
    
    public void setTableColumn90(TableColumn tc) {
        this.tableColumn90 = tc;
    }
    
    private StaticText staticText88 = new StaticText();
    
    public StaticText getStaticText88() {
        return staticText88;
    }
    
    public void setStaticText88(StaticText st) {
        this.staticText88 = st;
    }
    
    private TableColumn tableColumn91 = new TableColumn();
    
    public TableColumn getTableColumn91() {
        return tableColumn91;
    }
    
    public void setTableColumn91(TableColumn tc) {
        this.tableColumn91 = tc;
    }
    
    private StaticText staticText89 = new StaticText();
    
    public StaticText getStaticText89() {
        return staticText89;
    }
    
    public void setStaticText89(StaticText st) {
        this.staticText89 = st;
    }
    
    private TableColumn tableColumn101 = new TableColumn();
    
    public TableColumn getTableColumn101() {
        return tableColumn101;
    }
    
    public void setTableColumn101(TableColumn tc) {
        this.tableColumn101 = tc;
    }
    
    private StaticText staticText99 = new StaticText();
    
    public StaticText getStaticText99() {
        return staticText99;
    }
    
    public void setStaticText99(StaticText st) {
        this.staticText99 = st;
    }
    
    private TableColumn tableColumn102 = new TableColumn();
    
    public TableColumn getTableColumn102() {
        return tableColumn102;
    }
    
    public void setTableColumn102(TableColumn tc) {
        this.tableColumn102 = tc;
    }
    
    private StaticText staticText100 = new StaticText();
    
    public StaticText getStaticText100() {
        return staticText100;
    }
    
    public void setStaticText100(StaticText st) {
        this.staticText100 = st;
    }
    
    private TableColumn tableColumn103 = new TableColumn();
    
    public TableColumn getTableColumn103() {
        return tableColumn103;
    }
    
    public void setTableColumn103(TableColumn tc) {
        this.tableColumn103 = tc;
    }
    
    private StaticText staticText101 = new StaticText();
    
    public StaticText getStaticText101() {
        return staticText101;
    }
    
    public void setStaticText101(StaticText st) {
        this.staticText101 = st;
    }
    
    private TableColumn tableColumn104 = new TableColumn();
    
    public TableColumn getTableColumn104() {
        return tableColumn104;
    }
    
    public void setTableColumn104(TableColumn tc) {
        this.tableColumn104 = tc;
    }
    
    private StaticText staticText102 = new StaticText();
    
    public StaticText getStaticText102() {
        return staticText102;
    }
    
    public void setStaticText102(StaticText st) {
        this.staticText102 = st;
    }
    
    private TableColumn tableColumn106 = new TableColumn();
    
    public TableColumn getTableColumn106() {
        return tableColumn106;
    }
    
    public void setTableColumn106(TableColumn tc) {
        this.tableColumn106 = tc;
    }
    
    private StaticText staticText104 = new StaticText();
    
    public StaticText getStaticText104() {
        return staticText104;
    }
    
    public void setStaticText104(StaticText st) {
        this.staticText104 = st;
    }
    
    private TableColumn tableColumn107 = new TableColumn();
    
    public TableColumn getTableColumn107() {
        return tableColumn107;
    }
    
    public void setTableColumn107(TableColumn tc) {
        this.tableColumn107 = tc;
    }
    
    private StaticText staticText105 = new StaticText();
    
    public StaticText getStaticText105() {
        return staticText105;
    }
    
    public void setStaticText105(StaticText st) {
        this.staticText105 = st;
    }
    
    private Table table12 = new Table();
    
    public Table getTable12() {
        return table12;
    }
    
    public void setTable12(Table t) {
        this.table12 = t;
    }
    
    private TableRowGroup tableRowGroup12 = new TableRowGroup();
    
    public TableRowGroup getTableRowGroup12() {
        return tableRowGroup12;
    }
    
    public void setTableRowGroup12(TableRowGroup trg) {
        this.tableRowGroup12 = trg;
    }
    
    private CachedRowSetDataProvider filealgoDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getFilealgoDataProvider() {
        return filealgoDataProvider;
    }
    
    public void setFilealgoDataProvider(CachedRowSetDataProvider crsdp) {
        this.filealgoDataProvider = crsdp;
    }
    
    private TableColumn tableColumn111 = new TableColumn();
    
    public TableColumn getTableColumn111() {
        return tableColumn111;
    }
    
    public void setTableColumn111(TableColumn tc) {
        this.tableColumn111 = tc;
    }
    
    private StaticText staticText109 = new StaticText();
    
    public StaticText getStaticText109() {
        return staticText109;
    }
    
    public void setStaticText109(StaticText st) {
        this.staticText109 = st;
    }
    
    private TableColumn tableColumn113 = new TableColumn();
    
    public TableColumn getTableColumn113() {
        return tableColumn113;
    }
    
    public void setTableColumn113(TableColumn tc) {
        this.tableColumn113 = tc;
    }
    
    private StaticText staticText111 = new StaticText();
    
    public StaticText getStaticText111() {
        return staticText111;
    }
    
    public void setStaticText111(StaticText st) {
        this.staticText111 = st;
    }
    
    private TableColumn tableColumn115 = new TableColumn();
    
    public TableColumn getTableColumn115() {
        return tableColumn115;
    }
    
    public void setTableColumn115(TableColumn tc) {
        this.tableColumn115 = tc;
    }
    
    private StaticText staticText113 = new StaticText();
    
    public StaticText getStaticText113() {
        return staticText113;
    }
    
    public void setStaticText113(StaticText st) {
        this.staticText113 = st;
    }
    
    private TableColumn tableColumn116 = new TableColumn();
    
    public TableColumn getTableColumn116() {
        return tableColumn116;
    }
    
    public void setTableColumn116(TableColumn tc) {
        this.tableColumn116 = tc;
    }
    
    private StaticText staticText114 = new StaticText();
    
    public StaticText getStaticText114() {
        return staticText114;
    }
    
    public void setStaticText114(StaticText st) {
        this.staticText114 = st;
    }
    
    private TableColumn tableColumn117 = new TableColumn();
    
    public TableColumn getTableColumn117() {
        return tableColumn117;
    }
    
    public void setTableColumn117(TableColumn tc) {
        this.tableColumn117 = tc;
    }
    
    private StaticText staticText115 = new StaticText();
    
    public StaticText getStaticText115() {
        return staticText115;
    }
    
    public void setStaticText115(StaticText st) {
        this.staticText115 = st;
    }
    
    private TableColumn tableColumn118 = new TableColumn();
    
    public TableColumn getTableColumn118() {
        return tableColumn118;
    }
    
    public void setTableColumn118(TableColumn tc) {
        this.tableColumn118 = tc;
    }
    
    private StaticText staticText116 = new StaticText();
    
    public StaticText getStaticText116() {
        return staticText116;
    }
    
    public void setStaticText116(StaticText st) {
        this.staticText116 = st;
    }
    
    private TableColumn tableColumn3 = new TableColumn();
    
    public TableColumn getTableColumn3() {
        return tableColumn3;
    }
    
    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }
    
    private StaticText staticText5 = new StaticText();
    
    public StaticText getStaticText5() {
        return staticText5;
    }
    
    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    
    private CachedRowSetDataProvider processedDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getProcessedDataProvider() {
        return processedDataProvider;
    }
    
    public void setProcessedDataProvider(CachedRowSetDataProvider crsdp) {
        this.processedDataProvider = crsdp;
    }
    
    private CachedRowSetDataProvider fileInfoDataProvider = new CachedRowSetDataProvider();
    
    public CachedRowSetDataProvider getFileInfoDataProvider() {
        return fileInfoDataProvider;
    }
    
    public void setFileInfoDataProvider(CachedRowSetDataProvider crsdp) {
        this.fileInfoDataProvider = crsdp;
    }

    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }

    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }

    private ImageHyperlink imageHyperlink1 = new ImageHyperlink();

    public ImageHyperlink getImageHyperlink1() {
        return imageHyperlink1;
    }

    public void setImageHyperlink1(ImageHyperlink ih) {
        this.imageHyperlink1 = ih;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private PageSeparator pageSeparator2 = new PageSeparator();

    public PageSeparator getPageSeparator2() {
        return pageSeparator2;
    }

    public void setPageSeparator2(PageSeparator ps) {
        this.pageSeparator2 = ps;
    }

    private Tab tab15 = new Tab();

    public Tab getTab15() {
        return tab15;
    }

    public void setTab15(Tab t) {
        this.tab15 = t;
    }

    private PanelLayout layoutPanel15 = new PanelLayout();

    public PanelLayout getLayoutPanel15() {
        return layoutPanel15;
    }

    public void setLayoutPanel15(PanelLayout pl) {
        this.layoutPanel15 = pl;
    }

    private Table table13 = new Table();

    public Table getTable13() {
        return table13;
    }

    public void setTable13(Table t) {
        this.table13 = t;
    }

    private TableRowGroup tableRowGroup13 = new TableRowGroup();

    public TableRowGroup getTableRowGroup13() {
        return tableRowGroup13;
    }

    public void setTableRowGroup13(TableRowGroup trg) {
        this.tableRowGroup13 = trg;
    }

    private CachedRowSetDataProvider procdsparentDataProvider = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getProcdsparentDataProvider() {
        return procdsparentDataProvider;
    }

    public void setProcdsparentDataProvider(CachedRowSetDataProvider crsdp) {
        this.procdsparentDataProvider = crsdp;
    }

    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tc) {
        this.tableColumn15 = tc;
    }

    private Hyperlink hyperlink3 = new Hyperlink();

    public Hyperlink getHyperlink3() {
        return hyperlink3;
    }

    public void setHyperlink3(Hyperlink h) {
        this.hyperlink3 = h;
    }

    private TableColumn tableColumn16 = new TableColumn();

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tc) {
        this.tableColumn16 = tc;
    }

    private StaticText staticText21 = new StaticText();

    public StaticText getStaticText21() {
        return staticText21;
    }

    public void setStaticText21(StaticText st) {
        this.staticText21 = st;
    }

    private TableColumn tableColumn19 = new TableColumn();

    public TableColumn getTableColumn19() {
        return tableColumn19;
    }

    public void setTableColumn19(TableColumn tc) {
        this.tableColumn19 = tc;
    }

    private StaticText staticText22 = new StaticText();

    public StaticText getStaticText22() {
        return staticText22;
    }

    public void setStaticText22(StaticText st) {
        this.staticText22 = st;
    }

    private TableColumn tableColumn20 = new TableColumn();

    public TableColumn getTableColumn20() {
        return tableColumn20;
    }

    public void setTableColumn20(TableColumn tc) {
        this.tableColumn20 = tc;
    }

    private StaticText staticText23 = new StaticText();

    public StaticText getStaticText23() {
        return staticText23;
    }

    public void setStaticText23(StaticText st) {
        this.staticText23 = st;
    }

    private TableColumn tableColumn21 = new TableColumn();

    public TableColumn getTableColumn21() {
        return tableColumn21;
    }

    public void setTableColumn21(TableColumn tc) {
        this.tableColumn21 = tc;
    }

    private StaticText staticText24 = new StaticText();

    public StaticText getStaticText24() {
        return staticText24;
    }

    public void setStaticText24(StaticText st) {
        this.staticText24 = st;
    }

    private CachedRowSetDataProvider algorithmconfigDataProvider1 = new CachedRowSetDataProvider();

    public CachedRowSetDataProvider getAlgorithmconfigDataProvider1() {
        return algorithmconfigDataProvider1;
    }

    public void setAlgorithmconfigDataProvider1(CachedRowSetDataProvider crsdp) {
        this.algorithmconfigDataProvider1 = crsdp;
    }

    private BigDecimalConverter listbox1Converter = new BigDecimalConverter();

    public BigDecimalConverter getListbox1Converter() {
        return listbox1Converter;
    }

    public void setListbox1Converter(BigDecimalConverter bdc) {
        this.listbox1Converter = bdc;
    }
    
    // </editor-fold>
    
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Page1() {
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
        
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
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
    public void prerender() {
        
        
        if ( primaryName.getSelected() == null ) {
            try {
                primarydatasetDataProvider.cursorFirst();
                getSessionBean1().getProcesseddatasetRowSet().setObject(
                        1, primarydatasetDataProvider.getValue("PrimaryDataset.Name"));
                processeddatasetDataProvider.refresh();
            } catch (Exception e) {
                error("Cannot switch to person " + e.getMessage());
            }
        }
        
        if ( getSessionBean1().getCurrentProcDSID() == null ) {
            processeddatasetDataProvider.cursorFirst();
            Object procDSID = processeddatasetDataProvider.getValue("ProcessedDataset.ID");
            refreshProcDS(procDSID);
        }
        
        if (getSessionBean1().getCurrentFileID() == null ) {
            filesDataProvider.cursorFirst();
            Object fileID = filesDataProvider.getValue("Files.ID");
            refreshFile(fileID);
        }
        
    }
    private void refreshProcDS(Object procDSID){
        try {
            if(procDSID == null) {
                procDSID = "-1";
                System.out.println("procID is null");
            }
            System.out.println("procDS ID " + procDSID);
            getSessionBean1().setCurrentProcDSID(procDSID);
            
            getSessionBean1().getFilesRowSet().setObject(1, procDSID);
            filesDataProvider.refresh();
            getSessionBean1().getAnalysisdatasetRowSet().setObject(1, procDSID);
            analysisdatasetDataProvider.refresh();
            getSessionBean1().getDatatierRowSet().setObject(1, procDSID);
            datatierDataProvider.refresh();
            getSessionBean1().getRunsRowSet().setObject(1, procDSID);
            runsDataProvider.refresh();
            getSessionBean1().getAlgorithmconfigRowSet().setObject(1, procDSID);
            algorithmconfigDataProvider.refresh();
            getSessionBean1().getBlockRowSet().setObject(1, procDSID);
            blockDataProvider.refresh();
            getSessionBean1().getProcdsparentRowSet().setObject(1, procDSID);
            procdsparentDataProvider.refresh();
            setProcDSTableTitle(getCurrentPath(procDSID));
            
        } catch (Exception e) {
            log("Cannot switch tso  " + e.getMessage());
        }
    }
    
    private void refreshFile(Object fileID){
        try {
            
            if(fileID == null) {
                fileID = "-1";
                System.out.println("fileID is null");
            }
            getSessionBean1().setCurrentFileID(fileID);
            
            getSessionBean1().getFilesParentRowSet().setObject(1, fileID);
            filesParentDataProvider.refresh();
            getSessionBean1().getFiletierRowSet().setObject(1, fileID);
            filetierDataProvider.refresh();
            getSessionBean1().getFilebranchRowSet().setObject(1, fileID);
            filebranchDataProvider.refresh();
            getSessionBean1().getLumisectionRowSet().setObject(1, fileID);
            lumisectionDataProvider.refresh();
            getSessionBean1().getFilealgoRowSet().setObject(1, fileID);
            filealgoDataProvider.refresh();
            System.out.println("Setting table when hyperlink2 = null");
            setFileTableTitle(getCurrentFile(fileID));
        } catch (Exception e) {
            log("Cannot switch tso  " + e.getMessage());
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
        primarydatasetDataProvider.close();
        processeddatasetDataProvider.close();
        filesDataProvider.close();
        algorithmconfigDataProvider.close();
        blockDataProvider.close();
        analysisdatasetDataProvider.close();
        datatierDataProvider.close();
        runsDataProvider.close();
        filesParentDataProvider.close();
        filetierDataProvider.close();
        filebranchDataProvider.close();
        lumisectionDataProvider.close();
        filealgoDataProvider.close();
        processedDataProvider.close();
        fileInfoDataProvider.close();
        procdsparentDataProvider.close();
        algorithmconfigDataProvider1.close();
    }
    
    /**
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
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
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
    }
    
    public void primaryName_processValueChange(ValueChangeEvent event) {
        // TODO: Replace with your code
        try {
            getSessionBean1().getProcesseddatasetRowSet().setObject(
                    1, primaryName.getSelected());
            processeddatasetDataProvider.refresh();
            
            //processeddatasetDataProvider.cursorFirst();
            Object procDSID = processeddatasetDataProvider.getValue("ProcessedDataset.ID");
            
            refreshProcDS(procDSID);
            filesDataProvider.cursorFirst();
            Object fileID = filesDataProvider.getValue("Files.ID");
            refreshFile(fileID);
        } catch (Exception e) {
            log("Cannot switch to primaryName " + e.getMessage());
        }
    }
    
    
    
    
    
    /*public String procDSID2_action() {
        // TODO: Replace with your code
        try {
     
            getSessionBean1().getFilesRowSet().setObject(
                    1, procDSID2.getValue());
            filesDataProvider.refresh();
        } catch (Exception e) {
            log("Cannot switch tso  " + e.getMessage());
        }
        return null;
    }*/
    
    public String tab3_action() {
        // TODO: Replace with your code
        
        return null;
    }
    
    public String tab1_action() {
        // TODO: Replace with your code
        
        return null;
    }
    
    public void dropDown1_processValueChange(ValueChangeEvent event) {
        // TODO: Replace with your code
        
    }
    
    public String tab9_action() {
        // TODO: Replace with your code
        
        return null;
    }
    
    private String getCurrentPath(Object procDSID) {
        String path = null;
        try{
            //System.out.println("id passed is  " + procDSID);
            getSessionBean1().getProcessedRowSet().setObject(1, procDSID);
            processedDataProvider.refresh();
            String primDSName = (String)primarydatasetDataProvider.getValue("PrimaryDataset.Name");
            String procDSName = (String)processedDataProvider.getValue("ProcessedDataset.Name");
            path = "Selected ProcDS [ /" + primDSName + "/" + procDSName + " ]";
            //System.out.println("Path is " + path);
        } catch (Exception e) {
            log("Cannot switch to  " + e.getMessage());
        }
        return path;
    }
    
    private String getCurrentFile(Object fileID) {
        String lfn = null;
        try{
            //System.out.println("FILE ID clicked is " + fileID);
            getSessionBean1().getFileInfoRowSet().setObject(1, fileID);
            fileInfoDataProvider.refresh();
            lfn = "Slected file name [ " + (String)fileInfoDataProvider.getValue("Files.LogicalFileName") + " ]";
        } catch (Exception e) {
            log("Cannot switch to  " + e.getMessage());
        }
        return lfn;
    }
    
    public String hyperlink1_action() {
        // TODO: Replace with your code
        try {
            Object value = hyperlink1.getValue();
            refreshProcDSTables(value);
            
        } catch (Exception e) {
            log("Cannot switch tso  " + e.getMessage());
        }
        return null;
    }
    
    private void refreshProcDSTables(Object value) {
        try {
            getSessionBean1().getFilesRowSet().setObject(1, value);
            filesDataProvider.refresh();
            getSessionBean1().getAnalysisdatasetRowSet().setObject(1, value);
            analysisdatasetDataProvider.refresh();
            getSessionBean1().getDatatierRowSet().setObject(1, value);
            datatierDataProvider.refresh();
            getSessionBean1().getRunsRowSet().setObject(1, value);
            runsDataProvider.refresh();
            getSessionBean1().getAlgorithmconfigRowSet().setObject(1, value);
            algorithmconfigDataProvider.refresh();
            getSessionBean1().getBlockRowSet().setObject(1, value);
            blockDataProvider.refresh();
            getSessionBean1().getProcdsparentRowSet().setObject(1, value);
            procdsparentDataProvider.refresh();
            getSessionBean1().setCurrentProcDSID(value);
            
            setProcDSTableTitle(getCurrentPath(value));
            
        } catch (Exception e) {
            log("Cannot switch tso  " + e.getMessage());
        }
    }
    public String hyperlink5_action() {
        // TODO: Replace with your code
        
        return null;
    }
    
    private void setFileTableTitle(String lfn) {
        getTable1().setTitle(lfn);
        getTable8().setTitle(lfn);
        getTable9().setTitle(lfn);
        getTable10().setTitle(lfn);
        getTable11().setTitle(lfn);
        getTable12().setTitle(lfn);
    }
    
    private void setProcDSTableTitle(String path) {
        getTable2().setTitle(path);
        getTable3().setTitle(path);
        getTable4().setTitle(path);
        getTable5().setTitle(path);
        getTable6().setTitle(path);
        getTable7().setTitle(path);
        getTable13().setTitle(path);
    }
    
    
    public String hyperlink2_action() {
        // TODO: Replace with your code
        try{
            
            
            
            Object value = hyperlink2.getValue();
            getSessionBean1().getFilesParentRowSet().setObject(1, value);
            filesParentDataProvider.refresh();
            getSessionBean1().getFiletierRowSet().setObject(1, value);
            filetierDataProvider.refresh();
            getSessionBean1().getFilebranchRowSet().setObject(1, value);
            filebranchDataProvider.refresh();
            getSessionBean1().getLumisectionRowSet().setObject(1, value);
            lumisectionDataProvider.refresh();
            getSessionBean1().getFilealgoRowSet().setObject(1, value);
            filealgoDataProvider.refresh();
            
            getSessionBean1().setCurrentFileID(value);
            //System.out.println("filename " + getSessionBean1().getCurrentFileID());
            setFileTableTitle(getCurrentFile(value));
            
        } catch (Exception e) {
            log("Cannot switch tso  " + e.getMessage());
            
        }
        return null;
    }
    
    
    public String tab10_action() {
        // TODO: Replace with your code
        
        return null;
    }

    public String hyperlink3_action() {
        // TODO: Replace with your code
        try {
            Object value = hyperlink3.getValue();
            refreshProcDSTables(value);
            
        } catch (Exception e) {
            log("Cannot switch tso  " + e.getMessage());
        }
        return null;
    }
    
    
}

