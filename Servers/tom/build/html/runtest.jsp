 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

	<div align="center">
	<h:form binding="#{runBean.form1}" id="form1">
		<h:outputText value="Tier 0 Monitoring" style="font-size:xx-large;"/>
		<h:panelGrid columns="1">
			<rich:tabPanel id="RunPanel1"><rich:tab label="Runs" id="runTab">
				<rich:dataTable columnClasses="column-index"
					id="runTable1"
					rendered="true"
					rowClasses="list-row3"
					rows="10"
					styleClass="list-table1"
					title="All Runs"
					value="#{runBean.runs}"
					var="runVar">
					<rich:column sortBy="#{runVar.id}">
						<f:facet name="header">	<h:outputText value="Run Id"/></f:facet>
						<h:commandLink action="success">
							<f:setPropertyActionListener target="#{runDetailBean.runId}" value="#{runVar.id}" />
							<h:outputText value="#{runVar.id}"/>
						</h:commandLink>
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
							for="runTable1"
							reRender="runTable1"
							maxPages="10"
							rendered="true"/>
					</f:facet>
				</rich:dataTable>
			</rich:tab></rich:tabPanel>
                <rich:tabPanel id="StatsPanel"><rich:tab label="Stats" id="statTab">
			<ui:include src="statstest.jsp"/>
		</rich:tab></rich:tabPanel>
		<rich:tabPanel id="JobsPanel"><rich:tab label="Jobs" id="jobTab">
			<ui:include src="jobstest.jsp"/>
		</rich:tab></rich:tabPanel>





		</h:panelGrid>
	</h:form>
	</div>
</body>
</html>
