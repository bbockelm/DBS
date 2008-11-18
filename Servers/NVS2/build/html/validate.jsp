 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>
        <h:form binding="#{validateBean.form1}" id="form1">
                <h:outputText value="Name Validation Service" style="font-size:xx-large;"/>
                <rich:tabPanel id="UpperPanel"><rich:tab label="Input Request">
                        <h:panelGrid columns="3" columnClasses="gridContent" id="pg">
                                <h:outputText value="Name"/>
                                <h:inputText id="nameText" size="100" required="true" binding="#{validateBean.nameInputText}"/>
                                <rich:message for="nameText">
                                        <f:facet name="passedMarker"><h:graphicImage value="/html/images/passed.gif" /></f:facet>
                                        <f:facet name="errorMarker"><h:graphicImage value="/html/images/error.gif" /></f:facet>
                                </rich:message>

                                <h:outputText value="Type"/>
                                <h:selectOneRadio id="radio" required="true" binding="#{validateBean.type}">
                                        <f:selectItem itemValue="Primary" itemLabel="Primary DS"/>
                                        <f:selectItem itemValue="Processed" itemLabel="Processed DS"/>
                                        <f:selectItem itemValue="Physics" itemLabel="Physics Group"/>
                                        <f:selectItem itemValue="Tier" itemLabel="Data Tier"/>
                                        
                                </h:selectOneRadio>
                                <rich:message for="radio">
                                        <f:facet name="passedMarker"><h:graphicImage value="/html/images/passed.gif" /></f:facet>
                                        <f:facet name="errorMarker"><h:graphicImage value="/html/images/error.gif" /></f:facet>
                                </rich:message>
                                <f:facet name="footer">
                                        <h:commandButton id="submitButton" value="Validate" action="#{validateBean.validateAction}" reRender="outTab"/>
                                </f:facet>
                        </h:panelGrid>
                        <rich:message for="pg" binding="#{validateBean.generalInputMessage}">
                                <f:facet name="passedMarker"><h:graphicImage value="/html/images/passed.gif" /></f:facet>
                                <f:facet name="errorMarker"><h:graphicImage value="/html/images/error.gif" /></f:facet>
                        </rich:message>

                </rich:tab></rich:tabPanel>
                <rich:tabPanel id="BottomPanel"><rich:tab label="Output Request" rendered="#{validateBean.loadTable}" id="outTab">
                        <rich:dataTable columnClasses="column-index"
                                id="dataTable1"
                                rendered="true"
                                rowClasses="list-row3"
                                rows="20"
                                styleClass="list-table1"
                                title="Similar Names in DBS"
                                value="#{validateBean.result}"
                                var="resultVar">
                                <f:facet name="header">
                                        <rich:columnGroup>
                                                <rich:column><h:outputText value="Names in DBS" /></rich:column>
                                                <rich:column><h:outputText value="Similarity in %" /></rich:column>
                                        </rich:columnGroup>
                                </f:facet>
                                <rich:column><h:outputText value="#{resultVar.name}"/></rich:column>
                                <rich:column><h:outputText value="#{resultVar.similar}"/></rich:column>
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
