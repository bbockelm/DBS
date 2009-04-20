 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

		<h:panelGrid columns="2">
			<rich:panel bodyClass="rich-laguna-panel-no-header">
				<ui:include src="singlejob.jsp">
					<ui:param name="id" value="repackRunningJobsTable"/>
					<ui:param name="listFromBean" value="#{runBean.repackRunningJobs}"/>
					<ui:param name="header" value="Repack Running Jobs"/>
				</ui:include>
			</rich:panel>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
				<ui:include src="singlejob.jsp">
					<ui:param name="id" value="repackMergedAcquiredJobsTable"/>
					<ui:param name="listFromBean" value="#{runBean.repackMergedAcquiredJobs}"/>
					<ui:param name="header" value="Repack Merged Acquired Jobs"/>
				</ui:include>
			</rich:panel>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
				<ui:include src="singlejob.jsp">
					<ui:param name="id" value="recoAcquiredJobsTable"/>
					<ui:param name="listFromBean" value="#{runBean.recoAcquiredJobs}"/>
					<ui:param name="header" value="Reco Acquired Jobs"/>
				</ui:include>
			</rich:panel>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
				<ui:include src="singlejob.jsp">
					<ui:param name="id" value="recoMergedAcquiredJobsTable"/>
					<ui:param name="listFromBean" value="#{runBean.recoMergedAcquiredJobs}"/>
					<ui:param name="header" value="Reco Merged Acquired Jobs"/>
				</ui:include>
			</rich:panel>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
				<ui:include src="singlejob.jsp">
					<ui:param name="id" value="alcaSkimAcquiredJobsTable"/>
					<ui:param name="listFromBean" value="#{runBean.alcaSkimAcquiredJobs}"/>
					<ui:param name="header" value="Alca Skim Acquired Jobs"/>
				</ui:include>
			</rich:panel>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
				<ui:include src="singlejob.jsp">
					<ui:param name="id" value="alcaSkimMergedAcquiredJobsTable"/>
					<ui:param name="listFromBean" value="#{runBean.alcaSkimMergedAcquiredJobs}"/>
					<ui:param name="header" value="Alka Skim Merged Acquired Jobs"/>
				</ui:include>
			</rich:panel>

	
		</h:panelGrid>

</body>
</html>
