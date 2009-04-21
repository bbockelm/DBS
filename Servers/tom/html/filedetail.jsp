 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<div align="center">

	<ui:include src="menu.jsp"/>
	<h:form binding="#{fileDetailBean.form1}" id="form1">
		<h:panelGrid columns="1">
			<h:commandLink action="rundetail"><h:outputText value="Go Back"/></h:commandLink>
			<rich:tabPanel id="FileDetailPanel"><rich:tab label="Files" id="fileDetailTab">
				<ui:include src="singlefile.jsp">
					<ui:param name="id" value="fileDetailTable"/>
					<ui:param name="listFromBean" value="#{fileDetailBean.files}"/>
					<ui:param name="header" value="Files"/>
				</ui:include>
			</rich:tab></rich:tabPanel>
		</h:panelGrid>
	</h:form>
	</div>
</body>
</html>
