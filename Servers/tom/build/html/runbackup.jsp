 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>
        <h:form binding="#{runBean.form1}" id="form1">
                <h:outputText value="Tier 0 Monitoring" style="font-size:xx-large;"/>
                <rich:tabPanel id="BottomPanel"><rich:tab label="Runs" id="outTab">
                        <rich:dataTable columnClasses="column-index"
                                id="dataTable1"
                                rendered="true"
                                rowClasses="list-row3"
                                rows="20"
                                styleClass="list-table1"
                                title="Similar Names in DBS"
                                value="#{runBean.runs}"
                                var="resultVar">
                                <f:facet name="header">
                                        <rich:columnGroup>
                                                <rich:column sortBy="#{resultVar.id}"><h:outputText value="RUN_ID" /></rich:column>
                                                <rich:column><h:outputText value="Start Time" /></rich:column>
                                                <rich:column><h:outputText value="End Time" /></rich:column>
                                                <rich:column><h:outputText value="Process" /></rich:column>
                                                <rich:column><h:outputText value="Version" /></rich:column>
                                                <rich:column><h:outputText value="HLT Key" /></rich:column>
                                                <rich:column><h:outputText value="Acq Era" /></rich:column>
                                                <rich:column><h:outputText value="Status" /></rich:column>
                                                <rich:column><h:outputText value="Streamers" /></rich:column>
                                        </rich:columnGroup>
                                </f:facet>
                                <rich:column><h:outputText value="#{resultVar.id}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.start}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.end}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.process}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.version}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.hltKey}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.acqEra}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.status}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.streamers}"/></rich:column>
                                <f:facet name="footer">
                                        <rich:datascroller ajaxSingle="true"
                                                for="dataTable1"
                                                reRender="dataTable1"
                                                maxPages="10"
                                                rendered="true"/>
                                </f:facet>
                        </rich:dataTable>
                </rich:tab></rich:tabPanel>
        </h:form>
</body>
</html>
