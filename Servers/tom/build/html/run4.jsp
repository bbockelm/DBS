 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>
        <h:form binding="#{runBean.form1}" id="form1">
                <h:outputText value="Tier 0 Monitoring" style="font-size:xx-large;"/>
                <rich:tabPanel id="BottomPanel"><rich:tab label="Runs" id="outTab">
                        <rich:dataTable columnClasses="column-index"
                                id="dataTable1"
                                rendered="true"
                                rowClasses="list-row3"
                                rows="20"
                                styleClass="list-table1"
                                title="Similar Names in DBS"
                                value="#{runBean.runs}"
                                var="resultVar">
				<rich:column sortBy="#{resultVar.id}">
					<f:facet name="header">	<h:outputText value="RUN_ID"/></f:facet>
					<h:outputText value="#{resultVar.id}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.start}">
					<f:facet name="header">	<h:outputText value="Start Time"/></f:facet>
					<h:outputText value="#{resultVar.start}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.end}">
					<f:facet name="header">	<h:outputText value="End Time"/></f:facet>
					<h:outputText value="#{resultVar.end}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.process}">
					<f:facet name="header">	<h:outputText value="Process"/></f:facet>
					<h:outputText value="#{resultVar.process}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.version}">
					<f:facet name="header">	<h:outputText value="Version"/></f:facet>
					<h:outputText value="#{resultVar.version}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.hltKey}">
					<f:facet name="header">	<h:outputText value="HLT Key"/></f:facet>
					<h:outputText value="#{resultVar.hltKey}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.acqEra}">
					<f:facet name="header">	<h:outputText value="Acq Era"/></f:facet>
					<h:outputText value="#{resultVar.acqEra}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.status}">
					<f:facet name="header">	<h:outputText value="Status"/></f:facet>
					<h:outputText value="#{resultVar.status}"/>
				</rich:column> 
				<rich:column sortBy="#{resultVar.streamers}">
					<f:facet name="header">	<h:outputText value="Streamers"/></f:facet>
					<h:outputText value="#{resultVar.streamers}"/>
				</rich:column> 

                                <f:facet name="footer">
                                        <rich:datascroller ajaxSingle="true"
                                                for="dataTable1"
                                                reRender="dataTable1"
                                                maxPages="10"
                                                rendered="true"/>
                                </f:facet>
                        </rich:dataTable>
                </rich:tab></rich:tabPanel>
        </h:form>
</body>
</html>
