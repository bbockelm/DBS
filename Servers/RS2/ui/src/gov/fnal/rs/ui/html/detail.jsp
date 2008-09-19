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
    <style>
        .label{
        font-weight:bold;
        }
    </style>
    <!--h:panelGrid columns="3" columnClasses="col1, col2, col3"-->
    <h:panelGrid columns="1" columnClasses="gridContent" style="left: 20%; right: 80%; top: 20px; width: 55%; position: relative">
    <!--rich:panel rendered="false"></rich:panel-->
        <h:form id="form1">
        <rich:tabPanel id="TopPanel"><rich:tab label="DBS Detail" id="topTab">
            <h:panelGrid columns="2" columnClasses="col1, col2">
                <h:outputText value="DBS URL " styleClass="label"/>
                <h:outputText value="#{home.register.url}"/> 
                <h:outputText value="Status " styleClass="label"/>
                <h:outputText value="#{home.register.status}"/> 
                <h:outputText value="Alias " styleClass="label"/>
                <h:outputText value="#{home.register.alias}"/> 
                <h:outputText value="Date " styleClass="label"/>
                <h:outputText value="#{home.register.creationDate}"/> 
                <h:outputText value="DB Name " styleClass="label"/>
                <h:outputText value="#{home.register.dbName}"/> 
                <h:outputText value="DB Port " styleClass="label"/>
                <h:outputText value="#{home.register.dbPort}"/> 
                <h:outputText value="Critical " styleClass="label"/>
                <h:outputText value="#{home.register.critical}"/> 
                <h:outputText value="Node Name " styleClass="label"/>
                <h:outputText value="#{home.register.nodeName}"/> 
                <h:outputText value="Physical Location " styleClass="label"/>
                <h:outputText value="#{home.register.physicalLocation}"/> 
                <h:outputText value="Schema Version " styleClass="label"/>
                <h:outputText value="#{home.register.schemaVersion}"/> 
                <h:outputText value="Server Version " styleClass="label"/>
                <h:outputText value="#{home.register.serverVersion}"/> 
                <h:outputText value="Admin DN " styleClass="label"/>
                <h:outputText value="#{home.register.person.distinguishedName}"/> 
                <h:outputText value="Admin Name " styleClass="label"/>
                <h:outputText value="#{home.register.person.name}"/> 
                <h:outputText value="Admin Contact Info " styleClass="label"/>
                <h:outputText value="#{home.register.person.contactInfo}"/> 
                <h:commandLink action="home">
                    <h:outputText value="Back"/>
                </h:commandLink>
            </h:panelGrid>
        </rich:tab></rich:tabPanel>
        </h:form>
        <!--rich:panel rendered="false"></rich:panel-->
        </h:panelGrid>
    </f:view>
</jsp:root>