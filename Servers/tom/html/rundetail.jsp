 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<div align="center">
	<ui:include src="menu.jsp"/>
	<h:form binding="#{runDetailBean.form1}" id="form1">
		<h:panelGrid columns="1">
			<rich:tabPanel id="RunDetailPanel"><rich:tab label="Run Detail" id="runDetailTab">
				<rich:dataTable columnClasses="column-index"
					id="runDetailTable"
					rendered="true"
					rowClasses="list-row3"
					rows="10"
					styleClass="list-table1"
					title="All Runs"
					value="#{runDetailBean.runDetails}"
					var="runDetailVar">

					<f:facet name="header">
						<rich:columnGroup>
							<rich:column rowspan="2"><h:outputText value="Dataset"/></rich:column>
							<rich:column colspan="2"><h:outputText value="Repack"/></rich:column>
							<rich:column colspan="5"><h:outputText value="Reco"/></rich:column>
							<rich:column colspan="4"><h:outputText value="AlcaSkim"/></rich:column>
							<rich:column colspan="5"><h:outputText value="Tier1Skim"/></rich:column>
							<rich:column breakBefore="true"><h:outputText value="ProcVersion"/></rich:column>
							<rich:column><h:outputText value="HLT Debug"/></rich:column>
							<rich:column><h:outputText value="Enabled"/></rich:column>
							<rich:column><h:outputText value="CMSSW Version"/></rich:column>
							<rich:column><h:outputText value="Global Tag"/></rich:column>
							<rich:column><h:outputText value="Config URL"/></rich:column>
							<rich:column><h:outputText value="Proc Version"/></rich:column>
							<rich:column><h:outputText value="Enabled"/></rich:column>
							<rich:column><h:outputText value="CMSSW Version"/></rich:column>
							<rich:column><h:outputText value="Config URL"/></rich:column>
							<rich:column><h:outputText value="Proc Version"/></rich:column>
							<rich:column><h:outputText value="Skim Name"/></rich:column>
							<rich:column><h:outputText value="Tier Name"/></rich:column>
							<rich:column><h:outputText value="CMSSW Version"/></rich:column>
							<rich:column><h:outputText value="Config URL"/></rich:column>
							<rich:column><h:outputText value="Proc Version"/></rich:column>
						</rich:columnGroup>
					</f:facet>
					<rich:column><h:outputText value="#{runDetailVar.dataset}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.repack.procVersion}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.repack.hltDebug}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.reco.recoEnabled}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.reco.cmsswVersion}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.reco.globalTag}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.reco.configUrl}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.reco.procVersion}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.alcaSkim.alcaEnabled}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.alcaSkim.cmsswVersion}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.alcaSkim.configUrl}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.alcaSkim.procVersion}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.tier1Skim.skimName}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.tier1Skim.tierName}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.tier1Skim.cmsswVersion}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.tier1Skim.configUrl}"/></rich:column> 
					<rich:column><h:outputText value="#{runDetailVar.tier1Skim.procVersion}"/></rich:column> 
					<f:facet name="footer">
						<rich:datascroller ajaxSingle="true"
							for="runDetailTable"
							reRender="runDetailTable"
							maxPages="10"
							rendered="true"/>
					</f:facet>
				</rich:dataTable>
			</rich:tab></rich:tabPanel>


 	                <rich:tabPanel id="JobsPanel"><rich:tab label="Jobs" id="jobTab">
				<ui:include src="jobs.jsp"/>
			</rich:tab></rich:tabPanel>
			<rich:tabPanel id="FilesPanel"><rich:tab label="Files" id="fileTab">
				<ui:include src="files.jsp"/>
			</rich:tab></rich:tabPanel>

		</h:panelGrid>
	</h:form>
	</div>
</body>
</html>
