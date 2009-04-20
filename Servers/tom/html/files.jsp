 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<h:panelGrid columns="2">

		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlestreamerfile.jsp">
				<ui:param name="id" value="streamerFilesTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.streamerFiles}"/>
				<ui:param name="header" value="Streamer Files"/>
			</ui:include>
		</rich:panel>

		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlefile.jsp">
				<ui:param name="id" value="mergedFilesTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.mergedFiles}"/>
				<ui:param name="header" value="Merged Files"/>
			</ui:include>
		</rich:panel>
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlefile.jsp">
				<ui:param name="id" value="unmergedFileTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.unmergedFiles}"/>
				<ui:param name="header" value="Unmerged Files"/>
			</ui:include>
		</rich:panel>
	</h:panelGrid>

</body>
</html>
