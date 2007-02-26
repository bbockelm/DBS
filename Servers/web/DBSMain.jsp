<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{DBSMain.page1}" id="page1">
            <ui:html binding="#{DBSMain.html1}" id="html1">
                <ui:head binding="#{DBSMain.head1}" id="head1">
                    <ui:link binding="#{DBSMain.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{DBSMain.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{DBSMain.form1}" id="form1">
                        <ui:hyperlink action="case2" binding="#{DBSMain.hyperlink1}" id="hyperlink1" style="position: absolute; left: 120px; top: 120px" text="DBS Browser"/>
                        <ui:hyperlink action="case1" binding="#{DBSMain.hyperlink2}" id="hyperlink2" style="left: 120px; top: 192px; position: absolute" text="DBS Dataset Discovery"/>
                        <ui:imageHyperlink binding="#{DBSMain.imageHyperlink1}" height="48" id="imageHyperlink1" imageURL="/resources/cms-logo.gif"
                            style="left: 24px; top: 37px; position: absolute" width="48"/>
                        <ui:staticText binding="#{DBSMain.staticText1}" id="staticText1"
                            style="color: gray; font-size: 18px; height: 22px; left: 96px; top: 48px; position: absolute; width: 478px" text="DBS DATA BROWSER AND DISCOVERY PAGE"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
