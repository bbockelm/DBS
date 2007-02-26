<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{AnalysisDatasets.page1}" id="page1">
            <ui:html binding="#{AnalysisDatasets.html1}" id="html1">
                <ui:head binding="#{AnalysisDatasets.head1}" id="head1">
                    <ui:link binding="#{AnalysisDatasets.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{AnalysisDatasets.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{AnalysisDatasets.form1}" id="form1">
                        <ui:tabSet binding="#{AnalysisDatasets.tabSet1}" id="tabSet1" selected="tab1" style="position: absolute; left: 120px; top: 168px">
                            <ui:tab binding="#{AnalysisDatasets.tab1}" id="tab1" text="Files">
                                <ui:panelLayout binding="#{AnalysisDatasets.layoutPanel1}" id="layoutPanel1" style="width: 100%; height: 128px;">
                                    <ui:table augmentTitle="false" binding="#{AnalysisDatasets.table1}" id="table1" title="Table" width="264">
                                        <ui:tableRowGroup binding="#{AnalysisDatasets.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                            sourceData="#{AnalysisDatasets.analysisdsfilelumiDataProvider}" sourceVar="currentRow">
                                            <ui:tableColumn binding="#{AnalysisDatasets.tableColumn9}" headerText="LogicalFileName" id="tableColumn9" sort="Files.LogicalFileName">
                                                <ui:staticText binding="#{AnalysisDatasets.staticText9}" id="staticText9" text="#{currentRow.value['Files.LogicalFileName']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{AnalysisDatasets.tableColumn10}" headerText="NumberOfEvents" id="tableColumn10" sort="Files.NumberOfEvents">
                                                <ui:staticText binding="#{AnalysisDatasets.staticText10}" id="staticText10" text="#{currentRow.value['Files.NumberOfEvents']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{AnalysisDatasets.tableColumn11}" headerText="FileSize" id="tableColumn11" sort="Files.FileSize">
                                                <ui:staticText binding="#{AnalysisDatasets.staticText11}" id="staticText11" text="#{currentRow.value['Files.FileSize']}"/>
                                            </ui:tableColumn>
                                            <ui:tableColumn binding="#{AnalysisDatasets.tableColumn12}" headerText="Block Name" id="tableColumn12" sort="Block.Name">
                                                <ui:staticText binding="#{AnalysisDatasets.staticText12}" id="staticText12" text="#{currentRow.value['Block.Name']}"/>
                                            </ui:tableColumn>
                                        </ui:tableRowGroup>
                                    </ui:table>
                                </ui:panelLayout>
                            </ui:tab>
                        </ui:tabSet>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
