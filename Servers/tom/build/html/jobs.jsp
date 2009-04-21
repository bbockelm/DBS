 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<h:panelGrid columns="2">
		<h:form>
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlejob.jsp">
				<ui:param name="id" value="repackJobTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.repackJobs}"/>
				<ui:param name="header" value="Repack Jobs"/>
				<ui:param name="render" value="false"/>
				<ui:param name="navOutput" value="streamerfiles"/>
			</ui:include>

		</rich:panel>
		</h:form>
		<h:form>
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlejob.jsp">
				<ui:param name="id" value="mergedJobTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.mergedJobs}"/>
				<ui:param name="header" value="Merge Jobs"/>
				<ui:param name="render" value="true"/>
				<ui:param name="navOutput" value="files"/>
			</ui:include>
		</rich:panel>
		</h:form>
		<h:form>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlejob.jsp">
				<ui:param name="id" value="recoJobTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.recoJobs}"/>
				<ui:param name="header" value="Reco Jobs"/>
				<ui:param name="render" value="true"/>
				<ui:param name="navOutput" value="files"/>
			</ui:include>
		</rich:panel>
		</h:form>
		<h:form>
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlejob.jsp">
				<ui:param name="id" value="alcaSkimJobTable"/>
				<ui:param name="listFromBean" value="#{runDetailBean.alcaSkimJobs}"/>
				<ui:param name="header" value="AlcaSkim Jobs"/>
				<ui:param name="render" value="true"/>
				<ui:param name="navOutput" value="files"/>
			</ui:include>
		</rich:panel>
		</h:form>
	
	</h:panelGrid>

</body>
</html>
