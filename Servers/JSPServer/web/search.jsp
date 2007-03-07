<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{search.page1}" id="page1">
            <ui:html binding="#{search.html1}" id="html1">
                <ui:head binding="#{search.head1}" id="head1">
                    <ui:link binding="#{search.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{search.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{search.form1}" id="form1">
                        <ui:staticText binding="#{search.staticText1}" id="staticText1" style="left: 24px; top: 72px; position: absolute" text="Algorithm Configs"/>
                        <ui:listbox binding="#{search.listbox1}" converter="#{search.listbox1Converter}" id="listbox1"
                            items="#{search.algorithmconfigDataProvider_search.options['ID,APP_PATH']}" multiple="true" rows="10" style="height: 96px; left: 24px; top: 96px; position: absolute; width: 456px"/>
                        <ui:staticText binding="#{search.staticText2}" id="staticText2" style="left: 144px; top: 216px; position: absolute" text="Data Tiers"/>
                        <ui:listbox binding="#{search.listbox2}" converter="#{search.listbox2Converter}" id="listbox2"
                            items="#{search.datatierDataProvider_search.options['ID,NAME']}" multiple="true" style="height: 96px; left: 144px; top: 240px; position: absolute; width: 192px"/>
                        <ui:dropDown binding="#{search.dropDown1}" converter="#{search.dropDown1Converter}" id="dropDown1"
                            items="#{search.primarydatasetDataProvider_saerch.options['ID,NAME']}"
                            style="left: 24px; top: 48px; position: absolute; width: 456px" valueChangeListener="#{search.dropDown1_processValueChange}"/>
                        <ui:listbox binding="#{search.listbox3}" converter="#{search.listbox3Converter}" id="listbox3"
                            items="#{search.runsDataProvider_search.options['ID,RUNNUMBER']}" multiple="true" style="height: 240px; left: 24px; top: 240px; position: absolute; width: 96px"/>
                        <ui:staticText binding="#{search.staticText3}" id="staticText3" style="left: 24px; top: 216px; position: absolute" text="Runs"/>
                        <ui:staticText binding="#{search.staticText4}" id="staticText4" style="left: 24px; top: 24px; position: absolute" text="Primary Dataset"/>
                        <ui:listbox binding="#{search.listbox4}" converter="#{search.listbox4Converter}" id="listbox4"
                            items="#{search.storageelementDataProvider_search.options['ID,SENAME']}" multiple="true" style="height: 122px; left: 144px; top: 360px; position: absolute; width: 312px"/>
                        <ui:staticText binding="#{search.staticText5}" id="staticText5" style="left: 144px; top: 336px; position: absolute" text="Storage Element"/>
                        <ui:button action="#{search.button1_action}" binding="#{search.button1}" id="button1"
                            style="height: 48px; left: 23px; top: 504px; position: absolute; width: 168px" text="Search My Datasets..."/>
                        <ui:table augmentTitle="false" binding="#{search.table1}" id="table1" paginateButton="true" paginationControls="true"
                            style="left: 528px; top: 48px; position: absolute; width: 671px" title="Matching Datasets..." width="560">
                            <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table1");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table1");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table1");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table1");
  table.initAllRows();
}
/*
 * Set the selected state for the given row groups
 * displayed in the table.  This functionality requires
 * the 'selectId' of the tableColumn to be set.
 *
 * @param rowGroupId HTML element id of the tableRowGroup component
 * @param selected Flag indicating whether components should be selected
 */
function selectGroupRows(rowGroupId, selected) {
  var table = document.getElementById("form1:table1");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table1");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                            <ui:tableRowGroup binding="#{search.tableRowGroup1}" emptyDataMsg="No Datasets Found" id="tableRowGroup1" rows="10"
                                sourceData="#{search.processeddatasetDataProvider_tier}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{search.tableColumn3}" headerText="Processed Dataset" id="tableColumn3" sort="PROCNAME">
                                    <ui:hyperlink action="#{search.hyperlink1_action}" binding="#{search.hyperlink1}" id="hyperlink1" text="#{currentRow.value['PROCNAME']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn1}" headerText="Dataset Status" id="tableColumn1" sort="PRODSTATUS">
                                    <ui:staticText binding="#{search.staticText7}" id="staticText7" text="#{currentRow.value['PRODSTATUS']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn6}" headerText="CREATEDBY" id="tableColumn6" sort="CREATEDBY">
                                    <ui:staticText binding="#{search.staticText12}" id="staticText12" text="#{currentRow.value['CREATEDBY']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn7}" headerText="CREATIONDATE" id="tableColumn7" sort="CREATIONDATE">
                                    <ui:staticText binding="#{search.staticText13}" id="staticText13" text="#{currentRow.value['CREATIONDATE']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn8}" headerText="LASTMODIFIEDBY" id="tableColumn8" sort="LASTMODIFIEDBY">
                                    <ui:staticText binding="#{search.staticText14}" id="staticText14" text="#{currentRow.value['LASTMODIFIEDBY']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn9}" headerText="LASTMODIFICATIONDATE" id="tableColumn9" sort="LASTMODIFICATIONDATE">
                                    <ui:staticText binding="#{search.staticText15}" id="staticText15" text="#{currentRow.value['LASTMODIFICATIONDATE']}"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                        <ui:table augmentTitle="false" binding="#{search.table2}" id="table2" paginateButton="true" paginationControls="true"
                            style="position: absolute; left: 528px; top: 360px" title="Matching Blocks..." width="600">
                            <ui:tableRowGroup binding="#{search.tableRowGroup2}" id="tableRowGroup2" rows="10"
                                sourceData="#{search.processeddatasetDataProvider_tier}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{search.tableColumn2}" headerText="BLOCKNAME" id="tableColumn2" sort="BLOCKNAME">
                                    <ui:hyperlink binding="#{search.hyperlink2}" id="hyperlink2" text="#{currentRow.value['BLOCKNAME']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn4}" headerText="BLOCKSIZE" id="tableColumn4" sort="BLOCKSIZE">
                                    <ui:staticText binding="#{search.staticText8}" id="staticText8" text="#{currentRow.value['BLOCKSIZE']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn5}" headerText="NUMBEROFFILES" id="tableColumn5" sort="NUMBEROFFILES">
                                    <ui:staticText binding="#{search.staticText9}" id="staticText9" text="#{currentRow.value['NUMBEROFFILES']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn11}" headerText="NUMBEROFEVENTS" id="tableColumn11" sort="NUMBEROFEVENTS">
                                    <ui:staticText binding="#{search.staticText10}" id="staticText10" text="#{currentRow.value['NUMBEROFEVENTS']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{search.tableColumn12}" headerText="OPENFORWRITING" id="tableColumn12" sort="OPENFORWRITING">
                                    <ui:staticText binding="#{search.staticText11}" id="staticText11" text="#{currentRow.value['OPENFORWRITING']}"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                        <ui:button action="#{search.button2_action}" binding="#{search.button2}" id="button2"
                            style="height: 48px; left: 215px; top: 504px; position: absolute; width: 168px" text="Clear Selection"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
