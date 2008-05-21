<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : Page2
    Created on : Jan 31, 2008, 3:49:48 PM
    Author     : Vijay Sekhri
-->
<jsp:root version="1.2" 
xmlns:f="http://java.sun.com/jsf/core" 
xmlns:h="http://java.sun.com/jsf/html" 
xmlns:jsp="http://java.sun.com/JSP/Page" 
xmlns:a4j="http://richfaces.org/a4j"
xmlns:rich="http://richfaces.org/rich"
xmlns:ui="http://java.sun.com/jsf/facelets">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
    <h:panelGrid columns="3" columnClasses="col1, col2, col3">
    <rich:panel rendered="false"></rich:panel>
        <h:form binding="#{home.form1}" id="form1">
            
            
                <h:outputText value="Registration Service WebUI" style="font-size:xx-large;"/>
                <rich:tabPanel id="TopPanel"><rich:tab label="DBS Services" id="topTab">
                        <rich:dataTable columnClasses="column-index"
                                id="dataTable1"
                                rendered="true"
                                rowClasses="list-row3"
                                rows="20"
                                styleClass="list-table1"
                                title="DBS Services"
                                value="#{home.result}"
                                var="resultVar">
                                <f:facet name="header">
                                        <rich:columnGroup>
                                                <rich:column><h:outputText value="DBS URL" /></rich:column>
                                                <rich:column><h:outputText value="Status" /></rich:column>
                                        </rich:columnGroup>
                                </f:facet>
                                <rich:column>
                                    <h:commandLink action="#{home.detailAction}">
                                        <h:outputText value="#{resultVar.url}" binding="#{home.url}" />
                                    </h:commandLink>
                                </rich:column>
                                <rich:column><h:outputText value="#{resultVar.status}"/></rich:column>
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
        <rich:panel rendered="false"></rich:panel>
        </h:panelGrid>
    </f:view>
</jsp:root>
