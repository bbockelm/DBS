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
				rows="10"
				styleClass="list-table1"
				title="#{header}"
				value="#{listFromBean}"
				var="statVar">
				<f:facet name="header"> <h:outputText value="#{header}" /></f:facet>
				<rich:column sortBy="#{statVar.jobId}">
					<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
					<h:outputText value="#{statVar.jobId}"/>
				</rich:column> 
				<rich:column sortBy="#{statVar.name}">
					<f:facet name="header">	<h:outputText value="Name"/></f:facet>
					<h:outputText value="#{statVar.name}"/>
				</rich:column> 
				<rich:column sortBy="#{statVar.status}">
					<f:facet name="header">	<h:outputText value="Status"/></f:facet>
					<h:outputText value="#{statVar.status}"/>
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
