 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<div align="center">
	<rich:panel bodyClass="rich-laguna-panel-no-header" style="text-align: center;">
	<h:form binding="#{runBean.form1}" id="form1">
		<h:outputText value="Tier 0 Monitoring" style="font-size:xx-large;"/>
		<h:panelGrid columns="1">
			<rich:tabPanel id="RunPanel"><rich:tab label="Runs" id="runTab">
				<rich:dataTable columnClasses="column-index"
					id="runTable"
					rendered="true"
					rowClasses="list-row3"
					rows="10"
					styleClass="list-table1"
					title="All Runs"
					value="#{runBean.runs}"
					var="runVar">
					<rich:column sortBy="#{runVar.id}">
						<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
						<h:outputText value="#{runVar.id}"/>
					</rich:column> 
					<rich:column sortBy="#{runVar.start}">
						<f:facet name="header">	<h:outputText value="Start"/></f:facet>
						<h:graphicImage value="/html/images/clock.png" />
						<rich:toolTip><h:outputText value="#{runVar.start}"/></rich:toolTip>
					</rich:column> 
					<rich:column sortBy="#{runVar.end}">
						<f:facet name="header">	<h:outputText value="End"/></f:facet>
						<h:graphicImage value="/html/images/clock.png" />
						<rich:toolTip><h:outputText value="#{runVar.end}"/></rich:toolTip>
					</rich:column> 
					<rich:column sortBy="#{runVar.process}">
						<f:facet name="header">	<h:outputText value="Process"/></f:facet>
						<h:outputText value="#{runVar.process}"/>
					</rich:column> 
					<rich:column sortBy="#{runVar.version}">
						<f:facet name="header">	<h:outputText value="Version"/></f:facet>
						<h:outputText value="#{runVar.version}"/>
					</rich:column> 
					<rich:column sortBy="#{runVar.hltKey}">
						<f:facet name="header">	<h:outputText value="HLT Key"/></f:facet>
						<h:outputText value="#{runVar.hltKey}"/>
					</rich:column> 
					<rich:column sortBy="#{runVar.acqEra}">
						<f:facet name="header">	<h:outputText value="Acq Era"/></f:facet>
						<h:outputText value="#{runVar.acqEra}"/>
					</rich:column> 
					<rich:column sortBy="#{runVar.status}">
						<f:facet name="header">	<h:outputText value="Status"/></f:facet>
						<h:outputText value="#{runVar.status}"/>
					</rich:column> 
					<rich:column sortBy="#{runVar.streamers}">
						<f:facet name="header">	<h:outputText value="Streamers"/></f:facet>
						<h:outputText value="#{runVar.streamers}"/>
					</rich:column> 
					<f:facet name="footer">
						<rich:datascroller ajaxSingle="true"
							for="runTable"
							reRender="runTable"
							maxPages="10"
							rendered="true"/>
					</f:facet>
				</rich:dataTable>
			</rich:tab></rich:tabPanel>

			<ui:include src="stats.jsp"/>


	                <rich:tabPanel id="JobsPanel"><rich:tab label="Jobs" id="jobTab">
				<h:panelGrid columns="2">
					<rich:dataTable columnClasses="column-index"
						id="repackRunningJobsTable"
						rendered="true"
						rowClasses="list-row3"
						rows="10"
						styleClass="list-table1"
						title="Repack Jobs"
						value="#{runBean.repackRunningJobs}"
						var="jobVar">
						<f:facet name="header"> <h:outputText value="Repack Running Jobs" /></f:facet>
						<rich:column sortBy="#{jobVar.jobId}">
							<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
							<h:outputText value="#{jobVar.jobId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.runId}">
							<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
							<h:outputText value="#{jobVar.runId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.definitionTime}">
							<f:facet name="header">	<h:outputText value="Definition Time"/></f:facet>
							<h:outputText value="#{jobVar.definitionTime}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.numberOfFiles}">
							<f:facet name="header">	<h:outputText value="# of Files"/></f:facet>
							<h:outputText value="#{jobVar.numberOfFiles}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.dataset}">
							<f:facet name="header">	<h:outputText value="Dataset"/></f:facet>
							<h:outputText value="#{jobVar.dataset}"/>
						</rich:column> 
						<f:facet name="footer">
							<rich:datascroller ajaxSingle="true"
								for="repackRunningJobsTable"
								reRender="repackRunningJobsTable"
								maxPages="10"
								rendered="true"/>
						</f:facet>
					</rich:dataTable>

					<rich:dataTable columnClasses="column-index"
						id="repackMergedAcquiredJobsTable"
						rendered="true"
						rowClasses="list-row3"
						rows="10"
						styleClass="list-table1"
						title="Repack Merged Acquired Jobs"
						value="#{runBean.repackMergedAcquiredJobs}"
						var="jobVar">
						<f:facet name="header"> <h:outputText value="Repack Merged Acquired Jobs" /></f:facet>
						<rich:column sortBy="#{jobVar.jobId}">
							<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
							<h:outputText value="#{jobVar.jobId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.runId}">
							<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
							<h:outputText value="#{jobVar.runId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.definitionTime}">
							<f:facet name="header">	<h:outputText value="Definition Time"/></f:facet>
							<h:outputText value="#{jobVar.definitionTime}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.numberOfFiles}">
							<f:facet name="header">	<h:outputText value="# of Files"/></f:facet>
							<h:outputText value="#{jobVar.numberOfFiles}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.dataset}">
							<f:facet name="header">	<h:outputText value="Dataset"/></f:facet>
							<h:outputText value="#{jobVar.dataset}"/>
						</rich:column> 
						<f:facet name="footer">
							<rich:datascroller ajaxSingle="true"
								for="repackMergedAcquiredJobsTable"
								reRender="repackMergedAcquiredJobsTable"
								maxPages="10"
								rendered="true"/>
						</f:facet>
					</rich:dataTable>


					<rich:dataTable columnClasses="column-index"
						id="recoAcquiredJobsTable"
						rendered="true"
						rowClasses="list-row3"
						rows="10"
						styleClass="list-table1"
						title="Reco Acquired Jobs"
						value="#{runBean.recoAcquiredJobs}"
						var="jobVar">
						<f:facet name="header"> <h:outputText value="Reco Acquired Jobs" /></f:facet>
						<rich:column sortBy="#{jobVar.jobId}">
							<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
							<h:outputText value="#{jobVar.jobId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.runId}">
							<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
							<h:outputText value="#{jobVar.runId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.definitionTime}">
							<f:facet name="header">	<h:outputText value="Definition Time"/></f:facet>
							<h:outputText value="#{jobVar.definitionTime}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.numberOfFiles}">
							<f:facet name="header">	<h:outputText value="# of Files"/></f:facet>
							<h:outputText value="#{jobVar.numberOfFiles}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.dataset}">
							<f:facet name="header">	<h:outputText value="Dataset"/></f:facet>
							<h:outputText value="#{jobVar.dataset}"/>
						</rich:column> 
						<f:facet name="footer">
							<rich:datascroller ajaxSingle="true"
								for="recoAcquiredJobsTable"
								reRender="recoAcquiredJobsTable"
								maxPages="10"
								rendered="true"/>
						</f:facet>
					</rich:dataTable>


					<rich:dataTable columnClasses="column-index"
						id="recoMergedAcquiredJobsTable"
						rendered="true"
						rowClasses="list-row3"
						rows="10"
						styleClass="list-table1"
						title="Reco Merged Acquired Jobs"
						value="#{runBean.recoMergedAcquiredJobs}"
						var="jobVar">
						<f:facet name="header"> <h:outputText value="Reco Merged Acquired Jobs" /></f:facet>
						<rich:column sortBy="#{jobVar.jobId}">
							<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
							<h:outputText value="#{jobVar.jobId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.runId}">
							<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
							<h:outputText value="#{jobVar.runId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.definitionTime}">
							<f:facet name="header">	<h:outputText value="Definition Time"/></f:facet>
							<h:outputText value="#{jobVar.definitionTime}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.numberOfFiles}">
							<f:facet name="header">	<h:outputText value="# of Files"/></f:facet>
							<h:outputText value="#{jobVar.numberOfFiles}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.dataset}">
							<f:facet name="header">	<h:outputText value="Dataset"/></f:facet>
							<h:outputText value="#{jobVar.dataset}"/>
						</rich:column> 
						<f:facet name="footer">
							<rich:datascroller ajaxSingle="true"
								for="recoMergedAcquiredJobsTable"
								reRender="recoMergedAcquiredJobsTable"
								maxPages="10"
								rendered="true"/>
						</f:facet>
					</rich:dataTable>

	
					<rich:dataTable columnClasses="column-index"
						id="alcaSkimAcquiredJobsTable"
						rendered="true"
						rowClasses="list-row3"
						rows="10"
						styleClass="list-table1"
						title="Alca Skim Acquired Jobs"
						value="#{runBean.alcaSkimAcquiredJobs}"
						var="jobVar">
						<f:facet name="header"> <h:outputText value="Alca Skim Acquired Jobs" /></f:facet>
						<rich:column sortBy="#{jobVar.jobId}">
							<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
							<h:outputText value="#{jobVar.jobId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.runId}">
							<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
							<h:outputText value="#{jobVar.runId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.definitionTime}">
							<f:facet name="header">	<h:outputText value="Definition Time"/></f:facet>
							<h:outputText value="#{jobVar.definitionTime}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.numberOfFiles}">
							<f:facet name="header">	<h:outputText value="# of Files"/></f:facet>
							<h:outputText value="#{jobVar.numberOfFiles}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.dataset}">
							<f:facet name="header">	<h:outputText value="Dataset"/></f:facet>
							<h:outputText value="#{jobVar.dataset}"/>
						</rich:column> 
						<f:facet name="footer">
							<rich:datascroller ajaxSingle="true"
								for="alcaSkimAcquiredJobsTable"
								reRender="alcaSkimAcquiredJobsTable"
								maxPages="10"
								rendered="true"/>
						</f:facet>
					</rich:dataTable>


					<rich:dataTable columnClasses="column-index"
						id="alcaSkimMergedAcquiredJobsTable"
						rendered="true"
						rowClasses="list-row3"
						rows="10"
						styleClass="list-table1"
						title="Alka Skim Merged Acquired Jobs"
						value="#{runBean.alcaSkimMergedAcquiredJobs}"
						var="jobVar">
						<f:facet name="header"> <h:outputText value="Alka Skim Merged Acquired Jobs" /></f:facet>
						<rich:column sortBy="#{jobVar.jobId}">
							<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
							<h:outputText value="#{jobVar.jobId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.runId}">
							<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
							<h:outputText value="#{jobVar.runId}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.definitionTime}">
							<f:facet name="header">	<h:outputText value="Definition Time"/></f:facet>
							<h:outputText value="#{jobVar.definitionTime}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.numberOfFiles}">
							<f:facet name="header">	<h:outputText value="# of Files"/></f:facet>
							<h:outputText value="#{jobVar.numberOfFiles}"/>
						</rich:column> 
						<rich:column sortBy="#{jobVar.dataset}">
							<f:facet name="header">	<h:outputText value="Dataset"/></f:facet>
							<h:outputText value="#{jobVar.dataset}"/>
						</rich:column> 
						<f:facet name="footer">
							<rich:datascroller ajaxSingle="true"
								for="alcaSkimMergedAcquiredJobsTable"
								reRender="alcaSkimMergedAcquiredJobsTable"
								maxPages="10"
								rendered="true"/>
						</f:facet>
					</rich:dataTable>



	
				</h:panelGrid>
			</rich:tab></rich:tabPanel>


		</h:panelGrid>
	</h:form>
	</rich:panel>
	</div>
</body>
</html>
