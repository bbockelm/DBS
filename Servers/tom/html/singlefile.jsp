 <html xmlns="http://www.w3.org/1999/xhtml"
xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:rich="http://richfaces.org/rich"
xmlns:a4j="http://richfaces.org/a4j">
<body>

			<rich:dataTable columnClasses="column-index"
				id="#{id}"
				rendered="true"
				rowClasses="list-row3"
				rows="5"
				styleClass="list-table1"
				title="#{header}"
				value="#{listFromBean}"
				var="fileVar">
				<f:facet name="header"> <h:outputText value="#{header}" /></f:facet>
				<rich:column sortBy="#{fileVar.id}">
					<f:facet name="header">	<h:outputText value="Id"/></f:facet>
					<h:outputText value="#{fileVar.id}"/>
				</rich:column> 
				<rich:column sortBy="#{fileVar.fileSize}">
					<f:facet name="header">	<h:outputText value="File Size"/></f:facet>
					<h:outputText value="#{fileVar.fileSize}"/>
				</rich:column> 
				<rich:column sortBy="#{fileVar.events}">
					<f:facet name="header">	<h:outputText value="Events"/></f:facet>
					<h:outputText value="#{fileVar.events}"/>
				</rich:column> 
				<rich:column sortBy="#{fileVar.primaryDataset}">
					<f:facet name="header">	<h:outputText value="Primary Dataset"/></f:facet>
					<h:outputText value="#{fileVar.primaryDataset}"/>
				</rich:column> 	
				<rich:column sortBy="#{fileVar.processedDataset}">
					<f:facet name="header">	<h:outputText value="Processed Dataset"/></f:facet>
					<h:outputText value="#{fileVar.processedDataset}"/>
				</rich:column> 
				<rich:column sortBy="#{fileVar.dataTier}">
					<f:facet name="header">	<h:outputText value="Data Tier"/></f:facet>
					<h:outputText value="#{fileVar.dataTier}"/>
				</rich:column> 
				<rich:column sortBy="#{fileVar.lfn}">
					<f:facet name="header">	<h:outputText value="lfn"/></f:facet>
					<h:graphicImage value="/html/images/doc.png" />
					<rich:toolTip><h:outputText value="#{fileVar.lfn}"/></rich:toolTip>
				</rich:column> 
				<f:facet name="footer">
					<rich:datascroller ajaxSingle="true"
						for="#{id}"
						reRender="#{id}"
						maxPages="10"
						rendered="true"/>
				</f:facet>
			</rich:dataTable>

</body>
</html>
