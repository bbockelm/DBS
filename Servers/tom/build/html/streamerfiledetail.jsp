 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<div align="center">
	<ui:include src="menu.jsp"/>
	<h:form binding="#{streamerFileDetailBean.form1}" id="form1">
		<h:panelGrid columns="1">
			<rich:tabPanel id="StreamerFileDetailPanel"><rich:tab label="Streamer Files" id="streamerFileDetailTab">
				<ui:include src="singlestreamerfile.jsp">
					<ui:param name="id" value="streamerFilesTable1"/>
					<ui:param name="listFromBean" value="#{streamerFileDetailBean.streamerFiles}"/>
					<ui:param name="header" value="Streamer Files"/>
				</ui:include>
			</rich:tab></rich:tabPanel>
		</h:panelGrid>
	</h:form>
	</div>
</body>
</html>
