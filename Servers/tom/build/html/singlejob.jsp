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
		var="jobVar">
		<f:facet name="header"> <h:outputText value="#{header}" /></f:facet>
		<rich:column sortBy="#{jobVar.jobId}">
			<f:facet name="header">	<h:outputText value="Job Id"/></f:facet>
			<a href="filedetail.jsf?reload=true&amp;jobId=#{jobVar.jobId}"><h:outputText value="#{jobVar.jobId}"/></a>
		</rich:column> 
		<rich:column sortBy="#{jobVar.name}">
			<f:facet name="header">	<h:outputText value="Job Name"/></f:facet>
			<h:outputText value="#{jobVar.name}"/>
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
		<rich:column sortBy="#{jobVar.status}">
			<f:facet name="header">	<h:outputText value="Status"/></f:facet>
			<h:outputText value="#{jobVar.status}"/>
		</rich:column> 

		<f:facet name="footer">
			<rich:datascroller ajaxSingle="true"
				for="#{id}"
				reRender="form1"
				maxPages="10"
				rendered="true"/>
		</f:facet>
	</rich:dataTable>

</body>
</html>
