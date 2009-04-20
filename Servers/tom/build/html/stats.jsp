 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>
	<style>
		.label{font-size:xx-small;
		}
	</style>

	<h:panelGrid columns="4">
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<rich:simpleTogglePanel switchType="client" label="Repack stats">
				<h:panelGrid columns="2">
					<h:outputText value="New Jobs" styleClass="label"/>
					<h:outputText value="#{runBean.repackedStat.newJobs}" styleClass="label"/>
					<h:outputText value="Used Jobs" styleClass="label"/>
					<h:outputText value="#{runBean.repackedStat.usedJobs}" styleClass="label"/>
					<h:outputText value="Successful Jobs" styleClass="label"/>
					<h:outputText value="#{runBean.repackedStat.successfulJobs}" styleClass="label"/>
					<h:outputText value="Failed Jobs" styleClass="label"/>
					<h:outputText value="#{runBean.repackedStat.failedJobs}" styleClass="label"/>
				</h:panelGrid>
			</rich:simpleTogglePanel>
		</rich:panel>


		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlestat.jsp">
				<ui:param name="aJobVal" value="#{runBean.mergedStat.acquiredJobs}"/>
				<ui:param name="sJobVal" value="#{runBean.mergedStat.successfulJobs}"/>
				<ui:param name="fJobVal" value="#{runBean.mergedStat.failedJobs}"/>
				<ui:param name="header" value="All Merged stats"/>
			</ui:include>
		</rich:panel>
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlestat.jsp">
				<ui:param name="aJobVal" value="#{runBean.recoStat.acquiredJobs}"/>
				<ui:param name="sJobVal" value="#{runBean.recoStat.successfulJobs}"/>
				<ui:param name="fJobVal" value="#{runBean.recoStat.failedJobs}"/>
				<ui:param name="header" value="Reco stats"/>
			</ui:include>
		</rich:panel>
		<rich:panel bodyClass="rich-laguna-panel-no-header">
			<ui:include src="singlestat.jsp">
				<ui:param name="aJobVal" value="#{runBean.alcaSkimStat.acquiredJobs}"/>
				<ui:param name="sJobVal" value="#{runBean.alcaSkimStat.successfulJobs}"/>
				<ui:param name="fJobVal" value="#{runBean.alcaSkimStat.failedJobs}"/>
				<ui:param name="header" value="AlcaSkim stats"/>
			</ui:include>
		</rich:panel>

	</h:panelGrid>
</body>
</html>
